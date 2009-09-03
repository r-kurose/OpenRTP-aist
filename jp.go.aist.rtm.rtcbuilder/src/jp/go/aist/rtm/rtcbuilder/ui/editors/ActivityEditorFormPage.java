package jp.go.aist.rtm.rtcbuilder.ui.editors;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

/**
 * アクティビティページ
 */
public class ActivityEditorFormPage extends AbstractEditorFormPage {

	private List<Button> implChk;
	private Text actionNameText;
	private Text activityText;
	private Text preConditionText;
	private Text postConditionText;
	//
	private int preSelection;

	/**
	 * コンストラクタ
	 * 
	 * @param editor
	 *            親のエディタ
	 */
	public ActivityEditorFormPage(RtcBuilderEditor editor) {
		super(editor, "id", IMessageConstants.ACTIVITY_SECTION);
		//
		preSelection = -1;
		implChk = new ArrayList<Button>();
	}

	/**
	 * {@inheritDoc}
	 */
	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = super.createBase(managedForm);
		FormToolkit toolkit = managedForm.getToolkit();

		Label label = toolkit.createLabel(form.getBody(), IMessageConstants.ACTIVITY_SECTION);
		if( titleFont==null ) {
			titleFont = new Font(form.getDisplay(), IMessageConstants.TITLE_FONT, 16, SWT.BOLD);
		}
		label.setFont(titleFont);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);
		//
		createActivitySection(managedForm.getToolkit(), form);
		createHintSection(toolkit, form);
		createDocumentSection(managedForm.getToolkit(), form);

		load();
	}

	private void createActivitySection(FormToolkit toolkit, ScrolledForm form) {

		Composite composite = createSectionBaseWithLabel(toolkit, form, 
				IMessageConstants.ACTIVITY_ACTIVITY_TITLE, IMessageConstants.ACTIVITY_ACTIVITY_EXPL, 1);
		//
		//
		Composite cmpInitFinal = toolkit.createComposite(composite, SWT.NULL);
		GridLayout gl = new GridLayout(1, false);
		cmpInitFinal.setLayout(gl);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		cmpInitFinal.setLayoutData(gd);
		cmpInitFinal.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		//
		Label initFinal = toolkit.createLabel(cmpInitFinal, IMessageConstants.ACTIVITY_LBL_INIT_FINAL);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalAlignment = GridData.CENTER;
		initFinal.setLayoutData(gd);
		initFinal.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		createActionSelection(composite, "on_initialize");
		createActionSelection(composite, "on_finalize");
		//
		//
		Composite cmpStartEnd = toolkit.createComposite(composite, SWT.NULL);
		gl = new GridLayout(1, false);
		cmpStartEnd.setLayout(gl);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		cmpStartEnd.setLayoutData(gd);
		cmpStartEnd.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		//
		Label startEnd = toolkit.createLabel(cmpStartEnd, IMessageConstants.ACTIVITY_LBL_START_END);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalAlignment = GridData.CENTER;
		startEnd.setLayoutData(gd);
		startEnd.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		createActionSelection(composite, "on_startup");
		createActionSelection(composite, "on_shutdown");
		//
		//
		Composite cmpAlive = toolkit.createComposite(composite, SWT.NULL);
		gl = new GridLayout(1, false);
		cmpAlive.setLayout(gl);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		cmpAlive.setLayoutData(gd);
		cmpAlive.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		//
		Label alive = toolkit.createLabel(cmpAlive, IMessageConstants.ACTIVITY_LBL_ALIVE);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalAlignment = GridData.CENTER;
		alive.setLayoutData(gd);
		alive.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		createActionSelection(composite, "on_activated");
		createActionSelection(composite, "on_deactivated");
		createActionSelection(composite, "on_aborting");
		createActionSelection(composite, "on_error");
		createActionSelection(composite, "on_reset");
		//
		//
		Composite cmpDataFlow = toolkit.createComposite(composite, SWT.NULL);
		gl = new GridLayout(1, false);
		cmpDataFlow.setLayout(gl);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		cmpDataFlow.setLayoutData(gd);
		cmpDataFlow.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		//
		Label dataflow = toolkit.createLabel(cmpDataFlow, IMessageConstants.ACTIVITY_LBL_DATAFLOW);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalAlignment = GridData.CENTER;
		dataflow.setLayoutData(gd);
		dataflow.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		createActionSelection(composite, "on_execute");
		createActionSelection(composite, "on_state_update");
		createActionSelection(composite, "on_rate_changed");
		//
		//
		Composite cmpFsm = toolkit.createComposite(composite, SWT.NULL);
		gl = new GridLayout(1, false);
		cmpFsm.setLayout(gl);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		cmpFsm.setLayoutData(gd);
		cmpFsm.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		//
		Label fsm = toolkit.createLabel(cmpFsm, IMessageConstants.ACTIVITY_LBL_FSM);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalAlignment = GridData.CENTER;
		fsm.setLayoutData(gd);
		fsm.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		createActionSelection(composite, "on_action");
		//
		//
		Composite cmpMode = toolkit.createComposite(composite, SWT.NULL);
		gl = new GridLayout(1, false);
		cmpMode.setLayout(gl);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		cmpMode.setLayoutData(gd);
		cmpMode.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		//
		Label mode = toolkit.createLabel(cmpMode, IMessageConstants.ACTIVITY_LBL_MODE);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalAlignment = GridData.CENTER;
		mode.setLayoutData(gd);
		mode.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		createActionSelection(composite, "on_mode_changed");
		//
	}

	private void createActionSelection(Composite composite, String actionName) {
		Button impl = new Button(composite, SWT.CHECK);
		impl.setText(actionName);
		impl.addSelectionListener(new SelectionListener() {
			  public void widgetDefaultSelected(SelectionEvent e){
			  }
			  public void widgetSelected(SelectionEvent e){
				  if( preSelection >= 0 ) {
					  editor.getRtcParam().setDocActionOverView(preSelection, getDocText(activityText.getText()));
					  editor.getRtcParam().setDocActionPreCondition(preSelection, getDocText(preConditionText.getText()));
					  editor.getRtcParam().setDocActionPostCondition(preSelection, getDocText(postConditionText.getText()));
				  }
				  int index = implChk.indexOf(e.getSource());
				  editor.getRtcParam().setActionImplemented(index, implChk.get(index).getSelection());
				  actionNameText.setText(IRtcBuilderConstants.ACTION_TYPE_ITEMS[index]);
				  activityText.setText(getDisplayDocText(editor.getRtcParam().getDocActionOverView(index)));
				  preConditionText.setText(getDisplayDocText(editor.getRtcParam().getDocActionPreCondition(index)));
				  postConditionText.setText(getDisplayDocText(editor.getRtcParam().getDocActionPostCondition(index)));
				  preSelection = index;
			  }
			});
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		impl.setLayoutData(gridData);
		implChk.add(impl);
	}

	private void createHintSection(FormToolkit toolkit, ScrolledForm form) {
		Composite composite = createHintSectionBase(toolkit, form, 12);
		//
		createHintLabel(IMessageConstants.ACTIVITY_HINT_ONINITIALIZE_TITLE, IMessageConstants.ACTIVITY_HINT_ONINITIALIZE_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.ACTIVITY_HINT_ONFINALIZE_TITLE, IMessageConstants.ACTIVITY_HINT_ONFINALIZE_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.ACTIVITY_HINT_ONSTARTUP_TITLE, IMessageConstants.ACTIVITY_HINT_ONSTARTUP_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.ACTIVITY_HINT_ONSHUTDOWN_TITLE, IMessageConstants.ACTIVITY_HINT_ONSHUTDOWN_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.ACTIVITY_HINT_ONACTIVATED_TITLE, IMessageConstants.ACTIVITY_HINT_ONACTIVATED_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.ACTIVITY_HINT_ONDEACTIVATED_TITLE, IMessageConstants.ACTIVITY_HINT_ONDEACTIVATED_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.ACTIVITY_HINT_ONABORTING_TITLE, IMessageConstants.ACTIVITY_HINT_ONABORTING_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.ACTIVITY_HINT_ONERROR_TITLE, IMessageConstants.ACTIVITY_HINT_ONERROR_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.ACTIVITY_HINT_ONRESET_TITLE, IMessageConstants.ACTIVITY_HINT_ONRESET_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.ACTIVITY_HINT_ONEXECUTE_TITLE, IMessageConstants.ACTIVITY_HINT_ONEXECUTE_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.ACTIVITY_HINT_ONSTATEUPDATE_TITLE, IMessageConstants.ACTIVITY_HINT_ONSTATEUPDATE_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.ACTIVITY_HINT_ONRATECHANGED_TITLE, IMessageConstants.ACTIVITY_HINT_ONRATECHANGED_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.ACTIVITY_HINT_ONACTION_TITLE, IMessageConstants.ACTIVITY_HINT_ONACTION_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.ACTIVITY_HINT_ONMODECHANGED_TITLE, IMessageConstants.ACTIVITY_HINT_ONMODECHANGED_DESC, toolkit, composite);
		//
		createHintSpace(toolkit, composite);
		//
		createHintLabel(IMessageConstants.ACTIVITY_HINT_DESCRIPTION_TITLE, IMessageConstants.ACTIVITY_HINT_DESCRIPTION_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.ACTIVITY_HINT_PRECONDITION_TITLE, IMessageConstants.ACTIVITY_HINT_PRECONDITION_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.ACTIVITY_HINT_POSTCONDITION_TITLE, IMessageConstants.ACTIVITY_HINT_POSTCONDITION_DESC, toolkit, composite);
	}
	
	private void createDocumentSection(FormToolkit toolkit, ScrolledForm form) {
		Composite composite = createSectionBaseWithLabel(toolkit, form, 
				"Documentation", IMessageConstants.ACTIVITY_DOCUMENT_EXPL, 2);
		
		actionNameText = createLabelAndText(toolkit, composite,
				IMessageConstants.ACTIVITY_LBL_ACTIVITYNAME, SWT.BORDER);
		actionNameText.setEditable(false);
		actionNameText.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 50;
		activityText = createLabelAndText(toolkit, composite,
				IMessageConstants.ACTIVITY_LBL_DESCRIPTION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		activityText.setLayoutData(gridData);
		preConditionText = createLabelAndText(toolkit, composite,
				IMessageConstants.ACTIVITY_LBL_PRECONDITION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		preConditionText.setLayoutData(gridData);
		postConditionText = createLabelAndText(toolkit, composite,
				IMessageConstants.ACTIVITY_LBL_POSTCONDITION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		postConditionText.setLayoutData(gridData);
	}
	
	public void update() {
		RtcParam rtcParam = editor.getRtcParam();

		if( preSelection >= 0 ) {
			rtcParam.setDocActionOverView(preSelection, getDocText(activityText.getText()));
			rtcParam.setDocActionPreCondition(preSelection, getDocText(preConditionText.getText()));
			rtcParam.setDocActionPostCondition(preSelection, getDocText(postConditionText.getText()));
		}

		editor.updateDirty();
	}

	/**
	 * データをロードする
	 */
	public void load() {
		RtcParam rtcParam = editor.getRtcParam();

		if( activityText != null ) {
			for( int intidx=IRtcBuilderConstants.ACTIVITY_INITIALIZE; intidx<IRtcBuilderConstants.ACTIVITY_MODE_CHANGED; intidx++) {
				implChk.get(intidx).setSelection(rtcParam.getActionImplemented(intidx));
			}
		}
	}

	public String validateParam() {
		//入力パラメータチェックなし
		return null;
	}

}
