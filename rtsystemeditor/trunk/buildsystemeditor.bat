@set DUMMY=%ANT_HOME%
@set ANT_HOME=%ECLIPSE_HOME%\plugins\org.apache.ant_1.6.5\

@if exist jp.go.aist.rtm.fsmcbuilder (
    echo -jp.go.aist.rtm.fsmcbuilder 
    cd jp.go.aist.rtm.fsmcbuilder 
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\
    if ERRORLEVEL 1 goto FAIL
    cd ..
) else (
    echo -jp.go.aist.rtm.fsmcbuilder doesn't exist.
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

@if exist jp.go.aist.rtm.nameserviceview (
    echo -jp.go.aist.rtm.nameserviceview 
    cd jp.go.aist.rtm.nameserviceview 
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\
    if ERRORLEVEL 1 goto FAIL
    cd ..
) else (
    echo -jp.go.aist.rtm.nameserviceview doesn't exist.
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

@if exist jp.go.aist.rtm.systemeditor (
    echo -jp.go.aist.rtm.systemeditor
    cd jp.go.aist.rtm.systemeditor
    call ant buildAll -lib %ECLIPSE_HOME%\plugins\net.sf.ant4eclipse.plugin_0.5.0.rc1\lib\
    echo --
:FAIL
    cd ..
) else (
    echo -jp.go.aist.rtm.systemeditor doesn't exist.
)

@set ANT_HOME=%DUMMY%


