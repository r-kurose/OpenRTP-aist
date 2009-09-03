Public Class MyServiceImpl
    Inherits MarshalByRefObject
    Implements MyService
    Public Sub setGain(ByVal gain As System.Single) Implements MyService.setGain
        Throw New Exception("The method or operation is not implemented.")
    End Sub

    Public Function getGain() As System.Single Implements MyService.getGain
        Throw New Exception("The method or operation is not implemented.")
    End Function

End Class
