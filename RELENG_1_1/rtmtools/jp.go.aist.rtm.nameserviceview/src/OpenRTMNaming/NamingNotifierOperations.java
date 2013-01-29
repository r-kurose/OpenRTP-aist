package OpenRTMNaming;


/**
* OpenRTMNaming/NamingNotifierOperations.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: ./OpenRTMNaming.idl
* 2011年2月10日 18時15分14秒 JST
*/

public interface NamingNotifierOperations 
{
  void list_all (OpenRTMNaming.TreeBindingListHolder tbl);
  boolean subscribe (OpenRTMNaming.ObserverProfile oprof);
  boolean unsubscribe (String id);
} // interface NamingNotifierOperations
