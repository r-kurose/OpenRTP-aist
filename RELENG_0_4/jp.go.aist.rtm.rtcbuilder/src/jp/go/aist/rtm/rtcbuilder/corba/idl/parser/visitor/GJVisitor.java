//
// Generated by JTB 1.3.2
//

package jp.go.aist.rtm.rtcbuilder.corba.idl.parser.visitor;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.NodeList;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.NodeListOptional;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.NodeOptional;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.NodeSequence;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.NodeToken;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.add_expr;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.and_expr;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.any_type;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.array_declarator;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.attr_dcl;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.base_type_spec;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.boolean_literal;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.boolean_type;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.case_label;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.casex;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.char_type;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.character_literal;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.complex_declarator;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.const_dcl;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.const_exp;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.const_type;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.constr_type_spec;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.context_expr;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.declarator;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.declarators;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.definition;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.element_spec;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.enum_type;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.enumerator;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.except_dcl;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.export;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.fixed_array_size;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.floating_pt_literal;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.floating_pt_type;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.forward_dcl;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.identifier;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.inheritance_spec;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.integer_literal;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.integer_type;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.interface_body;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.interface_dcl;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.interface_header;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.interfacex;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.literal;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.member;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.member_list;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.module;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.mult_expr;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.octet_type;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.op_attribute;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.op_dcl;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.op_type_spec;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.or_expr;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.param_attribute;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.param_dcl;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.param_type_spec;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.parameter_dcls;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.positive_int_const;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.primary_expr;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.raises_expr;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.scoped_name;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.sequence_type;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.shift_expr;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.signed_int;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.signed_long_double_int;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.signed_long_int;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.signed_long_long_int;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.signed_short_int;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.simple_declarator;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.simple_type_spec;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.specification;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.string_literal;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.string_type;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.struct_type;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.switch_body;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.switch_type_spec;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.template_type_spec;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.type_dcl;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.type_declarator;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.type_spec;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.unary_expr;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.unary_operator;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.union_type;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.unsigned_int;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.unsigned_long_int;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.unsigned_long_long_int;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.unsigned_short_int;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.xor_expr;

/**
 * All GJ visitors must implement this interface.
 */

public interface GJVisitor<R,A> {

   //
   // GJ Auto class visitors
   //

   public R visit(NodeList n, A argu);
   public R visit(NodeListOptional n, A argu);
   public R visit(NodeOptional n, A argu);
   public R visit(NodeSequence n, A argu);
   public R visit(NodeToken n, A argu);

   //
   // User-generated visitor methods below
   //

   /**
    * <PRE>
    * nodeList -> ( definition() )+
    * </PRE>
    */
   public R visit(specification n, A argu);

   /**
    * <PRE>
    * nodeChoice -> type_dcl() ";"
    *       | const_dcl() ";"
    *       | except_dcl() ";"
    *       | interfacex() ";"
    *       | module() ";"
    * </PRE>
    */
   public R visit(definition n, A argu);

   /**
    * <PRE>
    * nodeToken -> "module"
    * identifier -> identifier()
    * nodeToken1 -> "{"
    * nodeList -> ( definition() )+
    * nodeToken2 -> "}"
    * </PRE>
    */
   public R visit(module n, A argu);

   /**
    * <PRE>
    * nodeChoice -> interface_dcl()
    *       | forward_dcl()
    * </PRE>
    */
   public R visit(interfacex n, A argu);

   /**
    * <PRE>
    * interface_header -> interface_header()
    * nodeToken -> "{"
    * interface_body -> interface_body()
    * nodeToken1 -> "}"
    * </PRE>
    */
   public R visit(interface_dcl n, A argu);

   /**
    * <PRE>
    * nodeToken -> "interface"
    * identifier -> identifier()
    * </PRE>
    */
   public R visit(forward_dcl n, A argu);

   /**
    * <PRE>
    * nodeToken -> "interface"
    * identifier -> identifier()
    * nodeOptional -> [ inheritance_spec() ]
    * </PRE>
    */
   public R visit(interface_header n, A argu);

