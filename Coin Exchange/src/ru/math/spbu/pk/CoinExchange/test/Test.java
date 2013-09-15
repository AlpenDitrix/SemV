package ru.math.spbu.pk.CoinExchange.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.math.spbu.pk.CoinExchange.ATM;

public class Test {

	static Parser p = new Parser();

	public static void main(String[] s) throws IOException {
		
		//1)SET UP THE ATM
		ATM atm = new ATM(readWorths());
		
		//2)WORK WITH HIM
		while (true) {
			try {
				System.err.println(atm.exchange(readMoney()));
			} catch (NumberFormatException e) {
				System.err
						.println("Please, use only digits. I can't understand what do you mean");
			}
		}
	}

	private static int readMoney() throws IOException {
		try {
			Thread.sleep(15);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out
				.println("\nEnter how much money do you want to exchange\nEnter any non-number string to exit");
		return p.nextInteger();
	}

	private static int[] readWorths() throws IOException {
		System.out
				.println("Enter which worhts must be available in your ATM\nUse \" \\t\\n\\r\\f\" as delimeters and end input with '.' (dot)\nOr type just a dot to use default (1 3 5 10)");
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
						.println("Please, use only digits, delimeters or dot as input. I can't understand what do you mean");
			}
			s = p.nextString();
		}

		if (list.size() < 1) {
			int[] v = { 1, 3, 5, 10 };
			System.out.println("\nGood. You've  chosen that worths: "
					.concat(Arrays.toString(v)));
			return v;
		} else {
			System.out.println("\nGood. You've  chosen that worths: "
					.concat(list.toString()));
			int[] v = new int[list.size()];
			for (int i = 0; i < v.length; i++) {
				v[i] = list.get(i);
			}
			return v;
		}
	}
}
