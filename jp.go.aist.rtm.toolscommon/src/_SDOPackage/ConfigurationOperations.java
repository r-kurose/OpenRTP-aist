package _SDOPackage;


/**
* _SDOPackage/ConfigurationOperations.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public interface ConfigurationOperations 
{
  boolean set_device_profile (_SDOPackage.DeviceProfile dProfile) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  boolean add_service_profile (_SDOPackage.ServiceProfile sProfile) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  boolean add_organization (_SDOPackage.Organization organization_object) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  boolean remove_service_profile (String id) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  boolean remove_organization (String organization_id) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  _SDOPackage.Parameter[] get_configuration_parameters () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  _SDOPackage.NameValue[] get_configuration_parameter_values () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  org.omg.CORBA.Any get_configuration_parameter_value (String name) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  boolean set_configuration_parameter (String name, org.omg.CORBA.Any value) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  _SDOPackage.ConfigurationSet[] get_configuration_sets () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  _SDOPackage.ConfigurationSet get_configuration_set (String config_id) throws _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  boolean set_configuration_set_values (_SDOPackage.ConfigurationSet configuration_set) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  _SDOPackage.ConfigurationSet get_active_configuration_set () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  boolean add_configuration_set (_SDOPackage.ConfigurationSet configuration_set) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  boolean remove_configuration_set (String config_id) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  boolean activate_configuration_set (String config_id) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
} // interface ConfigurationOperations
