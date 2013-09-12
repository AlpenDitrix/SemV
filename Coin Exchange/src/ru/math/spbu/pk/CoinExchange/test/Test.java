package ru.math.spbu.pk.CoinExchange.test;

import ru.math.spbu.pk.CoinExchange.ATM;

public class Test {

	public static void main(String[] s) {
		int[] vals = { 1, 3, 5, 10 };
		ATM atm = new ATM(vals);
		
		for (int i = -1; i < 11; i++) {
			System.out.println(i);
			System.out.println(atm.exchange(i) + "\n\n\n");
		}
	}

}
