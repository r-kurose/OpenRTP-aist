package RTC;


/**
* RTC/DataFlowComponentActionPOA.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分46秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief DataFlowComponentAction
   *
   * @section Description
   *
   * DataFlowComponentAction is a companion to ComponentAction (see
   * Section 5.2.2.4) that provides additional callbacks for
   * intercepting the two execution passes defined in Section
   * 5.3.1.1.2.
   *
   * @endif
   */
public abstract class DataFlowComponentActionPOA extends org.omg.PortableServer.Servant
 implements RTC.DataFlowComponentActionOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("on_execute", new java.lang.Integer (0));
    _methods.put ("on_state_update", new java.lang.Integer (1));
    _methods.put ("on_rate_changed", new java.lang.Integer (2));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief on_execute
     * @section Description
     *
     * This operation will be invoked periodically at the rate of the
     * given execution context as long as the following conditions
     * hold:
     *
     * - The RTC is Active.
     *
     * - The given execution context is Running.
     *
     * @section Semantics
     *
     This callback occurs during the first execution pass.
     *
     * @section Constraints
     *
     * - The execution context of the given context shall be PERIODIC.
     *
     * @endif
     */
       case 0:  // RTC/DataFlowComponentAction/on_execute
       {
         int exec_handle = RTC.ExecutionContextHandle_tHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.on_execute (exec_handle);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief on_state_update
     *
     * @section Description
     *
     * This operation will be invoked periodically at the rate of the
     * given execution context as long as the following conditions hold:
     *
     * - The RTC is Active.
     *
     * - The given execution context is Running.
     *
     * @section Semantics
     *
     * This callback occurs during the second execution pass.
     *
     * @section Constraints
     *
     * - The execution context of the given context shall be PERIODIC.
     *
     *
     * @endif
     */
       case 1:  // RTC/DataFlowComponentAction/on_state_update
       {
         int exec_handle = RTC.ExecutionContextHandle_tHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.on_state_update (exec_handle);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief on_rate_changed
     *
     * @section Description
     *
     * This operation is a notification that the rate of the indicated
     * execution context (see Section 5.2.2.6.4) has changed.
     *
     * @section Constraints
     *
     * - The execution context of the given context shall be PERIODIC.
     *
     *
     * @endif
     */
       case 2:  // RTC/DataFlowComponentAction/on_rate_changed
       {
         int exec_handle = RTC.ExecutionContextHandle_tHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.on_rate_changed (exec_handle);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:omg.org/RTC/DataFlowComponentAction:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public DataFlowComponentAction _this() 
  {
    return DataFlowComponentActionHelper.narrow(
    super._this_object());
  }

  public DataFlowComponentAction _this(org.omg.CORBA.ORB orb) 
  {
    return DataFlowComponentActionHelper.narrow(
    super._this_object(orb));
  }


} // class DataFlowComponentActionPOA
