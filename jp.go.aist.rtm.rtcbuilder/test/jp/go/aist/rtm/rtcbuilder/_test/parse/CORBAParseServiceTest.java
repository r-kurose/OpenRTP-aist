package jp.go.aist.rtm.rtcbuilder._test.parse;

import java.io.StringReader;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.IDLParser;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.specification;
import jp.go.aist.rtm.rtcbuilder.generator.IDLParamConverter;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;
import jp.go.aist.rtm.rtcbuilder.util.FileUtil;

public class CORBAParseServiceTest extends TestBase {

	public void testDup() throws Exception{
		String idlContent = FileUtil.readFile(rootPath + "\\resource\\IDL\\MyServiceModuleDup.idl");
		IDLParser parser = new IDLParser(new StringReader(idlContent));
		specification spec = parser.specification();
		List<ServiceClassParam> serviceClassParams = IDLParamConverter
					.convert(spec, "");
		
		assertEquals(2, serviceClassParams.size());
	}
	
	public void testComment() throws Exception{
		String idlContent = FileUtil.readFile(rootPath + "\\resource\\IDL\\MyServiceComment.idl");
		IDLParser parser = new IDLParser(new StringReader(idlContent));
		specification spec = parser.specification();
		List<ServiceClassParam> serviceClassParams = IDLParamConverter
					.convert(spec, "");
		
		assertEquals(1, serviceClassParams.size());
		assertEquals("MyService", serviceClassParams.get(0).getName());
	}
	
	public void testRTM() throws Exception{
		String idlContent = FileUtil.readFile(rootPath + "\\resource\\IDL\\RTC_061222.idl");
		IDLParser parser = new IDLParser(new StringReader(idlContent));
		specification spec = parser.specification();
		List<ServiceClassParam> serviceClassParams = IDLParamConverter
					.convert(spec, "");
		
		assertEquals(16, serviceClassParams.size());
		assertEquals("RTC::ComponentAction", serviceClassParams.get(0).getName());
		assertEquals("RTC::LightweightRTObject", serviceClassParams.get(1).getName());
		assertEquals("RTC::ExecutionContext", serviceClassParams.get(2).getName());
		assertEquals("RTC::DataFlowComposite", serviceClassParams.get(3).getName());
		assertEquals("RTC::DataFlowComponentAction", serviceClassParams.get(4).getName());
		assertEquals("RTC::DataFlowParticipant", serviceClassParams.get(5).getName());
		assertEquals("RTC::Fsm", serviceClassParams.get(6).getName());
		assertEquals("RTC::FsmComponentAction", serviceClassParams.get(7).getName());
		assertEquals("RTC::FsmParticipant", serviceClassParams.get(8).getName());
		assertEquals("RTC::Mode", serviceClassParams.get(9).getName());
		assertEquals("RTC::ModeCapable", serviceClassParams.get(10).getName());
		assertEquals("RTC::MultiModeComponentAction", serviceClassParams.get(11).getName());
		assertEquals("RTC::MultiModeObject", serviceClassParams.get(12).getName());
		assertEquals("RTC::Port", serviceClassParams.get(13).getName());
		assertEquals("RTC::ExecutionContextService", serviceClassParams.get(14).getName());
		assertEquals("RTC::RTObject", serviceClassParams.get(15).getName());
	}
	
	public void testModule() throws Exception{
		String idlContent = FileUtil.readFile(rootPath + "\\resource\\IDL\\MyServiceModule.idl");
		IDLParser parser = new IDLParser(new StringReader(idlContent));
		specification spec = parser.specification();
		List<ServiceClassParam> serviceClassParams = IDLParamConverter
					.convert(spec, "");
		
		assertEquals(1, serviceClassParams.size());
		assertEquals("RTM::MyService", serviceClassParams.get(0).getName());
	}
	
	public void testMulti() throws Exception{
		String idlContent = FileUtil.readFile(rootPath + "\\resource\\IDL\\MyServiceMulti.idl");
		IDLParser parser = new IDLParser(new StringReader(idlContent));
		specification spec = parser.specification();
		List<ServiceClassParam> serviceClassParams = IDLParamConverter
					.convert(spec, "");
		
		assertEquals(2, serviceClassParams.size());
		assertEquals("MyService", serviceClassParams.get(0).getName());
		assertEquals("MyServiceOpen", serviceClassParams.get(1).getName());
	}
	
	public void testDiff() throws Exception{
		String idlContent = FileUtil.readFile(rootPath + "\\resource\\IDL\\MyServiceDiff.idl");
		IDLParser parser = new IDLParser(new StringReader(idlContent));
		specification spec = parser.specification();
		List<ServiceClassParam> serviceClassParams = IDLParamConverter
					.convert(spec, "");
		
		assertEquals(1, serviceClassParams.size());
		assertEquals("MyServiceDif", serviceClassParams.get(0).getName());
	}
	
	public void testBasic() throws Exception{
		String idlContent = FileUtil.readFile(rootPath + "\\resource\\IDL\\MyService.idl");
		IDLParser parser = new IDLParser(new StringReader(idlContent));
		specification spec = parser.specification();
		List<ServiceClassParam> serviceClassParams = IDLParamConverter
					.convert(spec, "");
		
		assertEquals(1, serviceClassParams.size());
		assertEquals("MyService", serviceClassParams.get(0).getName());
	}
}
