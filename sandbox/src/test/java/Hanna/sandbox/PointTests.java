package Hanna.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance() {
        Point a = new Point(2, 4);
        Point b = new Point(7, 11);
        Assert.assertEquals(a.distance(b),8.602325267042627);

        Point t = new Point(5, 8);
        Point c = new Point(6, 14);
        Assert.assertEquals(t.distance(c),6.082762530298219);
    }

}
