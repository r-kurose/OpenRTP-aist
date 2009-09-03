package jp.go.aist.rtm.nameserviceview.factory;

import jp.go.aist.rtm.nameserviceview.model.nameservice.NameServiceReference;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameserviceFactory;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingContextNode;
import jp.go.aist.rtm.toolscommon.factory.MappingRuleFactory;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;

import org.omg.CosNaming.Binding;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;

/**
 * RtcLinkの内部で使用されるドメインオブジェクトを作成するファクトリ
 * <p>
 * 内部では、同期フレームワークを使用している。
 * NameServerNamingContext(CORBA専用）を作成する用途にしか、今のところ用いられていない
 */
public class NameServiceViewWrapperFactory {

    private static NameServiceViewWrapperFactory __instance = null;
    
    private SynchronizationManager synchronizationManager;

    /**
     * コンストラクタ
     * 
     * @param mappingRules
     */
    private NameServiceViewWrapperFactory(MappingRule[] mappingRules) {
    	 synchronizationManager = new SynchronizationManager(mappingRules);
    }

    /**
     * ファクトリのシングルトンを取得する
     * 
     * @return ファクトリのシングルトン
     */
    public static NameServiceViewWrapperFactory getInstance() {
        if (__instance == null) {
            __instance = new NameServiceViewWrapperFactory(MappingRuleFactory
                    .getMappingRule());
        }

        return __instance;
    }


    /**
     * ネームコンテクストオブジェクトとネームサーバ名から、ネームサーバのドメインオブジェクトを作成する
     * 
     * @param namingContext
     *            ネーミングコンテクスト
     * @param nameServerName
     *            ネームサーバ名
     * @return ネームサーバのドメインオブジェクト
     */
    public NamingContextNode getNameServiceContextCorbaWrapper(
            NamingContextExt namingContext, String nameServerName) {

        NameServiceReference nameServiceReference = NameserviceFactory.eINSTANCE.createNameServiceReference();
        nameServiceReference.setNameServerName(nameServerName);
        Binding binding = new Binding();
        binding.binding_name = new NameComponent[] {};
        nameServiceReference.setBinding(binding);
        nameServiceReference.setRootNamingContext(namingContext);
        
        return (NamingContextNode) synchronizationManager.createLocalObject(null,
                new Object[]{namingContext, nameServiceReference}, null, false);
    }
}
