package jp.go.aist.rtm.rtcbuilder.vbdotnet.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;

public class VbDotNetConverter {
	protected Map<String, String> mapType;

	private final String dirIn = "in";
//	private final String dirOut = "out";
//	private final String dirInOut = "inout";

	private final String idlShort = "short";
	private final String idlLong = "long";
	private final String idlLongLong = "longlong";
	private final String idlUnsignedLong = "unsignedlong";
	private final String idlUnsignedLongLong = "unsignedlonglong";
	private final String idlUnsignedShort = "unsignedshort";
	private final String idlFloat = "float";
	private final String idlDouble = "double";

	private final String idlChar = "char";
	private final String idlWChar = "wchar";
	private final String idlString = "string";
	private final String idlWstring = "wstring";

	private final String idlOctet = "octet";
	private final String idlBoolean = "boolean";
	private final String idlAny = "any";

//	private final String idlVoid= "void";
	//
	private final String typeShort = "System.Int16";
	private final String typeLong = "System.Int32";
	private final String typeLongLong = "System.Int64";
	private final String typeString = "System.String";
	private final String typeFloat = "System.Single";
	private final String typeDouble = "System.Double";
	private final String typeChar = "System.Char";
	private final String typeOctet = "System.Byte";
	private final String typeBoolean = "System.Boolean";
	private final String typeAny = "System.Object";
	
	private final String[] escapeName = {"set"};

	//
	private static String projectGUID = null;
	private static String assemblyGUID = null;
	

	public VbDotNetConverter() {
		mapType = new HashMap<String, String>();
		mapType.put(idlShort, typeShort);
		mapType.put(idlLong, typeLong);
		mapType.put(idlUnsignedShort, typeShort);
		mapType.put(idlFloat, typeFloat);
		mapType.put(idlDouble, typeDouble);
		mapType.put(idlLongLong, typeLongLong);
		mapType.put(idlUnsignedLong, typeLong);
		mapType.put(idlUnsignedLongLong, typeLongLong);
		//
		mapType.put(idlString, typeString);
		mapType.put(idlWstring, typeString);
		mapType.put(idlChar, typeChar);
		mapType.put(idlWChar, typeChar);
		mapType.put(idlBoolean, typeBoolean);
		mapType.put(idlOctet, typeOctet);
		mapType.put(idlAny, typeAny);
		//
	}

	public static String getProjectGUID(boolean isTest) {
		if( isTest ) return "e68a065c-3654-4319-9973-1df7c31e45e0";
		if( assemblyGUID==null ) assemblyGUID = UUID.randomUUID().toString();
		return assemblyGUID;
	}

	public static String getAssemblyGUID(boolean isTest) {
		if( isTest ) return "42415f99-e546-4ad7-b017-3c2e6260e83d";
		if( projectGUID==null ) projectGUID = UUID.randomUUID().toString();
		return projectGUID;
	}

	/**
	 * C#型で使用できない関数名をescape
	 * 
	 * @param source 対象関数名
	 * @return escape結果
	 */
	public String escapeName(String source) {
		for(int index=0;index<escapeName.length;index++) {
			if(escapeName[index].toLowerCase().equals(source.toLowerCase())) {
				return "_" + source;
			}
		}
		return source;
	}
	
	/**
	 * CORBA型からC#型へ型を変換する(TypeDef考慮)
	 * 
	 * @param strCorba CORBA型
	 * @return C#型
	 */
	public String convCORBA2VBdotnetTypeDef(String strCorba, ServiceClassParam scp) {
		String target = strCorba;
		if( target.contains("::")) {
			String[] type = target.split("::");
			target = type[type.length-1];
		}
		String strType = scp.getTypeDef().get(target).getOriginalDef();
		if(strType == null) strType = strCorba;
		strType = convCORBA2VBdotnet(strType);
			
		if(strType.endsWith("[]")) {
			strType = strType.substring(0, strType.length()-2);
			strType = convCORBA2VBdotnet(strType) + "()";
		}
		return strType;
	}
	/**
	 * CORBA型からC#型へ型を変換する
	 * 
	 * @param strCorba CORBA型
	 * @param scp サービスクラス
	 * @return C#型
	 */
	public String convCORBA2VBdotnet(String strCorba) {
		String result = mapType.get(strCorba);
		if( result == null ) result = strCorba;

		return result;
	}
	/**
	 * CORBA型からC#型へ型を変換する(引数用,TypeDef考慮)
	 * 
	 * @param strCorba CORBA型
	 * @param strDirection 入出力方向
	 * @param scp サービスクラス
	 * @return C#型
	 */
	public String convCORBA2VBdotnetArg(String strCorba, String strDirection, ServiceClassParam scp) {
		String result = "";
		String target = strCorba;
		if( target.contains("::")) {
			String[] type = target.split("::");
			target = type[type.length-1];
		}
		String strType = scp.getTypeDef().get(target).getOriginalDef();
		if( strType==null ) {
			if( strDirection.equals(dirIn) ) {
				result = mapType.get(strCorba);
				if( result == null ) result = strCorba;
			} else {
				result = mapType.get(strCorba);
				if( result == null ) result = strCorba;
				result += "&";
			}
		} else {
			boolean blnSequence = false;
			if(strType.endsWith("[]")) {
				blnSequence = true;
				strType = strType.substring(0, strType.length()-2);
			}
			result = mapType.get(strType);
			if( result == null ) {
				result = strType;
			}
			if( blnSequence ) result = result + "()";
		}
		return result;
	}
	/**
	 * XML禁則文字をエスケープする
	 * 
	 * @param type 対象文字列
	 * @return 取得結果
	 */
	public String escapeString(String type) {
		String result = type;
		result = result.replace("<", "&lt;");
		result = result.replace(">", "&gt;");
		return result;
	}
	/**
	 * List型の中身を取得する
	 * 
	 * @param type 検証対象型
	 * @return 取得結果
	 */
	public String getListType(String type) {
		int start = type.indexOf('<');
		int end = type.indexOf('>');
		return type.substring(start+1, end);
	}
	/**
	 * List型か判断する
	 * 
	 * @param type 検証対象型
	 * @return 検証結果
	 */
	public boolean isList(String type) {
		if( type.toLowerCase().startsWith("list") )
			return true;
		return false;
	}
	/**
	 * String型か判断する
	 * 
	 * @param type 検証対象型
	 * @return 検証結果
	 */
	public boolean isString(String type) {
		if( type.toLowerCase().equals(idlString) )
			return true;
		return false;
	}
	
	/**
	 * Portに設定された型の一覧を取得する
	 * 
	 * @param param  RtcParam
	 * @return 型一覧リスト
	 */
	public static List<String> getPortTypes(RtcParam param) {
		List<String> portTypes = new ArrayList<String>();
		for( DataPortParam dataPort : param.getInports() ) {
			if( !portTypes.contains(dataPort.getType()) ) {
				portTypes.add(dataPort.getType());
			}
		}
		for( DataPortParam dataPort : param.getOutports() ) {
			if( !portTypes.contains(dataPort.getType()) ) {
				portTypes.add(dataPort.getType());
			}
		}
		return portTypes;
	}
	
	/**
	 * データポート用のデータ型imports文を返す
	 * 
	 * @param rtcType ポートの型
	 * @return import文字列
	 */
	public String getDataportPackageName(String rtcType) {
		//module名が付いていないデータ型（::が付いていない）はimports文を生成しない
		if(!rtcType.matches(".*::.*")) return "";
		
		//module名=パッケージ名
		//struct名=クラス名
		String importDef = "Imports " + rtcType.replace("::", ".") + ";";
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

}
