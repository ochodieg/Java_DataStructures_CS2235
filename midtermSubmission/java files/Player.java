package MidProject_rev6;


import java.util.ArrayDeque;

public class Player {   // player class is meant to hold the pile of cards that a player has
    // cards dealt to player will be stored in their deque
    // cards being used in a war will be stored in war chest deque then passed to victors deque (use addAll() ?)

    // there a few times when you really only deal with front and back so deques can be hard to use

    // instance variables
    private ArrayDeque<Card> playerDeck;
    private int playerNumber;


    // constructor
    public Player(int playerNumber){
        playerDeck = new ArrayDeque<Card>(); // constructor w/ empty deck ready to be added to
        this.playerNumber = playerNumber;
    }

    // methods
    public  void bottomAddCard( Card c) {
        playerDeck.addLast(c);  // add last as we add to the bottom of the deck
    }

    public  void topAddCard( Card c) {
        playerDeck.addFirst(c);  // add last as we add to the bottom of the deck
    }

    public Card dealCard(){ return playerDeck.removeFirst();}


    // this causes players name to be null when printing player
    public String toString(){
        // used for troubleshooting
        String sb = "";
        for (var card : playerDeck){
            sb += card.toString() + "\t";
        }
        return sb;
    }


    public ArrayDeque<Card> getPlayerDeck(){return playerDeck;}
    public int getPlayerNumber() {return playerNumber;}
    public int size() { return playerDeck.size(); }
    // may also want a getFirst(), removeFirst() etc
    // what to do with wars? could have a warChest. <--could have an ArrayDeque<Card> warChest in War play(), to hold the Cards that
    // are undergoing war


}
