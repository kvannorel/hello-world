%~d0
cd %~dp0
java -Dtalend.component.manager.m2.repository="%cd%/../lib" -Xms256M -Xmx1024M -cp .;../lib/routines.jar;../lib/TalendSAX.jar;../lib/crypto-utils.jar;../lib/dom4j-1.6.1.jar;../lib/jaxen-1.1.1.jar;../lib/log4j-1.2.17.jar;../lib/postgresql-42.2.5.jar;../lib/talend_file_enhanced_20070724.jar;../lib/xercesImpl.jar;brk_kadasterstuk_init_0_1.jar;joblogger_0_1.jar; brk.brk_kadasterstuk_init_0_1.brk_kadasterstuk_init  --context=Default %*