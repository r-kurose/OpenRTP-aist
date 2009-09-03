package _SDOPackage;

/**
* _SDOPackage/MonitoringHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分39秒 JST
*/

public final class MonitoringHolder implements org.omg.CORBA.portable.Streamable
{
  public _SDOPackage.Monitoring value = null;

  public MonitoringHolder ()
  {
  }

  public MonitoringHolder (_SDOPackage.Monitoring initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = _SDOPackage.MonitoringHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    _SDOPackage.MonitoringHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return _SDOPackage.MonitoringHelper.type ();
  }

}
