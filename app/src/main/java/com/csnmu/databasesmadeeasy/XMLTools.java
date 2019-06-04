package com.csnmu.databasesmadeeasy;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

class XMLTools {
    /**
     * Reads an XML file
     * @param xmlQuery XML query string
     * @return NodeList representing the XPath query
     */
    public static NodeList readAll(Context context, int xmlResId, String xmlQuery) {
        Document document = getDocument(context, xmlResId);
        try {
            return (NodeList) XPathFactory.newInstance().newXPath().compile(xmlQuery).evaluate(document, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return new NodeList() {
            @Override
            public Node item(int index) {
                return null;
            }

            @Override
            public int getLength() {
                return 0;
            }
        };
    }

    /**
     * Returns a document representing a XML resource
     *
     * @param context  application context
     * @param xmlResId XML resource ID
     * @return XML document
     */
    public static Document getDocument(Context context, int xmlResId) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputStream inputStream = context.getResources().openRawResource(xmlResId);
            return documentBuilder.parse(inputStream);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Runs an XPath Aggregation Query and returns the result, else returns -1;
     * @param context context
     * @param xmlResId resource file of xml document
     * @param xmlQuery xml aggregation query
     * @return returns the aggregated result
     */
    public static int aggregateQuery(Context context, int xmlResId, String xmlQuery) {
        Document document = getDocument(context, xmlResId);
        try {
            Object evaluate = XPathFactory.newInstance().newXPath().compile(xmlQuery).evaluate(document, XPathConstants.NUMBER);
            return (int) Math.round(Double.parseDouble(evaluate.toString()));
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
