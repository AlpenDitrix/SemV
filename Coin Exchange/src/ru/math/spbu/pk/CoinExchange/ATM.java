package ru.math.spbu.pk.CoinExchange;

import java.util.Arrays;
import de.vogella.algorithms.sort.quicksort.Quicksort;

/**
 * Automated Teller Machine
 * 
 * @author Alpen
 * 
 */
public class ATM {

	private final int[] availableCoinValues;

	public ATM(int[] values) {
		availableCoinValues = values;
		// descending sorting => highest is first, lowest is last
		Quicksort.sort(availableCoinValues, false);
	}

	public ListOfCases exchange(int moneyToExchange) {
		if (moneyToExchange < 1) {
			return ListOfCases.WORNG_INPUT;
		}

		return computeVariants(moneyToExchange);
	}

	private ListOfCases computeVariants(int given) {
		ListOfCases cases = new ListOfCases();
		for (int i = 0; i < availableCoinValues.length; i++) {
			computeVariants0(given, i, new Case(), cases);
		}
		return cases;
	}

	private void computeVariants0(int given, int i, Case c, ListOfCases cases) {

		if (given >= availableCoinValues[i]) {
			// store value
			c.incrementCoin(availableCoinValues[i]);
		} else {
			if (given == 0 && i == availableCoinValues.length-1) {
				cases.add(c);
			}
			return;
		}

		for (int j = 0; j + i < availableCoinValues.length; j++) {
			computeVariants0(given - availableCoinValues[i], i + j, new Case(c), cases);
		}

		// if (given == 0 && c.size() > 0) {
		// cases.add(c);
		// }
	}

	public String toString() {
		return Arrays.toString(availableCoinValues);
	}

}
