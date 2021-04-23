import java.awt.*;
import javax.swing.*;
public class PlayController {
    // Members
    PlayView gameWindow = new PlayView();
    PlayModel gameModel = new PlayModel();
    // Methods
    public PlayController() {

    }
    public void game() {

    }

    public void round() {
        gameModel.roundStart();
        for(int i = 0; i < PlayModel.HAND_SIZE; ++i) {
            gameWindow.addCardtoHand(gameModel.getP1Hand()[i]);
            gameWindow.fillCPUHand();   
        }
        
    }
    public static void turn() {
        
    }

    public static void clicked(Component e) {
        
        turn();
    }

    public static void main(String[] args) {

    }

}
