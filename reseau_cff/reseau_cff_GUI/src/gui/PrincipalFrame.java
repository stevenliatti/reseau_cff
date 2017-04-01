/*
 * Created by JFormDesigner on Fri Mar 24 20:37:19 CET 2017
 */

package gui;

import core.CffCompute;
import gui.listeners.AddCityListener;
import gui.listeners.AddConnecListener;
import gui.listeners.RemoveCityListener;
import model.CitiesPointsArray;
import model.CityToDraw;
import model.MapPointsArray;
import model.Node;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;

/**
 * @author Abdennadher Raed
 */
public class PrincipalFrame extends JFrame {
    private CffCompute cffCompute;
    private JTabbedPane jTabbedPane;
    private DrawingPanel drawingPanel;
    private JPanel cancelAddPanel;
    private JLabel addLabel;
    private JPanel bottomPanel;
    private MatrixPanel matPrecFTab;
    private MatrixPanel matParcFTab;
    private AddCityListener addCityListener;
    private AddConnecListener addConnecListener;
    private RemoveCityListener removeCityListener;

    private CitiesPointsArray citiesPointsArray;

    private Map<MatrixPanel, Integer> jTabbedMap;

    private PrincipalFrame() {
        initComponents();
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
    }

    public PrincipalFrame(String s) throws IOException {
        this();
        this.setTitle(s);

        MapPointsArray mapPointsArray = new MapPointsArray("suisse.txt");
        citiesPointsArray = new CitiesPointsArray("villes.xml");
        cffCompute = citiesPointsArray.getCffCompute();

        drawingPanel = new DrawingPanel(mapPointsArray, citiesPointsArray);
        drawingPanel.setPreferredSize(new Dimension(1100, 700));


        matParcFTab = new MatrixPanel(0, cffCompute);
        matPrecFTab = new MatrixPanel(1, cffCompute);

        JPanel centerPanel = new JPanel(new BorderLayout());

        JScrollPane drawingScrollPane = new JScrollPane(drawingPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        ZoomCommand zoomCommand = new ZoomCommand(mapPointsArray, citiesPointsArray, this);
        JPanel centerRightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerRightPanel.add(zoomCommand.getZoomPanel());

        cancelAddPanel = new JPanel(new GridLayout(1, 2));
        cancelAddPanel.setBorder(null);
        JButton cancelButton = new JButton("Annuler");
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jp.add(cancelButton);
        cancelButton.addActionListener(e -> cancelAddCity());
        cancelButton.setBackground(new Color(192, 0, 6));
        cancelButton.setForeground(Color.white);
        addLabel = new JLabel();
        addLabel.setForeground(new Color(192, 0, 6));
        
        cancelAddPanel.add(addLabel);
        cancelAddPanel.add(jp);

        centerPanel.add(cancelAddPanel, BorderLayout.SOUTH);
        centerPanel.add(centerRightPanel, BorderLayout.EAST);
        centerPanel.add(drawingScrollPane, BorderLayout.CENTER);

        jTabbedPane = new JTabbedPane(SwingConstants.TOP);
        jTabbedPane.setUI(new CostumTabbedPaneUI());
        jTabbedPane.addTab("Carte", centerPanel);
        jTabbedPane.addChangeListener(this::refreshTab);
        this.add(jTabbedPane);

        jTabbedMap = new LinkedHashMap<>();

        this.rightScrollPane.setVisible(false);
        this.cancelAddPanel.setVisible(false);
    }

    private void refreshTab(ChangeEvent e) {
        JTabbedPane jt = (JTabbedPane) e.getSource();
        for (MatrixPanel mp :
                jTabbedMap.keySet()) {
            if (jTabbedMap.get(mp) == jt.getSelectedIndex()) {
                if (mp.getId() == 0) {
                    matParcFTab.refrech(0, cffCompute);
                } else if (mp.getId() == 1) {
                    matPrecFTab.refrech(1, cffCompute);
                }
            }
        }
    }


    private void exporterMenuItemActionPerformed() {
        ExportXmlDialog dialog = new ExportXmlDialog(this);
        dialog.setLocationRelativeTo(this);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void matParcFMenuItemActionPerformed() {
//        if (MatrixPanel.INSTANCES[0] == null) {
        if (!jTabbedMap.containsKey(matParcFTab)) {
            matParcFTab = new MatrixPanel(0, cffCompute);
            jTabbedMap.put(matParcFTab, jTabbedPane.getTabCount());
            this.jTabbedPane.addTab(
                    "Parcours Floyd",
                    matParcFTab.getContainer()
            );
        } else {
            matParcFTab.refrech(0, cffCompute);
        }
        jTabbedPane.setSelectedComponent(matParcFTab.getContainer());
//            this.jTabbedPane.setSelectedIndex(this.jTabbedPane.getTabCount() - 1);
//        } else {
//            this.jTabbedPane.setSelectedComponent(MatrixPanel.getMatrixPanelInstance(0, cffCompute).getContainer());
//        }
    }

    private void matPrecFMenuItemActionPerformed() {
        if (!jTabbedMap.containsKey(matPrecFTab)) {
            matPrecFTab = new MatrixPanel(1, cffCompute);
            jTabbedMap.put(matPrecFTab, jTabbedPane.getTabCount());
            this.jTabbedPane.addTab(
                    "Précédences Floyd",
                    matPrecFTab.getContainer()
            );
        }
        jTabbedPane.setSelectedComponent(matPrecFTab.getContainer());
    }

    private void parcoursFMenuItemActionPerformed() {
        ParcoursDialog parcoursDialog = new ParcoursDialog(this, "F");
        parcoursDialog.setLocationRelativeTo(this);
        parcoursDialog.pack();
        parcoursDialog.setVisible(true);
    }

    private void tabParcDMenuItemActionPerformed() {
        DepatureCityDialog depatureCityDialog = new DepatureCityDialog(this);
        depatureCityDialog.setLocationRelativeTo(this);
        depatureCityDialog.pack();
        depatureCityDialog.setVisible(true);
    }

    private void parcoursDMenuItemActionPerformed() {
        ParcoursDialog parcoursDialog = new ParcoursDialog(this, "D");
        parcoursDialog.setLocationRelativeTo(this);
        parcoursDialog.pack();
        parcoursDialog.setVisible(true);
    }

    private void ajoutVilleMenuItemActionPerformed() {
        addLabel.setText("Cliquer sur la carte pour ajouter une ville.");
        cancelAddPanel.setVisible(true);
        drawingPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addCityListener = new AddCityListener(this);
        drawingPanel.addMouseListener(addCityListener);
        drawingPanel.setPaintPathTowCities(false);
        bottomPanel.removeAll();
        bottomPanel.setVisible(false);
        menuBar1.setVisible(false);
        jTabbedPane.setSelectedIndex(0);
    }

    private void cancelAddCity() {
        drawingPanel.removeMouseListener(addCityListener);
        drawingPanel.removeMouseListener(addConnecListener);
        drawingPanel.removeMouseListener(removeCityListener);
        drawingPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        cancelAddPanel.setVisible(false);
        menuBar1.setVisible(true);
    }

    private void ajoutLiaisonMenuItemActionPerformed() {
        addLabel.setText("Cliquer sur deux villes pour ajouter une liaison.");
        cancelAddPanel.setVisible(true);
        drawingPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addConnecListener = new AddConnecListener(this);
        drawingPanel.addMouseListener(addConnecListener);
        drawingPanel.setPaintPathTowCities(false);
        bottomPanel.removeAll();
        bottomPanel.setVisible(false);
        menuBar1.setVisible(false);
        jTabbedPane.setSelectedIndex(0);
    }

    private void supprVilleMenuItemActionPerformed() {
        addLabel.setText("Cliquer sur une ville pour la supprimer.");
        cancelAddPanel.setVisible(true);
        removeCityListener = new RemoveCityListener(this);
        drawingPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        drawingPanel.addMouseListener(removeCityListener);
        drawingPanel.setPaintPathTowCities(false);
        bottomPanel.removeAll();
        bottomPanel.setVisible(false);
        menuBar1.setVisible(false);
        jTabbedPane.setSelectedIndex(0);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Raed Abdennadher
        menuBar1 = new JMenuBar();
        JMenu fichierMenu = new JMenu();
        JMenuItem exporterMenuItem = new JMenuItem();
        JMenuItem quitterMenuItem = new JMenuItem();
        JMenu editionMenu = new JMenu();
        JMenuItem ajoutVilleMenuItem = new JMenuItem();
        JMenuItem supprVilleMenuItem = new JMenuItem();
        JMenuItem ajoutLiaisonMenuItem = new JMenuItem();
        JMenuItem supprLiaisonMenuItem = new JMenuItem();
        JMenu actionMenu = new JMenu();
        JMenu floydMenu = new JMenu();
        JMenuItem matParcFMenuItem = new JMenuItem();
        JMenuItem matPrecFMenuItem = new JMenuItem();
        JMenuItem parcoursFMenuItem = new JMenuItem();
        JMenu dijkstraMenu = new JMenu();
        JMenuItem tabParcDMenuItem = new JMenuItem();
        JMenuItem parcoursDMenuItem = new JMenuItem();
        rightScrollPane = new JScrollPane();
        JPanel rightPanel = new JPanel();
        tableContentPanel = new JPanel();
        titleLabel = new JLabel();

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
                exporterMenuItem.addActionListener(e -> exporterMenuItemActionPerformed());
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
                ajoutVilleMenuItem.addActionListener(e -> ajoutVilleMenuItemActionPerformed());
                editionMenu.add(ajoutVilleMenuItem);

                //---- supprVilleMenuItem ----
                supprVilleMenuItem.setText("Supprimer une ville");
                supprVilleMenuItem.addActionListener(e -> supprVilleMenuItemActionPerformed());
                editionMenu.add(supprVilleMenuItem);
                editionMenu.addSeparator();

                //---- ajoutLiaisonMenuItem ----
                ajoutLiaisonMenuItem.setText("Ajouter une liaison");
                ajoutLiaisonMenuItem.addActionListener(e -> ajoutLiaisonMenuItemActionPerformed());
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
                    matParcFMenuItem.addActionListener(e -> matParcFMenuItemActionPerformed());
                    floydMenu.add(matParcFMenuItem);

                    //---- matPrecFMenuItem ----
                    matPrecFMenuItem.setText("Mtrice des pr\u00e9c\u00e9dences");
                    matPrecFMenuItem.addActionListener(e -> matPrecFMenuItemActionPerformed());
                    floydMenu.add(matPrecFMenuItem);

                    //---- parcoursFMenuItem ----
                    parcoursFMenuItem.setText("Parcours entre deux villes");
                    parcoursFMenuItem.addActionListener(e -> parcoursFMenuItemActionPerformed());
                    floydMenu.add(parcoursFMenuItem);
                }
                actionMenu.add(floydMenu);

                //======== dijkstraMenu ========
                {
                    dijkstraMenu.setText("Dijkstra");

                    //---- tabParcDMenuItem ----
                    tabParcDMenuItem.setText("Tableau de parcours");
                    tabParcDMenuItem.addActionListener(e -> tabParcDMenuItemActionPerformed());
                    dijkstraMenu.add(tabParcDMenuItem);

                    //---- parcoursDMenuItem ----
                    parcoursDMenuItem.setText("Parcours entre deux villes");
                    parcoursDMenuItem.addActionListener(e -> parcoursDMenuItemActionPerformed());
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
                        java.awt.Color.red), rightPanel.getBorder())); rightPanel.addPropertyChangeListener(e -> {if("border".equals(e.getPropertyName()))throw new RuntimeException();});

