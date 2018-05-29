package jp.go.aist.rtm.rtcbuilder._test.parse;

import java.io.StringReader;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.IDLParser;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.specification;
import jp.go.aist.rtm.rtcbuilder.generator.IDLParamConverter;
import jp.go.aist.rtm.rtcbuilder.generator.PreProcessor;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.TypeDefParam;
import jp.go.aist.rtm.rtcbuilder.util.FileUtil;

public class CORBAParseCommentTest extends TestBase {

	public void testMultiComment() throws Exception{
		String idlContent = FileUtil.readFile(rootPath + "\\resource\\IDL\\WalkGeneratorService.idl");

		String idl = PreProcessor.parse(idlContent, null, null, true);
		IDLParser parser = new IDLParser(new StringReader(idl));

		specification spec = parser.specification();
		List<TypeDefParam> typedefParams = IDLParamConverter
				.convert_typedef(spec, "");
		
		assertEquals(1, typedefParams.size());
	}
	
	public void testMultiCommentforPref() throws Exception{
		String idlContent = FileUtil.readFile(rootPath + "\\resource\\IDL\\WalkGeneratorService.idl");

		String idl = PreProcessor.parseAlltoSpace(idlContent);
		IDLParser parser = new IDLParser(new StringReader(idl));

		specification spec = parser.specification();
		List<TypeDefParam> typedefParams = IDLParamConverter
				.convert_typedef(spec, "");
		
		assertEquals(1, typedefParams.size());
	}
}
