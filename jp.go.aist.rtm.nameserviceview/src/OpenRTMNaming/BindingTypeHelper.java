package OpenRTMNaming;


/**
* OpenRTMNaming/BindingTypeHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: ./OpenRTMNaming.idl
* 2011年2月10日 18時15分14秒 JST
*/

abstract public class BindingTypeHelper
{
  private static String  _id = "IDL:OpenRTMNaming/BindingType:1.0";

  public static void insert (org.omg.CORBA.Any a, org.omg.CosNaming.BindingType that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static org.omg.CosNaming.BindingType extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CosNaming.BindingTypeHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (OpenRTMNaming.BindingTypeHelper.id (), "BindingType", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static org.omg.CosNaming.BindingType read (org.omg.CORBA.portable.InputStream istream)
  {
    org.omg.CosNaming.BindingType value = null;
    value = org.omg.CosNaming.BindingTypeHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, org.omg.CosNaming.BindingType value)
  {
    org.omg.CosNaming.BindingTypeHelper.write (ostream, value);
  }

}
