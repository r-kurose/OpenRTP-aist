package jp.go.aist.rtm.rtcbuilder._test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for jp.go.aist.rtm.rtcbuilder._test");
		//$JUnit-BEGIN$
		//Common
		suite.addTestSuite(CORBAParseTypeTest.class);
		suite.addTestSuite(CORBAParseTest.class);
		suite.addTestSuite(CORBAParseServiceTest.class);
		suite.addTestSuite(CORBAParseMethodTypeTest.class);
		suite.addTestSuite(CORBAParseCommentTest.class);
		suite.addTestSuite(CORBAParseTypeDefTest.class);
		suite.addTestSuite(CORBAParseInheritTest.class);
		suite.addTestSuite(StringUtilTest.class);
		//cxx
		suite.addTestSuite(IDLParamConverterTest.class);
		suite.addTestSuite(MergeBlockParserTest.class);
		suite.addTestSuite(IDLParserTest.class);
		suite.addTestSuite(PreProcessorTest.class);
		//
		suite.addTestSuite(CheckIDLTest.class);
		//
		suite.addTestSuite(CXXIDLType.class);
		suite.addTestSuite(CXXDocLongTest.class);
		suite.addTestSuite(CXXDataPortIDLTest.class);
		suite.addTestSuite(CXXExCxtTest.class);
		suite.addTestSuite(CXXBasicTest.class);
		suite.addTestSuite(CXXBuildTest.class);
		suite.addTestSuite(CXXLuckTest.class);
		suite.addTestSuite(CXXTemplateTestAIST2.class);
		suite.addTestSuite(CXXPrefixSuffixTest.class);
		suite.addTestSuite(CXXIDLModuleTest.class);
		suite.addTestSuite(CXXConstraint.class);
		suite.addTestSuite(CXXIDLInheritTest.class);
		suite.addTestSuite(CXXIDLPathTest.class);
		suite.addTestSuite(CXXImplTest.class);
		suite.addTestSuite(CXXMultiTest.class);
		suite.addTestSuite(CXXVariableTest.class);
		suite.addTestSuite(CXXSystemConfigTest.class);
		suite.addTestSuite(CXXBaseClass.class);
		suite.addTestSuite(CXXLibraryTest.class);
		suite.addTestSuite(CXXConfigSetTest.class);
		suite.addTestSuite(CXXIDLStructTest.class);
		suite.addTestSuite(CXXManipTest.class);
		suite.addTestSuite(CXXContentTest.class);
		//
		suite.addTestSuite(TemplateHelperTest.class);
		//
		suite.addTestSuite(TargetEnvParamTest.class);
		suite.addTestSuite(DataPortParamTest.class);
		suite.addTestSuite(ConfigSetParamTest.class);
		suite.addTestSuite(RtcParamTest.class);
		suite.addTestSuite(ServicePortParamTest.class);
		suite.addTestSuite(ServicePortInterfaceParamTest.class);
		suite.addTestSuite(RecordedListTest.class);
		suite.addTestSuite(ConfigParameterParamTest.class);
		suite.addTestSuite(ParamActionsTest.class);
		//$JUnit-END$
		return suite;
	}

}
