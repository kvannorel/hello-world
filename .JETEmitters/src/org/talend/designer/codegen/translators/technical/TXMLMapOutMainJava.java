package org.talend.designer.codegen.translators.technical;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.designer.xmlmap.XmlMapComponent;
import org.talend.designer.xmlmap.model.emf.xmlmap.XmlMapData;
import org.eclipse.emf.common.util.EList;
import org.talend.designer.xmlmap.generation.GenerationManager;
import org.talend.core.model.process.BlockCode;
import org.talend.designer.xmlmap.generation.HashedMetadataTable;
import org.talend.designer.xmlmap.model.emf.xmlmap.VarNode;
import org.talend.designer.xmlmap.model.emf.xmlmap.VarTable;
import org.talend.designer.xmlmap.model.emf.xmlmap.InputXmlTree;
import org.talend.designer.xmlmap.model.emf.xmlmap.OutputXmlTree;
import org.talend.designer.xmlmap.model.emf.xmlmap.TreeNode;
import org.talend.designer.xmlmap.model.emf.xmlmap.OutputTreeNode;
import org.talend.designer.xmlmap.model.emf.xmlmap.Connection;
import org.talend.designer.xmlmap.model.emf.xmlmap.LookupConnection;
import org.talend.designer.xmlmap.model.emf.xmlmap.FilterConnection;
import org.talend.designer.xmlmap.model.emf.xmlmap.NodeType;
import org.talend.designer.xmlmap.model.emf.xmlmap.InputLoopNodesTable;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.utils.NodeUtil;
import org.talend.core.model.metadata.IMetadataColumn;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.designer.xmlmap.model.emf.xmlmap.GlobalMapNode;

/**
 * add by wliu
 */
public class TXMLMapOutMainJava {

