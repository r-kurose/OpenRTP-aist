#!/bin/sh
#===========================================================================
# update:
# cerate:Sep/11/2008
# 
# ¤³¤Î¥·¥§¥ë¥¹¥¯¥ê¥×¥È¤Ï RTSystemEditor ¤ò¥Ó¥ë¥É¤·¤Þ¤¹¡£
#     ¡Ê°Ê¹ß¡¢"Update"Ëè¤Ë¾åµ­¤ËÆüÉÕ¡¢Ì¾Á°¡¢ÊÑ¹¹ÆâÍÆ¤òµ­½Ò¤¹¤ë¡Ë
# ¤³¤Î¥·¥§¥ë¥¹¥¯¥ê¥×¥È¤Ï RTSystemEditor ¤ò¥Ó¥ë¥É¤·¤Þ¤¹¡£
# ´Ä¶­
#  °Ê²¼¤Ë¥Ó¥ë¥É¤ËÉ¬Í×¤Ê´Ä¶­¤È¥¤¥ó¥¹¥È¡¼¥ëÊýË¡¤ò´ÊÃ±¤Ë¼¨¤·¤Þ¤¹¡£
#   Eclipse SDK
#   ant
#   ant4eclipse
#   jdk
# 
# ´Ä¶­ÊÑ¿ô
#  ¥Ó¥ë¥É¤ËÉ¬Í×¤Ê´Ä¶­ÊÑ¿ô¤ò°Ê²¼¤Ë¼¨¤·¤Þ¤¹¡£
#   ECLIPSE_HOME   Eclipse SDK 3.2.x ¤Î¼Â¹Ô¥Õ¥¡¥¤¥ë¤¬¤¢¤ë¥Ç¥£¥ì¥¯¥È¥ê¤ò»ØÄê£
#   ECLIPSE33_HOME Eclipse SDK 3.3.x ¤Î¼Â¹Ô¥Õ¥¡¥¤¥ë¤¬¤¢¤ë¥Ç¥£¥ì¥¯¥È¥ê¤ò»ØÄê£
#   ANT_HOME       ant ¤Î¥Ç¥£¥ì¥¯¥È¥ê¤ò»ØÄê¤·¤Þ¤¹¡£
#   JAVA_HOME      jdk¤Î¥Ç¥£¥ì¥¯¥È¥ê¤ò»ØÄê¤·¤Þ¤¹¡£
#
#===========================================================================
#
#

#---------------------------------------------------------------------------
#---------------------------------------------------------------------------

DUMMY=$ANT_HOME
export ANT_HOME=$ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/

# ECLIPSE_HOME=/usr/users/n-ando/work/rtmtools/eclipse/

ANT4ECLIPSE=$ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/
JUNIT=$ECLIPSE_HOME/plugins/org.junit_3.8.2.v20080602-1318/
JARDIR=openrtp_1.1.0
LIBS="-lib $ANT4ECLIPSE -lib $ANT_HOME -lib $JUNIT -lib $ECLIPSE_HOME/plugins"
projects="jp.go.aist.rtm.toolscommon.profiles
    jp.go.aist.rtm.toolscommon.profiles.nl1
    jp.go.aist.rtm.toolscommon
    jp.go.aist.rtm.toolscommon.nl1
    jp.go.aist.rtm.rtcbuilder
    jp.go.aist.rtm.rtcbuilder.nl1
    jp.go.aist.rtm.rtcbuilder.java
    jp.go.aist.rtm.rtcbuilder.python
    jp.go.aist.rtm.rtcbuilder.vbdotnet
    jp.go.aist.rtm.rtcbuilder.csharp
    jp.go.aist.rtm.repositoryView
    jp.go.aist.rtm.repositoryView.nl1
    jp.go.aist.rtm.nameserviceview
    jp.go.aist.rtm.nameserviceview.nl1
    jp.go.aist.rtm.systemeditor
    jp.go.aist.rtm.systemeditor.nl1"


if test -d $JARDIR; then
    rm -rf $JARDIR
    mkdir $JARDIR
else
    mkdir $JARDIR
fi

for project in $projects; do
    if test -d $project; then
	echo "-" $project
	cd $project

	ant buildAll $LIBS
	    
	if test $? -ne 0; then
	    echo "build failed: " $project
	    export ANT_HOME=$DUMMY
	    exit 1
	fi
	mv jar/*aist*.jar ../$JARDIR
	cd ..
    else
	echo $project "does not exist"
    fi
done

if test -f $JARDIR.zip ; then
    rm $JARDIR.zip
fi
zip $JARDIR.zip -r ./$JARDIR

export ANT_HOME=$DUMMY


