package irina.jft.sandbox;

public class Point {
    double x;
    double y;

    public Point (double x, double y){
        this.x = x;
        this.y = y;
    }
    public void distance (Point p1, Point p2){
        double bc = bc(p1, p2);
        double ac = ac(p1, p2);
        double sum = sum (ac, bc);
        square(sum);
    }

    public double bc (Point p1, Point p2){
        double bc = (p2.x - p1.x) * (p2.x - p1.x);
        System.out.println("BC:" + " = " + bc);
        return bc;
    }
    public double ac (Point p1, Point p2){
        double ac = (p2.y - p1.y) * (p2.y - p1.y);
        System.out.println("AC:" + " = " + ac);
        return ac;
    }

    public double sum (double ac, double bc){
        double sum = ac + bc;
        System.out.println("Sum AC + BC = " + sum);
        return sum;
    }
    public void square (double sum){
        double square = Math.sqrt(sum);
        System.out.println("Distance = " + square);
    }


    public void printCoordinates (Point a) {
        System.out.println("Координаты точки:" + " x = " + a.x + ", " + "y = " + a.y + "." );
    }

}


