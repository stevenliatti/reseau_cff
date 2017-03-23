package management;

import model.City;
import model.Connection;
import model.Neighbour;
import model.Net;

import javax.xml.bind.*;
import java.io.File;
import java.util.*;

/**
 * Created by raed on 14.03.17.
 */
public class GraphManagement {
    private JAXBContext jaxbContext;
    private Net net;
    private ArrayList<String> cityNamesArrayList;
    private int[][] initialWeightMatrix;
    private Map<String, List<Neighbour>> weightList;
    private Map<String, Neighbour> graph;

    private int[][] weightMatrixFloyd;
    private int[][] precMatrixFloyd;

    public GraphManagement(String filePath) {
        try {

            //charger le fichier XML
            this.net = XmlFileManagement.loadXmlFile(filePath);
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
        this.cityNamesArrayList = new ArrayList<>();
        for (City c: this.net.getCityList()) {
            this.cityNamesArrayList.add(c.getName());
        }
    }

    public void displayCityNamesArrayList() {
        for (int i = 0; i < this.cityNamesArrayList.size(); i++) {
            System.out.print("[" + i + ":" + this.cityNamesArrayList.get(i) + "] ");
        }
        System.out.println();
    }

    private void buildInitialWeightMatrix() {
        int n = this.net.getCityList().size();
        this.initialWeightMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    this.initialWeightMatrix[i][j] = 0;
                else
                    this.initialWeightMatrix[i][j] = Integer.MAX_VALUE;
            }
        }
        for (Connection c : this.net.getConnectionList()) {
            int i = this.cityNamesArrayList.indexOf(c.getVil_1());
            int j = this.cityNamesArrayList.indexOf(c.getVil_2());
            this.initialWeightMatrix[i][j] = c.getDuratin();
            int x = i;
            i = j;
            j = x;
            this.initialWeightMatrix[i][j] = c.getDuratin();
        }
    }

    public void displayInitialWeightMatrix() {
        displayMatrix(this.initialWeightMatrix);
    }

    private void addNeighbour(String city, String neighbour, int duration) {
        if (!this.weightList.containsKey(city)) {
            this.weightList.put(city, new LinkedList<>());
        }
        this.weightList.get(city).add(new Neighbour(neighbour, duration, city));
    }

    private void buildWeightList() {
        this.weightList = new LinkedHashMap<>();
        for (Connection c : this.net.getConnectionList()) {
            addNeighbour(c.getVil_1(), c.getVil_2(), c.getDuratin());
            addNeighbour(c.getVil_2(), c.getVil_1(), c.getDuratin());
        }
    }

    public void displayWeightList() {
        for (String city : this.weightList.keySet()) {
            System.out.print(city);
            for (Neighbour neighbour : this.weightList.get(city)) {
                System.out.print(" " + neighbour);
            }
            System.out.println();
        }
    }

    private void initDijkstra(String city) {
        this.graph = new HashMap<>();
        this.graph.put(city, new Neighbour(city, 0, null));
        for (String s : this.cityNamesArrayList) {
        	this.graph.putIfAbsent(s, new Neighbour(s, Integer.MAX_VALUE, null));
        }
    }

    private void relaxDijkstra(String u, String v, int weight) {
        int newDuration = this.graph.get(u).getDuration() + weight;
        if (this.graph.get(v).getDuration() > newDuration) {
            this.graph.get(v).setDuration(newDuration);
            this.graph.get(v).setPredecessor(u);
        }
    }

    private void dijkstra(String startCity) {
    	initDijkstra(startCity);
    	PriorityQueue<Neighbour> queue = new PriorityQueue<>(new Neighbour());
        for (Neighbour n : graph.values()) {
            queue.add(n);
        }
        while (!queue.isEmpty()) {
			Neighbour next = queue.poll();
			List<Neighbour> neighbours = this.weightList.get(next.getName());
			if (neighbours != null) {
				for (Neighbour n : neighbours) {
					relaxDijkstra(next.getName(), n.getName(), n.getDuration());
					Neighbour update = graph.get(n.getName());
					if (queue.remove(update))
						queue.add(update);
				}
			}
	    }
    }

    private List<Neighbour> sortGraph() {
        List<Neighbour> list = new ArrayList<>(graph.values());
        Collections.sort(list, new Neighbour());
        return list;
    }

    public void displayDijkstraTime(String startCity) {
    	dijkstra(startCity);
        for (Neighbour n : sortGraph()) {
            System.out.print("[" + n.getName() + ":" + n.getDuration() + "] ");
        }
        System.out.println();
    }

    public void displayPrecedenceArray(String startCity) {
	    dijkstra(startCity);
        for (Neighbour n : sortGraph()) {
            if (n.getPredecessor() != null)
                System.out.print("[" + n.getPredecessor() + "<-" + n.getName() + "] ");
        }
        System.out.println();
    }

    public void displayTimeBetweenTwoCities(String departure, String destination) {
	    dijkstra(departure);
        int duration = graph.get(destination).getDuration();
        if (duration == Integer.MAX_VALUE)
            System.out.println("inf");
        else
            System.out.println(duration);
    }

    public void displayPathBetweenTwoCities(String departure, String destination) {
	    dijkstra(departure);
    	Stack<String> stack = new Stack<>();
    	Neighbour predecessor = graph.get(destination);
    	while (predecessor.getDuration() != 0) {
		    stack.add(predecessor.getName());
		    predecessor = graph.get(predecessor.getPredecessor());
	    }
	    stack.add(predecessor.getName());
    	StringBuilder builder = new StringBuilder();
    	builder.append("[");
	    while (!stack.isEmpty()) {
	    	builder.append(stack.pop() + ":");
	    }
	    builder.deleteCharAt(builder.length() - 1);
	    builder.append("]");
	    System.out.println(builder.toString());
    }

    private void buildMatrixFloyd() {
        int n = this.net.getCityList().size();
        this.weightMatrixFloyd = new int[n][n];
        this.precMatrixFloyd = new int[n][n];
        initMatrixFloyd();
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (i != k && this.weightMatrixFloyd[i][k] != Integer.MAX_VALUE) {
                    for (int j = 0; j < n; j++) {
                        if (i != j && j != k && this.weightMatrixFloyd[k][j] != Integer.MAX_VALUE) {
                            if (this.weightMatrixFloyd[i][k] + this.weightMatrixFloyd[k][j] < this.weightMatrixFloyd[i][j]) {
                                this.weightMatrixFloyd[i][j] = this.weightMatrixFloyd[i][k] + this.weightMatrixFloyd[k][j];
                                this.precMatrixFloyd[i][j] = this.precMatrixFloyd[k][j];
                            }
                        }
                    }
                }
            }
        }
    }

    public void displayWeightMatrixFloyd() {
        displayMatrix(this.weightMatrixFloyd);
    }

    public void displayPrecMatrixFloyd() {
        displayMatrix(this.precMatrixFloyd);
    }

    private void initMatrixFloyd() {
        int n = this.net.getCityList().size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.weightMatrixFloyd[i][j] = this.initialWeightMatrix[i][j];
                if (i == j || this.initialWeightMatrix[i][j] == Integer.MAX_VALUE)
                    this.precMatrixFloyd[i][j] = -1;
                else
                    this.precMatrixFloyd[i][j] = i;
            }
        }
    }

    public int timeTowCitiesFloyd(String city1, String city2) {
        if (!this.cityNamesArrayList.contains(city1) || !this.cityNamesArrayList.contains(city2)) {
            return -1;
        }
        int i = this.cityNamesArrayList.indexOf(city1);
        int j = this.cityNamesArrayList.indexOf(city2);
        return this.weightMatrixFloyd[i][j];
    }

    public ArrayList<String> pathTowCitiesFloyd(String city1, String city2) {
        if (!this.cityNamesArrayList.contains(city1)) {
            return null;
        }
        if (!this.cityNamesArrayList.contains(city2)) {
            return null;
        }
        int i = this.cityNamesArrayList.indexOf(city1);
        int j = this.cityNamesArrayList.indexOf(city2);
        ArrayList<String> path = new ArrayList<>();
        int previous = this.precMatrixFloyd[i][j];
        if (previous != -1) {
            while (previous != i && previous != -1) {
                path.add(0, this.cityNamesArrayList.get(previous));
                previous = this.precMatrixFloyd[i][previous];
            }
            path.add(0, city1);
            path.add(city2);
        }
        if (city1.equals(city2)) {
            path.add(city1);
        }
        return path;
    }

    public boolean addCity(String city) {
        if (this.cityNamesArrayList.contains(city)) {
            return false;
        }
        else {
            this.cityNamesArrayList.add(city);
            this.net.getCityList().add(new City(city));
            updateAfterChanges();
            return true;
        }
    }

    public boolean removeCity(String city) {
        removeAllConnections(city);
        Iterator<City> i = this.net.getCityList().iterator();
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

    public int addNewConnection(String city1, String city2, String durationString) {
        if (!this.cityNamesArrayList.contains(city1)) {
            return -1;
        }
        if (!this.cityNamesArrayList.contains(city2)) {
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
        updateAfterChanges();
        return 0;
    }

    public int removeConnection(String city1, String city2) {
        if (!this.cityNamesArrayList.contains(city1)) {
            return -1;
        }
        if (!this.cityNamesArrayList.contains(city2)) {
            return -1;
        }
        Iterator<Connection> i = this.net.getConnectionList().iterator();
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

    public int removeAllConnections(String cityName) {
        Iterator<Connection> i = this.net.getConnectionList().iterator();
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
        buildInitialWeightMatrix();
        buildMatrixFloyd();
        buildWeightList();
    }

    public boolean isConnectedGraph() {
        boolean isConnected = true;
        for (int i = 0; i < weightMatrixFloyd.length; i++) {
            for (int j = 0; j < weightMatrixFloyd.length; j++) {
                if (weightMatrixFloyd[i][j] == Integer.MAX_VALUE) {
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
            XmlFileManagement.storeXmlFormat(filePath, this.net);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
