package OpenRTM;


/**
* OpenRTM/_ComponentObserverStub.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: ./OpenRTM.idl
* 2011年2月16日 17時08分38秒 JST
*/

public class _ComponentObserverStub extends org.omg.CORBA.portable.ObjectImpl implements OpenRTM.ComponentObserver
{

  public void update_status (OpenRTM.StatusKind status_kind, String hint)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("update_status", false);
                OpenRTM.StatusKindHelper.write ($out, status_kind);
                $out.write_string (hint);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                update_status (status_kind, hint        );
            } finally {
                _releaseReply ($in);
            }
  } // update_status

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:OpenRTM/ComponentObserver:1.0", 
    "IDL:org.omg/SDOPackage/SDOService:1.0"};

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
} // class _ComponentObserverStub
