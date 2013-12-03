package jp.go.aist.rtm.rtcbuilder._test.cxx;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTestsCXX {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for jp.go.aist.rtm.rtcbuilder._test");
		//$JUnit-BEGIN$
		suite.addTestSuite(IDLParamConverterTest.class);
		suite.addTestSuite(MergeBlockParserTest.class);
		suite.addTestSuite(IDLParserTest.class);
		suite.addTestSuite(PreProcessorTest.class);
		//$JUnit-END$
		return suite;
	}

}
