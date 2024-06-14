import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class ModernDigitalClock extends JFrame {

    private JLabel timeLabel;
    private JLabel dateLabel;
    private SimpleDateFormat timeFormat;
    private SimpleDateFormat dateFormat;

    public ModernDigitalClock() {
        // Set up the JFrame
        setTitle("Digital Clock");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        // Initialize time and date format
        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");

        // Create and configure the time label
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.BOLD, 48));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setForeground(Color.WHITE);

        // Create and configure the date label
        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dateLabel.setForeground(Color.WHITE);

        // Create a panel with a gradient background
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                Color color1 = new Color(66, 134, 244);
                Color color2 = new Color(19, 84, 122);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };
        panel.setLayout(new BorderLayout());
        panel.add(timeLabel, BorderLayout.CENTER);
        panel.add(dateLabel, BorderLayout.SOUTH);

        add(panel);

        // Start the clock
        startClock();

        // Make the JFrame visible
        setVisible(true);
    }

    private void startClock() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Update the time and date labels with the current time and date
                String time = timeFormat.format(new Date());
                String date = dateFormat.format(new Date());
                timeLabel.setText(time);
                dateLabel.setText(date);
            }
        }, 0, 1000); // Update every second
    }

    public static void main(String[] args) {
        // Run the clock in the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new ModernDigitalClock());
    }
}