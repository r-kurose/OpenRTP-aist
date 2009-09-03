package RTC;

/**
* RTC/ModeHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分44秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief Mode
   *
   * @section Description
   *
   * Each mode defined by a given RTC shall be represented by an
   * instance of Mode.
   *
   * @endif
   */
public final class ModeHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.Mode value = null;

  public ModeHolder ()
  {
  }

  public ModeHolder (RTC.Mode initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.ModeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.ModeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.ModeHelper.type ();
  }

}
