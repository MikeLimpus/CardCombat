package src;

/**
 * Mike Limpus
 * CST 338 Final Project
 * Play
 * Creates the swing window and elements of the game board
 */
import java.awt.*;
import javax.swing.*;

public class PlayView extends JFrame {
    // Members
    private static final String PROGRAM_NAME = "Card Combat";
    public JPanel leftPanel, rightPanel; 
    // Right side panels
    public JPanel p1Melee, p1Ranged, p1Magic, 
        p2Melee, p2Ranged, p2Magic, weatherPanel;
    // Left side panels
    public JPanel p1Hand, p2Hand, gameInfo;
    public JLabel score, p1Score, p2Score;

    public PlayView(String title) {
        super(title);
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Initialize the Jpanel and Jlabel objects
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        p1Melee = new JPanel();
        p1Ranged = new JPanel();
        p1Magic = new JPanel();
        p2Melee = new JPanel();
        p2Ranged = new JPanel();
        p2Magic = new JPanel();
        weatherPanel = new JPanel();
        p1Hand = new JPanel();
        p2Hand = new JPanel();
        gameInfo = new JPanel();
        score = new JLabel("0 - 0", JLabel.CENTER);       //TODO Add the scores here
        score.setFont(new Font("San Serif", Font.PLAIN, 20));
        p1Score = new JLabel("p1Score", JLabel.CENTER);
        p2Score = new JLabel("p2Score", JLabel.CENTER);

        // Add some layouts and borders
        getContentPane().setLayout(new GridLayout(0,2));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder());
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder());
        p1Melee.setLayout(new FlowLayout());
        p1Melee.setBorder(BorderFactory.createTitledBorder("Melee"));
        p1Ranged.setLayout(new FlowLayout());
        p1Ranged.setBorder(BorderFactory.createTitledBorder("Ranged"));
        p1Magic.setLayout(new FlowLayout());
        p1Magic.setBorder(BorderFactory.createTitledBorder("Magic"));
        p2Melee.setLayout(new FlowLayout());
        p2Melee.setBorder(BorderFactory.createTitledBorder("Melee"));
        p2Ranged.setLayout(new FlowLayout());
        p2Ranged.setBorder(BorderFactory.createTitledBorder("Ranged"));
        p2Magic.setLayout(new FlowLayout());
        p2Magic.setBorder(BorderFactory.createTitledBorder("Magic"));
        p1Hand.setBorder(BorderFactory.createTitledBorder("Hand"));
        p2Hand.setBorder(BorderFactory.createTitledBorder("Hand"));
        gameInfo.setLayout(new BoxLayout(gameInfo, BoxLayout.Y_AXIS));
        gameInfo.setBorder(BorderFactory.createEmptyBorder());

        // Add everything
        this.add(leftPanel);
        this.add(rightPanel);
        leftPanel.add(p2Hand);
        leftPanel.add(gameInfo);
        leftPanel.add(p1Hand);
        rightPanel.add(p2Magic);
        rightPanel.add(p2Ranged);
        rightPanel.add(p2Melee);
        rightPanel.add(weatherPanel);
        rightPanel.add(p1Melee);
        rightPanel.add(p1Ranged);
        rightPanel.add(p1Magic);
        gameInfo.add(p2Score);
        gameInfo.add(score);
        gameInfo.add(p1Score);



        setVisible(true);
    }


    public static void main(String[] args) {
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
        } 
        catch (UnsupportedLookAndFeelException e) {
           // handle exception
           e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
           // handle exception
           e.printStackTrace();
        }
        catch (InstantiationException e) {
           // handle exception
           e.printStackTrace();
        }
        catch (IllegalAccessException e) {
           // handle exception
           e.printStackTrace();
        }
        PlayView view = new PlayView(PROGRAM_NAME);
    }
}
