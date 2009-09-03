package jp.go.aist.rtm.rtcbuilder.python.ui.editors;

import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.python.IRtcBuilderConstantsPython;
import jp.go.aist.rtm.rtcbuilder.ui.editors.LanguageEditorSection;
import jp.go.aist.rtm.rtcbuilder.ui.editors.RtcBuilderEditor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

public class PythonEditorSection implements LanguageEditorSection {

	private Section PythonSection;
	private List<Section> sectionList;
	private RtcBuilderEditor editor;
	
	public PythonEditorSection(){
	}

	public Section createSection(IManagedForm managedForm, ScrolledForm form) {
		GridLayout gl;

		FormToolkit toolkit = managedForm.getToolkit();
		PythonSection = toolkit.createSection(form.getBody(),
				Section.TITLE_BAR | Section.EXPANDED | Section.TWISTIE);
		PythonSection.setText("Python");
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		PythonSection.setLayoutData(gridData);
		//
		Composite composite = toolkit.createComposite(PythonSection, SWT.NULL);
		composite.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		toolkit.paintBordersFor(composite);
		gl = new GridLayout(2, false);
		composite.setLayout(gl);
		PythonSection.setClient(composite);
		PythonSection.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				if( e.getState() ) {
					closeSection();
				}
				update();
			}
		});
		//
		toolkit.createLabel(composite, "Python");
		PythonSection.setExpanded(false);
		return PythonSection;
	}

	public void update() {
		RtcParam rtcParam = editor.getRtcParam();
		if (PythonSection.isExpanded()) {
			rtcParam.getLangList().add(IRtcBuilderConstantsPython.LANG_PYTHON);
			rtcParam.getLangArgList().add(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		}
	}
	public void load() {
		RtcParam rtcParam = editor.getRtcParam();
		if( rtcParam.getLangList().contains(IRtcBuilderConstantsPython.LANG_PYTHON)) {
			if( PythonSection != null ) {
				closeSection();
				PythonSection.setExpanded(true);
			}
		}
	}

	private void closeSection() {
		for(Iterator<Section> iter = sectionList.iterator(); iter.hasNext(); ) {
			Section section = iter.next();
			if( !section.getText().equals("Python")) {
				section.setExpanded(false);
			}
		}
	}

	public void setEditor(RtcBuilderEditor editor) {
		this.editor = editor;
	}

	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}
}
