import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
public class PlayController {
    // Members
    PlayView gameWindow = new PlayView();
    PlayModel gameModel = new PlayModel();
    int foo;
    // Methods
    public PlayController() {
        gameModel.roundStart();
        // Create the mouse listeners
        for(int i = 0; i < PlayModel.HAND_SIZE; ++i) {
            gameModel.getP1Hand()[i].setPosInHand(i); 
            gameWindow.hand1[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle the view manip in here then pass to clicked for
                    // Game manipulation
                    JLabel temp = (JLabel) e.getSource();
                    foo = i;
                    temp.setIcon(new ImageIcon ("res/image/Empty.jpg"));
                    clicked(e.getComponent());    
                }

                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
                
            });
        }
    }
    public void game() {
        

    }

    public void round() {
        gameModel.roundStart();
        for(int i = 0; i < PlayModel.HAND_SIZE; ++i) {
            gameWindow.addCardtoHand(gameModel.getP1Hand()[i], i);
        }
        gameWindow.fillCPUHand(); 
        gameWindow.setWeather(gameModel.weatherRoll());
        gameWindow.setPower(gameModel.getP1Power(), gameModel.getP2Power());
        gameWindow.setScore(gameModel.getP1Score(), gameModel.getP2Score());
        
    }
    public void turn() {
        
    }

    public void clicked(Component e) {
        
        turn();
    }
}
