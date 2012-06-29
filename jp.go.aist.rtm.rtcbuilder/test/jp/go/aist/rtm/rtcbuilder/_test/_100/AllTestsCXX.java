package jp.go.aist.rtm.rtcbuilder._test._100;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTestsCXX {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for jp.go.aist.rtm.rtcbuilder._test");
		//$JUnit-BEGIN$
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
		//$JUnit-END$
		return suite;
	}

}
