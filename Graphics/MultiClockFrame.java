import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class MultiClockFrame extends JFrame {
    private StillClock bangkokClock =
            new StillClock(TimeZone.getTimeZone("Asia/Bangkok"));
    private StillClock londonClock =
            new StillClock(TimeZone.getTimeZone("Europe/London"));
    private StillClock tokyoClock =
            new StillClock(TimeZone.getTimeZone("Asia/Tokyo"));

    public MultiClockFrame() {
        setLayout(new GridLayout(1, 3));

        add(createClockPanel("Bangkok", bangkokClock));
        add(createClockPanel("London", londonClock));
        add(createClockPanel("Tokyo", tokyoClock));

        // Update every second
        Timer timer = new Timer(1000, e -> {
            bangkokClock.setCurrentTime();
            londonClock.setCurrentTime();
            tokyoClock.setCurrentTime();
            repaint();
        });
        timer.start();
    }

    private JPanel createClockPanel(String city, StillClock clock) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(city, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label, BorderLayout.NORTH);
        panel.add(clock, BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        JFrame frame = new MultiClockFrame();
        frame.setTitle("World Clocks");
        frame.setSize(250, 750);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class StillClock extends JPanel {
    private int hour;
    private int minute;
    private int second;
    private TimeZone timeZone;

    public StillClock(TimeZone timeZone) {
        this.timeZone = timeZone;
        setCurrentTime();
    }

    public void setCurrentTime() {
        Calendar calendar = new GregorianCalendar(timeZone);
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int clockRadius = (int) (Math.min(getWidth(), getHeight()) * 0.4);
        int xCenter = getWidth() / 2;
        int yCenter = getHeight() / 2;

        // Draw circle
        g.setColor(Color.BLACK);
        g.drawOval(xCenter - clockRadius, yCenter - clockRadius,
                2 * clockRadius, 2 * clockRadius);
        g.drawString("12", xCenter - 5, yCenter - clockRadius + 12);
        g.drawString("9", xCenter - clockRadius + 3, yCenter + 5);
        g.drawString("3", xCenter + clockRadius - 10, yCenter + 3);
        g.drawString("6", xCenter - 3, yCenter + clockRadius - 3);

        // Draw second hand
        int sLength = (int) (clockRadius * 0.8);
        int xSecond = (int) (xCenter + sLength * Math.sin(second * (2 * Math.PI / 60)));
        int ySecond = (int) (yCenter - sLength * Math.cos(second * (2 * Math.PI / 60)));
        g.setColor(Color.RED);
        g.drawLine(xCenter, yCenter, xSecond, ySecond);

        // Draw minute hand
        int mLength = (int) (clockRadius * 0.65);
        int xMinute = (int) (xCenter + mLength * Math.sin(minute * (2 * Math.PI / 60)));
        int yMinute = (int) (yCenter - mLength * Math.cos(minute * (2 * Math.PI / 60)));
        g.setColor(Color.BLUE);
        g.drawLine(xCenter, yCenter, xMinute, yMinute);

        // Draw hour hand
        int hLength = (int) (clockRadius * 0.5);
        int xHour = (int) (xCenter + hLength *
                Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));
        int yHour = (int) (yCenter - hLength *
                Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));
        g.setColor(Color.GREEN);
        g.drawLine(xCenter, yCenter, xHour, yHour);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }
}
