#!/bin/sh

cd `dirname $0`
pwd

export ANT_HOME=eclipse/plugins/org.apache.ant_1.7.0.v200803061910/
export PATH=${PATH}:${ANT_HOME}/bin

echo "ANT_HOME=${ANT_HOME}"
echo "PATH=${PATH}"

ant -lib lib get.plugins

ant -lib lib feature.sign.gen

ant -lib lib build.features

ant -lib lib deploy.site

# EOF
