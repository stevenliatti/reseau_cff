package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExportXmlDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField fileNameTextField;

    private PrincipalFrame principalFrame;

    private ExportXmlDialog() {
        setTitle("Exporter en xml");
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

        // call onChange()
        fileNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                onChange();
            }
        });
    }

    ExportXmlDialog(PrincipalFrame principalFrame) {
        this();
        this.principalFrame = principalFrame;
    }

    private void onOK() {
        // add your code here
        String fileName = fileNameTextField.getText();
        if (!fileName.isEmpty()) {
            this.principalFrame.getCffCompute().toXmlFile(fileName);
            dispose();
        } else {
            fileNameTextField.requestFocus();
            fileNameTextField.setBackground(Color.RED);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void onChange() {
        this.fileNameTextField.setBackground(Color.WHITE);
    }
}
