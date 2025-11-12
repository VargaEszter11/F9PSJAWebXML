package F9PSJA_1112.xPathF9PSJA.xpathf9psja;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class xPathModifyF9PSJA {
    public static void main(String[] args) throws Exception {
        File inputFile = new File("/home/eszter/github/webeskorny/F9PSJAWebXML/F9PSJA_1112/xPathF9PSJA/xpathf9psja/studentF9PSJA.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(inputFile);

        XPath xPath = XPathFactory.newInstance().newXPath();
        Node node = (Node) xPath.evaluate("/class/student[@id='01']/keresztnev", doc, XPathConstants.NODE);

        if (node != null) {
            node.setTextContent("Anikó");
            System.out.println("Modified student with id=01:");
            Node parent = node.getParentNode();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(parent), new StreamResult(System.out));
        }
    }
}
