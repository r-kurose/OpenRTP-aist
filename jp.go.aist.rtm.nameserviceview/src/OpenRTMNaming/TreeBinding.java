package OpenRTMNaming;


/**
* OpenRTMNaming/TreeBinding.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: ./OpenRTMNaming.idl
* 2011年2月10日 18時15分14秒 JST
*/

public final class TreeBinding implements org.omg.CORBA.portable.IDLEntity
{
  public org.omg.CosNaming.NameComponent binding_name[] = null;
  public org.omg.CosNaming.BindingType binding_type = null;
  public org.omg.CORBA.Object binding_object = null;
  public OpenRTMNaming.TreeBinding binding_children[] = null;

  public TreeBinding ()
  {
  } // ctor

  public TreeBinding (org.omg.CosNaming.NameComponent[] _binding_name, org.omg.CosNaming.BindingType _binding_type, org.omg.CORBA.Object _binding_object, OpenRTMNaming.TreeBinding[] _binding_children)
  {
    binding_name = _binding_name;
    binding_type = _binding_type;
    binding_object = _binding_object;
    binding_children = _binding_children;
  } // ctor

} // class TreeBinding
