package RTC;

/**
* RTC/DataFlowComponentHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分44秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief dataFlowComponent
   *
   * @section Description
   *
   * The dataFlowComponent stereotype may be applied to a component
   * type to indicate that its instances should be executed in sorted
   * order by a periodic execution context.
   *
   * @section Constraints
   *
   * - An instance of a component extended by the dataFlowComponent
   *   stereotype must participate in at least one * execution context
   *   of kind PERIODIC, which shall also be used for the execution of
   *   any contained data flow components.
   *
   * - A component extended by dataFlowComponent must realize the
   *   interface DataFlowComponentAction.
   *
   *
   * @endif
   */
public final class DataFlowComponentHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.DataFlowComponent value = null;

  public DataFlowComponentHolder ()
  {
  }

  public DataFlowComponentHolder (RTC.DataFlowComponent initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.DataFlowComponentHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.DataFlowComponentHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.DataFlowComponentHelper.type ();
  }

}
