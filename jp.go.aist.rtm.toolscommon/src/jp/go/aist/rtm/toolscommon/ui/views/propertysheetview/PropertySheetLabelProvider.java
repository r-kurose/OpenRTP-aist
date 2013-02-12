package jp.go.aist.rtm.toolscommon.ui.views.propertysheetview;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.model.IWorkbenchAdapter;

/**
 * RtcのPropertySheetのLabelProviderクラス
 */
public class PropertySheetLabelProvider extends LabelProvider implements
		ITableLabelProvider, ITableColorProvider, ITableFontProvider {

	public Image getColumnImage(Object element, int columnIndex) {
		Image result = null;
		if (columnIndex == 0) {
			element = getElement(element);
			IWorkbenchAdapter workbenchAdapter = ((IWorkbenchAdapter) AdapterUtil
					.getAdapter(element, IWorkbenchAdapter.class));
			if (workbenchAdapter != null) {
				ImageDescriptor descriptor = workbenchAdapter
						.getImageDescriptor(element);
				result = ToolsCommonPlugin.getCachedImage(descriptor);
			}
		}
		return result;
	}

	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof PropertyDescriptorWithSource) {
			if (columnIndex == 0) {
				return ((PropertyDescriptorWithSource) element)
						.getPropertyDescriptor().getDisplayName();
			} else if (columnIndex == 1) {
				Object propertyValue = ((PropertyDescriptorWithSource) element)
						.getSource().getPropertyValue(
								((PropertyDescriptorWithSource) element)
										.getPropertyDescriptor().getId());
				if (propertyValue == null) {
					propertyValue = "";
				}

				return propertyValue.toString();
			}
		} else {
			if (columnIndex == 0) {
				element = getElement(element);
				return ((IWorkbenchAdapter) AdapterUtil.getAdapter(element,
						IWorkbenchAdapter.class)).getLabel(element);
			}
		}
		return "";
	}

	public Color getForeground(Object element, int columnIndex) {
		return null;
	}

	public Color getBackground(Object element, int columnIndex) {
		return null;
	}

	public Font getFont(Object element, int columnIndex) {
		return null;
	}

	Object getElement(Object element) {
		if (element instanceof PropertySheetContentProvider.ChildWithParent) {
			return ((PropertySheetContentProvider.ChildWithParent) element)
					.getChild();
		}
		return element;
	}

}
