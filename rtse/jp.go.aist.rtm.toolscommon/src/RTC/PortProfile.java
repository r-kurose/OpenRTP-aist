package RTC;


/**
* RTC/PortProfile.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief PortProfile
   *
   * @section Description
   *
   * A PortProfile describes a port of an RTC (referred to as the
   * "target" RTC). This port is referred to as the "target" port.
   * From this profile, other components and tools can obtain Porta?s
   * name, type, object reference, and so on.
   *
   * @endif
   */
public final class PortProfile implements org.omg.CORBA.portable.IDLEntity
{

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief name
     *
     * @section Description
     *
     * This attribute contains the name of the target port.
     *
     * @section Semantics
     *
     * Ports owned by an RTC are distinguished by their
     * names. Therefore, this name should be unique within the target
     * RTC.
     *
     * @endif
     */
  public String name = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief interfaces
     *
     * @section Description
     *
     * This attribute contains the name and polarity of each interface
     * exposed by the target port.
     *
     * @endif
     */
  public RTC.PortInterfaceProfile interfaces[] = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief port_ref
     *
     * @section Description
     *
     * This attributes contains a reference to the target port.
     *
     * @endif
     */
  public RTC.PortService port_ref = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief connector_profiles
     *
     * @section Description
     *
     * This attribute contains a collection of profiles describing the
     * connections to the target port.
     *
     * @endif
     */
  public RTC.ConnectorProfile connector_profiles[] = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief owner
     *
     * @section Description
     *
     * This attribute contains a reference to the target RTC.
     *
     * @endif
     */
  public RTC.RTObject owner = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief properties
     *
     * @section Description
     *
     * This attribute contains additional properties of the port.
     *
     * @section Semantics
     *
     * This attribute provides implementations the opportunity to
     * describe additional characteristics of a particular port that
     * are otherwise outside of the scope of this specification.
     *
     * @endif
     */
  public _SDOPackage.NameValue properties[] = null;

  public PortProfile ()
  {
  } // ctor

  public PortProfile (String _name, RTC.PortInterfaceProfile[] _interfaces, RTC.PortService _port_ref, RTC.ConnectorProfile[] _connector_profiles, RTC.RTObject _owner, _SDOPackage.NameValue[] _properties)
  {
    name = _name;
    interfaces = _interfaces;
    port_ref = _port_ref;
    connector_profiles = _connector_profiles;
    owner = _owner;
    properties = _properties;
  } // ctor

} // class PortProfile
