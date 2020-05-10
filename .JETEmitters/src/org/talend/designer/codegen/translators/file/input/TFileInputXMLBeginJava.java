package org.talend.designer.codegen.translators.file.input;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.Map;
import java.util.List;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;

public class TFileInputXMLBeginJava
{
  protected static String nl;
  public static synchronized TFileInputXMLBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TFileInputXMLBeginJava result = new TFileInputXMLBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t\tlog.debug(\"";
  protected final String TEXT_2 = " - Retrieving records from the datasource.\");" + NL + "\t\t\t";
  protected final String TEXT_3 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_4 = " - Retrieved records count: \"+ nb_line_";
  protected final String TEXT_5 = " + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_6 = " - Retrieved records count: \"+ globalMap.get(\"";
  protected final String TEXT_7 = "_NB_LINE\") + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_8 = " - Written records count: \" + nb_line_";
  protected final String TEXT_9 = NL + "\t\t\t\tfinal StringBuffer log4jSb_";
  protected final String TEXT_10 = " = new StringBuffer();" + NL + "\t\t\t";
  protected final String TEXT_11 = " - Retrieving the record \" + (nb_line_";
  protected final String TEXT_12 = ") + \".\");" + NL + "\t\t\t";
  protected final String TEXT_13 = " - Writing the record \" + nb_line_";
  protected final String TEXT_14 = " + \" to the file.\");" + NL + "\t\t\t";
  protected final String TEXT_15 = " - Processing the record \" + nb_line_";
  protected final String TEXT_16 = " + \".\");" + NL + "\t\t\t";
  protected final String TEXT_17 = " - Processed records count: \" + nb_line_";
  protected final String TEXT_18 = NL + "\t";
  protected final String TEXT_19 = NL + "\t\t\trowHelper_";
  protected final String TEXT_20 = ".valueToConn(";
  protected final String TEXT_21 = ", ";
  protected final String TEXT_22 = " ";
  protected final String TEXT_23 = ");" + NL + "\t\t";
  protected final String TEXT_24 = ".valueToConnWithD(";
  protected final String TEXT_25 = ".connToConn(";
  protected final String TEXT_26 = ",";
  protected final String TEXT_27 = NL + "\t\t\t\tclass RowHelper_";
  protected final String TEXT_28 = "{" + NL + "\t\t\t\t";
  protected final String TEXT_29 = NL + "\t\t\t\t\tpublic void valueToConn_";
  protected final String TEXT_30 = "(";
  protected final String TEXT_31 = "Struct ";
  protected final String TEXT_32 = ") throws java.lang.Exception{" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_33 = NL + "\t\t\t\t\t}" + NL + "\t\t\t\t\t";
  protected final String TEXT_34 = NL + "\t\t\t\t\tpublic void valueToConnWithD_";
  protected final String TEXT_35 = ", routines.system.Dynamic ";
  protected final String TEXT_36 = NL + "\t\t\t\t\tpublic void connToConn_";
  protected final String TEXT_37 = NL + "\t\t\t\t\tpublic void valueToConn(";
  protected final String TEXT_38 = ") throws java.lang.Exception{" + NL + "\t\t\t\t";
  protected final String TEXT_39 = NL + "\t\t\t\t\tpublic void valueToConnWithD(";
  protected final String TEXT_40 = NL + "\t\t\t\t\t\tvalueToConn_";
  protected final String TEXT_41 = ");" + NL + "\t\t\t\t\t";
  protected final String TEXT_42 = NL + "\t\t\t\t\t\tvalueToConnWithD_";
  protected final String TEXT_43 = NL + "\t\t\t\t\t}" + NL + "\t\t\t\t";
  protected final String TEXT_44 = NL + "\t\t\t\t\tpublic void connToConn(";
  protected final String TEXT_45 = ") throws java.lang.Exception{" + NL + "\t\t\t\t\t";
  protected final String TEXT_46 = NL + "\t\t\t\t\t\tconnToConn_";
  protected final String TEXT_47 = ");" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_48 = NL + "\t\t\t\t}" + NL + "\t\t\t\tRowHelper_";
  protected final String TEXT_49 = " rowHelper_";
  protected final String TEXT_50 = "  = new RowHelper_";
  protected final String TEXT_51 = "();" + NL + "\t\t\t";
  protected final String TEXT_52 = NL;
  protected final String TEXT_53 = NL + "int nb_line_";
  protected final String TEXT_54 = " = 0;" + NL;
  protected final String TEXT_55 = NL + "class IgnoreDTDEntityResolver_";
  protected final String TEXT_56 = " implements org.xml.sax.EntityResolver {" + NL + "" + NL + " public org.xml.sax.InputSource resolveEntity(String publicId, String systemId)" + NL + "   throws org.xml.sax.SAXException, java.io.IOException {" + NL + "        return new org.xml.sax.InputSource(new java.io.ByteArrayInputStream(\"<?xml version='1.0' encoding='UTF-8'?>\".getBytes()));" + NL + " }" + NL + "" + NL + "}";
  protected final String TEXT_57 = NL + "\tString os_";
  protected final String TEXT_58 = " = System.getProperty(\"os.name\").toLowerCase();" + NL + "\tboolean isWindows_";
  protected final String TEXT_59 = "=false;" + NL + "\tif(os_";
  protected final String TEXT_60 = ".indexOf(\"windows\") > -1 || os_";
  protected final String TEXT_61 = ".indexOf(\"nt\") > -1){" + NL + "\t\tisWindows_";
  protected final String TEXT_62 = "=true;" + NL + "\t}";
  protected final String TEXT_63 = NL + "class TalendPrefixResolver_";
  protected final String TEXT_64 = " implements org.apache.xml.utils.PrefixResolver {" + NL + "" + NL + "    private java.util.Map<String, String> map = new java.util.HashMap<String, String>();" + NL + "    " + NL + "\tprivate java.util.List<String> defualtNSPath = new java.util.ArrayList<String>();" + NL + "\t" + NL + "\tpublic java.util.List<String> getDefualtNSPath() {" + NL + "\t\treturn defualtNSPath;" + NL + "\t}" + NL + "\t" + NL + "    public String getBaseIdentifier() {" + NL + "        return null;" + NL + "    }" + NL + "" + NL + "    public String getNamespaceForPrefix(String prefix) {" + NL + "        if ((\"xml\").equals(prefix)) {" + NL + "            return org.apache.xml.utils.Constants.S_XMLNAMESPACEURI;" + NL + "        } else {" + NL + "            return map.get(prefix);" + NL + "        }" + NL + "    }" + NL + "" + NL + "    public String getNamespaceForPrefix(String prefix, org.w3c.dom.Node context) {" + NL + "        return getNamespaceForPrefix(prefix);" + NL + "    }" + NL + "" + NL + "    public boolean handlesNullPrefixes() {" + NL + "        return false;" + NL + "    }" + NL + "" + NL + "    public boolean countNSMap(org.w3c.dom.Node el) {" + NL + "    \tboolean hasDefaultPrefix = false;" + NL + "        if (el.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {" + NL + "            org.w3c.dom.NamedNodeMap attris = el.getAttributes();" + NL + "            for (int i = 0; i < attris.getLength(); i++) {" + NL + "                org.w3c.dom.Node attr = attris.item(i);" + NL + "                String aname = attr.getNodeName();" + NL + "                if (aname.startsWith(\"xmlns\")) {" + NL + "                    int index = aname.indexOf(\":\");" + NL + "                    if(index > 0){" + NL + "                    \taname = aname.substring(index + 1);" + NL + "                    }else{" + NL + "                    \taname = \"pre\"+defualtNSPath.size();" + NL + "                    \thasDefaultPrefix = true;" + NL + "                    \tString path = \"\";" + NL + "                    \torg.w3c.dom.Node elTmp = el;" + NL + "                    \twhile(elTmp!=null && !(elTmp instanceof org.w3c.dom.Document)){" + NL + "\t\t\t\t\t\t\tpath = \"/\"+elTmp.getNodeName()+path;" + NL + "                    \t\telTmp = elTmp.getParentNode();" + NL + "                    \t}" + NL + "                        defualtNSPath.add(path);" + NL + "                    }" + NL + "                    map.put(aname, attr.getNodeValue());" + NL + "                }" + NL + "            }" + NL + "        }" + NL + "        org.w3c.dom.NodeList nodeList = el.getChildNodes();" + NL + "        for (int i = 0; i < nodeList.getLength(); i++) {" + NL + "            hasDefaultPrefix = hasDefaultPrefix | countNSMap(nodeList.item(i));" + NL + "        }" + NL + "        return hasDefaultPrefix;" + NL + "    }" + NL + "    " + NL + "}" + NL + "" + NL + "class XML_API_";
  protected final String TEXT_65 = "{" + NL + "" + NL + "\tString loopPath = null;" + NL + "\tboolean hasDefaultNS = false;" + NL + "\tTalendPrefixResolver_";
  protected final String TEXT_66 = " pr = null;" + NL + "\t" + NL + "\tpublic boolean isDefNull(org.w3c.dom.Node node) throws javax.xml.transform.TransformerException {" + NL + "        if (node != null && node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {" + NL + "            return ((org.apache.xerces.xs.ElementPSVI) node).getNil();" + NL + "        }" + NL + "        return false;" + NL + "    }" + NL + "" + NL + "    public boolean isMissing(org.w3c.dom.Node node) throws javax.xml.transform.TransformerException {" + NL + "        return node == null ? true : false;" + NL + "    }" + NL + "" + NL + "    public boolean isEmpty(org.w3c.dom.Node node) throws javax.xml.transform.TransformerException {" + NL + "        if (node != null) {" + NL + "            return node.getTextContent().length() == 0;" + NL + "        }" + NL + "        return false;" + NL + "    }" + NL + "" + NL + "\tprivate final org.talend.xpath.XPathUtil util = new  org.talend.xpath.XPathUtil();" + NL + "" + NL + "\tpublic void initXPath(org.w3c.dom.Node root){" + NL + "\t\tpr= new TalendPrefixResolver_";
  protected final String TEXT_67 = "();" + NL + "    \thasDefaultNS = pr.countNSMap(root);" + NL + "    \tutil.setDefaultNSPath(pr.getDefualtNSPath());" + NL + "\t}" + NL + "" + NL + "//==============add for feature 10753 start================================" + NL + "\torg.apache.xpath.objects.XObject obj = null;" + NL + "\t" + NL + "\tpublic int getNodeType(org.w3c.dom.Node node, String xpath) throws javax.xml.transform.TransformerException{" + NL + "\t\tobj = org.apache.xpath.XPathAPI.eval(node, util.addDefaultNSPrefix(xpath,loopPath), pr);" + NL + "\t\treturn obj.getType();" + NL + "\t}" + NL + "\t" + NL + "\tpublic String getNodeString(org.w3c.dom.Node node, String xpath) throws javax.xml.transform.TransformerException{" + NL + "\t\tif(obj==null){" + NL + "\t\t\tobj = org.apache.xpath.XPathAPI.eval(node, util.addDefaultNSPrefix(xpath,loopPath), pr);" + NL + "\t\t}" + NL + "\t\treturn obj.str();" + NL + "\t}" + NL + "" + NL + "    public org.w3c.dom.Node getSingleNode(org.w3c.dom.Node node, String xpath) throws javax.xml.transform.TransformerException {" + NL + "    \tif(obj==null){" + NL + "        \tobj = org.apache.xpath.XPathAPI.eval(node, util.addDefaultNSPrefix(xpath,loopPath), pr);" + NL + "        }" + NL + "        return obj.nodelist().item(0);" + NL + "    }" + NL + "//=====================end=================================================" + NL + "" + NL + "    public org.w3c.dom.NodeList getNodeList(org.w3c.dom.Node node, String xpath) throws javax.xml.transform.TransformerException {" + NL + "        org.apache.xpath.objects.XObject obj = org.apache.xpath.XPathAPI.eval(node, util.addDefaultNSPrefix(xpath), pr);" + NL + "        return obj.nodelist();" + NL + "    }" + NL + "    " + NL + "}" + NL;
  protected final String TEXT_68 = NL + "class ContentTool_";
  protected final String TEXT_69 = "{" + NL + "\tpublic String field_separator=\",\";//default value" + NL + "\t" + NL + "\tpublic String getNodeContent(org.w3c.dom.Node node){" + NL + "\t\tString result=getSubNodeContent(node);" + NL + "\t\tif(result.equals(\"\")) return \"\";" + NL + "\t\telse return result.substring(1);" + NL + "\t}" + NL + "\t" + NL + "\tprivate String getSubNodeContent(org.w3c.dom.Node node){" + NL + "\t\tif(node==null) return \"\";" + NL + "\t\t" + NL + "\t\tString tmp=node.getNodeValue();" + NL + "\t\t" + NL + "\t\tif(tmp==null){" + NL + "\t\t\ttmp=\"\";" + NL + "\t\t}else{" + NL + "\t\t\ttmp=field_separator+tmp;" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\tif(node.hasChildNodes()){" + NL + "\t\t\torg.w3c.dom.NodeList nodeList = node.getChildNodes();" + NL + "\t\t\tfor(int i=0;i<nodeList.getLength();i++){" + NL + "\t\t\t\torg.w3c.dom.Node tmpNode= nodeList.item(i);\t" + NL + "\t\t\t\ttmp=tmp+getSubNodeContent(tmpNode);" + NL + "" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\treturn tmp;" + NL + "\t}" + NL + "}" + NL + "ContentTool_";
  protected final String TEXT_70 = " contentTool_";
  protected final String TEXT_71 = " = new ContentTool_";
  protected final String TEXT_72 = "();" + NL + "contentTool_";
  protected final String TEXT_73 = ".field_separator=";
  protected final String TEXT_74 = ";";
  protected final String TEXT_75 = NL + NL + "XML_API_";
  protected final String TEXT_76 = " xml_api_";
  protected final String TEXT_77 = " = new XML_API_";
  protected final String TEXT_78 = "();" + NL + "xml_api_";
  protected final String TEXT_79 = ".loopPath=";
  protected final String TEXT_80 = ";" + NL + "org.apache.xerces.parsers.DOMParser parser_";
  protected final String TEXT_81 = " = new org.apache.xerces.parsers.DOMParser();";
  protected final String TEXT_82 = NL + "parser_";
  protected final String TEXT_83 = ".setEntityResolver(new IgnoreDTDEntityResolver_";
  protected final String TEXT_84 = "());";
  protected final String TEXT_85 = ".setProperty(\"http://apache.org/xml/properties/dom/document-class-name\"," + NL + "        \"org.apache.xerces.dom.PSVIDocumentImpl\");" + NL + "parser_";
  protected final String TEXT_86 = ".setFeature(\"http://xml.org/sax/features/validation\", true);" + NL + "parser_";
  protected final String TEXT_87 = ".setFeature(\"http://apache.org/xml/features/validation/schema\", true);" + NL + "parser_";
  protected final String TEXT_88 = ".setFeature(\"http://apache.org/xml/features/validation/schema-full-checking\", true);" + NL + "parser_";
  protected final String TEXT_89 = ".setFeature(\"http://xml.org/sax/features/namespaces\", true);" + NL + "parser_";
  protected final String TEXT_90 = ".setErrorHandler(null);" + NL + "Object filename_";
  protected final String TEXT_91 = " = null;" + NL + "try {" + NL + "\tfilename_";
  protected final String TEXT_92 = " = ";
  protected final String TEXT_93 = ";" + NL + "} catch(java.lang.Exception e) {" + NL + "\t";
  protected final String TEXT_94 = NL + "\tthrow(e);" + NL + "\t";
  protected final String TEXT_95 = NL + "\t";
  protected final String TEXT_96 = NL + "\t\tlog.error(\"";
  protected final String TEXT_97 = " - \" + e.getMessage());" + NL + "\t";
  protected final String TEXT_98 = NL + "\tSystem.err.println(e.getMessage());" + NL + "\t";
  protected final String TEXT_99 = NL + "}" + NL + "" + NL + "boolean isValidFile_";
  protected final String TEXT_100 = " = true;" + NL + "java.io.Closeable toClose_";
  protected final String TEXT_101 = " = null;" + NL + "try{" + NL + "    if(filename_";
  protected final String TEXT_102 = " != null && filename_";
  protected final String TEXT_103 = " instanceof String && filename_";
  protected final String TEXT_104 = ".toString().startsWith(\"//\")){" + NL + "\t\tif (!isWindows_";
  protected final String TEXT_105 = "){" + NL + "\t\t\tfilename_";
  protected final String TEXT_106 = " = filename_";
  protected final String TEXT_107 = ".toString().replaceFirst(\"//\",\"/\");" + NL + "\t\t}" + NL + "    }" + NL + "    if(filename_";
  protected final String TEXT_108 = " instanceof java.io.InputStream){" + NL + "    \tjava.io.InputStream inputStream_";
  protected final String TEXT_109 = " = (java.io.InputStream)filename_";
  protected final String TEXT_110 = ";" + NL + "    \ttoClose_";
  protected final String TEXT_111 = " = inputStream_";
  protected final String TEXT_112 = ";" + NL + "    \tparser_";
  protected final String TEXT_113 = ".parse(new org.xml.sax.InputSource(inputStream_";
  protected final String TEXT_114 = "));" + NL + "    }else{" + NL + "    \tjava.io.InputStream in_";
  protected final String TEXT_115 = "= new java.io.FileInputStream(String.valueOf(filename_";
  protected final String TEXT_116 = "));" + NL + "    \tjava.io.Reader unicodeReader_";
  protected final String TEXT_117 = " = new UnicodeReader(in_";
  protected final String TEXT_118 = ");" + NL + "    \ttoClose_";
  protected final String TEXT_119 = " = unicodeReader_";
  protected final String TEXT_120 = ";" + NL + "    \torg.xml.sax.InputSource xmlInputSource_";
  protected final String TEXT_121 = " = new org.xml.sax.InputSource(unicodeReader_";
  protected final String TEXT_122 = ");" + NL + "    \tparser_";
  protected final String TEXT_123 = ".parse(xmlInputSource_";
  protected final String TEXT_124 = ");" + NL + "    }" + NL + "}catch(java.lang.Exception e){";
  protected final String TEXT_125 = NL + "\tthrow(e);";
  protected final String TEXT_126 = NL + "\tSystem.err.println(e.getMessage());" + NL + "\tisValidFile_";
  protected final String TEXT_127 = " = false;";
  protected final String TEXT_128 = NL + "}finally {" + NL + "\tif(toClose_";
  protected final String TEXT_129 = "!=null) {" + NL + "\t\ttoClose_";
  protected final String TEXT_130 = ".close();" + NL + "\t}" + NL + "}" + NL + "if(isValidFile_";
  protected final String TEXT_131 = "){" + NL + "org.w3c.dom.Document doc_";
  protected final String TEXT_132 = " = parser_";
  protected final String TEXT_133 = ".getDocument();" + NL + "" + NL + "xml_api_";
  protected final String TEXT_134 = ".initXPath(doc_";
  protected final String TEXT_135 = ");" + NL + "org.w3c.dom.NodeList nodelist_";
  protected final String TEXT_136 = " = xml_api_";
  protected final String TEXT_137 = ".getNodeList(doc_";
  protected final String TEXT_138 = ");" + NL + "" + NL + "org.w3c.dom.Node node_";
  protected final String TEXT_139 = "  = null;" + NL + "String str_";
  protected final String TEXT_140 = " = null;";
  protected final String TEXT_141 = NL + "for (int i_";
  protected final String TEXT_142 = " = 0; i_";
  protected final String TEXT_143 = " < nodelist_";
  protected final String TEXT_144 = ".getLength(); i_";
  protected final String TEXT_145 = "++) {" + NL + "\torg.w3c.dom.Node tmp_";
  protected final String TEXT_146 = " = nodelist_";
  protected final String TEXT_147 = ".item(i_";
  protected final String TEXT_148 = ");";
  protected final String TEXT_149 = NL + "class NameSpaceTool_";
  protected final String TEXT_150 = " {" + NL + "" + NL + "    public java.util.HashMap<String, String> xmlNameSpaceMap = new java.util.HashMap<String, String>();" + NL + "    " + NL + "\tprivate java.util.List<String> defualtNSPath = new java.util.ArrayList<String>();" + NL + "" + NL + "    public void countNSMap(org.dom4j.Element el) {" + NL + "        for (org.dom4j.Namespace ns : (java.util.List<org.dom4j.Namespace>) el.declaredNamespaces()) {" + NL + "            if (ns.getPrefix().trim().length() == 0) {" + NL + "                xmlNameSpaceMap.put(\"pre\"+defualtNSPath.size(), ns.getURI());" + NL + "                String path = \"\";" + NL + "                org.dom4j.Element elTmp = el;" + NL + "                while (elTmp != null) {" + NL + "                \tif (elTmp.getNamespacePrefix() != null && elTmp.getNamespacePrefix().length() > 0) {" + NL + "                        path = \"/\" + elTmp.getNamespacePrefix() + \":\" + elTmp.getName() + path;" + NL + "                    } else {" + NL + "                        path = \"/\" + elTmp.getName() + path;" + NL + "                    }" + NL + "                    elTmp = elTmp.getParent();" + NL + "                }" + NL + "                defualtNSPath.add(path);" + NL + "            } else {" + NL + "                xmlNameSpaceMap.put(ns.getPrefix(), ns.getURI());" + NL + "            }" + NL + "" + NL + "        }" + NL + "        for (org.dom4j.Element e : (java.util.List<org.dom4j.Element>) el.elements()) {" + NL + "            countNSMap(e);" + NL + "        }" + NL + "    }" + NL + "    " + NL + "    private final org.talend.xpath.XPathUtil util = new  org.talend.xpath.XPathUtil();" + NL + "    " + NL + "    {" + NL + "    \tutil.setDefaultNSPath(defualtNSPath);" + NL + "    }" + NL + "    " + NL + "\tpublic String addDefaultNSPrefix(String path) {" + NL + "\t\treturn util.addDefaultNSPrefix(path);" + NL + "\t}" + NL + "\t" + NL + "\tpublic String addDefaultNSPrefix(String relativeXpression, String basePath) {" + NL + "\t\treturn util.addDefaultNSPrefix(relativeXpression,basePath);" + NL + "\t}" + NL + "    " + NL + "}" + NL + "" + NL + "class XML_API_";
  protected final String TEXT_151 = "{" + NL + "\tpublic boolean isDefNull(org.dom4j.Node node) throws javax.xml.transform.TransformerException {" + NL + "        if (node != null && node instanceof org.dom4j.Element) {" + NL + "        \torg.dom4j.Attribute attri = ((org.dom4j.Element)node).attribute(\"nil\");" + NL + "        \tif(attri != null && (\"true\").equals(attri.getText())){" + NL + "            \treturn true;" + NL + "            }" + NL + "        }" + NL + "        return false;" + NL + "    }" + NL + "" + NL + "    public boolean isMissing(org.dom4j.Node node) throws javax.xml.transform.TransformerException {" + NL + "        return node == null ? true : false;" + NL + "    }" + NL + "" + NL + "    public boolean isEmpty(org.dom4j.Node node) throws javax.xml.transform.TransformerException {" + NL + "        if (node != null) {" + NL + "            return node.getText().length() == 0;" + NL + "        }" + NL + "        return false;" + NL + "    }" + NL + "}" + NL;
  protected final String TEXT_152 = NL + "\tclass XML_NS_RMV_";
  protected final String TEXT_153 = "{\t" + NL + "" + NL + "\t\tpublic void removeNamespace(org.dom4j.Document reader," + NL + "\t\t\t\torg.dom4j.Document writer) {" + NL + "\t\t\torg.dom4j.Element elemReader = reader.getRootElement();" + NL + "\t\t\torg.dom4j.Element elemTo = writer.addElement(elemReader" + NL + "\t\t\t\t\t.getName());" + NL + "\t\t\tif(elemReader!=null && elemReader.getText()!=null && !\"\".equals(elemReader.getText())){" + NL + "\t\t\t\telemTo.setText(elemReader.getText());" + NL + "\t\t\t}" + NL + "\t\t\tfor (org.dom4j.Attribute attri : (List<org.dom4j.Attribute>) elemReader" + NL + "\t\t\t\t\t.attributes()) {" + NL + "\t\t\t\telemTo.addAttribute(attri.getName(),attri.getText());" + NL + "\t\t\t}" + NL + "\t\t\tremoveSubNamespace(elemReader, elemTo);" + NL + "\t\t}" + NL + "" + NL + "\t\tpublic void removeSubNamespace(org.dom4j.Element elemFrom," + NL + "\t\t\t\torg.dom4j.Element elemTo) {" + NL + "\t\t\tfor (org.dom4j.Element subFrom : (List<org.dom4j.Element>) elemFrom" + NL + "\t\t\t\t\t.elements()) {" + NL + "\t\t\t\torg.dom4j.Element tmpElemTo = elemTo.addElement(subFrom" + NL + "\t\t\t\t\t\t.getName());" + NL + "\t\t\t\tif(subFrom!=null && subFrom.getText()!=null && !\"\".equals(subFrom.getText())){" + NL + "\t\t\t\t\ttmpElemTo.setText(subFrom.getText());" + NL + "\t\t\t\t}" + NL + "\t\t\t\tfor (org.dom4j.Attribute attri : (List<org.dom4j.Attribute>) subFrom" + NL + "\t\t\t\t\t\t.attributes()) {" + NL + "\t\t\t\t\ttmpElemTo.addAttribute(attri.getName(),attri.getText());" + NL + "\t\t\t\t}" + NL + "\t\t\t\tremoveSubNamespace(subFrom, tmpElemTo);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_154 = NL + NL + "org.dom4j.io.SAXReader reader_";
  protected final String TEXT_155 = " = new org.dom4j.io.SAXReader();";
  protected final String TEXT_156 = NL + "reader_";
  protected final String TEXT_157 = NL + "Object filename_";
  protected final String TEXT_158 = NL + "}" + NL + "if(filename_";
  protected final String TEXT_159 = ".toString().startsWith(\"//\")){" + NL + "\tif (!isWindows_";
  protected final String TEXT_160 = "){" + NL + "\t\tfilename_";
  protected final String TEXT_161 = ".toString().replaceFirst(\"//\",\"/\");" + NL + "\t}" + NL + "}" + NL;
  protected final String TEXT_162 = NL + "org.dom4j.Document doc_reader_";
  protected final String TEXT_163 = " = null;" + NL + "org.dom4j.Document doc_tmp_";
  protected final String TEXT_164 = " = org.dom4j.DocumentHelper.createDocument();" + NL + "org.dom4j.io.OutputFormat format_";
  protected final String TEXT_165 = " = org.dom4j.io.OutputFormat.createPrettyPrint();" + NL + "try {" + NL + "\tif(filename_";
  protected final String TEXT_166 = " instanceof java.io.InputStream){" + NL + "\t\tdoc_reader_";
  protected final String TEXT_167 = " = reader_";
  protected final String TEXT_168 = ".read((java.io.InputStream)filename_";
  protected final String TEXT_169 = ");" + NL + "\t}else{" + NL + "\t\tdoc_reader_";
  protected final String TEXT_170 = ".read(new java.io.File(String.valueOf(filename_";
  protected final String TEXT_171 = ")).toURI().toString());" + NL + "\t}" + NL + "\tformat_";
  protected final String TEXT_172 = ".setTrimText(false);" + NL + "\tformat_";
  protected final String TEXT_173 = ".setEncoding(";
  protected final String TEXT_174 = ");" + NL + "\tnew XML_NS_RMV_";
  protected final String TEXT_175 = "().removeNamespace(doc_reader_";
  protected final String TEXT_176 = ",doc_tmp_";
  protected final String TEXT_177 = ");" + NL + "\tdoc_reader_";
  protected final String TEXT_178 = ".clearContent();" + NL + "\tdoc_reader_";
  protected final String TEXT_179 = " = null;" + NL + "\tjava.io.FileOutputStream stream_";
  protected final String TEXT_180 = " = new java.io.FileOutputStream(";
  protected final String TEXT_181 = ");" + NL + "\torg.dom4j.io.XMLWriter output_";
  protected final String TEXT_182 = " = new org.dom4j.io.XMLWriter(stream_";
  protected final String TEXT_183 = ", format_";
  protected final String TEXT_184 = ");" + NL + "\toutput_";
  protected final String TEXT_185 = ".write(doc_tmp_";
  protected final String TEXT_186 = ".close();" + NL + "} catch(java.lang.Exception e) {" + NL + "\t";
  protected final String TEXT_187 = NL + "} finally {" + NL + "\tif(filename_";
  protected final String TEXT_188 = " instanceof java.io.InputStream && filename_";
  protected final String TEXT_189 = "!=null){" + NL + "\t\t((java.io.InputStream)filename_";
  protected final String TEXT_190 = ").close();" + NL + "\t}" + NL + "}" + NL + "filename_";
  protected final String TEXT_191 = NL + "boolean isValidFile_";
  protected final String TEXT_192 = " = true;" + NL + "org.dom4j.Document doc_";
  protected final String TEXT_193 = " = null;" + NL + "java.io.Closeable toClose_";
  protected final String TEXT_194 = " = null;" + NL + "try{" + NL + "\tif(filename_";
  protected final String TEXT_195 = " instanceof java.io.InputStream){" + NL + "\t\tjava.io.InputStream inputStream_";
  protected final String TEXT_196 = ";" + NL + "\t\ttoClose_";
  protected final String TEXT_197 = ";" + NL + "\t\tdoc_";
  protected final String TEXT_198 = ".read(inputStream_";
  protected final String TEXT_199 = ");" + NL + "\t}else{" + NL + "\t\tjava.io.Reader unicodeReader_";
  protected final String TEXT_200 = " = new UnicodeReader(new java.io.FileInputStream(String.valueOf(filename_";
  protected final String TEXT_201 = ")),";
  protected final String TEXT_202 = ");" + NL + "\t\ttoClose_";
  protected final String TEXT_203 = ";" + NL + "\t\torg.xml.sax.InputSource in_";
  protected final String TEXT_204 = "= new org.xml.sax.InputSource(unicodeReader_";
  protected final String TEXT_205 = ");" + NL + "\t\tdoc_";
  protected final String TEXT_206 = ".read(in_";
  protected final String TEXT_207 = ");" + NL + "\t}" + NL + "}catch(java.lang.Exception e){";
  protected final String TEXT_208 = NL + "} finally {" + NL + "\tif(toClose_";
  protected final String TEXT_209 = "){" + NL + "NameSpaceTool_";
  protected final String TEXT_210 = " nsTool_";
  protected final String TEXT_211 = " = new NameSpaceTool_";
  protected final String TEXT_212 = "();" + NL + "nsTool_";
  protected final String TEXT_213 = ".countNSMap(doc_";
  protected final String TEXT_214 = ".getRootElement());" + NL + "java.util.HashMap<String,String> xmlNameSpaceMap_";
  protected final String TEXT_215 = " = nsTool_";
  protected final String TEXT_216 = ".xmlNameSpaceMap;  " + NL + "" + NL + "org.dom4j.XPath x_";
  protected final String TEXT_217 = " = doc_";
  protected final String TEXT_218 = ".createXPath(nsTool_";
  protected final String TEXT_219 = ".addDefaultNSPrefix(";
  protected final String TEXT_220 = "));  " + NL + "x_";
  protected final String TEXT_221 = ".setNamespaceURIs(xmlNameSpaceMap_";
  protected final String TEXT_222 = "); ";
  protected final String TEXT_223 = NL + "org.jaxen.NamespaceContext namespaceContext_";
  protected final String TEXT_224 = " = new org.jaxen.NamespaceContext() {" + NL + "" + NL + "\tpublic String translateNamespacePrefixToUri(String prefix) { " + NL + "\t\treturn \"\";//ignore prefix in xpath when evaluate" + NL + "\t}" + NL + "\t" + NL + "};" + NL + "x_";
  protected final String TEXT_225 = ".setNamespaceContext(namespaceContext_";
  protected final String TEXT_226 = NL + NL + "java.util.List<org.dom4j.tree.AbstractNode> nodeList_";
  protected final String TEXT_227 = " = (java.util.List<org.dom4j.tree.AbstractNode>)x_";
  protected final String TEXT_228 = ".selectNodes(doc_";
  protected final String TEXT_229 = ");\t" + NL + "XML_API_";
  protected final String TEXT_230 = "();" + NL + "String str_";
  protected final String TEXT_231 = " = \"\";" + NL + "org.dom4j.Node node_";
  protected final String TEXT_232 = " = null;" + NL + "" + NL + "//init all mapping xpaths" + NL + "java.util.Map<Integer,org.dom4j.XPath> xpaths_";
  protected final String TEXT_233 = "=new java.util.HashMap<Integer,org.dom4j.XPath>();";
  protected final String TEXT_234 = NL + "\tclass XPathUtil_";
  protected final String TEXT_235 = "{" + NL + "\t";
  protected final String TEXT_236 = NL + "\t\t\t   public void initXPaths_";
  protected final String TEXT_237 = "(java.util.Map<Integer,org.dom4j.XPath> xpaths,NameSpaceTool_";
  protected final String TEXT_238 = " nsTool," + NL + "\t\t\t       java.util.HashMap<String,String> xmlNameSpaceMap";
  protected final String TEXT_239 = ",org.jaxen.NamespaceContext namespaceContext";
  protected final String TEXT_240 = "){" + NL + "\t\t\t";
  protected final String TEXT_241 = NL + "\torg.dom4j.XPath xpath_";
  protected final String TEXT_242 = " = org.dom4j.DocumentHelper.createXPath(nsTool.addDefaultNSPrefix(";
  protected final String TEXT_243 = "));" + NL + "\txpath_";
  protected final String TEXT_244 = ".setNamespaceURIs(xmlNameSpaceMap);" + NL + "\t";
  protected final String TEXT_245 = NL + "\txpath_";
  protected final String TEXT_246 = ".setNamespaceContext(namespaceContext);" + NL + "\t";
  protected final String TEXT_247 = NL + "\t\t\txpaths.put(";
  protected final String TEXT_248 = ",xpath_";
  protected final String TEXT_249 = ");" + NL + "\t\t\t";
  protected final String TEXT_250 = NL + "\t         }" + NL + "\t      ";
  protected final String TEXT_251 = NL + "\t     }" + NL + "\t   ";
  protected final String TEXT_252 = NL + "\t      public void initXPaths(java.util.Map<Integer,org.dom4j.XPath> xpaths,NameSpaceTool_";
  protected final String TEXT_253 = " nsTool," + NL + "\t\t\t    java.util.HashMap<String,String> xmlNameSpaceMap";
  protected final String TEXT_254 = "){" + NL + "\t\t\t    ";
  protected final String TEXT_255 = NL + "\t\t\t        initXPaths_";
  protected final String TEXT_256 = "(xpaths,nsTool, xmlNameSpaceMap";
  protected final String TEXT_257 = ",namespaceContext";
  protected final String TEXT_258 = ");" + NL + "\t\t\t    ";
  protected final String TEXT_259 = NL + "\t\t   }" + NL + "\t}" + NL + "\tXPathUtil_";
  protected final String TEXT_260 = " xPathUtil_";
  protected final String TEXT_261 = " = new XPathUtil_";
  protected final String TEXT_262 = "();" + NL + "\txPathUtil_";
  protected final String TEXT_263 = ".initXPaths(xpaths_";
  protected final String TEXT_264 = ", nsTool_";
  protected final String TEXT_265 = ", xmlNameSpaceMap_";
  protected final String TEXT_266 = ", namespaceContext_";
  protected final String TEXT_267 = NL + "\tif(true){" + NL + "\t\tthrow new IllegalArgumentException(\"Min column number of optimize code should be bigger than 0!\");" + NL + "\t}";
  protected final String TEXT_268 = NL + "for (org.dom4j.tree.AbstractNode temp_";
  protected final String TEXT_269 = ": nodeList_";
  protected final String TEXT_270 = ") {";
  protected final String TEXT_271 = NL + "class XML_API_";
  protected final String TEXT_272 = "{" + NL + "\tpublic boolean isDefNull(String[] node) throws javax.xml.transform.TransformerException {" + NL + "        if(node[0] != null && node[1]!=null && (\"true\").equals(node[1])){" + NL + "        \treturn true;" + NL + "        }" + NL + "        return false;" + NL + "    }" + NL + "" + NL + "    public boolean isMissing(String[] node) throws javax.xml.transform.TransformerException {" + NL + "        return node[0] == null ? true : false;" + NL + "    }" + NL + "" + NL + "    public boolean isEmpty(String[] node) throws javax.xml.transform.TransformerException {" + NL + "        if(node[0]!=null ){" + NL + "        \treturn node[0].length() == 0;" + NL + "        }" + NL + "        return false;" + NL + "    }" + NL + "}" + NL + "XML_API_";
  protected final String TEXT_273 = "();" + NL + "" + NL + "String[] queryPaths_";
  protected final String TEXT_274 = " = new String[]{";
  protected final String TEXT_275 = "\t" + NL + "\t";
  protected final String TEXT_276 = "\t\t" + NL + "\t,";
  protected final String TEXT_277 = "+\"/@xsi:nil\"";
  protected final String TEXT_278 = NL + "};" + NL + "" + NL + "boolean[] asXMLs_";
  protected final String TEXT_279 = " = new boolean[]{";
  protected final String TEXT_280 = "\t\t" + NL + "\t,false";
  protected final String TEXT_281 = NL + "};" + NL + "" + NL + "String str_";
  protected final String TEXT_282 = " = \"\";" + NL + "String[] node_";
  protected final String TEXT_283 = " = null;" + NL + "org.talend.xml.sax.SAXLooper looper_";
  protected final String TEXT_284 = " = new org.talend.xml.sax.SAXLooper(";
  protected final String TEXT_285 = ",queryPaths_";
  protected final String TEXT_286 = ",asXMLs_";
  protected final String TEXT_287 = NL + "looper_";
  protected final String TEXT_288 = ".setIgnoreDTD(true);";
  protected final String TEXT_289 = ");" + NL + "Object filename_";
  protected final String TEXT_290 = NL + "\tlog.error(\"";
  protected final String TEXT_291 = " - \" + e.getMessage());" + NL + "\t\t";
  protected final String TEXT_292 = ".toString().replaceFirst(\"//\",\"/\");" + NL + "\t}" + NL + "}" + NL + "if(filename_";
  protected final String TEXT_293 = " instanceof java.io.InputStream){" + NL + "\tlooper_";
  protected final String TEXT_294 = ".parse((java.io.InputStream)filename_";
  protected final String TEXT_295 = ");" + NL + "}else{" + NL + "\tlooper_";
  protected final String TEXT_296 = ".parse(String.valueOf(filename_";
  protected final String TEXT_297 = "));" + NL + "}" + NL + "java.util.Iterator<java.util.Map<String, String>> it_";
  protected final String TEXT_298 = "  = looper_";
  protected final String TEXT_299 = ".iterator();";
  protected final String TEXT_300 = NL + "while (it_";
  protected final String TEXT_301 = ".hasNext()) {" + NL + "\tjava.util.Map<String, String> row_";
  protected final String TEXT_302 = " = it_";
  protected final String TEXT_303 = ".next();";
  protected final String TEXT_304 = NL + "\tif (nb_line_";
  protected final String TEXT_305 = ">=";
  protected final String TEXT_306 = ") {" + NL + "\t";
  protected final String TEXT_307 = NL + "\t\t\tlooper_";
  protected final String TEXT_308 = ".stopRead();" + NL + "\t";
  protected final String TEXT_309 = NL + "\t\tbreak;" + NL + "\t}";
  protected final String TEXT_310 = NL + "\t\tnb_line_";
  protected final String TEXT_311 = "++;" + NL + "\t\t";
  protected final String TEXT_312 = " = null;\t\t\t";
  protected final String TEXT_313 = NL + "\tif(xml_api_";
  protected final String TEXT_314 = ".getNodeType(tmp_";
  protected final String TEXT_315 = ")==org.apache.xpath.objects.XObject.CLASS_NODESET){" + NL + "\t\tnode_";
  protected final String TEXT_316 = ".getSingleNode(tmp_";
  protected final String TEXT_317 = NL + "    \tif(node_";
  protected final String TEXT_318 = "!=null && node_";
  protected final String TEXT_319 = ".getNodeType()==org.w3c.dom.Node.ELEMENT_NODE && node_";
  protected final String TEXT_320 = ".hasChildNodes()){" + NL + "    \t\tstr_";
  protected final String TEXT_321 = " = contentTool_";
  protected final String TEXT_322 = ".getNodeContent(node_";
  protected final String TEXT_323 = ");" + NL + "    \t}else{" + NL + "    \t\tstr_";
  protected final String TEXT_324 = " = node_";
  protected final String TEXT_325 = "!=null?node_";
  protected final String TEXT_326 = ".getTextContent():\"\";" + NL + "    \t}";
  protected final String TEXT_327 = NL + "\t\tstr_";
  protected final String TEXT_328 = ".getTextContent():\"\";";
  protected final String TEXT_329 = NL + "\t}else{" + NL + "\t\tnode_";
  protected final String TEXT_330 = " = tmp_";
  protected final String TEXT_331 = ";" + NL + "\t\tstr_";
  protected final String TEXT_332 = ".getNodeString(tmp_";
  protected final String TEXT_333 = ");" + NL + "\t}";
  protected final String TEXT_334 = NL + "\tif(true) {" + NL + "\t\tthrow new RuntimeException(\"Error Config: \\\"Get Nodes\\\" should be checked when type is \\\"Document\\\"\");" + NL + "\t}";
  protected final String TEXT_335 = NL + "    Object obj";
  protected final String TEXT_336 = "_";
  protected final String TEXT_337 = " = xpaths_";
  protected final String TEXT_338 = ".get(";
  protected final String TEXT_339 = ").evaluate(temp_";
  protected final String TEXT_340 = ");" + NL + "    if(obj";
  protected final String TEXT_341 = " == null) {" + NL + "    \tnode_";
  protected final String TEXT_342 = NL + "    \tstr_";
  protected final String TEXT_343 = " = \"\";";
  protected final String TEXT_344 = NL + "    \t" + NL + "    } else if(obj";
  protected final String TEXT_345 = " instanceof org.dom4j.Node) {" + NL + "    \tnode_";
  protected final String TEXT_346 = " = (org.dom4j.Node)obj";
  protected final String TEXT_347 = ".asXML();";
  protected final String TEXT_348 = " = org.jaxen.function.StringFunction.evaluate(node_";
  protected final String TEXT_349 = ",org.jaxen.dom4j.DocumentNavigator.getInstance());";
  protected final String TEXT_350 = NL + "    } else if(obj";
  protected final String TEXT_351 = " instanceof String || obj";
  protected final String TEXT_352 = " instanceof Number){" + NL + "    \tnode_";
  protected final String TEXT_353 = " = temp_";
  protected final String TEXT_354 = ";" + NL + "    \tstr_";
  protected final String TEXT_355 = " = String.valueOf(obj";
  protected final String TEXT_356 = ");" + NL + "    } else if(obj";
  protected final String TEXT_357 = " instanceof java.util.List){" + NL + "    \tjava.util.List<org.dom4j.Node> nodes_";
  protected final String TEXT_358 = " = (java.util.List<org.dom4j.Node>)obj";
  protected final String TEXT_359 = ";" + NL + "    \tnode_";
  protected final String TEXT_360 = " = nodes_";
  protected final String TEXT_361 = ".size()>0 ? nodes_";
  protected final String TEXT_362 = ".get(0) : null;";
  protected final String TEXT_363 = "==null?null:node_";
  protected final String TEXT_364 = "==null?\"\":org.jaxen.function.StringFunction.evaluate(node_";
  protected final String TEXT_365 = NL + "\t}";
  protected final String TEXT_366 = NL + "\tstr_";
  protected final String TEXT_367 = " = row_";
  protected final String TEXT_368 = NL + "\tnode_";
  protected final String TEXT_369 = " = new String[]{str_";
  protected final String TEXT_370 = ",row_";
  protected final String TEXT_371 = "+\"/@xsi:nil\")};";
  protected final String TEXT_372 = ",null};";
  protected final String TEXT_373 = NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_374 = ".";
  protected final String TEXT_375 = " = ParserUtils.parseTo_Document(str_";
  protected final String TEXT_376 = " = str_";
  protected final String TEXT_377 = NL + "\t\t\t\t\t\t\t\t\tif(xml_api_";
  protected final String TEXT_378 = ".isDefNull(node_";
  protected final String TEXT_379 = ")){" + NL + "\t\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_380 = " =null;" + NL + "\t\t\t\t\t\t\t\t\t}else if(xml_api_";
  protected final String TEXT_381 = ".isEmpty(node_";
  protected final String TEXT_382 = ")){" + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_383 = " =\"\";" + NL + "\t\t\t\t\t\t\t\t\t}else if(xml_api_";
  protected final String TEXT_384 = ".isMissing(node_";
  protected final String TEXT_385 = " )){ " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_386 = " =";
  protected final String TEXT_387 = ";" + NL + "\t\t\t\t\t\t\t\t\t}else{";
  protected final String TEXT_388 = "\t" + NL + "\t\t\t\t\t\t\t\t\t\tif(xml_api_";
  protected final String TEXT_389 = " =null;" + NL + "\t\t\t\t\t\t\t\t\t\t}else if(xml_api_";
  protected final String TEXT_390 = ") || xml_api_";
  protected final String TEXT_391 = "=";
  protected final String TEXT_392 = ";" + NL + "\t\t\t\t\t\t\t\t\t\t}else{";
  protected final String TEXT_393 = NL + "\t\t\t\t\t\t\t\t\t\tif(xml_api_";
  protected final String TEXT_394 = NL + "\t\t";
  protected final String TEXT_395 = " = ParserUtils.parseTo_Date(str_";
  protected final String TEXT_396 = ",false);";
  protected final String TEXT_397 = "\t\t\t\t\t\t\t" + NL + "\t\t";
  protected final String TEXT_398 = ".getBytes(";
  protected final String TEXT_399 = " = ParserUtils.parseTo_";
  protected final String TEXT_400 = "(ParserUtils.parseTo_Number(str_";
  protected final String TEXT_401 = "));";
  protected final String TEXT_402 = "(str_";
  protected final String TEXT_403 = NL + "    \t\t\t\t";
  protected final String TEXT_404 = NL + "\tboolean whetherReject_";
  protected final String TEXT_405 = " = false;" + NL + "\t";
  protected final String TEXT_406 = " = new ";
  protected final String TEXT_407 = "Struct();" + NL + "\ttry{";
  protected final String TEXT_408 = " = null; ";
  protected final String TEXT_409 = NL + "\t\t\t" + NL + "    } catch (java.lang.Exception e) {" + NL + "        whetherReject_";
  protected final String TEXT_410 = " = true;";
  protected final String TEXT_411 = NL + "            throw(e);";
  protected final String TEXT_412 = NL + "\t\t\t\tlog.error(\"";
  protected final String TEXT_413 = " - \" + e.getMessage());" + NL + "\t\t\t";
  protected final String TEXT_414 = NL + "                    ";
  protected final String TEXT_415 = "Struct();";
  protected final String TEXT_416 = NL + "                ";
  protected final String TEXT_417 = ".errorMessage = e.getMessage() + \" - Line: \" + tos_count_";
  protected final String TEXT_418 = NL + "                System.err.println(e.getMessage());";
  protected final String TEXT_419 = NL + "            \t";
  protected final String TEXT_420 = NL + "    }";
  protected final String TEXT_421 = "if(!whetherReject_";
  protected final String TEXT_422 = ") { ";
  protected final String TEXT_423 = "      " + NL + "             if(";
  protected final String TEXT_424 = " == null){ " + NL + "            \t ";
  protected final String TEXT_425 = "Struct();" + NL + "             }";
  protected final String TEXT_426 = NL + "\t    \t ";
  protected final String TEXT_427 = ";    \t\t\t\t";
  protected final String TEXT_428 = " } ";
  protected final String TEXT_429 = "\t";
  protected final String TEXT_430 = NL + "\t\t\t";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	//this util class use by set log4j debug paramters
	class DefaultLog4jFileUtil {
	
		INode node = null;
	    String cid = null;
 		boolean isLog4jEnabled = false;
 		String label = null;
 		
 		public DefaultLog4jFileUtil(){
 		}
 		public DefaultLog4jFileUtil(INode node) {
 			this.node = node;
 			this.cid = node.getUniqueName();
 			this.label = cid;
			this.isLog4jEnabled = ("true").equals(org.talend.core.model.process.ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));
 		}
 		
 		public void setCid(String cid) {
 			this.cid = cid;
 		}
 		
		//for all tFileinput* components 
		public void startRetriveDataInfo() {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_1);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_2);
    
			}
		}
		
