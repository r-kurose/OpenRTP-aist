package jp.go.aist.rtm.rtcbuilder._test._100;

import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;

public class CXXBaseClass extends TestBase {
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
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);

		genParam.setRtcParam(rtcParam);
	}

	public void testDFFSMMM() throws Exception{
		rtcParam.setComponentKind("DataFlowFiniteStateMachineMultiModeComponent");
		
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "/resource/100/CXX/basicClass/DFFSMMM/";
		checkResults(result, resourceDir);
	}

	public void testFSM() throws Exception{
		rtcParam.setComponentKind("FiniteStateMachineComponent");
		
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "/resource/100/CXX/basicClass/FSM/";
		checkResults(result, resourceDir);
	}
	
	public void testDataFlow() throws Exception{
		rtcParam.setComponentKind("DataFlowComponent");
		
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "/resource/100/CXX/basicClass/DataFlow/";
		checkResults(result, resourceDir);
	}
	
	private void checkResults(List<GeneratedResult> result, String resourceDir) {
		assertEquals(default_file_num, result.size());
		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "foo.h");
		checkCode(result, resourceDir, "foo.cpp");
	}
	
}
