package OpenRTMNaming;


/**
* OpenRTMNaming/NamingNotifierPOA.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: ./OpenRTMNaming.idl
* 2011年2月10日 18時15分14秒 JST
*/

public abstract class NamingNotifierPOA extends org.omg.PortableServer.Servant
 implements OpenRTMNaming.NamingNotifierOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("list_all", new java.lang.Integer (0));
    _methods.put ("subscribe", new java.lang.Integer (1));
    _methods.put ("unsubscribe", new java.lang.Integer (2));
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
       case 0:  // OpenRTMNaming/NamingNotifier/list_all
       {
         OpenRTMNaming.TreeBindingListHolder tbl = new OpenRTMNaming.TreeBindingListHolder ();
         this.list_all (tbl);
         out = $rh.createReply();
         OpenRTMNaming.TreeBindingListHelper.write (out, tbl.value);
         break;
       }

       case 1:  // OpenRTMNaming/NamingNotifier/subscribe
       {
         OpenRTMNaming.ObserverProfile oprof = OpenRTMNaming.ObserverProfileHelper.read (in);
         boolean $result = false;
         $result = this.subscribe (oprof);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 2:  // OpenRTMNaming/NamingNotifier/unsubscribe
       {
         String id = in.read_string ();
         boolean $result = false;
         $result = this.unsubscribe (id);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:OpenRTMNaming/NamingNotifier:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public NamingNotifier _this() 
  {
    return NamingNotifierHelper.narrow(
    super._this_object());
  }

  public NamingNotifier _this(org.omg.CORBA.ORB orb) 
  {
    return NamingNotifierHelper.narrow(
    super._this_object(orb));
  }


} // class NamingNotifierPOA
