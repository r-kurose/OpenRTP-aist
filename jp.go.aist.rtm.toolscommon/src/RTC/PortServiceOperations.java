package RTC;


/**
* RTC/PortServiceOperations.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief PortService
   *
   * @section Description
   *
   * An instance of the PortService interface represents a port (i.e.,
   * UML::Composite Structures::Ports::Port) of an RTC. It provides
   * operations that allow it to be connected to and disconnected from
   * other ports.
   *
   * @section Semantics
   *
   * A port service can support unidirectional or bidirectional
   * communication.  A port service may allow for a service-oriented
   * connection, in which other connected ports, invoke methods on
   * it. It may also allow for a data-centric connection, in which
   * data values are streamed in or out. In either case, the
   * connection is described by an instance of
   * ConnectorProfile. However, the behavioral contracts of such
   * connections are dependent on the interfaces exposed by the ports
   * and are not described normatively by this specification.
   *
   * @endif
   */
public interface PortServiceOperations  extends _SDOPackage.SDOServiceOperations
{

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief get_port_profile
     *
     * @section Description
     *
     * This operation returns the PortProfile of the PortService.
     *
     * @endif
     */
  RTC.PortProfile get_port_profile ();

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief get_connector_profiles
     *
     * @section Description
     *
     * This operation returns a list of the ConnectorProfiles of the
     * PortService.
     *
     * @endif
     */
  RTC.ConnectorProfile[] get_connector_profiles ();

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief get_connector_profiles
     *
     * @section Description
     *
     * This operation returns a list of the ConnectorProfiles of the
     * PortService.
     *
     * @endif
     */
  RTC.ConnectorProfile get_connector_profile (String connector_id);

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief connect
     *
     * @section Description
     *
     * This operation establishes connection between this port and the
     * peer ports according to given ConnectionProfile.
     *
     * @section Semantics
     *
     * A ConnectorProfile has a sequence of port references. This port
     * invokes the notify_connect operation of one of the ports
     * included in the sequence. It follows that the notification of
     * connection is propagated by the notify_connect operation with
     * ConnectorProfile. This operation returns ConnectorProfile
     * return value and returns ReturnCode_t as return codes.
     *
     * @endif
     */
  RTC.ReturnCode_t connect (RTC.ConnectorProfileHolder connector_profile);

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief disconnect
     *
     * @section Description
     *
     * This operation destroys the connection between this port and
     * its peer ports using the ID that was given when the connection
     * was established.
     *
     * @section Semantics
     *
     * This port invokes the notify_disconnect operation of one of the
     * ports included in the sequence of the ConnectorProfile stored
     * when the connection was established. The notification of
     * disconnection is propagated by the notify_disconnect operation.
     *
     * @endif
     */
  RTC.ReturnCode_t disconnect (String connector_id);

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief disconnect_all
     *
     * @section Description
     *
     * This operation destroys all connection channels owned by the
     * PortService.
     *
     * @endif
     */
  RTC.ReturnCode_t disconnect_all ();

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief notify_connect
     *
     * @section Description
     *
     * This operation notifies this PortService of the connection
     * between its corresponding port and the other ports and
     * propagates the given ConnectionProfile.
     *
     * @section Semantics
     *
     * A ConnectorProfile has a sequence of port references. This
     * PortService stores the ConnectorProfile and invokes the
     * notify_connect operation of the next PortService in the
     * sequence. As ports are added to the connector, PortService
     * references are added to the ConnectorProfile and provided to
     * the caller. In this way, notification of connection is
     * propagated with the ConnectorProfile.
     *
     * @endif
     */
  RTC.ReturnCode_t notify_connect (RTC.ConnectorProfileHolder connector_profile);

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief notify_disconnect
     *
     * @section Description
     *
     * This operation notifies a PortService of a disconnection
     * between its corresponding port and the other ports. The
     * disconnected connector is identified by the given ID, which was
     * given when the connection was established.
     *
     * @section Semantics
     *
     * This port invokes the notify_disconnect operation of the next
     * PortService in the sequence of the ConnectorProfile that was
     * stored when the connection was established. As ports are
     * disconnected, PortService references are removed from the
     * ConnectorProfile. In this way, the notification of
     * disconnection is propagated by the notify_disconnect operation.
     *
     * @endif
     */
  RTC.ReturnCode_t notify_disconnect (String connector_id);
} // interface PortServiceOperations
