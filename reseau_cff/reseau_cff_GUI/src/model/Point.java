package model;

/**
 * Created by raed on 23.03.17.
 */
public class Point {
    public static double scale = 3;
    public static int tx = -1450;
    public static int ty = 900;

    private int x;
    private int y;

    public Point(int x, int y, boolean withScale) {
        if (withScale) {
            this.x = (int) (x * scale) + tx;
            this.y = (int) (-y * scale) + ty;
        } else {
            this.x = x;
            this.y = y;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