  protected static String nl;
  public static synchronized TXMLMapOutMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TXMLMapOutMainJava result = new TXMLMapOutMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "\tboolean ";
  protected final String TEXT_3 = " = false;" + NL + "\tboolean ";
  protected final String TEXT_4 = " = false;" + NL + "\tboolean isMatchDocRow";
  protected final String TEXT_5 = " = false;" + NL + "\t  " + NL + "\t";
  protected final String TEXT_6 = NL + "\t\t\t";
  protected final String TEXT_7 = NL;
  protected final String TEXT_8 = NL + "\t\t\t\t\t{";
  protected final String TEXT_9 = NL + "\t\t\t\ttreeNodeAPI_";
  protected final String TEXT_10 = ".clear();" + NL + "\t\t\t\t";
  protected final String TEXT_11 = NL + "\t\t\t\t\t//init document to flat tool" + NL + "\t\t\t\t\troutines.system.DocumentToFlat docToFlat_";
  protected final String TEXT_12 = "_";
  protected final String TEXT_13 = " = new routines.system.DocumentToFlat();" + NL + "\t\t\t\t\tdocToFlat_";
  protected final String TEXT_14 = ".setOriginalLoop(\"";
  protected final String TEXT_15 = "\");" + NL + "\t\t\t\t\tdocToFlat_";
  protected final String TEXT_16 = ".setIsOptional(";
  protected final String TEXT_17 = ");" + NL + "\t\t\t\t\tif(";
  protected final String TEXT_18 = ".";
  protected final String TEXT_19 = " == null " + NL + "\t\t\t\t\t\t|| ";
  protected final String TEXT_20 = ".getDocument() == null) {" + NL + "\t\t\t\t\t\tthrow new RuntimeException(\"";
  protected final String TEXT_21 = " can't be empty\");" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t\torg.dom4j.Document doc_";
  protected final String TEXT_22 = " = ";
  protected final String TEXT_23 = ".getDocument();" + NL + "\t\t\t\t\tdocToFlat_";
  protected final String TEXT_24 = ".setDoc(doc_";
  protected final String TEXT_25 = ");" + NL + "\t\t\t\t\tdocToFlat_";
  protected final String TEXT_26 = ".setDefineNS(";
  protected final String TEXT_27 = ".setNamespaceTool(nsTool_";
  protected final String TEXT_28 = ");" + NL + "\t\t\t\t\t";
  protected final String TEXT_29 = NL + "\t\t\t\t\t\t//old version, find NS from doc" + NL + "\t\t\t\t\t\tnsTool_";
  protected final String TEXT_30 = ".countNSMap(doc_";
  protected final String TEXT_31 = ".getRootElement());" + NL + "\t\t\t\t\t\tjava.util.HashMap<String,String> xmlNameSpaceMap_";
  protected final String TEXT_32 = " = nsTool_";
  protected final String TEXT_33 = ".xmlNameSpaceMap;" + NL + "\t\t\t\t\t";
  protected final String TEXT_34 = NL + "\t\t\t\t\t\tjava.util.HashMap<String,String> xmlNameSpaceMap_";
  protected final String TEXT_35 = " = new java.util.HashMap<String,String>();" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_36 = NL + "\t\t\t\t\t";
  protected final String TEXT_37 = NL + "\t\t\t\t\tdocToFlat_";
  protected final String TEXT_38 = ".setXmlNameSpaceMap(xmlNameSpaceMap_";
  protected final String TEXT_39 = NL + "\t\t\t\t\tjava.util.List<String> absolutePathMappingsList_";
  protected final String TEXT_40 = " = new java.util.ArrayList<String>();" + NL + "\t\t\t\t\tjava.util.List<String> relativePathMappingsList_";
  protected final String TEXT_41 = " = new java.util.ArrayList<String>();" + NL + "\t\t\t\t\t";
  protected final String TEXT_42 = NL + "\t\t\t\t\tabsolutePathMappingsList_";
  protected final String TEXT_43 = ".add(\"";
  protected final String TEXT_44 = "\");" + NL + "\t\t\t\t\trelativePathMappingsList_";
  protected final String TEXT_45 = "\");" + NL + "\t\t\t\t\t";
  protected final String TEXT_46 = NL + "\t\t\t\t\tString[] absolutePathMappings_";
  protected final String TEXT_47 = " = absolutePathMappingsList_";
  protected final String TEXT_48 = ".toArray(new String[0]);" + NL + "\t\t\t\t\tString[] relativePathMappings_";
  protected final String TEXT_49 = " = relativePathMappingsList_";
  protected final String TEXT_50 = ".toArray(new String[0]);" + NL + "\t\t\t\t\t" + NL + "\t\t\t\t\tdocToFlat_";
  protected final String TEXT_51 = ".setAbsolutePathMappings(absolutePathMappings_";
  protected final String TEXT_52 = ".setCurrentRelativePathMappings(relativePathMappings_";
  protected final String TEXT_53 = ");" + NL + "\t\t\t\t\t//generate document to flat data" + NL + "\t\t\t\t\tdocToFlat_";
  protected final String TEXT_54 = ".flat();" + NL + "\t\t\t\t\t//get flat data" + NL + "\t\t\t\t\tjava.util.List<java.util.Map<String, String>> resultSet_";
  protected final String TEXT_55 = " = docToFlat_";
  protected final String TEXT_56 = ".getResultSet();" + NL + "\t\t\t\t\t" + NL + "\t\t\t\t\tfor (java.util.Map<String,String> oneRow_";
  protected final String TEXT_57 = ": resultSet_";
  protected final String TEXT_58 = ") { // G_TXM_M_001";
  protected final String TEXT_59 = NL + "\t\t\t\t\t\tnb_line_";
  protected final String TEXT_60 = "++;" + NL + "\t\t\t\t    \t";
  protected final String TEXT_61 = " = false;" + NL + "\t\t\t\t    \t";
  protected final String TEXT_62 = " = false;" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_63 = "=false;" + NL + "\t\t\t\t\t    isMatchDocRow";
  protected final String TEXT_64 = " = false;";
  protected final String TEXT_65 = NL + "\t\t\t\t    \ttreeNodeAPI_";
  protected final String TEXT_66 = ".clear();";
  protected final String TEXT_67 = NL + "\t\t\t\t    \tfor(java.util.Map.Entry<String, String> entry_";
  protected final String TEXT_68 = " : oneRow_";
  protected final String TEXT_69 = ".entrySet()) {" + NL + "\t\t\t\t\t\t\ttreeNodeAPI_";
  protected final String TEXT_70 = ".put(entry_";
  protected final String TEXT_71 = ".getKey(),entry_";
  protected final String TEXT_72 = ".getValue());" + NL + "\t\t\t\t\t\t}";
  protected final String TEXT_73 = "\t";
  protected final String TEXT_74 = NL + "\t\t\t\t///////////////////////////////////////////////" + NL + "\t\t\t\t//Starting Lookup Table \"";
  protected final String TEXT_75 = "\" " + NL + "\t\t\t\t///////////////////////////////////////////////" + NL + "\t\t\t\t//tHash_Lookup_";
  protected final String TEXT_76 = ".initGet();" + NL + "\t\t\t\t";
  protected final String TEXT_77 = NL + "\t\t\t\t\t\t" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_78 = "HashKey.documentLookupResult.clear();" + NL + "\t\t\t\t\t\tjava.util.Map<String,String> xpathToPatternMap_";
  protected final String TEXT_79 = " = new java.util.HashMap<String,String>();" + NL + "\t\t\t\t\t\tjava.util.Map<String,String> xpathToTypeMap_";
  protected final String TEXT_80 = " = new java.util.HashMap<String,String>();" + NL + "\t\t\t\t    ";
  protected final String TEXT_81 = NL + "\t\t\t\t" + NL + "\t\t\t\t";
  protected final String TEXT_82 = NL + "\t\t\t\t\tboolean forceLoop";
  protected final String TEXT_83 = " = false;" + NL + "\t\t\t\t\t";
  protected final String TEXT_84 = " ";
  protected final String TEXT_85 = "ObjectFromLookup = null;" + NL + "\t\t\t\t\tboolean hasResultFromLookupCache";
  protected final String TEXT_86 = " = false;" + NL + "\t\t\t\t\tjava.util.Map<String,Object> cacheResult";
  protected final String TEXT_87 = " = new java.util.HashMap<String,Object>();" + NL + "\t\t\t\t";
  protected final String TEXT_88 = NL + "\t\t\t\t\t\t";
  protected final String TEXT_89 = "HashKey = new ";
  protected final String TEXT_90 = "Struct() {" + NL + "\t\t\t\t\t\t\t{" + NL + "\t\t\t\t\t\t\t\tthis.loopKey = \"";
  protected final String TEXT_91 = "\";" + NL + "\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t\t" + NL + "\t\t\t\t\t\t\tpublic int hashCode() {" + NL + "\t\t\t\t\t\t\t\tif(this.hashCodeDirty){" + NL + "\t\t\t\t\t\t\t\t\tfinal int prime = PRIME;" + NL + "\t\t\t\t\t\t\t\t\tint result = DEFAULT_HASHCODE;" + NL + "\t\t\t\t\t";
  protected final String TEXT_92 = NL + "\t\t\t\t        \t\t\tresult = prime * result + (this.";
  protected final String TEXT_93 = " ? 1231 : 1237);" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_94 = NL + "\t\t\t\t\t\t\t\t\tresult = prime * result + (int) this.";
  protected final String TEXT_95 = ";" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_96 = NL + "\t\t\t\t\t\t\t\tresult = prime * result + java.util.Arrays.hashCode(this.";
  protected final String TEXT_97 = ");" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_98 = NL + "\t\t\t\t\t\t\t\tresult = prime * result + ((this.";
  protected final String TEXT_99 = " == null) ? 0 : this.";
  protected final String TEXT_100 = ".hashCode());" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_101 = NL + "\t\t\t\t\t\t\t\t\tresult = prime * result + ((this.loopKey == null) ? 0 : this.loopKey.hashCode());" + NL + "\t\t\t\t\t\t\t\t\t" + NL + "\t\t\t\t\t\t\t\t\tthis.hashCode = result;" + NL + "    \t\t\t\t\t\t\t\tthis.hashCodeDirty = false;\t" + NL + "\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t\t\treturn this.hashCode;" + NL + "\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t\t" + NL + "\t\t\t\t\t\t\tpublic boolean equals(Object obj) {" + NL + "\t\t\t\t\t\t\t\tif (this == obj) return true;" + NL + "\t\t\t\t\t\t\t\tif (obj == null) return false;" + NL + "\t\t\t\t\t\t\t\tif(!(obj instanceof ";
  protected final String TEXT_102 = "Struct)) return false;" + NL + "\t\t\t\t\t\t\t\tfinal ";
  protected final String TEXT_103 = "Struct other = (";
  protected final String TEXT_104 = "Struct) obj;" + NL + "\t\t\t\t\t\t\t\tif(!this.loopKey.equals(other.loopKey)) return false;";
  protected final String TEXT_105 = NL + "\t\t\t\t\t\t\t\t\t\tif (this.";
  protected final String TEXT_106 = " != other.";
  protected final String TEXT_107 = ") " + NL + "\t\t\t\t\t\t\t\t\t\t\treturn false;" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_108 = NL + "\t\t\t\t\t\t\t\t\t\tif(!java.util.Arrays.equals(this.";
  protected final String TEXT_109 = ", other.";
  protected final String TEXT_110 = ")) {" + NL + "\t\t\t\t\t\t\t\t\t\t\treturn false;" + NL + "\t\t\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_111 = " == null) {" + NL + "\t\t\t\t\t\t\t\t\t\t\tif (other.";
  protected final String TEXT_112 = " != null) " + NL + "\t\t\t\t\t\t\t\t\t\t\t\treturn false;" + NL + "\t\t\t\t\t\t\t\t\t\t} else if (!this.";
  protected final String TEXT_113 = ".equals(other.";
  protected final String TEXT_114 = ")) " + NL + "\t\t\t\t\t\t\t\t\t\t\treturn false;" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_115 = NL + "\t\t\t\t\t\t\t\treturn true;" + NL + "\t\t\t\t\t\t\t} " + NL + "\t\t\t\t\t\t\t" + NL + "\t\t\t\t\t\t};" + NL + "\t\t\t\t\t";
  protected final String TEXT_116 = NL + "\t\t   \t\t\t\tList<Object> lookupCacheKey";
  protected final String TEXT_117 = " = new java.util.ArrayList<Object>();" + NL + "\t\t   \t\t\t";
  protected final String TEXT_118 = NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_119 = "HashKey.documentLookupResult.put(\"";
  protected final String TEXT_120 = "\",";
  protected final String TEXT_121 = ");" + NL + "\t\t\t\t\t\t\txpathToPatternMap_";
  protected final String TEXT_122 = ".put(\"";
  protected final String TEXT_123 = ");" + NL + "\t\t\t\t\t\t\txpathToTypeMap_";
  protected final String TEXT_124 = "\",\"";
  protected final String TEXT_125 = "\");" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_126 = NL + "\t\t\t\t\t\t\t\tlookupCacheKey";
  protected final String TEXT_127 = ".add(";
  protected final String TEXT_128 = ");" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_129 = NL + "\t\t\t\t\t   \t";
  protected final String TEXT_130 = "HashKey.";
  protected final String TEXT_131 = ";" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_132 = "HashKey.hashCodeDirty = true;" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_133 = NL + "\t\t\t\t\t\tlookupCacheKey";
  protected final String TEXT_134 = "HashKey);" + NL + "\t\t\t\t\t";
  protected final String TEXT_135 = NL + "\t\t\t\t\t" + NL + "\t\t\t\t\t";
  protected final String TEXT_136 = NL + "\t\t\t\t\t\tif(!";
  protected final String TEXT_137 = "){//TD120" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_138 = NL + "\t\t\t\t\t\t\t\t\ttHash_Lookup_Cache_";
  protected final String TEXT_139 = ".lookup(lookupCacheKey";
  protected final String TEXT_140 = ");" + NL + "\t\t  \t\t\t\t\t\t\tif(tHash_Lookup_Cache_";
  protected final String TEXT_141 = ".hasNext()) {" + NL + "\t  \t\t\t\t\t\t\t\t\thasResultFromLookupCache";
  protected final String TEXT_142 = " = true;" + NL + "\t\t  \t\t\t\t\t\t\t} else {" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_143 = NL + "\t\t\t\t\t\t\t\t\t\tglobalMap.put(";
  protected final String TEXT_144 = ", ";
  protected final String TEXT_145 = ");" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_146 = NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_147 = "Process(globalMap);" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_148 = NL + "\t\t\t\t\t\t\t\t\ttHash_Lookup_";
  protected final String TEXT_149 = " = " + NL + "\t\t\t\t\t\t\t\t\t\t(org.talend.designer.components.lookup.persistent.Persistent";
  protected final String TEXT_150 = "LookupManager<";
  protected final String TEXT_151 = "Struct>) " + NL + "\t\t\t\t\t\t\t\t\t\t\t((org.talend.designer.components.lookup.persistent.Persistent";
  protected final String TEXT_152 = "Struct>) " + NL + "\t\t\t\t\t\t\t\t\t\t\t\t globalMap.get( \"tHash_Lookup_";
  protected final String TEXT_153 = "\" ));" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_154 = " = " + NL + "\t\t\t\t\t\t\t\t\t(org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<";
  protected final String TEXT_155 = "Struct>) " + NL + "\t\t\t\t\t\t\t\t\t\t((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<";
  protected final String TEXT_156 = "Struct>) " + NL + "\t\t\t\t\t\t\t\t\t\t\tglobalMap.get( \"tHash_Lookup_";
  protected final String TEXT_157 = NL + "\t\t\t\t\t    \t\t\ttHash_Lookup_";
  protected final String TEXT_158 = ".initGet();" + NL + "\t\t\t\t\t    \t\t";
  protected final String TEXT_159 = ".lookup( ";
  protected final String TEXT_160 = "HashKey );" + NL + "\t\t\t\t\t    \t\t";
  protected final String TEXT_161 = ".lookup();" + NL + "\t\t\t\t\t    \t\t";
  protected final String TEXT_162 = NL + "\t\t\t\t\t\t    \t\t}" + NL + "\t\t\t\t\t\t\t    ";
  protected final String TEXT_163 = NL + "\t\t\t\t\t" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_164 = NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_165 = "HashKey );" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_166 = ".lookup();" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_167 = NL + "\t\t\t\t\t\t\tif(hasResultFromLookupCache";
  protected final String TEXT_168 = "  || tHash_Lookup_";
  protected final String TEXT_169 = ".hasNext()){" + NL + "\t\t\t\t\t\t\t" + NL + "\t\t\t\t\t\t\t} else {" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_170 = " = true;" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_171 = " = true;" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_172 = "Default;" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_173 = NL + "\t\t\t\t\t\t\t\t" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_174 = NL + "\t\t\t\t\t\t\t\tforceLoop";
  protected final String TEXT_175 = NL + "\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t}//TD120" + NL + "\t\t\t\t   " + NL + "\t\t\t\t   \t\t";
  protected final String TEXT_176 = NL + "\t\t\t\t   \t\t\tif(";
  protected final String TEXT_177 = ") {" + NL + "\t\t\t\t   \t\t\t\tforceLoop";
  protected final String TEXT_178 = " = true;" + NL + "\t\t\t\t   \t\t\t}" + NL + "\t\t\t\t   \t\t";
  protected final String TEXT_179 = NL + "\t\t\t\t   " + NL + "\t\t\t\t   \t\t";
  protected final String TEXT_180 = "Struct fromLookup_";
  protected final String TEXT_181 = " = null;" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_182 = "Default;" + NL + "\t\t\t\t   " + NL + "\t\t\t\t   \t\t";
  protected final String TEXT_183 = NL + "\t\t\t\t\t    if(hasResultFromLookupCache";
  protected final String TEXT_184 = ") {//TD10086" + NL + "\t\t\t\t\t    \t";
  protected final String TEXT_185 = NL + "\t\t\t\t\t    \tjava.util.Map<String,Object> cacheResult_";
  protected final String TEXT_186 = " = tHash_Lookup_Cache_";
  protected final String TEXT_187 = ".next();" + NL + "\t\t\t\t\t    \t\t";
  protected final String TEXT_188 = NL + "\t\t\t\t\t    \t\t\ttreeNodeAPI_";
  protected final String TEXT_189 = "\",StringUtils.valueOf(cacheResult_";
  protected final String TEXT_190 = ".get(\"";
  protected final String TEXT_191 = "\")));" + NL + "\t\t\t\t\t    \t\t";
  protected final String TEXT_192 = NL + "\t\t\t\t    \t\t\t";
  protected final String TEXT_193 = " = (";
  protected final String TEXT_194 = ")cacheResult_";
  protected final String TEXT_195 = "\");" + NL + "\t\t\t\t\t    \t";
  protected final String TEXT_196 = NL + "\t\t\t\t\t    } else if(!";
  protected final String TEXT_197 = ") {" + NL + "\t\t\t\t\t\t  \t";
  protected final String TEXT_198 = NL + "\t\t\t\t\t\t   \tisMatchDocRow";
  protected final String TEXT_199 = " = false;" + NL + "\t\t\t\t\t\t  \t";
  protected final String TEXT_200 = NL + "\t\t\t\t\t\t\twhile (tHash_Lookup_";
  protected final String TEXT_201 = ".hasNext()) {//TD119" + NL + "\t\t\t\t\t\t\t\tfromLookup_";
  protected final String TEXT_202 = " = null;" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_203 = "Default;" + NL + "\t\t\t\t\t\t\t\tfromLookup_";
  protected final String TEXT_204 = " = tHash_Lookup_";
  protected final String TEXT_205 = ".next();" + NL + "\t\t\t\t\t\t\t\tif (fromLookup_";
  protected final String TEXT_206 = " != null) {" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_207 = NL + "\t\t\t\t\t\t\t\t\t//begin to lookup Document Object (case 1:lookup doc key exists),(case 2:lookup doc key not exists but lookup line doc output exist)" + NL + "\t\t\t\t\t\t\t\t\troutines.system.Document lookupDoc_";
  protected final String TEXT_208 = " = fromLookup_";
  protected final String TEXT_209 = ";" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_210 = NL + "\t\t\t\t\t\t\t\t\tboolean quit";
  protected final String TEXT_211 = " = false;" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_212 = NL + "\t\t\t\t\t\t\t\t\tjava.util.Map<String,String> xPathAsOutput_";
  protected final String TEXT_213 = " = new java.util.HashMap<String,String>();" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_214 = NL + "\t\t\t\t\t\t\t\t\txPathAsOutput_";
  protected final String TEXT_215 = "\");" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_216 = NL + "\t\t\t\t\t\t\t\t\tif(lookupDoc_";
  protected final String TEXT_217 = " == null) {" + NL + "\t\t\t\t\t\t\t\t\t\tthrow new RuntimeException(\"";
  protected final String TEXT_218 = " can't be empty\");" + NL + "\t\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t\t\t\tList<java.util.Map<String,Object>> result_";
  protected final String TEXT_219 = " = lookupDoc_";
  protected final String TEXT_220 = ".LookupDocument(\"";
  protected final String TEXT_221 = ",";
  protected final String TEXT_222 = "HashKey.documentLookupResult," + NL + "\t\t\t\t\t\t\t\t\t\txPathAsOutput_";
  protected final String TEXT_223 = ",xmlNameSpaceMap_";
  protected final String TEXT_224 = ",xpathToTypeMap_";
  protected final String TEXT_225 = ",xpathToPatternMap_";
  protected final String TEXT_226 = ",\"";
  protected final String TEXT_227 = "\");" + NL + "\t\t\t\t\t\t\t\t\tif(result_";
  protected final String TEXT_228 = " == null || result_";
  protected final String TEXT_229 = ".size() == 0) {" + NL + "\t\t\t\t\t\t\t\t\t\t// not find" + NL + "\t\t\t\t\t\t\t\t\t} else {" + NL + "\t\t\t\t\t\t\t\t\t\t//find and cache it,now only memory,inner join and unique match mode" + NL + "\t\t\t\t\t\t\t\t\t\t//once find,not reject." + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_230 = NL + "\t\t\t\t\t\t\t\t\t\tisMatchDocRow";
  protected final String TEXT_231 = " = true;" + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_232 = NL + "\t\t\t\t\t\t\t\t\t\tfor(java.util.Map<String,Object> docRow_";
  protected final String TEXT_233 = " : result_";
  protected final String TEXT_234 = ") {" + NL + "\t\t\t\t\t\t\t\t\t\t\tfor(String path_";
  protected final String TEXT_235 = " : docRow_";
  protected final String TEXT_236 = ".keySet()) {" + NL + "\t\t\t\t\t\t\t\t\t\t\t\ttreeNodeAPI_";
  protected final String TEXT_237 = ".put(path_";
  protected final String TEXT_238 = ", StringUtils.valueOf(docRow_";
  protected final String TEXT_239 = ".get(path_";
  protected final String TEXT_240 = ")));" + NL + "\t\t\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_241 = NL + "\t\t\t\t\t\t\t\t\t\t\t\tcacheResult";
  protected final String TEXT_242 = ",StringUtils.valueOf(docRow_";
  protected final String TEXT_243 = NL + "\t\t\t\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_244 = NL + "\t\t\t\t\t\t\t\t\t\tquit";
  protected final String TEXT_245 = NL + "\t\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_246 = ";" + NL + "\t\t\t\t\t\t\t\t\t" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_247 = NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_248 = NL + "\t\t\t\t\t\t\t\t\t\tif(isMatchDocRow";
  protected final String TEXT_249 = ") {" + NL + "\t\t\t\t\t\t\t\t\t\t\tcacheResult";
  protected final String TEXT_250 = ");" + NL + "\t\t\t\t\t\t\t\t\t\t\ttHash_Lookup_Cache_";
  protected final String TEXT_251 = ".put(lookupCacheKey";
  protected final String TEXT_252 = ",cacheResult";
  protected final String TEXT_253 = "); " + NL + "\t\t\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_254 = NL + "\t\t\t\t\t\t\t\t\t\tcacheResult";
  protected final String TEXT_255 = "); " + NL + "\t\t\t\t\t\t\t\t\t\ttHash_Lookup_Cache_";
  protected final String TEXT_256 = "); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_257 = NL + "\t\t\t\t\t\t\t\t\t" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_258 = NL + "\t\t\t\t\t\t\t\t\t\tif(quit";
  protected final String TEXT_259 = ") {//for first match,once find in document,no need to continue to lookup." + NL + "\t\t\t\t\t\t\t\t\t\t\tbreak;" + NL + "\t\t\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_260 = NL + "\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t\t}//TD119" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_261 = NL + "\t\t\t\t\t\t   \t";
  protected final String TEXT_262 = " = !isMatchDocRow";
  protected final String TEXT_263 = " && !hasResultFromLookupCache";
  protected final String TEXT_264 = ";" + NL + "\t\t\t\t\t\t    ";
  protected final String TEXT_265 = ";" + NL + "\t\t\t\t\t\t  \t";
  protected final String TEXT_266 = NL + "\t\t\t\t\t\t  \t";
  protected final String TEXT_267 = NL + "\t\t\t\t\t\t  \tif(!isMatchDocRow";
  protected final String TEXT_268 = ") {" + NL + "\t\t\t\t\t\t  \t\t";
  protected final String TEXT_269 = "Default;" + NL + "\t\t\t\t\t\t  \t}" + NL + "\t\t\t\t\t\t  \t";
  protected final String TEXT_270 = NL + "\t\t\t\t\t\t  \t\t//do nothing" + NL + "\t\t\t\t\t\t  \t";
  protected final String TEXT_271 = NL + "\t\t\t\t  \t\t}//TD10086" + NL + "\t\t\t\t  \t\t" + NL + "\t\t\t\t  \t\t";
  protected final String TEXT_272 = NL + "\t\t\t\t  \t\t\tif(!";
  protected final String TEXT_273 = " &&" + NL + "\t\t\t\t  \t\t\t\t!(";
  protected final String TEXT_274 = ")" + NL + "\t\t\t\t  \t\t\t) {" + NL + "\t\t\t\t  \t\t\t\t";
  protected final String TEXT_275 = "Default;" + NL + "\t\t\t\t  \t\t\t\t" + NL + "\t\t\t\t  \t\t\t\t";
  protected final String TEXT_276 = " " + NL + "\t\t\t\t  \t\t\t\ttreeNodeAPI_";
  protected final String TEXT_277 = "\",null);" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_278 = NL + "\t\t\t\t  \t\t\t\t" + NL + "\t\t\t\t  \t\t\t\t";
  protected final String TEXT_279 = " = true;\t\t\t  \t\t\t\t" + NL + "\t\t\t\t  \t\t\t\t";
  protected final String TEXT_280 = NL + "\t\t\t\t  \t\t\t}" + NL + "\t\t\t\t  \t\t";
  protected final String TEXT_281 = NL + "\t\t\t\t  \t\t" + NL + "\t\t\t\t  \t\t//////////////////////////////////////////////////////////////////////////////////////////////" + NL + "\t\t\t\t  \t\t";
  protected final String TEXT_282 = NL + "\t\t\t\t  \t\tboolean fromCache";
  protected final String TEXT_283 = " = hasResultFromLookupCache";
  protected final String TEXT_284 = ";" + NL + "\t\t\t\t  \t\tList<java.util.Map<String,Object>> multipleResultSet";
  protected final String TEXT_285 = " = new java.util.ArrayList<java.util.Map<String,Object>>();" + NL + "\t\t\t\t  \t\t//the var for cache the ";
  protected final String TEXT_286 = "Struct" + NL + "\t\t\t\t  \t\tjava.util.Map<String,Object> oneRow_";
  protected final String TEXT_287 = " = null;" + NL + "\t\t\t\t\t\tif(hasResultFromLookupCache";
  protected final String TEXT_288 = ") {" + NL + "\t\t\t\t\t    \t";
  protected final String TEXT_289 = ".next();" + NL + "\t\t\t\t\t    \tmultipleResultSet";
  protected final String TEXT_290 = ".add(cacheResult_";
  protected final String TEXT_291 = ");" + NL + "\t\t\t\t\t    \t";
  protected final String TEXT_292 = NL + "\t\t\t\t\t    \twhile (tHash_Lookup_";
  protected final String TEXT_293 = ".hasNext()) {" + NL + "\t\t\t\t\t    \t\tfromLookup_";
  protected final String TEXT_294 = ".next();" + NL + "\t\t\t\t\t\t\t\tif(fromLookup_";
  protected final String TEXT_295 = NL + "\t\t\t\t\t\t\t\t\troutines.system.Document lookupDoc_";
  protected final String TEXT_296 = ";" + NL + "\t\t\t\t\t\t\t\t\tjava.util.Map<String,String> xPathAsOutput_";
  protected final String TEXT_297 = " = new java.util.HashMap<String,String>();" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_298 = NL + "\t\t\t\t\t\t\t\t\t\txPathAsOutput_";
  protected final String TEXT_299 = "\");" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_300 = ".size() == 0) {" + NL + "\t\t\t\t\t\t\t\t\t\t//do nothing" + NL + "\t\t\t\t\t\t\t\t\t} else {" + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_301 = ") {" + NL + "\t\t\t\t\t\t\t\t\t\t\tdocRow_";
  protected final String TEXT_302 = "\",fromLookup_";
  protected final String TEXT_303 = ");" + NL + "\t\t\t\t\t\t\t\t\t\t\tmultipleResultSet";
  protected final String TEXT_304 = ".add(docRow_";
  protected final String TEXT_305 = ");" + NL + "\t\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_306 = NL + "\t\t\t\t\t\t\t\t\t\t\tfor(String path_";
  protected final String TEXT_307 = ".keySet()) {" + NL + "\t\t\t\t\t\t\t\t\t\t\t\tcacheResult";
  protected final String TEXT_308 = ")));" + NL + "\t\t\t\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t\t\t\t\t\tcacheResult";
  protected final String TEXT_309 = NL + "\t\t\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_310 = "\t" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_311 = ";" + NL + "\t\t\t\t\t\t\t\t\t//construct the resultset for mutiple lookup when no document lookup." + NL + "\t\t\t\t\t\t\t\t\toneRow_";
  protected final String TEXT_312 = " = new java.util.HashMap<String, Object>();" + NL + "\t\t\t\t\t\t\t\t\toneRow_";
  protected final String TEXT_313 = ");" + NL + "\t\t\t\t\t\t\t\t\tmultipleResultSet";
  protected final String TEXT_314 = ".add(oneRow_";
  protected final String TEXT_315 = NL + "\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t    \t}//end while" + NL + "\t\t\t\t\t    \t" + NL + "\t\t\t\t\t    }//end else if" + NL + "\t\t\t\t\t    //now not support cache all_matches lookup" + NL + "\t\t\t\t\t    ";
  protected final String TEXT_316 = NL + "\t\t\t\t\t    \tforceLoop";
  protected final String TEXT_317 = ";" + NL + "\t\t\t\t\t    ";
  protected final String TEXT_318 = NL + "\t\t\t\t\t    " + NL + "\t\t\t\t\t    ";
  protected final String TEXT_319 = ";" + NL + "\t\t\t\t\t    forceLoop";
  protected final String TEXT_320 = ";" + NL + "\t\t\t\t\t  \t";
  protected final String TEXT_321 = NL + "\t\t\t\t\t    " + NL + "\t\t\t\t\t    java.util.Iterator<java.util.Map<String,Object>> iter";
  protected final String TEXT_322 = " = multipleResultSet";
  protected final String TEXT_323 = ".iterator();" + NL + "\t\t\t\t\t    while(iter";
  protected final String TEXT_324 = ".hasNext() || forceLoop";
  protected final String TEXT_325 = ") { // G_TM_M_002" + NL + "\t\t\t\t\t    \t";
  protected final String TEXT_326 = "Default;" + NL + "\t\t\t\t\t    \t";
  protected final String TEXT_327 = "Struct tempLookup";
  protected final String TEXT_328 = " = null; " + NL + "\t\t\t\t\t    \tif(!forceLoop";
  protected final String TEXT_329 = ") {" + NL + "\t\t\t\t\t\t    \tjava.util.Map<String,Object> oneRow";
  protected final String TEXT_330 = " = iter";
  protected final String TEXT_331 = ".next();" + NL + "\t\t\t\t\t\t   \t\t";
  protected final String TEXT_332 = NL + "\t\t\t\t\t\t    \t\t\ttreeNodeAPI_";
  protected final String TEXT_333 = "\",StringUtils.valueOf(oneRow";
  protected final String TEXT_334 = "\")));" + NL + "\t\t\t\t\t\t    \t";
  protected final String TEXT_335 = NL + "\t\t\t\t\t    \t\ttempLookup";
  protected final String TEXT_336 = ")oneRow";
  protected final String TEXT_337 = "\");" + NL + "\t\t\t\t\t    \t\tif(tempLookup";
  protected final String TEXT_338 = "!=null) {" + NL + "\t\t\t\t\t    \t\t\t";
  protected final String TEXT_339 = " = tempLookup";
  protected final String TEXT_340 = ";" + NL + "\t\t\t\t\t    \t\t}" + NL + "\t\t\t\t\t    \t\t" + NL + "\t\t\t\t\t    \t\t";
  protected final String TEXT_341 = NL + "\t\t\t\t\t    \t\tif(!";
  protected final String TEXT_342 = " &&" + NL + "\t\t\t\t  \t\t\t\t\t!(";
  protected final String TEXT_343 = ")" + NL + "\t\t\t\t  \t\t\t\t) {" + NL + "\t\t\t\t  \t\t\t\t\t";
  protected final String TEXT_344 = "Default;" + NL + "\t\t\t\t  \t\t\t\t" + NL + "\t\t\t\t  \t\t\t\t\t";
  protected final String TEXT_345 = " " + NL + "\t\t\t\t  \t\t\t\t\ttreeNodeAPI_";
  protected final String TEXT_346 = "\",null);" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_347 = NL + "\t\t\t\t  \t\t\t\t" + NL + "\t\t\t\t  \t\t\t\t\t";
  protected final String TEXT_348 = " = true;\t\t\t  \t\t\t\t" + NL + "\t\t\t\t  \t\t\t\t\t";
  protected final String TEXT_349 = NL + "\t\t\t\t  \t\t\t\t}" + NL + "\t\t\t\t\t    \t\t";
  protected final String TEXT_350 = NL + "\t\t\t\t    \t\t}" + NL + "\t\t\t\t    \t\tforceLoop";
  protected final String TEXT_351 = " = false;" + NL + "\t\t\t\t  \t\t";
  protected final String TEXT_352 = NL + "\t\t\t\t  \t\t/////////////////////////////////////////////////////////////////////////////////////////////////" + NL + "\t\t\t\t  \t\t" + NL + "\t\t\t\t\t";
  protected final String TEXT_353 = NL + "\t\t\t\t";
  protected final String TEXT_354 = NL + "{ // start of Var scope" + NL + "" + NL + "\t// ###############################" + NL + "\t// # Vars tables";
  protected final String TEXT_355 = NL + NL + "\t\t\t";
  protected final String TEXT_356 = NL + "\t\t// ###############################" + NL + "\t\t// # Output table";
  protected final String TEXT_357 = NL + "\t\t\t\t}// G_TM_M_002";
  protected final String TEXT_358 = NL + "\t\t\t\t}//close G_TXM_M_001";
  protected final String TEXT_359 = NL + "\t\t\t\t\t}";
  protected final String TEXT_360 = NL + "\t\t\t\t    gen_Doc_";
  protected final String TEXT_361 = " = new GenerateDocument_";
  protected final String TEXT_362 = "();" + NL + "\t\t\t\t    " + NL + "\t\t\t\t\t";
  protected final String TEXT_363 = "_tmp = new ";
  protected final String TEXT_364 = "Struct();" + NL + "\t\t\t\t";
  protected final String TEXT_365 = "   " + NL + "\t\t\t\t\t";
  protected final String TEXT_366 = "_tmp.";
  protected final String TEXT_367 = " = null;" + NL + "\t\t\t\t\t";
  protected final String TEXT_368 = NL + "\t\t" + NL + "\t\t";
  protected final String TEXT_369 = NL + "\t\ttreeNodeAPI_";
  protected final String TEXT_370 = ".clear();" + NL + "\t\t";
  protected final String TEXT_371 = NL + "\t\t\t\t//init document to flat tool" + NL + "\t\t\t\troutines.system.DocumentToFlat docToFlat_";
  protected final String TEXT_372 = " = new routines.system.DocumentToFlat();" + NL + "\t\t\t\tdocToFlat_";
  protected final String TEXT_373 = "\");" + NL + "\t\t\t\tdocToFlat_";
  protected final String TEXT_374 = ");" + NL + "\t\t\t\tif(";
  protected final String TEXT_375 = " == null || ";
  protected final String TEXT_376 = ".getDocument() == null) {" + NL + "\t\t\t\t\tthrow new RuntimeException(\"";
  protected final String TEXT_377 = " can't be empty\");" + NL + "\t\t\t\t}" + NL + "\t\t\t\torg.dom4j.Document doc_";
  protected final String TEXT_378 = ".getDocument();" + NL + "\t\t\t\tdocToFlat_";
  protected final String TEXT_379 = ");" + NL + "\t\t\t\tdocToFlat_";
  protected final String TEXT_380 = ");" + NL + "\t\t\t\t";
  protected final String TEXT_381 = NL + "\t\t\t\t\t//old version, find NS from doc" + NL + "\t\t\t\t\tnsTool_";
  protected final String TEXT_382 = ".getRootElement());" + NL + "\t\t\t\t\tjava.util.HashMap<String,String> xmlNameSpaceMap_";
  protected final String TEXT_383 = ".xmlNameSpaceMap;" + NL + "\t\t\t\t";
  protected final String TEXT_384 = NL + "\t\t\t\t\tjava.util.HashMap<String,String> xmlNameSpaceMap_";
  protected final String TEXT_385 = " = new java.util.HashMap<String,String>();" + NL + "\t\t\t\t\t";
  protected final String TEXT_386 = NL + "\t\t\t\tdocToFlat_";
  protected final String TEXT_387 = NL + "\t\t\t\tString[] absolutePathMappings_";
  protected final String TEXT_388 = " = new String[";
  protected final String TEXT_389 = "];" + NL + "\t\t\t\tString[] relativePathMappings_";
  protected final String TEXT_390 = "];" + NL + "\t\t\t\t";
  protected final String TEXT_391 = NL + "\t\t\t\tabsolutePathMappings_";
  protected final String TEXT_392 = "[";
  protected final String TEXT_393 = "] = \"";
  protected final String TEXT_394 = "\";" + NL + "\t\t\t\trelativePathMappings_";
  protected final String TEXT_395 = "\";" + NL + "\t\t\t\t";
  protected final String TEXT_396 = ");" + NL + "\t\t\t\t//generate document to flat data" + NL + "\t\t\t\tdocToFlat_";
  protected final String TEXT_397 = ".flat();" + NL + "\t\t\t\t//get flat data" + NL + "\t\t\t\tjava.util.List<java.util.Map<String, String>> resultSet_";
  protected final String TEXT_398 = ".getResultSet();" + NL + "\t\t\t\t" + NL + "\t\t\t\tfor (java.util.Map<String,String> oneRow_";
  protected final String TEXT_399 = ") { // G_TXM_M_001" + NL + "\t\t\t\t\tnb_line_";
  protected final String TEXT_400 = "++;" + NL + "\t\t\t    \t";
  protected final String TEXT_401 = " = false;" + NL + "\t\t\t    \t";
  protected final String TEXT_402 = "=false;" + NL + "\t\t\t\t    isMatchDocRow";
  protected final String TEXT_403 = " = false;" + NL + "\t\t\t\t\t" + NL + "\t\t\t    \ttreeNodeAPI_";
  protected final String TEXT_404 = ".clear();" + NL + "\t\t\t    \tfor(java.util.Map.Entry<String, String> entry_";
  protected final String TEXT_405 = ".entrySet()) {" + NL + "\t\t\t\t\t\ttreeNodeAPI_";
  protected final String TEXT_406 = ".getValue());" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t\t";
  protected final String TEXT_407 = NL + NL + "\t\t";
  protected final String TEXT_408 = NL + "{ // start of Var scope" + NL + "" + NL + "\t// ###############################" + NL + "\t// # Vars tables" + NL;
  protected final String TEXT_409 = NL + "\t\t// ###############################" + NL + "\t\t// # Output tables";
  protected final String TEXT_410 = NL + "\t\t}// G_TM_M_002";
  protected final String TEXT_411 = NL + "\t\t}//G_TXM_M_001 close" + NL + "\t";

	class XMLMapUtil {
	
		private XmlMapData getXmlMapData(INode node) {
		
			XmlMapData xmlMapData = (XmlMapData)ElementParameterParser.getObjectValueXMLTree(node);
			
			return xmlMapData;
		}
		
		private List<IConnection> filterAndSort(INode node) {
			
			XmlMapData xmlMapData= getXmlMapData(node);
			EList<InputXmlTree> inputTables = xmlMapData.getInputTrees();
			final List<String> tableNames = new ArrayList<String>();
			HashMap<String, InputXmlTree> hNameToInputXmlTree = new HashMap<String, InputXmlTree>();
			
			for(InputXmlTree inputTable : inputTables){
				hNameToInputXmlTree.put(inputTable.getName(), inputTable);
				tableNames.add(inputTable.getName());
			}
			
			List<IConnection> inputConnections = new ArrayList<IConnection>();
			
			for(IConnection conn : node.getIncomingConnections()){
				if(hNameToInputXmlTree.get(conn.getName()) != null){
					inputConnections.add(conn);
				}
			}
			
			java.util.Collections.sort(inputConnections,new java.util.Comparator<IConnection>() {
				public int compare(IConnection conn1, IConnection conn2) {
					return tableNames.indexOf(conn1.getName()) - tableNames.indexOf(conn2.getName());
				}	
			});
			
			return inputConnections;
		}
	
		public String createSignature(INode node, boolean force) {
			String toReturn = "";
			
			List<IConnection> inputConnections = filterAndSort(node);
			
			for(IConnection conn : inputConnections) {
				if (conn.getLineStyle().equals(EConnectionType.FLOW_MAIN) || conn.getLineStyle().equals(EConnectionType.FLOW_MERGE) || conn.getLineStyle().equals(EConnectionType.FLOW_REF)) {
					if ((force)|| conn.getLineStyle().equals(EConnectionType.FLOW_REF) ||(conn.getSource().isSubProcessStart() || !(NodeUtil.isDataAutoPropagated(conn.getSource())))) {
						toReturn+=", "+conn.getName() + "Struct " + conn.getName();
					} else {
						toReturn+=", "+getConnectionType(conn)+"Struct " + conn.getName();
					}
				}
			}
    		return toReturn;
    	}
    	
    	public String getConnectionType(IConnection currentConn) {
    	    INode sourceNode = currentConn.getSource();
    		for(IConnection conn : sourceNode.getIncomingConnections()) {
				if (conn.getLineStyle().equals(EConnectionType.FLOW_MAIN) || conn.getLineStyle().equals(EConnectionType.FLOW_MERGE) || conn.getLineStyle().equals(EConnectionType.FLOW_REF)) {
					if ((conn.getLineStyle().equals(EConnectionType.FLOW_REF) || conn.getSource().isSubProcessStart() || !(NodeUtil.isDataAutoPropagated(conn.getSource())))) {
						return conn.getName();
					} else {
						return getConnectionType(conn);
					}
				} else if (conn.getLineStyle().equals(EConnectionType.ITERATE)||conn.getLineStyle().equals(EConnectionType.ON_ROWS_END)) {
				    if(sourceNode.getOutgoingConnections() != null && sourceNode.getOutgoingConnections().size() > 0) {
				        return currentConn.getName();
				    }
				}
			}
			return "";
    	}
    	
    	public boolean tableHasADocument(EList<OutputTreeNode> outputTableEntries) {
    		for (OutputTreeNode outputTableEntry : outputTableEntries) {
    			if(("id_Document").equals(outputTableEntry.getType())){
    				return true;
    			}
    		}
    		return false;
    	}
	}

	class MatchXmlTreeExpr {
		String cid = null;
		java.util.Map<String, String> pathTypeMap = new java.util.HashMap<String, String>(); // Map<input table xpath, typeToGenerate>
		java.util.Map<String, String> pathPatternMap = new java.util.HashMap<String, String>(); // Map<input table xpath, Pattern>
		java.util.Map<String, String> pathTypeToGenMap = new java.util.HashMap<String, String>(); // Map<"/root/name","String">
		
		//only for main input table
		public MatchXmlTreeExpr(TreeNode node, String cid) {
			this.cid = cid;
			init(node);
		}
		
		//for main and all lookup tables
		public MatchXmlTreeExpr(List<TreeNode> nodes, String cid) {
			this.cid = cid;
			for(TreeNode node : nodes) {
				init(node);
			}
		}
		
		//NO TYPE CHECK 
		public MatchXmlTreeExpr(String cid) {
			this.cid = cid;
		}
		
		private void init(TreeNode node) {
			if(node.getOutgoingConnections().size()>0 || node.getLookupOutgoingConnections().size()>0 || node.getFilterOutGoingConnections().size()>0){
				String talendType = node.getType();
				JavaType javaType = JavaTypesManager.getJavaTypeFromId(talendType);
				String typeToGenerate = JavaTypesManager.getTypeToGenerate(talendType, node.isNullable());
				String patternValue = node.getPattern() == null || node.getPattern().trim().length() == 0 ? null : node.getPattern();
				
				pathTypeMap.put(node.getXpath(), talendType);
				pathPatternMap.put(node.getXpath(), patternValue);
				pathTypeToGenMap.put(node.getXpath(), typeToGenerate);
			}
    		
    		for(TreeNode tmpNode : node.getChildren()) {
    			init(tmpNode);
    		}
		}
		
		String generateExprCode(String expression){
			StringBuilder strBuilder = new StringBuilder();
			if(expression==null || ("").equals(expression)) {
				return "";
			}
	
			String currentExpression = expression;
			String tmpXpath = "";
			java.util.regex.Pattern expressionFromDoc = java.util.regex.Pattern.compile("\\[.*\\..*:.*\\]");
			java.util.regex.Matcher matcherFromDoc;
			
			boolean end = false;
			
			if(expression.indexOf("[")>-1) {
				strBuilder.append(expression.substring(0, expression.indexOf("[")));
				currentExpression = currentExpression.substring(currentExpression.indexOf("["), currentExpression.length());
				
				while(currentExpression.length()>0 && !end) {
					expression = this.getXPathExpression(currentExpression);
					currentExpression = currentExpression.substring(expression.length(), currentExpression.length());
					matcherFromDoc = expressionFromDoc.matcher(expression);
					if(matcherFromDoc.matches()) {
						tmpXpath = expression.substring(1, expression.length()-1);
						if("id_String".equals(pathTypeMap.get(tmpXpath)) || "id_Object".equals(pathTypeMap.get(tmpXpath))){
							strBuilder.append("treeNodeAPI_"+cid+".get_String(\""+ tmpXpath + "\")");
						} else if("id_Date".equals(pathTypeMap.get(tmpXpath))) {
							strBuilder.append("treeNodeAPI_"+cid+".get_Date(\""+ tmpXpath + "\" , " + pathPatternMap.get(tmpXpath) + ")");
						} else if("id_byte[]".equals(pathTypeMap.get(tmpXpath))) {
							strBuilder.append("treeNodeAPI_"+cid+".get_Bytes(\""+ tmpXpath + "\")");
						} else {
							if(pathTypeToGenMap.get(tmpXpath)!=null) {
								strBuilder.append("treeNodeAPI_"+cid+".get_" + pathTypeToGenMap.get(tmpXpath) + "(\""+ tmpXpath + "\")");
							} else {
								strBuilder.append("treeNodeAPI_"+cid+".get_String(\""+ tmpXpath + "\")");
							}
						}				
					} else {
						strBuilder.append(expression);
					}
					if(currentExpression.indexOf("[")>-1) {
						strBuilder.append(currentExpression.substring(0, currentExpression.indexOf("[")));
						currentExpression = currentExpression.substring(currentExpression.indexOf("["), currentExpression.length());
					} else {
						strBuilder.append(currentExpression);
						end=true;
					}
				
				}
			} else {
				strBuilder.append(expression);
			}
			return strBuilder.toString();
		}
		
		private String getXPathExpression(String currentExpression) {
			int count = 0;
			int i = 0;
			for(;i<currentExpression.length();i++) {
				char c = currentExpression.charAt(i);
				if('[' == c) {
					count++;
				}
				if(']' == c) {
					if(count==1) {
						break;
					}
					count--;
				}
			}
			return currentExpression.substring(0,i+1);
		}
		
	}
	
	class XPathHelper{
		int dPrefixCount = 0;
		Map<String,String> nsMapping = new HashMap<String,String>();
		Map<String,String> dPrefixMapping = new HashMap<String,String>();
		TreeNode rootNode;
		boolean isOptional = false; // use to judge if the loop node is mandotary in the source file.
		TreeNode loopNode;
		String finalLoopNodeXPath;
		Map<String,String> outNodesXPath = new HashMap<String,String>();
		Map<String,String> lookupInputNodesXPath;
		
		Map<String,String> xpathToPattern = new HashMap<String,String>();
		Map<String,String> xpathToType = new HashMap<String,String>();
		
		//all input loops used by current output table
		List<TreeNode> inputLoopsInCurrentOutputTable;
		public void setInputLoopsInCurrentOutputTable(List<TreeNode> inputLoopsInCurrentOutputTable) {
			this.inputLoopsInCurrentOutputTable = inputLoopsInCurrentOutputTable;
		}
		
		public Map<String,String> getXpathToPatternMap(){
			return xpathToPattern;
		}
		
		public Map<String,String> getXpathToTypeMap(){
			return xpathToType;
		}
		
		public XPathHelper(TreeNode rootNode){
			this(rootNode,false);
		}
		
		public XPathHelper(TreeNode rootNode,boolean isMultiLoop){
			this.rootNode = rootNode;
			collectionNS(rootNode, nsMapping, dPrefixMapping, null);
			if(!isMultiLoop) {
				//set loop node
				setInputLoopNode(findLoopNode(rootNode));
			} else {
				//do nothing
			}
			
		}
				
		public void setInputLoopNode(TreeNode inputLoopNode) {
			if(inputLoopNode==null){
				return;
			}
			loopNode = inputLoopNode;
			isOptional = loopNode.isOptional();
			
			
			List<TreeNode> outNodes = new ArrayList<TreeNode>(); 
			//find all output node(out & lookup out)
			findOutputNodes(rootNode, outNodes);
			
			//build xpath with prefix
			finalLoopNodeXPath = buildXPathWithPrefix(getXPath(loopNode.getXpath()), dPrefixMapping);
			
			outNodesXPath = new HashMap<String,String>();
			buildXPathWithPrefix(outNodes, dPrefixMapping, outNodesXPath);
			
			//build xpath for the node has lookup input connection
			List<TreeNode> lookupInputNodes = new ArrayList<TreeNode>();
			findLookupInputNodes(rootNode, lookupInputNodes);
			lookupInputNodesXPath = new HashMap<String,String>();
			buildXPathWithPrefix(lookupInputNodes, dPrefixMapping, lookupInputNodesXPath);
		}
		
		public boolean hasLoopNode(){
			if(loopNode == null){
				return false;
			}
			return true;
		}
		public boolean hasDefinedNS(){
			if(nsMapping.size()==0){
				return false;
			}
			return true;
		}
		
		/**
		 * use to judge if the loop is mandotary
		 * 
		 */
		public boolean isLoopOptional() {
			return isOptional;
		}
		
		public Map<String,String> getOutNodesXPath(){
			return outNodesXPath;
		}
		
		public Map<String,String> getLookupInputNodesXPath(){
			return lookupInputNodesXPath;
		}
		
		public String buildNSMapping(String name){
			StringBuilder sb = new StringBuilder();
			for (Object key : nsMapping.keySet()) { 
			    Object val = nsMapping.get(key);
			    sb.append(name+".put(\""+key+"\",\""+val+"\");"); 
			} 
			return sb.toString();
		}
		
		public String getLoopNodeXPath(){
			return finalLoopNodeXPath;
		}
		
		private void buildXPathWithPrefix(List<TreeNode> nodes, Map<String,String> dPrefixMapping, Map<String,String> nodesXPath){
			String loopNodeXPath = loopNode.getXpath();
			for(TreeNode node: nodes){
				String currentNodeXPath = node.getXpath();
				String typeToGenerate = JavaTypesManager.getTypeToGenerate(node.getType(), node.isNullable()); 
				xpathToType.put(currentNodeXPath,typeToGenerate);
				xpathToPattern.put(currentNodeXPath,node.getPattern());
				
				if(currentNodeXPath==null || ("").equals(currentNodeXPath)){
				}else if(loopNodeXPath.equals(currentNodeXPath)){
					nodesXPath.put(currentNodeXPath, ".");
				}else{
					String xpath = getXPath(currentNodeXPath);
					
					if(inputLoopsInCurrentOutputTable!=null) {
						for(TreeNode inputLoop : inputLoopsInCurrentOutputTable) {
							if(loopNode.equals(inputLoop)) {//skip current loop
								continue;
							}
							
							String otherLoopNodeXPath = inputLoop.getXpath();
							if(currentNodeXPath.equals(otherLoopNodeXPath) || isChild(currentNodeXPath,otherLoopNodeXPath)) {//current node in other loop node
								StringBuilder sb = new StringBuilder(currentNodeXPath);
								String newXPath = sb.insert(otherLoopNodeXPath.length(),"[1]").toString();
							 	xpath = getXPath(newXPath);
							 	break;
							}
						}
					}
					
					String nodeXPathWithPrefix = buildXPathWithPrefix(xpath, dPrefixMapping);
					if(isChild(nodeXPathWithPrefix,finalLoopNodeXPath)){
						String relativeXPath = nodeXPathWithPrefix.substring(finalLoopNodeXPath.length() + 1);
						nodesXPath.put(currentNodeXPath, relativeXPath);
					}else{
						StringBuilder relativeXPath = new StringBuilder();
						String tmp = finalLoopNodeXPath;
						
						while(!isChild(nodeXPathWithPrefix,tmp)){
							int index = tmp.lastIndexOf("/");
							if(index<0){ break; }
							tmp = tmp.substring(0,index);
							relativeXPath.append("../");
						}
						if(tmp.lastIndexOf("/") < 0 ){
							System.out.println("Loop Path is not set or loop Path is invalid");
						}else{
							relativeXPath.append(nodeXPathWithPrefix.substring(tmp.length() + 1));
						}
						nodesXPath.put(currentNodeXPath, relativeXPath.toString());
					}
				}
			}
		} 
		
		private boolean isChild(String asChild,String asParent) {
            return asChild.startsWith(asParent) && '/' == asChild.charAt(asParent.length());
		}
		
		private String buildXPathWithPrefix(String xpath, Map<String,String> dPrefixMapping){
			StringBuilder finalXPath = new StringBuilder();
			return buildXPathWithPrefix(finalXPath, xpath, dPrefixMapping);
			
		}
		private String buildXPathWithPrefix(StringBuilder finalXPath, String xpath, Map<String,String> dPrefixMapping){
			List<String> nodePaths = new ArrayList<String>();
			
			while(xpath.lastIndexOf("/") != -1){
				nodePaths.add(xpath);
				xpath = xpath.substring(0,xpath.lastIndexOf("/"));
			}
			
			for(int i=nodePaths.size()-1; i>=0; i--){
				String nodePath = nodePaths.get(i);
				String prefix = dPrefixMapping.get(nodePath);
				String nodeName = nodePath.substring(nodePath.lastIndexOf("/")+1);
				
				finalXPath.append("/");
				if(prefix != null && !"".equals(prefix)){
					finalXPath.append(prefix);
					finalXPath.append(":");
					finalXPath.append(nodeName);
				}else{
					finalXPath.append(nodeName);
				}
			}
			
			return finalXPath.toString();
		}
		
		private void findOutputNodes(TreeNode currentNode, List<TreeNode> outNodes){
			if(currentNode.getOutgoingConnections().size()>0 || currentNode.getLookupOutgoingConnections().size()>0 || currentNode.getFilterOutGoingConnections().size()>0){
				outNodes.add(currentNode);
			}
			for(TreeNode childNode : currentNode.getChildren()) {
				if(childNode.getNodeType() == NodeType.ELEMENT || childNode.getNodeType() == NodeType.ATTRIBUT){
					findOutputNodes(childNode, outNodes);
				}
			}
		}
		
		private void findLookupInputNodes(TreeNode currentNode, List<TreeNode> lookupInputNodes){
			if(currentNode.getLookupIncomingConnections().size()>0){
				lookupInputNodes.add(currentNode);
			}
			for(TreeNode childNode : currentNode.getChildren()) {
				if(childNode.getNodeType() == NodeType.ELEMENT || childNode.getNodeType() == NodeType.ATTRIBUT){
					findLookupInputNodes(childNode, lookupInputNodes);
				}
			}
		}
		
		private void collectionNS(TreeNode currentNode, Map<String,String> nsMapping, Map<String,String> dPrefixMapping, String parentDPrefix){
			EList<TreeNode> childNodes = currentNode.getChildren();
			for(TreeNode childNode : childNodes){
				if(childNode.getNodeType() == NodeType.NAME_SPACE){
					if(childNode.getName() != null && !"(default)".equals(childNode.getName())){
						nsMapping.put(childNode.getName(),childNode.getDefaultValue());
					}else{
						String defaultPrefix = findVaildDefaultPrefix();
						nsMapping.put(defaultPrefix,childNode.getDefaultValue());
						//dPrefixMapping.put(getXPath(currentNode.getXpath()),defaultPrefix);
						parentDPrefix = defaultPrefix;
					}
				}
			}
			if(!currentNode.getName().contains(":")){
				if(parentDPrefix != null && !"".equals(parentDPrefix)){
					dPrefixMapping.put(getXPath(currentNode.getXpath()),parentDPrefix);
				}
			}else{
				//parentDPrefix = null;
			}
			for(TreeNode childNode : childNodes){
				if(childNode.getNodeType() == NodeType.ELEMENT){
					collectionNS(childNode, nsMapping, dPrefixMapping, parentDPrefix);
				}
			}
		}
		
		private String findVaildDefaultPrefix(){
			String prefix = "TPrefix"+dPrefixCount;
			dPrefixCount++;
			if(nsMapping.get(prefix)==null || "".equals(nsMapping.get(prefix))){
				return prefix;
			}else{
				return findVaildDefaultPrefix();
			}
		}
		
		private TreeNode findLoopNode(TreeNode node){
			if(node == null){
				return null;
			}
			if(node.isLoop()) {
				return node;
			}
			for(TreeNode childNode : node.getChildren()) {
				if(childNode.getNodeType() == NodeType.ELEMENT){
					TreeNode resultNode = findLoopNode(childNode);
					if(resultNode!=null){
						return resultNode;
					}
				}
			}
			
			return null;
		}
		
		private String getXPath(String uiXPath){
			return uiXPath.substring(uiXPath.indexOf(":")+1);
		}
	}

	class TreeUtil{
		//get all nodes whose expression is editable;
		void getAllLeaf(TreeNode node,List<TreeNode> result) {
			EList<TreeNode> children = node.getChildren();
			if(children==null || children.size() == 0) {
				result.add(node);//leaf is editable
			} else {
				boolean editableAtExpression = true;
				for(TreeNode child : children) {
					if(child!=null) {
						//attribute and namespace are not treat as subnode , so the expression of treeNode should be editable.
						if(NodeType.ATTRIBUT != child.getNodeType() && NodeType.NAME_SPACE != child.getNodeType()) {
							editableAtExpression = false;
						}
						getAllLeaf(child,result);
					}
				}
				
				if(editableAtExpression) {
					result.add(node);
				}
			}
		}
		
		void getAllLoopNodes(OutputTreeNode node,List<OutputTreeNode> result) {
			if(node == null) {
				return;
			}
			if(node.isLoop()){
				result.add(node);
				return;
			}
			for(TreeNode child : node.getChildren()){
				getAllLoopNodes((OutputTreeNode)child,result);
			}
		}
		
		TreeNode getUniqueLoopNode(TreeNode node) {
			if(node == null) {
				return null;
			}
			if(node.isLoop()){
				return node;
			}
			for(TreeNode child : node.getChildren()){
				TreeNode loopNode = getUniqueLoopNode(child);
				if(loopNode!=null) {
					return loopNode;
				}
			}
			return null;
		}
		
		boolean isRootLoop(TreeNode root) {
			if(root == null) return false;
			List<TreeNode> children = root.getChildren();
			if(children == null || children.size() == 0) {
				return false;
			}
			TreeNode realRoot = children.get(0);
			return realRoot.isLoop();
		}
	}
	
	static class XMLOrderUtil {
		
		int groupCount = 0;
		
		int getGroupCount(OutputTreeNode rootNode) {
			groupCount = 0;
			countGroupNode(rootNode);
			return groupCount;
		}
		
		static int getCurrOrder(OutputTreeNode currNode) {
			int currOrder = 0;
			if(isGroupOrLoopInMain(currNode)) {
				OutputTreeNode parent = (OutputTreeNode)currNode.eContainer();
				if(parent!=null) {
					EList<TreeNode> children = parent.getChildren();
					//the order not containing namespace and attribute
					for(TreeNode child : children) {
						NodeType nodeType = child.getNodeType();
						if(nodeType != NodeType.ELEMENT) {
							continue;
						}
						if(currNode.equals(child)) {
							break;
						} 
						currOrder++;
					}
				}
			}
			return currOrder;
		}
		
		static int getCurrPos(OutputTreeNode currNode) {
			int currPos = 0;
			if(isGroupOrLoopInMain(currNode)) {
				OutputTreeNode parent = (OutputTreeNode)currNode.eContainer();
				OutputTreeNode tmpNode = parent;
				while(tmpNode!=null){
					boolean isGroupOrLoopInMain = isGroupOrLoopInMain(tmpNode);
					boolean isVirtualNode = isVirtualNode(tmpNode);
					
					if(!isGroupOrLoopInMain && !isVirtualNode) {
						break;
					}
					
					if(isGroupOrLoopInMain && !isVirtualNode) {
    					currPos++;
    				}
    				
    				tmpNode = (OutputTreeNode)tmpNode.eContainer();
    			}
			}
			return currPos;
		}
		
		//work for multi loop document output
		static java.util.List<OutputTreeNode> getNextSiblings(OutputTreeNode currNode){
            java.util.List<OutputTreeNode> result = new java.util.ArrayList<OutputTreeNode>();
            OutputTreeNode parent = (OutputTreeNode)currNode.eContainer();
            boolean isNextSibling = false;
            if(parent!=null){
            	EList<TreeNode> children = parent.getChildren();
                for(TreeNode child : children) {
					NodeType nodeType = child.getNodeType();
					if(nodeType != NodeType.ELEMENT) {
						continue;
					}
					
					if(isNextSibling) {
						result.add((OutputTreeNode)child);
						continue;
					}
					
					if(currNode.equals(child)) {
						isNextSibling = true;
					}
				}
            }
            return result;
        }
		
		private static boolean isGroupOrLoopInMain(OutputTreeNode currNode) {
			return currNode.isMain() && (currNode.isGroup() || currNode.isLoop());
		}
		
		private static boolean isVirtualNode(OutputTreeNode currNode) {
			return currNode.isSubstitution() || currNode.isChoice();
		}
		
		private void countGroupNode(OutputTreeNode node) {
			EList<TreeNode> children = node.getChildren();
			
			if(children==null || children.size() == 0) {
				//it is impossible that leaf is Group.
				
			} else {
				//branch maybe Group
				if(node.isGroup() && !isVirtualNode(node)) {
					groupCount++;
				}
				
				for(TreeNode child : children) {
					if(child!=null) {
						countGroupNode((OutputTreeNode)child);
					}
				}
			}
		}
		
		
		
	}
	
	public INode searchSubProcessStartNode(IConnection connection) {
       	INode source = connection.getSource();
    	INode subprocessStartNode = null;
    	if(source != null) {
			String searchedComponentName = source.getUniqueName();
			List<? extends INode> generatedNodes = source.getProcess().getGeneratingNodes();
			for(INode loopNode : generatedNodes) {
				if(loopNode.getUniqueName().equals(searchedComponentName)) {
					subprocessStartNode = loopNode.getSubProcessStartNode(false);
				}
			}
		}
		return subprocessStartNode;
	}
	
	public List<InputLoopNodesTable> getValidInputLoopNodesTables(List<InputLoopNodesTable> inputLoopNodesTablesWithUnValid) {
    	List<InputLoopNodesTable> inputLoopNodesTables = new ArrayList<InputLoopNodesTable>();
    	if(inputLoopNodesTablesWithUnValid == null) return inputLoopNodesTables;
		for(InputLoopNodesTable inputLoopNodesTable : inputLoopNodesTablesWithUnValid) {
			if(inputLoopNodesTable!=null && inputLoopNodesTable.getInputloopnodes()!=null && inputLoopNodesTable.getInputloopnodes().size()>0)
				inputLoopNodesTables.add(inputLoopNodesTable);
		}
		return inputLoopNodesTables;
    }
	
    public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	XmlMapComponent node = (XmlMapComponent) codeGenArgument.getArgument();
	GenerationManager gm =  (GenerationManager)node.initGenerationManager();
	String cid = node.getUniqueName();
	
	String uniqueNameComponent = cid.replaceAll("_TXMLMAP_OUT", "");
	String uniqueNameComponentIn = cid.replaceAll("_TXMLMAP_OUT", "_TXMLMAP_IN");
	boolean checkingSyntax = codeGenArgument.isCheckingSyntax();
	String cr = "\n";
	
	INode generatingNodeIn = null;
	for(INode loopNode : node.getProcess().getGeneratingNodes()) {
		if(loopNode.getUniqueName().equals(uniqueNameComponentIn)) {
			generatingNodeIn = loopNode;
		}
	}
	
	XmlMapData xmlMapData = (XmlMapData)ElementParameterParser.getObjectValueXMLTree(node);
	
	boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));
	
	XMLMapUtil utilXML = new XMLMapUtil();
	
	boolean isVirtual = true;
	int closeWhileCountForMultiResult = 0;
	String loopKey = null;
	List<InputLoopNodesTable> allInputLoopNodesTables = null;
	
	EList<InputXmlTree> inputTablesWithInvalid = xmlMapData.getInputTrees();
	EList<OutputXmlTree> outputTables = xmlMapData.getOutputTrees();
	EList<VarTable> varsTables = xmlMapData.getVarTables();

	String rejectedInnerJoin = "rejectedInnerJoin_" + cid;
	String rejectedDocInnerJoin = "rejectedDocInnerJoin_" + cid;
	String rejected = "rejected_" + cid;
	String mainRowRejected = "mainRowRejected_" + cid;
	boolean isLookup = false;
	boolean isXpathFromLookup = true;
	boolean hasOutgoingConnection = false;//to output or another lookup
	boolean hasDocumentInMainInputTable = false;
	boolean atLeastOneInputTableWithInnerJoin = false;
	boolean isPlainNode = true;
	boolean isPlainNodeInLookup = false;
	boolean hasValidLookupTables = false;
	boolean hasDocumentGlobal = false;
	
	boolean isAllInOne = false;
	
	boolean hasMainFilter = false;
	boolean mainFilterAlreadyDone = false;
	
	List<IConnection> outputConnections = (List<IConnection>) generatingNodeIn.getOutgoingConnections();
	Map<String, IConnection> nameToOutputConnection = new HashMap<String, IConnection>();
    for (IConnection connection : outputConnections) {
	  		nameToOutputConnection.put(connection.getName(), connection);
	}
    
	List<IConnection> inputConnections = (List<IConnection>) node.getIncomingConnections();
	HashMap<String, IConnection> hNameToConnection = new HashMap<String, IConnection>();
	for(IConnection connection : inputConnections){
		 hNameToConnection.put(connection.getName(), connection);
	}
	
	List<InputXmlTree> inputTables = new ArrayList<InputXmlTree>();
	for(int i=0; i<inputTablesWithInvalid.size(); i++){
		InputXmlTree  currentTree = inputTablesWithInvalid.get(i);
		if(hNameToConnection.get(currentTree.getName()) != null){
			inputTables.add(currentTree);
		}
	}
	
	boolean hasDocumentInAnyLookupTable = false;
	
	int lstSizeInputs = inputTables.size();
	for(int i = 1; i<lstSizeInputs; i++){
		InputXmlTree inputTable = (InputXmlTree)inputTables.get(i);
		EList<TreeNode> treeNodes = inputTable.getNodes();
		if(treeNodes!=null) {
			for(TreeNode treeNode : treeNodes) {
				if("id_Document".equals(treeNode.getType())) {
					hasDocumentInAnyLookupTable = true;
					break;
				}
			}
		}
	}
	
	HashMap<String, TreeNode> hInputTableNodes = new HashMap<String, TreeNode>();
	int sizeInputTables = inputTables.size();
	InputXmlTree mainInputTable = null;  // the main input table
	
	String mainInputTableName = null;
	IConnection realMainConnection = null;
	java.util.Map<String, String> xpathTypeMap = null;
	String str  = "";
	TreeNode currentMainNode = null;
	boolean findNext = false;

	String nextLookupName = null;
	List<IMetadataColumn> nextLookupColumnsKeys = new ArrayList<IMetadataColumn>();
 
	boolean hasPersistentLookup = false;
	int indexLastPersistentSortedTable = -1;
	 
	ArrayList<InputXmlTree> inputTablesWithInnerJoin = new ArrayList<InputXmlTree>();
	if(inputConnections==null || inputConnections.size() < 1) {
		return "";
	}
	
    stringBuffer.append(TEXT_2);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_3);
    stringBuffer.append( rejectedDocInnerJoin );
    stringBuffer.append(TEXT_3);
    stringBuffer.append( mainRowRejected );
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    
	if(inputTables==null || inputTables.size()==0) return "";
	for(IConnection conn : inputConnections) { // find the input main table
		InputXmlTree inputTable = inputTables.get(0);
		EConnectionType connectionType = conn.getLineStyle();
		if (connectionType == EConnectionType.FLOW_MAIN) {
			if(!inputTable.isLookup()) { // lookup is used to sign it is lookup table
    			mainInputTable = inputTable;
    			break;
			}
		}
	}
	
	//filter unvalid output tables
	ArrayList<OutputXmlTree> outputTablesSortedByReject = new ArrayList<OutputXmlTree>();
	for(OutputXmlTree outputTable : outputTables) {
		if(nameToOutputConnection.get(outputTable.getName())!=null) {
			outputTablesSortedByReject.add(outputTable);
		}
	}
	// sorting outputs : rejects tables after not rejects table
	Collections.sort(outputTablesSortedByReject, new Comparator<OutputXmlTree>() {

		public int compare(OutputXmlTree o1, OutputXmlTree o2) {
			if (o1.isReject() != o2.isReject()) {
				if (o1.isReject()) {
					return 1;
				} else {
					return -1;
				}
			}
			if (o1.isRejectInnerJoin() != o2.isRejectInnerJoin()) {
				if (o1.isRejectInnerJoin()) {
					return 1;
				} else {
					return -1;
				}
			}
			return 0;
		}

	});
	
	if(mainInputTable!=null) {
		
		List<TreeNode> allNodes = new ArrayList<TreeNode>();
		for(InputXmlTree inputTable : inputTables) {
			allNodes.addAll(inputTable.getNodes());
		}
		
		MatchXmlTreeExpr matchXmlTreeExpr = new MatchXmlTreeExpr(allNodes,cid);
		
		String mainInputName = mainInputTable.getName();
	
		TreeNode documentNodeInMainInputTable = null;
		LoopHelper loopHelper = null;
		boolean isMultiLoop = false;
		boolean parallelLoopExistInCurrentOutputTable = false;
		boolean parallelRelationExistBetweenAllOutputTables = false;
		List<TreeNode> currentInputLoopNodes;
		//check whether multiloop
		isMultiLoop = mainInputTable.isMultiLoops();
		//check whether parallel relation exist
		List<TreeNode> inputLoopNodesForPreviousOutputTable = null;
		for(OutputXmlTree outputTable : outputTablesSortedByReject) {
			if(outputTable.isMultiLoops()) {
				isMultiLoop = true;
			}
			
			List<InputLoopNodesTable> inputLoopNodesTables = getValidInputLoopNodesTables(outputTable.getInputLoopNodesTables());
			
			for(InputLoopNodesTable inputLoopNodesTable : inputLoopNodesTables) {
				List<TreeNode> inputLoopNodesForCurrentOutputTable = inputLoopNodesTable.getInputloopnodes();
				if(inputLoopNodesForPreviousOutputTable!=null) {
					//use different loops for output tables(it is also a parallel relation,but between different output tables)
					if(inputLoopNodesForPreviousOutputTable.size() != inputLoopNodesForCurrentOutputTable.size() || !inputLoopNodesForPreviousOutputTable.containsAll(inputLoopNodesForCurrentOutputTable)) {
						parallelRelationExistBetweenAllOutputTables = true;
					}
				}
				inputLoopNodesForPreviousOutputTable = inputLoopNodesForCurrentOutputTable;
				
			}
			
		}
	
		if(isMultiLoop) {

    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    
	
	for(TreeNode currentNode : mainInputTable.getNodes()){
		if(("id_Document").equals(currentNode.getType())) {
			hasDocumentInMainInputTable = true;
			documentNodeInMainInputTable = currentNode;
		}
	}
	
	XPathHelper xpathHelperForMain = null;
	
	if(hasDocumentInMainInputTable) {
		loopHelper = new LoopHelper(documentNodeInMainInputTable,outputTablesSortedByReject,inputTables);
		xpathHelperForMain = new XPathHelper(documentNodeInMainInputTable,true);
	}
	
	TreeUtil treeUtil = new TreeUtil();

	allInputLoopNodesTables = new ArrayList<InputLoopNodesTable>();
	for(OutputXmlTree outputTable : outputTablesSortedByReject) {
		List<InputLoopNodesTable> inputLoopNodesTables = getValidInputLoopNodesTables(outputTable.getInputLoopNodesTables());
		allInputLoopNodesTables.addAll(inputLoopNodesTables);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//GODE GENERATE START
	int index = -1;
	int indexOfInputLoopNodesTable = 0;
	for(OutputXmlTree currentOutputTable : outputTablesSortedByReject) {//TD128
		String currentOutputTableName = currentOutputTable.getName();
		//current output table action
		if(hasDocumentInMainInputTable) {
			loopHelper.initForOneOutputTable(currentOutputTable);
			
			xpathHelperForMain.setInputLoopsInCurrentOutputTable(loopHelper.getInputLoopsInCurrentOutputTable());
		}
		
		//output loops in current output table
		List<Object> outputLoops = new ArrayList<Object>();
		
		boolean onlyFlatOutput = true;//not support flat and doc output exist at the same time.
		List<OutputTreeNode> loopNodes = new ArrayList<OutputTreeNode>();
		for(OutputTreeNode currentNode : currentOutputTable.getNodes()) {
			if("id_Document".equals(currentNode.getType())) {
				treeUtil.getAllLoopNodes(currentNode,loopNodes);
				onlyFlatOutput = false;
			}
		}
		
		List<InputLoopNodesTable> inputLoopNodesTables = getValidInputLoopNodesTables(currentOutputTable.getInputLoopNodesTables());
		
		//case 1 : no any loopNode in Document,but inputLoopNodesTable exist ==> only flat output,all the output loops come from inputLoopNodesTables
		//case 2 : loopNode exist and inputLoopNodesTable exist or not, but some loopNode do not have inputLoopNodesTable ==> some output loop come fome input flat
		
		if(onlyFlatOutput) {
			for(InputLoopNodesTable inputLoopNodesTable : inputLoopNodesTables) {
				outputLoops.add(inputLoopNodesTable);
			}
			if(outputLoops.size()<1) {
				if(hasDocumentInMainInputTable && !mainInputTable.isMultiLoops()) {
					outputLoops.add(treeUtil.getUniqueLoopNode(documentNodeInMainInputTable));
				} else {
					outputLoops.add(new Object());
				}
			}
		} else {//only Document output
			for(OutputTreeNode loop : loopNodes) {
				InputLoopNodesTable inputLoopTable = loop.getInputLoopNodesTable();
				if(inputLoopTable!=null && inputLoopTable.getInputloopnodes()!=null && inputLoopTable.getInputloopnodes().size()>0) {
					outputLoops.add(loop.getInputLoopNodesTable());
				} else if(hasDocumentInMainInputTable && !mainInputTable.isMultiLoops()) {
					outputLoops.add(treeUtil.getUniqueLoopNode(documentNodeInMainInputTable));
				} else {
					outputLoops.add(new Object());
				}
			}
		}
		
		parallelLoopExistInCurrentOutputTable = loopNodes.size() > 1;
		
		for(int idx=0;idx<outputLoops.size();idx++) {//TD256
			//current output loop action
			Object outputLoop = outputLoops.get(idx);
			currentInputLoopNodes = new ArrayList<TreeNode>();
			if(outputLoop instanceof InputLoopNodesTable) {
				InputLoopNodesTable inputLoopNodesTable = (InputLoopNodesTable)outputLoop;
				currentInputLoopNodes = inputLoopNodesTable.getInputloopnodes();
			} else if(outputLoop instanceof TreeNode){
				currentInputLoopNodes.add((TreeNode)outputLoop);
			}
			loopKey = "loopKey"+(indexOfInputLoopNodesTable++);
			String isCurrentLoopRef = null;
			if(parallelLoopExistInCurrentOutputTable) {
				isCurrentLoopRef = "subTreeLoop"+idx;
			}
			
			int nestedLoopWhileCount = 0;
			closeWhileCountForMultiResult = 0;
			
			if(!hasDocumentInMainInputTable && currentInputLoopNodes.size()<1) {

    stringBuffer.append(TEXT_8);
    
				if(hasDocumentInAnyLookupTable) {
				
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_10);
    
				}
			}
			
			for(TreeNode inputLoopNode : currentInputLoopNodes) {//TD512
				index++;
				if(hasDocumentInMainInputTable) {//TD1024
					nestedLoopWhileCount++;
					xpathHelperForMain.setInputLoopNode(inputLoopNode);
					String loopPath = xpathHelperForMain.getLoopNodeXPath();

    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(loopPath );
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(xpathHelperForMain.isLoopOptional() );
    stringBuffer.append(TEXT_17);
    stringBuffer.append(mainInputName );
    stringBuffer.append(TEXT_18);
    stringBuffer.append(documentNodeInMainInputTable.getName() );
    stringBuffer.append(TEXT_19);
    stringBuffer.append(mainInputName );
    stringBuffer.append(TEXT_18);
    stringBuffer.append(documentNodeInMainInputTable.getName() );
    stringBuffer.append(TEXT_20);
    stringBuffer.append(mainInputName );
    stringBuffer.append(TEXT_18);
    stringBuffer.append(documentNodeInMainInputTable.getName() );
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(mainInputName );
    stringBuffer.append(TEXT_18);
    stringBuffer.append(documentNodeInMainInputTable.getName() );
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(xpathHelperForMain.hasDefinedNS());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    if(!xpathHelperForMain.hasDefinedNS()){
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    }else{
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(xpathHelperForMain.buildNSMapping("xmlNameSpaceMap"+"_"+cid+"_"+index));
    stringBuffer.append(TEXT_36);
    }
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_28);
    
					Map<String,String> outNodesXPath = xpathHelperForMain.getOutNodesXPath();
					
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_41);
    
					//not filter for the first input loop in output table
					boolean needFilter = !(idx==0 && nestedLoopWhileCount==1);
					boolean isMainInputLoopInNestLoop = (nestedLoopWhileCount == 1);
					for (String key : outNodesXPath.keySet()) { 
					    String val = outNodesXPath.get(key);
					    //filter for output mapping
					    if(needFilter && !loopHelper.belongToCurrentLoop(inputLoopNode,key,isMainInputLoopInNestLoop)) {//the node is not belong to current loop
					    	continue;
					    }
					
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(val);
    stringBuffer.append(TEXT_45);
    
					}
					
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_58);
    
					if(nestedLoopWhileCount == currentInputLoopNodes.size()) {

    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_61);
    stringBuffer.append( rejectedDocInnerJoin );
    stringBuffer.append(TEXT_62);
    stringBuffer.append(mainRowRejected);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_64);
    
					}	
					if(nestedLoopWhileCount == 1) {

    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_66);
    
					}

    stringBuffer.append(TEXT_67);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_72);
    
				}//TD1024
			}//TD512
			
