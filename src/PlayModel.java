/**
 * Mike Limpus
 * CST 338 Final Project
 * PlayModel.java
 * Handle all of the game calculations and data
 */

public class PlayModel {
    // Members
    public TCDeck p1Deck = new TCDeck();
    public TCDeck p2Deck = new TCDeck(true);
    public static int p1Score = 0, p2Score = 0;
    public static final int HAND_SIZE = 6, BOARD_SIZE = 5;
    public int p1MeleePower, p1RangedPower, p1MagicPower, p1TotalPower, 
        p2MeleePower, p2RangedPower, p2MagicPower, p2TotalPower;
    private TradingCard[] p1MeleeBoard, p1RangedBoard, p1MagicBoard, p1Hand, 
        p2MeleeBoard, p2RangedBoard, p2MagicBoard, p2Hand; 
    private static boolean initialized = false;
    private int cpuTracker; 
    /**
     * Weather conditions will affect different card types: 
     * Clear - no change
     * Heatwave - melee nerf 
     * Wind - ranged nerf
     * Fog - magic nerf
     * Eclipse - Magic buff, slight melee nerf
     * Nice Breeze - Melee buff, slight ranged nerf
     * Rain - Ranged buff, slight magic nerf
     */
    public enum Weather {CLEAR, HEATWAVE, WIND, FOG, ECLIPSE, NICEBREEZE, RAIN};

    // Methods
    public PlayModel() {}

    public int calculatePower(Weather condition, int totalPower, int meleePower,
         int rangedPower, int magicPower) {
        switch (condition) {
            case CLEAR:         // No change
                totalPower = meleePower + rangedPower + magicPower; 
                break;
            case ECLIPSE:       // Magic * 2, Melee - 5
                totalPower = 
                    (meleePower - 5) + rangedPower + (magicPower * 2);
                break;
            case FOG:           // Magic - 10
                totalPower = 
                    meleePower + rangedPower + (magicPower - 10);
                break;
            case HEATWAVE:      // Melee - 10
                totalPower = 
                    (meleePower - 10) + rangedPower + magicPower;
                break;
            case NICEBREEZE:    // Melee * 2, Ranged - 5
                totalPower = 
                    (meleePower * 2) + (rangedPower - 5) + magicPower; 
                break;
            case RAIN:          // Ranged * 2, Magic - 5
                totalPower = 
                    meleePower + (rangedPower * 2) + (magicPower - 5);
                break;
            case WIND:          // Ranged - 10
                totalPower = 
                    meleePower + (rangedPower - 10) + magicPower;
                break;
            default:
                condition = Weather.CLEAR;
                break;
        }
        return totalPower;
    }

    public int calculateRowPower(TradingCard[] row) {
        int power = 0;
        for (int i = 0; i < row.length; ++i) {
            if(row[i] != null)
                power += row[i].getPower(); 
        }
        return power;
    }

    /**
     * Allocate memory for the arrays, shuffle the decks
     */
    public void initializeGame() {
        p1MeleeBoard = new TradingCard[BOARD_SIZE];
        p1RangedBoard = new TradingCard[BOARD_SIZE];
        p1MagicBoard = new TradingCard[BOARD_SIZE];
        p1Hand = new TradingCard[HAND_SIZE];
        p2MeleeBoard = new TradingCard[BOARD_SIZE];
        p2RangedBoard = new TradingCard[BOARD_SIZE];
        p2MagicBoard = new TradingCard[BOARD_SIZE];
        p2Hand = new TradingCard[HAND_SIZE]; 

        p1Deck.shuffle();
        p2Deck.shuffle();

    }

    public void roundStart() {
        // Initialize the board on the first round
        if (!initialized) {
            initializeGame();
        }
        // 'Discard the board' by resetting the arrays
        else {
            for (int i = 0; i < BOARD_SIZE; ++i) {
                p1MeleeBoard[i] = null;
                p1RangedBoard[i] = null;
                p1MagicBoard[i] = null;
                p2MeleeBoard[i] = null;
                p2RangedBoard[i] = null;
                p2MagicBoard[i] = null;
            }
        }
        // Draw cards
        for(int i = 0; i < HAND_SIZE; ++i) {
            p1Hand[i] = p1Deck.drawCard();
            p2Hand[i] = p2Deck.drawCard();
        }

        cpuTracker = HAND_SIZE;
    }

    public TradingCard playCard(int index) {
        TradingCard tempCard = new TradingCard(p1Hand[index]);
        p1Hand[index] = null;
        return tempCard;
    }

    /**
     * The not-so-intelligent "AI" for the computer player. The computer will 
     * default to playing their top card. Because this game implementation is 
     * more luck based in the current implementation, this will ultimately be
     * trivial.  
     * @return the "rightmost" card in p2Hand
     */
    public TradingCard cpuPlay() {
        TradingCard tempCard = new TradingCard(p2Hand[cpuTracker]);
        p2Hand[cpuTracker] = null;
        cpuTracker--;
        return tempCard;
    }

    /**
     * Simulate rolling a ten sided die to decide the weather. Clear weather 
     * has a 40% chance, the rest have a 10%
     * @return
     */
    public Weather weatherRoll() {
        int random = (int) (Math.random() * 9);
        switch (random) {
            case 0:
                return Weather.CLEAR;
            case 1: 
                return Weather.CLEAR;
            case 2:
                return Weather.CLEAR;
            case 3:
                return Weather.CLEAR;
            case 4: 
                return Weather.ECLIPSE;
            case 5:
                return Weather.FOG;
            case 6:
                return Weather.HEATWAVE;
            case 7:
                return Weather.NICEBREEZE;
            case 8:
                return Weather.RAIN;
            case 9: 
                return Weather.WIND;
            default:        // We should never get here, but just in case
                return Weather.CLEAR;
        } 
    }


    public TradingCard[] getP1Hand() {
        return p1Hand;
    }

    public TradingCard[] getP2Hand() {
        return p2Hand;
    }
}