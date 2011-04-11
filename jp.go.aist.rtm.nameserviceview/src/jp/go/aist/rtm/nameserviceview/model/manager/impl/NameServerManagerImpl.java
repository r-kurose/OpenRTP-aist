/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.nameserviceview.model.manager.impl;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.nameserviceview.corba.NameServerAccesser;
import jp.go.aist.rtm.nameserviceview.factory.NameServiceViewWrapperFactory;
import jp.go.aist.rtm.nameserviceview.model.manager.ManagerPackage;
import jp.go.aist.rtm.nameserviceview.model.manager.NameServerContext;
import jp.go.aist.rtm.nameserviceview.model.manager.NameServerManager;
import jp.go.aist.rtm.nameserviceview.model.manager.Node;
import jp.go.aist.rtm.nameserviceview.model.manager.util.AlreadyExistException;
import jp.go.aist.rtm.toolscommon.synchronizationframework.RefreshThread;

import org.eclipse.emf.ecore.EClass;
import org.omg.CosNaming.NamingContextExt;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Name Server Manager</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class NameServerManagerImpl extends NodeImpl implements NameServerManager {

	/**
	 * ネームサーバのシングルトンインスタンス
	 */
	private static NameServerManager __instance = ManagerFactoryImpl.eINSTANCE
			.createNameServerManager();

	/**
	 * リフレッシュのスレッドクラス
	 */
	private RefreshThread refreshThread;

	/**
	 * ネームサーバのシングルトンインスタンスを取得する
	 */
	public static NameServerManager getInstance() {
		return __instance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NameServerManagerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ManagerPackage.Literals.NAME_SERVER_MANAGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * すべてのネームサーバを破棄し、再構築する。
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void refreshAll() {
		List<NameServerContext> unModifyList = getUnModifiedNameServerContexts();
		for (NameServerContext nameServerNamingContext : unModifyList) {
			refresh(nameServerNamingContext);
		}
	}

	/**
	 * 指定されたネームサーバを破棄し、再構築する。
	 * 
	 * @param nameServerNamingContext
	 */
	private void refresh(NameServerContext nameServerNamingContext) {
		String nameServerName = nameServerNamingContext.getNameServerName();
		removeNode(nameServerNamingContext);
		addNameServer(nameServerName);
	}

	/**
	 * 指定されたネームサーバを破棄する
	 * 
	 * @param nameServerNamingContext
	 */
	@Override
	public synchronized void removeNode(
			NameServerContext nameServerNamingContext) {
		nameServerNamingContext.finalizeLocal();
		getNodes().remove(nameServerNamingContext);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 対象のネームサービス名が、存在するかどうか。
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isExist(String nameServer) {
		for (Node node : getNodes()) {
			NameServerContext namingContext = (NameServerContext) node;
			if (namingContext.getNameServerName().equals(nameServer)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * ネームサーバを追加する
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public synchronized NameServerContext addNameServer(String nameServer) {
		if (isExist(nameServer)) {
			throw new AlreadyExistException();
		}

		// TODO: CORBA以外への対応
		return addCorbaNameServer(nameServer);
	}

	private NameServerContext addCorbaNameServer(String nameServer) {
//		long start = System.currentTimeMillis();
		NameServerAccesser accessor = NameServerAccesser.getInstance();
		NamingContextExt namingContext = accessor
				.getNameServerRootContext(nameServer);
		if (namingContext == null) {
			return null;
		}
		OpenRTMNaming.NamingNotifier notifier = accessor
				.getNamingNotifier(nameServer);
		NameServerContext result = NameServiceViewWrapperFactory.getInstance()
				.getNameServiceContextCorbaWrapper(namingContext, nameServer,
						notifier);
		if (result != null) {
			this.getNodes().add(result);
			result.synchronizeLocal();
		}
//		System.out.println(System.currentTimeMillis() -start);

		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 一定時間ごとに同期を行うようにする。
	 * 「0」を設定した場合には、同期しない。
	 * 
	 * UIスレッドからのネームサーバ追加、更新リクエストへの対応はgetNodes()呼び出しだけを
	 * synchronizeさせればOKのはず。
	 * NameServerContextは別インスタンスなので競合は発生しないはず。
	 * 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public synchronized void setSynchronizeInterval(final long milliSecond) {
		if (refreshThread == null) {
			refreshThread = new RefreshThread(milliSecond) {
				@Override
				protected void executeCommand() {
					try {
						synchronizeAll();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			refreshThread.setDaemon(true);
			refreshThread.start();
		} else {
			refreshThread.setSynchronizeInterval(milliSecond);
		}
	}

	/**
	 * すべてのネームサーバについて、リモート側を正として同期する。
	 */
	private void synchronizeAll() {
		List<NameServerContext> unModifyList = getUnModifiedNameServerContexts();
		for (NameServerContext nsc : unModifyList) {
			nsc.synchronizeLocal();
		}
	}

	private synchronized List<NameServerContext> getUnModifiedNameServerContexts() {
		List<NameServerContext> result = new ArrayList<NameServerContext>();
		for (Node node : getNodes()) {
			result.add((NameServerContext) node);
		}
		return result;
	}

} //NameServerManagerImpl