                rightPanel.setLayout(new BorderLayout());

                //======== tableContentPanel ========
                {
                    tableContentPanel.setBackground(Color.white);
                    tableContentPanel.setLayout(new GridLayout(1, 3, -1, 1));
                }
                rightPanel.add(tableContentPanel, BorderLayout.CENTER);

                //---- titleLabel ----
                titleLabel.setText("text");
                titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
                titleLabel.setFont(new Font("Ubuntu", Font.BOLD, 20));
                titleLabel.setForeground(new Color(0, 102, 102));
                rightPanel.add(titleLabel, BorderLayout.NORTH);
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
    private JScrollPane rightScrollPane;
    private JPanel tableContentPanel;
    private JLabel titleLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public CffCompute getCffCompute() {
        return cffCompute;
    }

    int courseTowCitiesFloydPanel(String departureCity, String destinationCity) {
        int time = cffCompute.outTimeTowCitiesFloyd(departureCity, destinationCity);
        if (time == Integer.MAX_VALUE)
            return -1;
        bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.setBackground(Color.WHITE);
        String str = "Parcours de " + departureCity + " à " + destinationCity + " : " + time + " minutes";
        JLabel commentLabel = new JLabel(str);
        commentLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.add(commentLabel);
        String pathString = cffCompute.outPathTowCitiesFloyd(departureCity, destinationCity).toString();
        JLabel listeLabel = new JLabel(pathString);
        listeLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.add(listeLabel);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.drawingPanel.paintConnectionTowCities(pathString);
        this.drawingPanel.repaint();
        this.revalidate();
        return 0;
    }

