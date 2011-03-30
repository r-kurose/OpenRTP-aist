package OpenRTM;


/**
* OpenRTM/StatusKind.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: ./OpenRTM.idl
* 2011年2月16日 17時08分39秒 JST
*/

public class StatusKind implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 6;
  private static OpenRTM.StatusKind[] __array = new OpenRTM.StatusKind [__size];

  public static final int _COMPONENT_PROFILE = 0;
  public static final OpenRTM.StatusKind COMPONENT_PROFILE = new OpenRTM.StatusKind(_COMPONENT_PROFILE);
  public static final int _RTC_STATUS = 1;
  public static final OpenRTM.StatusKind RTC_STATUS = new OpenRTM.StatusKind(_RTC_STATUS);
  public static final int _EC_STATUS = 2;
  public static final OpenRTM.StatusKind EC_STATUS = new OpenRTM.StatusKind(_EC_STATUS);
  public static final int _PORT_PROFILE = 3;
  public static final OpenRTM.StatusKind PORT_PROFILE = new OpenRTM.StatusKind(_PORT_PROFILE);
  public static final int _CONFIGURATION = 4;
  public static final OpenRTM.StatusKind CONFIGURATION = new OpenRTM.StatusKind(_CONFIGURATION);
  public static final int _HEART_BEAT = 5;
  public static final OpenRTM.StatusKind HEART_BEAT = new OpenRTM.StatusKind(_HEART_BEAT);

  public int value ()
  {
    return __value;
  }

  public static OpenRTM.StatusKind from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected StatusKind (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class StatusKind
