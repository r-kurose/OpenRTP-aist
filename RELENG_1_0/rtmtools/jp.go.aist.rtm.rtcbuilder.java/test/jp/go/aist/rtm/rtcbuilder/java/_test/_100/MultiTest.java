package jp.go.aist.rtm.rtcbuilder.java._test._100;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.java.IRtcBuilderConstantsJava;
import jp.go.aist.rtm.rtcbuilder.java._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.java.manager.JavaGenerateManager;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;

public class MultiTest extends TestBase {

	protected void setUp() throws Exception {
	}

	public void testProConMulti() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsJava.LANG_JAVA);
		rtcParam.setLanguageArg(IRtcBuilderConstantsJava.LANG_JAVA_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion("1.0.0");
		
		List<DataPortParam> dataInport = new ArrayList<DataPortParam>(); 
		dataInport.add(new DataPortParam("in1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataInport);

		List<DataPortParam> dataOutport = new ArrayList<DataPortParam>(); 
		dataOutport.add(new DataPortParam("out1", "RTC::TimedLong", "", 0));
		rtcParam.getOutports().addAll(dataOutport);

		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myserviceP1", "", "", 
				rootPath + "resource\\100\\Multi\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		ServicePortParam service2 = new ServicePortParam("MySVPro2",0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service1, "myserviceP2", "", "", 
				rootPath + "resource\\100\\Multi\\MyService2.idl", "MyService2", "", 0);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);

		ServicePortParam service3 = new ServicePortParam("MyConPro",0);
		List<ServicePortInterfaceParam> srvintert3 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int3 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "resource\\100\\Multi\\MyService.idl", "MyService", "", 1);
		srvintert3.add(int3);
		service3.getServicePortInterfaces().addAll(srvintert3);
		srvports.add(service3);

		ServicePortParam service4 = new ServicePortParam("MyConPro2",0);
		List<ServicePortInterfaceParam> srvinterts4 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int4 = new ServicePortInterfaceParam(service1, "myservice2", "", "", 
				rootPath + "resource\\100\\Multi\\DAQService.idl", "DAQService", "", 1);
		srvinterts4.add(int4);
		service4.getServicePortInterfaces().addAll(srvinterts4);
		srvports.add(service4);
		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		GenerateManager manager = new JavaGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\Multi\\ProConMulti\\";

		assertEquals(8, result.size());
		checkCode(result, resourceDir, "\\src\\fooComp.java");
		checkCode(result, resourceDir, "build_foo.xml");
		checkCode(result, resourceDir, "\\src\\foo.java");
		checkCode(result, resourceDir, "\\src\\fooImpl.java");
		checkCode(result, resourceDir, "\\src\\MyServiceSVC_impl.java");
		checkCode(result, resourceDir, "\\src\\MyService2SVC_impl.java");
	}

	public void testConsumerMulti() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsJava.LANG_JAVA);
		rtcParam.setLanguageArg(IRtcBuilderConstantsJava.LANG_JAVA_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion("1.0.0");
		
		List<DataPortParam> dataInport = new ArrayList<DataPortParam>(); 
		dataInport.add(new DataPortParam("in1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataInport);

		List<DataPortParam> dataOutport = new ArrayList<DataPortParam>(); 
		dataOutport.add(new DataPortParam("out1", "RTC::TimedLong", "", 0));
		rtcParam.getOutports().addAll(dataOutport);

		ServicePortParam service1 = new ServicePortParam("MyConPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "resource\\100\\Multi\\MyService.idl", "MyService", "", 1);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		ServicePortParam service2 = new ServicePortParam("MyConPro2",0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service1, "myservice2", "", "", 
				rootPath + "resource\\100\\Multi\\DAQService.idl", "DAQService", "", 1);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);
		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);
		
		Generator generator = new Generator();
		GenerateManager manager = new JavaGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\Multi\\ConMulti\\";

		assertEquals(6, result.size());
		checkCode(result, resourceDir, "\\src\\fooComp.java");
		checkCode(result, resourceDir, "build_foo.xml");
		checkCode(result, resourceDir, "\\src\\foo.java");
		checkCode(result, resourceDir, "\\src\\fooImpl.java");
	}

	public void testProviderMulti() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsJava.LANG_JAVA);
		rtcParam.setLanguageArg(IRtcBuilderConstantsJava.LANG_JAVA_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion("1.0.0");
		
		List<DataPortParam> dataInport = new ArrayList<DataPortParam>(); 
		dataInport.add(new DataPortParam("in1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataInport);

		List<DataPortParam> dataOutport = new ArrayList<DataPortParam>(); 
		dataOutport.add(new DataPortParam("out1", "RTC::TimedLong", "", 0));
		rtcParam.getOutports().addAll(dataOutport);

		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "resource\\100\\Multi\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		ServicePortParam service2 = new ServicePortParam("MySVPro2",0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service1, "myservice2", "", "", 
				rootPath + "resource\\100\\Multi\\DAQService.idl", "DAQService", "", 0);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);
		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);
		
		Generator generator = new Generator();
		GenerateManager manager = new JavaGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\Multi\\ProMulti\\";

		assertEquals(8, result.size());
		checkCode(result, resourceDir, "\\src\\fooComp.java");
		checkCode(result, resourceDir, "build_foo.xml");
		checkCode(result, resourceDir, "\\src\\foo.java");
		checkCode(result, resourceDir, "\\src\\fooImpl.java");
		checkCode(result, resourceDir, "\\src\\MyServiceSVC_impl.java");
		checkCode(result, resourceDir, "\\src\\DAQServiceSVC_impl.java");
	}
}
