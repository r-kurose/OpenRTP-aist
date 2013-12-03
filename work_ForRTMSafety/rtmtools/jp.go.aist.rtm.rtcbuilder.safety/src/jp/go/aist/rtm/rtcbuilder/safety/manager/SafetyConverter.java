package jp.go.aist.rtm.rtcbuilder.safety.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigSetParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceArgumentParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceMethodParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.TypeDefParam;

/**
 * Safetyソースを出力する際に使用されるユーティリティ
 */
public class SafetyConverter {
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
	private final String safetyLongLong = "long";
	private final String safetyLong = "int";
	private final String safetyUnsignedLong = "int";
	private final String safetyUnsignedLongLong = "long";
	private final String safetyShort = "short";
	private final String safetyUnsignedShort = "short";
	private final String safetyFloat = "float";
	private final String safetyDouble = "double";
	private final String safetyLongDouble = "double";
	private final String safetyChar = "char";
	private final String safetyWchar = "char";
	private final String safetyOctet = "byte";
	private final String safetyBoolean = "boolean";
	private final String safetyStringS = "string";
	private final String safetyString = "String";
	private final String safetyWstring = "String";
	private final String safetyAny = "org.omg.CORBA.Any";
	private final String safetyVoid= "void";
	//
	private final String safetyInt = "int";
	private final String safetyByte = "byte";
	//
	private final String safetyShortHolder = "org.omg.CORBA.ShortHolder";
	private final String safetyLongHolder = "org.omg.CORBA.IntHolder";
	private final String safetyLongLongHolder = "org.omg.CORBA.LongHolder";
	private final String safetyUnsignedLongHolder = "org.omg.CORBA.IntHolder";
	private final String safetyUnsignedLongLongHolder = "org.omg.CORBA.LongHolder";
	private final String safetyUnsignedShortHolder = "org.omg.CORBA.ShortHolder";
	private final String safetyFloatHolder = "org.omg.CORBA.FloatHolder";
	private final String safetyDoubleHolder = "org.omg.CORBA.DoubleHolder";
	private final String safetyCharHolder = "org.omg.CORBA.CharHolder";
	private final String safetyWcharHolder = "org.omg.CORBA.CharHolder";
	private final String safetyOctetHolder = "org.omg.CORBA.ByteHolder";
	private final String safetyBooleanHolder = "org.omg.CORBA.BooleanHolder";
	private final String safetyStringHolder = "org.omg.CORBA.StringHolder";
	private final String safetyWstringHolder = "org.omg.CORBA.StringHolder";
	private final String safetyAnyHolder = "org.omg.CORBA.AnyHolder";
	private final String safetyLongDoubleHolder = "org.omg.CORBA.DoubleHolder";
	//
	private final String safetyShortParam = "ShortHolder";
	private final String safetyIntParam = "IntegerHolder";
	private final String safetyLongParam = "LongHolder";
	private final String safetyFloatParam = "FloatHolder";
	private final String safetyDoubleParam = "DoubleHolder";
	private final String safetyByteParam = "ByteHolder";
	private final String safetyStringParam = "StringHolder";
//	private final String safetyLongDouble = "double";

