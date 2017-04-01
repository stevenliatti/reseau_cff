package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created by raed on 01.04.17.
 */
public class CustomCellRender extends DefaultTableCellRenderer {
    private JTable dataTable;

    public CustomCellRender(JTable dataTable) {
        this.dataTable = dataTable;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        String toolTip = "<html><body>" +
                "Ligne : <strong>" + dataTable.getValueAt(row, 1) + "</strong><br>" +
                "Colonne : <strong>" + dataTable.getValueAt(1, column) + "</strong><br>" +
                "Valeur : <strong>" + String.valueOf(value) + "</strong>" +
                "</body></html>";
        JLabel c = this;
        if (row > 1 && column > 1 && (row % 2) == 0) {
            setBackground(new Color(0xE6EFFF));
            setForeground(Color.black);
            c.setToolTipText(toolTip);
        } else if (row > 1 && column > 1 && (row % 2) == 1) {
            setBackground(Color.white);
            setForeground(Color.black);
            c.setToolTipText(toolTip);
        } else if (row == 0) {
            setBackground(new Color(0x000D0D));
            setForeground(Color.white);
        } else if (column == 0) {
            setBackground(new Color(0x000D0D));
            setForeground(Color.white);
        } else if (row == 1) {
            setFont(new Font("Ubuntu", Font.BOLD, 14));
            setBackground(new Color(0x003333));
            setForeground(Color.white);
            c.setToolTipText(String.valueOf(value));
        } else if (column == 1) {
            setFont(new Font("Ubuntu", Font.BOLD, 14));
            setBackground(new Color(0x003333));
            setForeground(Color.white);
            c.setToolTipText(String.valueOf(value));
        } else {
            setBackground(Color.white);
            setForeground(Color.black);
        }
        if (value != null && row > 1 && column > 1) {
            try {
                Integer x = new Integer(String.valueOf(value));
                if (x == -1 || x == Integer.MAX_VALUE) {
                    setBackground(Color.red);
                    setText("--");
                }
            } catch (Exception e) {}
        }
        setHorizontalAlignment(CENTER);
        return this;
    }
}
