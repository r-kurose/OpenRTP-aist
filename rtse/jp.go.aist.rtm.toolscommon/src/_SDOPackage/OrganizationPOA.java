package _SDOPackage;


/**
* _SDOPackage/OrganizationPOA.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public abstract class OrganizationPOA extends org.omg.PortableServer.Servant
 implements _SDOPackage.OrganizationOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("get_organization_id", new java.lang.Integer (0));
    _methods.put ("get_organization_property", new java.lang.Integer (1));
    _methods.put ("get_organization_property_value", new java.lang.Integer (2));
    _methods.put ("add_organization_property", new java.lang.Integer (3));
    _methods.put ("set_organization_property_value", new java.lang.Integer (4));
    _methods.put ("remove_organization_property", new java.lang.Integer (5));
    _methods.put ("get_owner", new java.lang.Integer (6));
    _methods.put ("set_owner", new java.lang.Integer (7));
    _methods.put ("get_members", new java.lang.Integer (8));
    _methods.put ("set_members", new java.lang.Integer (9));
    _methods.put ("add_members", new java.lang.Integer (10));
    _methods.put ("remove_member", new java.lang.Integer (11));
    _methods.put ("get_dependency", new java.lang.Integer (12));
    _methods.put ("set_dependency", new java.lang.Integer (13));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // _SDOPackage/Organization/get_organization_id
       {
         try {
           String $result = null;
           $result = this.get_organization_id ();
           out = $rh.createReply();
           out.write_string ($result);
         } catch (_SDOPackage.InvalidParameter $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InvalidParameterHelper.write (out, $ex);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 1:  // _SDOPackage/Organization/get_organization_property
       {
         try {
           _SDOPackage.OrganizationProperty $result = null;
           $result = this.get_organization_property ();
           out = $rh.createReply();
           _SDOPackage.OrganizationPropertyHelper.write (out, $result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 2:  // _SDOPackage/Organization/get_organization_property_value
       {
         try {
           String name = in.read_string ();
           org.omg.CORBA.Any $result = null;
           $result = this.get_organization_property_value (name);
           out = $rh.createReply();
           out.write_any ($result);
         } catch (_SDOPackage.InvalidParameter $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InvalidParameterHelper.write (out, $ex);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 3:  // _SDOPackage/Organization/add_organization_property
       {
         try {
           _SDOPackage.OrganizationProperty organization_property = _SDOPackage.OrganizationPropertyHelper.read (in);
           boolean $result = false;
           $result = this.add_organization_property (organization_property);
           out = $rh.createReply();
           out.write_boolean ($result);
         } catch (_SDOPackage.InvalidParameter $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InvalidParameterHelper.write (out, $ex);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 4:  // _SDOPackage/Organization/set_organization_property_value
       {
         try {
           String name = in.read_string ();
           org.omg.CORBA.Any value = in.read_any ();
           boolean $result = false;
           $result = this.set_organization_property_value (name, value);
           out = $rh.createReply();
           out.write_boolean ($result);
         } catch (_SDOPackage.InvalidParameter $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InvalidParameterHelper.write (out, $ex);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 5:  // _SDOPackage/Organization/remove_organization_property
       {
         try {
           String name = in.read_string ();
           boolean $result = false;
           $result = this.remove_organization_property (name);
           out = $rh.createReply();
           out.write_boolean ($result);
         } catch (_SDOPackage.InvalidParameter $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InvalidParameterHelper.write (out, $ex);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 6:  // _SDOPackage/Organization/get_owner
       {
         try {
           _SDOPackage.SDOSystemElement $result = null;
           $result = this.get_owner ();
           out = $rh.createReply();
           _SDOPackage.SDOSystemElementHelper.write (out, $result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 7:  // _SDOPackage/Organization/set_owner
       {
         try {
           _SDOPackage.SDOSystemElement sdo = _SDOPackage.SDOSystemElementHelper.read (in);
           boolean $result = false;
           $result = this.set_owner (sdo);
           out = $rh.createReply();
           out.write_boolean ($result);
         } catch (_SDOPackage.InvalidParameter $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InvalidParameterHelper.write (out, $ex);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 8:  // _SDOPackage/Organization/get_members
       {
         try {
           _SDOPackage.SDO $result[] = null;
           $result = this.get_members ();
           out = $rh.createReply();
           _SDOPackage.SDOListHelper.write (out, $result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 9:  // _SDOPackage/Organization/set_members
       {
         try {
           _SDOPackage.SDO sdos[] = _SDOPackage.SDOListHelper.read (in);
           boolean $result = false;
           $result = this.set_members (sdos);
           out = $rh.createReply();
           out.write_boolean ($result);
         } catch (_SDOPackage.InvalidParameter $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InvalidParameterHelper.write (out, $ex);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 10:  // _SDOPackage/Organization/add_members
       {
         try {
           _SDOPackage.SDO sdo_list[] = _SDOPackage.SDOListHelper.read (in);
           boolean $result = false;
           $result = this.add_members (sdo_list);
           out = $rh.createReply();
           out.write_boolean ($result);
         } catch (_SDOPackage.InvalidParameter $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InvalidParameterHelper.write (out, $ex);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 11:  // _SDOPackage/Organization/remove_member
       {
         try {
           String id = _SDOPackage.UniqueIdentifierHelper.read (in);
           boolean $result = false;
           $result = this.remove_member (id);
           out = $rh.createReply();
           out.write_boolean ($result);
         } catch (_SDOPackage.InvalidParameter $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InvalidParameterHelper.write (out, $ex);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 12:  // _SDOPackage/Organization/get_dependency
       {
         try {
           _SDOPackage.DependencyType $result = null;
           $result = this.get_dependency ();
           out = $rh.createReply();
           _SDOPackage.DependencyTypeHelper.write (out, $result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 13:  // _SDOPackage/Organization/set_dependency
       {
         try {
           _SDOPackage.DependencyType dependency = _SDOPackage.DependencyTypeHelper.read (in);
           boolean $result = false;
           $result = this.set_dependency (dependency);
           out = $rh.createReply();
           out.write_boolean ($result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:org.omg/SDOPackage/Organization:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Organization _this() 
  {
    return OrganizationHelper.narrow(
    super._this_object());
  }

  public Organization _this(org.omg.CORBA.ORB orb) 
  {
    return OrganizationHelper.narrow(
    super._this_object(orb));
  }


} // class OrganizationPOA
