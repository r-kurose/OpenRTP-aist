package RTC;


/**
* RTC/FsmOperations.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分46秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief fsm
   *
   * @section Description
   *
   * Applying the fsm stereotype to a component implies the ability to
   * define component-specific states and transitions.
   *
   * @section Semantics
   *
   * In creating a state machine such as is depicted in Figure 5.22,
   * the RTC developer is implicitly defining the Active state to be a
   * submachine state.  * The BehaviorStateMachines package described
   * in [UML] is considered the normative definition of a state
   * machine.
   *
   * @endif
   */
public interface FsmOperations  extends RTC.LightweightRTObjectOperations
{
} // interface FsmOperations
