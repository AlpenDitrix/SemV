package ru.math.spbu.pk.CoinExchange;

import java.util.ArrayList;

public class ListOfCases extends ArrayList<Case> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8037932627088736022L;

	public static final ListOfCases WORNG_INPUT = new ListOfCases() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6741001396872270661L;

		public String toString() {
			return "Your money is fake";
		}
	};
	public static final ListOfCases NO_CASES = new ListOfCases() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6741001396872270661L;

		public String toString() {
			return "Unfortunately this ATM can not exchange that money";
		}
	};

	public String toString() {
		if (size() > 0) {
			StringBuilder sb = new StringBuilder("Here is ");
			sb.append(size());
			if(size()>1){
				sb.append(" cases:");
			} else {
				sb.append(" case:");
			}
//			sb.append(String.format(" case%s:", size() > 1 ? "s" : ""));
			for (Case c : this) {
				sb.append('\n');
				sb.append("_______");
				sb.append('\n');
				sb.append(c.toString());
			}
			return sb.toString();
		} else {
			return "There's no cases";
		}
	}

}
