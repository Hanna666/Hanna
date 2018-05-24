package Hanna.sandbox;

public class Point {

    public double p1;
    public double p2;


    public Point (double p1, double p2) {
        this.p1 = p1;
        this.p2 = p2;

    }

    public double distance(Point second) {
        // Math.sqrt((a.p2 - a.p1) * (a.p2 - a.p1) + (b.p2 - b.p1) * (b.p2 - b.p1));
        return Math.sqrt((this.p2 - this.p1)*(this.p2 - this.p1)+(second.p2 - second.p1)*(second.p2 - second.p1));
    }

    }

