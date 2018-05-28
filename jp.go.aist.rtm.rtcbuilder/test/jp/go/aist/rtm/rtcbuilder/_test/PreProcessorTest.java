package jp.go.aist.rtm.rtcbuilder._test;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.generator.PreProcessor;

public class PreProcessorTest extends TestBase {

	@Override
	protected void setUp() throws Exception {
	}

	public void testIsInclude() throws Exception {
		String result;

		List<String> pathList = new ArrayList<String>();
		pathList.add(rootPath + "\\resource");
		result = PreProcessor.getIncludeFileContent("#include <test.txt>", pathList, null, true);
		assertEquals("testTextContents\r\n", result);

		result = PreProcessor.getIncludeFileContent("#include  \"test.txt\"", pathList, null, true);
		assertEquals("testTextContents\r\n", result);

	}

	public void testDefault() throws Exception {
		String result;

		result = PreProcessor.parse("", null, null, true);
		assertEquals("", result);

		result = PreProcessor.parse(
				"\n#IFDEF \n#IFDEF HOGE_TYPE \nhoge\n// #comment#\n#ENDIF",
				null, null, true);
		assertEquals("\n\n\nhoge\n// #comment#\n", result);

		result = PreProcessor.parse(
				"\n#IFDEF \n#IFDEF HOGE_TYPE \nhoge\n// #comment#\n#ENDIF",
				null, null, true);
		assertEquals("\n\n\nhoge\n// #comment#\n", result);

	}
}
