package model;

import javax.xml.bind.annotation.XmlType;

/**
 * Classe représentant une ville du graphe. Utilisée dans le parser XML.
 * @author Raed Abdennadher
 * @author Steven Liatti
 */
@XmlType(propOrder = {"name", "longitude", "latitude"})
public class City {
    private String name;
    private int longitude;
    private int latitude;

    /**
     * Constructeur par défaut.
     */
    public City() {}

    /**
     * Construit une ville à partir d'un nom (longitude et latitude à 0).
     * @param name Le nom de la ville.
     */
    public City(String name) {
        this.name = name;
        longitude = 0;
        latitude = 0;
    }

    /**
     * Construit une ville à partir d'un nom (longitude et latitude à 0).
     * @param name Le nom de la ville.
     * @param longitude Le longitude de la ville.
     * @param latitude Le latitude de la ville.
     */
    public City(String name, int longitude, int latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Retourne le nom de la ville.
     * @return le nom de la ville
     */
    public String getName() {
        return name;
    }

    /**
     * Modifie le nom de la ville.
     * @param name Le nom de la ville.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne la longitude.
     * @return la longitude
     */
    public int getLongitude() {
        return longitude;
    }

    /**
     * Modifie la longitude.
     * @param longitude La longitude en int.
     */
    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    /**
     * Retourne la latitude.
     * @return la latitude
     */
    public int getLatitude() {
        return latitude;
    }

    /**
     * Modifie la latitude.
     * @param latitude La latitude en int.
     */
    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "[" + name + ", " + longitude + ", " + latitude + "]";
    }
}
