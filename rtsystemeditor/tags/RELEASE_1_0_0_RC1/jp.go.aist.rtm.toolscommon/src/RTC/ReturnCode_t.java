package RTC;


/**
* RTC/ReturnCode_t.java .
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
public class ReturnCode_t implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 6;
  private static RTC.ReturnCode_t[] __array = new RTC.ReturnCode_t [__size];

  public static final int _RTC_OK = 0;
  public static final RTC.ReturnCode_t RTC_OK = new RTC.ReturnCode_t(_RTC_OK);
  public static final int _RTC_ERROR = 1;
  public static final RTC.ReturnCode_t RTC_ERROR = new RTC.ReturnCode_t(_RTC_ERROR);
  public static final int _BAD_PARAMETER = 2;
  public static final RTC.ReturnCode_t BAD_PARAMETER = new RTC.ReturnCode_t(_BAD_PARAMETER);
  public static final int _UNSUPPORTED = 3;
  public static final RTC.ReturnCode_t UNSUPPORTED = new RTC.ReturnCode_t(_UNSUPPORTED);
  public static final int _OUT_OF_RESOURCES = 4;
  public static final RTC.ReturnCode_t OUT_OF_RESOURCES = new RTC.ReturnCode_t(_OUT_OF_RESOURCES);
  public static final int _PRECONDITION_NOT_MET = 5;
  public static final RTC.ReturnCode_t PRECONDITION_NOT_MET = new RTC.ReturnCode_t(_PRECONDITION_NOT_MET);

  public int value ()
  {
    return __value;
  }

  public static RTC.ReturnCode_t from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected ReturnCode_t (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class ReturnCode_t
