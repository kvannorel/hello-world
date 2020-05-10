package org.talend.designer.codegen.translators.generic.component;

import java.util.List;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.utils.NodeUtil;

public class ComponentProcess_data_begin
{
  protected static String nl;
  public static synchronized ComponentProcess_data_begin create(String lineSeparator)
  {
    nl = lineSeparator;
    ComponentProcess_data_begin result = new ComponentProcess_data_begin();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "            boolean ";
  protected final String TEXT_2 = " = false;" + NL + "            routines.system.Dynamic ";
  protected final String TEXT_3 = " = new routines.system.Dynamic();";
  protected final String TEXT_4 = NL + "        org.talend.codegen.enforcer.OutgoingSchemaEnforcer ";
  protected final String TEXT_5 = " = org.talend.codegen.enforcer.EnforcerCreator.createOutgoingEnforcer(";
  protected final String TEXT_6 = ", ";
  protected final String TEXT_7 = ");" + NL + "" + NL + "        // Create a reusable factory that converts the output of the reader to an IndexedRecord." + NL + "        org.talend.daikon.avro.converter.IndexedRecordConverter<Object, ? extends org.apache.avro.generic.IndexedRecord> ";
  protected final String TEXT_8 = " = null;";
  protected final String TEXT_9 = NL + "        // Construct the factory once when the first data arrives." + NL + "        if (";
  protected final String TEXT_10 = " == null) {";
  protected final String TEXT_11 = NL + "            ";
  protected final String TEXT_12 = " = (org.talend.daikon.avro.converter.IndexedRecordConverter<Object, ? extends org.apache.avro.generic.IndexedRecord>)" + NL + "                    new org.talend.daikon.avro.AvroRegistry()" + NL + "                            .createIndexedRecordConverter(";
  protected final String TEXT_13 = ".getClass());" + NL + "        }" + NL + "" + NL + "        // Enforce the outgoing schema on the input.";
  protected final String TEXT_14 = NL + "        ";
  protected final String TEXT_15 = ".setWrapped(";
  protected final String TEXT_16 = ".convertToAvro(";
  protected final String TEXT_17 = "));";
  protected final String TEXT_18 = NL + "            if (!";
  protected final String TEXT_19 = ") {" + NL + "                org.apache.avro.Schema dynSchema_";
  protected final String TEXT_20 = " = ((org.talend.codegen.enforcer.OutgoingDynamicSchemaEnforcer) ";
  protected final String TEXT_21 = ").getDynamicFieldsSchema();" + NL + "                for (org.apache.avro.Schema.Field dynamicField_";
  protected final String TEXT_22 = " : dynSchema_";
  protected final String TEXT_23 = ".getFields()){" + NL + "                    routines.system.DynamicMetadata dynamicMetadata_";
  protected final String TEXT_24 = " = new routines.system.DynamicMetadata();" + NL + "                    org.apache.avro.Schema dynamicFieldSchema_";
  protected final String TEXT_25 = " = dynamicField_";
  protected final String TEXT_26 = ".schema();" + NL + "                    // set name" + NL + "                    dynamicMetadata_";
  protected final String TEXT_27 = ".setName(dynamicField_";
  protected final String TEXT_28 = ".name());" + NL + "                    // set db name" + NL + "                    dynamicMetadata_";
  protected final String TEXT_29 = ".setDbName(dynamicField_";
  protected final String TEXT_30 = ".name());" + NL + "                    // set nullable" + NL + "                    if (org.talend.daikon.avro.AvroUtils.isNullable(dynamicFieldSchema_";
  protected final String TEXT_31 = ")) {" + NL + "                        dynamicMetadata_";
  protected final String TEXT_32 = ".setNullable(true);" + NL + "                    }" + NL + "                    // set type" + NL + "                    String talendType_";
  protected final String TEXT_33 = " = org.talend.codegen.converter.TypeConverter.avroToDi(dynamicFieldSchema_";
  protected final String TEXT_34 = ");" + NL + "                    dynamicMetadata_";
  protected final String TEXT_35 = ".setType(talendType_";
  protected final String TEXT_36 = ");" + NL + "                    // set pattern" + NL + "                    String pattern_";
  protected final String TEXT_37 = ".getProp(org.talend.daikon.avro.SchemaConstants.TALEND_COLUMN_PATTERN);" + NL + "                    if (pattern_";
  protected final String TEXT_38 = " != null && !pattern_";
  protected final String TEXT_39 = ".trim().isEmpty()) {" + NL + "                        dynamicMetadata_";
  protected final String TEXT_40 = ".setFormat(pattern_";
  protected final String TEXT_41 = ");" + NL + "                    }" + NL + "                    // set logical type" + NL + "                    org.apache.avro.Schema unwrappedSchema_";
  protected final String TEXT_42 = " = org.talend.daikon.avro.AvroUtils.unwrapIfNullable(dynamicFieldSchema_";
  protected final String TEXT_43 = ");" + NL + "                    String logicalType_";
  protected final String TEXT_44 = " = org.talend.daikon.avro.LogicalTypeUtils.getLogicalTypeName(unwrappedSchema_";
  protected final String TEXT_45 = ".setLogicalType(logicalType_";
  protected final String TEXT_46 = ");" + NL + "                    // set length" + NL + "                    Object length_";
  protected final String TEXT_47 = ".getProp(org.talend.daikon.avro.SchemaConstants.TALEND_COLUMN_DB_LENGTH);" + NL + "                    if (length_";
  protected final String TEXT_48 = " != null) {" + NL + "                        dynamicMetadata_";
  protected final String TEXT_49 = ".setLength(Integer.parseInt(String.valueOf(length_";
  protected final String TEXT_50 = ")));" + NL + "                    }" + NL + "                    // set DbType" + NL + "                    Object dbType_";
  protected final String TEXT_51 = ".getProp(org.talend.daikon.avro.SchemaConstants.TALEND_COLUMN_DB_TYPE);" + NL + "                    if (dbType_";
  protected final String TEXT_52 = ".setDbType(String.valueOf(dbType_";
  protected final String TEXT_53 = "));" + NL + "                    }" + NL + "                    // set precision" + NL + "                    Object precision_";
  protected final String TEXT_54 = ".getProp(org.talend.daikon.avro.SchemaConstants.TALEND_COLUMN_PRECISION); " + NL + "                    if (precision_";
  protected final String TEXT_55 = ".setPrecision(Integer.parseInt(String.valueOf(precision_";
  protected final String TEXT_56 = ")));" + NL + "                    } " + NL + "                    // add dynamic field metadata to dynamic variable";
  protected final String TEXT_57 = NL + "                    ";
  protected final String TEXT_58 = ".metadatas.add(dynamicMetadata_";
  protected final String TEXT_59 = ");" + NL + "                }" + NL + "                initDyn_";
  protected final String TEXT_60 = " = true;" + NL + "            }";
  protected final String TEXT_61 = ".clearColumnValues();";
  protected final String TEXT_62 = NL + "                java.util.Map<String, Object> dynamicValue_";
  protected final String TEXT_63 = " = (java.util.Map<String, Object>) ";
  protected final String TEXT_64 = ".get(";
  protected final String TEXT_65 = ");" + NL + "                for (java.util.Map.Entry<String, Object> dynamicValueEntry_";
  protected final String TEXT_66 = " : dynamicValue_";
  protected final String TEXT_67 = ".entrySet()) {";
  protected final String TEXT_68 = ".setColumnValue(";
  protected final String TEXT_69 = ".getIndex(dynamicValueEntry_";
  protected final String TEXT_70 = ".getKey()), dynamicValueEntry_";
  protected final String TEXT_71 = ".getValue());" + NL + "                }";
  protected final String TEXT_72 = NL + "                ";
  protected final String TEXT_73 = ".";
  protected final String TEXT_74 = " = ";
  protected final String TEXT_75 = ";";
  protected final String TEXT_76 = NL + "                Object columnValue_";
  protected final String TEXT_77 = "_";
  protected final String TEXT_78 = ");";
  protected final String TEXT_79 = NL + "                        ";
  protected final String TEXT_80 = " = ParserUtils.parseTo_Document(String.valueOf(columnValue_";
  protected final String TEXT_81 = " = (";
  protected final String TEXT_82 = ") (columnValue_";
  protected final String TEXT_83 = NL + "                    if (columnValue_";
  protected final String TEXT_84 = ";" + NL + "                    } else {";
  protected final String TEXT_85 = NL + "                            ";
  protected final String TEXT_86 = NL + "                    }";
  protected final String TEXT_87 = NL + "            \tif (writer_";
  protected final String TEXT_88 = " instanceof org.talend.components.api.component.runtime.WriterWithFeedback) {" + NL + "\t\t\t\t\torg.talend.components.api.component.runtime.WriterWithFeedback writerWithFeedbackMain_";
  protected final String TEXT_89 = " = (org.talend.components.api.component.runtime.WriterWithFeedback) writer_";
  protected final String TEXT_90 = ";" + NL + "\t\t\t\t\twriterWithFeedbackMain_";
  protected final String TEXT_91 = " = new org.talend.codegen.flowvariables.runtime.FlowVariablesWriter(writerWithFeedbackMain_";
  protected final String TEXT_92 = ", container_";
  protected final String TEXT_93 = ");" + NL + "\t\t\t\t\tjava.lang.Iterable<?> outgoingRecs_";
  protected final String TEXT_94 = " = writerWithFeedbackMain_";
  protected final String TEXT_95 = ".getSuccessfulWrites();" + NL + "                    java.util.Iterator outgoingMainRecords_";
  protected final String TEXT_96 = " = outgoingRecs_";
  protected final String TEXT_97 = ".iterator();" + NL + "                    Object outgoingMain_";
  protected final String TEXT_98 = " = null;" + NL + "                    if (outgoingMainRecords_";
  protected final String TEXT_99 = ".hasNext()) {" + NL + "                    \toutgoingMainRecordsList_";
  protected final String TEXT_100 = ";" + NL + "                    }" + NL + "              \t}";
  protected final String TEXT_101 = NL + "                java.lang.Iterable<?> outgoingRejectRecordsList_";
  protected final String TEXT_102 = " = new java.util.ArrayList<Object>();" + NL + "                if (writer_";
  protected final String TEXT_103 = " instanceof org.talend.components.api.component.runtime.WriterWithFeedback) {" + NL + "\t\t\t\t\t     org.talend.components.api.component.runtime.WriterWithFeedback writerWithFeedbackReject_";
  protected final String TEXT_104 = ";" + NL + "\t\t\t\t\t     writerWithFeedbackReject_";
  protected final String TEXT_105 = " = new org.talend.codegen.flowvariables.runtime.FlowVariablesWriter(writerWithFeedbackReject_";
  protected final String TEXT_106 = ");" + NL + "\t\t\t\t\t     java.lang.Iterable<?> outgoingRejectRecs_";
  protected final String TEXT_107 = " = writerWithFeedbackReject_";
  protected final String TEXT_108 = ".getRejectedWrites();" + NL + "                    java.util.Iterator outgoingRejectRecords_";
  protected final String TEXT_109 = " = outgoingRejectRecs_";
  protected final String TEXT_110 = ".iterator();" + NL + "                    if (outgoingRejectRecords_";
  protected final String TEXT_111 = ".hasNext()) {" + NL + "                        outgoingRejectRecordsList_";
  protected final String TEXT_112 = ";" + NL + "                    }" + NL + "                }";
  protected final String TEXT_113 = NL + "            \toutgoingMainRecordsIt_";
  protected final String TEXT_114 = " = outgoingMainRecordsList_";
  protected final String TEXT_115 = ".iterator();";
  protected final String TEXT_116 = NL + "            \tjava.util.Iterator outgoingRejectRecordsIt_";
  protected final String TEXT_117 = " = outgoingRejectRecordsList_";
  protected final String TEXT_118 = NL + "            \twhile(";
  protected final String TEXT_119 = ") { //Start of data processing while block" + NL + "            \t\t";
  protected final String TEXT_120 = NL + "            \t\t\tif(outgoingMainRecordsIt_";
  protected final String TEXT_121 = ".hasNext()) {" + NL + "            \t\t\t\t";
  protected final String TEXT_122 = " = new ";
  protected final String TEXT_123 = "Struct();" + NL + "            \t\t\t\tObject outgoingMain_";
  protected final String TEXT_124 = " = outgoingMainRecordsIt_";
  protected final String TEXT_125 = ".next();" + NL + "            \t\t\t\t";
  protected final String TEXT_126 = NL + "            \t\t\t} else {" + NL + "            \t\t\t\t";
  protected final String TEXT_127 = " = null;" + NL + "            \t\t\t}" + NL + "            \t\t";
  protected final String TEXT_128 = NL + "            \t\t\tif(outgoingRejectRecordsIt_";
  protected final String TEXT_129 = "Struct();" + NL + "            \t\t\t\tObject outgoingReject_";
  protected final String TEXT_130 = " = outgoingRejectRecordsIt_";
  protected final String TEXT_131 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    

/**
 * Utility for generating code that can turn an IndexedRecording coming from a
 * generic component into a rowStruct expected by the Studio.
 */
class IndexedRecordToRowStructGenerator {

    /** A unique tag for generating code variables, usually based on the cid
     *  of the node. */
    private final String cid;

    /** The columns in the rowStruct to generate. */
    private final List<IMetadataColumn> columns;

    /** The connection that we're generating code for. */
    private final IConnection connection;

    /** If there is a dynamic column, its name.  Null if none. */
    private final String dynamicColName;

    /** Variable names generated in code used by this utility. */
    private final String codeVarSchemaEnforcer;
    private final String codeVarIsDynamicInitialized;
    private final String codeVarDynamic;
    private final String codeVarIndexedRecordAdapter;

    public IndexedRecordToRowStructGenerator(String cid, IConnection connection) {
        this(cid, connection, connection.getMetadataTable().getListColumns());
    }

    public IndexedRecordToRowStructGenerator(String cid, IConnection connection, List<IMetadataColumn> columns) {
        this.cid = cid;
        this.connection = connection;
        this.columns = columns;

        String tmpDynamicColName = null;
        for (IMetadataColumn column : columns) {
            if (column.getTalendType().equals("id_Dynamic")) {
                tmpDynamicColName = column.getLabel();
                break;
            }
        }
        dynamicColName = tmpDynamicColName;

        this.codeVarSchemaEnforcer = "outgoingEnforcer_" + cid;
        this.codeVarIsDynamicInitialized = "initDyn_" + cid;
        this.codeVarDynamic = "dynamic_" + cid;
        this.codeVarIndexedRecordAdapter = "factory_" + cid;
    }

    public IConnection getConnection() {
        return connection;
    }

    /**
     * Generate code that declares and initializes the variables that are used
     * in the code generated by this utility.
     */
    public void generateInitialVariables(String codeVarSchemaToEnforce, boolean dynamicByPosition) {
        if (dynamicColName != null) {
            
    stringBuffer.append(TEXT_1);
    stringBuffer.append(codeVarIsDynamicInitialized);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(codeVarDynamic);
    stringBuffer.append(TEXT_3);
    
        }

        
    stringBuffer.append(TEXT_4);
    stringBuffer.append(codeVarSchemaEnforcer);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(codeVarSchemaToEnforce);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(dynamicByPosition);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(codeVarIndexedRecordAdapter);
    stringBuffer.append(TEXT_8);
    
    }

    /**
     * Generate code that copies data from the IndexedRecord to the rowStruct.
     *
     * @param codeVarIndexedRecord the name of the variable that contains the
     *    IndexedRecord.
     * @param codeVarRowStruct the name of the variable that contains the
     *    rowStruct.
     */
    public void generateConvertRecord(String codeVarIndexedRecord, String codeVarRowStruct) {
        generateConvertRecord(codeVarIndexedRecord, codeVarRowStruct, columns);
    }

    /**
     * Generate code that copies data from the IndexedRecord to the rowStruct.
     *
     * @param codeVarIndexedRecord the name of the variable that contains the
     *    IndexedRecord.
     * @param codeVarRowStruct the name of the variable that contains the
     *    rowStruct.
     * @param columnsToGenerate the list of columns in the rowStruct to generate
     *    code for.
     */
    public void generateConvertRecord(String codeVarIndexedRecord, String codeVarRowStruct, List<IMetadataColumn> columnsToGenerate) {
        
    stringBuffer.append(TEXT_9);
    stringBuffer.append(codeVarIndexedRecordAdapter);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(codeVarIndexedRecordAdapter);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(codeVarIndexedRecord);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(codeVarSchemaEnforcer);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(codeVarIndexedRecordAdapter);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(codeVarIndexedRecord);
    stringBuffer.append(TEXT_17);
    

        if (dynamicColName != null) {
            
    stringBuffer.append(TEXT_18);
    stringBuffer.append(codeVarIsDynamicInitialized);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(codeVarSchemaEnforcer);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(codeVarDynamic);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(codeVarDynamic);
    stringBuffer.append(TEXT_61);
    
        }

        for (int i = 0; i < columnsToGenerate.size(); i++) {
            IMetadataColumn column = columnsToGenerate.get(i);
            String columnName = column.getLabel();
            String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
            if (columnName.equals(dynamicColName)) {
            
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(codeVarSchemaEnforcer);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(codeVarDynamic);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(codeVarDynamic);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(codeVarRowStruct);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(dynamicColName);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(codeVarDynamic);
    stringBuffer.append(TEXT_75);
    
            } else {
            
    stringBuffer.append(TEXT_76);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(codeVarSchemaEnforcer);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_78);
    
                if (JavaTypesManager.NULL.equals(JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate))) {
                    // there is no default value, so just assign data value to RowStruct
                
    
                    if ("id_Document".equals(column.getTalendType())) {
                    
    stringBuffer.append(TEXT_79);
    stringBuffer.append(codeVarRowStruct);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    
                    } else {
                    
    stringBuffer.append(TEXT_79);
    stringBuffer.append(codeVarRowStruct);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    
                    }
                } else {
                    // there is default value, so check on null and set default value instead of null
                
    stringBuffer.append(TEXT_83);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(codeVarRowStruct);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate));
    stringBuffer.append(TEXT_84);
    
                        if ("id_Document".equals(column.getTalendType())) {
                        
    stringBuffer.append(TEXT_85);
    stringBuffer.append(codeVarRowStruct);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    
                        } else {
                        
    stringBuffer.append(TEXT_85);
    stringBuffer.append(codeVarRowStruct);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    
                        }
                        
    stringBuffer.append(TEXT_86);
    
                }
            }
        }
    }
}

    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();
