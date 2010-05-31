package jp.go.aist.rtm.rtcbuilder.python._test._100;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import jp.go.aist.rtm.rtcbuilder.python.IRtcBuilderConstantsPython;
import jp.go.aist.rtm.rtcbuilder.python._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.python.manager.PythonGenerateManager;

public class AISTTest extends TestBase {

	protected void setUp() throws Exception {
	}

	public void testAIST6() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsPython.LANG_PYTHON);
		rtcParam.setLanguageArg(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		rtcParam.setName("test");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion(IRtcBuilderConstants.RTM_VERSION_100);

		ServicePortParam service1 = new ServicePortParam("MySVPro0",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "resource\\Python\\AIST6\\work\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		ServicePortParam service2 = new ServicePortParam("MySVPro1",0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service1, "myservice", "", "", 
				rootPath + "resource\\Python\\AIST6\\work\\MyServiceAIST.idl", "MyService2", "", 0);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);
		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);
		//
		genParam = new GeneratorParam();
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.getServicePorts().clear();
		genParam.getRtcParams().add(rtcParam);
		generator = new Generator();
		GenerateManager manager = new PythonGenerateManager();
		generator.addGenerateManager(manager);
		result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\Python\\100\\AIST6\\";

		assertEquals(2, result.size());
		checkCode(result, resourceDir, "test.py");
		try {
			checkCode(result, resourceDir, "README.test");
			fail();
		} catch(Exception ex) {
		}
	}

	public void testAIST4() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsPython.LANG_PYTHON);
		rtcParam.setLanguageArg(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		rtcParam.setName("test");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion(IRtcBuilderConstants.RTM_VERSION_100);

		ServicePortParam service1 = new ServicePortParam("MySVPro0",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "resource\\Python\\AIST4\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		ServicePortParam service2 = new ServicePortParam("MySVPro1",0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service1, "myservice", "", "", 
				rootPath + "resource\\Python\\AIST4\\MyService.idl", "MyService", "", 0);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);
		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		GenerateManager manager = new PythonGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\Python\\100\\AIST4\\";

		assertEquals(5, result.size());
		checkCode(result, resourceDir, "test.py");
		checkCode(result, resourceDir, "MyService_idl_example.py");
		try {
			checkCode(result, resourceDir, "README.test");
			fail();
		} catch(Exception ex) {
		}
		checkCode(result, resourceDir, "idlcompile.bat");
		checkCode(result, resourceDir, "idlcompile.sh");
//		checkCode(result, resourceDir, "MyService_idl.py");
//		checkCode(result, resourceDir, "\\_GlobalIDL\\__init__.py");
//		checkCode(result, resourceDir, "\\_GlobalIDL__POA\\__init__.py");
	}

	public void testAIST3() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsPython.LANG_PYTHON);
		rtcParam.setLanguageArg(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		rtcParam.setName("test");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("example");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion(IRtcBuilderConstants.RTM_VERSION_100);

		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("in", "RTC::TimedFloatSeq", "", 0));
		rtcParam.getInports().addAll(dataport);

		List<DataPortParam> dataoutport = new ArrayList<DataPortParam>(); 
		dataoutport.add(new DataPortParam("out", "RTC::TimedFloatSeq", "", 0));
		rtcParam.getOutports().addAll(dataoutport);

		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "resource\\Python\\AIST3\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		ServicePortParam service2 = new ServicePortParam("MySVCon",0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service1, "myservice1", "", "", 
				rootPath + "resource\\Python\\AIST3\\MyService.idl", "MyService", "", 1);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);
		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		GenerateManager manager = new PythonGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\Python\\100\\AIST3\\";

		assertEquals(5, result.size());
		checkCode(result, resourceDir, "test.py");
		checkCode(result, resourceDir, "MyService_idl_example.py");
		try {
			checkCode(result, resourceDir, "README.test");
			fail();
		} catch(Exception ex) {
		}
		checkCode(result, resourceDir, "idlcompile.bat");
		checkCode(result, resourceDir, "idlcompile.sh");
