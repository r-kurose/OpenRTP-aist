#!/bin/sh

export JAVA_HOME="/c/Program Files/Java/jdk1.8.0_92"
# export ECLIPSE_HOME=../../../../eclipse-201809
export ECLIPSE_HOME=../../../RTM2018/eclipse-201809

ant $* -lib ../lib -lib ../lib/a4e-2137 -lib ../lib/a4e-2137/libs -lib $ECLIPSE_HOME/plugins

