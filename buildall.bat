@echo off
rem ---------------------------------------------------------------------------
rem ---------------------------------------------------------------------------
@set JARDIR=openrtp_1.1.0
@set LIBS=-lib ..\lib -lib %ECLIPSE_HOME%\plugins

rem ---------------------------------------------------------------------------
rem
rem ---------------------------------------------------------------------------
set TARGETS=^
	jp.go.aist.rtm.toolscommon.profiles ^
	jp.go.aist.rtm.toolscommon.profiles.nl1 ^
	jp.go.aist.rtm.toolscommon ^
	jp.go.aist.rtm.toolscommon.nl1 ^
	jp.go.aist.rtm.rtcbuilder ^
	jp.go.aist.rtm.rtcbuilder.nl1 ^
	jp.go.aist.rtm.rtcbuilder.java ^
	jp.go.aist.rtm.rtcbuilder.python ^
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
    call ant buildAll %LIBS%
    if ERRORLEVEL 1 goto FAIL
    copy jar\*aist*.jar ..\%JARDIR%
    cd ..
)

echo "Build finished successfully"
@goto END

:FAIL
cd ..
echo "Build failed" %TARGET%

:END

pause;