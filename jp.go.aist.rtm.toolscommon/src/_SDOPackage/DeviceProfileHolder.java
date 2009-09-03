package _SDOPackage;

/**
* _SDOPackage/DeviceProfileHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public final class DeviceProfileHolder implements org.omg.CORBA.portable.Streamable
{
  public _SDOPackage.DeviceProfile value = null;

  public DeviceProfileHolder ()
  {
  }

  public DeviceProfileHolder (_SDOPackage.DeviceProfile initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = _SDOPackage.DeviceProfileHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    _SDOPackage.DeviceProfileHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return _SDOPackage.DeviceProfileHelper.type ();
  }

}
