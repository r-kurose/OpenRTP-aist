package RTC;


/**
* RTC/_ComponentActionStub.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分44秒 JST
*/


/*!
   * @if jp
   * @brief
   * @else
   * @brief ComponentAction
   *
   * @section Description
   *
   * The ComponentAction interface provides callbacks corresponding to
   * the execution of the lifecycle operations of LightweightRTObject
   * (see Section 5.2.2.2) and ExecutionContext (see Section
   * 5.2.2.5). An RTC developer may implement these callback
   * operations in order to execute application-specific logic
   * pointing response to those transitions.
   *
   * @section Semantics
   *
   * Clients of an RTC are not expected to invoke these operations
   * directly; they are provided for the benefit of the RTC middleware
   * implementation.
   *
   * @endif
   */
public class _ComponentActionStub extends org.omg.CORBA.portable.ObjectImpl implements RTC.ComponentAction
{


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
  public RTC.ReturnCode_t on_initialize ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("on_initialize", true);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return on_initialize (        );
            } finally {
                _releaseReply ($in);
            }
  } // on_initialize


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
  public RTC.ReturnCode_t on_finalize ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("on_finalize", true);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return on_finalize (        );
            } finally {
                _releaseReply ($in);
            }
  } // on_finalize


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
  public RTC.ReturnCode_t on_startup (int exec_handle)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("on_startup", true);
                RTC.ExecutionContextHandle_tHelper.write ($out, exec_handle);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return on_startup (exec_handle        );
            } finally {
                _releaseReply ($in);
            }
  } // on_startup


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
  public RTC.ReturnCode_t on_shutdown (int exec_handle)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("on_shutdown", true);
                RTC.ExecutionContextHandle_tHelper.write ($out, exec_handle);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return on_shutdown (exec_handle        );
            } finally {
                _releaseReply ($in);
            }
  } // on_shutdown


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
  public RTC.ReturnCode_t on_activated (int exec_handle)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("on_activated", true);
                RTC.ExecutionContextHandle_tHelper.write ($out, exec_handle);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return on_activated (exec_handle        );
            } finally {
                _releaseReply ($in);
            }
  } // on_activated


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
  public RTC.ReturnCode_t on_deactivated (int exec_handle)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("on_deactivated", true);
                RTC.ExecutionContextHandle_tHelper.write ($out, exec_handle);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return on_deactivated (exec_handle        );
            } finally {
                _releaseReply ($in);
            }
  } // on_deactivated


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
  public RTC.ReturnCode_t on_aborting (int exec_handle)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("on_aborting", true);
                RTC.ExecutionContextHandle_tHelper.write ($out, exec_handle);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return on_aborting (exec_handle        );
            } finally {
                _releaseReply ($in);
            }
  } // on_aborting


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
  public RTC.ReturnCode_t on_error (int exec_handle)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("on_error", true);
                RTC.ExecutionContextHandle_tHelper.write ($out, exec_handle);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return on_error (exec_handle        );
            } finally {
                _releaseReply ($in);
            }
  } // on_error


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
  public RTC.ReturnCode_t on_reset (int exec_handle)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("on_reset", true);
                RTC.ExecutionContextHandle_tHelper.write ($out, exec_handle);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return on_reset (exec_handle        );
            } finally {
                _releaseReply ($in);
            }
  } // on_reset

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:omg.org/RTC/ComponentAction:1.0"};

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
} // class _ComponentActionStub
