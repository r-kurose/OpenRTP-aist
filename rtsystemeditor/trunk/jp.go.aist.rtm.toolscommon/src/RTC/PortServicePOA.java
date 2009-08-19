package RTC;


/**
* RTC/PortServicePOA.java .
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
public abstract class PortServicePOA extends org.omg.PortableServer.Servant
 implements RTC.PortServiceOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("get_port_profile", new java.lang.Integer (0));
    _methods.put ("get_connector_profiles", new java.lang.Integer (1));
    _methods.put ("get_connector_profile", new java.lang.Integer (2));
    _methods.put ("connect", new java.lang.Integer (3));
    _methods.put ("disconnect", new java.lang.Integer (4));
    _methods.put ("disconnect_all", new java.lang.Integer (5));
    _methods.put ("notify_connect", new java.lang.Integer (6));
    _methods.put ("notify_disconnect", new java.lang.Integer (7));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
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
       case 0:  // RTC/PortService/get_port_profile
       {
         RTC.PortProfile $result = null;
         $result = this.get_port_profile ();
         out = $rh.createReply();
         RTC.PortProfileHelper.write (out, $result);
         break;
       }


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
       case 1:  // RTC/PortService/get_connector_profiles
       {
         RTC.ConnectorProfile $result[] = null;
         $result = this.get_connector_profiles ();
         out = $rh.createReply();
         RTC.ConnectorProfileListHelper.write (out, $result);
         break;
       }


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
       case 2:  // RTC/PortService/get_connector_profile
       {
         String connector_id = RTC.UniqueIdentifierHelper.read (in);
         RTC.ConnectorProfile $result = null;
         $result = this.get_connector_profile (connector_id);
         out = $rh.createReply();
         RTC.ConnectorProfileHelper.write (out, $result);
         break;
       }


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
       case 3:  // RTC/PortService/connect
       {
         RTC.ConnectorProfileHolder connector_profile = new RTC.ConnectorProfileHolder ();
         connector_profile.value = RTC.ConnectorProfileHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.connect (connector_profile);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         RTC.ConnectorProfileHelper.write (out, connector_profile.value);
         break;
       }


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
       case 4:  // RTC/PortService/disconnect
       {
         String connector_id = RTC.UniqueIdentifierHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.disconnect (connector_id);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


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
       case 5:  // RTC/PortService/disconnect_all
       {
         RTC.ReturnCode_t $result = null;
         $result = this.disconnect_all ();
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }


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
       case 6:  // RTC/PortService/notify_connect
       {
         RTC.ConnectorProfileHolder connector_profile = new RTC.ConnectorProfileHolder ();
         connector_profile.value = RTC.ConnectorProfileHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.notify_connect (connector_profile);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         RTC.ConnectorProfileHelper.write (out, connector_profile.value);
         break;
       }


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
       case 7:  // RTC/PortService/notify_disconnect
       {
         String connector_id = RTC.UniqueIdentifierHelper.read (in);
         RTC.ReturnCode_t $result = null;
         $result = this.notify_disconnect (connector_id);
         out = $rh.createReply();
         RTC.ReturnCode_tHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:omg.org/RTC/PortService:1.0", 
    "IDL:org.omg/SDOPackage/SDOService:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public PortService _this() 
  {
    return PortServiceHelper.narrow(
    super._this_object());
  }

  public PortService _this(org.omg.CORBA.ORB orb) 
  {
    return PortServiceHelper.narrow(
    super._this_object(orb));
  }


} // class PortServicePOA
