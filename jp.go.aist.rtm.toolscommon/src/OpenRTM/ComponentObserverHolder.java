package OpenRTM;

/**
* OpenRTM/ComponentObserverHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: ./OpenRTM.idl
* 2011年2月16日 17時08分39秒 JST
*/

public final class ComponentObserverHolder implements org.omg.CORBA.portable.Streamable
{
  public OpenRTM.ComponentObserver value = null;

  public ComponentObserverHolder ()
  {
  }

  public ComponentObserverHolder (OpenRTM.ComponentObserver initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = OpenRTM.ComponentObserverHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    OpenRTM.ComponentObserverHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return OpenRTM.ComponentObserverHelper.type ();
  }

}
