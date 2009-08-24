package RTC;

/**
* RTC/ExecutionContextProfileHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief 
   * @endif
   */
public final class ExecutionContextProfileHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.ExecutionContextProfile value = null;

  public ExecutionContextProfileHolder ()
  {
  }

  public ExecutionContextProfileHolder (RTC.ExecutionContextProfile initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.ExecutionContextProfileHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.ExecutionContextProfileHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.ExecutionContextProfileHelper.type ();
  }

}
