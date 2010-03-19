package jp.go.aist.rtm.rtcbuilder._test._100;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;

public class CXXBasicTest extends TestBase {
	private RtcParam rtcParam;
	private GeneratorParam genParam;

	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
	}

	public void testOperation() throws Exception{
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
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);

		// List<String> privateAtt = new ArrayList<String>();
		// privateAtt.add(new String("int private1"));
		// privateAtt.add(new String("static int attribute_4"));
		// rtcParam.setPrivateAttributes(privateAtt);
		rtcParam.getPrivateAttributes().clear();
		rtcParam.getPrivateAttributes().add("int private1");
		rtcParam.getPrivateAttributes().add("static int attribute_4");

		// List<String> protectedAtt = new ArrayList<String>();
		// protectedAtt.add(new String("String protectval"));
		// protectedAtt.add(new String("static float attribute_4"));
		// rtcParam.setProtectedAttributes(protectedAtt);
		rtcParam.getProtectedAttributes().clear();
		rtcParam.getProtectedAttributes().add("String protectval");
		rtcParam.getProtectedAttributes().add("static float attribute_4");

		// List<String> publicAtt = new ArrayList<String>();
		// publicAtt.add(new String("boolean pubbol1"));
		// publicAtt.add(new String("double attribute_5"));
		// rtcParam.setPublicAttributes(publicAtt);
		rtcParam.getPublicAttributes().clear();
		rtcParam.getPublicAttributes().add("boolean pubbol1");
		rtcParam.getPublicAttributes().add("double attribute_5");

		// List<String> publicOpe = new ArrayList<String>();
		// publicOpe.add(new String("int operation_2(int param_1 = 10)"));
		// publicOpe.add(new String("String operation_1(String param_1, int
		// param_2)"));
		// rtcParam.setPublicOperations(publicOpe);
		rtcParam.getPublicOperations().clear();
		rtcParam.getPublicOperations().add("int operation_2(int param_1 = 10)");
		rtcParam.getPublicOperations().add(
				"String operation_1(String param_1, int param_2)");

		genParam.getRtcParams().add(rtcParam);
		
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\operation\\";

		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "Makefile.foo");
		checkCode(result, resourceDir, "foo.h");
		checkCode(result, resourceDir, "foo.cpp");
		checkCode(result, resourceDir, "README.foo");
	}

	public void testAttribute() throws Exception{
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
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);

		// List<String> privateAtt = new ArrayList<String>();
		// privateAtt.add(new String("int private1"));
		// privateAtt.add(new String("static int attribute_4"));
		// rtcParam.setPrivateAttributes(privateAtt);
		rtcParam.getPrivateAttributes().clear();
		rtcParam.getPrivateAttributes().add("int private1");
		rtcParam.getPrivateAttributes().add("static int attribute_4");

		// List<String> protectedAtt = new ArrayList<String>();
		// protectedAtt.add(new String("String protectval"));
		// protectedAtt.add(new String("static float attribute_4"));
		// rtcParam.setProtectedAttributes(protectedAtt);
		rtcParam.getProtectedAttributes().clear();
		rtcParam.getProtectedAttributes().add("String protectval");
		rtcParam.getProtectedAttributes().add("static float attribute_4");

		// List<String> publicdAtt = new ArrayList<String>();
		// publicdAtt.add(new String("boolean pubbol1"));
		// publicdAtt.add(new String("double attribute_5"));
		// rtcParam.setPublicAttributes(publicdAtt);
		rtcParam.getPublicAttributes().clear();
		rtcParam.getPublicAttributes().add("boolean pubbol1");
		rtcParam.getPublicAttributes().add("double attribute_5");

		genParam.getRtcParams().add(rtcParam);
		
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\attribute\\";

		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "Makefile.foo");
		checkCode(result, resourceDir, "foo.h");
		checkCode(result, resourceDir, "foo.cpp");
		checkCode(result, resourceDir, "README.foo");
	}

	public void testServicePort2() throws Exception{
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);
		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("InP1", "RTC::TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "RTC::TimedLong", "", 0));
		rtcParam.getInports().addAll(dataport);
		List<DataPortParam> outport = new ArrayList<DataPortParam>(); 
		outport.add(new DataPortParam("OutP1", "RTC::TimedInt", "", 0));
		outport.add(new DataPortParam("OutP2", "RTC::TimedFloat", "", 0));
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
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\service2\\";

		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "Makefile.foo");
		checkCode(result, resourceDir, "foo.h");
		checkCode(result, resourceDir, "foo.cpp");
		checkCode(result, resourceDir, "MyServiceSVC_impl.h");
		checkCode(result, resourceDir, "MyServiceSVC_impl.cpp");
		checkCode(result, resourceDir, "README.foo");
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
		//
	}

	public void testServicePort1() throws Exception{
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);
		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("InP1", "RTC::TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "RTC::TimedLong", "", 0));
		rtcParam.getInports().addAll(dataport);
		List<DataPortParam> outport = new ArrayList<DataPortParam>(); 
		outport.add(new DataPortParam("OutP1", "RTC::TimedInt", "", 0));
		outport.add(new DataPortParam("OutP2", "RTC::TimedFloat", "", 0));
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
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\service1\\";

		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "Makefile.foo");
		checkCode(result, resourceDir, "foo.h");
		checkCode(result, resourceDir, "foo.cpp");
		checkCode(result, resourceDir, "MyServiceSVC_impl.h");
		checkCode(result, resourceDir, "MyServiceSVC_impl.cpp");
		checkCode(result, resourceDir, "README.foo");
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
		//
	}

	public void testOutPort2() throws Exception{
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);
		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("InP1", "RTC::TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "RTC::TimedLong", "", 0));
		rtcParam.getInports().addAll(dataport);
		List<DataPortParam> outport = new ArrayList<DataPortParam>(); 
		outport.add(new DataPortParam("OutP1", "RTC::TimedInt", "", 0));
		outport.add(new DataPortParam("OutP2", "RTC::TimedFloat", "", 0));
		rtcParam.getOutports().addAll(outport);
		
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\outport2\\";

		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "Makefile.foo");
		checkCode(result, resourceDir, "foo.h");
		checkCode(result, resourceDir, "foo.cpp");
		checkCode(result, resourceDir, "README.foo");
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
		//
	}

	public void testOutPort1() throws Exception{
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);
		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("InP1", "RTC::TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "RTC::TimedLong", "", 0));
		rtcParam.getInports().addAll(dataport);
		List<DataPortParam> outport = new ArrayList<DataPortParam>(); 
		outport.add(new DataPortParam("OutP1", "RTC::TimedInt", "", 0));
		rtcParam.getOutports().addAll(outport);
		
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\outport1\\";

		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "Makefile.foo");
		checkCode(result, resourceDir, "foo.h");
		checkCode(result, resourceDir, "foo.cpp");
		checkCode(result, resourceDir, "README.foo");
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
		//
	}

	public void testInPort2() throws Exception{
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setMaxInstance(5);
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);
		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("InP1", "RTC::TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "RTC::TimedLong", "", 0));
		rtcParam.getInports().addAll(dataport);
		
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\inport2\\";

		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "Makefile.foo");
		checkCode(result, resourceDir, "foo.h");
		checkCode(result, resourceDir, "foo.cpp");
		checkCode(result, resourceDir, "README.foo");
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
		//
	}

	public void testInPort() throws Exception{
		rtcParam.setName("test");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);
		
		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("InP1", "RTC::TimedShort", "", 0));
		rtcParam.getInports().addAll(dataport);
		
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\inport1\\";

		checkCode(result, resourceDir, "testComp.cpp");
		checkCode(result, resourceDir, "Makefile.test");
		checkCode(result, resourceDir, "test.h");
		checkCode(result, resourceDir, "test.cpp");
		checkCode(result, resourceDir, "README.test");
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
		//
	}

	public void testBasic() throws Exception{
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);
		
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\name\\";

		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "Makefile.foo");
		checkCode(result, resourceDir, "foo.h");
		checkCode(result, resourceDir, "foo.cpp");
		checkCode(result, resourceDir, "README.foo");
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
		//
	}
}
