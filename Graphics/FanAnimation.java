import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class FanAnimation extends JPanel {
    private int angle = 0; // rotation angle
    private int speed = 5;
    private JButton stopBtn,lowBtn, medBtn, highBtn;
    public FanAnimation() {
        JFrame frame = new JFrame("Fan Animation");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        JPanel panel = new JPanel(new FlowLayout());
        stopBtn = new JButton("stop");
        lowBtn = new JButton("1");
        medBtn = new JButton("2");
        highBtn = new JButton("3");

        panel.add(stopBtn);
        panel.add(lowBtn);
        panel.add(medBtn);
        panel.add(highBtn);

        frame.add(panel,BorderLayout.SOUTH);
        frame.add(this,BorderLayout.CENTER);
        frame.setVisible(true);

        stopBtn.addActionListener(e->{
            if(e.getSource() == stopBtn){
                speed = 0;
            }
        });

        lowBtn.addActionListener(e->{
            if(e.getSource()  == lowBtn){
                System.out.print("low Btn clicked");
                speed = 3;
            }
        });
        medBtn.addActionListener(e->{
            if(e.getSource() == medBtn){
                speed = 5;
            }
        });
        highBtn.addActionListener(e->{
            if(e.getSource() == highBtn){
                speed = 10;
            }
        });




        Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                angle += speed;
                repaint();   // redraw the fan
            }
        });
        timer.start();


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int xCenter = getWidth() / 2;
        int yCenter = getHeight() / 2;
        int radius = Math.min(getWidth(), getHeight()) / 3;

        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(xCenter - radius, yCenter - radius, 2 * radius, 2 * radius);

        g.setColor(Color.BLUE);

        // Draw 4 blades
        for (int i = 0; i < 4; i++) {
            g.fillArc(xCenter - radius, yCenter - radius, 2 * radius, 2 * radius,
                      angle + i * 90, 30); // each blade covers 30 degrees
        }
    }


    public static void main(String[] args) {
     new  FanAnimation();
    }
}
