package RTC;


/**
* RTC/RTObjectPOA.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief RTObject
   *
   * @section Description
   *
   * The RTObject interface defines the operations that all SDO-based
   * RTCs must provide. It is required by the rtComponent stereotype.
   *
   * @endif
   */
public abstract class RTObjectPOA extends org.omg.PortableServer.Servant
 implements RTC.RTObjectOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("get_component_profile", new java.lang.Integer (0));
    _methods.put ("get_ports", new java.lang.Integer (1));
    _methods.put ("initialize", new java.lang.Integer (2));
    _methods.put ("finalize", new java.lang.Integer (3));
    _methods.put ("is_alive", new java.lang.Integer (4));
    _methods.put ("exit", new java.lang.Integer (5));
    _methods.put ("attach_context", new java.lang.Integer (6));
    _methods.put ("detach_context", new java.lang.Integer (7));
    _methods.put ("get_context", new java.lang.Integer (8));
    _methods.put ("get_owned_contexts", new java.lang.Integer (9));
    _methods.put ("get_participating_contexts", new java.lang.Integer (10));
    _methods.put ("get_context_handle", new java.lang.Integer (11));
    _methods.put ("on_initialize", new java.lang.Integer (12));
    _methods.put ("on_finalize", new java.lang.Integer (13));
    _methods.put ("on_startup", new java.lang.Integer (14));
    _methods.put ("on_shutdown", new java.lang.Integer (15));
    _methods.put ("on_activated", new java.lang.Integer (16));
    _methods.put ("on_deactivated", new java.lang.Integer (17));
    _methods.put ("on_aborting", new java.lang.Integer (18));
    _methods.put ("on_error", new java.lang.Integer (19));
    _methods.put ("on_reset", new java.lang.Integer (20));
    _methods.put ("get_sdo_id", new java.lang.Integer (21));
    _methods.put ("get_sdo_type", new java.lang.Integer (22));
    _methods.put ("get_device_profile", new java.lang.Integer (23));
    _methods.put ("get_service_profiles", new java.lang.Integer (24));
    _methods.put ("get_service_profile", new java.lang.Integer (25));
    _methods.put ("get_sdo_service", new java.lang.Integer (26));
    _methods.put ("get_configuration", new java.lang.Integer (27));
    _methods.put ("get_monitoring", new java.lang.Integer (28));
    _methods.put ("get_organizations", new java.lang.Integer (29));
    _methods.put ("get_status_list", new java.lang.Integer (30));
    _methods.put ("get_status", new java.lang.Integer (31));
    _methods.put ("get_owned_organizations", new java.lang.Integer (32));
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
     * @brief get_component_profile
     *
     * @section Description
     *
     * This operation returns the ComponentProfile of the RTC.
     *
     * @endif
     */
       case 0:  // RTC/RTObject/get_component_profile
       {
         RTC.ComponentProfile $result = null;
         $result = this.get_component_profile ();
         out = $rh.createReply();
         RTC.ComponentProfileHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief get_ports
     *
     * @section Description
     *
     * This operation returns a list of the RTCs ports.
     *
     * @endif
     */
       case 1:  // RTC/RTObject/get_ports
       {
         RTC.PortService $result[] = null;
         $result = this.get_ports ();
         out = $rh.createReply();
         RTC.PortServiceListHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief initialize
     *
     * @section Description
     * Initialize the RTC that realizes this interface.
     *
     * @section Semantics
     * The invocation of this operation shall result in the invocation
     * of the callback ComponentAction::on_initialize.
     *
     * @section Constraints
     *
     * - An RTC may be initialized only while it is in the Created
     *   state. Any attempt to invoke this operation while in another
     *   state shall fail with ReturnCode_t::PRECONDITION_NOT_MET.
     * - Application developers are not expected to call this operation
     *   directly; it exists for use by the RTC infrastructure.
     *
     * @endif
     */
       case 2:  // RTC/LightweightRTObject/initialize
       {
         RTC.ReturnCode_t $result = null;
         $result = this.initialize ();
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief finalize
     *
     * @section Description
     *
     * Finalize the RTC that realizes this interface, preparing it for
     * destruction.
     *
     * @section Semantics
     *
     * This invocation of this operation shall result in the
     * invocation of the callback ComponentAction::on_finalize
     *
     * @section Constraints
     *
     * - An RTC may not be finalized while it is participating in any
     *   execution context. It must first be removed with
     *   ExecutionContextOperations::remove_component. Otherwise, this
     *   operation shall fail with
     *   ReturnCode_t::PRECONDITION_NOT_MET. See Figure 5.9.
     *
     * - An RTC may not be finalized while it is in the Created state. Any
     *   attempt to invoke this operation while in that state shall fail with
     *   ReturnCode_t::PRECONDITION_NOT_MET.
     *
     * - Application developers are not expected to call this operation
     *   directly; it exists for use by the RTC infrastructure.
     *
     * @endif
     */
       case 3:  // RTC/LightweightRTObject/_finalize
       {
         RTC.ReturnCode_t $result = null;
         $result = this._finalize ();
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief is_alive
     * 
     * @section Description
     *
     * A component is alive or not regardless of the execution context
     * from which it is observed. However, whether or not it is
     * Active, Inactive, or in Error is dependent on the execution
     * context(s) (see Figure 5.7) in which it is running. That is, it
     * may be Active in one context but Inactive in
     * another. Therefore, this operation shall report whether this
     * RTC is either Active, Inactive, or in Error; which of those
     * states a component is in with respect to a particular context
     * may be queried from the context itself.
     *
     * @endif
     *
     */
       case 4:  // RTC/LightweightRTObject/is_alive
       {
         RTC.ExecutionContext exec_context = RTC.ExecutionContextHelper.read (in);
         boolean $result = false;
         $result = this.is_alive (exec_context);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief exit
     *
     * @section Description
     *
     * Stop the RTCa?s execution context(s) and finalize it along with
     * its contents.
     *
     * @section Semantics
     *
     * Any execution contexts for which the RTC is the owner shall be
     * stopped.  If the RTC participates in any execution contexts
     * belonging to another RTC that contains it, directly or
     * indirectly (i.e., the containing RTC is the owner of the
     * ExecutionContext), it shall be deactivated in those contexts.
     * After the RTC is no longer Active in any Running execution
     * context, it and any RTCs contained transitively within it shall
     * be finalized.
     *
     * @section Constraints
     *
     * An RTC cannot be exited if it has not yet been initialized. Any
     * attempt to exit an RTC that is in the Created state shall fail
     * with ReturnCode_t::PRECONDITION_NOT_MET.  
     *
     * @endif
     */
       case 5:  // RTC/LightweightRTObject/exit
       {
         RTC.ReturnCode_t $result = null;
         $result = this.exit ();
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief attach_context
     *
     * @section Description
     *
     * Inform this RTC that it is participating in the given execution
     * context. Return a handle that represents the association of
     * this RTC with the context.
     *
     * @section Semantics
     *
     * This operation is intended to be invoked by
     * ExecutionContextOperations::add_component (see Section
     * 5.2.2.6.6). It is not intended for use by other clients.
     *
     * @endif
     */
       case 6:  // RTC/LightweightRTObject/attach_context
       {
         RTC.ExecutionContext exec_context = RTC.ExecutionContextHelper.read (in);
         int $result = (int)0;
         $result = this.attach_context (exec_context);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief detach_context
     * @section Description
     * 
     * Inform this RTC that it is no longer participating in the given
     * execution context.
     *
     * @section Semantics
     * 
     * This operation is intended to be invoked by
     * ExecutionContextOperations::remove_component (see Section
     * 5.2.2.6.7). It is not intended for use by other clients.
     *
     * @section Constraints
     *
     * - This operation may not be invoked if this RTC is not already
     *   participating in the execution context. Such a call shall fail
     *   with ReturnCode_t::PRECONDITION_NOT_MET.
     *
     * - This operation may not be invoked if this RTC is Active in
     *   the indicated execution context. Otherwise, it shall fail with
     *   ReturnCode_t::PRECONDITION_NOT_MET.
     *
     * @endif
     */
       case 7:  // RTC/LightweightRTObject/detach_context
       {
         int exec_handle = RTC.ExecutionContextHandle_tHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.detach_context (exec_handle);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief get_context
     *
     * @section Description
     *
     * Obtain a reference to the execution context represented by the
     * given handle.
     *
     * @section Semantics
     *
     * The mapping from handle to context is specific to a particular
     * RTC instance. The given handle must have been obtained by a
     * previous call to attach_context on this RTC.
     *
     * @endif
     */
       case 8:  // RTC/LightweightRTObject/get_context
       {
         int exec_handle = RTC.ExecutionContextHandle_tHelper.read (in);
         RTC.ExecutionContext $result = null;
         $result = this.get_context (exec_handle);
         out = $rh.createReply();
         RTC.ExecutionContextHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief get_owned_contexts
     *
     * @section Description 
     *
     * dsd? RTC dl}??mdyd? ExecutionContext d?e?eye?d?9?dyd?ac
     * 
     * @else
     * @brief get_owned_contexts
     *
     * @section Description 
     *
     * This operation returns a list of all execution contexts owned
     * by this RTC.
     *
     * @endif
     */
       case 9:  // RTC/LightweightRTObject/get_owned_contexts
       {
         RTC.ExecutionContext $result[] = null;
         $result = this.get_owned_contexts ();
         out = $rh.createReply();
         RTC.ExecutionContextListHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief get_participating_contexts
     *  
     * @section Description
     *
     * dsd? RTC dl{rr?dwd?ddd?dyd?d?d? ExecutionContext d?e?eye?d?9?dyd?ac
     *
     * @section Semantics
     *
     * dsd?e?eye?d?t?d?d?d?|?y?ese??emeye?d?abattach_context dlx?d?}?
     * dud?d?dtd?d?abe?eye?d???r?dud?abdetach_context dlx?d?}?dud?d?dt
     * d?d?abe?eye?dkd??}?dud?d?ac
     *
     * @else
     * @brief* get_participating_contexts
     *
     * @section Description
     *
     * This operation returns a list of all execution contexts in
     * which this RTC participates.
     *
     * @section Semantics
     *
     * Each call to attach_context causes the provided context to be
     * added to this list. Each call to detach_context causes the
     * provided context to be removed from this list.
     *
     * @endif
     */
       case 10:  // RTC/LightweightRTObject/get_participating_contexts
       {
         RTC.ExecutionContext $result[] = null;
         $result = this.get_participating_contexts ();
         out = $rh.createReply();
         RTC.ExecutionContextListHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * ### [x?b] RTC.idl d?d?t?d?d?d?ddd?dddlPIMd?d?t?d?d?d?ddd?ac
     * ### PIMdl?udwddac
     *
     * @brief 
     * @else
     * @brief get_context_handle
     *
     * @section Description
     * 
     * This operation returns a handle that is associated with the given
     * execution context.
     *
     * @endif
     */
       case 11:  // RTC/LightweightRTObject/get_context_handle
       {
         RTC.ExecutionContext cxt = RTC.ExecutionContextHelper.read (in);
         int $result = (int)0;
         $result = this.get_context_handle (cxt);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief on_initialize
     *
     * @section Description
     *
     * The RTC has been initialized and entered the Alive state.
     *
     * @section Semantics
     *
     * Any RTC-specific initialization logic should be performed here.
     *
     * @endif
     */
       case 12:  // RTC/ComponentAction/on_initialize
       {
         RTC.ReturnCode_t $result = null;
         $result = this.on_initialize ();
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief on_finalize
     *
     * @section Description
     *
     * The RTC is being destroyed.
     *
     * @section Semantics
     *
     * Any final RTC-specific tear-down logic should be performed here.
     *
     * @endif
     */
       case 13:  // RTC/ComponentAction/on_finalize
       {
         RTC.ReturnCode_t $result = null;
         $result = this.on_finalize ();
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief on_startup
     *
     * @section Description
     *
     * The given execution context, in which the RTC is participating,
     * has transitioned from Stopped to Running.
     *
     * @endif
     */
       case 14:  // RTC/ComponentAction/on_startup
       {
         int exec_handle = RTC.ExecutionContextHandle_tHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.on_startup (exec_handle);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief on_shutdown
     *
     * @section Description
     *
     * The given execution context, in which the RTC is participating,
     * has transitioned from Running to Stopped.
     *
     * @endif
     */
       case 15:  // RTC/ComponentAction/on_shutdown
       {
         int exec_handle = RTC.ExecutionContextHandle_tHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.on_shutdown (exec_handle);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief on_activated
     *
     * @section Description
     *
     * The RTC has been activated in the given execution context.
     *
     * @endif
     */
       case 16:  // RTC/ComponentAction/on_activated
       {
         int exec_handle = RTC.ExecutionContextHandle_tHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.on_activated (exec_handle);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief on_deactivated
     *
     * @section Description
     *
     * The RTC has been deactivated in the given execution context.
     *
     * @endif
     */
       case 17:  // RTC/ComponentAction/on_deactivated
       {
         int exec_handle = RTC.ExecutionContextHandle_tHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.on_deactivated (exec_handle);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief on_aborting
     *
     * @section Description
     *
     * The RTC is transitioning from the Active state to the Error
     * state in some execution context.
     *
     * @section Semantics
     *
     * This callback is invoked only a single time for time that the
     * RTC transitions into the Error state from another state. This
     * behavior is in contrast to that of on_error.  
     *
     * @endif
     */
       case 18:  // RTC/ComponentAction/on_aborting
       {
         int exec_handle = RTC.ExecutionContextHandle_tHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.on_aborting (exec_handle);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief on_error
     *
     * @section Description
     *
     * The RTC remains in the Error state.
     *
     * @section Semantics
     *
     * If the RTC is in the Error state relative to some execution
     * context when it would otherwise be invoked from that context
     * (according to the contexta?s ExecutionKind), this callback
     * shall be invoked instead. For example,
     *
     * - If the ExecutionKind is PERIODIC, this operation shall be
     *   invoked in sorted order at the rate of the context instead of
     *   DataFlowComponentAction::on_execute and on_state_update.
     *
     * - If the ExecutionKind is EVENT_DRIVEN, this operation shall be
     *   invoked whenever FsmParticipantAction::on_action would
     *   otherwise have been invoked.
     *
     * @endif
     */
       case 19:  // RTC/ComponentAction/on_error
       {
         int exec_handle = RTC.ExecutionContextHandle_tHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.on_error (exec_handle);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief on_reset
     *
     * @section Description
     *
     * The RTC is in the Error state. An attempt is being made to
     * recover it such that it can return to the Inactive state.
     *
     * @section Semantics
     *
     * If the RTC was successfully recovered and can safely return to
     * the Inactive state, this method shall complete with
     * ReturnCode_t::OK. Any other result shall indicate that the RTC
     * should remain in the Error state.
     *
     * @endif
     */
       case 20:  // RTC/ComponentAction/on_reset
       {
         int exec_handle = RTC.ExecutionContextHandle_tHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.on_reset (exec_handle);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }

       case 21:  // _SDOPackage/SDO/get_sdo_id
       {
         try {
           String $result = null;
           $result = this.get_sdo_id ();
           out = $rh.createReply();
           out.write_string ($result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 22:  // _SDOPackage/SDO/get_sdo_type
       {
         try {
           String $result = null;
           $result = this.get_sdo_type ();
           out = $rh.createReply();
           out.write_string ($result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 23:  // _SDOPackage/SDO/get_device_profile
       {
         try {
           _SDOPackage.DeviceProfile $result = null;
           $result = this.get_device_profile ();
           out = $rh.createReply();
           _SDOPackage.DeviceProfileHelper.write (out, $result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 24:  // _SDOPackage/SDO/get_service_profiles
       {
         try {
           _SDOPackage.ServiceProfile $result[] = null;
           $result = this.get_service_profiles ();
           out = $rh.createReply();
           _SDOPackage.ServiceProfileListHelper.write (out, $result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 25:  // _SDOPackage/SDO/get_service_profile
       {
         try {
           String id = _SDOPackage.UniqueIdentifierHelper.read (in);
           _SDOPackage.ServiceProfile $result = null;
           $result = this.get_service_profile (id);
           out = $rh.createReply();
           _SDOPackage.ServiceProfileHelper.write (out, $result);
         } catch (_SDOPackage.InvalidParameter $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InvalidParameterHelper.write (out, $ex);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 26:  // _SDOPackage/SDO/get_sdo_service
       {
         try {
           String id = _SDOPackage.UniqueIdentifierHelper.read (in);
           _SDOPackage.SDOService $result = null;
           $result = this.get_sdo_service (id);
           out = $rh.createReply();
           _SDOPackage.SDOServiceHelper.write (out, $result);
         } catch (_SDOPackage.InvalidParameter $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InvalidParameterHelper.write (out, $ex);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 27:  // _SDOPackage/SDO/get_configuration
       {
         try {
           _SDOPackage.Configuration $result = null;
           $result = this.get_configuration ();
           out = $rh.createReply();
           _SDOPackage.ConfigurationHelper.write (out, $result);
         } catch (_SDOPackage.InterfaceNotImplemented $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InterfaceNotImplementedHelper.write (out, $ex);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 28:  // _SDOPackage/SDO/get_monitoring
       {
         try {
           _SDOPackage.Monitoring $result = null;
           $result = this.get_monitoring ();
           out = $rh.createReply();
           _SDOPackage.MonitoringHelper.write (out, $result);
         } catch (_SDOPackage.InterfaceNotImplemented $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InterfaceNotImplementedHelper.write (out, $ex);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 29:  // _SDOPackage/SDO/get_organizations
       {
         try {
           _SDOPackage.Organization $result[] = null;
           $result = this.get_organizations ();
           out = $rh.createReply();
           _SDOPackage.OrganizationListHelper.write (out, $result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 30:  // _SDOPackage/SDO/get_status_list
       {
         try {
           _SDOPackage.NameValue $result[] = null;
           $result = this.get_status_list ();
           out = $rh.createReply();
           _SDOPackage.NVListHelper.write (out, $result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 31:  // _SDOPackage/SDO/get_status
       {
         try {
           String name = in.read_string ();
           org.omg.CORBA.Any $result = null;
           $result = this.get_status (name);
           out = $rh.createReply();
           out.write_any ($result);
         } catch (_SDOPackage.InvalidParameter $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InvalidParameterHelper.write (out, $ex);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 32:  // _SDOPackage/SDOSystemElement/get_owned_organizations
       {
         try {
           _SDOPackage.Organization $result[] = null;
           $result = this.get_owned_organizations ();
           out = $rh.createReply();
           _SDOPackage.OrganizationListHelper.write (out, $result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:omg.org/RTC/RTObject:1.0", 
    "IDL:omg.org/RTC/LightweightRTObject:1.0", 
    "IDL:omg.org/RTC/ComponentAction:1.0", 
    "IDL:org.omg/SDOPackage/SDO:1.0", 
    "IDL:org.omg/SDOPackage/SDOSystemElement:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public RTObject _this() 
  {
    return RTObjectHelper.narrow(
    super._this_object());
  }

  public RTObject _this(org.omg.CORBA.ORB orb) 
  {
    return RTObjectHelper.narrow(
    super._this_object(orb));
  }


} // class RTObjectPOA
