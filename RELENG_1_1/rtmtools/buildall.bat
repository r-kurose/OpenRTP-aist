@echo off
rem ---------------------------------------------------------------------------
rem ---------------------------------------------------------------------------
@set DUMMY=%ANT_HOME%
@set ANT_HOME=%ECLIPSE_HOME%\plugins\org.apache.ant_1.6.5\
rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------

@set ANT4ECLIPSE=%ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\
@set JUNIT=%ECLIPSE_HOME%\plugins\org.junit_3.8.1\
@set JARDIR=openrtp_1.0.0


set TARGETS=^
	jp.go.aist.rtm.toolscommon.profiles ^
	jp.go.aist.rtm.toolscommon.profiles.nl1 ^
	jp.go.aist.rtm.toolscommon ^
	jp.go.aist.rtm.toolscommon.nl1 ^
	jp.go.aist.rtm.rtcbuilder ^
	jp.go.aist.rtm.rtcbuilder.nl1 ^
	jp.go.aist.rtm.rtcbuilder.java ^
	jp.go.aist.rtm.rtcbuilder.python ^
	jp.go.aist.rtm.rtcbuilder.vbdotnet ^
	jp.go.aist.rtm.rtcbuilder.csharp ^
	jp.go.aist.rtm.repositoryView ^
	jp.go.aist.rtm.repositoryView.nl1 ^
	jp.go.aist.rtm.nameserviceview ^
	jp.go.aist.rtm.nameserviceview.nl1 ^
	jp.go.aist.rtm.systemeditor ^
	jp.go.aist.rtm.systemeditor.nl1

@if exist %JARDIR% (
    rd /S /Q %JARDIR%
)
mkdir %JARDIR%

for /D %%p in ( %TARGETS% ) do (
    @set target=%%p
    echo %%p
    cd %%p
    call ant buildAll -lib %ANT4ECLIPSE% -lib %JUNIT%
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\%JARDIR%
    cd ..
)

echo "Build finished successfully"
@goto END

:FAIL
cd ..
@set ANT_HOME=%DUMMY%
echo "Build failed" %TARGET%

:END
