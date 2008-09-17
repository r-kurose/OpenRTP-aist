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
set DUMMY=$ANT_HOME
export ANT_HOME=$ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/


#---------------------------------------------------------------------------
#
#
#
#---------------------------------------------------------------------------
if [ -d jp.go.aist.rtm.fsmcbuilder ]
then
    echo "-jp.go.aist.rtm.fsmcbuilder "
    cd jp.go.aist.rtm.fsmcbuilder 
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
        set ANT_HOME=$DUMMY
        exit 1
    fi
    cd ..
else
    echo "jp.go.aist.rtm.fsmcbuilder doesn't exist."
fi

#---------------------------------------------------------------------------
#
#
#
#---------------------------------------------------------------------------
if [ -d jp.go.aist.rtm.toolscommon.profiles ]
then
    echo "-jp.go.aist.rtm.toolscommon.profiles"
    cd jp.go.aist.rtm.toolscommon.profiles
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
        set ANT_HOME=$DUMMY
         exit 1
    fi
    cd ..
else
    echo "jp.go.aist.rtm.toolscommon.profiles doesn't exist."
fi


#---------------------------------------------------------------------------
#
#
#
#---------------------------------------------------------------------------
if [ -d jp.go.aist.rtm.toolscommon ]
then
    echo "-jp.go.aist.rtm.toolscommon"
    cd jp.go.aist.rtm.toolscommon
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
        set ANT_HOME=$DUMMY
        exit 1
    fi
    cd ..
else
    echo "jp.go.aist.rtm.toolscommon doesn't exist."
fi

#---------------------------------------------------------------------------
#
#
#
#---------------------------------------------------------------------------
if [ -d jp.go.aist.rtm.nameserviceview ]
then
    echo "-jp.go.aist.rtm.nameserviceview"
    cd jp.go.aist.rtm.nameserviceview 
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
        set ANT_HOME=$DUMMY
         exit 1
    fi
    cd ..
else
    echo "jp.go.aist.rtm.nameserviceview doesn't exist."
fi


#---------------------------------------------------------------------------
#
#
#
#---------------------------------------------------------------------------
if [ -d jp.go.aist.rtm.repositoryView ]
then
    echo "-jp.go.aist.rtm.repositoryView"
    cd jp.go.aist.rtm.repositoryView
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
        set ANT_HOME=$DUMMY
         exit 1
    fi
    cd ..
else
    echo "jp.go.aist.rtm.repositoryView doesn't exist."
fi

#---------------------------------------------------------------------------
#
#
#
#---------------------------------------------------------------------------
if [ -d jp.go.aist.rtm.systemeditor ]
then
    echo "-jp.go.aist.rtm.systemeditor"
    cd jp.go.aist.rtm.systemeditor
    ant buildAll -lib $ECLIPSE_HOME/plugins/net.sf.ant4eclipse.plugin_0.5.0.rc1/lib/ -lib $ECLIPSE_HOME/plugins/org.apache.ant_1.6.5/lib/ -lib $ECLIPSE_HOME/plugins/org.junit_3.8.1/ -lib $ECLIPSE_HOME/plugins
    if [ $? -ne 0 ];
    then 
        set ANT_HOME=$DUMMY
        exit 1
    fi
    echo "--"
    cd ..
else
    echo "jp.go.aist.rtm.systemeditor doesn't exist."
fi
#---------------------------------------------------------------------------
# É¬Í×¤Ê¥Õ¥¡¥¤¥ë¤òzip
#
#
#---------------------------------------------------------------------------
find ./ -name '*aist*.jar' -exec cp -p {} . \;
rm RTSystemEditor.zip 
zip RTSystemEditor.zip *aist*.jar 
rm *aist*.jar



#---------------------------------------------------------------------------
#---------------------------------------------------------------------------
set ANT_HOME=$DUMMY


