package jp.go.aist.rtm.rtcbuilder.ui.editors;

import java.util.List;

import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

public interface  LanguageEditorSection {

	public Section createSection(IManagedForm managedForm, ScrolledForm form);
	public void update();
	public void load();

	public void setEditor(RtcBuilderEditor editor);
	public void setSectionList(List<Section> sectionList);

}
