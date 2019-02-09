package test;

import java.io.FileOutputStream;
import java.net.URLDecoder;

import junit.framework.TestCase;
//
public class XMLHandlerTest extends TestCase {
	// /RTSystemEditor/a　bと/RTSystemEditor/a%20bディレクトリが存在するという前提のテスト
	public void testHasSpace() throws Exception {
		String fileName = "/RTSystemEditor/a%20b/test.xml";
		try(FileOutputStream fos = new FileOutputStream(fileName)) {
		}
	}
	public void testHasSpace2() throws Exception {
		String fileName = "/RTSystemEditor/a b/test.xml";
		try( FileOutputStream fos = new FileOutputStream(fileName) ) {
		}
	}
	public void testHasSpace3() throws Exception {
		String fileName = "/RTSystemEditor/a%20b/test.xml";
		try( FileOutputStream fos = new FileOutputStream(URLDecoder.decode(fileName, "UTF-8")) ) {
		}
	}
	public void testHasSpace4() throws Exception {
		String fileName = "/RTSystemEditor/a%2520b/test.xml";
		try( FileOutputStream fos = new FileOutputStream(URLDecoder.decode(fileName, "UTF-8")) ) {
		}
	}
}
