package OpenRTMNaming;

/**
* OpenRTMNaming/TreeBindingHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: ./OpenRTMNaming.idl
* 2011年2月10日 18時15分14秒 JST
*/

public final class TreeBindingHolder implements org.omg.CORBA.portable.Streamable
{
  public OpenRTMNaming.TreeBinding value = null;

  public TreeBindingHolder ()
  {
  }

  public TreeBindingHolder (OpenRTMNaming.TreeBinding initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = OpenRTMNaming.TreeBindingHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    OpenRTMNaming.TreeBindingHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return OpenRTMNaming.TreeBindingHelper.type ();
  }

}
