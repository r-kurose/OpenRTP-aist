/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CorbaPortConnectorImpl.class);

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

			List<RTC.PortService> portLists = new ArrayList<RTC.PortService>();
			RTC.PortService firstPortService = null;
			RTC.PortService secondPortService = null;
			if (first != null) {
				firstPortService = getCorbaObjectInterface(first);
				portLists.add(firstPortService);
			}
			if (second != null) {
				secondPortService = getCorbaObjectInterface(second);
				portLists.add(secondPortService);
			}
			profile.ports = portLists.toArray(new RTC.PortService[0]);
			profile.properties = CorbaConnectorProfileImpl
					.createProperties(connectorProfile);

			ConnectorProfileHolder connectorProfileHolder = new ConnectorProfileHolder(
					profile);
			RTC.ReturnCode_t ret = RTC.ReturnCode_t.BAD_PARAMETER;
			if (firstPortService != null) {
				ret = firstPortService.connect(connectorProfileHolder);
			} else if (secondPortService != null) {
				ret = secondPortService.connect(connectorProfileHolder);
			}
			return ret == RTC.ReturnCode_t.RTC_OK;

		} catch (RuntimeException e) {
			LOGGER.error("Fail to create connector", e);
		}

		return false;
	}

	private PortService getCorbaObjectInterface(Port port) {
		CorbaPortSynchronizer synchronizer = (CorbaPortSynchronizer) port
				.getSynchronizer();
		return (PortService) synchronizer.getCorbaObjectInterface();
	}

	@Override
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

} // CorbaPortConnectorImpl
