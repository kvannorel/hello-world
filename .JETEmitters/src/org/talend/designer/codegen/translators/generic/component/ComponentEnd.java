package org.talend.designer.codegen.translators.generic.component;

import java.util.List;
import java.util.Set;
import org.talend.components.api.component.ComponentDefinition;
import org.talend.components.api.component.ConnectorTopology;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.designer.core.generic.model.Component;
import org.talend.core.model.utils.NodeUtil;

public class ComponentEnd
{
  protected static String nl;
  public static synchronized ComponentEnd create(String lineSeparator)
  {
    nl = lineSeparator;
    ComponentEnd result = new ComponentEnd();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "// end of generic" + NL;
  protected final String TEXT_2 = NL + NL + "resourceMap.put(\"finish_";
  protected final String TEXT_3 = "\", Boolean.TRUE);" + NL;
  protected final String TEXT_4 = NL + "    } // while" + NL + "    reader_";
  protected final String TEXT_5 = ".close();" + NL + "    final java.util.Map<String, Object> resultMap_";
  protected final String TEXT_6 = " = reader_";
  protected final String TEXT_7 = ".getReturnValues();";
  protected final String TEXT_8 = NL + "    org.talend.components.api.component.runtime.Result resultObject_";
  protected final String TEXT_9 = " = (org.talend.components.api.component.runtime.Result)writer_";
  protected final String TEXT_10 = " = writer_";
  protected final String TEXT_11 = ".getWriteOperation().finalize(java.util.Arrays.<org.talend.components.api.component.runtime.Result>asList(resultObject_";
  protected final String TEXT_12 = "), container_";
  protected final String TEXT_13 = ");";
  protected final String TEXT_14 = NL + "if(resultMap_";
  protected final String TEXT_15 = "!=null) {" + NL + "\tfor(java.util.Map.Entry<String,Object> entry_";
  protected final String TEXT_16 = " : resultMap_";
  protected final String TEXT_17 = ".entrySet()) {" + NL + "\t\tswitch(entry_";
  protected final String TEXT_18 = ".getKey()) {" + NL + "\t\tcase org.talend.components.api.component.ComponentDefinition.RETURN_ERROR_MESSAGE :" + NL + "\t\t\tcontainer_";
  protected final String TEXT_19 = ".setComponentData(\"";
  protected final String TEXT_20 = "\", \"ERROR_MESSAGE\", entry_";
  protected final String TEXT_21 = ".getValue());" + NL + "\t\t\tbreak;" + NL + "\t\tcase org.talend.components.api.component.ComponentDefinition.RETURN_TOTAL_RECORD_COUNT :" + NL + "\t\t\tcontainer_";
  protected final String TEXT_22 = "\", \"NB_LINE\", entry_";
  protected final String TEXT_23 = ".getValue());" + NL + "\t\t\tbreak;" + NL + "\t\tcase org.talend.components.api.component.ComponentDefinition.RETURN_SUCCESS_RECORD_COUNT :" + NL + "\t\t\tcontainer_";
  protected final String TEXT_24 = "\", \"NB_SUCCESS\", entry_";
  protected final String TEXT_25 = ".getValue());" + NL + "\t\t\tbreak;" + NL + "\t\tcase org.talend.components.api.component.ComponentDefinition.RETURN_REJECT_RECORD_COUNT :" + NL + "\t\t\tcontainer_";
  protected final String TEXT_26 = "\", \"NB_REJECT\", entry_";
  protected final String TEXT_27 = ".getValue());" + NL + "\t\t\tbreak;" + NL + "\t\tdefault :" + NL + "            StringBuilder studio_key_";
  protected final String TEXT_28 = " = new StringBuilder();" + NL + "            for (int i_";
  protected final String TEXT_29 = " = 0; i_";
  protected final String TEXT_30 = " < entry_";
  protected final String TEXT_31 = ".getKey().length(); i_";
  protected final String TEXT_32 = "++) {" + NL + "                char ch_";
  protected final String TEXT_33 = " = entry_";
  protected final String TEXT_34 = ".getKey().charAt(i_";
  protected final String TEXT_35 = ");" + NL + "                if(Character.isUpperCase(ch_";
  protected final String TEXT_36 = ") && i_";
  protected final String TEXT_37 = "> 0) {" + NL + "                \tstudio_key_";
  protected final String TEXT_38 = ".append('_');" + NL + "                }" + NL + "                studio_key_";
  protected final String TEXT_39 = ".append(ch_";
  protected final String TEXT_40 = ");" + NL + "            }" + NL + "\t\t\tcontainer_";
  protected final String TEXT_41 = "\", studio_key_";
  protected final String TEXT_42 = ".toString().toUpperCase(java.util.Locale.ENGLISH), entry_";
  protected final String TEXT_43 = ".getValue());" + NL + "\t\t\tbreak;" + NL + "\t\t}" + NL + "\t}" + NL + "}";
  protected final String TEXT_44 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();
Component component = (Component)node.getComponent();
ComponentDefinition def = component.getComponentDefinition();

IMetadataTable metadata = null;
List<IMetadataTable> metadatas = node.getMetadataList();
if ((metadatas != null) && (metadatas.size() > 0)) {
    metadata = metadatas.get(0);
}

// Return at this point if there is no metadata.
if (metadata == null) {
    return stringBuffer.toString();
}

boolean hasInput = !NodeUtil.getIncomingConnections(node, IConnectionCategory.DATA).isEmpty();
boolean hasOutput = !NodeUtil.getOutgoingConnections(node, IConnectionCategory.DATA).isEmpty();
boolean hasOutputOnly = hasOutput && !hasInput;

Set<ConnectorTopology> connectorTopologies = def.getSupportedConnectorTopologies();
boolean asInputComponent = connectorTopologies!=null && (connectorTopologies.size() < 3) && connectorTopologies.contains(ConnectorTopology.OUTGOING);

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    
List< ? extends IConnection> iterateLine = node.getOutgoingConnections(EConnectionType.ITERATE);
boolean hasIterateLine = iterateLine!=null && !iterateLine.isEmpty();
boolean isTopologyNone = !hasOutput && !hasInput && !hasIterateLine;

if(isTopologyNone) {
    return stringBuffer.toString();
}

else if(hasOutputOnly || asInputComponent){

    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    
}else if(hasInput){

    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    
} else {
	return stringBuffer.toString();
}

    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
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
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(TEXT_44);
    return stringBuffer.toString();
  }
}
