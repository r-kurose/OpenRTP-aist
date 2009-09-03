package jp.go.aist.rtm.rtcbuilder._test._042.cxx;

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

public class CXXTemplateTestAIST2 extends TestBase {

	protected void setUp() throws Exception {
	}

	public void testAIST6() throws Exception{
		String resourceDir = "CXX\\module";
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam,true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
		rtcParam.setName("ModuleName");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("VenderName");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("DataFlowComponent");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);
		rtcParam.setRtmVersion("0.4.2");
		rtcParam.setIsTest(true);

		ServicePortParam service1 = new ServicePortParam("portName",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "name", "", "", 
				rootPath + "resource\\042\\CXX\\module\\MyService.idl", "Test::MyService", "", 0);
		srvinterts.add(int1);
		service1.setServicePortInterfaces(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.setServicePorts(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String targetDir = rootPath + "\\resource\\042\\CXX\\module\\";
		checkCode(result, targetDir, "ModuleNameComp.cpp");
		checkCode(result, targetDir, "Makefile.ModuleName");
		checkCode(result, targetDir, "ModuleName.h");
		checkCode(result, targetDir, "ModuleName.cpp");
		checkCode(result, targetDir, "MyServiceSVC_impl.h");
		checkCode(result, targetDir, "MyServiceSVC_impl.cpp");
		checkCode(result, targetDir, "README.ModuleName");
		//
		checkCode(result, targetDir, "ModuleName_vc8.sln");
		checkCode(result, targetDir, "ModuleName_vc8.vcproj");
		checkCode(result, targetDir, "ModuleNameComp_vc8.vcproj");
		checkCode(result, targetDir, "ModuleName_vc9.sln");
		checkCode(result, targetDir, "ModuleName_vc9.vcproj");
		checkCode(result, targetDir, "ModuleNameComp_vc9.vcproj");
		//
		checkCode(result, targetDir, "copyprops.bat");
		checkCode(result, targetDir, "user_config.vsprops");
	}
}
