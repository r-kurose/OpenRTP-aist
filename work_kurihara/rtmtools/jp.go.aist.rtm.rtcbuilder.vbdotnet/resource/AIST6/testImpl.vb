Public Class testImpl
    Inherits MarshalByRefObject
    Implements test
    Public Sub _Set(ByVal Data As System.Int16) Implements test._Set
        Throw New Exception("The method or operation is not implemented.")
    End Sub

End Class
