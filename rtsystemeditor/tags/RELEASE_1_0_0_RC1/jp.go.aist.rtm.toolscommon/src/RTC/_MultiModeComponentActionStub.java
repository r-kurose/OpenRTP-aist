package RTC;


/**
* RTC/_MultiModeComponentActionStub.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分45秒 JST
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
public class _MultiModeComponentActionStub extends org.omg.CORBA.portable.ObjectImpl implements RTC.MultiModeComponentAction
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
  public RTC.ReturnCode_t on_mode_changed (int exec_handle)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("on_mode_changed", true);
                RTC.ExecutionContextHandle_tHelper.write ($out, exec_handle);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return on_mode_changed (exec_handle        );
            } finally {
                _releaseReply ($in);
            }
  } // on_mode_changed

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:omg.org/RTC/MultiModeComponentAction:1.0"};

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
} // class _MultiModeComponentActionStub
