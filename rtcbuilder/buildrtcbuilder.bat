@set DUMMY=%ANT_HOME%
@set ANT_HOME=%ECLIPSE_HOME%\plugins\org.apache.ant_1.6.5\

@if exist jp.go.aist.rtm.RTC  (
    echo -jp.go.aist.rtm.RTC
    cd jp.go.aist.rtm.RTC
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\
    if ERRORLEVEL 1 goto FAIL
    cd ..
) else (
    echo -jp.go.aist.rtm.RTC doesn't exist.
)

@if exist jp.go.aist.rtm.toolscommon.profiles (
    echo -jp.go.aist.rtm.toolscommon.profiles
    cd jp.go.aist.rtm.toolscommon.profiles
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\ -lib %ECLIPSE_HOME%\plugins\org.junit_3.8.1\
    if ERRORLEVEL 1 goto FAIL
    cd ..
) else (
    echo -jp.go.aist.rtm.toolscommon.profiles doesn't exist.
)

@if exist jp.go.aist.rtm.toolscommon (
    echo -jp.go.aist.rtm.toolscommon
    cd jp.go.aist.rtm.toolscommon
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\ -lib %ECLIPSE_HOME%\plugins\org.junit_3.8.1\
    if ERRORLEVEL 1 goto FAIL
    cd ..
) else (
    echo -jp.go.aist.rtm.toolscommon doesn't exist.
)

@if exist jp.go.aist.rtm.repositoryView (
    echo -jp.go.aist.rtm.repositoryView
    cd jp.go.aist.rtm.repositoryView
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\ -lib %ECLIPSE_HOME%\plugins\org.junit_3.8.1\
    if ERRORLEVEL 1 goto FAIL
    cd ..
) else (
    echo -jp.go.aist.rtm.repositoryView doesn't exist.
)


@if exist jp.go.aist.rtm.logview (
    echo -jp.go.aist.rtm.logview
    cd jp.go.aist.rtm.logview
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\
    if ERRORLEVEL 1 goto FAIL
    cd ..
) else (
    echo -jp.go.aist.rtm.logview doesn't exist.
)

@if exist jp.go.aist.rtm.rtcbuilder (
    echo -jp.go.aist.rtm.rtcbuilder
    cd jp.go.aist.rtm.rtcbuilder
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\ -lib %ECLIPSE_HOME%\plugins\org.junit_3.8.1\
    cd ..
) else (
    echo -jp.go.aist.rtm.rtcbuilder doesn't exist.
)

@if exist jp.go.aist.rtm.rtcbuilder.java (
    echo -jp.go.aist.rtm.rtcbuilder.java
    cd jp.go.aist.rtm.rtcbuilder.java
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\ -lib %ECLIPSE_HOME%\plugins\org.junit_3.8.1\
    cd ..
) else (
    echo -jp.go.aist.rtm.rtcbuilder.java doesn't exist.
)

@if exist jp.go.aist.rtm.rtcbuilder.python (
    echo -jp.go.aist.rtm.rtcbuilder.python
    cd jp.go.aist.rtm.rtcbuilder.python
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\ -lib %ECLIPSE_HOME%\plugins\org.junit_3.8.1\
    echo --
:FAIL
    cd ..
) else (
    echo -jp.go.aist.rtm.rtcbuilder.python doesn't exist.
)
@set ANT_HOME=%DUMMY%


