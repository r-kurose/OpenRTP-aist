package jp.go.aist.rtm.rtcbuilder.java.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigSetParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceMethodParam;

/**
 * Javaソースを出力する際に使用されるユーティリティ
 */
public class JavaConverter {
	protected Map<String, String> mapType;
	protected Map<String, String> mapTypeHolder;
	protected Map<String, String> mapParamHolder;

	private final String dirIn = "in";
//	private final String dirOut = "out";
//	private final String dirInOut = "inout";

	private final String idlLongLong = "longlong";
	private final String idlLong = "long";
	private final String idlUnsignedLong = "unsignedlong";
	private final String idlUnsignedLongLong = "unsignedlonglong";
	private final String idlShort = "short";
	private final String idlUnsignedShort = "unsignedshort";
	private final String idlFloat = "float";
	private final String idlDouble = "double";
	private final String idlLongDouble = "longdouble";
	private final String idlChar = "char";
	private final String idlWchar = "wchar";
	private final String idlOctet = "octet";
	private final String idlBoolean = "boolean";
	private final String idlString = "string";
	private final String idlWstring = "wstring";
	private final String idlAny = "any";
	private final String idlVoid= "void";
	//
	private final String javaLongLong = "long";
	private final String javaLong = "int";
	private final String javaUnsignedLong = "int";
	private final String javaUnsignedLongLong = "long";
	private final String javaShort = "short";
	private final String javaUnsignedShort = "short";
	private final String javaFloat = "float";
	private final String javaDouble = "double";
	private final String javaLongDouble = "double";
	private final String javaChar = "char";
	private final String javaWchar = "char";
	private final String javaOctet = "byte";
	private final String javaBoolean = "boolean";
	private final String javaString = "String";
	private final String javaWstring = "String";
	private final String javaAny = "org.omg.CORBA.Any";
	private final String javaVoid= "void";
	//
	private final String javaInt = "int";
	private final String javaByte = "byte";
	//
	private final String javaShortHolder = "org.omg.CORBA.ShortHolder";
	private final String javaLongHolder = "org.omg.CORBA.IntHolder";
	private final String javaLongLongHolder = "org.omg.CORBA.LongHolder";
	private final String javaUnsignedLongHolder = "org.omg.CORBA.IntHolder";
	private final String javaUnsignedLongLongHolder = "org.omg.CORBA.LongHolder";
	private final String javaUnsignedShortHolder = "org.omg.CORBA.ShortHolder";
	private final String javaFloatHolder = "org.omg.CORBA.FloatHolder";
	private final String javaDoubleHolder = "org.omg.CORBA.DoubleHolder";
	private final String javaCharHolder = "org.omg.CORBA.CharHolder";
	private final String javaWcharHolder = "org.omg.CORBA.CharHolder";
	private final String javaOctetHolder = "org.omg.CORBA.ByteHolder";
	private final String javaBooleanHolder = "org.omg.CORBA.BooleanHolder";
	private final String javaStringHolder = "org.omg.CORBA.StringHolder";
	private final String javaWstringHolder = "org.omg.CORBA.StringHolder";
	private final String javaAnyHolder = "org.omg.CORBA.AnyHolder";
	private final String javaLongDoubleHolder = "org.omg.CORBA.DoubleHolder";
	//
	private final String javaShortParam = "ShortHolder";
	private final String javaIntParam = "IntegerHolder";
	private final String javaLongParam = "LongHolder";
	private final String javaFloatParam = "FloatHolder";
	private final String javaDoubleParam = "DoubleHolder";
	private final String javaByteParam = "ByteHolder";
	private final String javaStringParam = "StringHolder";
//	private final String javaLongDouble = "double";

