package asynctasks;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Created by mac on 27/9/15.
 */
public class XpathUtil {

    public static List<String> getListObjects(final String xml, final int position, final String expresion) {
        NodeList nodes = XpathUtil.getXptathResult(xml,expresion);

        List<String> planlistValores = new ArrayList<String>();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            NodeList venueChildNodes = node.getChildNodes();
            NodeList nodeItem = venueChildNodes.item(position).getChildNodes();
            if(nodeItem.getLength()>0){
                String valor = nodeItem.item(0).getNodeValue();
                if(! "".equals(valor)){
                    planlistValores.add(valor);
                }

            }
        }

        return planlistValores;
    }


    public static Map<String,String> getMapObjects(final String xml, final int positionKey, final int positionValue ,  final String expresion) {
        NodeList nodes = XpathUtil.getXptathResult(xml,expresion);

        final Map<String,String> mapRequerimientos = new HashMap<String,String>();


        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            NodeList venueChildNodes = node.getChildNodes();
            NodeList nodeItemKey = venueChildNodes.item(positionKey).getChildNodes();
            NodeList nodeItemValue = venueChildNodes.item(positionValue).getChildNodes();
            String key = "";
            String value = "";

            if(nodeItemKey.getLength()>0){
                String valor = nodeItemKey.item(0).getNodeValue();
                    key = valor;
            }

            if(nodeItemValue.getLength()>0){
                String valor = nodeItemValue.item(0).getNodeValue();
                value = valor;
            }

            mapRequerimientos.put(key,value);


        }

        return mapRequerimientos;
    }




    public static  NodeList getXptathResult(final String xml, final String expression  ) {
        NodeList nodes = null;
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(new ByteArrayInputStream(xml.getBytes()));
            XPath xPath = XPathFactory.newInstance().newXPath();
            Object result = xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            nodes = (NodeList) result;
        } catch (SAXException e) {
        } catch (IOException e) {
        } catch (ParserConfigurationException e) {
        } catch (XPathExpressionException e) {
        }

        return nodes;

    }

}
