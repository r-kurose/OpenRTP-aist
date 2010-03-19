package jp.go.aist.rtm.rtcbuilder._test;

import jp.go.aist.rtm.rtcbuilder._test._100.CXX042DiffTest;
import jp.go.aist.rtm.rtcbuilder._test._100.CXX042LuckTest;
import jp.go.aist.rtm.rtcbuilder._test._100.CXX042TemplateTestAIST;
import jp.go.aist.rtm.rtcbuilder._test._100.CXX042TemplateTestAIST3;
import jp.go.aist.rtm.rtcbuilder._test._100.CXX042VarTest;
import jp.go.aist.rtm.rtcbuilder._test._100.CXXBaseClass;
import jp.go.aist.rtm.rtcbuilder._test._100.CXXBasicTest;
import jp.go.aist.rtm.rtcbuilder._test._100.CXXConfigSetTest;
import jp.go.aist.rtm.rtcbuilder._test._100.CXXConstraint;
import jp.go.aist.rtm.rtcbuilder._test._100.CXXDocLongTest;
import jp.go.aist.rtm.rtcbuilder._test._100.CXXDocTest;
import jp.go.aist.rtm.rtcbuilder._test._100.CXXExCxtTest;
import jp.go.aist.rtm.rtcbuilder._test._100.CXXIDLInheritTest;
import jp.go.aist.rtm.rtcbuilder._test._100.CXXIDLPathTest;
import jp.go.aist.rtm.rtcbuilder._test._100.CXXIDLStruct;
import jp.go.aist.rtm.rtcbuilder._test._100.CXXImplTest;
import jp.go.aist.rtm.rtcbuilder._test._100.CXXLibraryTest;
import jp.go.aist.rtm.rtcbuilder._test._100.CXXLuckTest;
import jp.go.aist.rtm.rtcbuilder._test._100.CXXMultiTest;
import jp.go.aist.rtm.rtcbuilder._test._100.CXXPrefixSuffixTest;
import jp.go.aist.rtm.rtcbuilder._test._100.CXXSystemConfigTest;
import jp.go.aist.rtm.rtcbuilder._test._100.CXXTemplateTestAIST2;
import jp.go.aist.rtm.rtcbuilder._test._100.CXXVariableTest;
import jp.go.aist.rtm.rtcbuilder._test.com.CORBAParseCommentTest;
import jp.go.aist.rtm.rtcbuilder._test.com.CORBAParseInheritTest;
import jp.go.aist.rtm.rtcbuilder._test.com.CORBAParseMethodTypeTest;
import jp.go.aist.rtm.rtcbuilder._test.com.CORBAParseServiceTest;
import jp.go.aist.rtm.rtcbuilder._test.com.CORBAParseTest;
import jp.go.aist.rtm.rtcbuilder._test.com.CORBAParseTypeDefTest;
import jp.go.aist.rtm.rtcbuilder._test.com.CORBAParseTypeTest;
import jp.go.aist.rtm.rtcbuilder._test.com.StringUtilTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.IDLParamConverterTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.IDLParserTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.MergeBlockParserTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.PreProcessorTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for All");
		//$JUnit-BEGIN$
		//Common
		suite.addTestSuite(CORBAParseCommentTest.class);
		suite.addTestSuite(CORBAParseInheritTest.class);
		suite.addTestSuite(CORBAParseMethodTypeTest.class);
		suite.addTestSuite(CORBAParseServiceTest.class);
		suite.addTestSuite(CORBAParseTest.class);
		suite.addTestSuite(CORBAParseTypeDefTest.class);
		suite.addTestSuite(CORBAParseTypeTest.class);
		suite.addTestSuite(StringUtilTest.class);
		//CXX
		suite.addTestSuite(CXXDocLongTest.class);
		suite.addTestSuite(CXXDocTest.class);
		suite.addTestSuite(CXXExCxtTest.class);
		suite.addTestSuite(CXXIDLPathTest.class);
		suite.addTestSuite(CXXImplTest.class);
		suite.addTestSuite(CXXLuckTest.class);
		suite.addTestSuite(CXXLibraryTest.class);
		suite.addTestSuite(CXXSystemConfigTest.class);
		suite.addTestSuite(CXXVariableTest.class);
		suite.addTestSuite(IDLParamConverterTest.class);
		suite.addTestSuite(IDLParserTest.class);
		suite.addTestSuite(MergeBlockParserTest.class);
		suite.addTestSuite(PreProcessorTest.class);
//		suite.addTestSuite(TemplateTest.class);
		
		//C++1.0.0
		suite.addTestSuite(CXX042DiffTest.class);
		suite.addTestSuite(CXX042LuckTest.class);
		suite.addTestSuite(CXX042TemplateTestAIST.class);
		suite.addTestSuite(CXX042TemplateTestAIST3.class);
		suite.addTestSuite(CXX042VarTest.class);
		suite.addTestSuite(CXXBaseClass.class);
		suite.addTestSuite(CXXBasicTest.class);
		suite.addTestSuite(CXXConfigSetTest.class);
		suite.addTestSuite(CXXConstraint.class);
		suite.addTestSuite(CXXIDLInheritTest.class);
		suite.addTestSuite(CXXIDLStruct.class);
		suite.addTestSuite(CXXMultiTest.class);
		suite.addTestSuite(CXXPrefixSuffixTest.class);
		suite.addTestSuite(CXXTemplateTestAIST2.class);
		//$JUnit-END$
		return suite;
	}

}