	public JavaConverter() {
		mapType = new HashMap<String, String>();
		mapType.put(idlLongLong, javaLongLong);
		mapType.put(idlLong, javaLong);
		mapType.put(idlUnsignedLong, javaUnsignedLong);
		mapType.put(idlUnsignedLongLong, javaUnsignedLongLong);
		mapType.put(idlShort, javaShort);
		mapType.put(idlUnsignedShort, javaUnsignedShort);
		mapType.put(idlFloat, javaFloat);
		mapType.put(idlDouble, javaDouble);
		mapType.put(idlLongDouble, javaLongDouble);
		mapType.put(idlChar, javaChar);
		mapType.put(idlWchar, javaWchar);
		mapType.put(idlOctet, javaOctet);
		mapType.put(idlBoolean, javaBoolean);
		mapType.put(idlString, javaString);
		mapType.put(idlWstring, javaWstring);
		mapType.put(idlAny, javaAny);
		mapType.put(idlVoid, javaVoid);
		//
		mapTypeHolder = new HashMap<String, String>();
		mapTypeHolder.put(idlLongLong, javaLongLongHolder);
		mapTypeHolder.put(idlLong, javaLongHolder);
		mapTypeHolder.put(idlUnsignedLong, javaUnsignedLongHolder);
		mapTypeHolder.put(idlUnsignedLongLong, javaUnsignedLongLongHolder);
		mapTypeHolder.put(idlShort, javaShortHolder);
		mapTypeHolder.put(idlUnsignedShort, javaUnsignedShortHolder);
		mapTypeHolder.put(idlFloat, javaFloatHolder);
		mapTypeHolder.put(idlDouble, javaDoubleHolder);
		mapTypeHolder.put(idlChar, javaCharHolder);
		mapTypeHolder.put(idlWchar, javaWcharHolder);
		mapTypeHolder.put(idlOctet, javaOctetHolder);
		mapTypeHolder.put(idlBoolean, javaBooleanHolder);
		mapTypeHolder.put(idlString, javaStringHolder);
		mapTypeHolder.put(idlWstring, javaWstringHolder);
		mapTypeHolder.put(idlAny, javaAnyHolder);
		mapTypeHolder.put(idlLongDouble, javaLongDoubleHolder);
		//
		mapParamHolder = new HashMap<String, String>();
		mapParamHolder.put(javaShort, javaShortParam);
		mapParamHolder.put(javaInt, javaIntParam);
		mapParamHolder.put(javaLongLong, javaLongParam);
		mapParamHolder.put(javaFloat, javaFloatParam);
		mapParamHolder.put(javaDouble, javaDoubleParam);
		mapParamHolder.put(javaByte, javaByteParam);
		mapParamHolder.put(javaString, javaStringParam);
	}

	/**
	 * Portに設定された型の一覧を取得する
	 * 
	 * @param param  RtcParam
	 * @return 型一覧リスト
	 */
	public List<String> getPortTypes(RtcParam param) {
		List<String> portTypes = new ArrayList<String>();
		for (DataPortParam dataPort : param.getInports()) {
			if (!portTypes.contains(dataPort.getType())) {
				portTypes.add(dataPort.getType());
			}
		}
		for (DataPortParam dataPort : param.getOutports()) {
			if (!portTypes.contains(dataPort.getType())) {
				portTypes.add(dataPort.getType());
			}
		}
		return portTypes;
	}

