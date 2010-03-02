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
        private TimedLong m_InP2InData = new TimedLong();
        private InPort<TimedLong, RingBuffer<TimedLong>> m_InP2InPort;
        private TimedFloat m_OutP1OutData = new TimedFloat();
        private OutPort<TimedFloat, RingBuffer<TimedFloat>> m_OutP1OutPort;
        private TimedDouble m_OutP2OutData = new TimedDouble();
        private OutPort<TimedDouble, RingBuffer<TimedDouble>> m_OutP2OutPort;

        private MyServiceImpl m_accProv = new MyServiceImpl();
        private CorbaPort m_MyServiceProvPort = new CorbaPort("accProv");



        public foo(Manager manager)
            : base(manager)
        {
            m_InP1InPort = new InPort<TimedShort, RingBuffer<TimedShort>>("InP1", m_InP1InData);
            registerInPort("InP1", m_InP1InPort);
            m_InP2InPort = new InPort<TimedLong, RingBuffer<TimedLong>>("InP2", m_InP2InData);
            registerInPort("InP2", m_InP2InPort);
            m_OutP1OutPort = new OutPort<TimedFloat, RingBuffer<TimedFloat>>("OutP1", m_OutP1OutData);
            registerOutPort("OutP1", m_OutP1OutPort);
            m_OutP2OutPort = new OutPort<TimedDouble, RingBuffer<TimedDouble>>("OutP2", m_OutP2OutData);
            registerOutPort("OutP2", m_OutP2OutPort);

            m_MyServiceProvPort.registerProvider("acc", "MyService", m_accProv);
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

    public class fooInit
    {
        public static string[] spec = new string[]
        {
            "implementation_id", "foo",
            "type_name",         "foo",
            "description",       "MDesc",
            "version",           "1.0.1",
            "vendor",            "TA",
            "category",          "Manip",
            "activity_type",     "PERIODIC",
            "max_instance",      "5",
            "language",          "C#",
            "lang_type",         "COMPILE"
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
