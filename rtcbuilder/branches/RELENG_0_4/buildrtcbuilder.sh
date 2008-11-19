#!/bin/sh
#
#

set DUMMY=$ANT_HOME
export ANT_HOME=$ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/

#
#
#
if [ -d jp.go.aist.rtm.RTC ]
then
    echo "-jp.go.aist.rtm.RTC"
    cd jp.go.aist.rtm.RTC
    ant buildAllLinux -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/
    if [ $? -ne 0 ];
    then 
        exit 1
    fi
    cd ..
else
    echo "jp.go.aist.rtm.RTC doesn't exist."
fi

#
#
#
if [ -d jp.go.aist.rtm.fsmcbuilder ]
then
    echo "-jp.go.aist.rtm.fsmcbuilder "
    cd jp.go.aist.rtm.fsmcbuilder 
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
        exit 1
    fi
    cd ..
else
    echo "jp.go.aist.rtm.fsmcbuilder doesn't exist."
fi



#
#
#
if [ -d jp.go.aist.rtm.toolscommon.profiles ]
then
    echo "-jp.go.aist.rtm.toolscommon.profiles"
    cd jp.go.aist.rtm.toolscommon.profiles
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
         exit 1
    fi
    cd ..
else
    echo "jp.go.aist.rtm.toolscommon.profiles doesn't exist."
fi


#
#
#
if [ -d jp.go.aist.rtm.toolscommon ]
then
    echo "-jp.go.aist.rtm.toolscommon"
    cd jp.go.aist.rtm.toolscommon
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
         exit 1
    fi
    cd ..
else
    echo "jp.go.aist.rtm.toolscommon doesn't exist."
fi


#
#
#
if [ -d jp.go.aist.rtm.repositoryView ]
then
    echo "-jp.go.aist.rtm.repositoryView"
    cd jp.go.aist.rtm.repositoryView
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
         exit 1
    fi
    cd ..
else
    echo "jp.go.aist.rtm.repositoryView doesn't exist."
fi



#
#
#
if [ -d jp.go.aist.rtm.logview ]
then
    echo "-jp.go.aist.rtm.logview"
    cd jp.go.aist.rtm.logview
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
         exit 1
    fi
    cd ..
else
    echo "jp.go.aist.rtm.logview doesn't exist."
fi



#
#
#
if [ -d jp.go.aist.rtm.rtcbuilder ]
then
    echo "-jp.go.aist.rtm.rtcbuilder"
    cd jp.go.aist.rtm.rtcbuilder
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
         exit 1
    fi
    echo "--"
    cd ..
else
    echo "jp.go.aist.rtm.rtcbuilder doesn't exist."
fi

#
#
#
if [ -d jp.go.aist.rtm.rtcbuilder.java ]
then
    echo "-jp.go.aist.rtm.rtcbuilder.java"
    cd jp.go.aist.rtm.rtcbuilder.java
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
         exit 1
    fi
    echo "--"
    cd ..
else
    echo "jp.go.aist.rtm.rtcbuilder.java doesn't exist."
fi

#
#
#
if [ -d jp.go.aist.rtm.rtcbuilder.python ]
then
    echo "-jp.go.aist.rtm.rtcbuilder.python"
    cd jp.go.aist.rtm.rtcbuilder.python
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
         exit 1
    fi
    echo "--"
    cd ..
else
    echo "jp.go.aist.rtm.rtcbuilder.python doesn't exist."
fi


set ANT_HOME=$DUMMY


