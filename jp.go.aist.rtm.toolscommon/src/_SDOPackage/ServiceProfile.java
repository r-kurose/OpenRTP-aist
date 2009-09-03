package _SDOPackage;


/**
* _SDOPackage/ServiceProfile.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public final class ServiceProfile implements org.omg.CORBA.portable.IDLEntity
{
  public String id = null;
  public String interface_type = null;
  public _SDOPackage.NameValue properties[] = null;
  public _SDOPackage.SDOService service = null;

  public ServiceProfile ()
  {
  } // ctor

  public ServiceProfile (String _id, String _interface_type, _SDOPackage.NameValue[] _properties, _SDOPackage.SDOService _service)
  {
    id = _id;
    interface_type = _interface_type;
    properties = _properties;
    service = _service;
  } // ctor

} // class ServiceProfile
