package jp.go.aist.rtm.rtcbuilder.ui.preference;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

public class CodeGeneratePortPreferencePage extends AbstractPreferencePage {

	private Text dataPortNameText;
	private Text dataPortTypeText;
	private Text dataPortVarNameText;
	private Text dataPortConstraintText;
	private Text dataPortUnitText;
	private Text dataPortPrefixText;
	private Text dataPortSuffixText;
	//
	private Text servicePortNameText;
	private Text servicePortPrefixText;
	private Text servicePortSuffixText;
	//
	private Text serviceIFNameText;
	private Text serviceIFInstanceNameText;
	private Text serviceIFVarNameText;
	private Text serviceIFPrefixText;
	private Text serviceIFSuffixText;
	//

	public CodeGeneratePortPreferencePage() {
	}

	public CodeGeneratePortPreferencePage(String title) {
		super(title);
	}

	public CodeGeneratePortPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
	}

	@Override
	protected Control createContents(Composite parent) {
		GridData gd;

		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout());
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.grabExcessHorizontalSpace = true;
		composite.setLayoutData(gd);

		//Data Port Psge
		Group dataportGroup = createGroup(composite, IPreferenceMessageConstants.PORT_TITLE_DATA_PORT);
		dataPortNameText = createLabelAndText(dataportGroup, IPreferenceMessageConstants.PORT_LBL_DATAPORT_NAME);
		dataPortNameText.setText(String.valueOf(
				PortPreferenceManager.getInstance().getDataPort_Name()));
		dataPortTypeText = createLabelAndText(dataportGroup, IPreferenceMessageConstants.PORT_LBL_DATAPORT_TYPE);
		dataPortTypeText.setText(String.valueOf(
				PortPreferenceManager.getInstance().getDataPort_Type()));
		dataPortVarNameText = createLabelAndText(dataportGroup, IPreferenceMessageConstants.PORT_LBL_DATAPORT_VARIABLE_NAME);
		dataPortVarNameText.setText(String.valueOf(
				PortPreferenceManager.getInstance().getDataPort_VarName()));
		dataPortConstraintText = createLabelAndText(dataportGroup, IPreferenceMessageConstants.PORT_LBL_DATAPORT_CONSTRAINT);
		dataPortConstraintText.setText(String.valueOf(
				PortPreferenceManager.getInstance().getDataPort_Constraint()));
		dataPortUnitText = createLabelAndText(dataportGroup, IPreferenceMessageConstants.PORT_LBL_DATAPORT_UNIT);
		dataPortUnitText.setText(String.valueOf(
				PortPreferenceManager.getInstance().getDataPort_Unit()));
		dataPortPrefixText = createLabelAndText(dataportGroup, IPreferenceMessageConstants.PORT_LBL_PREFIX);
		dataPortPrefixText.setText(String.valueOf(
				PortPreferenceManager.getInstance().getDataPort_Prefix()));
		dataPortSuffixText = createLabelAndText(dataportGroup, IPreferenceMessageConstants.PORT_LBL_SUFFIX);
		dataPortSuffixText.setText(String.valueOf(
				PortPreferenceManager.getInstance().getDataPort_Suffix()));
		//Service Port Page
		Group serviceportGroup = createGroup(composite, IPreferenceMessageConstants.PORT_TITLE_SERVICE_PORT);
		servicePortNameText = createLabelAndText(serviceportGroup, IPreferenceMessageConstants.PORT_LBL_SERVICEPORT_NAME);
		servicePortNameText.setText(String.valueOf(
				PortPreferenceManager.getInstance().getServicePort_Name()));
		servicePortPrefixText = createLabelAndText(serviceportGroup, IPreferenceMessageConstants.PORT_LBL_PREFIX);
		servicePortPrefixText.setText(String.valueOf(
				PortPreferenceManager.getInstance().getServicePort_Prefix()));
		servicePortSuffixText = createLabelAndText(serviceportGroup, IPreferenceMessageConstants.PORT_LBL_SUFFIX);
		servicePortSuffixText.setText(String.valueOf(
				PortPreferenceManager.getInstance().getServicePort_Suffix()));
		//Service I/F Page
		Group serviceIFGroup = createGroup(composite, IPreferenceMessageConstants.PORT_TITLE_SERVICE_INTERFACE);
		serviceIFNameText = createLabelAndText(serviceIFGroup, IPreferenceMessageConstants.PORT_LBL_INTERFACE_NAME);
		serviceIFNameText.setText(String.valueOf(
				PortPreferenceManager.getInstance().getServiceIF_Name()));
		serviceIFInstanceNameText = createLabelAndText(serviceIFGroup, IPreferenceMessageConstants.PORT_LBL_INSTANCE_NAME);
		serviceIFInstanceNameText.setText(String.valueOf(
				PortPreferenceManager.getInstance().getServiceIF_InstanceName()));
		serviceIFVarNameText = createLabelAndText(serviceIFGroup, IPreferenceMessageConstants.PORT_LBL_VARIABLE_NAME);
		serviceIFVarNameText.setText(String.valueOf(
				PortPreferenceManager.getInstance().getServiceIF_VarName()));
		serviceIFPrefixText = createLabelAndText(serviceIFGroup, IPreferenceMessageConstants.PORT_LBL_PREFIX);
		serviceIFPrefixText.setText(String.valueOf(
				PortPreferenceManager.getInstance().getServiceIF_Prefix()));
		serviceIFSuffixText = createLabelAndText(serviceIFGroup, IPreferenceMessageConstants.PORT_LBL_SUFFIX);
		serviceIFSuffixText.setText(String.valueOf(
				PortPreferenceManager.getInstance().getServiceIF_Suffix()));

		return null;
	}

	@Override
	protected void performDefaults() {
		setDefault();

		super.performDefaults();
	}

	@Override
	public boolean performOk() {
		PortPreferenceManager.getInstance().setDataPort_Name(dataPortNameText.getText());
		PortPreferenceManager.getInstance().setDataPort_Type(dataPortTypeText.getText());
		PortPreferenceManager.getInstance().setDataPort_VarName(dataPortVarNameText.getText());
		PortPreferenceManager.getInstance().setDataPort_Constraint(dataPortConstraintText.getText());
		PortPreferenceManager.getInstance().setDataPort_Unit(dataPortUnitText.getText());
		PortPreferenceManager.getInstance().setDataPort_Prefix(dataPortPrefixText.getText());
		PortPreferenceManager.getInstance().setDataPort_Suffix(dataPortSuffixText.getText());
		//
		PortPreferenceManager.getInstance().setServicePort_Name(servicePortNameText.getText());
		PortPreferenceManager.getInstance().setServicePort_Prefix(servicePortPrefixText.getText());
		PortPreferenceManager.getInstance().setServicePort_Suffix(servicePortSuffixText.getText());
		//
		PortPreferenceManager.getInstance().setServiceIF_Name(serviceIFNameText.getText());
		PortPreferenceManager.getInstance().setServiceIF_InstanceName(serviceIFInstanceNameText.getText());
		PortPreferenceManager.getInstance().setServiceIF_VarName(serviceIFVarNameText.getText());
		PortPreferenceManager.getInstance().setServiceIF_Prefix(serviceIFPrefixText.getText());
		PortPreferenceManager.getInstance().setServiceIF_Suffix(serviceIFSuffixText.getText());
	
		PortPreferenceManager.getInstance().setDirtyToPortPreferenceStatus();

		return super.performOk();
	}

	private void setDefault() {
		dataPortNameText.setText(PortPreferenceManager.DEFAULT_DATAPORT_NAME);
		dataPortTypeText.setText(PortPreferenceManager.DEFAULT_DATAPORT_TYPE);
		dataPortVarNameText.setText(PortPreferenceManager.DEFAULT_DATAPORT_VARNAME);
		dataPortConstraintText.setText(PortPreferenceManager.DEFAULT_DATAPORT_CONSTRAINT);
		dataPortUnitText.setText(PortPreferenceManager.DEFAULT_DATAPORT_UNIT);
		dataPortPrefixText.setText(PortPreferenceManager.DEFAULT_DATAPORT_PREFIX);
		dataPortSuffixText.setText(PortPreferenceManager.DEFAULT_DATAPORT_SUFFIX);
		//
		servicePortNameText.setText(PortPreferenceManager.DEFAULT_SERVICEPORT_NAME);
		servicePortPrefixText.setText(PortPreferenceManager.DEFAULT_SERVICEPORT_PREFIX);
		servicePortSuffixText.setText(PortPreferenceManager.DEFAULT_SERVICEPORT_SUFFIX);
		//
		serviceIFNameText.setText(PortPreferenceManager.DEFAULT_SERVICEIF_NAME);
		serviceIFInstanceNameText.setText(PortPreferenceManager.DEFAULT_SERVICEIF_INSTANCENAME);
		serviceIFVarNameText.setText(PortPreferenceManager.DEFAULT_SERVICEIF_VARNAME);
		serviceIFPrefixText.setText(PortPreferenceManager.DEFAULT_SERVICEIF_PREFIX);
		serviceIFSuffixText.setText(PortPreferenceManager.DEFAULT_SERVICEIF_SUFFIX);
	}

	@Override
	protected boolean validate() {
		return true;
	}
}
