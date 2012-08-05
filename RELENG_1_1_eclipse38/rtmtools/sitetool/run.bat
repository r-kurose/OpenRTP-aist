
cd %~dp0

set ANT_HOME=%ECLIPSE_HOME%\plugins\org.apache.ant_1.7.0.v200803061910
set PATH=%PATH%;%ANT_HOME%\bin

call ant -lib lib get.plugins

call ant -lib lib feature.sign.gen

call ant -lib lib build.features

call ant -lib lib deploy.site