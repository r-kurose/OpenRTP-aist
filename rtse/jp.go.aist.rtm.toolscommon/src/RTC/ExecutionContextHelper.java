package RTC;


/**
* RTC/ExecutionContextHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分44秒 JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief ExecutionContext
   *
   * @section Description
   *
   * An ExecutionContext allows the business logic of an RTC to be
   * decoupled from the thread of control in which it is executed. The
   * context represents a logical thread of control and is provided to
   * RTCs at runtime as an argument to various operations, allowing
   * them to query and modify their own state, and that of other RTCs
   * executing within the same context, in the lifecycle.  This
   * separation of concerns is important for two primary reasons:
   *
   * - Large number of components may collaborate tightly within a
   * single node or process. If each component were to run within its
   * own thread of control, the infrastructure may not be able to
   * satisfy the timeliness and determinism requirements of real-time
   * applications due to the large number of threads and the required
   * synchronization between them.
   *
   * - A single application may carry out a number of independent
   * tasks that require different execution rates. For example, it may
   * need to sample a sensor periodically at a very high rate and update a
   * user interface at a much lower rate.
   *
   * @section Semantics
   *
   * The state machine of an ExecutionContext has two parts. The
   * behavior of the ExecutionContext itself is defined by the upper
   * region in the above figure. The behavior of the RTCs that
   * participate in the context is defined by the lower region. The
   * contents of that region are displayed in more detail in Figure
   * 5.5 in Section 5.2.2.2.  Ownership and Participation Each
   * execution context is owned by a single RTC and may be used to
   * execute that RTC and the RTCs contained within it, directly or
   * indirectly. An RTC that owns one or more execution contexts is
   * known as an autonomous RTC.  An autonomous RTC and some subset of
   * the RTCs within it (to be defined by the application developer)
   * shall be executed by the infrastructure according to the contexta?
   * s execution kind, which defines when each RTCa?s operations will
   * be invoked when and in which order. These RTCs are said to
   * participate in the context. The available execution kinds are
   * described in Section 5.2.2.7.  The relationship between RTCs and
   * execution contexts may be many-to-many in the general case:
   * multiple RTCs may be invoked from the same execution context, and
   * a single RTC may be invoked from multiple contexts. In the case
   * where multiple RTCs are invoked from the same context, starting
   * or stopping the context shall result in the corresponding
   * lifecycle transitions for all of those components.
   *
   * @section Logical and Physical Threads
   *
   * Although an execution context represents a logical thread of
   * control, the choice of how it maps to a physical thread shall be
   * left to the applicationa?s deployment
   * environment. Implementations may elect to associate contexts with
   * threads with a one-to-one mapping, to serve multiple contexts
   * from a single thread, or by any other means. In the case where a
   * given RTC may be invoked from multiple contexts, concurrency
   * management is implementation-dependent.
   *
   * @endif
   */
abstract public class ExecutionContextHelper
{
  private static String  _id = "IDL:omg.org/RTC/ExecutionContext:1.0";

  public static void insert (org.omg.CORBA.Any a, RTC.ExecutionContext that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTC.ExecutionContext extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (RTC.ExecutionContextHelper.id (), "ExecutionContext");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTC.ExecutionContext read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ExecutionContextStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTC.ExecutionContext value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static RTC.ExecutionContext narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof RTC.ExecutionContext)
      return (RTC.ExecutionContext)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      RTC._ExecutionContextStub stub = new RTC._ExecutionContextStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
