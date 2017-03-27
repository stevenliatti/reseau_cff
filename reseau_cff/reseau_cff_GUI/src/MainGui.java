import gui.PrincipalFrame;

import java.io.IOException;

/**
 * Created by raed on 21.03.17.
 */
public class MainGui {
    public static void main(String[] args) throws IOException {
//        MainGui frame = new MainGui("Programme de test");

        PrincipalFrame frame = new PrincipalFrame("Réseau CFF");
//        frame.add(drawingPanel);

//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
        frame.setSize(1200, 800);
        frame.setLocation(0, 0);
//        frame.setExtendedState(frame.getExtendedState() | frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
