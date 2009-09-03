package RTC;


/**
* RTC/PortInterfaceProfileHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief PortInterfaceProfile
   *
   * @section Description
   *
   * PortInterfaceProfile describes an instance of a particular
   * interface as it is exposed by a particular port. These objects
   * are referred to below as the "target interface" and "target
   * port" respectively.
   *
   * @endif
   */
abstract public class PortInterfaceProfileHelper
{
  private static String  _id = "IDL:omg.org/RTC/PortInterfaceProfile:1.0";

  public static void insert (org.omg.CORBA.Any a, RTC.PortInterfaceProfile that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTC.PortInterfaceProfile extract (org.omg.CORBA.Any a)
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
            "instance_name",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "type_name",
            _tcOf_members0,
            null);
          _tcOf_members0 = RTC.PortInterfacePolarityHelper.type ();
          _members0[2] = new org.omg.CORBA.StructMember (
            "polarity",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (RTC.PortInterfaceProfileHelper.id (), "PortInterfaceProfile", _members0);
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

  public static RTC.PortInterfaceProfile read (org.omg.CORBA.portable.InputStream istream)
  {
    RTC.PortInterfaceProfile value = new RTC.PortInterfaceProfile ();
    value.instance_name = istream.read_string ();
    value.type_name = istream.read_string ();
    value.polarity = RTC.PortInterfacePolarityHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTC.PortInterfaceProfile value)
  {
    ostream.write_string (value.instance_name);
    ostream.write_string (value.type_name);
    RTC.PortInterfacePolarityHelper.write (ostream, value.polarity);
  }

}
