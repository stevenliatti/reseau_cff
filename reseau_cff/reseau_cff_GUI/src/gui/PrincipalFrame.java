/*
 * Created by JFormDesigner on Fri Mar 24 20:37:19 CET 2017
 */

package gui;

import management.GraphManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class PrincipalFrame extends JFrame {
    private GraphManagement graphManagement;

    public PrincipalFrame() {
        initComponents();
    }

    public PrincipalFrame(String s, GraphManagement graphManagement) {
        this();
        this.setTitle(s);
        this.graphManagement = graphManagement;
    }

    private void exporterMenuItemActionPerformed(ActionEvent e) {
        ExportXmlDialog dialog = new ExportXmlDialog(this);
        dialog.setLocationRelativeTo(this);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void matParcFMenuItemActionPerformed(ActionEvent e) {
        WeightMatFlFrame weightMatFlFrame = new WeightMatFlFrame(this.graphManagement.getWeightMatrixFloyd(), this.graphManagement.getCityNamesArrayList());
        weightMatFlFrame.setLocationRelativeTo(this);
        weightMatFlFrame.setVisible(true);
    }

    private void matPrecFMenuItemActionPerformed(ActionEvent e) {
        WeightMatFlFrame weightMatFlFrame = new WeightMatFlFrame(this.graphManagement.getPrecMatrixFloyd(), this.graphManagement.getCityNamesArrayList());
        weightMatFlFrame.setLocationRelativeTo(this);
        weightMatFlFrame.setVisible(true);
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
                exporterMenuItem.addActionListener(e -> exporterMenuItemActionPerformed(e));
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
                    matParcFMenuItem.addActionListener(e -> matParcFMenuItemActionPerformed(e));
                    floydMenu.add(matParcFMenuItem);

                    //---- matPrecFMenuItem ----
                    matPrecFMenuItem.setText("Mtrice des pr\u00e9c\u00e9dences");
                    matPrecFMenuItem.addActionListener(e -> matPrecFMenuItemActionPerformed(e));
                    floydMenu.add(matPrecFMenuItem);

                    //---- parcoursFMenuItem ----
                    parcoursFMenuItem.setText("Parcours entre deux villes");
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

    public GraphManagement getGraphManagement() {
        return graphManagement;
    }
}
