package jp.go.aist.rtm.rtcbuilder.python._test._100;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTestsPy {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for jp.go.aist.rtm.rtcbuilder.python._test._100");
		//$JUnit-BEGIN$
		suite.addTestSuite(BaseTest.class);
		suite.addTestSuite(AISTTest.class);
		suite.addTestSuite(ConfigSetTest.class);
		suite.addTestSuite(PyDocTest.class);
		suite.addTestSuite(PyIDLInheritTest.class);
		suite.addTestSuite(PyImplTest.class);
		suite.addTestSuite(PyModuleTest.class);
		//$JUnit-END$
		return suite;
	}

}
