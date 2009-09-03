package _SDOPackage;


/**
* _SDOPackage/Numeric.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008年12月4日 (木曜日) 12時46分40秒 JST
*/

public final class Numeric implements org.omg.CORBA.portable.IDLEntity
{
  private short ___short_value;
  private int ___long_value;
  private float ___float_value;
  private double ___double_value;
  private _SDOPackage.NumericType __discriminator;
  private boolean __uninitialized = true;

  public Numeric ()
  {
  }

  public _SDOPackage.NumericType discriminator ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    return __discriminator;
  }

  public short short_value ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyshort_value (__discriminator);
    return ___short_value;
  }

  public void short_value (short value)
  {
    __discriminator = _SDOPackage.NumericType.SHORT_TYPE;
    ___short_value = value;
    __uninitialized = false;
  }

  public void short_value (_SDOPackage.NumericType discriminator, short value)
  {
    verifyshort_value (discriminator);
    __discriminator = discriminator;
    ___short_value = value;
    __uninitialized = false;
  }

  private void verifyshort_value (_SDOPackage.NumericType discriminator)
  {
    if (discriminator != _SDOPackage.NumericType.SHORT_TYPE)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public int long_value ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifylong_value (__discriminator);
    return ___long_value;
  }

  public void long_value (int value)
  {
    __discriminator = _SDOPackage.NumericType.LONG_TYPE;
    ___long_value = value;
    __uninitialized = false;
  }

  public void long_value (_SDOPackage.NumericType discriminator, int value)
  {
    verifylong_value (discriminator);
    __discriminator = discriminator;
    ___long_value = value;
    __uninitialized = false;
  }

  private void verifylong_value (_SDOPackage.NumericType discriminator)
  {
    if (discriminator != _SDOPackage.NumericType.LONG_TYPE)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public float float_value ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifyfloat_value (__discriminator);
    return ___float_value;
  }

  public void float_value (float value)
  {
    __discriminator = _SDOPackage.NumericType.FLOAT_TYPE;
    ___float_value = value;
    __uninitialized = false;
  }

  public void float_value (_SDOPackage.NumericType discriminator, float value)
  {
    verifyfloat_value (discriminator);
    __discriminator = discriminator;
    ___float_value = value;
    __uninitialized = false;
  }

  private void verifyfloat_value (_SDOPackage.NumericType discriminator)
  {
    if (discriminator != _SDOPackage.NumericType.FLOAT_TYPE)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

  public double double_value ()
  {
    if (__uninitialized)
      throw new org.omg.CORBA.BAD_OPERATION ();
    verifydouble_value (__discriminator);
    return ___double_value;
  }

  public void double_value (double value)
  {
    __discriminator = _SDOPackage.NumericType.DOUBLE_TYPE;
    ___double_value = value;
    __uninitialized = false;
  }

  public void double_value (_SDOPackage.NumericType discriminator, double value)
  {
    verifydouble_value (discriminator);
    __discriminator = discriminator;
    ___double_value = value;
    __uninitialized = false;
  }

  private void verifydouble_value (_SDOPackage.NumericType discriminator)
  {
    if (discriminator != _SDOPackage.NumericType.DOUBLE_TYPE)
      throw new org.omg.CORBA.BAD_OPERATION ();
  }

} // class Numeric
