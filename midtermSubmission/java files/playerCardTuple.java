package MidProject_rev6;

// what would be faster? a list of tuples? or a tuple of lists?
// in this way, I cant really ''remove'' an object, since this is just a pair of generics.
// instead, the location will be set to null, which essentially is a place-holder for 'nothing'
// rather than it being a location of 'empty data' [?]
// now,
public class playerCardTuple <C, P>{
    private C Card;
    private C ArrayList_TupOf_CardAndPNum;
    private C inheritedChest;

    private P Player;
    private P PlayerList;
    private P DoubleWar_Chest;

    // constructor
    public playerCardTuple(C card, P playerNumber){
        Card = card;
        ArrayList_TupOf_CardAndPNum = card;
        inheritedChest = card;

        Player = playerNumber;
        PlayerList = playerNumber;
        DoubleWar_Chest = playerNumber;
    }

    // getter methods used to help keep track of cards and players
    public C getCard()  {return Card;}
    public P getPlayer(){return Player;}

    // getter methods used to keep track of player/card pair and list of players
    public C getArrayList_TupOf_CardAndPNum(){return ArrayList_TupOf_CardAndPNum;}
    public P getPlayerArrayList()            {return PlayerList;}

    // getter methods used to keep track of Inherited Chest and Double War Chest
    public C get_IChest(){return  inheritedChest;}
    public P get_DChest(){return DoubleWar_Chest;}

    // setter methods
    public void setCard(C C_toSet)    {Card = C_toSet;}
    public void setPlayer(P P_toSet){Player = P_toSet;}



    public void removeCard(C cardToRemove)      {Card = null;}
    public void removePlayer(P playerToRemove)  {Player = null;}


}

