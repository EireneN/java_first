package irina.jft.sandbox;

public class Point {
    double x;
    double y;

    public Point (double x, double y){
        this.x = x;
        this.y = y;
    }
    public double distance (Point p2){
        double bc = bc(p2);
        double ac = ac(p2);
        double sum = sum (ac, bc);
        return square(sum);
    }

    public double bc (Point p2){
        double bc = (p2.x - x) * (p2.x - x);
        System.out.println("BC:" + " = " + bc);
        return bc;
    }
    public double ac (Point p2){
        double ac = (p2.y - y) * (p2.y - y);
        System.out.println("AC:" + " = " + ac);
        return ac;
    }

    public double sum (double ac, double bc){
        double sum = ac + bc;
        System.out.println("Sum AC + BC = " + sum);
        return sum;
    }
    public double square (double sum){
        double square = Math.sqrt(sum);
        System.out.println("Distance = " + square);
        return square;
    }


    public void printCoordinates () {
        System.out.println("Координаты точки:" + " x = " + x + ", " + "y = " + y + "." );
    }

    public void printCoordinates (double x, double y) {
        System.out.println("Координаты точки:" + " x = " + x + ", " + "y = " + y + "." );
    }

    }