		public void retrievedDataNumberInfo() {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    
			}
		}
		
		public void retrievedDataNumberInfoFromGlobalMap(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_7);
    
			}
		}
		
		//for all tFileinput* components 
		public void retrievedDataNumberInfo(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    
			}
		}
		
		public void writeDataFinishInfo(INode node) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    
			}
		}
		
 		//TODO delete it and remove all log4jSb parameter from components
		public void componentStartInfo(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    
			}
		}
		
		//TODO rename or delete it
		public void debugRetriveData(INode node,boolean hasIncreased) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(hasIncreased?"":"+1");
    stringBuffer.append(TEXT_12);
    
			}
		}
		
		//TODO rename or delete it
		public void debugRetriveData(INode node) {
			debugRetriveData(node,true);
		}
		
		//TODO rename or delete it
		public void debugWriteData(INode node) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_14);
    
			}
		}
		
		public void logCurrentRowNumberInfo() {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_16);
    
			}
		}
		
		public void logDataCountInfo() {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    
			}
		}
	}
	
	final DefaultLog4jFileUtil log4jFileUtil = new DefaultLog4jFileUtil((INode)(((org.talend.designer.codegen.config.CodeGeneratorArgument)argument).getArgument()));
	
    stringBuffer.append(TEXT_18);
    
	class DefaultRowUtil {
		String cid = "";
		INode node;
		int schemaOptNum;
		int columnListSize;
		boolean isOptimizeCode;
		boolean isBehindDynamic;
	
		boolean useV2C = false;
		IMetadataTable V2CMetadata;
		String V2CTargetConnName;
		String V2CSourceValueClass;
		String V2CSourceValueName;
		StringBuffer parmsStr;
		StringBuffer varsStr;
		public void prepareOptimizeParam(IMetadataTable metadata,int schemaOptNum){
			this.schemaOptNum=schemaOptNum;
			this.columnListSize = metadata.getListColumns().size();
			this.isOptimizeCode = schemaOptNum < this.columnListSize;
		}
		
		public void prepareValueToConn(INode node, IMetadataTable metadata, String sourceValueClass, String sourceValueName, String targetConnName, int schemaOptNum){
			this.node = node;
			this.V2CMetadata = metadata;
			this.V2CTargetConnName = targetConnName;
			this.V2CSourceValueClass = sourceValueClass;
			this.V2CSourceValueName = sourceValueName;
			this.useV2C = true;
			prepareOptimizeParam(metadata, schemaOptNum);
		}
		public void genVarsParmsStr(Map<String,String> varsMap){
			parmsStr=new StringBuffer();
			varsStr=new StringBuffer();
			if(varsMap!=null && varsMap.size()>0){
				java.util.Set<String> vars=varsMap.keySet();
				for(String varName:vars){
					parmsStr.append(","+varsMap.get(varName)+" "+varName);
					varsStr.append(","+varName);
				}
			}
		}
		public void codeForValueToConn(INode node, IMetadataTable metadata, String sourceValueClass, String sourceValueName, String targetConnName, int start , int end){
		}
		
		public void callValueToConn(String sourceValueName, String targetConnName){
			if(isOptimizeCode){
		
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(sourceValueName);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(targetConnName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append((varsStr!=null?varsStr.toString():""));
    stringBuffer.append(TEXT_23);
    
			}else{
				codeForValueToConn(node, V2CMetadata, V2CSourceValueClass, V2CSourceValueName, V2CTargetConnName, 0, columnListSize);
			}
		}
		
		boolean useV2CWithD = false;
		IMetadataTable V2CWithDMetadata;
		String V2CWithDTargetConnName;
		String V2CWithDSourceValueClass;
		String V2CWithDSourceValueName;
		String V2CWithDDynamicName;
		public void prepareValueToConnWithD(INode node, IMetadataTable metadata, String sourceValueClass, String sourceValueName, String targetConnName, String dynamicName, int schemaOptNum){
			this.node = node;
			this.V2CWithDMetadata = metadata;
			this.V2CWithDTargetConnName = targetConnName;
			this.V2CWithDSourceValueClass = sourceValueClass;
			this.V2CWithDSourceValueName = sourceValueName;
			this.V2CWithDDynamicName = dynamicName;
			this.useV2CWithD = true;
			prepareOptimizeParam(metadata, schemaOptNum);
		}
		
		public boolean codeForValueToConnWithD(INode node, IMetadataTable metadata, String sourceValueClass, String sourceValueName, String targetConnName, String dynamicName ,int start , int end){
			return false;
		}
		
		public void callValueToConnWithD(String sourceValueName, String targetConnName, String dynamicName){
			if(isOptimizeCode){
		
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(sourceValueName);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(targetConnName);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(dynamicName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append((varsStr!=null?varsStr.toString():""));
    stringBuffer.append(TEXT_23);
    
			}else{
				codeForValueToConnWithD(node, V2CWithDMetadata, V2CWithDSourceValueClass, V2CWithDSourceValueName, V2CWithDTargetConnName, V2CWithDDynamicName, 0, columnListSize);
			}
		}
		
		IMetadataTable C2CMetadata;
		String C2CTargetConnName;
		String C2CSourceConnName;
		boolean useC2C = false;
		public void prepareConnToConn(IMetadataTable metadata, String sourceConnName, String targetConnName){
			this.C2CMetadata = metadata;
			this.C2CTargetConnName = targetConnName;
			this.C2CSourceConnName = sourceConnName;
			this.useC2C = true;
		}
		
		public void codeForConnToConn(INode node, IMetadataTable metadata, String sourceConnName, String targetConnName, int start ,int end){
		}
		
		public void callConnToConn(String sourceConnName, String targetConnName){
			if(isOptimizeCode){
		
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(sourceConnName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(targetConnName);
    stringBuffer.append(TEXT_23);
    
			}else{
				codeForConnToConn(node, C2CMetadata, C2CSourceConnName, C2CTargetConnName, 0, columnListSize);
			}
		}
		
		
		public void generateClass(){
		  	if(isOptimizeCode){
				cid = node.getUniqueName();
				int methodNum=(columnListSize%schemaOptNum==0?columnListSize/schemaOptNum:(columnListSize/schemaOptNum)+1);
				
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    
				int start=0;
				int end=0;
				for(int i=0;i<methodNum;i++){
					start=i*schemaOptNum;
					if(i==(methodNum-1)){
						end=columnListSize;
					}else{
						end=(i+1)*schemaOptNum;
					}
					if(useV2C){
					
    stringBuffer.append(TEXT_29);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(V2CSourceValueClass);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(V2CSourceValueName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(V2CTargetConnName);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(V2CTargetConnName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append((parmsStr!=null?parmsStr.toString():""));
    stringBuffer.append(TEXT_32);
    codeForValueToConn(node, V2CMetadata, V2CSourceValueClass, V2CSourceValueName, V2CTargetConnName, start, end);
    stringBuffer.append(TEXT_33);
    	
					}
					if(useV2CWithD){
					
    stringBuffer.append(TEXT_34);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(V2CWithDSourceValueClass);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(V2CWithDSourceValueName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(V2CWithDTargetConnName);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(V2CWithDTargetConnName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(V2CWithDDynamicName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append((parmsStr!=null?parmsStr.toString():""));
    stringBuffer.append(TEXT_32);
    this.isBehindDynamic = codeForValueToConnWithD(node, V2CWithDMetadata, V2CWithDSourceValueClass, V2CWithDSourceValueName, V2CWithDTargetConnName, V2CWithDDynamicName, start, end);
    stringBuffer.append(TEXT_33);
    		
					}
					if(useC2C){
					
    stringBuffer.append(TEXT_36);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(C2CSourceConnName);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(C2CSourceConnName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(C2CTargetConnName);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(C2CTargetConnName);
    stringBuffer.append(TEXT_32);
    codeForConnToConn(node, C2CMetadata, C2CSourceConnName, C2CTargetConnName, start, end);
    stringBuffer.append(TEXT_33);
    
					}
				}
				if(useV2C){//Call all split methods in one method
				
    stringBuffer.append(TEXT_37);
    stringBuffer.append(V2CSourceValueClass);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(V2CSourceValueName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(V2CTargetConnName);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(V2CTargetConnName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append((parmsStr!=null?parmsStr.toString():""));
    stringBuffer.append(TEXT_38);
    	
				}
				if(useV2CWithD){
				
    stringBuffer.append(TEXT_39);
    stringBuffer.append(V2CWithDSourceValueClass);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(V2CWithDSourceValueName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(V2CWithDTargetConnName);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(V2CWithDTargetConnName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(V2CWithDDynamicName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append((parmsStr!=null?parmsStr.toString():""));
    stringBuffer.append(TEXT_38);
    
				}
				for(int i=0;i<methodNum;i++){
					if(useV2C){
					
    stringBuffer.append(TEXT_40);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(V2CSourceValueName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(V2CTargetConnName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append((varsStr!=null?varsStr.toString():""));
    stringBuffer.append(TEXT_41);
    
					}
					if(useV2CWithD){
					
    stringBuffer.append(TEXT_42);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(V2CWithDSourceValueName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(V2CWithDTargetConnName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(V2CWithDDynamicName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append((varsStr!=null?varsStr.toString():""));
    stringBuffer.append(TEXT_41);
    
					}
				}
				if(useV2C || useV2CWithD){
				
    stringBuffer.append(TEXT_43);
    
				}
				if(useC2C){
				
    stringBuffer.append(TEXT_44);
    stringBuffer.append(C2CSourceConnName);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(C2CSourceConnName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(C2CTargetConnName);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(C2CTargetConnName);
    stringBuffer.append(TEXT_45);
    
					for(int i=0;i<methodNum;i++){
						if(useC2C){
						
    stringBuffer.append(TEXT_46);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(C2CSourceConnName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(C2CTargetConnName);
    stringBuffer.append(TEXT_47);
    
						}
					}
					
    stringBuffer.append(TEXT_43);
    
				}
				
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    
		  	}
		}
	}
	
    stringBuffer.append(TEXT_52);
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();

String cid = node.getUniqueName();
        
List<Map<String, String>> mapping = (List<Map<String,String>>)ElementParameterParser.getObjectValueXML(node, "__MAPPING__");
String encoding = ElementParameterParser.getValue(node, "__ENCODING__");
String loopQuery = ElementParameterParser.getValue(node, "__LOOP_QUERY__"); 

String filename = ElementParameterParser.getValue(node, "__FILENAME__");

boolean ignore_NS="true".equals(ElementParameterParser.getValue(node, "__IGNORE_NS__"));
String tmp_filename = ElementParameterParser.getValue(node, "__TMP_FILENAME__");

String useSeparator = ElementParameterParser.getValue(node, "__USE_SEPARATOR__");
String fieldSeparator = ElementParameterParser.getValue(node, "__FIELD_SEPARATOR__");

String limit = ElementParameterParser.getValue(node, "__LIMIT__");
if (("").equals(limit)) {
	limit = "-1";
}

String dieOnErrorStr = ElementParameterParser.getValue(node, "__DIE_ON_ERROR__");
boolean dieOnError = (dieOnErrorStr!=null&&!("").equals(dieOnErrorStr))?("true").equals(dieOnErrorStr):false;


boolean ignoreDTD="true".equals(ElementParameterParser.getValue(node, "__IGNORE_DTD__"));

String mode = ElementParameterParser.getValue(node, "__GENERATION_MODE__");

int schemaOptNum=100;
String schemaOptNumStr=ElementParameterParser.getValue(node, "__SCHEMA_OPT_NUM__");
if(schemaOptNumStr!=null && !"".equals(schemaOptNumStr) && !"\"\"".equals(schemaOptNumStr)){
	schemaOptNum  = Integer.parseInt(schemaOptNumStr);
}

boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));
		
/* -------------------------- */

    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    
      if(ignoreDTD){

    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    
	}

    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    
// *** Xerces *** //
if(("Xerces").equals(mode)){

    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
    if("true".equals(useSeparator)){
    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_73);
    stringBuffer.append(fieldSeparator );
    stringBuffer.append(TEXT_74);
    }
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(loopQuery);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_81);
    
      if(ignoreDTD){

    stringBuffer.append(TEXT_82);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_84);
    
      }

    stringBuffer.append(TEXT_82);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_92);
    stringBuffer.append(filename );
    stringBuffer.append(TEXT_93);
    if (dieOnError) {
    stringBuffer.append(TEXT_94);
    }else{
    stringBuffer.append(TEXT_95);
    if(isLog4jEnabled){
    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_97);
    }
    stringBuffer.append(TEXT_98);
    }
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_100);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_104);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_106);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_107);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_108);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_110);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_115);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_116);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(encoding);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_119);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_124);
    if (dieOnError) {
    stringBuffer.append(TEXT_125);
    }else{
    stringBuffer.append(TEXT_95);
    if(isLog4jEnabled){
    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_97);
    }
    stringBuffer.append(TEXT_126);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_127);
    }
    stringBuffer.append(TEXT_128);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_131);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_132);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_134);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_135);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_136);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_137);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(loopQuery);
    stringBuffer.append(TEXT_138);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_139);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_140);
    log4jFileUtil.startRetriveDataInfo();
    stringBuffer.append(TEXT_141);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_142);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_143);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_144);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_145);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_146);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_147);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_148);
    
