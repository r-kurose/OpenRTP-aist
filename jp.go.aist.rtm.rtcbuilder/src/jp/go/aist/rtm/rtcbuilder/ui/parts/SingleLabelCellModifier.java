package jp.go.aist.rtm.rtcbuilder.ui.parts;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

public class SingleLabelCellModifier extends EditingSupport {

	private CellEditor editor;

	public SingleLabelCellModifier(ColumnViewer viewer) {
		super(viewer);
		editor = new TextCellEditor(((TableViewer) viewer).getTable());
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
	public Object getValue(Object element) {
		if (element instanceof SingleLabelItem == false) return null;
		SingleLabelItem selectedItem = (SingleLabelItem) element;
		String result = selectedItem.getLabeltext();
		return result;
	}

	@Override
	protected void setValue(Object element, Object value) {
		if (element instanceof SingleLabelItem == false) return;
		SingleLabelItem selectedItem = (SingleLabelItem) element;		
		selectedItem.setLabeltext((String) value);

		getViewer().update(element, null);
	}
}