////////////////////////////////////////////////////////////////////////////////////////////////////////
//START LOOK UP ACTION
			//it is different with one loop when parallel happen
			if(hasDocumentInMainInputTable) {
				loopHelper.initForOneOutputTableLoop(currentInputLoopNodes);
			}
			for (int iInputTable = 0; iInputTable < sizeInputTables; iInputTable++) {//TD513
					hasOutgoingConnection = false;//reset the var for every lookup table 
					InputXmlTree nextInputTable = inputTables.get(iInputTable);
					if(!atLeastOneInputTableWithInnerJoin) {
						if(nextInputTable.isInnerJoin()) {
							atLeastOneInputTableWithInnerJoin = true;
						}
					}

    stringBuffer.append(TEXT_36);
    stringBuffer.append(TEXT_73);
    
	EConnectionType connectionType = null;
	if (nextInputTable != null && nextInputTable.isLookup()) {//TD132 
		IConnection connection =  hNameToConnection.get(nextInputTable.getName());

		if(connection == null){
			continue;
		}else{//TD131
			connectionType = connection.getLineStyle();
			
			if(connectionType == EConnectionType.FLOW_REF) {//TD130
				IMetadataTable metadataTable = connection.getMetadataTable();
				String tableName = nextInputTable.getName();
				String lookupMode = nextInputTable.getLookupMode();
				boolean isCacheOrReloadLookupMode = org.talend.designer.xmlmap.model.tree.LOOKUP_MODE.CACHE_OR_RELOAD.name().equals(lookupMode);
				boolean isOneOfReloadLookupMode = isCacheOrReloadLookupMode ||
					   org.talend.designer.xmlmap.model.tree.LOOKUP_MODE.RELOAD.name().equals(lookupMode);
				INode lookupSubProcessStartNode = searchSubProcessStartNode(connection);
			  	String lookupSubProcessStartNodeName = lookupSubProcessStartNode.getUniqueName();
				boolean isInnerJoin = false;
				if (nextInputTable.isInnerJoin()) {
					isInnerJoin = true;
			    	inputTablesWithInnerJoin.add(nextInputTable);
			 	}
				
    stringBuffer.append(TEXT_74);
    stringBuffer.append( nextInputTable );
    stringBuffer.append(TEXT_75);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_76);
    
				String filtersConditions = nextInputTable.getExpressionFilter();
				String filtersConditionsFormatted = "";
				boolean hasExpressionFilter = nextInputTable.isActivateExpressionFilter() && filtersConditions != null && !("").equals(filtersConditions.trim());
				if(hasExpressionFilter && matchXmlTreeExpr != null) {
					filtersConditionsFormatted = matchXmlTreeExpr.generateExprCode(filtersConditions);
				}

				String expressionKey = "";
				List<IMetadataColumn> listColumns = metadataTable.getListColumns();
				ArrayList<String> keysNames = new ArrayList<String>();
				ArrayList<String> keysValues = new ArrayList<String>();
				ArrayList<String> keysTypes = new ArrayList<String>();
				EList<TreeNode> externalTreeNodes = nextInputTable.getNodes();
				
				boolean findFromBasicType = false;
				boolean findFromDocumentType = false;
				String documentColumn = null;
				boolean hasOutputFromLookupDoc = false;//to output or another lookup from Document
				Map<String,String> outNodesXPath = new HashMap<String,String>();//output contain(1.lookup table to output table 2.lookup table to lookup table)
				Map<String,String> lookupInputNodesXPath = new HashMap<String,String>();
				String loopXPath = "";
				boolean isLoopOptional = false;
				boolean isMultipleResult = false;
				boolean existXpathKeyFromCurrentLookup = false;
				boolean hasExpressionKey = false;
				//cache the type and pattern information for sub columns in Document column
				Map<String,String> xpathToPatternMap = null;
				Map<String,String> xpathToTypeMap = null;
				
				boolean unvalidLookupKeyExistForHash = false;
				List<IMetadataColumn> validLookupKeyFlatColumns = new ArrayList<IMetadataColumn>();
				
				for(int j = 0; j < listColumns.size(); j++){
					IMetadataColumn column = listColumns.get(j);
					String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
				    String columnName = column.getLabel();
				    TreeNode  externalInputTableEntry = externalTreeNodes.get(j);
				    XPathHelper xpathHelper;
				    if("Document".equals(typeToGenerate)) {
				    	xpathHelper = new XPathHelper(externalInputTableEntry);
						loopXPath = xpathHelper.getLoopNodeXPath();
						isLoopOptional = xpathHelper.isLoopOptional();
						outNodesXPath = xpathHelper.getOutNodesXPath();
						lookupInputNodesXPath = xpathHelper.getLookupInputNodesXPath();
						xpathToPatternMap = xpathHelper.getXpathToPatternMap();
						xpathToTypeMap = xpathHelper.getXpathToTypeMap();
						
    stringBuffer.append(TEXT_34);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(xpathHelper.buildNSMapping("xmlNameSpaceMap"+"_"+tableName));
    stringBuffer.append(TEXT_77);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_78);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_80);
    
				    }
				    List<TreeNode> allLeaf = new ArrayList<TreeNode>();
				    treeUtil.getAllLeaf(externalInputTableEntry,allLeaf); 
				    
				    for(TreeNode leaf : allLeaf) { 
					    if (leaf != null) {
				    		if("Document".equals(typeToGenerate)) {
				    			documentColumn = columnName;
				    			EList<Connection> outputs = leaf.getOutgoingConnections();
				    			EList<LookupConnection> lookupOutputs = leaf.getLookupOutgoingConnections();
				    			EList<FilterConnection> filterOutputs = leaf.getFilterOutGoingConnections();
					    		if((outputs!=null && outputs.size()!=0) || (lookupOutputs!=null && lookupOutputs.size()!=0) || (filterOutputs!=null && filterOutputs.size()!=0)) {
					    			hasOutputFromLookupDoc = true;
					    			hasOutgoingConnection = true;
					    		}
				    		}
				    	  	if(leaf.getOutgoingConnections().size() > 0 || leaf.getLookupOutgoingConnections().size() > 0 || leaf.getFilterOutGoingConnections().size() > 0){
						    	hasOutgoingConnection = true;
						    }
						    
				    	  	expressionKey = leaf.getExpression();
							if (expressionKey != null && !"".equals(expressionKey.trim())) {
							
								//filter for valid lookup key
								if(hasDocumentInMainInputTable && (parallelLoopExistInCurrentOutputTable || parallelRelationExistBetweenAllOutputTables)) {
									if(!loopHelper.validLookupKey(leaf)) {
										continue;
									} else {
										if(!"Document".equals(typeToGenerate)) {
											validLookupKeyFlatColumns.add(column);
										}
									}
								}
								
								hasExpressionKey = true;
							    String resultExpression = null;
							    if("Document".equals(typeToGenerate)) {
							    	findFromDocumentType = true;
							    	keysNames.add(leaf.getXpath());
							    } else {
							    	keysNames.add(columnName);
							    	findFromBasicType = true;
							    }
							    if(matchXmlTreeExpr != null){
							    	resultExpression = matchXmlTreeExpr.generateExprCode(expressionKey);
							    }
							    if(resultExpression != null && !("").equals(resultExpression)){
							    	keysValues.add(resultExpression);
							    	if(resultExpression.indexOf("/") != -1) {
							    		existXpathKeyFromCurrentLookup = true;
							    	}
							    } else {
							    	keysValues.add(expressionKey);
							    }
							    keysTypes.add(typeToGenerate);
							    boolean javaPrimitiveKeyColumn = JavaTypesManager.isJavaPrimitiveType(typeToGenerate);
							}
					    }
					}
				}
				
				/////////////////////////////////////////////////////
				//set unvalidLookupKeyExistForHash
				if(hasDocumentInMainInputTable && (parallelLoopExistInCurrentOutputTable || parallelRelationExistBetweenAllOutputTables)) {
					LoopHelper nestLoopHelper = new LoopHelper(documentNodeInMainInputTable,outputTablesSortedByReject,inputTables);
					for(int ii=0;ii<allInputLoopNodesTables.size();ii++) {
						InputLoopNodesTable oneInputLoopNodesTable = allInputLoopNodesTables.get(ii);
						List<TreeNode> oneInputLoopNodes = oneInputLoopNodesTable.getInputloopnodes();
						nestLoopHelper.initForOneOutputTableLoop(oneInputLoopNodes);
						if(nextInputTable!=null && nextInputTable.isLookup()) {
							EList<TreeNode> treeNodes = nextInputTable.getNodes();
							for(TreeNode treeNode : treeNodes) {
								//only check the flat column,because hash only ready for flat column
								if(!nestLoopHelper.validLookupKey(treeNode)) {
									unvalidLookupKeyExistForHash = true;
								}
								
							}
							
						}
					}
				}
				/////////////////////////////////////////////////////
				
				isXpathFromLookup = existXpathKeyFromCurrentLookup;
				String[] aKeysNames = keysNames.toArray(new String[0]);
				String[] aKeysValues = keysValues.toArray(new String[0]);
				String className = tableName + "Struct";
				
				hasValidLookupTables = true;
				
				String matchingMode = nextInputTable.getMatchingMode();
				if(matchingMode == null) {
					if(aKeysValues.length > 0) {
						matchingMode = "UNIQUE_MATCH";
					} else {
						matchingMode = "ALL_ROWS";
					}
				} else {
					if(aKeysValues.length == 0) {
						matchingMode = "ALL_ROWS";
					}
				}
				
				boolean isAllRows = "ALL_ROWS".equals(matchingMode);
				boolean isDocumentAllRows = isAllRows && documentColumn!=null;
				
				if("ALL_ROWS".equals(matchingMode) || "ALL_MATCHES".equals(matchingMode)) {
					isMultipleResult = true;
				}
				
    stringBuffer.append(TEXT_81);
    if(hasOutgoingConnection || hasExpressionKey || isAllRows){ 
    stringBuffer.append(TEXT_82);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_83);
    stringBuffer.append( className );
    stringBuffer.append(TEXT_84);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_85);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_86);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_87);
    } 
    stringBuffer.append(TEXT_81);
    
				if(aKeysValues!=null){//TD129
				////////////////////////////////////////////////////////////////////////
					if(unvalidLookupKeyExistForHash) {
					
    stringBuffer.append(TEXT_88);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_89);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_90);
    stringBuffer.append(loopKey);
    stringBuffer.append(TEXT_91);
    
						for(IMetadataColumn column : validLookupKeyFlatColumns) {
							String columnName = column.getLabel();
    						JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());

							if (JavaTypesManager.isJavaPrimitiveType(column.getTalendType(), column.isNullable())) {
							
							 	String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
							 	if(javaType == JavaTypesManager.BOOLEAN) {
								
    stringBuffer.append(TEXT_92);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_93);
    
								} else {	
								
    stringBuffer.append(TEXT_94);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_95);
    
								}
								
							} else if(javaType == JavaTypesManager.BYTE_ARRAY) {
		
								
    stringBuffer.append(TEXT_96);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_97);
    
							
							} else {
							
    stringBuffer.append(TEXT_98);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_99);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_100);
    
							}
						}
					
    stringBuffer.append(TEXT_101);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_104);
    	
								for(IMetadataColumn column : validLookupKeyFlatColumns) {
									String columnName = column.getLabel();
									JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
				
									if (JavaTypesManager.isJavaPrimitiveType(column.getTalendType(), column.isNullable())) {
									
    stringBuffer.append(TEXT_105);
    stringBuffer.append(columnName );
    stringBuffer.append(TEXT_106);
    stringBuffer.append(columnName );
    stringBuffer.append(TEXT_107);
    
									} else if(javaType == JavaTypesManager.BYTE_ARRAY) {
									
    stringBuffer.append(TEXT_108);
    stringBuffer.append(columnName );
    stringBuffer.append(TEXT_109);
    stringBuffer.append(columnName );
    stringBuffer.append(TEXT_110);
    
									} else {
									
    stringBuffer.append(TEXT_105);
    stringBuffer.append(columnName );
    stringBuffer.append(TEXT_111);
    stringBuffer.append(columnName );
    stringBuffer.append(TEXT_112);
    stringBuffer.append(columnName );
    stringBuffer.append(TEXT_113);
    stringBuffer.append(columnName );
    stringBuffer.append(TEXT_114);
    
									}
				
								}

    stringBuffer.append(TEXT_115);
    
					}
					///////////////////////////////////////////////////////////////////////
				 	if(isCacheOrReloadLookupMode) {
		   			
    stringBuffer.append(TEXT_116);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_117);
    	
		  			} 
					for (int iKeyName = 0; iKeyName < aKeysNames.length; iKeyName++) {
						String typeToGenerate = keysTypes.get(iKeyName);
						boolean javaPrimitiveKeyColumn = JavaTypesManager.isJavaPrimitiveType(typeToGenerate);
			   			if("Document".equals(typeToGenerate)) {
			   				String xpath = aKeysNames[iKeyName];
			   				String relativeXPath = lookupInputNodesXPath.get(xpath);
						
    stringBuffer.append(TEXT_118);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_119);
    stringBuffer.append(relativeXPath);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(aKeysValues[iKeyName] );
    stringBuffer.append(TEXT_121);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(relativeXPath);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(xpathToPatternMap.get(xpath));
    stringBuffer.append(TEXT_123);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(relativeXPath);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(xpathToTypeMap.get(xpath));
    stringBuffer.append(TEXT_125);
    					
							if(isCacheOrReloadLookupMode) {
							
    stringBuffer.append(TEXT_126);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_127);
    stringBuffer.append(aKeysValues[iKeyName] );
    stringBuffer.append(TEXT_128);
    
							}
			   			} else {
						
    stringBuffer.append(TEXT_129);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_130);
    stringBuffer.append( aKeysNames[iKeyName] );
    stringBuffer.append(TEXT_22);
    stringBuffer.append(aKeysValues[iKeyName] );
    stringBuffer.append(TEXT_131);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_132);
    		
				   		}
					}// end for aKeysNames
					
					if(findFromBasicType && isCacheOrReloadLookupMode) {
					
    stringBuffer.append(TEXT_133);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_127);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_134);
    	
					} 
					
    stringBuffer.append(TEXT_135);
    
					if(hasOutgoingConnection || hasExpressionKey || isAllRows){
					
    stringBuffer.append(TEXT_136);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_137);
    if(isOneOfReloadLookupMode) {//TD110
								if(isCacheOrReloadLookupMode) {
							
    stringBuffer.append(TEXT_138);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_139);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_140);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_141);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_142);
    
								}
								List<GlobalMapNode> globalMapEntries = nextInputTable.getGlobalMapKeysValues();
								if(globalMapEntries != null) {
									for(GlobalMapNode entry : globalMapEntries) {
									
    stringBuffer.append(TEXT_143);
    stringBuffer.append( entry.getName());
    stringBuffer.append(TEXT_144);
    stringBuffer.append( matchXmlTreeExpr.generateExprCode(entry.getExpression()) );
    stringBuffer.append(TEXT_145);
    
									}
								}
								
    stringBuffer.append(TEXT_146);
    stringBuffer.append( lookupSubProcessStartNodeName );
    stringBuffer.append(TEXT_147);
    
					            if(nextInputTable.isPersistent()) {
    stringBuffer.append(TEXT_148);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_149);
    stringBuffer.append( isAllRows ? "" : "Sorted" );
    stringBuffer.append(TEXT_150);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_151);
    stringBuffer.append( isAllRows ? "" : "Sorted" );
    stringBuffer.append(TEXT_150);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_152);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_153);
    
							    } else {
							    
    stringBuffer.append(TEXT_148);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_155);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_156);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_153);
    
							    }
							    
    stringBuffer.append(TEXT_157);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_158);
     if(findFromBasicType || isAllRows) { 
    stringBuffer.append(TEXT_157);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_159);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_160);
     } else if(findFromDocumentType) { 
    stringBuffer.append(TEXT_157);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_161);
     } 
						    	if(isCacheOrReloadLookupMode) {
						    	
    stringBuffer.append(TEXT_162);
    	
							    }
							}//TD110
							
    stringBuffer.append(TEXT_163);
     if(!isOneOfReloadLookupMode) {
    stringBuffer.append(TEXT_164);
    if(findFromBasicType || isAllRows) {
    stringBuffer.append(TEXT_148);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_159);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_165);
     } else if(findFromDocumentType) { 
    stringBuffer.append(TEXT_148);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_166);
     } 
    stringBuffer.append(TEXT_118);
     } 
    stringBuffer.append(TEXT_167);
    stringBuffer.append(tableName );
    stringBuffer.append(TEXT_168);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_169);
     if(isInnerJoin) {//inner join,not find and should reject data(not Document)
    stringBuffer.append(TEXT_146);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_170);
    stringBuffer.append( rejectedDocInnerJoin );
    stringBuffer.append(TEXT_171);
     } else {//left outter join should keep main table data,not reject anytime.
    stringBuffer.append(TEXT_146);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_172);
     } 
    stringBuffer.append(TEXT_173);
    if(isMultipleResult) {
    stringBuffer.append(TEXT_174);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_171);
    }
    stringBuffer.append(TEXT_175);
    if(isMultipleResult) {
    stringBuffer.append(TEXT_176);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_177);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_178);
     } 
    stringBuffer.append(TEXT_179);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_180);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_182);
     if(!isMultipleResult) {
    stringBuffer.append(TEXT_183);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_184);
    if(isCacheOrReloadLookupMode) {
    stringBuffer.append(TEXT_185);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_186);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_187);
     for (Object key : outNodesXPath.keySet()) { 
    stringBuffer.append(TEXT_188);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_122);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_189);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_190);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_191);
     } 
    stringBuffer.append(TEXT_192);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_193);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_194);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_190);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_195);
     } 
    stringBuffer.append(TEXT_196);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_197);
     if(findFromDocumentType) {
    stringBuffer.append(TEXT_198);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_199);
     } 
    stringBuffer.append(TEXT_200);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_201);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_202);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_203);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_204);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_205);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_206);
     if(findFromDocumentType || hasOutputFromLookupDoc) {
    stringBuffer.append(TEXT_207);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_208);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(documentColumn);
    stringBuffer.append(TEXT_209);
     if("FIRST_MATCH".equals(matchingMode)) {
    stringBuffer.append(TEXT_210);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_211);
     } 
    stringBuffer.append(TEXT_212);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_213);
    
								for (Object key : outNodesXPath.keySet()) { 
									Object val = outNodesXPath.get(key);
								
    stringBuffer.append(TEXT_214);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(val);
    stringBuffer.append(TEXT_215);
    
								}
								
    stringBuffer.append(TEXT_216);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_217);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(documentColumn);
    stringBuffer.append(TEXT_218);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_219);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_220);
    stringBuffer.append(loopXPath);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(isLoopOptional );
    stringBuffer.append(TEXT_221);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_222);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_223);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_224);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_225);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_226);
    stringBuffer.append(matchingMode);
    stringBuffer.append(TEXT_227);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_228);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_229);
     if(findFromDocumentType) { 
    stringBuffer.append(TEXT_230);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_231);
     } 
    stringBuffer.append(TEXT_232);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_233);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_234);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_235);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_236);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_238);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_239);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_240);
    if(isCacheOrReloadLookupMode) {
    stringBuffer.append(TEXT_241);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_242);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_239);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_240);
     } 
    stringBuffer.append(TEXT_243);
     if("FIRST_MATCH".equals(matchingMode)) {
    stringBuffer.append(TEXT_244);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_231);
     } 
    stringBuffer.append(TEXT_245);
     } 
    stringBuffer.append(TEXT_146);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_208);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_246);
    if(isCacheOrReloadLookupMode) {
    stringBuffer.append(TEXT_247);
    if(findFromDocumentType) {
    stringBuffer.append(TEXT_248);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_249);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_250);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_251);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_252);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_253);
     } else { 
    stringBuffer.append(TEXT_254);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_255);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_251);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_252);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_256);
     } 
    stringBuffer.append(TEXT_146);
     } 
    stringBuffer.append(TEXT_257);
     if("FIRST_MATCH".equals(matchingMode) && (findFromDocumentType || hasOutputFromLookupDoc)) {
    stringBuffer.append(TEXT_258);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_259);
     } 
    stringBuffer.append(TEXT_260);
     if(isInnerJoin && findFromDocumentType) {
    stringBuffer.append(TEXT_261);
    stringBuffer.append( rejectedDocInnerJoin );
    stringBuffer.append(TEXT_262);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_263);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_264);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_22);
    stringBuffer.append( rejectedDocInnerJoin );
    stringBuffer.append(TEXT_265);
     } 
    stringBuffer.append(TEXT_266);
     if(!isInnerJoin && findFromDocumentType) { 
    stringBuffer.append(TEXT_267);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_263);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_268);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_269);
     } 
    stringBuffer.append(TEXT_266);
     if(hasOutputFromLookupDoc && !findFromDocumentType) {//for the case : not lookup doc,but doc output exists
    stringBuffer.append(TEXT_270);
     } 
    stringBuffer.append(TEXT_271);
     if(hasExpressionFilter) {
    stringBuffer.append(TEXT_272);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_273);
    stringBuffer.append(filtersConditionsFormatted );
    stringBuffer.append(TEXT_274);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_275);
    for (String key : outNodesXPath.keySet()) {
    stringBuffer.append(TEXT_276);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_122);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_277);
    }
    stringBuffer.append(TEXT_278);
     if(isInnerJoin) {
    stringBuffer.append(TEXT_164);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_279);
     } 
    stringBuffer.append(TEXT_280);
    
				  			}
				  		} 
				  		
    stringBuffer.append(TEXT_281);
    if(isMultipleResult) {//TD114
    stringBuffer.append(TEXT_282);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_283);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_284);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_285);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_286);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_287);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_288);
    if(isCacheOrReloadLookupMode) {
    stringBuffer.append(TEXT_185);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_186);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_289);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_290);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_291);
     } 
    stringBuffer.append(TEXT_196);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_288);
     if(findFromDocumentType) {
    stringBuffer.append(TEXT_198);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_199);
     } 
    stringBuffer.append(TEXT_292);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_293);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_202);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_203);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_204);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_294);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_206);
     if(findFromDocumentType || hasOutputFromLookupDoc || isDocumentAllRows) {//TA110
    stringBuffer.append(TEXT_295);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_208);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(documentColumn);
    stringBuffer.append(TEXT_296);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_297);
    
									for (Object key : outNodesXPath.keySet()) { 
										Object val = outNodesXPath.get(key);
									
    stringBuffer.append(TEXT_298);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(val);
    stringBuffer.append(TEXT_299);
    
									}
									
    stringBuffer.append(TEXT_216);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_217);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(documentColumn);
    stringBuffer.append(TEXT_218);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_219);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_220);
    stringBuffer.append(loopXPath);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(isLoopOptional );
    stringBuffer.append(TEXT_221);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_222);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_223);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_224);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_225);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_226);
    stringBuffer.append(matchingMode);
    stringBuffer.append(TEXT_227);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_228);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_300);
     if(findFromDocumentType) { 
    stringBuffer.append(TEXT_230);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_231);
     } 
    stringBuffer.append(TEXT_232);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_233);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_301);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_302);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_303);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_304);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_305);
    if(isCacheOrReloadLookupMode) {
    stringBuffer.append(TEXT_306);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_235);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_307);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_242);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_239);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_308);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_302);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_250);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_251);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_252);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_305);
     } 
    stringBuffer.append(TEXT_309);
     }//TA110 
    stringBuffer.append(TEXT_146);
    if(!findFromDocumentType && !isDocumentAllRows){
    stringBuffer.append(TEXT_310);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_208);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_311);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_312);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_313);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_314);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_145);
    }
    stringBuffer.append(TEXT_315);
     if(findFromDocumentType && !isInnerJoin) {
    stringBuffer.append(TEXT_316);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_262);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_263);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_317);
     } 
    stringBuffer.append(TEXT_318);
     if(findFromDocumentType && isInnerJoin) {
    stringBuffer.append(TEXT_129);
    stringBuffer.append( rejectedDocInnerJoin );
    stringBuffer.append(TEXT_262);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_263);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_317);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_22);
    stringBuffer.append( rejectedDocInnerJoin );
    stringBuffer.append(TEXT_319);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_22);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_320);
     } 
    stringBuffer.append(TEXT_321);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_322);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_323);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_324);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_325);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_326);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_327);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_328);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_329);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_330);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_331);
     for (Object key : outNodesXPath.keySet()) { 
    stringBuffer.append(TEXT_332);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_122);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_333);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_190);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_334);
     } 
    stringBuffer.append(TEXT_335);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_193);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_336);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_190);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_337);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_338);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_339);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_340);
     if(hasExpressionFilter) {
    stringBuffer.append(TEXT_341);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_342);
    stringBuffer.append(filtersConditionsFormatted );
    stringBuffer.append(TEXT_343);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_344);
    for (String key : outNodesXPath.keySet()) {
    stringBuffer.append(TEXT_345);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_122);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_346);
    }
    stringBuffer.append(TEXT_347);
     if(isInnerJoin) {
    stringBuffer.append(TEXT_146);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_348);
     } 
    stringBuffer.append(TEXT_349);
     } 
    stringBuffer.append(TEXT_350);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_351);
     
				  			if(isVirtual) {
				  				closeWhileCountForMultiResult++;
				  			} else {
				  				gm.addBlocksCodeToClose(new BlockCode("close loop of lookup '" + tableName +"' // G_TM_M_002"));
				  			}
				  		}//TD114 
				  		
    stringBuffer.append(TEXT_352);
    				
					}
					
    stringBuffer.append(TEXT_353);
    
				} //TD129 end of expression key checking
			}//TD130
		}//TD131
	} else {
		isXpathFromLookup = false;
	}//TD132
	
    
			}//TD513
