package OpenRTMNaming;


/**
* OpenRTMNaming/NamingNotifierHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: ./OpenRTMNaming.idl
* 2011年2月10日 18時15分14秒 JST
*/

abstract public class NamingNotifierHelper
{
  private static String  _id = "IDL:OpenRTMNaming/NamingNotifier:1.0";

  public static void insert (org.omg.CORBA.Any a, OpenRTMNaming.NamingNotifier that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static OpenRTMNaming.NamingNotifier extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (OpenRTMNaming.NamingNotifierHelper.id (), "NamingNotifier");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static OpenRTMNaming.NamingNotifier read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_NamingNotifierStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, OpenRTMNaming.NamingNotifier value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static OpenRTMNaming.NamingNotifier narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof OpenRTMNaming.NamingNotifier)
      return (OpenRTMNaming.NamingNotifier)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      OpenRTMNaming._NamingNotifierStub stub = new OpenRTMNaming._NamingNotifierStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static OpenRTMNaming.NamingNotifier unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof OpenRTMNaming.NamingNotifier)
      return (OpenRTMNaming.NamingNotifier)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      OpenRTMNaming._NamingNotifierStub stub = new OpenRTMNaming._NamingNotifierStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
