package RTC;

/**
* RTC/ReturnCode_tHolder.java .
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
public final class ReturnCode_tHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.ReturnCode_t value = null;

  public ReturnCode_tHolder ()
  {
  }

  public ReturnCode_tHolder (RTC.ReturnCode_t initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.ReturnCode_tHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.ReturnCode_tHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.ReturnCode_tHelper.type ();
  }

}
