package jp.go.aist.rtm.rtcbuilder._test._100.cxx;

import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;

public class CXXBaseClass extends TestBase {

	protected void setUp() throws Exception {
	}

	public void testDFFSMMM() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setComponentKind("DataFlowFiniteStateMachineMultiModeComponent");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);
		
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\DFFSMMM\\";

		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "Makefile.foo");
		checkCode(result, resourceDir, "foo.h");
		checkCode(result, resourceDir, "foo.cpp");
		checkCode(result, resourceDir, "README.foo");
		//
		checkCode(result, resourceDir, "foo_vc8.sln");
		checkCode(result, resourceDir, "foo_vc8.vcproj");
		checkCode(result, resourceDir, "fooComp_vc8.vcproj");
		checkCode(result, resourceDir, "foo_vc9.sln");
		checkCode(result, resourceDir, "foo_vc9.vcproj");
		checkCode(result, resourceDir, "fooComp_vc9.vcproj");
		//
		checkCode(result, resourceDir, "copyprops.bat");
		checkCode(result, resourceDir, "user_config.vsprops");
	}
	
	public void testFSM() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setComponentKind("FiniteStateMachineComponent");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);
		
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\FSM\\";

		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "Makefile.foo");
		checkCode(result, resourceDir, "foo.h");
		checkCode(result, resourceDir, "foo.cpp");
		checkCode(result, resourceDir, "README.foo");
		//
		checkCode(result, resourceDir, "foo_vc8.sln");
		checkCode(result, resourceDir, "foo_vc8.vcproj");
		checkCode(result, resourceDir, "fooComp_vc8.vcproj");
		checkCode(result, resourceDir, "foo_vc9.sln");
		checkCode(result, resourceDir, "foo_vc9.vcproj");
		checkCode(result, resourceDir, "fooComp_vc9.vcproj");
		//
		checkCode(result, resourceDir, "copyprops.bat");
		checkCode(result, resourceDir, "user_config.vsprops");
	}
	
	public void testDataFlow() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);
		
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\DataFlow\\";

		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "Makefile.foo");
		checkCode(result, resourceDir, "foo.h");
		checkCode(result, resourceDir, "foo.cpp");
		checkCode(result, resourceDir, "README.foo");
		//
		checkCode(result, resourceDir, "foo_vc8.sln");
		checkCode(result, resourceDir, "foo_vc8.vcproj");
		checkCode(result, resourceDir, "fooComp_vc8.vcproj");
		checkCode(result, resourceDir, "foo_vc9.sln");
		checkCode(result, resourceDir, "foo_vc9.vcproj");
		checkCode(result, resourceDir, "fooComp_vc9.vcproj");
		//
		checkCode(result, resourceDir, "copyprops.bat");
		checkCode(result, resourceDir, "user_config.vsprops");
	}
}
