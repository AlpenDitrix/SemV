package vladimir.chugunov.CoinExchange;

/**
 * 
 * This class marks the time, when some calculation starts and after some time
 * tries to stop it because for basics problems this applicaiton is fast. So PC
 * maybe freezed if calculation of easy problem is so long. Moreover, for really
 * heavy problems you can just 'continue' compilation
 * 
 * @author Alpen Ditrix
 * 
 */
public class WaiterOf extends Thread {

	/**
	 * Time in milliseconds. Every ___ms this thread will be active and will
	 * chech if he must to act
	 */
	private static final int sleepTime = 100;
	/**
	 * Every {@link #sleepTime} equals to 1 tick. Every
	 * {@value #printDotEvery_Ticks} ticks this thread will print something to
	 * show "I'm not frezed"
	 */
	private static final int printDotEvery_Ticks = 10;
	/**
	 * After each continueing {@link #threshold} will be increased on this
	 */
	private double increaseFactor = 4 / 3d;
	/**
	 * After each continueing {@link #increaseFactor} will be increased on this
	 */

	private static final double increaseIncreaseFactorFactor = 6 / 5d;
	/**
	 * Basic time of waiting is 3 seconds
	 */
	private long threshold = 3 * 1000;

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
	 * 
	 * @param atm
	 *            owner of calculatioin
	 * @param uid
	 *            unique id of calculation
	 */
	public WaiterOf(ATM atm, long uid, long thresh) {
		observedATM = atm;
		threshold = thresh;
		UID = uid;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		int i = Integer.MIN_VALUE;

		while (true) {
			while (System.currentTimeMillis() - start < threshold
					&& observedATM.isRunning(UID)) {
				try {
					if (i++ % printDotEvery_Ticks == 0) {
						System.out.println(".");
					}
					sleep(sleepTime);
					// System.out.println("Waiter cycle");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// System.out.println("Waiter asking");
			if (observedATM.askToTerminateComputation(UID)) {
				/* computation terminated */
				break;
				// return;
			} else {
				/* cumputation continued */
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