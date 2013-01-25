#!/bin/sh
#
# @file buildall.sh
# @brief rtmtools build script
# @author Noriaki Ando <n-ando@aist.go.jp>
#
# update:
# cerate:Sep/11/2008
#
# * How to build rtmtools
#
# ** Required environment
#
# The following development environment and tools are required to
# build rtmtools.
#
# - jdk
# - ant
# - Eclipse SDK (3.4 or later is required.)
#
# ** Before build tools
#
# Please edit "version" text to set version number of the tools. This
# is a kind of bash script.
#
# Example:
# -------
# VERSION=1.1.0
# PROJECT_VERSION=${VERSION}.rc7v$(date +%Y%m%d)
#
# ** Environment variables
#
# To build rtmtools, some environmental variables can be set. In most
# case, these variables are automatically set.
#
# - ECLIPSE_HOME: A directory path to an Eclipse SDK. Under this
#                 directory, .eclipseproduct, eclipse.ini, plugins and
#                 eclipse executable should exist. If this
#                 env.variable is not set, this script tries to search
#                 eclipse directory under some directories that is set
#                 in a env.variable ECLIPSE_DIRS written in the head
#                 of this script.
#
# - JAVA_HOME: A directory JDK installed. If this variable is not set,
#                 this script tries to estimate JDK directory to
#                 resolve symbolic link of javac executable.
#
# - VERSION: A simple version number for the tools like 1.1.1. This is
#                 used for actual bundles' version number with
#                 PROJECT_VERSION number.
#
# - PROJECT_VERSION: Project version number is full version string
#                 with simple version and suffix like
#                 rc120121212. This version string is used actual jar
#                 file name.
#
# - JARDIR: A directory to store jar files. Default directory is "jar".
#                 This directory is  temporary jar files store place.
#
# - DISTDIR: A directory to be archived for distribution package.
#                 This directory name becomes archive package name.
#                 Default name is openrtm-x.y.z. x, y, z are version
#                 number which is defined in version text file.
#

# Eclipse search directories
ECLIPSE_DIRS="$HOME/eclipse $HOME ../ ../../ ../..//usr/lib/ /usr/share"

# Ant eclipse plugin location
ANT_HOME_DEFAULT="${ECLIPSE_HOME}/plugins/org.apache.ant_1.7.0.v200803061910/"

#============================================================
# functions
#============================================================

#------------------------------------------------------------
# find_eclipsehome
#
# This function checks ECLIPSE_HOME env variable and if it is
# not set, it searches an eclipse directory under ECLIPSE_DIRS,
# and set ECLIPSE_HOME env variable.
# ------------------------------------------------------------
find_eclipsehome()
{
    if test ! "x$ECLIPSE_HOME" = "x" ; then
        if test -d $ECLIPSE_HOME ; then
            return 0
        fi
        echo "ECLIPSE_HOME $ECLIPSE_HOME does not exist."
    fi
    echo "Environment variable ECLIPSE_HOME is not set. Seaching..."
    for d in $ECLIPSE_DIRS ; do
        tmp=`find -L $d -name .eclipseproduct`
        if test "x$tmp" = "x" ; then
            continue
        fi
        for e in $tmp ; do
            edir=`dirname $e`
            if test -f $edir/eclipse.ini && test -d $edir/plugins ; then
                export ECLIPSE_HOME="$edir"
                return 0
            fi
        done
    done
    echo "eclipse not found. Please install eclipse and set ECLIPSE_HOME."
    exit 1
}




find_antplugin()
{
    if test ! "x$ANT_HOME" = "x" ; then
        return 0
    fi
    echo "Environment variable ANT_HOME does not exist. Searching..."
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
}

check_plugins()
{
    if test "x$PLUGINS_DIR" = "x" ; then
        echo "Environment variable PLUGINS_DIR is not set."
        tmp=`find ../jar -name 'jp.go.aist.rtm.toolscommon*' -exec dirname {} \;`
        if test "x$tmp" = "x" ; then
            echo "Plugins not found. Trying to build plugins."
            exit 0
            cd ../
            sh buildall.sh
            cd -
            if test $? -ne 0 ; then
                echo "Build failed. Aborting."
                exit 1
            fi
            tmp=`find ../jar -name 'jp.go.aist.rtm.toolscommon*' -exec dirname {} \;`
            if test "x$tmp" = "x" ; then
                echo "Plugins not found. Aborting."
                return 1
            fi
        fi
        for t in $tmp ; do
            plugindir=$t
            break
        done
        PLUGINS_DIR=$plugindir
    fi
}

#==============================
# main
#==============================
cd `dirname $0`
pwd

find_eclipsehome
find_antplugin
check_plugins

export ANT_HOME
export PLUGINS_DIR
export PATH=${PATH}:${ANT_HOME}/bin

echo "ANT_HOME=${ANT_HOME}"
echo "PLUGINS_DIR=${PLUGINS_DIR}"
echo "PATH=${PATH}"

echo "Getting plugins (get.plugins)"
ant -lib lib get.plugins
if test $? -ne 0 ; then
    echo "Error in get.plugins target. Aborting..."
fi

echo "Signing features (feature.sign.gen)"
ant -lib lib feature.sign.gen
if test $? -ne 0 ; then
    echo "Error in feature.sign.gen target. Aborting..."
fi

echo "Building features (build.features)"
ant -lib lib build.features
if test $? -ne 0 ; then
    echo "Error in build.features target. Aborting..."
fi

echo "Deploying features to site (deploy.site)"
ant -lib lib deploy.site
if test $? -ne 0 ; then
    echo "Error in deploy.site target. Aborting..."
fi

# EOF
