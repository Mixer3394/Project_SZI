package sample;

/**
 * Created by Mariusz on 18.04.2016.
 */
public class GraphPoints {
    private int X;
    private int Y;

    public GraphPoints() {
        this(0,0);
    }
    public GraphPoints(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
    public int getX() {
        return X;
    }
    public int getY() {
        return Y;
    }
    public void setX(int X) {
        this.X = X;
    }
    public void setY(int Y) {
        this.Y = Y;
    }
}
