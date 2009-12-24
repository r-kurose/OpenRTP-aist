package jp.go.aist.rtm.rtcbuilder._test._100.cxx;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;

public class CXXIDLStruct extends TestBase {

	protected void setUp() throws Exception {
	}

	public void testIDLStruct() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
		rtcParam.setName("ModuleName");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("VenderName");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("STATIC");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setExecutionRate(1.0);
		rtcParam.setMaxInstance(1);
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);

		ServicePortParam service1 = new ServicePortParam("sV1",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "sIF1", "", "sIFv", 
				rootPath + "resource\\100\\CXX\\Struct\\TestService.idl", "TestService", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		
		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);
		
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\Struct\\";

		checkCode(result, resourceDir, "ModuleNameComp.cpp");
		checkCode(result, resourceDir, "Makefile.ModuleName");
		checkCode(result, resourceDir, "ModuleName.h");
		checkCode(result, resourceDir, "ModuleName.cpp");
		checkCode(result, resourceDir, "README.ModuleName");
		checkCode(result, resourceDir, "TestServiceSVC_impl.h");
		checkCode(result, resourceDir, "TestServiceSVC_impl.cpp");
		//
		checkCode(result, resourceDir, "ModuleName_vc8.sln");
		checkCode(result, resourceDir, "ModuleName_vc8.vcproj");
		checkCode(result, resourceDir, "ModuleNameComp_vc8.vcproj");
		checkCode(result, resourceDir, "ModuleName_vc9.sln");
		checkCode(result, resourceDir, "ModuleName_vc9.vcproj");
		checkCode(result, resourceDir, "ModuleNameComp_vc9.vcproj");
		//
		checkCode(result, resourceDir, "copyprops.bat");
		checkCode(result, resourceDir, "user_config.vsprops");
	}
	
	public void testIDLSeqStrring() throws Exception{
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
		rtcParam.setComponentType("STATIC");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(5);
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);

		ServicePortParam service1 = new ServicePortParam("svPort",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "acc", "", "acc", 
				rootPath + "resource\\100\\CXX\\SeqString\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		
		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);
		
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\SeqString\\";

		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "Makefile.foo");
		checkCode(result, resourceDir, "foo.h");
		checkCode(result, resourceDir, "foo.cpp");
		checkCode(result, resourceDir, "README.foo");
		checkCode(result, resourceDir, "MyServiceSVC_impl.h");
		checkCode(result, resourceDir, "MyServiceSVC_impl.cpp");
		//
		checkCode(result, resourceDir, "foo_vc8.sln");
		checkCode(result, resourceDir, "foo_vc8.vcproj");
		checkCode(result, resourceDir, "fooComp_vc8.vcproj");
		checkCode(result, resourceDir, "foo_vc9.sln");
		checkCode(result, resourceDir, "foo_vc9.vcproj");
		checkCode(result, resourceDir, "fooComp_vc9.vcproj");
		//
		checkCode(result, resourceDir, "copyprops.bat");
		checkCode(result, resourceDir, "user_config.vsprops");
	}
}
