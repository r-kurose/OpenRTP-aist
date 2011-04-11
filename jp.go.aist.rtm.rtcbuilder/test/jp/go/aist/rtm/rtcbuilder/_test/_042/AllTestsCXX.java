package jp.go.aist.rtm.rtcbuilder._test._042;

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
		suite.addTestSuite(CXX042DocTest.class);
		//
		//$JUnit-END$
		return suite;
	}

}
