package _SDOPackage;


/**
* _SDOPackage/ComplexDataType.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public class ComplexDataType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 3;
  private static _SDOPackage.ComplexDataType[] __array = new _SDOPackage.ComplexDataType [__size];

  public static final int _ENUMERATION = 0;
  public static final _SDOPackage.ComplexDataType ENUMERATION = new _SDOPackage.ComplexDataType(_ENUMERATION);
  public static final int _RANGE = 1;
  public static final _SDOPackage.ComplexDataType RANGE = new _SDOPackage.ComplexDataType(_RANGE);
  public static final int _INTERVAL = 2;
  public static final _SDOPackage.ComplexDataType INTERVAL = new _SDOPackage.ComplexDataType(_INTERVAL);

  public int value ()
  {
    return __value;
  }

  public static _SDOPackage.ComplexDataType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected ComplexDataType (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class ComplexDataType
