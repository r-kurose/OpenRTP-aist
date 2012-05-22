@echo off
rem ---------------------------------------------------------------------------
rem ---------------------------------------------------------------------------
@set JARDIR=rtcbuilder_1.1.0
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
    echo -jp.go.aist.rtm.toolscommon.profilesi.nl1 doesn't exist.
)

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
@if exist jp.go.aist.rtm.rtcbuilder (
    echo -jp.go.aist.rtm.rtcbuilder
    cd jp.go.aist.rtm.rtcbuilder
    call ant buildAll %LIBS%
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\%JARDIR%
    cd ..
) else (
    echo -jp.go.aist.rtm.rtcbuilder doesn't exist.
)

rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist jp.go.aist.rtm.rtcbuilder.nl1 (
    echo -jp.go.aist.rtm.rtcbuilder.nl1
    cd jp.go.aist.rtm.rtcbuilder.nl1
    call ant buildAll %LIBS%
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\%JARDIR%
    cd ..
) else (
    echo -jp.go.aist.rtm.rtcbuilder.nl1 doesn't exist.
)

rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist jp.go.aist.rtm.rtcbuilder.java (
    echo -jp.go.aist.rtm.rtcbuilder.java
    cd jp.go.aist.rtm.rtcbuilder.java
    call ant buildAll %LIBS%
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\%JARDIR%
    cd ..
) else (
    echo -jp.go.aist.rtm.rtcbuilder.java doesn't exist.
)

rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist jp.go.aist.rtm.rtcbuilder.python (
    echo -jp.go.aist.rtm.rtcbuilder.python
    cd jp.go.aist.rtm.rtcbuilder.python
    call ant buildAll %LIBS%
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\%JARDIR%
    cd ..
) else (
    echo -jp.go.aist.rtm.rtcbuilder.python doesn't exist.
)

rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist jp.go.aist.rtm.rtcbuilder.vbdotnet (
    echo -jp.go.aist.rtm.rtcbuilder.vbdotnet
    cd jp.go.aist.rtm.rtcbuilder.vbdotnet
    call ant buildAll %LIBS%
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\%JARDIR%
    cd ..
) else (
    echo -jp.go.aist.rtm.rtcbuilder.vbdotnet doesn't exist.
)

rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist jp.go.aist.rtm.rtcbuilder.csharp (
    echo -jp.go.aist.rtm.rtcbuilder.csharp
    cd jp.go.aist.rtm.rtcbuilder.csharp
    call ant buildAll %LIBS%
    echo --
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\%JARDIR%
    cd ..
) else (
    echo -jp.go.aist.rtm.rtcbuilder.csharp doesn't exist.
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
cd..

:END