   /**
    * <PRE>
    * nodeListOptional -> ( export() )*
    * </PRE>
    */
   public R visit(interface_body n, A argu);

   /**
    * <PRE>
    * nodeChoice -> type_dcl() ";"
    *       | const_dcl() ";"
    *       | except_dcl() ";"
    *       | attr_dcl() ";"
    *       | op_dcl() ";"
    * </PRE>
    */
   public R visit(export n, A argu);

   /**
    * <PRE>
    * nodeToken -> ":"
    * scoped_name -> scoped_name()
    * nodeListOptional -> ( "," scoped_name() )*
    * </PRE>
    */
   public R visit(inheritance_spec n, A argu);

   /**
    * <PRE>
    * nodeOptional -> [ "::" ]
    * identifier -> identifier()
    * nodeListOptional -> ( "::" identifier() )*
    * </PRE>
    */
   public R visit(scoped_name n, A argu);

   /**
    * <PRE>
    * nodeToken -> "const"
    * const_type -> const_type()
    * identifier -> identifier()
    * nodeToken1 -> "="
    * const_exp -> const_exp()
    * </PRE>
    */
   public R visit(const_dcl n, A argu);

   /**
    * <PRE>
    * nodeChoice -> integer_type()
    *       | char_type()
    *       | boolean_type()
    *       | floating_pt_type()
    *       | string_type()
    *       | scoped_name()
    * </PRE>
    */
   public R visit(const_type n, A argu);

   /**
    * <PRE>
    * or_expr -> or_expr()
    * </PRE>
    */
   public R visit(const_exp n, A argu);

   /**
    * <PRE>
    * xor_expr -> xor_expr()
    * nodeListOptional -> ( "|" xor_expr() )*
    * </PRE>
    */
   public R visit(or_expr n, A argu);

   /**
    * <PRE>
    * and_expr -> and_expr()
    * nodeListOptional -> ( "^" and_expr() )*
    * </PRE>
    */
   public R visit(xor_expr n, A argu);

   /**
    * <PRE>
    * shift_expr -> shift_expr()
    * nodeListOptional -> ( "&" shift_expr() )*
    * </PRE>
    */
   public R visit(and_expr n, A argu);

   /**
    * <PRE>
    * add_expr -> add_expr()
    * nodeListOptional -> ( ( "&gt;&gt;" | "&lt;&lt;" ) add_expr() )*
    * </PRE>
    */
   public R visit(shift_expr n, A argu);

   /**
    * <PRE>
    * mult_expr -> mult_expr()
    * nodeListOptional -> ( ( "+" | "-" ) mult_expr() )*
    * </PRE>
    */
   public R visit(add_expr n, A argu);

   /**
    * <PRE>
    * unary_expr -> unary_expr()
    * nodeListOptional -> ( ( "*" | "/" | "%" ) unary_expr() )*
    * </PRE>
    */
   public R visit(mult_expr n, A argu);

   /**
    * <PRE>
    * nodeOptional -> [ unary_operator() ]
    * primary_expr -> primary_expr()
    * </PRE>
    */
   public R visit(unary_expr n, A argu);

   /**
    * <PRE>
    * nodeChoice -> "-"
    *       | "+"
    *       | "~"
    * </PRE>
    */
   public R visit(unary_operator n, A argu);

   /**
    * <PRE>
    * nodeChoice -> scoped_name()
    *       | literal()
    *       | "(" const_exp() ")"
    * </PRE>
    */
   public R visit(primary_expr n, A argu);

   /**
    * <PRE>
    * nodeChoice -> integer_literal()
    *       | string_literal()
    *       | character_literal()
    *       | floating_pt_literal()
    *       | boolean_literal()
    * </PRE>
    */
   public R visit(literal n, A argu);

   /**
    * <PRE>
    * nodeChoice -> "TRUE"
    *       | "FALSE"
    * </PRE>
    */
   public R visit(boolean_literal n, A argu);

