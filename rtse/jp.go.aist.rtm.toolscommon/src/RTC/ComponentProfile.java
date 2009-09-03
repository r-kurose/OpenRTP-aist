package RTC;


/**
* RTC/ComponentProfile.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分47秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief ComponentProfile
   *
   * @section Description
   *
   * ComponentProfile represents the static state of an RTC that is
   * referred to here as the "target" RTC.
   *
   * @endif
   */
public final class ComponentProfile implements org.omg.CORBA.portable.IDLEntity
{

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief instance_name
     *
     * @section Description
     *
     * This attribute shall contain the name of the target RTC instance.
     *
     * @section Semantics
     *
     * The instance_name should be unique among RTC instances
     * contained within the same containing component.
     *
     * @endif
     */
  public String instance_name = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief type_name
     *
     * @section Description
     *
     * This attribute shall contain the name of the target RTC class.
     *
     * @section Semantics
     *
     * Each RTC class must have a name that is unique within an
     * application.
     *
     * @endif
     */
  public String type_name = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief description
     *
     * @section Description
     *
     * This attribute shall briefly describe the target RTC for the
     * benefit of a human operator.
     *
     * @endif
     */
  public String description = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief version
     *
     * @section Description
     *
     * This attribute shall contain the version number of the target
     * RTC class.
     *
     * @section Semantics
     *
     * The format of the version number is outside of the scope of
     * this specification.
     *
     * @endif
     */
  public String version = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief vendor
     *
     * @section Description
     *
     * The name of the individual or organization that produced the
     * target RTC class.
     *
     * @endif
     */
  public String vendor = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief category
     *
     * @section Description
     *
     * This attribute contains the name of a "category" or group to
     * which the target RTC belongs.
     *
     *
     * @endif
     */
  public String category = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief port_profiles
     *
     * @section Description
     *
     * This attribute contains a list of PortProfiles that describe
     * the ports of the target RTC.
     *
     * @section Semantics
     *
     * There shall be a one-to-one correspondence between the members
     * of this list and the ports of the target RTC.
     *
     *
     * @endif
     */
  public RTC.PortProfile port_profiles[] = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief parent
     *
     * @section Description
     *
     * This attribute contains a reference to the RTC that contains
     * the target RTC instance. If the target RTC instance is not
     * owned by any other RTC, this field stores a nil reference.
     *
     * @endif
     */
  public RTC.RTObject parent = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief properties
     *
     * @section Description
     *
     * This attribute contains additional properties of the target RTC.
     *
     * @section Semantics
     *
     * This attribute provides implementations the opportunity to
     * describe additional characteristics of a particular RTC that
     * are otherwise outside of the scope of this specification.
     *
     * @endif
     */
  public _SDOPackage.NameValue properties[] = null;

  public ComponentProfile ()
  {
  } // ctor

  public ComponentProfile (String _instance_name, String _type_name, String _description, String _version, String _vendor, String _category, RTC.PortProfile[] _port_profiles, RTC.RTObject _parent, _SDOPackage.NameValue[] _properties)
  {
    instance_name = _instance_name;
    type_name = _type_name;
    description = _description;
    version = _version;
    vendor = _vendor;
    category = _category;
    port_profiles = _port_profiles;
    parent = _parent;
    properties = _properties;
  } // ctor

} // class ComponentProfile
