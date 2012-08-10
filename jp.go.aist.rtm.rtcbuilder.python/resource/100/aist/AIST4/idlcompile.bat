echo off
setlocal
for %%I in (python.exe) do if exist %%~$path:I set f=%%~$path:I
if exist hoge do (
  set mypythonpath=%f:python.exe=%
  omniidl.exe -bpython -I"%RTM_ROOT%rtm\idl" idl/MyService.idl 
) else do (
  echo "python.exe" can not be found.
  echo Please modify PATH environmental variable for python command.
)
endlocal
