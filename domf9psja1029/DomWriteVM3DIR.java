package domf9psja1029;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomWriteVM3DIR {
    
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        
        // Beolvassuk a hallgatoF9PSJA.xml fájlt
        File xmlFile = new File("domf9psja1029/hallgatoF9PSJA.xml");
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);
        try {
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        } catch (ParserConfigurationException e) {
            // Ignore if features are not supported
        }
        
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        
        doc.getDocumentElement().normalize();
        
        System.out.println("=== XML dokumentum fastruktúrája ===\n");
        printTreeStructure(doc.getDocumentElement(), 0);
        
        // Transformer beállítása az XML fájlba való íráshoz
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        
        // Beállítjuk a dokumentum kódolását és behúzását
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        
        // DOMSource létrehozása
        DOMSource source = new DOMSource(doc);
        
        // StreamResult objektumok létrehozása konzolhoz és fájlhoz
        StreamResult console = new StreamResult(System.out);
        File outputFile = new File("domf9psja1029/hallgato1F9PSJA.xml");
        StreamResult file = new StreamResult(outputFile);
        
        System.out.println("\n\n=== XML tartalom konzolra írása ===\n");
        transformer.transform(source, console);
        
        System.out.println("\n\n=== XML tartalom fájlba írása: hallgato1F9PSJA.xml ===\n");
        transformer.transform(source, file);
        
        System.out.println("A fájl sikeresen létrehozva!");
    }
    
    /**
     * Rekurzív módon kiírja az XML dokumentum fastruktúráját
     */
    private static void printTreeStructure(Node node, int level) {
        if (node == null) {
            return;
        }
        
        // Behúzás a szintekhez
        String indent = "  ".repeat(level);
        
        // Csomópont típusa alapján különböző formátum
        switch (node.getNodeType()) {
            case Node.ELEMENT_NODE:
                Element element = (Element) node;
                System.out.print(indent + "<" + element.getTagName());
                
                // Attribútumok kiírása
                if (element.hasAttributes()) {
                    for (int i = 0; i < element.getAttributes().getLength(); i++) {
                        Node attr = element.getAttributes().item(i);
                        System.out.print(" " + attr.getNodeName() + "=\"" + attr.getNodeValue() + "\"");
                    }
                }
                
                System.out.print(">");
                
                // Ha van szöveges tartalom közvetlenül az elemben
                NodeList children = element.getChildNodes();
                boolean hasElementChildren = false;
                for (int i = 0; i < children.getLength(); i++) {
                    if (children.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        hasElementChildren = true;
                        break;
                    }
                }
                
                // Ha nincs elem gyerek, csak szöveg, akkor azt is kiírjuk
                if (!hasElementChildren && element.getTextContent().trim().length() > 0) {
                    System.out.print(element.getTextContent().trim());
                }
                
                System.out.println();
                
                // Rekurzív hívás a gyerekekre
                for (int i = 0; i < children.getLength(); i++) {
                    Node child = children.item(i);
                    if (child.getNodeType() == Node.ELEMENT_NODE) {
                        printTreeStructure(child, level + 1);
                    }
                }
                
                // Záró tag
                if (hasElementChildren || element.getTextContent().trim().length() == 0) {
                    System.out.println(indent + "</" + element.getTagName() + ">");
                } else {
                    System.out.print("</" + element.getTagName() + ">");
                }
                break;
                
            case Node.TEXT_NODE:
                String text = node.getTextContent().trim();
                if (!text.isEmpty()) {
                    System.out.println(indent + text);
                }
                break;
                
            default:
                break;
        }
    }
}