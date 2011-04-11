package jp.go.aist.rtm.systemeditor.ui.preference;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import static jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager.*;

/**
 * SystemEditor縺ｮ險ｭ螳壹�壹�ｼ繧ｸ
 */
public class SystemEditorPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	static final String LABEL_STATUS_OBSERVER_GROUP = Messages
			.getString("SystemEditorPreferencePage.6");
	static final String LABEL_HB_ENABLE = Messages
			.getString("SystemEditorPreferencePage.7");
	static final String LABEL_HB_INTERVAL = Messages
			.getString("SystemEditorPreferencePage.8");
	static final String LABEL_HB_INTERVAL_UNIT = Messages
			.getString("SystemEditorPreferencePage.9");
	static final String LABEL_HB_TRYCOUNT = Messages
			.getString("SystemEditorPreferencePage.10");
	static final String LABEL_HB_TRYCOUNT_UNIT = Messages
			.getString("SystemEditorPreferencePage.11");

	static final String LABEL_SYNC_GROUP = Messages
			.getString("SystemEditorPreferencePage.0");
	static final String LABEL_CYCLE = Messages
			.getString("SystemEditorPreferencePage.1");
	static final String LABEL_CYCLE_EXPLAIN = Messages
			.getString("SystemEditorPreferencePage.2");

	static final String MSG_INVALID_VALUE = Messages
			.getString("SystemEditorPreferencePage.5");

	Button hbEnableButton;
	Text hbIntervalText;
	Text hbTryCountText;

	Text syncCycleText;

	@Override
	protected Control createContents(Composite parent) {
		GridLayout gl;
		GridData gd;

		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout());

		// 状態通知オブザーバ設定
		Group statusObserverGroup = new Group(composite, SWT.NONE);
		gl = new GridLayout();
		gl.numColumns = 3;
		statusObserverGroup.setLayout(gl);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		statusObserverGroup.setLayoutData(gd);
		statusObserverGroup.setText(LABEL_STATUS_OBSERVER_GROUP);

		createLabel(statusObserverGroup, LABEL_HB_ENABLE);

		hbEnableButton = new Button(statusObserverGroup, SWT.CHECK);
		gd = new GridData();
		gd.horizontalSpan = 2;
		hbEnableButton.setLayoutData(gd);

		createLabel(statusObserverGroup, LABEL_HB_INTERVAL);

		hbIntervalText = new Text(statusObserverGroup, SWT.SINGLE | SWT.BORDER
				| SWT.RIGHT);
		gd = new GridData();
		gd.widthHint = 45;
		hbIntervalText.setLayoutData(gd);
		hbIntervalText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateStatus();
			}
		});

		createLabel(statusObserverGroup, LABEL_HB_INTERVAL_UNIT);

		createLabel(statusObserverGroup, LABEL_HB_TRYCOUNT);

		hbTryCountText = new Text(statusObserverGroup, SWT.SINGLE | SWT.BORDER
				| SWT.RIGHT);
		gd = new GridData();
		gd.widthHint = 45;
		hbTryCountText.setLayoutData(gd);
		hbTryCountText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateStatus();
			}
		});

		createLabel(statusObserverGroup, LABEL_HB_TRYCOUNT_UNIT);

		// 同期設定
		Group syncGroup = new Group(composite, SWT.NONE);
		gl = new GridLayout();
		gl.numColumns = 3;
		syncGroup.setLayout(gl);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		syncGroup.setLayoutData(gd);
		syncGroup.setText(LABEL_SYNC_GROUP);

		createLabel(syncGroup, LABEL_CYCLE);

		syncCycleText = new Text(syncGroup, SWT.SINGLE | SWT.BORDER | SWT.RIGHT);
		syncCycleText.setTextLimit(7);
		syncCycleText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateStatus();
			}
		});
		gd = new GridData();
		gd.widthHint = 45;
		syncCycleText.setLayoutData(gd);

		createLabel(syncGroup, LABEL_CYCLE_EXPLAIN);

		buildData();

		return composite;
	}

	Label createLabel(Composite parent, String text) {
		Label label = new Label(parent, SWT.NULL);
		label.setText(text);
		return label;
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	void buildData() {
		ToolsCommonPreferenceManager tcPref = ToolsCommonPreferenceManager
				.getInstance();
		hbEnableButton.setSelection(tcPref.isSTATUS_OBSERVER_HB_ENABLE());
		hbIntervalText.setText(tcPref.getSTATUS_OBSERVER_HB_INTERVAL()
				.toString());
		hbTryCountText.setText(tcPref.getSTATUS_OBSERVER_HB_TRYCOUNT()
				.toString());

		SystemEditorPreferenceManager sePref = SystemEditorPreferenceManager
				.getInstance();
		syncCycleText.setText(String.valueOf(sePref
				.getInterval(SYNC_SYSTEMEDITOR_INTERVAL)));
	}

	/**
	 * 迥ｶ諷九ｒ螟画峩縺励◆髫帙↓蜻ｼ縺ｳ蜃ｺ縺吶％縺ｨ
	 */
	void updateStatus() {
		try {
			Double d = Double.valueOf(hbIntervalText.getText());
			setErrorMessage(null);
			if (d <= 0.0) {
				setValid(false);
				return;
			}
		} catch (NumberFormatException e) {
			setErrorMessage("'" + hbIntervalText.getText() + "'"
					+ MSG_INVALID_VALUE);
			setValid(false);
			return;
		}
		//
		try {
			Integer i = Integer.valueOf(hbTryCountText.getText());
			setErrorMessage(null);
			if (i <= 0) {
				setValid(false);
				return;
			}
		} catch (NumberFormatException e) {
			setErrorMessage("'" + hbTryCountText.getText() + "'"
					+ MSG_INVALID_VALUE);
			setValid(false);
			return;
		}
		//
		try {
			int parse = Integer.parseInt(syncCycleText.getText());
			setErrorMessage(null);
			if (!(0 <= parse && parse <= 1000000)) {
				setValid(false);
				return;
			}
		} catch (Exception e) {
			setErrorMessage("'" + syncCycleText.getText() + "'"
					+ MSG_INVALID_VALUE);
			setValid(false);
			return;
		}
		setValid(true);
	}

	@Override
	public boolean performOk() {
		ToolsCommonPreferenceManager tcPref = ToolsCommonPreferenceManager
				.getInstance();
		tcPref.setSTATUS_OBSERVER_HB_ENABLE(hbEnableButton.getSelection());
		tcPref.setSTATUS_OBSERVER_HB_INTERVAL(Double.valueOf(hbIntervalText
				.getText()));
		tcPref.setSTATUS_OBSERVER_HB_TRYCOUNT(Integer.valueOf(hbTryCountText
				.getText()));
		buildData();

		SystemEditorPreferenceManager sePref = SystemEditorPreferenceManager
				.getInstance();
		sePref.setInterval(SYNC_SYSTEMEDITOR_INTERVAL, Integer
				.parseInt(syncCycleText.getText()));

		return super.performOk();
	}

	@Override
	protected void performDefaults() {
		ToolsCommonPreferenceManager tcPref = ToolsCommonPreferenceManager
				.getInstance();
		tcPref.resetSTATUS_OBSERVER_HB_ENABLE();
		tcPref.resetSTATUS_OBSERVER_HB_INTERVAL();
		tcPref.resetSTATUS_OBSERVER_HB_TRYCOUNT();
		buildData();

		SystemEditorPreferenceManager sePref = SystemEditorPreferenceManager
				.getInstance();
		syncCycleText.setText(String.valueOf(sePref.getDefaultIntervalMap()
				.get(SYNC_SYSTEMEDITOR_INTERVAL)));

		super.performDefaults();
	}

}
