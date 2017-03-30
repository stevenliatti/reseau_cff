import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Wed Mar 29 23:02:20 CEST 2017
 */



/**
 * @author Raed Abdennadher
 */
public class PrincipalFrame extends JFrame {
    public PrincipalFrame() {
        initComponents();
    }

    private void exporterMenuItemActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void matParcFMenuItemActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void matPrecFMenuItemActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void parcoursFMenuItemActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Raed Abdennadher
        menuBar1 = new JMenuBar();
        fichierMenu = new JMenu();
        exporterMenuItem = new JMenuItem();
        quitterMenuItem = new JMenuItem();
        editionMenu = new JMenu();
        ajoutVilleMenuItem = new JMenuItem();
        supprVilleMenuItem = new JMenuItem();
        ajoutLiaisonMenuItem = new JMenuItem();
        supprLiaisonMenuItem = new JMenuItem();
        actionMenu = new JMenu();
        floydMenu = new JMenu();
        matParcFMenuItem = new JMenuItem();
        matPrecFMenuItem = new JMenuItem();
        parcoursFMenuItem = new JMenuItem();
        dijkstraMenu = new JMenu();
        tabParcDMenuItem = new JMenuItem();
        tabPrecDMenuItem = new JMenuItem();
        parcoursDMenuItem = new JMenuItem();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== fichierMenu ========
            {
                fichierMenu.setText("Fichier");

                //---- exporterMenuItem ----
                exporterMenuItem.setText("Exporter fichier Xml");
                exporterMenuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        exporterMenuItemActionPerformed(e);
                    }
                });
                fichierMenu.add(exporterMenuItem);
                fichierMenu.addSeparator();

                //---- quitterMenuItem ----
                quitterMenuItem.setText("Quitter");
                fichierMenu.add(quitterMenuItem);
            }
            menuBar1.add(fichierMenu);

            //======== editionMenu ========
            {
                editionMenu.setText("Edition");

                //---- ajoutVilleMenuItem ----
                ajoutVilleMenuItem.setText("Ajouter une ville");
                editionMenu.add(ajoutVilleMenuItem);

                //---- supprVilleMenuItem ----
                supprVilleMenuItem.setText("Supprimer une ville");
                editionMenu.add(supprVilleMenuItem);
                editionMenu.addSeparator();

                //---- ajoutLiaisonMenuItem ----
                ajoutLiaisonMenuItem.setText("Ajouter une liaison");
                editionMenu.add(ajoutLiaisonMenuItem);

                //---- supprLiaisonMenuItem ----
                supprLiaisonMenuItem.setText("Supprimer une liaison");
                editionMenu.add(supprLiaisonMenuItem);
            }
            menuBar1.add(editionMenu);

            //======== actionMenu ========
            {
                actionMenu.setText("Actions");

                //======== floydMenu ========
                {
                    floydMenu.setText("Floyd");

                    //---- matParcFMenuItem ----
                    matParcFMenuItem.setText("Matrice des temps de parcours");
                    matParcFMenuItem.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            matParcFMenuItemActionPerformed(e);
                        }
                    });
                    floydMenu.add(matParcFMenuItem);

                    //---- matPrecFMenuItem ----
                    matPrecFMenuItem.setText("Mtrice des pr\u00e9c\u00e9dences");
                    matPrecFMenuItem.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            matPrecFMenuItemActionPerformed(e);
                        }
                    });
                    floydMenu.add(matPrecFMenuItem);

                    //---- parcoursFMenuItem ----
                    parcoursFMenuItem.setText("Parcours entre deux villes");
                    parcoursFMenuItem.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            parcoursFMenuItemActionPerformed(e);
                        }
                    });
                    floydMenu.add(parcoursFMenuItem);
                }
                actionMenu.add(floydMenu);

                //======== dijkstraMenu ========
                {
                    dijkstraMenu.setText("Dijkstra");

                    //---- tabParcDMenuItem ----
                    tabParcDMenuItem.setText("Tableau des temps de parcours");
                    dijkstraMenu.add(tabParcDMenuItem);

                    //---- tabPrecDMenuItem ----
                    tabPrecDMenuItem.setText("Tableau des pr\u00e9c\u00e9dences");
                    dijkstraMenu.add(tabPrecDMenuItem);

                    //---- parcoursDMenuItem ----
                    parcoursDMenuItem.setText("Parcours entre deux villes");
                    dijkstraMenu.add(parcoursDMenuItem);
                }
                actionMenu.add(dijkstraMenu);
            }
            menuBar1.add(actionMenu);
        }
        setJMenuBar(menuBar1);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Raed Abdennadher
    private JMenuBar menuBar1;
    private JMenu fichierMenu;
    private JMenuItem exporterMenuItem;
    private JMenuItem quitterMenuItem;
    private JMenu editionMenu;
    private JMenuItem ajoutVilleMenuItem;
    private JMenuItem supprVilleMenuItem;
    private JMenuItem ajoutLiaisonMenuItem;
    private JMenuItem supprLiaisonMenuItem;
    private JMenu actionMenu;
    private JMenu floydMenu;
    private JMenuItem matParcFMenuItem;
    private JMenuItem matPrecFMenuItem;
    private JMenuItem parcoursFMenuItem;
    private JMenu dijkstraMenu;
    private JMenuItem tabParcDMenuItem;
    private JMenuItem tabPrecDMenuItem;
    private JMenuItem parcoursDMenuItem;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
