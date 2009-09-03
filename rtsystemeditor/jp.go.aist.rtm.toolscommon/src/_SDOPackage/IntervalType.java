package _SDOPackage;


/**
* _SDOPackage/IntervalType.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public final class IntervalType implements org.omg.CORBA.portable.IDLEntity
{
  public _SDOPackage.Numeric min = null;
  public _SDOPackage.Numeric max = null;
  public boolean min_inclusive = false;
  public boolean max_inclusive = false;
  public _SDOPackage.Numeric step = null;

  public IntervalType ()
  {
  } // ctor

  public IntervalType (_SDOPackage.Numeric _min, _SDOPackage.Numeric _max, boolean _min_inclusive, boolean _max_inclusive, _SDOPackage.Numeric _step)
  {
    min = _min;
    max = _max;
    min_inclusive = _min_inclusive;
    max_inclusive = _max_inclusive;
    step = _step;
  } // ctor

} // class IntervalType
