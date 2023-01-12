package MidProject_rev6;


// we will be shuffling the deck and randomly deal
// then we're gonna need an arraylist

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Deck {
    // Instance Variables
    // contains one thing -> collection of cards
    private ArrayList<Card> deck; // deck of cards

    // constructor for deck
    public Deck(boolean joker){
        if (joker){
            deck = new ArrayList<Card>(54);
            String[] suits = {"H", "D", "S", "C"};
             for(var suit : suits){
                 for ( int i = 2; i <= 14; i++){ // add 13 cards (11 = j, 12 = q, 13 = k, 14 = ace
                     // create card object
                     Card card = new Card(i, suit);
                     deck.add(card); //comes from java util
                 }
             }
            Card card = new Card(15, "J");
            deck.add(card);
            deck.add(card);
        }
        else {
            deck = new ArrayList<Card>(52);
            String[] suits = {"H", "D", "S", "C"};
            for(var suit : suits){
                for ( int i = 2; i <= 14; i++){ // add 13 cards (11 = j, 12 = q, 13 = k, 14 = ace
                    // create card object
                    Card card = new Card(i, suit);
                    deck.add(card); //comes from java util
                }
            }
        }

    }

    // basic constructor creates empty deck
    public Deck(){
        deck = new ArrayList<Card>();
    }

    // Methods
    public ArrayList<Card> inDeck(){
        return this.deck;
    }

    public String toString() {
        String sb = "";
        int cardAmount = 0;
        for (var car : deck) { // --> for (Card car : deck) {
            sb += car.toString() + "\t";
            cardAmount += 1;
        }
        // sb += cardAmount;
        // carAmount just used for troubleshooting
        return sb;
    }

    public void addCard(Card c){
        deck.add(c);
    }

    public Card getCard(int index){ // -1
        index -= 1;
        if(deck.size() > index){ // out of bounds check
            return deck.get(index);
        }else{
            System.out.println("problem with getCard and index: " + index);
            return null;
        }
    }

    public Card getCard(Card c){
        int value = c.getValue();
        String suit = c.getSuit();
        Card returnCard = null;

        for (var card : this.deck){
            if(Objects.equals(card.getValue(),value) && Objects.equals(card.getSuit(), suit)){
                returnCard = card;
            }
        }
        return returnCard;
    }


    //public Card getCard(int index){
     //   return deck.get(index);
  //  }

    public int getCardIndex(Card c) {
        int value = c.getValue();
        String suit = c.getSuit();
        int cardCount = 0;
        int cardIndex = 0;
        int index = -1;

        for (var card : this.deck) {
            index += 1;
            if (Objects.equals(card.getValue(), value) && Objects.equals(card.getSuit(), suit)) {
                cardIndex = index;
                cardCount += 1;
            }
        }

        if (cardCount > 1){
            System.out.println("getCardIndex error: more than one of this card");
            return -1;
        }
        else return cardIndex;
    }





    public int size() { return deck.size(); }
    public void shuffle() {
        Collections.shuffle(deck);  // just steal this from collections (ezpz)
    }
    public Card dealCard(){
        // take from top or bottom? since its an arrayList, we'll just take it from
        // the 0 index
        // however, if we remove from the 0 index, we must shift the entire rest
        // of the array afterwards, therefore, it might be computationally faster
        // to remove from the back (last index) instead
        if (deck.size() > 0){   // check to make sure there are cards in here

            return deck.remove(0); // deck.remove(deck.size()-1)
            // it might be faster to just copy the array onto a deque
            // then deal from that
        }
        return null;
    }
// card will be added last and into the derck take from the first as we play
    // we always want to be adding to the bottom of the deck
    // pit it on the back

}
