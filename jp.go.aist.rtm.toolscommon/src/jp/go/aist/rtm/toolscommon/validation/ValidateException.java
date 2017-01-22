package jp.go.aist.rtm.toolscommon.validation;

public class ValidateException extends Exception {

	private static final long serialVersionUID = 6178966522996954324L;

	private String detail;

	public ValidateException(String msg) {
		super(msg);
	}

	public ValidateException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
