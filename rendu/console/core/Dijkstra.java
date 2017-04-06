package core;

import model.City;
import model.Connection;
import model.Node;
import model.Net;

import java.util.*;

/**
 * Classe implémentant la construction de la liste des poids dans un graphe et l'algorithme de Dijkstra.
 * @author Raed Abdennadher
 * @author Steven Liatti
 */
public class Dijkstra {
    private Net net;
    private Map<String, List<Node>> weightList;
    private Map<String, Node> graph;
    private Map<String, Map<String, Node>> dijkstraPaths;

    /**
     * Construit un objet Dijkstra à partir d'un réseau de villes ({@link Net}.
     * @param net Un réseau de villes
     */
    public Dijkstra(Net net) {
        this.net = net;
        buildWeightList();
        dijkstraPaths = new HashMap<>();
    }

    /**
     * Construit la liste des poids avec le réseau de villes et connexions courantes.
     */
    public void buildWeightList() {
        weightList = new LinkedHashMap<>();
        for (Connection c : net.getConnectionList()) {
            addNeighbour(c.getVil_1(), c.getVil_2(), c.getDuratin());
            addNeighbour(c.getVil_2(), c.getVil_1(), c.getDuratin());
        }
    }

    /**
     * Algorithme de Dijkstra, construit le plus court chemin entre la ville donnée en paramètres
     * et toutes les autres villes atteignables du réseau.
     * @param startCity La ville de départ ({@link String}
     * @return Une {@link Map} associant la ville de départ avec les poids et les précédences pour
     * chaque ville du réseau.
     */
    public Map<String, Node> compute(String startCity) {
        // "Cache" de Dijkstra, retourne le chemin si déjà calculé.
        if (dijkstraPaths.containsKey(startCity)) {
            graph = dijkstraPaths.get(startCity);
            return dijkstraPaths.get(startCity);
        }
        // Implémentation de l'algo comme vue en cours.
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
        dijkstraPaths.put(startCity, graph);
        return graph;
    }

    /**
     * Renvoie les {@link Node} d'un graphe courant dans l'ordre alphabétique (principalement
     * pour l'affichage).
     * @return Une {@link List} de {@link Node} triés par ordre alphabétique.
     */
    public List<Node> sortNodes() {
        List<Node> list = new ArrayList<>(graph.values());
        list.sort(new Node());
        return list;
    }

    /**
     * Retourne le graphe courant ({@link Map} entre un nom de ville et ses "voisins")
     * @return
     */
    public Map<String, Node> getGraph() {
        return graph;
    }

    /**
     * Retourne la liste des poids.
     * @return Une {@link Map} entre chaque ville et ses voisins directs ({@link List} de {@link Node}).
     */
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