// *** Dom4j *** //
}else if(("Dom4j").equals(mode)){

    stringBuffer.append(TEXT_149);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_150);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_151);
    
	if(ignore_NS){

    stringBuffer.append(TEXT_152);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_153);
    
	}

    stringBuffer.append(TEXT_154);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_155);
    
      if(ignoreDTD){

    stringBuffer.append(TEXT_156);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_84);
    
      }

    stringBuffer.append(TEXT_157);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_92);
    stringBuffer.append(filename );
    stringBuffer.append(TEXT_93);
    if (dieOnError) {
    stringBuffer.append(TEXT_94);
    }else{
    stringBuffer.append(TEXT_95);
    if(isLog4jEnabled){
    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_97);
    }
    stringBuffer.append(TEXT_98);
    }
    stringBuffer.append(TEXT_158);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_159);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_160);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_106);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_161);
    
	if(ignore_NS){

    stringBuffer.append(TEXT_162);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_163);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_164);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_165);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_166);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_167);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_168);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_169);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_167);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_170);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_171);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_172);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_173);
    stringBuffer.append(encoding );
    stringBuffer.append(TEXT_174);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_175);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_176);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_177);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_179);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_180);
    stringBuffer.append(tmp_filename );
    stringBuffer.append(TEXT_181);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_182);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_183);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_184);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_185);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_184);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_186);
    if (dieOnError) {
    stringBuffer.append(TEXT_94);
    }else{
    stringBuffer.append(TEXT_95);
    if(isLog4jEnabled){
    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_97);
    }
    stringBuffer.append(TEXT_98);
    }
    stringBuffer.append(TEXT_187);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_188);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_189);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_190);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_92);
    stringBuffer.append(tmp_filename );
    stringBuffer.append(TEXT_74);
    
	}

    stringBuffer.append(TEXT_191);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_192);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_193);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_194);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_195);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_196);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_197);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_167);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_198);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_199);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_200);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_201);
    stringBuffer.append(encoding);
    stringBuffer.append(TEXT_202);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_119);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_203);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_204);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_205);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_167);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_206);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_207);
    if (dieOnError) {
    stringBuffer.append(TEXT_125);
    }else{
    stringBuffer.append(TEXT_95);
    if(isLog4jEnabled){
    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_97);
    }
    stringBuffer.append(TEXT_126);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_127);
    }
    stringBuffer.append(TEXT_208);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_209);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_210);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_211);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_212);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_213);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_214);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_215);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_216);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_217);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_218);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_219);
    stringBuffer.append(loopQuery);
    stringBuffer.append(TEXT_220);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_221);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_222);
    if(ignore_NS){
    stringBuffer.append(TEXT_223);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_224);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_225);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_148);
    }
    stringBuffer.append(TEXT_226);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_227);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_228);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_229);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_230);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_231);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_232);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_233);
    
