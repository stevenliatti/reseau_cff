package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by raed on 13.03.17.
 */
@XmlType
public class Liaison {
    private String vil_1;
    private String vil_2;
    private int temps;

    public String getVil_1() {
        return vil_1;
    }

    public void setVil_1(String vil_1) {
        this.vil_1 = vil_1;
    }

    public String getVil_2() {
        return vil_2;
    }

    public void setVil_2(String vil_2) {
        this.vil_2 = vil_2;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }
}
