package hu.domparse.f9psja;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class F9PSJADOMModify {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("F9PSJA_XMLTask/F9PSJA_XML.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();

            // 1. módosítás: első hallgató neve
            Element firstHallgato = (Element) doc.getElementsByTagName("Hallgato").item(0);
            String oldName = firstHallgato.getElementsByTagName("Nev").item(0).getTextContent();
            firstHallgato.getElementsByTagName("Nev").item(0).setTextContent("Horváth Anna Mária");
            System.out.println("Hallgató neve módosítva: " + oldName + " → Horváth Anna Mária");

            // 2. módosítás: új kurzus beszúrása a többi kurzus után
            Node lastKurzus = doc.getElementsByTagName("Kurzus")
                    .item(doc.getElementsByTagName("Kurzus").getLength() - 1);

            Element newKurzus = doc.createElement("Kurzus");
            newKurzus.setAttribute("kid", "K3");

            Element kod = doc.createElement("Kod");
            kod.setTextContent("WEB3");
            Element nev = doc.createElement("Nev");
            nev.setTextContent("Webprogramozás alapjai");
            Element kredit = doc.createElement("Kredit");
            kredit.setTextContent("4");
            Element tipus = doc.createElement("Tipus");
            tipus.setTextContent("Előadás");
            Element tanarRef = doc.createElement("TanarRef");
            tanarRef.setTextContent("TA1");

            newKurzus.appendChild(kod);
            newKurzus.appendChild(nev);
            newKurzus.appendChild(kredit);
            newKurzus.appendChild(tipus);
            newKurzus.appendChild(tanarRef);

            root.insertBefore(newKurzus, lastKurzus.getNextSibling());
            System.out.println("Új kurzus hozzáadva: WEB3");

            // 3. módosítás: törölje az első Beiratkozás elemet
            Node firstBeiratkozas = doc.getElementsByTagName("Beiratkozas").item(0);
            root.removeChild(firstBeiratkozas);
            System.out.println("Első beiratkozás törölve.");

            // 4. módosítás: oktató e-mail frissítése
            Element oktato = (Element) doc.getElementsByTagName("Oktato").item(0);
            Node emailNode = oktato.getElementsByTagName("Email").item(0);
            String oldEmail = emailNode.getTextContent();
            emailNode.setTextContent("szabo.gabor@inf.unideb.hu");
            System.out.println("Oktató e-mail módosítva: " + oldEmail + " → szabo.gabor@inf.unideb.hu");

            // XML mentése fájlba
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(new DOMSource(doc), new StreamResult(new File("F9PSJA_XMLTask/F9PSJA_XML_modified.xml")));

            System.out.println("\nMódosítások elvégezve és mentve: F9PSJA_XML_modified.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
