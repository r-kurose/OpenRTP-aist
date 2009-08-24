package _SDOPackage;


/**
* _SDOPackage/ServiceProfileListHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

abstract public class ServiceProfileListHelper
{
  private static String  _id = "IDL:org.omg/SDOPackage/ServiceProfileList:1.0";

  public static void insert (org.omg.CORBA.Any a, _SDOPackage.ServiceProfile[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static _SDOPackage.ServiceProfile[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = _SDOPackage.ServiceProfileHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (_SDOPackage.ServiceProfileListHelper.id (), "ServiceProfileList", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static _SDOPackage.ServiceProfile[] read (org.omg.CORBA.portable.InputStream istream)
  {
    _SDOPackage.ServiceProfile value[] = null;
    int _len0 = istream.read_long ();
    value = new _SDOPackage.ServiceProfile[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = _SDOPackage.ServiceProfileHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, _SDOPackage.ServiceProfile[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      _SDOPackage.ServiceProfileHelper.write (ostream, value[_i0]);
  }

}
