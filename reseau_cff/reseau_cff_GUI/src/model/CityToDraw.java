package model;

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
}
