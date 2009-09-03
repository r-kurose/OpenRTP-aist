package RTC;


/**
* RTC/ExecutionContextProfile.java .
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
public final class ExecutionContextProfile implements org.omg.CORBA.portable.IDLEntity
{

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief kind
     *
     * @section Description
     *
     * This attribute stores the contexta?s ExecutionKind.
     *
     * @endif
     */
  public RTC.ExecutionKind kind = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief rate
     *
     * @section Description
     *
     * This attribute stores execution rate.
     *
     * @section Semantics
     *
     * If the execution kind is not PERIODIC, the value here may not
     * be valid (and should be negative in that case). See
     * ExecutionContext::get_rate (see Section 5.2.2.6.4) and set_rate
     * (see Section 5.2.2.6.5) for more information.
     *
     * @endif
     */
  public double rate = (double)0;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief owner
     *
     * @section Description
     *
     * This attribute stores a reference to the RTC that owns the context.
     *
     * @endif
     */
  public RTC.RTObject owner = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief participants
     *
     * @section Description
     *
     * This attribute stores references to the contexta?s participant RTCs.
     *
     * @endif
     */
  public RTC.RTObject participants[] = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief properties
     *
     * @section Description
     *
     * This attribute contains additional properties of the execution
     * context.
     *
     * @section Semantics
     *
     * This attribute provides implementations the opportunity to
     * describe additional characteristics of a particular execution
     * context that are outside the scope of this specification.
     *
     * @endif
     */
  public _SDOPackage.NameValue properties[] = null;

  public ExecutionContextProfile ()
  {
  } // ctor

  public ExecutionContextProfile (RTC.ExecutionKind _kind, double _rate, RTC.RTObject _owner, RTC.RTObject[] _participants, _SDOPackage.NameValue[] _properties)
  {
    kind = _kind;
    rate = _rate;
    owner = _owner;
    participants = _participants;
    properties = _properties;
  } // ctor

} // class ExecutionContextProfile
