package RTC;


/**
* RTC/ExecutionContextListHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分46秒 JST
*/

abstract public class ExecutionContextListHelper
{
  private static String  _id = "IDL:omg.org/RTC/ExecutionContextList:1.0";

  public static void insert (org.omg.CORBA.Any a, RTC.ExecutionContext[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTC.ExecutionContext[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = RTC.ExecutionContextHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (RTC.ExecutionContextListHelper.id (), "ExecutionContextList", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTC.ExecutionContext[] read (org.omg.CORBA.portable.InputStream istream)
  {
    RTC.ExecutionContext value[] = null;
    int _len0 = istream.read_long ();
    value = new RTC.ExecutionContext[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = RTC.ExecutionContextHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTC.ExecutionContext[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      RTC.ExecutionContextHelper.write (ostream, value[_i0]);
  }

}
