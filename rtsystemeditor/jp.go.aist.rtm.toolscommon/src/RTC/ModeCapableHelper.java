package RTC;


/**
* RTC/ModeCapableHelper.java .
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
abstract public class ModeCapableHelper
{
  private static String  _id = "IDL:omg.org/RTC/ModeCapable:1.0";

  public static void insert (org.omg.CORBA.Any a, RTC.ModeCapable that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTC.ModeCapable extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (RTC.ModeCapableHelper.id (), "ModeCapable");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTC.ModeCapable read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ModeCapableStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTC.ModeCapable value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static RTC.ModeCapable narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof RTC.ModeCapable)
      return (RTC.ModeCapable)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      RTC._ModeCapableStub stub = new RTC._ModeCapableStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
