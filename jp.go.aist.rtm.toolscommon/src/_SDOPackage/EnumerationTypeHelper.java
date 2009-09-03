package _SDOPackage;


/**
* _SDOPackage/EnumerationTypeHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

abstract public class EnumerationTypeHelper
{
  private static String  _id = "IDL:org.omg/SDOPackage/EnumerationType:1.0";

  public static void insert (org.omg.CORBA.Any a, _SDOPackage.EnumerationType that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static _SDOPackage.EnumerationType extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [1];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (_SDOPackage.StringListHelper.id (), "StringList", _tcOf_members0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "enumerated_values",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (_SDOPackage.EnumerationTypeHelper.id (), "EnumerationType", _members0);
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

  public static _SDOPackage.EnumerationType read (org.omg.CORBA.portable.InputStream istream)
  {
    _SDOPackage.EnumerationType value = new _SDOPackage.EnumerationType ();
    value.enumerated_values = _SDOPackage.StringListHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, _SDOPackage.EnumerationType value)
  {
    _SDOPackage.StringListHelper.write (ostream, value.enumerated_values);
  }

}
