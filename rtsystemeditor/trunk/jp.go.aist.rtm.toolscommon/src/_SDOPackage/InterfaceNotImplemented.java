package _SDOPackage;


/**
* _SDOPackage/InterfaceNotImplemented.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public final class InterfaceNotImplemented extends org.omg.CORBA.UserException
{
  public String description = null;

  public InterfaceNotImplemented ()
  {
    super(InterfaceNotImplementedHelper.id());
  } // ctor

  public InterfaceNotImplemented (String _description)
  {
    super(InterfaceNotImplementedHelper.id());
    description = _description;
  } // ctor


  public InterfaceNotImplemented (String $reason, String _description)
  {
    super(InterfaceNotImplementedHelper.id() + "  " + $reason);
    description = _description;
  } // ctor

} // class InterfaceNotImplemented
