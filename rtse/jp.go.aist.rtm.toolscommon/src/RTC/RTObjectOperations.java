package RTC;


/**
* RTC/RTObjectOperations.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief RTObject
   *
   * @section Description
   *
   * The RTObject interface defines the operations that all SDO-based
   * RTCs must provide. It is required by the rtComponent stereotype.
   *
   * @endif
   */
public interface RTObjectOperations  extends RTC.LightweightRTObjectOperations, _SDOPackage.SDOOperations
{

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief get_component_profile
     *
     * @section Description
     *
     * This operation returns the ComponentProfile of the RTC.
     *
     * @endif
     */
  RTC.ComponentProfile get_component_profile ();

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief get_ports
     *
     * @section Description
     *
     * This operation returns a list of the RTCs ports.
     *
     * @endif
     */
  RTC.PortService[] get_ports ();
} // interface RTObjectOperations
