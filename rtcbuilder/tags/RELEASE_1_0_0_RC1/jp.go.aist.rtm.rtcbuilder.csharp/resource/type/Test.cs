using System;
using System.Collections.Generic;
using System.Text;

using omg.org.RTC;
using RTC;
using org.omg.SDOPackage;



namespace Test
{

    public class Test
        : DataFlowComponentBase
    {

        private MyServiceImpl m_meserviceProv = new MyServiceImpl();
        private CorbaPort m_MyServiceProvPort = new CorbaPort("meserviceProv");



        public Test(Manager manager)
            : base(manager)
        {

            m_MyServiceProvPort.registerProvider("meservice", "MyService", m_meserviceProv);
            registerPort(m_MyServiceProvPort);

        }

        protected override ReturnCode_t onInitialize()
        {

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

    public class TestInit
    {
        public static string[] spec = new string[]
        {
            "implementation_id", "Test",
            "type_name",         "Test",
            "description",       "Test Component.",
            "version",           "1.0.0",
            "vendor",            "S.Kurihara",
            "category",          "exmple",
            "activity_type",     "PERIODIC",
            "max_instance",      "1",
            "language",          "C#",
            "lang_type",         "COMPILE"
        };

        public TestInit(Manager manager)
        {
            Properties profile = new Properties(spec);
            manager.registerFactory(profile,
                delegate(Manager mgr) { return new Test(mgr); },
                delegate(RTObject_impl rtc) { rtc.Dispose(); });
        }
    }
}
