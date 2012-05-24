package jp.go.aist.rtm.rtcbuilder.generator.param;

import java.io.Serializable;

/**
 * Actions情報を表すクラス
 */
public class ActionsParam extends AbstractRecordedParam implements Serializable {

	private static final long serialVersionUID = -750739376634870859L;

	private boolean implemented;
	private String pre_condition;
	private String overview;
	private String post_condition;

	public ActionsParam() {
		this(false, "", "", "");
	}

	public ActionsParam(boolean implemented, String overview, String pre_con,
			String post_con) {
		this.implemented = implemented;
		this.pre_condition = pre_con;
		this.overview = overview;
		this.post_condition = post_con;
		//
		setUpdated(false);
	}

	public boolean getImplemented() {
		return this.implemented;
	}
	public String getOverView() {
		if(this.overview==null) this.overview = "";
		return this.overview;
	}
	public String getPreCondition() {
		if(this.pre_condition==null) this.pre_condition = "";
		return this.pre_condition;
	}
	public String getPostCondition() {
		if(this.post_condition==null) this.post_condition = "";
		return this.post_condition;
	}

	public void setImplemaented(String implemented) {
		boolean impl = implemented.toUpperCase().equals("TRUE") ? true : false;
		setImplemaented(impl);
	}
	public void setImplemaented(boolean implemented) {
		if (this.implemented != implemented) {
			setUpdated(true);
		}
		this.implemented = implemented;
	}
	public void setOverview(String overview) {
		checkUpdated(this.overview, overview);
		this.overview = overview;
	}
	public void setPreCondition(String precondition) {
		checkUpdated(this.pre_condition, precondition);
		this.pre_condition = precondition;
	}
	public void setPostCondition(String postcondition) {
		checkUpdated(this.post_condition, postcondition);
		this.post_condition = postcondition;
	}

}
