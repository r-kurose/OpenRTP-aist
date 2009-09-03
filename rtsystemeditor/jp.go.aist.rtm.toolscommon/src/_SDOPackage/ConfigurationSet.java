package _SDOPackage;


/**
* _SDOPackage/ConfigurationSet.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public final class ConfigurationSet implements org.omg.CORBA.portable.IDLEntity
{
  public String id = null;
  public String description = null;
  public _SDOPackage.NameValue configuration_data[] = null;

  public ConfigurationSet ()
  {
  } // ctor

  public ConfigurationSet (String _id, String _description, _SDOPackage.NameValue[] _configuration_data)
  {
    id = _id;
    description = _description;
    configuration_data = _configuration_data;
  } // ctor

} // class ConfigurationSet
