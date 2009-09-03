Imports RTC
Imports omg.org.RTC



Public Class Test
    Inherits DataFlowComponentBase

    Private m_inInData As TimedFloatSeq = New TimedFloatSeq()
    Private m_inInPort As InPort(Of TimedFloatSeq, RingBuffer(Of TimedFloatSeq))
    Private m_outOutData As TimedFloatSeq = New TimedFloatSeq()
    Private m_outOutPort As OutPort(Of TimedFloatSeq, RingBuffer(Of TimedFloatSeq))

    Private m_myserviceCons As CorbaConsumerBase = New CorbaConsumerBase()
    Private m_MyServiceConsPort As CorbaPort = New CorbaPort("myserviceCons")



    Public Sub New(ByVal manager As Manager)
        MyBase.New(manager)
        m_inInPort = New InPort(Of TimedFloatSeq, RingBuffer(Of TimedFloatSeq))("in", m_inInData)
        registerInPort("in", m_inInPort)
        m_outOutPort = New OutPort(Of TimedFloatSeq, RingBuffer(Of TimedFloatSeq))("out", m_outOutData)
        registerOutPort("out", m_outOutPort)

        m_MyServiceConsPort.registerConsumer("myservice", "MyService", m_myserviceCons)
        registerPort(m_MyServiceConsPort)

    End Sub

    Protected Overrides Function onInitialize() As ReturnCode_t

        Return ReturnCode_t.RTC_OK
    End Function

    'Protected Overrides Function onFinalize() As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function

    'Protected Overrides Function onStartup(ByVal ec_id As Integer) As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function

    'Protected Overrides Function onShutdown(ByVal ec_id As Integer) As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function

    'Protected Overrides Function onActivated(ByVal ec_id As Integer) As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function

    'Protected Overrides Function onDeactivated(ByVal ec_id As Integer) As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function

    'Protected Overrides Function onExecute(ByVal ec_id As Integer) As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function

    'Protected Overrides Function onAborting(ByVal ec_id As Integer) As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function

    'Protected Overrides Function onError(ByVal ec_id As Integer) As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function

    'Protected Overrides Function onReset(ByVal ec_id As Integer) As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function

    'Protected Overrides Function onStateUpdate(ByVal ec_id As Integer) As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function

    'Protected Overrides Function onRateChanged(ByVal ec_id As Integer) As ReturnCode_t
    '    Return ReturnCode_t.RTC_OK
    'End Function
End Class

Public Class TestInit

    Private spec() As String = New String() _
    { _
        "implementation_id", "Test", _
        "type_name",         "Test", _
        "description",       "Test Component.", _
        "version",           "1.0.0", _
        "vendor",            "S.Kurihara", _
        "category",          "exmple", _
        "activity_type",     "PERIODIC", _
        "max_instance",      "10", _
        "language",          "Visual Basic", _
        "lang_type",         "COMPILE" _
    }

    Public Sub New(ByVal manager As Manager)
        Dim profile As Properties = New Properties(spec)
        manager.registerFactory(profile, _
            New RtcNewFunc(AddressOf CreateTest), _
            New RtcDeleteFunc(AddressOf DeleteTest))
    End Sub

    Public Function CreateTest(ByVal manager As Manager) As RTObject_impl
        Return New Test(manager)
    End Function

    Public Sub DeleteTest(ByVal rtc As RTObject_impl)
        rtc.Dispose()
    End Sub

End Class
