package ru.math.spbu.pk.CoinExchange;

import java.util.HashMap;
import java.util.Map;

public class Case extends HashMap<Integer, Integer> {

	/**
	 * Generates serial versioin ID
	 */
	private static final long serialVersionUID = 3155379491380118135L;

	public void incrementCoin(int value) {
		if (containsKey(value)) {
			put(value, get(value) + 1);
		} else {
			put(value, 0+1);
		}
	}

	public Case(){
		super();
	}
	
	public Case(Case c){
		for(Map.Entry<Integer, Integer> e : c.entrySet()){
			this.put(e.getKey(), e.getValue());
		}
	}
	
	public void addCoins(int value, int amount) {
		put(value, amount);
	}

	@Override
	public String toString() {
		if (size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<Integer, Integer> e : this.entrySet()) {
				sb.append('\'');
				sb.append(e.getKey());
				sb.append('\'');
				sb.append(" x ");
				sb.append(e.getValue());
				sb.append("\n");
			}
			return sb.toString();
		} else {
			return "Empty case";
		}

	}

}
