package model;

/**
 * Created by raed on 23.03.17.
 */
public class CityToDraw extends Point {
    private String cityName;

    public CityToDraw(int x, int y, String cityName) {
        super(x, y);
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }
}
