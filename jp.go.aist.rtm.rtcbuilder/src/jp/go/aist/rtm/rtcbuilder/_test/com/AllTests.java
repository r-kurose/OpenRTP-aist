package jp.go.aist.rtm.rtcbuilder._test.com;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for All Languages");
		//$JUnit-BEGIN$
		//Common
		suite.addTestSuite(CORBAParseTypeTest.class);
		suite.addTestSuite(CORBAParseTest.class);
		suite.addTestSuite(CORBAParseServiceTest.class);
		suite.addTestSuite(CORBAParseMethodTypeTest.class);
		suite.addTestSuite(CORBAParseCommentTest.class);
		//$JUnit-END$
		return suite;
	}

}
