package model;

/**
 * Created by stevenliatti on 18.03.17.
 */
public class Neighbour {
    private String name;
    private int duration;

    public Neighbour(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[" + name + ":" + duration + "]";
    }
}
