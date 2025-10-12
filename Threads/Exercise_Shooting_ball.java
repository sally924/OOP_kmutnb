import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Exercise extends JFrame{
    JTextField scoreField;
    public Exercise(){
        setTitle("Exercise");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Score:"));
        scoreField = new JTextField("0",5);
        scoreField.setEditable(false);
        topPanel.add(scoreField);

        ShootingPanel panel = new ShootingPanel(this);
        add(panel,BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        setVisible(true);
        panel.requestFocusInWindow();

    }
    public void updateScore(int score){
        scoreField.setText(String.valueOf(score));
    }
    public int getScore(){
        return Integer.parseInt(scoreField.getText());
    }

    public static  void main(String[] args){
        new Exercise();
    }

}






// ShootingPanel
public class ShootingPanel extends JPanel {
    private Timer timer;
    private Thread thread;
    private Gun gun = new Gun();
    private ArrayList<Ball> bullets = new ArrayList<>();
    private ArrayList<Point> targets = new ArrayList<>();
    private Exercise parent;

    int min = 100, max = 400, targetSize = 40;

    public ShootingPanel(Exercise parent) {
        this.parent = parent;
        setFocusable(true);
        requestFocusInWindow();


        for (int i = 0; i < 1; i++) {
            int x = (int) (Math.random() * (max - min + 1)) + min;
            int y = (int) (Math.random() * (max - min + 1)) + min;
            targets.add(new Point(x, y));
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        gun.setAngle(gun.getAngle() - Math.toRadians(5));
                        break;
                    case KeyEvent.VK_RIGHT:
                        gun.setAngle(gun.getAngle() + Math.toRadians(5));
                        break;
                    case KeyEvent.VK_UP:
                        bullets.add(gun.shootBall());
                        break;
                }
                repaint();
            }
        });

        thread = new Thread(() ->{
            while(true){
                for (int i = 0; i < bullets.size(); i++) {
                    Ball b = bullets.get(i);
                    b.move();

                    for (int j = 0; j < targets.size(); j++) {
                        Point t = targets.get(j);
                        double distance = Math.sqrt(Math.pow(b.getX() - t.x, 2) + Math.pow(b.getY() - t.y, 2));
                        if (distance <= 30) {
                            System.out.println("Hit target #" + j);


                            bullets.remove(i);
                            i--;


                            targets.remove(j);

                            for (int k = 0; k < 10; k++) {
                                int x = (int) (Math.random() * (max - min + 1)) + min;
                                int y = (int) (Math.random() * (max - min + 1)) + min;
                                targets.add(new Point(x, y));

                            }
                            parent.updateScore(parent.getScore()  + 100);
                            break;
                        }
                    }
                }
                repaint();
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    break;
                }
            }
        });
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        gun.draw(g);
        for (Ball b : bullets) {
            b.draw(g);
        }

        g.setColor(Color.RED);
        for (Point p : targets) {
            g.fillOval(p.x, p.y, targetSize, targetSize);
        }
    }
}





//Ball
public class Ball {
    private int x,y;
    private Color color;
    private int speed;

    public Ball(int x, int  y, Color color, int speed){
        this.x = x;
        this.y =y;
        this.color = color;
        this.speed = speed;
    }
    public void move(){
        y -= speed;
    }
    public int getX(){return  x;}
    public int getY(){return y;}

    public void draw(Graphics g){
        g.setColor(Color.black);
        g.fillOval(x,y,10,10);

    }
}






//gun
public class Gun {
    private int startX = 250;
    private int startY = 480;
    private int length = 150;
    private double angle = Math.toRadians(90);

    public Gun() {}

    // setter / getter
    public int getStartX() { return startX; }
    public int getStartY() { return startY; }
    public void setAngle(double angle) { this.angle = angle; }
    public double getAngle(){ return  angle;}


    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(5));

        int endX = startX + (int)(length * Math.cos(angle));
        int endY = startY - (int)(length * Math.sin(angle)); // ลบเพราะแกน Y ลงล่าง

        g2.drawLine(startX, startY, endX, endY);
    }

    public Ball shootBall(){
        int endX = startX + (int)(length * Math.cos(angle));
        int endY = startY - (int)(length * Math.sin(angle));
        return new Ball(endX, endY, Color.BLACK, 5);
    }
}

