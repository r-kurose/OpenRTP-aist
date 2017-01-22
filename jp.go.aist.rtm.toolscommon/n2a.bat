for %%A in (%1) do (
  "%JAVA_HOME%\bin\native2ascii.exe" %%A %%A.tmp
  del %%A
  move %%A.tmp %%A
)
