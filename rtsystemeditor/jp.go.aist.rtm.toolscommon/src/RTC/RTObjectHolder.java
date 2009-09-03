package RTC;

/**
* RTC/RTObjectHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分45秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief RTObject
   *
   * @section Description
   *
   * The RTObject interface defines the operations that all SDO-based
   * RTCs must provide. It is required by the rtComponent stereotype.
   *
   * @endif
   */
public final class RTObjectHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.RTObject value = null;

  public RTObjectHolder ()
  {
  }

  public RTObjectHolder (RTC.RTObject initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.RTObjectHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.RTObjectHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.RTObjectHelper.type ();
  }

}
