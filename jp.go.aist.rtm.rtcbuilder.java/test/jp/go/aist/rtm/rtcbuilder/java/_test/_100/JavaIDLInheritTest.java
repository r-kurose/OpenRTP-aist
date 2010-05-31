package jp.go.aist.rtm.rtcbuilder.java._test._100;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.java.IRtcBuilderConstantsJava;
import jp.go.aist.rtm.rtcbuilder.java._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.java.manager.JavaGenerateManager;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;

public class JavaIDLInheritTest extends TestBase {

	GeneratorParam genParam;

	RtcParam rtcParam;

	Generator generator;

	GenerateManager manager;

	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);

		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsJava.LANG_JAVA);
		rtcParam.setLanguageArg(IRtcBuilderConstantsJava.LANG_JAVA_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("test module");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("sample");
		rtcParam.setComponentType("STATIC");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setMaxInstance(2);
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setRtmVersion(IRtcBuilderConstants.RTM_VERSION_100);
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);

		generator = new Generator();
		manager = new JavaGenerateManager();
		generator.addGenerateManager(manager);
	}

	String fixturePath(String name) {
		return rootPath + "resource\\100\\" + name;
	}

	public void testInherit1() throws Exception {
		String name = "inherit1";

		List<ServicePortParam> svports = new ArrayList<ServicePortParam>();

		ServicePortParam sv1 = new ServicePortParam("MyServiceProvider", 0);
		List<ServicePortInterfaceParam> iflist = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam sif1 = new ServicePortInterfaceParam(sv1,
				"MyServiceProvider", "", "", fixturePath(name)
						+ "\\MyServiceChildMulti.idl", "MyServiceChild",
				fixturePath(name), 0);
		iflist.add(sif1);
		sv1.getServicePortInterfaces().addAll(iflist);
		svports.add(sv1);

		ServicePortParam sv2 = new ServicePortParam("MyServiceRequire", 0);
		iflist = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam sif2 = new ServicePortInterfaceParam(sv2,
				"MyServiceRequire", "", "", fixturePath(name)
						+ "\\MyServiceChildMulti.idl", "MyServiceChild",
				fixturePath(name), 1);
		iflist.add(sif2);
		sv2.getServicePortInterfaces().addAll(iflist);
		svports.add(sv2);

		rtcParam.getServicePorts().addAll(svports);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = fixturePath(name) + "\\";

		assertEquals(8, result.size());
		checkCode(result, resourceDir, "fooComp.java");
		checkCode(result, resourceDir, "build_foo.xml");
		checkCode(result, resourceDir, "foo.java");
		checkCode(result, resourceDir, "fooImpl.java");
		checkCode(result, resourceDir, "MyServiceChildSVC_impl.java");
		checkCode(result, resourceDir, "MyServiceSVC_impl.java");
		checkCode(result, resourceDir, "MyService2SVC_impl.java");
		try {
			checkCode(result, resourceDir, "README.foo");
			fail();
		} catch(Exception ex) {
		}
	}

	public void testInherit2() throws Exception {
		String name = "inherit2";

		List<ServicePortParam> svports = new ArrayList<ServicePortParam>();

		ServicePortParam sv1 = new ServicePortParam("MyServiceProvider", 0);
		List<ServicePortInterfaceParam> iflist = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam sif1 = new ServicePortInterfaceParam(sv1,
				"MyServiceProvider", "", "", fixturePath(name)
						+ "\\MyServiceChildMulti.idl", "MyServiceChild",
				fixturePath(name), 0);
		iflist.add(sif1);
		ServicePortInterfaceParam sif2 = new ServicePortInterfaceParam(sv1,
				"MyServiceProvider2", "", "", fixturePath(name)
						+ "\\MyServiceChildWithType.idl",
				"MyServiceWithTypeChild", fixturePath(name), 0);
		iflist.add(sif2);
		sv1.getServicePortInterfaces().addAll(iflist);
		svports.add(sv1);

		ServicePortParam sv2 = new ServicePortParam("MyServiceRequire", 0);
		iflist = new ArrayList<ServicePortInterfaceParam>();
		ServicePortInterfaceParam sif3 = new ServicePortInterfaceParam(sv2,
				"MyServiceRequire", "", "", fixturePath(name)
						+ "\\MyServiceChildMulti.idl", "MyServiceChild",
				fixturePath(name), 1);
		iflist.add(sif3);
		ServicePortInterfaceParam sif4 = new ServicePortInterfaceParam(sv2,
				"MyServiceRequire2", "", "", fixturePath(name)
						+ "\\MyServiceChildWithType.idl",
				"MyServiceWithTypeChild", fixturePath(name), 1);
		iflist.add(sif4);
		sv2.getServicePortInterfaces().addAll(iflist);
		svports.add(sv2);

		rtcParam.getServicePorts().addAll(svports);

		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = fixturePath(name) + "\\";

		assertEquals(10, result.size());
		checkCode(result, resourceDir, "fooComp.java");
		checkCode(result, resourceDir, "build_foo.xml");
		checkCode(result, resourceDir, "foo.java");
		checkCode(result, resourceDir, "fooImpl.java");
		checkCode(result, resourceDir, "MyServiceChildSVC_impl.java");
		checkCode(result, resourceDir, "MyServiceSVC_impl.java");
		checkCode(result, resourceDir, "MyService2SVC_impl.java");
		checkCode(result, resourceDir, "MyServiceWithTypeChildSVC_impl.java");
		checkCode(result, resourceDir, "MyServiceWithTypeSVC_impl.java");
		try {
			checkCode(result, resourceDir, "README.foo");
			fail();
		} catch(Exception ex) {
		}
	}

}
