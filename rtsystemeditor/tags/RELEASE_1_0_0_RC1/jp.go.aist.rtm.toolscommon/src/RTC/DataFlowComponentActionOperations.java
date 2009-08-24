package RTC;


/**
* RTC/DataFlowComponentActionOperations.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分46秒 JST
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
public interface DataFlowComponentActionOperations 
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
  RTC.ReturnCode_t on_execute (int exec_handle);

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
  RTC.ReturnCode_t on_state_update (int exec_handle);

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
  RTC.ReturnCode_t on_rate_changed (int exec_handle);
} // interface DataFlowComponentActionOperations
