package jp.go.aist.rtm.toolscommon.corba;

import org.omg.CORBA.Any;
import org.omg.CORBA.Context;
import org.omg.CORBA.ContextList;
import org.omg.CORBA.DomainManager;
import org.omg.CORBA.ExceptionList;
import org.omg.CORBA.NVList;
import org.omg.CORBA.NamedValue;
import org.omg.CORBA.Object;
import org.omg.CORBA.Policy;
import org.omg.CORBA.Request;
import org.omg.CORBA.SetOverrideType;

import _SDOPackage.Configuration;
import _SDOPackage.ConfigurationSet;
import _SDOPackage.DeviceProfile;
import _SDOPackage.InternalError;
import _SDOPackage.InvalidParameter;
import _SDOPackage.NameValue;
import _SDOPackage.NotAvailable;
import _SDOPackage.Organization;
import _SDOPackage.Parameter;
import _SDOPackage.ServiceProfile;

public class ConfigurationMock implements Configuration {
	private static final long serialVersionUID = 1L;
	private StringBuffer buffer;

	public ConfigurationMock(StringBuffer buffer) {
		this.buffer = buffer;
	}

//	@Override
	public boolean activate_configuration_set(String config_id)
			throws InvalidParameter, NotAvailable, InternalError {
		return false;
	}

//	@Override
	public boolean add_configuration_set(ConfigurationSet configuration_set)
			throws InvalidParameter, NotAvailable, InternalError {
		buffer.append("add_configuration_set ");
		buffer.append("id:").append(configuration_set.id).append(" ");
		for (NameValue element : configuration_set.configuration_data) {
			buffer.append(element.name).append(":");
			buffer.append(element.value.extract_string()).append(" ");
		}
		return true;
	}

//	@Override
	public boolean add_organization(Organization organization_object)
			throws InvalidParameter, NotAvailable, InternalError {
		return false;
	}

//	@Override
	public boolean add_service_profile(ServiceProfile profile)
			throws InvalidParameter, NotAvailable, InternalError {
		return false;
	}

//	@Override
	public ConfigurationSet get_active_configuration_set() throws NotAvailable,
			InternalError {
		buffer.append("get_active_configuration_set ");
		ConfigurationSet result = new ConfigurationSet();
		result.id = "default";
		Any value = CorbaUtil.getOrb().create_any();
		value.insert_string("child.port2");
		result.configuration_data = new NameValue[]{new NameValue("exported_ports", value)};
		return result;
	}

//	@Override
	public Any get_configuration_parameter_value(String name)
			throws InvalidParameter, NotAvailable, InternalError {
		return null;
	}

//	@Override
	public NameValue[] get_configuration_parameter_values()
			throws NotAvailable, InternalError {
		return null;
	}

//	@Override
	public Parameter[] get_configuration_parameters() throws NotAvailable,
			InternalError {
		return null;
	}

//	@Override
	public ConfigurationSet get_configuration_set(String config_id)
			throws NotAvailable, InternalError {
		return null;
	}

//	@Override
	public ConfigurationSet[] get_configuration_sets() throws NotAvailable,
			InternalError {
		ConfigurationSet config1 = new ConfigurationSet();
		config1.id = "default";
		ConfigurationSet config2 = new ConfigurationSet();
		config2.id = "mode_alpha";
		return new ConfigurationSet[]{config1, config2};
	}

//	@Override
	public boolean remove_configuration_set(String config_id)
			throws InvalidParameter, NotAvailable, InternalError {
		return false;
	}

//	@Override
	public boolean remove_organization(String organization_id)
			throws InvalidParameter, NotAvailable, InternalError {
		return false;
	}

//	@Override
	public boolean remove_service_profile(String id) throws InvalidParameter,
			NotAvailable, InternalError {
		return false;
	}

//	@Override
	public boolean set_configuration_parameter(String name, Any value)
			throws InvalidParameter, NotAvailable, InternalError {
		return false;
	}

//	@Override
	public boolean set_configuration_set_values(
			ConfigurationSet configuration_set) throws InvalidParameter,
			NotAvailable, InternalError {
		buffer.append("set_configuration_set_values ");
		buffer.append("id:").append(configuration_set.id).append(" ");
		for (NameValue element : configuration_set.configuration_data) {
			buffer.append(element.name).append(":");
			buffer.append(element.value.extract_string()).append(" ");
		}
		return true;
	}

//	@Override
	public boolean set_device_profile(DeviceProfile profile)
			throws InvalidParameter, NotAvailable, InternalError {
		return false;
	}

	public Request _create_request(Context ctx, String operation,
			NVList arg_list, NamedValue result) {
		return null;
	}

	public Request _create_request(Context ctx, String operation,
			NVList arg_list, NamedValue result, ExceptionList exclist,
			ContextList ctxlist) {
		return null;
	}

	public Object _duplicate() {
		return null;
	}

	public DomainManager[] _get_domain_managers() {
		return null;
	}

	public Object _get_interface_def() {
		return null;
	}

	public Policy _get_policy(int policy_type) {
		return null;
	}

	public int _hash(int maximum) {
		return 0;
	}

	public boolean _is_a(String repositoryIdentifier) {
		return false;
	}

	public boolean _is_equivalent(Object other) {
		return false;
	}

	public boolean _non_existent() {
		return false;
	}

	public void _release() {
	}

	public Request _request(String operation) {
		return null;
	}

	public Object _set_policy_override(Policy[] policies,
			SetOverrideType set_add) {
		return null;
	}
}
