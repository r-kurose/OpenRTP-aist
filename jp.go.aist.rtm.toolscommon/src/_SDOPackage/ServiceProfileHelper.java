package _SDOPackage;


/**
* _SDOPackage/ServiceProfileHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

abstract public class ServiceProfileHelper
{
  private static String  _id = "IDL:org.omg/SDOPackage/ServiceProfile:1.0";

  public static void insert (org.omg.CORBA.Any a, _SDOPackage.ServiceProfile that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static _SDOPackage.ServiceProfile extract (org.omg.CORBA.Any a)
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
            "id",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "interface_type",
            _tcOf_members0,
            null);
          _tcOf_members0 = _SDOPackage.NameValueHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (_SDOPackage.NVListHelper.id (), "NVList", _tcOf_members0);
          _members0[2] = new org.omg.CORBA.StructMember (
            "properties",
            _tcOf_members0,
            null);
          _tcOf_members0 = _SDOPackage.SDOServiceHelper.type ();
          _members0[3] = new org.omg.CORBA.StructMember (
            "service",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (_SDOPackage.ServiceProfileHelper.id (), "ServiceProfile", _members0);
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

  public static _SDOPackage.ServiceProfile read (org.omg.CORBA.portable.InputStream istream)
  {
    _SDOPackage.ServiceProfile value = new _SDOPackage.ServiceProfile ();
    value.id = istream.read_string ();
    value.interface_type = istream.read_string ();
    value.properties = _SDOPackage.NVListHelper.read (istream);
    value.service = _SDOPackage.SDOServiceHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, _SDOPackage.ServiceProfile value)
  {
    ostream.write_string (value.id);
    ostream.write_string (value.interface_type);
    _SDOPackage.NVListHelper.write (ostream, value.properties);
    _SDOPackage.SDOServiceHelper.write (ostream, value.service);
  }

}
