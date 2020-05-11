package geometry;

import test.Assert;

/**
 * Make some tests on classes of the type Geometry.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
public class GeometryTest {

   public static void main(String[] args) {
      
      /*
       * Only needs to test Point2D and Rectangle, because they are subtypes of
       * Point and Volume.
       */
      Point2D inner = new Point2D(1, 1);
      Point2D outer = new Point2D(-2, 1);

      Rectangle r1 = new Rectangle(new Point2D(-1, 4), new Point2D(4, -1));
      Rectangle r2 = new Rectangle(new Point2D(2, 7), new Point2D(6, 1));

      Rectangle r3 = new Rectangle(new Point2D(6, 1), new Point2D(2, 7));

      System.out.println("Test Geometry package...");

      System.out.println("Test volume() in Point / Point2D");
      Assert.assertEquals(0, inner.volume(), 0.000001, "volume()");
      Assert.assertEquals(0, outer.volume(), 0.000001, "volume()");

      System.out.println("Test encapsulate() in Point / Point2D");
      Assert.assertEquals(new Rectangle(inner, outer), inner.encapsulate(outer),"encapsulate()");
      Assert.assertEquals(r1, inner.encapsulate(r1),"encapsulate()");
      Assert.assertEquals(null, inner.encapsulate(new Point(1.0, 1.0, 1.0)),"encapsulate()");

      System.out.println("Test compareTo() in Point / Point2D");
      Assert.assertTrue(inner.compareTo(outer) == 0,"compareTo");
      Assert.assertTrue(inner.compareTo(r1) < 0,"compareTo");
      Assert.assertTrue(r2.compareTo(inner) > 0,"compareTo");

      System.out.println("Test init of Volume / Rectangle");
      Assert.assertEquals(r2, r3,"init of Volume");

      System.out.println("Test volume() in Volume / Rectangle");
      Assert.assertEquals(25.0, r1.volume(), 0.000001,"volume()");
      Assert.assertEquals(24.0, r2.volume(), 0.000001,"volume");

      System.out.println("Test encapsulate() in Volume / Rectangle");
      Assert.assertEquals(r1, r1.encapsulate(inner),"encapsulate");
      Assert.assertEquals(null, r2.encapsulate(new Point(1.0, 1.0, 1.0)),"encapsulate");
      Assert.assertEquals(new Rectangle(new Point2D(-1, -1), new Point2D(6, 7)),
            r1.encapsulate(r2),"encapsulate");

      System.out.println("Test compareTo() in Volume / Rectangle");
      Assert.assertTrue(r1.compareTo(r2) > 0,"compareTo()");
      Assert.assertTrue(r2.compareTo(r1) < 0,"compareTo()");
      Assert.assertTrue(r1.compareTo(r1) == 0,"compareTo()");

      System.out.println("...finished.");

   }
}
