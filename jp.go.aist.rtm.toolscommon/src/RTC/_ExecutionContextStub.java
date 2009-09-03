package RTC;


/**
* RTC/_ExecutionContextStub.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分44秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief ExecutionContext
   *
   * @section Description
   *
   * An ExecutionContext allows the business logic of an RTC to be
   * decoupled from the thread of control in which it is executed. The
   * context represents a logical thread of control and is provided to
   * RTCs at runtime as an argument to various operations, allowing
   * them to query and modify their own state, and that of other RTCs
   * executing within the same context, in the lifecycle.  This
   * separation of concerns is important for two primary reasons:
   *
   * - Large number of components may collaborate tightly within a
   * single node or process. If each component were to run within its
   * own thread of control, the infrastructure may not be able to
   * satisfy the timeliness and determinism requirements of real-time
   * applications due to the large number of threads and the required
   * synchronization between them.
   *
   * - A single application may carry out a number of independent
   * tasks that require different execution rates. For example, it may
   * need to sample a sensor periodically at a very high rate and update a
   * user interface at a much lower rate.
   *
   * @section Semantics
   *
   * The state machine of an ExecutionContext has two parts. The
   * behavior of the ExecutionContext itself is defined by the upper
   * region in the above figure. The behavior of the RTCs that
   * participate in the context is defined by the lower region. The
   * contents of that region are displayed in more detail in Figure
   * 5.5 in Section 5.2.2.2.  Ownership and Participation Each
   * execution context is owned by a single RTC and may be used to
   * execute that RTC and the RTCs contained within it, directly or
   * indirectly. An RTC that owns one or more execution contexts is
   * known as an autonomous RTC.  An autonomous RTC and some subset of
   * the RTCs within it (to be defined by the application developer)
   * shall be executed by the infrastructure according to the contexta?
   * s execution kind, which defines when each RTCa?s operations will
   * be invoked when and in which order. These RTCs are said to
   * participate in the context. The available execution kinds are
   * described in Section 5.2.2.7.  The relationship between RTCs and
   * execution contexts may be many-to-many in the general case:
   * multiple RTCs may be invoked from the same execution context, and
   * a single RTC may be invoked from multiple contexts. In the case
   * where multiple RTCs are invoked from the same context, starting
   * or stopping the context shall result in the corresponding
   * lifecycle transitions for all of those components.
   *
   * @section Logical and Physical Threads
   *
   * Although an execution context represents a logical thread of
   * control, the choice of how it maps to a physical thread shall be
   * left to the applicationa?s deployment
   * environment. Implementations may elect to associate contexts with
   * threads with a one-to-one mapping, to serve multiple contexts
   * from a single thread, or by any other means. In the case where a
   * given RTC may be invoked from multiple contexts, concurrency
   * management is implementation-dependent.
   *
   * @endif
   */
public class _ExecutionContextStub extends org.omg.CORBA.portable.ObjectImpl implements RTC.ExecutionContext
{


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
  public boolean is_running ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("is_running", true);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return is_running (        );
            } finally {
                _releaseReply ($in);
            }
  } // is_running


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
  public RTC.ReturnCode_t start ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("start", true);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return start (        );
            } finally {
                _releaseReply ($in);
            }
  } // start


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
  public RTC.ReturnCode_t stop ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("stop", true);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return stop (        );
            } finally {
                _releaseReply ($in);
            }
  } // stop


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
  public double get_rate ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_rate", true);
                $in = _invoke ($out);
                double $result = $in.read_double ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_rate (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_rate


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
  public RTC.ReturnCode_t set_rate (double rate)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("set_rate", true);
                $out.write_double (rate);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return set_rate (rate        );
            } finally {
                _releaseReply ($in);
            }
  } // set_rate


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
  public RTC.ReturnCode_t add_component (RTC.LightweightRTObject comp)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("add_component", true);
                RTC.LightweightRTObjectHelper.write ($out, comp);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return add_component (comp        );
            } finally {
                _releaseReply ($in);
            }
  } // add_component


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
  public RTC.ReturnCode_t remove_component (RTC.LightweightRTObject comp)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("remove_component", true);
                RTC.LightweightRTObjectHelper.write ($out, comp);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return remove_component (comp        );
            } finally {
                _releaseReply ($in);
            }
  } // remove_component


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
  public RTC.ReturnCode_t activate_component (RTC.LightweightRTObject comp)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("activate_component", true);
                RTC.LightweightRTObjectHelper.write ($out, comp);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return activate_component (comp        );
            } finally {
                _releaseReply ($in);
            }
  } // activate_component


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
  public RTC.ReturnCode_t deactivate_component (RTC.LightweightRTObject comp)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("deactivate_component", true);
                RTC.LightweightRTObjectHelper.write ($out, comp);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return deactivate_component (comp        );
            } finally {
                _releaseReply ($in);
            }
  } // deactivate_component


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
  public RTC.ReturnCode_t reset_component (RTC.LightweightRTObject comp)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("reset_component", true);
                RTC.LightweightRTObjectHelper.write ($out, comp);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return reset_component (comp        );
            } finally {
                _releaseReply ($in);
            }
  } // reset_component


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
  public RTC.LifeCycleState get_component_state (RTC.LightweightRTObject comp)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_component_state", true);
                RTC.LightweightRTObjectHelper.write ($out, comp);
                $in = _invoke ($out);
                RTC.LifeCycleState $result = RTC.LifeCycleStateHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_component_state (comp        );
            } finally {
                _releaseReply ($in);
            }
  } // get_component_state


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
  public RTC.ExecutionKind get_kind ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_kind", true);
                $in = _invoke ($out);
                RTC.ExecutionKind $result = RTC.ExecutionKindHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_kind (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_kind

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:omg.org/RTC/ExecutionContext:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.Object obj = org.omg.CORBA.ORB.init (args, props).string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     String str = org.omg.CORBA.ORB.init (args, props).object_to_string (this);
     s.writeUTF (str);
  }
} // class _ExecutionContextStub
