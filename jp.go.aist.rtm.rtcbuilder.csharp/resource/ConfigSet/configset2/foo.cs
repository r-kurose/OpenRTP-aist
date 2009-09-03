using System;
using System.Collections.Generic;
using System.Text;

using omg.org.RTC;
using RTC;
using org.omg.SDOPackage;



namespace foo
{

    public class foo
        : DataFlowComponentBase
    {
        private TimedShort m_InP1InData = new TimedShort();
        private InPort<TimedShort, RingBuffer<TimedShort>> m_InP1InPort;

        private MyServiceImpl m_accProv = new MyServiceImpl();
        private CorbaPort m_MyServiceProvPort = new CorbaPort("accProv");

        private int m_int_param0Conf = new int();
        private int m_int_param1Conf = new int();


        public foo(Manager manager)
            : base(manager)
        {
            m_InP1InPort = new InPort<TimedShort, RingBuffer<TimedShort>>("InP1", m_InP1InData);
            registerInPort("InP1", m_InP1InPort);

            m_MyServiceProvPort.registerProvider("acc", "MyService", m_accProv);
            registerPort(m_MyServiceProvPort);

        }

        protected override ReturnCode_t onInitialize()
        {
            bindParameter("int_param0", "0", delegate(string str)
            {
                return int.TryParse(str, out m_int_param0Conf);
            });
            bindParameter("int_param1", "1", delegate(string str)
            {
                return int.TryParse(str, out m_int_param1Conf);
            });

            return ReturnCode_t.RTC_OK;
        }

        //protected override ReturnCode_t onFinalize()
        //{
        //    return ReturnCode_t.RTC_OK;
        //}

        //protected override ReturnCode_t onStartup(int ec_id)
        //{
        //    return ReturnCode_t.RTC_OK;
        //}

        //protected override ReturnCode_t onShutdown(int ec_id)
        //{
        //    return ReturnCode_t.RTC_OK;
        //}

        //protected override ReturnCode_t onActivated(int ec_id)
        //{
        //    return ReturnCode_t.RTC_OK;
        //}

        //protected override ReturnCode_t onDeactivated(int ec_id)
        //{
        //    return ReturnCode_t.RTC_OK;
        //}

        //protected override ReturnCode_t onExecute(int ec_id)
        //{
        //    return ReturnCode_t.RTC_OK;
        //}

        //protected override ReturnCode_t onAborting(int ec_id)
        //{
        //    return ReturnCode_t.RTC_OK;
        //}

        //protected override ReturnCode_t onError(int ec_id)
        //{
        //    return ReturnCode_t.RTC_OK;
        //}

        //protected override ReturnCode_t onReset(int ec_id)
        //{
        //    return ReturnCode_t.RTC_OK;
        //}

        //protected override ReturnCode_t onStateUpdate(int ec_id)
        //{
        //    return ReturnCode_t.RTC_OK;
        //}

        //protected override ReturnCode_t onRateChanged(int ec_id)
        //{
        //    return ReturnCode_t.RTC_OK;
        //}
    }

    public class fooInit
    {
        public static string[] spec = new string[]
        {
            "implementation_id", "foo",
            "type_name",         "foo",
            "description",       "MDesc",
            "version",           "1.0.3",
            "vendor",            "TA2",
            "category",          "manip2",
            "activity_type",     "DataFlowComponent",
            "max_instance",      "3",
            "language",          "C#",
            "lang_type",         "COMPILE"
        , "conf.default.int_param0",   "0"
        , "conf.default.int_param1",   "1"
        };

        public fooInit(Manager manager)
        {
            Properties profile = new Properties(spec);
            manager.registerFactory(profile,
                delegate(Manager mgr) { return new foo(mgr); },
                delegate(RTObject_impl rtc) { rtc.Dispose(); });
        }
    }
}
