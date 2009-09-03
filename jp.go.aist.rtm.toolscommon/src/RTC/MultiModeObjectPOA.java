package RTC;


/**
* RTC/MultiModeObjectPOA.java .
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
public abstract class MultiModeObjectPOA extends org.omg.PortableServer.Servant
 implements RTC.MultiModeObjectOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("initialize", new java.lang.Integer (0));
    _methods.put ("finalize", new java.lang.Integer (1));
    _methods.put ("is_alive", new java.lang.Integer (2));
    _methods.put ("exit", new java.lang.Integer (3));
    _methods.put ("attach_context", new java.lang.Integer (4));
    _methods.put ("detach_context", new java.lang.Integer (5));
    _methods.put ("get_context", new java.lang.Integer (6));
    _methods.put ("get_owned_contexts", new java.lang.Integer (7));
    _methods.put ("get_participating_contexts", new java.lang.Integer (8));
    _methods.put ("get_context_handle", new java.lang.Integer (9));
    _methods.put ("on_initialize", new java.lang.Integer (10));
    _methods.put ("on_finalize", new java.lang.Integer (11));
    _methods.put ("on_startup", new java.lang.Integer (12));
    _methods.put ("on_shutdown", new java.lang.Integer (13));
    _methods.put ("on_activated", new java.lang.Integer (14));
    _methods.put ("on_deactivated", new java.lang.Integer (15));
    _methods.put ("on_aborting", new java.lang.Integer (16));
    _methods.put ("on_error", new java.lang.Integer (17));
    _methods.put ("on_reset", new java.lang.Integer (18));
    _methods.put ("get_default_mode", new java.lang.Integer (19));
    _methods.put ("get_current_mode", new java.lang.Integer (20));
    _methods.put ("get_current_mode_in_context", new java.lang.Integer (21));
    _methods.put ("get_pending_mode", new java.lang.Integer (22));
    _methods.put ("get_pending_mode_in_context", new java.lang.Integer (23));
    _methods.put ("set_mode", new java.lang.Integer (24));
    _methods.put ("on_mode_changed", new java.lang.Integer (25));
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
       case 0:  // RTC/LightweightRTObject/initialize
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
       case 1:  // RTC/LightweightRTObject/_finalize
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
       case 2:  // RTC/LightweightRTObject/is_alive
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
       case 3:  // RTC/LightweightRTObject/exit
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
       case 4:  // RTC/LightweightRTObject/attach_context
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
       case 5:  // RTC/LightweightRTObject/detach_context
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
       case 6:  // RTC/LightweightRTObject/get_context
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
       case 7:  // RTC/LightweightRTObject/get_owned_contexts
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
       case 8:  // RTC/LightweightRTObject/get_participating_contexts
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
       case 9:  // RTC/LightweightRTObject/get_context_handle
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
       case 10:  // RTC/ComponentAction/on_initialize
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
       case 11:  // RTC/ComponentAction/on_finalize
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
       case 12:  // RTC/ComponentAction/on_startup
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
       case 13:  // RTC/ComponentAction/on_shutdown
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
       case 14:  // RTC/ComponentAction/on_activated
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
       case 15:  // RTC/ComponentAction/on_deactivated
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
       case 16:  // RTC/ComponentAction/on_aborting
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
       case 17:  // RTC/ComponentAction/on_error
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
       case 18:  // RTC/ComponentAction/on_reset
       {
         int exec_handle = RTC.ExecutionContextHandle_tHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.on_reset (exec_handle);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief get_default_mode
     *
     * @section Description
     *
     * This operation shall return the mode in which the RTC shall be
     * when no other mode has been set.
     *
     * @section Constraints
     *
     * - This operation shall not return nil.
     *
     * @endif
     */
       case 19:  // RTC/ModeCapable/get_default_mode
       {
         RTC.Mode $result = null;
         $result = this.get_default_mode ();
         out = $rh.createReply();
         RTC.ModeHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief get_current_mode
     *
     * @section Description
     *
     * This operation shall return the last mode to have
     * stabilized. If no mode has been explicitly set, the current
     * mode shall be the default mode.
     *
     * @section Constraints
     *
     * - This operation shall never return nil.
     *
     * @endif
     */
       case 20:  // RTC/ModeCapable/get_current_mode
       {
         RTC.Mode $result = null;
         $result = this.get_current_mode ();
         out = $rh.createReply();
         RTC.ModeHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief get_current_mode_in_context
     *
     * @section Description
     *
     * This operation returns the current mode of the component as
     * seen by the indicated execution context.
     *
     * @section Semantics
     *
     * The manner in which this property changes is described in Figure 5.26.
     *
     * @endif
     */
       case 21:  // RTC/ModeCapable/get_current_mode_in_context
       {
         RTC.ExecutionContext exec_context = RTC.ExecutionContextHelper.read (in);
         RTC.Mode $result = null;
         $result = this.get_current_mode_in_context (exec_context);
         out = $rh.createReply();
         RTC.ModeHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief get_pending_mode
     *
     * @section Description
     *
     * This operation shall return the last mode to have been passed
     * to set_mode that has not yet stabilized. Once the RTCa?s mode
     * has stabilized, this operation shall return nil.
     *
     * @endif
     */
       case 22:  // RTC/ModeCapable/get_pending_mode
       {
         RTC.Mode $result = null;
         $result = this.get_pending_mode ();
         out = $rh.createReply();
         RTC.ModeHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief get_pending_mode_in_context
     *
     * @section Description
     *
     * If the last mode to be requested by a call to set_mode is
     * different than the current mode as seen by the indicated
     * execution context (see get_current_mode_in_context), this
     * operation returns the former. If the requested mode has already
     * been seen in that context, it returns nil.
     *
     * @section Semantics
     *
     * See Figure 5.26 for a description of how the pending mode
     * relates to the current mode within a given execution context.
     *
     * @endif
     */
       case 23:  // RTC/ModeCapable/get_pending_mode_in_context
       {
         RTC.ExecutionContext exec_context = RTC.ExecutionContextHelper.read (in);
         RTC.Mode $result = null;
         $result = this.get_pending_mode_in_context (exec_context);
         out = $rh.createReply();
         RTC.ModeHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief set_mode
     *
     * @section Description
     *
     * This operation shall request that the RTC change to the indicated mode.
     *
     * @section Semantics
     *
     * Usually, the new mode will be pending in each execution context
     * in which the component executes until the next sample period
     * (if the execution kind is PERIODIC); at that point it will
     * become the current mode in that context and there will no
     * longer be a pending mode. However, in some cases it is
     * important for a mode change to take place immediately; for
     * example, a serious fault has occurred and the component must
     * enter an emergency mode to ensure fail-safe behavior in a
     * safety-critical system. In such a case, immediate should be
     * true and the mode change will take place in all contexts
     * without waiting for the next sample period.
     *
     * @endif
     */
       case 24:  // RTC/ModeCapable/set_mode
       {
         RTC.Mode new_mode = RTC.ModeHelper.read (in);
         boolean immediate = in.read_boolean ();
         RTC.ReturnCode_t $result = null;
         $result = this.set_mode (new_mode, immediate);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief on_mode_changed
     *
     * @section Description
     *
     * This callback is invoked each time the observed mode of a
     * component has changed with respect to a particular execution
     * context.
     *
     * @section Semantics
     *
     * If the context is PERIODIC, this callback shall come before the
     * next call to on_execute (see Section 5.3.1.2.1) within that
     * context.  The new mode can be retrieved with
     * get_current_mode_in_context. If the result is the same as the
     * result of get_current_mode, the mode has stabilized.
     *
     * @endif
     */
       case 25:  // RTC/MultiModeComponentAction/on_mode_changed
       {
         int exec_handle = RTC.ExecutionContextHandle_tHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.on_mode_changed (exec_handle);
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
    "IDL:omg.org/RTC/MultiModeObject:1.0", 
    "IDL:omg.org/RTC/LightweightRTObject:1.0", 
    "IDL:omg.org/RTC/ComponentAction:1.0", 
    "IDL:omg.org/RTC/ModeCapable:1.0", 
    "IDL:omg.org/RTC/MultiModeComponentAction:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public MultiModeObject _this() 
  {
    return MultiModeObjectHelper.narrow(
    super._this_object());
  }

  public MultiModeObject _this(org.omg.CORBA.ORB orb) 
  {
    return MultiModeObjectHelper.narrow(
    super._this_object(orb));
  }


} // class MultiModeObjectPOA
