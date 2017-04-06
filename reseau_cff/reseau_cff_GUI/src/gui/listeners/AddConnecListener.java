package gui.listeners;

import gui.PrincipalFrame;
import model.CityToDraw;
import model.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddConnecListener implements MouseListener{
    private PrincipalFrame principalFrame;
    private static int clickCount = 0;
    private static String city1 = "";
    private static String city2 = "";

    public AddConnecListener(PrincipalFrame principalFrame) {
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
        for (CityToDraw c : principalFrame.getCitiesPointsArray()) {
            Rectangle r = new Rectangle(c.getCoordinate().getX() - 5, c.getCoordinate().getY() - 5,
                    10, 10);
            if (r.contains(cursorX, cursorY)) {
                clickCount++;
                if (clickCount == 1) {
                    city1 = c.getCityName();
                } else {
                    city2 = c.getCityName();
                }
            }
        }
        if (clickCount == 2) {
            if (city1.equals(city2)) {
                clickCount--;
                city2 = "";
            } else {
                boolean existed = false;
                for (Connection n : principalFrame.getCffCompute().getNet().getConnectionList()) {
                    if (n.getVil_1().equals(city1) && n.getVil_2().equals(city2) ||
                            n.getVil_1().equals(city2) && n.getVil_2().equals(city1)) {
                        existed = true;
                    }
                }
                if (existed) {
                    JOptionPane.showMessageDialog(principalFrame, "Cette connection existe déjà!", "Attention", JOptionPane.WARNING_MESSAGE);
                } else {
                    boolean b;
                    int duration = 0;
                    do {
                        String s = JOptionPane.showInputDialog(
                                this.principalFrame,
                                "De " + city1 + " à " + city2 + " (en minutes) :",
                                "Durée du trajet",
                                JOptionPane.QUESTION_MESSAGE
                        );
                        if (s == null) {
                            b = true;
                            duration = -1;
                        } else {
                            try {
                                duration = Integer.parseInt(s);
                                b = true;
                            } catch (Exception ee) {
                                b = false;
                            }
                        }
                    } while (!b || duration == 0);
                    if (duration > 0) {
                        principalFrame.addConnection(city1, city2, duration);
                    }
                }
                clickCount = 0;
                city1 = "";
                city2 = "";
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
