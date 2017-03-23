package management;

import model.Net;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by raed on 23.03.17.
 */
public class XmlFileManagement {


    private static JAXBContext jaxbContext;

    public static Net loadXmlFile(String fileName) throws JAXBException {
        File file = new File(fileName);
        jaxbContext = JAXBContext.newInstance(Net.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Net) jaxbUnmarshaller.unmarshal(file);
    }

    public static void storeXmlFormat(String fileName, Net net) throws JAXBException {
        if (!fileName.toUpperCase().contains(".XML") ||
                fileName.toUpperCase().lastIndexOf(".XML") != (fileName.length() - 4)) {
            fileName += ".xml";
        }
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        File fileOut = new File(fileName);
        jaxbMarshaller.marshal(net, fileOut);
        //jaxbMarshaller.marshal(this.net, System.out);
    }
}
