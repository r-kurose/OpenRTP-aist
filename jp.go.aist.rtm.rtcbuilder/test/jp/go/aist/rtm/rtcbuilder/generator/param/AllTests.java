package jp.go.aist.rtm.rtcbuilder.generator.param;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for jp.go.aist.rtm.rtcbuilder.generator.param");
		//$JUnit-BEGIN$
		suite.addTestSuite(TargetEnvParamTest.class);
		suite.addTestSuite(DataPortParamTest.class);
		suite.addTestSuite(ConfigSetParamTest.class);
		suite.addTestSuite(RtcParamTest.class);
		suite.addTestSuite(ServicePortParamTest.class);
		suite.addTestSuite(ServicePortInterfaceParamTest.class);
		suite.addTestSuite(RecordedListTest.class);
		suite.addTestSuite(ConfigParameterParamTest.class);
		suite.addTestSuite(ActionsParamTest.class);
		//$JUnit-END$
		return suite;
	}

}
