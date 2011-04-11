package jp.go.aist.rtm.systemeditor.ui.editor.editpart.direct;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Text;

public class NameCellEditorLocator implements CellEditorLocator {

	IFigure figure;

	public NameCellEditorLocator(IFigure figure) {
		this.figure = figure;
	}

	@Override
	public void relocate(CellEditor celleditor) {
		Text text = (Text) celleditor.getControl();
		Rectangle bounds = figure.getBounds().getCopy();
		figure.translateToAbsolute(bounds);
		text.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
	}

}
