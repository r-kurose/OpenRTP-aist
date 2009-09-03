package jp.go.aist.rtm.rtcbuilder._test;

import jp.go.aist.rtm.rtcbuilder._test._100.cxx.CXXBaseClass;
import jp.go.aist.rtm.rtcbuilder._test._100.cxx.CXXConstraint;
import jp.go.aist.rtm.rtcbuilder._test.com.CORBAParseTypeTest;
import jp.go.aist.rtm.rtcbuilder._test.com.StringUtilTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.CXXConfigSetTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.CXXDiffTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.CXXDocLongTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.CXXDocTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.CXXExCxtTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.CXXIDLPathTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.CXXImplTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.CXXLibraryTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.CXXLuckTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.CXXMultiTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.CXXSystemConfigTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.CXXTemplateTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.CXXTemplateTestAIST;
import jp.go.aist.rtm.rtcbuilder._test.cxx.CXXTemplateTestAIST2;
import jp.go.aist.rtm.rtcbuilder._test.cxx.CXXVarTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.CXXVariableTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.IDLParamConverterTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.IDLParserTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.MergeBlockParserTest;
import jp.go.aist.rtm.rtcbuilder._test.cxx.PreProcessorTest;
import jp.go.aist.rtm.rtcbuilder._test.cxxwin.CXXWinDocLongTest;
import jp.go.aist.rtm.rtcbuilder._test.cxxwin.CXXWinIDLPathTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for All Languages");
		//$JUnit-BEGIN$
		//Common
		suite.addTestSuite(CORBAParseTypeTest.class);
		suite.addTestSuite(StringUtilTest.class);
		//CXX
		suite.addTestSuite(CXXConfigSetTest.class);
		suite.addTestSuite(CXXDiffTest.class);
		suite.addTestSuite(CXXMultiTest.class);
		suite.addTestSuite(CXXLuckTest.class);
		suite.addTestSuite(IDLParamConverterTest.class);
		suite.addTestSuite(CXXTemplateTestAIST.class);
		suite.addTestSuite(MergeBlockParserTest.class);
		suite.addTestSuite(IDLParserTest.class);
		suite.addTestSuite(CXXTemplateTest.class);
		suite.addTestSuite(PreProcessorTest.class);
		suite.addTestSuite(CXXVarTest.class);
		suite.addTestSuite(CXXIDLPathTest.class);
		suite.addTestSuite(CXXDocTest.class);
		suite.addTestSuite(CXXDocLongTest.class);
		suite.addTestSuite(CXXExCxtTest.class);
		suite.addTestSuite(CXXSystemConfigTest.class);
		suite.addTestSuite(CXXImplTest.class);
		suite.addTestSuite(CXXVariableTest.class);
		suite.addTestSuite(CXXTemplateTestAIST2.class);
		suite.addTestSuite(CXXLibraryTest.class);
		//CXXWin
		suite.addTestSuite(jp.go.aist.rtm.rtcbuilder._test.cxxwin.CXXConfigSetTest.class);
		suite.addTestSuite(jp.go.aist.rtm.rtcbuilder._test.cxxwin.CXXDiffTest.class);
		suite.addTestSuite(jp.go.aist.rtm.rtcbuilder._test.cxxwin.CXXMultiTest.class);
		suite.addTestSuite(jp.go.aist.rtm.rtcbuilder._test.cxxwin.CXXLuckTest.class);
		suite.addTestSuite(jp.go.aist.rtm.rtcbuilder._test.cxxwin.CXXTemplateTestAIST.class);
		suite.addTestSuite(jp.go.aist.rtm.rtcbuilder._test.cxxwin.CXXTemplateTest.class);
		suite.addTestSuite(jp.go.aist.rtm.rtcbuilder._test.cxxwin.CXXVarTest.class);
		suite.addTestSuite(CXXWinIDLPathTest.class);
		suite.addTestSuite(CXXWinDocLongTest.class);
//		//Java
//		suite.addTestSuite(ConfigSetTest.class);
//		suite.addTestSuite(DiffTest.class);
//		suite.addTestSuite(LuckTest.class);
//		suite.addTestSuite(MultiTest.class);
//		suite.addTestSuite(AISTTest.class);
//		suite.addTestSuite(BaseTest.class);
//		suite.addTestSuite(BuildPathTest.class);
//		suite.addTestSuite(VarTest.class);
//		suite.addTestSuite(JavaIDLPathTest.class);
//		suite.addTestSuite(JavaDocTest.class);
//		suite.addTestSuite(JavaDocLongTest.class);
//		suite.addTestSuite(MLTest.class);
//		suite.addTestSuite(JavaExCxtTest.class);
//		suite.addTestSuite(JavaSystemConfigTest.class);
//		suite.addTestSuite(JavaImplTest.class);
//		suite.addTestSuite(JavaVariableTest.class);
//		//Python
//		suite.addTestSuite(jp.go.aist.rtm.rtcbuilder._test.python.ConfigSetTest.class);
//		suite.addTestSuite(jp.go.aist.rtm.rtcbuilder._test.python.DiffTest.class);
//		suite.addTestSuite(jp.go.aist.rtm.rtcbuilder._test.python.LuckTest.class);
//		suite.addTestSuite(jp.go.aist.rtm.rtcbuilder._test.python.AISTTest.class);
//		suite.addTestSuite(jp.go.aist.rtm.rtcbuilder._test.python.MultiTest.class);
//		suite.addTestSuite(jp.go.aist.rtm.rtcbuilder._test.python.BaseTest.class);
//		suite.addTestSuite(jp.go.aist.rtm.rtcbuilder._test.python.VarTest.class);
//		suite.addTestSuite(PyDocTest.class);
//		suite.addTestSuite(PyDocLongTest.class);
//		suite.addTestSuite(PyExCxtTest.class);
//		suite.addTestSuite(PySystemConfigTest.class);
//		suite.addTestSuite(PyImplTest.class);
//		suite.addTestSuite(PyVariableTest.class);
		//C++0.4.2
		suite.addTestSuite(jp.go.aist.rtm.rtcbuilder._test._042.cxx.CXXConfigSetTest.class);
		suite.addTestSuite(jp.go.aist.rtm.rtcbuilder._test._042.cxx.CXXTemplateTestAIST.class);
		suite.addTestSuite(jp.go.aist.rtm.rtcbuilder._test._042.cxx.CXXTemplateTestAIST2.class);
		suite.addTestSuite(jp.go.aist.rtm.rtcbuilder._test._042.cxx.CXXDiffTest.class);
		suite.addTestSuite(jp.go.aist.rtm.rtcbuilder._test._042.cxx.CXXMultiTest.class);
		suite.addTestSuite(jp.go.aist.rtm.rtcbuilder._test._042.cxx.CXXLuckTest.class);
		//C++1.0.0
		suite.addTestSuite(CXXBaseClass.class);
		suite.addTestSuite(CXXConstraint.class);
		//$JUnit-END$
		return suite;
	}

}