if(schemaOptNum>0){

    stringBuffer.append(TEXT_234);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_235);
    
	   int mappingSize=mapping.size();
		for (int i=0;i<mappingSize;i++) {
			String query = mapping.get(i).get("QUERY");
			if(i%schemaOptNum==0){
			
    stringBuffer.append(TEXT_236);
    stringBuffer.append(i/schemaOptNum);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_238);
    if(ignore_NS){
    stringBuffer.append(TEXT_239);
    }
    stringBuffer.append(TEXT_240);
    
			}
	      
    stringBuffer.append(TEXT_241);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_242);
    stringBuffer.append(query);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(loopQuery);
    stringBuffer.append(TEXT_243);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_244);
    
			if(ignore_NS){
	
    stringBuffer.append(TEXT_245);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_246);
    
			}
			
    stringBuffer.append(TEXT_247);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_248);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_249);
    
			if((i+1)%schemaOptNum==0){
	      
    stringBuffer.append(TEXT_250);
    
	      }
		}
		if(mappingSize>0&&(mappingSize%schemaOptNum)>0){
	   
    stringBuffer.append(TEXT_251);
    
	   }
	   
    stringBuffer.append(TEXT_252);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_253);
    if(ignore_NS){
    stringBuffer.append(TEXT_239);
    }
    stringBuffer.append(TEXT_254);
    
			    for (int i=0;i<=((mappingSize-1)/schemaOptNum);i++) {
			    
    stringBuffer.append(TEXT_255);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_256);
    if(ignore_NS){
    stringBuffer.append(TEXT_257);
    }
    stringBuffer.append(TEXT_258);
    
			    }
			    
    stringBuffer.append(TEXT_259);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_260);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_261);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_262);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_263);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_264);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_265);
    stringBuffer.append(cid);
    if(ignore_NS){
    stringBuffer.append(TEXT_266);
    stringBuffer.append(cid);
    }
    stringBuffer.append(TEXT_148);
    
}else{

    stringBuffer.append(TEXT_267);
    
}

    log4jFileUtil.startRetriveDataInfo();
    stringBuffer.append(TEXT_268);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_269);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_270);
    
