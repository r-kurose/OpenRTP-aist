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

public class CSBaseTest extends TestBase {

	protected void setUp() throws Exception {
	}

	public void testServicePort2() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsCSharp.LANG_CSHARP);
		rtcParam.setLanguageArg(IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("DataFlowComponent");
		rtcParam.setIsTest(true);
		rtcParam.setMaxInstance(5);
		genParam.getRtcParams().add(rtcParam);
		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("InP1", "TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "TimedLong", "", 0));
		rtcParam.getInports().addAll(dataport);
		List<DataPortParam> outport = new ArrayList<DataPortParam>(); 
		outport.add(new DataPortParam("OutP1", "TimedFloat", "", 0));
		outport.add(new DataPortParam("OutP2", "TimedDouble", "", 0));
		rtcParam.getOutports().addAll(outport);

		ServicePortParam service1 = new ServicePortParam("svPort",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "acc", "", "", 
				rootPath + "\\resource\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		
		ServicePortParam service2 = new ServicePortParam("cmPort",0);
		List<ServicePortInterfaceParam> srvinterts2 = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int2 = new ServicePortInterfaceParam(service2, "rate", "", "", 
				rootPath + "\\resource\\DAQService.idl", "DAQService", "", 1);
		srvinterts2.add(int2);
		service2.getServicePortInterfaces().addAll(srvinterts2);
		srvports.add(service2);
		
		rtcParam.getServicePorts().addAll(srvports);

		Generator generator = new Generator();
		GenerateManager manager = new CSharpGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\service2\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "foo.cs");
		checkCode(result, resourceDir, "foo.csproj");
		checkCode(result, resourceDir, "foo.csproj.user");
		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.cs");
		checkCode(result, resourceDir, "MyServiceImpl.cs");
		//
		checkCode(result, resourceDir, "\\Properties\\AssemblyInfo.cs");
		//
//		checkCode(result, resourceDir, "README.foo");
	}

	public void testServicePort1() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsCSharp.LANG_CSHARP);
		rtcParam.setLanguageArg(IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(5);
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);
		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("InP1", "TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "TimedLong", "", 0));
		rtcParam.getInports().addAll(dataport);
		List<DataPortParam> outport = new ArrayList<DataPortParam>(); 
		outport.add(new DataPortParam("OutP1", "TimedFloat", "", 0));
		outport.add(new DataPortParam("OutP2", "TimedDouble", "", 0));
		rtcParam.getOutports().addAll(outport);

		ServicePortParam service1 = new ServicePortParam("svPort",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "acc", "", "", 
				rootPath + "\\resource\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);
		
		Generator generator = new Generator();
		GenerateManager manager = new CSharpGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\service1\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "foo.cs");
		checkCode(result, resourceDir, "foo.csproj");
		checkCode(result, resourceDir, "foo.csproj.user");
		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.cs");
		checkCode(result, resourceDir, "MyServiceImpl.cs");
		//
		checkCode(result, resourceDir, "\\Properties\\AssemblyInfo.cs");
		//
//		checkCode(result, resourceDir, "README.foo");
	}

	public void testOutPort2() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsCSharp.LANG_CSHARP);
		rtcParam.setLanguageArg(IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("DataFlowComponent");
		rtcParam.setMaxInstance(5);
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);
		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("InP1", "TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "TimedLong", "", 0));
		rtcParam.getInports().addAll(dataport);
		List<DataPortParam> outport = new ArrayList<DataPortParam>(); 
		outport.add(new DataPortParam("OutP1", "TimedFloat", "", 0));
		outport.add(new DataPortParam("OutP2", "TimedDouble", "", 0));
		rtcParam.getOutports().addAll(outport);
		
		Generator generator = new Generator();
		GenerateManager manager = new CSharpGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\outport2\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "foo.cs");
		checkCode(result, resourceDir, "foo.csproj");
		checkCode(result, resourceDir, "foo.csproj.user");
		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.cs");
		//
		checkCode(result, resourceDir, "\\Properties\\AssemblyInfo.cs");
		//
//		checkCode(result, resourceDir, "README.foo");
	}

	public void testOutPort1() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsCSharp.LANG_CSHARP);
		rtcParam.setLanguageArg(IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(5);
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);
		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("InP1", "TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "TimedLong", "", 0));
		rtcParam.getInports().addAll(dataport);
		List<DataPortParam> outport = new ArrayList<DataPortParam>(); 
		outport.add(new DataPortParam("OutP1", "TimedFloat", "", 0));
		rtcParam.getOutports().addAll(outport);
		
		Generator generator = new Generator();
		GenerateManager manager = new CSharpGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\outport1\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "foo.cs");
		checkCode(result, resourceDir, "foo.csproj");
		checkCode(result, resourceDir, "foo.csproj.user");
		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.cs");
		//
		checkCode(result, resourceDir, "\\Properties\\AssemblyInfo.cs");
		//
//		checkCode(result, resourceDir, "README.foo");
	}

	public void testInPort2() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsCSharp.LANG_CSHARP);
		rtcParam.setLanguageArg(IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(5);
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);
		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("InP1", "TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "TimedLong", "", 0));
		rtcParam.getInports().addAll(dataport);
		
		Generator generator = new Generator();
		GenerateManager manager = new CSharpGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\inport2\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "foo.cs");
		checkCode(result, resourceDir, "foo.csproj");
		checkCode(result, resourceDir, "foo.csproj.user");
		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.cs");
		//
		checkCode(result, resourceDir, "\\Properties\\AssemblyInfo.cs");
		//
//		checkCode(result, resourceDir, "README.foo");
	}

	public void testInPort() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsCSharp.LANG_CSHARP);
		rtcParam.setLanguageArg(IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(5);
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);
		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("InP1", "TimedShort", "", 0));
		rtcParam.getInports().addAll(dataport);
		
		Generator generator = new Generator();
		GenerateManager manager = new CSharpGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\inport1\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "foo.cs");
		checkCode(result, resourceDir, "foo.csproj");
		checkCode(result, resourceDir, "foo.csproj.user");
		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.cs");
		//
		checkCode(result, resourceDir, "\\Properties\\AssemblyInfo.cs");
		//
//		checkCode(result, resourceDir, "README.foo");
	}

	public void testBasic() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsCSharp.LANG_CSHARP);
		rtcParam.setLanguageArg(IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("SPORADIC");
		rtcParam.setMaxInstance(5);
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);
		
		Generator generator = new Generator();
		GenerateManager manager = new CSharpGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\name\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "foo.cs");
		checkCode(result, resourceDir, "foo.csproj");
		checkCode(result, resourceDir, "foo.csproj.user");
		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.cs");
		//
		checkCode(result, resourceDir, "\\Properties\\AssemblyInfo.cs");
		//
//		checkCode(result, resourceDir, "README.foo");
	}
}
