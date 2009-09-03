package RTC;

/**
* RTC/ComponentActionHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分44秒 JST
*/


/*!
   * @if jp
   * @brief
   * @else
   * @brief ComponentAction
   *
   * @section Description
   *
   * The ComponentAction interface provides callbacks corresponding to
   * the execution of the lifecycle operations of LightweightRTObject
   * (see Section 5.2.2.2) and ExecutionContext (see Section
   * 5.2.2.5). An RTC developer may implement these callback
   * operations in order to execute application-specific logic
   * pointing response to those transitions.
   *
   * @section Semantics
   *
   * Clients of an RTC are not expected to invoke these operations
   * directly; they are provided for the benefit of the RTC middleware
   * implementation.
   *
   * @endif
   */
public final class ComponentActionHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.ComponentAction value = null;

  public ComponentActionHolder ()
  {
  }

  public ComponentActionHolder (RTC.ComponentAction initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.ComponentActionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.ComponentActionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.ComponentActionHelper.type ();
  }

}
