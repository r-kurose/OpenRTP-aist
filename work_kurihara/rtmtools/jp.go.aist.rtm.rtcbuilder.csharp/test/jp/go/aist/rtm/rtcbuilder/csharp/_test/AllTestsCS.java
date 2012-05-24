package jp.go.aist.rtm.rtcbuilder.csharp._test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTestsCS {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for jp.go.aist.rtm.rtcbuilder._test");
		//$JUnit-BEGIN$
		suite.addTestSuite(CSBaseTest.class);
		suite.addTestSuite(CSConfigSetTest.class);
		suite.addTestSuite(CSTestAIST.class);
		suite.addTestSuite(CSTestAIST2.class);
		//$JUnit-END$
		return suite;
	}

}
