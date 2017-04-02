package core;

import model.City;
import model.Connection;
import model.Node;
import model.Net;

import javax.xml.bind.*;
import java.util.*;

/**
 * Created by raed on 14.03.17.
 */
public class CffCompute {
    private Net net;
    public static ArrayList<String> cityNames;
    private Dijkstra dijkstra;
    private Floyd floyd;

    public CffCompute(String filePath) {
        try {
            //charger le fichier XML
            net = XmlFile.loadXmlFile(filePath);
            dijkstra = new Dijkstra(net);
            //liste des villes
            buildCityNames();
            floyd = new Floyd(net);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void outCityNames() {
        for (int i = 0; i < cityNames.size(); i++) {
            System.out.print("[" + i + ":" + cityNames.get(i) + "] ");
        }
        System.out.println();
    }

    public void outInitialWeightMatrix() { outMatrix(floyd.getInitialWeightMatrix()); }

    public void outWeightList() {
        for (String city : dijkstra.getWeightList().keySet()) {
            System.out.print(city);
            for (Node node : dijkstra.getWeightList().get(city)) { System.out.print(" " + node); }
            System.out.println();
        }
    }

    public void outWeightMatrixFloyd() { outMatrix(floyd.getWeightMatrixFloyd()); }

    public void outPrecMatrixFloyd() { outMatrix(floyd.getPrecMatrixFloyd()); }

    public int outTimeTowCitiesFloyd(String city1, String city2) {
        if (!cityNames.contains(city1) || !cityNames.contains(city2))
            return -1;
        int i = cityNames.indexOf(city1);
        int j = cityNames.indexOf(city2);
        int duration = floyd.getWeightMatrixFloyd()[i][j];
        System.out.println(duration);
        return duration;
    }

    public StringBuilder outPathTowCitiesFloyd(String city1, String city2) {
        if (!cityNames.contains(city1)) return null;
        if (!cityNames.contains(city2)) return null;

        int i = cityNames.indexOf(city1);
        int j = cityNames.indexOf(city2);
        ArrayList<String> path = new ArrayList<>();
        int previous = floyd.getPrecMatrixFloyd()[i][j];
        if (previous != -1) {
            while (previous != i && previous != -1) {
                path.add(0, cityNames.get(previous));
                previous = floyd.getPrecMatrixFloyd()[i][previous];
            }
            path.add(0, city1);
            path.add(city2);
        }
        if (city1.equals(city2)) { path.add(city1); }
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

    public void outTimeDijkstra(String startCity) {
        dijkstra.compute(startCity);
        for (Node n : dijkstra.sortNodes()) {
            System.out.print("[" + n.getName() + ":" + n.getDuration() + "] ");
        }
        System.out.println();
    }

    public void outPrecArrayDijkstra(String startCity) {
        dijkstra.compute(startCity);
        for (Node n : dijkstra.sortNodes()) {
            if (n.getPredecessor() != null)
                System.out.print("[" + n.getPredecessor() + "<-" + n.getName() + "] ");
        }
        System.out.println();
    }

    public int outTimeTwoCitiesDijkstra(String departure, String destination) {
        dijkstra.compute(departure);
        int duration = dijkstra.getGraph().get(destination).getDuration();
        System.out.println(duration);
        return duration;
    }

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

    public boolean addCity(String city) {
        if (cityNames.contains(city)) {
            return false;
        }
        else {
            cityNames.add(city);
            net.getCityList().add(new City(city));
            updateAfterChanges();
            return true;
        }
    }

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

    public boolean iaConnectedCity(String cityName) {
        int index = cityNames.indexOf(cityName);
        int x = 0;
        for (int i = 0; i < floyd.getWeightMatrixFloyd()[index].length; i++) {
            if (floyd.getWeightMatrixFloyd()[index][i] == Integer.MAX_VALUE) {
                x++;
            }
        }
        return !(x == floyd.getWeightMatrixFloyd()[index].length - 1);
    }

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

    public Net getNet() {
        return net;
    }

    public ArrayList<String> getCityNames() {
        return cityNames;
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
