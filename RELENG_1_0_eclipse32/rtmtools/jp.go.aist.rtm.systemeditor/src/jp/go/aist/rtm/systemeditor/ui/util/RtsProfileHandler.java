package jp.go.aist.rtm.systemeditor.ui.util;

import static jp.go.aist.rtm.toolscommon.model.component.Component.COMPOSITETYPE_GROUPING;
import static jp.go.aist.rtm.toolscommon.model.component.Component.COMPOSITETYPE_NONE;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeFactory;

import jp.go.aist.rtm.repositoryView.model.RepositoryViewItem;
import jp.go.aist.rtm.repositoryView.model.RepositoryViewLeafItem;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.corba.CorbaUtil;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import jp.go.aist.rtm.toolscommon.model.component.util.PortConnectorFactory;
import jp.go.aist.rtm.toolscommon.model.core.Point;
import jp.go.aist.rtm.toolscommon.model.core.Rectangle;
import jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.openrtp.namespaces.rts.version02.Component;
import org.openrtp.namespaces.rts.version02.ComponentExt;
import org.openrtp.namespaces.rts.version02.ConfigurationData;
import org.openrtp.namespaces.rts.version02.ConfigurationSet;
import org.openrtp.namespaces.rts.version02.Dataport;
import org.openrtp.namespaces.rts.version02.DataportConnector;
import org.openrtp.namespaces.rts.version02.DataportConnectorExt;
import org.openrtp.namespaces.rts.version02.DataportExt;
import org.openrtp.namespaces.rts.version02.ExecutionContext;
import org.openrtp.namespaces.rts.version02.Location;
import org.openrtp.namespaces.rts.version02.ObjectFactory;
import org.openrtp.namespaces.rts.version02.Participants;
import org.openrtp.namespaces.rts.version02.Property;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;
import org.openrtp.namespaces.rts.version02.Serviceport;
import org.openrtp.namespaces.rts.version02.ServiceportConnector;
import org.openrtp.namespaces.rts.version02.ServiceportConnectorExt;
import org.openrtp.namespaces.rts.version02.ServiceportExt;
import org.openrtp.namespaces.rts.version02.TargetComponent;
import org.openrtp.namespaces.rts.version02.TargetComponentExt;
import org.openrtp.namespaces.rts.version02.TargetPort;
import org.openrtp.namespaces.rts.version02.TargetPortExt;

import RTC.RTObject;
import RTC.RTObjectHelper;

import com.sun.org.apache.xerces.internal.jaxp.datatype.DatatypeFactoryImpl;

/**
 * RTSプロファイルの入出力を司るクラス
 *
 */
public class RtsProfileHandler {
	
	private boolean online;
	private List<RepositoryViewItem> repositoryModel;
	private RtsProfileExt originalProfile;
	private List<String> savedConnectors;
	private ObjectFactory factory;
	private SystemDiagram diagram;
	private static final String COMPONENT_PATH_ID = "COMPONENT_PATH_ID";

	/**
	 * RTSプロファイルをロードする
	 * @param targetFile	ロード対象のファイル
	 * @param kind			オンラインかオフラインかの種別
	 * @return				ロードしたシステムダイアグラム
	 * @throws Exception
	 */
	public SystemDiagram load(String targetFile, SystemDiagramKind kind) throws Exception {
		XmlHandler handler = new XmlHandler();
		RtsProfileExt profile = handler.loadXmlRts(targetFile);
		return load(profile, kind);
	}

	public SystemDiagram load(RtsProfileExt profile, SystemDiagramKind kind) throws Exception {
		diagram = ComponentFactory.eINSTANCE.createSystemDiagram();
		diagram.setProfile(profile);
		diagram.setKind(kind);
		setOnline(kind == SystemDiagramKind.ONLINE_LITERAL);
		populate(diagram, profile);
		return diagram;
	}

	/**
	 * オフラインのシステムダイアグラムをロードするために、RTCレポジトリのモデルをセットする
	 * @param repositoryModel
	 */
	public void setRepositoryModel(List<RepositoryViewItem> repositoryModel) {
		this.repositoryModel = repositoryModel;
	}

	/**
	 * ダイアグラムの直下に含まれる全コンポーネントに対し、IORからCORABAオブジェクトを設定する
	 * @param diagram
	 */
	public void populateCorbaBaseObject(SystemDiagram diagram) {
		for (Object element : diagram.getRegisteredComponents()) {
			if (!(element instanceof CorbaComponent)) continue;
			CorbaComponent component = (CorbaComponent)element;
			String ior = component.getIor();
			if (ior == null) continue;
			component.setCorbaObject(getRTObject(ior));
		}
	}

