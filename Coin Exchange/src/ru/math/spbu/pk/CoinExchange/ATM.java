package ru.math.spbu.pk.CoinExchange;

import java.util.Arrays;
import de.vogella.algorithms.sort.quicksort.Quicksort;

/**
 * Automated Teller Machine
 * 
 * @author Alpen Ditrix
 * 
 */
public class ATM {

	private final int[] availableCoinWorth;

	public ATM(int[] worths) {
		availableCoinWorth = worths;
		// descending sorting => highest is first, lowest is last
		Quicksort.sort(availableCoinWorth, false);
	}

	public ListOfCases exchange(int moneyToExchange) {
		if (moneyToExchange < 1) {
			return ListOfCases.WORNG_INPUT;
		}
		return computeCases(moneyToExchange);
	}

	private ListOfCases computeCases(int given) {
		ListOfCases cases = new ListOfCases();
		for (int i = 0; i < availableCoinWorth.length; i++) {
			computeCases0(given, i, new Case(), cases);
		}
		return cases;
	}

	private void computeCases0(int given, int i, Case c, ListOfCases cases) {
		if (given >= availableCoinWorth[i]) {
			c.incrementCoin(availableCoinWorth[i]);
		} else {
			if (given == 0 && i == availableCoinWorth.length-1) {
				cases.add(c);
			}
			return;
		}

		for (int j = 0; j + i < availableCoinWorth.length; j++) {
			computeCases0(given - availableCoinWorth[i], i + j, new Case(c), cases);
		}
	}

	public String toString() {
		return Arrays.toString(availableCoinWorth);
	}

}
