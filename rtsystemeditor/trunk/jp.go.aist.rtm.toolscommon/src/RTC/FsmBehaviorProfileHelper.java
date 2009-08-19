package RTC;


/**
* RTC/FsmBehaviorProfileHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief FsmBehaviorProfile
   *
   * @section Description
   *
   * FsmBehaviorProfile represents the association of an FSM
   * participant with a transition, state entry, or state exit in an
   * FSM.
   *
   * @section Semantics
   *
   * The assignment of identifiers to particular transitions, state
   * entries, or state exits is implementation-dependent.
   *
   * @endif
   */
abstract public class FsmBehaviorProfileHelper
{
  private static String  _id = "IDL:omg.org/RTC/FsmBehaviorProfile:1.0";

  public static void insert (org.omg.CORBA.Any a, RTC.FsmBehaviorProfile that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTC.FsmBehaviorProfile extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [2];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = RTC.FsmParticipantActionHelper.type ();
          _members0[0] = new org.omg.CORBA.StructMember (
            "action_component",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (_SDOPackage.UniqueIdentifierHelper.id (), "UniqueIdentifier", _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (RTC.UniqueIdentifierHelper.id (), "UniqueIdentifier", _tcOf_members0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "id",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (RTC.FsmBehaviorProfileHelper.id (), "FsmBehaviorProfile", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTC.FsmBehaviorProfile read (org.omg.CORBA.portable.InputStream istream)
  {
    RTC.FsmBehaviorProfile value = new RTC.FsmBehaviorProfile ();
    value.action_component = RTC.FsmParticipantActionHelper.read (istream);
    value.id = istream.read_string ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTC.FsmBehaviorProfile value)
  {
    RTC.FsmParticipantActionHelper.write (ostream, value.action_component);
    ostream.write_string (value.id);
  }

}
