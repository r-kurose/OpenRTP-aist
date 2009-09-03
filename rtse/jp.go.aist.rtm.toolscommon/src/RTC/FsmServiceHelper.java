package RTC;


/**
* RTC/FsmServiceHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分45秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief FsmService
   *
   * @section Description
   *
   * The FsmService interface defines operations necessary for
   * Stimulus Response Processing as an SDO service.
   *
   * @endif
   */
abstract public class FsmServiceHelper
{
  private static String  _id = "IDL:omg.org/RTC/FsmService:1.0";

  public static void insert (org.omg.CORBA.Any a, RTC.FsmService that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTC.FsmService extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (RTC.FsmServiceHelper.id (), "FsmService");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTC.FsmService read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_FsmServiceStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTC.FsmService value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static RTC.FsmService narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof RTC.FsmService)
      return (RTC.FsmService)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      RTC._FsmServiceStub stub = new RTC._FsmServiceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
