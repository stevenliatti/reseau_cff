package gui;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ParcoursDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> departureComboBox;
    private JComboBox<String> destinationComboBox;

    private String departureCity;
    private String destinationCity;

    public ParcoursDialog(ArrayList<String> citiesName) {
        setTitle("Parcours");

        for (String s : citiesName) {
            destinationComboBox.addItem(s);
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
        destinationCity = destinationComboBox.getSelectedItem().toString();
        departureCity = departureComboBox.getSelectedItem().toString();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        destinationCity = null;
        departureCity = null;
        dispose();
    }

    public String[] getReturnedData() {
        if (destinationCity == null)
            return null;
        return new String[] {departureCity, destinationCity};
    }
}
