package jp.go.aist.rtm.rtcbuilder._test.com;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.generator.IDLParamConverter;
import jp.go.aist.rtm.rtcbuilder.util.FileUtil;

public class CORBAParseTypeTest extends TestBase {

	public void testException() throws Exception{
		List<String> sources = new ArrayList<String>();
		List<String> sourceContents = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		sources.add(rootPath + "\\resource\\IDL\\SDOPackage.idl");
		
		for(int intidx=0;intidx<sources.size();intidx++) {
			String idlContent = FileUtil.readFile(sources.get(intidx));
			sourceContents.add(idlContent);
		}
			
		results = IDLParamConverter.extractTypeDef(sourceContents);
		
		assertEquals(9, results.size());
		assertTrue(results.contains("SDOPackage::NameValue"));
		assertTrue(results.contains("SDOPackage::EnumerationType"));
		assertTrue(results.contains("SDOPackage::RangeType"));
		assertTrue(results.contains("SDOPackage::IntervalType"));
		assertTrue(results.contains("SDOPackage::Parameter"));
		assertTrue(results.contains("SDOPackage::OrganizationProperty"));
		assertTrue(results.contains("SDOPackage::DeviceProfile"));
		assertTrue(results.contains("SDOPackage::ServiceProfile"));
		assertTrue(results.contains("SDOPackage::ConfigurationSet"));
	}
	
	public void testInclude() throws Exception{
		List<String> sources = new ArrayList<String>();
		List<String> sourceContents = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		sources.add(rootPath + "\\resource\\IDL\\Manager.idl");
		
		for(int intidx=0;intidx<sources.size();intidx++) {
			String idlContent = FileUtil.readFile(sources.get(intidx));
			sourceContents.add(idlContent);
		}
			
		results = IDLParamConverter.extractTypeDef(sourceContents);
		
		assertEquals(2, results.size());
		assertTrue(results.contains("RTM::ModuleProfile"));
		assertTrue(results.contains("RTM::ManagerProfile"));
	}
	
	public void testDuplicate() throws Exception{
		List<String> sources = new ArrayList<String>();
		List<String> sourceContents = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		sources.add(rootPath + "\\resource\\IDL\\DupliDataTypes1.idl");
		sources.add(rootPath + "\\resource\\IDL\\DupliDataTypes2.idl");
		
		for(int intidx=0;intidx<sources.size();intidx++) {
			String idlContent = FileUtil.readFile(sources.get(intidx));
			sourceContents.add(idlContent);
		}
			
		try {
			results = IDLParamConverter.extractTypeDef(sourceContents);
			fail();
		} catch (Exception e) {
			
		}
	}
	
	public void testMulti() throws Exception{
		List<String> sources = new ArrayList<String>();
		List<String> sourceContents = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		sources.add(rootPath + "\\resource\\IDL\\MyData.idl");
		sources.add(rootPath + "\\resource\\IDL\\Basic5DataTypes.idl");
		
		for(int intidx=0;intidx<sources.size();intidx++) {
			String idlContent = FileUtil.readFile(sources.get(intidx));
			sourceContents.add(idlContent);
		}
			
		results = IDLParamConverter.extractTypeDef(sourceContents);
		
		assertEquals(7, results.size());
		assertTrue(results.contains("RTC::MyData"));
		assertTrue(results.contains("RTC::TimedMyData"));
		assertTrue(results.contains("RTC::Time"));
		assertTrue(results.contains("RTC::TimedState"));
		assertTrue(results.contains("RTC::TimedString"));
		assertTrue(results.contains("RTC::TimedShortSeq"));
		assertTrue(results.contains("RTC::TimedStringSeq"));
	}
	
	public void testMyData() throws Exception{
		List<String> sources = new ArrayList<String>();
		List<String> sourceContents = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		sources.add(rootPath + "\\resource\\IDL\\MyData.idl");
		
		for(int intidx=0;intidx<sources.size();intidx++) {
			String idlContent = FileUtil.readFile(sources.get(intidx));
			sourceContents.add(idlContent);
		}
			
		results = IDLParamConverter.extractTypeDef(sourceContents);

		assertEquals(2, results.size());
		assertTrue(results.contains("RTC::MyData"));
		assertTrue(results.contains("RTC::TimedMyData"));
	}
	
