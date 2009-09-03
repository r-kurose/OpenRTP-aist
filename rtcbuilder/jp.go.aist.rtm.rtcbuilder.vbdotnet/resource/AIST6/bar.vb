Imports RTC
Imports omg.org.RTC



Public Class bar
    Inherits DataFlowComponentBase

    Private m_InP1InData As TimedShort = New TimedShort()
    Private m_InP1InPort As InPort(Of TimedShort, RingBuffer(Of TimedShort))
    Private m_InP2InData As TimedLong = New TimedLong()
    Private m_InP2InPort As InPort(Of TimedLong, RingBuffer(Of TimedLong))
    Private m_OutP1OutData As TimedDouble = New TimedDouble()
    Private m_OutP1OutPort As OutPort(Of TimedDouble, RingBuffer(Of TimedDouble))
    Private m_OutP2OutData As TimedFloat = New TimedFloat()
    Private m_OutP2OutPort As OutPort(Of TimedFloat, RingBuffer(Of TimedFloat))

    Private m_accProv As testImpl  = New testImpl()
    Private m_testProvPort As CorbaPort = New CorbaPort("accProv")



    Public Sub New(ByVal manager As Manager)
        MyBase.New(manager)
        m_InP1InPort = New InPort(Of TimedShort, RingBuffer(Of TimedShort))("InP1", m_InP1InData)
        registerInPort("InP1", m_InP1InPort)
        m_InP2InPort = New InPort(Of TimedLong, RingBuffer(Of TimedLong))("InP2", m_InP2InData)
        registerInPort("InP2", m_InP2InPort)
        m_OutP1OutPort = New OutPort(Of TimedDouble, RingBuffer(Of TimedDouble))("OutP1", m_OutP1OutData)
        registerOutPort("OutP1", m_OutP1OutPort)
        m_OutP2OutPort = New OutPort(Of TimedFloat, RingBuffer(Of TimedFloat))("OutP2", m_OutP2OutData)
        registerOutPort("OutP2", m_OutP2OutPort)

        m_testProvPort.registerProvider("acc", "test", m_accProv)
        registerPort(m_testProvPort)

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

Public Class barInit

    Private spec() As String = New String() _
    { _
        "implementation_id", "bar", _
        "type_name",         "bar", _
        "description",       "bartest", _
        "version",           "2.0", _
        "vendor",            "Tec", _
        "category",          "same", _
        "activity_type",     "PERIODIC", _
        "max_instance",      "10", _
        "language",          "Visual Basic", _
        "lang_type",         "COMPILE" _
    }

    Public Sub New(ByVal manager As Manager)
        Dim profile As Properties = New Properties(spec)
        manager.registerFactory(profile, _
            New RtcNewFunc(AddressOf Createbar), _
            New RtcDeleteFunc(AddressOf Deletebar))
    End Sub

    Public Function Createbar(ByVal manager As Manager) As RTObject_impl
        Return New bar(manager)
    End Function

    Public Sub Deletebar(ByVal rtc As RTObject_impl)
        rtc.Dispose()
    End Sub

End Class
