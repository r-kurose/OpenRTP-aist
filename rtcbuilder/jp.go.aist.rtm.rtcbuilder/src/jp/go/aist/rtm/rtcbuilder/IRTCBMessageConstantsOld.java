package jp.go.aist.rtm.rtcbuilder;

public interface IRTCBMessageConstantsOld {
	
	public static final String VALIDATE_ERROR_OUTPUTPROJECT = "OutputProjectが指定されていません。";
	public static final String VALIDATE_ERROR_COMPONENTNAME = "Component Nameが指定されていません。";
	public static final String VALIDATE_ERROR_PORTSAMENAME = "Portに同じ名前が存在します。 :";
	public static final String VALIDATE_ERROR_INTERFACESAMENAME = "ProviderもしくはConsumerに同じ名前が存在します。 :";
	//
	public static final String ERROR_IDL_DIRECTORY_NOT_FOUND = "Include IDL のディレクトリが見つかりません。";
	public static final String ERROR_GENERATE_FAILED = "プロジェクトの生成に失敗しました";
	//
	public static final String ERROR_PREPROCESSOR = "#includeするIDLのディレクトリを指定してください。" + System.getProperty("line.separator") + "pathを解決できません　:";
	public static final String ERROR_IDLPARSE = " : is undefined in idl";
	public static final String ERROR_IDLTYPEDUPLICAT = "Type definition is duplicated.";
	//
	public static final String ERROR_PROFILE_RESTORE = "ファイルの読み込みに失敗しました。" + System.getProperty("line.separator") + "RtcBuilder以外のファイルが読み込まれていないか確認してください";
	//
	//
	public static final String CONFIRM_PROJECT_GENERATE_TITLE = "プロジェクト生成";
	public static final String CONFIRM_PROJECT_GENERATE = "指定されたプロジェクトがWorkspace内に存在しません。" + System.getProperty("line.separator") + 
															"新規に生成してもよろしいですか？";

}
