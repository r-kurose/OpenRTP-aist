package RTC;


/**
* RTC/PortProfileHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief PortProfile
   *
   * @section Description
   *
   * A PortProfile describes a port of an RTC (referred to as the
   * "target" RTC). This port is referred to as the "target" port.
   * From this profile, other components and tools can obtain Porta?s
   * name, type, object reference, and so on.
   *
   * @endif
   */
abstract public class PortProfileHelper
{
  private static String  _id = "IDL:omg.org/RTC/PortProfile:1.0";

  public static void insert (org.omg.CORBA.Any a, RTC.PortProfile that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTC.PortProfile extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [6];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "name",
            _tcOf_members0,
            null);
          _tcOf_members0 = RTC.PortInterfaceProfileHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (RTC.PortInterfaceProfileListHelper.id (), "PortInterfaceProfileList", _tcOf_members0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "interfaces",
            _tcOf_members0,
            null);
          _tcOf_members0 = RTC.PortServiceHelper.type ();
          _members0[2] = new org.omg.CORBA.StructMember (
            "port_ref",
            _tcOf_members0,
            null);
          _tcOf_members0 = RTC.ConnectorProfileHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (RTC.ConnectorProfileListHelper.id (), "ConnectorProfileList", _tcOf_members0);
          _members0[3] = new org.omg.CORBA.StructMember (
            "connector_profiles",
            _tcOf_members0,
            null);
          _tcOf_members0 = RTC.RTObjectHelper.type ();
          _members0[4] = new org.omg.CORBA.StructMember (
            "owner",
            _tcOf_members0,
            null);
          _tcOf_members0 = _SDOPackage.NameValueHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (_SDOPackage.NVListHelper.id (), "NVList", _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (RTC.NVListHelper.id (), "NVList", _tcOf_members0);
          _members0[5] = new org.omg.CORBA.StructMember (
            "properties",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (RTC.PortProfileHelper.id (), "PortProfile", _members0);
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

  public static RTC.PortProfile read (org.omg.CORBA.portable.InputStream istream)
  {
    RTC.PortProfile value = new RTC.PortProfile ();
    value.name = istream.read_string ();
    value.interfaces = RTC.PortInterfaceProfileListHelper.read (istream);
    value.port_ref = RTC.PortServiceHelper.read (istream);
    value.connector_profiles = RTC.ConnectorProfileListHelper.read (istream);
    value.owner = RTC.RTObjectHelper.read (istream);
    value.properties = _SDOPackage.NVListHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTC.PortProfile value)
  {
    ostream.write_string (value.name);
    RTC.PortInterfaceProfileListHelper.write (ostream, value.interfaces);
    RTC.PortServiceHelper.write (ostream, value.port_ref);
    RTC.ConnectorProfileListHelper.write (ostream, value.connector_profiles);
    RTC.RTObjectHelper.write (ostream, value.owner);
    _SDOPackage.NVListHelper.write (ostream, value.properties);
  }

}
