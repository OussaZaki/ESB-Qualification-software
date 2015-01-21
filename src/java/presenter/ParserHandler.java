/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Mike
 */
public class ParserHandler {
	
        /**
         * parse an XmlFile and
         * return an instance of an Object specified by the Class c
         * @param xmlFile
         * @param c
         * @return 
         */
	public static Object getInstanceFromXmlFile(File xmlFile, Class<?> c)
            throws JAXBException {
		Object o = new Object();
		try {
			//create JAXBContext and Unmarshaller
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller u = context.createUnmarshaller();
	
			//create object from XML
			o = u.unmarshal(xmlFile);
		} catch (JAXBException ex) {
                    throw ex;
		}
		
		return o;
	}
    
}
