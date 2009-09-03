package RTC;

/**
* RTC/FsmProfileHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief FsmProfile
   *
   * @section Description
   *
   * The FsmProfile describes the correspondence between an FSM and
   * its contained FSM participants. This Profile is necessary for
   * Stimulus Response Processing.
   *
   * @endif
   */
public final class FsmProfileHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.FsmProfile value = null;

  public FsmProfileHolder ()
  {
  }

  public FsmProfileHolder (RTC.FsmProfile initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.FsmProfileHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.FsmProfileHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.FsmProfileHelper.type ();
  }

}
