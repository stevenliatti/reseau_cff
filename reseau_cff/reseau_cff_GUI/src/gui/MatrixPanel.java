package gui;

import core.CffCompute;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by raed on 26.03.17.
 */
public class MatrixPanel {
    private JPanel container;
    private JTable dataTable;
    private JLabel subTitlelabel;
    private JScrollPane scrollPane1;

    private int[][] matrix;
    private ArrayList<String> names;

    //patron de conception : SINGLETON
    public static MatrixPanel[] INSTANCES = new MatrixPanel[2];

    private MatrixPanel(int id, CffCompute cffCompute) {
        this.names = cffCompute.getCityNames();
        if (id == 0) {
            subTitlelabel.setText("Matrice de temps de parcours (Floyd)");
            this.matrix = cffCompute.getWeightMatrixFloyd();
        } else {
            subTitlelabel.setText("Matrice de précédences (Floyd)");
            this.matrix = cffCompute.getPrecMatrixFloyd();
        }
        loadData();
        dataTable.setAutoCreateRowSorter(false);
        dataTable.setDragEnabled(false);
        dataTable.setPreferredSize(null);
        scrollPane1.setViewportView(dataTable);

        subTitlelabel.setHorizontalAlignment(SwingConstants.CENTER);
        subTitlelabel.setFont(new Font("Ubuntu", Font.BOLD, 20));
        subTitlelabel.setForeground(new Color(0, 102, 102));
        subTitlelabel.setBorder(new EmptyBorder(0, 0, 10, 0));
    }

    public static MatrixPanel getMatrixPanelInstance(int id, CffCompute cffCompute) {
        if (INSTANCES[id] == null) {
            INSTANCES[id] = new MatrixPanel(id, cffCompute);
        }
        return INSTANCES[id];
    }

    private void loadData() {
        int[][] mat = this.matrix;
        ArrayList<String> namesArray = this.names;
        DefaultTableModel dtm = new DefaultTableModel(namesArray.size(), namesArray.size() + 1) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
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

    public JPanel getContainer() {
        return container;
    }
}
