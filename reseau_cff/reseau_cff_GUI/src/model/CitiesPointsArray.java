package model;

import core.CffCompute;

import java.util.ArrayList;

/**
 * Created by raed on 23.03.17.
 */
public class CitiesPointsArray extends ArrayList<CityToDraw> {
    private CffCompute cffCompute;

    public CitiesPointsArray(String filePath) {
        this.cffCompute = new CffCompute(filePath);
        Net net = this.cffCompute.getNet();
        for (City c : net.getCityList()) {
            int y = c.getLatitude();
            int x = c.getLongitude();
            this.add(new CityToDraw(x, y, c.getName()));
        }
    }

    public CffCompute getCffCompute() {
        return cffCompute;
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
