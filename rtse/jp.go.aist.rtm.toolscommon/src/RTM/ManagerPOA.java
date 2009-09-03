package RTM;


/**
* RTM/ManagerPOA.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/Manager.idl
* 2008年12月5日 (金曜日) 12時20分51秒 JST
*/

public abstract class ManagerPOA extends org.omg.PortableServer.Servant
 implements RTM.ManagerOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("load_module", new java.lang.Integer (0));
    _methods.put ("unload_module", new java.lang.Integer (1));
    _methods.put ("get_loadable_modules", new java.lang.Integer (2));
    _methods.put ("get_loaded_modules", new java.lang.Integer (3));
    _methods.put ("get_factory_profiles", new java.lang.Integer (4));
    _methods.put ("create_component", new java.lang.Integer (5));
    _methods.put ("delete_component", new java.lang.Integer (6));
    _methods.put ("get_components", new java.lang.Integer (7));
    _methods.put ("get_component_profiles", new java.lang.Integer (8));
    _methods.put ("get_profile", new java.lang.Integer (9));
    _methods.put ("get_configuration", new java.lang.Integer (10));
    _methods.put ("set_configuration", new java.lang.Integer (11));
    _methods.put ("get_owner", new java.lang.Integer (12));
    _methods.put ("set_owner", new java.lang.Integer (13));
    _methods.put ("get_child", new java.lang.Integer (14));
    _methods.put ("set_child", new java.lang.Integer (15));
    _methods.put ("fork", new java.lang.Integer (16));
    _methods.put ("shutdown", new java.lang.Integer (17));
    _methods.put ("restart", new java.lang.Integer (18));
    _methods.put ("get_service", new java.lang.Integer (19));
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

  // module ￠#
       case 0:  // RTM/Manager/load_module
       {
         String pathname = in.read_string ();
         String initfunc = in.read_string ();
         RTC.ReturnCode_t $result = null;
         $result = this.load_module (pathname, initfunc);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }

       case 1:  // RTM/Manager/unload_module
       {
         String pathname = in.read_string ();
         RTC.ReturnCode_t $result = null;
         $result = this.unload_module (pathname);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }

       case 2:  // RTM/Manager/get_loadable_modules
       {
         RTM.ModuleProfile $result[] = null;
         $result = this.get_loadable_modules ();
         out = $rh.createReply();
         RTM.ModuleProfileListHelper.write (out, $result);
         break;
       }

       case 3:  // RTM/Manager/get_loaded_modules
       {
         RTM.ModuleProfile $result[] = null;
         $result = this.get_loaded_modules ();
         out = $rh.createReply();
         RTM.ModuleProfileListHelper.write (out, $result);
         break;
       }


  // component ￠#
       case 4:  // RTM/Manager/get_factory_profiles
       {
         RTM.ModuleProfile $result[] = null;
         $result = this.get_factory_profiles ();
         out = $rh.createReply();
         RTM.ModuleProfileListHelper.write (out, $result);
         break;
       }

       case 5:  // RTM/Manager/create_component
       {
         String module_name = in.read_string ();
         RTC.RTObject $result = null;
         $result = this.create_component (module_name);
         out = $rh.createReply();
         RTC.RTObjectHelper.write (out, $result);
         break;
       }

       case 6:  // RTM/Manager/delete_component
       {
         String instance_name = in.read_string ();
         RTC.ReturnCode_t $result = null;
         $result = this.delete_component (instance_name);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }

       case 7:  // RTM/Manager/get_components
       {
         RTC.RTObject $result[] = null;
         $result = this.get_components ();
         out = $rh.createReply();
         RTC.RTCListHelper.write (out, $result);
         break;
       }

       case 8:  // RTM/Manager/get_component_profiles
       {
         RTC.ComponentProfile $result[] = null;
         $result = this.get_component_profiles ();
         out = $rh.createReply();
         RTC.ComponentProfileListHelper.write (out, $result);
         break;
       }


  // manager ?,
       case 9:  // RTM/Manager/get_profile
       {
         RTM.ManagerProfile $result = null;
         $result = this.get_profile ();
         out = $rh.createReply();
         RTM.ManagerProfileHelper.write (out, $result);
         break;
       }

       case 10:  // RTM/Manager/get_configuration
       {
         _SDOPackage.NameValue $result[] = null;
         $result = this.get_configuration ();
         out = $rh.createReply();
         _SDOPackage.NVListHelper.write (out, $result);
         break;
       }

       case 11:  // RTM/Manager/set_configuration
       {
         String name = in.read_string ();
         String value = in.read_string ();
         RTC.ReturnCode_t $result = null;
         $result = this.set_configuration (name, value);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }

       case 12:  // RTM/Manager/get_owner
       {
         RTM.Manager $result = null;
         $result = this.get_owner ();
         out = $rh.createReply();
         RTM.ManagerHelper.write (out, $result);
         break;
       }

       case 13:  // RTM/Manager/set_owner
       {
         RTM.Manager mgr = RTM.ManagerHelper.read (in);
         RTM.Manager $result = null;
         $result = this.set_owner (mgr);
         out = $rh.createReply();
         RTM.ManagerHelper.write (out, $result);
         break;
       }

       case 14:  // RTM/Manager/get_child
       {
         RTM.Manager $result = null;
         $result = this.get_child ();
         out = $rh.createReply();
         RTM.ManagerHelper.write (out, $result);
         break;
       }

       case 15:  // RTM/Manager/set_child
       {
         RTM.Manager mgr = RTM.ManagerHelper.read (in);
         RTM.Manager $result = null;
         $result = this.set_child (mgr);
         out = $rh.createReply();
         RTM.ManagerHelper.write (out, $result);
         break;
       }

       case 16:  // RTM/Manager/fork
       {
         RTC.ReturnCode_t $result = null;
         $result = this.fork ();
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }

       case 17:  // RTM/Manager/shutdown
       {
         RTC.ReturnCode_t $result = null;
         $result = this.shutdown ();
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }

       case 18:  // RTM/Manager/restart
       {
         RTC.ReturnCode_t $result = null;
         $result = this.restart ();
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }

       case 19:  // RTM/Manager/get_service
       {
         String name = in.read_string ();
         org.omg.CORBA.Object $result = null;
         $result = this.get_service (name);
         out = $rh.createReply();
         org.omg.CORBA.ObjectHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:RTM/Manager:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Manager _this() 
  {
    return ManagerHelper.narrow(
    super._this_object());
  }

  public Manager _this(org.omg.CORBA.ORB orb) 
  {
    return ManagerHelper.narrow(
    super._this_object(orb));
  }


} // class ManagerPOA
