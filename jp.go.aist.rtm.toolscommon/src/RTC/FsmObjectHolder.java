package RTC;

/**
* RTC/FsmObjectHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分45秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief FsmObject
   *
   * @section Description
   *
   * The FsmObject interface allows programs to send stimuli to a
   * finite state machine, possibly causing it to change states.
   *
   * @endif
   */
public final class FsmObjectHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.FsmObject value = null;

  public FsmObjectHolder ()
  {
  }

  public FsmObjectHolder (RTC.FsmObject initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.FsmObjectHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.FsmObjectHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.FsmObjectHelper.type ();
  }

}