// *** SAX *** //
}else if(("SAX").equals(mode)){

    stringBuffer.append(TEXT_271);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_272);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_273);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_274);
    
	boolean first = true;
	for(Map<String,String> path:mapping){

    stringBuffer.append(TEXT_275);
    stringBuffer.append(first?"":",");
    stringBuffer.append(path.get("QUERY"));
    
		String query = path.get("QUERY");
		if(query!=null && query.indexOf("@")<0){

    stringBuffer.append(TEXT_276);
    stringBuffer.append(query);
    stringBuffer.append(TEXT_277);
    
		}
		first=false;
	}

    stringBuffer.append(TEXT_278);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_279);
    
	first = true;
	for(Map<String,String> path:mapping){

    stringBuffer.append(TEXT_275);
    stringBuffer.append(first?"":",");
    stringBuffer.append("true".equals(path.get("NODECHECK"))? "true":"false" );
    
		String query = path.get("QUERY");
		if(query!=null && query.indexOf("@")<0){

    stringBuffer.append(TEXT_280);
    
		}
		first=false;
	}

    stringBuffer.append(TEXT_281);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_282);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_283);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_284);
    stringBuffer.append(loopQuery);
    stringBuffer.append(TEXT_285);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_286);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_148);
    if(ignoreDTD){
    stringBuffer.append(TEXT_287);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_288);
    }
    stringBuffer.append(TEXT_287);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_173);
    stringBuffer.append(encoding );
    stringBuffer.append(TEXT_289);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_92);
    stringBuffer.append(filename );
    stringBuffer.append(TEXT_93);
    if (dieOnError) {
    stringBuffer.append(TEXT_94);
    }else{
		if(isLog4jEnabled){
    stringBuffer.append(TEXT_290);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_291);
    }
    stringBuffer.append(TEXT_98);
    }
    stringBuffer.append(TEXT_158);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_159);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_160);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_106);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_292);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_293);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_294);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_295);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_296);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_297);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_298);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_299);
    log4jFileUtil.startRetriveDataInfo();
    stringBuffer.append(TEXT_300);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_301);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_302);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_303);
    
}
			// limit
		if (limit.compareTo("-1")!=0) {

    stringBuffer.append(TEXT_304);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_305);
    stringBuffer.append(limit );
    stringBuffer.append(TEXT_306);
    if(("SAX").equals(mode)) {
    stringBuffer.append(TEXT_307);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_308);
    }
    stringBuffer.append(TEXT_309);
     
		}
		
    stringBuffer.append(TEXT_310);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_311);
    
