package RTC;


/**
* RTC/_ModeCapableStub.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分45秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief ModeCapable
   *
   * @section Description
   *
   * The ModeCapable interface provides access to an objecta?s modes
   * and a means to set the current mode.
   *
   * @section Semantics
   *
   * A given RTC may support multiple modes as well as multiple
   * execution contexts. In such a case, a request for a mode change
   * (e.g., from "cruise control on" to "cruise control off") may
   * come asynchronously with respect to one or more of those
   * execution contexts. The mode of an RTC may therefore be observed
   * to be different from one execution context to another.  - A mode
   * is pending in a given execution context when a mode change has
   * been requested but the new mode has not yet been observed by that
   * context.
   *
   * - The new mode has been committed in a given execution context
   *   when the context finally observes the new mode.
   *
   * - The new mode has stabilized once it has been committed in all
   *   execution contexts in which the RTC participates.
   *
   * Figure 5.26 depicts a state machine that describes mode
   * changes. Each parallel region in the composite state Mode Pending
   * represents an execution context. The trigger "sample" within
   * that state is considered to have occurred: - a?just before the
   * next call to on_execute (see Section 5.3.1.2.1) in the case where
   * immediate is false and the execution kind is PERIODIC, a?
   *
   * - a?just before the processing of the next stimulus in the case
   *   where immediate is false and the execution kind is
   *   EVENT_DRIVEN, or a?- a?immediately in all other cases.
   *
   * @endif
   */
public class _ModeCapableStub extends org.omg.CORBA.portable.ObjectImpl implements RTC.ModeCapable
{


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
  public RTC.Mode get_default_mode ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_default_mode", true);
                $in = _invoke ($out);
                RTC.Mode $result = RTC.ModeHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_default_mode (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_default_mode


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
  public RTC.Mode get_current_mode ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_current_mode", true);
                $in = _invoke ($out);
                RTC.Mode $result = RTC.ModeHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_current_mode (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_current_mode


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
  public RTC.Mode get_current_mode_in_context (RTC.ExecutionContext exec_context)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_current_mode_in_context", true);
                RTC.ExecutionContextHelper.write ($out, exec_context);
                $in = _invoke ($out);
                RTC.Mode $result = RTC.ModeHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_current_mode_in_context (exec_context        );
            } finally {
                _releaseReply ($in);
            }
  } // get_current_mode_in_context


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
  public RTC.Mode get_pending_mode ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_pending_mode", true);
                $in = _invoke ($out);
                RTC.Mode $result = RTC.ModeHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_pending_mode (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_pending_mode


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
  public RTC.Mode get_pending_mode_in_context (RTC.ExecutionContext exec_context)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_pending_mode_in_context", true);
                RTC.ExecutionContextHelper.write ($out, exec_context);
                $in = _invoke ($out);
                RTC.Mode $result = RTC.ModeHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_pending_mode_in_context (exec_context        );
            } finally {
                _releaseReply ($in);
            }
  } // get_pending_mode_in_context


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
  public RTC.ReturnCode_t set_mode (RTC.Mode new_mode, boolean immediate)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("set_mode", true);
                RTC.ModeHelper.write ($out, new_mode);
                $out.write_boolean (immediate);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return set_mode (new_mode, immediate        );
            } finally {
                _releaseReply ($in);
            }
  } // set_mode

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:omg.org/RTC/ModeCapable:1.0"};

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
} // class _ModeCapableStub
