package RTC;


/**
* RTC/ExecutionKind.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分46秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief ExecutionKind
   * 
   * @sectioni Description
   *
   * The ExecutionKind enumeration defines the execution semantics
   * (see Section 5.3) of the RTCs that participate in an execution
   * context.
   *
   * @endif
   */
public class ExecutionKind implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 3;
  private static RTC.ExecutionKind[] __array = new RTC.ExecutionKind [__size];

  public static final int _PERIODIC = 0;
  public static final RTC.ExecutionKind PERIODIC = new RTC.ExecutionKind(_PERIODIC);
  public static final int _EVENT_DRIVEN = 1;
  public static final RTC.ExecutionKind EVENT_DRIVEN = new RTC.ExecutionKind(_EVENT_DRIVEN);
  public static final int _OTHER = 2;
  public static final RTC.ExecutionKind OTHER = new RTC.ExecutionKind(_OTHER);

  public int value ()
  {
    return __value;
  }

  public static RTC.ExecutionKind from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected ExecutionKind (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class ExecutionKind
