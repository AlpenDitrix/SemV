package ru.math.spbu.pk.CoinExchange.test;

import java.util.ArrayList;
import java.util.List;

import ru.math.spbu.pk.CoinExchange.ATM;

public class Test {

	static Parser p = new Parser();
	
	public static void main(String[] s) throws Throwable {
		ATM atm = new ATM(readWorths());
		
		try{
			while(true){
				System.err.println(atm.exchange(readMoney()));
			}
		} catch (Exception e){
			
		}
	}

	private static int readMoney() throws Throwable {
		Thread.sleep(15);
		System.out.println("\nEnter how much money do you want to exchange\nEnter any non-number string to exit");
		return p.nextInteger();
	}

	private static int[] readWorths() throws Exception {
		System.out
				.println("Enter which worhts must be available in your ATM\n Use \" \\t\\n\\r\\f\" as delimeters and end input with '.' (dot)");
		List<Integer> list = new ArrayList<>();
		String s = p.nextString();
		while (true) {
			if (s.contains(".")) {
				s = s.replace(".", "");
				if (!s.equals("")) {
					list.add(Integer.parseInt(s));
				}
				break;
			}
			list.add(Integer.parseInt(s));
			s = p.nextString();
		}

		System.out.println("\nGood. You've  chosen that worths: ".concat(list
				.toString()));

		int[] v = new int[list.size()];
		for (int i = 0; i < v.length; i++) {
			v[i] = list.get(i);
		}
		
		return v;

	}

}
