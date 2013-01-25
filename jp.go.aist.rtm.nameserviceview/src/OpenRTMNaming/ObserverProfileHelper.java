package OpenRTMNaming;


/**
* OpenRTMNaming/ObserverProfileHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: ./OpenRTMNaming.idl
* 2011年2月10日 18時15分14秒 JST
*/

abstract public class ObserverProfileHelper
{
  private static String  _id = "IDL:OpenRTMNaming/ObserverProfile:1.0";

  public static void insert (org.omg.CORBA.Any a, OpenRTMNaming.ObserverProfile that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static OpenRTMNaming.ObserverProfile extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [3];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "id",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "interface_type",
            _tcOf_members0,
            null);
          _tcOf_members0 = OpenRTMNaming.NamingObserverHelper.type ();
          _members0[2] = new org.omg.CORBA.StructMember (
            "observer",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (OpenRTMNaming.ObserverProfileHelper.id (), "ObserverProfile", _members0);
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

  public static OpenRTMNaming.ObserverProfile read (org.omg.CORBA.portable.InputStream istream)
  {
    OpenRTMNaming.ObserverProfile value = new OpenRTMNaming.ObserverProfile ();
    value.id = istream.read_string ();
    value.interface_type = istream.read_string ();
    value.observer = OpenRTMNaming.NamingObserverHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, OpenRTMNaming.ObserverProfile value)
  {
    ostream.write_string (value.id);
    ostream.write_string (value.interface_type);
    OpenRTMNaming.NamingObserverHelper.write (ostream, value.observer);
  }

}
