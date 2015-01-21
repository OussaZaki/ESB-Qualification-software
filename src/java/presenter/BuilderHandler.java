/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Class used to build a XML Scenario
 * To write a scenario in a file, just use the createXmlFileFromObject method
 * @author Mike
 */
public class BuilderHandler {

	/**
         * Write the scenario in the appropriate file.
         * @param file
         * @param o 
         */
	public static void createXmlFileFromObject(File file, Object o)
            throws FileNotFoundException, JAXBException {
		if(o == null) {
                    throw new NullPointerException();
		}
	 	
		try {
			JAXBContext context = JAXBContext.newInstance(o.getClass());
			Marshaller m = context.createMarshaller();
			//for pretty-print XML in JAXB
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Write to System.out for debugging
			m.marshal(o, System.out);
		} catch (JAXBException e) {
                        throw e;
		}
		
		try {
			//ouverture fichier
			PrintStream printStream = new PrintStream(file);
			PrintStream console = System.out;
	
			//rediriger sortie standard pour utiliser la fonction printXML (modularite)
			System.setOut(printStream);
			toXML(o);//display on sta,dard output redirected
	
			//rediriger sortie standard normal (console)
			printStream.close();
			System.setOut(console);
		} catch (FileNotFoundException e) {
			throw e;
		}
	}

	/**
	 * Display XML content for Object o on the standard output.
	 * @param o
	 */
	public static void toXML(Object o){
		try {
			JAXBContext context = JAXBContext.newInstance(o.getClass());
			Marshaller m = context.createMarshaller();
			//for pretty-print XML in JAXB
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Write to System.out
			m.marshal(o, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
