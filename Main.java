import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    // 1. PRECISION POINT: Variables go here at the top
    static int fumbleCount = 0;
    static int currentDayIndex = 0; // This tracks which box to color (Starts at 0)
    static JLabel label;
    static JPanel gridPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("SpeakStreak - Progress Tracker");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(30, 30, 30));

        // --- UI: COUNTER ---
        label = new JLabel("Fumbles: 0", SwingConstants.CENTER);
        label.setFont(new Font("Monospaced", Font.BOLD, 50));
        label.setForeground(Color.WHITE);
        frame.add(label, BorderLayout.CENTER);

        // --- UI: GRID ---
        gridPanel = new JPanel(new FlowLayout());
        gridPanel.setBackground(new Color(30, 30, 30));

        for (int i = 0; i < 7; i++) {
            JPanel daySquare = new JPanel();
            daySquare.setPreferredSize(new Dimension(30, 30));
            daySquare.setBackground(Color.GRAY);
            daySquare.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            gridPanel.add(daySquare);
        }
        frame.add(gridPanel, BorderLayout.SOUTH);

        // 2. PRECISION POINT: The Key Listener Logic
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    fumbleCount++;
                    label.setText("Fumbles: " + fumbleCount);
                    flashLabel(Color.RED);
                }
                else if (e.getKeyCode() == KeyEvent.VK_S) {
                    saveFumbleData(fumbleCount, frame);

                    // This is the logic that moves left-to-right
                    if (currentDayIndex < 7) {
                        updateGrid(currentDayIndex);
                        currentDayIndex++; // Move to the next box for the next Save
                    }

                    resetCounter();
                }
            }
        });

        frame.setFocusable(true);
        frame.setVisible(true);
        frame.requestFocusInWindow();
    }

    private static void updateGrid(int index) {
        JPanel square = (JPanel) gridPanel.getComponent(index);
        square.setBackground(new Color(57, 211, 83)); // GitHub Green
    }

    private static void flashLabel(Color color) {
        label.setForeground(color);
        Timer timer = new Timer(150, e -> label.setForeground(Color.WHITE));
        timer.setRepeats(false);
        timer.start();
    }

    private static void resetCounter() {
        fumbleCount = 0;
        label.setText("Fumbles: 0");
    }

    public static void saveFumbleData(int count, JFrame frame) {
        try {
            FileWriter writer = new FileWriter("fumble_log.txt", true);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            writer.write(dtf.format(LocalDateTime.now()) + " | Fumbles: " + count + "\n");
            writer.close();
            JOptionPane.showMessageDialog(frame, "Session Saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}