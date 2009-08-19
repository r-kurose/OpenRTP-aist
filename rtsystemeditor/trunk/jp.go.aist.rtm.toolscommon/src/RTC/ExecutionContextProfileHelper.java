package RTC;


/**
* RTC/ExecutionContextProfileHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief 
   * @endif
   */
abstract public class ExecutionContextProfileHelper
{
  private static String  _id = "IDL:omg.org/RTC/ExecutionContextProfile:1.0";

  public static void insert (org.omg.CORBA.Any a, RTC.ExecutionContextProfile that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTC.ExecutionContextProfile extract (org.omg.CORBA.Any a)
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
          _tcOf_members0 = RTC.ExecutionKindHelper.type ();
          _members0[0] = new org.omg.CORBA.StructMember (
            "kind",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_double);
          _members0[1] = new org.omg.CORBA.StructMember (
            "rate",
            _tcOf_members0,
            null);
          _tcOf_members0 = RTC.RTObjectHelper.type ();
          _members0[2] = new org.omg.CORBA.StructMember (
            "owner",
            _tcOf_members0,
            null);
          _tcOf_members0 = RTC.RTObjectHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (RTC.RTCListHelper.id (), "RTCList", _tcOf_members0);
          _members0[3] = new org.omg.CORBA.StructMember (
            "participants",
            _tcOf_members0,
            null);
          _tcOf_members0 = _SDOPackage.NameValueHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (_SDOPackage.NVListHelper.id (), "NVList", _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (RTC.NVListHelper.id (), "NVList", _tcOf_members0);
          _members0[4] = new org.omg.CORBA.StructMember (
            "properties",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (RTC.ExecutionContextProfileHelper.id (), "ExecutionContextProfile", _members0);
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

  public static RTC.ExecutionContextProfile read (org.omg.CORBA.portable.InputStream istream)
  {
    RTC.ExecutionContextProfile value = new RTC.ExecutionContextProfile ();
    value.kind = RTC.ExecutionKindHelper.read (istream);
    value.rate = istream.read_double ();
    value.owner = RTC.RTObjectHelper.read (istream);
    value.participants = RTC.RTCListHelper.read (istream);
    value.properties = _SDOPackage.NVListHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTC.ExecutionContextProfile value)
  {
    RTC.ExecutionKindHelper.write (ostream, value.kind);
    ostream.write_double (value.rate);
    RTC.RTObjectHelper.write (ostream, value.owner);
    RTC.RTCListHelper.write (ostream, value.participants);
    _SDOPackage.NVListHelper.write (ostream, value.properties);
  }

}
