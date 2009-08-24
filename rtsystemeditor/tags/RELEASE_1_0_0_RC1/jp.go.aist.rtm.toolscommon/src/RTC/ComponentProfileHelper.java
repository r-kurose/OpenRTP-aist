package RTC;


/**
* RTC/ComponentProfileHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief ComponentProfile
   *
   * @section Description
   *
   * ComponentProfile represents the static state of an RTC that is
   * referred to here as the "target" RTC.
   *
   * @endif
   */
abstract public class ComponentProfileHelper
{
  private static String  _id = "IDL:omg.org/RTC/ComponentProfile:1.0";

  public static void insert (org.omg.CORBA.Any a, RTC.ComponentProfile that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTC.ComponentProfile extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [9];
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
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[2] = new org.omg.CORBA.StructMember (
            "description",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[3] = new org.omg.CORBA.StructMember (
            "version",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[4] = new org.omg.CORBA.StructMember (
            "vendor",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[5] = new org.omg.CORBA.StructMember (
            "category",
            _tcOf_members0,
            null);
          _tcOf_members0 = RTC.PortProfileHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (RTC.PortProfileListHelper.id (), "PortProfileList", _tcOf_members0);
          _members0[6] = new org.omg.CORBA.StructMember (
            "port_profiles",
            _tcOf_members0,
            null);
          _tcOf_members0 = RTC.RTObjectHelper.type ();
          _members0[7] = new org.omg.CORBA.StructMember (
            "parent",
            _tcOf_members0,
            null);
          _tcOf_members0 = _SDOPackage.NameValueHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (_SDOPackage.NVListHelper.id (), "NVList", _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (RTC.NVListHelper.id (), "NVList", _tcOf_members0);
          _members0[8] = new org.omg.CORBA.StructMember (
            "properties",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (RTC.ComponentProfileHelper.id (), "ComponentProfile", _members0);
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

  public static RTC.ComponentProfile read (org.omg.CORBA.portable.InputStream istream)
  {
    RTC.ComponentProfile value = new RTC.ComponentProfile ();
    value.instance_name = istream.read_string ();
    value.type_name = istream.read_string ();
    value.description = istream.read_string ();
    value.version = istream.read_string ();
    value.vendor = istream.read_string ();
    value.category = istream.read_string ();
    value.port_profiles = RTC.PortProfileListHelper.read (istream);
    value.parent = RTC.RTObjectHelper.read (istream);
    value.properties = _SDOPackage.NVListHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTC.ComponentProfile value)
  {
    ostream.write_string (value.instance_name);
    ostream.write_string (value.type_name);
    ostream.write_string (value.description);
    ostream.write_string (value.version);
    ostream.write_string (value.vendor);
    ostream.write_string (value.category);
    RTC.PortProfileListHelper.write (ostream, value.port_profiles);
    RTC.RTObjectHelper.write (ostream, value.parent);
    _SDOPackage.NVListHelper.write (ostream, value.properties);
  }

}