//END LOOKUP ACTION
////////////////////////////////////////////////////////////////////////////////////////////////////////
//VARIABLES

    stringBuffer.append(TEXT_354);
    
for (VarTable varsTable : varsTables) {
	EList<VarNode> varsTableEntries = varsTable.getNodes();
	if (varsTableEntries == null) {
		continue;
	}
	String varsTableName = varsTable.getName();
	String instanceVarName = varsTableName + "__" + cid;
	String className = instanceVarName + "__Struct";

	
    stringBuffer.append( cr + className + " " + varsTableName + " = " + instanceVarName + ";" );
    
	
	for (VarNode varsTableEntry : varsTableEntries) {
		String varsColumnName = varsTableEntry.getName();
		String varExpression = varsTableEntry.getExpression();
		if (varExpression == null || varExpression.trim().length() == 0) {
			varExpression = JavaTypesManager.getDefaultValueFromJavaIdType(varsTableEntry.getType(),
					varsTableEntry.isNullable());
		}
		
		String resultExpression = "";
		if(matchXmlTreeExpr != null)
			resultExpression = matchXmlTreeExpr.generateExprCode(varExpression);
		if(resultExpression!=null && !("").equals(resultExpression)){

    stringBuffer.append(TEXT_355);
    stringBuffer.append( cr + gm.getGeneratedCodeTableColumnVariable(cid, varsTableName, varsColumnName, false) + " = " + resultExpression
				+ ";" );
    
		}
	}
}
////////////////////////////////////////////////////////////////////////////////////////////////////////
//OUTPUTS

    stringBuffer.append(TEXT_356);
    
		StringBuilder sb = new StringBuilder();
		
		boolean allNotRejectTablesHaveFilter = true;
	    boolean atLeastOneReject = false;
	    boolean atLeastOneRejectInnerJoin = false;
	    String lastValidOutputTableName = null;
	    boolean closeTestInnerJoinConditionsBracket = false;
	    
	    int lstSizeOutputs = outputTablesSortedByReject.size();
	    boolean end = false;
	    for (int i = 0; i < lstSizeOutputs; i++) {
	        OutputXmlTree outputTable = (OutputXmlTree) outputTablesSortedByReject.get(i);
	
			if(outputTable == currentOutputTable) {
		 		end = true;
		 	}
	
	        String outputTableName = outputTable.getName();
	
	        if (outputTable.isRejectInnerJoin()) {
	            atLeastOneRejectInnerJoin = true;
	        }
	        EList<OutputTreeNode> columnEntries = outputTable.getNodes();
	        boolean hasFilter = outputTable.isActivateExpressionFilter() && outputTable.getExpressionFilter() != null && !("").equals(outputTable.getExpressionFilter().trim());
	        if (columnEntries != null && columnEntries.size() > 0) {
	            if (!hasFilter && !(outputTable.isReject() || outputTable.isRejectInnerJoin())) {
	                if(!outputTable.isErrorReject()){
	                	allNotRejectTablesHaveFilter = false;
	                }
	            }
	            if (outputTable.isReject()) {
	                atLeastOneReject = true;
	            }
	        }
	        
			lastValidOutputTableName = outputTableName;
			
			if(end) {
				break;
			}
	
	    }
		
		sb.append(cr + currentOutputTableName + " = null;");
		sb.append(cr);
		
		if (allNotRejectTablesHaveFilter && atLeastOneReject) {
            // write rejected = false;
            sb.append(cr + "boolean " + rejected + " = true;");
        }
        
        // write conditions for inner join reject
        if (lastValidOutputTableName != null && hasValidLookupTables && lstSizeOutputs > 0 && atLeastOneInputTableWithInnerJoin) {
            sb.append(cr + "if(");
            sb.append("!" + rejectedInnerJoin);
            sb.append(" ) {");
            closeTestInnerJoinConditionsBracket = true;
        }
        
		boolean lastValueReject = false;
    	boolean oneFilterForNotRejectTable = false;
		
		end = false;
		// run through output tables list for generating intilization of outputs arrays
        int dummyVarCounter = 0;
		for (int indexCurrentTable = 0; indexCurrentTable < lstSizeOutputs; indexCurrentTable++) {//TD10086
		 	OutputXmlTree outputTable = (OutputXmlTree) outputTablesSortedByReject.get(indexCurrentTable);
		 	
		 	boolean isCurrentOutputTable = outputTable == currentOutputTable;
		 	
		 	if(isCurrentOutputTable) {
		 		end = true;
		 	}
		 	
            EList<OutputTreeNode> outputTableEntries = outputTable.getNodes();
            String outputTableName = outputTable.getName();
			isAllInOne = outputTable.isAllInOne();
			
			IConnection outputConnection = nameToOutputConnection.get(outputTableName);
			HashedMetadataTable hashedMetadataTable = null;
			boolean connectionExists = true;
			if (outputTableEntries == null) {
				connectionExists = false;
			} else {
				hashedMetadataTable = new HashedMetadataTable(outputConnection.getMetadataTable());
			}
			
			boolean currentIsReject = outputTable.isReject();
            boolean currentIsRejectInnerJoin = outputTable.isRejectInnerJoin();
            
            boolean hasExpressionFilter = outputTable.isActivateExpressionFilter() && outputTable.getExpressionFilter() != null && !("").equals(outputTable.getExpressionFilter().trim());
            boolean rejectValueHasJustChanged = lastValueReject != (currentIsReject || currentIsRejectInnerJoin);
            oneFilterForNotRejectTable = !(currentIsReject || currentIsRejectInnerJoin) && hasExpressionFilter;
			
			if (rejectValueHasJustChanged) {
                if (closeTestInnerJoinConditionsBracket) {
                    sb.append(cr + "} // closing inner join bracket (1)");
                    if (atLeastOneReject && allNotRejectTablesHaveFilter) {
                        sb.append(cr + " else {");
                        sb.append(cr + rejected + " = false;");
                        sb.append(cr + "} // closing else inner join bracket (1)");
                    }
                    closeTestInnerJoinConditionsBracket = false;
                }
            }
            
            boolean hasAggregateColumn = false;
            
            // No connection matching and no checking errors
            if (connectionExists || checkingSyntax) {//TD12580
	            if (rejectValueHasJustChanged) {
	                sb.append(cr + "// ###### START REJECTS ##### ");
	            }

	            // write filters conditions and code to execute
	            if (!currentIsReject && !currentIsRejectInnerJoin || rejectValueHasJustChanged
	                    && oneFilterForNotRejectTable || currentIsReject && allNotRejectTablesHaveFilter
	                    || currentIsRejectInnerJoin && atLeastOneInputTableWithInnerJoin || checkingSyntax) {

	                boolean closeFilterOrRejectBracket = false;
	                if (currentIsReject || currentIsRejectInnerJoin) {
	                    sb.append(cr + cr + "// # Output reject table : '" + outputTableName + "'");
	                } else {
	                    sb.append(cr + cr + "// # Output table : '" + outputTableName + "'");
	                }
	                if (hasExpressionFilter || currentIsReject || currentIsRejectInnerJoin && atLeastOneInputTableWithInnerJoin) {
	                    sb.append(cr + "// # Filter conditions ");
						String ifConditions = "if( ";
	                    String rejectedTests = null;
	                    if (allNotRejectTablesHaveFilter && atLeastOneReject && currentIsReject && currentIsRejectInnerJoin
	                            && atLeastOneInputTableWithInnerJoin) {
	                        rejectedTests = rejected + " || " + rejectedInnerJoin;
	                        if (hasExpressionFilter) {
	                            rejectedTests = "(" + rejectedTests + ")";
	                        }
	                    } else if (allNotRejectTablesHaveFilter && atLeastOneReject && currentIsReject) {
	                        rejectedTests = rejected;
	                    } else if (currentIsRejectInnerJoin && atLeastOneInputTableWithInnerJoin) {
	                        rejectedTests = rejectedInnerJoin;
	                    }
	                    String filtersConditions = null;
	                    filtersConditions = outputTable.getExpressionFilter();
						if(filtersConditions != null && !("".equals(filtersConditions.trim())) && (filtersConditions.trim().length()>0)) {
							String filtersConditionsFormatted = "";
							if(matchXmlTreeExpr != null)
								filtersConditionsFormatted = matchXmlTreeExpr.generateExprCode(filtersConditions);
							if (rejectedTests == null) {
								ifConditions += cr + cr + filtersConditionsFormatted  + cr + cr;
							} else {
								ifConditions += rejectedTests + " && (" + cr + cr + filtersConditionsFormatted + cr + cr +")";
							}
						} else {
							if (rejectedTests != null) {
								ifConditions += rejectedTests;
							}
						}
	                    
	                    ifConditions += " ) {";
	
	                    sb.append(cr).append(ifConditions);
	
	                    closeFilterOrRejectBracket = true;
	                    if (allNotRejectTablesHaveFilter && !(currentIsReject || currentIsRejectInnerJoin)
	                            && atLeastOneReject) {
	                        sb.append(cr + rejected + " = false;");
	                    }
	                }

					//only current output table has ouput generate
	                if (isCurrentOutputTable && outputTableEntries != null && (!currentIsReject && !currentIsRejectInnerJoin || currentIsReject || currentIsRejectInnerJoin
	                        && atLeastOneInputTableWithInnerJoin || checkingSyntax)) {
	                    if(isLog4jEnabled){
							sb.append(cr + "count_" + outputTableName + "_" + cid + "++;" + cr);
						}
						
						if(!utilXML.tableHasADocument(outputTableEntries)) {
							sb.append(cr + cr + outputTableName + "_tmp = new " + outputTableName + "Struct();");
						}
						boolean hasDocument = false;
						//cache flat column expressions and fix TDI-17811
						List<String> flatExpressions = new ArrayList<String>();
						Map<String,String> nameToAssignationVar = new HashMap<String,String>();
						Map<String,String> nameToResultExpression = new HashMap<String,String>();
						for (OutputTreeNode outputTableEntry : outputTableEntries) {//block 12580
							String outputColumnName = outputTableEntry.getName();
							IMetadataColumn metadataColumnFromConn = null;
	                        if(connectionExists) {
		                        metadataColumnFromConn = hashedMetadataTable.getColumn(outputColumnName);
		                    }
		                    String outputExpression = outputTableEntry.getExpression();
		                    
		                    String resultExpression = "";
		                    if(matchXmlTreeExpr != null)
								resultExpression = matchXmlTreeExpr.generateExprCode(outputExpression);
	                        if (resultExpression == null || resultExpression.trim().length() == 0) {
	                        	if(metadataColumnFromConn == null) {
		                            resultExpression = JavaTypesManager.getDefaultValueFromJavaIdType(outputTableEntry
	    	                                .getType(), outputTableEntry.isNullable());
	    	                    } else {
		                            resultExpression = JavaTypesManager.getDefaultValueFromJavaIdType(metadataColumnFromConn
	    	                                .getTalendType(), metadataColumnFromConn.isNullable());
	    	                    }
	                        } else {
	                        
	                        	if(metadataColumnFromConn == null) {
									continue;
								}	                        
	                        
	                        }
	                        
	                        String assignationVar = null;
	                        if (connectionExists) {                       
	                        	assignationVar = gm.getGeneratedCodeTableColumnVariable(uniqueNameComponent, outputTableName + "_tmp",outputColumnName, false);
	                        } else {
	                        	if(metadataColumnFromConn == null) {
		                            assignationVar = JavaTypesManager.getTypeToGenerate(outputTableEntry.getType(),
	                                    outputTableEntry.isNullable())
	                                    + " dummyVar" + (dummyVarCounter++);
	                            } else {
		                            assignationVar = JavaTypesManager.getTypeToGenerate(metadataColumnFromConn
	    	                                .getTalendType(), metadataColumnFromConn.isNullable())
	                                    + " dummyVar" + (dummyVarCounter++);
	                            }
	                        }
    	                    String expression = assignationVar + " = " + resultExpression + ";";
    	                    
    	                    nameToAssignationVar.put(outputColumnName,assignationVar);
    	                    nameToResultExpression.put(outputColumnName,resultExpression);
    	                    
    	                    if(!("id_Document").equals(outputTableEntry.getType())){
								flatExpressions.add(expression);
							}
    	                    
						}//block 12580
						
	                    for (OutputTreeNode outputTableEntry : outputTableEntries) {
	                        String outputColumnName = outputTableEntry.getName();
	                       	
	                       	String assignationVar = nameToAssignationVar.get(outputColumnName);
	                       	String resultExpression = nameToResultExpression.get(outputColumnName);
	                       	
	                       	if(resultExpression == null) {
	                       		continue;
	                       	}
	                       	
    	                    String expression = assignationVar + " = " + resultExpression + ";";
	
							if(("id_Document").equals(outputTableEntry.getType())){
								hasDocument = true;
								hasDocumentGlobal = true;
								JavaType javaType = JavaTypesManager.getJavaTypeFromId(outputTableEntry.getType());
								String typeToGenerate = javaType.getNullableClass().getCanonicalName();
								
								//get the document aggregation columns
								List<String> xpaths = new ArrayList<String>();
								List<String> exprCodes = new ArrayList<String>();
								
								MatchXmlTreeExpr exprUtil = new MatchXmlTreeExpr(cid);
								List<TreeNode> allLeaf = new ArrayList<TreeNode>();
								treeUtil.getAllLeaf(outputTableEntry,allLeaf);
								for(TreeNode leaf : allLeaf) {
									OutputTreeNode outputLeaf = (OutputTreeNode)leaf;
									if(outputLeaf.isAggregate()) {
										String xpath = outputLeaf.getXpath();
										String expressionAgg = outputLeaf.getExpression();
										String exprCode = exprUtil.generateExprCode(expressionAgg);
										
										hasAggregateColumn = true;
										
										xpaths.add(xpath);
										exprCodes.add(exprCode);
									}
								}
								
								if(hasAggregateColumn || isAllInOne) { //TD256
									int size = xpaths.size();
									if(xpaths.size() >0) {
										sb.append(cr + cr + "if(");
										for(int i=0; i<size; i++) {
											String xpath = xpaths.get(i);
											String exprCode = exprCodes.get(i);
											sb.append("(");
											sb.append(exprCode);
											sb.append(")==null ||");
											sb.append("!" + exprCode + ".equals(aggregateCacheMap_"+cid+".get(\""+xpath+"\"))");
											if(i != size-1) {
												sb.append(" || ");
											}
										}
										sb.append(") {" + cr);
									} else {
										sb.append(cr + cr + "if(");
										sb.append("!docAlreadyInstanciate_" + outputTableName);
										sb.append(") {" + cr);
										sb.append("docAlreadyInstanciate_" + outputTableName + " = true;");
									}
									sb.append("gen_Doc_" + outputTableName + "_" + cid + " = new GenerateDocument_" + outputTableName + "();" + cr);
									sb.append("//init one new out struct for cache the result." + cr);
									sb.append(outputTableName + "_aggregate = new " + outputTableName + "Struct();" + cr);
									sb.append(outputTableName + "_aggregate." + outputTableEntry.getName() + " = new " + typeToGenerate + "();" + cr);
									sb.append(outputTableName + "_aggregate." + outputTableEntry.getName() + ".setDocument(gen_Doc_" + outputTableName + "_" + cid + ".getDocument());");
									sb.append(cr + cr);
									sb.append("//construct the resultset" + cr);
									sb.append("allOutsForAggregate_" + uniqueNameComponent + ".add(" + outputTableName + "_aggregate);" + cr);
									for(int i=0; i<size; i++) {
										String xpath = xpaths.get(i);
										String exprCode = exprCodes.get(i);
										sb.append("//store the previous value" + cr);
										sb.append("aggregateCacheMap_" + cid + ".put(\"" + xpath + "\", " + exprCode + ");" + cr);
									}
									sb.append("}" + cr);
								} else if(!isAllInOne) { // If we have a document in output table entry, with allInOne = false and no aggregation
									sb.append(cr + cr + "gen_Doc_" + outputTableName + "_" + cid + " = new GenerateDocument_" + outputTableName + "();" + cr);
									sb.append(outputTableName + "_tmp = new " + outputTableName + "Struct();");
									//fix TDI-17811
									for(String exp : flatExpressions) {
										sb.append(cr).append(exp);
									}
									sb.append(cr + cr);
								} //TD256
								
								if(parallelLoopExistInCurrentOutputTable) {
									sb.append(cr +"gen_Doc_"+outputTableName+"_"+cid+"."+isCurrentLoopRef + " = true;");
								}
								
								sb.append(cr + cr +"gen_Doc_"+outputTableName+"_"+cid+".generateElements(");
								if(hasDocumentInMainInputTable || hasDocumentInAnyLookupTable) {
									sb.append("treeNodeAPI_"+cid+",");
								}
								sb.append(rejectedDocInnerJoin);
								for (InputXmlTree inputTable: inputTables) {
									sb.append(","+inputTable.getName());
								}
								for (VarTable var : varsTables) {
									sb.append(","+var.getName());
								}

								sb.append(");" + cr + cr);
								
								if(parallelLoopExistInCurrentOutputTable) {
									sb.append(cr +"gen_Doc_"+outputTableName+"_"+cid+"."+isCurrentLoopRef + " = false;");
								}
								
								/*
								Generate the following part if:
								1 - Virtual generation = 1 document in main input table OR 1 document with aggregate in an output table OR 1 table with "All In One" activated.
								2 - a table in output with a document (without aggregate column and without the "All in one" option.
								*/
								
								if(!hasAggregateColumn && !isAllInOne) {
									sb.append("if("+assignationVar + " == null){" + cr);
									sb.append(assignationVar + " = new " + typeToGenerate + "();" + cr);
									sb.append(assignationVar + ".setDocument(gen_Doc_"+outputTableName+"_"+cid+".getDocument());" + cr);
									if(!hasAggregateColumn && !isAllInOne) {
										sb.append("//construct the resultset when there is no aggregate column for the out table" + cr);
										sb.append("allOutsForAggregate_" + uniqueNameComponent + ".add(" + outputTableName + "_tmp);");
									}
								
									sb.append("}" + cr);
								}
							} else {
								sb.append(cr).append(expression);
								if(hasAggregateColumn) {
								sb.append("//set the flat column for aggregate" + cr);
									sb.append(outputTableName + "_aggregate." + outputTableEntry.getName() + " = " + resultExpression + ";" + cr);
								}
							}
	                    } // for entries
	
	                    if (connectionExists) {
							// If an output table doesn't contain a Document, and if there is an aggregate element in the output tables
							boolean flat = true;
							for(OutputTreeNode table_ent : outputTable.getNodes()) {
								if(("id_Document").equals(table_ent.getType())){
									flat = false;
									break;
								}
							}
							if(flat) {
								sb.append("allOutsForAggregate_" + uniqueNameComponent + ".add(" + outputTableName + "_tmp);" + cr);
							}
							
							if(isLog4jEnabled){
								sb.append(cr + "log.debug(\"" + uniqueNameComponent + " - Outputting the record \" + count_" + outputTableName + "_" + cid + " + \" of the output table '" + outputTableName + "'.\");" + cr);
							}
	                    }
	                }
					if (closeFilterOrRejectBracket) {
						sb.append(cr + "} // closing filter/reject");
					}
				}
				lastValueReject = currentIsReject || currentIsRejectInnerJoin;
				
			}//TD12580

			if (isCurrentOutputTable && (hasAggregateColumn || isAllInOne)) {
				boolean checkNull = false;
				boolean first = true;
				
				for (OutputTreeNode outputTableEntry : outputTableEntries) {
					if(!("id_Document".equals(outputTableEntry.getType()))) {
						if(first) {
							first = false;
							checkNull = true;
							sb.append(cr).append(cr).append("if(").append(outputTableName).append("_aggregate").append("!=null){");
						}
						sb.append(cr + cr + outputTableName + "_aggregate." + outputTableEntry.getName() + " = " + outputTableName + "_tmp." + outputTableEntry.getName() + ";");
					}
				}
				
				if(checkNull) {
					sb.append(cr).append("}");
				}
			}
			
			/*boolean isLastTable = indexCurrentTable == lstSizeOutputs - 1;
			if (closeTestInnerJoinConditionsBracket 
				&& (isLastTable || !checkingSyntax && (outputTableName.equals(lastValidOutputTableName) || lastValidOutputTableName == null))) {
				sb.append(cr + "}  // closing inner join bracket (2)");
				closeTestInnerJoinConditionsBracket = false;
			}*/
			
            if(end) {
            	//current table always be the last table
            	if (closeTestInnerJoinConditionsBracket 
					&& (!checkingSyntax && (outputTableName.equals(lastValidOutputTableName) || lastValidOutputTableName == null))) {
					sb.append(cr + "}  // closing inner join bracket (2)");
					closeTestInnerJoinConditionsBracket = false;
				}
            	break;
            }
            
		}//TD10086
		
		sb.append(cr + "// ###############################");
		sb.append(cr);
		sb.append(cr + "} // end of Var scope");
		sb.append(cr + cr + rejectedInnerJoin + " = false;");
		sb.append(cr);
