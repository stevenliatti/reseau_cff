package management;

import model.City;
import model.Net;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by raed on 14.03.17.
 */
public class GraphManagement {
    private Net net;
    private ArrayList<String> cityNamesArrayList;

    public GraphManagement(String filePath) {
        try {

            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(Net.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            this.net = (Net) jaxbUnmarshaller.unmarshal(file);

            createCityNames();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void createCityNames() {
        cityNamesArrayList = new ArrayList<>();
        for (City c: net.getCityList()) {
            cityNamesArrayList.add(c.getName());
        }
    }

    public void displayCityNames() {
        for (int i = 0; i < cityNamesArrayList.size(); i++) {
            System.out.print("[" + i + ":" + cityNamesArrayList.get(i) + "] ");
        }
        System.out.println();
//        System.out.println("titre : " + net.getTitre());
//        for (City v: net.getCityList()) {
//            System.out.println("ville nom : " + v.getName());
//            System.out.println("ville longitude : " + v.getLongitude());
//            System.out.println("ville latitude : " + v.getLatitude());
//        }
//        for (Connection v: net.getConnectionList()) {
//            System.out.println("Connection vil_1 : " + v.getVil_1());
//            System.out.println("Connection vil_2 : " + v.getVil_2());
//            System.out.println("Connection temps : " + v.getTemps());
//        }
    }
}
