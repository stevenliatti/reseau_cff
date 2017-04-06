package model;

import java.util.Comparator;

/**
 * Classe représentant un noeud du graphe, c'est-à-dire le nom de la ville avec le poids reliant
 * son prédécesseur. Utilisé pour la liste des poids et l'algorithme de Dijkstra.
 * L'implémentation de l'interface {@link Comparator} est nécessaire à l'algorithme de Dijkstra.
 * @author Raed Abdennadher
 * @author Steven Liatti
 */
public class Node implements Comparator<Node> {
    private String name;
    private int duration;
    private String predecessor;

    /**
     * Constructeur par défaut.
     */
    public Node() {}

    /**
     * Construit un noeud avec un nom, un poids associé au prédécesseur.
     * @param name Le nom de la ville
     * @param duration La durée pour atteindre le prédécesseur
     * @param predecessor La ville précédente, le prédécesseur
     */
    public Node(String name, int duration, String predecessor) {
        this.name = name;
        this.duration = duration;
        this.predecessor = predecessor;
    }

    /**
     * Retourne le nom de la ville/noeud.
     * @return Le nom de la ville/noeud
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne la durée/poids entre le noeud courant et son prédécesseur.
     * @return La durée/poids entre le noeud courant et son prédécesseur
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Modifie la durée/poids entre le noeud courant et son prédécesseur.
     * @param duration La nouvelle durée/poids entre le noeud courant et son prédécesseur
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Retourne le nom du prédécesseur au noeud courant.
     * @return Le nom du prédécesseur au noeud courant
     */
    public String getPredecessor() {
        return predecessor;
    }

    /**
     * Modifie le nom du prédécesseur au noeud courant.
     * @param predecessor Le nouveau nom du prédécesseur au noeud courant
     */
    public void setPredecessor(String predecessor) {
        this.predecessor = predecessor;
    }

    @Override
    public String toString() {
        return "[" + name + ":" + duration + "]";
    }

    /**
     * Comparaison sur le nom.
     * @param o Un autre objet.
     * @return True si le nom du noeud en paramètres = le nom du noeau courant
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return name != null ? name.equals(node.name) : node.name == null;
    }

    @Override
    public int compare(Node n1, Node n2) {
        if (n1.duration < n2.duration)
            return -1;
        else if (n1.duration > n2.duration)
            return 1;
        else
            return 0;
    }
}
