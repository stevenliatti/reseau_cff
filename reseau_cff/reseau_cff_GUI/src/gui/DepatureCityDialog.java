package gui;

import javax.swing.*;
import java.awt.event.*;

public class DepatureCityDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> departureComboBox;

    private PrincipalFrame principalFrame;

    public DepatureCityDialog(PrincipalFrame principalFrame) {
        this.principalFrame = principalFrame;
        setTitle("Parcours");

        for (String s : principalFrame.getGraphManagement().getCityNamesArrayList()) {
            departureComboBox.addItem(s);
        }

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        String departureCity = (String) departureComboBox.getSelectedItem();
        principalFrame.courseFromCityPanel(departureCity);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
