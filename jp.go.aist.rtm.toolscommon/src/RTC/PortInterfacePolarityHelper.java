package RTC;


/**
* RTC/PortInterfacePolarityHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief PortInterfacePolarity
   *
   * @section Description
   *
   * The PortInterfacePolarity enumeration identifies exposed
   * interface instances as provided or required.  @endif
   */
abstract public class PortInterfacePolarityHelper
{
  private static String  _id = "IDL:omg.org/RTC/PortInterfacePolarity:1.0";

  public static void insert (org.omg.CORBA.Any a, RTC.PortInterfacePolarity that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTC.PortInterfacePolarity extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_enum_tc (RTC.PortInterfacePolarityHelper.id (), "PortInterfacePolarity", new String[] { "PROVIDED", "REQUIRED"} );
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTC.PortInterfacePolarity read (org.omg.CORBA.portable.InputStream istream)
  {
    return RTC.PortInterfacePolarity.from_int (istream.read_long ());
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTC.PortInterfacePolarity value)
  {
    ostream.write_long (value.value ());
  }

}
