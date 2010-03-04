package jp.go.aist.rtm.rtcbuilder.ui.preference;

import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class BuilderViewPreferencePage extends AbstarctFieldEditorPreferencePage implements
							IWorkbenchPreferencePage {
	public BuilderViewPreferencePage(){
		setPreferenceStore(RtcBuilderPlugin.getDefault().getPreferenceStore());
	}
	@Override
	public void init(IWorkbench workbench) {
		IPreferenceStore store = RtcBuilderPlugin.getDefault().getPreferenceStore();
		PreferenceConverter.setDefault(store, BuilderViewPreferenceManager.COLOR_COMPONENT, 
				BuilderViewPreferenceManager.defaultRGBMap.get(BuilderViewPreferenceManager.COLOR_COMPONENT));
		PreferenceConverter.setDefault(store, BuilderViewPreferenceManager.COLOR_DATAINPORT, 
				BuilderViewPreferenceManager.defaultRGBMap.get(BuilderViewPreferenceManager.COLOR_DATAINPORT));
		PreferenceConverter.setDefault(store, BuilderViewPreferenceManager.COLOR_DATAOUTPORT, 
				BuilderViewPreferenceManager.defaultRGBMap.get(BuilderViewPreferenceManager.COLOR_DATAOUTPORT));
		PreferenceConverter.setDefault(store, BuilderViewPreferenceManager.COLOR_SERVICEIF, 
				BuilderViewPreferenceManager.defaultRGBMap.get(BuilderViewPreferenceManager.COLOR_SERVICEIF));
		PreferenceConverter.setDefault(store, BuilderViewPreferenceManager.COLOR_SERVICEPORT, 
				BuilderViewPreferenceManager.defaultRGBMap.get(BuilderViewPreferenceManager.COLOR_SERVICEPORT));
	}

	@Override
	protected void createFieldEditors() {
		Composite composite = new Composite(getFieldEditorParent(), SWT.NULL);
		composite.setLayout(new GridLayout());
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.grabExcessHorizontalSpace = true;
		composite.setLayoutData(gd);
		Composite colorGroup = createGroup(composite, "");
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 5;
		colorGroup.setLayout(gridLayout);
		//
		ColorFieldEditor componentEditor = 
			new ColorFieldEditor(BuilderViewPreferenceManager.COLOR_COMPONENT,
					IPreferenceMessageConstants.BUILDER_VIEW_LBL_COMPONENT, colorGroup);
		addField(componentEditor);
		ColorFieldEditor inPortEditor = 
			new ColorFieldEditor(BuilderViewPreferenceManager.COLOR_DATAINPORT,
					IPreferenceMessageConstants.BUILDER_VIEW_LBL_DATAINPORT, colorGroup);
		addField(inPortEditor);
		ColorFieldEditor outPortEditor = 
			new ColorFieldEditor(BuilderViewPreferenceManager.COLOR_DATAOUTPORT,
					IPreferenceMessageConstants.BUILDER_VIEW_LBL_DATAOUTPORT, colorGroup);
		addField(outPortEditor);
		ColorFieldEditor servicePortEditor = 
			new ColorFieldEditor(BuilderViewPreferenceManager.COLOR_SERVICEPORT,
					IPreferenceMessageConstants.BUILDER_VIEW_LBL_SERVICEPORT, colorGroup);
		addField(servicePortEditor);
		ColorFieldEditor serviceIFEditor = 
			new ColorFieldEditor(BuilderViewPreferenceManager.COLOR_SERVICEIF,
					IPreferenceMessageConstants.BUILDER_VIEW_LBL_SERVICEIF, colorGroup);
		addField(serviceIFEditor);
	}
}
