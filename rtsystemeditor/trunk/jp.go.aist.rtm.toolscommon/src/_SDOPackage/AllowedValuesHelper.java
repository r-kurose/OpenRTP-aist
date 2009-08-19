package _SDOPackage;


/**
* _SDOPackage/AllowedValuesHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

abstract public class AllowedValuesHelper
{
  private static String  _id = "IDL:org.omg/SDOPackage/AllowedValues:1.0";

  public static void insert (org.omg.CORBA.Any a, _SDOPackage.AllowedValues that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static _SDOPackage.AllowedValues extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      org.omg.CORBA.TypeCode _disTypeCode0;
      _disTypeCode0 = _SDOPackage.ComplexDataTypeHelper.type ();
      org.omg.CORBA.UnionMember[] _members0 = new org.omg.CORBA.UnionMember [3];
      org.omg.CORBA.TypeCode _tcOf_members0;
      org.omg.CORBA.Any _anyOf_members0;

      // Branch for allowed_enum (case label ENUMERATION)
      _anyOf_members0 = org.omg.CORBA.ORB.init ().create_any ();
      _SDOPackage.ComplexDataTypeHelper.insert (_anyOf_members0, _SDOPackage.ComplexDataType.ENUMERATION);
      _tcOf_members0 = _SDOPackage.EnumerationTypeHelper.type ();
      _members0[0] = new org.omg.CORBA.UnionMember (
        "allowed_enum",
        _anyOf_members0,
        _tcOf_members0,
        null);

      // Branch for allowed_interval (case label INTERVAL)
      _anyOf_members0 = org.omg.CORBA.ORB.init ().create_any ();
      _SDOPackage.ComplexDataTypeHelper.insert (_anyOf_members0, _SDOPackage.ComplexDataType.INTERVAL);
      _tcOf_members0 = _SDOPackage.IntervalTypeHelper.type ();
      _members0[1] = new org.omg.CORBA.UnionMember (
        "allowed_interval",
        _anyOf_members0,
        _tcOf_members0,
        null);

      // Branch for allowed_range (case label RANGE)
      _anyOf_members0 = org.omg.CORBA.ORB.init ().create_any ();
      _SDOPackage.ComplexDataTypeHelper.insert (_anyOf_members0, _SDOPackage.ComplexDataType.RANGE);
      _tcOf_members0 = _SDOPackage.RangeTypeHelper.type ();
      _members0[2] = new org.omg.CORBA.UnionMember (
        "allowed_range",
        _anyOf_members0,
        _tcOf_members0,
        null);
      __typeCode = org.omg.CORBA.ORB.init ().create_union_tc (_SDOPackage.AllowedValuesHelper.id (), "AllowedValues", _disTypeCode0, _members0);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static _SDOPackage.AllowedValues read (org.omg.CORBA.portable.InputStream istream)
  {
    _SDOPackage.AllowedValues value = new _SDOPackage.AllowedValues ();
    _SDOPackage.ComplexDataType _dis0 = null;
    _dis0 = _SDOPackage.ComplexDataTypeHelper.read (istream);
    switch (_dis0.value ())
    {
      case _SDOPackage.ComplexDataType._ENUMERATION:
        _SDOPackage.EnumerationType _allowed_enum = null;
        _allowed_enum = _SDOPackage.EnumerationTypeHelper.read (istream);
        value.allowed_enum (_allowed_enum);
        break;
      case _SDOPackage.ComplexDataType._INTERVAL:
        _SDOPackage.IntervalType _allowed_interval = null;
        _allowed_interval = _SDOPackage.IntervalTypeHelper.read (istream);
        value.allowed_interval (_allowed_interval);
        break;
      case _SDOPackage.ComplexDataType._RANGE:
        _SDOPackage.RangeType _allowed_range = null;
        _allowed_range = _SDOPackage.RangeTypeHelper.read (istream);
        value.allowed_range (_allowed_range);
        break;
    }
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, _SDOPackage.AllowedValues value)
  {
    _SDOPackage.ComplexDataTypeHelper.write (ostream, value.discriminator ());
    switch (value.discriminator ().value ())
    {
      case _SDOPackage.ComplexDataType._ENUMERATION:
        _SDOPackage.EnumerationTypeHelper.write (ostream, value.allowed_enum ());
        break;
      case _SDOPackage.ComplexDataType._INTERVAL:
        _SDOPackage.IntervalTypeHelper.write (ostream, value.allowed_interval ());
        break;
      case _SDOPackage.ComplexDataType._RANGE:
        _SDOPackage.RangeTypeHelper.write (ostream, value.allowed_range ());
        break;
    }
  }

}
