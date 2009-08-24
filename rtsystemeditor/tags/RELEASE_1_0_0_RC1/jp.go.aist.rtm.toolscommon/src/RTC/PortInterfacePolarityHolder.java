package RTC;

/**
* RTC/PortInterfacePolarityHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief PortInterfacePolarity
   *
   * @section Description
   *
   * The PortInterfacePolarity enumeration identifies exposed
   * interface instances as provided or required.  @endif
   */
public final class PortInterfacePolarityHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.PortInterfacePolarity value = null;

  public PortInterfacePolarityHolder ()
  {
  }

  public PortInterfacePolarityHolder (RTC.PortInterfacePolarity initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.PortInterfacePolarityHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.PortInterfacePolarityHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.PortInterfacePolarityHelper.type ();
  }

}
