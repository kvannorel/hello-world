%~d0
cd %~dp0
java -Dtalend.component.manager.m2.repository="%cd%/../lib" -Xms256M -Xmx1024M -cp .;../lib/routines.jar;../lib/crypto-utils.jar;../lib/dom4j-1.6.1.jar;../lib/log4j-1.2.17.jar;../lib/postgresql-42.2.5.jar;../lib/talend_file_enhanced_20070724.jar;fill_original_objects_0_1.jar;joblogger_0_1.jar; brk.fill_original_objects_0_1.fill_original_objects  --context=Default %*