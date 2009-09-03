package RTC;

/**
* RTC/PortProfileHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief PortProfile
   *
   * @section Description
   *
   * A PortProfile describes a port of an RTC (referred to as the
   * "target" RTC). This port is referred to as the "target" port.
   * From this profile, other components and tools can obtain Porta?s
   * name, type, object reference, and so on.
   *
   * @endif
   */
public final class PortProfileHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.PortProfile value = null;

  public PortProfileHolder ()
  {
  }

  public PortProfileHolder (RTC.PortProfile initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.PortProfileHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.PortProfileHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.PortProfileHelper.type ();
  }

}
