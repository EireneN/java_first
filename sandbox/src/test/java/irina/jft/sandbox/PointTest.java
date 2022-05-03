package irina.jft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PointTest {

    @Test
    public void testBc(){
        Point p1 = new Point(3, 2);
        Point p2 = new Point(5,6);
        Assert.assertEquals(p1.bc(p1,p2), 4.0);
    }
    @Test
    public void testAc(){
        Point p1 = new Point(3, 2);
        Point p2 = new Point(5,6);
        Assert.assertEquals(p1.ac(p1,p2), 16.0);
    }
    @Test
    public void testSum(){
        Point p1 = new Point(3, 2);
        double ac = 10.0;
        double bc = 12.0;
        double actual = p1.sum(ac,bc);
        Assert.assertEquals(actual, 22.0);
    }
    @Test
    public void testSquare(){
       Point p1 = new Point(3, 2);
       double sum = 20.0;
       double actual = p1.square(sum);
       Assert.assertEquals(actual, 4.47213595499958);
    }

}