package model;

/**
 * Created by stevenliatti on 18.03.17.
 */
public class Neighbour {
    private String name;
    private int duration;
    private String predecessor;

    public Neighbour(String name, int duration, String predecessor) {
        this.name = name;
        this.duration = duration;
        this.predecessor = predecessor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(String predecessor) {
        this.predecessor = predecessor;
    }

    @Override
    public String toString() {
        return "[" + name + ":" + duration + "]";
    }
}
