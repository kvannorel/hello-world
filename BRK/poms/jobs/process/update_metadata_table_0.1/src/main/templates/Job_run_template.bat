%~d0
cd %~dp0
java -Dtalend.component.manager.m2.repository="%cd%/../lib" -Xms256M -Xmx1024M -cp .;../lib/routines.jar;../lib/json-20160810.jar;../lib/log4j-1.2.17.jar;../lib/postgresql-42.2.5.jar;../lib/dom4j-1.6.1.jar;../lib/crypto-utils.jar;update_metadata_table_0_1.jar; brk.update_metadata_table_0_1.update_metadata_table  --context=Default %*