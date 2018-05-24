package Hanna.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {

    Point a = new Point(2, 4);
    Point b = new Point(7, 11);

    System.out.println("Расстояние между двумя точками на плоскости с координатами точки a=" + a.p1 + ";" + a.p2 + " и точки b=" + b.p1 + ";" + b.p2 + " равно " + a.distance(b));


  }
}