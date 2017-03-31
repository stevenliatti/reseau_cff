package model;

/**
 * Created by raed on 23.03.17.
 */
public class CityToDraw {
    private String cityName;
    private Point coordinate;

    public CityToDraw(int x, int y, String cityName, boolean withScale) {
        this.coordinate = new Point(x, y, withScale);
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }
}
