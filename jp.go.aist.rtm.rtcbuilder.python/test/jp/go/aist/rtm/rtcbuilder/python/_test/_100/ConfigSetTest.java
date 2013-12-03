package jp.go.aist.rtm.rtcbuilder.python._test._100;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigSetParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.python.IRtcBuilderConstantsPython;
import jp.go.aist.rtm.rtcbuilder.python._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.python.manager.PythonCMakeGenerateManager;
import jp.go.aist.rtm.rtcbuilder.python.manager.PythonGenerateManager;

public class ConfigSetTest extends TestBase {

	Generator generator;
	GeneratorParam genParam;
	RtcParam rtcParam;

	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "/resource/work");
		rtcParam.setLanguage(IRtcBuilderConstantsPython.LANG_PYTHON);
		rtcParam.setLanguageArg(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		rtcParam.setRtmVersion(IRtcBuilderConstants.RTM_VERSION_100);
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);

		generator = new Generator();
		generator.addGenerateManager(new PythonGenerateManager());
		generator.addGenerateManager(new PythonCMakeGenerateManager());
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

		assertEquals(26, result.size());
		checkCode(result, resourceDir, "foo.py");
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

		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>();
		configset.add(new ConfigSetParam("int_param0", "int", "", "0"));
		configset.add(new ConfigSetParam("int_param1", "int", "", "1"));
		rtcParam.getConfigParams().addAll(configset);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/ConfigSet/configset2/";

		assertEquals(26, result.size());
		checkCode(result, resourceDir, "foo.py");
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

		assertEquals(26, result.size());
		checkCode(result, resourceDir, "foo.py");
	}

	public void testConfigSet4() throws Exception {
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
		configset.add(new ConfigSetParam("vector_param0", "double", "",
				"0.0,1.0,2.0,3.0"));
		rtcParam.getConfigParams().addAll(configset);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/ConfigSet/configset4/";

		assertEquals(26, result.size());
		checkCode(result, resourceDir, "foo.py");
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

		assertEquals(26, result.size());
		checkCode(result, resourceDir, "foo.py");
	}
}
