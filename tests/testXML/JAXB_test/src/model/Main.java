package model;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Created by raed on 13.03.17.
 */
public class Main {
    public static void main(String[] args) {

        try {

            File file = new File("villes.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Reseau.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Reseau reseau = (Reseau) jaxbUnmarshaller.unmarshal(file);

            print_reseau(reseau);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    private static void print_reseau(Reseau reseau) {
        System.out.println("titre : " + reseau.getTitre());
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
