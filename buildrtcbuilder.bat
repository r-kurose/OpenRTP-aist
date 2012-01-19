@echo off
rem ---------------------------------------------------------------------------
rem ---------------------------------------------------------------------------
@set DUMMY=%ANT_HOME%
@set ANT_HOME=%ECLIPSE_HOME%\plugins\org.apache.ant_1.6.5\
rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist rtcbuilder_1.1.0 (
	rd /S /Q rtcbuilder_1.1.0
)
mkdir rtcbuilder_1.1.0
rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist jp.go.aist.rtm.toolscommon.profiles (
    echo -jp.go.aist.rtm.toolscommon.profiles
    cd jp.go.aist.rtm.toolscommon.profiles
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\ -lib %ECLIPSE_HOME%\plugins\org.junit_3.8.1\
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\rtcbuilder_1.1.0
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
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\ -lib %ECLIPSE_HOME%\plugins\org.junit_3.8.1\
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\rtcbuilder_1.1.0
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
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\ -lib %ECLIPSE_HOME%\plugins\org.junit_3.8.1\
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\rtcbuilder_1.1.0
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
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\ -lib %ECLIPSE_HOME%\plugins\org.junit_3.8.1\
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\rtcbuilder_1.1.0
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
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\ -lib %ECLIPSE_HOME%\plugins\org.junit_3.8.1\
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\rtcbuilder_1.1.0
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
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\ -lib %ECLIPSE_HOME%\plugins\org.junit_3.8.1\
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\rtcbuilder_1.1.0
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
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\ -lib %ECLIPSE_HOME%\plugins\org.junit_3.8.1\
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\rtcbuilder_1.1.0
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
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\ -lib %ECLIPSE_HOME%\plugins\org.junit_3.8.1\
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\rtcbuilder_1.1.0
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
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\ -lib %ECLIPSE_HOME%\plugins\org.junit_3.8.1\
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\rtcbuilder_1.1.0
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
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\ -lib %ECLIPSE_HOME%\plugins\org.junit_3.8.1\
    echo --
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\rtcbuilder_1.1.0
    cd ..
) else (
    echo -jp.go.aist.rtm.rtcbuilder.csharp doesn't exist.
)

rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist zip.vbs (
	del rtcbuilder_1.1.0.zip
	CScript.exe .\zip.vbs\MakeZIP.VBS rtcbuilder_1.1.0.zip rtcbuilder_1.1.0
	rd /S /Q rtcbuilder_1.1.0
)
@set ANT_HOME=%DUMMY%
@goto END

:FAIL
cd..
@set ANT_HOME=%DUMMY%

:END
