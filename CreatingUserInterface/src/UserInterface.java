import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class UserInterface {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("User Interface Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the menu
        JMenu menu = new JMenu("Options");
        menuBar.add(menu);

        // Create menu items
        JMenuItem item1 = new JMenuItem("Print Date and Time");
        JMenuItem item2 = new JMenuItem("Save Text to File");
        JMenuItem item3 = new JMenuItem("Change Background Color");
        JMenuItem item4 = new JMenuItem("Exit");

        // Add menu items to the menu
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        menu.add(item4);

        // Set the menu bar for the frame
        frame.setJMenuBar(menuBar);

        // Create the text box
        JTextArea textBox = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textBox);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Action listeners for menu items
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                textBox.setText(now.format(formatter));
            }
        });

        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (FileWriter writer = new FileWriter("log.txt")) {
                    writer.write(textBox.getText());
                    JOptionPane.showMessageDialog(frame, "Text saved to log.txt");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error saving to file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color randomOrangeHue = getRandomOrangeHue();
                frame.getContentPane().setBackground(randomOrangeHue);
                menu.setText(String.format("Options - Background: #%02x%02x%02x", randomOrangeHue.getRed(), randomOrangeHue.getGreen(), randomOrangeHue.getBlue()));
            }
        });

        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    private static Color getRandomOrangeHue() {
        Random random = new Random();
        int r = 255;
        int g = random.nextInt(156); // Green component between 0 and 155
        int b = 0;
        return new Color(r, g, b);
    }
}




