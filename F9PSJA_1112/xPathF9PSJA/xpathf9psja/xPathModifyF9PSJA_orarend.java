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

public class xPathModifyF9PSJA_orarend {
    public static void main(String[] args) throws Exception {
        File inputFile = new File("/home/eszter/github/webeskorny/F9PSJAWebXML/F9PSJA_1112/xPathF9PSJA/xpathf9psja/F9PSJA_orarend.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(inputFile);
        doc.getDocumentElement().normalize();

        XPath xPath = XPathFactory.newInstance().newXPath();

        // Modification 1: change location of Webtechnológiák I. gyakorlat
        Node helyszin = (Node) xPath.evaluate("//ora[targy='Webtechnológiák I.' and @tipus='gyakorlat']/helyszin", doc,
                XPathConstants.NODE);
        if (helyszin != null)
            helyszin.setTextContent("In/202");

        // Modification 2: change teacher of Mobil programozás alapjai eloadas
        Node oktato = (Node) xPath.evaluate("//ora[targy='Mobil programozás alapjai' and @tipus='eloadas']/oktato", doc,
                XPathConstants.NODE);
        if (oktato != null)
            oktato.setTextContent("Kiss Balázs");

        // Modification 3: change time of UNIX rendszergazda gyakorlat
        Node tol = (Node) xPath.evaluate("//ora[targy='UNIX rendszergazda' and @tipus='gyakorlat']/idopont/tol", doc,
                XPathConstants.NODE);
        if (tol != null)
            tol.setTextContent("17:00");

        // Save modified XML
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(new File("orarendF9PSJA1.xml")));

        System.out.println("Modifications saved to orarendF9PSJA1.xml");
    }
}
