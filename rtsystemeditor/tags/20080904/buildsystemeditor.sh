#!/bin/sh
#
#

set DUMMY=$ANT_HOME
export ANT_HOME=$ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/


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
if [ -d jp.go.aist.rtm.nameserviceview ]
then
    echo "-jp.go.aist.rtm.nameserviceview"
    cd jp.go.aist.rtm.nameserviceview 
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
         exit 1
    fi
    cd ..
else
    echo "jp.go.aist.rtm.nameserviceview doesn't exist."
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
if [ -d jp.go.aist.rtm.systemeditor ]
then
    echo "-jp.go.aist.rtm.systemeditor"
    cd jp.go.aist.rtm.systemeditor
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
         exit 1
    fi
    echo "--"
    cd ..
else
    echo "jp.go.aist.rtm.systemeditor doesn't exist."
fi

set ANT_HOME=$DUMMY


