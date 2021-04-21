/**
 * Mike Limpus
 * CST 338 Final Project
 * TradingCard.java
 * Represent the data of a trading card object in the game 
 */

public class TradingCard {
    // Members
    public enum CardType {MELEE, RANGED, MAGIC, DEBUG};
    private int power; 
    private String name;  
    private CardType type;   

    // Methods

    /**
     * Default constructor creates a useless card
     */
    TradingCard() {
        power = 0; 
        name = "Default Card";
        type = CardType.DEBUG;
    }

    /**
     * Create a new card if you have access to the CardType enum
     * @param power
     * @param name
     * @param type
     */
    TradingCard(int power, String name, CardType type) {
        this.power = power;
        this.name = name;
        this.type = type;
    }

    /**
     * Create a new card using a String for its type
     * @param power
     * @param name
     * @param type
     */
    TradingCard(int power, String name, String type) {
        this.power = power; 
        this.name = name; 
        if (type.contains("MELEE")) {
            this.type = CardType.MELEE;
        }
        else if (type.contains("RANGED")) {
            this.type = CardType.RANGED;
        }
        else if (type.contains("MAGIC")) {
            this.type = CardType.MAGIC;
        }
        else {
            this.type = CardType.DEBUG;
        }
    } 

    /**
     * Copy Constructor for TradingCard
     * @param card
     */
    TradingCard(TradingCard card) {
        this.power = card.power;
        this.name = card.name;
        this.type = card.type;
    }
    /**
     * Accessor for power
     * @return power
     */
    public int getPower() {
        return power;
    }

    /**
     * Accessor for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor for type
     * @return type
     */
    public CardType getType() { 
        return type;
    }

    public void print() {
        System.out.println(name + " " + type + " " + power);
    }
}
