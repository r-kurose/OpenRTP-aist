package _SDOPackage;


/**
* _SDOPackage/_OrganizationStub.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分39秒 JST
*/

public class _OrganizationStub extends org.omg.CORBA.portable.ObjectImpl implements _SDOPackage.Organization
{

  public String get_organization_id () throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_organization_id", true);
                $in = _invoke ($out);
                String $result = _SDOPackage.UniqueIdentifierHelper.read ($in);
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
                return get_organization_id (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_organization_id

  public _SDOPackage.OrganizationProperty get_organization_property () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_organization_property", true);
                $in = _invoke ($out);
                _SDOPackage.OrganizationProperty $result = _SDOPackage.OrganizationPropertyHelper.read ($in);
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
                return get_organization_property (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_organization_property

  public org.omg.CORBA.Any get_organization_property_value (String name) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_organization_property_value", true);
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
                return get_organization_property_value (name        );
            } finally {
                _releaseReply ($in);
            }
  } // get_organization_property_value

  public boolean add_organization_property (_SDOPackage.OrganizationProperty organization_property) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("add_organization_property", true);
                _SDOPackage.OrganizationPropertyHelper.write ($out, organization_property);
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
                return add_organization_property (organization_property        );
            } finally {
                _releaseReply ($in);
            }
  } // add_organization_property

  public boolean set_organization_property_value (String name, org.omg.CORBA.Any value) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("set_organization_property_value", true);
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
                return set_organization_property_value (name, value        );
            } finally {
                _releaseReply ($in);
            }
  } // set_organization_property_value

  public boolean remove_organization_property (String name) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("remove_organization_property", true);
                $out.write_string (name);
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
                return remove_organization_property (name        );
            } finally {
                _releaseReply ($in);
            }
  } // remove_organization_property

  public _SDOPackage.SDOSystemElement get_owner () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_owner", true);
                $in = _invoke ($out);
                _SDOPackage.SDOSystemElement $result = _SDOPackage.SDOSystemElementHelper.read ($in);
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
                return get_owner (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_owner

  public boolean set_owner (_SDOPackage.SDOSystemElement sdo) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("set_owner", true);
                _SDOPackage.SDOSystemElementHelper.write ($out, sdo);
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
                return set_owner (sdo        );
            } finally {
                _releaseReply ($in);
            }
  } // set_owner

  public _SDOPackage.SDO[] get_members () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_members", true);
                $in = _invoke ($out);
                _SDOPackage.SDO $result[] = _SDOPackage.SDOListHelper.read ($in);
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
                return get_members (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_members

  public boolean set_members (_SDOPackage.SDO[] sdos) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("set_members", true);
                _SDOPackage.SDOListHelper.write ($out, sdos);
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
                return set_members (sdos        );
            } finally {
                _releaseReply ($in);
            }
  } // set_members

  public boolean add_members (_SDOPackage.SDO[] sdo_list) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("add_members", true);
                _SDOPackage.SDOListHelper.write ($out, sdo_list);
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
                return add_members (sdo_list        );
            } finally {
                _releaseReply ($in);
            }
  } // add_members

  public boolean remove_member (String id) throws _SDOPackage.InvalidParameter, _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("remove_member", true);
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
                return remove_member (id        );
            } finally {
                _releaseReply ($in);
            }
  } // remove_member

  public _SDOPackage.DependencyType get_dependency () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_dependency", true);
                $in = _invoke ($out);
                _SDOPackage.DependencyType $result = _SDOPackage.DependencyTypeHelper.read ($in);
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
                return get_dependency (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_dependency

  public boolean set_dependency (_SDOPackage.DependencyType dependency) throws _SDOPackage.NotAvailable, _SDOPackage.InternalError
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("set_dependency", true);
                _SDOPackage.DependencyTypeHelper.write ($out, dependency);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
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
                return set_dependency (dependency        );
            } finally {
                _releaseReply ($in);
            }
  } // set_dependency

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:org.omg/SDOPackage/Organization:1.0"};

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
} // class _OrganizationStub
