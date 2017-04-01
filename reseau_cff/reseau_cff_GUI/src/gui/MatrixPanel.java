package gui;

import core.CffCompute;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * Created by raed on 26.03.17.
 */
public class MatrixPanel {
    private JPanel container;
    private JTable dataTable;
    private JLabel subTitlelabel;
    private JScrollPane scrollPane1;

    int id;
    private int[][] matrix;
    private ArrayList<String> names;

    public MatrixPanel(int id, CffCompute cffCompute) {
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
        this.names = cffCompute.getCityNames();
        if (id == 0) {
            subTitlelabel.setText("Matrice de temps de parcours (Floyd)");
            this.matrix = cffCompute.getWeightMatrixFloyd();
        } else {
            subTitlelabel.setText("Matrice de précédences (Floyd)");
            this.matrix = cffCompute.getPrecMatrixFloyd();
        }
        int[][] mat = this.matrix;
        ArrayList<String> namesArray = this.names;
        DefaultTableModel dtm = new DefaultTableModel(namesArray.size() + 2, namesArray.size() + 2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (int i = 2; i < namesArray.size() + 2; i++) {
            dtm.setValueAt(i - 2, 0, i);
            dtm.setValueAt(namesArray.get(i - 2), 1, i);

            dtm.setValueAt(i - 2, i, 0);
            dtm.setValueAt(namesArray.get(i - 2), i, 1);
        }

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                dtm.setValueAt(mat[i][j], i + 2, j + 2);
            }
        }
        dataTable.setModel(dtm);
    }

    public void refrech(int id, CffCompute cffCompute) {
        loadData(id, cffCompute);
    }

    public JPanel getContainer() {
        return container;
    }

    public int getId() {
        return id;
    }
}
