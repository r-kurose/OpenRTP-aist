package RTC;

/**
* RTC/ExecutionKindHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分46秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief ExecutionKind
   * 
   * @sectioni Description
   *
   * The ExecutionKind enumeration defines the execution semantics
   * (see Section 5.3) of the RTCs that participate in an execution
   * context.
   *
   * @endif
   */
public final class ExecutionKindHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.ExecutionKind value = null;

  public ExecutionKindHolder ()
  {
  }

  public ExecutionKindHolder (RTC.ExecutionKind initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.ExecutionKindHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.ExecutionKindHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.ExecutionKindHelper.type ();
  }

}
