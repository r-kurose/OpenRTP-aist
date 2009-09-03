package RTC;


/**
* RTC/ExecutionContextServicePOA.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief ExecutionContextService
   *
   * @section Description
   *
   * An ExecutionContextService exposes an ExecutionContext as an SDO
   * service such that the context may be controlled remotely.
   *
   * @section Semantics
   *
   * Depending on the implementation, this interface may itself be an
   * execution context (that is, it may be passed to the operations of
   * ComponentAction) or it may represent a remote execution context
   * that is not of type ExecutionContextService.  @endif
   */
public abstract class ExecutionContextServicePOA extends org.omg.PortableServer.Servant
 implements RTC.ExecutionContextServiceOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("get_profile", new java.lang.Integer (0));
    _methods.put ("is_running", new java.lang.Integer (1));
    _methods.put ("start", new java.lang.Integer (2));
    _methods.put ("stop", new java.lang.Integer (3));
    _methods.put ("get_rate", new java.lang.Integer (4));
    _methods.put ("set_rate", new java.lang.Integer (5));
    _methods.put ("add_component", new java.lang.Integer (6));
    _methods.put ("remove_component", new java.lang.Integer (7));
    _methods.put ("activate_component", new java.lang.Integer (8));
    _methods.put ("deactivate_component", new java.lang.Integer (9));
    _methods.put ("reset_component", new java.lang.Integer (10));
    _methods.put ("get_component_state", new java.lang.Integer (11));
    _methods.put ("get_kind", new java.lang.Integer (12));
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
     * @brief get_profile
     *
     * @section Description
     *
     * This operation provides a profile "descriptor" for the
     * execution context.
     *
     * @endif
     */
       case 0:  // RTC/ExecutionContextService/get_profile
       {
         RTC.ExecutionContextProfile $result = null;
         $result = this.get_profile ();
         out = $rh.createReply();
         RTC.ExecutionContextProfileHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief is_running
     * @section Description
     *
     * This operation shall return true if the context is in the
     * Running state.
     *
     * @section Semantics
     *
     * While the context is Running, all Active RTCs participating in
     * the context shall be executed according to the contexta?s
     * execution kind.  
     *
     * @endif
     */
       case 1:  // RTC/ExecutionContext/is_running
       {
         boolean $result = false;
         $result = this.is_running ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     *
     * @brief start
     *
     * @section Description
     *
     * Request that the context enter the Running state. Once the
     * state transition occurs, the ComponentAction::on_startup
     * operation (see Section 5.2.2.4.3) will be invoked.  @section *
     *
     * @section Semantics
     *
     * An execution context may not be started
     * until the RT components that participate in it have been
     * initialized. An execution context may be started and stopped
     * multiple times.
     *
     * @section Constraints
     *
     * - This operation shall fail with
     * ReturnCode_t::PRECONDITION_NOT_MET if the context is not in the
     * Stopped state.
     *
     * - This operation shall fail with
     * ReturnCode_t::PRECONDITION_NOT_MET if any of the participating
     * components are not in their Alive state.
     *
     * @endif
     */
       case 2:  // RTC/ExecutionContext/start
       {
         RTC.ReturnCode_t $result = null;
         $result = this.start ();
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief stop
     *
     * @section Description
     *
     * Request that the context enter the Stopped state. Once the
     * transition occurs, the ComponentAction::on_shutdown operation
     * (see Section 5.2.2.4.4) will be invoked.
     *
     * @section Semantics
     *
     * An execution context must be stopped before the RT components
     * that participate in it are finalized.
     *
     * An execution context may be started and stopped multiple times.
     *
     * @section Constraints
     *
     * - This operation shall fail with
     * ReturnCode_t::PRECONDITION_NOT_MET if the context is not in the
     * Running state.
     *
     * @endif
     */
       case 3:  // RTC/ExecutionContext/stop
       {
         RTC.ReturnCode_t $result = null;
         $result = this.stop ();
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief get_rate
     *
     * @section Description
     *
     * This operation shall return the rate (in hertz) at which its
     * Active participating RTCs are being invoked.
     *
     * @section Semantics
     *
     * An implementation is permitted to perform some periodic or
     * quasi-periodic processing within an execution context with an
     * ExecutionKind other than PERIODIC. In such a case, the result
     * of this operation is implementation-defined. If no periodic
     * processing of any kind is taking place within the context, this
     * operation shall fail as described in Section 5.2.1.
     *
     * @section Constraints
     *
     * - If the context has an ExecutionKind of PERIODIC, this
     *   operation shall return a rate greater than zero.
     *
     * @endif
     */
       case 4:  // RTC/ExecutionContext/get_rate
       {
         double $result = (double)0;
         $result = this.get_rate ();
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief set_rate
     *
     * @section Description
     *
     * This operation shall set the rate (in hertz) at which this
     * contexta?s Active participating RTCs are being called.
     *
     * @section Semantics
     *
     * If the execution kind of the context is PERIODIC, a rate change
     * shall result in the invocation of on_rate_changed on any RTCs
     * realizing DataFlowComponentAction that are registered with any
     * RTCs participating in the context.  An implementation is
     * permitted to perform some periodic or quasi-periodic processing
     * within an execution context with an ExecutionKind other than
     * PERIODIC. If such is the case, and the implementation reports a
     * rate from get_rate, this operation shall set that rate
     * successfully provided that the given rate is valid. If no
     * periodic processing of any kind is taking place within the
     * context, this operation shall fail with
     * ReturnCode_t::UNSUPPORTED.
     *
     * @section Constraints
     *
     * - The given rate must be greater than zero. Otherwise, this
     * operation shall fail with ReturnCode_t::BAD_PARAMETER.
     *
     * @endif
     */
       case 5:  // RTC/ExecutionContext/set_rate
       {
         double rate = in.read_double ();
         RTC.ReturnCode_t $result = null;
         $result = this.set_rate (rate);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief add_component
     *
     * @section Description
     *
     * The operation causes the given RTC to begin participating in
     * the execution context.
     *
     * @section Semantics
     *
     * The newly added RTC will receive a call to
     * LightweightRTComponent::attach_context (see Section 5.2.2.2.5)
     * and then enter the Inactive state. 
     * 
     * @section Constraints
     * 
     * - If the ExecutionKind of this context is PERIODIC, the RTC
     * must be a data flow component (see Section 5.3.1.1).
     * Otherwise, this operation shall fail with
     * ReturnCode_t::PRECONDITION_NOT_MET.
     * 
     * - If the ExecutionKind of this context is EVENT_DRIVEN, the RTC
     * must be an FSM participant (see Section 5.3.2.3).  Otherwise,
     * this operation shall fail with
     * ReturnCode_t::PRECONDITION_NOT_MET.
     *
     * @endif
     */
       case 6:  // RTC/ExecutionContext/add_component
       {
         RTC.LightweightRTObject comp = RTC.LightweightRTObjectHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.add_component (comp);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief remove_component
     *
     * @section Description
     *
     * This operation causes a participant RTC to stop participating
     * in the execution context.
     *
     * @section Semantics
     *
     * The removed RTC will receive a call to
     * LightweightRTComponent::detach_context (see Section 5.2.2.2.6).
     *
     * @section Constraints
     *
     * - If the given RTC is not currently participating in the
     * execution context, this operation shall fail with
     * ReturnCode_t::BAD_PARAMETER.
     * 
     * - An RTC must be deactivated before it can be removed from an
     * execution context. If the given RTC is participating in the
     * execution context but is still in the Active state, this
     * operation shall fail with ReturnCode_t::PRECONDITION_NOT_MET.
     *
     * @endif
     */
       case 7:  // RTC/ExecutionContext/remove_component
       {
         RTC.LightweightRTObject comp = RTC.LightweightRTObjectHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.remove_component (comp);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief activate_component
     *
     * @section Description
     *
     * The given participant RTC is Inactive and is therefore not
     * being invoked according to the execution contexta?s execution
     * kind. This operation shall cause the RTC to transition to the
     * Active state such that it may subsequently be invoked in this
     * execution context.
     *
     * @section Semantics
     *
     * The callback on_activate shall be called as a result of calling
     * this operation. This operation shall not return until the
     * callback has returned, and shall result in an error if the
     * callback does.  The following figure is a non-normative example
     * sequence diagram for activate_component.
     *
     * @section Constraints
     *
     * - An execution context can only activate its participant
     *   components. If the given RTC is not participating in the
     *   execution context, this operation shall fail with
     *   ReturnCode_t::BAD_PARAMETER.
     *
     * - An RTC that is in the Error state cannot be activated until
     *   after it has been reset. If the given RTC is in the Error
     *   state, this operation shall fail with
     *   ReturnCode_t::PRECONDITION_NOT_MET.
     *
     * - This operation shall fail with ReturnCode_t::BAD_PARAMETER if
     *   the given component is not in its Alive state.
     *
     * @endif
     */
       case 8:  // RTC/ExecutionContext/activate_component
       {
         RTC.LightweightRTObject comp = RTC.LightweightRTObjectHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.activate_component (comp);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief deactivate_component
     *
     * @section Description
     *
     * The given RTC is Active in the execution context. Cause it to
     * transition to the Inactive state such that it will not be
     * subsequently invoked from the context unless and until it is
     * activated again.
     *
     * @section Semantics
     *
     * The callback on_deactivate shall be called as a result of
     * calling this operation. This operation shall not return until
     * the callback has returned, and shall result in an error if the
     * callback does.  The following figure is a non-normative example
     * sequence diagram for deactivate_component.
     *
     * @section Constraints
     *
     * - An execution context can only deactivate its participant
     * components. If the given RTC is not participating in the
     * execution context, this operation shall fail with
     * ReturnCode_t::BAD_PARAMETER.
     *
     * - This operation shall fail with ReturnCode_t::BAD_PARAMETER if
     * the given component is not in its Alive state.
     *
     * @endif
     */
       case 9:  // RTC/ExecutionContext/deactivate_component
       {
         RTC.LightweightRTObject comp = RTC.LightweightRTObjectHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.deactivate_component (comp);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief reset_component
     *
     * @section Description
     *
     * Attempt to recover the RTC when it is in Error.
     *
     * @section Semantics
     *
     * The ComponentAction::on_reset callback shall be invoked. This
     * operation shall not return until the callback has returned, and
     * shall result in an error if the callback does. If possible, the
     * RTC developer should implement that callback such that the RTC
     * may be returned to a valid state.  * If this operation fails,
     * the RTC will remain in Error.
     *
     * @section Constraints
     *
     * - An RTC may only be reset in an execution context in which it
     *   is in error. If the RTC is not in Error in the identified
     *   context, this operation shall fail with
     *   ReturnCode_t::PRECONDITION_NOT_MET. However, that failure shall
     *   not cause the RTC to enter the Error state.
     *
     * - An RTC may not be reset while in the Created state. Any
     *   attempt to invoke this operation while the RTC is in that state
     *   shall fail with ReturnCode_t::PRECONDITION_NOT_MET. However,
     *   that failure shall not cause the RTC to enter the Error state.
     *
     * @endif
     */
       case 10:  // RTC/ExecutionContext/reset_component
       {
         RTC.LightweightRTObject comp = RTC.LightweightRTObjectHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.reset_component (comp);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief get_component_state
     *
     * @section Description
     *
     * This operation shall report the LifeCycleState of the given
     * participant RTC.
     *
     * @section Constraints
     *
     * - The given RTC must be Alive.
     *
     * - The given RTC must be a participant in the target execution context.
     *
     * - The LifeCycleState returned by this operation shall be one of
     *   LifeCycleState::INACTIVE, ACTIVE, or ERROR.
     *
     * @endif
     */
       case 11:  // RTC/ExecutionContext/get_component_state
       {
         RTC.LightweightRTObject comp = RTC.LightweightRTObjectHelper.read (in);
         RTC.LifeCycleState $result = null;
         $result = this.get_component_state (comp);
         out = $rh.createReply();
         RTC.LifeCycleStateHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief get_kind
     *
     * @section Description
     *
     * This operation shall report the execution kind of the execution
     * context.
     *
     * @endif
     */
       case 12:  // RTC/ExecutionContext/get_kind
       {
         RTC.ExecutionKind $result = null;
         $result = this.get_kind ();
         out = $rh.createReply();
         RTC.ExecutionKindHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:omg.org/RTC/ExecutionContextService:1.0", 
    "IDL:omg.org/RTC/ExecutionContext:1.0", 
    "IDL:org.omg/SDOPackage/SDOService:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ExecutionContextService _this() 
  {
    return ExecutionContextServiceHelper.narrow(
    super._this_object());
  }

  public ExecutionContextService _this(org.omg.CORBA.ORB orb) 
  {
    return ExecutionContextServiceHelper.narrow(
    super._this_object(orb));
  }


} // class ExecutionContextServicePOA
