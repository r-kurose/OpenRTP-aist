#!/bin/sh
#===========================================================================
# update:
# cerate:Sep/11/2008
# 
# このシェルスクリプトは RTSystemEditor をビルドします。
#     （以降、"Update"毎に上記に日付、名前、変更内容を記述する）
# このシェルスクリプトは RTSystemEditor をビルドします。
# 環境
#  以下にビルドに必要な環境とインストール方法を簡単に示します。
#   Eclipse SDK
#   ant
#   jdk
# 
# 環境変数
#  ビルドに必要な環境変数を以下に示します。
#   ECLIPSE_HOME   Eclipse SDK 3.2.x の実行ファイルがあるディレクトリを指定
#   JAVA_HOME      jdkのディレクトリを指定します。
#
#===========================================================================
#
#

#---------------------------------------------------------------------------
#---------------------------------------------------------------------------
JARDIR=openrtp_1.1.0
LIBS="-lib ../lib -lib $ECLIPSE_HOME/plugins"

projects="jp.go.aist.rtm.toolscommon.profiles
    jp.go.aist.rtm.toolscommon.profiles.nl1
    jp.go.aist.rtm.toolscommon
    jp.go.aist.rtm.toolscommon.nl1
    jp.go.aist.rtm.rtcbuilder
    jp.go.aist.rtm.rtcbuilder.nl1
    jp.go.aist.rtm.rtcbuilder.java
    jp.go.aist.rtm.rtcbuilder.python
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


