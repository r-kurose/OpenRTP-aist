using System;
using System.Collections.Generic;
using System.Text;

namespace Test
{
    public class MyServiceOpenImpl : MarshalByRefObject, MyServiceOpen
    {
        public void AdOpen()
        {
            throw new Exception("The method or operation is not implemented.");
        }
    }
}
