package RTC;


/**
* RTC/ExecutionKindHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分46秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief ExecutionKind
   * 
   * @sectioni Description
   *
   * The ExecutionKind enumeration defines the execution semantics
   * (see Section 5.3) of the RTCs that participate in an execution
   * context.
   *
   * @endif
   */
abstract public class ExecutionKindHelper
{
  private static String  _id = "IDL:omg.org/RTC/ExecutionKind:1.0";

  public static void insert (org.omg.CORBA.Any a, RTC.ExecutionKind that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTC.ExecutionKind extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_enum_tc (RTC.ExecutionKindHelper.id (), "ExecutionKind", new String[] { "PERIODIC", "EVENT_DRIVEN", "OTHER"} );
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTC.ExecutionKind read (org.omg.CORBA.portable.InputStream istream)
  {
    return RTC.ExecutionKind.from_int (istream.read_long ());
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTC.ExecutionKind value)
  {
    ostream.write_long (value.value ());
  }

}
