package _SDOPackage;


/**
* _SDOPackage/IntervalTypeHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

abstract public class IntervalTypeHelper
{
  private static String  _id = "IDL:org.omg/SDOPackage/IntervalType:1.0";

  public static void insert (org.omg.CORBA.Any a, _SDOPackage.IntervalType that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static _SDOPackage.IntervalType extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [5];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = _SDOPackage.NumericHelper.type ();
          _members0[0] = new org.omg.CORBA.StructMember (
            "min",
            _tcOf_members0,
            null);
          _tcOf_members0 = _SDOPackage.NumericHelper.type ();
          _members0[1] = new org.omg.CORBA.StructMember (
            "max",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_boolean);
          _members0[2] = new org.omg.CORBA.StructMember (
            "min_inclusive",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_boolean);
          _members0[3] = new org.omg.CORBA.StructMember (
            "max_inclusive",
            _tcOf_members0,
            null);
          _tcOf_members0 = _SDOPackage.NumericHelper.type ();
          _members0[4] = new org.omg.CORBA.StructMember (
            "step",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (_SDOPackage.IntervalTypeHelper.id (), "IntervalType", _members0);
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

  public static _SDOPackage.IntervalType read (org.omg.CORBA.portable.InputStream istream)
  {
    _SDOPackage.IntervalType value = new _SDOPackage.IntervalType ();
    value.min = _SDOPackage.NumericHelper.read (istream);
    value.max = _SDOPackage.NumericHelper.read (istream);
    value.min_inclusive = istream.read_boolean ();
    value.max_inclusive = istream.read_boolean ();
    value.step = _SDOPackage.NumericHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, _SDOPackage.IntervalType value)
  {
    _SDOPackage.NumericHelper.write (ostream, value.min);
    _SDOPackage.NumericHelper.write (ostream, value.max);
    ostream.write_boolean (value.min_inclusive);
    ostream.write_boolean (value.max_inclusive);
    _SDOPackage.NumericHelper.write (ostream, value.step);
  }

}
