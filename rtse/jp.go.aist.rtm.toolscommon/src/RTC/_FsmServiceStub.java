package RTC;


/**
* RTC/_FsmServiceStub.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分45秒 JST
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
public class _FsmServiceStub extends org.omg.CORBA.portable.ObjectImpl implements RTC.FsmService
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
  public RTC.FsmProfile get_fsm_profile ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_fsm_profile", true);
                $in = _invoke ($out);
                RTC.FsmProfile $result = RTC.FsmProfileHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_fsm_profile (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_fsm_profile


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
  public RTC.ReturnCode_t set_fsm_profile (RTC.FsmProfile fsm_profile)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("set_fsm_profile", true);
                RTC.FsmProfileHelper.write ($out, fsm_profile);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return set_fsm_profile (fsm_profile        );
            } finally {
                _releaseReply ($in);
            }
  } // set_fsm_profile

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:omg.org/RTC/FsmService:1.0", 
    "IDL:org.omg/SDOPackage/SDOService:1.0"};

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
} // class _FsmServiceStub
