package gui;

import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class CostumTabbedPaneUI extends BasicTabbedPaneUI {
    @Override
    protected int calculateTabHeight(
            int tabPlacement, int tabIndex, int fontHeight) {
        return 32;
    }

    @Override
    protected void paintTab(
            Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex,
            Rectangle iconRect, Rectangle textRect) {
        super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);
    }
}
