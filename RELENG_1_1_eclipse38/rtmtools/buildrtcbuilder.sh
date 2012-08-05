#!/bin/sh
#===========================================================================
# update:
# cerate:Sep/11/2008
# 
# このシェルスクリプトは RTCBuilder をビルドします。
#     （以降、"Update"毎に上記に日付、名前、変更内容を記述する）
# このシェルスクリプトは RTCBuilder をビルドします。
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

#---------------------------------------------------------------------------
#---------------------------------------------------------------------------
JARDIR=rtcbuilder_1.1.0
LIBS="-lib ../lib -lib $ECLIPSE_HOME/plugins"

#---------------------------------------------------------------------------
#
#---------------------------------------------------------------------------
declare -a build_tbl=(	\
	"jp.go.aist.rtm.toolscommon"	\
	"jp.go.aist.rtm.toolscommon.nl1"	\
	"jp.go.aist.rtm.toolscommon.profiles"	\
	"jp.go.aist.rtm.toolscommon.profiles.nl1"	\
	"jp.go.aist.rtm.rtcbuilder"	\
	"jp.go.aist.rtm.rtcbuilder.nl1"	\
	"jp.go.aist.rtm.rtcbuilder.csharp"	\
	"jp.go.aist.rtm.rtcbuilder.java"	\
	"jp.go.aist.rtm.rtcbuilder.python"	\
	"jp.go.aist.rtm.rtcbuilder.vbdotnet"	\
)


declare	-i num
declare -i ic
num=${#build_tbl[@]}

ic=0
while [ $ic -lt $num ]
do
	if [ -d ${build_tbl[ic]} ]
	then
		echo "-" ${build_tbl[ic]}
		cd ${build_tbl[ic]}
		ant buildAll ${LIBS}
		if [ $? -ne 0 ];
		then 
			exit 1
		fi
		cd ..
	else
		echo ${build_tbl[ic]} "doesn't exist."
	fi
	ic=ic+1
done

#---------------------------------------------------------------------------
# 必要なファイルをzip
#
#
#---------------------------------------------------------------------------
mkdir ./${JARDIR}
ic=0
declare name
while [ $ic -lt $num ]
do
	name=${build_tbl[ic]}"_*.jar"
	cp -p ${build_tbl[ic]}/jar/$name ./${JARDIR} 
	ic=ic+1
done
rm ${JARDIR}.zip
zip ${JARDIR}.zip -r ./${JARDIR}/

rm -rf ./${JARDIR}


