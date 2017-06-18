package jp.go.aist.rtm.toolscommon.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import jp.go.aist.rtm.toolscommon.model.core.Point;

import org.junit.Test;

public class RtcProfileHandlerTest {

	@Test
	public void testConvertFromBendPointString() {
		RtsProfileHandler handler = new RtsProfileHandler();

		// ブランクパターン
		{
			String[] patterns = new String[] { null, "", "{}", "{", "}" };
			for (String pattern : patterns) {
				Map<Integer, Point> points = handler
						.convertFromBendPointString(pattern);
				String msg = String.format("pattern: <%s>", pattern);
				assertNotNull(msg, points);
				assertTrue(msg, points.isEmpty());
			}
		}

		// 2ポイント正常＋ポイント書式不正パターン
		{
			String[] patterns = new String[] { //
					"{1:(100,100),2:(200,200)}", // 正常
					" { 1 : ( 100 , 100 ) , 2 : ( 200 , 200 ) } ", // 正常(スペースあり)
					"1:(100,100),2:(200,200)", // 正常(外括弧なし)
					"{1:(100,100),,2:(200,200)}",
					"{1:(100,100),3,2:(200,200)}",
					"{1:(100,100),:,2:(200,200)}",
					"{1:(100,100),3:,2:(200,200)}",
					"{1:(100,100),3:(,2:(200,200)}",
					"{1:(100,100),3:),2:(200,200)}",
					"{1:(100,100),3:(),2:(200,200)}",
					"{1:(100,100),3:(,),2:(200,200)}",
					"{1:(100,100),3:(150,),2:(200,200)}",
					"{1:(100,100),3:(,150),2:(200,200)}" };
			for (String pattern : patterns) {
				Map<Integer, Point> points = handler
						.convertFromBendPointString(pattern);
				String msg = String.format("pattern: <%s>", pattern);
				assertNotNull(msg, points);
				assertEquals(msg, 2, points.size());
				assertEquals(msg, 100, points.get(1).getX());
				assertEquals(msg, 100, points.get(1).getY());
				assertEquals(msg, 200, points.get(2).getX());
				assertEquals(msg, 200, points.get(2).getY());
			}
		}

		// 3ポイント正常パターン
		{
			Map<Integer, Point> points = handler
					.convertFromBendPointString("{1:(100,100),3:(150,150),2:(200,200)}");
			assertNotNull(points);
			assertEquals(3, points.size());
			assertEquals(100, points.get(1).getX());
			assertEquals(100, points.get(1).getY());
			assertEquals(200, points.get(2).getX());
			assertEquals(200, points.get(2).getY());
			assertEquals(150, points.get(3).getX());
			assertEquals(150, points.get(3).getY());
		}
	}

}
