/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.utils.system;

import java.io.StringReader;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author cedric
 */
public class XMLParseUtil 
{
    private static ContentHandler handler;
      public static String getTagValue(String sTag, String eElement) throws Exception 
      {
	XMLReader myReader = XMLReaderFactory.createXMLReader();
        myReader.parse(new InputSource(new StringReader(sTag)));
        return (String) myReader.getProperty(eElement); 
      }
}
