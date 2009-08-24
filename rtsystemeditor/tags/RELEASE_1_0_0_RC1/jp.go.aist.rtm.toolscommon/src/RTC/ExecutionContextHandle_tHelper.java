package RTC;


/**
* RTC/ExecutionContextHandle_tHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分46秒 JST
*/

abstract public class ExecutionContextHandle_tHelper
{
  private static String  _id = "IDL:omg.org/RTC/ExecutionContextHandle_t:1.0";

  public static void insert (org.omg.CORBA.Any a, int that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static int extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (RTC.ExecutionContextHandle_tHelper.id (), "ExecutionContextHandle_t", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static int read (org.omg.CORBA.portable.InputStream istream)
  {
    int value = (int)0;
    value = istream.read_long ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, int value)
  {
    ostream.write_long (value);
  }

}
