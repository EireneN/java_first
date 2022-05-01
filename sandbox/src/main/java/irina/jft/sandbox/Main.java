package irina.jft.sandbox;

public class Main {
    public static void main (String[] args) {
        Point p1 = new Point(3, 2);
        Point p2 = new Point(5,6);

        printCoordinates (p1);
        printCoordinates (p2);

        distance(p1, p2);

    }
    public static void distance (Point p1, Point p2){
        double bc = bc(p1, p2);
        double ac = ac(p1, p2);
        double sum = sum (ac, bc);
        square(sum);
    }

    public static double bc (Point p1, Point p2){
        double bc = (p2.x - p1.x) * (p2.x - p1.x);
        System.out.println("BC:" + " = " + bc);
        return bc;
    }
    public static double ac (Point p1, Point p2){
        double ac = (p2.y - p1.y) * (p2.y - p1.y);
        System.out.println("AC:" + " = " + ac);
        return ac;
    }

    public static double sum (double ac, double bc){
        double sum = ac + bc;
        System.out.println("Sum AC + BC = " + sum);
        return sum;
    }
    public static double square (double sum){
        double distance = Math.sqrt(sum);
        System.out.println("Distance = " + distance);
        return distance;
    }


    public static void printCoordinates (Point a) {
    System.out.println("Координаты точки:" + " x = " + a.x + ", " + "y = " + a.y + "." );
    }

}

