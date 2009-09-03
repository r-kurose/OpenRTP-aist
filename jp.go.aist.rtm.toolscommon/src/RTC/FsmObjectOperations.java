package RTC;


/**
* RTC/FsmObjectOperations.java .
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
public interface FsmObjectOperations 
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
  RTC.ReturnCode_t send_stimulus (String message, int exec_handle);
} // interface FsmObjectOperations
