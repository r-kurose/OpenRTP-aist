package RTM;

/**
* RTM/ModuleProfileHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/Manager.idl
* 2008年12月5日 (金曜日) 12時20分51秒 JST
*/

public final class ModuleProfileHolder implements org.omg.CORBA.portable.Streamable
{
  public RTM.ModuleProfile value = null;

  public ModuleProfileHolder ()
  {
  }

  public ModuleProfileHolder (RTM.ModuleProfile initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTM.ModuleProfileHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTM.ModuleProfileHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTM.ModuleProfileHelper.type ();
  }

}
