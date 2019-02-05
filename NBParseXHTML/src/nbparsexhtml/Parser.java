package nbparsexhtml;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Parser {

    private List libri;

    public Parser() {
        libri = new ArrayList();
    }

    public List parseDocument(String filename)
            throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document document;
        Element root, element;
        NodeList nodelist;
        Link libro;
        // creazione dell’albero DOM dal documento XML
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        document = builder.parse(filename);
        root = document.getDocumentElement();
        // generazione della lista degli elementi "libro"
        nodelist = root.getElementsByTagName("tr");
        if(nodelist.toString().equals("DISCIPLINA"))
       
            for (int i = 0; i < nodelist.getLength(); i++) {
                element = (Element) nodelist.item(i);
                libro = getLink(element);
                if (libro != null) {
                    libri.add(libro);
                }
            }
        return libri;
    }

    private Link getLink(Element element) {
        Link libro = null;
        String href = element.getAttribute("tr");
        Element elementParent = (Element) element.getParentNode().getParentNode();
        String materia = getTextValue(elementParent, "td", 0);
        String nome = getTextValue(elementParent, "td", 1);
        String giorno = getTextValue(elementParent, "td", 2);
        String ora= getTextValue(elementParent, "td", 3);
        
        return libro;
    }

    // restituisce il valore testuale dell’elemento figlio specificato
    private String getTextValue(Element element, String tag, int child) {
        String value = null;
        NodeList nodelist;
        nodelist = element.getElementsByTagName(tag);
        if (nodelist != null && nodelist.getLength() > child) {
            Node nodeChild = nodelist.item(child).getFirstChild();
            if ((nodeChild != null) && nodeChild.hasChildNodes()) {
                value = nodeChild.getFirstChild().getNodeValue();
            }
        }
        return value;
    }

}
