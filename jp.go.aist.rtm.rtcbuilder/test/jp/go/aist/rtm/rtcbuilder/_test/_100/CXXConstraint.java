package jp.go.aist.rtm.rtcbuilder._test._100;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigSetParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;

public class CXXConstraint extends TestBase {
	private GeneratorParam genParam;
	private RtcParam rtcParam;

	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "/resource/work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.3");
		rtcParam.setVender("TA2");
		rtcParam.setCategory("manip2");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(3);
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);
		rtcParam.setEnableOldBuildEnv(true);
		genParam.getRtcParams().add(rtcParam);
	}

	public void testConstraint3() throws Exception{
		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>(); 
		configset.add(new ConfigSetParam("int_param0","int","", "0", "0<x<1"));
		configset.add(new ConfigSetParam("str_param0","string","", "up", "(up, down, right, left)"));
		configset.add(new ConfigSetParam("int_param1","int","", "0"));
		configset.add(new ConfigSetParam("double_param0","int","", "0", "1.5<=x<=3.2"));
		rtcParam.getConfigParams().addAll(configset);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "/resource/100/CXX/Constraint/Constraint3/";
		checkResults(result, resourceDir);
	}

	public void testConstraint2() throws Exception{
		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>(); 
		configset.add(new ConfigSetParam("int_param0","int","", "0", "0<x<1"));
		configset.add(new ConfigSetParam("str_param0","string","", "up", "(up, down, right, left)"));
		rtcParam.getConfigParams().addAll(configset);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "/resource/100/CXX/Constraint/Constraint2/";
		checkResults(result, resourceDir);
	}

	public void testConstraint1() throws Exception{
		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>(); 
		configset.add(new ConfigSetParam("int_param0","int","", "0", "0<x<1"));
		rtcParam.getConfigParams().addAll(configset);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "/resource/100/CXX/Constraint/Constraint1/";
		checkResults(result, resourceDir);
	}

	private void checkResults(List<GeneratedResult> result, String resourceDir) {
		assertEquals(15, result.size());
		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "foo.h");
		checkCode(result, resourceDir, "foo.cpp");
		checkCode(result, resourceDir, "foo.conf");
		checkCode(result, resourceDir, "rtc.conf");
	}

}
