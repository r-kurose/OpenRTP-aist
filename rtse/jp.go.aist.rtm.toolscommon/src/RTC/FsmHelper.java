package RTC;


/**
* RTC/FsmHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分44秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief fsm
   *
   * @section Description
   *
   * Applying the fsm stereotype to a component implies the ability to
   * define component-specific states and transitions.
   *
   * @section Semantics
   *
   * In creating a state machine such as is depicted in Figure 5.22,
   * the RTC developer is implicitly defining the Active state to be a
   * submachine state.  * The BehaviorStateMachines package described
   * in [UML] is considered the normative definition of a state
   * machine.
   *
   * @endif
   */
abstract public class FsmHelper
{
  private static String  _id = "IDL:omg.org/RTC/Fsm:1.0";

  public static void insert (org.omg.CORBA.Any a, RTC.Fsm that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTC.Fsm extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (RTC.FsmHelper.id (), "Fsm");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTC.Fsm read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_FsmStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTC.Fsm value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static RTC.Fsm narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof RTC.Fsm)
      return (RTC.Fsm)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      RTC._FsmStub stub = new RTC._FsmStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