////////////////////////////////////////////////////////////////////////////////////////////////////////

    stringBuffer.append(TEXT_7);
    stringBuffer.append( sb.toString());
    
			while((closeWhileCountForMultiResult--) > 0) {

    stringBuffer.append(TEXT_357);
    
			
			}
			
			while((nestedLoopWhileCount--) > 0) {//TD514

    stringBuffer.append(TEXT_358);
    
			}//TD514
			
			if(!hasDocumentInMainInputTable && currentInputLoopNodes.size()<1) {

    stringBuffer.append(TEXT_359);
    
			}
			
		}//TD256
	}//TD128

    
		} else {

    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    
		for(OutputXmlTree outputTable : outputTables) {
			String outputTableName = outputTable.getName();
			if(nameToOutputConnection.get(outputTableName) == null) {
				continue;
			}
			isAllInOne = outputTable.isAllInOne();
			for(OutputTreeNode outputNode: outputTable.getNodes()) {
				if(("id_Document").equals(outputNode.getType())){
				
					//get the document aggregation columns
					boolean hasAggregateColumn = false;
					
					
					TreeUtil treeUtil = new TreeUtil();
					List<TreeNode> allLeaf = new ArrayList<TreeNode>();
					treeUtil.getAllLeaf(outputNode,allLeaf);
					for(TreeNode leaf : allLeaf) {
						OutputTreeNode outputLeaf = (OutputTreeNode)leaf;
						if(outputLeaf.isAggregate()) {
							hasAggregateColumn = true;
							break;
						}
					}
					
					if(!hasAggregateColumn && !isAllInOne) {
				
    stringBuffer.append(TEXT_360);
    stringBuffer.append(outputTableName );
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_361);
    stringBuffer.append(outputTableName );
    stringBuffer.append(TEXT_362);
    stringBuffer.append(outputTableName );
    stringBuffer.append(TEXT_363);
    stringBuffer.append(outputTableName );
    stringBuffer.append(TEXT_364);
      
					}
				
    stringBuffer.append(TEXT_365);
    stringBuffer.append(outputTableName );
    stringBuffer.append(TEXT_366);
    stringBuffer.append(outputNode.getName());
    stringBuffer.append(TEXT_367);
    
					break;
				} // if(docuemnt)
			}// for(outputNode)
		} // for (outputXMLTree)
		
    stringBuffer.append(TEXT_368);
    
		if(hasDocumentInAnyLookupTable) {
		
    stringBuffer.append(TEXT_369);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_370);
    
		}
		
		for(TreeNode tmpNode : mainInputTable.getNodes()){
//			boolean isInnerJoin = true;
			currentMainNode = tmpNode;

			if(tmpNode.getType().equals("id_Document")){
				isPlainNode = false;
			}
			
			if(("id_Document").equals(tmpNode.getType())) { // find the type:document node.
				hasDocumentInMainInputTable = true;
				//find the loop path
				XPathHelper xpathHelper = new XPathHelper(tmpNode);
				
				String loopPath = xpathHelper.getLoopNodeXPath();
				
    stringBuffer.append(TEXT_371);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_372);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(loopPath );
    stringBuffer.append(TEXT_373);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(xpathHelper.isLoopOptional() );
    stringBuffer.append(TEXT_374);
    stringBuffer.append(mainInputName );
    stringBuffer.append(TEXT_18);
    stringBuffer.append(tmpNode.getName() );
    stringBuffer.append(TEXT_375);
    stringBuffer.append(mainInputName );
    stringBuffer.append(TEXT_18);
    stringBuffer.append(tmpNode.getName() );
    stringBuffer.append(TEXT_376);
    stringBuffer.append(mainInputName );
    stringBuffer.append(TEXT_18);
    stringBuffer.append(tmpNode.getName() );
    stringBuffer.append(TEXT_377);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(mainInputName );
    stringBuffer.append(TEXT_18);
    stringBuffer.append(tmpNode.getName() );
    stringBuffer.append(TEXT_378);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_379);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(xpathHelper.hasDefinedNS());
    stringBuffer.append(TEXT_379);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_380);
    if(!xpathHelper.hasDefinedNS()){
    stringBuffer.append(TEXT_381);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_382);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_383);
    }else{
    stringBuffer.append(TEXT_384);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_385);
    stringBuffer.append(xpathHelper.buildNSMapping("xmlNameSpaceMap"+"_"+cid));
    stringBuffer.append(TEXT_353);
    }
    stringBuffer.append(TEXT_386);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_380);
    
				Map<String,String> outNodesXPath = xpathHelper.getOutNodesXPath();
				
    stringBuffer.append(TEXT_387);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_388);
    stringBuffer.append(outNodesXPath.size());
    stringBuffer.append(TEXT_389);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_388);
    stringBuffer.append(outNodesXPath.size());
    stringBuffer.append(TEXT_390);
    
				int i = 0;
				for (String key : outNodesXPath.keySet()) { 
				    String val = outNodesXPath.get(key);
				
    stringBuffer.append(TEXT_391);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_392);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_393);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_394);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_392);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_393);
    stringBuffer.append(val);
    stringBuffer.append(TEXT_395);
    
					i++;
				}
				
    stringBuffer.append(TEXT_386);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_379);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_396);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_397);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_398);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_399);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_400);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_401);
    stringBuffer.append( rejectedDocInnerJoin );
    stringBuffer.append(TEXT_83);
    stringBuffer.append(mainRowRejected);
    stringBuffer.append(TEXT_402);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_403);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_404);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_405);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_406);
    
	   		}// if(document)
		}// for()
		
    stringBuffer.append(TEXT_7);
    
	TreeUtil treeUtil = new TreeUtil();
	for (int iInputTable = 0; iInputTable < sizeInputTables; iInputTable++) {
		hasOutgoingConnection = false;//reset the var for every lookup table 
		InputXmlTree nextInputTable = inputTables.get(iInputTable);
		if(!atLeastOneInputTableWithInnerJoin) {
			if(nextInputTable.isInnerJoin()) {
				atLeastOneInputTableWithInnerJoin = true;
			}
		}

    stringBuffer.append(TEXT_407);
    stringBuffer.append(TEXT_73);
    
	EConnectionType connectionType = null;
	if (nextInputTable != null && nextInputTable.isLookup()) {//TD132 
		IConnection connection =  hNameToConnection.get(nextInputTable.getName());

		if(connection == null){
			continue;
		}else{//TD131
			connectionType = connection.getLineStyle();
			
			if(connectionType == EConnectionType.FLOW_REF) {//TD130
				IMetadataTable metadataTable = connection.getMetadataTable();
				String tableName = nextInputTable.getName();
				String lookupMode = nextInputTable.getLookupMode();
				boolean isCacheOrReloadLookupMode = org.talend.designer.xmlmap.model.tree.LOOKUP_MODE.CACHE_OR_RELOAD.name().equals(lookupMode);
				boolean isOneOfReloadLookupMode = isCacheOrReloadLookupMode ||
					   org.talend.designer.xmlmap.model.tree.LOOKUP_MODE.RELOAD.name().equals(lookupMode);
				INode lookupSubProcessStartNode = searchSubProcessStartNode(connection);
			  	String lookupSubProcessStartNodeName = lookupSubProcessStartNode.getUniqueName();
				boolean isInnerJoin = false;
				if (nextInputTable.isInnerJoin()) {
					isInnerJoin = true;
			    	inputTablesWithInnerJoin.add(nextInputTable);
			 	}
				
    stringBuffer.append(TEXT_74);
    stringBuffer.append( nextInputTable );
    stringBuffer.append(TEXT_75);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_76);
    
				String filtersConditions = nextInputTable.getExpressionFilter();
				String filtersConditionsFormatted = "";
				boolean hasExpressionFilter = nextInputTable.isActivateExpressionFilter() && filtersConditions != null && !("").equals(filtersConditions.trim());
				if(hasExpressionFilter && matchXmlTreeExpr != null) {
					filtersConditionsFormatted = matchXmlTreeExpr.generateExprCode(filtersConditions);
				}

				String expressionKey = "";
				List<IMetadataColumn> listColumns = metadataTable.getListColumns();
				ArrayList<String> keysNames = new ArrayList<String>();
				ArrayList<String> keysValues = new ArrayList<String>();
				ArrayList<String> keysTypes = new ArrayList<String>();
				EList<TreeNode> externalTreeNodes = nextInputTable.getNodes();
				
				boolean findFromBasicType = false;
				boolean findFromDocumentType = false;
				String documentColumn = null;
				boolean hasOutputFromLookupDoc = false;//to output or another lookup from Document
				Map<String,String> outNodesXPath = new HashMap<String,String>();//output contain(1.lookup table to output table 2.lookup table to lookup table)
				Map<String,String> lookupInputNodesXPath = new HashMap<String,String>();
				String loopXPath = "";
				boolean isLoopOptional = false;
				boolean isMultipleResult = false;
				boolean existXpathKeyFromCurrentLookup = false;
				boolean hasExpressionKey = false;
				//cache the type and pattern information for sub columns in Document column
				Map<String,String> xpathToPatternMap = null;
				Map<String,String> xpathToTypeMap = null;
				
				boolean unvalidLookupKeyExistForHash = false;
				List<IMetadataColumn> validLookupKeyFlatColumns = new ArrayList<IMetadataColumn>();
				
				for(int j = 0; j < listColumns.size(); j++){
					IMetadataColumn column = listColumns.get(j);
					String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
				    String columnName = column.getLabel();
				    TreeNode  externalInputTableEntry = externalTreeNodes.get(j);
				    XPathHelper xpathHelper;
				    if("Document".equals(typeToGenerate)) {
				    	xpathHelper = new XPathHelper(externalInputTableEntry);
						loopXPath = xpathHelper.getLoopNodeXPath();
						isLoopOptional = xpathHelper.isLoopOptional();
						outNodesXPath = xpathHelper.getOutNodesXPath();
						lookupInputNodesXPath = xpathHelper.getLookupInputNodesXPath();
						xpathToPatternMap = xpathHelper.getXpathToPatternMap();
						xpathToTypeMap = xpathHelper.getXpathToTypeMap();
						
    stringBuffer.append(TEXT_34);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(xpathHelper.buildNSMapping("xmlNameSpaceMap"+"_"+tableName));
    stringBuffer.append(TEXT_77);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_78);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_80);
    
				    }
				    List<TreeNode> allLeaf = new ArrayList<TreeNode>();
				    treeUtil.getAllLeaf(externalInputTableEntry,allLeaf); 
				    
				    for(TreeNode leaf : allLeaf) { 
					    if (leaf != null) {
				    		if("Document".equals(typeToGenerate)) {
				    			documentColumn = columnName;
				    			EList<Connection> outputs = leaf.getOutgoingConnections();
				    			EList<LookupConnection> lookupOutputs = leaf.getLookupOutgoingConnections();
				    			EList<FilterConnection> filterOutputs = leaf.getFilterOutGoingConnections();
					    		if((outputs!=null && outputs.size()!=0) || (lookupOutputs!=null && lookupOutputs.size()!=0) || (filterOutputs!=null && filterOutputs.size()!=0)) {
					    			hasOutputFromLookupDoc = true;
					    			hasOutgoingConnection = true;
					    		}
				    		}
				    	  	if(leaf.getOutgoingConnections().size() > 0 || leaf.getLookupOutgoingConnections().size() > 0 || leaf.getFilterOutGoingConnections().size() > 0){
						    	hasOutgoingConnection = true;
						    }
						    
				    	  	expressionKey = leaf.getExpression();
							if (expressionKey != null && !"".equals(expressionKey.trim())) {
							
								//filter for valid lookup key
								if(hasDocumentInMainInputTable && (parallelLoopExistInCurrentOutputTable || parallelRelationExistBetweenAllOutputTables)) {
									if(!loopHelper.validLookupKey(leaf)) {
										continue;
									} else {
										if(!"Document".equals(typeToGenerate)) {
											validLookupKeyFlatColumns.add(column);
										}
									}
								}
								
								hasExpressionKey = true;
							    String resultExpression = null;
							    if("Document".equals(typeToGenerate)) {
							    	findFromDocumentType = true;
							    	keysNames.add(leaf.getXpath());
							    } else {
							    	keysNames.add(columnName);
							    	findFromBasicType = true;
							    }
							    if(matchXmlTreeExpr != null){
							    	resultExpression = matchXmlTreeExpr.generateExprCode(expressionKey);
							    }
							    if(resultExpression != null && !("").equals(resultExpression)){
							    	keysValues.add(resultExpression);
							    	if(resultExpression.indexOf("/") != -1) {
							    		existXpathKeyFromCurrentLookup = true;
							    	}
							    } else {
							    	keysValues.add(expressionKey);
							    }
							    keysTypes.add(typeToGenerate);
							    boolean javaPrimitiveKeyColumn = JavaTypesManager.isJavaPrimitiveType(typeToGenerate);
							}
					    }
					}
				}
				
				/////////////////////////////////////////////////////
				//set unvalidLookupKeyExistForHash
				if(hasDocumentInMainInputTable && (parallelLoopExistInCurrentOutputTable || parallelRelationExistBetweenAllOutputTables)) {
					LoopHelper nestLoopHelper = new LoopHelper(documentNodeInMainInputTable,outputTablesSortedByReject,inputTables);
					for(int ii=0;ii<allInputLoopNodesTables.size();ii++) {
						InputLoopNodesTable oneInputLoopNodesTable = allInputLoopNodesTables.get(ii);
						List<TreeNode> oneInputLoopNodes = oneInputLoopNodesTable.getInputloopnodes();
						nestLoopHelper.initForOneOutputTableLoop(oneInputLoopNodes);
						if(nextInputTable!=null && nextInputTable.isLookup()) {
							EList<TreeNode> treeNodes = nextInputTable.getNodes();
							for(TreeNode treeNode : treeNodes) {
								//only check the flat column,because hash only ready for flat column
								if(!nestLoopHelper.validLookupKey(treeNode)) {
									unvalidLookupKeyExistForHash = true;
								}
								
							}
							
						}
					}
				}
				/////////////////////////////////////////////////////
				
				isXpathFromLookup = existXpathKeyFromCurrentLookup;
				String[] aKeysNames = keysNames.toArray(new String[0]);
				String[] aKeysValues = keysValues.toArray(new String[0]);
				String className = tableName + "Struct";
				
				hasValidLookupTables = true;
				
				String matchingMode = nextInputTable.getMatchingMode();
				if(matchingMode == null) {
					if(aKeysValues.length > 0) {
						matchingMode = "UNIQUE_MATCH";
					} else {
						matchingMode = "ALL_ROWS";
					}
				} else {
					if(aKeysValues.length == 0) {
						matchingMode = "ALL_ROWS";
					}
				}
				
				boolean isAllRows = "ALL_ROWS".equals(matchingMode);
				boolean isDocumentAllRows = isAllRows && documentColumn!=null;
				
				if("ALL_ROWS".equals(matchingMode) || "ALL_MATCHES".equals(matchingMode)) {
					isMultipleResult = true;
				}
				
    stringBuffer.append(TEXT_81);
    if(hasOutgoingConnection || hasExpressionKey || isAllRows){ 
    stringBuffer.append(TEXT_82);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_83);
    stringBuffer.append( className );
    stringBuffer.append(TEXT_84);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_85);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_86);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_87);
    } 
    stringBuffer.append(TEXT_81);
    
				if(aKeysValues!=null){//TD129
				////////////////////////////////////////////////////////////////////////
					if(unvalidLookupKeyExistForHash) {
					
    stringBuffer.append(TEXT_88);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_89);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_90);
    stringBuffer.append(loopKey);
    stringBuffer.append(TEXT_91);
    
						for(IMetadataColumn column : validLookupKeyFlatColumns) {
							String columnName = column.getLabel();
    						JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());

							if (JavaTypesManager.isJavaPrimitiveType(column.getTalendType(), column.isNullable())) {
							
							 	String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
							 	if(javaType == JavaTypesManager.BOOLEAN) {
								
    stringBuffer.append(TEXT_92);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_93);
    
								} else {	
								
    stringBuffer.append(TEXT_94);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_95);
    
								}
								
							} else if(javaType == JavaTypesManager.BYTE_ARRAY) {
		
								
    stringBuffer.append(TEXT_96);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_97);
    
							
							} else {
							
    stringBuffer.append(TEXT_98);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_99);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_100);
    
							}
						}
					
    stringBuffer.append(TEXT_101);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_104);
    	
								for(IMetadataColumn column : validLookupKeyFlatColumns) {
									String columnName = column.getLabel();
									JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
				
									if (JavaTypesManager.isJavaPrimitiveType(column.getTalendType(), column.isNullable())) {
									
    stringBuffer.append(TEXT_105);
    stringBuffer.append(columnName );
    stringBuffer.append(TEXT_106);
    stringBuffer.append(columnName );
    stringBuffer.append(TEXT_107);
    
									} else if(javaType == JavaTypesManager.BYTE_ARRAY) {
									
    stringBuffer.append(TEXT_108);
    stringBuffer.append(columnName );
    stringBuffer.append(TEXT_109);
    stringBuffer.append(columnName );
    stringBuffer.append(TEXT_110);
    
									} else {
									
    stringBuffer.append(TEXT_105);
    stringBuffer.append(columnName );
    stringBuffer.append(TEXT_111);
    stringBuffer.append(columnName );
    stringBuffer.append(TEXT_112);
    stringBuffer.append(columnName );
    stringBuffer.append(TEXT_113);
    stringBuffer.append(columnName );
    stringBuffer.append(TEXT_114);
    
									}
				
								}

    stringBuffer.append(TEXT_115);
    
					}
					///////////////////////////////////////////////////////////////////////
				 	if(isCacheOrReloadLookupMode) {
		   			
    stringBuffer.append(TEXT_116);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_117);
    	
		  			} 
					for (int iKeyName = 0; iKeyName < aKeysNames.length; iKeyName++) {
						String typeToGenerate = keysTypes.get(iKeyName);
						boolean javaPrimitiveKeyColumn = JavaTypesManager.isJavaPrimitiveType(typeToGenerate);
			   			if("Document".equals(typeToGenerate)) {
			   				String xpath = aKeysNames[iKeyName];
			   				String relativeXPath = lookupInputNodesXPath.get(xpath);
						
    stringBuffer.append(TEXT_118);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_119);
    stringBuffer.append(relativeXPath);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(aKeysValues[iKeyName] );
    stringBuffer.append(TEXT_121);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(relativeXPath);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(xpathToPatternMap.get(xpath));
    stringBuffer.append(TEXT_123);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(relativeXPath);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(xpathToTypeMap.get(xpath));
    stringBuffer.append(TEXT_125);
    					
							if(isCacheOrReloadLookupMode) {
							
    stringBuffer.append(TEXT_126);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_127);
    stringBuffer.append(aKeysValues[iKeyName] );
    stringBuffer.append(TEXT_128);
    
							}
			   			} else {
						
    stringBuffer.append(TEXT_129);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_130);
    stringBuffer.append( aKeysNames[iKeyName] );
    stringBuffer.append(TEXT_22);
    stringBuffer.append(aKeysValues[iKeyName] );
    stringBuffer.append(TEXT_131);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_132);
    		
				   		}
					}// end for aKeysNames
					
					if(findFromBasicType && isCacheOrReloadLookupMode) {
					
    stringBuffer.append(TEXT_133);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_127);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_134);
    	
					} 
					
    stringBuffer.append(TEXT_135);
    
					if(hasOutgoingConnection || hasExpressionKey || isAllRows){
					
    stringBuffer.append(TEXT_136);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_137);
    if(isOneOfReloadLookupMode) {//TD110
								if(isCacheOrReloadLookupMode) {
							
    stringBuffer.append(TEXT_138);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_139);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_140);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_141);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_142);
    
								}
								List<GlobalMapNode> globalMapEntries = nextInputTable.getGlobalMapKeysValues();
								if(globalMapEntries != null) {
									for(GlobalMapNode entry : globalMapEntries) {
									
    stringBuffer.append(TEXT_143);
    stringBuffer.append( entry.getName());
    stringBuffer.append(TEXT_144);
    stringBuffer.append( matchXmlTreeExpr.generateExprCode(entry.getExpression()) );
    stringBuffer.append(TEXT_145);
    
									}
								}
								
    stringBuffer.append(TEXT_146);
    stringBuffer.append( lookupSubProcessStartNodeName );
    stringBuffer.append(TEXT_147);
    
					            if(nextInputTable.isPersistent()) {
    stringBuffer.append(TEXT_148);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_149);
    stringBuffer.append( isAllRows ? "" : "Sorted" );
    stringBuffer.append(TEXT_150);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_151);
    stringBuffer.append( isAllRows ? "" : "Sorted" );
    stringBuffer.append(TEXT_150);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_152);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_153);
    
							    } else {
							    
    stringBuffer.append(TEXT_148);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_155);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_156);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_153);
    
							    }
							    
    stringBuffer.append(TEXT_157);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_158);
     if(findFromBasicType || isAllRows) { 
    stringBuffer.append(TEXT_157);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_159);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_160);
     } else if(findFromDocumentType) { 
    stringBuffer.append(TEXT_157);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_161);
     } 
						    	if(isCacheOrReloadLookupMode) {
						    	
    stringBuffer.append(TEXT_162);
    	
							    }
							}//TD110
							
    stringBuffer.append(TEXT_163);
     if(!isOneOfReloadLookupMode) {
    stringBuffer.append(TEXT_164);
    if(findFromBasicType || isAllRows) {
    stringBuffer.append(TEXT_148);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_159);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_165);
     } else if(findFromDocumentType) { 
    stringBuffer.append(TEXT_148);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_166);
     } 
    stringBuffer.append(TEXT_118);
     } 
    stringBuffer.append(TEXT_167);
    stringBuffer.append(tableName );
    stringBuffer.append(TEXT_168);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_169);
     if(isInnerJoin) {//inner join,not find and should reject data(not Document)
    stringBuffer.append(TEXT_146);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_170);
    stringBuffer.append( rejectedDocInnerJoin );
    stringBuffer.append(TEXT_171);
     } else {//left outter join should keep main table data,not reject anytime.
    stringBuffer.append(TEXT_146);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_172);
     } 
    stringBuffer.append(TEXT_173);
    if(isMultipleResult) {
    stringBuffer.append(TEXT_174);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_171);
    }
    stringBuffer.append(TEXT_175);
    if(isMultipleResult) {
    stringBuffer.append(TEXT_176);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_177);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_178);
     } 
    stringBuffer.append(TEXT_179);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_180);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_182);
     if(!isMultipleResult) {
    stringBuffer.append(TEXT_183);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_184);
    if(isCacheOrReloadLookupMode) {
    stringBuffer.append(TEXT_185);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_186);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_187);
     for (Object key : outNodesXPath.keySet()) { 
    stringBuffer.append(TEXT_188);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_122);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_189);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_190);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_191);
     } 
    stringBuffer.append(TEXT_192);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_193);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_194);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_190);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_195);
     } 
    stringBuffer.append(TEXT_196);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_197);
     if(findFromDocumentType) {
    stringBuffer.append(TEXT_198);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_199);
     } 
    stringBuffer.append(TEXT_200);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_201);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_202);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_203);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_204);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_205);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_206);
     if(findFromDocumentType || hasOutputFromLookupDoc) {
    stringBuffer.append(TEXT_207);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_208);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(documentColumn);
    stringBuffer.append(TEXT_209);
     if("FIRST_MATCH".equals(matchingMode)) {
    stringBuffer.append(TEXT_210);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_211);
     } 
    stringBuffer.append(TEXT_212);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_213);
    
								for (Object key : outNodesXPath.keySet()) { 
									Object val = outNodesXPath.get(key);
								
    stringBuffer.append(TEXT_214);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(val);
    stringBuffer.append(TEXT_215);
    
								}
								
    stringBuffer.append(TEXT_216);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_217);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(documentColumn);
    stringBuffer.append(TEXT_218);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_219);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_220);
    stringBuffer.append(loopXPath);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(isLoopOptional );
    stringBuffer.append(TEXT_221);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_222);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_223);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_224);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_225);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_226);
    stringBuffer.append(matchingMode);
    stringBuffer.append(TEXT_227);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_228);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_229);
     if(findFromDocumentType) { 
    stringBuffer.append(TEXT_230);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_231);
     } 
    stringBuffer.append(TEXT_232);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_233);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_234);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_235);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_236);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_238);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_239);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_240);
    if(isCacheOrReloadLookupMode) {
    stringBuffer.append(TEXT_241);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_242);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_239);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_240);
     } 
    stringBuffer.append(TEXT_243);
     if("FIRST_MATCH".equals(matchingMode)) {
    stringBuffer.append(TEXT_244);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_231);
     } 
    stringBuffer.append(TEXT_245);
     } 
    stringBuffer.append(TEXT_146);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_208);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_246);
    if(isCacheOrReloadLookupMode) {
    stringBuffer.append(TEXT_247);
    if(findFromDocumentType) {
    stringBuffer.append(TEXT_248);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_249);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_250);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_251);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_252);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_253);
     } else { 
    stringBuffer.append(TEXT_254);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_255);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_251);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_252);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_256);
     } 
    stringBuffer.append(TEXT_146);
     } 
    stringBuffer.append(TEXT_257);
     if("FIRST_MATCH".equals(matchingMode) && (findFromDocumentType || hasOutputFromLookupDoc)) {
    stringBuffer.append(TEXT_258);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_259);
     } 
    stringBuffer.append(TEXT_260);
     if(isInnerJoin && findFromDocumentType) {
    stringBuffer.append(TEXT_261);
    stringBuffer.append( rejectedDocInnerJoin );
    stringBuffer.append(TEXT_262);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_263);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_264);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_22);
    stringBuffer.append( rejectedDocInnerJoin );
    stringBuffer.append(TEXT_265);
     } 
    stringBuffer.append(TEXT_266);
     if(!isInnerJoin && findFromDocumentType) { 
    stringBuffer.append(TEXT_267);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_263);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_268);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_269);
     } 
    stringBuffer.append(TEXT_266);
     if(hasOutputFromLookupDoc && !findFromDocumentType) {//for the case : not lookup doc,but doc output exists
    stringBuffer.append(TEXT_270);
     } 
    stringBuffer.append(TEXT_271);
     if(hasExpressionFilter) {
    stringBuffer.append(TEXT_272);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_273);
    stringBuffer.append(filtersConditionsFormatted );
    stringBuffer.append(TEXT_274);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_275);
    for (String key : outNodesXPath.keySet()) {
    stringBuffer.append(TEXT_276);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_122);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_277);
    }
    stringBuffer.append(TEXT_278);
     if(isInnerJoin) {
    stringBuffer.append(TEXT_164);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_279);
     } 
    stringBuffer.append(TEXT_280);
    
				  			}
				  		} 
				  		
    stringBuffer.append(TEXT_281);
    if(isMultipleResult) {//TD114
    stringBuffer.append(TEXT_282);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_283);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_284);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_285);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_286);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_287);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_288);
    if(isCacheOrReloadLookupMode) {
    stringBuffer.append(TEXT_185);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_186);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_289);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_290);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_291);
     } 
    stringBuffer.append(TEXT_196);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_288);
     if(findFromDocumentType) {
    stringBuffer.append(TEXT_198);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_199);
     } 
    stringBuffer.append(TEXT_292);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_293);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_202);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_203);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_204);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_294);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_206);
     if(findFromDocumentType || hasOutputFromLookupDoc || isDocumentAllRows) {//TA110
    stringBuffer.append(TEXT_295);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_208);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(documentColumn);
    stringBuffer.append(TEXT_296);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_297);
    
									for (Object key : outNodesXPath.keySet()) { 
										Object val = outNodesXPath.get(key);
									
    stringBuffer.append(TEXT_298);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(val);
    stringBuffer.append(TEXT_299);
    
									}
									
    stringBuffer.append(TEXT_216);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_217);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(documentColumn);
    stringBuffer.append(TEXT_218);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_219);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_220);
    stringBuffer.append(loopXPath);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(isLoopOptional );
    stringBuffer.append(TEXT_221);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_222);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_223);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_224);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_225);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_226);
    stringBuffer.append(matchingMode);
    stringBuffer.append(TEXT_227);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_228);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_300);
     if(findFromDocumentType) { 
    stringBuffer.append(TEXT_230);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_231);
     } 
    stringBuffer.append(TEXT_232);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_233);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_301);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_302);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_303);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_304);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_305);
    if(isCacheOrReloadLookupMode) {
    stringBuffer.append(TEXT_306);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_235);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_307);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_242);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_239);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_308);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_302);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_250);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_251);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_252);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_305);
     } 
    stringBuffer.append(TEXT_309);
     }//TA110 
    stringBuffer.append(TEXT_146);
    if(!findFromDocumentType && !isDocumentAllRows){
    stringBuffer.append(TEXT_310);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_208);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_311);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_312);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_313);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_314);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_145);
    }
    stringBuffer.append(TEXT_315);
     if(findFromDocumentType && !isInnerJoin) {
    stringBuffer.append(TEXT_316);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_262);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_263);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_317);
     } 
    stringBuffer.append(TEXT_318);
     if(findFromDocumentType && isInnerJoin) {
    stringBuffer.append(TEXT_129);
    stringBuffer.append( rejectedDocInnerJoin );
    stringBuffer.append(TEXT_262);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_263);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_317);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_22);
    stringBuffer.append( rejectedDocInnerJoin );
    stringBuffer.append(TEXT_319);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_22);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_320);
     } 
    stringBuffer.append(TEXT_321);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_322);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_323);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_324);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_325);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_326);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_327);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_328);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_329);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_330);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_331);
     for (Object key : outNodesXPath.keySet()) { 
    stringBuffer.append(TEXT_332);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_122);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_333);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_190);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_334);
     } 
    stringBuffer.append(TEXT_335);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_193);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_336);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_190);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_337);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_338);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_339);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_340);
     if(hasExpressionFilter) {
    stringBuffer.append(TEXT_341);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_342);
    stringBuffer.append(filtersConditionsFormatted );
    stringBuffer.append(TEXT_343);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_344);
    for (String key : outNodesXPath.keySet()) {
    stringBuffer.append(TEXT_345);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_122);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_346);
    }
    stringBuffer.append(TEXT_347);
     if(isInnerJoin) {
    stringBuffer.append(TEXT_146);
    stringBuffer.append( rejectedInnerJoin );
    stringBuffer.append(TEXT_348);
     } 
    stringBuffer.append(TEXT_349);
     } 
    stringBuffer.append(TEXT_350);
    stringBuffer.append( tableName );
    stringBuffer.append(TEXT_351);
     
				  			if(isVirtual) {
				  				closeWhileCountForMultiResult++;
				  			} else {
				  				gm.addBlocksCodeToClose(new BlockCode("close loop of lookup '" + tableName +"' // G_TM_M_002"));
				  			}
				  		}//TD114 
				  		
    stringBuffer.append(TEXT_352);
    				
					}
					
    stringBuffer.append(TEXT_353);
    
				} //TD129 end of expression key checking
			}//TD130
		}//TD131
	} else {
		isXpathFromLookup = false;
	}//TD132
	
    
	} // end for lookup tables

