package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by raed on 13.03.17.
 */

@XmlRootElement(name = "reseau")
@XmlType
public class Reseau {
    private String titre;

    List<Ville> villeList;

    List<Liaison> liaisonList;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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