   /**
    * <PRE>
    * const_exp -> const_exp()
    * </PRE>
    */
   public R visit(positive_int_const n, A argu);

   /**
    * <PRE>
    * nodeChoice -> "typedef" type_declarator()
    *       | struct_type()
    *       | union_type()
    *       | enum_type()
    * </PRE>
    */
   public R visit(type_dcl n, A argu);

   /**
    * <PRE>
    * type_spec -> type_spec()
    * declarators -> declarators()
    * </PRE>
    */
   public R visit(type_declarator n, A argu);

   /**
    * <PRE>
    * nodeChoice -> simple_type_spec()
    *       | constr_type_spec()
    * </PRE>
    */
   public R visit(type_spec n, A argu);

   /**
    * <PRE>
    * nodeChoice -> base_type_spec()
    *       | template_type_spec()
    *       | scoped_name()
    * </PRE>
    */
   public R visit(simple_type_spec n, A argu);

   /**
    * <PRE>
    * nodeChoice -> floating_pt_type()
    *       | integer_type()
    *       | char_type()
    *       | boolean_type()
    *       | octet_type()
    *       | any_type()
    * </PRE>
    */
   public R visit(base_type_spec n, A argu);

   /**
    * <PRE>
    * nodeChoice -> sequence_type()
    *       | string_type()
    * </PRE>
    */
   public R visit(template_type_spec n, A argu);

   /**
    * <PRE>
    * nodeChoice -> struct_type()
    *       | union_type()
    *       | enum_type()
    * </PRE>
    */
   public R visit(constr_type_spec n, A argu);

   /**
    * <PRE>
    * declarator -> declarator()
    * nodeListOptional -> ( "," declarator() )*
    * </PRE>
    */
   public R visit(declarators n, A argu);

   /**
    * <PRE>
    * nodeChoice -> complex_declarator()
    *       | simple_declarator()
    * </PRE>
    */
   public R visit(declarator n, A argu);

   /**
    * <PRE>
    * identifier -> identifier()
    * </PRE>
    */
   public R visit(simple_declarator n, A argu);

   /**
    * <PRE>
    * array_declarator -> array_declarator()
    * </PRE>
    */
   public R visit(complex_declarator n, A argu);

   /**
    * <PRE>
    * nodeChoice -> "float"
    *       | "double"
    * </PRE>
    */
   public R visit(floating_pt_type n, A argu);

   /**
    * <PRE>
    * nodeChoice -> signed_int()
    *       | unsigned_int()
    * </PRE>
    */
   public R visit(integer_type n, A argu);

   /**
    * <PRE>
    * nodeChoice -> signed_long_long_int()
    *       | signed_long_double_int()
    *       | signed_long_int()
    *       | signed_short_int()
    * </PRE>
    */
   public R visit(signed_int n, A argu);

   /**
    * <PRE>
    * nodeToken -> "long"
    * </PRE>
    */
   public R visit(signed_long_int n, A argu);

   /**
    * <PRE>
    * nodeToken -> "short"
    * </PRE>
    */
   public R visit(signed_short_int n, A argu);

   /**
    * <PRE>
    * nodeToken -> "long"
    * nodeToken1 -> "long"
    * </PRE>
    */
   public R visit(signed_long_long_int n, A argu);

   /**
    * <PRE>
    * nodeToken -> "long"
    * nodeToken1 -> "double"
    * </PRE>
    */
   public R visit(signed_long_double_int n, A argu);

   /**
    * <PRE>
    * nodeChoice -> unsigned_long_long_int()
    *       | unsigned_long_int()
    *       | unsigned_short_int()
    * </PRE>
    */
   public R visit(unsigned_int n, A argu);

   /**
    * <PRE>
    * nodeToken -> "unsigned"
    * nodeToken1 -> "long"
    * </PRE>
    */
   public R visit(unsigned_long_int n, A argu);

   /**
    * <PRE>
    * nodeToken -> "unsigned"
    * nodeToken1 -> "short"
    * </PRE>
    */
   public R visit(unsigned_short_int n, A argu);

