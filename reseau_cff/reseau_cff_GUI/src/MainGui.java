import model.CitiesPointsArray;
import model.MapPointsArray;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by raed on 21.03.17.
 */
public class MainGui extends JFrame {

    public MainGui(String appName) {
        super(appName);
    }

    public static void main(String[] args) throws IOException {
        MainGui frame = new MainGui("Programme de test");
        frame.setPreferredSize(new Dimension(1200, 800));

        MapPointsArray mapPointsArray = new MapPointsArray("suisse.txt");
        CitiesPointsArray citiesPointsArray = new CitiesPointsArray("villes.xml");

        DrawingPanel drawingPanel = new DrawingPanel(mapPointsArray, citiesPointsArray);

        frame.add(drawingPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
