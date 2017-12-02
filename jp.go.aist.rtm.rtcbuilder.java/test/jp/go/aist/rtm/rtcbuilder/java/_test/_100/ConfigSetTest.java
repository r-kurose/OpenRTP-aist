package jp.go.aist.rtm.rtcbuilder.java._test._100;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigSetParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.java.IRtcBuilderConstantsJava;
import jp.go.aist.rtm.rtcbuilder.java._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.java.manager.JavaCMakeGenerateManager;
import jp.go.aist.rtm.rtcbuilder.java.manager.JavaGenerateManager;

public class ConfigSetTest extends TestBase {

	Generator generator;
	GeneratorParam genParam;
	RtcParam rtcParam;

	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "/resource/work");
		rtcParam.setLanguage(IRtcBuilderConstantsJava.LANG_JAVA);
		rtcParam.setLanguageArg(IRtcBuilderConstantsJava.LANG_JAVA_ARG);
		rtcParam.setRtmVersion(IRtcBuilderConstants.RTM_VERSION_100);
		rtcParam.setIsTest(true);
		genParam.setRtcParam(rtcParam);

		generator = new Generator();
		generator.addGenerateManager(new JavaGenerateManager());
		generator.addGenerateManager(new JavaCMakeGenerateManager());
	}

	public void testAIST7() throws Exception {
		rtcParam.setName("ModuleName");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("VenderName");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("DataFlowComponent");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");

		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>();
		ConfigSetParam param01 = new ConfigSetParam("test", "double", "", "0");
		param01.setWidget("slider");
		param01.setStep("0.2");
		param01.setConstraint("-1.0<x<1.0");
		configset.add(param01);
		rtcParam.getConfigParams().addAll(configset);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String targetDir = rootPath + "/resource/100/ConfigSet/AIST7/";

		assertEquals(32, result.size());
		checkCode(result, targetDir, "src/ModuleNameComp.java");
		checkCode(result, targetDir, "src/ModuleName.java");
		checkCode(result, targetDir, "src/ModuleNameImpl.java");
		//
		checkCode(result, targetDir, "build_ModuleName.xml");
	}
	
	public void testAIST6() throws Exception {
		rtcParam.setName("ModuleName");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("VenderName");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("DataFlowComponent");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");

		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>();
		configset.add(new ConfigSetParam("int_param0", "int", "", "0"));
		configset.add(new ConfigSetParam("vector_param", "Vector", "",
				"1.0,2.0,3.0"));
		rtcParam.getConfigParams().addAll(configset);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String targetDir = rootPath + "/resource/100/ConfigSet/AIST6/";

		assertEquals(32, result.size());
		checkCode(result, targetDir, "src/ModuleNameComp.java");
		checkCode(result, targetDir, "src/ModuleName.java");
		checkCode(result, targetDir, "src/ModuleNameImpl.java");
		//
		checkCode(result, targetDir, "build_ModuleName.xml");
	}

	public void testConfigSet() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.3");
		rtcParam.setVender("TA2");
		rtcParam.setCategory("manip2");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(3);
		rtcParam.setComponentKind("DataFlowComponent");

		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>();
		configset.add(new ConfigSetParam("int_param0", "int", "", "0"));
		rtcParam.getConfigParams().addAll(configset);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/ConfigSet/configset1/";

		assertEquals(32, result.size());
		checkCode(result, resourceDir, "src/fooComp.java");
		checkCode(result, resourceDir, "src/foo.java");
		checkCode(result, resourceDir, "src/fooImpl.java");
		//
		checkCode(result, resourceDir, "build_foo.xml");
	}

	public void testConfigSet2() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.3");
		rtcParam.setVender("TA2");
		rtcParam.setCategory("manip2");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(3);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion("1.0.0");

		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>();
		configset.add(new ConfigSetParam("int_param0", "int", "", "0"));
		configset.add(new ConfigSetParam("int_param1", "int", "", "1"));
		rtcParam.getConfigParams().addAll(configset);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/ConfigSet/configset2/";

		assertEquals(32, result.size());
		checkCode(result, resourceDir, "src/fooComp.java");
		checkCode(result, resourceDir, "src/foo.java");
		checkCode(result, resourceDir, "src/fooImpl.java");
		//
		checkCode(result, resourceDir, "build_foo.xml");
	}

	public void testConfigSet3() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.3");
		rtcParam.setVender("TA2");
		rtcParam.setCategory("manip2");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(3);
		rtcParam.setComponentKind("DataFlowComponent");

		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>();
		configset.add(new ConfigSetParam("int_param0", "int", "", "0"));
		configset.add(new ConfigSetParam("int_param1", "int", "", "1"));
		configset
				.add(new ConfigSetParam("double_param0", "double", "", "0.11"));
		configset.add(new ConfigSetParam("str_param0", "String", "", "hoge"));
		configset.add(new ConfigSetParam("str_param1", "String", "", "dara"));
		rtcParam.getConfigParams().addAll(configset);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/ConfigSet/configset3/";

		assertEquals(32, result.size());
		checkCode(result, resourceDir, "src/fooComp.java");
		checkCode(result, resourceDir, "src/foo.java");
		checkCode(result, resourceDir, "src/fooImpl.java");
		//
		checkCode(result, resourceDir, "build_foo.xml");
	}

	public void testConfigSetType() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.3");
		rtcParam.setVender("TA2");
		rtcParam.setCategory("manip2");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(3);
		rtcParam.setComponentKind("DataFlowComponent");

		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>();
		configset.add(new ConfigSetParam("short_param", "short", "", "0"));
		configset.add(new ConfigSetParam("int_param", "int", "", "1"));
		configset.add(new ConfigSetParam("long_param", "long", "", "14"));
		configset.add(new ConfigSetParam("float_param", "float", "", "0.11"));
		configset.add(new ConfigSetParam("double_param", "double", "", "4.11"));
		configset.add(new ConfigSetParam("str_param0", "string", "", "hoge"));
		configset.add(new ConfigSetParam("str_param1", "string", "", "dara"));
		rtcParam.getConfigParams().addAll(configset);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath
				+ "/resource/100/ConfigSet/ConfigSetType/";

		assertEquals(32, result.size());
		checkCode(result, resourceDir, "src/fooComp.java");
		checkCode(result, resourceDir, "src/foo.java");
		checkCode(result, resourceDir, "src/fooImpl.java");
		//
		checkCode(result, resourceDir, "build_foo.xml");
	}

}
