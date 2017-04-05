package gui;

import model.*;
import model.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
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

    public void parsePathToCities(String path) {
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
            String duration = String.valueOf(
                    citiesPointsArray.getCffCompute().outTimeTowCitiesFloyd(
                            cityToDraw1.getCityName(),
                            cityToDraw2.getCityName()
                    )
            );
            g.drawString(duration, centerX, centerY);
        }
    }

    private void drawAllConnections(Graphics g) {
//        Random rand = new Random();
        List<Connection> connectionsList = this.citiesPointsArray.getCffCompute().getNet().getConnectionList();
        for (Connection c : connectionsList) {
            CityToDraw cityToDraw1 = citiesPointsArray.getCityToDrawByName(c.getVil_1());
            CityToDraw cityToDraw2 = citiesPointsArray.getCityToDrawByName(c.getVil_2());
            if (cityToDraw1 != null && cityToDraw2 != null) {
                int x1 = cityToDraw1.getCoordinate().getX();
                int y1 = cityToDraw1.getCoordinate().getY();
                int x2 = cityToDraw2.getCoordinate().getX();
                int y2 = cityToDraw2.getCoordinate().getY();
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
            int x = p.getCoordinate().getX() - 3;
            int y = p.getCoordinate().getY() - 3;
            int width = 6;
            int height = 6;
            g.fillRect(x, y, width, height);
            g.setColor(Color.BLACK);
            x = p.getCoordinate().getX() - 20;
            y = p.getCoordinate().getY() - 5;
            g.drawString(p.getCityName(), x, y);
        }
    }

    private void drawMap(Graphics g) {
        Point firstPoint = this.mapPointsArray.get(0);
        Point previousPoint = this.mapPointsArray.get(0);
        g.setColor(Color.GRAY);
        for (int i = 1; i < this.mapPointsArray.size(); i++) {
            Point currentPoint = this.mapPointsArray.get(i);
            int x1 = previousPoint.getX();
            int y1 = previousPoint.getY();
            int x2 = currentPoint.getX();
            int y2 = currentPoint.getY();
            g.drawLine(x1, y1, x2, y2);
            previousPoint = currentPoint;
        }
        int x1 = previousPoint.getX();
        int y1 = previousPoint.getY();
        int x2 = firstPoint.getX();
        int y2 = firstPoint.getY();
        g.drawLine(x1, y1, x2, y2);
    }

    public void setPaintPathTowCities(boolean paintPathTowCities) {
        this.paintPathTowCities = paintPathTowCities;
    }
}
