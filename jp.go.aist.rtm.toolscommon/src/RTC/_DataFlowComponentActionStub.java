package RTC;


/**
* RTC/_DataFlowComponentActionStub.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分44秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief DataFlowComponentAction
   *
   * @section Description
   *
   * DataFlowComponentAction is a companion to ComponentAction (see
   * Section 5.2.2.4) that provides additional callbacks for
   * intercepting the two execution passes defined in Section
   * 5.3.1.1.2.
   *
   * @endif
   */
public class _DataFlowComponentActionStub extends org.omg.CORBA.portable.ObjectImpl implements RTC.DataFlowComponentAction
{


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief on_execute
     * @section Description
     *
     * This operation will be invoked periodically at the rate of the
     * given execution context as long as the following conditions
     * hold:
     *
     * - The RTC is Active.
     *
     * - The given execution context is Running.
     *
     * @section Semantics
     *
     This callback occurs during the first execution pass.
     *
     * @section Constraints
     *
     * - The execution context of the given context shall be PERIODIC.
     *
     * @endif
     */
  public RTC.ReturnCode_t on_execute (int exec_handle)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("on_execute", true);
                RTC.ExecutionContextHandle_tHelper.write ($out, exec_handle);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return on_execute (exec_handle        );
            } finally {
                _releaseReply ($in);
            }
  } // on_execute


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief on_state_update
     *
     * @section Description
     *
     * This operation will be invoked periodically at the rate of the
     * given execution context as long as the following conditions hold:
     *
     * - The RTC is Active.
     *
     * - The given execution context is Running.
     *
     * @section Semantics
     *
     * This callback occurs during the second execution pass.
     *
     * @section Constraints
     *
     * - The execution context of the given context shall be PERIODIC.
     *
     *
     * @endif
     */
  public RTC.ReturnCode_t on_state_update (int exec_handle)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("on_state_update", true);
                RTC.ExecutionContextHandle_tHelper.write ($out, exec_handle);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return on_state_update (exec_handle        );
            } finally {
                _releaseReply ($in);
            }
  } // on_state_update


  /*!
     * @if jp
     * @brief 
     * @else
     * @brief on_rate_changed
     *
     * @section Description
     *
     * This operation is a notification that the rate of the indicated
     * execution context (see Section 5.2.2.6.4) has changed.
     *
     * @section Constraints
     *
     * - The execution context of the given context shall be PERIODIC.
     *
     *
     * @endif
     */
  public RTC.ReturnCode_t on_rate_changed (int exec_handle)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("on_rate_changed", true);
                RTC.ExecutionContextHandle_tHelper.write ($out, exec_handle);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return on_rate_changed (exec_handle        );
            } finally {
                _releaseReply ($in);
            }
  } // on_rate_changed

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:omg.org/RTC/DataFlowComponentAction:1.0"};

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
} // class _DataFlowComponentActionStub
