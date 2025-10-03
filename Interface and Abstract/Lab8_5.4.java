public class ComparableCircle extends Circle implements Comparable<Circle> {

    public ComparableCircle(double radius){
        super(radius);
    }
    @Override
    public int compareTo(Circle o){
        return Double.compare(this.getArea(), o.getArea());
        // < 0 if this.getArea() < o.getArea()
        //   0 if this.getArea() == o.getArea()
        // > 0 if this.getArea() > o.getArea()
    }
}
