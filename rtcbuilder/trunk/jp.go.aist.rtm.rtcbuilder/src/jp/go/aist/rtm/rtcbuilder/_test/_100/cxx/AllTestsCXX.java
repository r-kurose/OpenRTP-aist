package jp.go.aist.rtm.rtcbuilder._test._100.cxx;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTestsCXX {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for jp.go.aist.rtm.rtcbuilder._test");
		//$JUnit-BEGIN$
		suite.addTestSuite(CXXBaseClass.class);
		suite.addTestSuite(CXXConstraint.class);
		//
		suite.addTestSuite(CXXTemplateTest.class);
		suite.addTestSuite(CXXConfigSetTest.class);
		suite.addTestSuite(CXXMultiTest.class);
		suite.addTestSuite(CXXPrefixSuffixTest.class);
		
		//$JUnit-END$
		return suite;
	}

}
