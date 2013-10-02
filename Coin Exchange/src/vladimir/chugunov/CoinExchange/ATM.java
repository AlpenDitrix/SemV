package vladimir.chugunov.CoinExchange;

import java.io.BufferedReader;
import java.io.IOException;
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
	 * Object, which processes output
	 */
	private final IPrintable printer;

	/**
	 * Set your custom available worths.
	 * 
	 * @param w
	 *            array of denominations
	 * @param p
	 *            printer
	 * 
	 */
	public ATM(int[] w, IPrintable p) {
		int[] worths = w.clone();
		printer = p;

		if (worths.length < 1) {
			throw new RuntimeException(Messages.getString("noCoins"));
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
			availableCoinWorth = w;
		}

		// descending sorting => highest is first, lowest is last
		Quicksort.sort(availableCoinWorth, false);

	}

	/**
	 * This method creates storage structure, marks timedout case-lists and
	 * cares about {@link StackOverflowError}. Also here will be set off the
	 * flag "running"
	 * 
	 * @param given
	 *            how much money was given to ATM
	 */
	private void computeCases(int given) {

		try {
			for (int i = 0; i < availableCoinWorth.length; i++) {
				if (isRunning()) {
					computeCases0(given, i, new int[availableCoinWorth.length]);
				}
			}
		} catch (StackOverflowError e) {
			System.err.println(Messages.getString("stackOverflow"));
		} catch (Exception e) {
			System.err.println(Messages.getString("SmthIsWrong"));
		}
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
	private void computeCases0(int given, int i, int[] c) {
		if (!isRunning()) {
			return;
		}

		// if I can use this coin
		if (given >= availableCoinWorth[i]) {
			// ...increment amount of used coins of this worth
			c[i]++;
		} else {
			// if I have no coins with fewer worth, I must end this Case and add
			// it to list
			if (i == availableCoinWorth.length - 1) {
				if (given > 0) {
					// I have no more coins to exchange money. E.g. you can't
					// exchange 3$ using only 2$ coins
					throw new RuntimeException(
							Messages.getString("notEnoughCoins"));
				}
				try {
					printer.print(availableCoinWorth, c);
				} catch (IOException e) {
					e.printStackTrace();
				}
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
			computeCases0(given - availableCoinWorth[i], i + j, c.clone());
		}
	}

	/**
	 * That method starts support-service and sets running-flag. Also it may
	 * return {@link ListOfCases#WRONG_INPUT} for wrong money amount
	 * 
	 * @param moneyToExchange
	 *            how much to exchange
	 */
	public void exchange(int moneyToExchange) {
		synchronized (this) {
			running = true;
			currentUID = new Random().nextLong();
			new WaiterOf(this, currentUID, 3000).start();
		}

		computeCases(moneyToExchange);

		synchronized (this) {
			running = false;
		}
	}

	/**
	 * @return if that calculation is running
	 */
	public synchronized boolean isRunning() {
		// System.out.println("isRunning");
		return running;
	}

	/**
	 * @param UID
	 *            unique calculation ID
	 * @return if it is specified calculation and if it's running
	 */
	public synchronized boolean isRunning(long UID) {
		return running && UID == currentUID;
	}

	@Override
	public String toString() {
		return Arrays.toString(availableCoinWorth);
	}

	/**
	 * Figures out is some computation may be running, or it's need to be
	 * terminated becouse of something
	 */
	private volatile boolean running;

	/**
	 * It's needed to know is some computation with specified Id has beed
	 * complited
	 */
	private long currentUID;

	/**
	 * Computation will stop on next reaching if(running) marker
	 * 
	 * @param UID
	 *            Id of calculatioin to stop
	 * @return if termination was successful
	 */
	public synchronized boolean askToTerminateComputation(long UID) {
		// System.out.println("Entered");
		if (isRunning(UID)) {
			// System.out.println("Came through");
			System.err.println(Messages.getString("timeoutQuestion"));
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
		return !running;
		// System.out.println("Waiter got answer and exited from sync-block");
	}
}
