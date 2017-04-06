package gui.listeners;

import gui.PrincipalFrame;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddCityListener implements MouseListener{
    private PrincipalFrame principalFrame;
    public AddCityListener(PrincipalFrame principalFrame) {
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
        String cityName;
        do {
            cityName = JOptionPane.showInputDialog(this.principalFrame, null, "Nouvelle ville", JOptionPane.QUESTION_MESSAGE);
        } while (cityName != null && cityName.isEmpty());
        if (cityName != null) {
            principalFrame.addCity(cityName, e.getX(), e.getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
