package RTC;


/**
* RTC/FsmParticipantActionHelper.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��44�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief FsmParticipantAction
   *
   * @section Description
   *
   * FsmParticipantAction is companion to ComponentAction (see Section
   * 5.2.2.4) that is intended for use with FSM participant RTCs. It
   * adds a callback for the interception of state transitions, state
   * entries, and state exits.
   *
   * @endif
   */
abstract public class FsmParticipantActionHelper
{
  private static String  _id = "IDL:omg.org/RTC/FsmParticipantAction:1.0";

  public static void insert (org.omg.CORBA.Any a, RTC.FsmParticipantAction that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTC.FsmParticipantAction extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (RTC.FsmParticipantActionHelper.id (), "FsmParticipantAction");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTC.FsmParticipantAction read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_FsmParticipantActionStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTC.FsmParticipantAction value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static RTC.FsmParticipantAction narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof RTC.FsmParticipantAction)
      return (RTC.FsmParticipantAction)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      RTC._FsmParticipantActionStub stub = new RTC._FsmParticipantActionStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}