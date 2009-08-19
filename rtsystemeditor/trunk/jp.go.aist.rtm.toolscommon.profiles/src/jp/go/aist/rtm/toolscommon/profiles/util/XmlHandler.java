package jp.go.aist.rtm.toolscommon.profiles.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import jp.go.aist.rtm.toolscommon.profiles.nl.Messages;

import org.openrtp.namespaces.rtc.version02.ActionStatusDoc;
import org.openrtp.namespaces.rtc.version02.Actions;
import org.openrtp.namespaces.rtc.version02.And;
import org.openrtp.namespaces.rtc.version02.BasicInfoExt;
import org.openrtp.namespaces.rtc.version02.ConfigurationExt;
import org.openrtp.namespaces.rtc.version02.ConfigurationSet;
import org.openrtp.namespaces.rtc.version02.ConstraintHashType;
import org.openrtp.namespaces.rtc.version02.ConstraintListType;
import org.openrtp.namespaces.rtc.version02.ConstraintType;
import org.openrtp.namespaces.rtc.version02.ConstraintUnitType;
import org.openrtp.namespaces.rtc.version02.Dataport;
import org.openrtp.namespaces.rtc.version02.DataportExt;
import org.openrtp.namespaces.rtc.version02.DocAction;
import org.openrtp.namespaces.rtc.version02.DocBasic;
import org.openrtp.namespaces.rtc.version02.DocConfiguration;
import org.openrtp.namespaces.rtc.version02.DocDataport;
import org.openrtp.namespaces.rtc.version02.DocServiceinterface;
import org.openrtp.namespaces.rtc.version02.DocServiceport;
import org.openrtp.namespaces.rtc.version02.LanguageExt;
import org.openrtp.namespaces.rtc.version02.Library;
import org.openrtp.namespaces.rtc.version02.ObjectFactory;
import org.openrtp.namespaces.rtc.version02.Or;
import org.openrtp.namespaces.rtc.version02.Parameter;
import org.openrtp.namespaces.rtc.version02.Position;
import org.openrtp.namespaces.rtc.version02.Property;
import org.openrtp.namespaces.rtc.version02.PropertyIsBetween;
import org.openrtp.namespaces.rtc.version02.PropertyIsEqualTo;
import org.openrtp.namespaces.rtc.version02.PropertyIsGreaterThan;
import org.openrtp.namespaces.rtc.version02.PropertyIsGreaterThanOrEqualTo;
import org.openrtp.namespaces.rtc.version02.PropertyIsLessThan;
import org.openrtp.namespaces.rtc.version02.PropertyIsLessThanOrEqualTo;
import org.openrtp.namespaces.rtc.version02.RtcProfile;
import org.openrtp.namespaces.rtc.version02.ServiceinterfaceExt;
import org.openrtp.namespaces.rtc.version02.Serviceport;
import org.openrtp.namespaces.rtc.version02.ServiceportExt;
import org.openrtp.namespaces.rtc.version02.TargetEnvironment;
import org.openrtp.namespaces.rts.version02.Activation;
import org.openrtp.namespaces.rts.version02.ComponentExt;
import org.openrtp.namespaces.rts.version02.ConditionExt;
import org.openrtp.namespaces.rts.version02.DataportConnectorExt;
import org.openrtp.namespaces.rts.version02.Deactivation;
import org.openrtp.namespaces.rts.version02.ExecutionContextExt;
import org.openrtp.namespaces.rts.version02.Location;
import org.openrtp.namespaces.rts.version02.Preceding;
import org.openrtp.namespaces.rts.version02.Resetting;
import org.openrtp.namespaces.rts.version02.RtsProfile;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;
import org.openrtp.namespaces.rts.version02.ServiceportConnectorExt;
import org.openrtp.namespaces.rts.version02.Shutdown;
import org.openrtp.namespaces.rts.version02.Startup;
import org.openrtp.namespaces.rts.version02.TargetComponentExt;
import org.openrtp.namespaces.rts.version02.TargetExecutioncontext;
import org.openrtp.namespaces.rts.version02.TargetPortExt;
import org.openrtp.namespaces.rts.version02.Waittime;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlHandler {
	
	public RtsProfileExt loadXmlRts(String targetFile) throws Exception {
		
		StringBuffer stbRet = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(new FileInputStream(targetFile), "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		String str = new String();
		while( (str = br.readLine()) != null ){
			stbRet.append(str + "\n");
		}
		br.close();
		isr.close();
		return restoreFromXmlRts(stbRet.toString());
	}

	public RtsProfileExt restoreFromXmlRts(String targetXML) throws Exception {
		RtsProfileExt result = null;
		SAXParserFactory spfactory = SAXParserFactory.newInstance();
		SAXParser parser = spfactory.newSAXParser();
		RtsXMLParser xmlParser = new RtsXMLParser();
		StringReader xmlReader = new StringReader(targetXML);
		parser.parse(new InputSource(xmlReader), xmlParser);
		String targetClass = null;
//		if (xmlParser.version.equals("0.1")) {
		if (xmlParser.version.equals("0.1") || xmlParser.version.equals("1.0")) {
			targetClass = "org.openrtp.namespaces.rts.version01";
		} else if (xmlParser.version.equals("0.2")) {
			targetClass = "org.openrtp.namespaces.rts.version02";
		}
		if (targetClass == null) {
			throw new Exception(Messages.getString("XmlHandler.7"));
		}
		JAXBContext jc = JAXBContext.newInstance(targetClass);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		unmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
		// SAXÇ≈ÇÃâêÕå„Ç…closeÇµÇƒÇµÇ‹Ç§ÇΩÇﬂÅCçƒì«çû
		xmlReader = new StringReader(targetXML);
		Object profile = unmarshaller.unmarshal(xmlReader);
		//
//		if (xmlParser.version.equals("0.1")) {
		if (targetClass.equals("org.openrtp.namespaces.rts.version01")) {
			result = convertRtsProfile01to02(profile);
		} else {
			result = (RtsProfileExt) ((JAXBElement<?>) profile).getValue();
		}
		return result;
	}

	public boolean saveXmlRts(RtsProfile profile, String targetFile) throws Exception {
		String xmlString = convertToXmlRts(profile);

		String lineSeparator = System.getProperty( "line.separator" );
		if( lineSeparator==null || lineSeparator.equals("") ) lineSeparator = "\n";
		String xmlSplit[] = xmlString.split(lineSeparator);

		BufferedWriter outputFile = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(targetFile), "UTF-8"));
		for(int intIdx=0;intIdx<xmlSplit.length;intIdx++) {
			outputFile.write(xmlSplit[intIdx]);
			outputFile.newLine();
		}
		outputFile.close();
		
		return true;
	}
	
	public String convertToXmlRts(RtsProfile profile) throws Exception {
	    String xmlString = "";
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("org.openrtp.namespaces.rts.version02");
			Marshaller marshaller = jaxbContext.createMarshaller();
		    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT , new Boolean(true));
		    marshaller.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper",
	                new NamespacePrefixMapperImpl("http://www.openrtp.org/namespaces/rts"));
		    StringWriter xmlFileWriter = new StringWriter();
		    marshaller.marshal(profile, xmlFileWriter);
		    xmlString = xmlFileWriter.toString();
		} catch (JAXBException exception) {
			throw new Exception(Messages.getString("XmlHandler.17"), exception);
		}
		return xmlString;
	}
	//
	public static boolean validateXmlRtc(String targetString) throws Exception {
		try {
			JAXBContext jc = JAXBContext.newInstance(RtcProfile.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);  
			Schema schema = sf.newSchema(new File("schema/RtcProfile_ext.xsd"));
			unmarshaller.setSchema(schema);

			((JAXBElement<?>)unmarshaller.unmarshal(new StreamSource(new FileReader(targetString)))).getValue();
		} catch (JAXBException e) {
			throw new Exception(Messages.getString("XmlHandler.19"), e);
		} catch (IOException e) {
			throw new Exception(Messages.getString("XmlHandler.20"), e);
		}
		return true;
	}
	//
	//
	public String convertToXmlRtc(RtcProfile profile) throws Exception {
	    String xmlString = "";
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("org.openrtp.namespaces.rtc.version02");
			Marshaller marshaller = jaxbContext.createMarshaller();
		    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT , new Boolean(true));
		    marshaller.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper",
	                new NamespacePrefixMapperImpl("http://www.openrtp.org/namespaces/rtc"));
		    StringWriter xmlFileWriter = new StringWriter();
		    marshaller.marshal(profile, xmlFileWriter);
		    xmlString = xmlFileWriter.toString();
		} catch (JAXBException exception) {
			throw new Exception(Messages.getString("XmlHandler.25"), exception);
		}
		return xmlString;
	}

	public RtcProfile restoreFromXmlRtc(String targetXML) throws Exception {
		RtcProfile result = null;
	    SAXParserFactory spfactory = SAXParserFactory.newInstance();
	    SAXParser parser = spfactory.newSAXParser();
	    RtcXMLParser xmlParser = new RtcXMLParser();
	    StringReader xmlReader = new StringReader(targetXML);
	    parser.parse(new InputSource(xmlReader), xmlParser);
	    String targetClass = null;
	    if( xmlParser.version.equals("0.1") ) {
	    	targetClass = "org.openrtp.namespaces.rtc.version01";
	    } else if( xmlParser.version.equals("0.2") ) {
	    	targetClass = "org.openrtp.namespaces.rtc.version02";
	    }
	    if( targetClass==null ) {
	    	throw new Exception(Messages.getString("XmlHandler.30"));
	    }
	    
	    if( !xmlParser.version.equals("0.1") ) {
	    	targetXML = replacePositionValue(targetXML);
	    }
	    
		JAXBContext jc = JAXBContext.newInstance(targetClass);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		unmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
		//SAXÇ≈ÇÃâêÕå„Ç…closeÇµÇƒÇµÇ‹Ç§ÇΩÇﬂÅCçƒì«çû
	    xmlReader = new StringReader(targetXML);
	    Object profile = ((JAXBElement<?>)unmarshaller.unmarshal(xmlReader)).getValue();
	    //
	    if( xmlParser.version.equals("0.1") ) {
	    	result = convertRtcProfile01to02(profile);
	    } else {
	    	result = (RtcProfile)profile;
	    }

	    return result;
	}
	
	private class RtsXMLParser extends DefaultHandler {
		private String version = "";

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			if(qName.equals("rts:RtsProfile")) {
				for(int intIdx=0;intIdx<attributes.getLength();intIdx++) {
					if( attributes.getQName(intIdx).equals("rts:version") ) {
						version = attributes.getValue(intIdx);
						break;
					}
				}
			}
			super.startElement(uri, localName, qName, attributes);
		}
		
	}
	
	private class RtcXMLParser extends DefaultHandler {
		private String version = "";

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			if(qName.equals("rtc:RtcProfile")) {
				for(int intIdx=0;intIdx<attributes.getLength();intIdx++) {
					if( attributes.getQName(intIdx).equals("rtc:version") ) {
						version = attributes.getValue(intIdx);
						break;
					}
				}
			}
			super.startElement(uri, localName, qName, attributes);
		}
		
	}
	
	private String replacePositionValue(String targetXML) {
		// ãåversion02Ç©ÇÁêVversion02ÇÃç∑ï™Çãzé˚Ç∑ÇÈÇΩÇﬂÇÃíuä∑
		Pattern patternLeft = Pattern.compile("rtcExt:position=\"left\"");
		Matcher matcherLeft = patternLeft.matcher(targetXML);
		targetXML = matcherLeft.replaceAll("rtcExt:position=\"LEFT\"");
		
		Pattern patternRight = Pattern.compile("rtcExt:position=\"right\"");
		Matcher matcherRight = patternRight.matcher(targetXML);
		targetXML = matcherRight.replaceAll("rtcExt:position=\"RIGHT\"");
	
		Pattern patternTop = Pattern.compile("rtcExt:position=\"top\"");
		Matcher matcherTop = patternTop.matcher(targetXML);
		targetXML = matcherTop.replaceAll("rtcExt:position=\"TOP\"");
	
		Pattern patternBottom = Pattern.compile("rtcExt:position=\"bottom\"");
		Matcher matcherBottom = patternBottom.matcher(targetXML);
		targetXML = matcherBottom.replaceAll("rtcExt:position=\"BOTTOM\"");
		
		return targetXML;
	}
	
	public static String restoreConstraint(ConstraintType source) throws Exception {
		if( source==null ) 	throw new Exception(Messages.getString("XmlHandler.38"));

		StringBuffer result = new StringBuffer();
		//
		//
		ConstraintHashType hash = source.getConstraintHashType();
		if( hash!=null ) {
			result.append("{");
			for(ConstraintType type : hash.getConstraint()) {
				result.append(type.getConstraintUnitType().getKey());
				result.append(":");
				result.append(restoreConstraint(type));
				result.append(",");
			}
			if( result.length() > 0 ) result.deleteCharAt(result.length()-1);
			result.append("}");
			return result.toString();
		}
		//
		//
		ConstraintListType list = source.getConstraintListType();
		if( list!=null ) {
			for(ConstraintType type : list.getConstraint()) {
				result.append(restoreConstraint(type));
				result.append(",");
			}
			if( result.length() > 0 ) result.deleteCharAt(result.length()-1);
			return result.toString();
		}
		//
		//
		ConstraintUnitType unit = source.getConstraintUnitType();
		if( unit==null ) throw new Exception(Messages.getString("XmlHandler.44"));
		////
		PropertyIsEqualTo equal = unit.getPropertyIsEqualTo();
		if( equal!=null ) {
			if( equal.getLiteral()==null || equal.getLiteral().length()<=0 ) 
				throw new Exception(Messages.getString("XmlHandler.45"));
			result.append(equal.getLiteral());
		}
		////
		PropertyIsGreaterThanOrEqualTo greaterEq = unit.getPropertyIsGreaterThanOrEqualTo();
		if( greaterEq!=null ) {
			if( greaterEq.getLiteral()==null || greaterEq.getLiteral().length()<=0 ) 
				throw new Exception(Messages.getString("XmlHandler.46"));
			result.append("x>=" + greaterEq.getLiteral());
		}
		////
		PropertyIsLessThanOrEqualTo lessEq = unit.getPropertyIsLessThanOrEqualTo();
		if( lessEq!=null ) {
			if( lessEq.getLiteral()==null || lessEq.getLiteral().length()<=0 ) 
				throw new Exception(Messages.getString("XmlHandler.48"));
			result.append("x<=" + lessEq.getLiteral());
		}
		////
		PropertyIsGreaterThan greater = unit.getPropertyIsGreaterThan();
		if( greater!=null ) {
			if( greater.getLiteral()==null || greater.getLiteral().length()<=0 ) 
				throw new Exception(Messages.getString("XmlHandler.50"));
			result.append("x>" + greater.getLiteral());
		}
		////
		PropertyIsLessThan less = unit.getPropertyIsLessThan();
		if( less!=null ) {
			if( less.getLiteral()==null || less.getLiteral().length()<=0 )
				throw new Exception(Messages.getString("XmlHandler.52"));
			result.append("x<" + less.getLiteral());
		}
		////
		PropertyIsBetween between = unit.getPropertyIsBetween();
		if( between!=null ) {
			if( between.getLowerBoundary()==null || between.getLowerBoundary().length()<=0 )
				throw new Exception(Messages.getString("XmlHandler.54"));
			if( between.getUpperBoundary()==null || between.getUpperBoundary().length()<=0 )
				throw new Exception(Messages.getString("XmlHandler.55"));
			result.append(between.getLowerBoundary() + "<=x<=" + between.getUpperBoundary());
		}
		//
		//
		Or or = unit.getOr();
		if( or!=null ) {
			result.append("(");
			for(ConstraintType type : or.getConstraint() ) {
				result.append(restoreConstraint(type));
				result.append(",");
			}
			if( result.length()>0 ) result.deleteCharAt(result.length()-1);
			result.append(")");
		}
		//
		//
		And and = unit.getAnd();
		if( and!=null ) {
			//AndèåèÇ≈ÇRÇ¬à»è„ÇÃóvëfÇÕÇ»Ç¢
			if( and.getConstraint().size() > 2 ) throw new Exception(Messages.getString("XmlHandler.60"));
			
			ConstraintUnitType former = and.getConstraint().get(0).getConstraintUnitType();
			ConstraintUnitType latter = and.getConstraint().get(1).getConstraintUnitType();

			if( former.getPropertyIsGreaterThan() != null || former.getPropertyIsGreaterThanOrEqualTo() != null ) {
				if( former.getPropertyIsGreaterThan() != null ) {
					result.append(former.getPropertyIsGreaterThan().getLiteral());
					result.append("<x");
				} else	if( former.getPropertyIsGreaterThanOrEqualTo() != null ) {
					result.append(former.getPropertyIsGreaterThanOrEqualTo().getLiteral());
					result.append("<=x");
				}
				//
				if( latter.getPropertyIsLessThan() != null ) {
					result.append("<");
					result.append(latter.getPropertyIsLessThan().getLiteral());
				} else if( latter.getPropertyIsLessThanOrEqualTo() != null ) {
					result.append("<=");
					result.append(latter.getPropertyIsLessThanOrEqualTo().getLiteral());
				}
			} else if( latter.getPropertyIsGreaterThan() != null || latter.getPropertyIsGreaterThanOrEqualTo() != null ) {
				if( latter.getPropertyIsGreaterThan() != null ) {
					result.append(latter.getPropertyIsGreaterThan().getLiteral());
					result.append("<x");
				} else	if( latter.getPropertyIsGreaterThanOrEqualTo() != null ) {
					result.append(latter.getPropertyIsGreaterThanOrEqualTo().getLiteral());
					result.append("<=x");
				}
				//
				if( former.getPropertyIsLessThan() != null ) {
					result.append("<");
					result.append(former.getPropertyIsLessThan().getLiteral());
				} else if( former.getPropertyIsLessThanOrEqualTo() != null ) {
					result.append("<=");
					result.append(former.getPropertyIsLessThanOrEqualTo().getLiteral());
				}
			}
		}

		return result.toString();
	}
	
	public static ConstraintType convertToXmlConstraint(String source) throws Exception {
		if(source==null || source.length()==0 ) throw new Exception(Messages.getString("XmlHandler.69"));
		
		ObjectFactory factory = new ObjectFactory();
		ConstraintType result = factory.createConstraintType();
		
		ConstraintUnitType unit = factory.createConstraintUnitType();
		
		if(source.trim().startsWith("(") && source.trim().endsWith(")")) {
			String body = source.trim().substring(1,source.trim().length()-1);
			String[] elem = body.trim().split(",");
			if( elem.length<=0 ) throw new Exception(Messages.getString("XmlHandler.73"));
			Or prop = factory.createOr();
			for(int index=0;index<elem.length;index++) {
				prop.getConstraint().add(convertToXmlConstraint(elem[index]));
			}
			unit.setOr(prop);

		} else 	if(source.trim().startsWith("{") && source.trim().endsWith("}")) {
			String body = source.trim().substring(1,source.trim().length()-1);
			String[] elem = body.trim().split(",");
			if( elem.length<=0 ) throw new Exception(Messages.getString("XmlHandler.77"));
			ConstraintHashType list = factory.createConstraintHashType();
			for(int index=0;index<elem.length;index++) {
				int indsep = elem[index].trim().indexOf(":");
				if(indsep<0) throw new Exception(Messages.getString("XmlHandler.79"));
				String key = elem[index].trim().substring(0,indsep);
				if(key.length()<=0) throw new Exception(Messages.getString("XmlHandler.79"));
				String value = elem[index].trim().substring(indsep+1);
				ConstraintType hashConst = convertToXmlConstraint(value);
				hashConst.getConstraintUnitType().setKey(key);
				list.getConstraint().add(hashConst);
			}
			result.setConstraintHashType(list);
			return result;
			
		} else 	if( source.contains(",") ) {
			String[] elem = source.trim().split(",");
			if( elem.length<=0 ) throw new Exception(Messages.getString("XmlHandler.82"));
			ConstraintListType list = factory.createConstraintListType();
			for(int index=0;index<elem.length;index++) {
				list.getConstraint().add(convertToXmlConstraint(elem[index]));
			}
			result.setConstraintListType(list);
			return result;
			
		} else 	if( source.contains("=") || source.contains("<") || source.contains(">") ) {
			if(source.trim().startsWith("x") || source.trim().endsWith("x")) {
				String body = "";
				if( source.trim().startsWith("x>=") || source.trim().endsWith("<=x") ) {
					if(source.trim().startsWith("x>=")) {
						body = source.trim().substring("x>=".length());
					} else if(source.trim().endsWith("<=x")) {
						body = source.trim().substring(0,source.trim().length()-"x>=".length());
					}
					if(body.length()==0 || !body.matches("^[-,+]?[0-9.]*"))
						throw new Exception(Messages.getString("XmlHandler.96"));
					PropertyIsGreaterThanOrEqualTo prop = factory.createPropertyIsGreaterThanOrEqualTo();
					prop.setLiteral(body);
					unit.setPropertyIsGreaterThanOrEqualTo(prop);
					
				} else if( source.trim().startsWith("x>") || source.trim().endsWith("<x") ) {
					if(source.trim().startsWith("x>")) {
						body = source.trim().substring("x>".length());
					} else if(source.trim().endsWith("<x")) {
						body = source.trim().substring(0,source.trim().length()-"<x".length());
					}
					if(body.length()==0 || !body.matches("^[-,+]?[0-9.]*"))
						throw new Exception(Messages.getString("XmlHandler.104"));
					PropertyIsGreaterThan prop = factory.createPropertyIsGreaterThan();
					prop.setLiteral(body);
					unit.setPropertyIsGreaterThan(prop);
					
				} else if( source.trim().startsWith("x<=") || source.trim().endsWith("=>x") ) {
					if(source.trim().startsWith("x<=")) {
						body = source.trim().substring("x<=".length());
					} else if(source.trim().endsWith("=>x")) {
						body = source.trim().substring(0,source.trim().length()-"=>x".length());
					}
					if(body.length()==0 || !body.matches("^[-,+]?[0-9.]*"))
						throw new Exception(Messages.getString("XmlHandler.112"));
					PropertyIsLessThanOrEqualTo prop = factory.createPropertyIsLessThanOrEqualTo();
					prop.setLiteral(body);
					unit.setPropertyIsLessThanOrEqualTo(prop);

				} else if( source.trim().startsWith("x<") || source.trim().endsWith(">x") ) {
					if(source.trim().startsWith("x<")) {
						body = source.trim().substring("x<".length());
					} else if(source.trim().endsWith(">x")) {
						body = source.trim().substring(0,source.trim().length()-">x".length());
					}
					if(body.length()==0 || !body.matches("^[-,+]?[0-9.]*"))
						throw new Exception(Messages.getString("XmlHandler.120"));
					PropertyIsLessThan prop = factory.createPropertyIsLessThan();
					prop.setLiteral(body);
					unit.setPropertyIsLessThan(prop);
				} else {
					throw new Exception(Messages.getString("XmlHandler.121"));
				}
			} else	if( source.trim().contains("<=x<=") || source.trim().contains("=>x=>") ) {
				int index = -1;
				String lower = "";
				String upper = "";
				if( source.trim().contains("<=x<=") ) {
					index = source.trim().indexOf("<=x<=");
					lower = source.trim().substring(0,index);
					upper = source.trim().substring(index+"<=x<=".length());
				} else 	if( source.trim().contains("=>x=>") ) {
					index = source.trim().indexOf("=>x=>");
					upper = source.trim().substring(0,index);
					lower = source.trim().substring(index+"=>x=>".length());
				}
				if(lower.length()==0 || !lower.matches("^[-,+]?[0-9.]*") ||
						upper.length()==0 || !upper.matches("^[-,+]?[0-9.]*") ||
						Double.valueOf(lower).doubleValue() > Double.valueOf(upper).doubleValue()) {
					throw new Exception(Messages.getString("XmlHandler.134"));
				}
				PropertyIsBetween prop = factory.createPropertyIsBetween();
				prop.setLowerBoundary(lower);
				prop.setUpperBoundary(upper);
				unit.setPropertyIsBetween(prop);
					
			} else	if( source.trim().contains("x") ) {
				int index = source.trim().indexOf("x");
				String former = source.trim().substring(0,index+1);
				String latter = source.trim().substring(index);
				And prop = factory.createAnd();
				prop.getConstraint().add(convertToXmlConstraint(former));
				prop.getConstraint().add(convertToXmlConstraint(latter));
				unit.setAnd(prop);
				
			} else {
				throw new Exception(Messages.getString("XmlHandler.137"));
			}

		} else {
			//=
			PropertyIsEqualTo equal = factory.createPropertyIsEqualTo();
			equal.setLiteral(source);
			unit.setPropertyIsEqualTo(equal);
		}
		result.setConstraintUnitType(unit);
		
		return result;
	}
	
	private RtsProfileExt convertRtsProfile01to02(Object source) {
		RtsProfileExt result02 = null;
		org.openrtp.namespaces.rts.version01.RtsProfile profile01 = (org.openrtp.namespaces.rts.version01.RtsProfile)source;
		if( source==null ) return result02;
		org.openrtp.namespaces.rts.version02.ObjectFactory factory = new org.openrtp.namespaces.rts.version02.ObjectFactory();
		//RtsProfile
		result02 = factory.createRtsProfileExt();
//		result02.setId(convertId01to02(profile01.getId()));
		result02.setId(convertRtsId01to02(profile01.getId()));
		result02.setAbstract(profile01.getAbstract());
		result02.setVersion("0.2");
		result02.setCreationDate(profile01.getCreationDate());
		result02.setUpdateDate(profile01.getUpdateDate());
		//Ext Profile
		if( profile01 instanceof org.openrtp.namespaces.rts.version01.RtsProfileExt) {
			result02.getVersionUpLogs().addAll(((org.openrtp.namespaces.rts.version01.RtsProfileExt)profile01).getVersionUpLog());
			List<org.openrtp.namespaces.rts.version01.Property> properties01 = ((org.openrtp.namespaces.rts.version01.RtsProfileExt)profile01).getProperties();
			if( properties01!=null ) {
				result02.getProperties().addAll(convertRtsProperty01to02(factory, properties01));
			}
		}
		//
		//Component
		List<org.openrtp.namespaces.rts.version01.Component> comps01 = profile01.getComponent();
		for( org.openrtp.namespaces.rts.version01.Component comp01 : comps01 ) {
			ComponentExt comp02 = factory.createComponentExt();
			comp02.setId(comp01.getId());
			comp02.setPathUri(comp01.getPathUri());
			comp02.setActiveConfigurationSet(comp01.getActiveConfigurationSet());
			comp02.setInstanceName(comp01.getInstanceName());
			comp02.setCompositeType(covertCompositeType01to02(comp01.getCompositeType()));
			comp02.setIsRequired(comp01.isIsRequired());
			result02.getComponents().add(comp02);
			//Ext Profile
			if( comp01 instanceof org.openrtp.namespaces.rts.version01.ComponentExt) {
				org.openrtp.namespaces.rts.version01.ComponentExt comp01ex = (org.openrtp.namespaces.rts.version01.ComponentExt)comp01;
				Location location02 = factory.createLocation();
				location02.setX(comp01ex.getLocation().getX());
				location02.setY(comp01ex.getLocation().getY());
				location02.setHeight(comp01ex.getLocation().getHeight());
				location02.setWidth(comp01ex.getLocation().getWidth());
				location02.setDirection(comp01ex.getLocation().getDirection());
				comp02.setLocation(location02);
				List<org.openrtp.namespaces.rts.version01.Property> properties01 = comp01ex.getProperties();
				if( properties01!=null ) {
					List<org.openrtp.namespaces.rts.version02.Property> properties02 = new ArrayList<org.openrtp.namespaces.rts.version02.Property>();
					for( org.openrtp.namespaces.rts.version01.Property property01 : properties01 ) {
						//
						if( property01.getName().equals("Shutdown")) {
							//Shutdown
							Shutdown shut02 = factory.createShutdown();
							shut02.getTargets().add(setCondition("Shutdown", factory, properties01, property01));
						} else if( property01.getName().equals("Activate")) {
							//Activate
							Activation activ02 = factory.createActivation();
							activ02.getTargets().add(setCondition("Activate", factory, properties01, property01));
						} else if( property01.getName().equals("Deactivate")) {
							//Deactivate
							Deactivation deactiv02 = factory.createDeactivation();
							deactiv02.getTargets().add(setCondition("Deactivate", factory, properties01, property01));
						} else if( property01.getName().equals("Resetting")) {
							//Resetting
							Resetting reset02 = factory.createResetting();
							reset02.getTargets().add(setCondition("Resetting", factory, properties01, property01));
						} else {
							org.openrtp.namespaces.rts.version02.Property property = factory.createProperty();
							property.setName(property01.getName());
							property.setValue(property01.getValue());
							properties02.add(property);
						}
					}
					comp02.getProperties().addAll(properties02);
				}
				//
				//ConfigurationSet
				List<org.openrtp.namespaces.rts.version01.ConfigurationSet> configSets01 = comp01.getConfigurationSets();
				if( configSets01!=null) {
					for( org.openrtp.namespaces.rts.version01.ConfigurationSet configSet01 : configSets01 ) {
						org.openrtp.namespaces.rts.version02.ConfigurationSet configSet02 = factory.createConfigurationSet();
						configSet02.setId(configSet01.getId());
						comp02.getConfigurationSets().add(configSet02);
						//ConfigurationData
						List<org.openrtp.namespaces.rts.version01.ConfigurationData> configs01 = configSet01.getConfigurationData();
						for( org.openrtp.namespaces.rts.version01.ConfigurationData config01 : configs01 ) {
							org.openrtp.namespaces.rts.version02.ConfigurationData config02 = factory.createConfigurationData();
							config02.setName(config01.getName());
							config02.setData(config01.getData());
							configSet02.getConfigurationData().add(config02);
						}
					}
				}
				//
				//DataPort
				List<org.openrtp.namespaces.rts.version01.Dataport> dataPorts01 = comp01.getDataPorts();
				if( dataPorts01!=null) {
					for( org.openrtp.namespaces.rts.version01.Dataport dataPort01 : dataPorts01 ) {
						org.openrtp.namespaces.rts.version02.DataportExt dataPort02 = factory.createDataportExt();
						dataPort02.setName(dataPort01.getName());
						comp02.getDataPorts().add(dataPort02);
						//DataPortConnector
						List<org.openrtp.namespaces.rts.version01.DataportConnector> connectors01 = dataPort01.getDataPortConnectors();
						if( connectors01!=null ) {
							for( org.openrtp.namespaces.rts.version01.DataportConnector connector01 : connectors01 ) {
								DataportConnectorExt connector02 = factory.createDataportConnectorExt();
								connector02.setConnectorId(connector01.getConnectorId());
								connector02.setName(connector01.getName());
								connector02.setDataType(connector01.getDataType());
								connector02.setInterfaceType(connector01.getInterfaceType());
								connector02.setDataflowType(connector01.getDataflowType());
								connector02.setSubscriptionType(connector01.getSubscriptionType());
								connector02.setPushInterval(connector01.getPushInterval());
								//
								TargetPortExt source02 = factory.createTargetPortExt();
								source02.setComponentId(comp01ex.getId());
								source02.setInstanceName(comp01ex.getInstanceName());
								source02.setPortName(dataPort01.getName());
								connector02.setSourceDataPort(source02);
								//
								TargetPortExt target02 = factory.createTargetPortExt();
								target02.setComponentId(connector01.getTargetDataPort().getComponentId());
								target02.setInstanceName(connector01.getTargetDataPort().getInstanceName());
								target02.setPortName(connector01.getTargetDataPort().getPortName());
								connector02.setTargetDataPort(target02);
								//
								if(connector01.getTargetDataPort() instanceof org.openrtp.namespaces.rts.version01.TargetPortExt) {
									org.openrtp.namespaces.rts.version01.TargetPortExt targetPort01Ex = (org.openrtp.namespaces.rts.version01.TargetPortExt)connector01.getTargetDataPort();
									List<org.openrtp.namespaces.rts.version01.Property> portProperties01 = targetPort01Ex.getProperties();
									if( portProperties01!=null ) {
										target02.getProperties().addAll(convertRtsProperty01to02(factory, portProperties01));
									}
								}
								result02.getDataPortConnectors().add(connector02);
								//
								if(connector01 instanceof org.openrtp.namespaces.rts.version01.DataportConnectorExt) {
									org.openrtp.namespaces.rts.version01.DataportConnectorExt connector01Ex = (org.openrtp.namespaces.rts.version01.DataportConnectorExt)connector01;
									List<org.openrtp.namespaces.rts.version01.Property> portProperties01 = connector01Ex.getProperties();
									if( portProperties01!=null ) {
										connector02.getProperties().addAll(convertRtsProperty01to02(factory, portProperties01));
									}
								}									
							}
						}
					}
				}
				//
				//ServicePort
				List<org.openrtp.namespaces.rts.version01.Serviceport> servicePorts01 = comp01.getServicePorts();
				if( servicePorts01!=null) {
					for( org.openrtp.namespaces.rts.version01.Serviceport servicePort01 : servicePorts01 ) {
						org.openrtp.namespaces.rts.version02.ServiceportExt servicePort02 = factory.createServiceportExt();
						servicePort02.setName(servicePort01.getName());
						comp02.getServicePorts().add(servicePort02);
						//ServicePortConnector
						List<org.openrtp.namespaces.rts.version01.ServiceportConnector> connectors01 = servicePort01.getServicePortConnectors();
						if( connectors01!=null ) {
							for( org.openrtp.namespaces.rts.version01.ServiceportConnector connector01 : connectors01 ) {
								ServiceportConnectorExt connector02 = factory.createServiceportConnectorExt();
								connector02.setConnectorId(connector01.getConnectorId());
								connector02.setName(connector01.getName());
								//
								TargetPortExt source02 = factory.createTargetPortExt();
								source02.setComponentId(comp01ex.getId());
								source02.setInstanceName(comp01ex.getInstanceName());
								source02.setPortName(servicePort01.getName());
								connector02.setSourceServicePort(source02);
								//
								TargetPortExt target02 = factory.createTargetPortExt();
								target02.setComponentId(connector01.getTargetServicePort().getComponentId());
								target02.setInstanceName(connector01.getTargetServicePort().getInstanceName());
								target02.setPortName(connector01.getTargetServicePort().getPortName());
								connector02.setTargetServicePort(target02);
								result02.getServicePortConnectors().add(connector02);
								//
								if(connector01.getTargetServicePort() instanceof org.openrtp.namespaces.rts.version01.TargetPortExt) {
									org.openrtp.namespaces.rts.version01.TargetPortExt targetPort01Ex = (org.openrtp.namespaces.rts.version01.TargetPortExt)connector01.getTargetServicePort();
									List<org.openrtp.namespaces.rts.version01.Property> portProperties01 = targetPort01Ex.getProperties();
									if( portProperties01!=null ) {
										target02.getProperties().addAll(convertRtsProperty01to02(factory, portProperties01));
									}
								}
								//
								if(connector01 instanceof org.openrtp.namespaces.rts.version01.ServiceportConnectorExt) {
									org.openrtp.namespaces.rts.version01.ServiceportConnectorExt connector01Ex = (org.openrtp.namespaces.rts.version01.ServiceportConnectorExt)connector01;
									List<org.openrtp.namespaces.rts.version01.Property> portProperties01 = connector01Ex.getProperties();
									if( portProperties01!=null ) {
										connector02.getProperties().addAll(convertRtsProperty01to02(factory, portProperties01));
									}
								}									
							}
						}
					}
				}
				//
				//ExecutionContext
				List<org.openrtp.namespaces.rts.version01.ExecutionContext> ecList01 = comp01.getExecutionContexts();
				if( ecList01!=null) {
					for( org.openrtp.namespaces.rts.version01.ExecutionContext ec01 : ecList01 ) {
						ExecutionContextExt ec02 = factory.createExecutionContextExt();
						ec02.setKind(ec01.getKind());
						ec02.setRate(ec01.getRate());
						comp02.getExecutionContexts().add(ec02);
						//Participant
						List<org.openrtp.namespaces.rts.version01.TargetComponent> particip01List = ec01.getParticipants();
						if( particip01List!=null ) {
							for( org.openrtp.namespaces.rts.version01.TargetComponent particip01 : particip01List ) {
								TargetComponentExt target02 = factory.createTargetComponentExt();
								target02.setComponentId(particip01.getComponentId());
								target02.setInstanceName(particip01.getInstanceName());
								ec02.getParticipants().add(target02);
								//
								if(particip01 instanceof org.openrtp.namespaces.rts.version01.TargetComponentExt) {
									org.openrtp.namespaces.rts.version01.TargetComponentExt particip01Ex = (org.openrtp.namespaces.rts.version01.TargetComponentExt)particip01;
									List<org.openrtp.namespaces.rts.version01.Property> partProperties01 = particip01Ex.getProperties();
									if( partProperties01!=null ) {
										target02.getProperties().addAll(convertRtsProperty01to02(factory, partProperties01));
									}
								}
							}
						}
						//
						if(ec01 instanceof org.openrtp.namespaces.rts.version01.ExecutionContextExt) {
							org.openrtp.namespaces.rts.version01.ExecutionContextExt ec01Ex = (org.openrtp.namespaces.rts.version01.ExecutionContextExt)ec01;
							List<org.openrtp.namespaces.rts.version01.Property> ecProperties01 = ec01Ex.getProperties();
							if( ecProperties01!=null ) {
								ec02.getProperties().addAll(convertRtsProperty01to02(factory, ecProperties01));
							}
						}
					}
				}
				//
				//StartUp
				org.openrtp.namespaces.rts.version01.Startup start01 = comp01.getStartUp();
				if( start01!=null ) {
					Startup start02 = factory.createStartup();
					ConditionExt cond02 = factory.createConditionExt();
					cond02.setSequence(start01.getSequence());
					start02.getTargets().add(cond02);
					List<org.openrtp.namespaces.rts.version01.Condition> cond01List = start01.getCondition();
					for(org.openrtp.namespaces.rts.version01.Condition cond01 : cond01List) {
						if( cond01.getWaitTime()!=null ) {
							//WaitTime
							Waittime wait02 = factory.createWaittime();
							wait02.setWaitTime(BigInteger.valueOf(cond01.getWaitTime().getSeconds()));
							cond02.setWaitTime(wait02);
						} else if( cond01.getPreceding()!=null ) {
							//Preceding
							Preceding pred02 = factory.createPreceding();
							for( org.openrtp.namespaces.rts.version01.TargetComponent target01 : cond01.getPreceding().getPrecedingComponent() ) {
								TargetExecutioncontext target02 = factory.createTargetExecutioncontext();
								target02.setComponentId(target01.getComponentId());
								target02.setInstanceName(target01.getInstanceName());
								pred02.getPrecedingComponents().add(target02);
							}
							cond02.setPreceding(pred02);
						}
					}
				}
			}
		}
		return result02;
	}

	private String covertCompositeType01to02(String compositeType) {
		if (compositeType == null) return "None";
		if (compositeType.equals("AllShared")) return "PeriodicStateShared";
		if (compositeType.equals("ECShared")) return "PeriodicECShared";
		if (compositeType.equals("NonShared")) return "Grouping";
		return "None";
	}

	private String convertRtsId01to02(String ver01Id) {
		String[] element = ver01Id.split(":");
		String result = null;
		if (element.length >= 3) {
			String[] items = element[1].split("\\.");
			StringBuffer vendor = new StringBuffer();
			for (int index = 0; index < items.length - 1; index++) {
				vendor.append(items[index]);
			}
			result = element[0] + ":" + vendor.toString() + ":"
					+ items[items.length - 1] + ":" + element[2];
		}
		return result;
	}

	private List<org.openrtp.namespaces.rts.version02.Property> convertRtsProperty01to02(org.openrtp.namespaces.rts.version02.ObjectFactory factory, List<org.openrtp.namespaces.rts.version01.Property> properties01) {
		List<org.openrtp.namespaces.rts.version02.Property> properties02 = new ArrayList<org.openrtp.namespaces.rts.version02.Property>();
		for( org.openrtp.namespaces.rts.version01.Property property01 : properties01 ) {
			org.openrtp.namespaces.rts.version02.Property property = factory.createProperty();
			property.setName(property01.getName());
			property.setValue(property01.getValue());
			properties02.add(property);
		}
		return properties02;
	}

	private ConditionExt setCondition(String type, org.openrtp.namespaces.rts.version02.ObjectFactory factory, List<org.openrtp.namespaces.rts.version01.Property> properties01, org.openrtp.namespaces.rts.version01.Property property01) {
		ConditionExt cond02 = factory.createConditionExt();
		cond02.setSequence(BigInteger.valueOf(Integer.parseInt(property01.getValue())));
		if( getPropertyValue(type + "_Waittime", properties01)!=null ) {
			//WaitTime
			Waittime wait02 = factory.createWaittime();
			wait02.setWaitTime(BigInteger.valueOf(Integer.parseInt(getPropertyValue(type + "_Waittime", properties01))));
			cond02.setWaitTime(wait02);
		} else if( getPropertyValue(type + "_Preceding", properties01)!=null ) {
			//Preceding
			Preceding pred02 = factory.createPreceding();
			TargetExecutioncontext target02 = factory.createTargetExecutioncontext();
			target02.setComponentId(getPropertyValue(type + "_Preceding", properties01));
			pred02.getPrecedingComponents().add(target02);
			cond02.setPreceding(pred02);
		}
		return cond02;
	}
	
	private String getPropertyValue(String targetKey, List<org.openrtp.namespaces.rts.version01.Property> propList) {
		for(org.openrtp.namespaces.rts.version01.Property prop : propList) {
			if( prop.getName().equals(targetKey) )
				return prop.getValue();
		}
		return null;
		
	}
	//
	private RtcProfile convertRtcProfile01to02(Object source) {
		RtcProfile result02 = null;
		org.openrtp.namespaces.rtc.version01.RtcProfile profile01 = (org.openrtp.namespaces.rtc.version01.RtcProfile)source;
		if( source==null ) return result02;
		ObjectFactory factory = new ObjectFactory();
		//BasicInfo
		result02 = factory.createRtcProfile();
		org.openrtp.namespaces.rtc.version01.BasicInfo basic01 = profile01.getBasicInfo();
		result02.setVersion("0.2");
		result02.setId(convertId01to02(profile01.getId()));
		if( basic01!=null ) {
			BasicInfoExt basic02 = factory.createBasicInfoExt();
			//Basic Profile
			basic02.setName(basic01.getName());
			basic02.setComponentType(basic01.getComponentType());
			basic02.setActivityType(basic01.getActivityType());
			basic02.setComponentKind(basic01.getComponentKind());
			basic02.setCategory(basic01.getCategory());
			basic02.setDescription(basic01.getDescription());
			basic02.setExecutionRate(basic01.getExecutionRate());
			basic02.setExecutionType(basic01.getExecutionType());
			basic02.setMaxInstances(basic01.getMaxInstances());
			basic02.setVendor(basic01.getVendor());
			basic02.setVersion(basic01.getVersion());
			basic02.setAbstract(basic01.getAbstract());
			basic02.setCreationDate(basic01.getCreationDate());
			basic02.setUpdateDate(basic01.getUpdateDate());
			//Doc Profile
			if( basic01 instanceof org.openrtp.namespaces.rtc.version01.BasicInfoDoc) {
				org.openrtp.namespaces.rtc.version01.DocBasic doc01 = ((org.openrtp.namespaces.rtc.version01.BasicInfoDoc)basic01).getDoc();
				if( doc01!=null ) {
					DocBasic doc02 = factory.createDocBasic();
					doc02.setDescription(doc01.getDescription());
					doc02.setInout(doc01.getInout());
					doc02.setAlgorithm(doc01.getAlgorithm());
					doc02.setCreator(doc01.getCreator());
					doc02.setLicense(doc01.getLicense());
					doc02.setReference(doc01.getReference());
					basic02.setDoc(doc02);
				}
				//Ext Profile
				if( basic01 instanceof org.openrtp.namespaces.rtc.version01.BasicInfoExt) {
					basic02.getVersionUpLogs().addAll(((org.openrtp.namespaces.rtc.version01.BasicInfoExt)basic01).getVersionUpLog());
					List<org.openrtp.namespaces.rtc.version01.Property> properties01 = ((org.openrtp.namespaces.rtc.version01.BasicInfoExt)basic01).getProperties();
					if( properties01!=null ) {
						List<Property> properties02 = new ArrayList<Property>();
						for( org.openrtp.namespaces.rtc.version01.Property property01 : properties01 ) {
							if( property01.getName().equals("RTCType")) {
								basic02.setRtcType(property01.getValue());
							} else {
								Property property = factory.createProperty();
								property.setName(property01.getName());
								property.setValue(property01.getValue());
								properties02.add(property);
							}
						}
						basic02.getProperties().addAll(properties02);
					}
				}
			}
			result02.setBasicInfo(basic02);
		}
		//Actions
		org.openrtp.namespaces.rtc.version01.Actions actions01 = profile01.getActions();
		if( actions01!=null ) {
			Actions actions02 = factory.createActions();
			//Initialize
			org.openrtp.namespaces.rtc.version01.ActionStatus action01 = actions01.getOnInitialize();
			ActionStatusDoc action02 = convertAction01to02(factory, actions02, action01);
			actions02.setOnInitialize(action02);
			//Finalize
			action01 = actions01.getOnFinalize();
			action02 = convertAction01to02(factory, actions02, action01);
			actions02.setOnFinalize(action02);
			//Startup
			action01 = actions01.getOnStartup();
			action02 = convertAction01to02(factory, actions02, action01);
			actions02.setOnStartup(action02);
			//Shutdown
			action01 = actions01.getOnShutdown();
			action02 = convertAction01to02(factory, actions02, action01);
			actions02.setOnShutdown(action02);
			//Activated
			action01 = actions01.getOnActivated();
			action02 = convertAction01to02(factory, actions02, action01);
			actions02.setOnActivated(action02);
			//Deactivated
			action01 = actions01.getOnDeactivated();
			action02 = convertAction01to02(factory, actions02, action01);
			actions02.setOnDeactivated(action02);
			//Aborting
			action01 = actions01.getOnAborting();
			action02 = convertAction01to02(factory, actions02, action01);
			actions02.setOnAborting(action02);
			//Error
			action01 = actions01.getOnError();
			action02 = convertAction01to02(factory, actions02, action01);
			actions02.setOnError(action02);
			//Reset
			action01 = actions01.getOnReset();
			action02 = convertAction01to02(factory, actions02, action01);
			actions02.setOnReset(action02);
			//Execute
			action01 = actions01.getOnExecute();
			action02 = convertAction01to02(factory, actions02, action01);
			actions02.setOnExecute(action02);
			//StateUpdate
			action01 = actions01.getOnStateUpdate();
			action02 = convertAction01to02(factory, actions02, action01);
			actions02.setOnStateUpdate(action02);
			//RateChanged
			action01 = actions01.getOnRateChanged();
			action02 = convertAction01to02(factory, actions02, action01);
			actions02.setOnRateChanged(action02);
			//
			result02.setActions(actions02);
		}
		//Configuration
		org.openrtp.namespaces.rtc.version01.ConfigurationSet configset01 = profile01.getConfigurationSet();
		if( configset01!=null ) {
			ConfigurationSet configset02 = factory.createConfigurationSet();
			List<org.openrtp.namespaces.rtc.version01.Configuration> configList01 = configset01.getConfiguration();
			for(org.openrtp.namespaces.rtc.version01.Configuration config01 : configList01) {
				//Basic Profile
				ConfigurationExt config02 = factory.createConfigurationExt();
				config02.setName(config01.getName());
				config02.setType(config01.getType());
				config02.setVariableName(config01.getVarname());
				config02.setDefaultValue(config01.getDefaultValue());
				//Doc Profile
				if( config01 instanceof org.openrtp.namespaces.rtc.version01.ConfigurationDoc) {
					org.openrtp.namespaces.rtc.version01.DocConfiguration doc01 = ((org.openrtp.namespaces.rtc.version01.ConfigurationDoc)config01).getDoc();
					if( doc01!=null ) {
						DocConfiguration docConfig02 = factory.createDocConfiguration();
						docConfig02.setDataname(doc01.getDataname());
						docConfig02.setDefaultValue(doc01.getDefaultValue());
						docConfig02.setDescription(doc01.getDescription());
						docConfig02.setUnit(doc01.getUnit());
						docConfig02.setRange(doc01.getRange());
						docConfig02.setConstraint(doc01.getConstraint());
						config02.setDoc(docConfig02);
					}
				}
				//Ext Profile
				if( config01 instanceof org.openrtp.namespaces.rtc.version01.ConfigurationExt) {
					List<org.openrtp.namespaces.rtc.version01.Property> properties01 = ((org.openrtp.namespaces.rtc.version01.ConfigurationExt)config01).getProperties();
					if( properties01!=null ) {
						config02.getProperties().addAll(convertRtcProperty01to02(factory, properties01));
					}
				}
				configset02.getConfiguration().add(config02);
			}
			result02.setConfigurationSet(configset02);
		}
		//DataPort
		List<org.openrtp.namespaces.rtc.version01.Dataport> dataPorts01 = profile01.getDataPorts();
		if( dataPorts01!=null ) {
			List<Dataport> dataPorts02 = new ArrayList<Dataport>();
			for( org.openrtp.namespaces.rtc.version01.Dataport dataport01 : dataPorts01 ) {
				//Basic Profile
				DataportExt dataPort02 = factory.createDataportExt();
				dataPort02.setPortType(dataport01.getPortType());
				dataPort02.setName(dataport01.getName());
				dataPort02.setType(dataport01.getType());
				dataPort02.setIdlFile(dataport01.getIdlFile());
				dataPort02.setInterfaceType(dataport01.getInterfaceType());
				dataPort02.setDataflowType(dataport01.getDataflowType());
				dataPort02.setSubscriptionType(dataport01.getSubscriprionType());
				//Doc Profile
				if( dataport01 instanceof org.openrtp.namespaces.rtc.version01.DataportDoc) {
					org.openrtp.namespaces.rtc.version01.DocDataport docDataPort01 = ((org.openrtp.namespaces.rtc.version01.DataportDoc)dataport01).getDoc(); 
					if( docDataPort01!=null ) {
						DocDataport docDataPort02 = factory.createDocDataport();
						docDataPort02.setDescription(docDataPort01.getDescription());
						docDataPort02.setType(docDataPort01.getType());
						docDataPort02.setNumber(docDataPort01.getNumber());
						docDataPort02.setSemantics(docDataPort01.getSemantics());
						docDataPort02.setUnit(docDataPort01.getUnit());
						docDataPort02.setOccerrence(docDataPort01.getOccerrence());
						docDataPort02.setOperation(docDataPort01.getOperation());
						dataPort02.setDoc(docDataPort02);
					}
					//Ext Profile
					if( dataport01 instanceof org.openrtp.namespaces.rtc.version01.DataportExt) {
						dataPort02.setVariableName(((org.openrtp.namespaces.rtc.version01.DataportExt)dataport01).getVarname());
						dataPort02.setPosition(getPosition((org.openrtp.namespaces.rtc.version01.DataportExt)dataport01));
						List<org.openrtp.namespaces.rtc.version01.Property> properties01 = ((org.openrtp.namespaces.rtc.version01.DataportExt)dataport01).getProperties();
						if( properties01!=null ) {
							dataPort02.getProperties().addAll(convertRtcProperty01to02(factory, properties01));
						}
					}
				}
				dataPorts02.add(dataPort02);
			}
			result02.getDataPorts().addAll(dataPorts02);
		}
		//ServicePort
		List<org.openrtp.namespaces.rtc.version01.Serviceport> servicePorts01 = profile01.getServicePorts();
		if( servicePorts01!=null ) {
			List<Serviceport> servicePorts02 = new ArrayList<Serviceport>();
			for( org.openrtp.namespaces.rtc.version01.Serviceport serviceport01 : servicePorts01 ) {
				//Basic Profile
				ServiceportExt servicePort02 = factory.createServiceportExt();
				servicePort02.setName(serviceport01.getName());
				//Doc Profile
				if( serviceport01 instanceof org.openrtp.namespaces.rtc.version01.ServiceportDoc) {
					org.openrtp.namespaces.rtc.version01.DocServiceport docServicePort01 = ((org.openrtp.namespaces.rtc.version01.ServiceportDoc)serviceport01).getDoc(); 
					if( docServicePort01!=null ) {
						DocServiceport docServicePort02 = factory.createDocServiceport();
						docServicePort02.setDescription(docServicePort01.getDescription());
						docServicePort02.setIfdescription(docServicePort01.getIfdescription());
						servicePort02.setDoc(docServicePort02);
					}
					//Ext Profile
					if( serviceport01 instanceof org.openrtp.namespaces.rtc.version01.ServiceportExt) {
						servicePort02.setPosition(getPosition((org.openrtp.namespaces.rtc.version01.ServiceportExt)serviceport01));
						List<org.openrtp.namespaces.rtc.version01.Property> properties01 = ((org.openrtp.namespaces.rtc.version01.ServiceportExt)serviceport01).getProperties();
						if( properties01!=null ) {
							servicePort02.getProperties().addAll(convertRtcProperty01to02(factory, properties01));
						}
					}
				}
				servicePorts02.add(servicePort02);
				//Service Interface
				List<org.openrtp.namespaces.rtc.version01.Serviceinterface> serviceIFs01 = serviceport01.getServiceInterface();
				if( serviceIFs01!=null ) {
					List<ServiceinterfaceExt> serviceIFs02 = new ArrayList<ServiceinterfaceExt>();
					for( org.openrtp.namespaces.rtc.version01.Serviceinterface serviceIF01 : serviceIFs01 ) {
						//Basic Profile
						ServiceinterfaceExt serviceIF02 = factory.createServiceinterfaceExt();
						serviceIF02.setName(serviceIF01.getName());
						serviceIF02.setDirection(serviceIF01.getDirection());
						serviceIF02.setInstanceName(serviceIF01.getInstanceName());
						serviceIF02.setVariableName(serviceIF01.getVarname());
						serviceIF02.setIdlFile(serviceIF01.getIdlFile());
						serviceIF02.setType(serviceIF01.getType());
						serviceIF02.setPath(serviceIF01.getPath());
						//Doc Profile
						if( serviceIF01 instanceof org.openrtp.namespaces.rtc.version01.ServiceinterfaceDoc) {
							org.openrtp.namespaces.rtc.version01.DocServiceinterface docServiceIF01 = ((org.openrtp.namespaces.rtc.version01.ServiceinterfaceDoc)serviceIF01).getDoc(); 
							if( docServiceIF01!=null ) {
								DocServiceinterface docServiceIF02 = factory.createDocServiceinterface();
								docServiceIF02.setDescription(docServiceIF01.getDescription());
								docServiceIF02.setDocArgument(docServiceIF01.getDocArgument());
								docServiceIF02.setDocReturn(docServiceIF01.getDocReturn());
								docServiceIF02.setDocException(docServiceIF01.getDocException());
								docServiceIF02.setDocPreCondition(docServiceIF01.getDocPreCondition());
								docServiceIF02.setDocPostCondition(docServiceIF01.getDocPostCondition());
								serviceIF02.setDoc(docServiceIF02);
							}
							//Ext Profile
							if( serviceIF01 instanceof org.openrtp.namespaces.rtc.version01.ServiceinterfaceExt) {
								List<org.openrtp.namespaces.rtc.version01.Property> properties01 = ((org.openrtp.namespaces.rtc.version01.ServiceinterfaceExt)serviceIF01).getProperties();
								if( properties01!=null ) {
									serviceIF02.getProperties().addAll(convertRtcProperty01to02(factory, properties01));
								}
							}
						}
						serviceIFs02.add(serviceIF02);
					}
					servicePort02.getServiceInterface().addAll(serviceIFs02);
				}
			}
			result02.getServicePorts().addAll(servicePorts02);
		}
		//Parameter
		List<org.openrtp.namespaces.rtc.version01.Parameter> parameters01 = profile01.getParameters();
		if( parameters01!=null ) {
			List<Parameter> parameters02 = new ArrayList<Parameter>();
			for( org.openrtp.namespaces.rtc.version01.Parameter parameter01 : parameters01 ) {
				//Basic Profile
				Parameter parameter02 = factory.createParameter();
				parameter02.setName(parameter01.getName());
				parameter02.setDefaultValue(parameter01.getDefaultValue());
				parameters02.add(parameter02);
			}
			result02.getParameters().addAll(parameters02);
		}
		//Language
		org.openrtp.namespaces.rtc.version01.Language lang01 = profile01.getLanguage();
		org.openrtp.namespaces.rtc.version01.Cxxlang langCxx = lang01.getCxx();
		if( langCxx!=null) {
			LanguageExt lang = factory.createLanguageExt();
			lang.setKind("cxx");
			TargetEnvironment env = factory.createTargetEnvironment();
			env.setOs(langCxx.getOs());
			env.setCpuOther(langCxx.getArch());
			for( String library : langCxx.getLibrary() ) {
				Library lib = factory.createLibrary();
				lib.setName(library);
				env.getLibraries().add(lib);
			}
			lang.getTargets().add(env);
			result02.setLanguage(lang);
		}
		org.openrtp.namespaces.rtc.version01.Javalang langJava = lang01.getJava();
		if( langJava!=null) {
			LanguageExt lang = factory.createLanguageExt();
			lang.setKind("java");
			TargetEnvironment env = factory.createTargetEnvironment();
			for( String library : langJava.getLibrary() ) {
				Library lib = factory.createLibrary();
				lib.setName(library);
				env.getLibraries().add(lib);
			}
			lang.getTargets().add(env);
			result02.setLanguage(lang);
		}
		String langPython = lang01.getPython();
		if( langPython!=null) {
			LanguageExt lang = factory.createLanguageExt();
			lang.setKind("python");
			result02.setLanguage(lang);
		}
		String langCsharp = lang01.getCsharp();
		if( langCsharp!=null) {
			LanguageExt lang = factory.createLanguageExt();
			lang.setKind("csharp");
			result02.setLanguage(lang);
		}
		String langRuby = lang01.getRuby();
		if( langRuby!=null) {
			LanguageExt lang = factory.createLanguageExt();
			lang.setKind("ruby");
			result02.setLanguage(lang);
		}
		
		return result02;
	}

	private Position getPosition(
			org.openrtp.namespaces.rtc.version01.ServiceportExt serviceport01) {
		if (serviceport01.getPosition() == null) return null;
		if (serviceport01.getPosition().name() == null) return null;
		return Position.fromValue(serviceport01.getPosition().name().toUpperCase());
	}

	private Position getPosition(
			org.openrtp.namespaces.rtc.version01.DataportExt dataport01) {
		if (dataport01.getPosition() == null) return null;
		if (dataport01.getPosition().name() == null) return null;
		return Position.fromValue(dataport01.getPosition().name().toUpperCase());
	}

	private List<Property> convertRtcProperty01to02(ObjectFactory factory, List<org.openrtp.namespaces.rtc.version01.Property> properties01) {
		List<Property> properties02 = new ArrayList<Property>();
		for( org.openrtp.namespaces.rtc.version01.Property property01 : properties01 ) {
			Property property = factory.createProperty();
			property.setName(property01.getName());
			property.setValue(property01.getValue());
			properties02.add(property);
		}
		return properties02;
	}

	private String convertId01to02(String ver01Id) {
		String[] element = ver01Id.split(":");
		String result = null;
		if( element.length >= 2) {
			String[] items = element[1].split("\\.");
			StringBuffer vendor = new StringBuffer();
			for(int index=0;index<items.length-2;index++) {
				vendor.append(items[index]);
			}
			result = element[0] + ":"
						+ vendor.toString() + ":"
						+ items[items.length-2] + ":"
						+ items[items.length-1] + ":"
						+ element[2];
		}
		return result;
	}
	
	private ActionStatusDoc convertAction01to02(ObjectFactory factory, Actions actions02, org.openrtp.namespaces.rtc.version01.ActionStatus action01) {
		ActionStatusDoc action02 = null;
		if( action01!=null ) {
			action02 = factory.createActionStatusDoc();
			action02.setImplementedbln(action01.isImplemented());
			if( action01 instanceof org.openrtp.namespaces.rtc.version01.ActionStatusDoc ) {
				org.openrtp.namespaces.rtc.version01.DocAction docAction01 = ((org.openrtp.namespaces.rtc.version01.ActionStatusDoc)action01).getDoc();
				if( docAction01!=null ) {
					DocAction docAction02 = factory.createDocAction();
					docAction02.setDescription(docAction01.getDescription());
					docAction02.setPreCondition(docAction01.getPreCondition());
					docAction02.setPostCondition(docAction01.getPostCondition());
					action02.setDoc(docAction02);
				}
			}
		}
		return action02; 
	}
	
	public boolean validateXmlRtcBySchema(String targetString) throws Exception {
		try {
		    SAXParserFactory spfactory = SAXParserFactory.newInstance();
		    SAXParser parser = spfactory.newSAXParser();
		    RtcXMLParser xmlParser = new RtcXMLParser();
		    StringReader xmlReader = new StringReader(targetString);
		    parser.parse(new InputSource(xmlReader), xmlParser);
		    String targetClass = null;
		    if( xmlParser.version.equals("0.1") ) {
		    	targetClass = "org.openrtp.namespaces.rtc.version01";
		    } else if( xmlParser.version.equals("0.2") ) {
		    	targetClass = "org.openrtp.namespaces.rtc.version02";
		    }
		    if( targetClass==null ) {
		    	throw new Exception("XML Parse Error");
		    }
			
			JAXBContext jc = JAXBContext.newInstance(targetClass);
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(getClass().getResource("/RtcProfile_ext.xsd"));
			unmarshaller.setSchema(schema);

			((JAXBElement<?>)unmarshaller.unmarshal(new StringReader(targetString))).getValue();

		} catch (JAXBException e) {
			throw new JAXBException("XML Validation Error.", e);
		}
		return true;
	}
	
	public boolean validateXmlRtsBySchema(String targetString) throws Exception {
		try {
		    SAXParserFactory spfactory = SAXParserFactory.newInstance();
		    SAXParser parser = spfactory.newSAXParser();
		    RtsXMLParser xmlParser = new RtsXMLParser();
		    StringReader xmlReader = new StringReader(targetString);
		    parser.parse(new InputSource(xmlReader), xmlParser);
		    String targetClass = null;
		    if( xmlParser.version.equals("0.1") ) {
		    	targetClass = "org.openrtp.namespaces.rts.version01";
		    } else if( xmlParser.version.equals("0.2") ) {
		    	targetClass = "org.openrtp.namespaces.rts.version02";
		    }
		    if( targetClass==null ) {
		    	throw new Exception("XML Parse Error");
		    }
			
			JAXBContext jc = JAXBContext.newInstance(targetClass);
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(getClass().getResource("/RtsProfile_ext.xsd"));
			unmarshaller.setSchema(schema);

			((JAXBElement<?>)unmarshaller.unmarshal(new StringReader(targetString))).getValue();

		} catch (JAXBException e) {
			throw new JAXBException("XML Validation Error.", e);
		}
		return true;
	}


}