   /**
    * <PRE>
    * nodeToken -> "unsigned"
    * nodeToken1 -> "long"
    * nodeToken2 -> "long"
    * </PRE>
    */
   public R visit(unsigned_long_long_int n, A argu);

   /**
    * <PRE>
    * nodeToken -> "char"
    * </PRE>
    */
   public R visit(char_type n, A argu);

   /**
    * <PRE>
    * nodeToken -> "boolean"
    * </PRE>
    */
   public R visit(boolean_type n, A argu);

   /**
    * <PRE>
    * nodeToken -> "octet"
    * </PRE>
    */
   public R visit(octet_type n, A argu);

   /**
    * <PRE>
    * nodeToken -> "any"
    * </PRE>
    */
   public R visit(any_type n, A argu);

   /**
    * <PRE>
    * nodeToken -> "struct"
    * identifier -> identifier()
    * nodeToken1 -> "{"
    * member_list -> member_list()
    * nodeToken2 -> "}"
    * </PRE>
    */
   public R visit(struct_type n, A argu);

   /**
    * <PRE>
    * nodeList -> ( member() )+
    * </PRE>
    */
   public R visit(member_list n, A argu);

   /**
    * <PRE>
    * type_spec -> type_spec()
    * declarators -> declarators()
    * nodeToken -> ";"
    * </PRE>
    */
   public R visit(member n, A argu);

   /**
    * <PRE>
    * nodeToken -> "union"
    * identifier -> identifier()
    * nodeToken1 -> "switch"
    * nodeToken2 -> "("
    * switch_type_spec -> switch_type_spec()
    * nodeToken3 -> ")"
    * nodeToken4 -> "{"
    * switch_body -> switch_body()
    * nodeToken5 -> "}"
    * </PRE>
    */
   public R visit(union_type n, A argu);

   /**
    * <PRE>
    * nodeChoice -> integer_type()
    *       | char_type()
    *       | boolean_type()
    *       | enum_type()
    *       | scoped_name()
    * </PRE>
    */
   public R visit(switch_type_spec n, A argu);

   /**
    * <PRE>
    * nodeList -> ( casex() )+
    * </PRE>
    */
   public R visit(switch_body n, A argu);

   /**
    * <PRE>
    * nodeList -> ( case_label() )+
    * element_spec -> element_spec()
    * nodeToken -> ";"
    * </PRE>
    */
   public R visit(casex n, A argu);

   /**
    * <PRE>
    * nodeChoice -> "case" const_exp() ":"
    *       | "default" ":"
    * </PRE>
    */
   public R visit(case_label n, A argu);

   /**
    * <PRE>
    * type_spec -> type_spec()
    * declarator -> declarator()
    * </PRE>
    */
   public R visit(element_spec n, A argu);

   /**
    * <PRE>
    * nodeToken -> "enum"
    * identifier -> identifier()
    * nodeToken1 -> "{"
    * enumerator -> enumerator()
    * nodeListOptional -> ( "," enumerator() )*
    * nodeToken2 -> "}"
    * </PRE>
    */
   public R visit(enum_type n, A argu);

   /**
    * <PRE>
    * identifier -> identifier()
    * </PRE>
    */
   public R visit(enumerator n, A argu);

   /**
    * <PRE>
    * nodeToken -> "sequence"
    * nodeToken1 -> "&lt;"
    * simple_type_spec -> simple_type_spec()
    * nodeOptional -> [ "," positive_int_const() ]
    * nodeToken2 -> "&gt;"
    * </PRE>
    */
   public R visit(sequence_type n, A argu);

   /**
    * <PRE>
    * nodeToken -> "string"
    * nodeOptional -> [ "&lt;" positive_int_const() "&gt;" ]
    * </PRE>
    */
   public R visit(string_type n, A argu);

   /**
    * <PRE>
    * identifier -> identifier()
    * nodeList -> ( fixed_array_size() )+
    * </PRE>
    */
   public R visit(array_declarator n, A argu);

