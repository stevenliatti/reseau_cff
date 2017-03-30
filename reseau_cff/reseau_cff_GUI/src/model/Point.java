package model;

/**
 * Created by raed on 23.03.17.
 */
public class Point {
    private static final double scale = 3;
    private static final int tx = -1450;
    private static final int ty = 900;

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = (int)(x * scale) + tx;
        this.y = (int)(-y * scale) + ty;
    }

    public Point(Point firstPoint) {
        this.x = firstPoint.x;
        this.y = firstPoint.y;
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
