package jp.go.aist.rtm.toolscommon.model.component.util;

import java.util.ArrayList;
import java.util.List;

import static jp.go.aist.rtm.toolscommon.util.RTMixin.*;

public class RTCLogStore {

	public static RTCLogStore eINSTANCE = new RTCLogStore();

	List<Record> store;

	RTCLogStore() {
		this.store = new ArrayList<Record>();
	}

	/**
	 * ログ通知オブザーバのIDリストを指定して、対象の RTCログを検索します。
	 */
	public synchronized List<Record> find(List<String> id_list, int limit) {
		List<Record> result = new ArrayList<Record>();
		int count = 0;
		for (Record r : store) {
			if (!id_list.contains(r.observer_id)) {
				continue;
			}
			result.add(r);
			count++;
			if (limit > 0 && count > limit) {
				break;
			}
		}
		return result;
	}

	public List<Record> find(List<String> id_list) {
		return find(id_list, 0);
	}

	/**
	 * ログ通知オブザーバのIDに関連付けて、RTCログを保存します。
	 */
	public synchronized void save(String id, String name,
			OpenRTM.LogRecord record) {
		Record r = new Record(id, name, record);
		store.add(0, r);
	}

	/**
	 * ログ通知オブザーバのIDを指定して、対象の RTCログを削除します。
	 */
	public synchronized void remove(String id) {
		List<Record> unmodified = new ArrayList<Record>(store);
		for (Record r : unmodified) {
			if (eql(id, r.observer_id)) {
				store.remove(r);
			}
		}
	}

	/**
	 * ログレベルオブジェクトから文字列に変換します。
	 */
	public static String toLevelName(OpenRTM.LogLevel lv) {
		if (OpenRTM.LogLevel.ERROR.equals(lv)) {
			return "ERROR";
		} else if (OpenRTM.LogLevel.WARN.equals(lv)) {
			return "WARN";
		} else if (OpenRTM.LogLevel.INFO.equals(lv)) {
			return "INFO";
		} else if (OpenRTM.LogLevel.NORMAL.equals(lv)) {
			return "NORMAL";
		} else if (OpenRTM.LogLevel.DEBUG.equals(lv)) {
			return "DEBUG";
		} else if (OpenRTM.LogLevel.TRACE.equals(lv)) {
			return "TRACE";
		} else if (OpenRTM.LogLevel.VERBOSE.equals(lv)) {
			return "VERBOSE";
		} else if (OpenRTM.LogLevel.PARANOID.equals(lv)) {
			return "PARANOID";
		}
		return "UNKNOWN";
	}

	/**
	 * RTCログを表すクラス
	 */
	public static class Record {
		String observer_id;
		String rtc_name;
		String level_name;
		OpenRTM.LogRecord r;

		Record(String id, String name, OpenRTM.LogRecord record) {
			this.observer_id = id;
			this.rtc_name = name;
			this.level_name = toLevelName(record.level);
			this.r = record;
		}

		public String getObserverId() {
			return observer_id;
		}

		public String getRtcName() {
			return rtc_name;

		}

		public String getLevelName() {
			return level_name;
		}

		public RTC.Time getTime() {
			return r.time;
		}

		public OpenRTM.LogLevel getLevel() {
			return r.level;
		}

		public String getLoggerName() {
			return r.loggername;
		}

		public String getMessage() {
			return r.message;
		}
	}

}
