package RTC;


/**
* RTC/ModeCapablePOA.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分46秒 JST
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
public abstract class ModeCapablePOA extends org.omg.PortableServer.Servant
 implements RTC.ModeCapableOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("get_default_mode", new java.lang.Integer (0));
    _methods.put ("get_current_mode", new java.lang.Integer (1));
    _methods.put ("get_current_mode_in_context", new java.lang.Integer (2));
    _methods.put ("get_pending_mode", new java.lang.Integer (3));
    _methods.put ("get_pending_mode_in_context", new java.lang.Integer (4));
    _methods.put ("set_mode", new java.lang.Integer (5));
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
       case 0:  // RTC/ModeCapable/get_default_mode
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
       case 1:  // RTC/ModeCapable/get_current_mode
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
       case 2:  // RTC/ModeCapable/get_current_mode_in_context
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
       case 3:  // RTC/ModeCapable/get_pending_mode
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
       case 4:  // RTC/ModeCapable/get_pending_mode_in_context
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
       case 5:  // RTC/ModeCapable/set_mode
       {
         RTC.Mode new_mode = RTC.ModeHelper.read (in);
         boolean immediate = in.read_boolean ();
         RTC.ReturnCode_t $result = null;
         $result = this.set_mode (new_mode, immediate);
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
    "IDL:omg.org/RTC/ModeCapable:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ModeCapable _this() 
  {
    return ModeCapableHelper.narrow(
    super._this_object());
  }

  public ModeCapable _this(org.omg.CORBA.ORB orb) 
  {
    return ModeCapableHelper.narrow(
    super._this_object(orb));
  }


} // class ModeCapablePOA
