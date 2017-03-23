package model;

import javax.xml.bind.annotation.XmlType;

/**
 * Created by raed on 13.03.17.
 */
@XmlType(propOrder = {"vil_1", "vil_2", "duratin"})
public class Connection {
    private String vil_1;
    private String vil_2;
    private int duratin;

    public Connection() {}

    public Connection(String vil_1, String vil_2, int duratin) {
        this.vil_1 = vil_1;
        this.vil_2 = vil_2;
        this.duratin = duratin;
    }

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

    public int getDuratin() {
        return duratin;
    }

    public void setDuratin(int duratin) {
        this.duratin = duratin;
    }

    @Override
    public String toString() {
        return "[" + vil_1 + " - " + vil_2 + ", " + duratin + "]";
    }
}
