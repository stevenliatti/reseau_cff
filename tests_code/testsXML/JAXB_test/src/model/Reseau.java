package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by raed on 13.03.17.
 */

@XmlRootElement(name = "reseau")
@XmlType(propOrder = {"title", "villeList", "liaisonList"})
public class Reseau {
    private String title;

    List<Ville> villeList;

    List<Liaison> liaisonList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "ville")
    public List<Ville> getVilleList() {
        return villeList;
    }

    public void setVilleList(List<Ville> villeList) {
        this.villeList = villeList;
    }

    @XmlElement(name = "liaison")
    public List<Liaison> getLiaisonList() {
        return liaisonList;
    }

    public void setLiaisonList(List<Liaison> liaisonList) {
        this.liaisonList = liaisonList;
    }
}
