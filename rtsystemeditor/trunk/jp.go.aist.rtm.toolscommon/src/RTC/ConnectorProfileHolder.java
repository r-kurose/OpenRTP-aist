package RTC;

/**
* RTC/ConnectorProfileHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief ConnectorProfile
   *
   * @section Description
   *
   * The ConnectorProfile contains information about a connection
   * between the ports of collaborating RTCs.
   *
   * @endif
   */
public final class ConnectorProfileHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.ConnectorProfile value = null;

  public ConnectorProfileHolder ()
  {
  }

  public ConnectorProfileHolder (RTC.ConnectorProfile initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.ConnectorProfileHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.ConnectorProfileHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.ConnectorProfileHelper.type ();
  }

}
