package gui;

import javax.swing.*;
import java.awt.event.*;

public class ParcoursDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> departureComboBox;
    private JComboBox<String> destinationComboBox;

    private PrincipalFrame principalFrame;
    private String algorithm;

    public ParcoursDialog(PrincipalFrame principalFrame, String algorithm) {
        this.principalFrame = principalFrame;
        this.algorithm = algorithm;

        setTitle("Parcours");

        for (String s : principalFrame.getCffCompute().getCityNames()) {
            if (principalFrame.getCffCompute().isConnectedCity(s)) {
                destinationComboBox.addItem(s);
                departureComboBox.addItem(s);
            }
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
        String destinationCity = (String) destinationComboBox.getSelectedItem();
        if (!destinationCity.equals(departureCity)) {
            int result = 0;
            if (algorithm.equals("F"))
                result =  principalFrame.courseTowCitiesFloydPanel(departureCity, destinationCity);
            else if (algorithm.equals("D"))
                result = principalFrame.courseTowCitiesDijkstraPanel(departureCity, destinationCity);
            if (result == -1) {
                JOptionPane.showMessageDialog(
                        this,
                        "Les deux villes ne sont pas connectées!",
                        "Avertissement",
                        JOptionPane.WARNING_MESSAGE
                );
            } else {
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Il faut choisir deux villes différentes");
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
