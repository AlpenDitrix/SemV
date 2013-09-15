package ru.math.spbu.pk.CoinExchange.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.math.spbu.pk.CoinExchange.ATM;

@SuppressWarnings("javadoc")
public class Test {

	/**
	 * Instance of input-helper
	 */
	static Parser p = new Parser();

	public static void main(String[] s) throws IOException {
		// 1)SET UP THE ATM
		ATM atm = new ATM(readWorths());
		// 2)WORK WITH HIM
		while (true) {
			try {
				System.err.println(atm.exchange(readMoney()));
			} catch (NumberFormatException e) {
				System.err
						.println(Messages.getString("PleaseDigits"));
			}
		}
	}

	private static int readMoney() throws IOException {
		try {
			// I need this to show messages from differet streams (System.in and
			// System.err) in a proper order
			Thread.sleep(15);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out
				.println(Messages.getString("EnterMoney"));
		return p.nextInteger();
	}

	private static int[] readWorths() throws IOException {
		System.out
				.println(Messages.getString("EnterWorths"));
		List<Integer> list = new ArrayList<>();
		String s = p.nextString();
		while (true) {
			try {
				if (s.contains(".")) {
					s = s.replace(".", "");
					if (!s.equals("")) {
						// There must not be duplicates of usable coins
						int newWorth = Integer.parseInt(s);
						if (!list.contains(newWorth)) {
							list.add(newWorth);
						}
					}
					break;
				}
				list.add(Integer.parseInt(s));
			} catch (NumberFormatException e) {
				System.err
						.println(Messages.getString("PleaseDigitsEtc"));
			}
			s = p.nextString();
		}

		// may be made better, but so will do
		if (list.size() < 1) {
			int[] v = { 1, 3, 5, 10 };
			System.out.println(Messages.getString("Good")
					.concat(Arrays.toString(v)));
			return v;
		} else {
			System.out.println(Messages.getString("Good")
					.concat(list.toString()));
			int[] v = new int[list.size()];
			for (int i = 0; i < v.length; i++) {
				v[i] = list.get(i);
			}
			return v;
		}
	}
}
