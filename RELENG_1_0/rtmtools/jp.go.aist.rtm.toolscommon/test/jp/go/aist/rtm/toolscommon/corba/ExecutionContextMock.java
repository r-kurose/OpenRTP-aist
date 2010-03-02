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
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public ReturnCode_t deactivate_component(LightweightRTObject comp) {
		return ReturnCode_t.RTC_OK;
	}

//	@Override
	public LifeCycleState get_component_state(LightweightRTObject comp) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public ExecutionKind get_kind() {
		return ExecutionKind.PERIODIC;
	}

//	@Override
	public double get_rate() {
		// TODO Auto-generated method stub
		return 0;
	}

//	@Override
	public boolean is_running() {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public ReturnCode_t remove_component(LightweightRTObject comp) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public ReturnCode_t reset_component(LightweightRTObject comp) {
		return ReturnCode_t.RTC_OK;
	}

//	@Override
	public ReturnCode_t set_rate(double rate) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	public Request _create_request(Context ctx, String operation,
			NVList arg_list, NamedValue result, ExceptionList exclist,
			ContextList ctxlist) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object _duplicate() {
		// TODO Auto-generated method stub
		return null;
	}

	public DomainManager[] _get_domain_managers() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object _get_interface_def() {
		// TODO Auto-generated method stub
		return null;
	}

	public Policy _get_policy(int policy_type) {
		// TODO Auto-generated method stub
		return null;
	}

	public int _hash(int maximum) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean _is_a(String repositoryIdentifier) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean _is_equivalent(Object other) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean _non_existent() {
		// TODO Auto-generated method stub
		return false;
	}

	public void _release() {
		// TODO Auto-generated method stub

	}

	public Request _request(String operation) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object _set_policy_override(Policy[] policies,
			SetOverrideType set_add) {
		// TODO Auto-generated method stub
		return null;
	}

}
