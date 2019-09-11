package jp.go.aist.rtm.rtcbuilder.fsm;

import jp.go.aist.rtm.rtcbuilder.generator.param.EventPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;

public class TransitionParam {
	private String event;
	private String condition;
	private String target;
	private String source;
	private EventParam eventParam; 
	
	public TransitionParam() {
		this.event = "";
		this.condition = "";
		this.source = "";
		this.target = "";
	}
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}

	public EventParam getEventParam() {
		return eventParam;
	}
	public boolean existEventParam() {
		return eventParam != null;
	}
	
	public void searchEventParam(RtcParam rtcParam) {
		String transName = this.event;
		if(transName == null) transName = "";
		String transCondition = this.condition;
		if(transCondition == null) transCondition = "";
		String transSource = this.source;
		if(transSource == null) transSource = "";
		String transTarget = this.target;
		if(transTarget == null) transTarget = "";

		EventPortParam eventPort = rtcParam.getEventport();
		if(eventPort==null) return;
		for(EventParam event : eventPort.getEvents()) {
			if(event.getName().equals(transName)
					&& event.getCondition().equals(transCondition)
					&& event.getSource().equals(transSource)
					&& event.getTarget().equals(transTarget) ) {
				this.eventParam = event;
				break;
			}
		}
	}
}
