package RTC;

/**
* RTC/PortInterfaceProfileHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief PortInterfaceProfile
   *
   * @section Description
   *
   * PortInterfaceProfile describes an instance of a particular
   * interface as it is exposed by a particular port. These objects
   * are referred to below as the "target interface" and "target
   * port" respectively.
   *
   * @endif
   */
public final class PortInterfaceProfileHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.PortInterfaceProfile value = null;

  public PortInterfaceProfileHolder ()
  {
  }

  public PortInterfaceProfileHolder (RTC.PortInterfaceProfile initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.PortInterfaceProfileHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.PortInterfaceProfileHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.PortInterfaceProfileHelper.type ();
  }

}
