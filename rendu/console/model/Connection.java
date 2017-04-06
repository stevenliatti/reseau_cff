package model;

import javax.xml.bind.annotation.XmlType;

/**
 * Classe représentant le poids d'une connexion entre deux villes.
 * @author Raed Abdennadher
 * @author Steven Liatti
 */
@XmlType(propOrder = {"vil_1", "vil_2", "duratin"})
public class Connection {
    private String vil_1;
    private String vil_2;
    private int duratin;

    /**
     * Constructeur par défaut.
     */
    public Connection() {}

    /**
     * Construit une connexion entre @vil_1 et @vil_2 avec poids @duratin.
     * @param vil_1 Le nom de la 1ère ville.
     * @param vil_2 Le nom de la 2ème ville.
     * @param duratin Le poids/durée de parcours entre les deux villes.
     */
    public Connection(String vil_1, String vil_2, int duratin) {
        this.vil_1 = vil_1;
        this.vil_2 = vil_2;
        this.duratin = duratin;
    }

    /**
     * Retourne le nom de la 1ère ville.
     * @return Le nom de la 1ère ville
     */
    public String getVil_1() {
        return vil_1;
    }

    /**
     * Modifie le nom de la 1ère ville.
     * @param vil_1 Le nouveau nom
     */
    public void setVil_1(String vil_1) {
        this.vil_1 = vil_1;
    }

    /**
     * Retourne le nom de la 2ème ville.
     * @return Le nom de la 2ème ville
     */
    public String getVil_2() {
        return vil_2;
    }

    /**
     * Modifie le nom de la 2ème ville.
     * @param vil_2 Le nouveau nom
     */
    public void setVil_2(String vil_2) {
        this.vil_2 = vil_2;
    }

    /**
     * Retourne la durée du parcours/le poids courant.
     * @return La durée en int
     */
    public int getDuratin() {
        return duratin;
    }

    /**
     * Modifie la durée du parcours/le poids courant.
     * @param duratin La nouvelle durée.
     */
    public void setDuratin(int duratin) {
        this.duratin = duratin;
    }

    @Override
    public String toString() {
        return "[" + vil_1 + " - " + vil_2 + ", " + duratin + "]";
    }
}
