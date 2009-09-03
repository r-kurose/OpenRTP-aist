package RTM;


/**
* RTM/_ManagerStub.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/Manager.idl
* 2008年12月5日 (金曜日) 12時20分51秒 JST
*/

public class _ManagerStub extends org.omg.CORBA.portable.ObjectImpl implements RTM.Manager
{


  // module ￠#
  public RTC.ReturnCode_t load_module (String pathname, String initfunc)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("load_module", true);
                $out.write_string (pathname);
                $out.write_string (initfunc);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return load_module (pathname, initfunc        );
            } finally {
                _releaseReply ($in);
            }
  } // load_module

  public RTC.ReturnCode_t unload_module (String pathname)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("unload_module", true);
                $out.write_string (pathname);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return unload_module (pathname        );
            } finally {
                _releaseReply ($in);
            }
  } // unload_module

  public RTM.ModuleProfile[] get_loadable_modules ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_loadable_modules", true);
                $in = _invoke ($out);
                RTM.ModuleProfile $result[] = RTM.ModuleProfileListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_loadable_modules (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_loadable_modules

  public RTM.ModuleProfile[] get_loaded_modules ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_loaded_modules", true);
                $in = _invoke ($out);
                RTM.ModuleProfile $result[] = RTM.ModuleProfileListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_loaded_modules (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_loaded_modules


  // component ￠#
  public RTM.ModuleProfile[] get_factory_profiles ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_factory_profiles", true);
                $in = _invoke ($out);
                RTM.ModuleProfile $result[] = RTM.ModuleProfileListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_factory_profiles (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_factory_profiles

  public RTC.RTObject create_component (String module_name)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("create_component", true);
                $out.write_string (module_name);
                $in = _invoke ($out);
                RTC.RTObject $result = RTC.RTObjectHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return create_component (module_name        );
            } finally {
                _releaseReply ($in);
            }
  } // create_component

  public RTC.ReturnCode_t delete_component (String instance_name)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("delete_component", true);
                $out.write_string (instance_name);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return delete_component (instance_name        );
            } finally {
                _releaseReply ($in);
            }
  } // delete_component

  public RTC.RTObject[] get_components ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_components", true);
                $in = _invoke ($out);
                RTC.RTObject $result[] = RTC.RTCListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_components (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_components

  public RTC.ComponentProfile[] get_component_profiles ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_component_profiles", true);
                $in = _invoke ($out);
                RTC.ComponentProfile $result[] = RTC.ComponentProfileListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_component_profiles (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_component_profiles


  // manager ?,
  public RTM.ManagerProfile get_profile ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_profile", true);
                $in = _invoke ($out);
                RTM.ManagerProfile $result = RTM.ManagerProfileHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_profile (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_profile

  public _SDOPackage.NameValue[] get_configuration ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_configuration", true);
                $in = _invoke ($out);
                _SDOPackage.NameValue $result[] = RTM.NVListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_configuration (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_configuration

  public RTC.ReturnCode_t set_configuration (String name, String value)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("set_configuration", true);
                $out.write_string (name);
                $out.write_string (value);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return set_configuration (name, value        );
            } finally {
                _releaseReply ($in);
            }
  } // set_configuration

  public RTM.Manager get_owner ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_owner", true);
                $in = _invoke ($out);
                RTM.Manager $result = RTM.ManagerHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_owner (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_owner

  public RTM.Manager set_owner (RTM.Manager mgr)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("set_owner", true);
                RTM.ManagerHelper.write ($out, mgr);
                $in = _invoke ($out);
                RTM.Manager $result = RTM.ManagerHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return set_owner (mgr        );
            } finally {
                _releaseReply ($in);
            }
  } // set_owner

  public RTM.Manager get_child ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_child", true);
                $in = _invoke ($out);
                RTM.Manager $result = RTM.ManagerHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_child (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_child

  public RTM.Manager set_child (RTM.Manager mgr)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("set_child", true);
                RTM.ManagerHelper.write ($out, mgr);
                $in = _invoke ($out);
                RTM.Manager $result = RTM.ManagerHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return set_child (mgr        );
            } finally {
                _releaseReply ($in);
            }
  } // set_child

  public RTC.ReturnCode_t fork ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("fork", true);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return fork (        );
            } finally {
                _releaseReply ($in);
            }
  } // fork

  public RTC.ReturnCode_t shutdown ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("shutdown", true);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return shutdown (        );
            } finally {
                _releaseReply ($in);
            }
  } // shutdown

  public RTC.ReturnCode_t restart ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("restart", true);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return restart (        );
            } finally {
                _releaseReply ($in);
            }
  } // restart

  public org.omg.CORBA.Object get_service (String name)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_service", true);
                $out.write_string (name);
                $in = _invoke ($out);
                org.omg.CORBA.Object $result = org.omg.CORBA.ObjectHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_service (name        );
            } finally {
                _releaseReply ($in);
            }
  } // get_service

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:RTM/Manager:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.Object obj = org.omg.CORBA.ORB.init (args, props).string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     String str = org.omg.CORBA.ORB.init (args, props).object_to_string (this);
     s.writeUTF (str);
  }
} // class _ManagerStub
