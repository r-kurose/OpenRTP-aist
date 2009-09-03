Imports RTC
Imports omg.org.RTC



Public Class foo
    Inherits DataFlowComponentBase



    Private m_int_param0Conf As int = New int()
    Private Function int_param0Trans(ByVal str As String) As Boolean
                Return int.TryParse(str, m_int_param0Conf)
    End Function
    Private m_int_param1Conf As int = New int()
    Private Function int_param1Trans(ByVal str As String) As Boolean
                Return int.TryParse(str, m_int_param1Conf)
    End Function
    Private m_double_param0Conf As double = New double()
    Private Function double_param0Trans(ByVal str As String) As Boolean
                Return double.TryParse(str, m_double_param0Conf)
    End Function
    Private m_str_param0Conf As string = New string()
    Private Function str_param0Trans(ByVal str As String) As Boolean
                m_str_param0Conf = str
                Return True
    End Function
    Private m_str_param1Conf As string = New string()
    Private Function str_param1Trans(ByVal str As String) As Boolean
                m_str_param1Conf = str
                Return True
    End Function


    Public Sub New(ByVal manager As Manager)
        MyBase.New(manager)


    End Sub

    Protected Overrides Function onInitialize() As ReturnCode_t
        bindParameter("int_param0", "0", New RTC.Config.TransFunc(AddressOf int_param0Trans))
        bindParameter("int_param1", "1", New RTC.Config.TransFunc(AddressOf int_param1Trans))
        bindParameter("double_param0", "0.11", New RTC.Config.TransFunc(AddressOf double_param0Trans))
        bindParameter("str_param0", "hoge", New RTC.Config.TransFunc(AddressOf str_param0Trans))
        bindParameter("str_param1", "dara", New RTC.Config.TransFunc(AddressOf str_param1Trans))

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
        "version",           "1.0.3", _
        "vendor",            "TA2", _
        "category",          "manip2", _
        "activity_type",     "DataFlowComponent", _
        "max_instance",      "3", _
        "language",          "Visual Basic", _
        "lang_type",         "COMPILE" _
        , "conf.default.int_param0",   "0" _
        , "conf.default.int_param1",   "1" _
        , "conf.default.double_param0",   "0.11" _
        , "conf.default.str_param0",   "hoge" _
        , "conf.default.str_param1",   "dara" _
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
