/*
 * Created by JFormDesigner on Fri Mar 24 20:37:19 CET 2017
 */

package gui;

import management.GraphManagement;
import model.CitiesPointsArray;
import model.MapPointsArray;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

/**
 * @author unknown
 */
public class PrincipalFrame extends JFrame {
    private GraphManagement graphManagement;
    private JTabbedPane jTabbedPane;
    private DrawingPanel drawingPanel;

    public PrincipalFrame() {
        initComponents();
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
    }

    public PrincipalFrame(String s) throws IOException {
        this();
        this.setTitle(s);

        MapPointsArray mapPointsArray = new MapPointsArray("suisse.txt");
        CitiesPointsArray citiesPointsArray = new CitiesPointsArray("villes.xml");
        this.graphManagement = citiesPointsArray.getGraphManagement();

        drawingPanel = new DrawingPanel(mapPointsArray, citiesPointsArray);
        jTabbedPane = new JTabbedPane(SwingConstants.TOP) {
            @Override
            public void addTab(String title, Component component) {
                super.addTab(title, component);
            }
        };
        jTabbedPane.setUI(new CostumTabbedPaneUI());
        jTabbedPane.addTab("Carte", drawingPanel);

        this.add(jTabbedPane);
    }

    private void exporterMenuItemActionPerformed(ActionEvent e) {
        ExportXmlDialog dialog = new ExportXmlDialog(this);
        dialog.setLocationRelativeTo(this);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void matParcFMenuItemActionPerformed(ActionEvent e) {
//        if (MatrixPanel.INSTANCES[0] == null) {
            this.jTabbedPane.addTab(
                    "Parcours Floyd",
                    MatrixPanel.getMatrixPanelInstance(0, graphManagement).getContainer()
            );
            this.jTabbedPane.setSelectedIndex(this.jTabbedPane.getTabCount() - 1);
//        } else {
//            this.jTabbedPane.setSelectedComponent(MatrixPanel.getMatrixPanelInstance(0, graphManagement).getContainer());
//        }
    }

    private void matPrecFMenuItemActionPerformed(ActionEvent e) {
        this.jTabbedPane.addTab(
                "Précédences Floyd",
                MatrixPanel.getMatrixPanelInstance(1, graphManagement).getContainer()
        );
        this.jTabbedPane.setSelectedIndex(this.jTabbedPane.getTabCount() - 1);
    }

    private void parcoursFMenuItemActionPerformed(ActionEvent e) {
        ParcoursDialog parcoursDialog = new ParcoursDialog(graphManagement.getCityNamesArrayList());
        parcoursDialog.setLocationRelativeTo(this);
        parcoursDialog.pack();
        parcoursDialog.setVisible(true);
        String[] request = parcoursDialog.getReturnedData();
        if (request != null) {
            JPanel parcoursPanel = new JPanel(new GridLayout(2, 1));
            String str = "Parcours de " + request[0] + " à " + request[1] + " : " +
                    graphManagement.displayTimeBetweenTwoCities(request[0], request[1]) +
                    " minutes";
            parcoursPanel.add(new JLabel(str));
            parcoursPanel.add(new JLabel(graphManagement.displayPathBetweenTwoCities(request[0], request[1]).toString()));
            this.add(parcoursPanel, BorderLayout.SOUTH);
            this.revalidate();
        }
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
                    parcoursFMenuItem.addActionListener(e -> parcoursFMenuItemActionPerformed(e));
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
