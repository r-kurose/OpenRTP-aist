using System;
using System.Collections.Generic;
using System.Text;

namespace foo
{
    public class testImpl : MarshalByRefObject, test
    {
        public void _Set(System.Int16[] Data)
        {
            throw new Exception("The method or operation is not implemented.");
        }
        public System.Int32 Set2(System.Int16[] Data)
        {
            throw new Exception("The method or operation is not implemented.");
        }
    }
}
