package RTC;

/**
* RTC/FsmServiceHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分45秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief FsmService
   *
   * @section Description
   *
   * The FsmService interface defines operations necessary for
   * Stimulus Response Processing as an SDO service.
   *
   * @endif
   */
public final class FsmServiceHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.FsmService value = null;

  public FsmServiceHolder ()
  {
  }

  public FsmServiceHolder (RTC.FsmService initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.FsmServiceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.FsmServiceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.FsmServiceHelper.type ();
  }

}
