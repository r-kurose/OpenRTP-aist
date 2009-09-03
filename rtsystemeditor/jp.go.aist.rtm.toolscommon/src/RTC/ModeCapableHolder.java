package RTC;

/**
* RTC/ModeCapableHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分45秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief ModeCapable
   *
   * @section Description
   *
   * The ModeCapable interface provides access to an objecta?s modes
   * and a means to set the current mode.
   *
   * @section Semantics
   *
   * A given RTC may support multiple modes as well as multiple
   * execution contexts. In such a case, a request for a mode change
   * (e.g., from "cruise control on" to "cruise control off") may
   * come asynchronously with respect to one or more of those
   * execution contexts. The mode of an RTC may therefore be observed
   * to be different from one execution context to another.  - A mode
   * is pending in a given execution context when a mode change has
   * been requested but the new mode has not yet been observed by that
   * context.
   *
   * - The new mode has been committed in a given execution context
   *   when the context finally observes the new mode.
   *
   * - The new mode has stabilized once it has been committed in all
   *   execution contexts in which the RTC participates.
   *
   * Figure 5.26 depicts a state machine that describes mode
   * changes. Each parallel region in the composite state Mode Pending
   * represents an execution context. The trigger "sample" within
   * that state is considered to have occurred: - a?just before the
   * next call to on_execute (see Section 5.3.1.2.1) in the case where
   * immediate is false and the execution kind is PERIODIC, a?
   *
   * - a?just before the processing of the next stimulus in the case
   *   where immediate is false and the execution kind is
   *   EVENT_DRIVEN, or a?- a?immediately in all other cases.
   *
   * @endif
   */
public final class ModeCapableHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.ModeCapable value = null;

  public ModeCapableHolder ()
  {
  }

  public ModeCapableHolder (RTC.ModeCapable initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.ModeCapableHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.ModeCapableHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.ModeCapableHelper.type ();
  }

}
