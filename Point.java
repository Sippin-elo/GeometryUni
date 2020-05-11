package geometry;

import java.util.Arrays;

/**
 * Represents a point with n dimensions.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
public class Point extends ComparableGeometry {

   /**
    * Holds the value of this Point in every dimension
    */
   private double[] values;

   /**
    * Create a new Point. Will copy all of the given values.
    *
    * @param values
    */
   public Point(double... values) {
      super(values.length);
      this.values = new double[values.length];
      System.arraycopy(values, 0, this.values, 0, values.length);
   }

   /**
    * The volume of a Point is always 0.
    *
    * @return 0
    */
   public double volume() {
      return 0;
   }

   /**
    * Returns the encapsulate of this Point and another geometry.
    *
    * @return a new Volume which contains this and other or <code>null</code>
    * @throws RuntimeException if the concrete type of the given geometry is
    *                          unknown.
    */
   public Geometry encapsulate(Geometry other) {
      if (other.dimensions() != this.dimensions()) {
         return null;
      }
      if (other instanceof Volume) {
         return ((Volume) other).encapsulate(this);
      }
      if (other instanceof Point) {
         return new Volume(this, (Point) other);
      }
      throw new RuntimeException("unknown Geometry type");
   }

   /**
    * Returns the value at a given dimension.
    *
    * @param dim position of the value
    * @return value at dimension <code>dim</code>
    */
   public double getValueAt(int dim) {
      return values[dim];
   }

   public String toString() {

      StringBuffer buf = new StringBuffer();
      buf.append("(");
      for (int dim = 0; dim < this.dimensions(); dim++) {
         buf.append(values[dim] + ((dim < this.dimensions() - 1) ? "," : ""));
      }
      buf.append(")");
      return buf.toString();
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + Arrays.hashCode(values);
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Point other = (Point) obj;
      if (!Arrays.equals(values, other.values))
         return false;
      return true;
   }

}
