Imports RTC
Imports omg.org.RTC



Public Class foo
    Inherits DataFlowComponentBase

    Private m_InP1InData As TimedShort = New TimedShort()
    Private m_InP1InPort As InPort(Of TimedShort, RingBuffer(Of TimedShort))




    Public Sub New(ByVal manager As Manager)
        MyBase.New(manager)
        m_InP1InPort = New InPort(Of TimedShort, RingBuffer(Of TimedShort))("InP1", m_InP1InData)
        registerInPort("InP1", m_InP1InPort)


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
