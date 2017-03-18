package management;

import model.City;
import model.Connection;
import model.Neighbour;
import model.Net;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.*;

/**
 * Created by raed on 14.03.17.
 */
public class GraphManagement {
    private Net net;
    private ArrayList<String> cityNamesArrayList;
    private int[][] initialWeightMatrix;
    private Map<String, List<Neighbour>> weightList;
//    private Map<City, List<Connection>> listMap;

    private int[][] weightMatrixFloyd;
    private int[][] precMatrixFloyd;

    public GraphManagement(String filePath) {
        try {

            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(Net.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            this.net = (Net) jaxbUnmarshaller.unmarshal(file);

            //liste des villes
            buildCityNamesArrayList();
            //matrice des poids
            buildInitialWeightMatrix();
            //liste des poids
            buildWeightList();
            //matrice des poids et matrice de précédence Floyd
            buildMatrixFloyd();

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
        for (City c: net.getCityList()) {
            cityNamesArrayList.add(c.getName());
        }
    }

    public void displayCityNamesArrayList() {
        for (int i = 0; i < cityNamesArrayList.size(); i++) {
            System.out.print("[" + i + ":" + cityNamesArrayList.get(i) + "] ");
        }
        System.out.println();
    }

    public void buildInitialWeightMatrix() {
        int n = net.getCityList().size();
        initialWeightMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    initialWeightMatrix[i][j] = 0;
                else
                    initialWeightMatrix[i][j] = Integer.MAX_VALUE;
            }
        }
        for (Connection c : net.getConnectionList()) {
            int i = cityNamesArrayList.indexOf(c.getVil_1());
            int j = cityNamesArrayList.indexOf(c.getVil_2());
            initialWeightMatrix[i][j] = c.getDuratin();
            int x = i;
            i = j;
            j = x;
            initialWeightMatrix[i][j] = c.getDuratin();
        }
    }

    public void displayInitialWeightMatrix() {
        displayMatrix(initialWeightMatrix);
    }

    private void addNeighbour(String city, String neighbour, int duration) {
        if (!weightList.containsKey(city)) {
            weightList.put(city, new LinkedList<>());
        }
        weightList.get(city).add(new Neighbour(neighbour, duration));
    }

    public void buildWeightList() {
        weightList = new LinkedHashMap<>();
        for (Connection c : net.getConnectionList()) {
            addNeighbour(c.getVil_1(), c.getVil_2(), c.getDuratin());
            addNeighbour(c.getVil_2(), c.getVil_1(), c.getDuratin());
        }
    }

    public void displayWeightList() {
        for (String city : weightList.keySet()) {
            System.out.print(city);
            for (Neighbour neighbour : weightList.get(city)) {
                System.out.print(" " + neighbour);
            }
            System.out.println();
        }
    }

    private void buildMatrixFloyd() {
        int n = net.getCityList().size();
        weightMatrixFloyd = new int[n][n];
        precMatrixFloyd = new int[n][n];
        initMatrixFloyd();
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (i != k && weightMatrixFloyd[i][k] != Integer.MAX_VALUE) {
                    for (int j = 0; j < n; j++) {
                        if (i != j && j != k && weightMatrixFloyd[k][j] != Integer.MAX_VALUE) {
                            if (weightMatrixFloyd[i][k] + weightMatrixFloyd[k][j] < weightMatrixFloyd[i][j]) {
                                weightMatrixFloyd[i][j] = weightMatrixFloyd[i][k] + weightMatrixFloyd[k][j];
                                precMatrixFloyd[i][j] = precMatrixFloyd[k][j];
                            }
                        }
                    }
                }
            }
        }
    }

    public void displayWeightMatrixFloyd() {
        displayMatrix(weightMatrixFloyd);
    }

    public void displayPrecMatrixFloyd() {
        displayMatrix(precMatrixFloyd);
    }

    private void initMatrixFloyd() {
        int n = net.getCityList().size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                weightMatrixFloyd[i][j] = initialWeightMatrix[i][j];
                if (i == j || initialWeightMatrix[i][j] == Integer.MAX_VALUE)
                    precMatrixFloyd[i][j] = -1;
                else
                    precMatrixFloyd[i][j] = i;
            }
        }
    }

    public int timeTowCitiesFloyd(String city1, String city2) {
        if (!cityNamesArrayList.contains(city1) || !cityNamesArrayList.contains(city2)) {
            return -1;
        }
        int i = cityNamesArrayList.indexOf(city1);
        int j = cityNamesArrayList.indexOf(city2);
        return weightMatrixFloyd[i][j];
    }

    public ArrayList<String> pathTowCitiesFloyd(String city1, String city2) {
        if (!cityNamesArrayList.contains(city1)) {
            return null;
        }
        if (!cityNamesArrayList.contains(city2)) {
            return null;
        }
        int i = cityNamesArrayList.indexOf(city1);
        int j = cityNamesArrayList.indexOf(city2);
        ArrayList<String> path = new ArrayList<>();
        int previous = precMatrixFloyd[i][j];
        while (previous != i && previous != -1) {
            path.add(0, cityNamesArrayList.get(previous));
            previous = precMatrixFloyd[i][previous];
        }
        path.add(0, city1);
        path.add(city2);
        return path;
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
        this.net.getConnectionList().add(newConnection);
        updateAfterConnectionChanges();
        return 0;
    }

    public int removeConnection(String city1, String city2) {
        if (!cityNamesArrayList.contains(city1)) {
            return -1;
        }
        if (!cityNamesArrayList.contains(city2)) {
            return -1;
        }
        for (int i = 0; i < net.getConnectionList().size(); i++) {
            String c1 = net.getConnectionList().get(i).getVil_1();
            String c2 = net.getConnectionList().get(i).getVil_1();
            if ((c1.equals(city1) && c2.equals(city2)) ||
                    c1.equals(city2) && c2.equals(city1)) {
                net.getConnectionList().remove(i);
            }
        }
        updateAfterConnectionChanges();
        return 0;
    }

    private void updateAfterConnectionChanges() {
        buildInitialWeightMatrix();
        buildMatrixFloyd();
    }
}
