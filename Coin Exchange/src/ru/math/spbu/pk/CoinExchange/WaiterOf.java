package ru.math.spbu.pk.CoinExchange;

public class WaiterOf extends Thread {

	private long threshold = 3 * 1000;
	private double increaseFactor = 4 / 3d;
	private double increaseIncreaseFactorFactor = 6 / 5d;
	private final ATM observedATM;
	private final long UID;

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
