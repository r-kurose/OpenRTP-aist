package jp.go.aist.rtm.rtcbuilder.csharp._test;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.csharp.IRtcBuilderConstantsCSharp;
import jp.go.aist.rtm.rtcbuilder.csharp.manager.CSharpGenerateManager;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;

public class CSTestAIST extends TestBase {

	protected void setUp() throws Exception {
	}

	public void testAIST5() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsCSharp.LANG_CSHARP);
		rtcParam.setLanguageArg(IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG);
		rtcParam.setName("Test");
		rtcParam.setDescription("Test Component.");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setIsTest(true);

		ServicePortParam service1 = new ServicePortParam("MySVPro0",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "resource\\AIST5\\MyService.idl", "MyServiceT", "", 0);
		srvinterts.add(int1);
		service1.setServicePortInterfaces(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		ServicePortParam service2 = new ServicePortParam("MySVPro1",0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service1, "myservice", "", "", 
				rootPath + "resource\\AIST5\\MyService.idl", "MyServiceOpen", "", 0);
		srvinterts2.add(int2);
		service2.setServicePortInterfaces(srvinterts2);
		srvports.add(service2);
		rtcParam.setServicePorts(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		GenerateManager manager = new CSharpGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\AIST5\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "Test.cs");
		checkCode(result, resourceDir, "Test.csproj");
		checkCode(result, resourceDir, "Test.csproj.user");
		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.cs");
		checkCode(result, resourceDir, "MyServiceTImpl.cs");
		checkCode(result, resourceDir, "MyServiceOpenImpl.cs");
		//
		checkCode(result, resourceDir, "\\Properties\\AssemblyInfo.cs");
		//
		checkCode(result, resourceDir, "README.Test");
	}

	public void testAIST4() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsCSharp.LANG_CSHARP);
		rtcParam.setLanguageArg(IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG);
		rtcParam.setName("Test");
		rtcParam.setDescription("Test Component.");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setIsTest(true);

		ServicePortParam service1 = new ServicePortParam("MySVPro0",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "resource\\AIST4\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.setServicePortInterfaces(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		ServicePortParam service2 = new ServicePortParam("MySVPro1",0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service1, "myservice", "", "", 
				rootPath + "resource\\AIST4\\MyService.idl", "MyService", "", 0);
		srvinterts2.add(int2);
		service2.setServicePortInterfaces(srvinterts2);
		srvports.add(service2);
		rtcParam.setServicePorts(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		GenerateManager manager = new CSharpGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\AIST4\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "Test.cs");
		checkCode(result, resourceDir, "Test.csproj");
		checkCode(result, resourceDir, "Test.csproj.user");
		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.cs");
		checkCode(result, resourceDir, "MyServiceImpl.cs");
		//
		checkCode(result, resourceDir, "\\Properties\\AssemblyInfo.cs");
		//
		checkCode(result, resourceDir, "README.Test");
	}

	public void testAIST3() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsCSharp.LANG_CSHARP);
		rtcParam.setLanguageArg(IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG);
		rtcParam.setName("Test");
		rtcParam.setDescription("Test Component.");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("example");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setIsTest(true);

		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("in", "TimedFloatSeq", "", 0));
		rtcParam.setInports(dataport);

		List<DataPortParam> dataoutport = new ArrayList<DataPortParam>(); 
		dataoutport.add(new DataPortParam("out", "TimedFloatSeq", "", 0));
		rtcParam.setOutports(dataoutport);

		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "resource\\AIST3\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.setServicePortInterfaces(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		ServicePortParam service2 = new ServicePortParam("MySVCon",0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service1, "myservice1", "", "", 
				rootPath + "resource\\AIST3\\MyService.idl", "MyService", "", 1);
		srvinterts2.add(int2);
		service2.setServicePortInterfaces(srvinterts2);
		srvports.add(service2);
		rtcParam.setServicePorts(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		GenerateManager manager = new CSharpGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\AIST3\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "Test.cs");
		checkCode(result, resourceDir, "Test.csproj");
		checkCode(result, resourceDir, "Test.csproj.user");
		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.cs");
		checkCode(result, resourceDir, "MyServiceImpl.cs");
		//
		checkCode(result, resourceDir, "\\Properties\\AssemblyInfo.cs");
		//
		checkCode(result, resourceDir, "README.Test");
	}
	
	public void testAIST2() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsCSharp.LANG_CSHARP);
		rtcParam.setLanguageArg(IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG);
		rtcParam.setName("Test");
		rtcParam.setDescription("Test Component.");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("exmple");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("DataFlowComponent");
		rtcParam.setMaxInstance(10);
		rtcParam.setIsTest(true);

		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("in", "TimedFloatSeq", "", 0));
		rtcParam.setInports(dataport);

		List<DataPortParam> dataoutport = new ArrayList<DataPortParam>(); 
		dataoutport.add(new DataPortParam("out", "TimedFloatSeq", "", 0));
		rtcParam.setOutports(dataoutport);

		ServicePortParam service1 = new ServicePortParam("MySVCon",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice", "", "", 
				rootPath + "resource\\AIST2\\MyService.idl", "MyService", "", 1);
		srvinterts.add(int1);
		service1.setServicePortInterfaces(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.setServicePorts(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		GenerateManager manager = new CSharpGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\AIST2\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "Test.cs");
		checkCode(result, resourceDir, "Test.csproj");
		checkCode(result, resourceDir, "Test.csproj.user");
		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.cs");
//		checkCode(result, resourceDir, "MyServiceImpl.cs");
		//
		checkCode(result, resourceDir, "\\Properties\\AssemblyInfo.cs");
		//
		checkCode(result, resourceDir, "README.Test");
	}

	public void testType() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsCSharp.LANG_CSHARP);
		rtcParam.setLanguageArg(IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG);
		rtcParam.setName("Test");
		rtcParam.setDescription("Test Component.");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("exmple");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setIsTest(true);

		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "meservice", "", "", 
				rootPath + "resource\\MyServiceType.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.setServicePortInterfaces(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.setServicePorts(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		GenerateManager manager = new CSharpGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\type\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "Test.cs");
		checkCode(result, resourceDir, "Test.csproj");
		checkCode(result, resourceDir, "Test.csproj.user");
		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.cs");
		checkCode(result, resourceDir, "MyServiceImpl.cs");
		//
		checkCode(result, resourceDir, "\\Properties\\AssemblyInfo.cs");
		//
		checkCode(result, resourceDir, "README.Test");
	}

	public void testServicePort() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsCSharp.LANG_CSHARP);
		rtcParam.setLanguageArg(IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG);
		rtcParam.setName("Test");
		rtcParam.setDescription("Test Component.");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("exmple");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setIsTest(true);

		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice", "", "", 
				rootPath + "resource\\AIST1\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.setServicePortInterfaces(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.setServicePorts(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		GenerateManager manager = new CSharpGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\AIST1\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "Test.cs");
		checkCode(result, resourceDir, "Test.csproj");
		checkCode(result, resourceDir, "Test.csproj.user");
		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.cs");
		checkCode(result, resourceDir, "MyServiceImpl.cs");
		//
		checkCode(result, resourceDir, "\\Properties\\AssemblyInfo.cs");
		//
		checkCode(result, resourceDir, "README.Test");
	}
}
