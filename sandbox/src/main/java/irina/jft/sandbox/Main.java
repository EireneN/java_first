package irina.jft.sandbox;

public class Main {
    public static void main (String[] args) {
        Point p1 = new Point(3, 2);
        Point p2 = new Point(5,6);

        p1.printCoordinates (p1);
        p2.printCoordinates (p2);

        p1.distance (p1, p2);

    }

}

