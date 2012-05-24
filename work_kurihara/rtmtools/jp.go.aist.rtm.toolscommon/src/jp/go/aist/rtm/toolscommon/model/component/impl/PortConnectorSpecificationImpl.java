package jp.go.aist.rtm.toolscommon.model.component.impl;

/**
 * <copyright>
 * </copyright>
 *
 * $Id: PortConnectorSpecificationImpl.java,v 1.5 2008/04/03 01:53:59 yamashita Exp $
 */
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Port Connector Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class PortConnectorSpecificationImpl extends PortConnectorImpl implements
		PortConnector {

	public boolean createConnectorR(Port source, Port target,
			ConnectorProfile connectorProfile) {
		if (connectorProfile.getConnectorId() == null) {
			connectorProfile.setConnectorId(UUID.randomUUID().toString());
		}
		if (source != null) {
			connectorProfile.setSourceString(source.getOriginalPortString());
		}
		if (target != null) {
			connectorProfile.setTargetString(target.getOriginalPortString());
		}
		if (source != null) {
			addPortProfile(source, connectorProfile);
		}
		if (target != null) {
			addPortProfile(target, connectorProfile);
		}
		return true;
	}

	private void addPortProfile(Port port, ConnectorProfile connectorProfile) {
		for (Object object : port.getConnectorProfiles()) {
			if (connectorProfile.getConnectorId().equals(
					((ConnectorProfile) object).getConnectorId())) {
				return;
			}
		}
		port.getConnectorProfiles().add(connectorProfile);
	}

	@Override
	public boolean deleteConnectorR() {
		try {
			if (getSource() != null) {
				removeByID(getSource().getConnectorProfiles(),
						getConnectorProfile());
			}
			if (getTarget() != null) {
				removeByID(getTarget().getConnectorProfiles(),
						getConnectorProfile());
			}
			return true;
		} catch (RuntimeException e) {
			return false;
		}
	}

	private void removeByID(List<ConnectorProfile> profiles, ConnectorProfile cp) {
		List<ConnectorProfile> list = new ArrayList<ConnectorProfile>(profiles);
		for (ConnectorProfile e : list) {
			if (e.getConnectorId().equals(cp.getConnectorId())) {
				profiles.remove(e);
			}
		}
	}

	@Override
	public boolean createConnectorR() {
		return createConnectorR(getSource(), getTarget(), getConnectorProfile());

	}

} // PortConnectorSpecificationImpl
