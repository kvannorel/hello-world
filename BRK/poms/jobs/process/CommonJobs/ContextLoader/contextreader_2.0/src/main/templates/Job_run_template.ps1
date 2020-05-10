$fileDir = Split-Path -Parent $MyInvocation.MyCommand.Path
cd $fileDir
java '-Dtalend.component.manager.m2.repository=%cd%/../lib' '-Xms256M' '-Xmx1024M' -cp '.;../lib/routines.jar;../lib/crypto-utils.jar;../lib/dom4j-1.6.1.jar;../lib/json-20160810.jar;../lib/log4j-1.2.17.jar;contextreader_2_0.jar;' brk.contextreader_2_0.ContextReader  --context=Default %*