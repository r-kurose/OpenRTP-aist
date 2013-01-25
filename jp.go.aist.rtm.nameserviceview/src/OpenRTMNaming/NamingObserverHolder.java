package OpenRTMNaming;

/**
* OpenRTMNaming/NamingObserverHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: ./OpenRTMNaming.idl
* 2011年2月10日 18時15分14秒 JST
*/

public final class NamingObserverHolder implements org.omg.CORBA.portable.Streamable
{
  public OpenRTMNaming.NamingObserver value = null;

  public NamingObserverHolder ()
  {
  }

  public NamingObserverHolder (OpenRTMNaming.NamingObserver initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = OpenRTMNaming.NamingObserverHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    OpenRTMNaming.NamingObserverHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return OpenRTMNaming.NamingObserverHelper.type ();
  }

}
