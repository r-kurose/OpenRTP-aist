package jp.go.aist.rtm.rtcbuilder.csharp._test;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.csharp.IRtcBuilderConstantsCSharp;
import jp.go.aist.rtm.rtcbuilder.csharp.manager.CSharpGenerateManager;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigSetParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;

public class CSConfigSetTest extends TestBase {

	protected void setUp() throws Exception {
	}

	public void testConfigSet() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsCSharp.LANG_CSHARP);
		rtcParam.setLanguageArg(IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.3");
		rtcParam.setVender("TA2");
		rtcParam.setCategory("manip2");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(3);
		rtcParam.setIsTest(true);

		genParam.getRtcParams().add(rtcParam);
		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>(); 
		configset.add(new ConfigSetParam("int_param0","int","", "0"));
		rtcParam.getConfigParams().addAll(configset);

		Generator generator = new Generator();
		GenerateManager manager = new CSharpGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\ConfigSet\\configset1\\";
		
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

	public void testConfigSet2() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsCSharp.LANG_CSHARP);
		rtcParam.setLanguageArg(IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG);
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
		rtcParam.getInports().addAll(dataport);

		genParam.getRtcParams().add(rtcParam);
		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>(); 
		configset.add(new ConfigSetParam("int_param0","int","", "0"));
		configset.add(new ConfigSetParam("int_param1","int","", "1"));
		rtcParam.getConfigParams().addAll(configset);

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

		String resourceDir = rootPath +  "\\resource\\ConfigSet\\configset2\\";

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

	public void testConfigSet3() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsCSharp.LANG_CSHARP);
		rtcParam.setLanguageArg(IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.3");
		rtcParam.setVender("TA2");
		rtcParam.setCategory("manip2");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(3);
		rtcParam.setIsTest(true);

		genParam.getRtcParams().add(rtcParam);
		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>(); 
		configset.add(new ConfigSetParam("int_param0","int","", "0"));
		configset.add(new ConfigSetParam("int_param1","int","", "1"));
		configset.add(new ConfigSetParam("double_param0","double","", "0.11"));
		configset.add(new ConfigSetParam("str_param0","string","", "hoge"));
		configset.add(new ConfigSetParam("str_param1","string","", "dara"));
		rtcParam.getConfigParams().addAll(configset);

		Generator generator = new Generator();
		GenerateManager manager = new CSharpGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\ConfigSet\\configset3\\";

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
	public void testConfigSet4() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsCSharp.LANG_CSHARP);
		rtcParam.setLanguageArg(IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.3");
		rtcParam.setVender("TA2");
		rtcParam.setCategory("manip2");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(3);
		rtcParam.setIsTest(true);

		genParam.getRtcParams().add(rtcParam);
		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>(); 
		configset.add(new ConfigSetParam("int_param0","int","", "0"));
		configset.add(new ConfigSetParam("float_param0","List<float>","", "1.0,2.0,3.0"));
		rtcParam.getConfigParams().addAll(configset);

		Generator generator = new Generator();
		GenerateManager manager = new CSharpGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\ConfigSet\\configset4\\";

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
	public void testConfigSet5() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsCSharp.LANG_CSHARP);
		rtcParam.setLanguageArg(IRtcBuilderConstantsCSharp.LANG_CSHARP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.3");
		rtcParam.setVender("TA2");
		rtcParam.setCategory("manip2");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(3);
		rtcParam.setIsTest(true);

		genParam.getRtcParams().add(rtcParam);
		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>(); 
		configset.add(new ConfigSetParam("int_param0","int","", "1"));
		configset.add(new ConfigSetParam("str_param0","List<string>","", "\"aaa\",\"bbb\",\"ccc\""));
		rtcParam.getConfigParams().addAll(configset);

		Generator generator = new Generator();
		GenerateManager manager = new CSharpGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\ConfigSet\\configset5\\";

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
