package model;

import java.util.Comparator;

/**
 * Created by stevenliatti on 18.03.17.
 */
public class Neighbour implements Comparator<Neighbour> {
    private String name;
    private int duration;
    private String predecessor;

    public Neighbour() {}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Neighbour neighbour = (Neighbour) o;

        return name != null ? name.equals(neighbour.name) : neighbour.name == null;
    }

    @Override
    public int compare(Neighbour n1, Neighbour n2) {
        if (n1.duration < n2.duration)
            return -1;
        else if (n1.duration > n2.duration)
            return 1;
        else
            return 0;
    }
}
