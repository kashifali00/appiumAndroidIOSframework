package com.qa.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;

public class TestUtils {
    public final static long WAIT=10;
    public final static long newCommandTimeoutWait=10000;
    public final static long uiAutomatorSeverTimeoutWait=10000;
    public final static long appDurationTimeoutWait=10000;

    public HashMap<String, String> parseStringXML(InputStream file) throws Exception {
        HashMap<String,String> stringHashMap = new HashMap<String, String>();

        // Get document builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Build document
        Document document = builder.parse(file);

        //normalize the xml strutuctre and its very important
        document.getDocumentElement().normalize();

        //Get all the elements
        NodeList nodeList = document.getElementsByTagName("string");
        for(int iter=0; iter<nodeList.getLength(); iter++){
            Node node = nodeList.item(iter);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element)node;
                stringHashMap.put(element.getAttribute("name"),element.getTextContent());
            }
        }

        return stringHashMap;
    }
}
