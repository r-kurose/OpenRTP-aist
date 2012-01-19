@echo off
rem ---------------------------------------------------------------------------
rem ---------------------------------------------------------------------------
@set DUMMY=%ANT_HOME%
@set ANT_HOME=%ECLIPSE_HOME%\plugins\org.apache.ant_1.6.5\
rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist rtsystemeditor_1.1.0 (
	rd /S /Q rtsystemeditor_1.1.0
)
mkdir rtsystemeditor_1.1.0
rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist jp.go.aist.rtm.toolscommon (
    echo -jp.go.aist.rtm.toolscommon
    cd jp.go.aist.rtm.toolscommon
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\ -lib %ECLIPSE_HOME%\plugins\org.junit_3.8.1\
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\rtsystemeditor_1.1.0
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
    copy jar\*aist*.jar ..\rtsystemeditor_1.1.0
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
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\ -lib %ECLIPSE_HOME%\plugins\org.junit_3.8.1\
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\rtsystemeditor_1.1.0
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
    copy jar\*aist*.jar ..\rtsystemeditor_1.1.0
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
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\ -lib %ECLIPSE_HOME%\plugins\org.junit_3.8.1\
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\rtsystemeditor_1.1.0
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
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\ -lib %ECLIPSE_HOME%\plugins\org.junit_3.8.1\
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\rtsystemeditor_1.1.0
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
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\rtsystemeditor_1.1.0
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
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\rtsystemeditor_1.1.0
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
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\
    echo --
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\rtsystemeditor_1.1.0
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
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\
    echo --
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\rtsystemeditor_1.1.0
    cd ..
) else (
    echo -jp.go.aist.rtm.systemeditor.nl1 doesn't exist.
)

rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
@if exist zip.vbs (
	del rtsystemeditor_1.1.0.zip
	CScript.exe .\zip.vbs\MakeZIP.VBS rtsystemeditor_1.1.0.zip rtsystemeditor_1.1.0
	rd /S /Q rtsystemeditor_1.1.0
)
@set ANT_HOME=%DUMMY%
@goto END

:FAIL
cd ..
@set ANT_HOME=%DUMMY%

:END

