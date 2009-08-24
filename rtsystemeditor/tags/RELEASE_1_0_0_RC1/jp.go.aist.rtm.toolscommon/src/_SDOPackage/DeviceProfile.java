package _SDOPackage;


/**
* _SDOPackage/DeviceProfile.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public final class DeviceProfile implements org.omg.CORBA.portable.IDLEntity
{
  public String device_type = null;
  public String manufacturer = null;
  public String model = null;
  public String version = null;
  public _SDOPackage.NameValue properties[] = null;

  public DeviceProfile ()
  {
  } // ctor

  public DeviceProfile (String _device_type, String _manufacturer, String _model, String _version, _SDOPackage.NameValue[] _properties)
  {
    device_type = _device_type;
    manufacturer = _manufacturer;
    model = _model;
    version = _version;
    properties = _properties;
  } // ctor

} // class DeviceProfile
