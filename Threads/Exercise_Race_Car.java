import javax.swing.*;
import java.awt.*;

//ข้อ 6
public class Exercise extends JFrame {
    public Exercise() {
        setTitle("Race Car Control");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        RaceCar track = new RaceCar();
        add(track, BorderLayout.CENTER);

        JPanel control = new JPanel();

        JTextField[] fields = new JTextField[4];
        for (int i = 0; i < 4; i++) {
            control.add(new JLabel("Car " + (i + 1) + " Speed:"));
            fields[i] = new JTextField("5", 5);
            control.add(fields[i]);

            int index = i;
            fields[i].addActionListener(e -> {
                try {
                    int speed = Integer.parseInt(fields[index].getText());
                    track.setCarSpeed(index, speed);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Enter number only!");
                }
            });
        }

        add(control, BorderLayout.NORTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Exercise();
    }
}


//Race Car
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RaceCar extends JPanel {
    private ArrayList<Car> cars = new ArrayList<>();
    private Thread thread;

    public RaceCar() {
        // Create 4 cars with different lanes and colors
        cars.add(new Car(0, 60, 5, Color.GREEN));
        cars.add(new Car(0, 110, 7, Color.GREEN));
        cars.add(new Car(0, 160, 3, Color.GREEN));
        cars.add(new Car(0, 210, 6, Color.GREEN));
        // Thread for animation
        thread = new Thread(() -> {
            while (true) {
                for (Car c : cars)
                    c.move(getWidth());
                repaint();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        thread.start();
    }

    public void setCarSpeed(int carIndex, int speed) {
        if (carIndex >= 0 && carIndex < cars.size()) {
            cars.get(carIndex).setSpeed(speed);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw lanes
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 50; i < getHeight(); i += 50) {
            g.drawLine(0, i, getWidth(), i);
        }

        // Draw cars
        for (Car c : cars)
            c.draw(g);
    }
}




//Car
public class Car {
    private int x;
    private int y;
    private int speed;
    private Color color;

    public Car(int x, int y, int speed, Color color){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.color = color;
    }
    public void move(int width) {
        x += speed;
        if (x > width) x = -50; // loop around
    }
    public void draw(Graphics g) {
        // Wheels
        g.setColor(Color.BLACK);
        g.fillOval(x + 10, y, 10, 10);
        g.fillOval(x + 30, y, 10, 10);

        // Body
        g.setColor(color);
        g.fillRect(x, y - 10, 50, 10);

        // Top
        g.setColor(Color.RED);
        Polygon p = new Polygon();
        p.addPoint(x + 10, y - 10);
        p.addPoint(x + 20, y - 20);
        p.addPoint(x + 30, y - 20);
        p.addPoint(x + 40, y - 10);
        g.fillPolygon(p);
    }
    public void setSpeed(int s) { speed = s; }
    public int getSpeed() { return speed; }

}


