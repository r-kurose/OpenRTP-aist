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




        public foo(Manager manager)
            : base(manager)
        {
            m_InP1InPort = new InPort<TimedShort, RingBuffer<TimedShort>>("InP1", m_InP1InData);
            registerInPort("InP1", m_InP1InPort);
            m_InP2InPort = new InPort<TimedLong, RingBuffer<TimedLong>>("InP2", m_InP2InData);
            registerInPort("InP2", m_InP2InPort);


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
