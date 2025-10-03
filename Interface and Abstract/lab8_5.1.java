public class Circle extends GeometricObject implements Comparable<Circle>{
    private double radius;
    public Circle(double radius){
        this.radius = radius;
    }
    public double getArea(){
        return Math.PI * (radius*radius);
    }
    public double  getPerimeter(){
        return 2 * Math.PI * radius;
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
           
        } 
        else if(obj  == null || getClass() != obj.getClass()){
                return false;
        }
        Circle other  = (Circle) obj;
        return radius == other.radius;

    }
    @Override
    public int compareTo(Circle c){
        if(radius == c.radius){
            return 1;
        }else{
            return 0;
        }
    }
    public static void main(String args[]){
        Circle c1 = new Circle(10);
        Circle c2 = new Circle(10);

        System.out.println(c1.equals(c2)); //true
        System.out.println(c1.compareTo(c2)); // 1

        System.out.println(c1.getArea());
        System.out.println(c2.getArea());

        System.out.println(c1.getPerimeter());
        System.out.println(c2.getPerimeter());
    }
    
}
