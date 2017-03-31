package gui;

import model.*;
import model.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by raed on 30.03.17.
 */
public class ZoomCommand {
    private JPanel zoomPanel;
    private JButton zoomOutButton;
    private JButton zoomInButton;

    MapPointsArray mapPointsArray;
    CitiesPointsArray citiesPointsArray;
    PrincipalFrame principalFrame;

    public ZoomCommand(MapPointsArray mapPointsArray, CitiesPointsArray citiesPointsArray, PrincipalFrame principalFrame) {
        this.principalFrame = principalFrame;
        this.citiesPointsArray = citiesPointsArray;
        this.mapPointsArray = mapPointsArray;

        ImageIcon imageIcon1 = new ImageIcon(new
                ImageIcon("zoom_in.png").getImage().
                getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        zoomInButton.setIcon(imageIcon1);
        zoomInButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ImageIcon imageIcon2 = new ImageIcon(new
                ImageIcon("zoom_out.png").getImage().
                getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        zoomOutButton.setIcon(imageIcon2);
        zoomOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        zoomInButton.addActionListener(e -> zoomIn());
    }

    private void zoomIn() {
    }

    public JPanel getZoomPanel() {
        return zoomPanel;
    }
}
