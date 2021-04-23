/**
 * Mike Limpus
 * CST 338 Final Project
 * PlayModel.java
 * Handle all of the game calculations and data
 */

import java.util.ArrayList;
public class PlayModel {
    // Members
    public TCDeck p1Deck = new TCDeck();
    public TCDeck p2Deck = new TCDeck(true);
    public static int p1Score = 0, p2Score = 0;
    public static final int HAND_SIZE = 5, BOARD_SIZE = 5;
    public int p1MeleePower, p1RangedPower, p1MagicPower, p1TotalPower, 
        p2MeleePower, p2RangedPower, p2MagicPower, p2TotalPower;
    private TradingCard[] p1MeleeBoard, p1RangedBoard, p1MagicBoard, p1Hand, 
        p2MeleeBoard, p2RangedBoard, p2MagicBoard, p2Hand; 
    private static boolean initialized = false;
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
    public void calculatePower(Weather condition) {
        switch (condition) {
            case CLEAR:         // No change
                p1TotalPower = p1MeleePower + p1RangedPower + p1MagicPower;
                p2TotalPower = p2MeleePower + p2RangedPower + p2MagicPower; 
                break;
            case ECLIPSE:       // Magic * 2, Melee - 5
                p1TotalPower = 
                    (p1MeleePower - 5) + p1RangedPower + (p1MagicPower * 2);
                p2TotalPower = 
                    (p2MeleePower - 5) + p2RangedPower + (p2MagicPower * 2); 
                break;
            case FOG:           // Magic - 10
                p1TotalPower = 
                    p1MeleePower + p1RangedPower + (p1MagicPower - 10);
                p2TotalPower 
                    = p2MeleePower + p2RangedPower + (p2MagicPower - 10); 
                break;
            case HEATWAVE:      // Melee - 10
                p1TotalPower = 
                    (p1MeleePower - 10) + p1RangedPower + p1MagicPower;
                p2TotalPower = 
                    (p2MeleePower - 10) + p2RangedPower + p2MagicPower; 
                break;
            case NICEBREEZE:    // Melee * 2, Ranged - 5
                p1TotalPower = 
                    (p1MeleePower * 2) + (p1RangedPower - 5) + p1MagicPower;
                p2TotalPower = 
                    (p2MeleePower * 2) + (p2RangedPower - 5) + p2MagicPower; 
                break;
            case RAIN:          // Ranged * 2, Magic - 5
                p1TotalPower = 
                    p1MeleePower + (p1RangedPower * 2) + (p1MagicPower - 5);
                p2TotalPower =
                    p2MeleePower + (p2RangedPower * 2) + (p2MagicPower - 5);
                break;
            case WIND:          // Ranged - 10
                p1TotalPower = 
                    p1MeleePower + (p1RangedPower - 10) + p1MagicPower;
                p2TotalPower = 
                    p2MeleePower + (p2RangedPower - 10) + p2MagicPower;
                break;
            default:
                condition = Weather.CLEAR;
                break;
        }
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
        
        


    }

    
}