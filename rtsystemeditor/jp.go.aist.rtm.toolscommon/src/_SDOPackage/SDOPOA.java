package _SDOPackage;


/**
* _SDOPackage/SDOPOA.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public abstract class SDOPOA extends org.omg.PortableServer.Servant
 implements _SDOPackage.SDOOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("get_sdo_id", new java.lang.Integer (0));
    _methods.put ("get_sdo_type", new java.lang.Integer (1));
    _methods.put ("get_device_profile", new java.lang.Integer (2));
    _methods.put ("get_service_profiles", new java.lang.Integer (3));
    _methods.put ("get_service_profile", new java.lang.Integer (4));
    _methods.put ("get_sdo_service", new java.lang.Integer (5));
    _methods.put ("get_configuration", new java.lang.Integer (6));
    _methods.put ("get_monitoring", new java.lang.Integer (7));
    _methods.put ("get_organizations", new java.lang.Integer (8));
    _methods.put ("get_status_list", new java.lang.Integer (9));
    _methods.put ("get_status", new java.lang.Integer (10));
    _methods.put ("get_owned_organizations", new java.lang.Integer (11));
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
       case 0:  // _SDOPackage/SDO/get_sdo_id
       {
         try {
           String $result = null;
           $result = this.get_sdo_id ();
           out = $rh.createReply();
           out.write_string ($result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 1:  // _SDOPackage/SDO/get_sdo_type
       {
         try {
           String $result = null;
           $result = this.get_sdo_type ();
           out = $rh.createReply();
           out.write_string ($result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 2:  // _SDOPackage/SDO/get_device_profile
       {
         try {
           _SDOPackage.DeviceProfile $result = null;
           $result = this.get_device_profile ();
           out = $rh.createReply();
           _SDOPackage.DeviceProfileHelper.write (out, $result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 3:  // _SDOPackage/SDO/get_service_profiles
       {
         try {
           _SDOPackage.ServiceProfile $result[] = null;
           $result = this.get_service_profiles ();
           out = $rh.createReply();
           _SDOPackage.ServiceProfileListHelper.write (out, $result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 4:  // _SDOPackage/SDO/get_service_profile
       {
         try {
           String id = _SDOPackage.UniqueIdentifierHelper.read (in);
           _SDOPackage.ServiceProfile $result = null;
           $result = this.get_service_profile (id);
           out = $rh.createReply();
           _SDOPackage.ServiceProfileHelper.write (out, $result);
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

       case 5:  // _SDOPackage/SDO/get_sdo_service
       {
         try {
           String id = _SDOPackage.UniqueIdentifierHelper.read (in);
           _SDOPackage.SDOService $result = null;
           $result = this.get_sdo_service (id);
           out = $rh.createReply();
           _SDOPackage.SDOServiceHelper.write (out, $result);
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

       case 6:  // _SDOPackage/SDO/get_configuration
       {
         try {
           _SDOPackage.Configuration $result = null;
           $result = this.get_configuration ();
           out = $rh.createReply();
           _SDOPackage.ConfigurationHelper.write (out, $result);
         } catch (_SDOPackage.InterfaceNotImplemented $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InterfaceNotImplementedHelper.write (out, $ex);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 7:  // _SDOPackage/SDO/get_monitoring
       {
         try {
           _SDOPackage.Monitoring $result = null;
           $result = this.get_monitoring ();
           out = $rh.createReply();
           _SDOPackage.MonitoringHelper.write (out, $result);
         } catch (_SDOPackage.InterfaceNotImplemented $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InterfaceNotImplementedHelper.write (out, $ex);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 8:  // _SDOPackage/SDO/get_organizations
       {
         try {
           _SDOPackage.Organization $result[] = null;
           $result = this.get_organizations ();
           out = $rh.createReply();
           _SDOPackage.OrganizationListHelper.write (out, $result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 9:  // _SDOPackage/SDO/get_status_list
       {
         try {
           _SDOPackage.NameValue $result[] = null;
           $result = this.get_status_list ();
           out = $rh.createReply();
           _SDOPackage.NVListHelper.write (out, $result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 10:  // _SDOPackage/SDO/get_status
       {
         try {
           String name = in.read_string ();
           org.omg.CORBA.Any $result = null;
           $result = this.get_status (name);
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

       case 11:  // _SDOPackage/SDOSystemElement/get_owned_organizations
       {
         try {
           _SDOPackage.Organization $result[] = null;
           $result = this.get_owned_organizations ();
           out = $rh.createReply();
           _SDOPackage.OrganizationListHelper.write (out, $result);
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
    "IDL:org.omg/SDOPackage/SDO:1.0", 
    "IDL:org.omg/SDOPackage/SDOSystemElement:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public SDO _this() 
  {
    return SDOHelper.narrow(
    super._this_object());
  }

  public SDO _this(org.omg.CORBA.ORB orb) 
  {
    return SDOHelper.narrow(
    super._this_object(orb));
  }


} // class SDOPOA
