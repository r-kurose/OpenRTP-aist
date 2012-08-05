package OpenRTMNaming;


/**
* OpenRTMNaming/TreeBindingListHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: ./OpenRTMNaming.idl
* 2011年2月10日 18時15分14秒 JST
*/

abstract public class TreeBindingListHelper
{
  private static String  _id = "IDL:OpenRTMNaming/TreeBindingList:1.0";

  public static void insert (org.omg.CORBA.Any a, OpenRTMNaming.TreeBinding[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static OpenRTMNaming.TreeBinding[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = OpenRTMNaming.TreeBindingHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (OpenRTMNaming.TreeBindingListHelper.id (), "TreeBindingList", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static OpenRTMNaming.TreeBinding[] read (org.omg.CORBA.portable.InputStream istream)
  {
    OpenRTMNaming.TreeBinding value[] = null;
    int _len0 = istream.read_long ();
    value = new OpenRTMNaming.TreeBinding[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = OpenRTMNaming.TreeBindingHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, OpenRTMNaming.TreeBinding[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      OpenRTMNaming.TreeBindingHelper.write (ostream, value[_i0]);
  }

}
