package RTC;


/**
* RTC/FsmProfile.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief FsmProfile
   *
   * @section Description
   *
   * The FsmProfile describes the correspondence between an FSM and
   * its contained FSM participants. This Profile is necessary for
   * Stimulus Response Processing.
   *
   * @endif
   */
public final class FsmProfile implements org.omg.CORBA.portable.IDLEntity
{

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief behavior_profiles
     *
     * @section Description
     *
     * This attribute lists the correspondences between an FSM and its
     * contained FSM participants.
     *
     * @endif
     */
  public RTC.FsmBehaviorProfile behavior_profiles[] = null;

  public FsmProfile ()
  {
  } // ctor

  public FsmProfile (RTC.FsmBehaviorProfile[] _behavior_profiles)
  {
    behavior_profiles = _behavior_profiles;
  } // ctor

} // class FsmProfile
