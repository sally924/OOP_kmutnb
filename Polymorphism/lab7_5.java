public class Empolyee {
   protected int id;
   protected String name;
   protected double salary;
   protected Address address = new Empolyee$1(this);

   Empolyee() {
   }

   Empolyee(int var1, String var2, double var3) {
      this.id = var1;
      this.name = var2;
      this.salary = var3;
   }

   public String getDetails() {
      return "id : " + this.id + " name : " + this.name + " salary : " + this.salary;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int var1) {
      this.id = var1;
   }

   public void setName(String var1) {
      this.name = var1;
   }

   public double getSalary() {
      return this.salary;
   }

   public void setSalary(double var1) {
      this.salary = var1;
   }

}


//------------Address class--------------------
public abstract class Address {
    protected String street;
    protected String city;

    Address(){
        
    }

    public String getStreet(){
        return this.street;
    }
    public void setStreet(String street){
        this.street = street;
    }
    public String getCity(){
        return this.city;
    }
    public void setCity(String city){
        this.city = city;

    }
    public String getAddressInfo(){
        return "Street : " + this.street + " City : " + this.city;
    }
}


//------------Manger class--------------------
public class Manager extends Empolyee {

     protected  String parkingNo;

     Manager(int id, String name, double salary){
        super(id,name,salary);
     }

     public String getParkingNo(){
        return parkingNo;
     }
     public void setParkingNo(String parkingNo){
        this.parkingNo = parkingNo;
     }
     @Override
     public String getDetails(){
       return "id : " + this.id + " name : " + this.name + " salary : " + this.salary + " parking No. : " + this.parkingNo;
     }

    

}
