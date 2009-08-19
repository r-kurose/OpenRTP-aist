/**
 * <copyright>
 * </copyright>
 *
 * $Id: OutPortImpl.java,v 1.2 2008/03/14 05:35:51 yamashita Exp $
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.ui.propertysource.PortPropertySource;
import jp.go.aist.rtm.toolscommon.util.ConnectorUtil;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Out Port</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class OutPortImpl extends PortImpl implements OutPort {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected OutPortImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.OUT_PORT;
	}

	@Override
	public boolean validateSourceConnector(Port source) {
		return false;
	}

	public boolean validateTargetConnector(Port target) {
		if (target instanceof InPort == false) {
			return false;
		}
		if (isAllowDataType((InPort) target)) return true;
		if (this.eContainer instanceof ComponentSpecification){
			if (ConnectorUtil.getAllowDataTypes(this, (InPort) target)
					.size() >= 1
					|| ConnectorUtil.isAllowAnyDataType(this,
							(InPort) target)) {
				return true;
			}
		}
		return false;
	}

	private boolean isAllowDataType(InPort target) {
		if (ConnectorUtil.getAllowDataTypes(this, target).size() < 1
				&& !ConnectorUtil.isAllowAnyDataType(this,target)) 
			return false;
		if (ConnectorUtil.getAllowDataflowTypes(this, target).size() < 1
				&& !ConnectorUtil.isAllowAnyDataflowType(this,target)) 
			return false;
		if (ConnectorUtil.getAllowInterfaceTypes(this, target).size() < 1
				&& !ConnectorUtil.isAllowAnyInterfaceType(this, target)) 
			return false;
		if (ConnectorUtil.getAllowSubscriptionTypes(this, target).size() < 1
				&& !ConnectorUtil.isAllowAnySubscriptionType(this, target)) 
			return false;
		return true;
	}

	@SuppressWarnings("unchecked")
	public java.lang.Object getAdapter(Class adapter) {
		java.lang.Object result = null;
		if (IPropertySource.class.equals(adapter)) {
			result = new PortPropertySource(this);
		}

		if (result == null) {
			result = super.getAdapter(adapter);
		}

		return result;
	}


} // OutPortImpl
