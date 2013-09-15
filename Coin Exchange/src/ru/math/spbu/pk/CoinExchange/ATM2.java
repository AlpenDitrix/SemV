package ru.math.spbu.pk.CoinExchange;

import java.util.Arrays;
import de.vogella.algorithms.sort.quicksort.Quicksort;

/**
 * Automated Teller Machine
 * 
 * @author Alpen Ditrix
 * 
 */
public class ATM2 {

	private final int[] availableCoinWorth;

	/**
	 * Set default worths: 1, 3, 5 and 10
	 */
	public ATM2() {
		int[] w = { 1, 3, 5, 10 };
		availableCoinWorth = w;
	}

	/**
	 * Set your custom "available worths"
	 * 
	 * @param worths
	 *            array of worths of coins, which you want to use
	 */
	public ATM2(int[] worths) {
		availableCoinWorth = worths;
		// descending sorting => highest is first, lowest is last
		Quicksort.sort(availableCoinWorth, false);
	}

	/**
	 * 
	 * @param moneyToExchange
	 *            how much to exchange
	 * @return list of options "how to exchange"
	 */
	public ListOfCases exchange(int moneyToExchange) {
		if (moneyToExchange < 1) {
			// wrong input
			return ListOfCases.WRONG_INPUT;
		}
		return computeCases(moneyToExchange);
	}

	/**
	 * 
	 * @param moneyToExchange
	 *            how much to exchange
	 * @return list of options "how to exchange"
	 */
	private ListOfCases computeCases(int moneyToExchange) {
		// create empty list of lists
		ListOfCases cases = new ListOfCases();

		// external cycle "with which coin to start"
		for (int i = 0; i < availableCoinWorth.length; i++) {
			computeCases0(moneyToExchange, i, new Case(), cases);
		}
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
				// just add
				cases.add(c);
			}
			// can't use this coin. Go to upper method to the recursion cycle
			// and choose another (coin)
			return;
		}

		// here's recursion
		for (int j = 0; j + i < availableCoinWorth.length; j++) {
			computeCases0(given - availableCoinWorth[i], i + j, new Case(c),
					cases);
		}
	}

	public String toString() {
		return Arrays.toString(availableCoinWorth);
	}

}
