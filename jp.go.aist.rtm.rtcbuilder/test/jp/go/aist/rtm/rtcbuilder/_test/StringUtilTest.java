package jp.go.aist.rtm.rtcbuilder._test;

import jp.go.aist.rtm.rtcbuilder.util.StringUtil;
import junit.framework.TestCase;

public class StringUtilTest extends TestCase {
	public void testSplit() throws Exception{
		String original = "1234567890";
		String result = StringUtil.splitString(original, 5, " * ", 0);
		String expected = "12345\r\n * 67890";
		assertEquals(expected, result);
	}

	public void testSplit2() throws Exception{
		String original = "12345678901";
		String result = StringUtil.splitString(original, 5, " * ", 0);
		String expected = "12345\r\n * 67890\r\n * 1";
		assertEquals(expected, result);
	}

	public void testSplit3() throws Exception{
		String original = "１２３４５６７８９";
		String result = StringUtil.splitString(original, 5, " * ", 0);
		String expected = "１２３\r\n * ４５６\r\n * ７８９";
		assertEquals(expected, result);
	}

	public void testSplit4() throws Exception{
		String original = "１２３４５６７８９０";
		String result = StringUtil.splitString(original, 5, " * ", 0);
		String expected = "１２３\r\n * ４５６\r\n * ７８９\r\n * ０";
		assertEquals(expected, result);
	}

	public void testSplit5() throws Exception{
		String original = "1２３4５６7８９０";
		String result = StringUtil.splitString(original, 5, " * ", 0);
		String expected = "1２３\r\n * 4５６\r\n * 7８９\r\n * ０";
		assertEquals(expected, result);
	}

	public void testSplit6() throws Exception{
		String original = "12３４５67８９０";
		String result = StringUtil.splitString(original, 5, " * ", 0);
		String expected = "12３４\r\n * ５67８\r\n * ９０";
		assertEquals(expected, result);
	}

	public void testSplit7() throws Exception{
		String original = "12３４５67８９０";
		String result = StringUtil.splitString(original, 5, " * ", 3);
		String expected = "12\r\n * ３\r\n * ４\r\n * ５\r\n * 67\r\n * ８\r\n * ９\r\n * ０";
		assertEquals(expected, result);
	}

	public void testSplit8() throws Exception{
		String original = "1234567";
		String result = StringUtil.splitString(original, 10, " * ", 1);
		String expected = "1234567";
		assertEquals(expected, result);
	}

	public void testSplit9() throws Exception{
		String original = "123456<br>";
		String result = StringUtil.splitString(original, 8, " * ", 0);
		String expected = "123456<br>";
		assertEquals(expected, result);
	}
	
//	public void testSplit10() throws Exception{
//		String original = "<li><a href=\"/news-and-topics/\" title=\"新着情報\">新着情報</a></li>";
//		String result = StringUtil.splitString(original, 5, " * ", 0);
//		String expected = "<li><a href=\"/news-and-topics/\" title=\"新着情報\">\r\n * 新着情\r\n * 報</a>\r\n * </li>";
//		assertEquals(expected, result);
//	}

//	public void testSplit11() throws Exception{
//		String original = "<li><a href=\"/news-and-topics/\" title=\"新着情報\">新着情報</a></li>";
//		String result = StringUtil.splitString(original, 50, " * ", 0);
//		String expected = "<li><a href=\"/news-and-topics/\" title=\"新着情報\">\r\n * 新着情報</a></li>";
//		assertEquals(expected, result);
//	}

//	public void testSplit12() throws Exception{
//		String original = "<li><a href=\"/news-and-topics/\" title=\"新着情報\">新着情報</a></li>";
//		String result = StringUtil.splitString(original, 4, " * ", 0);
//		String expected = "<li>\r\n * <a href=\"/news-and-topics/\" title=\"新着情報\">\r\n * 新着\r\n * 情報\r\n * </a>\r\n * </li>";
//		assertEquals(expected, result);
//	}
	
	public void testSplit13() throws Exception{
		String original = "<li><span><br></span></li>";
		String result = StringUtil.splitString(original, 15, " * ", 0);
		String expected = "<li><span><br></span>\r\n * </li>";
		assertEquals(expected, result);
	}
	
	public void testSplit14() throws Exception{
		String original = "<li><span><br></span></li>";
		String result = StringUtil.splitString(original, 14, " * ", 0);
		String expected = "<li><span><br>\r\n * </span></li>";
		assertEquals(expected, result);
	}
	
	public void testSplit15() throws Exception{
		String original = "<li><span><br></span></li>";
		String result = StringUtil.splitString(original, 2, " * ", 0);
		String expected = "<li>\r\n * <span>\r\n * <br>\r\n * </span>\r\n * </li>";
		assertEquals(expected, result);
	}

	public void testSplit9_2() throws Exception{
		String original = "123456<br>";
		String result = StringUtil.splitString(original, 8, " * ", 3);
		String expected = "12345\r\n * 6<br>";
		assertEquals(expected, result);
	}

//	public void testSplit10_2() throws Exception{
//		String original = "<li><a href=\"/news-and-topics/\" title=\"新着情報\">新着情報</a></li>";
//		String result = StringUtil.splitString(original, 5, " * ", 1);
//		String expected = "<li>\r\n * <a href=\"/news-and-topics/\" title=\"新着情報\">\r\n * 新着情\r\n * 報</a>\r\n * </li>";
//		assertEquals(expected, result);
//	}
	
//	public void testSplit11_2() throws Exception{
//		String original = "<li><a href=\"/news-and-topics/\" title=\"新着情報\">新着情報</a></li>";
//		String result = StringUtil.splitString(original, 50, " * ", 45);
//		String expected = "<li><a href=\"/news-and-topics/\" title=\"新着情報\">\r\n * 新着情報</a></li>";
//		assertEquals(expected, result);
//	}
	
	public void testSplit13_2() throws Exception{
		String original = "<li><span><br></span></li>";
		String result = StringUtil.splitString(original, 15, " * ", 5);
		String expected = "<li><span>\r\n * <br></span>\r\n * </li>";
		System.out.println(expected);
		System.out.println(result);
		assertEquals(expected, result);
	}
	
	public void testSplit14_2() throws Exception{
		String original = "<li><span><br></span></li>";
		String result = StringUtil.splitString(original, 14, " * ", 4);
		String expected = "<li><span>\r\n * <br></span>\r\n * </li>";
		System.out.println(expected);
		System.out.println(result);
		assertEquals(expected, result);
	}
	
	public void testSplit15_2() throws Exception{
		String original = "<li><span><br></span></li>";
		String result = StringUtil.splitString(original, 2, " * ", 1);
		String expected = "<li>\r\n * <span>\r\n * <br>\r\n * </span>\r\n * </li>";
		assertEquals(expected, result);
	}
	
	
	public void testSplit16() throws Exception{
		String original = "１２３４５６７８９０１２３４５６７８９０<br>";
		String result = StringUtil.splitString(original, 16, " * ", 0);
		String expected = "１２３４５６７８\r\n * ９０１２３４５６\r\n * ７８９０<br>";
		assertEquals(expected, result);
	}
	public void testSplit17() throws Exception{
		String original = "１２３４５<br/>６７８９０１２３４５６７８９０<br>";
		String result = StringUtil.splitString(original, 16, " * ", 0);
		String expected = "１２３４５\r\n * ６７８９０１２３\r\n * ４５６７８９０<br>";
		assertEquals(expected, result);
	}
}
