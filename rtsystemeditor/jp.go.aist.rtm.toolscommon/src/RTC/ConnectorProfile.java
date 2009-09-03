package RTC;


/**
* RTC/ConnectorProfile.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief ConnectorProfile
   *
   * @section Description
   *
   * The ConnectorProfile contains information about a connection
   * between the ports of collaborating RTCs.
   *
   * @endif
   */
public final class ConnectorProfile implements org.omg.CORBA.portable.IDLEntity
{

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief name
     *
     * @section Description
     *
     * This attribute contains the name of this connection.
     *
     * @endif
     */
  public String name = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief connector_id
     *
     * @section Description
     *
     * Each connector has a unique identifier that is assigned when
     * connection is established. This attribute stores that
     * identifier.
     *
     * @endif
     */
  public String connector_id = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief ports
     *
     * @section Description
     *
     * This field stores references to all ports connected by the
     * target connector.
     *
     * @endif
     */
  public RTC.PortService ports[] = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief properties
     *
     * @section Description
     *
     * This attribute contains additional properties of the connection.
     *
     * @section Semantics
     *
     * This attribute provides implementations the opportunity to
     * describe additional characteristics of a particular connection
     * that are outside of the scope of this specification.
     *
     * @endif
     */
  public _SDOPackage.NameValue properties[] = null;

  public ConnectorProfile ()
  {
  } // ctor

  public ConnectorProfile (String _name, String _connector_id, RTC.PortService[] _ports, _SDOPackage.NameValue[] _properties)
  {
    name = _name;
    connector_id = _connector_id;
    ports = _ports;
    properties = _properties;
  } // ctor

} // class ConnectorProfile
