import javax.swing.*;
import java.util.*;

// ===== Superclass Employee =====
class Employee {
    protected String firstname;
    protected String lastname;
    protected String id;

    public Employee(String firstname, String lastname, String id){
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
    }

    // polymorphism methods
    public double earning() {
        return 0.0;
    }

    public double bonus(int year) {
        return 0.0;
    }
}

// ===== Subclass SalariedEmployee =====
class SalariedEmployee extends Employee {
    private double salary;

    public SalariedEmployee(String firstname, String lastname, String id, double salary){
        super(firstname, lastname, id);
        this.salary = salary;
    }

    @Override
    public double earning(){
        return salary;
    }

    @Override
    public double bonus(int year){
        if(year > 5) return salary * 2;   // สมมติว่า bonus = 2 เท่าของเงินเดือน
        else return salary;              // ถ้าไม่เกิน 5 ปี bonus = 1 เท่าของเงินเดือน
    }
}

// ===== Subclass ComEmployee =====
class ComEmployee extends Employee {
    private double grossSale;
    private double comRate;

    public ComEmployee(String firstname, String lastname, String id, double sales, double percent){
        super(firstname, lastname, id);
        this.grossSale = sales;
        this.comRate = percent;
    }

    @Override
    public double bonus(int year){
        if(year > 5) return grossSale * 6;
        else return grossSale * 3;
    }

    @Override
    public double earning(){
        return grossSale + (grossSale * comRate);
    }
}

// ===== Test Class =====
public class Final {
    public static void main(String[] args) {
        ArrayList<Employee> list = new ArrayList<>();

        // เพิ่มพนักงาน SalariedEmployee 2 คน
        list.add(new SalariedEmployee("John", "Smith", "E001", 20000));
        list.add(new SalariedEmployee("Anna", "Lee", "E002", 25000));

        // เพิ่มพนักงาน ComEmployee 2 คน
        list.add(new ComEmployee("Mike", "Brown", "E003", 30000, 0.1));
        list.add(new ComEmployee("Sara", "Miller", "E004", 40000, 0.15));

        printEmp(list);
    }

    public static void printEmp(ArrayList<Employee> a){
        double[] arrayEarn = new double[a.size()];
        double[] arrayBonus = new double[a.size()];

        StringBuilder sb = new StringBuilder();
        sb.append("First name   Last name   Earning   Bonus\n");

        int idx = 0;
        for(Employee r : a){
            double earn = r.earning();
            double bon = r.bonus(6); // สมมติว่าทุกคนทำงาน 6 ปี
            arrayEarn[idx] = earn;
            arrayBonus[idx] = bon;

            sb.append(String.format("%-12s %-10s %-10.2f %-10.2f\n",
                    r.firstname, r.lastname, earn, bon));
            idx++;
        }

        // แสดงผลออกทาง Dialog
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
