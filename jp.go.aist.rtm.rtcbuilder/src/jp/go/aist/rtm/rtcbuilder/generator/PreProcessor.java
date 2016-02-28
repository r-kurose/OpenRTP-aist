package jp.go.aist.rtm.rtcbuilder.generator;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.go.aist.rtm.rtcbuilder.IRTCBMessageConstants;
import jp.go.aist.rtm.rtcbuilder.util.FileUtil;

/**
 * プリプロセッサ
 * <p>
 * パースの前に、対象文字列に対して実行する。 <br>
 * 「#include<hoge>("")」のみ対応。その他は空文字に変換する
 */
public class PreProcessor {
	private static final Pattern PREPROSESSOR_PATTERN = Pattern.compile(
			"^#.*$", Pattern.MULTILINE);

	private static final Pattern INCLUDE_PATTERN = Pattern
			.compile("^#include\\s*(<|\")(.*)(>|\").*$");

	private static final Pattern COMMENT_PATTERN = Pattern
			.compile("/\\*(.*?)(\\*/)", Pattern.DOTALL);

	private static final Pattern SPACE_PATTERN = Pattern
			.compile("^ +", Pattern.MULTILINE);
	
	private static final int INCLUDE_FILE_INDEX = 2;

	/**
	 * 対象文字列に対してプリプロセッサを実行する。
	 * 全プリプロセッサを削除する
	 * 
	 * @param target
	 *            対象文字列
	 * @return 実行後文字列
	 */
	public static String parseAlltoSpace(String target) {
		String targetNoCmt = eraseComments(target);
		//
		StringBuffer targetNoSpace = new StringBuffer();
		Matcher matcherSpace = SPACE_PATTERN.matcher(targetNoCmt);
		while (matcherSpace.find()) {
			matcherSpace.appendReplacement(targetNoSpace, Matcher.quoteReplacement(""));
		}
		matcherSpace.appendTail(targetNoSpace);
		//
		StringBuffer result = new StringBuffer();
		Matcher matcher = PREPROSESSOR_PATTERN.matcher(targetNoSpace.toString());
		while (matcher.find()) {
			matcher.appendReplacement(result, Matcher.quoteReplacement(""));
		}
		matcher.appendTail(result);
		//

		return result.toString();
	}
	
	private static String eraseComments(String target) {
		StringBuffer result = new StringBuffer();
		Matcher matcher = COMMENT_PATTERN.matcher(target);
		while (matcher.find()) {
			matcher.appendReplacement(result, Matcher.quoteReplacement(""));
		}
		matcher.appendTail(result);

		return result.toString();
	}

	/**
	 * 対象文字列に対してプリプロセッサを実行する。
	 * 
	 * @param target
	 *            対象文字列
	 * @return 実行後文字列
	 * @throws IOException 
	 */
	public static String parse(String target, File includeBaseDir, List<String> includeFiles) throws IOException {
		String targetNoCmt = eraseComments(target);
		/////
		StringBuffer result = new StringBuffer();
		Matcher matcher = PREPROSESSOR_PATTERN.matcher(targetNoCmt);
		while (matcher.find()) {
			String replateString = "";
			String includeFileContent = getIncludeFileContentThoroughgoing(
					matcher.group(), includeBaseDir, includeFiles);
			if (includeFileContent != null) {
				replateString = includeFileContent;
			}

			matcher.appendReplacement(result, Matcher
					.quoteReplacement(replateString));
		}
		matcher.appendTail(result);

		return result.toString();
	}

	public static String getIncludeFileContentThoroughgoing(String directive,
			File includeBaseDir, List<String> includeFiles) throws IOException {
		String result = getIncludeFileContent(directive, includeBaseDir, includeFiles);
		if (result != null) {
			result = parse(result, includeBaseDir, includeFiles);
		}

		return result;
	}

	/**
	 * インクルードであった場合に、ファイルのコンテンツを取得する
	 * 
	 * @param directive
	 * @param includeBaseDir
	 * @return
	 * @throws IOException 
	 */
	public static String getIncludeFileContent(String directive,
			File includeBaseDir, List<String> includeFiles) throws IOException {
		String result = null;
		
		Matcher matcher = INCLUDE_PATTERN.matcher(directive);
		if (matcher.find()) {
			String filePath = matcher.group(INCLUDE_FILE_INDEX);
			if (includeBaseDir == null) {
				throw new RuntimeException(IRTCBMessageConstants.ERROR_PREPROCESSOR + filePath);
			}
			String includeFilePath = new File(includeBaseDir, filePath).getAbsolutePath();
			File target = new File(includeFilePath);
			if(target.exists()==false) {
				throw new RuntimeException("Include of IDL '" + filePath + "' cannot be solved");
			}
			result = FileUtil.readFile(includeFilePath);
			if(includeFiles!=null) {
				if( !includeFiles.contains(includeFilePath) ) {
					includeFiles.add(includeFilePath);
				}
			}
		}

		return result;
	}
}
