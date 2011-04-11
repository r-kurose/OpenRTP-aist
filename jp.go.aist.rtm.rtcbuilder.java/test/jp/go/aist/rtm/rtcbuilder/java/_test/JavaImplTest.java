package jp.go.aist.rtm.rtcbuilder.java._test;

import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.java.IRtcBuilderConstantsJava;
import jp.go.aist.rtm.rtcbuilder.java.manager.JavaGenerateManager;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;

public class JavaImplTest extends TestBase {
	private RtcParam rtcParam;
	private GeneratorParam genParam;

	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstantsJava.LANG_JAVA);
		rtcParam.setLanguageArg(IRtcBuilderConstantsJava.LANG_JAVA_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);
		rtcParam.setRtmVersion("0.4.2");
		genParam.getRtcParams().add(rtcParam);
	}

	public void testAll() throws Exception{
		rtcParam.setActionImplemented(0, true);
		rtcParam.setActionImplemented(1, true);
		rtcParam.setActionImplemented(2, true);
		rtcParam.setActionImplemented(3, true);
		rtcParam.setActionImplemented(4, true);
		rtcParam.setActionImplemented(5, true);
		rtcParam.setActionImplemented(6, true);
		rtcParam.setActionImplemented(7, true);
		rtcParam.setActionImplemented(8, true);
		rtcParam.setActionImplemented(9, true);
		rtcParam.setActionImplemented(10, true);
		rtcParam.setActionImplemented(11, true);
		
		Generator generator = new Generator();
		GenerateManager manager = new JavaGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);
		String resourceDir = rootPath +  "\\resource\\Java\\impl\\all\\";

		checkResults(result, resourceDir);
	}

	public void testExecute() throws Exception{
		rtcParam.setActionImplemented(0, true);
		rtcParam.setActionImplemented(1, true);
		rtcParam.setActionImplemented(3, true);
		rtcParam.setActionImplemented(5, true);
		rtcParam.setActionImplemented(9, true);
		
		Generator generator = new Generator();
		GenerateManager manager = new JavaGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\Java\\impl\\execute\\";
		checkResults(result, resourceDir);
	}

	public void testFinalize() throws Exception{
		rtcParam.setActionImplemented(0, true);
		rtcParam.setActionImplemented(1, true);
		
		Generator generator = new Generator();
		GenerateManager manager = new JavaGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\Java\\impl\\finalize\\";
		checkResults(result, resourceDir);
	}

	public void testInitialize() throws Exception{
		rtcParam.setActionImplemented(0, true);
		
		Generator generator = new Generator();
		GenerateManager manager = new JavaGenerateManager();
		generator.addGenerateManager(manager);
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\Java\\impl\\initialize\\";
		checkResults(result, resourceDir);
	}

	private void checkResults(List<GeneratedResult> result, String resourceDir) {
		checkCode(result, resourceDir, "fooComp.java");
		checkCode(result, resourceDir, "build_foo.xml");
		checkCode(result, resourceDir, "foo.java");
		checkCode(result, resourceDir, "fooImpl.java");
		checkCode(result, resourceDir, "README.foo");
	}
}
