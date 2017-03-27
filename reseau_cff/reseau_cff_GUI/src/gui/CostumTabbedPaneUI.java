package gui;

import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

/**
 * Created by raed on 26.03.17.
 */
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
//        rects[tabIndex].setBounds(
//                rects[tabIndex].getBounds().x,
//                rects[tabIndex].getBounds().y,
//                (int) rects[tabIndex].getBounds().getWidth() + 20,
//                (int) rects[tabIndex].getBounds().getHeight()
//        );
//
//        iconRect.setBounds(
//                iconRect.x + (int)textRect.getWidth() + 5,
//                textRect.y,
//                (int)iconRect.getBounds().getWidth(),
//                (int)iconRect.getBounds().getHeight()
//        );
//        int x = iconRect.x;
//        int y = iconRect.y;
//        int y_p = y + 2;
//        g.drawLine(x + 1, y_p, x + 12, y_p);
//        g.drawLine(x + 1, y_p + 13, x + 12, y_p + 13);
//        g.drawLine(x, y_p + 1, x, y_p + 12);
//        g.drawLine(x + 13, y_p + 1, x + 13, y_p + 12);
//        g.drawLine(x + 3, y_p + 3, x + 10, y_p + 10);
//        g.drawLine(x + 3, y_p + 4, x + 9, y_p + 10);
//        g.drawLine(x + 4, y_p + 3, x + 10, y_p + 9);
//        g.drawLine(x + 10, y_p + 3, x + 3, y_p + 10);
//        g.drawLine(x + 10, y_p + 4, x + 4, y_p + 10);
//        g.drawLine(x + 9, y_p + 3, x + 3, y_p + 9);
//        if(tabIndex==0) {
//            rects[tabIndex].height = 20 + 1;
//            rects[tabIndex].y = 32 - rects[tabIndex].height + 1;
//        } else if(tabIndex==1) {
//            rects[tabIndex].height = 26 + 1;
//            rects[tabIndex].y = 32 - rects[tabIndex].height + 1;
//        }
        super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);
    }
}
