package jp.go.aist.rtm.toolscommon.util;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
//import org.apache.log4j.Category;
//import org.apache.log4j.BasicConfigurator;

import java.io.File;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jp.go.aist.rtm.toolscommon.util.GeneratedResult;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
//import org.apache.velocity.app.VelocityEngine;

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
		System.out.println("createGeneratedResult1 entery");
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
		System.out.println("createGeneratedResult entery");
		return new GeneratedResult(fileName, generate(in, contextMap));
	}

	/**
	 * 設定済みのVelocityEngineを取得する
	 * 
	 * @return
	 */
	public static VelocityEngine getEngine() {
		System.out.println("getEngine entery");
		VelocityEngine result = new VelocityEngine();
		//result.setProperty(VelocityEngine.RESOURCE_LOADER, "class");
		result.setProperty(VelocityEngine.RESOURCE_LOADER, "class, jar");
		result.setProperty(VelocityEngine.VM_LIBRARY, "");
		result.setProperty("class.resource.loader.description",
				"Velocity Classpath Resource Loader");
		result.setProperty("class.resource.loader.class",
						"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		//result.setProperty(" FILE.resource.loader.class","org.apache.velocity.runtime.resource.loader.FileResourceLoader");
		//result.setProperty("file.resource.loader.path","jp.go.aist.rtm.rtcbuilder.safety/");
		//result.setProperty("file.resource.loader.path",".");
		//result.setProperty("file.resource.loader.path","/jp.go.aist.rtm.rtcbuilder.safety/bin/");
		//result.setProperty("file.resource.loader.path","\\");
		//result.setProperty("file.resource.loader.path",".,\\,/,C:/Users/katami/Downloads/000softs/eclipse-SDK-3.4.2-win32_test/eclipse/plugins,C:/Users/katami/Downloads/000softs/eclipse-SDK-3.4.2-win32_test/eclipse/plugins/jp.go.aist.rtm.rtcbuilder.safety,C:\\Users\\katami\\Downloads\\000softs\\eclipse-SDK-3.4.2-win32_test\\eclipse\\plugins,C:\\Users\\katami\\Downloads\\000softs\\eclipse-SDK-3.4.2-win32_test\\eclipse\\plugins\\jp.go.aist.rtm.rtcbuilder.safety");
		//result.setProperty("FILE.resource.loader.path",".");
		result.setProperty("jar.resource.loader.class",
			"org.apache.velocity.runtime.resource.loader.JarResourceLoader");
		//result.setProperty("jar.resource.loader.path", "jar:file:C:/Users/katami/Downloads/000softs/eclipse-SDK-3.4.2-win32_test/eclipse/plugins/jp.go.aist.rtm.rtcbuilder.safety_1.1.0.rc4v20130124.jar,jar:file:C:/Users/katami/Downloads/000softs/eclipse-SDK-3.4.2-win32_test/eclipse/plugins/jp.go.aist.rtm.rtcbuilder_1.1.0.rc4v20130124.jar");
		System.out.println("classpath : " + System.getProperty("java.class.path"));
		// Add the processing which specifies all the file names here. 
		String jarpath = new String();
		File dir = new File(System.getProperty("user.dir").replace("\\", "/")+"/plugins/");
		File[] files = dir.listFiles();
		for(File afile : files)
		{
			String ext = null;
			String filename = afile.getName();
			int dotIndex = filename.lastIndexOf('.');
			if ((dotIndex > 0) && (dotIndex < filename.length() - 1))
			{
				ext = filename.substring(dotIndex + 1).toLowerCase();
    			}
			if(ext.equals("jar")&&filename.startsWith("jp.go.aist.rtm.rtcbuilder"))
			{
				jarpath = jarpath +
				"jar:file:"+
				System.getProperty("user.dir").replace("\\", "/")+
				"/plugins/"+
				afile.getName()+
				",";

			}
		}
		System.out.println("jarpath :"+jarpath);
		/*
		String jarpath = 
			"jar:file:"+
			System.getProperty("user.dir").replace("\\", "/")+
			"/plugins/"+
			"jp.go.aist.rtm.rtcbuilder.safety_1.1.0.rc4v20130124.jar"+
			","+
			"jar:file:"+
			System.getProperty("user.dir").replace("\\", "/")+
			"/plugins/"+
			"jp.go.aist.rtm.rtcbuilder_1.1.0.rc4v20130124.jar";
		*/
		result.setProperty("jar.resource.loader.path", jarpath);
		System.out.println("current dir :"+new java.io.File(".").getAbsolutePath());
		System.out.println("user.dir :"+System.getProperty("user.dir"));
		//result.setProperty( RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.SimpleLog4JLogSystem" );

        	//result.setProperty("runtime.log.logsystem.log4j.category", CATEGORY_NAME);


		//result.setProperty(VelocityEngine.RUNTIME_LOG_LOGSYSTEM, new org.springframework.security.saml.util.SLF4JLogChute());
		try {
			System.out.println("getEngine result.init()");
			result.init();
		} catch (Exception e) {
			System.out.println("getEngine Exception");
			throw new RuntimeException(e); // system error
		}

		System.out.println("getEngine return");
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
		System.out.println("generate entry in:"+in);
		VelocityEngine ve = TemplateUtil.getEngine();
		System.out.println("generate 010");
		ve.setProperty(VelocityEngine.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.NullLogSystem");
		System.out.println("generate 020");
		VelocityContext vc = TemplateUtil.getDefaultVelocityContext();
		System.out.println("generate 030");
		for (Iterator iter = contextMap.entrySet().iterator(); iter.hasNext();) {
			Map.Entry element = (Map.Entry) iter.next();
			vc.put((String) element.getKey(), element.getValue());
			System.out.println("    "+element.getKey()+":"+element.getValue());
		}
		System.out.println("generate 040");

		StringWriter result = new StringWriter();
		System.out.println("generate 050");
		try {
			String classpath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			System.out.println("classpath : " + classpath);
			System.out.println("classpath : " + System.getProperty("java.class.path"));
			System.out.println("generate 060 file.resource.loader.path:"+ve.getProperty("file.resource.loader.path"));
			System.out.println("generate 061 jar.resource.loader.path:"+ve.getProperty("jar.resource.loader.path"));
			ve.evaluate(vc, result, "", new InputStreamReader(in, "UTF-8"));
			System.out.println("generate 070");
			result.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		System.out.println("generate 080");
		return result.toString().replace("\r\n", System.getProperty( "line.separator" ));
	}

}
