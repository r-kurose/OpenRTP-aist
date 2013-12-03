set IDL_DIR=.
set IDL_FILE=OpenRTMNaming.idl
set IDL_OUTPUT=..\src
set IDL_KEEP=-keep
idlj.exe -v -pkgTranslate CosNaming org.omg.CosNaming -fall -td %IDL_OUTPUT% %IDL_KEEP% -i %IDL_DIR% %IDL_DIR%\%IDL_FILE%
pause
