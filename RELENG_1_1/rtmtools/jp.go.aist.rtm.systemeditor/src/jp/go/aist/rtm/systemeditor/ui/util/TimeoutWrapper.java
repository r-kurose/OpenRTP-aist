package jp.go.aist.rtm.systemeditor.ui.util;

import jp.go.aist.rtm.systemeditor.nl.Messages;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

/**
 * 時間のかかる処理を別スレッドで行い、指定された時間内に終わらなければ、エラーメッセージを表示するクラス
 *
 */
public class TimeoutWrapper {
	private long milliSecond;
	private TimeoutWrappedJob job;

	public TimeoutWrapper(long milliSecond) {
		this.milliSecond = milliSecond;
	}
	
	public void setJob(TimeoutWrappedJob job) {
		this.job = job;
	}
	
	public Object start() {
		job.setDaemon(true);
		job.start();
		synchronized (job) {
			try {
				job.wait(milliSecond);
			} catch (InterruptedException e) {
			}
		}
		if (job.isDone()) return job.getResult();
		MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()
				, Messages.getString("TimeoutWrapper.0"), //$NON-NLS-1$
				Messages.getString("TimeoutWrapper.1"));
		return null;
	}
}
