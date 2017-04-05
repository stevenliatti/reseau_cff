import gui.PrincipalFrame;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created by raed on 21.03.17.
 */
public class MainGui {
    public static void main(String[] args) throws IOException, JAXBException {
        PrincipalFrame frame = new PrincipalFrame("Réseau CFF", "villes.xml");
        frame.setSize(1200, 800);
        frame.setLocation(0, 0);
        frame.setVisible(true);
    }
}
