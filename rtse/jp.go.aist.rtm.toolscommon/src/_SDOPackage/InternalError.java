package _SDOPackage;


/**
* _SDOPackage/InternalError.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public final class InternalError extends org.omg.CORBA.UserException
{
  public String description = null;

  public InternalError ()
  {
    super(InternalErrorHelper.id());
  } // ctor

  public InternalError (String _description)
  {
    super(InternalErrorHelper.id());
    description = _description;
  } // ctor


  public InternalError (String $reason, String _description)
  {
    super(InternalErrorHelper.id() + "  " + $reason);
    description = _description;
  } // ctor

} // class InternalError
