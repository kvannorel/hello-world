@echo off
%~d0
setlocal
SET MY_PATH=%~dp0
rem check if JAVA_HOME is set and if so if set to one of Makelaarsuite's versions
rem call the setJavaHome from the res-folder
call %MY_PATH%/../../../../res/setJavaHome.bat
cd %~dp0
java -Xms256M -Xmx1024M -cp ../lib/systemRoutines.jar;../lib/userRoutines.jar;.;modulebeheer_0_1.jar;anl_beh_leverregels_per_lag_0_1.jar;anl_beh_afnemerregels_per_lag_0_1.jar;toevoegen_ldf_overzichten_beheer_0_1.jar;anl_beh_element_group_0_1.jar;vullen_ldf_overzichten_2_0.jar;contextreader_2_0.jar;logger_2_0.jar;anl_beh_prf_elem_gegevens_0_1.jar;../lib/advancedPersistentLookupLib-1.0.jar;../lib/commons-collections-3.2.jar;../lib/dom4j-1.6.1.jar;../lib/jboss-serialization.jar;../lib/json-20160810.jar;../lib/log4j-1.2.15.jar;../lib/oracleJdbcDriver.jar;../lib/postgresql-9.2-1003.jdbc3.jar;../lib/talendcsv.jar;../lib/talend_file_enhanced_20070724.jar;../lib/trove.jar; ldf.modulebeheer_0_1.ModuleBeheer --context=Default %* 
endlocal