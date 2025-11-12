package F9PSJA_1112.xPathF9PSJA.xpathf9psja;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class xPathQueryF9PSJA_orarend {
    public static void main(String[] args) throws Exception {
        File inputFile = new File("/home/eszter/github/webeskorny/F9PSJAWebXML/F9PSJA_1112/xPathF9PSJA/xpathf9psja/F9PSJA_orarend.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(inputFile);
        doc.getDocumentElement().normalize();

        XPath xPath = XPathFactory.newInstance().newXPath();

        System.out.println("All course names:");
        NodeList list1 = (NodeList) xPath.evaluate("//targy", doc, XPathConstants.NODESET);
        for (int i = 0; i < list1.getLength(); i++)
            System.out.println(list1.item(i).getTextContent());

        System.out.println("\nAll gyakorlat classes:");
        NodeList list2 = (NodeList) xPath.evaluate("//ora[@tipus='gyakorlat']/targy", doc, XPathConstants.NODESET);
        for (int i = 0; i < list2.getLength(); i++)
            System.out.println(list2.item(i).getTextContent());

        System.out.println("\nSubjects taught by Agárdi Anita:");
        NodeList list3 = (NodeList) xPath.evaluate("//ora[oktato='Agárdi Anita']/targy", doc, XPathConstants.NODESET);
        for (int i = 0; i < list3.getLength(); i++)
            System.out.println(list3.item(i).getTextContent());
    }
}
