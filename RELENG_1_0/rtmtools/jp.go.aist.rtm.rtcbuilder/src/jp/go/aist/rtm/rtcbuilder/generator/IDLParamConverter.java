package jp.go.aist.rtm.rtcbuilder.generator;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.IDLParser;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.ParseException;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.Node;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.NodeToken;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.array_declarator;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.base_type_spec;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.enum_type;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.identifier;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.interface_dcl;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.interface_header;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.module;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.op_dcl;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.param_dcl;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.scoped_name;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.simple_type_spec;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.specification;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.string_type;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.struct_type;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.type_declarator;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.visitor.DepthFirstVisitor;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.visitor.GJNoArguDepthFirst;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.visitor.GJVoidDepthFirst;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataTypeParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceArgumentParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceMethodParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.TypeDefParam;

/**
 * IDLの構文解析木から、ジェネレータのインプットとなる情報に変換するクラス
 * <p>
 */
public class IDLParamConverter {

	/**
	 * IDLの構文解析木から、ジェネレータのインプットとなるServiceParamに変換する
	 * 
	 * @param spec
	 * @return ServiceParam
	 */
	public static List<ServiceClassParam> convert(specification spec,
			final String idlPath) {
		final List<ServiceClassParam> result = new ArrayList<ServiceClassParam>();
		spec.accept(new GJVoidDepthFirst<String>() {
			@SuppressWarnings("unchecked")
			@Override
			public void visit(interface_dcl n, String argu) {
				final InterfaceInfomation interfaceInfomation = new InterfaceInfomation();

				interface_header interfaceHeader = n.interface_header;

				interfaceInfomation.name = interfaceHeader.identifier.nodeToken.tokenImage;

				n.interface_header.accept(
						new GJVoidDepthFirst<InterfaceInfomation>() {
							@Override
							public void visit(scoped_name n,
									InterfaceInfomation argu) {
								interfaceInfomation.superInterfaceList
										.add(node2String(n));
							}
						}, interfaceInfomation);

				final ServiceClassParam service = new ServiceClassParam();
				service.setIdlPath(idlPath);
				service.setModule(getModuleName(n));
				service.setName(getModuleName(n) + interfaceInfomation.name);
				service.getSuperInterfaceList().addAll(interfaceInfomation.superInterfaceList);

				n.interface_body.accept(new GJVoidDepthFirst() {
					@Override
					public void visit(op_dcl n, Object argu) {
						final ServiceMethodParam serviceMethodParam = new ServiceMethodParam();
						serviceMethodParam
								.setName(n.identifier.nodeToken.tokenImage);
						serviceMethodParam.setType(node2String(n.op_type_spec));
						serviceMethodParam.setModule(service.getModule());

						n.parameter_dcls.accept(new GJVoidDepthFirst() {
							@Override
							public void visit(param_dcl n, Object argu) {
								ServiceArgumentParam serviceArgumentParam = new ServiceArgumentParam();
								serviceArgumentParam
										.setName(n.simple_declarator.identifier.nodeToken.tokenImage);
								serviceArgumentParam
										.setType(node2String(n.param_type_spec));
								serviceArgumentParam.setDirection(node2String(n.param_attribute));

								serviceMethodParam.getArguments().add(
										serviceArgumentParam);
							}
						}, null);

						service.getMethods().add(serviceMethodParam);
					}
				}, null);

				result.add(service);
			}

		}, null);
		checkSuperInterface(result);

		return result;
	}
	
	public static void checkSuperInterface(List<ServiceClassParam> targetList) {
		for(ServiceClassParam target : targetList) {
			if( target.getSuperInterfaceList().size()==0 ) continue;
			for(String targetIF : target.getSuperInterfaceList()) {
				for(ServiceClassParam source : targetList) {
					if(targetIF.equals(source.getName())) {
						target.getMethods().addAll(source.getMethods());
						target.setTypeDef(source.getTypeDefList());
						break;
					}
				}
			}
		}
		
	}

