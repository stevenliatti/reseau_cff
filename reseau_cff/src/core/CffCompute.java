package core;

import model.City;
import model.Connection;
import model.Node;
import model.Net;

import javax.xml.bind.*;
import java.util.*;

/**
 * Classe implémentant tous les calculs demandés dans le programme principal.
 * Les fonctions sont données dans l'ordre du menu fourni.
 * @author Raed Abdennadher
 * @author Steven Liatti
 */
public class CffCompute {
    private Net net;
    public static ArrayList<String> cityNames;
    private Dijkstra dijkstra;
    private Floyd floyd;

    /**
     * Construit un CffCompute à partir d'un fichier de données.
     * @param filePath Le nom du fichier.
     */
    public CffCompute(String filePath) {
        try {
            net = XmlFile.loadXmlFile(filePath);
            buildCityNames();
            dijkstra = new Dijkstra(net);
            floyd = new Floyd(net);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Imprime la liste des noms des villes du réseau.
     */
    public void outCityNames() {
        for (int i = 0; i < cityNames.size(); i++) {
            System.out.print("[" + i + ":" + cityNames.get(i) + "] ");
        }
        System.out.println();
    }

    /**
     * Imprime la matrice des poids initiale.
     */
    public void outInitialWeightMatrix() { outMatrix(floyd.getInitialWeightMatrix()); }

    /**
     * Imprime la liste des poids calculée grâce à Dijkstra.
     */
    public void outWeightList() {
        for (String city : dijkstra.getWeightList().keySet()) {
            System.out.print(city);
            for (Node node : dijkstra.getWeightList().get(city)) { System.out.print(" " + node); }
            System.out.println();
        }
    }

    /**
     * Imprime la matrice des temps de parcours de Floyd.
     */
    public void outWeightMatrixFloyd() { outMatrix(floyd.getWeightMatrixFloyd()); }

    /**
     * Imprime la matrice des précédences de Floyd.
     */
    public void outPrecMatrixFloyd() { outMatrix(floyd.getPrecMatrixFloyd()); }

    /**
     * Imprime le temps/poids de parcours entre les deux villes données en arguments selon Floyd.
     * @param departure Le nom de la ville de départ
     * @param destination Le nom de la ville de destination
     * @return Le temps/poids de parcours entre les deux villes en int
     */
    public int outTimeTowCitiesFloyd(String departure, String destination) {
        if (!cityNames.contains(departure) || !cityNames.contains(destination))
            return -1;
        int i = cityNames.indexOf(departure);
        int j = cityNames.indexOf(destination);
        int duration = floyd.getWeightMatrixFloyd()[i][j];
        System.out.println(duration);
        return duration;
    }

    /**
     * Imprime le parcours entre deux villes selon Floyd.
     * @param departure Le nom de la ville de départ
     * @param destination Le nom de la ville de destination
     * @return Le parcours, les différentes villes à traverser
     */
    public StringBuilder outPathTowCitiesFloyd(String departure, String destination) {
        if (!cityNames.contains(departure)) return null;
        if (!cityNames.contains(destination)) return null;

        int i = cityNames.indexOf(departure);
        int j = cityNames.indexOf(destination);
        ArrayList<String> path = new ArrayList<>();
        int previous = floyd.getPrecMatrixFloyd()[i][j];
        if (previous != -1) {
            while (previous != i && previous != -1) {
                path.add(0, cityNames.get(previous));
                previous = floyd.getPrecMatrixFloyd()[i][previous];
            }
            path.add(0, departure);
            path.add(destination);
        }
        if (departure.equals(destination)) { path.add(departure); }
        if (path.isEmpty()) return null;
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (String s: path) {
            builder.append(s + ":");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]");
        System.out.println(builder.toString());
        return builder;
    }

    /**
     * Imprime le temps de parcours depuis une ville de départ vers toutes les villes du réseau
     * avec l'algorithme de Dijkstra.
     * @param startCity Le nom de la ville de départ
     */
    public void outTimeArrayDijkstra(String startCity) {
        dijkstra.compute(startCity);
        for (Node n : dijkstra.sortNodes()) {
            System.out.print("[" + n.getName() + ":" + n.getDuration() + "] ");
        }
        System.out.println();
    }

    /**
     * Imprime le tableau des précédences en appliquant l'algorithme de Dijkstra.
     * @param startCity Le nom de la ville de départ
     */
    public void outPrecArrayDijkstra(String startCity) {
        dijkstra.compute(startCity);
        for (Node n : dijkstra.sortNodes()) {
            if (n.getPredecessor() != null)
                System.out.print("[" + n.getPredecessor() + "<-" + n.getName() + "] ");
        }
        System.out.println();
    }

    /**
     * Imprime le temps/poids de parcours entre les deux villes données en arguments selon Dijkstra.
     * @param departure Le nom de la ville de départ
     * @param destination Le nom de la ville de destination
     * @return Le temps/poids de parcours entre les deux villes en int
     */
    public int outTimeTwoCitiesDijkstra(String departure, String destination) {
        dijkstra.compute(departure);
        int duration = dijkstra.getGraph().get(destination).getDuration();
        System.out.println(duration);
        return duration;
    }

    /**
     * Imprime le parcours entre deux villes selon Dijkstra.
     * @param departure Le nom de la ville de départ
     * @param destination Le nom de la ville de destination
     * @return Le parcours, les différentes villes à traverser
     */
    public StringBuilder outPathTwoCitiesDijkstra(String departure, String destination) {
        dijkstra.compute(departure);
        Stack<String> stack = new Stack<>();
        Node predecessor = dijkstra.getGraph().get(destination);
        while (predecessor.getDuration() != 0) {
            stack.add(predecessor.getName());
            if (predecessor.getPredecessor() == null) {
                return null;
            }
            predecessor = dijkstra.getGraph().get(predecessor.getPredecessor());
        }
        stack.add(predecessor.getName());
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        while (!stack.isEmpty()) {
            builder.append(stack.pop()).append(":");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]");
        System.out.println(builder.toString());
        return builder;
    }

    /**
     * Ajoute une ville au réseau, si elle n'est pas déjà présente.
     * @param city Le nom de la ville à ajouter
     * @return True si ajout, False sinon
     */
    public boolean addCity(String city) {
        if (cityNames.contains(city)) { return false; }
        else {
            cityNames.add(city);
            net.getCityList().add(new City(city));
            updateAfterChanges();
            return true;
        }
    }

    /**
     * Ajoute une ville au réseau, si elle n'est pas déjà présente.
     * @param city Le nom de la ville à ajouter
     * @return True si ajout, False sinon
     */
    public boolean addCity(String city, int longitude, int latitude) {
        if (cityNames.contains(city)) { return false; }
        else {
            cityNames.add(city);
            net.getCityList().add(new City(city, longitude, latitude));
            updateAfterChanges();
            return true;
        }
    }

    /**
     * Ajoute une connexion entre deux villes.
     * @param city1 Le nom de la 1ère ville
     * @param city2 Le nom de la 2ème ville
     * @param durationString La durée, en String
     * @return 0 si ajout, -1 sinon
     */
    public int addConnection(String city1, String city2, String durationString) {
        if (!cityNames.contains(city1)) {
            return -1;
        }
        if (!cityNames.contains(city2)) {
            return -1;
        }
        int duration = -1;
        try {
            duration = Integer.parseInt(durationString);
        } catch (Exception e) {}
        if (duration == -1) {
            return -1;
        }
        Connection newConnection = new Connection(city1, city2, duration);
        net.getConnectionList().add(newConnection);
        updateAfterChanges();
        return 0;
    }

    /**
     * Supprime une ville du réseau, si présente.
     * @param city Le nom de la ville à supprimer
     * @return True si la suppression a réussi, False sinon
     */
    public boolean removeCity(String city) {
        removeAllConnections(city);
        Iterator<City> i = net.getCityList().iterator();
        while (i.hasNext()) {
            City c = i.next(); // must be called before you can call i.remove()
            if (c.getName().equals(city)) {
                i.remove();
                break;
            }
        }
        updateAfterChanges();
        return true;
    }

    /**
     * Supprime une connexion entre deux villes, si elle existe.
     * @param city1 Le nom de la 1ère ville
     * @param city2 Le nom de la 2ème ville
     * @return 0 si suppression, -1 sinon
     */
    public int removeConnection(String city1, String city2) {
        if (!cityNames.contains(city1)) { return -1; }
        if (!cityNames.contains(city2)) { return -1; }
        Iterator<Connection> i = net.getConnectionList().iterator();
        while (i.hasNext()) {
            Connection c = i.next();
            String c1 = c.getVil_1();
            String c2 = c.getVil_2();
            if ((c1.equals(city1) && c2.equals(city2)) ||
                    c1.equals(city2) && c2.equals(city1)) {
                i.remove();
            }
        }
        updateAfterChanges();
        return 0;
    }

    /**
     * Indique si le graphe courant est connexe ou non.
     * @return True si le graphe est connexe, False sinon
     */
    public boolean isConnectedGraph() {
        boolean isConnected = true;
        for (int i = 0; i < floyd.getWeightMatrixFloyd().length; i++) {
            for (int j = 0; j < floyd.getWeightMatrixFloyd().length; j++) {
                if (floyd.getWeightMatrixFloyd()[i][j] == Integer.MAX_VALUE) {
                    isConnected = false;
                    break;
                }
            }
            if (!isConnected) { break; }
        }
        return isConnected;
    }

    /**
     * Sauvegarde le graphe courant vers un fichier XML.
     * @param filePath Le nom du fichier XML
     */
    public void toXmlFile(String filePath) {
        try {
            XmlFile.storeXmlFormat(filePath, net);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void outMatrix(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                if (m[i][j] == Integer.MAX_VALUE)
                    System.out.print("inf ");
                else
                    System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void buildCityNames() {
        cityNames = new ArrayList<>();
        for (City c: net.getCityList()) { cityNames.add(c.getName()); }
    }

    private int removeAllConnections(String cityName) {
        Iterator<Connection> i = net.getConnectionList().iterator();
        while (i.hasNext()) {
            Connection connection = i.next();
            String c1 = connection.getVil_1();
            String c2 = connection.getVil_2();
            if (c1.equals(cityName) || c2.equals(cityName)) {
                i.remove();
            }
        }
        return 0;
    }

    private void updateAfterChanges() {
        buildCityNames();
        floyd.update();
        dijkstra.buildWeightList();
    }

    // GUI

    /**
     * Retourne le graphe {@link Net} courant.
     * @return Le graphe {@link Net} courant
     */
    public Net getNet() {
        return net;
    }

    /**
     * Retourne la liste des noms des villes.
     * @return La liste des noms des villes
     */
    public ArrayList<String> getCityNames() {
        return cityNames;
    }

    /**
     * Retourne la matrice de Floyd.
     * @return La matrice de Floyd
     */
    public int[][] getWeightMatrixFloyd() {
        return floyd.getWeightMatrixFloyd();
    }

    /**
     * Retourne la matrice des précédences de Floyd.
     * @return La matrice des précédences de Floyd
     */
    public int[][] getPrecMatrixFloyd() {
        return floyd.getPrecMatrixFloyd();
    }

    /**
     * Retourne les noeuds Dijkstra dans l'ordre croissant.
     * @param departure La ville de départ
     * @return
     */
    public List<Node> getNodesDijkstra(String departure) {
        dijkstra.compute(departure);
        return dijkstra.sortNodes();
    }

    /**
     * Indique si une ville est connectée (pour la GUI).
     * @param cityName Le nom de la ville
     * @return True si la ville est connectée, False sinon
     */
    public boolean isConnectedCity(String cityName) {
        int index = cityNames.indexOf(cityName);
        int x = 0;
        for (int i = 0; i < floyd.getWeightMatrixFloyd()[index].length; i++) {
            if (floyd.getWeightMatrixFloyd()[index][i] == Integer.MAX_VALUE) {
                x++;
            }
        }
        return !(x == floyd.getWeightMatrixFloyd()[index].length - 1);
    }
}
