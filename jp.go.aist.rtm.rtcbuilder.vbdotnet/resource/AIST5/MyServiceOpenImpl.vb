Public Class MyServiceOpenImpl
    Inherits MarshalByRefObject
    Implements MyServiceOpen

    Public Sub AdOpen() Implements MyServiceOpen.AdOpen
        Throw New Exception("The method or operation is not implemented.")
    End Sub

End Class