	public void testMyServiceTypeDef() throws Exception{
		List<String> sources = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		List<String> sourceContents = new ArrayList<String>();
		sources.add(rootPath + "\\resource\\IDL\\MyServiceTypeDef.idl");
		
		for(int intidx=0;intidx<sources.size();intidx++) {
			String idlContent = FileUtil.readFile(sources.get(intidx));
			sourceContents.add(idlContent);
		}
		
		results = IDLParamConverter.extractTypeDef(sourceContents);

		assertEquals(2, results.size());
		assertTrue(results.contains("Time"));
		assertTrue(results.contains("TimedState"));
	}
	
	public void testNoModule5Basics() throws Exception{
		List<String> sources = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		List<String> sourceContents = new ArrayList<String>();
		sources.add(rootPath + "\\resource\\IDL\\NoModule5DataTypes.idl");
		
		for(int intidx=0;intidx<sources.size();intidx++) {
			String idlContent = FileUtil.readFile(sources.get(intidx));
			sourceContents.add(idlContent);
		}
		results = IDLParamConverter.extractTypeDef(sourceContents);

		assertEquals(5, results.size());
		assertTrue(results.contains("Time"));
		assertTrue(results.contains("TimedState"));
		assertTrue(results.contains("TimedString"));
		assertTrue(results.contains("TimedShortSeq"));
		assertTrue(results.contains("TimedStringSeq"));
	}

	public void testMyService() throws Exception{
		List<String> sources = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		List<String> sourceContents = new ArrayList<String>();
		sources.add(rootPath + "\\resource\\IDL\\MyService.idl");
		
		for(int intidx=0;intidx<sources.size();intidx++) {
			String idlContent = FileUtil.readFile(sources.get(intidx));
			sourceContents.add(idlContent);
		}
		results = IDLParamConverter.extractTypeDef(sourceContents);
		assertEquals(0, results.size());
	}
	
	public void test5Basics() throws Exception{
		List<String> sources = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		List<String> sourceContents = new ArrayList<String>();
		sources.add(rootPath + "\\resource\\IDL\\Basic5DataTypes.idl");
		
		for(int intidx=0;intidx<sources.size();intidx++) {
			String idlContent = FileUtil.readFile(sources.get(intidx));
			sourceContents.add(idlContent);
		}
		results = IDLParamConverter.extractTypeDef(sourceContents);

		assertEquals(5, results.size());
		assertTrue(results.contains("RTC::Time"));
		assertTrue(results.contains("RTC::TimedState"));
		assertTrue(results.contains("RTC::TimedString"));
		assertTrue(results.contains("RTC::TimedShortSeq"));
		assertTrue(results.contains("RTC::TimedStringSeq"));
	}

	public void testBasicTypesOrg() throws Exception{
		List<String> sources = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		List<String> sourceContents = new ArrayList<String>();
		sources.add(rootPath + "\\resource\\IDL\\BasicDataTypeOrg.idl");
		
		for(int intidx=0;intidx<sources.size();intidx++) {
			String idlContent = FileUtil.readFile(sources.get(intidx));
			sourceContents.add(idlContent);
		}
		results = IDLParamConverter.extractTypeDef(sourceContents);

		assertEquals(22, results.size());
		assertTrue(results.contains("RTC::Time"));
		assertTrue(results.contains("RTC::TimedState"));
		assertTrue(results.contains("RTC::TimedShort"));
		assertTrue(results.contains("RTC::TimedLong"));
		assertTrue(results.contains("RTC::TimedUShort"));
		assertTrue(results.contains("RTC::TimedULong"));
		assertTrue(results.contains("RTC::TimedFloat"));
		assertTrue(results.contains("RTC::TimedDouble"));
		assertTrue(results.contains("RTC::TimedChar"));
		assertTrue(results.contains("RTC::TimedBoolean"));
		assertTrue(results.contains("RTC::TimedOctet"));
		assertTrue(results.contains("RTC::TimedString"));
		assertTrue(results.contains("RTC::TimedShortSeq"));
		assertTrue(results.contains("RTC::TimedLongSeq"));
		assertTrue(results.contains("RTC::TimedUShortSeq"));
		assertTrue(results.contains("RTC::TimedULongSeq"));
		assertTrue(results.contains("RTC::TimedFloatSeq"));
		assertTrue(results.contains("RTC::TimedDoubleSeq"));
		assertTrue(results.contains("RTC::TimedCharSeq"));
		assertTrue(results.contains("RTC::TimedBooleanSeq"));
		assertTrue(results.contains("RTC::TimedOctetSeq"));
		assertTrue(results.contains("RTC::TimedStringSeq"));
	}

