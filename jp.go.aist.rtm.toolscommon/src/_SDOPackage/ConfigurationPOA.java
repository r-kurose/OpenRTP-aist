package _SDOPackage;


/**
* _SDOPackage/ConfigurationPOA.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public abstract class ConfigurationPOA extends org.omg.PortableServer.Servant
 implements _SDOPackage.ConfigurationOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("set_device_profile", new java.lang.Integer (0));
    _methods.put ("add_service_profile", new java.lang.Integer (1));
    _methods.put ("add_organization", new java.lang.Integer (2));
    _methods.put ("remove_service_profile", new java.lang.Integer (3));
    _methods.put ("remove_organization", new java.lang.Integer (4));
    _methods.put ("get_configuration_parameters", new java.lang.Integer (5));
    _methods.put ("get_configuration_parameter_values", new java.lang.Integer (6));
    _methods.put ("get_configuration_parameter_value", new java.lang.Integer (7));
    _methods.put ("set_configuration_parameter", new java.lang.Integer (8));
    _methods.put ("get_configuration_sets", new java.lang.Integer (9));
    _methods.put ("get_configuration_set", new java.lang.Integer (10));
    _methods.put ("set_configuration_set_values", new java.lang.Integer (11));
    _methods.put ("get_active_configuration_set", new java.lang.Integer (12));
    _methods.put ("add_configuration_set", new java.lang.Integer (13));
    _methods.put ("remove_configuration_set", new java.lang.Integer (14));
    _methods.put ("activate_configuration_set", new java.lang.Integer (15));
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
       case 0:  // _SDOPackage/Configuration/set_device_profile
       {
         try {
           _SDOPackage.DeviceProfile dProfile = _SDOPackage.DeviceProfileHelper.read (in);
           boolean $result = false;
           $result = this.set_device_profile (dProfile);
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

       case 1:  // _SDOPackage/Configuration/add_service_profile
       {
         try {
           _SDOPackage.ServiceProfile sProfile = _SDOPackage.ServiceProfileHelper.read (in);
           boolean $result = false;
           $result = this.add_service_profile (sProfile);
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

       case 2:  // _SDOPackage/Configuration/add_organization
       {
         try {
           _SDOPackage.Organization organization_object = _SDOPackage.OrganizationHelper.read (in);
           boolean $result = false;
           $result = this.add_organization (organization_object);
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

       case 3:  // _SDOPackage/Configuration/remove_service_profile
       {
         try {
           String id = _SDOPackage.UniqueIdentifierHelper.read (in);
           boolean $result = false;
           $result = this.remove_service_profile (id);
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

       case 4:  // _SDOPackage/Configuration/remove_organization
       {
         try {
           String organization_id = _SDOPackage.UniqueIdentifierHelper.read (in);
           boolean $result = false;
           $result = this.remove_organization (organization_id);
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

       case 5:  // _SDOPackage/Configuration/get_configuration_parameters
       {
         try {
           _SDOPackage.Parameter $result[] = null;
           $result = this.get_configuration_parameters ();
           out = $rh.createReply();
           _SDOPackage.ParameterListHelper.write (out, $result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 6:  // _SDOPackage/Configuration/get_configuration_parameter_values
       {
         try {
           _SDOPackage.NameValue $result[] = null;
           $result = this.get_configuration_parameter_values ();
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

       case 7:  // _SDOPackage/Configuration/get_configuration_parameter_value
       {
         try {
           String name = in.read_string ();
           org.omg.CORBA.Any $result = null;
           $result = this.get_configuration_parameter_value (name);
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

       case 8:  // _SDOPackage/Configuration/set_configuration_parameter
       {
         try {
           String name = in.read_string ();
           org.omg.CORBA.Any value = in.read_any ();
           boolean $result = false;
           $result = this.set_configuration_parameter (name, value);
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

       case 9:  // _SDOPackage/Configuration/get_configuration_sets
       {
         try {
           _SDOPackage.ConfigurationSet $result[] = null;
           $result = this.get_configuration_sets ();
           out = $rh.createReply();
           _SDOPackage.ConfigurationSetListHelper.write (out, $result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 10:  // _SDOPackage/Configuration/get_configuration_set
       {
         try {
           String config_id = _SDOPackage.UniqueIdentifierHelper.read (in);
           _SDOPackage.ConfigurationSet $result = null;
           $result = this.get_configuration_set (config_id);
           out = $rh.createReply();
           _SDOPackage.ConfigurationSetHelper.write (out, $result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 11:  // _SDOPackage/Configuration/set_configuration_set_values
       {
         try {
           _SDOPackage.ConfigurationSet configuration_set = _SDOPackage.ConfigurationSetHelper.read (in);
           boolean $result = false;
           $result = this.set_configuration_set_values (configuration_set);
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

       case 12:  // _SDOPackage/Configuration/get_active_configuration_set
       {
         try {
           _SDOPackage.ConfigurationSet $result = null;
           $result = this.get_active_configuration_set ();
           out = $rh.createReply();
           _SDOPackage.ConfigurationSetHelper.write (out, $result);
         } catch (_SDOPackage.NotAvailable $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.NotAvailableHelper.write (out, $ex);
         } catch (_SDOPackage.InternalError $ex) {
           out = $rh.createExceptionReply ();
           _SDOPackage.InternalErrorHelper.write (out, $ex);
         }
         break;
       }

       case 13:  // _SDOPackage/Configuration/add_configuration_set
       {
         try {
           _SDOPackage.ConfigurationSet configuration_set = _SDOPackage.ConfigurationSetHelper.read (in);
           boolean $result = false;
           $result = this.add_configuration_set (configuration_set);
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

       case 14:  // _SDOPackage/Configuration/remove_configuration_set
       {
         try {
           String config_id = _SDOPackage.UniqueIdentifierHelper.read (in);
           boolean $result = false;
           $result = this.remove_configuration_set (config_id);
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

       case 15:  // _SDOPackage/Configuration/activate_configuration_set
       {
         try {
           String config_id = _SDOPackage.UniqueIdentifierHelper.read (in);
           boolean $result = false;
           $result = this.activate_configuration_set (config_id);
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

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:org.omg/SDOPackage/Configuration:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Configuration _this() 
  {
    return ConfigurationHelper.narrow(
    super._this_object());
  }

  public Configuration _this(org.omg.CORBA.ORB orb) 
  {
    return ConfigurationHelper.narrow(
    super._this_object(orb));
  }


} // class ConfigurationPOA
