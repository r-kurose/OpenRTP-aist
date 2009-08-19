package _SDOPackage;


/**
* _SDOPackage/SDOOperations.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public interface SDOOperations  extends _SDOPackage.SDOSystemElementOperations
{
  String get_sdo_id () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  String get_sdo_type () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  _SDOPackage.DeviceProfile get_device_profile () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  _SDOPackage.ServiceProfile[] get_service_profiles () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  _SDOPackage.ServiceProfile get_service_profile (String id) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  _SDOPackage.SDOService get_sdo_service (String id) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  _SDOPackage.Configuration get_configuration () throws _SDOPackage.InterfaceNotImplemented, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  _SDOPackage.Monitoring get_monitoring () throws _SDOPackage.InterfaceNotImplemented, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  _SDOPackage.Organization[] get_organizations () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  _SDOPackage.NameValue[] get_status_list () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  org.omg.CORBA.Any get_status (String name) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
} // interface SDOOperations
