public class Rectangle extends GeometricObject implements Comparable<Rectangle>{
    private double length;
    private double width;
    private double area;

    public Rectangle(double length, double width){
        this.length = length;
        this.width = width;
        area = length * width;
    }
    public double  getArea(){
        return area;
    }
    public double getPerimeter(){
        return 2*length + 2*width;
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        else  if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Rectangle other = (Rectangle) obj;
        return area == other.area;
    }
    @Override
    public int compareTo(Rectangle r){
        if(area > r.area){
            return 1;
        }
        else if(area < r.area){
            return -1;
        }
        else{
            return 0;
        }
    }
    public static void main(String args[]){
        Rectangle r1 = new Rectangle(5, 5);
        Rectangle r2 = new Rectangle(5, 5);

        System.out.println(r1.area);
        System.out.println(r2.area);

        System.out.println(r1.compareTo(r2));
        System.out.println(r1.equals(r2));

        System.out.println(r2.getClass());
    }
    
}