	public void testBasicTypes() throws Exception{
		List<String> sources = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		List<String> sourceContents = new ArrayList<String>();
		sources.add(rootPath + "\\resource\\IDL\\BasicDataType.idl");
		
		for(int intidx=0;intidx<sources.size();intidx++) {
			String idlContent = FileUtil.readFile(sources.get(intidx));
			sourceContents.add(idlContent);
		}
		results = IDLParamConverter.extractTypeDef(sourceContents);

		assertEquals(22, results.size());
		assertTrue(results.contains("RTC::Time"));
		assertTrue(results.contains("RTC::TimedState"));
		assertTrue(results.contains("RTC::TimedShort"));
		assertTrue(results.contains("RTC::TimedLong"));
		assertTrue(results.contains("RTC::TimedUShort"));
		assertTrue(results.contains("RTC::TimedULong"));
		assertTrue(results.contains("RTC::TimedFloat"));
		assertTrue(results.contains("RTC::TimedDouble"));
		assertTrue(results.contains("RTC::TimedChar"));
		assertTrue(results.contains("RTC::TimedBoolean"));
		assertTrue(results.contains("RTC::TimedOctet"));
		assertTrue(results.contains("RTC::TimedString"));
		assertTrue(results.contains("RTC::TimedShortSeq"));
		assertTrue(results.contains("RTC::TimedLongSeq"));
		assertTrue(results.contains("RTC::TimedUShortSeq"));
		assertTrue(results.contains("RTC::TimedULongSeq"));
		assertTrue(results.contains("RTC::TimedFloatSeq"));
		assertTrue(results.contains("RTC::TimedDoubleSeq"));
		assertTrue(results.contains("RTC::TimedCharSeq"));
		assertTrue(results.contains("RTC::TimedBooleanSeq"));
		assertTrue(results.contains("RTC::TimedOctetSeq"));
		assertTrue(results.contains("RTC::TimedStringSeq"));
	}
	
	public void testBasic() throws Exception{
		List<String> sources = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		List<String> sourceContents = new ArrayList<String>();
		sources.add(rootPath + "\\resource\\IDL\\Basic.idl");
		
		for(int intidx=0;intidx<sources.size();intidx++) {
			String idlContent = FileUtil.readFile(sources.get(intidx));
			sourceContents.add(idlContent);
		}
		results = IDLParamConverter.extractTypeDef(sourceContents);

		assertEquals(1, results.size());
		assertTrue(results.contains("RTC::Time"));
	}
	
	public void testError() throws Exception{
		List<String> sources = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		List<String> sourceContents = new ArrayList<String>();
		sources.add(rootPath + "\\resource\\IDL\\Error.idl");
		
		for(int intidx=0;intidx<sources.size();intidx++) {
			String idlContent = FileUtil.readFile(sources.get(intidx));
			sourceContents.add(idlContent);
		}
		try {
			results = IDLParamConverter.extractTypeDef(sourceContents);
			fail();
		} catch (Exception ex) {
			System.out.println("Error");
			
		}
	}
	
}
