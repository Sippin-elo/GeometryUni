package geometry;


/**
 * Represents a closed interval with a {@link #begin} and an {@link #end}.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
class Interval {

   private double begin;
   private double end;

   /**
    * Copy Constructor
    *
    * @param i
    */
   Interval(Interval i) {
      this(i.begin, i.end);
   }

   /**
    * With instantiation, begin and end are swapped if not begin < end.
    *
    * @param begin beginning of this interval
    * @param end   end of this interval
    */
   Interval(double begin, double end) {
      this.begin = begin < end ? begin : end;
      this.end = begin < end ? end : begin;
   }

   double getBegin() {
      return begin;
   }

   double getEnd() {
      return end;
   }

   /**
    * Unions this interval with another. Returns a new Interval which
    * contains this and the other interval.
    *
    * @param i another Interval which should be unioned with this
    * @return the uion of both intervals
    */
   Interval union(Interval i) {
      return new Interval(Math.min(this.begin, i.begin), Math.max(this.end, i
            .end));
   }

   /**
    * Return the absolute length of this Interval.
    *
    * @return the length of this Interval.
    */
   double length() {
      return Math.abs(end - begin);
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      long temp;
      temp = Double.doubleToLongBits(begin);
      result = prime * result + (int) (temp ^ (temp >>> 32));
      temp = Double.doubleToLongBits(end);
      result = prime * result + (int) (temp ^ (temp >>> 32));
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
      Interval other = (Interval) obj;
      if (Double.doubleToLongBits(begin) != Double
            .doubleToLongBits(other.begin))
         return false;
      if (Double.doubleToLongBits(end) != Double.doubleToLongBits(other.end))
         return false;
      return true;
   }


}