   /**
    * <PRE>
    * nodeToken -> "["
    * positive_int_const -> positive_int_const()
    * nodeToken1 -> "]"
    * </PRE>
    */
   public R visit(fixed_array_size n, A argu);

   /**
    * <PRE>
    * nodeOptional -> [ "readonly" ]
    * nodeToken -> "attribute"
    * param_type_spec -> param_type_spec()
    * simple_declarator -> simple_declarator()
    * nodeListOptional -> ( "," simple_declarator() )*
    * </PRE>
    */
   public R visit(attr_dcl n, A argu);

   /**
    * <PRE>
    * nodeToken -> "exception"
    * identifier -> identifier()
    * nodeToken1 -> "{"
    * nodeListOptional -> ( member() )*
    * nodeToken2 -> "}"
    * </PRE>
    */
   public R visit(except_dcl n, A argu);

   /**
    * <PRE>
    * nodeOptional -> [ op_attribute() ]
    * op_type_spec -> op_type_spec()
    * identifier -> identifier()
    * parameter_dcls -> parameter_dcls()
    * nodeOptional1 -> [ raises_expr() ]
    * nodeOptional2 -> [ context_expr() ]
    * </PRE>
    */
   public R visit(op_dcl n, A argu);

   /**
    * <PRE>
    * nodeToken -> "oneway"
    * </PRE>
    */
   public R visit(op_attribute n, A argu);

   /**
    * <PRE>
    * nodeChoice -> param_type_spec()
    *       | "void"
    * </PRE>
    */
   public R visit(op_type_spec n, A argu);

   /**
    * <PRE>
    * nodeToken -> "("
    * nodeOptional -> [ param_dcl() ( "," param_dcl() )* ]
    * nodeToken1 -> ")"
    * </PRE>
    */
   public R visit(parameter_dcls n, A argu);

   /**
    * <PRE>
    * param_attribute -> param_attribute()
    * param_type_spec -> param_type_spec()
    * simple_declarator -> simple_declarator()
    * </PRE>
    */
   public R visit(param_dcl n, A argu);

   /**
    * <PRE>
    * nodeChoice -> "in"
    *       | "out"
    *       | "inout"
    * </PRE>
    */
   public R visit(param_attribute n, A argu);

   /**
    * <PRE>
    * nodeToken -> "raises"
    * nodeToken1 -> "("
    * scoped_name -> scoped_name()
    * nodeListOptional -> ( "," scoped_name() )*
    * nodeToken2 -> ")"
    * </PRE>
    */
   public R visit(raises_expr n, A argu);

   /**
    * <PRE>
    * nodeToken -> "context"
    * nodeToken1 -> "("
    * string_literal -> string_literal()
    * nodeListOptional -> ( "," string_literal() )*
    * nodeToken2 -> ")"
    * </PRE>
    */
   public R visit(context_expr n, A argu);

   /**
    * <PRE>
    * nodeChoice -> base_type_spec()
    *       | string_type()
    *       | scoped_name()
    * </PRE>
    */
   public R visit(param_type_spec n, A argu);

   /**
    * <PRE>
    * nodeToken -> &lt;ID&gt;
    * </PRE>
    */
   public R visit(identifier n, A argu);

   /**
    * <PRE>
    * nodeChoice -> &lt;OCTALINT&gt;
    *       | &lt;DECIMALINT&gt;
    *       | &lt;HEXADECIMALINT&gt;
    * </PRE>
    */
   public R visit(integer_literal n, A argu);

   /**
    * <PRE>
    * nodeToken -> &lt;STRING&gt;
    * </PRE>
    */
   public R visit(string_literal n, A argu);

   /**
    * <PRE>
    * nodeToken -> &lt;CHARACTER&gt;
    * </PRE>
    */
   public R visit(character_literal n, A argu);

   /**
    * <PRE>
    * nodeChoice -> &lt;FLOATONE&gt;
    *       | &lt;FLOATTWO&gt;
    * </PRE>
    */
   public R visit(floating_pt_literal n, A argu);

}
