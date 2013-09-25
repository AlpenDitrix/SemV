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
	 * Date of last hard change
	 */
	private static final long serialVersionUID = 20130919L;
	
	/**
	 * This instance will be returned if ATM got wrong input
	 */
	public static final ListOfCases WRONG_INPUT = new ListOfCases() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6741001396872270661L;

		@Override
		public String toString() {
			bad();
			return Messages.getString("fakeMoney");
		}

	};

	/**
	 * Shows "if that calculation was timed out, so this list isn't full"
	 */
	private boolean timedOut = false;
	/**
	 * Shows how much timedOuts were
	 */
	private transient static int timedOutCounter = 0;
	/**
	 * Show how much times someone used wrong input
	 */
	private transient static int badCounter = 0;

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
			System.err.println(Messages.getString("tooSlow"));
		}
		if (badCounter == 3) {
			System.err
					.println(Messages.getString("nigga"));
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
	 * @param worths all worths, which can be met into results
	 * @return array of case-arrays
	 */
	public int[][] toDoubleArray(int[] worths){
		Case[] c = new Case[0];
		c = this.toArray(c);
		int[][] output = new int[c.length][worths.length];
		for(int i = 0; i<c.length; i++) {
			for(int j = 0; j<worths.length; j++) {
				Integer inte = c[i].get(worths[j]);
				output[i][j] =  inte == null ? 0 : inte;
					;
			}
		}
		
		return output;
	}
	
	@Override
	public String toString() {
		if (size() == 0) {
			return Messages.getString("noCases");
		}
		if (size() > 0) {
			StringBuilder sb = new StringBuilder();
			if (timedOut) {
				sb.append(Messages.getString("timeout"));
			}
			sb.append(String.format(Messages.getString("hereIs"),
					Integer.toString(size()), size() > 1 ? "s" : "")); 
			for (Case c : this) {
				sb.append("\n_______\n");
				sb.append(c.toString());
			}
			if (timedOut) {
				sb.append(Messages.getString("timeout"));
			}
			return sb.toString();

		} else {
			return Messages.getString("failedExchange");
		}
	}

}
