Imports RTC
Imports omg.org.RTC

Module Program

    Sub Main(ByVal args() As String)

        Dim manager As Manager = manager.init(args)

        manager.setModuleInitProc(New ModuleInitProc(AddressOf MyModuleInit))

        Dim ret As Boolean = manager.activateManager()

        manager.runManager()
    End Sub

    Sub MyModuleInit(ByVal manager As Manager)
        Dim init As barInit = New barInit(manager)

        Console.Write("Creating a component: bar....")
        Dim comp As RTObject_impl = manager.createComponent("bar")
        Console.WriteLine("succeed.")
    End Sub

End Module
