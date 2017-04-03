package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Classe représentant un graphe/réseau de villes et leurs connexions.
 * @author Raed Abdennadher
 * @author Steven Liatti
 */
@XmlRootElement(name = "reseau")
@XmlType(propOrder = {"title", "cityList", "connectionList"})
public class Net {
    private String title;
    private List<City> cityList;
    private List<Connection> connectionList;

    /**
     * Retourne le titre du graphe.
     * @return Le titre du graphe
     */
    public String getTitle() {
        return title;
    }

    /**
     * Modifie le titre du graphe.
     * @param title Le nouveau titre
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retourne la liste des villes du graphe.
     * @return La {@link List} des villes du graphe
     */
    @XmlElement(name = "ville")
    public List<City> getCityList() {
        return cityList;
    }

    /**
     * Modifie la liste des villes.
     * @param cityList La nouvelle liste des villes
     */
    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    /**
     * Retourne la liste des connexions entre les villes.
     * @return La {@link List} des connexions entre les villes
     */
    @XmlElement(name = "liaison")
    public List<Connection> getConnectionList() {
        return connectionList;
    }

    /**
     * Modifie la liste des connexions.
     * @param connectionList La nouvelle liste des connexions
     */
    public void setConnectionList(List<Connection> connectionList) {
        this.connectionList = connectionList;
    }
}
