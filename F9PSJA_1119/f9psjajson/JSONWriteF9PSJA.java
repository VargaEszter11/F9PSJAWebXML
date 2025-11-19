package f9psjajson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONWriteF9PSJA {

    public static void main(String[] args) {
        String inputFile = "orarendF9PSJA.json";
        String outputFile = "orarendF9PSJA1.json";

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(inputFile)) {
            Object obj = parser.parse(reader);
            JSONObject root = (JSONObject) obj;

            JSONObject orarendObj = (JSONObject) root.get("F9PSJA_orarend");
            JSONArray orak = (JSONArray) orarendObj.get("ora");

            int index = 1;
            for (Object o : orak) {
                JSONObject ora = (JSONObject) o;
                System.out.println("===== Óra #" + index + " =====");
                System.out.println("targy: " + ora.get("targy"));

                JSONObject idopont = (JSONObject) ora.get("idopont");
                if (idopont != null) {
                    System.out.println("idopont.nap: " + idopont.get("nap"));
                    System.out.println("idopont.tol: " + idopont.get("tol"));
                    System.out.println("idopont.ig: " + idopont.get("ig"));
                }

                System.out.println("helyszin: " + ora.get("helyszin"));
                System.out.println("oktato: " + ora.get("oktato"));
                System.out.println("szak: " + ora.get("szak"));
                System.out.println("_id: " + ora.get("_id"));
                System.out.println("_tipus: " + ora.get("_tipus"));
                System.out.println();

                index++;
            }

            try (FileWriter writer = new FileWriter(outputFile)) {

                org.json.JSONObject pretty = new org.json.JSONObject(root.toJSONString());

                writer.write(pretty.toString(4));
                writer.flush();

                System.out.println("Pretty JSON sikeresen kiírva ide: " + outputFile);
            }

        } catch (IOException e) {
            System.out.println("Hiba fájlművelet közben: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Hiba a JSON feldolgozásakor: " + e.getMessage());
        }
    }
}
