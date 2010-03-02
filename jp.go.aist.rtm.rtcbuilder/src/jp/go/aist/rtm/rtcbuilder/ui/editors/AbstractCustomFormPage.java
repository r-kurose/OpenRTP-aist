package jp.go.aist.rtm.rtcbuilder.ui.editors;

public abstract class AbstractCustomFormPage extends AbstractEditorFormPage {
	
	private String managerKey;
	
	public AbstractCustomFormPage(RtcBuilderEditor editor, String id, String name,String managerKey) {
		super(editor, id, name);
		this.managerKey = managerKey;
	}

	public String getManagerKey() {
		return managerKey;
	}

	abstract public void load();
	
	abstract public void setDefaultEnableInfo();
	abstract public void setEnableInfo(String key);

}
