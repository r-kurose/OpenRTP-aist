Imports RTC
Imports omg.org.RTC



Public Class foo
    Inherits DataFlowComponentBase

    Private m_InP1InData As TimedShort = New TimedShort()
    Private m_InP1InPort As InPort(Of TimedShort, RingBuffer(Of TimedShort))
    Private m_InP2InData As TimedLong = New TimedLong()
    Private m_InP2InPort As InPort(Of TimedLong, RingBuffer(Of TimedLong))
    Private m_OutP1OutData As TimedFloat = New TimedFloat()
    Private m_OutP1OutPort As OutPort(Of TimedFloat, RingBuffer(Of TimedFloat))
    Private m_OutP2OutData As TimedDouble = New TimedDouble()
    Private m_OutP2OutPort As OutPort(Of TimedDouble, RingBuffer(Of TimedDouble))

    Private m_accProv As MyServiceImpl  = New MyServiceImpl()
    Private m_MyServiceProvPort As CorbaPort = New CorbaPort("accProv")
    Private m_rateCons As CorbaConsumerBase = New CorbaConsumerBase()
    Private m_DAQServiceConsPort As CorbaPort = New CorbaPort("rateCons")



    Public Sub New(ByVal manager As Manager)
        MyBase.New(manager)
        m_InP1InPort = New InPort(Of TimedShort, RingBuffer(Of TimedShort))("InP1", m_InP1InData)
        registerInPort("InP1", m_InP1InPort)
        m_InP2InPort = New InPort(Of TimedLong, RingBuffer(Of TimedLong))("InP2", m_InP2InData)
        registerInPort("InP2", m_InP2InPort)
        m_OutP1OutPort = New OutPort(Of TimedFloat, RingBuffer(Of TimedFloat))("OutP1", m_OutP1OutData)
        registerOutPort("OutP1", m_OutP1OutPort)
        m_OutP2OutPort = New OutPort(Of TimedDouble, RingBuffer(Of TimedDouble))("OutP2", m_OutP2OutData)
        registerOutPort("OutP2", m_OutP2OutPort)

        m_MyServiceProvPort.registerProvider("acc", "MyService", m_accProv)
        registerPort(m_MyServiceProvPort)
        m_DAQServiceConsPort.registerConsumer("rate", "DAQService", m_rateCons)
        registerPort(m_DAQServiceConsPort)

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

Public Class fooInit

    Private spec() As String = New String() _
    { _
        "implementation_id", "foo", _
        "type_name",         "foo", _
        "description",       "MDesc", _
        "version",           "1.0.1", _
        "vendor",            "TA", _
        "category",          "Manip", _
        "activity_type",     "PERIODIC", _
        "max_instance",      "5", _
        "language",          "Visual Basic", _
        "lang_type",         "COMPILE" _
    }

    Public Sub New(ByVal manager As Manager)
        Dim profile As Properties = New Properties(spec)
        manager.registerFactory(profile, _
            New RtcNewFunc(AddressOf Createfoo), _
            New RtcDeleteFunc(AddressOf Deletefoo))
    End Sub

    Public Function Createfoo(ByVal manager As Manager) As RTObject_impl
        Return New foo(manager)
    End Function

    Public Sub Deletefoo(ByVal rtc As RTObject_impl)
        rtc.Dispose()
    End Sub

End Class
