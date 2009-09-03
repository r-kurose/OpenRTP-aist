package jp.go.aist.rtm.toolscommon.ui.propertysource;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.nl.Messages;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * PortInterfaceProfileのPropertySourceクラス
 */
public class ExecutionContextPropertySource implements IPropertySource {

	private static final String DISP_EC_ID = "ID";

	private static final String DISP_STATE = Messages.getString("ExecutionContextPropertySource.disp.state");

	private static final String DISP_KIND = Messages.getString("ExecutionContextPropertySource.disp.kind");

	private static final String DISP_RATE = Messages.getString("ExecutionContextPropertySource.disp.rate");

	private static final String EC_ID = "ID";

	private static final String STATE = "STATE";

	private static final String KIND = "KIND";

	private static final String RATE = "RATE";

	private static final String UNKNOWN = Messages.getString("ExecutionContextPropertySource.unknown");

	private static final String STATE_RUNNING_LABEL = Messages.getString("ExecutionContextPropertySource.state.running");

	private static final String STATE_STOPPED_LABEL = Messages.getString("ExecutionContextPropertySource.state.stopped");

	private static final String STATE_UNKNOWN_LABEL = Messages.getString("ExecutionContextPropertySource.state.unknown");

	private static final String KIND_EVENT_DRIVEN_LABEL = Messages.getString("ExecutionContextPropertySource.kind.event_driven");

	private static final String KIND_PERIODIC_LABEL = Messages.getString("ExecutionContextPropertySource.kind.periodic");

	private static final String KIND_OTHER_LABEL = Messages.getString("ExecutionContextPropertySource.kind.other");

	private static final IPropertyDescriptor[] PROPERTY_DESCRIPTORS = new IPropertyDescriptor[] {
			new TextPropertyDescriptor(EC_ID, DISP_EC_ID),
			new TextPropertyDescriptor(KIND, DISP_KIND),
			new TextPropertyDescriptor(RATE, DISP_RATE) };

	private static final IPropertyDescriptor[] ONLINE_PROPERTY_DESCRIPTORS = new IPropertyDescriptor[] {
			new TextPropertyDescriptor(EC_ID, DISP_EC_ID),
			new TextPropertyDescriptor(STATE, DISP_STATE),
			new TextPropertyDescriptor(KIND, DISP_KIND),
			new TextPropertyDescriptor(RATE, DISP_RATE) };

	private ExecutionContext delegate;

	/**
	 * コンストラクタ
	 * 
	 * @param delegate
	 *            モデル
	 */
	public ExecutionContextPropertySource(ExecutionContext delegate) {
		this.delegate = delegate;
	}

	/**
	 * {@inheritDoc}
	 */
	public java.lang.Object getPropertyValue(java.lang.Object id) {
		String result = UNKNOWN;
		try {
			if (STATE.equals(id)) {
				int value = delegate.getStateL();
				if (value == ExecutionContext.STATE_RUNNING) {
					result = STATE_RUNNING_LABEL;
				} else if (value == ExecutionContext.STATE_STOPPED) {
					result = STATE_STOPPED_LABEL;
				} else if (value == ExecutionContext.STATE_UNKNOWN) {
					result = STATE_UNKNOWN_LABEL;
				}
			} else if (KIND.equals(id)) {
				int value = delegate.getKindL();
				if (value == ExecutionContext.KIND_EVENT_DRIVEN) {
					result = KIND_EVENT_DRIVEN_LABEL;
				} else if (value == ExecutionContext.KIND_PERIODIC) {
					result = KIND_PERIODIC_LABEL;
				} else if (value == ExecutionContext.KIND_OTHER) {
					result = KIND_OTHER_LABEL;
				}
			} else if (RATE.equals(id)) {
				result = String.valueOf(delegate.getRateL());
			} else if (EC_ID.equals(id)) {
				if (delegate.eContainer() instanceof Component) {
					Component cp = (Component) delegate.eContainer();
					String ecid = cp.getExecutionContextId(delegate);
					if (ecid != null) {
						result = ecid;
					}
				}
			}
		} catch (Exception e) {
			// void
		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isPropertySet(java.lang.Object id) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void resetPropertyValue(java.lang.Object id) {
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPropertyValue(java.lang.Object id, java.lang.Object value) {
	}

	/**
	 * {@inheritDoc}
	 */
	public java.lang.Object getEditableValue() {
		return null;
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		if (this.delegate instanceof CorbaExecutionContext) {
			return ONLINE_PROPERTY_DESCRIPTORS;
		}
		return PROPERTY_DESCRIPTORS;
	}
}
