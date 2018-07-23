package jp.go.aist.rtm.rtcbuilder._test.generateCode;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataTypeParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;

public class DataPortTest extends TestBase {
	RtcParam rtcParam;
	GeneratorParam genParam;

	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "/resource/work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);
		rtcParam.setName("ModuleName");
		rtcParam.setDescription("ModuleDescription");
		rtcParam.setVersion("1.0.0");
		rtcParam.setVender("VenderName");
		rtcParam.setCategory("Category");
		rtcParam.setComponentType("DataFlowComponent");
		rtcParam.setActivityType("PERIODIC");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setMaxInstance(1);
		
		DataTypeParam param1 = new DataTypeParam();
		param1.setFullPath("C:\\Program Files\\OpenRTM-aist\\1.2.0\\rtm\\idl\\CameraCommonInterface.idl");
		param1.getDefinedTypes().add("Img::TimedCameraImage");
		param1.setDefault(true);
		genParam.getDataTypeParams().add(param1);
		
		DataTypeParam param2 = new DataTypeParam();
		param2.setFullPath("C:\\Program Files\\OpenRTM-aist\\1.2.0\\rtm\\idl\\InterfaceDataTypes.idl");
		param2.setDefault(true);
		genParam.getDataTypeParams().add(param2);
		
		DataTypeParam param3 = new DataTypeParam();
		param3.setFullPath("C:\\Program Files\\OpenRTM-aist\\1.2.0\\rtm\\idl\\BasicDataType.idl");
		param3.setDefault(true);
		genParam.getDataTypeParams().add(param3);
		
		DataTypeParam param4 = new DataTypeParam();
		param4.setFullPath("C:\\Program Files\\OpenRTM-aist\\1.2.0\\rtm\\idl\\ExtendedDataTypes.idl");
		param4.setDefault(true);
		genParam.getDataTypeParams().add(param4);
		
		genParam.setRtcParam(rtcParam);
	}

	public void testDataPort() throws Exception {
		DataPortParam port1 = new DataPortParam("dp_name", "Img::TimedCameraImage", "", 0);
		rtcParam.getInports().add(port1);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String targetDir = rootPath + "/resource/100/DataPort/";
		checkCode(result, targetDir, "idl/CMakeLists.txt");
	}
}
