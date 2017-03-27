package model;

import management.GraphManagement;

import java.util.ArrayList;

/**
 * Created by raed on 23.03.17.
 */
public class CitiesPointsArray extends ArrayList<CityToDraw> {
    private GraphManagement graphManagement;

    public CitiesPointsArray(String filePath) {
        this.graphManagement = new GraphManagement(filePath);
        Net net = this.graphManagement.getNet();
        for (City c : net.getCityList()) {
            int y = c.getLatitude();
            int x = c.getLongitude();
            this.add(new CityToDraw(x * 3 - 1400, -(y * 3) + 900, c.getName()));
        }
    }

    public GraphManagement getGraphManagement() {
        return graphManagement;
    }

    public CityToDraw getCityToDrawByName(String name) {
        for (CityToDraw c : this) {
            if (c.getCityName().equals(name)) {
                return c;
            }
        }
        return null;
    }
}
