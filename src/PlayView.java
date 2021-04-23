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
    public JPanel p1Hand, p2Hand, gameInfo, score;
    public JLabel p1Score, p2Score, p1Power, p2Power, weatherLabel, weatherDsc;
    public JLabel[] hand1, hand2;

    public PlayView(String title) {
        super(title);
        this.setSize(1280, 1024);
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
        score = new JPanel();
        p1Score = new JLabel("Player 1 Score", JLabel.RIGHT);       //TODO Add the scores here
        p2Score = new JLabel("Player 2 Score", JLabel.LEFT);
        p1Power = new JLabel("Player 1 Power", JLabel.CENTER);
        p2Power = new JLabel("Player 2 Power", JLabel.CENTER);
        weatherLabel = new JLabel("Weather", JLabel.CENTER);
        weatherDsc = new JLabel("Weather info here", JLabel.CENTER);
        hand1 = new JLabel[5];
        hand2 = new JLabel[5];


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
        p1Hand.setLayout(new FlowLayout());
        p1Hand.setBorder(BorderFactory.createTitledBorder("Hand"));
        p1Hand.setLayout(new FlowLayout());
        p2Hand.setBorder(BorderFactory.createTitledBorder("Hand"));
        gameInfo.setLayout(new GridLayout(3,0));
        gameInfo.setBorder(BorderFactory.createEmptyBorder());
        weatherPanel.setLayout(new GridLayout(2,0));
        score.setLayout(new GridLayout(0,3));
        p1Score.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        p2Score.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        p1Power.setFont(new Font("Sans Serif", Font.PLAIN, 24));
        p2Power.setFont(new Font("Sans Serif", Font.PLAIN, 24));
        weatherLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));

        // Add everything
        this.add(leftPanel);
        this.add(rightPanel);
        leftPanel.add(p2Hand);
        leftPanel.add(gameInfo);
        gameInfo.add(score);
        score.add(p1Score);
        score.add (new JLabel("-", JLabel.CENTER));
        score.add(p2Score);
        leftPanel.add(p1Hand);
        rightPanel.add(p2Magic);
        rightPanel.add(p2Ranged);
        rightPanel.add(p2Melee);
        rightPanel.add(weatherPanel);
        weatherPanel.add(weatherLabel);
        weatherPanel.add(weatherDsc);
        rightPanel.add(p1Melee);
        rightPanel.add(p1Ranged);
        rightPanel.add(p1Magic);
        gameInfo.add(p2Power);
        gameInfo.add(score);
        gameInfo.add(p1Power);
        setVisible(true);
    }

    public void setWeather(PlayModel.Weather condition) {
        switch(condition) {
        case CLEAR:
            weatherLabel.setText("Clear");
            weatherDsc.setText("The clear weather bothers nobody");
            break;
        case ECLIPSE:
            weatherLabel.setText("Eclipse");
            weatherDsc.setText("Your mages are empowered but your warriors' " +
                "vision is hindered");
            break;
        case FOG:
            weatherLabel.setText("Fog");
            weatherDsc.setText("Your mage's vision is concealed");
            break;
        case HEATWAVE:
            weatherLabel.setText("Heatwave");
            weatherDsc.setText("Your warrior's stamina is drained by the heat");
            break;
        case NICEBREEZE:
            weatherLabel.setText("Nice Breeze");
            weatherDsc.setText("The nice breeze cools your warriors but makes" +
                "arrows miss their mark");
            break;
        case RAIN:
            weatherLabel.setText("Rain");
            weatherDsc.setText(
                "The rain and mud slows all, helping archers' aim");
            break;
        case WIND:
            weatherLabel.setText("Wind");
            weatherDsc.setText(
                "The wind makes arrows fly far from their intended target");
            break;
        default:
            condition = PlayModel.Weather.CLEAR;
            break;
        }
    }

    public void setLabelText(JLabel label, String str) {
        label.setText(str);
    }
    

    public static void main(String[] args) {
        // From the official Java documentation, sets the app to look more 
        try {
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
