package RTC;


/**
* RTC/_PortServiceStub.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分45秒 JST
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
public class _PortServiceStub extends org.omg.CORBA.portable.ObjectImpl implements RTC.PortService
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
  public RTC.PortProfile get_port_profile ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_port_profile", true);
                $in = _invoke ($out);
                RTC.PortProfile $result = RTC.PortProfileHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_port_profile (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_port_profile


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
  public RTC.ConnectorProfile[] get_connector_profiles ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_connector_profiles", true);
                $in = _invoke ($out);
                RTC.ConnectorProfile $result[] = RTC.ConnectorProfileListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_connector_profiles (        );
            } finally {
                _releaseReply ($in);
            }
  } // get_connector_profiles


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
  public RTC.ConnectorProfile get_connector_profile (String connector_id)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_connector_profile", true);
                RTC.UniqueIdentifierHelper.write ($out, connector_id);
                $in = _invoke ($out);
                RTC.ConnectorProfile $result = RTC.ConnectorProfileHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_connector_profile (connector_id        );
            } finally {
                _releaseReply ($in);
            }
  } // get_connector_profile


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
  public RTC.ReturnCode_t connect (RTC.ConnectorProfileHolder connector_profile)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("connect", true);
                RTC.ConnectorProfileHelper.write ($out, connector_profile.value);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                connector_profile.value = RTC.ConnectorProfileHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return connect (connector_profile        );
            } finally {
                _releaseReply ($in);
            }
  } // connect


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
  public RTC.ReturnCode_t disconnect (String connector_id)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("disconnect", true);
                RTC.UniqueIdentifierHelper.write ($out, connector_id);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return disconnect (connector_id        );
            } finally {
                _releaseReply ($in);
            }
  } // disconnect


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
  public RTC.ReturnCode_t disconnect_all ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("disconnect_all", true);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return disconnect_all (        );
            } finally {
                _releaseReply ($in);
            }
  } // disconnect_all


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
  public RTC.ReturnCode_t notify_connect (RTC.ConnectorProfileHolder connector_profile)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("notify_connect", true);
                RTC.ConnectorProfileHelper.write ($out, connector_profile.value);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                connector_profile.value = RTC.ConnectorProfileHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return notify_connect (connector_profile        );
            } finally {
                _releaseReply ($in);
            }
  } // notify_connect


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
  public RTC.ReturnCode_t notify_disconnect (String connector_id)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("notify_disconnect", true);
                RTC.UniqueIdentifierHelper.write ($out, connector_id);
                $in = _invoke ($out);
                RTC.ReturnCode_t $result = RTC.ReturnCode_tHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return notify_disconnect (connector_id        );
            } finally {
                _releaseReply ($in);
            }
  } // notify_disconnect

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:omg.org/RTC/PortService:1.0", 
    "IDL:org.omg/SDOPackage/SDOService:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.Object obj = org.omg.CORBA.ORB.init (args, props).string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     String str = org.omg.CORBA.ORB.init (args, props).object_to_string (this);
     s.writeUTF (str);
  }
} // class _PortServiceStub
