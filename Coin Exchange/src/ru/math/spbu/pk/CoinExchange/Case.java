package ru.math.spbu.pk.CoinExchange;

import java.util.HashMap;
import java.util.Map;

/**
 * Map which stores pairs "coin worth"-"coin amount" for one variant
 * "how to exchange some money"
 * 
 * @author Alpen Ditrix
 * 
 */
public class Case extends HashMap<Integer, Integer> {

	/**
	 * Generates serial versioin ID
	 */
	private static final long serialVersionUID = 3155379491380118135L;

	/**
	 * Default constructor
	 */
	public Case() {
		super();
	}

	/**
	 * Copying constructor
	 * @param c all elements of that map will be stored in new
	 */
	public Case(Case c) {
		super();
		this.putAll(c);
		for (Map.Entry<Integer, Integer> e : c.entrySet()) {
			this.put(e.getKey(), e.getValue());
		}
	}

	/**
	 * Adds pair "worth"-"amount"
	 * @param value worth of coin
	 * @param amount how much it must be used
	 */
	public void addCoins(int value, int amount) {
		put(value, amount);
	}

	
	/**
	 * Adds one "usage" for coin by it's value
	 * @param value worth of used coin
	 */
	public void incrementCoin(int value) {
		if (containsKey(value)) {
			put(value, get(value) + 1);
		} else {
			put(value, 0 + 1);
		}
	}

	@Override
	public String toString() {
		if (size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<Integer, Integer> e : this.entrySet()) {
				sb.append(String.format("\'%s\' x %s\n", e.getKey(), e.getValue()));
			}
			return sb.toString();
		} else {
			return "Empty case";
		}

	}

}
