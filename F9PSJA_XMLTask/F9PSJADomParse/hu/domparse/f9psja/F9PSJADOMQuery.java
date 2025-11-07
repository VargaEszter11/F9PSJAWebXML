package hu.domparse.f9psja;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class F9PSJADOMQuery {

    public static void main(String[] args) {
        try {
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(new File("F9PSJA_XML.xml"));
            doc.getDocumentElement().normalize();

            System.out.println("===== LEKÉRDEZÉSEK =====");

            // 1. Lekérdezés: minden hallgató neve
            NodeList hallgatok = doc.getElementsByTagName("Hallgato");
            System.out.println("1. Minden hallgató neve:");
            for (int i = 0; i < hallgatok.getLength(); i++) {
                Element h = (Element) hallgatok.item(i);
                System.out.println("- " + h.getElementsByTagName("Nev").item(0).getTextContent());
            }

            // 2. Lekérdezés: 5 kredites kurzusok
            NodeList kurzusok = doc.getElementsByTagName("Kurzus");
            System.out.println("\n2. 5 kredites kurzusok:");
            for (int i = 0; i < kurzusok.getLength(); i++) {
                Element k = (Element) kurzusok.item(i);
                if (Integer.parseInt(k.getElementsByTagName("Kredit").item(0).getTextContent()) == 5) {
                    System.out.println("- " + k.getElementsByTagName("Nev").item(0).getTextContent());
                }
            }

            // 3. Lekérdezés: oktatók email címei
            NodeList oktatok = doc.getElementsByTagName("Oktato");
            System.out.println("\n3. Oktatók email címei:");
            for (int i = 0; i < oktatok.getLength(); i++) {
                Element o = (Element) oktatok.item(i);
                System.out.println(o.getElementsByTagName("Nev").item(0).getTextContent() + ": "
                        + o.getElementsByTagName("Email").item(0).getTextContent());
            }

            // 4. Lekérdezés: minden beiratkozás, ahol „Nem” a teljesítés
            NodeList beiratkozasok = doc.getElementsByTagName("Beiratkozas");
            System.out.println("\n4. Sikertelen teljesítések:");
            for (int i = 0; i < beiratkozasok.getLength(); i++) {
                Element b = (Element) beiratkozasok.item(i);
                if (b.getElementsByTagName("Teljesitve").item(0).getTextContent().equals("Nem")) {
                    System.out.println("- " + b.getAttribute("bid"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