    int courseTowCitiesDijkstraPanel(String departureCity, String destinationCity) {
        int time = cffCompute.outTimeTwoCitiesDijkstra(departureCity, destinationCity);
        if (time == Integer.MAX_VALUE)
            return -1;
        bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.setBackground(Color.WHITE);
        String str = "Parcours de " + departureCity + " à " + destinationCity + " : " + time + " minutes";
        JLabel commentLabel = new JLabel(str);
        commentLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.add(commentLabel);
        String pathString = cffCompute.outPathTwoCitiesDijkstra(departureCity, destinationCity).toString();
        JLabel listeLabel = new JLabel(pathString);
        listeLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.add(listeLabel);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.drawingPanel.paintConnectionTowCities(pathString);
        this.drawingPanel.repaint();
        this.revalidate();
        return 0;
    }

    void courseFromCityPanel(String departureCity) {
        String str = "Parcours depuis " + departureCity + " : ";
        titleLabel.setText(str);

        int rows = cffCompute.getCityNames().size();
        this.tableContentPanel.setLayout(new GridLayout(rows, 3));
        this.tableContentPanel.removeAll();
        //---- label1 ----
        JLabel label1 = new JLabel();
        label1.setText("Destination");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setForeground(new Color(0, 51, 51));
        label1.setFont(new Font("Ubuntu", Font.BOLD | Font.ITALIC, 16));
        tableContentPanel.add(label1);

        //---- label2 ----
        JLabel label2 = new JLabel();
        label2.setText("Temps de parcours");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setForeground(new Color(0, 51, 51));
        label2.setFont(new Font("Ubuntu", Font.BOLD | Font.ITALIC, 16));
        tableContentPanel.add(label2);

        //---- label3 ----
        JLabel label3 = new JLabel();
        label3.setText("Pr\u00e9c\u00e9dence");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setForeground(new Color(0, 51, 51));
        label3.setFont(new Font("Ubuntu", Font.BOLD | Font.ITALIC, 16));
        tableContentPanel.add(label3);

        List<Node> citiesList = getCffCompute().getNodesDijkstra(departureCity);
        for (Node n : citiesList) {
            if (!n.getName().equals(departureCity)) {
                JLabel l1 = new JLabel(n.getName());
                l1.setHorizontalAlignment(SwingConstants.CENTER);
                l1.setBorder(new LineBorder(Color.lightGray));
                tableContentPanel.add(l1);
                JLabel l2, l3;
                if (n.getDuration() == Integer.MAX_VALUE) {
                    l2 = new JLabel("--");
                    l3 = new JLabel("--");
                } else {
                    l2 = new JLabel(String.valueOf(n.getDuration()));
                    l3 = new JLabel(n.getPredecessor());
                }
                l2.setHorizontalAlignment(SwingConstants.CENTER);
                l2.setBorder(new LineBorder(Color.lightGray));
                tableContentPanel.add(l2);
                l3.setHorizontalAlignment(SwingConstants.CENTER);
                l3.setBorder(new LineBorder(Color.lightGray));
                tableContentPanel.add(l3);
            }
        }

        this.rightScrollPane.setVisible(true);
        this.revalidate();
    }

    public CitiesPointsArray getCitiesPointsArray() {
        return citiesPointsArray;
    }

    public void addCity(String cityName, int x, int y) {
        citiesPointsArray.add(new CityToDraw(x, y, cityName, false));

        this.cffCompute.addCity(cityName);
        drawingPanel.repaint();
        revalidate();
    }

    public void addConnection(String city1, String city2, int duration) {
        this.cffCompute.addConnection(city1, city2, String.valueOf(duration));
        drawingPanel.repaint();
        revalidate();
    }

    public void removeCity(String city) {
        this.cffCompute.removeCity(city);
        citiesPointsArray.remove(citiesPointsArray.getCityToDrawByName(city));
        drawingPanel.repaint();
        revalidate();
    }
}
