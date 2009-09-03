package _SDOPackage;


/**
* _SDOPackage/SDOSystemElementOperations.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/


/** ------- Interfaces -------*/
public interface SDOSystemElementOperations 
{
  _SDOPackage.Organization[] get_owned_organizations () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError;
} // interface SDOSystemElementOperations
