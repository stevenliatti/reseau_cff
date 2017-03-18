package model;

/**
 * Created by raed on 13.03.17.
 */

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"name", "longitude", "latitude"})
public class City {
    private String name;
    private int longitude;
    private int latitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "[" + name + ", " + longitude + ", " + latitude + "]";
    }
}
