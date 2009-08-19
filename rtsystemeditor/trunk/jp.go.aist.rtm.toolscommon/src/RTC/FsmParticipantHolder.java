package RTC;

/**
* RTC/FsmParticipantHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分44秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief 
   * @endif
   */
public final class FsmParticipantHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.FsmParticipant value = null;

  public FsmParticipantHolder ()
  {
  }

  public FsmParticipantHolder (RTC.FsmParticipant initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.FsmParticipantHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.FsmParticipantHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.FsmParticipantHelper.type ();
  }

}
