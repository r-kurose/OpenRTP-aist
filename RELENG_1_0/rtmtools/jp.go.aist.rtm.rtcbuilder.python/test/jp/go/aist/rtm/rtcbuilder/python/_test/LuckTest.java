package jp.go.aist.rtm.rtcbuilder.python._test;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import jp.go.aist.rtm.rtcbuilder.python.IRtcBuilderConstantsPython;
import jp.go.aist.rtm.rtcbuilder.python.manager.PythonGenerateManager;

public class LuckTest extends TestBase {

	protected void setUp() throws Exception {
	}

	public void testConsumerNoType() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsPython.LANG_PYTHON);
		rtcParam.setLanguageArg(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);
		genParam.getRtcParams().add(rtcParam);
		
		List<DataPortParam> dataInport = new ArrayList<DataPortParam>(); 
		dataInport.add(new DataPortParam("in1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataInport);

		List<DataPortParam> dataOutport = new ArrayList<DataPortParam>(); 
		dataOutport.add(new DataPortParam("out1", "RTC::TimedLong", "", 0));
		rtcParam.getOutports().addAll(dataOutport);

		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "\\resource\\Python\\Exception\\ConNoName\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		
		ServicePortParam service2 = new ServicePortParam("MyConPro",0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service1, "myservice1", "", "", 
				rootPath + "\\resource\\Python\\Exception\\ConNoName\\MyService.idl", "", "", 1);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);

		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		GenerateManager manager = new PythonGenerateManager();
		generator.addGenerateManager(manager);
		try {
			generator.generateTemplateCode(genParam);
			fail();
		} catch ( Exception ex ) {
		}
	}

	public void testConsumerNoName() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsPython.LANG_PYTHON);
		rtcParam.setLanguageArg(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);
		
		List<DataPortParam> dataInport = new ArrayList<DataPortParam>(); 
		dataInport.add(new DataPortParam("in1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataInport);

		List<DataPortParam> dataOutport = new ArrayList<DataPortParam>(); 
		dataOutport.add(new DataPortParam("out1", "RTC::TimedLong", "", 0));
		rtcParam.getOutports().addAll(dataOutport);

		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "\\resource\\Python\\Exception\\ConNoName\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		
		ServicePortParam service2 = new ServicePortParam("MyConPro",0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service1, "", "", "", 
				rootPath + "\\resource\\Python\\Exception\\ConNoName\\MyService.idl", "MyService", "", 1);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);

		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		GenerateManager manager = new PythonGenerateManager();
		generator.addGenerateManager(manager);
		try {
			generator.generateTemplateCode(genParam);
			fail();
		} catch ( Exception ex ) {
		}
	}

	public void testConsumerNoPortName() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsPython.LANG_PYTHON);
		rtcParam.setLanguageArg(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);
		
		List<DataPortParam> dataInport = new ArrayList<DataPortParam>(); 
		dataInport.add(new DataPortParam("in1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataInport);

		List<DataPortParam> dataOutport = new ArrayList<DataPortParam>(); 
		dataOutport.add(new DataPortParam("out1", "RTC::TimedLong", "", 0));
		rtcParam.getOutports().addAll(dataOutport);

		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "\\resource\\Python\\Exception\\ConNoPortName\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		
		ServicePortParam service2 = new ServicePortParam("",0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service1, "myservice1", "", "", 
				rootPath + "\\resource\\Python\\Exception\\ConNoPortName\\MyService.idl", "MyService", "", 1);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);

		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		GenerateManager manager = new PythonGenerateManager();
		generator.addGenerateManager(manager);
		try {
			generator.generateTemplateCode(genParam);
			fail();
		} catch ( Exception ex ) {
		}
	}

	public void testProviderNoType() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsPython.LANG_PYTHON);
		rtcParam.setLanguageArg(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);
		genParam.getRtcParams().add(rtcParam);
		
		List<DataPortParam> dataInport = new ArrayList<DataPortParam>(); 
		dataInport.add(new DataPortParam("in1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataInport);

		List<DataPortParam> dataOutport = new ArrayList<DataPortParam>(); 
		dataOutport.add(new DataPortParam("out1", "RTC::TimedLong", "", 0));
		rtcParam.getOutports().addAll(dataOutport);

		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "\\resource\\Python\\Exception\\ProNoType\\MyService.idl", "", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);
		
		Generator generator = new Generator();
		GenerateManager manager = new PythonGenerateManager();
		generator.addGenerateManager(manager);
		try {
			generator.generateTemplateCode(genParam);
			fail();
		} catch ( Exception ex ) {
		}

	}

	public void testProviderNoName() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsPython.LANG_PYTHON);
		rtcParam.setLanguageArg(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);
		
		List<DataPortParam> dataInport = new ArrayList<DataPortParam>(); 
		dataInport.add(new DataPortParam("in1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataInport);

		List<DataPortParam> dataOutport = new ArrayList<DataPortParam>(); 
		dataOutport.add(new DataPortParam("out1", "RTC::TimedLong", "", 0));
		rtcParam.getOutports().addAll(dataOutport);

		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "", "", "", 
				rootPath + "\\resource\\Python\\Exception\\ProNoName\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);
		
		Generator generator = new Generator();
		GenerateManager manager = new PythonGenerateManager();
		generator.addGenerateManager(manager);
		try {
			generator.generateTemplateCode(genParam);
			fail();
		} catch ( Exception ex ) {
		}
	}

	public void testProviderNoPortName() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsPython.LANG_PYTHON);
		rtcParam.setLanguageArg(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);
		
		List<DataPortParam> dataInport = new ArrayList<DataPortParam>(); 
		dataInport.add(new DataPortParam("in1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataInport);

		List<DataPortParam> dataOutport = new ArrayList<DataPortParam>(); 
		dataOutport.add(new DataPortParam("out1", "RTC::TimedLong", "", 0));
		rtcParam.getOutports().addAll(dataOutport);

		ServicePortParam service1 = new ServicePortParam("",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "\\resource\\Python\\Exception\\ProNoPortName\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);
		
		Generator generator = new Generator();
		GenerateManager manager = new PythonGenerateManager();
		generator.addGenerateManager(manager);
		try {
			generator.generateTemplateCode(genParam);
			fail();
		} catch ( Exception ex ) {
		}
	}

	public void testOutPortNoType() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsPython.LANG_PYTHON);
		rtcParam.setLanguageArg(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);
		genParam.getRtcParams().add(rtcParam);
		
		List<DataPortParam> dataInport = new ArrayList<DataPortParam>(); 
		dataInport.add(new DataPortParam("in1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataInport);

		List<DataPortParam> dataOutport = new ArrayList<DataPortParam>(); 
		dataOutport.add(new DataPortParam("out1", "", "", 0));
		rtcParam.getOutports().addAll(dataOutport);
		
		Generator generator = new Generator();
		GenerateManager manager = new PythonGenerateManager();
		generator.addGenerateManager(manager);
		try {
			generator.generateTemplateCode(genParam);
			fail();
		} catch ( Exception ex ) {
		}
	}

	public void testOutPortNoName() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsPython.LANG_PYTHON);
		rtcParam.setLanguageArg(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);
		genParam.getRtcParams().add(rtcParam);
		
		List<DataPortParam> dataInport = new ArrayList<DataPortParam>(); 
		dataInport.add(new DataPortParam("in1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataInport);

		List<DataPortParam> dataOutport = new ArrayList<DataPortParam>(); 
		dataOutport.add(new DataPortParam("", "RTC::TimedLong", "", 0));
		rtcParam.getOutports().addAll(dataOutport);
		
		Generator generator = new Generator();
		GenerateManager manager = new PythonGenerateManager();
		generator.addGenerateManager(manager);
		try {
			generator.generateTemplateCode(genParam);
			fail();
		} catch ( Exception ex ) {
		}
	}

	public void testInPortNoType() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsPython.LANG_PYTHON);
		rtcParam.setLanguageArg(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);
		genParam.getRtcParams().add(rtcParam);
		
		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("in1", "", "", 0));
		rtcParam.getInports().addAll(dataport);
		
		Generator generator = new Generator();
		GenerateManager manager = new PythonGenerateManager();
		generator.addGenerateManager(manager);
		try {
			generator.generateTemplateCode(genParam);
			fail();
		} catch ( Exception ex ) {
		}
	}

	public void testInPortNoName() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsPython.LANG_PYTHON);
		rtcParam.setLanguageArg(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);
		genParam.getRtcParams().add(rtcParam);
		
		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataport);
		
		Generator generator = new Generator();
		GenerateManager manager = new PythonGenerateManager();
		generator.addGenerateManager(manager);
		try {
			generator.generateTemplateCode(genParam);
			fail();
		} catch ( Exception ex ) {
		}
	}

}
