package jp.go.aist.rtm.rtcbuilder.vbdotnet._test;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigSetParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import jp.go.aist.rtm.rtcbuilder.vbdotnet.IRtcBuilderConstantsVbDotNet;
import jp.go.aist.rtm.rtcbuilder.vbdotnet.manager.VbDotNetGenerateManager;

public class VbDotNetConfigSetTest extends TestBase {

	protected void setUp() throws Exception {
	}

	public void testConfigSet() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsVbDotNet.LANG_VBDOTNET);
		rtcParam.setLanguageArg(IRtcBuilderConstantsVbDotNet.LANG_VBDOTNET_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.3");
		rtcParam.setVender("TA2");
		rtcParam.setCategory("manip2");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("DataFlowComponent");
		rtcParam.setMaxInstance(3);
		rtcParam.setIsTest(true);

		genParam.getRtcParams().add(rtcParam);
		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>(); 
		configset.add(new ConfigSetParam("int_param0","int","", "0"));
		rtcParam.setConfigParams(configset);

		Generator generator = new Generator();
		GenerateManager manager = new VbDotNetGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\ConfigSet\\configset1\\";
		
		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "foo.vb");
		checkCode(result, resourceDir, "foo.vbproj");
		checkCode(result, resourceDir, "foo.vbproj.user");
//		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.vb");
		//
		checkCode(result, resourceDir, "\\My Project\\AssemblyInfo.vb");
		//
//		checkCode(result, resourceDir, "README.foo");
	}

	public void testConfigSet2() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsVbDotNet.LANG_VBDOTNET);
		rtcParam.setLanguageArg(IRtcBuilderConstantsVbDotNet.LANG_VBDOTNET_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.3");
		rtcParam.setVender("TA2");
		rtcParam.setCategory("manip2");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("DataFlowComponent");
		rtcParam.setMaxInstance(3);
		rtcParam.setIsTest(true);
		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("InP1", "TimedShort", "", 0));
		rtcParam.setInports(dataport);

		genParam.getRtcParams().add(rtcParam);
		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>(); 
		configset.add(new ConfigSetParam("int_param0","int","", "0"));
		configset.add(new ConfigSetParam("int_param1","int","", "1"));
		rtcParam.setConfigParams(configset);

		ServicePortParam service1 = new ServicePortParam("svPort",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "acc", "", "", 
				rootPath + "\\resource\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.setServicePortInterfaces(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.setServicePorts(srvports);

		Generator generator = new Generator();
		GenerateManager manager = new VbDotNetGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\ConfigSet\\configset2\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "foo.vb");
		checkCode(result, resourceDir, "foo.vbproj");
		checkCode(result, resourceDir, "foo.vbproj.user");
//		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.vb");
		checkCode(result, resourceDir, "MyServiceImpl.vb");
		//
		checkCode(result, resourceDir, "\\My Project\\AssemblyInfo.vb");
		//
//		checkCode(result, resourceDir, "README.foo");
	}

	public void testConfigSet3() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsVbDotNet.LANG_VBDOTNET);
		rtcParam.setLanguageArg(IRtcBuilderConstantsVbDotNet.LANG_VBDOTNET_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.3");
		rtcParam.setVender("TA2");
		rtcParam.setCategory("manip2");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("DataFlowComponent");
		rtcParam.setMaxInstance(3);
		rtcParam.setIsTest(true);

		genParam.getRtcParams().add(rtcParam);
		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>(); 
		configset.add(new ConfigSetParam("int_param0","int","", "0"));
		configset.add(new ConfigSetParam("int_param1","int","", "1"));
		configset.add(new ConfigSetParam("double_param0","double","", "0.11"));
		configset.add(new ConfigSetParam("str_param0","string","", "hoge"));
		configset.add(new ConfigSetParam("str_param1","string","", "dara"));
		rtcParam.setConfigParams(configset);

		Generator generator = new Generator();
		GenerateManager manager = new VbDotNetGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\ConfigSet\\configset3\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "foo.vb");
		checkCode(result, resourceDir, "foo.vbproj");
		checkCode(result, resourceDir, "foo.vbproj.user");
//		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.vb");
		//
		checkCode(result, resourceDir, "\\My Project\\AssemblyInfo.vb");
		//
//		checkCode(result, resourceDir, "README.foo");
	}
	public void testConfigSet4() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsVbDotNet.LANG_VBDOTNET);
		rtcParam.setLanguageArg(IRtcBuilderConstantsVbDotNet.LANG_VBDOTNET_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.3");
		rtcParam.setVender("TA2");
		rtcParam.setCategory("manip2");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("DataFlowComponent");
		rtcParam.setMaxInstance(3);
		rtcParam.setIsTest(true);

		genParam.getRtcParams().add(rtcParam);
		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>(); 
		configset.add(new ConfigSetParam("int_param0","int","", "0"));
		configset.add(new ConfigSetParam("float_param0","List<float>","", "1.0,2.0,3.0"));
		rtcParam.setConfigParams(configset);

		Generator generator = new Generator();
		GenerateManager manager = new VbDotNetGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\ConfigSet\\configset4\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "foo.vb");
		checkCode(result, resourceDir, "foo.vbproj");
		checkCode(result, resourceDir, "foo.vbproj.user");
//		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.vb");
		//
		checkCode(result, resourceDir, "\\My Project\\AssemblyInfo.vb");
		//
//		checkCode(result, resourceDir, "README.foo");
	}
	public void testConfigSet5() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsVbDotNet.LANG_VBDOTNET);
		rtcParam.setLanguageArg(IRtcBuilderConstantsVbDotNet.LANG_VBDOTNET_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.3");
		rtcParam.setVender("TA2");
		rtcParam.setCategory("manip2");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("DataFlowComponent");
		rtcParam.setMaxInstance(3);
		rtcParam.setIsTest(true);

		genParam.getRtcParams().add(rtcParam);
		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>(); 
		configset.add(new ConfigSetParam("int_param0","int","", "1"));
		configset.add(new ConfigSetParam("str_param0","List<string>","", "\"aaa\",\"bbb\",\"ccc\""));
		rtcParam.setConfigParams(configset);

		Generator generator = new Generator();
		GenerateManager manager = new VbDotNetGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\ConfigSet\\configset5\\";

		checkCode(result, resourceDir, "App.config");
		checkCode(result, resourceDir, "foo.vb");
		checkCode(result, resourceDir, "foo.vbproj");
		checkCode(result, resourceDir, "foo.vbproj.user");
//		checkCode(result, resourceDir, "gen.xml");
		checkCode(result, resourceDir, "Program.vb");
		//
		checkCode(result, resourceDir, "\\My Project\\AssemblyInfo.vb");
		//
//		checkCode(result, resourceDir, "README.foo");
	}

}
