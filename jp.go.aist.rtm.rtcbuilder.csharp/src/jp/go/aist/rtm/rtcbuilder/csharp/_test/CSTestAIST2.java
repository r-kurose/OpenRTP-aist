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

public class CSTestAIST2 extends TestBase {

	protected void setUp() throws Exception {
	}

	public void testAIST7() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsCSharp.LANG_CSHARP);
		rtcParam.setLanguageArg(IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("TA");
		rtcParam.setCategory("example");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(10);
		rtcParam.setIsTest(true);

		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("InP1", "TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "TimedLong", "", 0));
		rtcParam.getInports().addAll(dataport);

		List<DataPortParam> outport = new ArrayList<DataPortParam>(); 
		outport.add(new DataPortParam("OutP1", "TimedFloat", "", 0));
		outport.add(new DataPortParam("OutP2", "TimedDouble", "", 0));
		rtcParam.getOutports().addAll(outport);

		ServicePortParam service1 = new ServicePortParam("MySVPro0",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "acc", "", "", 
				rootPath + "resource\\AIST7\\Service1.idl", "test", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		GenerateManager manager = new CSharpGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\AIST7\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "foo.cs");
		checkCode(result, resourceDir, "foo.csproj");
		checkCode(result, resourceDir, "foo.csproj.user");
		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.cs");
		checkCode(result, resourceDir, "testImpl.cs");
		//
		checkCode(result, resourceDir, "\\Properties\\AssemblyInfo.cs");
		//
//		checkCode(result, resourceDir, "README.foo");
	}

	public void testAIST6() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsCSharp.LANG_CSHARP);
		rtcParam.setLanguageArg(IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG);
		rtcParam.setName("bar");
		rtcParam.setDescription("bartest");
		rtcParam.setVersion("2.0");
		rtcParam.setVender("Tec");
		rtcParam.setCategory("same");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("SPORADIC");
		rtcParam.setMaxInstance(10);
		rtcParam.setIsTest(true);

		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("InP1", "TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "TimedLong", "", 0));
		rtcParam.getInports().addAll(dataport);

		List<DataPortParam> outport = new ArrayList<DataPortParam>(); 
		outport.add(new DataPortParam("OutP1", "TimedDouble", "", 0));
		outport.add(new DataPortParam("OutP2", "TimedFloat", "", 0));
		rtcParam.getOutports().addAll(outport);

		ServicePortParam service1 = new ServicePortParam("MySVPro0",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "acc", "", "", 
				rootPath + "resource\\AIST6\\Service1.idl", "test", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);

		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		GenerateManager manager = new CSharpGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\AIST6\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "bar.cs");
		checkCode(result, resourceDir, "bar.csproj");
		checkCode(result, resourceDir, "bar.csproj.user");
		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.cs");
		checkCode(result, resourceDir, "testImpl.cs");
		//
		checkCode(result, resourceDir, "\\Properties\\AssemblyInfo.cs");
		//
//		checkCode(result, resourceDir, "README.bar");
	}

}
