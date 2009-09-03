package RTC;

/**
* RTC/DataFlowComponentActionHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分44秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief DataFlowComponentAction
   *
   * @section Description
   *
   * DataFlowComponentAction is a companion to ComponentAction (see
   * Section 5.2.2.4) that provides additional callbacks for
   * intercepting the two execution passes defined in Section
   * 5.3.1.1.2.
   *
   * @endif
   */
public final class DataFlowComponentActionHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.DataFlowComponentAction value = null;

  public DataFlowComponentActionHolder ()
  {
  }

  public DataFlowComponentActionHolder (RTC.DataFlowComponentAction initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.DataFlowComponentActionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.DataFlowComponentActionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.DataFlowComponentActionHelper.type ();
  }

}
