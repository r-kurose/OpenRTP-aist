/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import RTC.ConnectorProfileHolder;
import RTC.PortService;
import RTC.ReturnCode_t;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Corba Port Connector</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class CorbaPortConnectorImpl extends PortConnectorImpl implements PortConnector {

	@Override
	public boolean createConnectorR() {
		return createConnectorR(getSource(), getTarget(), getConnectorProfile());
	}

	public boolean createConnectorR(
			jp.go.aist.rtm.toolscommon.model.component.Port first,
			jp.go.aist.rtm.toolscommon.model.component.Port second,
			ConnectorProfile connectorProfile) {
		try {
			RTC.ConnectorProfile profile = new RTC.ConnectorProfile();
			profile.connector_id = connectorProfile.getConnectorId();
			if (profile.connector_id == null) {
				profile.connector_id = "";
			}

			profile.name = connectorProfile.getName();
			profile.ports = new RTC.PortService[] { getCorbaObjectInterface(first),
					getCorbaObjectInterface(second) };
			profile.properties = CorbaConnectorProfileImpl.createProperties(connectorProfile);

			ConnectorProfileHolder connectorProfileHolder = new ConnectorProfileHolder(
					profile);
			getCorbaObjectInterface(first).connect(connectorProfileHolder);

			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		return false;
	}

	private PortService getCorbaObjectInterface(Port port) {
		CorbaPortSynchronizer synchronizer = (CorbaPortSynchronizer) port.getSynchronizer();
		return (PortService) synchronizer.getCorbaObjectInterface();
	}

	public boolean deleteConnectorR() {
		try {
			RTC.PortService inport = getCorbaObjectInterface(getTarget());

			ReturnCode_t code = inport.disconnect(this.getConnectorProfile()
					.getConnectorId());

			if (code == ReturnCode_t.RTC_OK) {
				return true;
			}
		} catch (RuntimeException e) {
			// void
		}

		return false;
	}
} //CorbaPortConnectorImpl
