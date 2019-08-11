package jp.go.aist.rtm.rtcbuilder.generator.param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * FSMのイベントポートを表すクラス
 */
public class EventPortParam extends PortBaseParam implements Serializable {

	private static final long serialVersionUID = 8617430815004190346L;

	private String name;
	private String varname;
	
	private List<EventParam> events = new ArrayList<EventParam>();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getVarname() {
		return varname;
	}
	public void setVarname(String varname) {
		this.varname = varname;
	}

	public List<EventParam> getEvents() {
		return events;
	}
}
