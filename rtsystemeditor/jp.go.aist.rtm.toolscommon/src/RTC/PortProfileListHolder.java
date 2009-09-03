package RTC;


/**
* RTC/PortProfileListHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/

public final class PortProfileListHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.PortProfile value[] = null;

  public PortProfileListHolder ()
  {
  }

  public PortProfileListHolder (RTC.PortProfile[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.PortProfileListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.PortProfileListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.PortProfileListHelper.type ();
  }

}
