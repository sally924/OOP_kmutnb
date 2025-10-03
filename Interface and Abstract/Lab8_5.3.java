public class Octagon extends GeometricObject implements Cloneable, Comparable<Octagon>{
    private double side;
    private double area;

    public Octagon(double side){
        this.side = side;
        area = (2 + 4/Math.sqrt(2)) *side *side;

    }
    public double getArea(){
        return area;
    }
    public double getPerimeter(){
        return 8*side;
    }
    @Override
    public int compareTo(Octagon o){
      if(area  == o.getArea()){
        return 1;
      }else{
        return 0;
      }

    }
    @Override
    public Object clone(){
        try{
            return super.clone();
        }
        catch(CloneNotSupportedException ex){
            return null;
        }
    }
    public static void main(String args[]){
        Octagon o1 = new Octagon(5);
        Octagon o2 = (Octagon)o1.clone();

        System.out.println(o1.getArea());
        System.out.println(o2.getArea());

        System.out.println(o1.compareTo(o2));

        Object o3 = new Octagon(5);
        System.out.println(((Octagon)o3).getArea());
    }


    
}
