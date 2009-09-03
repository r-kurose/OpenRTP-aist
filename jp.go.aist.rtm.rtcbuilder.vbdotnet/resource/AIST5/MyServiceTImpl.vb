Public Class MyServiceTImpl
    Inherits MarshalByRefObject
    Implements MyServiceT
    Public Function echo(ByVal msg As System.String) As System.String Implements MyServiceT.echo
        Throw New Exception("The method or operation is not implemented.")
    End Function

    Public Function get_echo_history() As System.String() Implements MyServiceT.get_echo_history
        Throw New Exception("The method or operation is not implemented.")
    End Function

    Public Sub set_value(ByVal value As System.Single) Implements MyServiceT.set_value
        Throw New Exception("The method or operation is not implemented.")
    End Sub

    Public Function get_value() As System.Single Implements MyServiceT.get_value
        Throw New Exception("The method or operation is not implemented.")
    End Function

    Public Function get_value_history() As System.Single() Implements MyServiceT.get_value_history
        Throw New Exception("The method or operation is not implemented.")
    End Function

End Class
