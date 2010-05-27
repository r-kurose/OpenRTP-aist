package jp.go.aist.rtm.rtcbuilder._test._100;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTestsCXX {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for jp.go.aist.rtm.rtcbuilder._test");
		//$JUnit-BEGIN$
		suite.addTestSuite(CXX042DiffTest.class);
		suite.addTestSuite(CXX042LuckTest.class);
		suite.addTestSuite(CXX042TemplateTestAIST.class);
		suite.addTestSuite(CXX042TemplateTestAIST3.class);
		suite.addTestSuite(CXX042VarTest.class);
		//
		suite.addTestSuite(CXXBaseClass.class);
		suite.addTestSuite(CXXBasicTest.class);
		suite.addTestSuite(CXXConfigSetTest.class);
		suite.addTestSuite(CXXConstraint.class);
		suite.addTestSuite(CXXIDLInheritTest.class);
		suite.addTestSuite(CXXIDLStruct.class);
		suite.addTestSuite(CXXMultiTest.class);
		suite.addTestSuite(CXXPrefixSuffixTest.class);
		suite.addTestSuite(CXXTemplateTestAIST2.class);
		//
		suite.addTestSuite(CXXDocLongTest.class);
		suite.addTestSuite(CXX042DocTest.class);
		suite.addTestSuite(CXXExCxtTest.class);
		suite.addTestSuite(CXXIDLPathTest.class);
		suite.addTestSuite(CXXImplTest.class);
		suite.addTestSuite(CXXLibraryTest.class);
		suite.addTestSuite(CXXLuckTest.class);
		suite.addTestSuite(CXXSystemConfigTest.class);
		suite.addTestSuite(CXXVariableTest.class);
		suite.addTestSuite(CXXModuleTest.class);
		suite.addTestSuite(CXXConfigSetTypeTest.class);
		//
		//$JUnit-END$
		return suite;
	}

}
