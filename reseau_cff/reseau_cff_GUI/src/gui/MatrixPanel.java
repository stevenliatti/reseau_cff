package gui;

import core.CffCompute;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MatrixPanel {
    private JPanel container;
    private JTable dataTable;
    private JLabel subTitlelabel;
    private JScrollPane scrollPane1;

    private int id;

    MatrixPanel(int id, CffCompute cffCompute) {
        loadData(id, cffCompute);
        dataTable.setAutoCreateRowSorter(false);
        dataTable.setDragEnabled(false);
        dataTable.setPreferredSize(null);
        dataTable.setDefaultRenderer(Object.class, new CustomCellRender(dataTable));
        dataTable.setTableHeader(null);
        dataTable.setRowHeight(20);
        scrollPane1.setViewportView(dataTable);

        subTitlelabel.setHorizontalAlignment(SwingConstants.CENTER);
        subTitlelabel.setFont(new Font("Ubuntu", Font.BOLD, 20));
        subTitlelabel.setForeground(new Color(0, 102, 102));
        subTitlelabel.setBorder(new EmptyBorder(0, 0, 10, 0));
    }

    private void loadData(int id, CffCompute cffCompute) {
        this.id = id;
        ArrayList<String> names = cffCompute.getCityNames();
        int[][] matrix;
        if (id == 0) {
            subTitlelabel.setText("Matrice de temps de parcours (Floyd)");
            matrix = cffCompute.getWeightMatrixFloyd();
        } else {
            subTitlelabel.setText("Matrice de précédences (Floyd)");
            matrix = cffCompute.getPrecMatrixFloyd();
        }
        int[][] mat = matrix;
        DefaultTableModel dtm = new DefaultTableModel(names.size() + 2, names.size() + 2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (int i = 2; i < names.size() + 2; i++) {
            dtm.setValueAt(i - 2, 0, i);
            dtm.setValueAt(names.get(i - 2), 1, i);

            dtm.setValueAt(i - 2, i, 0);
            dtm.setValueAt(names.get(i - 2), i, 1);
        }

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                dtm.setValueAt(mat[i][j], i + 2, j + 2);
            }
        }
        dataTable.setModel(dtm);
    }

    void refrech(int id, CffCompute cffCompute) {
        loadData(id, cffCompute);
    }

    JPanel getContainer() {
        return container;
    }

    int getId() {
        return id;
    }
}
