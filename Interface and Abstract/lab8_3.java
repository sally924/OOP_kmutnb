import javax.swing.JFrame;

import java.awt.GridLayout;
import java.util.*;

public class Lab8_3 extends JFrame{
    public Lab8_3(){
        getContentPane().setLayout(new GridLayout(3,3,5,5));
        getContentPane().add(new Drawparabola());
        getContentPane().add(new DrawSin());
        getContentPane().add(new DrawCos());
        getContentPane().add(new DrawTan());
        getContentPane().add(new f1());
        getContentPane().add(new f2());
        getContentPane().add(new f3());
    }

    class Drawparabola extends AbstractDrawFunction{
        @Override
        public double f(double x){
            return x*x;
        }
    }
    class DrawSin extends AbstractDrawFunction{
        @Override
        public double f(double x){
            return 100 * Math.sin(Math.toRadians(x));
        }
    }
    class DrawCos extends AbstractDrawFunction{
        @Override
        public double f(double x){
            return 100 * Math.cos(Math.toRadians(x));
        }
    }
    class DrawTan extends AbstractDrawFunction{
        @Override
        public double f(double x){
            return 100 * Math.tan(Math.toRadians(x));
        }
    }
    class f1 extends AbstractDrawFunction{
        @Override
        public double f(double x){
           // return 100 * DrawCos(x) + 5*DrawSin(x);
            return  100 * Math.cos(Math.toRadians(x)) +  5*(100 * Math.sin(Math.toRadians(x)));
        }
    }
    class f2 extends AbstractDrawFunction{
        @Override
        public double f(double x){
               return  100 * Math.sin(Math.toRadians(x)) +  5*(100 * Math.cos(Math.toRadians(x)));
        }
    }
    class f3 extends AbstractDrawFunction{
        @Override
        public double f(double x){
            return 100 * Math.log(x) + Math.pow(x,2);
        }
    }
    public static void main(String args[]){
        System.out.println("Run");
       Lab8_3 frame = new Lab8_3();
       frame.setSize(1000,1000);
       frame.setTitle("Lab8_3");
       frame.setVisible(true);
    }



    
}
