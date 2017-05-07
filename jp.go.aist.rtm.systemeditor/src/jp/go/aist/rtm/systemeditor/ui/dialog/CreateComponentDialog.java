package jp.go.aist.rtm.systemeditor.ui.dialog;

import static jp.go.aist.rtm.systemeditor.corba.CORBAHelper.CreateComponentParameter.KEY_IMPLEMENTATION_ID;
import static jp.go.aist.rtm.systemeditor.corba.CORBAHelper.CreateComponentParameter.KEY_LANGUAGE;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.systemeditor.corba.CORBAHelper;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.util.SDOUtil;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

/**
 * マネージャビューからコンポーネントを作成するダイアログ
 */
public class CreateComponentDialog extends TitleAreaDialog {

	private static final String LABEL_TYPE_TITLE = Messages
			.getString("CreateComponentDialog.2");
	private static final String LABEL_MANAGER_NAME_TITLE = Messages
			.getString("CreateComponentDialog.manager_name.title");
	private static final String LABEL_LANGUAGE_TITLE = Messages
			.getString("CreateComponentDialog.lang.title");
	private static final String LABEL_PARAMETER_TITLE = Messages
			.getString("CreateComponentDialog.3");
	private static final String ERR_INVALID_PARAM = Messages
			.getString("CreateComponentDialog.6");

	private static final String LABEL_BUTTON_ADD = Messages
			.getString("CreateComponentDialog.add_button");
	private static final String LABEL_BUTTON_DEL = Messages
			.getString("CreateComponentDialog.del_button");

	private static final int BUTTON_WIDTH = 70;

	private static final String COL_NAME = "Name";
	private static final String COL_VALUE = "Value";

	private Combo typeCombo;
	private Combo managerNameCombo;
	private Combo langCombo;
	private TableViewer parameterViewer;
	private Button parameterAddButton;
	private Button parameterDeleteButton;

	private List<Module> moduleList = new ArrayList<>();
	private List<String> managerNameList = new ArrayList<>();
	private List<ParameterParam> parameterList = new ArrayList<>();
	private ParameterParam selectedParam;

	private CORBAHelper.CreateComponentParameter parameter = null;

	/** モジュール情報 */
	static class Module {
		String type;
		String lang;
	}

