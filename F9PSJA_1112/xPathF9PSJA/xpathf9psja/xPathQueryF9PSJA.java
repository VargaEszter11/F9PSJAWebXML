package F9PSJA_1112.xPathF9PSJA.xpathf9psja;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class xPathQueryF9PSJA {
    public static void main(String[] args) throws Exception {
        File inputFile = new File("/home/eszter/github/webeskorny/F9PSJAWebXML/F9PSJA_1112/xPathF9PSJA/xpathf9psja/studentF9PSJA.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(inputFile);
        doc.getDocumentElement().normalize();

        XPath xPath = XPathFactory.newInstance().newXPath();

        System.out.println("All student names:");
        NodeList list1 = (NodeList) xPath.evaluate("/class/student/keresztnev", doc, XPathConstants.NODESET);
        for (int i = 0; i < list1.getLength(); i++)
            System.out.println(list1.item(i).getTextContent());

        System.out.println("\nStudents older than 20:");
        NodeList list2 = (NodeList) xPath.evaluate("/class/student[kor>20]/keresztnev", doc, XPathConstants.NODESET);
        for (int i = 0; i < list2.getLength(); i++)
            System.out.println(list2.item(i).getTextContent());

        System.out.println("\nNicknames:");
        NodeList list3 = (NodeList) xPath.evaluate("/class/student/becenev", doc, XPathConstants.NODESET);
        for (int i = 0; i < list3.getLength(); i++)
            System.out.println(list3.item(i).getTextContent());
    }
}
