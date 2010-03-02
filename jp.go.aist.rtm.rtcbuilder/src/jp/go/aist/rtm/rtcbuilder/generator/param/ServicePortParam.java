package jp.go.aist.rtm.rtcbuilder.generator.param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * サービスポートを表すクラス
 */
public class ServicePortParam extends PortBaseParam implements Serializable {

	private static final long serialVersionUID = -5584413830295784663L;

	private String name;
	private RecordedList<ServicePortInterfaceParam> serviceinterfaces = new RecordedList<ServicePortInterfaceParam>();
	//
	private String doc_description;
	private String doc_if_description;
	//Properties
	private List<PropertyParam> properties = new ArrayList<PropertyParam>();

	public ServicePortParam() {
		this.name = "";
		this.selection = 0;
		setPositionByIndex(selection);
		//
		this.doc_description = "";
		this.doc_if_description = "";
		//
		setUpdated(false);
	}

	public ServicePortParam(String name, String direction,
			String doc_description, String doc_if_description) {
		this.name = name;
		this.setPosition(direction);
		//
		this.doc_description = doc_description;
		this.doc_if_description = doc_if_description;
		//
		setUpdated(false);
	}

	public ServicePortParam(String name, int selection) {
		this.name = name;
		this.selection = selection;
		setPositionByIndex(selection);
		//
		this.doc_description = "";
		this.doc_if_description = "";
		//
		setUpdated(false);
	}

	public String getName() {
		return name;
	}
	public List<ServicePortInterfaceParam> getServicePortInterfaces() {
		return serviceinterfaces;
	}

	public void setName(String name) {
		checkUpdated(this.name, name);
		this.name = name;
	}
	//
	public String getDocDescription() {
		return doc_description;
	}
	public String getDocIfDescription() {
		return doc_if_description;
	}

	public void setDocDescription(String description) {
		checkUpdated(this.doc_description, description);
		this.doc_description = description;
	}
	public void setDocIfDescription(String ifdescription) {
		checkUpdated(this.doc_if_description, ifdescription);
		this.doc_if_description = ifdescription;
	}
	//
	public List<PropertyParam> getProperties() {
		return properties;
	}

	@Override
	public boolean isUpdated() {
		if (super.isUpdated()) {
			return true;
		}
		if (serviceinterfaces.isUpdated()) {
			return true;
		}
		return false;
	}

	@Override
	public void resetUpdated() {
		super.resetUpdated();
		serviceinterfaces.resetUpdated();
	}

}