	private RTObject getRTObject(String ior) {
		try {
			return RTObjectHelper.narrow(CorbaUtil.stringToObject(ior));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * RTSプロファイルからダイアグラム内にあるコンポーネント間の接続を復元させる
	 * @param diagram
	 */
	public void restoreConnection(SystemDiagram diagram) {
		this.diagram = diagram;
		setOnline(diagram.getKind() == SystemDiagramKind.ONLINE_LITERAL);
		RtsProfileExt profile = diagram.getProfile();
		List<jp.go.aist.rtm.toolscommon.model.component.Component> components
			= diagram.getRegisteredComponents();
		populateDataConnector(components, profile.getDataPortConnectors());
		populateServiceConnector(components, profile.getServicePortConnectors());
	}

	/**
	 * RTSプロファイルからダイアグラム内にあるコンポーネントのコンフィグセットを復元させる
	 * @param diagram
	 */
	public void restoreConfigSet(SystemDiagram diagram) {
		for (jp.go.aist.rtm.toolscommon.model.component.Component targetComponent: 
				diagram.getRegisteredComponents()) {
			Component component = findComponent(targetComponent,
					diagram.getProfile().getComponents());
			populateConfigSets(targetComponent, component);
		}
	}
	public void restoreConfigSetbyIOR(SystemDiagram diagram) {
		for (jp.go.aist.rtm.toolscommon.model.component.Component targetComponent: 
				diagram.getRegisteredComponents()) {
			Component component = findComponentbyIOR(targetComponent,
					diagram.getProfile().getComponents());
			populateConfigSets(targetComponent, component);
		}
	}

	/**
	 * オフラインの複合コンポーネントのポートを復元させる
	 * @param diagram
	 */
	@SuppressWarnings("unchecked")
	public void restoreCompositeComponentPort(SystemDiagram diagram) {
		List<Component> source = diagram.getProfile().getComponents();
		for (jp.go.aist.rtm.toolscommon.model.component.Component targetComponent: 
				diagram.getRegisteredComponents()) {
			Component component = findComponent(targetComponent, source);
			populateCompositeComponentPort(targetComponent, component);
		}
	}

	/**
	 * RTSプロファイルを保存する
	 * @param diagram
	 * @return
	 */
	public RtsProfileExt save(SystemDiagram diagram) {
		this.diagram = diagram;
		
		setOnline(diagram.getKind() == SystemDiagramKind.ONLINE_LITERAL);
		originalProfile = diagram.getProfile();
		savedConnectors = new ArrayList<String>();
		
		factory = new ObjectFactory();
		RtsProfileExt profile = factory.createRtsProfileExt();
		profile.setId(diagram.getSystemId());
		DatatypeFactory dateFactory = new DatatypeFactoryImpl();
		profile.setCreationDate(dateFactory.newXMLGregorianCalendar(diagram.getCreationDate()));
		profile.setUpdateDate(dateFactory.newXMLGregorianCalendar(diagram.getUpdateDate()));
		profile.setVersion("0.2");
		
		populateComponents(diagram, profile);
		
		populateFromProfileOnly(profile);
		return profile;
	}

	// オンラインエディタとオフラインエディタのどちらから呼び出されたかを設定する
	void setOnline(boolean online) {
		this.online = online;
	}

	// Open時にRTSプロファイルをシステムダイアログに変換する
	void populate(SystemDiagram diagram, RtsProfileExt profile) {
		diagram.setSystemId(profile.getId());
		diagram.setCreationDate(profile.getCreationDate().toString());
		diagram.setUpdateDate(profile.getUpdateDate().toString());
		populate(diagram.getComponents(), profile.getComponents());
		// ポートはCORBAObjectまたはRTCProfileを正とするので、この段階ではまだ接続できない
	}

	// ベンドポイントの文字列表現をMap表現に変換する
	Map<Integer, Point> convertFromBendPointString(String bendPoint) {
		if (StringUtils.isBlank(bendPoint)) return null;
		String content = bendPoint.trim();
		content = content.substring(1, content.length() - 1).trim(); // { }除去
		
		Map<Integer, Point> result = new HashMap<Integer, Point>();
		while(content.length() > 0) {
			content = populatePoint(result, content);
		}
		
		return result;
	}

	// Save時にシステムダイアログ内に含まれるコンポーネントをRTSプロファイル内にセットする
	@SuppressWarnings("unchecked")
	private void populateComponents(SystemDiagram diagram,
			RtsProfileExt rtsProfile ) {
		List<Component> components = rtsProfile.getComponents();
		for (jp.go.aist.rtm.toolscommon.model.component.Component source:
				diagram.getRegisteredComponents()) {
			ComponentExt target = factory.createComponentExt();
			target.setId(source.getComponentId());
			target.setPathUri(source.getPathId());
			target.setInstanceName(source.getInstanceNameL());
			target.setCompositeType(source.getCompositeTypeL());
			target.setIsRequired(source.isRequired());
			
			Component original = findOriginal(source);
			
			populateExecutionContext(source, target, original);			
			populateComponentLocation(source, target);			
			populateComponentProperty(source, target, original);
			populatePorts(source, target, original, rtsProfile);
			populateConfigurationSet(source, target);
			populateParticipants(source, target, original);
			
			populateFromProfileOnly(target, original);
			components.add(target);
		}
	}

	// Save時にシステムダイアログ内に含まれるデータポートとそれらの接続をRTSプロファイル内にセットする
	// Save時にダイアグラム内に含まれるサービスポート（とその接続）の情報をRTSプロファイル内にセットする
	private void populatePorts(
			jp.go.aist.rtm.toolscommon.model.component.Component source, ComponentExt target, Component original
			, RtsProfileExt rtsProfile) {
		for (Object element : source.getOutports()) {
			Port portBase = (Port)element;
			addDataPort(portBase, target, original);
			for(Object obj: portBase.getConnectorProfiles() ) {
				addDataPortConnector((ConnectorProfile)obj, rtsProfile, portBase);
			}
		}
		for (Object element : source.getInports()) {
			Port portBase = (Port)element;
			addDataPort(portBase, target, original);
		}
		for (Object element : source.getServiceports()) {
			Port portBase = (Port)element;
			addServicePort(portBase, target, original);
			for(Object obj: portBase.getConnectorProfiles() ) {
				addServicePortConnector((ConnectorProfile)obj, rtsProfile, portBase);
			}
		}
	}

	// データポートコネクタをRTSに追加する
	private void addDataPortConnector(ConnectorProfile profile, RtsProfileExt rtsProfile
			, Port portBase) {
		String connectorId = profile.getConnectorId();
		if(savedConnectors.contains(connectorId) ) return;
		rtsProfile.getDataPortConnectors().add(saveDataPortConnector(portBase, profile));
		savedConnectors.add(connectorId);		
	}
	
	// サービスポートコネクタをRTSに追加する
	private void addServicePortConnector(ConnectorProfile profile, RtsProfileExt rtsProfile
			, Port portBase) {
		String connectorId = profile.getConnectorId();
		if(savedConnectors.contains(connectorId) ) return;
		rtsProfile.getServicePortConnectors().add(saveServicePortConnector(portBase, profile));
		savedConnectors.add(connectorId);		
	}

	// Save時にシステムダイアログ内に含まれるデータポート接続をRTSプロファイル内の該当要素に変換する
	private DataportConnector saveDataPortConnector(
			Port portBase, ConnectorProfile profile) {
		DataportConnectorExt connector = factory.createDataportConnectorExt();
		connector.setConnectorId(profile.getConnectorId());
		connector.setName(profile.getName());
		connector.setInterfaceType(profile.getInterfaceType());
		connector.setDataType(profile.getDataType());
		connector.setDataflowType(profile.getDataflowType());
		if(profile.getSubscriptionType()!=null) connector.setSubscriptionType(profile.getSubscriptionType());
		if(profile.getPushRate()!=null) connector.setPushInterval(profile.getPushRate());

		// ベンドポイントの保存
		PortConnector portConnector = diagram.getConnectorMap().get(profile.getConnectorId());
		if (portConnector != null) {
			saveBendPoint(portConnector.getRoutingConstraint().map(), connector.getProperties());			
		}
		
		DataportConnector original = findOrignalDataportConnector(profile.getConnectorId());

		connector.setSourceDataPort(createTargetPort(portBase.findPort(diagram, profile.getSourceString())
				, original == null ? null : original.getSourceDataPort()));		
		connector.setTargetDataPort(createTargetPort(portBase.findPort(diagram, profile.getTargetString())
				, original == null ? null : original.getTargetDataPort()));
		
		if (original instanceof DataportConnectorExt) {
			DataportConnectorExt originalExt = (DataportConnectorExt) original;
			connector.setComment(originalExt.getComment());
			connector.setVisible(Boolean.valueOf(originalExt.isVisible()));
			for (Property property : originalExt.getProperties()) {
				if (property.getName().equals("BEND_POINT")) continue;
				connector.getProperties().add(property);
			}
		}
		
		return connector;
	}

	// Save時にシステムダイアログ内に含まれるサービスポート接続をRTSプロファイル内の該当要素に変換する
	private ServiceportConnector saveServicePortConnector(
			Port portBase,
			ConnectorProfile profile) {
		ServiceportConnectorExt connector = factory.createServiceportConnectorExt();
		connector.setConnectorId(profile.getConnectorId());
		connector.setName(profile.getName());
		// ベンドポイントの保存
		PortConnector portConnector = diagram.getConnectorMap().get(profile.getConnectorId());
		if (portConnector != null) {
			saveBendPoint(portConnector.getRoutingConstraint().map(), connector.getProperties());			
		}

		ServiceportConnector original = findOrignalServiceportConnector(profile.getConnectorId());
		
		connector.setSourceServicePort(createTargetPort(portBase.findPort(diagram, profile.getSourceString())
				, original == null ? null : original.getSourceServicePort()));		
		connector.setTargetServicePort(createTargetPort(portBase.findPort(diagram, profile.getTargetString())
				, original == null ? null : original.getTargetServicePort()));
		
		if (original instanceof ServiceportConnectorExt) {
			if (original != null) {
				connector.setTransMethod(original.getTransMethod());
			}
			ServiceportConnectorExt originalExt = (ServiceportConnectorExt) original;
			connector.setComment(originalExt.getComment());
			connector.setVisible(Boolean.valueOf(originalExt.isVisible()));
			for (Property property : originalExt.getProperties()) {
				if (property.getName().equals("BEND_POINT")) continue;
				connector.getProperties().add(property);
			}
		}

		return connector;
	}

	// Open時に読み込んだRTSプロファイルから該当するデータポート接続を見つけ出す
	private DataportConnector findOrignalDataportConnector(String connectorId) {
		if (originalProfile == null) return null;
		for (DataportConnector temp : originalProfile.getDataPortConnectors()) {
			if (temp.getConnectorId().equals(connectorId)) return temp;
		}
		return null;
	}

	// Open時に読み込んだRTSプロファイルから該当するサービスポート接続を見つけ出す
	private ServiceportConnector findOrignalServiceportConnector(
			String connectorId) {
		if (originalProfile == null) return null;
		for (ServiceportConnector temp : originalProfile.getServicePortConnectors()) {
			if (temp.getConnectorId().equals(connectorId)) return temp;
		}
		return null;
	}

	// ベンドポイントをRTSプロファイル内に保存する
	@SuppressWarnings("unchecked")
	private void saveBendPoint(Map map,
			List<Property> rtsProperties) {
		if (map == null || map.isEmpty()) return;
		
		Property propt = factory.createProperty();
		propt.setName("BEND_POINT");
		propt.setValue(convertToBendPointString(map));
		rtsProperties.add(propt);
	}

	// ベンドポイントのMapを文字列表現に変換する
	@SuppressWarnings("unchecked")
	private String convertToBendPointString(Map map) {
		StringBuffer buffer = new StringBuffer();
		for (Object key :map.keySet()) {
			if (buffer.length() == 0) {
				buffer.append("{");
			} else {
				buffer.append(",");
			}
			buffer.append(key).append(":").append("(");
			jp.go.aist.rtm.toolscommon.model.core.Point point = (Point) map.get(key);
			buffer.append(point.getX()).append(",").append(point.getY());
			buffer.append(")");
		}
		buffer.append("}");
		return buffer.toString();
	}

	// プロファイルのTargetPortを生成して返す
	private TargetPort createTargetPort(Port target
			, TargetPort original) {
		TargetPortExt port = factory.createTargetPortExt();
		final jp.go.aist.rtm.toolscommon.model.component.Component container = (jp.go.aist.rtm.toolscommon.model.component.Component)target.eContainer();
		port.setComponentId(container.getComponentId());
		port.setInstanceName(container.getInstanceNameL());
		port.setPortName(target.getNameL());
		
		// pathIdをプロパティにセットする
		Property propt = factory.createProperty();
		propt.setName(COMPONENT_PATH_ID);
		propt.setValue(container.getPathId());
		port.getProperties().add(propt);

		if (original instanceof TargetPortExt) {
			TargetPortExt originalPort = (TargetPortExt) original;
			for (Property property : originalPort.getProperties()) {
				if (property.getName().equals(COMPONENT_PATH_ID)) continue;
				port.getProperties().add(property);
			}
		}
		return port;
	}

	// Save時にComponentのConfigurationSetの情報をRTSプロファイルにセットする
	private void populateConfigurationSet(
			jp.go.aist.rtm.toolscommon.model.component.Component source, ComponentExt target) {
		for (Object element : source.getConfigurationSets()) {
			jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet sourceConfig 
				= (jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet) element;
			ConfigurationSet config = factory.createConfigurationSet();
			config.setId(sourceConfig.getId());
			for (Object obj : sourceConfig.getConfigurationData()) {
				NameValue value = (NameValue) obj;
				ConfigurationData data = factory.createConfigurationData();
				data.setName(value.getName());
				data.setData(value.getValueAsString());
				config.getConfigurationData().add(data);
			}
			target.getConfigurationSets().add(config);
			if (sourceConfig.equals(source.getActiveConfigurationSet())) {
				target.setActiveConfigurationSet(sourceConfig.getId());
			}
		}
	}

	// Save時に子RTCの情報をRTSプロファイルにセットする
	private void populateParticipants(
			jp.go.aist.rtm.toolscommon.model.component.Component source, ComponentExt target, Component original) {
		for(Object childBase : source.getComponents() ) {
			jp.go.aist.rtm.toolscommon.model.component.Component childComponentBase = (jp.go.aist.rtm.toolscommon.model.component.Component)childBase;
			Participants participants = findOriginalParticipants(original, childComponentBase);
			if (participants != null) {
				target.getParticipants().add(participants);
				continue;
			}
			TargetComponentExt child = factory.createTargetComponentExt();
			child.setComponentId(childComponentBase.getComponentId());
			child.setInstanceName(childComponentBase.getInstanceNameL());
			
			// pathIdをプロパティにセットする
			Property propt = factory.createProperty();
			propt.setName(COMPONENT_PATH_ID);
			propt.setValue(childComponentBase.getPathId());
			child.getProperties().add(propt);

			participants = factory.createParticipants();
			participants.setParticipant(child);
			target.getParticipants().add(participants);
		}
	}

	// OpenしたRTSプロファイルに存在したParticipants要素を探し出す
	private Participants findOriginalParticipants(Component original,
			jp.go.aist.rtm.toolscommon.model.component.Component childComponentBase) {
		if (original == null) return null;
		for (Participants participants : original.getParticipants()) {
			TargetComponent source = participants.getParticipant();
			if (source.getComponentId().equals(childComponentBase.getComponentId())
					&& source.getInstanceName().equals(childComponentBase.getInstanceNameL()))
				// pathIdもチェックする
				if (isSamePathId(childComponentBase, source)) return participants;
		}
		return null;
	}

	// Save時にデータポートの情報をRTSプロファイルに追加する
	private void addDataPort(Port portBase,
			ComponentExt target, Component original) {
		DataportExt port = factory.createDataportExt();
		port.setName(portBase.getNameL());
		if (original != null) {
			Dataport originalPort = findOriginalPort(original.getDataPorts(), port.getName());
			if (originalPort instanceof DataportExt) {
				DataportExt source = (DataportExt) originalPort;
				port.setComment(source.getComment());
				port.setVisible(Boolean.valueOf(source.isVisible()));
				port.getProperties().addAll(source.getProperties());
			}
		}
		target.getDataPorts().add(port);
	}

	// Save時にサービスポートの情報をRTSプロファイルに追加する
	private void addServicePort(Port portBase,
			ComponentExt target, Component original) {
		ServiceportExt port = factory.createServiceportExt();
		port.setName(portBase.getNameL());
		if (original != null) {
			Serviceport originalPort = findOriginalPort(original.getServicePorts(), port.getName());
			if (originalPort instanceof ServiceportExt) {
				ServiceportExt source = (ServiceportExt) originalPort;
				port.setComment(source.getComment());
				port.setVisible(Boolean.valueOf(source.isVisible()));
				port.getProperties().addAll(source.getProperties());
			}
		}
		target.getServicePorts().add(port);
	}

	// OpenしたRTSプロファイルに存在したDataport要素を探し出す
	private Dataport findOriginalPort(List<Dataport> dataPorts, String name) {
		for (Dataport dataport : dataPorts) {
			if (dataport.getName().equals(name)) return dataport;
		}
		return null;
	}

	// OpenしたRTSプロファイルに存在したServiceport要素を探し出す
	private Serviceport findOriginalPort(List<Serviceport> ports, String name) {
		for (Serviceport port : ports) {
			if (port.getName().equals(name)) return port;
		}
		return null;
	}

	// OpenしたRTSプロファイルに存在したComponent要素を探し出す
	private Component findOriginal(jp.go.aist.rtm.toolscommon.model.component.Component source) {
		if (originalProfile == null) return null;
		return findComponent(source, originalProfile.getComponents());
	}

	// EMFコンポーネントに合致するRTSコンポーネントを探し出す
	public static Component findComponent(
			jp.go.aist.rtm.toolscommon.model.component.Component source
			, List<Component> components) {
		for (Component component : components) {
			if (component.getId().equals(source.getComponentId()) 
					&& component.getInstanceName().equals(source.getInstanceNameL())
					&& component.getPathUri().equals(source.getPathId()))
				return component;
		}
		return null;
	}
	public static Component findComponentbyIOR(
			jp.go.aist.rtm.toolscommon.model.component.Component source
			, List<Component> components) {
		for (Component component : components) {
			List<Property> props = ((ComponentExt)component).getProperties();
			if( props==null ) continue;
			String compIor = null;
			for( Property prop : props ) {
				if( prop.getName().equals("IOR") ) {
					compIor = prop.getValue();
					break;
				}
			}
			if( compIor==null ) continue;
			if( compIor.equals(((CorbaComponent)source).getIor()) ) {
				return component;
			}
		}
		return null;
	}

	// Save時にコンポーネントのプロパティをセットする
	private void populateComponentProperty(
			jp.go.aist.rtm.toolscommon.model.component.Component source, ComponentExt target
			, Component original) {
		List<Property> rtsProperties = target.getProperties();
		if (original instanceof ComponentExt) {
			for(Property prop :  ((ComponentExt)original).getProperties()) {
				if (!prop.getName().equals("IOR")) rtsProperties.add(prop);
			}			
		}
		populateIOR(rtsProperties, source);
	}

	// Save時にコンポーネントの位置情報をセットする
	private void populateComponentLocation(jp.go.aist.rtm.toolscommon.model.component.Component source,
			ComponentExt target) {
		target.setLocation(new Location());
		target.getLocation().setX(BigInteger.valueOf(source.getConstraint().getX()));
		target.getLocation().setY(BigInteger.valueOf(source.getConstraint().getY()));
		target.getLocation().setHeight(BigInteger.valueOf(source.getConstraint().getHeight()));
		target.getLocation().setWidth(BigInteger.valueOf(source.getConstraint().getWidth()));
		target.getLocation().setDirection(source.getOutportDirection());
	}
	
	// IORを保存する
	private void populateIOR(List<Property> rtsProperties,
			jp.go.aist.rtm.toolscommon.model.component.Component source) {
		if (!(source instanceof CorbaComponent)) return;
		RTObject corbaObjectInterface = ((CorbaComponent)source).getCorbaObjectInterface();
		if (corbaObjectInterface == null) return;
		rtsProperties.add(createProperty("IOR", corbaObjectInterface.toString()));
	}

	// IORを復元する
	private void populateIOR(
			jp.go.aist.rtm.toolscommon.model.component.Component targetComponent,
			List<Property> properties) {
		if (!(targetComponent instanceof CorbaComponent)) return;
		for (Property prop : properties) {
			if (prop.getName().equals("IOR")) {
				((CorbaComponent)targetComponent).setIor(prop.getValue());
			}
		}
	}

	// Save時に元のファイルにあったコンポーネントの拡張属性をセットする
	private void populateFromProfileOnly(ComponentExt target, Component original) {
		if (!(original instanceof ComponentExt)) return;
		ComponentExt source = (ComponentExt) original;
		target.setComment(source.getComment());
		target.setVisible(Boolean.valueOf(source.isVisible()));
	}

	// Save時にExecutionContextの情報をRTSプロファイルにセットする
	private void populateExecutionContext(
			jp.go.aist.rtm.toolscommon.model.component.Component source,
			ComponentExt target, Component original) {
		for (Object obj : source.getExecutionContexts()) {
			jp.go.aist.rtm.toolscommon.model.component.ExecutionContext e = (jp.go.aist.rtm.toolscommon.model.component.ExecutionContext) obj;
			String id = source.getExecutionContextId(e);
			ExecutionContext ec = factory.createExecutionContextExt();
			ec.setId((id == null) ? "" : id);
			ec.setKind(e.getKindName());
			ec.setRate(e.getRateL());
			target.getExecutionContexts().add(ec);
		}
	}

	// Save時に、元のRTSプロファイル内だけに存在し、システムエディタでは使用しない要素を書き戻す
	private void populateFromProfileOnly(RtsProfileExt target) {
		if (originalProfile == null) return;
		target.setAbstract(originalProfile.getAbstract());
		target.getGroups().addAll(originalProfile.getGroups());
		target.getProperties().addAll(originalProfile.getProperties());
		target.setStartUp(originalProfile.getStartUp());
		target.setShutDown(originalProfile.getShutDown());
		target.setActivation(originalProfile.getActivation());
		target.setDeactivation(originalProfile.getDeactivation());
		target.setResetting(originalProfile.getResetting());
		target.setInitializing(originalProfile.getInitializing());
		target.setFinalizing(originalProfile.getFinalizing());
		target.setComment(originalProfile.getComment());
		target.getVersionUpLogs().addAll(originalProfile.getVersionUpLogs());
	}

	// ベンドポイントの文字列表現からをMap表現のエントリを1つ取り出して、セットする
	private String populatePoint(Map<Integer, Point> result, String content) {
		String key = content.substring(0, content.indexOf(":"));
		String value = content.substring(content.indexOf(":") + 1).trim();
		String x = value.substring(1, value.indexOf(",")).trim();
		value = value.substring(value.indexOf(",") + 1).trim();
		String y = value.substring(0, value.indexOf(")")).trim();
		
		Point point = new Point();
		point.setX(Integer.parseInt(x));
		point.setY(Integer.parseInt(y));
		result.put(new Integer(key), point);
		
		if (value.indexOf(",") < 0) return "";
		return value.substring(value.indexOf(",") + 1).trim();
	}

	// RTSプロファイルのプロパティのリストから、指定したキーの値を探し出す。
	private String findProperyValue(List<Property> properties, String name) {
		for (Property property : properties) {
			if (property.getName().equals(name)) return property.getValue();
		}
		return null;
	}

	// RTSプロファイルのプロパティを作成する
	private Property createProperty(String name, String value) {
		Property propm = factory.createProperty();
		propm.setName(name);
		propm.setValue(value);
		return propm;
	}

	// RTSプロファイルからEMFコンポーネントを復元する
	@SuppressWarnings("unchecked")
	private void populate(EList target, List<Component> source) {
		List<jp.go.aist.rtm.toolscommon.model.component.Component> registeredComponents = 
			new ArrayList<jp.go.aist.rtm.toolscommon.model.component.Component>();
		for (Component component : source) {
			jp.go.aist.rtm.toolscommon.model.component.Component targetComponent = createComponent(component);
			targetComponent.setComponentId(component.getId());
			targetComponent.setPathId(component.getPathUri());
			targetComponent.setInstanceNameL(component.getInstanceName());
			populateCompositeType(targetComponent, component.getCompositeType());
			targetComponent.setRequired(component.isIsRequired());
			
			// portはCORBAObjectまたはRTEProfileを正とする
			// CORBA経由で取得したコンフィグセットを正とする

			if (online && targetComponent.getCompositeTypeL().equals(COMPOSITETYPE_GROUPING)) {
				// Grouping複合RTCの場合は、ConfigurationSetを復元する
				populateConfigSets(targetComponent, component);
			}

			if (component instanceof ComponentExt) {
				ComponentExt componentExt = (ComponentExt)component;
				targetComponent.setConstraint(toRectangle(componentExt.getLocation()));
				targetComponent.setOutportDirection(componentExt.getLocation().getDirection());
				populateIOR(targetComponent, componentExt.getProperties());
			}
			
			registeredComponents.add(targetComponent);
		}
		for (jp.go.aist.rtm.toolscommon.model.component.Component targetComponent
				: registeredComponents) {
			if (isShown(targetComponent, registeredComponents, source))
				target.add(targetComponent);
		}
	}

	// ルートのシステムダイアグラムに表示されるのであれば、trueを返す
	@SuppressWarnings("unchecked")
	private boolean isShown(
			jp.go.aist.rtm.toolscommon.model.component.Component targetComponent,
			List<jp.go.aist.rtm.toolscommon.model.component.Component> registeredComponents
			, List<Component> source) {
		// targetComponentをparentComponentの子として追加する処理をここで行う
		for (int i=0; i < registeredComponents.size(); i++) {
			jp.go.aist.rtm.toolscommon.model.component.Component parentComponent 
				= registeredComponents.get(i);
			Component component = source.get(i);
			for (Participants participants : component.getParticipants()) {
				TargetComponent participant = participants.getParticipant();
				if (targetComponent.getComponentId().equals(participant.getComponentId())
						&& targetComponent.getInstanceNameL().equals(participant.getInstanceName())) {
					// pathIdもチェックする
					if (isSamePathId(targetComponent, participant)) {
						parentComponent.getComponents().add(targetComponent);
						return false;
					}	
				}
			}
		}
		return true;
	}

	// RtsプロファイルのLocationをRectangleに変換する
	private Rectangle toRectangle(Location location) {
		Rectangle result = new Rectangle();
		result.setX(location.getX().intValue());
		result.setY(location.getY().intValue());
		result.setWidth(location.getWidth().intValue());
		result.setHeight(location.getHeight().intValue());
		return result;
	}

	// 複合コンポーネントのポートを復元させる（オフライン）
	@SuppressWarnings("unchecked")
	private void populateCompositeComponentPort(
			jp.go.aist.rtm.toolscommon.model.component.Component targetComponent
			, Component component) {
		if (!targetComponent.isCompositeComponent()) return;
		if (!online || targetComponent.getCompositeTypeL().equals(COMPOSITETYPE_GROUPING)) {
				List<jp.go.aist.rtm.toolscommon.model.component.Component> emptyList 
					= Collections.emptyList();
				targetComponent.addComponentsR(emptyList);
		}
	}

	// データポートの接続を復元させる
	@SuppressWarnings("unchecked")
	private void populateDataConnector(List<jp.go.aist.rtm.toolscommon.model.component.Component> components,
			List<DataportConnector> dataPortConnectors) {
		for (DataportConnector connBase : dataPortConnectors) {
			ConnectorProfile conn = ComponentFactory.eINSTANCE.createConnectorProfile();
			conn.setConnectorId(connBase.getConnectorId());
			conn.setName(connBase.getName());
			conn.setInterfaceType(connBase.getInterfaceType());
			conn.setDataType(connBase.getDataType());
			conn.setDataflowType(connBase.getDataflowType());
			if(connBase.getSubscriptionType()!=null) conn.setSubscriptionType(connBase.getSubscriptionType());
			if(connBase.getPushInterval()!=null) conn.setPushRate(connBase.getPushInterval());
			connectPorts(conn, components,
					connBase.getTargetDataPort(), connBase.getSourceDataPort()
					, getBendPoint(connBase));
		}
	}

	// RTSプロファイルのサービスポート接続から接続を復元する
	private void populateServiceConnector(
			List<jp.go.aist.rtm.toolscommon.model.component.Component> components,
			List<ServiceportConnector> servicePortConnectors) {
		for( ServiceportConnector connBase : servicePortConnectors) {
			ConnectorProfile conn = ComponentFactory.eINSTANCE.createConnectorProfile();
			conn.setConnectorId(connBase.getConnectorId());
			conn.setName(connBase.getName());
			connectPorts(conn, components,
					connBase.getTargetServicePort(), connBase.getSourceServicePort()
					, getBendPoint(connBase));
		}
	}

	// ベンドポイントをプロパティから復元する
	private Map<Integer, Point> getBendPoint(DataportConnector connBase) {
		if (!(connBase instanceof DataportConnectorExt)) return null;
		DataportConnectorExt connBaseExt = (DataportConnectorExt) connBase;
		return getBendPoint(connBaseExt.getProperties());
	}

	// ベンドポイントをプロパティから復元する
	private Map<Integer, Point> getBendPoint(ServiceportConnector connBase) {
		if (!(connBase instanceof ServiceportConnectorExt)) return null;
		ServiceportConnectorExt connBaseExt = (ServiceportConnectorExt) connBase;
		return getBendPoint(connBaseExt.getProperties());
	}

	// ベンドポイントをプロパティから復元する
	private Map<Integer, Point> getBendPoint(List<Property> properties) {
		String bendPointString = findProperyValue(properties, "BEND_POINT");
		if (bendPointString == null) return null;
		return convertFromBendPointString(bendPointString);
	}

	// ポート間の接続を復元する
	@SuppressWarnings("unchecked")
	private void connectPorts(ConnectorProfile conn
			, List<jp.go.aist.rtm.toolscommon.model.component.Component> components,
			TargetPort target, TargetPort source, Map<Integer, Point> bendPoint) {
		PortConnector connector = PortConnectorFactory.createPortConnector(online);
		if (!online) {
			diagram.getConnectorMap().put(conn.getConnectorId(), connector);
		}
		connector.setSource(findPort(components, source));
		connector.setTarget(findPort(components, target));
		connector.setConnectorProfile(conn);
		if (bendPoint != null && !bendPoint.isEmpty()){
			connector.getRoutingConstraint().map().clear();
			connector.getRoutingConstraint().map().putAll(bendPoint);
		}
		connector.createConnectorR();
	}

	// 接続端または子RTCのコンポーネントを探し出す
	private jp.go.aist.rtm.toolscommon.model.component.Component findChild(
			List<jp.go.aist.rtm.toolscommon.model.component.Component> target
			, TargetComponent participant) {
		for (Object object : target) {
			jp.go.aist.rtm.toolscommon.model.component.Component component = (jp.go.aist.rtm.toolscommon.model.component.Component)object;
			if (component.getComponentId().equals(participant.getComponentId())
					&& component.getInstanceNameL().equals(participant.getInstanceName())){
				// pathIdもチェックする
				if (isSamePathId(component, participant)) return component;
			}
		}
		throw new IllegalStateException(Messages.getString("RtsProfileHandler.7") + participant.getComponentId() 
				+ Messages.getString("RtsProfileHandler.8") + participant.getInstanceName()
				+ ",pathId=" + getPathId(participant));
	}

	// pathIdも等しいかをチェックする
	private boolean isSamePathId(
			jp.go.aist.rtm.toolscommon.model.component.Component component,
			TargetComponent participant) {
		String pathId = getPathId(participant);
		if (pathId == null) return true;
		return pathId.equals(component.getPathId());
	}

	// 保存されている接続コンポーネントのPathIdを取り出す
	private String getPathId(TargetComponent participant) {
		if (participant instanceof TargetPortExt) {
			TargetPortExt saved = (TargetPortExt) participant;
			for (Property property : saved.getProperties()) {
				if(property.getName().equals(COMPONENT_PATH_ID)) return property.getValue();
			}
		}
		if (participant instanceof TargetComponentExt) {
			TargetComponentExt saved = (TargetComponentExt) participant;
			for (Property property : saved.getProperties()) {
				if(property.getName().equals(COMPONENT_PATH_ID)) return property.getValue();
			}
		}
		return null;
	}

	// 接続端のポートを探し出す
	private Port findPort(List<jp.go.aist.rtm.toolscommon.model.component.Component> components
			, TargetPort target) {
		jp.go.aist.rtm.toolscommon.model.component.Component component = findChild(components, target);
		for(Object trgPort : component.getPorts() ) {
			Port port = (Port)trgPort;
			if(target.getPortName().equals(port.getNameL())) {
				return port;
			}
		}
		throw new IllegalStateException(Messages.getString("RtsProfileHandler.10") + target.getComponentId() + Messages.getString("RtsProfileHandler.11") + target.getInstanceName() + Messages.getString("RtsProfileHandler.12") + target.getPortName());
	}

	// EMFのコンポーネントを作成する
	private jp.go.aist.rtm.toolscommon.model.component.Component createComponent(Component component) {
		if (online) {
			if(component.getCompositeType().equals(COMPOSITETYPE_GROUPING)) {
				return ComponentFactory.eINSTANCE.createComponentSpecification();
			}
			return ComponentFactory.eINSTANCE.createCorbaComponent();
		} else if(component.getCompositeType().equals(COMPOSITETYPE_NONE)) {
			String componentid = component.getId();
			String pathId = component.getPathUri();
			jp.go.aist.rtm.toolscommon.model.component.Component spec = findRTC(componentid, pathId, repositoryModel);
			if( spec==null ) {
				throw new IllegalStateException("Target Component["+ componentid +"]("+ pathId + ") does not exist in RepositoryView.");
			}
			return spec.copy();
		} else {
			return ComponentFactory.eINSTANCE.createComponentSpecification();
		}
	}

	// レポジトリからRTCを探し出す（オフライン）
	@SuppressWarnings("unchecked")
	private jp.go.aist.rtm.toolscommon.model.component.Component findRTC(String id, String pathId
			, List<RepositoryViewItem> models) {
		if (models == null) return null;
		for(RepositoryViewItem item : models) {
			if( item instanceof RepositoryViewLeafItem ) {
				ComponentSpecification target = ((RepositoryViewLeafItem)item).getComponent();
				if( target == null) return null;
				if(target.getComponentId().equals(id) && target.getPathId().equals(pathId)) return target;
			} else {
				jp.go.aist.rtm.toolscommon.model.component.Component result = findRTC(id, pathId, item.getChildren());
				if( result!=null ) return result;
			}
		}
		return null;
	}

	// RTSプロファイルからコンポジット種別を復元する
	private void populateCompositeType(jp.go.aist.rtm.toolscommon.model.component.Component targetComponent,
			String compositeType) {
		if (compositeType.equals("None")) return;
		targetComponent.setCategoryL("composite." + compositeType);
	}

	// RTSプロファイルからコンフィグセットを復元する
	@SuppressWarnings("unchecked")
	private void populateConfigSets(jp.go.aist.rtm.toolscommon.model.component.Component targetComponent,
			Component component) {
		targetComponent.getConfigurationSets().clear();
		String activeId = component.getActiveConfigurationSet();
		for (ConfigurationSet configSet : component.getConfigurationSets()) {
			jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet value = ComponentFactory.eINSTANCE.createConfigurationSet();
			if (configSet.getId().equals(activeId)) targetComponent.setActiveConfigurationSet(value);
			value.setId(configSet.getId());
			for (ConfigurationData configData : configSet.getConfigurationData()) {
				NameValue nv = ComponentFactory.eINSTANCE.createNameValue();
				nv.setName(configData.getName());
				nv.setValue(configData.getData());
				
				value.getConfigurationData().add(nv);
			}
			targetComponent.getConfigurationSets().add(value);
		}
		
	}

}
