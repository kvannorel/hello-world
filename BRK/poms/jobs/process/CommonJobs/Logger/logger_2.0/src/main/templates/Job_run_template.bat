%~d0
cd %~dp0
java -Dtalend.component.manager.m2.repository="%cd%/../lib" -Xms256M -Xmx1024M -cp .;../lib/routines.jar;../lib/crypto-utils.jar;../lib/dom4j-1.6.1.jar;../lib/json-20160810.jar;../lib/log4j-1.2.17.jar;../lib/postgresql-42.2.5.jar;../lib/talend_file_enhanced_20070724.jar;logger_2_0.jar; brk.logger_2_0.Logger  --context=Default %*