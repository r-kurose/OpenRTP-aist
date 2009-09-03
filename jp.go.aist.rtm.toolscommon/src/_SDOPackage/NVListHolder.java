package _SDOPackage;


/**
* _SDOPackage/NVListHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public final class NVListHolder implements org.omg.CORBA.portable.Streamable
{
  public _SDOPackage.NameValue value[] = null;

  public NVListHolder ()
  {
  }

  public NVListHolder (_SDOPackage.NameValue[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = _SDOPackage.NVListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    _SDOPackage.NVListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return _SDOPackage.NVListHelper.type ();
  }

}
