package ru.math.spbu.pk.CoinExchange.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@SuppressWarnings("javadoc")
/**
 * 
 * Helper class allowing to read from console or file easily
 * 
 * @author Alpen Ditrix
 * 
 */
class Parser {
	
	BufferedReader br;
	StringTokenizer st;

	/**
	 * Console-reader constructor
	 */
	public Parser() {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer("");
	}

	/**
	 * File-reader construcor
	 * @param path path to file
	 */
	public Parser(String path) {
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					"in.txt")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		st = new StringTokenizer("");
	}

	Double nextDouble() throws NumberFormatException, IOException {
		String s = nextString();
		return (s != null) ? Double.parseDouble(s) : null;
	}

	Integer nextInteger() throws NumberFormatException, IOException {
		return nextInteger(10);
	}

	Integer nextInteger(int radix) throws NumberFormatException, IOException {
		String s = nextString();
		return (s != null) ? Integer.parseInt(s, radix) : null;
	}

	String nextLine() throws IOException {
		st = new StringTokenizer("");
		return br.readLine();
	}

	Long nextLong() throws NumberFormatException, IOException {
		return nextLong(10);
	}

	Long nextLong(int radix) throws NumberFormatException, IOException {
		String s = nextString();
		return (s != null) ? Long.parseLong(s, radix) : null;
	}

	String nextString() throws IOException {
		while (!st.hasMoreTokens()) {
			String s = br.readLine();
			if (s != null)
				st = new StringTokenizer(s);
			else
				return null;
		}
		return st.nextToken();
	}
}