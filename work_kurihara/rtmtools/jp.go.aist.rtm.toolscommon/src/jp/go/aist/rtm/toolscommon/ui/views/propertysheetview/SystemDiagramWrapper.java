package jp.go.aist.rtm.toolscommon.ui.views.propertysheetview;

import org.eclipse.emf.ecore.EObject;

/**
 * ダイアグラムのラッパクラス
 */
public class SystemDiagramWrapper {

	EObject diagram;

	public SystemDiagramWrapper(EObject diagram) {
		this.diagram = diagram;
	}

	public EObject getDiagram() {
		return diagram;
	}

	public void setDiagram(EObject diagram) {
		this.diagram = diagram;
	}
}
