package management;

import model.City;
import model.Connection;
import model.Node;
import model.Net;

import javax.xml.bind.*;
import java.util.*;

/**
 * Created by raed on 14.03.17.
 */
public class GraphManagement {
    private Net net;
    public static ArrayList<String> cityNamesArrayList;
    private List<Node> nodesDijkstra;
    private Dijkstra dijkstra;
    private Floyd floyd;

    public GraphManagement(String filePath) {
        try {
            //charger le fichier XML
            net = XmlFileManagement.loadXmlFile(filePath);
            dijkstra = new Dijkstra(net);
            //liste des villes
            buildCityNamesArrayList();
            floyd = new Floyd(net);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void displayCityNamesArrayList() {
        for (int i = 0; i < cityNamesArrayList.size(); i++) {
            System.out.print("[" + i + ":" + cityNamesArrayList.get(i) + "] ");
        }
        System.out.println();
    }

    public void displayWeightList() {
        for (String city : dijkstra.getWeightList().keySet()) {
            System.out.print(city);
            for (Node node : dijkstra.getWeightList().get(city)) { System.out.print(" " + node); }
            System.out.println();
        }
    }

    public void displayWeightMatrixFloyd() { displayMatrix(floyd.getWeightMatrixFloyd()); }

    public void displayPrecMatrixFloyd() { displayMatrix(floyd.getPrecMatrixFloyd()); }

    public int timeTowCitiesFloyd(String city1, String city2) {
        if (!cityNamesArrayList.contains(city1) || !cityNamesArrayList.contains(city2))
            return -1;
        int i = cityNamesArrayList.indexOf(city1);
        int j = cityNamesArrayList.indexOf(city2);
        return floyd.getWeightMatrixFloyd()[i][j];
    }

    public ArrayList<String> pathTowCitiesFloyd(String city1, String city2) {
        if (!cityNamesArrayList.contains(city1)) return null;
        if (!cityNamesArrayList.contains(city2)) return null;

        int i = cityNamesArrayList.indexOf(city1);
        int j = cityNamesArrayList.indexOf(city2);
        ArrayList<String> path = new ArrayList<>();
        int previous = floyd.getPrecMatrixFloyd()[i][j];
        if (previous != -1) {
            while (previous != i && previous != -1) {
                path.add(0, cityNamesArrayList.get(previous));
                previous = floyd.getPrecMatrixFloyd()[i][previous];
            }
            path.add(0, city1);
            path.add(city2);
        }
        if (city1.equals(city2)) { path.add(city1); }
        return path;
    }

    public void displayDijkstraTime(String startCity) {
        dijkstra.compute(startCity);
        for (Node n : dijkstra.sortNodes()) {
            System.out.print("[" + n.getName() + ":" + n.getDuration() + "] ");
        }
        System.out.println();
    }

    public void displayPrecedenceArray(String startCity) {
        dijkstra.compute(startCity);
        for (Node n : dijkstra.sortNodes()) {
            if (n.getPredecessor() != null)
                System.out.print("[" + n.getPredecessor() + "<-" + n.getName() + "] ");
        }
        System.out.println();
    }

    public int displayTimeBetweenTwoCities(String departure, String destination) {
        dijkstra.compute(departure);
        int duration = dijkstra.getGraph().get(destination).getDuration();
        if (duration == Integer.MAX_VALUE)
            System.out.println("inf");
        else
            System.out.println(duration);
        return duration;
    }

    public StringBuilder displayPathBetweenTwoCities(String departure, String destination) {
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

    public void displayInitialWeightMatrix() { displayMatrix(floyd.getInitialWeightMatrix()); }

    public boolean addCity(String city) {
        if (cityNamesArrayList.contains(city)) {
            return false;
        }
        else {
            cityNamesArrayList.add(city);
            net.getCityList().add(new City(city));
            updateAfterChanges();
            return true;
        }
    }

    public int addNewConnection(String city1, String city2, String durationString) {
        if (!cityNamesArrayList.contains(city1)) {
            return -1;
        }
        if (!cityNamesArrayList.contains(city2)) {
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

    public int removeConnection(String city1, String city2) {
        if (!cityNamesArrayList.contains(city1)) { return -1; }
        if (!cityNamesArrayList.contains(city2)) { return -1; }
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

    public boolean isConnectedGraph() {
        boolean isConnected = true;
        for (int i = 0; i < floyd.getWeightMatrixFloyd().length; i++) {
            for (int j = 0; j < floyd.getWeightMatrixFloyd().length; j++) {
                if (floyd.getWeightMatrixFloyd()[i][j] == Integer.MAX_VALUE) {
                    isConnected = false;
                    break;
                }
            }
            if (!isConnected) {
                break;
            }
        }
        return isConnected;
    }

    public void toXmlFile(String filePath) {
        try {
            XmlFileManagement.storeXmlFormat(filePath, net);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void displayMatrix(int[][] m) {
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

    private void buildCityNamesArrayList() {
        cityNamesArrayList = new ArrayList<>();
        for (City c: net.getCityList()) { cityNamesArrayList.add(c.getName()); }
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
        buildCityNamesArrayList();
        floyd.update();
        dijkstra.buildWeightList();
    }

    // GUI

    public Net getNet() {
        return net;
    }

    public ArrayList<String> getCityNamesArrayList() {
        return cityNamesArrayList;
    }

    public int[][] getWeightMatrixFloyd() {
        return floyd.getWeightMatrixFloyd();
    }

    public int[][] getPrecMatrixFloyd() {
        return floyd.getPrecMatrixFloyd();
    }

    public List<Node> getNodesDijkstra(String departure) {
        dijkstra.compute(departure);
        return dijkstra.sortNodes();
    }
}
