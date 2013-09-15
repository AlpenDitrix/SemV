package ru.math.spbu.pk.CoinExchange;

import java.util.ArrayList;

/**
 * Just {@link ArrayList} of {@link Case}s with some environment
 * 
 * @author Alpen Ditrix
 * 
 */
public class ListOfCases extends ArrayList<Case> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8037932627088736022L;

	/**
	 * This instance will be returned if ATM got wrong input
	 */
	public static final ListOfCases WRONG_INPUT = new ListOfCases() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6741001396872270661L;

		public String toString() {
			bad();
			return Messages.getString("ListOfCases.fakeMoney");
		}

	};

	/**
	 * Shows "if that calculation was timed out, so this list isn't full"
	 */
	private boolean timedOut = false;
	/**
	 * Shows how much timedOuts were
	 */
	private static int timedOutCounter = 0;
	/**
	 * Show how much times someone used wrong input
	 */
	private static int badCounter = 0;

	public String toString() {
		if (size() == 0) {
			return Messages.getString("ListOfCases.noCases");
		}
		if (size() > 0) {
			StringBuilder sb = new StringBuilder();
			if (timedOut) {
				sb.append(Messages.getString("ListOfCases.timeout"));
			}
			sb.append(String.format(Messages.getString("ListOfCases.hereIs"),
					Integer.toString(size()), size() > 1 ? "s" : "")); 
			for (Case c : this) {
				sb.append("\n_______\n");
				sb.append(c.toString());
			}
			if (timedOut) {
				sb.append(Messages.getString("ListOfCases.timeout"));
			}
			return sb.toString();

		} else {
			return Messages.getString("ListOfCases.failedExchange");
		}
	}

	/**
	 * Sets flag and checks counter
	 */
	public void setTimedOut() {
		timedOut = true;
		timedOutCounter++;
		checkCounters();
	}

	/**
	 * Sets flag and check counter
	 */
	private static void bad() {
		badCounter++;
		checkCounters();
	}

	@SuppressWarnings("javadoc")
	private static void checkCounters() {
		if (timedOutCounter == 3) {
			System.err.println(Messages.getString("ListOfCases.tooSlow"));
		}
		if (badCounter == 3) {
			System.err
					.println(Messages.getString("ListOfCases.nigga"));
		}
	}

}
