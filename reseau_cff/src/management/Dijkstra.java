package management;

import model.City;
import model.Connection;
import model.Node;
import model.Net;

import java.util.*;

/**
 * Created by stevenliatti on 29.03.17.
 */
public class Dijkstra {
    private Net net;
    private Map<String, List<Node>> weightList;
    private Map<String, Node> graph;

    public Dijkstra(Net net) {
        this.net = net;
        buildWeightList();
    }

    public void buildWeightList() {
        weightList = new LinkedHashMap<>();
        for (Connection c : net.getConnectionList()) {
            addNeighbour(c.getVil_1(), c.getVil_2(), c.getDuratin());
            addNeighbour(c.getVil_2(), c.getVil_1(), c.getDuratin());
        }
    }

    public Map<String, Node> compute(String startCity) {
        initDijkstra(startCity);
        PriorityQueue<Node> queue = new PriorityQueue<>(new Node());
        for (Node n : graph.values()) { queue.add(n); }

        while (!queue.isEmpty()) {
            Node next = queue.poll();
            List<Node> neighbours = weightList.get(next.getName());
            if (neighbours != null) {
                for (Node n : neighbours) {
                    relaxDijkstra(next.getName(), n.getName(), n.getDuration());
                    Node update = graph.get(n.getName());
                    if (queue.remove(update))
                        queue.add(update);
                }
            }
        }
        return graph;
    }

    public List<Node> sortNodes() {
        List<Node> list = new ArrayList<>(graph.values());
        list.sort(new Node());
        return list;
    }

    public Map<String, Node> getGraph() {
        return graph;
    }

    public Map<String, List<Node>> getWeightList() {
        return weightList;
    }

    private void addNeighbour(String city, String neighbour, int duration) {
        if (!weightList.containsKey(city)) { weightList.put(city, new LinkedList<>()); }
        weightList.get(city).add(new Node(neighbour, duration, city));
    }

    private void initDijkstra(String city) {
        graph = new HashMap<>();
        graph.put(city, new Node(city, 0, null));
        for (City c : net.getCityList())
            graph.putIfAbsent(c.getName(), new Node(c.getName(), Integer.MAX_VALUE, null));
    }

    private void relaxDijkstra(String u, String v, int weight) {
        int newDuration = graph.get(u).getDuration() + weight;
        newDuration = newDuration < 0 ? Integer.MAX_VALUE : newDuration;
        if (graph.get(v).getDuration() > newDuration) {
            graph.get(v).setDuration(newDuration);
            graph.get(v).setPredecessor(u);
        }
    }
}
