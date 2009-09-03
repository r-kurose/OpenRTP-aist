package _SDOPackage;


/**
* _SDOPackage/DependencyType.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public class DependencyType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 3;
  private static _SDOPackage.DependencyType[] __array = new _SDOPackage.DependencyType [__size];

  public static final int _OWN = 0;
  public static final _SDOPackage.DependencyType OWN = new _SDOPackage.DependencyType(_OWN);
  public static final int _OWNED = 1;
  public static final _SDOPackage.DependencyType OWNED = new _SDOPackage.DependencyType(_OWNED);
  public static final int _NO_DEPENDENCY = 2;
  public static final _SDOPackage.DependencyType NO_DEPENDENCY = new _SDOPackage.DependencyType(_NO_DEPENDENCY);

  public int value ()
  {
    return __value;
  }

  public static _SDOPackage.DependencyType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected DependencyType (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class DependencyType
