package jp.go.aist.rtm.rtcbuilder._test._100;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigSetParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;

public class CXXDocLongTest extends TestBase {

	protected void setUp() throws Exception {
	}

	public void testDocAllLong() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion("1.0.0");
		//
		rtcParam.setDocCreator("Noriaki Ando <n-ando@aist.go.jp>34567894123456789512345678961234567897123456789812345");
		rtcParam.setDocLicense("Copyright (C) 2006-2008 ���C�Z���X12345678901234567890123456789012345678901234567890");
		rtcParam.setDocDescription("�{�R���|�[�l���g�̊T�v�־1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocInOut("�{�R���|�[�l���g�̓�o��1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocAlgorithm("�{�R���|�[�l���g�̃A���S���Y���Ȃ�1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocReference("�Q�l�����̏��1234567890123456789012345678901234567890123456789012345678901234567890");
		//
		rtcParam.setDocActionOverView(IRtcBuilderConstants.ACTIVITY_INITIALIZE, "on_initialize�T�v�־1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPreCondition(IRtcBuilderConstants.ACTIVITY_INITIALIZE, "on_initialize���O��1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPostCondition(IRtcBuilderConstants.ACTIVITY_INITIALIZE, "on_initialize�����1234567890123456789012345678901234567890123456789012345678901234567890");
		//
		rtcParam.setDocActionOverView(IRtcBuilderConstants.ACTIVITY_FINALIZE, "on_finalize�T�v�־1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPreCondition(IRtcBuilderConstants.ACTIVITY_FINALIZE, "on_finalize���O��1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPostCondition(IRtcBuilderConstants.ACTIVITY_FINALIZE, "on_finalize�����1234567890123456789012345678901234567890123456789012345678901234567890");
		//
		rtcParam.setDocActionOverView(IRtcBuilderConstants.ACTIVITY_STARTUP, "on_startup�T�v�־1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPreCondition(IRtcBuilderConstants.ACTIVITY_STARTUP, "on_startup���O��1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPostCondition(IRtcBuilderConstants.ACTIVITY_STARTUP, "on_startup�����1234567890123456789012345678901234567890123456789012345678901234567890");
		//
		rtcParam.setDocActionOverView(IRtcBuilderConstants.ACTIVITY_SHUTDOWN, "on_shutdown�T�v�־1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPreCondition(IRtcBuilderConstants.ACTIVITY_SHUTDOWN, "on_shutdown���O��1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPostCondition(IRtcBuilderConstants.ACTIVITY_SHUTDOWN, "on_shutdown�����1234567890123456789012345678901234567890123456789012345678901234567890");
		//
		rtcParam.setDocActionOverView(IRtcBuilderConstants.ACTIVITY_ACTIVATED, "on_activated�T�v�־1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPreCondition(IRtcBuilderConstants.ACTIVITY_ACTIVATED, "on_activated���O��1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPostCondition(IRtcBuilderConstants.ACTIVITY_ACTIVATED, "on_activated�����1234567890123456789012345678901234567890123456789012345678901234567890");
		//
		rtcParam.setDocActionOverView(IRtcBuilderConstants.ACTIVITY_DEACTIVATED, "on_deactivated�T�v�־1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPreCondition(IRtcBuilderConstants.ACTIVITY_DEACTIVATED, "on_deactivated���O��1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPostCondition(IRtcBuilderConstants.ACTIVITY_DEACTIVATED, "on_deactivated�����1234567890123456789012345678901234567890123456789012345678901234567890");
		//
		rtcParam.setDocActionOverView(IRtcBuilderConstants.ACTIVITY_EXECUTE, "on_execute�T�v�־1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPreCondition(IRtcBuilderConstants.ACTIVITY_EXECUTE, "on_execute���O��1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPostCondition(IRtcBuilderConstants.ACTIVITY_EXECUTE, "on_execute�����1234567890123456789012345678901234567890123456789012345678901234567890");
		//
		rtcParam.setDocActionOverView(IRtcBuilderConstants.ACTIVITY_ABORTING, "on_aborting�T�v�־1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPreCondition(IRtcBuilderConstants.ACTIVITY_ABORTING, "on_aborting���O��1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPostCondition(IRtcBuilderConstants.ACTIVITY_ABORTING, "on_aborting�����1234567890123456789012345678901234567890123456789012345678901234567890");
		//
		rtcParam.setDocActionOverView(IRtcBuilderConstants.ACTIVITY_ERROR, "on_error�T�v�־1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPreCondition(IRtcBuilderConstants.ACTIVITY_ERROR, "on_error���O��1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPostCondition(IRtcBuilderConstants.ACTIVITY_ERROR, "on_error�����1234567890123456789012345678901234567890123456789012345678901234567890");
		//
		rtcParam.setDocActionOverView(IRtcBuilderConstants.ACTIVITY_RESET, "on_reset�T�v�־1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPreCondition(IRtcBuilderConstants.ACTIVITY_RESET, "on_reset���O��1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPostCondition(IRtcBuilderConstants.ACTIVITY_RESET, "on_reset�����1234567890123456789012345678901234567890123456789012345678901234567890");
		//
		rtcParam.setDocActionOverView(IRtcBuilderConstants.ACTIVITY_STATE_UPDATE, "on_state_update�T�v�־1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPreCondition(IRtcBuilderConstants.ACTIVITY_STATE_UPDATE, "on_state_update���O��1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPostCondition(IRtcBuilderConstants.ACTIVITY_STATE_UPDATE, "on_state_update�����1234567890123456789012345678901234567890123456789012345678901234567890");
		//
		rtcParam.setDocActionOverView(IRtcBuilderConstants.ACTIVITY_RATE_CHANGED, "on_rate_changed�T�v�־1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPreCondition(IRtcBuilderConstants.ACTIVITY_RATE_CHANGED, "on_rate_changed���O��1234567890123456789012345678901234567890123456789012345678901234567890");
		rtcParam.setDocActionPostCondition(IRtcBuilderConstants.ACTIVITY_RATE_CHANGED, "on_rate_changed�����1234567890123456789012345678901234567890123456789012345678901234567890");
		//
		genParam.getRtcParams().add(rtcParam);
		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 

		DataPortParam datap1 = new DataPortParam("InP1", "RTC::TimedShort", "InName1", 0);
		datap1.setDocDescription("InPort1�̊T�v1234567890123456789012345678901234567890123456789012345678901234567890");
		datap1.setDocType("InPort1�̃f�[�^�̌^1234567890123456789012345678901234567890123456789012345678901234567890");
		datap1.setDocNum("InPort1�̃f�[�^�̐�1234567890123456789012345678901234567890123456789012345678901234567890");
		datap1.setDocSemantics("InPort1�̃f�[�^�̈Ӗ�1234567890123456789012345678901234567890123456789012345678901234567890");
		datap1.setDocUnit("InPort1�̃f�[�^�̒P��1234567890123456789012345678901234567890123456789012345678901234567890");
		datap1.setDocOccurrence("InPort1�̃f�[�^�̔����p�x1234567890123456789012345678901234567890123456789012345678901234567890");
		datap1.setDocOperation("InPort1�̃f�[�^�̏������1234567890123456789012345678901234567890123456789012345678901234567890");
		dataport.add(datap1);

		DataPortParam datap2 = new DataPortParam("InP2", "RTC::TimedLong", "InNm2", 0);
		datap2.setDocDescription("InPort2�̊T�v1234567890123456789012345678901234567890123456789012345678901234567890");
		datap2.setDocType("InPort2�̃f�[�^�̌^1234567890123456789012345678901234567890123456789012345678901234567890");
		datap2.setDocNum("InPort2�̃f�[�^�̐�1234567890123456789012345678901234567890123456789012345678901234567890");
		datap2.setDocSemantics("InPort2�̃f�[�^�̈Ӗ�1234567890123456789012345678901234567890123456789012345678901234567890");
		datap2.setDocUnit("InPort2�̃f�[�^�̒P��1234567890123456789012345678901234567890123456789012345678901234567890");
		datap2.setDocOccurrence("InPort2�̃f�[�^�̔����p�x1234567890123456789012345678901234567890123456789012345678901234567890");
		datap2.setDocOperation("InPort2�̃f�[�^�̏������1234567890123456789012345678901234567890123456789012345678901234567890");
		dataport.add(datap2);
		
		rtcParam.getInports().addAll(dataport);
		//
		List<DataPortParam> outport = new ArrayList<DataPortParam>(); 

		DataPortParam datap3 = new DataPortParam("OutP1", "RTC::TimedLong", "OutName1", 0);
		datap3.setDocDescription("OutPort1�̊T�v1234567890123456789012345678901234567890123456789012345678901234567890");
		datap3.setDocType("OutPort1�̃f�[�^�̌^1234567890123456789012345678901234567890123456789012345678901234567890");
		datap3.setDocNum("OutPort1�̃f�[�^�̐�1234567890123456789012345678901234567890123456789012345678901234567890");
		datap3.setDocSemantics("OutPort1�̃f�[�^�̈Ӗ�1234567890123456789012345678901234567890123456789012345678901234567890");
		datap3.setDocUnit("OutPort1�̃f�[�^�̒P��1234567890123456789012345678901234567890123456789012345678901234567890");
		datap3.setDocOccurrence("OutPort1�̃f�[�^�̔����p�x1234567890123456789012345678901234567890123456789012345678901234567890");
		datap3.setDocOperation("OutPort1�̃f�[�^�̏������1234567890123456789012345678901234567890123456789012345678901234567890");
		outport.add(datap3);

		DataPortParam datap4 = new DataPortParam("OutP2", "RTC::TimedFloat", "OutNme2", 0);
		datap4.setDocDescription("OutPort2�̊T�v1234567890123456789012345678901234567890123456789012345678901234567890");
		datap4.setDocType("OutPort2�̃f�[�^�̌^1234567890123456789012345678901234567890123456789012345678901234567890");
		datap4.setDocNum("OutPort2�̃f�[�^�̐�1234567890123456789012345678901234567890123456789012345678901234567890");
		datap4.setDocSemantics("OutPort2�̃f�[�^�̈Ӗ�1234567890123456789012345678901234567890123456789012345678901234567890");
		datap4.setDocUnit("OutPort2�̃f�[�^�̒P��1234567890123456789012345678901234567890123456789012345678901234567890");
		datap4.setDocOccurrence("OutPort2�̃f�[�^�̔����p�x1234567890123456789012345678901234567890123456789012345678901234567890");
		datap4.setDocOperation("OutPort2�̃f�[�^�̏������1234567890123456789012345678901234567890123456789012345678901234567890");
		outport.add(datap4);

		rtcParam.getOutports().addAll(outport);

		ServicePortParam service1 = new ServicePortParam("svPort",0);
		service1.setDocDescription("ServicePort1�̊T�v1234567890123456789012345678901234567890123456789012345678901234567890");
		service1.setDocIfDescription("ServicePort1�̃C���^�[�t�F�[�X�̊T�v1234567890123456789012345678901234567890123456789012345678901234567890");
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "acc", "", "", 
				rootPath + "\\resource\\MyService.idl", "MyService", "", 0);
		int1.setDocDescription("ServiceIF1�̊T�v�־1234567890123456789012345678901234567890123456789012345678901234567890");
		int1.setDocArgument("ServiceIF1�̈�1234567890123456789012345678901234567890123456789012345678901234567890");
		int1.setDocReturn("ServiceIF1�̕Ԓl1234567890123456789012345678901234567890123456789012345678901234567890");
		int1.setDocException("ServiceIF1�̗�O1234567890123456789012345678901234567890123456789012345678901234567890");
		int1.setDocPreCondition("ServiceIF1�̎��O��1234567890123456789012345678901234567890123456789012345678901234567890");
		int1.setDocPostCondition("ServiceIF1�̎����1234567890123456789012345678901234567890123456789012345678901234567890");
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		
		ServicePortParam service2 = new ServicePortParam("cmPort",0);
		service2.setDocDescription("ServicePort2�̊T�v1234567890123456789012345678901234567890123456789012345678901234567890");
		service2.setDocIfDescription("ServicePort2�̃C���^�[�t�F�[�X�̊T�v1234567890123456789012345678901234567890123456789012345678901234567890");
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service2, "rate", "", "", 
				rootPath + "\\resource\\DAQService.idl", "DAQService", "", 1);
		int2.setDocDescription("ServiceIF2�̊T�v�־1234567890123456789012345678901234567890123456789012345678901234567890");
		int2.setDocArgument("ServiceIF2�̈�1234567890123456789012345678901234567890123456789012345678901234567890");
		int2.setDocReturn("ServiceIF2�̕Ԓl1234567890123456789012345678901234567890123456789012345678901234567890");
		int2.setDocException("ServiceIF2�̗�O1234567890123456789012345678901234567890123456789012345678901234567890");
		int2.setDocPreCondition("ServiceIF2�̎��O��1234567890123456789012345678901234567890123456789012345678901234567890");
		int2.setDocPostCondition("ServiceIF2�̎����1234567890123456789012345678901234567890123456789012345678901234567890");
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);
		
		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>(); 
		ConfigSetParam config1 = new ConfigSetParam("int_param0","int","", "0");
		config1.setDocDataName("Config1�̖��O");
		config1.setDocDescription("Config1�̊T�v1234567890123456789012345678901234567890123456789012345678901234567890");
		config1.setDocDefaultVal("Config1�̃f�t�H���g�l");
		config1.setDocUnit("Config1�̒P��1234567890123456789012345678901234567890123456789012345678901234567890");
		config1.setDocRange("Config1�͈̔�1234567890123456789012345678901234567890123456789012345678901234567890");
		config1.setDocConstraint("Config1�̐����1234567890123456789012345678901234567890123456789012345678901234567890");
		configset.add(config1);
		ConfigSetParam config2 = new ConfigSetParam("int_param1","int","", "1");
		config2.setDocDataName("Config2�̖��O");
		config2.setDocDescription("Config2�̊T�v1234567890123456789012345678901234567890123456789012345678901234567890");
		config2.setDocDefaultVal("Config2�̃f�t�H���g�l");
		config2.setDocUnit("Config2�̒P��1234567890123456789012345678901234567890123456789012345678901234567890");
		config2.setDocRange("Config2�͈̔�1234567890123456789012345678901234567890123456789012345678901234567890");
		config2.setDocConstraint("Config2�̐����1234567890123456789012345678901234567890123456789012345678901234567890");
		configset.add(config2);
		ConfigSetParam config3 = new ConfigSetParam("double_param0","double","", "0.11");
		config3.setDocDataName("Config3�̖��O");
		config3.setDocDescription("Config3�̊T�v1234567890123456789012345678901234567890123456789012345678901234567890");
		config3.setDocDefaultVal("Config3�̃f�t�H���g�l");
		config3.setDocUnit("Config3�̒P��1234567890123456789012345678901234567890123456789012345678901234567890");
		config3.setDocRange("Config3�͈̔�1234567890123456789012345678901234567890123456789012345678901234567890");
		config3.setDocConstraint("Config3�̐����1234567890123456789012345678901234567890123456789012345678901234567890");
		configset.add(config3);
		ConfigSetParam config4 = new ConfigSetParam("str_param0","std::string","", "hoge");
		config4.setDocDataName("Config4�̖��O");
		config4.setDocDescription("Config4�̊T�v1234567890123456789012345678901234567890123456789012345678901234567890");
		config4.setDocDefaultVal("Config4�̃f�t�H���g�l");
		config4.setDocUnit("Config4�̒P��1234567890123456789012345678901234567890123456789012345678901234567890");
		config4.setDocRange("Config4�͈̔�1234567890123456789012345678901234567890123456789012345678901234567890");
		config4.setDocConstraint("Config4�̐����1234567890123456789012345678901234567890123456789012345678901234567890");
		configset.add(config4);
		ConfigSetParam config5 = new ConfigSetParam("str_param1","std::string","", "dara");
		config5.setDocDataName("Config5�̖��O");
		config5.setDocDescription("Config5�̊T�v1234567890123456789012345678901234567890123456789012345678901234567890");
		config5.setDocDefaultVal("Config5�̃f�t�H���g�l");
		config5.setDocUnit("Config5�̒P��1234567890123456789012345678901234567890123456789012345678901234567890");
		config5.setDocRange("Config5�͈̔�1234567890123456789012345678901234567890123456789012345678901234567890");
		config5.setDocConstraint("Config5�̐����1234567890123456789012345678901234567890123456789012345678901234567890");
		configset.add(config5);
		rtcParam.getConfigParams().addAll(configset);

		rtcParam.getServicePorts().addAll(srvports);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\Doc\\fullLong\\";

		assertEquals(15, result.size());
		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "Makefile.foo");
		//TODO 文字コード変更対応
//		checkCode(result, resourceDir, "foo.h");
//		checkCode(result, resourceDir, "foo.cpp");
//		checkCode(result, resourceDir, "MyServiceSVC_impl.h");
//		checkCode(result, resourceDir, "MyServiceSVC_impl.cpp");
		try {
			checkCode(result, resourceDir, "README.foo");
			fail();
		} catch(Exception ex) {
		}
	}

}
