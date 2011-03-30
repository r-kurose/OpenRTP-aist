package OpenRTM;


/**
* OpenRTM/StatusKindHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: ./OpenRTM.idl
* 2011年2月16日 17時08分39秒 JST
*/

abstract public class StatusKindHelper
{
  private static String  _id = "IDL:OpenRTM/StatusKind:1.0";

  public static void insert (org.omg.CORBA.Any a, OpenRTM.StatusKind that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static OpenRTM.StatusKind extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_enum_tc (OpenRTM.StatusKindHelper.id (), "StatusKind", new String[] { "COMPONENT_PROFILE", "RTC_STATUS", "EC_STATUS", "PORT_PROFILE", "CONFIGURATION", "HEART_BEAT"} );
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static OpenRTM.StatusKind read (org.omg.CORBA.portable.InputStream istream)
  {
    return OpenRTM.StatusKind.from_int (istream.read_long ());
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, OpenRTM.StatusKind value)
  {
    ostream.write_long (value.value ());
  }

}
