package jp.go.aist.rtm.toolscommon.synchronizationframework;

/**
 * 定期的にサーバの情報をローカルに反映させるクラス
 *
 */
public abstract class RefreshThread extends Thread {
	private long milliSecond;
	private long lastExceutedTime;
	
	public RefreshThread(long milliSecond) {
		this.milliSecond = milliSecond;
	}
	
	/**
	 * 指定した間隔ごとに実行される同期コマンド
	 */
	abstract protected void executeCommand();

	/**
	 * @param milliSecond	サーバから情報を収集する間隔
	 */
	public void setSynchronizeInterval(long milliSecond) {
		if (this.milliSecond == milliSecond) return;
		this.milliSecond = milliSecond;
		synchronized (this) {
			notify();
		}
	}

	@Override
	public void run() {
		while (milliSecond >= 0) {
			sleepInterval();
			execute();
		}
	}

	private void execute() {
		waitIfNotSync();
		lastExceutedTime = System.currentTimeMillis();
//		System.out.println(lastExceutedTime);
		executeCommand();
//		System.out.println(System.currentTimeMillis() - lastExceutedTime);
	}

	private void sleepInterval() {
		waitIfNotSync();
		if (milliSecond > 0) {
			long interval = System.currentTimeMillis() - lastExceutedTime;
			if (interval <  milliSecond) {
				try {
					Thread.sleep(milliSecond - interval);
				} catch (InterruptedException e) {
					// NOOP
				}
			}
		}
	}

	private void waitIfNotSync() {
		if (milliSecond == 0) {
			try {
				synchronized (this) {
					wait();
				}
			} catch (InterruptedException e) {
				// NOOP
			}
		}
	}

	/**
	 * @return	同期スレッドが実行中であるかどうか
	 */
	public boolean isRunning() {
		return milliSecond > 0;
	}
}
