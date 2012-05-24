@echo off
rem ---------------------------------------------------------------------------
rem ---------------------------------------------------------------------------
@set JARDIR=rtsystemeditor_1.1.0
@set LIBS=-lib ..\lib -lib %ECLIPSE_HOME%\plugins

rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist %JARDIR% (
	rd /S /Q %JARDIR%
)
mkdir %JARDIR%
rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist jp.go.aist.rtm.toolscommon (
    echo -jp.go.aist.rtm.toolscommon
    cd jp.go.aist.rtm.toolscommon
    call ant buildAll %LIBS%
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\%JARDIR%
    cd ..
) else (
    echo -jp.go.aist.rtm.toolscommon doesn't exist.
)

rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist jp.go.aist.rtm.toolscommon.nl1 (
    echo -jp.go.aist.rtm.toolscommon.nl1
    cd jp.go.aist.rtm.toolscommon.nl1
    call ant buildAll %LIBS%
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\%JARDIR%
    cd ..
) else (
    echo -jp.go.aist.rtm.toolscommon.nl1 doesn't exist.
)

rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist jp.go.aist.rtm.toolscommon.profiles (
    echo -jp.go.aist.rtm.toolscommon.profiles
    cd jp.go.aist.rtm.toolscommon.profiles
    call ant buildAll %LIBS%
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\%JARDIR%
    cd ..
) else (
    echo -jp.go.aist.rtm.toolscommon.profiles doesn't exist.
)

rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist jp.go.aist.rtm.toolscommon.profiles.nl1 (
    echo -jp.go.aist.rtm.toolscommon.profiles.nl1
    cd jp.go.aist.rtm.toolscommon.profiles.nl1
    call ant buildAll %LIBS%
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\%JARDIR%
    cd ..
) else (
    echo -jp.go.aist.rtm.toolscommon.profiles.nl1 doesn't exist.
)

rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist jp.go.aist.rtm.repositoryView (
    echo -jp.go.aist.rtm.repositoryView
    cd jp.go.aist.rtm.repositoryView
    call ant buildAll %LIBS%
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\%JARDIR%
    cd ..
) else (
    echo -jp.go.aist.rtm.repositoryView doesn't exist.
)

rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist jp.go.aist.rtm.repositoryView.nl1 (
    echo -jp.go.aist.rtm.repositoryView.nl1
    cd jp.go.aist.rtm.repositoryView.nl1
    call ant buildAll %LIBS%
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\%JARDIR%
    cd ..
) else (
    echo -jp.go.aist.rtm.repositoryView.nl1 doesn't exist.
)

rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist jp.go.aist.rtm.nameserviceview (
    echo -jp.go.aist.rtm.nameserviceview 
    cd jp.go.aist.rtm.nameserviceview 
    call ant buildAll %LIBS%
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\%JARDIR%
    cd ..
) else (
    echo -jp.go.aist.rtm.nameserviceview doesn't exist.
)

rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist jp.go.aist.rtm.nameserviceview.nl1 (
    echo -jp.go.aist.rtm.nameserviceview.nl1 
    cd jp.go.aist.rtm.nameserviceview.nl1 
    call ant buildAll %LIBS%
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\%JARDIR%
    cd ..
) else (
    echo -jp.go.aist.rtm.nameserviceview.nl1 doesn't exist.
)

rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist jp.go.aist.rtm.systemeditor (
    echo -jp.go.aist.rtm.systemeditor
    cd jp.go.aist.rtm.systemeditor
    call ant buildAll %LIBS%
    echo --
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\%JARDIR%
    cd ..
) else (
    echo -jp.go.aist.rtm.systemeditor doesn't exist.
)

rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist jp.go.aist.rtm.systemeditor.nl1 (
    echo -jp.go.aist.rtm.systemeditor.nl1
    cd jp.go.aist.rtm.systemeditor.nl1
    call ant buildAll %LIBS%
    echo --
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\%JARDIR%
    cd ..
) else (
    echo -jp.go.aist.rtm.systemeditor.nl1 doesn't exist.
)

rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist zip.vbs (
	del %JARDIR%.zip
	CScript.exe .\zip.vbs\MakeZIP.VBS %JARDIR%.zip %JARDIR%
	rd /S /Q %JARDIR%
)
@goto END

:FAIL
cd ..

:END

