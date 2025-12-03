package hu.domparse.f9psja;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

public class F9PSJADOMRead {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("F9PSJA_XMLTask/F9PSJA_XML.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            String outFileName = "F9PSJA_XMLTask/F9PSJA_DOMRead_output.txt";
            try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outFileName), "UTF-8"))) {

                String rootName = doc.getDocumentElement().getNodeName();
                printlnBoth("Gyökérelem: " + rootName, writer);
                printlnBoth("=========================================", writer);

                NodeList topChildren = doc.getDocumentElement().getChildNodes();
                for (int i = 0; i < topChildren.getLength(); i++) {
                    Node n = topChildren.item(i);
                    if (n.getNodeType() != Node.ELEMENT_NODE) continue;
                    Element e = (Element) n;

                    List<Element> siblings = getSameTagSiblings(e);
                    if (siblings.size() > 1) {
                        if (!isFirstOccurrence(e)) continue;
                        processGroup(siblings, writer);
                    } else {
                        processElement(e, writer, 0);
                    }
                }

                printlnBoth("\nMinden adat kiírva a fájlba: " + outFileName, writer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Kiírás konzolra és fájlba
    private static void printlnBoth(String s, PrintWriter writer) {
        System.out.println(s);
        writer.println(s);
    }

    private static List<Element> getSameTagSiblings(Element e) {
        List<Element> list = new ArrayList<>();
        Node parent = e.getParentNode();
        NodeList children = parent.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node n = children.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element) n;
                if (el.getTagName().equals(e.getTagName())) list.add(el);
            }
        }
        return list;
    }

    private static boolean isFirstOccurrence(Element e) {
        Node parent = e.getParentNode();
        NodeList children = parent.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node n = children.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element) n;
                if (el.getTagName().equals(e.getTagName())) {
                    return el == e;
                }
            }
        }
        return true;
    }

    // Feldolgoz egy csoportot
    private static void processGroup(List<Element> group, PrintWriter writer) {
        String groupName = group.get(0).getTagName();
        printlnBoth("\n=== " + groupName.toUpperCase() + " (összesen: " + group.size() + ") ===", writer);
        for (int i = 0; i < group.size(); i++) {
            Element e = group.get(i);
            printlnBoth("\n--- " + groupName + " #" + (i + 1) + " ---", writer);
            processElement(e, writer, 2);
        }
    }

    // Feldolgoz egy elemet
    private static void processElement(Element elem, PrintWriter writer, int indent) {
        String indentStr = " ".repeat(indent);
        NamedNodeMap attrs = elem.getAttributes();
        if (attrs.getLength() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(indentStr).append("Attribútumok: ");
            for (int i = 0; i < attrs.getLength(); i++) {
                Node a = attrs.item(i);
                sb.append(a.getNodeName()).append("=").append(a.getNodeValue());
                if (i < attrs.getLength() - 1) sb.append(", ");
            }
            printlnBoth(sb.toString(), writer);
        }

        Map<String, List<Element>> childrenByTag = new LinkedHashMap<>();
        NodeList children = elem.getChildNodes();
        boolean hasTextDirect = false;
        String directText = null;
        for (int i = 0; i < children.getLength(); i++) {
            Node c = children.item(i);
            if (c.getNodeType() == Node.ELEMENT_NODE) {
                Element ce = (Element) c;
                childrenByTag.computeIfAbsent(ce.getTagName(), k -> new ArrayList<>()).add(ce);
            } else if (c.getNodeType() == Node.TEXT_NODE) {
                String t = c.getTextContent().trim();
                if (!t.isEmpty()) {
                    hasTextDirect = true;
                    directText = t;
                }
            }
        }

        if (hasTextDirect && childrenByTag.isEmpty()) {
            printlnBoth(indentStr + elem.getTagName() + ": " + directText, writer);
            return;
        }

        for (Map.Entry<String, List<Element>> entry : childrenByTag.entrySet()) {
            String tag = entry.getKey();
            List<Element> list = entry.getValue();

            if (list.size() == 1) {
                Element child = list.get(0);
                if (hasOnlyText(child)) {
                    printlnBoth(indentStr + tag + ": " + child.getTextContent().trim(), writer);
                } else {
                    printlnBoth(indentStr + tag + ":", writer);
                    processElement(child, writer, indent + 2);
                }
            } else {
                StringBuilder multi = new StringBuilder();
                boolean allText = true;
                for (int i = 0; i < list.size(); i++) {
                    Element child = list.get(i);
                    if (!hasOnlyText(child)) allText = false;
                    multi.append(child.getTextContent().trim());
                    if (i < list.size() - 1) multi.append(", ");
                }
                if (allText) {
                    printlnBoth(indentStr + tag + "s: " + multi.toString(), writer);
                } else {
                    printlnBoth(indentStr + tag + " (lista):", writer);
                    for (int i = 0; i < list.size(); i++) {
                        printlnBoth(indentStr + " - " + tag + " #" + (i + 1), writer);
                        processElement(list.get(i), writer, indent + 4);
                    }
                }
            }
        }
    }

    private static boolean hasOnlyText(Element e) {
        NodeList children = e.getChildNodes();
        int elemCount = 0;
        int textCount = 0;
        for (int i = 0; i < children.getLength(); i++) {
            Node n = children.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) elemCount++;
            if (n.getNodeType() == Node.TEXT_NODE) {
                if (!n.getTextContent().trim().isEmpty()) textCount++;
            }
        }
        return elemCount == 0 && textCount > 0;
    }
}
