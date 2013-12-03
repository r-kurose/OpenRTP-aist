package jp.go.aist.rtm.rtcbuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class RTCBLogHandler {

	FileHandler handler;
	List<Logger> loggers;

	public RTCBLogHandler() throws SecurityException, IOException {
		this.loggers = new ArrayList<Logger>();
		//
		LogManager logManager = LogManager.getLogManager();
		String cname = RTCBLogHandler.class.getName();
		String propPattern = logManager.getProperty(cname + ".pattern");
		String propAppend = logManager.getProperty(cname + ".append");
		String propFormatter = logManager.getProperty(cname + ".formatter");
		String propLevel = logManager.getProperty(cname + ".level");
		if (propLevel == null) {
			propLevel = logManager.getProperty(".level");
		}
		//
		String pattern = "rtcbuilder%u.log";
		if (propPattern != null) {
			pattern = propPattern;
		}
		boolean append = false;
		if ("true".equalsIgnoreCase(propAppend)) {
			append = true;
		}
		this.handler = new FileHandler(pattern, append);
		Formatter formatter = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			Class<?> clazz = loader.loadClass(propFormatter);
			formatter = (Formatter) clazz.newInstance();
		} catch (Exception e) {
			// void
		}
		if (formatter != null) {
			this.handler.setFormatter(formatter);
		} else {
			this.handler.setFormatter(new DefaultFormatter());
		}
		Level level = Level.WARNING;
		if (propLevel != null) {
			level = Level.parse(propLevel);
		}
		this.handler.setLevel(level);
	}

	public void start() {
		addLogger(RtcBuilderPlugin.getLogger());
	}

	public void stop() {
		for (Logger logger : new ArrayList<Logger>(loggers)) {
			removeLogger(logger);
		}
		loggers.clear();
	}

	public void addLogger(Logger logger) {
		if (logger != null) {
			logger.addHandler(handler);
			loggers.add(logger);
		}
	}

	public void removeLogger(Logger logger) {
		if (logger != null) {
			logger.removeHandler(handler);
			loggers.remove(logger);
		}
	}

	public static class DefaultFormatter extends Formatter {
		@Override
		public String format(LogRecord record) {
			return String.format("%tF %tT %s: %s%n", record.getMillis(), record
					.getMillis(), record.getLevel(), record.getMessage());
		}
	}

}
