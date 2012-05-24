package jp.go.aist.rtm.rtcbuilder.vbdotnet._test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTestsVbDotNet {
	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for jp.go.aist.rtm.rtcbuilder._test");
		//$JUnit-BEGIN$
		suite.addTestSuite(VbDotNetBaseTest.class);
		suite.addTestSuite(VbDotNetConfigSetTest.class);
		suite.addTestSuite(VbDotNetTestAIST.class);
		suite.addTestSuite(VbDotNetTestAIST2.class);
		//$JUnit-END$
		return suite;
	}

}
