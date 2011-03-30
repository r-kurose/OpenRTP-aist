package jp.go.aist.rtm.rtcbuilder._test._042;

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

public class CXX042DiffTest extends TestBase {

	RtcParam rtcParam;
	GeneratorParam genParam;

	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
		rtcParam.setRtmVersion("0.4.2");
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);
	}

	public void testDiffName2() throws Exception {
		rtcParam.setName("test");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);

		ServicePortParam service1 = new ServicePortParam("MySVPro0",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "\\resource\\CXX\\diffname2\\MyService3.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\CXX\\diffname2\\";

		checkCode(result, resourceDir, "testComp.cpp");
		checkCode(result, resourceDir, "Makefile.test");
		checkCode(result, resourceDir, "test.h");
		checkCode(result, resourceDir, "test.cpp");
		checkCode(result, resourceDir, "MyService3SVC_impl.h");
		checkCode(result, resourceDir, "MyService3SVC_impl.cpp");
		checkCode(result, resourceDir, "README.test");
		//
		checkCode(result, resourceDir, "test_vc8.sln");
		checkCode(result, resourceDir, "test_vc8.vcproj");
		checkCode(result, resourceDir, "test_vc9.sln");
		checkCode(result, resourceDir, "test_vc9.vcproj");
		checkCode(result, resourceDir, "testComp_vc8.vcproj");
		checkCode(result, resourceDir, "testComp_vc9.vcproj");
		//
		checkCode(result, resourceDir, "copyprops.bat");
		checkCode(result, resourceDir, "user_config.vsprops");
	}

	public void testDiffName() throws Exception {
		rtcParam.setName("test");
		rtcParam.setDescription("test component");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("S.Kurihara");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(1);

		ServicePortParam service1 = new ServicePortParam("MySVPro0",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "myservice0", "", "", 
				rootPath + "resource\\CXX\\diffname\\MyService3.idl", "MyService", "", 0);
		srvinterts.add(int1);
		service1.getServicePortInterfaces().addAll(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.getServicePorts().addAll(srvports);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\CXX\\diffname\\";

		checkCode(result, resourceDir, "testComp.cpp");
		checkCode(result, resourceDir, "Makefile.test");
		checkCode(result, resourceDir, "test.h");
		checkCode(result, resourceDir, "test.cpp");
		checkCode(result, resourceDir, "MyService3SVC_impl.h");
		checkCode(result, resourceDir, "MyService3SVC_impl.cpp");
		checkCode(result, resourceDir, "README.test");
		//
		checkCode(result, resourceDir, "test_vc8.sln");
		checkCode(result, resourceDir, "test_vc8.vcproj");
		checkCode(result, resourceDir, "test_vc9.sln");
		checkCode(result, resourceDir, "test_vc9.vcproj");
		checkCode(result, resourceDir, "testComp_vc8.vcproj");
		checkCode(result, resourceDir, "testComp_vc9.vcproj");
		//
		checkCode(result, resourceDir, "copyprops.bat");
		checkCode(result, resourceDir, "user_config.vsprops");
	}
}
