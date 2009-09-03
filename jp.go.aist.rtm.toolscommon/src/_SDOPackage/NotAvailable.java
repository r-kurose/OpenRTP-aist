package _SDOPackage;


/**
* _SDOPackage/NotAvailable.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public final class NotAvailable extends org.omg.CORBA.UserException
{
  public String description = null;

  public NotAvailable ()
  {
    super(NotAvailableHelper.id());
  } // ctor

  public NotAvailable (String _description)
  {
    super(NotAvailableHelper.id());
    description = _description;
  } // ctor


  public NotAvailable (String $reason, String _description)
  {
    super(NotAvailableHelper.id() + "  " + $reason);
    description = _description;
  } // ctor

} // class NotAvailable
