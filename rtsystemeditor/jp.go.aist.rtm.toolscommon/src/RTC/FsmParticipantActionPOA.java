package RTC;


/**
* RTC/FsmParticipantActionPOA.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分46秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief FsmParticipantAction
   *
   * @section Description
   *
   * FsmParticipantAction is companion to ComponentAction (see Section
   * 5.2.2.4) that is intended for use with FSM participant RTCs. It
   * adds a callback for the interception of state transitions, state
   * entries, and state exits.
   *
   * @endif
   */
public abstract class FsmParticipantActionPOA extends org.omg.PortableServer.Servant
 implements RTC.FsmParticipantActionOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("on_action", new java.lang.Integer (0));
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
     * @brief on_action
     *
     * @section Description
     *
     * The indicated FSM participant RTC has been invoked as a result
     * of a transition, state entry, or state exit in its containing
     * FSM.
     *
     * @section Constraints
     *
     * - The given execution context shall be of kind EVENT_DRIVEN.
        *
     * @endif
     */
       case 0:  // RTC/FsmParticipantAction/on_action
       {
         int exec_handle = RTC.ExecutionContextHandle_tHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.on_action (exec_handle);
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
    "IDL:omg.org/RTC/FsmParticipantAction:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public FsmParticipantAction _this() 
  {
    return FsmParticipantActionHelper.narrow(
    super._this_object());
  }

  public FsmParticipantAction _this(org.omg.CORBA.ORB orb) 
  {
    return FsmParticipantActionHelper.narrow(
    super._this_object(orb));
  }


} // class FsmParticipantActionPOA
