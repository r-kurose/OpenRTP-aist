package _SDOPackage;


/**
* _SDOPackage/NumericTypeHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

abstract public class NumericTypeHelper
{
  private static String  _id = "IDL:org.omg/SDOPackage/NumericType:1.0";

  public static void insert (org.omg.CORBA.Any a, _SDOPackage.NumericType that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static _SDOPackage.NumericType extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_enum_tc (_SDOPackage.NumericTypeHelper.id (), "NumericType", new String[] { "SHORT_TYPE", "LONG_TYPE", "FLOAT_TYPE", "DOUBLE_TYPE"} );
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static _SDOPackage.NumericType read (org.omg.CORBA.portable.InputStream istream)
  {
    return _SDOPackage.NumericType.from_int (istream.read_long ());
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, _SDOPackage.NumericType value)
  {
    ostream.write_long (value.value ());
  }

}
