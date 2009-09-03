package _SDOPackage;


/**
* _SDOPackage/_SDOSystemElementStub.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分39秒 JST
*/


/** ------- Interfaces -------*/
public class _SDOSystemElementStub extends org.omg.CORBA.portable.ObjectImpl implements _SDOPackage.SDOSystemElement
{

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
} // class _SDOSystemElementStub
