package ru.math.spbu.pk.CoinExchange;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import de.vogella.algorithms.sort.quicksort.Quicksort;

/**
 * Automated Teller Machine for money exchange
 * 
 * @author Alpen Ditrix
 * 
 */
public class ATM {

	/**
	 * Worths of coins, which may be given to user
	 */
	private final int[] availableCoinWorth;

	/**
	 * Default worths is 1 3 5 and 10
	 */
	public ATM() {
		int[] w = { 1, 3, 5, 10 };
		availableCoinWorth = w;
	}

	/**
	 * Set your custom available worths.
	 * Also this constructor will remove all duplicated worths
	 * 
	 * @param worths
	 *            array of worths of coins, which you want to use
	 */
	public ATM(int[] worths) {
		if (worths.length < 1) {
			throw new RuntimeException("You must use at least one coin");
		}

		// checkForDuplicates
		boolean removedSomething = false;
		List<Integer> realWorths = new ArrayList<Integer>();
		for (int checkMe : worths) {
			if (!realWorths.contains(checkMe)) {
				realWorths.add(checkMe);
			} else {
				removedSomething = true;
			}
		}

		if (removedSomething) {
			// create new array
			int[] newArray = new int[realWorths.size()];
			for (int i = 0; i < realWorths.size(); i++) {
				newArray[i] = realWorths.get(i);
			}
			availableCoinWorth = newArray;
		} else {
			// just copy
			availableCoinWorth = worths;
		}

		// descending sorting => highest is first, lowest is last
		Quicksort.sort(availableCoinWorth, false);

	}

	/**
	 * That method starts support-service and sets running-flag. Also it may return {@link ListOfCases#WRONG_INPUT} for wrong money amount
	 * @param moneyToExchange
	 *            how much to exchange
	 * @return list of options "how to exchange"
	 */
	public ListOfCases exchange(int moneyToExchange) {
		running = true;
		synchronized (this) {
			currentUID = new Random().nextLong();
			new WaiterOf(this, currentUID).start();
		}
		if (moneyToExchange < 1) {
			return ListOfCases.WRONG_INPUT;
		}
		return computeCases(moneyToExchange);
	}

	/**
	 * This method creates storage structure, marks timedout case-lists and cares about {@link StackOverflowError}.
	 * Also here will be set off the flag "running"
	 * @param given how much money was given to ATM
	 * @return list of options "how to exchange"
	 */
	private ListOfCases computeCases(int given) {
		// create empty list of lists
		ListOfCases cases = new ListOfCases();
		long start = System.currentTimeMillis();
		try {
			// external cycle "with which coin to start"
			for (int i = 0; i < availableCoinWorth.length; i++) {
				if (isRunning()) {
					computeCases0(given, i, new Case(), cases);
				} else {
					System.out.println(System.currentTimeMillis() - start);
					cases.setTimedOut();
					// throw new RuntimeException("Computation timed out!");
					return cases;
				}
			}
		} catch (StackOverflowError e) {
			System.err.println("Oops. I've got stack owerflow.");
		} catch (Exception e) {
			System.err.println("Oops. Something gone wrong.");
		}
		running = false;
		return cases;
	}

	/**
	 * 
	 * @param given
	 *            how much money is not exchanged already
	 * @param i
	 *            index of coin in {@link #availableCoinWorth} which I want to
	 *            try to use now
	 * @param c
	 *            HashMap, which stores current case. Keys == coin worth, values
	 *            == amount of used coins of this worth
	 * @param cases
	 *            list, where current case will be finally added
	 */
	private void computeCases0(int given, int i, Case c, ListOfCases cases) {
		if (!isRunning()) {
			return;
		}

		// if I can use this coin
		if (given >= availableCoinWorth[i]) {
			// ...increment amount of used coins of this worth
			c.incrementCoin(availableCoinWorth[i]);
		} else {
			// if I have no coins with fewer worth, I must end this Case and add
			// it to list
			if (i == availableCoinWorth.length - 1) {
				if (given > 0) {
					// I have no more coins to exchange money. E.g. you can't
					// exchange 3$ using only 2$ coins
					throw new RuntimeException(
							"This ATM can not exchange this money");
				}
				cases.add(c);
			}
			// so I can't use this coin. Go to upper method to the
			// "recursion cycle"
			// and choose another (coin)
			return;
		}
		// just recursion cycle
		for (int j = 0; j + i < availableCoinWorth.length; j++) {
			/*
			 * 1)decrement used coin given -= availableCoinWorth[i];
			 * 
			 * 2)use offset i+=j;
			 * 
			 * 3)create new case-branch Case next = new Case(c);
			 */
			computeCases0(given - availableCoinWorth[i], i + j, new Case(c),
					cases);
		}
	}

	public String toString() {
		return Arrays.toString(availableCoinWorth);
	}

	/**
	 * @param UID unique calculation ID
	 * @return if it is specified calculation and if it's running
	 */
	public boolean isRunning(long UID) {
		synchronized (this) {
//			System.out.println("Checked");
			return running && UID == currentUID;
		}
	}

	/**
	 * @return if that calculation is running
	 */
	public boolean isRunning() {
		synchronized (this) {
//			System.out.println("isRunning");
			return running;
		}
	}

	/**
	 * Figures out is some computation may be running, or it's need to be
	 * terminated becouse of something
	 */
	private boolean running;

	/**
	 * It's needed to know is some computation with specified Id has beed
	 * complited
	 */
	private long currentUID;

	/**
	 * Computation will stop on next reaching if(running) marker
	 * @param UID Id of calculatioin to stop
	 * @return if termination was successful
	 */
	public boolean askToTerminateComputation(long UID) {
		synchronized (this) {
//			System.out.println("Entered");
			if (isRunning(UID)) {
//				System.out.println("Came through");
				System.err
						.println("The calculation has been going on for too long. Are you sure you want to continue? (Enter y\n for \"yes I want\"\\\"no, I don't want\"");
				boolean gotIt = false;
				while (!gotIt) {
					try {
						String s = new BufferedReader(new InputStreamReader(
								System.in)).readLine();
						if (s.toLowerCase().equals("y")
								|| s.toLowerCase().equals("ó")) {
							// let's stop it
							running = false;
							gotIt = true;
						} else if (s.toLowerCase().equals("n")) {
							// kk. Just go on
							gotIt = true;
						}
					} catch (Exception e) {

					}
				}
			}
		}
		return !running;
//		System.out.println("Waiter got answer and exited from sync-block");
	}
}
