package jp.go.aist.rtm.rtcbuilder.util;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;


public class StringUtil {

	private static final String START_MARK = "<";
	private static final String END_MARK = ">";

	public static boolean checkDigitAlphabet(String source) {
		if(source==null) return true;
	    for(int intIdx = 0; intIdx < source.length(); intIdx++) {
	        char target = source.charAt(intIdx);
	        if( (target < '0' || target > '9') &&    //数字チェック
	            (target < 'a' || target > 'z') &&    //小文字アルファベットチェック
	            (target < 'A' || target > 'Z') &&    //大文字アルファベットチェック
	            (target != '_') && (target != '-') && (target != ':')) {
	             return false;
	        }
	    }
	    return true;
	}

	public static String splitString(String source, int width, String prefix, int offset) {
		if( source==null || source.equals("") || width<=0 ) return "";

		String sep = System.getProperty("line.separator");
		String lines[] = source.split(IRtcBuilderConstants.NEWLINE_CODE);

		// 一文字ずつ保持
		StringBuffer strBuf = new StringBuffer();
		// 戻り値用
		StringBuffer result = new StringBuffer();
		
		// resultに投入する前のwork
		ArrayList<StringBuffer> workResult = new ArrayList<StringBuffer>();
		ArrayList<StringBuffer> temp = new ArrayList<StringBuffer>();
		
		int length = offset;
		boolean bolFlg = false;
		
		for( int intline=0; intline<lines.length; intline++ ) {
			source = lines[intline];
			for( int intIdx=0; intIdx<source.length(); intIdx++ ) {
				char c = source.charAt(intIdx);
				if ((c <= '\u007e') || // 英数字
						(c == '\u00a5') || // \記号
						(c == '\u203e') || // ~記号
						(c >= '\uff61' && c <= '\uff9f') // 半角カナ
				) {
					length += 1;
				} else {
					length += 2;
				}
				// 一文字ずつ取得する
				strBuf.append(c);
				// 改行文字の場合は，その前までを投入
				if (String.valueOf(c).equals(sep)) {
					workResult.add(strBuf);
					strBuf = new StringBuffer();
					length = 0;
				}
				
				if (String.valueOf(c).equals(START_MARK)) {
					// tempの値をworkに投入
					if (temp.size() > 0) {
						workResult.addAll(temp);
						temp = new ArrayList<StringBuffer>();
					}
					bolFlg = false;
				}
				
				if (String.valueOf(c).equals(END_MARK)) {
					bolFlg = true;
					if (temp.size() > 0) {
						// 終了文字までをStringBufferにため、workに投入
						StringBuffer workBuffer = new StringBuffer();
						for (int intIdx2=0; intIdx2 < temp.size(); intIdx2++) {
							workBuffer.append(temp.get(intIdx2));
						}
						workBuffer.append(strBuf);
						workResult.add(workBuffer);
						// 初期化
						bolFlg = false;
						temp = new ArrayList<StringBuffer>();
						strBuf = new StringBuffer();
						length = 0;
					}
				}
				
				if(length >= width) {
					// width分文字列を取得した時に終了文字が含まれていなければtempへ
					// 含まれていたらworkへ。
					if (bolFlg == false) {
						temp.add(strBuf);
					} else {
						workResult.add(strBuf);
					}
					strBuf = new StringBuffer();
					length = 0;
				}
				
			}
	
			// tempに残っている文字列をworkへ
			if (temp.size() > 0) workResult.addAll(temp);
			// strBufに残っている文字列をworkへ
			if (strBuf.length() > 0) workResult.add(strBuf);
			temp = new ArrayList<StringBuffer>();
			strBuf = new StringBuffer();
			length = 0;
		}
		
		// workResultからresultを成形する
		for (int intIdx=0; intIdx < workResult.size();intIdx++){
			if (intIdx > 0)	result.append(prefix);
			result.append(workResult.get(intIdx));
			if (intIdx+1 < workResult.size() ) result.append("\r\n");
		}

		return result.toString();
	}

//		if( source==null || source.equals("") || width<=0 ) return "";
//		StringBuffer result = new StringBuffer();
//		int length = offset;
//		int startpos = 0;
//		for( int intIdx=0; intIdx<source.length(); intIdx++ ) {
//			length += String.valueOf(source.charAt(intIdx)).getBytes().length;
//			if( width<=length ) {
//				if( result.length() > 0 )
//					result.append(prefix);
//				result.append(source.substring(startpos, intIdx+1));
//				if( intIdx+1 < source.length() )
//					result.append("\r\n");
//				startpos = intIdx+1;
//				length = 0;
//			}
//		}
//		if( startpos != source.length() ) {
//			if( result.length()>0 )
//				result.append(prefix);
//			result.append(source.substring(startpos));
//		}
//		return result.toString();
//	}
//
	
	public static String connectMessageWithSepalator(String[] ss){
		if( ss==null ) return "";
		
		String result = "";
		for( int i=0; i<ss.length; i++ ){
			result += ss[i];
			if( !"".equals(ss[i]) )
				result += System.getProperty("line.separator");
		}
		
		return result;
	}
	
	public static boolean matchKey(String source, String key) {
		if(key.equals("*")) return true;
		String[] keyList = key.split(",");
		for(String target : keyList) {
			if(source.trim().equals(target.trim())) return true;
		}
		
		return false;
	}

	public static boolean matchKey(List<String> source, String key) {
		if(key.equals("*")) return true;
		String[] keyList = key.split(",");
		for(String target : keyList) {
			if(source.contains(target.trim())) return true;
		}
		
		return false;
	}
}
