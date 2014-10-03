//
// Generated by JTB 1.3.2
//

package jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree;

/**
 * Grammar production:
 * xor_expr -> xor_expr()
 * nodeListOptional -> ( "|" xor_expr() )*
 */
public class or_expr implements Node {
   private Node parent;
   public xor_expr xor_expr;
   public NodeListOptional nodeListOptional;

   public or_expr(xor_expr n0, NodeListOptional n1) {
      xor_expr = n0;
      if ( xor_expr != null ) xor_expr.setParent(this);
      nodeListOptional = n1;
      if ( nodeListOptional != null ) nodeListOptional.setParent(this);
   }

   public void accept(jp.go.aist.rtm.rtcbuilder.corba.idl.parser.visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(jp.go.aist.rtm.rtcbuilder.corba.idl.parser.visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(jp.go.aist.rtm.rtcbuilder.corba.idl.parser.visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(jp.go.aist.rtm.rtcbuilder.corba.idl.parser.visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
   public void setParent(Node n) { parent = n; }
   public Node getParent()       { return parent; }
}

