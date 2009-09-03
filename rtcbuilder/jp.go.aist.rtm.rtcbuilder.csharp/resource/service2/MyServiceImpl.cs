using System;
using System.Collections.Generic;
using System.Text;

namespace foo
{
    public class MyServiceImpl : MarshalByRefObject, MyService
    {
        public void setGain(System.Single gain)
        {
            throw new Exception("The method or operation is not implemented.");
        }
        public System.Single getGain()
        {
            throw new Exception("The method or operation is not implemented.");
        }
    }
}
