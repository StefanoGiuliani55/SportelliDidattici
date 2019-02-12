/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbparsexhtml;

import nbparsexhtml.Parser;
import nbparsexhtml.MyFile;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author giuliani_stefano
 */
public class SportelliToCSV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<NodeList> tabella = null;
        Parser dom = new Parser();
        MyFile file = new MyFile();
        try {
            tabella = dom.parseDocumentV2("sportello.xml");
            String CSV = dom.ArraytoCSVMIO(tabella);
            //System.out.println(CSV); /* DEBUG*/
            file.Sovrascrivi("sportelli.csv", CSV);
            
        } catch (ParserConfigurationException | SAXException | IOException exception) {
            System.out.println("Errore!");
        }

    }
    
}
