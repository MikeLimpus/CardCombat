/**
 * Mike Limpus
 * CST 338 Final Project
 * PlayModel.java
 * Handle all of the game calculations and data
 */

import java.util.ArrayList;
public class PlayModel {
    // Members
    public TCDeck playerDeck = new TCDeck();
    public TCDeck cpuDeck = new TCDeck(true);
    public static int player1Score = 0;
    public static int player2Score = 0; 
    public int p1MeleePower, p1RangedPower, p1MagicPower, p1TotalPower, 
        p2MeleePower, p2RangedPower, p2MagicPower, p2TotalPower;
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
                
                break;
            case ECLIPSE:       // Magic * 2, Melee - 5
                break;
            case FOG:           // Magic - 10
                break;
            case HEATWAVE:      // Melee - 10
                break;
            case NICEBREEZE:    // Melee * 2, Ranged - 5
                break;
            case RAIN:          // Ranged * 2, Magic - 5
                break;
            case WIND:          // Ranged - 10
                break;
            default:
                break;
        }
    }

    
}