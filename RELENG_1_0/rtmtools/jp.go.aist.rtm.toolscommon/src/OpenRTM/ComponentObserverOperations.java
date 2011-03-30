package OpenRTM;


/**
* OpenRTM/ComponentObserverOperations.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: ./OpenRTM.idl
* 2011年2月16日 17時08分39秒 JST
*/

public interface ComponentObserverOperations  extends _SDOPackage.SDOServiceOperations
{
  void update_status (OpenRTM.StatusKind status_kind, String hint);
} // interface ComponentObserverOperations
