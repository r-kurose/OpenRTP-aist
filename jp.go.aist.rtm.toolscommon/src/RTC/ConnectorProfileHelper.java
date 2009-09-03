package RTC;


/**
* RTC/ConnectorProfileHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief ConnectorProfile
   *
   * @section Description
   *
   * The ConnectorProfile contains information about a connection
   * between the ports of collaborating RTCs.
   *
   * @endif
   */
abstract public class ConnectorProfileHelper
{
  private static String  _id = "IDL:omg.org/RTC/ConnectorProfile:1.0";

  public static void insert (org.omg.CORBA.Any a, RTC.ConnectorProfile that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTC.ConnectorProfile extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [4];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "name",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (_SDOPackage.UniqueIdentifierHelper.id (), "UniqueIdentifier", _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (RTC.UniqueIdentifierHelper.id (), "UniqueIdentifier", _tcOf_members0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "connector_id",
            _tcOf_members0,
            null);
          _tcOf_members0 = RTC.PortServiceHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (RTC.PortServiceListHelper.id (), "PortServiceList", _tcOf_members0);
          _members0[2] = new org.omg.CORBA.StructMember (
            "ports",
            _tcOf_members0,
            null);
          _tcOf_members0 = _SDOPackage.NameValueHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (_SDOPackage.NVListHelper.id (), "NVList", _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (RTC.NVListHelper.id (), "NVList", _tcOf_members0);
          _members0[3] = new org.omg.CORBA.StructMember (
            "properties",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (RTC.ConnectorProfileHelper.id (), "ConnectorProfile", _members0);
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

  public static RTC.ConnectorProfile read (org.omg.CORBA.portable.InputStream istream)
  {
    RTC.ConnectorProfile value = new RTC.ConnectorProfile ();
    value.name = istream.read_string ();
    value.connector_id = istream.read_string ();
    value.ports = RTC.PortServiceListHelper.read (istream);
    value.properties = _SDOPackage.NVListHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTC.ConnectorProfile value)
  {
    ostream.write_string (value.name);
    ostream.write_string (value.connector_id);
    RTC.PortServiceListHelper.write (ostream, value.ports);
    _SDOPackage.NVListHelper.write (ostream, value.properties);
  }

}
