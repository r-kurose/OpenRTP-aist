/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaContextHandler;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Corba Context Handler</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class CorbaContextHandlerImpl extends ContextHandlerImpl implements CorbaContextHandler {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CorbaContextHandlerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.CORBA_CONTEXT_HANDLER;
	}

	@Override
	public String getId(ExecutionContext ec) {
		for (String id : contextMap.keySet()) {
			ExecutionContext e = contextMap.get(id);
			if (e == null) {
				continue;
			}
			if (e.equals(ec)) {
				return id;
			}
			if (e instanceof CorbaExecutionContext
					&& ec instanceof CorbaExecutionContext) {
				// EMFのExecutionContext、もしくはCORBAオブジェクトに一致するIDを取得
				// (ID:CORBAオブジェクト = 1:1、ID:EMFオブジェクト = 1:n となるため)
				CorbaExecutionContext ce = (CorbaExecutionContext) e;
				CorbaExecutionContext cec = (CorbaExecutionContext) ec;
				if (ce.getCorbaObjectInterface().equals(
						cec.getCorbaObjectInterface())) {
					return id;
				}
			}
		}
		return null;
	}

	@Override
	public void sync() {
		List<ExecutionContext> deletes = new ArrayList<ExecutionContext>();
		for (ExecutionContext ec : values()) {
			if (!getOwnerContexts().contains(ec)) {
				deletes.add(ec);
			}
		}
		for (ExecutionContext ec : deletes) {
			removeId(ec);
		}
		for (ExecutionContext ec : getOwnerContexts()) {
			String id = createExecutionContextId(ec);
			setContext(id, ec);
		}
	}

	String createExecutionContextId(ExecutionContext ec) {
		if (!(ec instanceof CorbaExecutionContext)) {
			return null;
		}
		CorbaExecutionContext cec = (CorbaExecutionContext) ec;
		CorbaComponent cc = (CorbaComponent) eContainer();
		RTC.ExecutionContext rec = cec.getCorbaObjectInterface();
		int handle = cc.getCorbaObjectInterface().get_context_handle(rec);
		// CORBAの場合はcontext_handleをExecutionContext IDとして扱う
		return Integer.toString(handle);
	}

} //CorbaContextHandlerImpl
