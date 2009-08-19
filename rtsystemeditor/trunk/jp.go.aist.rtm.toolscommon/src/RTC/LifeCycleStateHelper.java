package RTC;


/**
* RTC/LifeCycleStateHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分46秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief LifeCycleState
   *
   * @section Description
   * LifeCycleState is an enumeration of the states in the lifecycle above.
   *
   * @endif
   */
abstract public class LifeCycleStateHelper
{
  private static String  _id = "IDL:omg.org/RTC/LifeCycleState:1.0";

  public static void insert (org.omg.CORBA.Any a, RTC.LifeCycleState that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTC.LifeCycleState extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_enum_tc (RTC.LifeCycleStateHelper.id (), "LifeCycleState", new String[] { "CREATED_STATE", "INACTIVE_STATE", "ACTIVE_STATE", "ERROR_STATE"} );
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTC.LifeCycleState read (org.omg.CORBA.portable.InputStream istream)
  {
    return RTC.LifeCycleState.from_int (istream.read_long ());
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTC.LifeCycleState value)
  {
    ostream.write_long (value.value ());
  }

}
