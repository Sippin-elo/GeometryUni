package geometry;

import java.util.Arrays;

/**
 * A Volume represents a n-dimensional rectangular and paraxial dataspace.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
public class Volume extends ComparableGeometry {

   /**
    * Holds the borders of this Volume as Intervals.
    */
   private Interval[] intervals;

   /**
    * Create a new Volume which is defined by the minimum and maximum values in
    * every dimension by <code>Point</code> <code>min</code> and
    * <code>max</code>. Both Points must have the same number of {@link
    * #dimensions()}.
    *
    * @param min first Point
    * @param max second Point
    * @throws RuntimeException if the dimension of <code>min</code> and
    *                          <code>max</code> are not equal
    */
   public Volume(Point min, Point max) {
      super(min.dimensions());
      /*
       * Check for dimension
       */
      if (min.dimensions() != max.dimensions()) {
         throw new RuntimeException("given Point instances have different " +
               "dimensions");
      }
      /*
       * transform the points into intervals
       */
      intervals = new Interval[this.dimensions()];
      for (int dim = 0; dim < intervals.length; dim++) {
         intervals[dim] = new Interval(min.getValueAt(dim), max.getValueAt(dim));
      }
   }

   /**
    * Create new Volume by using an array of Intervals, where every Interval
    * represents the borders in one dimension.
    *
    * @param intervals
    */
   private Volume(Interval[] intervals) {
      super(intervals.length);
      this.intervals = new Interval[intervals.length];
      System.arraycopy(intervals, 0, this.intervals, 0, intervals.length);
   }

   /**
    * Returns the encapsulate of this Volume and another geometry.
    *
    * @return a new Volume which contains this and other or <code>null</code>
    * @throws RuntimeException if the concrete type of the given geometry is
    *                          unknown.
    */
   public Geometry encapsulate(Geometry other) {
      if (this.dimensions() != other.dimensions()) {
         return null;
      }

      if (other instanceof Point) {
         Interval[] intervals = new Interval[this.dimensions()];
         for (int dim = 0; dim < this.dimensions(); dim++) {
            double point = ((Point) other).getValueAt(dim);
            intervals[dim] = new Interval(
                  Math.min(this.intervals[dim].getBegin(), point),
                  Math.max(this.intervals[dim].getEnd(), point)
            );
         }
         return new Volume(intervals);
      }
      if (other instanceof Volume) {
         Interval[] intervals = new Interval[this.dimensions()];
         for (int dim = 0; dim < this.dimensions(); dim++) {
            intervals[dim] = this.intervals[dim].union(((Volume) other)
                  .intervals[dim]);
         }
         return new Volume(intervals);
      }
      throw new RuntimeException("unknown Geometry type");
   }

   /**
    * Compute the volume of this Volume, maybe zero if this Volume has no extent
    * in one dimension.
    *
    * @return the volume of this Volume
    */
   public double volume() {
      int vol = 1;
      /*
       * multiply the length of this Volume in every dimension
       */
      for (Interval i : intervals) {
         vol *= i.length();
      }
      return vol;
   }

   public String toString() {
      StringBuffer buf = new StringBuffer();
      buf.append("(");
      for (int dim = 0; dim < this.dimensions(); dim++) {
         buf.append(intervals[dim].getBegin()
               + ((dim < this.dimensions() - 1) ? "," : ""));
      }
      buf.append("),(");

      for (int dim = 0; dim < this.dimensions(); dim++) {
         buf.append(intervals[dim].getEnd()
               + ((dim < this.dimensions() - 1) ? "," : ""));
      }
      buf.append(")");
      return buf.toString();

   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + Arrays.hashCode(intervals);
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (!(obj instanceof Volume))
         return false;
      Volume other = (Volume) obj;
      if (!Arrays.equals(intervals, other.intervals))
         return false;
      return true;
   }

}
