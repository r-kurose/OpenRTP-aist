package jp.go.aist.rtm.toolscommon.corba;

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

import RTC.ExecutionContext;
import RTC.ExecutionKind;
import RTC.LifeCycleState;
import RTC.LightweightRTObject;
import RTC.ReturnCode_t;

public class ExecutionContextMock implements ExecutionContext {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Override
	public ReturnCode_t activate_component(LightweightRTObject comp) {
		return ReturnCode_t.RTC_OK;
	}

//	@Override
	public ReturnCode_t add_component(LightweightRTObject comp) {
		return null;
	}

//	@Override
	public ReturnCode_t deactivate_component(LightweightRTObject comp) {
		return ReturnCode_t.RTC_OK;
	}

//	@Override
	public LifeCycleState get_component_state(LightweightRTObject comp) {
		return null;
	}

//	@Override
	public ExecutionKind get_kind() {
		return ExecutionKind.PERIODIC;
	}

//	@Override
	public double get_rate() {
		return 0;
	}

//	@Override
	public boolean is_running() {
		return false;
	}

//	@Override
	public ReturnCode_t remove_component(LightweightRTObject comp) {
		return null;
	}

//	@Override
	public ReturnCode_t reset_component(LightweightRTObject comp) {
		return ReturnCode_t.RTC_OK;
	}

//	@Override
	public ReturnCode_t set_rate(double rate) {
		return null;
	}

//	@Override
	public ReturnCode_t start() {
		return ReturnCode_t.RTC_OK;
	}

//	@Override
	public ReturnCode_t stop() {
		return ReturnCode_t.RTC_OK;
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
