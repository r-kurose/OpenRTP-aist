package jp.go.aist.rtm.systemeditor.template;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jp.go.aist.rtm.systemeditor.generator.GeneratedResult;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * テンプレートを出力する際に使用されるユーティリティ
 */
public class TemplateUtil {

	/**
	 * クラスパスリソースから内容を手に入れる
	 * 
	 * @param path
	 *            パス
	 * @return 内容
	 */
	public static String getResourceContents(String path) {
		InputStream input = TemplateUtil.class.getClassLoader()
				.getResourceAsStream(path);

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		byte[] buff = new byte[1024];

		int count;
		try {
			while ((count = input.read(buff)) != -1) {
				out.write(buff, 0, count);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return out.toString();
	}

	/**
	 * テンプレートからGeneratedResultを作成する
	 * 
	 * @param templatePath　テンプレートのパス
	 * @param contextRootName コンテクストのルートとなる名前
	 * @param contextRoot コンテクストのルート
	 * @param fileName 出力ファイル名
	 * @return
	 */
	public static GeneratedResult createGeneratedResult(InputStream in,
			String contextRootName, Object contextRoot, String fileName) {
		System.out.println("createGeneratedResult 1");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(contextRootName, contextRoot);

		return createGeneratedResult(in, map, fileName);
	}

	/**
	 * テンプレートからGeneratedResultを作成する
	 * 
	 * @param templatePath　テンプレートのパス
	 * @param contextMap コンテクストのマップ
	 * @param fileName 出力ファイル名
	 * 
	 * @return GeneratedResult
	 */
	@SuppressWarnings("unchecked")
	public static GeneratedResult createGeneratedResult(InputStream in,
			Map contextMap, String fileName) {
		System.out.println("createGeneratedResult");
		return new GeneratedResult(fileName, generate(in, contextMap));
	}
	public static String createGeneratedResultDM(InputStream in,
			Map<String, Object> contextMap, String fileName) {
		System.out.println("createGeneratedResultDM");
		return "Test";
	}
	public static void createGeneratedResultDM2() {
		System.out.println("createGeneratedResultDM");
		return ;
	}

	/**
	 * 設定済みのVelocityEngineを取得する
	 * 
	 * @return
	 */
	public static VelocityEngine getEngine() {
		VelocityEngine result = new VelocityEngine();
		result.setProperty(VelocityEngine.RESOURCE_LOADER, "class");
		result.setProperty(VelocityEngine.VM_LIBRARY, "");
		result.setProperty("class.resource.loader.description",
				"Velocity Classpath Resource Loader");
		result
				.setProperty("class.resource.loader.class",
						"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		try {
			result.init();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}

	public static VelocityContext getDefaultVelocityContext() {
		VelocityContext result = new VelocityContext();
		result.put("sharp", "#");
		result.put("dol", "$");
		result.put("def", "def");
		result.put("yen", "\\");

		return result;
	}

	/**
	 * マージを行い結果を返す
	 * 
	 * @param template
	 * @param vc
	 * @return
	 */
	public static String merge(Template template, VelocityContext vc) {
		StringWriter result = new StringWriter();
		try {
			template.merge(vc, result);
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result.toString();
	}

	/**
	 * 生成を行う
	 * 
	 * @param contextRoot
	 * @param templatePath
	 * @param contextRootName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String generate(InputStream in, Map contextMap) {
		System.out.println("Template.generate entry");
		VelocityEngine ve = TemplateUtil.getEngine();
		System.out.println("Template.generate 010");
		ve.setProperty(VelocityEngine.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.NullLogSystem");
		System.out.println("Template.generate 020");
		VelocityContext vc = TemplateUtil.getDefaultVelocityContext();
		System.out.println("Template.generate 030");
		for (Iterator iter = contextMap.entrySet().iterator(); iter.hasNext();) {
			Map.Entry element = (Map.Entry) iter.next();
			vc.put((String) element.getKey(), element.getValue());
		}

		System.out.println("Template.generate 040");
		StringWriter result = new StringWriter();
		System.out.println("Template.generate 050");
		try {
			ve.evaluate(vc, result, "", new InputStreamReader(in, "UTF-8"));
			result.close();
		} catch (Exception e) {
			System.out.println("Template.generate Exception");
			throw new RuntimeException(e); // system error
		}

		System.out.println("Template.generate return 1");
		return result.toString().replace("\r\n", System.getProperty( "line.separator" ));
	}

}
