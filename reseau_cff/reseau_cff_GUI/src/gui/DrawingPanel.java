package gui;

import model.CitiesPointsArray;
import model.CityToDraw;
import model.Point;
import model.MapPointsArray;

import javax.swing.*;
import java.awt.*;

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
        Point firstPoint = this.mapPointsArray.get(0);
        Point previousPoint = new Point(firstPoint);
        for (int i = 1; i < this.mapPointsArray.size(); i++) {
            Point currentPoint = this.mapPointsArray.get(i);
            g.drawLine(previousPoint.getX(), previousPoint.getY(), currentPoint.getX(), currentPoint.getY());
            previousPoint = currentPoint;
        }
        g.drawLine(previousPoint.getX(), previousPoint.getY(), firstPoint.getX(), firstPoint.getY());

        g.setFont(new Font("Ubuntu", Font.BOLD, 16));
        for (CityToDraw p: this.citiesPointsArray) {
            g.setColor(Color.RED);
            g.fillRect(p.getX(), p.getY(), 5, 5);
            g.setColor(Color.GRAY);
            g.drawString(p.getCityName(), p.getX() + 5, p.getY() + 15);
        }
    }
}
