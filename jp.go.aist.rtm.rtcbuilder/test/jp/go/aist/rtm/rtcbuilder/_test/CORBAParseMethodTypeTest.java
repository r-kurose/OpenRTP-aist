package jp.go.aist.rtm.rtcbuilder._test;

import java.io.StringReader;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.IDLParser;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.specification;
import jp.go.aist.rtm.rtcbuilder.generator.IDLParamConverter;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.TypeDefParam;
import jp.go.aist.rtm.rtcbuilder.util.FileUtil;

public class CORBAParseMethodTypeTest extends TestBase {

	public void testNoSeq() throws Exception{
		String idlContent = FileUtil.readFile(rootPath + "\\resource\\IDL\\MyServiceAISTnoSeq.idl");
		IDLParser parser = new IDLParser(new StringReader(idlContent));

		specification spec = parser.specification();
		List<TypeDefParam> typedefParams = IDLParamConverter
				.convert_typedef(spec, "");
		
		assertEquals(3, typedefParams.size());
//		assertEquals("string", typedefParams.get(0).getOriginalDef());
		assertEquals(false, typedefParams.get(0).isSequence());
//		assertEquals("float", typedefParams.get(1).getOriginalDef());
		assertEquals(false, typedefParams.get(1).isSequence());
	}

	
	public void testBasic() throws Exception{
		String idlContent = FileUtil.readFile(rootPath + "\\resource\\IDL\\MyServiceAIST.idl");
		IDLParser parser = new IDLParser(new StringReader(idlContent));

		specification spec = parser.specification();
		List<TypeDefParam> typedefParams = IDLParamConverter
				.convert_typedef(spec, "");
		
		assertEquals(3, typedefParams.size());
//		assertEquals("string[]", typedefParams.get(0).getOriginalDef());
		assertEquals("string", typedefParams.get(0).getOriginalDef());
		assertEquals(true, typedefParams.get(0).isSequence());
//		assertEquals("float[]", typedefParams.get(1).getOriginalDef());
		assertEquals("float", typedefParams.get(1).getOriginalDef());
		assertEquals(true, typedefParams.get(1).isSequence());
	}
}
