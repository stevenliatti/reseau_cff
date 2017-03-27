package gui;

import model.*;
import model.Point;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * Created by raed on 23.03.17.
 */
public class DrawingPanel extends JPanel {
    private MapPointsArray mapPointsArray;
    private CitiesPointsArray citiesPointsArray;

    public DrawingPanel(MapPointsArray mapPointsArray, CitiesPointsArray citiesPointsArray) {
        this.mapPointsArray = mapPointsArray;
        this.citiesPointsArray = citiesPointsArray;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Random rand = new Random();
        Point firstPoint = this.mapPointsArray.get(0);
        Point previousPoint = new Point(firstPoint);
        g.setColor(Color.GRAY);
        for (int i = 1; i < this.mapPointsArray.size(); i++) {
            Point currentPoint = this.mapPointsArray.get(i);
            g.drawLine(previousPoint.getX(), previousPoint.getY(), currentPoint.getX(), currentPoint.getY());
            previousPoint = currentPoint;
        }
        g.drawLine(previousPoint.getX(), previousPoint.getY(), firstPoint.getX(), firstPoint.getY());

        g.setFont(new Font("Ubuntu", Font.BOLD, 16));
        for (CityToDraw p: this.citiesPointsArray) {
            g.setColor(Color.RED);
            g.fillRect(p.getX() - 3, p.getY() - 3, 6, 6);
            g.setColor(Color.BLACK);
            g.drawString(p.getCityName(), p.getX() - 20, p.getY() - 5);
        }

        List<Connection> connectionsList = this.citiesPointsArray.getGraphManagement().getNet().getConnectionList();
        for (Connection c : connectionsList) {
            CityToDraw cityToDraw1 = citiesPointsArray.getCityToDrawByName(c.getVil_1());
            CityToDraw cityToDraw2 = citiesPointsArray.getCityToDrawByName(c.getVil_2());
            if (cityToDraw1 != null && cityToDraw2 != null) {
                float red = rand.nextFloat();
                float green = rand.nextFloat();
                float blue = rand.nextFloat();

                int x1 = cityToDraw1.getX();
                int y1 = cityToDraw1.getY();
                int x2 = cityToDraw2.getX();
                int y2 = cityToDraw2.getY();
                g.setColor(new Color(red, green, blue));
                g.drawLine(x1, y1, x2, y2);
                int centerX = (x1 + x2) / 2;
                int centerY = (y1 + y2) / 2;
                g.drawString(String.valueOf(c.getDuratin()), centerX, centerY);
            }
        }
    }
}
