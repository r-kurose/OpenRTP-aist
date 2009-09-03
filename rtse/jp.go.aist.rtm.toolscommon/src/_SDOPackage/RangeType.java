package _SDOPackage;


/**
* _SDOPackage/RangeType.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public final class RangeType implements org.omg.CORBA.portable.IDLEntity
{
  public _SDOPackage.Numeric min = null;
  public _SDOPackage.Numeric max = null;
  public boolean min_inclusive = false;
  public boolean max_inclusive = false;

  public RangeType ()
  {
  } // ctor

  public RangeType (_SDOPackage.Numeric _min, _SDOPackage.Numeric _max, boolean _min_inclusive, boolean _max_inclusive)
  {
    min = _min;
    max = _max;
    min_inclusive = _min_inclusive;
    max_inclusive = _max_inclusive;
  } // ctor

} // class RangeType
