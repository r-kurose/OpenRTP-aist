#!/bin/sh

cd `dirname $0`
pwd


if test "x$ECLIPSE_HOME" = "x" ; then
    echo "Environment variable ECLIPSE_HOME is not set. Aborting."
    return 1
fi
if test ! -d $ECLIPSE_HOME ; then
    echo "ECLIPSE_HOME $ECLIPSE_HOME does not exist. Aborting."
    return 1
fi


ANT_HOME="${ECLIPSE_HOME}/plugins/org.apache.ant_1.7.0.v200803061910/"

if test ! -d $ANT_HOME ; then
    echo "ANT_HOME $ANT_HOME does not exist. Finding another ant plugin."
    other_ant=`find $ECLIPSE_HOME/plugins -maxdepth 1 -type d -name 'org.apache.ant*'`
    echo $other_ant
    if test "x$other_ant" = "x" ; then
        echo "Ant plugin was not found. Aborting."
        return 1
    fi
    ANT_HOME=$other_ant
fi

if test "x$PLUGINS_DIR" = "x" ; then
    echo "Environment variable PLUGINS_DIR is not set."
    tmp=`find ../ -name 'jp.go.aist.rtm.toolscommon.nl*.jar' ! -name '*feature*' -exec dirname {} \;`
    if test "x$tmp" = "x" ; then
        echo "Plugins not found. Trying to build plugins."
        cd ../
        sh buildall.sh
        if test $? -ne 0 ; then
            echo "Build failed. Aborting."
            return 1
        fi
        tmp=`find ../ -name 'jp.go.aist.rtm.toolscommon.nl*.jar' ! -name '*feature*' -exec dirname {} \;`
        if test "x$tmp" = "x" ; then
            echo "Plugins not found. Aborting."
            return 1
        fi
    fi
    PLUGINS_DIR=$tmp
fi

export ANT_HOME
export PLUGINS_DIR
export PATH=${PATH}:${ANT_HOME}/bin

echo "ANT_HOME=${ANT_HOME}"
echo "PLUGINS_DIR=${PLUGINS_DIR}"
echo "PATH=${PATH}"

ant -lib lib get.plugins

ant -lib lib feature.sign.gen

ant -lib lib build.features

ant -lib lib deploy.site

# EOF
