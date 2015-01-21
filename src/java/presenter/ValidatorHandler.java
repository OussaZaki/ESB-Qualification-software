package presenter;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

/**
 *
 * @author Mike
 */
public class ValidatorHandler {

        /**
         * Validate an XML file against an XSD file
         * @param xmlFile
         * @param xsdFile
         * @return 
         */
	public static boolean isXmlValidAgainstXsd(File xmlFile, File xsdFile)
            throws SAXException, IOException {
		boolean validationOk = false;

		try {
			String schemaLang = "http://www.w3.org/2001/XMLSchema";

			SchemaFactory factory = SchemaFactory.newInstance(schemaLang);

			Schema schema = factory.newSchema(new StreamSource(xsdFile));
			Validator validator = schema.newValidator();

			validator.validate(new StreamSource(xmlFile));
                        validationOk = true;

		} catch (SAXException ex) {
                    throw ex;
                    //System.out.println("SAX exception :" + e.getMessage());
		} catch (IOException ex) {
                    throw ex;
                }

		return validationOk;
	}

}
