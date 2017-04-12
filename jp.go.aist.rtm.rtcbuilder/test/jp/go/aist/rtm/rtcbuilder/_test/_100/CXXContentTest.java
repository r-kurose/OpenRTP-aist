package jp.go.aist.rtm.rtcbuilder._test._100;

import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;

public class CXXContentTest extends TestBase {

	RtcParam rtcParam;
	GeneratorParam genParam;

	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "/resource/work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);
	}

	public void testBasic() throws Exception {
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);
		//
		rtcParam.setDetailContent(IRtcBuilderConstants.ACTIVITY_INITIALIZE, "  init();");
		rtcParam.setDetailContent(IRtcBuilderConstants.ACTIVITY_FINALIZE, "  fin();");
		rtcParam.getPrivateAttributes().add("  privateParam");
		rtcParam.getPrivateAttributes().add("  privateParam2");
		rtcParam.getProtectedAttributes().add("  protectedParam");
		rtcParam.getPublicAttributes().add("  publicdParam");
		rtcParam.getPrivateOperations().add("  privateOpe()");
		rtcParam.getProtectedOperations().add("  protectedOpe()");
		rtcParam.getPublicOperations().add("  publicOpe()");
		rtcParam.setPrivateOpeSource("  private void privateOpe(){}");
		rtcParam.setProtectedOpeSource("  protected void protectedOpe(){}");
		rtcParam.setPublicOpeSource("  public void publicOpe(){}");

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath + "/resource/100/CXX/Content/";

		assertEquals(default_file_num, result.size());
		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "foo.h");
		checkCode(result, resourceDir, "foo.cpp");
	}

}
