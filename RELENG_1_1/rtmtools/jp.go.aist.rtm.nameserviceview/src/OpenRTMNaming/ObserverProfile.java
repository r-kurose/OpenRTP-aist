package OpenRTMNaming;


/**
* OpenRTMNaming/ObserverProfile.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: ./OpenRTMNaming.idl
* 2011年2月10日 18時15分14秒 JST
*/

public final class ObserverProfile implements org.omg.CORBA.portable.IDLEntity
{
  public String id = null;
  public String interface_type = null;
  public OpenRTMNaming.NamingObserver observer = null;

  public ObserverProfile ()
  {
  } // ctor

  public ObserverProfile (String _id, String _interface_type, OpenRTMNaming.NamingObserver _observer)
  {
    id = _id;
    interface_type = _interface_type;
    observer = _observer;
  } // ctor

} // class ObserverProfile