	/**
	 * IDLの構文解析木から、sequence型のtypedefを探索する
	 * 
	 * @param spec
	 * @return HashMap
	 */
	public static List<TypeDefParam> convert_typedef(specification spec,
			final String idlPath) {
		final List<TypeDefParam> result = new ArrayList<TypeDefParam>();

		spec.accept(new GJVoidDepthFirst<String>() {
			@Override
			public void visit(interface_dcl n, String argu) {
				final String ifname = n.interface_header.identifier.nodeToken.tokenImage;
				n.interface_body.accept(new GJVoidDepthFirst<String>() {
					@Override
					public void visit(type_declarator n, String argu) {
						final TypeDefParam tdparam = new TypeDefParam();
						n.declarators.accept(new DepthFirstVisitor(){
							@Override
							public void visit(identifier n) {
								tdparam.setScopedName(ifname);
								tdparam.setTargetDef(node2String(n));
							}
						});
						n.type_spec.accept(new DepthFirstVisitor(){
							@Override
							public void visit(simple_type_spec n) {
								n.nodeChoice.accept(new DepthFirstVisitor(){
									@Override
									public void visit(simple_type_spec n) {
										tdparam.setOriginalDef(node2String(n) + "[]");
										tdparam.setSequence(true);
									}
									@Override
									public void visit(base_type_spec n) {
										tdparam.setOriginalDef(node2String(n));
									}
									@Override
									public void visit(scoped_name n) {
										tdparam.setOriginalDef(node2String(n));
									}
								});
								
							}
						});
						result.add(tdparam);
					}
				}, null);
			}
			@Override
			public void visit(struct_type n, String argu) {
				final TypeDefParam tdparam = new TypeDefParam();
				tdparam.setStruct(true);
				n.identifier.accept(new DepthFirstVisitor(){
					@Override
					public void visit(identifier n) {
						tdparam.setTargetDef(node2String(n));
					}
				});
				n.member_list.accept(new DepthFirstVisitor(){
					@Override
					public void visit(simple_type_spec n) {
						tdparam.getChildType().add(node2String(n));
					}
				});
				result.add(tdparam);
			}
			@Override
			public void visit(enum_type n, String argu) {
				final TypeDefParam tdparam = new TypeDefParam();
				tdparam.setEnum(true);
				n.identifier.accept(new DepthFirstVisitor(){
					@Override
					public void visit(identifier n) {
						tdparam.setTargetDef(node2String(n));
					}
				});
				result.add(tdparam);
			}
			@Override
			public void visit(type_declarator n, String argu) {
				final TypeDefParam tdparam = new TypeDefParam();
				n.declarators.accept(new DepthFirstVisitor(){
					@Override
					public void visit(identifier n) {
						tdparam.setTargetDef(node2String(n));
					}
					@Override
					public void visit(array_declarator n) {
						tdparam.setArray(true);
						n.identifier.accept(new DepthFirstVisitor(){
							@Override
							public void visit(identifier n) {
								tdparam.setTargetDef(node2String(n));
							}
						});
					}
				});
				n.type_spec.accept(new DepthFirstVisitor(){
					@Override
					public void visit(simple_type_spec n) {
						n.nodeChoice.accept(new DepthFirstVisitor(){
							@Override
							public void visit(simple_type_spec n) {
								tdparam.setOriginalDef(node2String(n) + "[]");
								tdparam.setSequence(true);
							}
							@Override
							public void visit(base_type_spec n) {
								tdparam.setOriginalDef(node2String(n));
							}
							@Override
							public void visit(scoped_name n) {
								tdparam.setOriginalDef(node2String(n));
							}
							@Override
							public void visit(string_type n) {
								n.nodeChoice.accept(new DepthFirstVisitor(){
									@Override
									public void visit(NodeToken n) {
										if(node2String(n).toLowerCase().equals("string") ) {
											tdparam.setOriginalDef("string");
											tdparam.setString(true);
										} else if(node2String(n).toLowerCase().equals("wstring") ) {
											tdparam.setOriginalDef("wstring");
											tdparam.setString(true);
										}
									}
								});
							}
						});
						
					}
				});
				result.add(tdparam);
			}
		}, null);
		return result;
	}
	
	public static List<String> extractTypeDef(List<DataTypeParam> sources) {
		List<String> result = new ArrayList<String>();
		
		for( Iterator<DataTypeParam> iter = sources.iterator(); iter.hasNext(); ) {
			DataTypeParam targetParam = iter.next();
			String targetContent = targetParam.getContent();
			targetContent = PreProcessor.parseAlltoSpace(targetContent);
			IDLParser parser = new IDLParser(new StringReader(targetContent));
			specification spec=null;
			try {
				spec = parser.specification();
			} catch (ParseException e) {
				continue;
			}
			List<String> types = parseForTypeDef(spec);
			for( Iterator<String> iterRes = types.iterator(); iterRes.hasNext(); ) {
				String resultType = iterRes.next();
				if( result.contains(resultType) ) {
					continue;
//					throw new ParseException("[" + resultType + "]" + IRTCBMessageConstants.ERROR_IDLTYPEDUPLICAT);
				}
				result.add(resultType);
				targetParam.getDefinedTypes().add(resultType);
			}
		}
		return result;
	}
	
	private static List<String> parseForTypeDef(specification spec) {
		final List<String> results = new ArrayList<String>();
		
		spec.accept(new GJVoidDepthFirst<String>() {
			@SuppressWarnings("unchecked")
			@Override
			public void visit(module n, String argu) {
				final String moduleName = node2String(n.identifier);
				n.accept(new GJVoidDepthFirst() {
					@Override
					public void visit(struct_type n, Object argu) {
						String typeName = node2String(n.identifier);
						if( moduleName!=null && moduleName.length()>0 ) {
							typeName = moduleName + "::" + typeName;
						}
						results.add(typeName);
					}
					
				},null);
			}
			//
			@Override
			public void visit(struct_type n, String argu) {
				String typeName = node2String(n.identifier);
				results.add(typeName);
			}
		}, null);

		return results;
	}
	/**
	 * インタフェースのモジュール名を取得する
	 * 
	 * @param n
	 * @return
	 */
	private static String getModuleName(interface_dcl n) {
		String result = "";

		Node node = n;
		while (node != null) {
			if (node instanceof module) {
				result = node2String(((module) node).identifier) + "::" + result;
			}
			node = node.getParent();
		}

		return result;
	}

	/**
	 * ノードを文字列に変換する
	 * 
	 * @param n
	 *            ノード
	 * @return 変換結果の文字列
	 */
	@SuppressWarnings("unchecked")
	public static String node2String(Node n) {
		final StringBuffer result = new StringBuffer();
		n.accept(new GJNoArguDepthFirst() {
			@Override
			public Object visit(NodeToken n) {
				return result.append(n.tokenImage);
			}
		});

		return result.toString();
	}

	/**
	 * IDLParamConverterクラスが内部で一時的に使用する、IDLのインタフェース表現。
	 * <p>
	 * 内部クラスであり大きな問題もないので、属性をpublicとしている。
	 */
	public static class InterfaceInfomation {
		public String name;

		public List<String> superInterfaceList = new ArrayList<String>();
	}
}
