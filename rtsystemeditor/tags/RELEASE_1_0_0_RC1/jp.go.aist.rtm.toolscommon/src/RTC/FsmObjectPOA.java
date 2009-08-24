package RTC;


/**
* RTC/FsmObjectPOA.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief FsmObject
   *
   * @section Description
   *
   * The FsmObject interface allows programs to send stimuli to a
   * finite state machine, possibly causing it to change states.
   *
   * @endif
   */
public abstract class FsmObjectPOA extends org.omg.PortableServer.Servant
 implements RTC.FsmObjectOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("send_stimulus", new java.lang.Integer (0));
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
     * @brief send_stimulus
     *
     * @section Description
     *
     * Send a stimulus to an FSM that realizes this interface.
     *
     * @section Semantics
     *
     * If the stimulus corresponds to any outgoing transition of the
     * current state, that transition shall be taken and the state
     * shall change. Any FSM participants associated with the exit of
     * the current state, the transition to the new state, or the
     * entry to the new state shall be invoked. If the stimulus does
     * not correspond to any such transition, this operation shall
     * succeed but have no effect.  
     *
     * If the given execution context is a non-nil reference to a
     * context in which this FSM participates, the transition shall be
     * executed in that context. If the argument is nil, the FSM shall
     * choose an EVENT_DRIVEN context in which to execute the
     * transition. If the argument is non-nil, but this FSM does not
     * participate in the given context, this operation shall fail
     * with * ReturnCode_t::BAD_PARAMETER.
     *
     * @section Constraints
     *
     * - The given execution context shall be of kind EVENT_DRIVEN.
     *
     * @endif
     */
       case 0:  // RTC/FsmObject/send_stimulus
       {
         String message = in.read_string ();
         int exec_handle = RTC.ExecutionContextHandle_tHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.send_stimulus (message, exec_handle);
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
    "IDL:omg.org/RTC/FsmObject:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public FsmObject _this() 
  {
    return FsmObjectHelper.narrow(
    super._this_object());
  }

  public FsmObject _this(org.omg.CORBA.ORB orb) 
  {
    return FsmObjectHelper.narrow(
    super._this_object(orb));
  }


} // class FsmObjectPOA