// /////////////////////////////////////////////////////////////////////////////////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////////////
// VARIABLES
// 

    stringBuffer.append(TEXT_408);
    

for (VarTable varsTable : varsTables) {
	EList<VarNode> varsTableEntries = varsTable.getNodes();
	if (varsTableEntries == null) {
		continue;
	}
	String varsTableName = varsTable.getName();
	String instanceVarName = varsTableName + "__" + cid;
	String className = instanceVarName + "__Struct";

	
    stringBuffer.append( cr + className + " " + varsTableName + " = " + instanceVarName + ";" );
    
	
	for (VarNode varsTableEntry : varsTableEntries) {
		String varsColumnName = varsTableEntry.getName();
		String varExpression = varsTableEntry.getExpression();
		if (varExpression == null || varExpression.trim().length() == 0) {
			varExpression = JavaTypesManager.getDefaultValueFromJavaIdType(varsTableEntry.getType(),
					varsTableEntry.isNullable());
		}
		
		String resultExpression = "";
		if(matchXmlTreeExpr != null)
			resultExpression = matchXmlTreeExpr.generateExprCode(varExpression);
		if(resultExpression!=null && !("").equals(resultExpression)){

    stringBuffer.append(TEXT_355);
    stringBuffer.append( cr + gm.getGeneratedCodeTableColumnVariable(cid, varsTableName, varsColumnName, false) + " = " + resultExpression
				+ ";" );
    
		}
	}
}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////
        // /////////////////////////////////////////////////////////////////////////////////////////////////////

        // /////////////////////////////////////////////////////////////////////////////////////////////////////
        // /////////////////////////////////////////////////////////////////////////////////////////////////////
        // OUTPUTS
        // 
        

    stringBuffer.append(TEXT_409);
    

		StringBuilder sb = new StringBuilder();
		
		boolean lastValueReject = false;
        boolean oneFilterForNotRejectTable = false;
        boolean allNotRejectTablesHaveFilter = true;
        boolean atLeastOneReject = false;
        boolean atLeastOneRejectInnerJoin = false;
        boolean closeTestInnerJoinConditionsBracket = false;

        for (IConnection outputConnection : outputConnections) {
            nameToOutputConnection.put(outputConnection.getName(), outputConnection);
        }

        int lstSizeOutputs = outputTablesSortedByReject.size();
        // ///////////////////////////////////////////////////////////////////
        // init of allNotRejectTablesHaveFilter and atLeastOneReject
        String lastValidOutputTableName = null;
		
		for (int i = 0; i < lstSizeOutputs; i++) {
            OutputXmlTree outputTable = (OutputXmlTree) outputTablesSortedByReject.get(i);

            String outputTableName = outputTable.getName();

            if (outputTable.isRejectInnerJoin()) {
                atLeastOneRejectInnerJoin = true;
            }
            EList<OutputTreeNode> columnEntries = outputTable.getNodes();
            boolean hasFilter = outputTable.isActivateExpressionFilter() && outputTable.getExpressionFilter() != null && !("").equals(outputTable.getExpressionFilter().trim());
            if (columnEntries != null && columnEntries.size() > 0) {
                if (!hasFilter && !(outputTable.isReject() || outputTable.isRejectInnerJoin())) {
                    if(!outputTable.isErrorReject()){
                    	allNotRejectTablesHaveFilter = false;
                    }
                }
                if (outputTable.isReject()) {
                    atLeastOneReject = true;
                }
            }
			IConnection outputConnection = nameToOutputConnection.get(outputTableName);            
			if (outputConnection != null) {
				sb.append(cr + outputTableName + " = null;");
			}
			if(checkingSyntax 
			|| !checkingSyntax && outputConnection != null) {
				lastValidOutputTableName = outputTableName;
			}
			

        }
        // ///////////////////////////////////////////////////////////////////
		sb.append(cr);

        if (allNotRejectTablesHaveFilter && atLeastOneReject) {
            // write rejected = false;
            sb.append(cr + "boolean " + rejected + " = true;");
        }

        // write conditions for inner join reject
        if (lastValidOutputTableName != null && hasValidLookupTables && lstSizeOutputs > 0 && atLeastOneInputTableWithInnerJoin) {
            sb.append(cr + "if(");
            sb.append("!" + rejectedInnerJoin);
            sb.append(" ) {");
            closeTestInnerJoinConditionsBracket = true;
        }

        // ///////////////////////////////////////////////////////////////////
        // run through output tables list for generating intilization of outputs arrays
        int dummyVarCounter = 0;
		//sb.append(cr + "boolean docAlreadyInstanciate = false;" + cr);
        for (int indexCurrentTable = 0; indexCurrentTable < lstSizeOutputs; indexCurrentTable++) {
            OutputXmlTree outputTable = (OutputXmlTree) outputTablesSortedByReject.get(indexCurrentTable);
            EList<OutputTreeNode> outputTableEntries = outputTable.getNodes();
            String outputTableName = outputTable.getName();
			isAllInOne = outputTable.isAllInOne();
            
            boolean connectionExists = true;
            IConnection outputConnection =null;
            outputConnection = nameToOutputConnection.get(outputTableName);
            
            HashedMetadataTable hashedMetadataTable = null;
            if (outputTableEntries == null || outputConnection == null) {
                connectionExists = false;
            } else {
            	hashedMetadataTable = new HashedMetadataTable(outputConnection.getMetadataTable());
            }

            boolean currentIsReject = outputTable.isReject();
            boolean currentIsRejectInnerJoin = outputTable.isRejectInnerJoin();

            boolean hasExpressionFilter = outputTable.isActivateExpressionFilter() && outputTable.getExpressionFilter() != null && !("").equals(outputTable.getExpressionFilter().trim());
            boolean rejectValueHasJustChanged = lastValueReject != (currentIsReject || currentIsRejectInnerJoin);
            oneFilterForNotRejectTable = !(currentIsReject || currentIsRejectInnerJoin) && hasExpressionFilter;

            if (rejectValueHasJustChanged) {
                if (closeTestInnerJoinConditionsBracket) {
                    sb.append(cr + "} // closing inner join bracket (1)");
                    if (atLeastOneReject && allNotRejectTablesHaveFilter) {
                        sb.append(cr + " else {");
                        sb.append(cr + rejected + " = false;");
                        sb.append(cr + "} // closing else inner join bracket (1)");
                    }
                    closeTestInnerJoinConditionsBracket = false;
                }
            }

			boolean hasAggregateColumn = false;

            // No connection matching and no checking errors
            if (connectionExists || checkingSyntax) {//TD12580
	            if (rejectValueHasJustChanged) {
	                sb.append(cr + "// ###### START REJECTS ##### ");
	            }

	            // write filters conditions and code to execute
	            if (!currentIsReject && !currentIsRejectInnerJoin || rejectValueHasJustChanged
	                    && oneFilterForNotRejectTable || currentIsReject && allNotRejectTablesHaveFilter
	                    || currentIsRejectInnerJoin && atLeastOneInputTableWithInnerJoin || checkingSyntax) {

	                boolean closeFilterOrRejectBracket = false;
	                if (currentIsReject || currentIsRejectInnerJoin) {
	                    sb.append(cr + cr + "// # Output reject table : '" + outputTableName + "'");
	                } else {
	                    sb.append(cr + cr + "// # Output table : '" + outputTableName + "'");
	                }
	                if (hasExpressionFilter || currentIsReject || currentIsRejectInnerJoin && atLeastOneInputTableWithInnerJoin) {
	                    sb.append(cr + "// # Filter conditions ");
						String ifConditions = "if( ";
	                    String rejectedTests = null;
	                    if (allNotRejectTablesHaveFilter && atLeastOneReject && currentIsReject && currentIsRejectInnerJoin
	                            && atLeastOneInputTableWithInnerJoin) {
	                        rejectedTests = rejected + " || " + rejectedInnerJoin;
	                        if (hasExpressionFilter) {
	                            rejectedTests = "(" + rejectedTests + ")";
	                        }
	                    } else if (allNotRejectTablesHaveFilter && atLeastOneReject && currentIsReject) {
	                        rejectedTests = rejected;
	                    } else if (currentIsRejectInnerJoin && atLeastOneInputTableWithInnerJoin) {
	                        rejectedTests = rejectedInnerJoin;
	                    }
	                    String filtersConditions = null;
	                    filtersConditions = outputTable.getExpressionFilter();
						if(filtersConditions != null && !("".equals(filtersConditions.trim())) && (filtersConditions.trim().length()>0)) {
							String filtersConditionsFormatted = "";
							if(matchXmlTreeExpr != null)
								filtersConditionsFormatted = matchXmlTreeExpr.generateExprCode(filtersConditions);
							if (rejectedTests == null) {
								ifConditions += cr + cr + filtersConditionsFormatted  + cr + cr;
							} else {
								ifConditions += rejectedTests + " && (" + cr + cr + filtersConditionsFormatted + cr + cr +")";
							}
						} else {
							if (rejectedTests != null) {
								ifConditions += rejectedTests;
							}
						}
	                    
	                    ifConditions += " ) {";
	
	                    sb.append(cr).append(ifConditions);
	
	                    closeFilterOrRejectBracket = true;
	                    if (allNotRejectTablesHaveFilter && !(currentIsReject || currentIsRejectInnerJoin)
	                            && atLeastOneReject) {
	                        sb.append(cr + rejected + " = false;");
	                    }
	                }

	                if (outputTableEntries != null && (!currentIsReject && !currentIsRejectInnerJoin || currentIsReject || currentIsRejectInnerJoin
	                        && atLeastOneInputTableWithInnerJoin || checkingSyntax)) {
						if(isLog4jEnabled){
							sb.append(cr + "count_" + outputTableName + "_" + cid + "++;" + cr);
						}
						
						if(!utilXML.tableHasADocument(outputTableEntries)) {
							sb.append(cr + cr + outputTableName + "_tmp = new " + outputTableName + "Struct();");
						}
						boolean hasDocument = false;
						//cache flat column expressions and fix TDI-17811
						List<String> flatExpressions = new ArrayList<String>();
						Map<String,String> nameToAssignationVar = new HashMap<String,String>();
						Map<String,String> nameToResultExpression = new HashMap<String,String>();
						for (OutputTreeNode outputTableEntry : outputTableEntries) {//block 12580
							String outputColumnName = outputTableEntry.getName();
							IMetadataColumn metadataColumnFromConn = null;
	                        if(connectionExists) {
		                        metadataColumnFromConn = hashedMetadataTable.getColumn(outputColumnName);
		                    }
		                    String outputExpression = outputTableEntry.getExpression();
		                    
		                    String resultExpression = "";
		                    if(matchXmlTreeExpr != null)
								resultExpression = matchXmlTreeExpr.generateExprCode(outputExpression);
	                        if (resultExpression == null || resultExpression.trim().length() == 0) {
	                        	if(metadataColumnFromConn == null) {
		                            resultExpression = JavaTypesManager.getDefaultValueFromJavaIdType(outputTableEntry
	    	                                .getType(), outputTableEntry.isNullable());
	    	                    } else {
		                            resultExpression = JavaTypesManager.getDefaultValueFromJavaIdType(metadataColumnFromConn
	    	                                .getTalendType(), metadataColumnFromConn.isNullable());
	    	                    }
	                        } else {
	                        
	                        	if(metadataColumnFromConn == null) {
									continue;
								}	                        
	                        
	                        }
	                        
	                        String assignationVar = null;
	                        if (connectionExists) {                       
	                        	assignationVar = gm.getGeneratedCodeTableColumnVariable(uniqueNameComponent, outputTableName + "_tmp",outputColumnName, false);
	                        } else {
	                        	if(metadataColumnFromConn == null) {
		                            assignationVar = JavaTypesManager.getTypeToGenerate(outputTableEntry.getType(),
	                                    outputTableEntry.isNullable())
	                                    + " dummyVar" + (dummyVarCounter++);
	                            } else {
		                            assignationVar = JavaTypesManager.getTypeToGenerate(metadataColumnFromConn
	    	                                .getTalendType(), metadataColumnFromConn.isNullable())
	                                    + " dummyVar" + (dummyVarCounter++);
	                            }
	                        }
    	                    String expression = assignationVar + " = " + resultExpression + ";";
    	                    
    	                    nameToAssignationVar.put(outputColumnName,assignationVar);
    	                    nameToResultExpression.put(outputColumnName,resultExpression);
    	                    
    	                    if(!("id_Document").equals(outputTableEntry.getType())){
								flatExpressions.add(expression);
							}
    	                    
						}//block 12580
						
	                    for (OutputTreeNode outputTableEntry : outputTableEntries) {
	                        String outputColumnName = outputTableEntry.getName();
	                       	
	                       	String assignationVar = nameToAssignationVar.get(outputColumnName);
	                       	String resultExpression = nameToResultExpression.get(outputColumnName);
	                       	
	                       	if(resultExpression == null) {
	                       		continue;
	                       	}
	                       	
    	                    String expression = assignationVar + " = " + resultExpression + ";";
	
							if(("id_Document").equals(outputTableEntry.getType())){
								hasDocument = true;
								hasDocumentGlobal = true;
								JavaType javaType = JavaTypesManager.getJavaTypeFromId(outputTableEntry.getType());
								String typeToGenerate = javaType.getNullableClass().getCanonicalName();
								
								//get the document aggregation columns
								List<String> xpaths = new ArrayList<String>();
								List<String> exprCodes = new ArrayList<String>();
								
								MatchXmlTreeExpr exprUtil = new MatchXmlTreeExpr(cid);
								List<TreeNode> allLeaf = new ArrayList<TreeNode>();
								treeUtil.getAllLeaf(outputTableEntry,allLeaf);
								for(TreeNode leaf : allLeaf) {
									OutputTreeNode outputLeaf = (OutputTreeNode)leaf;
									if(outputLeaf.isAggregate()) {
										String xpath = outputLeaf.getXpath();
										String expressionAgg = outputLeaf.getExpression();
										String exprCode = exprUtil.generateExprCode(expressionAgg);
										
										hasAggregateColumn = true;
										
										xpaths.add(xpath);
										exprCodes.add(exprCode);
									}
								}
								
								if(hasAggregateColumn || isAllInOne) { //TD256
									int size = xpaths.size();
									if(xpaths.size() >0) {
										sb.append(cr + cr + "if(");
										for(int i=0; i<size; i++) {
											String xpath = xpaths.get(i);
											String exprCode = exprCodes.get(i);
											sb.append("(");
											sb.append(exprCode);
											sb.append(")==null ||");
											sb.append("!" + exprCode + ".equals(aggregateCacheMap_"+cid+".get(\""+xpath+"\"))");
											if(i != size-1) {
												sb.append(" || ");
											}
										}
										sb.append(") {" + cr);
									} else {
										sb.append(cr + cr + "if(");
										sb.append("!docAlreadyInstanciate_" + outputTableName);
										sb.append(") {" + cr);
										sb.append("docAlreadyInstanciate_" + outputTableName + " = true;");
									}
									sb.append("gen_Doc_" + outputTableName + "_" + cid + " = new GenerateDocument_" + outputTableName + "();" + cr);
									sb.append("//init one new out struct for cache the result." + cr);
									sb.append(outputTableName + "_aggregate = new " + outputTableName + "Struct();" + cr);
									sb.append(outputTableName + "_aggregate." + outputTableEntry.getName() + " = new " + typeToGenerate + "();" + cr);
									sb.append(outputTableName + "_aggregate." + outputTableEntry.getName() + ".setDocument(gen_Doc_" + outputTableName + "_" + cid + ".getDocument());");
									sb.append(cr + cr);
									sb.append("//construct the resultset" + cr);
									sb.append("allOutsForAggregate_" + uniqueNameComponent + ".add(" + outputTableName + "_aggregate);" + cr);
									for(int i=0; i<size; i++) {
										String xpath = xpaths.get(i);
										String exprCode = exprCodes.get(i);
										sb.append("//store the previous value" + cr);
										sb.append("aggregateCacheMap_" + cid + ".put(\"" + xpath + "\", " + exprCode + ");" + cr);
									}
									sb.append("}" + cr);
								} else if(!isAllInOne) { // If we have a document in output table entry, with allInOne = false and no aggregation
									sb.append(cr + cr + "gen_Doc_" + outputTableName + "_" + cid + " = new GenerateDocument_" + outputTableName + "();" + cr);
									sb.append(outputTableName + "_tmp = new " + outputTableName + "Struct();");
									//fix TDI-17811
									for(String exp : flatExpressions) {
										sb.append(cr).append(exp);
									}
									sb.append(cr + cr);
								} //TD256
								
								sb.append(cr + cr +"gen_Doc_"+outputTableName+"_"+cid+".generateElements(");
								if(hasDocumentInMainInputTable || hasDocumentInAnyLookupTable) {
									sb.append("treeNodeAPI_"+cid+",");
								}
								sb.append(rejectedDocInnerJoin);
								for (InputXmlTree inputTable: inputTables) {
									sb.append(","+inputTable.getName());
								}
								for (VarTable var : varsTables) {
									sb.append(","+var.getName());
								}

								sb.append(");" + cr + cr);
								
								/*
								Generate the following part if:
								1 - Virtual generation = 1 document in main input table OR 1 document with aggregate in an output table OR 1 table with "All In One" activated.
								2 - a table in output with a document (without aggregate column and without the "All in one" option.
								*/
								
								if(!hasAggregateColumn && !isAllInOne) {
									sb.append("if("+assignationVar + " == null){" + cr);
									sb.append(assignationVar + " = new " + typeToGenerate + "();" + cr);
									sb.append(assignationVar + ".setDocument(gen_Doc_"+outputTableName+"_"+cid+".getDocument());" + cr);
									if(!hasAggregateColumn && !isAllInOne) {
										sb.append("//construct the resultset when there is no aggregate column for the out table" + cr);
										sb.append("allOutsForAggregate_" + uniqueNameComponent + ".add(" + outputTableName + "_tmp);");
									}
								
									sb.append("}" + cr);
								}
							} else {
								sb.append(cr).append(expression);
								if(hasAggregateColumn) {
								sb.append("//set the flat column for aggregate" + cr);
									sb.append(outputTableName + "_aggregate." + outputTableEntry.getName() + " = " + resultExpression + ";" + cr);
								}
							}
	                    } // for entries
	
	                    if (connectionExists) {
							// If an output table doesn't contain a Document, and if there is an aggregate element in the output tables
							boolean flat = true;
							for(OutputTreeNode table_ent : outputTable.getNodes()) {
								if(("id_Document").equals(table_ent.getType())){
									flat = false;
									break;
								}
							}
							if(flat) {
								sb.append("allOutsForAggregate_" + uniqueNameComponent + ".add(" + outputTableName + "_tmp);" + cr);
							}
							
							if(isLog4jEnabled){
								sb.append(cr + "log.debug(\"" + uniqueNameComponent + " - Outputting the record \" + count_" + outputTableName + "_" + cid + " + \" of the output table '" + outputTableName + "'.\");" + cr);
							}
	                    }
	                }
					if (closeFilterOrRejectBracket) {
						sb.append(cr + "} // closing filter/reject");
					}
				}
				lastValueReject = currentIsReject || currentIsRejectInnerJoin;
			}//TD12580

			if (hasAggregateColumn || isAllInOne) {
				boolean checkNull = false;
				boolean first = true;
				
				for (OutputTreeNode outputTableEntry : outputTableEntries) {
					if(!("id_Document".equals(outputTableEntry.getType()))) {
						if(first) {
							first = false;
							checkNull = true;
							sb.append(cr).append(cr).append("if(").append(outputTableName).append("_aggregate").append("!=null){");
						}
						sb.append(cr + cr + outputTableName + "_aggregate." + outputTableEntry.getName() + " = " + outputTableName + "_tmp." + outputTableEntry.getName() + ";");
					}
				}
				
				if(checkNull) {
					sb.append(cr).append("}");
				}
			}
			
			boolean isLastTable = indexCurrentTable == lstSizeOutputs - 1;
			if (closeTestInnerJoinConditionsBracket 
				&& (isLastTable || !checkingSyntax && (outputTableName.equals(lastValidOutputTableName) || lastValidOutputTableName == null))) {
				sb.append(cr + "}  // closing inner join bracket (2)");
				closeTestInnerJoinConditionsBracket = false;
			}

		} // for output tables
			
		sb.append(cr + "// ###############################");

		
		sb.append(cr);
		sb.append(cr + "} // end of Var scope");



		sb.append(cr + cr + rejectedInnerJoin + " = false;");
		
		sb.append(cr);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    stringBuffer.append(TEXT_7);
    stringBuffer.append( sb.toString());
    stringBuffer.append(TEXT_7);
    
	while((closeWhileCountForMultiResult--) > 0) {

    stringBuffer.append(TEXT_410);
    
	}
	
	if((!isPlainNode && !hasDocumentGlobal) || (hasDocumentGlobal && !isPlainNode)) {
	
    stringBuffer.append(TEXT_411);
    
		//gm.addBlocksCodeToClose(new BlockCode("G_TXM_M_001 close"));
	}

    
		}
	}

    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
    class LoopHelper {
	
		//all used input loops(if the input loop is no used in any output table,we treat as no loop node)
		List<TreeNode> inputLoopsInAllOutputTables = new ArrayList<TreeNode>();
		
		//all output tables
		List<OutputXmlTree> outputTables;
		
		//all input tables
		List<InputXmlTree> inputTables;
		
		TreeNode inputRootOfMainInputTableDocument;
		
		public LoopHelper(TreeNode inputRoot,List<OutputXmlTree> outputTables,List<InputXmlTree> inputTables) {
			this.inputRootOfMainInputTableDocument = inputRoot;
			this.outputTables = outputTables;
			this.inputTables = inputTables;
			
			for(OutputXmlTree outputTable : outputTables) {
				List<InputLoopNodesTable> inputLoopNodesTables = getValidInputLoopNodesTables(outputTable.getInputLoopNodesTables());
				for(InputLoopNodesTable inputLoopNodesTable : inputLoopNodesTables) {
					List<TreeNode> inputLoopNodes = inputLoopNodesTable.getInputloopnodes();
					for(TreeNode node : inputLoopNodes) {
						if(!inputLoopsInAllOutputTables.contains(node)) {
							inputLoopsInAllOutputTables.add(node);
						}
					}
					
				}
			}
		}
		
		//all input loops used by current output table
		List<TreeNode> inputLoopsInCurrentOutputTable;
		
		//for output mapping belong to unique input loop
		public Map<TreeNode,TreeNode> outputNodeToLoopNode;
		
		//need a var for lookup output mapping belong to input loop
		
		boolean parallelLoopExistInCurrentOutputTable;
		
		boolean nestedLoopExistInCurrentOutputTable;
		
		public List<TreeNode> getInputLoopsInCurrentOutputTable() {
			return inputLoopsInCurrentOutputTable;
		}
		
		//set current output table and init
		public void initForOneOutputTable(OutputXmlTree currentOutputTable) {
			inputLoopsInCurrentOutputTable = new ArrayList<TreeNode>();
			parallelLoopExistInCurrentOutputTable = false;
			nestedLoopExistInCurrentOutputTable = false;
			outputNodeToLoopNode = new HashMap<TreeNode,TreeNode>();
			
			List<InputLoopNodesTable> inputLoopNodesTables = getInputLoopNodesTables(currentOutputTable);
			if(inputLoopNodesTables.size() > 1) {
				parallelLoopExistInCurrentOutputTable = true;
			}
			for(InputLoopNodesTable inputLoopNodesTable : inputLoopNodesTables) {
				List<TreeNode> inputLoopNodes = inputLoopNodesTable.getInputloopnodes();
				if(inputLoopNodes.size() > 1) {
					nestedLoopExistInCurrentOutputTable = true;
				}
				for(TreeNode inputLoopNode : inputLoopNodes) {
					if(!inputLoopsInCurrentOutputTable.contains(inputLoopNode)) {
						inputLoopsInCurrentOutputTable.add(inputLoopNode);
					}
				}
			}
			if(parallelLoopExistInCurrentOutputTable || nestedLoopExistInCurrentOutputTable) {
				setLoopMapping();
			}
		}
		
		private List<InputLoopNodesTable> getInputLoopNodesTables(OutputXmlTree currentOutputTable) {
			List<InputLoopNodesTable> result = new ArrayList<InputLoopNodesTable>();
			
			List<OutputTreeNode> loopNodes = new ArrayList<OutputTreeNode>();
			boolean docExist = false;
			for(OutputTreeNode currentNode : currentOutputTable.getNodes()) {
				if("id_Document".equals(currentNode.getType())) {
					getAllLoopNodes(currentNode,loopNodes);
					docExist = true;
				}
			}
			
			if(docExist) {
				for(OutputTreeNode loop : loopNodes) {
					if(loop.getInputLoopNodesTable()!=null) {
						result.add(loop.getInputLoopNodesTable());
					}
				}
			} else {//only flat column exist
				result.addAll(getValidInputLoopNodesTables(currentOutputTable.getInputLoopNodesTables()));
			}
			
			return result;
		}
		
		List<TreeNode> currentInputLoopNodes;
		//filter for lookup table
		List<TreeNode> noUsedInputLoopsForCurrentOutputTableLoop;
		List<TreeNode> noUsedLookupNodesForAllLookupTableToCurrentOutputTableLoop;
		//set current output loop of current output table  and init
		public void initForOneOutputTableLoop(List<TreeNode> inputLoopNodes) {
			noUsedInputLoopsForCurrentOutputTableLoop = new ArrayList<TreeNode>();
			noUsedLookupNodesForAllLookupTableToCurrentOutputTableLoop = new ArrayList<TreeNode>();
			currentInputLoopNodes = inputLoopNodes;
			for(TreeNode inputLoopNode : inputLoopsInAllOutputTables) {
				if(!currentInputLoopNodes.contains(inputLoopNode)) {
					noUsedInputLoopsForCurrentOutputTableLoop.add(inputLoopNode);
					insideOfLoop(inputLoopNode);
				}
			}
		}
		
		private void insideOfLoop(TreeNode loopNode) {
			if(asOutput(loopNode)) {
				noUsedLookupNodesForAllLookupTableToCurrentOutputTableLoop.add(loopNode);
			}
			for(TreeNode childNode : loopNode.getChildren()) {
				insideOfLoop(childNode);
			}
		}
	
		/**
		 * used when parallel loop exist in current output table or  parallel relation exist between all output tables
		 * @param nodeOfLookupTable
		 * @return
		 */
		public boolean validLookupKey(TreeNode nodeOfLookupTable) {
			List<LookupConnection> lookupIncomings = nodeOfLookupTable.getLookupIncomingConnections();
			if(lookupIncomings.size()>0) {
				LookupConnection lookupIncoming = lookupIncomings.get(0);
				TreeNode source = (TreeNode)lookupIncoming.getSource();
				return !noUsedLookupNodesForAllLookupTableToCurrentOutputTableLoop.contains(source);
			}
			return true;
		}
		
		/**
		 * for the output mapping
		 */
		private void setLoopMapping() {
			for(TreeNode inputLoop : inputLoopsInCurrentOutputTable) {
				initInsideOfLoop(inputLoop,inputLoop);
			}
			initOutsideOfLoops(this.inputRootOfMainInputTableDocument);
		}
		
		private void initInsideOfLoop(TreeNode loopNode,TreeNode node) {
			if(asOutput(node)) {
				outputNodeToLoopNode.put(node, loopNode);
			}
			for(TreeNode childNode : node.getChildren()) {
				initInsideOfLoop(loopNode,childNode);
			}
		}
		
		private void initOutsideOfLoops(TreeNode node) {
			if(node.isLoop()) {
				return;
			}
			if(asOutput(node)) {
				outputNodeToLoopNode.put(node,null);
			}
			for(TreeNode childNode : node.getChildren()) {
				initOutsideOfLoops(childNode);
			}
		}
		
		//for output mapping 
		public boolean belongToCurrentLoop(TreeNode inputLoopNode,String xpath,boolean isMainInputLoopInNestLoop) {
			if(parallelLoopExistInCurrentOutputTable || nestedLoopExistInCurrentOutputTable) {
				for(Map.Entry<TreeNode, TreeNode> entry : outputNodeToLoopNode.entrySet()) {
					TreeNode outputNode = entry.getKey();
					TreeNode loopNode = entry.getValue();
					if(xpath.equals(outputNode.getXpath())) {
						if(isMainInputLoopInNestLoop) {
							if(loopNode == null) {//outside of any input loop
								return true;
							}
							return loopNode == inputLoopNode;
						} else {
							if(loopNode == null) {
								return false;
							}
							return loopNode == inputLoopNode;
						}
					}
				}
			}
			return true;
		}
		
		private boolean asOutput(TreeNode currentNode) {
			return currentNode.getOutgoingConnections().size()>0 || currentNode.getLookupOutgoingConnections().size()>0 || currentNode.getFilterOutGoingConnections().size()>0;
		}
		
		private List<InputLoopNodesTable> getValidInputLoopNodesTables(List<InputLoopNodesTable> inputLoopNodesTablesWithUnValid) {
	    	List<InputLoopNodesTable> inputLoopNodesTables = new ArrayList<InputLoopNodesTable>();
	    	if(inputLoopNodesTablesWithUnValid == null) return inputLoopNodesTables;
			for(InputLoopNodesTable inputLoopNodesTable : inputLoopNodesTablesWithUnValid) {
				if(inputLoopNodesTable!=null && inputLoopNodesTable.getInputloopnodes()!=null && inputLoopNodesTable.getInputloopnodes().size()>0)
					inputLoopNodesTables.add(inputLoopNodesTable);
			}
			return inputLoopNodesTables;
	    }
		
		private void getAllLoopNodes(OutputTreeNode node,List<OutputTreeNode> result) {
			if(node == null) {
				return;
			}
			if(node.isLoop()){
				result.add(node);
				return;
			}
			for(TreeNode child : node.getChildren()){
				getAllLoopNodes((OutputTreeNode)child,result);
			}
		}
		
	}
}
