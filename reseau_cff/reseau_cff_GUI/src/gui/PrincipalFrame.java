/*
 * Created by JFormDesigner on Fri Mar 24 20:37:19 CET 2017
 */

package gui;

import management.GraphManagement;
import model.CitiesPointsArray;
import model.MapPointsArray;
import model.Node;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

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
        drawingPanel.setPreferredSize(new Dimension(1100, 700));
        JScrollPane drawingScrollPane = new JScrollPane(drawingPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        jTabbedPane = new JTabbedPane(SwingConstants.TOP) {
            @Override
            public void addTab(String title, Component component) {
                super.addTab(title, component);
            }
        };
        jTabbedPane.setUI(new CostumTabbedPaneUI());
        jTabbedPane.addTab("Carte", drawingScrollPane);

        this.add(jTabbedPane);
        this.rightScrollPane.setVisible(false);
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
        ParcoursDialog parcoursDialog = new ParcoursDialog(this, "D");
        parcoursDialog.setLocationRelativeTo(this);
        parcoursDialog.pack();
        parcoursDialog.setVisible(true);
    }

    private void tabParcDMenuItemActionPerformed(ActionEvent e) {
        DepatureCityDialog depatureCityDialog = new DepatureCityDialog(this);
        depatureCityDialog.setLocationRelativeTo(this);
        depatureCityDialog.pack();
        depatureCityDialog.setVisible(true);
    }

    private void parcoursDMenuItemActionPerformed(ActionEvent e) {
        ParcoursDialog parcoursDialog = new ParcoursDialog(this, "D");
        parcoursDialog.setLocationRelativeTo(this);
        parcoursDialog.pack();
        parcoursDialog.setVisible(true);
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
        parcoursDMenuItem = new JMenuItem();
        rightScrollPane = new JScrollPane();
        rightPanel = new JPanel();
        tableContentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();

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
                    tabParcDMenuItem.setText("Tableau de parcours");
                    tabParcDMenuItem.addActionListener(e -> tabParcDMenuItemActionPerformed(e));
                    dijkstraMenu.add(tabParcDMenuItem);

                    //---- parcoursDMenuItem ----
                    parcoursDMenuItem.setText("Parcours entre deux villes");
                    parcoursDMenuItem.addActionListener(e -> parcoursDMenuItemActionPerformed(e));
                    dijkstraMenu.add(parcoursDMenuItem);
                }
                actionMenu.add(dijkstraMenu);
            }
            menuBar1.add(actionMenu);
        }
        setJMenuBar(menuBar1);

        //======== rightScrollPane ========
        {

            //======== rightPanel ========
            {
                rightPanel.setBackground(Color.white);

                // JFormDesigner evaluation mark
                rightPanel.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                        "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                        java.awt.Color.red), rightPanel.getBorder())); rightPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

                rightPanel.setLayout(new BorderLayout());

                //======== tableContentPanel ========
                {
                    tableContentPanel.setBackground(Color.white);
                    tableContentPanel.setLayout(new GridLayout(1, 3, -1, 1));

                    //---- label1 ----
                    label1.setText("Destination");
                    label1.setHorizontalAlignment(SwingConstants.CENTER);
                    label1.setForeground(new Color(0, 51, 51));
                    label1.setFont(new Font("Ubuntu", Font.BOLD | Font.ITALIC, 16));
                    tableContentPanel.add(label1);

                    //---- label2 ----
                    label2.setText("Temps de parcours");
                    label2.setHorizontalAlignment(SwingConstants.CENTER);
                    label2.setForeground(new Color(0, 51, 51));
                    label2.setFont(new Font("Ubuntu", Font.BOLD | Font.ITALIC, 16));
                    tableContentPanel.add(label2);

                    //---- label3 ----
                    label3.setText("Pr\u00e9c\u00e9dence");
                    label3.setHorizontalAlignment(SwingConstants.CENTER);
                    label3.setForeground(new Color(0, 51, 51));
                    label3.setFont(new Font("Ubuntu", Font.BOLD | Font.ITALIC, 16));
                    tableContentPanel.add(label3);
                }
                rightPanel.add(tableContentPanel, BorderLayout.CENTER);
            }
            rightScrollPane.setViewportView(rightPanel);
        }
        contentPane.add(rightScrollPane, BorderLayout.EAST);
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
    private JMenuItem parcoursDMenuItem;
    private JScrollPane rightScrollPane;
    private JPanel rightPanel;
    private JPanel tableContentPanel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public GraphManagement getGraphManagement() {
        return graphManagement;
    }

    public void courseTowCitiesFloydPanel(String departureCity, String destinationCity) {
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.setBackground(Color.WHITE);
        String str = "Parcours de " + departureCity + " à " + destinationCity + " : " +
                graphManagement.timeTowCitiesFloyd(departureCity, destinationCity) +
                " minutes";
        JLabel commentLabel = new JLabel(str);
        commentLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.add(commentLabel);
        String pathString = graphManagement.pathTowCitiesFloyd(departureCity, destinationCity).toString();
        JLabel listeLabel = new JLabel(pathString);
        listeLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.add(listeLabel);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.drawingPanel.paintConnectionTowCities(pathString);
        this.drawingPanel.repaint();
        this.revalidate();
    }

    public void courseTowCitiesDijkstraPanel(String departureCity, String destinationCity) {
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.setBackground(Color.WHITE);
        String str = "Parcours de " + departureCity + " à " + destinationCity + " : " +
                graphManagement.displayTimeBetweenTwoCities(departureCity, destinationCity) +
                " minutes";
        JLabel commentLabel = new JLabel(str);
        commentLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.add(commentLabel);
        String pathString = graphManagement.displayPathBetweenTwoCities(departureCity, destinationCity).toString();
        JLabel listeLabel = new JLabel(pathString);
        listeLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.add(listeLabel);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.drawingPanel.paintConnectionTowCities(pathString);
        this.drawingPanel.repaint();
        this.revalidate();
    }

    public void courseFromCityPanel(String departureCity) {
//        JPanel rightPanel = new JPanel(new GridLayout(2, 1));
//        rightPanel.setBackground(Color.WHITE);
//        String str = "Parcours depuis " + departureCity + " : ";
//        JLabel titleLabel = new JLabel(str);
//        titleLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
//        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        rightPanel.add(titleLabel);
//        String pathString = graphManagement.displayPathBetweenTwoCities(departureCity, destinationCity).toString();
//        JLabel listeLabel = new JLabel(pathString);
//        listeLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
//        rightPanel.add(listeLabel);
//        this.add(rightPanel, BorderLayout.SOUTH);
//        this.drawingPanel.paintConnectionTowCities(pathString);
//        this.drawingPanel.repaint();
        String str = "Parcours depuis " + departureCity + " : ";
        JLabel titleLabel = new JLabel(str);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Ubuntu", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 102, 102));
        this.rightPanel.add(titleLabel, BorderLayout.NORTH);

        int raws = graphManagement.getCityNamesArrayList().size();
        ((GridLayout) this.tableContentPanel.getLayout()).setRows(raws);
        List<Node> citiesList = getGraphManagement().getNodesDijkstra(departureCity);
        for (Node n : citiesList) {
            if (!n.getName().equals(departureCity)) {
                JLabel l1 = new JLabel(n.getName());
                l1.setHorizontalAlignment(SwingConstants.CENTER);
                l1.setBorder(new LineBorder(Color.lightGray));
                tableContentPanel.add(l1);
                JLabel l2 = new JLabel(String.valueOf(n.getDuration()));
                l2.setHorizontalAlignment(SwingConstants.CENTER);
                l2.setBorder(new LineBorder(Color.lightGray));
                tableContentPanel.add(l2);
                JLabel l3 = new JLabel(n.getPredecessor());
                l3.setHorizontalAlignment(SwingConstants.CENTER);
                l3.setBorder(new LineBorder(Color.lightGray));
                tableContentPanel.add(l3);
            }
        }

        this.rightScrollPane.setVisible(true);
        this.revalidate();
    }
}
