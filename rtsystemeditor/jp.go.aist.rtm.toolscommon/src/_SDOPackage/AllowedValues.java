package _SDOPackage;


/**
* _SDOPackage/AllowedValues.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public final class AllowedValues implements org.omg.CORBA.portable.IDLEntity
{
  private _SDOPackage.EnumerationType ___allowed_enum;
  private _SDOPackage.IntervalType ___allowed_interval;
  private _SDOPackage.RangeType ___allowed_range;
  private _SDOPackage.ComplexDataType __discriminator;
  private boolean __uninitialized = true;

  public AllowedValues ()
  {
  }

  public _SDOPackage.ComplexDataType discriminator ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    return __discriminator;
  }

  public _SDOPackage.EnumerationType allowed_enum ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyallowed_enum (__discriminator);
    return ___allowed_enum;
  }

  public void allowed_enum (_SDOPackage.EnumerationType value)
  {
    __discriminator = _SDOPackage.ComplexDataType.ENUMERATION;
    ___allowed_enum = value;
    __uninitialized = false;
  }

  public void allowed_enum (_SDOPackage.ComplexDataType discriminator, _SDOPackage.EnumerationType value)
  {
    verifyallowed_enum (discriminator);
    __discriminator = discriminator;
    ___allowed_enum = value;
    __uninitialized = false;
  }

  private void verifyallowed_enum (_SDOPackage.ComplexDataType discriminator)
  {
    if (discriminator != _SDOPackage.ComplexDataType.ENUMERATION)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public _SDOPackage.IntervalType allowed_interval ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyallowed_interval (__discriminator);
    return ___allowed_interval;
  }

  public void allowed_interval (_SDOPackage.IntervalType value)
  {
    __discriminator = _SDOPackage.ComplexDataType.INTERVAL;
    ___allowed_interval = value;
    __uninitialized = false;
  }

  public void allowed_interval (_SDOPackage.ComplexDataType discriminator, _SDOPackage.IntervalType value)
  {
    verifyallowed_interval (discriminator);
    __discriminator = discriminator;
    ___allowed_interval = value;
    __uninitialized = false;
  }

  private void verifyallowed_interval (_SDOPackage.ComplexDataType discriminator)
  {
    if (discriminator != _SDOPackage.ComplexDataType.INTERVAL)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public _SDOPackage.RangeType allowed_range ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyallowed_range (__discriminator);
    return ___allowed_range;
  }

  public void allowed_range (_SDOPackage.RangeType value)
  {
    __discriminator = _SDOPackage.ComplexDataType.RANGE;
    ___allowed_range = value;
    __uninitialized = false;
  }

  public void allowed_range (_SDOPackage.ComplexDataType discriminator, _SDOPackage.RangeType value)
  {
    verifyallowed_range (discriminator);
    __discriminator = discriminator;
    ___allowed_range = value;
    __uninitialized = false;
  }

  private void verifyallowed_range (_SDOPackage.ComplexDataType discriminator)
  {
    if (discriminator != _SDOPackage.ComplexDataType.RANGE)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

} // class AllowedValues