List< ? extends IConnection> conns = node.getOutgoingSortedConnections();
String firstConnName = "";
List<IMetadataTable> metadatas = node.getMetadataList();
if ((metadatas!=null)&&(metadatas.size()>0)) {
	IMetadataTable metadata = metadatas.get(0);
	if (metadata!=null) {
		if (conns!=null) {
//************ add for reject start*****************
		    String rejectConnName = "";
		    List<? extends IConnection> rejectConns = node.getOutgoingConnections("REJECT");
		    if(rejectConns != null && rejectConns.size() > 0) {
		        IConnection rejectConn = rejectConns.get(0);
		        rejectConnName = rejectConn.getName();
		    }
		    List<IMetadataColumn> rejectColumnList = null;
		    IMetadataTable metadataTable = node.getMetadataFromConnector("REJECT");
		    if(metadataTable != null) {
		        rejectColumnList = metadataTable.getListColumns();      
		    }
			for (int i=0;i<conns.size();i++) {
				IConnection connTemp = conns.get(i);
				if (connTemp.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {

    stringBuffer.append(TEXT_95);
    stringBuffer.append(connTemp.getName() );
    stringBuffer.append(TEXT_312);
    
				}
    		}
//***************************end********************
			Map<String,String> varsMap=new java.util.HashMap<String,String>();
			varsMap.put("xml_api_"+cid,"XML_API_"+cid);
			if("true".equals(useSeparator)){
				varsMap.put("contentTool_"+cid,"ContentTool_"+cid);
			}
			if(("Xerces").equals(mode)){
				varsMap.put("tmp_"+cid,"org.w3c.dom.Node");
				varsMap.put("node_"+cid,"org.w3c.dom.Node");
			}
			if(("Dom4j").equals(mode)){
				varsMap.put("node_"+cid,"org.dom4j.Node");
				varsMap.put("nsTool_"+cid,"NameSpaceTool_"+cid);
				varsMap.put("xmlNameSpaceMap_"+cid,"java.util.HashMap<String,String>");
				varsMap.put("temp_"+cid,"org.dom4j.tree.AbstractNode");
				if(ignore_NS){
					varsMap.put("namespaceContext_"+cid,"org.jaxen.NamespaceContext");
				}
				varsMap.put("xpaths_"+cid,"java.util.Map<Integer,org.dom4j.XPath>");
			}
			if(("SAX").equals(mode)){
				varsMap.put("row_"+cid,"java.util.Map<String, String>");
				varsMap.put("node_"+cid,"String[]");
			}
			class RowUtil extends DefaultRowUtil{
				public void codeForValueToConn(INode node, IMetadataTable metadata, String sourceValueClass, String sourceValueName, String targetConnName, int start , int end){
					cid = node.getUniqueName();
					boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));
					List<Map<String, String>> mapping = (List<Map<String,String>>)ElementParameterParser.getObjectValueXML(node, "__MAPPING__");
					List< ? extends IConnection> conns = node.getOutgoingSortedConnections();
					IConnection conn = conns.get(0);
					String advancedSeparatorStr = ElementParameterParser.getValue(node, "__ADVANCED_SEPARATOR__");
					boolean advancedSeparator = (advancedSeparatorStr!=null&&!("").equals(advancedSeparatorStr))?("true").equals(advancedSeparatorStr):false;
					String thousandsSeparator = ElementParameterParser.getValueWithJavaType(node, "__THOUSANDS_SEPARATOR__", JavaTypesManager.CHARACTER);
					String decimalSeparator = ElementParameterParser.getValueWithJavaType(node, "__DECIMAL_SEPARATOR__", JavaTypesManager.CHARACTER);
					
					String checkDateStr = ElementParameterParser.getValue(node,"__CHECK_DATE__");
					boolean checkDate = (checkDateStr!=null&&!("").equals(checkDateStr))?("true").equals(checkDateStr):false;
					boolean ignoreDTD="true".equals(ElementParameterParser.getValue(node, "__IGNORE_DTD__"));
					String encoding = ElementParameterParser.getValue(node, "__ENCODING__");
					
					String useSeparator = ElementParameterParser.getValue(node, "__USE_SEPARATOR__");
					
					String mode = ElementParameterParser.getValue(node, "__GENERATION_MODE__");
					
					for (int i=start;i<end;i++) { // S_for_a_1_0
						String query = mapping.get(i).get("QUERY");
						String nodeCheck = mapping.get(i).get("NODECHECK");
						if(("Xerces").equals(mode)){

    stringBuffer.append(TEXT_313);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_314);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(query);
    stringBuffer.append(TEXT_315);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_136);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_316);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(query);
    stringBuffer.append(TEXT_148);
    
							if("true".equals(useSeparator)){

    stringBuffer.append(TEXT_317);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_318);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_319);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_320);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_321);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_322);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_323);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_324);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_325);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_326);
    
							}else{

    stringBuffer.append(TEXT_327);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_324);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_325);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_328);
    
							}

    stringBuffer.append(TEXT_329);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_330);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_331);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_136);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_332);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(query);
    stringBuffer.append(TEXT_333);
    
						}else if(("Dom4j").equals(mode)){
							//TDI-18498
							boolean isWrongConfig = false;
							
							for(IMetadataColumn column:metadata.getListColumns()) {
								if (mapping.get(i).get("SCHEMA_COLUMN")!=null) {
									if (column.getLabel().compareTo(mapping.get(i).get("SCHEMA_COLUMN"))==0) {
										if("id_Document".equals(column.getTalendType()) && "false".equals(nodeCheck)) {
											isWrongConfig = true;
											break;
										}
									}
								}
							}
							
							if(isWrongConfig) {

    stringBuffer.append(TEXT_334);
    
							}

    stringBuffer.append(TEXT_335);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_336);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_337);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_338);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_339);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_340);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_336);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_341);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_140);
    
							if(("true").equals(nodeCheck)){

    stringBuffer.append(TEXT_327);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_140);
    
							
							}else{

    stringBuffer.append(TEXT_342);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_343);
    
						}

    stringBuffer.append(TEXT_344);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_336);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_345);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_346);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_336);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    
							if(("true").equals(nodeCheck)){

    stringBuffer.append(TEXT_327);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_324);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_347);
    
							
							}else{

    stringBuffer.append(TEXT_342);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_348);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_349);
    
							}

    stringBuffer.append(TEXT_350);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_336);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_351);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_336);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_352);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_353);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_354);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_355);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_336);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_356);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_336);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_357);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_358);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_336);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_359);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_360);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_361);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_362);
    
							if(("true").equals(nodeCheck)){

    stringBuffer.append(TEXT_327);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_324);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_363);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_347);
    
							
							}else{

    stringBuffer.append(TEXT_342);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_324);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_364);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_349);
    
							}

    stringBuffer.append(TEXT_365);
    
						}else if(("SAX").equals(mode)){

    stringBuffer.append(TEXT_366);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_367);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_338);
    stringBuffer.append(query);
    stringBuffer.append(TEXT_148);
    
							if(query!=null && query.indexOf("@")<0){

    stringBuffer.append(TEXT_368);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_369);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_370);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_338);
    stringBuffer.append(query);
    stringBuffer.append(TEXT_371);
    
							}else{

    stringBuffer.append(TEXT_368);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_369);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_372);
    
							}
						}
						for(IMetadataColumn column:metadata.getListColumns()) {  // S_for_0_1
							if (mapping.get(i).get("SCHEMA_COLUMN")!=null) { // S_if_0_2
								if (column.getLabel().compareTo(mapping.get(i).get("SCHEMA_COLUMN"))==0) { // S_if_0_3
									String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
									JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
									String patternValue = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
									
									boolean isNotSetDefault = false;
									String defaultValue=column.getDefault();
									if(defaultValue!=null){
										isNotSetDefault = defaultValue.length()==0;
									}else{
										isNotSetDefault=true;
									}
									if(("Dom4j").equals(mode) && ("true").equals(nodeCheck)){
										if("id_Document".equals(column.getTalendType())) {

    stringBuffer.append(TEXT_373);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_374);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_375);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(ignoreDTD);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(encoding);
    stringBuffer.append(TEXT_148);
    
										} else {

    stringBuffer.append(TEXT_373);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_374);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_376);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_74);
    
										}
										continue;
									}
									if(javaType == JavaTypesManager.STRING){
										if(column.isNullable()){

    stringBuffer.append(TEXT_377);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_378);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_379);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_374);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_380);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_381);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_382);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_374);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_383);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_384);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_385);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_374);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_386);
    stringBuffer.append(isNotSetDefault?null:column.getDefault());
    stringBuffer.append(TEXT_387);
    
										}else{ // column.isNullable()

    stringBuffer.append(TEXT_377);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_381);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_382);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_374);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_383);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_384);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_385);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_374);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_386);
    stringBuffer.append(isNotSetDefault?JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate):column.getDefault());
    stringBuffer.append(TEXT_387);
    
										}
									}else{ // other type
										if(column.isNullable()){

    stringBuffer.append(TEXT_388);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_378);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_379);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_374);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_389);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_381);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_390);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_384);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_379);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_374);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_391);
    stringBuffer.append(isNotSetDefault?null:column.getDefault());
    stringBuffer.append(TEXT_392);
    
								  		}else{ // column.isNullable()

    stringBuffer.append(TEXT_393);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_384);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_390);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_381);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_379);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_374);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_386);
    stringBuffer.append(isNotSetDefault?JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate):column.getDefault());
    stringBuffer.append(TEXT_392);
    
										}
									}
									if (javaType == JavaTypesManager.STRING || javaType == JavaTypesManager.OBJECT) {

    stringBuffer.append(TEXT_394);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_374);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_376);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_74);
    
									} else if (javaType == JavaTypesManager.DATE) {
										if(checkDate) {

    stringBuffer.append(TEXT_394);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_374);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_395);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_21);
    stringBuffer.append( patternValue );
    stringBuffer.append(TEXT_396);
      
										} else {

    stringBuffer.append(TEXT_394);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_374);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_395);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_21);
    stringBuffer.append( patternValue );
    stringBuffer.append(TEXT_148);
    
										}
									} else if(javaType == JavaTypesManager.BYTE_ARRAY){ 

    stringBuffer.append(TEXT_397);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_374);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_376);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_398);
    stringBuffer.append(encoding );
    stringBuffer.append(TEXT_148);
    
									} else if(advancedSeparator && JavaTypesManager.isNumberType(javaType)) { 

    stringBuffer.append(TEXT_394);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_374);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_399);
    stringBuffer.append( typeToGenerate );
    stringBuffer.append(TEXT_400);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_21);
    stringBuffer.append( thousandsSeparator );
    stringBuffer.append(TEXT_21);
    stringBuffer.append( decimalSeparator );
    stringBuffer.append(TEXT_401);
    
									} else {

    stringBuffer.append(TEXT_394);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_374);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_399);
    stringBuffer.append( typeToGenerate );
    stringBuffer.append(TEXT_402);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_148);
    
									}

    stringBuffer.append(TEXT_365);
    
								} //S_if_1_2
							} // S_if_1_1
						} // S_for_1_0
					} // S_for_a_1_0
				}
				
				public void codeForConnToConn(INode node, IMetadataTable metadata, String sourceConnName, String targetConnName, int start , int end){
	    			for(int colNum=start;colNum<end;colNum++) {
	    				IMetadataColumn column=metadata.getListColumns().get(colNum);

    stringBuffer.append(TEXT_403);
    stringBuffer.append(targetConnName);
    stringBuffer.append(TEXT_374);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(sourceConnName);
    stringBuffer.append(TEXT_374);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_74);
    
					}
				}
			}//class defined end
			RowUtil rowUtil = new RowUtil(); 
			if (conns.size()>0) { // S_if_a_0_0	
				IConnection conn = conns.get(0);
				firstConnName = conn.getName();
			    if (conns.size()>0 && firstConnName != null && firstConnName.length()>0) {
			    	rowUtil.genVarsParmsStr(varsMap);
					rowUtil.prepareValueToConn(node, metadata, "String", "str_"+cid, firstConnName, schemaOptNum);
					if(!("").equals(rejectConnName)&&!rejectConnName.equals(firstConnName)&&rejectColumnList != null && rejectColumnList.size() > 0) {
						rowUtil.prepareConnToConn(metadata, firstConnName, rejectConnName);
					}
					rowUtil.generateClass();
				}
				if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) { // add for reject

    stringBuffer.append(TEXT_404);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_405);
    stringBuffer.append(firstConnName );
    stringBuffer.append(TEXT_406);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_407);
    
	rowUtil.callValueToConn("str_"+cid, firstConnName);

     log4jFileUtil.debugRetriveData(node); 
    stringBuffer.append(TEXT_22);
    if(rejectConnName.equals(firstConnName)) {
    stringBuffer.append(TEXT_22);
    stringBuffer.append(firstConnName );
    stringBuffer.append(TEXT_408);
    }
    stringBuffer.append(TEXT_409);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_410);
    
        if (dieOnError) {
            
    stringBuffer.append(TEXT_411);
    
        } else {
			if(isLog4jEnabled){
    stringBuffer.append(TEXT_412);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_413);
    }
            if(!("").equals(rejectConnName)&&!rejectConnName.equals(firstConnName)&&rejectColumnList != null && rejectColumnList.size() > 0) {

                
    stringBuffer.append(TEXT_414);
    stringBuffer.append(rejectConnName );
    stringBuffer.append(TEXT_406);
    stringBuffer.append(rejectConnName );
    stringBuffer.append(TEXT_415);
    
                rowUtil.callConnToConn(firstConnName, rejectConnName);
                
    stringBuffer.append(TEXT_416);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_417);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_74);
    stringBuffer.append(TEXT_416);
    stringBuffer.append(firstConnName );
    stringBuffer.append(TEXT_140);
    
            } else if(("").equals(rejectConnName)){
                
    stringBuffer.append(TEXT_418);
    stringBuffer.append(TEXT_416);
    stringBuffer.append(firstConnName );
    stringBuffer.append(TEXT_140);
    
            } else if(rejectConnName.equals(firstConnName)){
    stringBuffer.append(TEXT_419);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_417);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_74);
    }
		}
        
    stringBuffer.append(TEXT_420);
    
				} //if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA))
			} // S_if_a_1_1

