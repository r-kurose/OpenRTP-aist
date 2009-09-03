package RTC;


/**
* RTC/ExecutionContextService.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief ExecutionContextService
   *
   * @section Description
   *
   * An ExecutionContextService exposes an ExecutionContext as an SDO
   * service such that the context may be controlled remotely.
   *
   * @section Semantics
   *
   * Depending on the implementation, this interface may itself be an
   * execution context (that is, it may be passed to the operations of
   * ComponentAction) or it may represent a remote execution context
   * that is not of type ExecutionContextService.  @endif
   */
public interface ExecutionContextService extends ExecutionContextServiceOperations, RTC.ExecutionContext, _SDOPackage.SDOService
{
} // interface ExecutionContextService
