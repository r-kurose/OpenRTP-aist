package _SDOPackage;


/**
* _SDOPackage/NumericHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

abstract public class NumericHelper
{
  private static String  _id = "IDL:org.omg/SDOPackage/Numeric:1.0";

  public static void insert (org.omg.CORBA.Any a, _SDOPackage.Numeric that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static _SDOPackage.Numeric extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      org.omg.CORBA.TypeCode _disTypeCode0;
      _disTypeCode0 = _SDOPackage.NumericTypeHelper.type ();
      org.omg.CORBA.UnionMember[] _members0 = new org.omg.CORBA.UnionMember [4];
      org.omg.CORBA.TypeCode _tcOf_members0;
      org.omg.CORBA.Any _anyOf_members0;

      // Branch for short_value (case label SHORT_TYPE)
      _anyOf_members0 = org.omg.CORBA.ORB.init ().create_any ();
      _SDOPackage.NumericTypeHelper.insert (_anyOf_members0, _SDOPackage.NumericType.SHORT_TYPE);
      _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_short);
      _members0[0] = new org.omg.CORBA.UnionMember (
        "short_value",
        _anyOf_members0,
        _tcOf_members0,
        null);

      // Branch for long_value (case label LONG_TYPE)
      _anyOf_members0 = org.omg.CORBA.ORB.init ().create_any ();
      _SDOPackage.NumericTypeHelper.insert (_anyOf_members0, _SDOPackage.NumericType.LONG_TYPE);
      _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
      _members0[1] = new org.omg.CORBA.UnionMember (
        "long_value",
        _anyOf_members0,
        _tcOf_members0,
        null);

      // Branch for float_value (case label FLOAT_TYPE)
      _anyOf_members0 = org.omg.CORBA.ORB.init ().create_any ();
      _SDOPackage.NumericTypeHelper.insert (_anyOf_members0, _SDOPackage.NumericType.FLOAT_TYPE);
      _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_float);
      _members0[2] = new org.omg.CORBA.UnionMember (
        "float_value",
        _anyOf_members0,
        _tcOf_members0,
        null);

      // Branch for double_value (case label DOUBLE_TYPE)
      _anyOf_members0 = org.omg.CORBA.ORB.init ().create_any ();
      _SDOPackage.NumericTypeHelper.insert (_anyOf_members0, _SDOPackage.NumericType.DOUBLE_TYPE);
      _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_double);
      _members0[3] = new org.omg.CORBA.UnionMember (
        "double_value",
        _anyOf_members0,
        _tcOf_members0,
        null);
      __typeCode = org.omg.CORBA.ORB.init ().create_union_tc (_SDOPackage.NumericHelper.id (), "Numeric", _disTypeCode0, _members0);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static _SDOPackage.Numeric read (org.omg.CORBA.portable.InputStream istream)
  {
    _SDOPackage.Numeric value = new _SDOPackage.Numeric ();
    _SDOPackage.NumericType _dis0 = null;
    _dis0 = _SDOPackage.NumericTypeHelper.read (istream);
    switch (_dis0.value ())
    {
      case _SDOPackage.NumericType._SHORT_TYPE:
        short _short_value = (short)0;
        _short_value = istream.read_short ();
        value.short_value (_short_value);
        break;
      case _SDOPackage.NumericType._LONG_TYPE:
        int _long_value = (int)0;
        _long_value = istream.read_long ();
        value.long_value (_long_value);
        break;
      case _SDOPackage.NumericType._FLOAT_TYPE:
        float _float_value = (float)0;
        _float_value = istream.read_float ();
        value.float_value (_float_value);
        break;
      case _SDOPackage.NumericType._DOUBLE_TYPE:
        double _double_value = (double)0;
        _double_value = istream.read_double ();
        value.double_value (_double_value);
        break;
    }
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, _SDOPackage.Numeric value)
  {
    _SDOPackage.NumericTypeHelper.write (ostream, value.discriminator ());
    switch (value.discriminator ().value ())
    {
      case _SDOPackage.NumericType._SHORT_TYPE:
        ostream.write_short (value.short_value ());
        break;
      case _SDOPackage.NumericType._LONG_TYPE:
        ostream.write_long (value.long_value ());
        break;
      case _SDOPackage.NumericType._FLOAT_TYPE:
        ostream.write_float (value.float_value ());
        break;
      case _SDOPackage.NumericType._DOUBLE_TYPE:
        ostream.write_double (value.double_value ());
        break;
    }
  }

}
