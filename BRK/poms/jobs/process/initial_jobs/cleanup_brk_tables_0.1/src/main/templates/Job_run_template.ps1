$fileDir = Split-Path -Parent $MyInvocation.MyCommand.Path
cd $fileDir
java '-Dtalend.component.manager.m2.repository=%cd%/../lib' '-Xms256M' '-Xmx1024M' -cp '.;../lib/routines.jar;../lib/crypto-utils.jar;../lib/dom4j-1.6.1.jar;../lib/log4j-1.2.17.jar;../lib/postgresql-42.2.5.jar;cleanup_brk_tables_0_1.jar;' brk.cleanup_brk_tables_0_1.cleanup_brk_tables  --context=Default %*