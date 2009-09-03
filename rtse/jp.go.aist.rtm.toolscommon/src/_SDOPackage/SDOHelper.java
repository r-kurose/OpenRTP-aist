package _SDOPackage;


/**
* _SDOPackage/SDOHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分39秒 JST
*/

abstract public class SDOHelper
{
  private static String  _id = "IDL:org.omg/SDOPackage/SDO:1.0";

  public static void insert (org.omg.CORBA.Any a, _SDOPackage.SDO that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static _SDOPackage.SDO extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (_SDOPackage.SDOHelper.id (), "SDO");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static _SDOPackage.SDO read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_SDOStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, _SDOPackage.SDO value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static _SDOPackage.SDO narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof _SDOPackage.SDO)
      return (_SDOPackage.SDO)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      _SDOPackage._SDOStub stub = new _SDOPackage._SDOStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
