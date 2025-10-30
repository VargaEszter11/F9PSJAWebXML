package domf9psja1029;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

class DOMRead1 {
    
    public static void main(String argv[]) throws SAXException, IOException, ParserConfigurationException {
        
        File xmlFile = new File("domf9psja1029/F9PSJA_orarend.xml");
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        
        Document orarend = dBuilder.parse(xmlFile);
        
        orarend.getDocumentElement().normalize();
        
        System.out.println("Gyökér elem: " + orarend.getDocumentElement().getNodeName());
        
        NodeList nList = orarend.getElementsByTagName("ora");
        
        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);
            
            System.out.println("\nAktuális elem: " + nNode.getNodeName());
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                
                Element elem = (Element) nNode;
                
                String oid = elem.getAttribute("id");

                String tipus = elem.getAttribute("tipus");

                Node node1 = elem.getElementsByTagName("targy").item(0);
                String targy = node1.getTextContent();
                
                Node node2 = elem.getElementsByTagName("idopont").item(0);
                String idopont = node2.getTextContent();
                
                Node node3 = elem.getElementsByTagName("helyszin").item(0);
                String helyszin = node3.getTextContent();
                
                Node node4 = elem.getElementsByTagName("oktato").item(0);
                String oktato = node4.getTextContent();
                
                Node node5 = elem.getElementsByTagName("szak").item(0);
                String szak = node5.getTextContent();
                
                System.out.println("Ora id: " + oid);
                System.out.println("Típus: " + tipus);
                System.out.println("Targy: " + targy);
                System.out.println("Idopont: " + idopont);
                System.out.println("Helyszín: " + helyszin);
                System.out.println("Oktató: " + oktato);
                System.out.println("Szak: " + szak);
            }
        }
    }
}