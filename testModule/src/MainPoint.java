import javax.swing.*;

/**
 * Created by raed on 21.03.17.
 */
public class MainPoint extends JFrame {

    public MainPoint(String appName) {
        super(appName);
    }

    public static void main(String[] args) {
        MainPoint frame = new MainPoint("Programme de test");
        frame.setContentPane(new TestProgram().getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
