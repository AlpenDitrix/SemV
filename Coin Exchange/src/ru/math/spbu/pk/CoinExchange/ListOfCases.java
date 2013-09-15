package ru.math.spbu.pk.CoinExchange;

import java.util.ArrayList;

public class ListOfCases extends ArrayList<Case> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8037932627088736022L;

	public static final ListOfCases WRONG_INPUT = new ListOfCases() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6741001396872270661L;

		public String toString() {
			bad();
			return "Your money is fake";
		}

	};

	private boolean timedOut = false;
	private static int timedOutCounter = 0;
	private static int badCounter = 0;

	public String toString() {
		if(size()== 0){
			return "[Feel free to take my nothing]";
		}
		if (size() > 0) {
			StringBuilder sb = new StringBuilder();
			if (timedOut) {
				sb.append("\nCOMPUTATIOIN OF THIS REQUEST TIMED OUT\n");
			}
			sb.append("[Here is ");
			sb.append(size());
			if (size() > 1) {
				sb.append(" cases:]");
			} else {
				sb.append(" case:]");
			}
			// sb.append(String.format(" case%s:", size() > 1 ? "s" : ""));
			for (Case c : this) {
				sb.append('\n');
				sb.append("_______");
				sb.append('\n');
				sb.append(c.toString());
			}
			if (timedOut) {
				sb.append("\nCOMPUTATIOIN OF THIS REQUEST TIMED OUT\n");
			}
			return sb.toString();

		} else {
			return "Unfortunately this ATM can not exchange that money";
		}
	}

	public void setTimedOut() {
		timedOut = true;
		timedOutCounter++;
		checkCounters();
	}

	private static void bad() {
		badCounter++;
		checkCounters();
	}

	private static void checkCounters() {
		if (timedOutCounter == 3) {
			System.err.println("Please, buy newer PC");
		}
		if (badCounter == 3) {
			System.err
					.println("You're not enough tricky. I'm going to call the police");
		}
	}

}
