#!/bin/sh
cd `dirname $0`
MY_LOCAL_PATH=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd)
# check if JAVA_HOME is set and if so if set to one of Makelaarsuite's versions
# call the setJavaHome from the res-folder
source $MY_LOCAL_PATH/../../../../res/setJavaHome.sh
ROOT_PATH=`pwd`
java -Xms256M -Xmx1024M -cp $ROOT_PATH:$ROOT_PATH/../lib/systemRoutines.jar:$ROOT_PATH/../lib/userRoutines.jar::.:$ROOT_PATH/modulebeheer_0_1.jar:$ROOT_PATH/anl_beh_leverregels_per_lag_0_1.jar:$ROOT_PATH/anl_beh_afnemerregels_per_lag_0_1.jar:$ROOT_PATH/toevoegen_ldf_overzichten_beheer_0_1.jar:$ROOT_PATH/anl_beh_element_group_0_1.jar:$ROOT_PATH/vullen_ldf_overzichten_2_0.jar:$ROOT_PATH/contextreader_2_0.jar:$ROOT_PATH/logger_2_0.jar:$ROOT_PATH/anl_beh_prf_elem_gegevens_0_1.jar:$ROOT_PATH/../lib/advancedPersistentLookupLib-1.0.jar:$ROOT_PATH/../lib/commons-collections-3.2.jar:$ROOT_PATH/../lib/dom4j-1.6.1.jar:$ROOT_PATH/../lib/jboss-serialization.jar:$ROOT_PATH/../lib/json-20160810.jar:$ROOT_PATH/../lib/log4j-1.2.15.jar:$ROOT_PATH/../lib/oracleJdbcDriver.jar:$ROOT_PATH/../lib/postgresql-9.2-1003.jdbc3.jar:$ROOT_PATH/../lib/talendcsv.jar:$ROOT_PATH/../lib/talend_file_enhanced_20070724.jar:$ROOT_PATH/../lib/trove.jar: ldf.modulebeheer_0_1.ModuleBeheer --context=Default "$@" 