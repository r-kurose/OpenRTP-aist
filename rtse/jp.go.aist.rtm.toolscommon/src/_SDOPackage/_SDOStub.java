package _SDOPackage;


/**
* _SDOPackage/_SDOStub.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分39秒 JST
*/

public class _SDOStub extends org.omg.CORBA.portable.ObjectImpl implements _SDOPackage.SDO
{

  public String get_sdo_id () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_sdo_id", true);
                $in = _invoke ($out);
                String $result = _SDOPackage.UniqueIdentifierHelper.read ($in);
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
                return get_sdo_id (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_sdo_id

  public String get_sdo_type () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_sdo_type", true);
                $in = _invoke ($out);
                String $result = $in.read_string ();
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
                return get_sdo_type (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_sdo_type

  public _SDOPackage.DeviceProfile get_device_profile () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_device_profile", true);
                $in = _invoke ($out);
                _SDOPackage.DeviceProfile $result = _SDOPackage.DeviceProfileHelper.read ($in);
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
                return get_device_profile (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_device_profile

  public _SDOPackage.ServiceProfile[] get_service_profiles () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_service_profiles", true);
                $in = _invoke ($out);
                _SDOPackage.ServiceProfile $result[] = _SDOPackage.ServiceProfileListHelper.read ($in);
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
                return get_service_profiles (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_service_profiles

  public _SDOPackage.ServiceProfile get_service_profile (String id) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_service_profile", true);
                _SDOPackage.UniqueIdentifierHelper.write ($out, id);
                $in = _invoke ($out);
                _SDOPackage.ServiceProfile $result = _SDOPackage.ServiceProfileHelper.read ($in);
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
                return get_service_profile (id        );
            } finally {
                _releaseReply ($in);
            }
  } // get_service_profile

  public _SDOPackage.SDOService get_sdo_service (String id) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_sdo_service", true);
                _SDOPackage.UniqueIdentifierHelper.write ($out, id);
                $in = _invoke ($out);
                _SDOPackage.SDOService $result = _SDOPackage.SDOServiceHelper.read ($in);
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
                return get_sdo_service (id        );
            } finally {
                _releaseReply ($in);
            }
  } // get_sdo_service

  public _SDOPackage.Configuration get_configuration () throws _SDOPackage.InterfaceNotImplemented, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_configuration", true);
                $in = _invoke ($out);
                _SDOPackage.Configuration $result = _SDOPackage.ConfigurationHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:org.omg/_SDOPackage/InterfaceNotImplemented:1.0"))
                    throw _SDOPackage.InterfaceNotImplementedHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/NotAvailable:1.0"))
                    throw _SDOPackage.NotAvailableHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/InternalError:1.0"))
                    throw _SDOPackage.InternalErrorHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_configuration (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_configuration

  public _SDOPackage.Monitoring get_monitoring () throws _SDOPackage.InterfaceNotImplemented, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_monitoring", true);
                $in = _invoke ($out);
                _SDOPackage.Monitoring $result = _SDOPackage.MonitoringHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:org.omg/_SDOPackage/InterfaceNotImplemented:1.0"))
                    throw _SDOPackage.InterfaceNotImplementedHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/NotAvailable:1.0"))
                    throw _SDOPackage.NotAvailableHelper.read ($in);
                else if (_id.equals ("IDL:org.omg/_SDOPackage/InternalError:1.0"))
                    throw _SDOPackage.InternalErrorHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_monitoring (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_monitoring

  public _SDOPackage.Organization[] get_organizations () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_organizations", true);
                $in = _invoke ($out);
                _SDOPackage.Organization $result[] = _SDOPackage.OrganizationListHelper.read ($in);
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
                return get_organizations (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_organizations

  public _SDOPackage.NameValue[] get_status_list () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_status_list", true);
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
                return get_status_list (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_status_list

  public org.omg.CORBA.Any get_status (String name) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_status", true);
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
                return get_status (name        );
            } finally {
                _releaseReply ($in);
            }
  } // get_status

  public _SDOPackage.Organization[] get_owned_organizations () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_owned_organizations", true);
                $in = _invoke ($out);
                _SDOPackage.Organization $result[] = _SDOPackage.OrganizationListHelper.read ($in);
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
                return get_owned_organizations (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_owned_organizations

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:org.omg/SDOPackage/SDO:1.0", 
    "IDL:org.omg/SDOPackage/SDOSystemElement:1.0"};

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
} // class _SDOStub
