package jp.go.aist.rtm.toolscommon.model.component.impl;

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

import _SDOPackage.DependencyType;
import _SDOPackage.InternalError;
import _SDOPackage.InvalidParameter;
import _SDOPackage.NotAvailable;
import _SDOPackage.Organization;
import _SDOPackage.OrganizationProperty;
import _SDOPackage.SDO;
import _SDOPackage.SDOSystemElement;

public class NullSDOOrganization implements Organization {
	private static final long serialVersionUID = 1L;

//	@Override
	public boolean add_members(SDO[] sdo_list) throws InvalidParameter,
			NotAvailable, InternalError {
		return false;
	}

//	@Override
	public boolean add_organization_property(
			OrganizationProperty organization_property)
			throws InvalidParameter, NotAvailable, InternalError {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public DependencyType get_dependency() throws NotAvailable, InternalError {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public SDO[] get_members() throws NotAvailable, InternalError {
		return null;
	}

//	@Override
	public String get_organization_id() throws InvalidParameter, NotAvailable,
			InternalError {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public OrganizationProperty get_organization_property()
			throws NotAvailable, InternalError {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public Any get_organization_property_value(String name)
			throws InvalidParameter, NotAvailable, InternalError {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public SDOSystemElement get_owner() throws NotAvailable, InternalError {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public boolean remove_member(String id) throws InvalidParameter,
			NotAvailable, InternalError {
		return false;
	}

//	@Override
	public boolean remove_organization_property(String name)
			throws InvalidParameter, NotAvailable, InternalError {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public boolean set_dependency(DependencyType dependency)
			throws NotAvailable, InternalError {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public boolean set_members(SDO[] sdos) throws InvalidParameter,
			NotAvailable, InternalError {
		return false;
	}

//	@Override
	public boolean set_organization_property_value(String name, Any value)
			throws InvalidParameter, NotAvailable, InternalError {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public boolean set_owner(SDOSystemElement sdo) throws InvalidParameter,
			NotAvailable, InternalError {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public Request _create_request(Context ctx, String operation,
			NVList arg_list, NamedValue result) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public Request _create_request(Context ctx, String operation,
			NVList arg_list, NamedValue result, ExceptionList exclist,
			ContextList ctxlist) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public Object _duplicate() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public DomainManager[] _get_domain_managers() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public Object _get_interface_def() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public Policy _get_policy(int policy_type) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public int _hash(int maximum) {
		// TODO Auto-generated method stub
		return 0;
	}

//	@Override
	public boolean _is_a(String repositoryIdentifier) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public boolean _is_equivalent(Object other) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public boolean _non_existent() {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public void _release() {
		// TODO Auto-generated method stub

	}

//	@Override
	public Request _request(String operation) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public Object _set_policy_override(Policy[] policies,
			SetOverrideType set_add) {
		// TODO Auto-generated method stub
		return null;
	}

}
