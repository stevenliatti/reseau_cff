package gui.listeners;

import gui.PrincipalFrame;
import model.CityToDraw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RemoveCityListener implements MouseListener {
    private PrincipalFrame principalFrame;

    public RemoveCityListener(PrincipalFrame principalFrame) {
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
        String city = null;
        for (CityToDraw c : principalFrame.getCitiesPointsArray()) {
            Rectangle r = new Rectangle(c.getCoordinate().getX() - 5, c.getCoordinate().getY() - 5,
                    10, 10);
            if (r.contains(cursorX, cursorY)) {
                city = c.getCityName();
            }
        }
        if (city != null && !city.isEmpty()) {
            int response = JOptionPane.showConfirmDialog(
                    principalFrame,
                    "Voulez-vous vraiment supprimer la ville \"" + city + "\"?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            if (response == JOptionPane.YES_OPTION) {
                principalFrame.removeCity(city);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
