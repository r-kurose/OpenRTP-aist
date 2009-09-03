package RTM;


/**
* RTM/ManagerHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/Manager.idl
* 2008年12月5日 (金曜日) 12時20分51秒 JST
*/

abstract public class ManagerHelper
{
  private static String  _id = "IDL:RTM/Manager:1.0";

  public static void insert (org.omg.CORBA.Any a, RTM.Manager that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTM.Manager extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (RTM.ManagerHelper.id (), "Manager");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTM.Manager read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ManagerStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTM.Manager value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static RTM.Manager narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof RTM.Manager)
      return (RTM.Manager)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      RTM._ManagerStub stub = new RTM._ManagerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
