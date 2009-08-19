package RTC;


/**
* RTC/PortInterfaceProfileListHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/

public final class PortInterfaceProfileListHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.PortInterfaceProfile value[] = null;

  public PortInterfaceProfileListHolder ()
  {
  }

  public PortInterfaceProfileListHolder (RTC.PortInterfaceProfile[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.PortInterfaceProfileListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.PortInterfaceProfileListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.PortInterfaceProfileListHelper.type ();
  }

}
