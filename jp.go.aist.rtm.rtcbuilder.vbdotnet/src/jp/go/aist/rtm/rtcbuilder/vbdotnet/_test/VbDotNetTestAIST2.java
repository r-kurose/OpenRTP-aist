package jp.go.aist.rtm.rtcbuilder.vbdotnet._test;

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
import jp.go.aist.rtm.rtcbuilder.vbdotnet.IRtcBuilderConstantsVbDotNet;
import jp.go.aist.rtm.rtcbuilder.vbdotnet.manager.VbDotNetGenerateManager;

public class VbDotNetTestAIST2 extends TestBase {

	protected void setUp() throws Exception {
	}

	public void testAIST7() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsVbDotNet.LANG_VBDOTNET);
		rtcParam.setLanguageArg(IRtcBuilderConstantsVbDotNet.LANG_VBDOTNET_ARG);
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
		GenerateManager manager = new VbDotNetGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\AIST7\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "foo.vb");
		checkCode(result, resourceDir, "foo.vbproj");
		checkCode(result, resourceDir, "foo.vbproj.user");
//		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.vb");
		checkCode(result, resourceDir, "testImpl.vb");
		//
		checkCode(result, resourceDir, "\\My Project\\AssemblyInfo.vb");
		//
		checkCode(result, resourceDir, "README.foo");
	}

	public void testAIST6() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsVbDotNet.LANG_VBDOTNET);
		rtcParam.setLanguageArg(IRtcBuilderConstantsVbDotNet.LANG_VBDOTNET_ARG);
		rtcParam.setName("bar");
		rtcParam.setDescription("bartest");
		rtcParam.setVersion("2.0");
		rtcParam.setVender("Tec");
		rtcParam.setCategory("same");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
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
		GenerateManager manager = new VbDotNetGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\AIST6\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "bar.vb");
		checkCode(result, resourceDir, "bar.vbproj");
		checkCode(result, resourceDir, "bar.vbproj.user");
//		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.vb");
		checkCode(result, resourceDir, "testImpl.vb");
		//
		checkCode(result, resourceDir, "\\My Project\\AssemblyInfo.vb");
		//
		checkCode(result, resourceDir, "README.bar");
	}

}
