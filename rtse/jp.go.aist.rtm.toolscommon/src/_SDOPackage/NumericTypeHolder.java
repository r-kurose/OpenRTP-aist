package _SDOPackage;

/**
* _SDOPackage/NumericTypeHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public final class NumericTypeHolder implements org.omg.CORBA.portable.Streamable
{
  public _SDOPackage.NumericType value = null;

  public NumericTypeHolder ()
  {
  }

  public NumericTypeHolder (_SDOPackage.NumericType initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = _SDOPackage.NumericTypeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    _SDOPackage.NumericTypeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return _SDOPackage.NumericTypeHelper.type ();
  }

}
