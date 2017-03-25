/*
 * Created by JFormDesigner on Fri Mar 24 22:49:54 CET 2017
 */

package gui;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author Raed Abdennadher
 */
public class WeightMatFlFrame extends JFrame {
    private int[][] matrix;
    private ArrayList<String> names;

    public WeightMatFlFrame(int[][] matrix, ArrayList<String> names) {
        this.matrix = matrix;
        this.names = names;
        initComponents();
//        loadData();
        loadData2();
        setSize(900, 600);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Raed Abdennadher
        container = new JPanel();
        scrollPane1 = new JScrollPane();
        dataTable = new JTable();
        label1 = new JLabel();

        //======== this ========
        setTitle("Floyd");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== container ========
        {
            container.setBorder(new EmptyBorder(10, 10, 10, 10));

            // JFormDesigner evaluation mark
            container.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), container.getBorder())); container.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            container.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {

                //---- dataTable ----
                dataTable.setAutoCreateRowSorter(true);
                dataTable.setEnabled(false);
                dataTable.setDragEnabled(true);
                dataTable.setPreferredSize(null);
                scrollPane1.setViewportView(dataTable);
            }
            container.add(scrollPane1, BorderLayout.CENTER);

            //---- label1 ----
            label1.setText("Matrice de parcours (Floyd)");
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setFont(new Font("Ubuntu", Font.BOLD, 20));
            label1.setForeground(new Color(0, 102, 102));
            label1.setBorder(new EmptyBorder(0, 0, 10, 0));
            container.add(label1, BorderLayout.NORTH);
        }
        contentPane.add(container, BorderLayout.CENTER);
        setSize(400, 300);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void loadData2() {
        int[][] mat = this.matrix;
        ArrayList<String> namesArray = this.names;
        DefaultTableModel dtm = new DefaultTableModel(namesArray.size(), namesArray.size() + 1);
        String[] header = new String[namesArray.size() + 1];
        header[0] = "";
        for (int i = 1; i < header.length; i++) {
            header[i] = namesArray.get(i - 1);
        }
        dtm.setColumnIdentifiers(header);
        for (int i = 0; i < mat.length; i++) {
            dtm.setValueAt(namesArray.get(i), i, 0);
            for (int j = 1; j < mat.length + 1; j++) {
                dtm.setValueAt(mat[i][j - 1], i, j);
            }
        }
        dataTable.setModel(dtm);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Raed Abdennadher
    private JPanel container;
    private JScrollPane scrollPane1;
    private JTable dataTable;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
