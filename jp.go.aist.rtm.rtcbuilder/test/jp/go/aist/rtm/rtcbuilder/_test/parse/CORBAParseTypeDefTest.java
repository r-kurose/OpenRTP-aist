package jp.go.aist.rtm.rtcbuilder._test.parse;

import java.io.StringReader;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.IDLParser;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.specification;
import jp.go.aist.rtm.rtcbuilder.generator.IDLParamConverter;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.TypeDefParam;
import jp.go.aist.rtm.rtcbuilder.util.FileUtil;

public class CORBAParseTypeDefTest extends TestBase {

	public void testStringDef() throws Exception{
		String idlContent = FileUtil.readFile(rootPath + "\\resource\\IDL\\MyServiceDef.idl");
		IDLParser parser = new IDLParser(new StringReader(idlContent));

		specification spec = parser.specification();
		List<TypeDefParam> typedefParams = IDLParamConverter
				.convert_typedef(spec, "");
		
		assertEquals(3, typedefParams.size());
		assertEquals("string<1>", typedefParams.get(0).getOriginalDef());
		assertEquals("Str1", typedefParams.get(0).getTargetDef());
		assertEquals(false, typedefParams.get(0).isSequence());
		
		assertEquals("wstring<1>", typedefParams.get(1).getOriginalDef());
		assertEquals("WStr1", typedefParams.get(1).getTargetDef());
		assertEquals(false, typedefParams.get(1).isSequence());
		
		assertEquals("", typedefParams.get(2).getOriginalDef());
		assertEquals("MyService", typedefParams.get(2).getTargetDef());
		assertEquals(false, typedefParams.get(2).isSequence());
	}
	
	public void testArrayDef() throws Exception{
		String idlContent = FileUtil.readFile(rootPath + "\\resource\\IDL\\InterfaceSample.idl");
		IDLParser parser = new IDLParser(new StringReader(idlContent));

		specification spec = parser.specification();
		List<TypeDefParam> typedefParams = IDLParamConverter
				.convert_typedef(spec, "");
		
		assertEquals(2, typedefParams.size());
		
		assertEquals("", typedefParams.get(0).getOriginalDef());
		assertEquals("sample", typedefParams.get(0).getTargetDef());
		assertEquals(false, typedefParams.get(0).isArray());
		
		assertEquals("short", typedefParams.get(1).getOriginalDef());
		assertEquals(true, typedefParams.get(1).isArray());
	}
	
	public void testModule() throws Exception{
		String idlContent = FileUtil.readFile(rootPath + "\\resource\\IDL\\MyServiceRec.idl");
		IDLParser parser = new IDLParser(new StringReader(idlContent));
		specification spec = parser.specification();
		List<ServiceClassParam> serviceClassParams = IDLParamConverter
					.convert(spec, "");
		
		assertEquals(1, serviceClassParams.size());
		assertEquals("aaa::bbb::MyService", serviceClassParams.get(0).getName());
	}
	
}
