package jp.go.aist.rtm.rtcbuilder.generator.param;

public abstract class AbstractRecordedParam implements UpdateRecordable {

	private boolean updated;

	public boolean isUpdated() {
		return this.updated;
	}

	public void resetUpdated() {
		setUpdated(false);
	}

	protected void setUpdated(boolean updated) {
		this.updated = updated;
	}

	protected void checkUpdated(Object oldValue, Object newValue) {
		if (oldValue == null) {
			if (newValue != null) {
				setUpdated(true);
			}
		} else if (!oldValue.equals(newValue)) {
			setUpdated(true);
		}
	}

}
