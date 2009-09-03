package RTC;


/**
* RTC/FsmServicePOA.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief FsmService
   *
   * @section Description
   *
   * The FsmService interface defines operations necessary for
   * Stimulus Response Processing as an SDO service.
   *
   * @endif
   */
public abstract class FsmServicePOA extends org.omg.PortableServer.Servant
 implements RTC.FsmServiceOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("get_fsm_profile", new java.lang.Integer (0));
    _methods.put ("set_fsm_profile", new java.lang.Integer (1));
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
     * @brief get_fsm_profile
     *
     * @section Description
     *
     * Get the current state of the FSM.
     *
     * @section Semantics
     *
     * Modifications to the object returned by this operation will not
     * be reflected in the FSM until and unless set_fsm_profile is
     * called.
     *
     * @endif
     */
       case 0:  // RTC/FsmService/get_fsm_profile
       {
         RTC.FsmProfile $result = null;
         $result = this.get_fsm_profile ();
         out = $rh.createReply();
         RTC.FsmProfileHelper.write (out, $result);
         break;
       }


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief set_fsm_profile
     *
     * @section Description
     *
     * This operation will be used to modify the behavior of an FSM as
     * described in Stimulus Response Processing.
     *
     * @endif
     */
       case 1:  // RTC/FsmService/set_fsm_profile
       {
         RTC.FsmProfile fsm_profile = RTC.FsmProfileHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.set_fsm_profile (fsm_profile);
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
    "IDL:omg.org/RTC/FsmService:1.0", 
    "IDL:org.omg/SDOPackage/SDOService:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public FsmService _this() 
  {
    return FsmServiceHelper.narrow(
    super._this_object());
  }

  public FsmService _this(org.omg.CORBA.ORB orb) 
  {
    return FsmServiceHelper.narrow(
    super._this_object(orb));
  }


} // class FsmServicePOA
