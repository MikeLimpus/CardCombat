/**
 * Mike Limpus
 * CST 338 Final Project
 * PlayController.java
 * The final piece of the MVC puzzle. The controller uses instances of PlayModel
 * and PlayView to construct the game itself. It handles some game logic and 
 * GUI layout, but only in instances where the Model and View directly need to 
 * communicate. It is also responsible for all user input through mouse 
 * listeners.
 */

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class PlayController {
    // Members
    PlayView gameWindow = new PlayView();
    PlayModel gameModel = new PlayModel();
    public TradingCard[] hand1, hand2;
    public PlayModel.Weather currentWeather;
    public static int turnCount = 0, roundCount = 0;
    public static final int MAX_TURNS = (PlayModel.HAND_SIZE - 1);
    public static final int MAX_ROUNDS = 5;

    // Methods
    public PlayController() {
        gameModel.roundStart();
        drawHand();
        // Create the mouse listeners
        addMouseListeners();
    }

    /**
     * Because new listeners need to be created every time a hand is drawn, 
     * the code is handled in this method, which is invoked when an instance of
     * the controller is created, as well as at the beginning of each round.
     */
    public void addMouseListeners() {
        for(int i = 0; i < PlayModel.HAND_SIZE; ++i) { 
            gameModel.getP1Hand()[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle the view manip in here then pass to clicked() for
                    // Game manipulation
                    clicked(new TradingCard((TradingCard)e.getComponent()));  
                    e.getComponent().setVisible(false);
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

    /**
     * This method gives the View a reference to the model's hand to draw the
     * TradingCard labels on the screen
     */
    public void drawHand() {
        for(int i = 0; i < PlayModel.HAND_SIZE; ++i) {
            gameWindow.p1Hand.add(gameModel.getP1Hand()[i]);
        }
    }
    
    /**
     * Clear the hand area of all card labels
     */
    public void discardHand() {
        gameWindow.p1Hand.removeAll();
    }

    /**
     * The central game process is divided into 3 levels of abstraction: 
     * turn->round->hand.
     * The game() method is invoked when the round() method has determined the
     * maximum amount of turns have happened, and the round() method determines 
     * when the maximum amount of turns have happened per round. 
     * 
     * The game() method simply determines if the player won or lost, and 
     * invokes winScreen() or loseScreen() as needed.
     */
    public void game() {
        if(gameModel.getP1Score() > gameModel.getP2Score()) 
            gameWindow.winScreen();
        else 
            gameWindow.loseScreen();

        
    }

    /**
     * round() initializes the game board using methods from the model and view.
     * It resets all values and reattaches mouse listeners after cards have 
     * been drawn. It then shares the score and power from the model and passes
     * them to the view. If it determines that the maximum rounds have been 
     * played, it invokes game() to end the program. It should be the method
     * called by the PlayController instance to start the program.
     */
    public void round() {
        if (roundCount < MAX_ROUNDS) {
            // Zero out everything for the new round
            turnCount = 0;
            gameWindow.setPower(0,0);
            gameModel.roundStart();
            gameWindow.clearBoard();
            addMouseListeners();
            gameWindow.fillCPUHand(); 
            currentWeather = gameModel.weatherRoll();
            gameWindow.setWeather(currentWeather);
            gameWindow.setScore(gameModel.getP1Score(), gameModel.getP2Score());
        }
        else {
            game();
        }
    }

    /**
     * turn() is called every time the player clicks a card. It handles both the
     * player and cpu turns at once. It calculates the power using the model 
     * and passes this value to the view. If it determines this should be the 
     * final turn of the round, it updates the score and draws a new hand, then 
     * invokes round().
     */
    public void turn(TradingCard card) {
        playCard(card);
        cpuPlay(gameModel.cpuPlay());
        int power1 = gameModel.calculatePower(currentWeather, 
            gameModel.p1MeleeBoard, gameModel.p1RangedBoard, gameModel.p1MagicBoard);
        int power2 = gameModel.calculatePower(currentWeather,
            gameModel.p2MeleeBoard, gameModel.p2RangedBoard, gameModel.p2MagicBoard);
        gameWindow.setPower(power1, power2);
        turnCount++;
        if (turnCount == MAX_TURNS) {
            if(power1 > power2) {
                gameModel.p1Score++;
            }
            else if(power2 > power1) {
                gameModel.p2Score++;
            }
            discardHand();
            drawHand();
            roundCount++;
            round();
        }
    }

    /**
     * Just a little wrapper for turn to help readablility. See turn().
     */
    public void clicked(TradingCard card) {
        turn(card);
    }

    /**
     * Determines where a played card should go within the view and model, and 
     * removes that card from the hand.
     */
    public void playCard(TradingCard card) {
        switch (card.getType()) {
        case DEBUG:
            break;
        case MAGIC:
            gameWindow.p1Magic.add(card);
            gameModel.p1MagicBoard.add(card);
            break;
        case MELEE:
            gameWindow.p1Melee.add(card);
            gameModel.p1MeleeBoard.add(card);
            break;
        case RANGED:
            gameWindow.p1Ranged.add(card);
            gameModel.p1RangedBoard.add(card);
            break;
        default:
            break;
        }

        for(int i = 0; i < PlayModel.HAND_SIZE; ++i) {
            if(card == gameModel.p1Hand[i])
                gameModel.p1Hand[i] = null;
        }
    }

    /**
     * Same as playCard, but does not remove the card as this is handled by the 
     * model for a cpu player.
     */
    public void cpuPlay(TradingCard card) {
        switch (card.getType()) {
        case DEBUG:
            break;
        case MAGIC:
            gameWindow.p2Magic.add(card);
            gameModel.p2MagicBoard.add(card);
            break;
        case MELEE:
            gameWindow.p2Melee.add(card);
            gameModel.p2MeleeBoard.add(card);
            break;
        case RANGED:
            gameWindow.p2Ranged.add(card);
            gameModel.p2RangedBoard.add(card);
            break;
        default:
            break;
        }
    }
}
