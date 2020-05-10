#!/bin/sh
cd `dirname $0`
ROOT_PATH=`pwd`
java -Dtalend.component.manager.m2.repository=$ROOT_PATH/../lib -Xms256M -Xmx1024M -cp .:$ROOT_PATH:$ROOT_PATH/../lib/routines.jar:$ROOT_PATH/../lib/crypto-utils.jar:$ROOT_PATH/../lib/dom4j-1.6.1.jar:$ROOT_PATH/../lib/json-20160810.jar:$ROOT_PATH/../lib/log4j-1.2.17.jar:$ROOT_PATH/contextreader_2_0.jar: brk.contextreader_2_0.ContextReader  --context=Default "$@"