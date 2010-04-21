package RTC.mock;

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

import RTC.ConnectorProfile;
import RTC.ConnectorProfileHolder;
import RTC.PortProfile;
import RTC.PortService;
import RTC.ReturnCode_t;

@SuppressWarnings("serial")
public class PortServiceImpl implements PortService {

	PortProfile profile;

	public PortServiceImpl() {
		this.profile = new RTC.PortProfile();
	}

	public ReturnCode_t connect(ConnectorProfileHolder connector_profile) {
		return null;
	}

	public ReturnCode_t disconnect(String connector_id) {
		return null;
	}

	public ReturnCode_t disconnect_all() {
		return null;
	}

	public ConnectorProfile get_connector_profile(String connector_id) {
		return null;
	}

	public ConnectorProfile[] get_connector_profiles() {
		return this.profile.connector_profiles;
	}

	public PortProfile get_port_profile() {
		return this.profile;
	}

	public ReturnCode_t notify_connect(ConnectorProfileHolder connector_profile) {
		return null;
	}

	public ReturnCode_t notify_disconnect(String connector_id) {
		return null;
	}

	public Request _create_request(Context arg0, String arg1, NVList arg2,
			NamedValue arg3) {
		return null;
	}

	public Request _create_request(Context arg0, String arg1, NVList arg2,
			NamedValue arg3, ExceptionList arg4, ContextList arg5) {
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

	public Policy _get_policy(int arg0) {
		return null;
	}

	public int _hash(int arg0) {
		return 0;
	}

	public boolean _is_a(String arg0) {
		return false;
	}

	public boolean _is_equivalent(Object arg0) {
		return false;
	}

	public boolean _non_existent() {
		return false;
	}

	public void _release() {
	}

	public Request _request(String arg0) {
		return null;
	}

	public Object _set_policy_override(Policy[] arg0, SetOverrideType arg1) {
		return null;
	}
}
