package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Map;

/**
 * Created by raed on 13.03.17.
 */

@XmlRootElement(name = "reseau")
@XmlType(propOrder = {"title", "cityList", "connectionList"})
public class Net {
    private String title;
    private List<City> cityList;
    private List<Connection> connectionList;
    // Cet attribut va servir pour créer la liste des poids/d'adjacence.
    // C'est une map qui associe une ville à une liste de paire ville/duration
    private Map<City, List<Map.Entry<City, Integer>>> neighbours;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "ville")
    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    @XmlElement(name = "liaison")
    public List<Connection> getConnectionList() {
        return connectionList;
    }

    public void setConnectionList(List<Connection> connectionList) {
        this.connectionList = connectionList;
    }
}
