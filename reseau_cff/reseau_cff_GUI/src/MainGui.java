import gui.PrincipalFrame;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MainGui {
    public static void main(String[] args) throws IOException, JAXBException {
        PrincipalFrame frame = new PrincipalFrame("Réseau CFF", "villes.xml");

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                frame.closing();
            }
        });

        frame.setSize(1200, 800);
        frame.setLocation(0, 0);
        frame.setVisible(true);
    }
}
