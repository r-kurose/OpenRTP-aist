package jp.go.aist.rtm.toolscommon.profiles.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

/**
 * ロギングの初期設定ユーティリティ
 */
public class LoggerUtil {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(LoggerUtil.class);

	private static final String DEFAULT_LOGBACK_XML = "./logback.xml";

	private static LoggerContext context = null;

	/**
	 * ロギングの設定を行います。
	 * <ul>
	 * <li>すでに設定済の場合はスキップ</li>
	 * <li>logback.xmlが存在しない場合は、デフォルトの設定ファイルを作成(カレントディレクトリ)</li>
	 * <li>デフォルトの設定ファイルを読み込み、設定を反映</li>
	 * </ul>
	 */
	public static synchronized void setup() {
		if (context != null) {
			LOGGER.info("setup: already setup logger.");
			return;
		}
		setupConfigurationFile();
		context = (LoggerContext) LoggerFactory.getILoggerFactory();
		try {
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(context);
			context.reset();
			configurator.doConfigure(DEFAULT_LOGBACK_XML);
		} catch (JoranException e) {
			System.err.println("Fail to configure by default");
			e.printStackTrace(System.err);
		}
		StatusPrinter.print(context);
	}

	private static void setupConfigurationFile() {
		File xml = new File(DEFAULT_LOGBACK_XML);
		if (xml.exists()) {
			return;
		}
		final String LOGBACK_XML_CONTENT = //
		"<configuration>\r\n" //
				+ "\r\n" //
				+ "	<appender name=\"file\" class=\"ch.qos.logback.core.FileAppender\">\r\n" //
				+ "		<file>${user.home}/openrtp.log</file>\r\n" //
				+ "		<append>true</append>\r\n" //
				+ "		<encoder>\r\n" //
				+ "			<pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n\r\n" //
				+ "			</pattern>\r\n" //
				+ "		</encoder>\r\n" //
				+ "	</appender>\r\n" //
				+ "\r\n" //
				+ "	<appender name=\"stdout\" class=\"ch.qos.logback.core.ConsoleAppender\">\r\n" //
				+ "		<encoder>\r\n" //
				+ "			<pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n\r\n" //
				+ "			</pattern>\r\n" //
				+ "		</encoder>\r\n" //
				+ "	</appender>\r\n" //
				+ "\r\n" //
				+ "	<root level=\"trace\">\r\n" //
				+ "		<appender-ref ref=\"stdout\" />\r\n" //
				+ "		<appender-ref ref=\"file\" />\r\n" //
				+ "	</root>\r\n" + "\r\n" //
				+ "</configuration>\r\n";

		try {
			Files.write(xml.toPath(), LOGBACK_XML_CONTENT.getBytes());
		} catch (IOException e) {
			System.err.println("Fail to create logback.xml");
			e.printStackTrace(System.err);
		}
	}

}
