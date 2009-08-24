package RTC;


/**
* RTC/LifeCycleState.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分46秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief LifeCycleState
   *
   * @section Description
   * LifeCycleState is an enumeration of the states in the lifecycle above.
   *
   * @endif
   */
public class LifeCycleState implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 4;
  private static RTC.LifeCycleState[] __array = new RTC.LifeCycleState [__size];

  public static final int _CREATED_STATE = 0;
  public static final RTC.LifeCycleState CREATED_STATE = new RTC.LifeCycleState(_CREATED_STATE);
  public static final int _INACTIVE_STATE = 1;
  public static final RTC.LifeCycleState INACTIVE_STATE = new RTC.LifeCycleState(_INACTIVE_STATE);
  public static final int _ACTIVE_STATE = 2;
  public static final RTC.LifeCycleState ACTIVE_STATE = new RTC.LifeCycleState(_ACTIVE_STATE);
  public static final int _ERROR_STATE = 3;
  public static final RTC.LifeCycleState ERROR_STATE = new RTC.LifeCycleState(_ERROR_STATE);

  public int value ()
  {
    return __value;
  }

  public static RTC.LifeCycleState from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected LifeCycleState (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class LifeCycleState
