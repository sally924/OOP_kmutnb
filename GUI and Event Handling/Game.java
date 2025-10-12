import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Game extends JFrame{
    private JTextField scoreF, positionF, shootingF, angleF;
    private JButton ok_btn;
    private DrawPanel drawPanel;
    private final double G = 9.8;
    private Bird redBird = new Bird();
    private Pig greenPig = new Pig();
    private boolean isHit = false;
    public double Sy,U,angle;


    public Game() {
        setTitle("Angry Birds");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setLayout(new BorderLayout());

        // Top Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel scene = new JLabel("SCENE 1: At TOKYO");
        scene.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        topPanel.add(scene, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        JLabel scoreLabel = new JLabel("Score: ");
        scoreF = new JTextField(5);
        scoreF.setEditable(false);
        scoreF.setText("0");
        rightPanel.add(scoreLabel);
        rightPanel.add(scoreF);
        topPanel.add(rightPanel, BorderLayout.EAST);

        //center
        drawPanel = new DrawPanel();
        drawPanel.setPreferredSize(new Dimension(700,400));

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 0 - Bird Position
        gbc.gridx = 0;
        gbc.gridy = 0;
        bottomPanel.add(new JLabel("Bird Position y-axis (m)"), gbc);
        gbc.gridx = 1;
        positionF = new JTextField(15);
        positionF.setText("0");
        bottomPanel.add(positionF, gbc);
        gbc.gridx = 2;
        bottomPanel.add(new JLabel("m"), gbc);

        // Row 1 - Shooting speed
        gbc.gridx = 0;
        gbc.gridy = 1;
        bottomPanel.add(new JLabel("Shooting speed(m/s)"), gbc);
        gbc.gridx = 1;
        shootingF = new JTextField(15);
        shootingF.setText("20");
        bottomPanel.add(shootingF, gbc);
        gbc.gridx = 2;
        bottomPanel.add(new JLabel("m/s"), gbc);

        // Row 2 - Angle
        gbc.gridx = 0;
        gbc.gridy = 2;
        bottomPanel.add(new JLabel("Angle (degree)"), gbc);
        gbc.gridx = 1;
        angleF = new JTextField(15);
        angleF.setText("45");
        bottomPanel.add(angleF, gbc);
        gbc.gridx = 2;
        bottomPanel.add(new JLabel("degree"), gbc);

        // Button
        gbc.gridx = 1;
        gbc.gridy = 3;
        ok_btn = new JButton("OK");
        bottomPanel.add(ok_btn, gbc);


        setLayout(new BorderLayout(10,10));
        add(topPanel, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        //redBird = new Bird()

        System.out.println("positon in main = " +  Double.parseDouble(positionF.getText()));
        isHit(Sy,U,angle);

        //double
        setVisible(true);

        //Action Listener
        ok_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double y0 = Double.parseDouble(positionF.getText());
                    double U = Double.parseDouble(shootingF.getText());
                    double angle = Double.parseDouble(angleF.getText());
                    double rad = Math.toRadians(angle);
                    double Ux = U * Math.cos(rad);
                    double Uy = U * Math.sin(rad);

                    drawPanel.initialY = y0;

                    Timer timer = new Timer(50, null);
                    timer.addActionListener(new ActionListener() {
                        double t = 0;

                        public void actionPerformed(ActionEvent e) {
                            t += 0.1;
                            double x = Ux * t;
                            double y = y0 + Uy * t - 0.5 * G * t * t;

                            // Convert meter to pixel
                            int groundYPixel = drawPanel.getHeight() - drawPanel.IMAGE_SIZE;
                            int birdPixelY = groundYPixel - (int) (y * drawPanel.SCALE_FACTOR);
                            int pigPixelY = groundYPixel - (int) (greenPig.getY() * drawPanel.SCALE_FACTOR);
                            int birdPixelX = (int) (x * drawPanel.SCALE_FACTOR) + drawPanel.START_X_PIXEL;
                            int pigPixelX = (int) (greenPig.getX() * drawPanel.SCALE_FACTOR) + drawPanel.START_X_PIXEL;

                            // Check ground hit
                            if (y < 0) {
                                y = 0;
                                if(Math.abs(birdPixelX-pigPixelX) <= 20){
                                    int currentScore = Integer.parseInt(scoreF.getText());
                                    currentScore += 100;
                                    scoreF.setText(String.valueOf(currentScore));
                                    System.out.println("Hit");

                                }
                                System.out.println("last position = " + birdPixelX + ", " + "pig = " + pigPixelX);
                                timer.stop();
                            }

                            // Check collision with pig
                            /*if ((Math.abs(birdPixelX - pigPixelX) <= 30) && (Math.abs(birdPixelY - pigPixelY) <= 30)){
                                int currentScore = Integer.parseInt(scoreF.getText());
                                currentScore += 100;
                                scoreF.setText(String.valueOf(currentScore));
                                timer.stop();
                            }
                            System.out.println(("Bird = " + birdPixelX + "," + birdPixelY));
                            System.out.println(("Pig = " +  pigPixelX + "," + pigPixelY));*/

                            drawPanel.setHitPoint(x, y);
                        }
                    });
                    timer.start();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers!");
                }
                //System.out.println("last drop position is: " + (Ux * t));
                double Sy =  Double.parseDouble(positionF.getText());
                double U  =  Double.parseDouble(shootingF.getText());
                double angle = Double.parseDouble(angleF.getText());
                isHit(Sy,U,angle);

            }
        });

    }
    public void isHit(double Sy, double U, double angle){
        double rad = Math.toRadians(angle);
        double Ux = U * Math.cos(rad);
        double Uy = U * Math.sin(rad);
        System.out.println("U :" + U);
        System.out.println("Sin :" + Math.sin(rad) + " Cos:" + Math.cos(rad));
       // System.out.println("Sin r :" + Math.cos(rad) + " Cos r:" + Math.sin(rad));


        System.out.println("Ux:" + Ux + " Uy:" + Uy);

        double a  = 0.5 * (-10); double b = Uy; double c = Sy*(-1);
        double t1 = 0 ; double t2 = 0;
        System.out.println("c:" + c);

        if(a != 0){

            t1 =( -b + Math.sqrt((Math.pow(b,2)) - 4*a*c)  )/(2*a);
            t2 =( -b - Math.sqrt((Math.pow(b,2))- 4*a*c)   )/(2*a);
            System.out.println("t1:" + t1 + " t2:" + t2);
            double T = (t1 >= 0) ? t1 : t2;

            double Sx = Ux * T;
            System.out.println("Sx:" + Sx);
            if(Math.abs(Sx*10 - greenPig.getX()*10) <= 20){
                int currentScore = Integer.parseInt(scoreF.getText());
                currentScore += 100;
                scoreF.setText(String.valueOf(currentScore));
                System.out.println("Hit");
            }
            System.out.println("x of pig :" + greenPig.getX());
            System.out.println(Math.abs(Sx*10 - greenPig.getX()*10));

            /*double Sx = Ux*T;
            System.out.println("T = " + T + " Sx = " + Sx);
            System.out.println(Math.sqrt(4440.516226));
            System.out.println(4*a*c);
            System.out.println((Math.pow(b,2)));*/

        }
        else{
            System.out.println("Error");
        }



    }
    class DrawPanel extends JPanel{
        private Image background;
        private Image birdImg;
        private Image pigImg;
        private double initialY = 0.0;
        public double SCALE_FACTOR = 10.0;
        public int START_X_PIXEL = 50;
        public int IMAGE_SIZE = 60;
        private Double hitX = null, hitY = null;


        public DrawPanel(){
            background = new ImageIcon(getClass().getResource("/picture/Tokyo.jpg")).getImage();
            birdImg = new ImageIcon(getClass().getResource("/picture/angry-birds-red1.png")).getImage();
            pigImg = new ImageIcon(getClass().getResource("/picture/angry-birds-pig1.png")).getImage();
        }
        public void setHitPoint(double x, double y) {
            this.hitX = x;
            this.hitY = y;
            repaint();
        }

        @Override
        public  void paintComponent(Graphics g){
            super.paintComponent(g);
            int groundYPixel = getHeight() - IMAGE_SIZE;

            g.drawImage(background,0,0,getWidth(),getHeight(),null);

            g.drawImage(birdImg, START_X_PIXEL, groundYPixel - (int) (initialY * SCALE_FACTOR), IMAGE_SIZE, IMAGE_SIZE, null);

            int pigPixelX = START_X_PIXEL + (int) (greenPig.getX() * SCALE_FACTOR);
            g.drawImage(pigImg, pigPixelX, groundYPixel - (int) (greenPig.getY() * SCALE_FACTOR), IMAGE_SIZE, IMAGE_SIZE, null);

            // Moving bird  ๅ
            if (hitX != null) {
                int px = START_X_PIXEL + (int) (hitX * SCALE_FACTOR);
                int py = groundYPixel - (int) (hitY * SCALE_FACTOR);
                g.drawImage(birdImg, px, py, IMAGE_SIZE, IMAGE_SIZE, null);
            }
        }
    }

    public static  void main(String args[]){
        new Game();
    }
}
// Bird class
class Bird {
    private int xPos = 0;
    private double yPos;

    public Bird() {
    }

    public Bird(double yPos) {
        this.yPos = yPos;
    }

    public void setY(double yPos) {
        this.yPos = yPos;
    }

    public int getX() {
        return xPos;
    }

    public double getY() {
        return yPos;
    }
}

// Pig class
class Pig {
    private double xPos = 60;
    private double yPos = 0;

    public Pig() {
    }

    public Pig(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void setX(double xPos) {
        this.xPos = xPos;
    }

    public void setY(double yPos) {
        this.yPos = yPos;
    }

    public double getX() {
        return xPos;
    }

    public double getY() {
        return yPos;
    }
}

