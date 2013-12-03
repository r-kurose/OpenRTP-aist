package jp.go.aist.rtm.rtcbuilder.safety._test._100;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTestsSafety {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for jp.go.aist.rtm.rtcbuilder._test");
		//$JUnit-BEGIN$
		suite.addTestSuite(ConfigSetTest.class);
		suite.addTestSuite(MultiTest.class);
		suite.addTestSuite(BaseTest.class);
		suite.addTestSuite(SafetyIDLInheritTest.class);
		suite.addTestSuite(ModuleTest.class);
		suite.addTestSuite(SafetyIDLTypeTest.class);
		suite.addTestSuite(BuildTest.class);
		//$JUnit-END$
		return suite;
	}

}