	/**
	 * パラメータの型一覧を取得する
	 * 
	 * @param param  RtcParam
	 * @return パラメータ型一覧リスト
	 */
	public List<String> getParamTypes(RtcParam param) {
		List<String> paramTypes = new ArrayList<String>();
		for( ConfigSetParam config : param.getConfigParams() ) {
			String paramType = convJava2ParamHolder(config.getType(),false);
			if( paramType!=null && !paramTypes.contains(paramType) ) {
				paramTypes.add(paramType);
			}
		}
		return paramTypes;
	}
	/**
	 * CORBA型からJava型へ型を変換する(TypeDef考慮)
	 * 
	 * @param strCorba CORBA型
	 * @return Java型
	 */
	public String convCORBA2Java(String strCorba, ServiceClassParam scp) {
		boolean blnSequence = false;
		String strType = scp.getTypeDef().get(strCorba);
		if( strType==null ) strType = strCorba;
		else {
			strType.replaceAll("::", ".");
			if(strType.endsWith("[]")) {
				blnSequence = true;
				strType = strType.substring(0, strType.length()-2);
			}
		}
		String result = mapType.get(strType);
		if( result == null ) result = strCorba;
		if( blnSequence ) result += "[]";
		return result;
	}
	/**
	 * CORBA型からJava型へ型を変換する
	 * 
	 * @param strCorba CORBA型
	 * @return Java型
	 */
	public String convCORBA2JavaNoDef(String strCorba) {
		String result = mapType.get(strCorba);
		if( result == null ) result = strCorba;
		return result;
	}
	/**
	 * CORBA型からJava型へ型を変換する(引数用,TypeDef考慮)
	 * 
	 * @param strCorba CORBA型
	 * @param strDirection 入出力方向
	 * @return Java型
	 */
	public String convCORBA2JavaforArg(String strCorba, String strDirection, ServiceClassParam scp) {
		String result = "";
		String strType = scp.getTypeDef().get(strCorba);
		if( strType==null ) {
			if( strDirection.equals(dirIn) ) {
				result = mapType.get(strCorba);
				if( result == null ) result = strCorba;
			} else {
				result = mapTypeHolder.get(strCorba);
				if( result == null ) result = strCorba + "Holder";
			}
		} else {
			strType.replaceAll("::", ".");
			boolean blnSequence = false;
			if(strType.endsWith("[]")) {
				blnSequence = true;
				strType = strType.substring(0, strType.length()-2);
			}
			result = mapType.get(strType);
			if( result == null ) {
				result = strType;
				if( !strDirection.equals(dirIn) ) result = result + "Holder";
			}
			if( blnSequence ) {
				if( !strDirection.equals(dirIn) ) {
					result = strCorba + "Holder";
				} else {
					result = result + "[]";
				}
			}
		}
		return result;
	}
	/**
	 * CORBA型からJava型へ型を変換する(引数用)
	 * 
	 * @param strCorba CORBA型
	 * @param strDirection 入出力方向
	 * @return Java型
	 */
	public String convCORBA2JavaforArg(String strCorba, String strDirection) {
		String result = "";
		if( strDirection.equals(dirIn) ) {
			result = mapType.get(strCorba);
			
		} else {
			result = mapTypeHolder.get(strCorba);
		}
		if( result == null ) result = strCorba;
		return result;
	}
	/**
	 * Java型からパラメータ用ホルダ型へ型を変換する
	 * 
	 * @param strJava Java型
	 * @return パラメータ用ホルダ型
	 */
	public String convJava2ParamHolder(String strJava, boolean isNullAdd) {
		String result = mapParamHolder.get(strJava);
		if( isNullAdd && result == null ) result = strJava + "Holder";
		return result;
	}
	/**
	 * String型か判断する
	 * 
	 * @param srvMethod 検証対象メソッド
	 * @return 検証結果
	 */
	public boolean isString(ServiceMethodParam srvMethod, ServiceClassParam scp) {
		String conv = this.convCORBA2Java(srvMethod.getType(), scp);
		if(conv.equals(javaString) || conv.equals(javaWstring) || conv.equals(javaAny) || conv.endsWith("[]") )
			return true;
		return false;
	}
	
	/**
	 * データポート用のデータ型import文を返す
	 * 
	 * @param rtcType ポートの型
	 * @return import文字列
	 */
	public String getDataportPackageName(String rtcType) {
		//module名が付いていないデータ型（::が付いていない）はimport文を生成しない
		if(!rtcType.matches(".*::.*")) return "";
		
		//module名=パッケージ名
		//struct名=クラス名
		String importDef = "import " + rtcType.replace("::", ".") + ";";
		return importDef;
	}
	
	/**
	 * データポート初期化用にmodule名をカットしたデータ型クラス名を返す
	 * 
	 * @param rtcType ポートの型
	 * @return クラス名
	 */
	public String getDataTypeName(String rtcType) {
		
		//module名が付いていないデータ型（::が付いていない）はそのまま返す
		if(!rtcType.matches(".*::.*")) return rtcType;

		String dataTypeNames[] = rtcType.split("::", 0);
		return dataTypeNames[1];
	}

	/**
	 * RTC.ReturnCode_tのインポートが必要な場合を判定します。
	 * 
	 * @param rtcParam
	 *            RtcParam
	 * @return RTC.ReturnCode_tのインポートが必要な場合は true
	 */
	public boolean useReturnCode(RtcParam rtcParam) {
		if( rtcParam.getInports().size()>0 || rtcParam.getOutports().size()>0 ||
				rtcParam.getServicePorts().size()>0 ) {
			return true;
		}
		if (!rtcParam.getConfigParams().isEmpty()) {
			return true;
		}
		for (int i = IRtcBuilderConstants.ACTIVITY_INITIALIZE; i < IRtcBuilderConstants.ACTIVITY_DUMMY; i++) {
			if (!rtcParam.IsNotImplemented(i)) {
				return true;
			}
		}
		return false;
	}

}
