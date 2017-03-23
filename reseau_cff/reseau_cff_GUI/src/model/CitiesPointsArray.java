package model;

import xml.XmlFileManagement;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;

/**
 * Created by raed on 23.03.17.
 */
public class CitiesPointsArray extends ArrayList<CityToDraw> {
    public CitiesPointsArray(String filePath) {
        try {
            Net net = XmlFileManagement.loadXmlFile(filePath);
            for (City c : net.getCityList()) {
                int y = c.getLatitude();
                int x = c.getLongitude();
                this.add(new CityToDraw(x * 3 - 1400, -(y * 3) + 900, c.getName()));
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
