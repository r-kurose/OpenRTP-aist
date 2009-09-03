package _SDOPackage;


/**
* _SDOPackage/OrganizationOperations.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public interface OrganizationOperations 
{
  String get_organization_id () throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  _SDOPackage.OrganizationProperty get_organization_property () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  org.omg.CORBA.Any get_organization_property_value (String name) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  boolean add_organization_property (_SDOPackage.OrganizationProperty organization_property) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  boolean set_organization_property_value (String name, org.omg.CORBA.Any value) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  boolean remove_organization_property (String name) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  _SDOPackage.SDOSystemElement get_owner () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  boolean set_owner (_SDOPackage.SDOSystemElement sdo) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  _SDOPackage.SDO[] get_members () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  boolean set_members (_SDOPackage.SDO[] sdos) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  boolean add_members (_SDOPackage.SDO[] sdo_list) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  boolean remove_member (String id) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  _SDOPackage.DependencyType get_dependency () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError;
  boolean set_dependency (_SDOPackage.DependencyType dependency) throws _SDOPackage.NotAvailable, _SDOPackage.InternalError;
} // interface OrganizationOperations
