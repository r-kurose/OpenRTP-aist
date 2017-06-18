package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

/**
 * マネージャのコンフィギュレーションを設定するダイアログ
 */
public class ManagerConfigurationDialog extends TitleAreaDialog {
	private static final int PARAMETER_KEY = 0;
	private static final int PARAMETER_VALUE = 1;

	private static final String MODIFY_COLOR = "MODIFY_COLOR";
	private static final String WHITE_COLOR = "WHITE_COLOR";
	
	private static final String LABEL_BUTTON_ADD = Messages
			.getString("CreateComponentDialog.add_button");
	private static final String LABEL_BUTTON_DEL = Messages
			.getString("CreateComponentDialog.del_button");
	private static final String LABEL_BUTTON_APPLY = Messages
			.getString("CreateComponentDialog.apply_button");

	private static final int BUTTON_WIDTH = 70;

	private static final String COL_NAME = "Name";
	private static final String COL_VALUE = "Value";

	private RTCManager targetManager;
	
	private TableViewer parameterViewer;
	private Button parameterAddButton;
	private Button parameterDeleteButton;
	private Button parameterApplyButton;

	private List<ParameterParam> parameterList = new ArrayList<>();
	private ParameterParam selectedParam;
	private static ColorRegistry colorRegistry = null;

	public void setManager(RTCManager source) {
		this.targetManager = source;
	}
	
	public ManagerConfigurationDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}

	protected Point getInitialSize() {
		return new Point(600, 600);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		if (colorRegistry == null) {
			colorRegistry = new ColorRegistry();
			colorRegistry.put(MODIFY_COLOR, new RGB(255, 192, 192));
			colorRegistry.put(WHITE_COLOR, new RGB(255, 255, 255));
		}
		
		Composite mainComposite = new Composite(
				(Composite) super.createDialogArea(parent), SWT.NONE);

		GridLayout gl;
		gl = new GridLayout(2, false);
		mainComposite.setLayout(gl);
		mainComposite.setFont(parent.getFont());
		mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		List<NameValue> configList = targetManager.getConfigurationR();
		for (NameValue conf : configList) {
			if(conf.getName()!=null && 0<conf.getName().length()) {
				ParameterParam newParam = new ParameterParam(conf.getName(), conf.getValue());
				parameterList.add(newParam);
			}
		}

		this.parameterViewer = createParameterTableViewer(mainComposite);
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
		colName.getColumn().setWidth(230);
		colName.getColumn().setResizable(true);
		colName.getColumn().setMoveable(false);
		colName.setEditingSupport(new ParameterCellModifier(viewer, PARAMETER_KEY));

		TableViewerColumn colValue = new TableViewerColumn(viewer, SWT.NONE);
		colValue.getColumn().setText(COL_VALUE);
		colValue.getColumn().setWidth(240);
		colValue.getColumn().setResizable(true);
		colValue.getColumn().setMoveable(false);
		colValue.setEditingSupport(new ParameterCellModifier(viewer, PARAMETER_VALUE));

		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new ParameterLabelProvider());
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection selection = (StructuredSelection) event
						.getSelection();
				selectedParam = (ParameterParam) selection.getFirstElement();
				if(selectedParam!=null) {
					parameterDeleteButton.setEnabled(selectedParam.isNew());
				}
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
		createParameterApplyButton(buttonComposite);

		return viewer;
	}

	private void createParameterAddButton(Composite buttonComposite) {
		this.parameterAddButton = new Button(buttonComposite, SWT.PUSH);
		this.parameterAddButton.setText(LABEL_BUTTON_ADD);
		this.parameterAddButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ParameterParam newParam = new ParameterParam("Name", "Value");
				newParam.setNew(true);
				parameterList.add(newParam);
				parameterViewer.refresh();
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
			}
		});
		this.parameterDeleteButton.setEnabled(false);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = BUTTON_WIDTH;
		this.parameterDeleteButton.setLayoutData(gd);
	}

	private void createParameterApplyButton(Composite buttonComposite) {
		this.parameterApplyButton = new Button(buttonComposite, SWT.PUSH);
		this.parameterApplyButton.setText(LABEL_BUTTON_APPLY);
		this.parameterApplyButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for(ParameterParam target : parameterList) {
					if(target.isNew() || target.isUpdated()) {
						targetManager.setConfigurationR(target.getName(), target.getValue());
						target.setNormal();
					}
				}
				parameterViewer.refresh();
			}
		});
		this.parameterApplyButton.setEnabled(true);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = BUTTON_WIDTH;
		this.parameterApplyButton.setLayoutData(gd);
	}
	
	@Override
	protected Control createButtonBar(Composite parent) {
		Control composite = super.createButtonBar(parent);
		return composite;
	}

	private class ParameterParam {
		private String name;
		private String value;
		private String orgValue;
		private boolean isNew;

		public ParameterParam(String name, String value) {
			this.name = name;
			this.value = value;
			this.orgValue = value;
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

		public boolean isNew() {
			return isNew;
		}
		public void setNew(boolean isNew) {
			this.isNew = isNew;
		}
		
		public boolean isUpdated() {
			if(this.value==null || this.orgValue==null) return false;
			return !(this.value.equals(this.orgValue));
		}
		
		public void setNormal() {
			this.isNew = false;
			this.orgValue = this.value;
		}
	}

	private class ParameterLabelProvider extends LabelProvider implements
			ITableLabelProvider, ITableColorProvider {

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

		@Override
		public Color getBackground(Object element, int columnIndex) {
			if (element instanceof ParameterParam == false) {
				return null;
			}
			
			Color color = colorRegistry.get(WHITE_COLOR);
			ParameterParam targetParam = (ParameterParam) element;
			if (columnIndex == 0) {
				if(targetParam.isNew()) {
					color = colorRegistry.get(MODIFY_COLOR);
				}
			} else if (columnIndex == 1) {
				if(targetParam.isUpdated()) {
					color = colorRegistry.get(MODIFY_COLOR);
				}
			}
			
			return color;
		}
		
		@Override
		public Color getForeground(Object element, int columnIndex) {
			return null;
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
			if (element instanceof ParameterParam == false) {
				return false;
			}
			ParameterParam targetParam = (ParameterParam) element;
			if(this.column == PARAMETER_KEY) {
				return targetParam.isNew();
			}
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
			case PARAMETER_KEY:
				return targetParam.getName();
			case PARAMETER_VALUE:
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
			case PARAMETER_KEY:
				targetParam.setName((String) value);
				break;
			case PARAMETER_VALUE:
				targetParam.setValue((String) value);
				break;
			default:
				break;
			}

			getViewer().update(element, null);
		}
	}
}
