package RTC;


/**
* RTC/LightweightRTObjectOperations.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分46秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief LightweightRTObject
   * 
   * @section Description
   *
   * This interface is realized by all lightweight RTCs (as required
   * by the lightweightRTComponent stereotype). It defines the states
   * and transitions through which all RTCs will pass from the time
   * they are created until the time they are destroyed.
   *
   * @section Semantics
   * @subsection Initialization
   *
   * An RTC begins in the Created state; at this point, it has been
   * instantiated but not yet fully initialized. Note that this state
   * is highly implementation-dependent. For example, it may
   * correspond to the invocation of a constructor in languages that
   * support that concept, but not all languages do. Furthermore, how
   * soon this state is entered before initialize is invoked is
   * implementation-dependent. Therefore, it should be relied on by
   * RTC implementers only to the minimum extent possible.  An RTC
   * that has completed its initialization and has not been finalized
   * is said to be Alive.
   *
   * @subsection Execution Context
   *
   * An RTC in the Alive state may participate in any number of
   * execution contexts (see Section 5.2.2.5 ). These contexts shall
   * be represented to an RTC as distinct instances of the
   * ExecutionContext class. The ExecutionContext manages the behavior
   * of each RTC that participates in it. This relationship is defined
   * by the following state machine, which is embedded within the
   * ExecutionContext's own lifecycle (see Figure 5.5 ). Each
   * participating RTC is represented as a separate parallel region.
   *
   * Relative to a given execution context, an RTC may either be
   * Active, Inactive, or in Error. When the RTC is Active in a
   * Running execution context, the ComponentAction callbacks (see
   * Section 5.2.2.4) shall be invoked as appropriate for the contexta?
   * s ExecutionKind. The callbacks shall not be invoked relative to
   * that context when either the RTC is Inactive in that context or
   * the context is Stopped. (Note that starting and stopping an
   * execution context shall not impact whether its participating RTCs
   * are Active or Inactive.)  It may be that a given RTC does not
   * directly participate in any execution contexts. Such an RTC is
   * referred to as passive.  A passive RTC may provide services to
   * other components upon request. At any other time, it shall not be
   * required to perform any ongoing activity of its own; therefore,
   * instances of such an RTC typically exist only as parts (directly
   * or indirectly) of a containing active RTC.
   *
   * @subsection Error Handling
   *
   * If an operation fails while the RTC is Active in a given
   * execution context, the RTC will transition to the Error state
   * corresponding to that context. While the RTC is in Error, the
   * ComponentAction::on_error callback will be invoked in place of
   * those callbacks that would otherwise have been invoked according
   * to the contexta?s ExecutionKind. For example, if the kind is
   * PERIODIC, on_error shall be invoked instead of the pair of
   * on_execute, and on_state_update.  When an RTC is in Error, it may
   * be reset. If resetting is successful, the RTC shall return to the
   * Inactive state. If resetting is unsuccessful, it shall remain in
   * the Error state.
   * 
   * @endif
   */
public interface LightweightRTObjectOperations  extends RTC.ComponentActionOperations
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
  RTC.ReturnCode_t initialize ();

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
  RTC.ReturnCode_t _finalize ();

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
  boolean is_alive (RTC.ExecutionContext exec_context);

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
  RTC.ReturnCode_t exit ();

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
  int attach_context (RTC.ExecutionContext exec_context);

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
  RTC.ReturnCode_t detach_context (int exec_handle);

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
  RTC.ExecutionContext get_context (int exec_handle);

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
  RTC.ExecutionContext[] get_owned_contexts ();

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
  RTC.ExecutionContext[] get_participating_contexts ();

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
  int get_context_handle (RTC.ExecutionContext cxt);
} // interface LightweightRTObjectOperations
