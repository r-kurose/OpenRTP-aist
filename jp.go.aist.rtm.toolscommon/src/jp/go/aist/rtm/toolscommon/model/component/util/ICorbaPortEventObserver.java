package jp.go.aist.rtm.toolscommon.model.component.util;

/**
 * ポートのイベントを通知するオブザーバのインターフェース (SEND/RECEIVE)
 */
public interface ICorbaPortEventObserver {

	boolean notifyEvent(String action, String port);

}
