package jp.go.aist.rtm.rtcbuilder._test.com;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.IRTCBMessageConstants;
import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.IDLParser;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.specification;
import jp.go.aist.rtm.rtcbuilder.generator.IDLParamConverter;
import jp.go.aist.rtm.rtcbuilder.generator.PreProcessor;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;
import jp.go.aist.rtm.rtcbuilder.util.FileUtil;

public class CORBAParseInheritTest extends TestBase {

	public void testInherit() throws Exception{
		List<String> includeFiles = new ArrayList<String>();
		
		String idlContent = FileUtil.readFile(rootPath + "\\resource\\IDL\\MyServiceChild.idl");
		String idl = PreProcessor.parse(idlContent, getIncludeIDLDic(rootPath + "\\resource\\IDL"), includeFiles);
		IDLParser parser = new IDLParser(new StringReader(idl));

		specification spec = parser.specification();
		List<ServiceClassParam> serviceClassParams = IDLParamConverter.convert(spec, "");

		assertEquals(2, serviceClassParams.size());
		assertEquals("MyService", serviceClassParams.get(0).getName());
		assertEquals("MyServiceChild", serviceClassParams.get(1).getName());
		//
		assertEquals(4, serviceClassParams.get(1).getMethods().size());
		assertEquals("setPos", serviceClassParams.get(1).getMethods().get(0).getName());
		assertEquals("getPos", serviceClassParams.get(1).getMethods().get(1).getName());
		assertEquals("setGain", serviceClassParams.get(1).getMethods().get(2).getName());
		assertEquals("getGain", serviceClassParams.get(1).getMethods().get(3).getName());
		//
		assertEquals(1, includeFiles.size());
		assertEquals("C:\\Tech-Arts\\EclipseRTM\\jp.go.aist.rtm.rtcbuilder\\resource\\IDL\\MyServiceParent.idl",
				includeFiles.get(0));
	}

	public void testInheritWithType() throws Exception{
		List<String> includeFiles = new ArrayList<String>();
		
		String idlContent = FileUtil.readFile(rootPath + "\\resource\\IDL\\MyServiceChildWithType.idl");
		String idl = PreProcessor.parse(idlContent, getIncludeIDLDic(rootPath + "\\resource\\IDL"), includeFiles);
		IDLParser parser = new IDLParser(new StringReader(idl));

		specification spec = parser.specification();
		List<ServiceClassParam> serviceClassParams = IDLParamConverter.convert(spec, "");

		assertEquals(2, serviceClassParams.size());
		assertEquals("MyService", serviceClassParams.get(0).getName());
		assertEquals("MyServiceChild", serviceClassParams.get(1).getName());
		//
		assertEquals(4, serviceClassParams.get(1).getMethods().size());
		assertEquals("setPos", serviceClassParams.get(1).getMethods().get(0).getName());
		assertEquals("getPos", serviceClassParams.get(1).getMethods().get(1).getName());
		assertEquals("get_echo_history", serviceClassParams.get(1).getMethods().get(2).getName());
		assertEquals("get_value_history", serviceClassParams.get(1).getMethods().get(3).getName());
		//
		assertEquals(1, includeFiles.size());
		assertEquals("C:\\Tech-Arts\\EclipseRTM\\jp.go.aist.rtm.rtcbuilder\\resource\\IDL\\MyServiceParentWithType.idl",
				includeFiles.get(0));
	}

	public void testInheritMulti() throws Exception{
		List<String> includeFiles = new ArrayList<String>();
		
		String idlContent = FileUtil.readFile(rootPath + "\\resource\\IDL\\MyServiceChildMulti.idl");
		String idl = PreProcessor.parse(idlContent, getIncludeIDLDic(rootPath + "\\resource\\IDL"), includeFiles);
		IDLParser parser = new IDLParser(new StringReader(idl));

		specification spec = parser.specification();
		List<ServiceClassParam> serviceClassParams = IDLParamConverter.convert(spec, "");

		assertEquals(3, serviceClassParams.size());
		assertEquals("MyService", serviceClassParams.get(0).getName());
		assertEquals("MyService2", serviceClassParams.get(1).getName());
		assertEquals("MyServiceChild", serviceClassParams.get(2).getName());
		//
		assertEquals(6, serviceClassParams.get(2).getMethods().size());
		assertEquals("setPos", serviceClassParams.get(2).getMethods().get(0).getName());
		assertEquals("getPos", serviceClassParams.get(2).getMethods().get(1).getName());
		assertEquals("get_echo_history", serviceClassParams.get(2).getMethods().get(2).getName());
		assertEquals("get_value_history", serviceClassParams.get(2).getMethods().get(3).getName());
		assertEquals("setGain", serviceClassParams.get(2).getMethods().get(4).getName());
		assertEquals("getGain", serviceClassParams.get(2).getMethods().get(5).getName());
		//
		assertEquals(2, includeFiles.size());
		assertEquals("C:\\Tech-Arts\\EclipseRTM\\jp.go.aist.rtm.rtcbuilder\\resource\\IDL\\MyServiceParent1.idl",
				includeFiles.get(0));
		assertEquals("C:\\Tech-Arts\\EclipseRTM\\jp.go.aist.rtm.rtcbuilder\\resource\\IDL\\MyServiceParent2.idl",
				includeFiles.get(1));
	}

	private File getIncludeIDLDic(String targetDir) {
		File result = null;
		if( targetDir!=null && targetDir.length()>0 ) {
			File file = new File(targetDir);
			if (file.exists()) {
				result = file;
			} else {
				throw new RuntimeException(IRTCBMessageConstants.ERROR_IDL_DIRECTORY_NOT_FOUND);
			}
		}
		return result;
	}

	
}