	public CreateComponentDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}

	/**
	 * 対象マネージャがコンポーネント生成可能なモジュール情報の一覧を設定します。
	 * 
	 * @param moduleProfileList
	 */
	public void setModuleProfileList(List<RTM.ModuleProfile> moduleProfileList) {
		this.moduleList.clear();
		for (RTM.ModuleProfile prof : moduleProfileList) {
			String type = SDOUtil.findValueAsString(KEY_IMPLEMENTATION_ID,
					prof.properties);
			String lang = SDOUtil.findValueAsString(KEY_LANGUAGE,
					prof.properties);
			Module mod = new Module();
			mod.type = type;
			mod.lang = lang;
			this.moduleList.add(mod);
		}
	}

	/**
	 * 対象マネージャに属するマネージャ名(プロセスグループ)の一覧を設定します。
	 * 
	 * @param managerNameList
	 */
	public void setManagerNameList(List<String> managerNameList) {
		this.managerNameList.clear();
		this.managerNameList.addAll(managerNameList);
	}

	/**
	 * コンポーネント生成のコマンド列を取得します。
	 */
	public String getParameter() {
		return (this.parameter == null) ? null : this.parameter.buildCommand();
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite mainComposite = new Composite(
				(Composite) super.createDialogArea(parent), SWT.NONE);

		GridLayout gl;
		gl = new GridLayout(2, false);
		mainComposite.setLayout(gl);
		mainComposite.setFont(parent.getFont());
		mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label typeLabel = new Label(mainComposite, SWT.NONE);
		typeLabel.setText(LABEL_TYPE_TITLE);
		this.typeCombo = new Combo(mainComposite, SWT.NONE);
		GridData gd = new GridData(GridData.GRAB_HORIZONTAL);
		gd.minimumWidth = 180;
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		this.typeCombo.setLayoutData(gd);
		for (Module mod : this.moduleList) {
			if (mod.type != null) {
				this.typeCombo.add(mod.type);
			}
		}
		this.typeCombo.select(0);
		this.typeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String type = typeCombo.getText();
				Module mod = findModuleByType(type);
				if (mod != null) {
					langCombo.removeAll();
					langCombo.add(mod.lang);
				}
				langCombo.select(0);
				//
				notifyModified();
			}
		});

		Label mnLabel = new Label(mainComposite, SWT.NONE);
		mnLabel.setText(LABEL_MANAGER_NAME_TITLE);
		this.managerNameCombo = new Combo(mainComposite, SWT.NONE);
		gd = new GridData(GridData.GRAB_HORIZONTAL);
		gd.minimumWidth = 180;
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		this.managerNameCombo.setLayoutData(gd);
		for (String mn : this.managerNameList) {
			this.managerNameCombo.add(mn);
		}
		this.managerNameCombo.select(0);
		this.managerNameCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				notifyModified();
			}
		});

		Label langLabel = new Label(mainComposite, SWT.NONE);
		langLabel.setText(LABEL_LANGUAGE_TITLE);
		this.langCombo = new Combo(mainComposite, SWT.NONE);
		gd = new GridData(GridData.GRAB_HORIZONTAL);
		gd.minimumWidth = 180;
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		this.langCombo.setLayoutData(gd);
		Module mod = findModuleByType(this.typeCombo.getText());
		if (mod != null) {
			this.langCombo.add(mod.lang);
		}
		this.langCombo.select(0);
		this.langCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				notifyModified();
			}
		});

		Group parameterGroup = new Group(mainComposite, SWT.SHADOW_IN);
		parameterGroup.setText(LABEL_PARAMETER_TITLE);
		gl = new GridLayout(2, false);
		parameterGroup.setLayout(gl);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		parameterGroup.setLayoutData(gd);
		this.parameterViewer = createParameterTableViewer(parameterGroup);
		this.parameterViewer.setInput(this.parameterList);

		return mainComposite;
	}

	private TableViewer createParameterTableViewer(Composite parent) {
		TableViewer viewer = new TableViewer(parent, SWT.SINGLE | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 120;
		gd.widthHint = 120;
		gd.grabExcessHorizontalSpace = true;

		Table table = viewer.getTable();
		table.setLayoutData(gd);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		//
		TableViewerColumn colName = new TableViewerColumn(viewer, SWT.NONE);
		colName.getColumn().setText(COL_NAME);
		colName.getColumn().setWidth(160);
		colName.getColumn().setResizable(true);
		colName.getColumn().setMoveable(false);
		colName.setEditingSupport(new ParameterCellModifier(viewer, 0));

		TableViewerColumn colValue = new TableViewerColumn(viewer, SWT.NONE);
		colValue.getColumn().setText(COL_VALUE);
		colValue.getColumn().setWidth(160);
		colValue.getColumn().setResizable(true);
		colValue.getColumn().setMoveable(false);
		colValue.setEditingSupport(new ParameterCellModifier(viewer, 1));

		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new ParameterLabelProvider());
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection selection = (StructuredSelection) event
						.getSelection();
				selectedParam = (ParameterParam) selection.getFirstElement();
				parameterDeleteButton.setEnabled(true);
			}
		});
		//
		Composite buttonComposite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.marginRight = 0;
		buttonComposite.setLayout(gl);
		gd = new GridData(GridData.FILL_VERTICAL);
		gd.verticalAlignment = SWT.BEGINNING;
		gd.horizontalAlignment = SWT.BEGINNING;
		buttonComposite.setLayoutData(gd);

		createParameterAddButton(buttonComposite);
		createParameterDeleteButton(buttonComposite);

		return viewer;
	}

	private void createParameterAddButton(Composite buttonComposite) {
		this.parameterAddButton = new Button(buttonComposite, SWT.PUSH);
		this.parameterAddButton.setText(LABEL_BUTTON_ADD);
		this.parameterAddButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ParameterParam newParam = new ParameterParam("Name", "Value");
				parameterList.add(newParam);
				parameterViewer.refresh();
				notifyModified();
			}
		});
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = BUTTON_WIDTH;
		this.parameterAddButton.setLayoutData(gd);
	}

	private void createParameterDeleteButton(Composite buttonComposite) {
		this.parameterDeleteButton = new Button(buttonComposite, SWT.PUSH);
		this.parameterDeleteButton.setText(LABEL_BUTTON_DEL);
		this.parameterDeleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (selectedParam == null) {
					return;
				}
				parameterList.remove(selectedParam);
				parameterViewer.refresh();
				parameterDeleteButton.setEnabled(false);
				notifyModified();
			}
		});
		this.parameterDeleteButton.setEnabled(false);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = BUTTON_WIDTH;
		this.parameterDeleteButton.setLayoutData(gd);
	}

	private String buildParameter() {
		StringBuilder builder = new StringBuilder();
		for (int index = 0; index < parameterList.size(); index++) {
			if (0 < index) {
				builder.append("&");
			}
			ParameterParam target = parameterList.get(index);
			builder.append(target.getName()).append("=")
					.append(target.getValue());
		}
		return builder.toString();
	}

	@Override
	protected Control createButtonBar(Composite parent) {
		Control composite = super.createButtonBar(parent);
		notifyModified();
		return composite;
	}

	/** コンポーネント型名からモジュール情報を検索します。 */
	private Module findModuleByType(String type) {
		if (type == null || type.isEmpty()) {
			return null;
		}
		for (Module mod : this.moduleList) {
			if (type.equals(mod.type)) {
				return mod;
			}
		}
		return null;
	}

	/** 変更を通知します。 */
	private void notifyModified() {
		this.parameter = new CORBAHelper.CreateComponentParameter(
				this.typeCombo.getText());
		this.parameter.setManagerName(this.managerNameCombo.getText());
		this.parameter.setLanguage(this.langCombo.getText());
		this.parameter.setParams(buildParameter());
		//
		if (!validateInput()) {
			getButton(IDialogConstants.OK_ID).setEnabled(false);
		} else {
			getButton(IDialogConstants.OK_ID).setEnabled(true);
		}
	}

	/** 入力内容を検証します。 */
	private boolean validateInput() {
		String type = this.typeCombo.getText();
		if (type == null || type.isEmpty()) {
			return false;
		}
		String errmsg = ERR_INVALID_PARAM;
		if (type.indexOf("&") != -1) {
			// component_nameに&が入っている
			this.setMessage(errmsg, IMessageProvider.WARNING);
			return false;
		}
		return true;
	}

	private class ParameterParam {

		private String name;
		private String value;

		public ParameterParam(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

	private class ParameterLabelProvider extends LabelProvider implements
			ITableLabelProvider {

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof ParameterParam == false) {
				return null;
			}
			ParameterParam targetParam = (ParameterParam) element;
			String result = null;
			if (columnIndex == 0) {
				result = targetParam.getName();
			} else if (columnIndex == 1) {
				result = targetParam.getValue();
			}
			return result;
		}

	}

	private class ParameterCellModifier extends EditingSupport {

		private CellEditor editor;
		private int column;

		public ParameterCellModifier(ColumnViewer viewer, int column) {
			super(viewer);
			editor = new TextCellEditor(((TableViewer) viewer).getTable());
			this.column = column;
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return editor;
		}

		@Override
		protected Object getValue(Object element) {
			if (element instanceof ParameterParam == false) {
				return null;
			}
			ParameterParam targetParam = (ParameterParam) element;

			switch (this.column) {
			case 0:
				return targetParam.getName();
			case 1:
				return targetParam.getValue();
			default:
				break;
			}
			return null;
		}

		@Override
		protected void setValue(Object element, Object value) {
			if (element instanceof ParameterParam == false) {
				return;
			}
			ParameterParam targetParam = (ParameterParam) element;

			switch (this.column) {
			case 0:
				targetParam.setName((String) value);
				break;
			case 1:
				targetParam.setValue((String) value);
				break;
			default:
				break;
			}

			getViewer().update(element, null);
			notifyModified();
		}

	}

}
