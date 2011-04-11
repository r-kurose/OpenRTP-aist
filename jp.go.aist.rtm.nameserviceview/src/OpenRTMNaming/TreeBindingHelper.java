package OpenRTMNaming;


/**
* OpenRTMNaming/TreeBindingHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: ./OpenRTMNaming.idl
* 2011年2月10日 18時15分14秒 JST
*/

abstract public class TreeBindingHelper
{
  private static String  _id = "IDL:OpenRTMNaming/TreeBinding:1.0";

  public static void insert (org.omg.CORBA.Any a, OpenRTMNaming.TreeBinding that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static OpenRTMNaming.TreeBinding extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [4];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CosNaming.NameComponentHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (org.omg.CosNaming.NameHelper.id (), "Name", _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (OpenRTMNaming.NameHelper.id (), "Name", _tcOf_members0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "binding_name",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CosNaming.BindingTypeHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (OpenRTMNaming.BindingTypeHelper.id (), "BindingType", _tcOf_members0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "binding_type",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ObjectHelper.type ();
          _members0[2] = new org.omg.CORBA.StructMember (
            "binding_object",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_recursive_tc ("");
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (OpenRTMNaming.TreeBindingListHelper.id (), "TreeBindingList", _tcOf_members0);
          _members0[3] = new org.omg.CORBA.StructMember (
            "binding_children",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (OpenRTMNaming.TreeBindingHelper.id (), "TreeBinding", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static OpenRTMNaming.TreeBinding read (org.omg.CORBA.portable.InputStream istream)
  {
    OpenRTMNaming.TreeBinding value = new OpenRTMNaming.TreeBinding ();
    value.binding_name = org.omg.CosNaming.NameHelper.read (istream);
    value.binding_type = org.omg.CosNaming.BindingTypeHelper.read (istream);
    value.binding_object = org.omg.CORBA.ObjectHelper.read (istream);
    value.binding_children = OpenRTMNaming.TreeBindingListHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, OpenRTMNaming.TreeBinding value)
  {
    org.omg.CosNaming.NameHelper.write (ostream, value.binding_name);
    org.omg.CosNaming.BindingTypeHelper.write (ostream, value.binding_type);
    org.omg.CORBA.ObjectHelper.write (ostream, value.binding_object);
    OpenRTMNaming.TreeBindingListHelper.write (ostream, value.binding_children);
  }

}
