package jp.go.aist.rtm.toolscommon.corba;

import org.omg.CORBA.Any;
import org.omg.CORBA.Context;
import org.omg.CORBA.ContextList;
import org.omg.CORBA.DomainManager;
import org.omg.CORBA.ExceptionList;
import org.omg.CORBA.NVList;
import org.omg.CORBA.NamedValue;
import org.omg.CORBA.Policy;
import org.omg.CORBA.Request;
import org.omg.CORBA.SetOverrideType;

import RTC.ConnectorProfile;
import RTC.ConnectorProfileHolder;
import RTC.PortProfile;
import RTC.PortService;
import RTC.RTObject;
import RTC.ReturnCode_t;
import _SDOPackage.Configuration;
import _SDOPackage.DeviceProfile;
import _SDOPackage.InterfaceNotImplemented;
import _SDOPackage.InternalError;
import _SDOPackage.InvalidParameter;
import _SDOPackage.Monitoring;
import _SDOPackage.NotAvailable;
import _SDOPackage.Organization;
import _SDOPackage.SDOService;
import _SDOPackage.ServiceProfile;

public class CorbaObjectMock implements RTObject, PortService {
	private static final long serialVersionUID = 1L;
	private StringBuffer buffer;
	private String stringValue;
	
	
	public CorbaObjectMock(StringBuffer stringBuffer) {
		this(stringBuffer, "newIOR");
	}
	public CorbaObjectMock(StringBuffer stringBuffer, String stringValue) {
		buffer = stringBuffer;
		this.stringValue = stringValue;
	}
	public RTC.ComponentProfile get_component_profile() {
		return null;
	}
	public PortService[] get_ports() {
		return null;
	}
	public ReturnCode_t _finalize() {
		return ReturnCode_t.RTC_OK;
	}
	public int attach_context(RTC.ExecutionContext exec_context) {
		return 0;
	}
	public ReturnCode_t detach_context(int exec_handle) {
		return null;
	}
	public ReturnCode_t exit() {
		buffer.append("exit ");
		return ReturnCode_t.from_int(0);
	}
	public RTC.ExecutionContext get_context(int exec_handle) {
		return null;
	}
	public int get_context_handle(RTC.ExecutionContext cxt) {
		return 0;
	}
	public RTC.ExecutionContext[] get_owned_contexts() {
		return null;
	}
	public RTC.ExecutionContext[] get_participating_contexts() {
		return null;
	}
	public ReturnCode_t initialize() {
		return ReturnCode_t.RTC_OK;
	}
	public boolean is_alive(RTC.ExecutionContext exec_context) {
		return false;
	}
	public ReturnCode_t on_aborting(int exec_handle) {
		return null;
	}
	public ReturnCode_t on_activated(int exec_handle) {
		return null;
	}
	public ReturnCode_t on_deactivated(int exec_handle) {
		return null;
	}
	public ReturnCode_t on_error(int exec_handle) {
		return null;
	}
	public ReturnCode_t on_finalize() {
		return null;
	}
	public ReturnCode_t on_initialize() {
		return null;
	}
	public ReturnCode_t on_reset(int exec_handle) {
		return null;
	}
	public ReturnCode_t on_shutdown(int exec_handle) {
		return null;
	}
	public ReturnCode_t on_startup(int exec_handle) {
		return null;
	}
	public Configuration get_configuration()
			throws InterfaceNotImplemented, NotAvailable, InternalError {
		buffer.append("get_configuration ");
		return new ConfigurationMock(buffer);
	}
	public DeviceProfile get_device_profile() throws NotAvailable,
			InternalError {
		return null;
	}
	public Monitoring get_monitoring() throws InterfaceNotImplemented,
			NotAvailable, InternalError {
		return null;
	}
	public Organization[] get_organizations() throws NotAvailable,
			InternalError {
		return null;
	}
	public String get_sdo_id() throws NotAvailable, InternalError {
		return null;
	}
	public SDOService get_sdo_service(String id)
			throws InvalidParameter, NotAvailable, InternalError {
		return null;
	}
	public String get_sdo_type() throws NotAvailable, InternalError {
		return null;
	}
	public ServiceProfile get_service_profile(String id)
			throws InvalidParameter, NotAvailable, InternalError {
		return null;
	}
	public ServiceProfile[] get_service_profiles() throws NotAvailable,
			InternalError {
		return null;
	}
	public Any get_status(String name) throws InvalidParameter,
			NotAvailable, InternalError {
		return null;
	}
	public _SDOPackage.NameValue[] get_status_list()
			throws NotAvailable, InternalError {
		return null;
	}
	public Organization[] get_owned_organizations()
			throws NotAvailable, InternalError {
		return null;
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
	public org.omg.CORBA.Object _duplicate() {
		return null;
	}
	public DomainManager[] _get_domain_managers() {
		return null;
	}
	public org.omg.CORBA.Object _get_interface_def() {
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
	public boolean _is_equivalent(org.omg.CORBA.Object other) {
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
	public org.omg.CORBA.Object _set_policy_override(Policy[] policies,
			SetOverrideType set_add) {
		return null;
	}
	public String toString() {
		return stringValue;
	}
//	@Override
	public ReturnCode_t connect(ConnectorProfileHolder connector_profile) {
		// TODO Auto-generated method stub
		return null;
	}
//	@Override
	public ReturnCode_t disconnect(String connector_id) {
		// TODO Auto-generated method stub
		return null;
	}
//	@Override
	public ReturnCode_t disconnect_all() {
		buffer.append("disconnect_all ");
		return null;
	}
//	@Override
	public ConnectorProfile get_connector_profile(String connector_id) {
		// TODO Auto-generated method stub
		return null;
	}
//	@Override
	public ConnectorProfile[] get_connector_profiles() {
		// TODO Auto-generated method stub
		return null;
	}
//	@Override
	public PortProfile get_port_profile() {
		// TODO Auto-generated method stub
		return null;
	}
//	@Override
	public ReturnCode_t notify_connect(ConnectorProfileHolder connector_profile) {
		// TODO Auto-generated method stub
		return null;
	}
//	@Override
	public ReturnCode_t notify_disconnect(String connector_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
