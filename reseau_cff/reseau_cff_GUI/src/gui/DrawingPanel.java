package gui;

import model.*;
import model.Point;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by raed on 23.03.17.
 */
public class DrawingPanel extends JPanel {
    private MapPointsArray mapPointsArray;
    private CitiesPointsArray citiesPointsArray;

    private boolean paintPathTowCities;
    private String[] cities;

    public DrawingPanel(MapPointsArray mapPointsArray, CitiesPointsArray citiesPointsArray) {
        this.mapPointsArray = mapPointsArray;
        this.citiesPointsArray = citiesPointsArray;
        this.paintPathTowCities = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
        drawCities(g);
        drawAllConnections(g);
        if (paintPathTowCities) {
            drawConnectionTowCities(g);
        }
    }

    protected void paintConnectionTowCities(String path) {
        this.paintPathTowCities = true;
        path = path.replace("[", "");
        path = path.replace("]", "");
        cities = path.split(":");

    }

    private void drawConnectionTowCities(Graphics g) {
        g.setColor(Color.red);
        for (int i = 0; i < cities.length - 1; i++) {
            CityToDraw cityToDraw1 = citiesPointsArray.getCityToDrawByName(cities[i]);
            CityToDraw cityToDraw2 = citiesPointsArray.getCityToDrawByName(cities[i + 1]);
            int x1 = cityToDraw1.getCoordinate().getX();
            int y1 = cityToDraw1.getCoordinate().getY();
            int x2 = cityToDraw2.getCoordinate().getX();
            int y2 = cityToDraw2.getCoordinate().getY();
            g.drawLine(x1, y1, x2, y2);
            int centerX = (x1 + x2) / 2;
            int centerY = (y1 + y2) / 2;
            String duration = String.valueOf(getTowCitiesDuration(cityToDraw1.getCityName(), cityToDraw2.getCityName()));
            g.drawString(duration, centerX, centerY);
        }
    }

    private int getTowCitiesDuration(String c1, String c2) {
        List<Connection> connectionsList = this.citiesPointsArray.getCffCompute().getNet().getConnectionList();
        for (Connection c : connectionsList) {
            if (c.getVil_1().equals(c1) && c.getVil_2().equals(c2) ||
                    c.getVil_1().equals(c2) && c.getVil_2().equals(c1)) {
                return c.getDuratin();
            }
        }
        return 0;
    }

    private void drawAllConnections(Graphics g) {
//        Random rand = new Random();
        List<Connection> connectionsList = this.citiesPointsArray.getCffCompute().getNet().getConnectionList();
        for (Connection c : connectionsList) {
            CityToDraw cityToDraw1 = citiesPointsArray.getCityToDrawByName(c.getVil_1());
            CityToDraw cityToDraw2 = citiesPointsArray.getCityToDrawByName(c.getVil_2());
            if (cityToDraw1 != null && cityToDraw2 != null) {
//                float red = rand.nextFloat();
//                float green = rand.nextFloat();
//                float blue = rand.nextFloat();

                int x1 = cityToDraw1.getCoordinate().getX();
                int y1 = cityToDraw1.getCoordinate().getY();
                int x2 = cityToDraw2.getCoordinate().getX();
                int y2 = cityToDraw2.getCoordinate().getY();
//                g.setColor(new Color(red, green, blue));
                g.setColor(Color.lightGray);
                g.drawLine(x1, y1, x2, y2);
                int centerX = (x1 + x2) / 2;
                int centerY = (y1 + y2) / 2;
                g.drawString(String.valueOf(c.getDuratin()), centerX, centerY);
            }
        }
    }

    private void drawCities(Graphics g) {
        g.setFont(new Font("Ubuntu", Font.BOLD, 16));
        for (CityToDraw p: this.citiesPointsArray) {
            g.setColor(Color.blue);
            g.fillRect(p.getCoordinate().getX() - 3, p.getCoordinate().getY() - 3, 6, 6);
            g.setColor(Color.BLACK);
            g.drawString(p.getCityName(), p.getCoordinate().getX() - 20, p.getCoordinate().getY() - 5);
        }
    }

    private void drawMap(Graphics g) {
        Point firstPoint = this.mapPointsArray.get(0);
        Point previousPoint = new Point(firstPoint);
        g.setColor(Color.GRAY);
        for (int i = 1; i < this.mapPointsArray.size(); i++) {
            Point currentPoint = this.mapPointsArray.get(i);
            g.drawLine(previousPoint.getX(), previousPoint.getY(), currentPoint.getX(), currentPoint.getY());
            previousPoint = currentPoint;
        }
        g.drawLine(previousPoint.getX(), previousPoint.getY(), firstPoint.getX(), firstPoint.getY());
    }
}