//***********************************
			if (conns.size()>0) {	
				boolean isFirstEnter = true;
				for (int i=0;i<conns.size();i++) {
					IConnection tmpconn = conns.get(i);
					if ((tmpconn.getName().compareTo(firstConnName)!=0)&&(tmpconn.getName().compareTo(rejectConnName)!=0)&&(tmpconn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA))) {

    stringBuffer.append(TEXT_394);
     if(isFirstEnter) {
    stringBuffer.append(TEXT_421);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_422);
     isFirstEnter = false; } 
    stringBuffer.append(TEXT_423);
    stringBuffer.append(tmpconn.getName() );
    stringBuffer.append(TEXT_424);
    stringBuffer.append(tmpconn.getName() );
    stringBuffer.append(TEXT_406);
    stringBuffer.append(tmpconn.getName() );
    stringBuffer.append(TEXT_425);
    
			    	 	for (IMetadataColumn column: metadata.getListColumns()) {

    stringBuffer.append(TEXT_426);
    stringBuffer.append(tmpconn.getName() );
    stringBuffer.append(TEXT_374);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_92);
    stringBuffer.append(firstConnName );
    stringBuffer.append(TEXT_374);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_427);
    
				 		}
					}
				}

    stringBuffer.append(TEXT_394);
     if(!isFirstEnter) {
    stringBuffer.append(TEXT_428);
     } 
    stringBuffer.append(TEXT_429);
    
			}
//***********************************

		} // if(conn!=null)
		

	}// if (metadata!=null)
} //if ((metadatas!=null)&&(metadatas.size()>0))

    stringBuffer.append(TEXT_430);
    stringBuffer.append(TEXT_52);
    return stringBuffer.toString();
  }
}
