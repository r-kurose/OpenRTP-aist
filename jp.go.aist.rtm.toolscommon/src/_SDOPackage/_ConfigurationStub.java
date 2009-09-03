package _SDOPackage;


/**
* _SDOPackage/_ConfigurationStub.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分39秒 JST
*/

public class _ConfigurationStub extends org.omg.CORBA.portable.ObjectImpl implements _SDOPackage.Configuration
{

  public boolean set_device_profile (_SDOPackage.DeviceProfile dProfile) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("set_device_profile", true);
                _SDOPackage.DeviceProfileHelper.write ($out, dProfile);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:org.omg/_SDOPackage/InvalidParameter:1.0"))
                    throw _SDOPackage.InvalidParameterHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/NotAvailable:1.0"))
                    throw _SDOPackage.NotAvailableHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/InternalError:1.0"))
                    throw _SDOPackage.InternalErrorHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return set_device_profile (dProfile        );
            } finally {
                _releaseReply ($in);
            }
  } // set_device_profile

  public boolean add_service_profile (_SDOPackage.ServiceProfile sProfile) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("add_service_profile", true);
                _SDOPackage.ServiceProfileHelper.write ($out, sProfile);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:org.omg/_SDOPackage/InvalidParameter:1.0"))
                    throw _SDOPackage.InvalidParameterHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/NotAvailable:1.0"))
                    throw _SDOPackage.NotAvailableHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/InternalError:1.0"))
                    throw _SDOPackage.InternalErrorHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return add_service_profile (sProfile        );
            } finally {
                _releaseReply ($in);
            }
  } // add_service_profile

  public boolean add_organization (_SDOPackage.Organization organization_object) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("add_organization", true);
                _SDOPackage.OrganizationHelper.write ($out, organization_object);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:org.omg/_SDOPackage/InvalidParameter:1.0"))
                    throw _SDOPackage.InvalidParameterHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/NotAvailable:1.0"))
                    throw _SDOPackage.NotAvailableHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/InternalError:1.0"))
                    throw _SDOPackage.InternalErrorHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return add_organization (organization_object        );
            } finally {
                _releaseReply ($in);
            }
  } // add_organization

  public boolean remove_service_profile (String id) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("remove_service_profile", true);
                _SDOPackage.UniqueIdentifierHelper.write ($out, id);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:org.omg/_SDOPackage/InvalidParameter:1.0"))
                    throw _SDOPackage.InvalidParameterHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/NotAvailable:1.0"))
                    throw _SDOPackage.NotAvailableHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/InternalError:1.0"))
                    throw _SDOPackage.InternalErrorHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return remove_service_profile (id        );
            } finally {
                _releaseReply ($in);
            }
  } // remove_service_profile

  public boolean remove_organization (String organization_id) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("remove_organization", true);
                _SDOPackage.UniqueIdentifierHelper.write ($out, organization_id);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:org.omg/_SDOPackage/InvalidParameter:1.0"))
                    throw _SDOPackage.InvalidParameterHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/NotAvailable:1.0"))
                    throw _SDOPackage.NotAvailableHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/InternalError:1.0"))
                    throw _SDOPackage.InternalErrorHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return remove_organization (organization_id        );
            } finally {
                _releaseReply ($in);
            }
  } // remove_organization

  public _SDOPackage.Parameter[] get_configuration_parameters () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_configuration_parameters", true);
                $in = _invoke ($out);
                _SDOPackage.Parameter $result[] = _SDOPackage.ParameterListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:org.omg/_SDOPackage/NotAvailable:1.0"))
                    throw _SDOPackage.NotAvailableHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/InternalError:1.0"))
                    throw _SDOPackage.InternalErrorHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_configuration_parameters (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_configuration_parameters

  public _SDOPackage.NameValue[] get_configuration_parameter_values () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_configuration_parameter_values", true);
                $in = _invoke ($out);
                _SDOPackage.NameValue $result[] = _SDOPackage.NVListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:org.omg/_SDOPackage/NotAvailable:1.0"))
                    throw _SDOPackage.NotAvailableHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/InternalError:1.0"))
                    throw _SDOPackage.InternalErrorHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_configuration_parameter_values (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_configuration_parameter_values

  public org.omg.CORBA.Any get_configuration_parameter_value (String name) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_configuration_parameter_value", true);
                $out.write_string (name);
                $in = _invoke ($out);
                org.omg.CORBA.Any $result = $in.read_any ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:org.omg/_SDOPackage/InvalidParameter:1.0"))
                    throw _SDOPackage.InvalidParameterHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/NotAvailable:1.0"))
                    throw _SDOPackage.NotAvailableHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/InternalError:1.0"))
                    throw _SDOPackage.InternalErrorHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_configuration_parameter_value (name        );
            } finally {
                _releaseReply ($in);
            }
  } // get_configuration_parameter_value

  public boolean set_configuration_parameter (String name, org.omg.CORBA.Any value) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("set_configuration_parameter", true);
                $out.write_string (name);
                $out.write_any (value);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:org.omg/_SDOPackage/InvalidParameter:1.0"))
                    throw _SDOPackage.InvalidParameterHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/NotAvailable:1.0"))
                    throw _SDOPackage.NotAvailableHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/InternalError:1.0"))
                    throw _SDOPackage.InternalErrorHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return set_configuration_parameter (name, value        );
            } finally {
                _releaseReply ($in);
            }
  } // set_configuration_parameter

  public _SDOPackage.ConfigurationSet[] get_configuration_sets () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_configuration_sets", true);
                $in = _invoke ($out);
                _SDOPackage.ConfigurationSet $result[] = _SDOPackage.ConfigurationSetListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:org.omg/_SDOPackage/NotAvailable:1.0"))
                    throw _SDOPackage.NotAvailableHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/InternalError:1.0"))
                    throw _SDOPackage.InternalErrorHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_configuration_sets (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_configuration_sets

  public _SDOPackage.ConfigurationSet get_configuration_set (String config_id) throws _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_configuration_set", true);
                _SDOPackage.UniqueIdentifierHelper.write ($out, config_id);
                $in = _invoke ($out);
                _SDOPackage.ConfigurationSet $result = _SDOPackage.ConfigurationSetHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:org.omg/_SDOPackage/NotAvailable:1.0"))
                    throw _SDOPackage.NotAvailableHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/InternalError:1.0"))
                    throw _SDOPackage.InternalErrorHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_configuration_set (config_id        );
            } finally {
                _releaseReply ($in);
            }
  } // get_configuration_set

  public boolean set_configuration_set_values (_SDOPackage.ConfigurationSet configuration_set) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("set_configuration_set_values", true);
                _SDOPackage.ConfigurationSetHelper.write ($out, configuration_set);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:org.omg/_SDOPackage/InvalidParameter:1.0"))
                    throw _SDOPackage.InvalidParameterHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/NotAvailable:1.0"))
                    throw _SDOPackage.NotAvailableHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/InternalError:1.0"))
                    throw _SDOPackage.InternalErrorHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return set_configuration_set_values (configuration_set        );
            } finally {
                _releaseReply ($in);
            }
  } // set_configuration_set_values

  public _SDOPackage.ConfigurationSet get_active_configuration_set () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_active_configuration_set", true);
                $in = _invoke ($out);
                _SDOPackage.ConfigurationSet $result = _SDOPackage.ConfigurationSetHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:org.omg/_SDOPackage/NotAvailable:1.0"))
                    throw _SDOPackage.NotAvailableHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/InternalError:1.0"))
                    throw _SDOPackage.InternalErrorHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_active_configuration_set (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_active_configuration_set

  public boolean add_configuration_set (_SDOPackage.ConfigurationSet configuration_set) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("add_configuration_set", true);
                _SDOPackage.ConfigurationSetHelper.write ($out, configuration_set);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:org.omg/_SDOPackage/InvalidParameter:1.0"))
                    throw _SDOPackage.InvalidParameterHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/NotAvailable:1.0"))
                    throw _SDOPackage.NotAvailableHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/InternalError:1.0"))
                    throw _SDOPackage.InternalErrorHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return add_configuration_set (configuration_set        );
            } finally {
                _releaseReply ($in);
            }
  } // add_configuration_set

  public boolean remove_configuration_set (String config_id) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("remove_configuration_set", true);
                _SDOPackage.UniqueIdentifierHelper.write ($out, config_id);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:org.omg/_SDOPackage/InvalidParameter:1.0"))
                    throw _SDOPackage.InvalidParameterHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/NotAvailable:1.0"))
                    throw _SDOPackage.NotAvailableHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/InternalError:1.0"))
                    throw _SDOPackage.InternalErrorHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return remove_configuration_set (config_id        );
            } finally {
                _releaseReply ($in);
            }
  } // remove_configuration_set

  public boolean activate_configuration_set (String config_id) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("activate_configuration_set", true);
                _SDOPackage.UniqueIdentifierHelper.write ($out, config_id);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:org.omg/_SDOPackage/InvalidParameter:1.0"))
                    throw _SDOPackage.InvalidParameterHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/NotAvailable:1.0"))
                    throw _SDOPackage.NotAvailableHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/InternalError:1.0"))
                    throw _SDOPackage.InternalErrorHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return activate_configuration_set (config_id        );
            } finally {
                _releaseReply ($in);
            }
  } // activate_configuration_set

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:org.omg/SDOPackage/Configuration:1.0"};

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
} // class _ConfigurationStub
