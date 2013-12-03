package jp.go.aist.rtm.rtcbuilder.safety._test._100;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigSetParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.safety.IRtcBuilderConstantsSafety;
import jp.go.aist.rtm.rtcbuilder.safety._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.safety.manager.SafetyCMakeGenerateManager;
import jp.go.aist.rtm.rtcbuilder.safety.manager.SafetyGenerateManager;

public class ConfigSetTest extends TestBase {

	Generator generator;
	GeneratorParam genParam;
	RtcParam rtcParam;

	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "/resource/work");
		rtcParam.setLanguage(IRtcBuilderConstantsSafety.LANG_SAFETY);
		rtcParam.setLanguageArg(IRtcBuilderConstantsSafety.LANG_SAFETY_ARG);
		rtcParam.setRtmVersion(IRtcBuilderConstants.RTM_VERSION_100);
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);

		generator = new Generator();
		generator.addGenerateManager(new SafetyGenerateManager());
		generator.addGenerateManager(new SafetyCMakeGenerateManager());
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

		assertEquals(33, result.size());
		checkCode(result, targetDir, "src/ModuleNameComp.c");
		checkCode(result, targetDir, "src/ModuleName.c");
		checkCode(result, targetDir, "src/ModuleNameImpl.c");
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

		assertEquals(33, result.size());
		checkCode(result, resourceDir, "src/fooComp.c");
		checkCode(result, resourceDir, "src/foo.c");
		checkCode(result, resourceDir, "src/fooImpl.c");
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

		assertEquals(33, result.size());
		checkCode(result, resourceDir, "src/fooComp.c");
		checkCode(result, resourceDir, "src/foo.c");
		checkCode(result, resourceDir, "src/fooImpl.c");
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

		assertEquals(33, result.size());
		checkCode(result, resourceDir, "src/fooComp.c");
		checkCode(result, resourceDir, "src/foo.c");
		checkCode(result, resourceDir, "src/fooImpl.c");
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

		assertEquals(33, result.size());
		checkCode(result, resourceDir, "src/fooComp.c");
		checkCode(result, resourceDir, "src/foo.c");
		checkCode(result, resourceDir, "src/fooImpl.c");
		//
		checkCode(result, resourceDir, "build_foo.xml");
	}

}