//		checkCode(result, resourceDir, "MyService_idl.py");
//		checkCode(result, resourceDir, "\\_GlobalIDL\\__init__.py");
//		checkCode(result, resourceDir, "\\_GlobalIDL__POA\\__init__.py");
	}
	
	public void testAIST2() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsPython.LANG_PYTHON);
		rtcParam.setLanguageArg(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		rtcParam.setName("test");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("example");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion(IRtcBuilderConstants.RTM_VERSION_100);

		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("in", "RTC::TimedFloatSeq", "", 0));
		rtcParam.getInports().addAll(dataport);

		List<DataPortParam> dataoutport = new ArrayList<DataPortParam>(); 
		dataoutport.add(new DataPortParam("out", "RTC::TimedFloatSeq", "", 0));
		rtcParam.getOutports().addAll(dataoutport);

		ServicePortParam service1 = new ServicePortParam("MySVCon",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice", "", "", 
				rootPath + "resource\\Python\\AIST2\\MyService.idl", "MyService", "", 1);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		GenerateManager manager = new PythonGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\Python\\100\\AIST2\\";

		assertEquals(4, result.size());
		checkCode(result, resourceDir, "test.py");
		try {
			checkCode(result, resourceDir, "README.test");
			fail();
		} catch(Exception ex) {
		}
		checkCode(result, resourceDir, "idlcompile.bat");
		checkCode(result, resourceDir, "idlcompile.sh");
//		checkCode(result, resourceDir, "MyService_idl.py");
//		checkCode(result, resourceDir, "\\_GlobalIDL\\__init__.py");
//		checkCode(result, resourceDir, "\\_GlobalIDL__POA\\__init__.py");
	}

	public void testType2() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsPython.LANG_PYTHON);
		rtcParam.setLanguageArg(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		rtcParam.setName("test");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("exmple");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion(IRtcBuilderConstants.RTM_VERSION_100);

		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice", "", "", 
				rootPath + "resource\\Python\\type2\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		GenerateManager manager = new PythonGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\Python\\100\\type2\\";

		assertEquals(5, result.size());
		checkCode(result, resourceDir, "test.py");
		checkCode(result, resourceDir, "MyService_idl_example.py");
		try {
			checkCode(result, resourceDir, "README.test");
			fail();
		} catch(Exception ex) {
		}
		checkCode(result, resourceDir, "idlcompile.bat");
		checkCode(result, resourceDir, "idlcompile.sh");
//		checkCode(result, resourceDir, "MyService_idl.py");
//		checkCode(result, resourceDir, "\\_GlobalIDL\\__init__.py");
//		checkCode(result, resourceDir, "\\_GlobalIDL__POA\\__init__.py");
	}

	public void testType() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsPython.LANG_PYTHON);
		rtcParam.setLanguageArg(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		rtcParam.setName("test");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("exmple");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion(IRtcBuilderConstants.RTM_VERSION_100);

		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice", "", "", 
				rootPath + "resource\\Python\\type\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		GenerateManager manager = new PythonGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\Python\\100\\type\\";

		assertEquals(5, result.size());
		checkCode(result, resourceDir, "test.py");
		checkCode(result, resourceDir, "MyService_idl_example.py");
		try {
			checkCode(result, resourceDir, "README.test");
			fail();
		} catch(Exception ex) {
		}
		checkCode(result, resourceDir, "idlcompile.bat");
		checkCode(result, resourceDir, "idlcompile.sh");
//		checkCode(result, resourceDir, "MyService_idl.py");
//		checkCode(result, resourceDir, "\\_GlobalIDL\\__init__.py");
//		checkCode(result, resourceDir, "\\_GlobalIDL__POA\\__init__.py");
	}

	public void testServicePort() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsPython.LANG_PYTHON);
		rtcParam.setLanguageArg(IRtcBuilderConstantsPython.LANG_PYTHON_ARG);
		rtcParam.setName("test");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("exmple");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion(IRtcBuilderConstants.RTM_VERSION_100);

		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice", "", "", 
				rootPath + "resource\\Python\\AIST1\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		GenerateManager manager = new PythonGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\Python\\100\\AIST1\\";

		assertEquals(5, result.size());
		checkCode(result, resourceDir, "test.py");
		checkCode(result, resourceDir, "MyService_idl_example.py");
		try {
			checkCode(result, resourceDir, "README.test");
			fail();
		} catch(Exception ex) {
		}
		checkCode(result, resourceDir, "idlcompile.bat");
		checkCode(result, resourceDir, "idlcompile.sh");
//		checkCode(result, resourceDir, "MyService_idl.py");
//		checkCode(result, resourceDir, "\\_GlobalIDL\\__init__.py");
//		checkCode(result, resourceDir, "\\_GlobalIDL__POA\\__init__.py");
	}
}
