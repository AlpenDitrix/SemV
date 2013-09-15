package ru.math.spbu.pk.CoinExchange;

/**
 * 
 * This class marks the time, when some calculation starts and after some time
 * tries to stop it because for basics problems this applicaiton is fast. So PC
 * maybe freezed if calculation of easy problem is so long.
 * For really heavy problems you alse can just 'continue' compilation
 * 
 * @author Alpen Ditrix
 * 
 */
public class WaiterOf extends Thread {

	/**
	 * Basic time of waiting is 3 seconds
	 */
	private long threshold = 3 * 1000;
	
	/**
	 * After each continueing {@link #threshold} will be increased on this
	 */
	private double increaseFactor = 4 / 3d;
	/**
	 * After each continueing {@link #increaseFactor} will be increased on this
	 */
	private double increaseIncreaseFactorFactor = 6 / 5d;
	/**
	 * Link to ATM, for whose calculations that class follows
	 */
	private final ATM observedATM;
	/**
	 * Unique ID of calculation for whose timing that class follows
	 */
	private final long UID;

	/**
	 * Default constructor
	 * @param atm owner of calculatioin
	 * @param uid unique id of calculation
	 */
	public WaiterOf(ATM atm, long uid) {
		observedATM = atm;
		UID = uid;
	}

	public void run() {
		long start = System.currentTimeMillis();
		int i = Integer.MIN_VALUE;

		while (true) {
			while (System.currentTimeMillis() - start < threshold
					&& observedATM.isRunning(UID)) {
				try {
					if (i++ % 10 == 0) {
						System.out.println(".");
					}
					sleep(100);
					// System.out.println("Waiter cycle");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// System.out.println("Waiter asking");
			if (observedATM.askToTerminateComputation(UID)) {
				break;
				// return;
			} else {
				start = System.currentTimeMillis();
				threshold *= increaseFactor;
				increaseFactor *= increaseIncreaseFactorFactor;
				// System.out.println("New threshold is " + threshold);
				// System.out.println("New increase factor is "+
				// increaseFactor);
			}
		}
	}

}
