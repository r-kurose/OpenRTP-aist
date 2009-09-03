using System;
using System.Collections.Generic;
using System.Text;

namespace Test
{
    public class MyServiceImpl : MarshalByRefObject, MyService
    {
        public System.String echo(System.String msg)
        {
            throw new Exception("The method or operation is not implemented.");
        }
        public System.String[] get_echo_history()
        {
            throw new Exception("The method or operation is not implemented.");
        }
        public void set_value(System.Single value)
        {
            throw new Exception("The method or operation is not implemented.");
        }
        public System.Single get_value()
        {
            throw new Exception("The method or operation is not implemented.");
        }
        public System.Single[] get_value_history()
        {
            throw new Exception("The method or operation is not implemented.");
        }
    }
}
