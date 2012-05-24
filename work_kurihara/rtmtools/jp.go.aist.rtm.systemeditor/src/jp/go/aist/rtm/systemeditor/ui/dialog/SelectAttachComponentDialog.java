package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.List;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.model.component.Component;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * ECに参加するコンポーネントを選択するダイアログ (ECViewから使用)
 */
public class SelectAttachComponentDialog extends TitleAreaDialog {

	static final String PROPERTY_PATH_ID = "PROPERTY_PATH_ID";
	static final String PROPERTY_COMPONENT_ID = "PROPERTY_COMPONENT_ID";
	static final String PROPERTY_INSTANCE_ID = "PROPERTY_INSTANCE_ID";

	static final String LABEL_SELECT_MESSAGE = Messages.getString("SelectAttachComponentDialog.1");

	static final String COLUMN_LABEL_PATH_ID = "Path Id";
	static final String COLUMN_LABEL_COMPONENT_ID = "Component Id";
	static final String COLUMN_LABEL_INSTANCE_ID = "Instance name";

	TableViewer tableViewer;
	Table table;

	List<Component> components;
	Component selectedComponent;

	public SelectAttachComponentDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}

	/** 選択対象のコンポーネント一覧を設定 */
	public void setComponents(List<Component> components) {
		this.components = components;
	}

	/** 選択されたコンポーネントを取得 */
	public Component getSelectedComponent() {
		return selectedComponent;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		GridLayout gl;
		GridData gd;

		Composite mainComposite = new Composite((Composite) super
				.createDialogArea(parent), SWT.NONE);
		gl = new GridLayout(2, false);
		gd = new GridData(GridData.FILL_BOTH);
		mainComposite.setLayout(gl);
		mainComposite.setLayoutData(gd);
		mainComposite.setFont(parent.getFont());

		Label label = new Label(mainComposite, SWT.NONE);
		label.setText(LABEL_SELECT_MESSAGE);

		tableViewer = new TableViewer(mainComposite, SWT.FULL_SELECTION
				| SWT.SINGLE | SWT.BORDER);
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setColumnProperties(new String[] { PROPERTY_PATH_ID,
				PROPERTY_COMPONENT_ID, PROPERTY_INSTANCE_ID });
		tableViewer.setLabelProvider(new ComponentLabelProvider());
		tableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						StructuredSelection selection = (StructuredSelection) event
								.getSelection();
						selectedComponent = (Component) selection
								.getFirstElement();
						notifyModified();
					}
				});

		table = tableViewer.getTable();
		gl = new GridLayout(1, false);
		gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 2;
		gd.heightHint = 180;
		table.setLayout(gl);
		table.setLayoutData(gd);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableColumn col = new TableColumn(table, SWT.NONE);
		col.setText(COLUMN_LABEL_PATH_ID);
		col.setWidth(300);
		col = new TableColumn(table, SWT.NONE);
		col.setText(COLUMN_LABEL_COMPONENT_ID);
		col.setWidth(200);
		col = new TableColumn(table, SWT.NONE);
		col.setText(COLUMN_LABEL_INSTANCE_ID);
		col.setWidth(120);

		buildData();

		return mainComposite;
	}

	@Override
	protected Control createButtonBar(Composite parent) {
		Control composite = super.createButtonBar(parent);
		notifyModified();
		return composite;
	}

	/** 表示内容を構築 */
	void buildData() {
		tableViewer.setInput(components);
	}

	/** 変更を通知します */
	void notifyModified() {
		if (selectedComponent == null) {
			getButton(IDialogConstants.OK_ID).setEnabled(false);
		} else {
			getButton(IDialogConstants.OK_ID).setEnabled(true);
		}
	}

	/** ラベルプロバイダ(コンポーネント) */
	public class ComponentLabelProvider extends LabelProvider implements
			ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			Component entry = (Component) element;
			if (columnIndex == 0) {
				return entry.getPathId();
			} else if (columnIndex == 1) {
				return entry.getComponentId();
			} else if (columnIndex == 2) {
				return entry.getInstanceNameL();
			}
			return null;
		}
	}

}
