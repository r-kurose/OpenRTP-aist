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


        private int m_int_param0Conf = new int();
        private List<float> m_float_param0Conf = new List<float>();


        public foo(Manager manager)
            : base(manager)
        {


        }

        protected override ReturnCode_t onInitialize()
        {
            bindParameter("int_param0", "0", delegate(string str)
            {
                return int.TryParse(str, out m_int_param0Conf);
            });
            bindParameter("float_param0", "1.0,2.0,3.0", delegate(string str)
            {
                String[] items = str.Split(new char[] {','});
                m_float_param0Conf.Clear();
                Boolean ret = true;
                foreach(String item in items)
                {
                    float temp;
                    if(!float.TryParse(item, out temp))
                    {
                        ret = false;
                        continue;
                    }
                    m_float_param0Conf.Add(temp);
                }
                return ret;
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
            "activity_type",     "PERIODIC",
            "max_instance",      "3",
            "language",          "C#",
            "lang_type",         "COMPILE"
        , "conf.default.int_param0",   "0"
        , "conf.default.float_param0",   "1.0,2.0,3.0"
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
