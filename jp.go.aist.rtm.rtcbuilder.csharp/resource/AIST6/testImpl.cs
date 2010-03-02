using System;
using System.Collections.Generic;
using System.Text;

namespace bar
{
    public class testImpl : MarshalByRefObject, test
    {
        public void _Set(System.Int16[] Data)
        {
            throw new Exception("The method or operation is not implemented.");
        }
    }
}