	public SafetyConverter() {
		mapType = new HashMap<String, String>();
		mapType.put(idlLongLong, safetyLongLong);
		mapType.put(idlLong, safetyLong);
		mapType.put(idlUnsignedLong, safetyUnsignedLong);
		mapType.put(idlUnsignedLongLong, safetyUnsignedLongLong);
		mapType.put(idlShort, safetyShort);
		mapType.put(idlUnsignedShort, safetyUnsignedShort);
		mapType.put(idlFloat, safetyFloat);
		mapType.put(idlDouble, safetyDouble);
		mapType.put(idlLongDouble, safetyLongDouble);
		mapType.put(idlChar, safetyChar);
		mapType.put(idlWchar, safetyWchar);
		mapType.put(idlOctet, safetyOctet);
		mapType.put(idlBoolean, safetyBoolean);
		mapType.put(idlString, safetyString);
		mapType.put(idlWstring, safetyWstring);
		mapType.put(idlAny, safetyAny);
		mapType.put(idlVoid, safetyVoid);
		//
		mapTypeHolder = new HashMap<String, String>();
		mapTypeHolder.put(idlLongLong, safetyLongLongHolder);
		mapTypeHolder.put(idlLong, safetyLongHolder);
		mapTypeHolder.put(idlUnsignedLong, safetyUnsignedLongHolder);
		mapTypeHolder.put(idlUnsignedLongLong, safetyUnsignedLongLongHolder);
		mapTypeHolder.put(idlShort, safetyShortHolder);
		mapTypeHolder.put(idlUnsignedShort, safetyUnsignedShortHolder);
		mapTypeHolder.put(idlFloat, safetyFloatHolder);
		mapTypeHolder.put(idlDouble, safetyDoubleHolder);
		mapTypeHolder.put(idlChar, safetyCharHolder);
		mapTypeHolder.put(idlWchar, safetyWcharHolder);
		mapTypeHolder.put(idlOctet, safetyOctetHolder);
		mapTypeHolder.put(idlBoolean, safetyBooleanHolder);
		mapTypeHolder.put(idlString, safetyStringHolder);
		mapTypeHolder.put(idlWstring, safetyWstringHolder);
		mapTypeHolder.put(idlAny, safetyAnyHolder);
		mapTypeHolder.put(idlLongDouble, safetyLongDoubleHolder);
		//
		mapParamHolder = new HashMap<String, String>();
		mapParamHolder.put(safetyShort, safetyShortParam);
		mapParamHolder.put(safetyInt, safetyIntParam);
		mapParamHolder.put(safetyLongLong, safetyLongParam);
		mapParamHolder.put(safetyFloat, safetyFloatParam);
		mapParamHolder.put(safetyDouble, safetyDoubleParam);
		mapParamHolder.put(safetyByte, safetyByteParam);
		mapParamHolder.put(safetyString, safetyStringParam);
		mapParamHolder.put(safetyStringS, safetyStringParam);
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
			String paramType = convSafety2ParamHolder(config.getType(),false);
			if( paramType!=null && !paramTypes.contains(paramType) ) {
				paramTypes.add(paramType);
			}
		}
		return paramTypes;
	}
	/**
	 * CORBA型からC型へ型を変換する(TypeDef考慮)
	 * 
	 * @param strCorba CORBA型
	 * @return C型
	 */
	public String convCORBA2Safety(ServiceMethodParam typeDef, ServiceClassParam scp) {
		String strType = getTypeDefs(typeDef.getType(), scp);
		if( strType==null ) {
			strType = typeDef.getType();
		} else {
			strType.replaceAll("::", ".");
		}
		
		String rawType = strType.replaceAll("\\[\\]", "");
		String convType = mapType.get(rawType);
		
		String result;
		if( convType == null ) {
			if(typeDef.isSequence() && !typeDef.isStruct()) {
				result = strType;
				
			} else {
				result = typeDef.getType();
			}
		} else {
			result = strType.replaceAll(rawType, convType);
			
		}
		
		return result;
	}
	private String getTypeDefs(String target, ServiceClassParam scp) {
		String result = null;
		
		TypeDefParam source = scp.getTypeDef().get(target);
		if( source==null || source.getOriginalDef()==null || source.getOriginalDef().length()==0 ) {
			return target;
		} else {
			result = getTypeDefs(source.getOriginalDef(), scp);
			if( source!=null ) {
				if( source.isSequence() || source.isArray() ) result += "[]";
			}
		}
		return result;
	}
	
	/**
	 * CORBA型からC型へ型を変換する
	 * 
	 * @param strCorba CORBA型
	 * @return C型
	 */
	public String convCORBA2SafetyNoDef(String strCorba) {
		String result = mapType.get(strCorba);
		if( result == null ) result = strCorba;
		return result;
	}
	/**
	 * CORBA型からC型へ型を変換する(引数用,TypeDef考慮)
	 * 
	 * @param strCorba CORBA型
	 * @param strDirection 入出力方向
	 * @return C型
	 */
	public String convCORBA2SafetyforArg(ServiceArgumentParam typeDef, String strDirection, ServiceClassParam scp) {
		String result = "";
		String strType = getTypeDefs(typeDef.getType(), scp);
		if( typeDef.getType().equals(strType) ) {
			if( strDirection.equals(dirIn) ) {
				result = mapType.get(typeDef.getType());
				if( result == null ) result = typeDef.getType();
			} else {
				result = mapTypeHolder.get(typeDef.getType());
				if( result == null ) result = typeDef.getType() + "Holder";
			}
		} else {
			strType.replaceAll("::", ".");
			String rawType = strType.replaceAll("\\[\\]", "");
			String convType = mapType.get(rawType);
			if( convType == null ) {
				if(typeDef.isStruct() || typeDef.isEnum()) {
					result = typeDef.getType();
				} else {
					result = strType;
				}
				if( !strDirection.equals(dirIn) ) result = result + "Holder";
			} else {
				result = strType.replaceAll(rawType, convType);
				
			}
			if( typeDef.isUnbounded() || typeDef.isArray() ) {
				if( !strDirection.equals(dirIn) ) {
					result = typeDef.getType() + "Holder";
				}
			}
		}
		return result;
	}
	/**
	 * CORBA型からC型へ型を変換する(引数用)
	 * 
	 * @param strCorba CORBA型
	 * @param strDirection 入出力方向
	 * @return C型
	 */
	public String convCORBA2SafetyforArg(String strCorba, String strDirection) {
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
	 * C型からパラメータ用ホルダ型へ型を変換する
	 * 
	 * @param strSafety C型
	 * @return パラメータ用ホルダ型
	 */
	public String convSafety2ParamHolder(String strSafety, boolean isNullAdd) {
		String result = mapParamHolder.get(strSafety);
		if( isNullAdd && result == null ) result = strSafety + "Holder";
		return result;
	}
	/**
	 * String型か判断する
	 * 
	 * @param srvMethod 検証対象メソッド
	 * @return 検証結果
	 */
	public boolean isRetNull(ServiceMethodParam srvMethod, ServiceClassParam scp) {
		if(srvMethod.isStruct()) return true;
		String conv = this.convCORBA2Safety(srvMethod, scp);
		if(conv.equals(safetyString) || conv.equals(safetyWstring) || conv.equals(safetyAny) || conv.endsWith("[]") )
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
