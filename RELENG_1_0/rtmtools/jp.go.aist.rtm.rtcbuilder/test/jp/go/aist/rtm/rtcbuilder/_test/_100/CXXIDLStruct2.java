package jp.go.aist.rtm.rtcbuilder._test._100;

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

public class CXXIDLStruct2 extends TestBase {
	private GeneratorParam genParam;
	private RtcParam rtcParam;

	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
	}

	public void testType() throws Exception{
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
		rtcParam.setName("test");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("exmple");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setMaxInstance(1);
		rtcParam.setIsTest(true);

		ServicePortParam service1 = new ServicePortParam("MySVPro",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice", "", "", 
				rootPath + "resource\\100\\CXX\\type\\MyService.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\type\\";

		checkCode(result, resourceDir, "MyServiceSVC_impl.h");
		checkCode(result, resourceDir, "MyServiceSVC_impl.cpp");
		checkCode(result, resourceDir, "testComp.cpp");
		checkCode(result, resourceDir, "Makefile.test");
		checkCode(result, resourceDir, "test.h");
		checkCode(result, resourceDir, "test.cpp");
		try {
			checkCode(result, resourceDir, "README.test");
			fail();
		} catch(Exception ex) {
		}
		//
		checkCode(result, resourceDir, "test_vc8.sln");
		checkCode(result, resourceDir, "test_vc8.vcproj");
		checkCode(result, resourceDir, "testComp_vc8.vcproj");
		checkCode(result, resourceDir, "test_vc9.sln");
		checkCode(result, resourceDir, "test_vc9.vcproj");
		checkCode(result, resourceDir, "testComp_vc9.vcproj");
		//
		checkCode(result, resourceDir, "copyprops.bat");
		checkCode(result, resourceDir, "user_config.vsprops");
	}

	public void testIDLStruct2() throws Exception{
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
				rootPath + "resource\\100\\CXX\\Struct2\\TestService.idl", "ComFk", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		
		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);
		
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\Struct2\\";

		assertEquals(16, result.size());
		checkCode(result, resourceDir, "ModuleNameComp.cpp");
		checkCode(result, resourceDir, "Makefile.ModuleName");
		checkCode(result, resourceDir, "ModuleName.h");
		checkCode(result, resourceDir, "ModuleName.cpp");
		try {
			checkCode(result, resourceDir, "README.ModuleName");
			fail();
		} catch(Exception ex) {
		}
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
	
	public void testIDLType() throws Exception{
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
				rootPath + "resource\\100\\CXX\\IDLType\\TestIDL.idl", "ComFk", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		
		rtcParam.getServicePorts().addAll(srvports);
		genParam.getRtcParams().add(rtcParam);
		
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\IDLType\\";

		assertEquals(16, result.size());
		checkCode(result, resourceDir, "ModuleNameComp.cpp");
		checkCode(result, resourceDir, "Makefile.ModuleName");
		checkCode(result, resourceDir, "ModuleName.h");
		checkCode(result, resourceDir, "ModuleName.cpp");
		try {
			checkCode(result, resourceDir, "README.ModuleName");
			fail();
		} catch(Exception ex) {
		}
		checkCode(result, resourceDir, "TestIDLSVC_impl.h");
		checkCode(result, resourceDir, "TestIDLSVC_impl.cpp");
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
}
