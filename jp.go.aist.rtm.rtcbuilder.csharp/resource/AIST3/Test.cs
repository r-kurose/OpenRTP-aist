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
        private TimedFloatSeq m_inInData = new TimedFloatSeq();
        private InPort<TimedFloatSeq, RingBuffer<TimedFloatSeq>> m_inInPort;
        private TimedFloatSeq m_outOutData = new TimedFloatSeq();
        private OutPort<TimedFloatSeq, RingBuffer<TimedFloatSeq>> m_outOutPort;

        private MyServiceImpl m_myservice0Prov = new MyServiceImpl();
        private CorbaPort m_MyServiceProvPort = new CorbaPort("myservice0Prov");
        private CorbaConsumerBase m_myservice1Cons = new CorbaConsumerBase();
        private CorbaPort m_MyServiceConsPort = new CorbaPort("myservice1Cons");



        public Test(Manager manager)
            : base(manager)
        {
            m_inInPort = new InPort<TimedFloatSeq, RingBuffer<TimedFloatSeq>>("in", m_inInData);
            registerInPort("in", m_inInPort);
            m_outOutPort = new OutPort<TimedFloatSeq, RingBuffer<TimedFloatSeq>>("out", m_outOutData);
            registerOutPort("out", m_outOutPort);

            m_MyServiceProvPort.registerProvider("myservice0", "MyService", m_myservice0Prov);
            registerPort(m_MyServiceProvPort);
            m_MyServiceConsPort.registerConsumer("myservice1", "MyService", m_myservice1Cons);
            registerPort(m_MyServiceConsPort);

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
            "category",          "example",
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
