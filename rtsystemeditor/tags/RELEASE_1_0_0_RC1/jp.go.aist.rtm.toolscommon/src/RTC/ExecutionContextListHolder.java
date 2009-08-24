package RTC;


/**
* RTC/ExecutionContextListHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分46秒 JST
*/

public final class ExecutionContextListHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.ExecutionContext value[] = null;

  public ExecutionContextListHolder ()
  {
  }

  public ExecutionContextListHolder (RTC.ExecutionContext[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.ExecutionContextListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.ExecutionContextListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.ExecutionContextListHelper.type ();
  }

}
