package _SDOPackage;

/**
* _SDOPackage/SDOHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分39秒 JST
*/

public final class SDOHolder implements org.omg.CORBA.portable.Streamable
{
  public _SDOPackage.SDO value = null;

  public SDOHolder ()
  {
  }

  public SDOHolder (_SDOPackage.SDO initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = _SDOPackage.SDOHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    _SDOPackage.SDOHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return _SDOPackage.SDOHelper.type ();
  }

}