boolean hasInput = !NodeUtil.getIncomingConnections(node, IConnectionCategory.DATA).isEmpty();
if(hasInput){
    // These will be initialized if there are outgoing connections and will be
    // null if there isn't a corresponding outgoing connection.
    IndexedRecordToRowStructGenerator mainIrToRow = null;
    IndexedRecordToRowStructGenerator rejectIrToRow = null;

    List<? extends IConnection> outgoingConns = node.getOutgoingSortedConnections();
    if (outgoingConns!=null){
        for (int i = 0; i < outgoingConns.size(); i++) {
            IConnection outgoingConn = outgoingConns.get(i);
            if (outgoingConn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
                
    stringBuffer.append(TEXT_72);
    stringBuffer.append(outgoingConn.getName());
    stringBuffer.append(TEXT_8);
    
            }
            if ("MAIN".equals(outgoingConn.getConnectorName())) {
                mainIrToRow = new IndexedRecordToRowStructGenerator(cid + "OutMain",
                        outgoingConn);
            }
            if ("REJECT".equals(outgoingConn.getConnectorName())) {
                rejectIrToRow = new IndexedRecordToRowStructGenerator(cid + "OutReject",
                        outgoingConn);
            }
        }
    }
    // Generate the code to handle the incoming records.
    IConnection inputConn = null;
    List< ? extends IConnection> inputConns = node.getIncomingConnections();
    if(inputConns!=null) {
	   	for (IConnection conn : inputConns) {
	   		if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
	   			inputConn = conn;
	    	}
	   	}
   	}

   	boolean hasValidInput = inputConn!=null;

   	IMetadataTable metadata;
    List<IMetadataTable> metadatas = node.getMetadataList();
    boolean haveValidNodeMetadata = ((metadatas != null) && (metadatas.size() > 0) && (metadata = metadatas.get(0)) != null);
    if(haveValidNodeMetadata && hasValidInput) {
    	List<IMetadataColumn> input_columnList = inputConn.getMetadataTable().getListColumns();
    	if (input_columnList!=null && !input_columnList.isEmpty()) {
			if (mainIrToRow != null) {
                
    stringBuffer.append(TEXT_87);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    
            }
            if (rejectIrToRow != null) {
            
    stringBuffer.append(TEXT_101);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_112);
    
            }
            if(mainIrToRow != null) {
            
    stringBuffer.append(TEXT_113);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_115);
    
            }
            if(rejectIrToRow != null) {
            
    stringBuffer.append(TEXT_116);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_115);
    
            }
            if(mainIrToRow != null || rejectIrToRow != null) {
            	StringBuilder loopConditionBuilder = new StringBuilder();
            	if(mainIrToRow != null) {
            		loopConditionBuilder.append("outgoingMainRecordsIt_").append(cid).append(".hasNext()");
            	}
            	if(mainIrToRow != null && rejectIrToRow != null) {
            		loopConditionBuilder.append(" || ");
            	}
            	if(rejectIrToRow != null) {
            		loopConditionBuilder.append("outgoingRejectRecordsIt_").append(cid).append(".hasNext()");
            	}
            	String loopCondition = loopConditionBuilder.toString();
            
    stringBuffer.append(TEXT_118);
    stringBuffer.append(loopCondition);
    stringBuffer.append(TEXT_119);
    if(mainIrToRow != null) {
    stringBuffer.append(TEXT_120);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(mainIrToRow.getConnection().getName());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(mainIrToRow.getConnection().getName() );
    stringBuffer.append(TEXT_123);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_125);
    
                    	    mainIrToRow.generateConvertRecord("outgoingMain_" + cid, mainIrToRow.getConnection().getName());
                    	    
    stringBuffer.append(TEXT_126);
    stringBuffer.append(mainIrToRow.getConnection().getName());
    stringBuffer.append(TEXT_127);
    }
            		if(rejectIrToRow != null) {
            		
    stringBuffer.append(TEXT_128);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(rejectIrToRow.getConnection().getName());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(rejectIrToRow.getConnection().getName() );
    stringBuffer.append(TEXT_129);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_125);
    
                    	    rejectIrToRow.generateConvertRecord("outgoingReject_" + cid, rejectIrToRow.getConnection().getName());
                    	    
    stringBuffer.append(TEXT_126);
    stringBuffer.append(rejectIrToRow.getConnection().getName());
    stringBuffer.append(TEXT_127);
    }
            }
    	}
	}
}

    stringBuffer.append(TEXT_131);
    return stringBuffer.toString();
  }
}
