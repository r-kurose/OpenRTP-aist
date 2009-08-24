package _SDOPackage;


/**
* _SDOPackage/SDOSystemElementPOA.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/


/** ------- Interfaces -------*/
public abstract class SDOSystemElementPOA extends org.omg.PortableServer.Servant
 implements _SDOPackage.SDOSystemElementOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("get_owned_organizations", new java.lang.Integer (0));
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
       case 0:  // _SDOPackage/SDOSystemElement/get_owned_organizations
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
    "IDL:org.omg/SDOPackage/SDOSystemElement:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public SDOSystemElement _this() 
  {
    return SDOSystemElementHelper.narrow(
    super._this_object());
  }

  public SDOSystemElement _this(org.omg.CORBA.ORB orb) 
  {
    return SDOSystemElementHelper.narrow(
    super._this_object(orb));
  }


} // class SDOSystemElementPOA
