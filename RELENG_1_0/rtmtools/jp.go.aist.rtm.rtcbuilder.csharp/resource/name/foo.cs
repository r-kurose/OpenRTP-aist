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




        public foo(Manager manager)
            : base(manager)
        {


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
            "activity_type",     "SPORADIC",
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
