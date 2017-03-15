package model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by raed on 13.03.17.
 */
public class Main {
    public static void main(String[] args) {

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Reseau.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            File fileIn = new File("villes.xml");
            Reseau reseau = (Reseau) jaxbUnmarshaller.unmarshal(fileIn);

            print_reseau(reseau);


            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            File fileOut = new File("villes2.xml");
            jaxbMarshaller.marshal(reseau, fileOut);
            jaxbMarshaller.marshal(reseau, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    private static void print_reseau(Reseau reseau) {
        System.out.println("titre : " + reseau.getTitle());
        for (Ville v: reseau.getVilleList()) {
            System.out.println("ville nom : " + v.getNom());
            System.out.println("ville longitude : " + v.getLongitude());
            System.out.println("ville latitude : " + v.getLatitude());
        }
        for (Liaison v: reseau.getLiaisonList()) {
            System.out.println("Liaison vil_1 : " + v.getVil_1());
            System.out.println("Liaison vil_2 : " + v.getVil_2());
            System.out.println("Liaison temps : " + v.getTemps());
        }
    }
}
