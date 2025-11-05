package F9PSJA_1105;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public class DomQuery1F9PSJA {

    public static void main(String[] args) {
        try {
            File inputFile = new File("F9PSJA_orarend.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(true);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());

            NodeList oraList = doc.getElementsByTagName("ora");

            List<String> kurzusNevek = new ArrayList<>();
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                String targy = ora.getElementsByTagName("targy").item(0).getTextContent();
                kurzusNevek.add(targy);
            }

            System.out.println("\n1.) Kurzusnevek listája:");
            System.out.println("Kurzusnév: " + kurzusNevek);

            if (oraList.getLength() > 0) {
                Element elsoOra = (Element) oraList.item(0);
                StringBuilder sb = new StringBuilder();
                sb.append("\n2.) Az első kurzus adatai:\n");
                sb.append("ID: ").append(elsoOra.getAttribute("id")).append("\n");
                sb.append("Típus: ").append(elsoOra.getAttribute("tipus")).append("\n");

                sb.append("Tantárgy: ")
                  .append(elsoOra.getElementsByTagName("targy").item(0).getTextContent()).append("\n");

                Element idopont = (Element) elsoOra.getElementsByTagName("idopont").item(0);
                sb.append("Időpont: ")
                  .append(idopont.getElementsByTagName("nap").item(0).getTextContent()).append(" ")
                  .append(idopont.getElementsByTagName("tol").item(0).getTextContent())
                  .append(" - ")
                  .append(idopont.getElementsByTagName("ig").item(0).getTextContent()).append("\n");

                sb.append("Helyszín: ")
                  .append(elsoOra.getElementsByTagName("helyszin").item(0).getTextContent()).append("\n");

                sb.append("Oktató: ")
                  .append(elsoOra.getElementsByTagName("oktato").item(0).getTextContent()).append("\n");

                sb.append("Szak: ")
                  .append(elsoOra.getElementsByTagName("szak").item(0).getTextContent()).append("\n");

                System.out.println(sb.toString());

                try (FileWriter fw = new FileWriter("elsoOraQueryNeptunkod.txt")) {
                    fw.write(sb.toString());
                }
                System.out.println("Az első kurzus kiírása fájlba mentve: elsoOraQueryNeptunkod.txt");
            }

            List<String> oktatoNevek = new ArrayList<>();
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                String oktato = ora.getElementsByTagName("oktato").item(0).getTextContent();
                oktatoNevek.add(oktato);
            }

            System.out.println("\n3.) Oktatók listája:");
            System.out.println("Oktatók: " + oktatoNevek);

            System.out.println("\n4.) Összetett lekérdezés – Agárdi Anita kurzusai:");
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                String oktato = ora.getElementsByTagName("oktato").item(0).getTextContent();
                if (oktato.equalsIgnoreCase("Agárdi Anita")) {
                    String targy = ora.getElementsByTagName("targy").item(0).getTextContent();
                    String nap = ((Element) ora.getElementsByTagName("idopont").item(0))
                            .getElementsByTagName("nap").item(0).getTextContent();
                    String tol = ((Element) ora.getElementsByTagName("idopont").item(0))
                            .getElementsByTagName("tol").item(0).getTextContent();
                    String ig = ((Element) ora.getElementsByTagName("idopont").item(0))
                            .getElementsByTagName("ig").item(0).getTextContent();

                    System.out.println("• " + targy + " (" + nap + " " + tol + "-" + ig + ")");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

