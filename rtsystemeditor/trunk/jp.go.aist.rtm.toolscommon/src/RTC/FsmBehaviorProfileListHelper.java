package RTC;


/**
* RTC/FsmBehaviorProfileListHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/

abstract public class FsmBehaviorProfileListHelper
{
  private static String  _id = "IDL:omg.org/RTC/FsmBehaviorProfileList:1.0";

  public static void insert (org.omg.CORBA.Any a, RTC.FsmBehaviorProfile[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTC.FsmBehaviorProfile[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = RTC.FsmBehaviorProfileHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (RTC.FsmBehaviorProfileListHelper.id (), "FsmBehaviorProfileList", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTC.FsmBehaviorProfile[] read (org.omg.CORBA.portable.InputStream istream)
  {
    RTC.FsmBehaviorProfile value[] = null;
    int _len0 = istream.read_long ();
    value = new RTC.FsmBehaviorProfile[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = RTC.FsmBehaviorProfileHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTC.FsmBehaviorProfile[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      RTC.FsmBehaviorProfileHelper.write (ostream, value[_i0]);
  }

}
