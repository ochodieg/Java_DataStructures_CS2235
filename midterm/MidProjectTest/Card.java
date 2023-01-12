package MidProjectTest;


public class Card {
    // object that says what a card is
    //2 instance variables
    // cards have # and a suit.
    // suit doesnt matter
    // value ca be rank

    // instance variables
    private int value; // will represent 2-A as 2-14, joker is 15 and doesnt have a suit (joker will be the highest)
    // can use "use joker" flag to pick
    // 52 cards without jokers
    // 54 cards with joker <-- maybe just use this cuz it's divisible by 3
    private String suit; // will rep suit
    // 4 suits
    // 5th suit for joker


    // constructor
    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;

    }

    // 2 getter methods
    // no update methods
    public int getValue() {
        return value;
    }
    public String getSuit() {
        return suit;
    }    // will need when printing out the steps

    // calling to methods is more common than accessing the states
    // but we could also just access the states (instance variables) to get this info
    public String toString(){
        // overloading toString
        // to print out what the card is
        String sb = getValue() + getSuit();
        return sb;
    }
}
