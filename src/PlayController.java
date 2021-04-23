import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
public class PlayController {
    // Members
    PlayView gameWindow = new PlayView();
    PlayModel gameModel = new PlayModel();
    public TradingCard[] hand1, hand2;
    // Methods
    public PlayController() {
        gameModel.roundStart();
        hand1 = new TradingCard[PlayModel.HAND_SIZE];
        hand2 = new TradingCard[PlayModel.HAND_SIZE];
        hand1 = gameModel.getP1Hand();
        hand2 = gameModel.getP2Hand();
        for (int i = 0; i < 6; ++i) {            
            gameWindow.p1Hand.add(hand1[i]);
            gameWindow.p2Hand.add(hand2[i]);
        }
        // Create the mouse listeners
        for(int i = 0; i < PlayModel.HAND_SIZE; ++i) { 
            hand1[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle the view manip in here then pass to clicked for
                    // Game manipulation
                    TradingCard temp = (TradingCard) e.getSource();
                    //gameWindow.playCard(temp, true);
                    temp.setIcon(new ImageIcon ("res/image/Empty.jpg"));
                    clicked(temp);   
                    System.out.println("Clicked"); 
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
        // for(int i = 0; i < PlayModel.HAND_SIZE; ++i) {
        //     gameWindow.addCardtoHand(gameModel.getP1Hand()[i], i);
        // }
        //gameWindow.fillCPUHand(); 
        gameWindow.setWeather(gameModel.weatherRoll());
        gameWindow.setPower(gameModel.getP1Power(), gameModel.getP2Power());
        gameWindow.setScore(gameModel.getP1Score(), gameModel.getP2Score());
        
    }
    public void turn() {
        
    }

    public void clicked(TradingCard card) {

        turn();
    }
}
