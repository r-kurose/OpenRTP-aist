package RTC;


/**
* RTC/ReturnCode_tHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分46秒 JST
*/


/*!
   * @if jp
   * @brief ReturnCode_t
   *
   * OMG RTC 1.0 {???????dod?eje?e?a|ewe>??abeoe#de??d???dwd?y?dh
   * d"?d?ehe|~-?d￢cyd??l??dldbd?acdsd?d?abReturnCode_twd?}?p?
   * e?ea|e?sa|e?d?d??y?d?d?d?ac
   *
   * OMG RTC 1.0 d? PIM ?E?djddd?abReturnCode_twd???d?dud?ddeje?e?a|ewe>??
   * d}d?d"??????d?wd?p??xdwd?ab|ad????ad?d??ehe|d￢cyd?dE?d?dyd?ac
   * -eje?e?a|ewe>?l?ur?d?dy~?y? (OMG RTC 1.0 Section 5.2.2.6.4 d?
   *  get_rated?d?fd?)ab??r?d?dydsd?d?d??ehe|d?hdydE?d?dyd?ac
   * - eje?e?a|ewe>?leje?exegeoe?e?e?eae?e?y(RTObject::get_component_profile
   *   OMG RTC 1.0 5.4.2.2.1d?r~?) d??~???dy~?yQbnil{r~?d?dydsd?d?d??
   *   ehe|d?hdydE?d?dyd?ac
   *
   * @else
   * @brief ReturnCode_t
   *
   * A number of operations in this specification will need to report
   * potential error conditions to their clients. This task shall be
   * accomplished by means of operation "return codes" of type
   * ReturnCode_t
   *
   * Operations in the PIM that do not return a value of type
   * ReturnCode_t shall report errors in the following ways, depending
   * on their return type:
   * - If an operation normally returns a positive numerical value (such as
   *   get_rate, see [OMG RTC 1.0 Section 5.2.2.6.4]), it shall indicate
   *   failure by returning a negative value.
   * - If an operation normally returns an object reference (such as
   *   RTObject::get_component_profile, see [OMG RTC 1.0 Section 5.4.2.2.1]),
   *   it shall indicate failure by returning a nil reference.
   *
   * @param RTC_OK The operation completed successfully.
   * @param RTC_ERROR The operation failed with a generic, unspecified error.
   * @param BAD_PARAMETER The operation failed because an illegal argument was
   *        passed to it.
   * @param UNSUPPORTED The operation is unsupported by the implementation
   *        (e.g., it belongs to a compliance point that is not implemented).
   * @param OUT_OF_RESOURCES The target of the operation ran out of the
   *        resources needed to complete the operation.
   * @param PRECONDITION_NOT_MET A pre-condition for the operation was not met.
   *
   * @endif
   */
abstract public class ReturnCode_tHelper
{
  private static String  _id = "IDL:omg.org/RTC/ReturnCode_t:1.0";

  public static void insert (org.omg.CORBA.Any a, RTC.ReturnCode_t that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTC.ReturnCode_t extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_enum_tc (RTC.ReturnCode_tHelper.id (), "ReturnCode_t", new String[] { "RTC_OK", "RTC_ERROR", "BAD_PARAMETER", "UNSUPPORTED", "OUT_OF_RESOURCES", "PRECONDITION_NOT_MET"} );
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTC.ReturnCode_t read (org.omg.CORBA.portable.InputStream istream)
  {
    return RTC.ReturnCode_t.from_int (istream.read_long ());
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTC.ReturnCode_t value)
  {
    ostream.write_long (value.value ());
  }

}
