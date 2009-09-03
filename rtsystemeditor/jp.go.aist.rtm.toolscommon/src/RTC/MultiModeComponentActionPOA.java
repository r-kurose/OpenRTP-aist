package RTC;


/**
* RTC/MultiModeComponentActionPOA.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分46秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief MultiModeComponentAction
   *
   * MultiModeComponentAction is a companion to ComponentAction that
   is realized by RTCs that support multiple modes.
   *
   * @endif
   */
public abstract class MultiModeComponentActionPOA extends org.omg.PortableServer.Servant
 implements RTC.MultiModeComponentActionOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("on_mode_changed", new java.lang.Integer (0));
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
       case 0:  // RTC/MultiModeComponentAction/on_mode_changed
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
    "IDL:omg.org/RTC/MultiModeComponentAction:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public MultiModeComponentAction _this() 
  {
    return MultiModeComponentActionHelper.narrow(
    super._this_object());
  }

  public MultiModeComponentAction _this(org.omg.CORBA.ORB orb) 
  {
    return MultiModeComponentActionHelper.narrow(
    super._this_object(orb));
  }


} // class MultiModeComponentActionPOA
