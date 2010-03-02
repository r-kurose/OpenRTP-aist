using System;
using System.Collections.Generic;
using System.Text;

using omg.org.RTC;
using RTC;
using org.omg.SDOPackage;

namespace foo
{
    class Program
    {
        static void Main(string[] args)
        {

            Manager manager = Manager.init(args);

            manager.setModuleInitProc(MyModuleInit);

            bool ret = manager.activateManager();

            manager.runManager();
        }

        static void MyModuleInit(Manager manager)
        {
            new fooInit(manager);

            // Create a component
            Console.Write("Creating a component: \"foo\"....");
            RTObject_impl comp = manager.createComponent("foo");
            Console.WriteLine("succeed.");

            ComponentProfile prof;
            prof = comp.get_component_profile();
            Console.WriteLine("=================================================");
            Console.WriteLine(" Component Profile");
            Console.WriteLine("-------------------------------------------------");
            Console.WriteLine("InstanceID:     " + prof.instance_name);
            Console.WriteLine("Implementation: " + prof.type_name);
            Console.WriteLine("Description:    " + prof.description);
            Console.WriteLine("Version:        " + prof.version);
            Console.WriteLine("Maker:          " + prof.vendor);
            Console.WriteLine("Category:       " + prof.category);
            Console.WriteLine("  Other properties   ");
            foreach (NameValue nv in prof.properties)
            {
                Console.WriteLine("    " + nv.name + ": " + ((omg.org.CORBA.Any)nv.value).Value);
            }
            Console.WriteLine("=================================================");

            Port[] portlist;
            portlist = comp.get_ports();

            foreach (Port port in portlist)
            {
                Console.WriteLine("=================================================");
                Console.WriteLine("Port (name): " + port.get_port_profile().name);
                Console.WriteLine("-------------------------------------------------");
                PortInterfaceProfile[] iflist;
                iflist = port.get_port_profile().interfaces;

                foreach (PortInterfaceProfile ifprof in iflist)
                {
                    Console.WriteLine("I/F name: " + ifprof.instance_name);
                    Console.WriteLine("I/F type: " + ifprof.type_name);
                    PortInterfacePolarity pol = ifprof.polarity;

                    Console.WriteLine("Polarity: " + pol.ToString());
                }
                Console.WriteLine("- properties -");
                foreach (NameValue nv in port.get_port_profile().properties)
                {
                    Console.WriteLine("    " + nv.name + ": " + ((omg.org.CORBA.Any)nv.value).Value);
                }
                Console.WriteLine("-------------------------------------------------");
            }
            return;
        }
    }
}
