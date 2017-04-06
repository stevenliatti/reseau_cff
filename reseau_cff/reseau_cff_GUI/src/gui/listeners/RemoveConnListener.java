package gui.listeners;

import gui.PrincipalFrame;
import model.CityToDraw;
import model.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RemoveConnListener implements MouseListener {
    private PrincipalFrame principalFrame;

    public RemoveConnListener(PrincipalFrame principalFrame) {
        this.principalFrame = principalFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int cursorX = e.getX();
        int cursorY = e.getY();
        for (Connection c : principalFrame.getCffCompute().getNet().getConnectionList()) {
            CityToDraw cityToDraw1 = principalFrame.getCitiesPointsArray().getCityToDrawByName(c.getVil_1());
            CityToDraw cityToDraw2 = principalFrame.getCitiesPointsArray().getCityToDrawByName(c.getVil_2());
            if (cityToDraw1 != null && cityToDraw2 != null) {
                int x1 = cityToDraw1.getCoordinate().getX();
                int y1 = cityToDraw1.getCoordinate().getY();
                int x2 = cityToDraw2.getCoordinate().getX();
                int y2 = cityToDraw2.getCoordinate().getY();

                double pente = (y1 - y2) / (x1 - x2);
                Polygon polygon;
                if (Math.abs(pente) < 1) {
                    polygon = new Polygon(
                            new int[]{x1, x2, x2, x1},
                            new int[]{y1 - 5, y2 - 5, y2 + 5, y1 + 5},
                            4);
                } else {
                    polygon = new Polygon(
                            new int[]{x1 - 5, x2 - 5, x2 + 5, x1 + 5},
                            new int[]{y1, y2, y2, y1},
                            4);
                }
                if (polygon.contains(cursorX, cursorY)) {
                    int response = JOptionPane.showConfirmDialog(
                    principalFrame,
                    "Voulez-vous vraiment supprimer la liaison \"" + cityToDraw1.getCityName() + "\" <=> \"" + cityToDraw2.getCityName() + "\"?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                    );
                    if (response == JOptionPane.YES_OPTION) {
                        principalFrame.removeConnection(cityToDraw1.getCityName(), cityToDraw2.getCityName());
                    }
                    break;
                }
            }
        }



//
//        String city = null;
//        for (CityToDraw c : principalFrame.getCitiesPointsArray()) {
//            Rectangle r = new Rectangle(c.getCoordinate().getX() - 5, c.getCoordinate().getY() - 5,
//                    10, 10);
//            if (r.contains(cursorX, cursorY)) {
//                city = c.getCityName();
//            }
//        }
//        if (city != null && !city.isEmpty()) {
//            int response = JOptionPane.showConfirmDialog(
//                    principalFrame,
//                    "Voulez-vous vraiment supprimer la ville \"" + city + "\"?",
//                    "Confirmation",
//                    JOptionPane.YES_NO_OPTION,
//                    JOptionPane.WARNING_MESSAGE
//            );
//            if (response == JOptionPane.YES_OPTION) {
//                principalFrame.removeCity(city);
//            }
//        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
