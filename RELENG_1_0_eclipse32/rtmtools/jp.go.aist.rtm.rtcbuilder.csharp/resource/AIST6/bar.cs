using System;
using System.Collections.Generic;
using System.Text;

using omg.org.RTC;
using RTC;
using org.omg.SDOPackage;



namespace bar
{

    public class bar
        : DataFlowComponentBase
    {
        private TimedShort m_InP1InData = new TimedShort();
        private InPort<TimedShort, RingBuffer<TimedShort>> m_InP1InPort;
        private TimedLong m_InP2InData = new TimedLong();
        private InPort<TimedLong, RingBuffer<TimedLong>> m_InP2InPort;
        private TimedDouble m_OutP1OutData = new TimedDouble();
        private OutPort<TimedDouble, RingBuffer<TimedDouble>> m_OutP1OutPort;
        private TimedFloat m_OutP2OutData = new TimedFloat();
        private OutPort<TimedFloat, RingBuffer<TimedFloat>> m_OutP2OutPort;

        private testImpl m_accProv = new testImpl();
        private CorbaPort m_testProvPort = new CorbaPort("accProv");



        public bar(Manager manager)
            : base(manager)
        {
            m_InP1InPort = new InPort<TimedShort, RingBuffer<TimedShort>>("InP1", m_InP1InData);
            registerInPort("InP1", m_InP1InPort);
            m_InP2InPort = new InPort<TimedLong, RingBuffer<TimedLong>>("InP2", m_InP2InData);
            registerInPort("InP2", m_InP2InPort);
            m_OutP1OutPort = new OutPort<TimedDouble, RingBuffer<TimedDouble>>("OutP1", m_OutP1OutData);
            registerOutPort("OutP1", m_OutP1OutPort);
            m_OutP2OutPort = new OutPort<TimedFloat, RingBuffer<TimedFloat>>("OutP2", m_OutP2OutData);
            registerOutPort("OutP2", m_OutP2OutPort);

            m_testProvPort.registerProvider("acc", "test", m_accProv);
            registerPort(m_testProvPort);

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

    public class barInit
    {
        public static string[] spec = new string[]
        {
            "implementation_id", "bar",
            "type_name",         "bar",
            "description",       "bartest",
            "version",           "2.0",
            "vendor",            "Tec",
            "category",          "same",
            "activity_type",     "SPORADIC",
            "max_instance",      "10",
            "language",          "C#",
            "lang_type",         "COMPILE"
        };

        public barInit(Manager manager)
        {
            Properties profile = new Properties(spec);
            manager.registerFactory(profile,
                delegate(Manager mgr) { return new bar(mgr); },
                delegate(RTObject_impl rtc) { rtc.Dispose(); });
        }
    }
}
