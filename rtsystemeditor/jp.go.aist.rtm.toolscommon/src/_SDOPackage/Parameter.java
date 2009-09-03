package _SDOPackage;


/**
* _SDOPackage/Parameter.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public final class Parameter implements org.omg.CORBA.portable.IDLEntity
{
  public String name = null;
  public org.omg.CORBA.TypeCode type = null;
  public _SDOPackage.AllowedValues allowed_values = null;

  public Parameter ()
  {
  } // ctor

  public Parameter (String _name, org.omg.CORBA.TypeCode _type, _SDOPackage.AllowedValues _allowed_values)
  {
    name = _name;
    type = _type;
    allowed_values = _allowed_values;
  } // ctor

} // class Parameter
