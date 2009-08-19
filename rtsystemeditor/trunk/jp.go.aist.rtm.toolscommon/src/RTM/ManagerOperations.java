package RTM;


/**
* RTM/ManagerOperations.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/Manager.idl
* 2008年12月5日 (金曜日) 12時20分51秒 JST
*/

public interface ManagerOperations 
{

  // module ￠#
  RTC.ReturnCode_t load_module (String pathname, String initfunc);
  RTC.ReturnCode_t unload_module (String pathname);
  RTM.ModuleProfile[] get_loadable_modules ();
  RTM.ModuleProfile[] get_loaded_modules ();

  // component ￠#
  RTM.ModuleProfile[] get_factory_profiles ();
  RTC.RTObject create_component (String module_name);
  RTC.ReturnCode_t delete_component (String instance_name);
  RTC.RTObject[] get_components ();
  RTC.ComponentProfile[] get_component_profiles ();

  // manager ?,
  RTM.ManagerProfile get_profile ();
  _SDOPackage.NameValue[] get_configuration ();
  RTC.ReturnCode_t set_configuration (String name, String value);
  RTM.Manager get_owner ();
  RTM.Manager set_owner (RTM.Manager mgr);
  RTM.Manager get_child ();
  RTM.Manager set_child (RTM.Manager mgr);
  RTC.ReturnCode_t fork ();
  RTC.ReturnCode_t shutdown ();
  RTC.ReturnCode_t restart ();
  org.omg.CORBA.Object get_service (String name);
} // interface ManagerOperations
