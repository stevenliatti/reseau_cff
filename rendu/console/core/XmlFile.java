package core;

import model.Net;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

/**
 * Classe permettant de lire et écrire un fichier XML de données.
 * @author Raed Abdennadher
 * @author Steven Liatti
 */
public class XmlFile {
    private static JAXBContext jaxbContext;

    /**
     * Crée un réseau {@link Net} à partir d'un fichier XML donné en paramètres.
     * @param fileName Le nom du fichier à lire.
     * @return Un réseau de villes et connexions
     * @throws JAXBException
     */
    public static Net loadXmlFile(String fileName) throws JAXBException {
        InputStream file = XmlFile.class.getResourceAsStream(fileName);
        jaxbContext = JAXBContext.newInstance(Net.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Net) jaxbUnmarshaller.unmarshal(file);
    }

    /**
     * Sauvegarde un {@link Net} donné en paramètres dans un fichier.
     * @param fileName Le nom du fichier
     * @param net Le réseau/graphe courant
     * @throws JAXBException
     */
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
    }
}
