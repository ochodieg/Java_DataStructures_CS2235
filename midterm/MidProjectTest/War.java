package MidProjectTest;


import java.util.ArrayList;
import java.util.Scanner;

// highlight code and
// press alt + enter to scratch
// decided to use switch cases to check
// verbosity in every method, instead of doing
// different methods. supposedly switch's
// are a lot faster than if's and since I'm not running
// prints when simulating a large # of games, it really wont effect it
// much anyway. Remember: keep your code D-R-Y
// ctr + '-' to hide code block
// double click '}' to highlight code block
public class War { // plays ome game of War (not to be confused with a tie = war)
    // instance variables

    private final int       playerNum;
    private final int       verbosity;
    private final int       gamesNum;
    private final boolean   jokerPick;
    public int playerCount;
    public String startingDeck1 = ""; //<-- these need to be initialized otherwise they print a null, before printing what they were assigned???
    public String startingDeck2 = "";
    public String verbType = "";
    public int warCount;
    public int doubleWarCount;
    public int TripleWarCount;


    //#################################################################################################################################################

    // constructor
    // constructor can contain all the methods ie when you create a new war, it will
    // initialize, play, and report for you
    // or you can make a bland constructor that will call some methods
    // and the rest from outside
    public War(){
        playerNum = this.getPlayers();
        verbosity = this.getVerbosity();
        gamesNum = this.getGames();
        jokerPick = this.getJoker();
        ArrayList<Player> playerArrayList = this.initialize(jokerPick, playerNum, verbosity);
        this.report(playerArrayList,verbosity);
        ArrayList<Player> updatedList = this.DrawCards(playerArrayList,1, false);
        this.playerDeckState(updatedList, verbosity);

        /*
        //------------------------------------------------------------------------------------------------------------
        System.out.println("**** check ****");
        for(var player : playerArrayList){ System.out.println(player.dealCard());} // check that dealing card correctly
        for(var player : playerArrayList){ System.out.println(player.dealCard());} // check that dealing card correctly
        for(var player : playerArrayList){ System.out.println(player.dealCard());} // check that dealing card correctly
        for(var player : playerArrayList){ System.out.println(player);}            // check that dealing card correctly
        System.out.println("**** check ****");
        for(var player : playerArrayList){
            int pCount = player.size();
            for(int i = 0; i < pCount; i++){
                player.dealCard();}}
        for(var player : playerArrayList){
            if (player.size() > 0){
                System.out.println(player);
            }else System.out.println("no cards");}
        System.out.println("** end check **");
        //------------------------------------------------------------------------------------------------------------
        */
        // print summary
        // pass list into play() and the return will be the game sunmmary
    }

    //#################################################################################################################################################
    // methods
    //******************************************** START *************************************************************
    private int getPlayers(){ // private - calling it from within class instance
        Scanner  askAmount = new Scanner(System.in);
        String playerSelect = """
                    
                    How many players will there be?""";
        System.out.println(playerSelect);
        int players;
        do {
            System.out.print("(Please enter at least 2, but no more than 13, players)\n>> ");
            while (!askAmount.hasNextInt()) {
                String input = askAmount.next();
                System.out.printf("*** \"%s\" is not a valid amount ***\n", input);
                System.out.print("*** Please enter at least 2 players ***\n>> ");
            }
            players = askAmount.nextInt();
        } while (players < 2  || players > 13);

        System.out.printf("-- You have entered %d players --\n", players);
        //playerNum = players;
        playerCount += players;
        // System.out.println("getPlayers check " + players); // check
        //playerNum.close(); // dont close scanner until finally done
        return players;}
    //********************************************* END **************************************************************
    //******************************************** START *************************************************************
    private int getGames(){
        Scanner gameAmount = new Scanner(System.in);

        String gameSelect = """
                                    
                How many games will be simulated?""";
        System.out.println(gameSelect);
        int games;
        do {
            System.out.print("(Please enter at least 1 game)\n>> ");
            while (!gameAmount.hasNextInt()) {
                String input = gameAmount.next();
                System.out.printf("*** \"%s\" is not a valid amount ***\n", input);
                System.out.print("*** Please enter at least 1 game ***\n>> ");
            }
            games = gameAmount.nextInt();
        } while (games < 1 );
        System.out.printf("-- You have entered %d games --\n", games);
        // System.out.println("getGames check " + games); // check
        //gameAmount.close(); // dont close scanner until finally done
        return games;}
    //********************************************* END **************************************************************
    //******************************************** START **************************************************************
    private int getVerbosity(){
        Scanner verbiage = new Scanner(System.in);
        String verbiageSelect = """
                    
                    How verbose should this program be?
                    1) Print everything.
                    2) Print important metrics.
                    3) Print summary only.""";
        System.out.println(verbiageSelect);

        int number;
        do {
            System.out.print("(Please select a given option)\n>> ");
            while (!verbiage.hasNextInt()) {
                String input = verbiage.next();
                System.out.printf("*** \"%s\" is not a valid option ***\n", input);
            }
            number = verbiage.nextInt();
        } while (number < 1 || number > 3);

        System.out.printf("-- Verbosity select: %d --\n", number);
        // System.out.println("getVerbosity check " + number); // check
        return number;}
    //********************************************* END **************************************************************
    //******************************************** START *************************************************************
    private boolean getJoker(){
        Scanner askForJ = new Scanner(System.in);
        boolean joker;
        String jokerSelect = """
                
                Will this game use Jokers?
                <y> yes
                <N> No
                >>  """;
        System.out.print(jokerSelect);
        String choice = askForJ.next();
        while ( !(choice.equals("yes") || (choice.equals("y"))) &&
                !(choice.equals("no") || (choice.equals("n")))
        ){
            System.out.println("*** please select a valid option ***\n>> ");
            choice = askForJ.next();
        }
        switch (choice){
            case "yes", "y" -> {    // switch case yes or y turns joker into true
                joker = true;
                System.out.println("-- Jokers will be used (54 card deck) --\n");
            }
            case "no", "n"  -> {    // switch case no or n turns joker into true
                joker = false;
                System.out.println("-- Jokers will not be used (52 card deck) --\n");
            }
            default -> throw new IllegalStateException("Unexpected value: " + choice);
            // added throw since i initiated joker up top but i didn't want it to give a false value for whatever
            // reason, I'd rather get an error msg to help troubleshoot if need be
        }
        return joker;
    }
    //********************************************* END **************************************************************
    //******************************************** START *************************************************************
    // composes string that lists the cards players were first dealt
    private void firstDeckDeal(ArrayList<Player> playerList, int verbiageSelect){
        int pCount = 1;

        String firstDeal = "";

        switch (verbiageSelect){
            case 1 -> {
                for (var player : playerList){
                    int cCount = player.size();
                    firstDeal += "player " + pCount + " has " + cCount + " cards:\n" + player + "\n\n"; // check
                    startingDeck1 += "player " + pCount + " started with " + cCount + " cards\n";
                    pCount += 1;
                }
                System.out.println(firstDeal);
            }
            case 2 -> {
                for (var player : playerList){
                    int cCount = player.size();
                    firstDeal += "player " + pCount + " has " + cCount + " cards\n"; // check
                    startingDeck2 += "     player " + pCount + " started with " + cCount + " cards\n";
                    pCount += 1;
                }
            }
            //case 3 -> {
            //for (var player : playerList) {
            //int cCount = player.size();
            //}
            //}
        }
    }
    //********************************************* END **************************************************************
    // prints the state of player decks
    private void playerDeckState(ArrayList<Player> playerList, int verbiageSelect){
        int pCount = 1;
        String firstDeal = "";
        switch (verbiageSelect){
            case 1 -> {
                for (var player : playerList){
                    int cCount = player.size();
                    firstDeal += "player " + pCount + " has " + cCount + " cards: " + player + "\n"; // check
                    //startingDeck1 += "player " + pCount + " started with " + cCount + " cards\n";
                    pCount += 1;
                }
                System.out.println(firstDeal);
            }
            case 2 -> {
                for (var player : playerList){
                    int cCount = player.size();
                    firstDeal += "player " + pCount + " has " + cCount + " cards\n"; // check
                    //startingDeck2 += "     player " + pCount + " started with " + cCount + " cards\n";
                    pCount += 1;
                }
            }
            //case 3 -> {
            //for (var player : playerList) {
            //int cCount = player.size();
            //}
            //}
        }

    }
    //******************************************** START *************************************************************
    // initialize()
    // create the deck
    // deal to players
    private ArrayList<Player> initialize(boolean joker, int players, int verbiageSelect){
        // create the deck and deal it to the players
        Deck newDeck = new Deck(joker);
        newDeck.shuffle();
        ArrayList<Player> playerList = new ArrayList<>(); // make list to add players to
        int n = 0;
        for (int i = 0; i < players; i++) { // add all players to player list
            playerList.add(i, new Player(i));
        }
        int totalCards = newDeck.size();
        int totalPlayers = playerList.size();
        int modulus = totalCards % totalPlayers;
        if (modulus == 0){
            for (var player : playerList){
                for (int i = 0; i < (totalCards/totalPlayers); i++)
                    player.bottomAddCard(newDeck.dealCard());
            }
        } else if (modulus != 0){
            for (var player : playerList){
                for (int i = 0; i < (totalCards/totalPlayers); i++)
                    player.bottomAddCard(newDeck.dealCard());
            }
            for (int i = 0; i < modulus; i++) playerList.get(i).bottomAddCard(newDeck.dealCard());
        }
        if (verbiageSelect == 1) {
            System.out.println("Starting metrics:");
            firstDeckDeal(playerList, verbiageSelect); // prints initial deck depending on verbiage select
        }
        return playerList;
    }
    //********************************************* END **************************************************************



    //******************************************** START *************************************************************
    // play() (while check if there is only one player left that has cards then stop)
    // like checking allBears()
    // placing cards at each step
    // deal with wars
    // know when a player has won
    // include bi-step printing or printing in ea. step (optional)
    private void play(ArrayList<Player> playerArrayList){
        // draw card

    }
    //********************************************* END **************************************************************

    //******************************************** START *************************************************************
    private ArrayList<Player> DrawCards(ArrayList<Player> playerArrayList, int verbosity, boolean warTime ){
        // draws players first card and gives all to the highest player
        // contain all cards in deqeue?
        // is it better to do that?


        ArrayList<Card> warChest = new ArrayList<>();   // create war chest
        if(!warTime) {
            for (var player : playerArrayList) {    // for every player
                warChest.add(player.dealCard());    // take one card from deck and place it in war chest
            }
            System.out.println("war chest:\n" + warChest); // check print war chest
            //ArrayList<Player> peaceUpdatedList; // create updated list
            // WarChest() will give winning player cards

            return WarChest(playerArrayList, warChest, verbosity, 0); }
        else {
            for (var player : playerArrayList) {    // for every player
                if (player.size() > 4){
                    for(int i = 0; i < 4; i++){
                        warChest.add(player.dealCard());    // take 4 cards from deck and place it in war chest
                    }
                }else if (player.size() < 4){
                    for(int i = 0; i < player.size(); i++){
                        warChest.add(player.dealCard());    // take 4 cards from deck and place it in war chest
                    }
                }
            }
            System.out.println("war chest:\n" + warChest); // check print war chest
            // WarChest() will give winning player cards

        return WarChest(playerArrayList, warChest, verbosity, 1);
        }
    }

    //********************************************* END **************************************************************





    /*
    //******************************************** START *************************************************************
    private ArrayList<Player> WarChest(ArrayList<Player> playerArrayList, ArrayList<Card> warChest, int verbosity, int warTime){
        // conatins drawn cars
        ArrayList<Player> updatedPlayerList = new ArrayList<>();
        int cardTracker = 0;    // keeps track of winning card------------------------------------------------------------------------------------------not used
        int count = 0;          // keeps track of largest card
        ArrayList<Integer> warLords = new ArrayList<>();  // indexes of players going to war
        switch (warTime){
            case 0 ->{ // no war. determining if winner or war
                // Switch is useful and clear for enumerated values, not for comparisons
                // and switch does not support logic expressions so i will use if's
                for( var card : warChest){      // for every card in deck
                    if (card.getValue() > count){ // if the value of the card is larger than the last
                        count = card.getValue(); // count takes up the value of the card
                        cardTracker = warChest.indexOf(card);   // card tracker value becomes the index of card that has highest and is used for winner in no-war
                        warLords.clear(); // prevents from accidentally adding wrong cards to war[?]
                    } else if (card.getValue() == count){   // if the
                        //warLords.addFirst(playerTracker); // index count to send to toWar() to pull the right players for war
                        warLords.clear();   // need to clear first. without, if first card is already part of the highest, then
                                                // then (card.getValue() > count) will never be true again and if there are three
                                                // or more players going to war, warLords.add(warChest.indexOf(sameCard) will
                                                // fire twice or more
                        for(var sameCard : warChest ){
                            if (sameCard.getValue() == count) {warLords.add(warChest.indexOf(sameCard)); } // index count to send to toWar() to pull the right players for war
                        }
                    }
                    // this gets the player not the card from warchest ->>System.out.println("the winner is: " + playerArrayList.get(cardTracker).); // check
                } // determining who wins round or if there will be war

                int pCount = 0;
                String playersToWar = "Players: ";

                for (Integer whichPlayer : warLords) { // gathering up the players to go to war
                    pCount += 1;
                    playersToWar += (whichPlayer + 1) + ", "; // adding 1 to print correct player, prints correct index regardless
                }// gathering the players to go to war, if any
                if (pCount > 1){  // there >2 player in whichPlayer, going to war
                    System.out.println("Index's of players going to war: " +  warLords ); // correct player index is used <<--- correct index is used but it mismatches player number because we are using an index here, instead of a counter
                    playersToWar += "are going to war";
                    System.out.println(playersToWar);
                    updatedPlayerList = makeWar(playerArrayList, warLords, warChest, verbosity);
                    //makeWar(playerArrayList, warLords, warChest, verbosity);

                }else { // one player won the round
                    int whichPlayer = cardTracker+1; // index starts counting @ 0
                    System.out.println("the winning card in war chest is card: " + warChest.get(cardTracker).toString()); // check the index of winning card in warchest
                    System.out.println("the winner is player list: " + playerArrayList.get(cardTracker).toString()); // check the index of winning card in player list
                    System.out.println("the winner is player: " + whichPlayer); // check the index of winning card in player list
                    updatedPlayerList = giveCards(playerArrayList, cardTracker, warChest); // call to give war chest to winner.
                } // one player won the round and is getting war chest

            } // no war. determining if winner or war
            case 1 -> { // going to war

                for( var card : warChest){      // for every card in deck
                    if (card.getValue() > count){ // if the value of the card is larger than the last
                        count = card.getValue(); // count takes up the value of the card
                        cardTracker = warChest.indexOf(card);   // card tracker value becomes the index of card that has highest and is used for winner in no-war
                        warLords.clear(); // prevents from accidentally adding wrong cards to war[?]
                    } else if (card.getValue() == count){   // if the
                        //warLords.addFirst(playerTracker); // index count to send to toWar() to pull the right players for war
                        warLords.clear();   // need to clear first. without, if first card is already part of the highest, then
                        // then (card.getValue() > count) will never be true again and if there are three
                        // or more players going to war, warLords.add(warChest.indexOf(sameCard) will
                        // fire twice or more
                        for(var sameCard : warChest ){
                            if (sameCard.getValue() == count) {warLords.add(warChest.indexOf(sameCard)); } // index count to send to toWar() to pull the right players for war
                        }
                    }
                    // this gets the player not the card from warchest ->>System.out.println("the winner is: " + playerArrayList.get(cardTracker).); // check
                } // determining who wins round or if there will be war


            }
        }

        return updatedPlayerList; // erase
    }

    //********************************************* END **************************************************************/

    //******************************************** START *************************************************************
    private ArrayList<Player> WarChest(ArrayList<Player> playerArrayList, ArrayList<Card> warChest, int verbosity, int warTime){
        // conatins drawn cars
        ArrayList<Player> updatedPlayerList = new ArrayList<>();
        int cardTracker = 0;    // keeps track of winning card------------------------------------------------------------------------------------------not used
        int count = 0;          // keeps track of largest card
        ArrayList<Integer> warLords = new ArrayList<>();  // indexes of players going to war
         // no war. determining if winner or war
        // Switch is useful and clear for enumerated values, not for comparisons
        // and switch does not support logic expressions so i will use if's
        for( var card : warChest){      // for every card in deck
            if (card.getValue() > count){ // if the value of the card is larger than the last
                count = card.getValue(); // count takes up the value of the card
                cardTracker = warChest.indexOf(card);   // card tracker value becomes the index of card that has highest and is used for winner in no-war

                warLords.clear(); // prevents from accidentally adding wrong cards to war[?]
            } else if (card.getValue() == count){   // if the
                //warLords.addFirst(playerTracker); // index count to send to toWar() to pull the right players for war
                warLords.clear();   // need to clear first. without, if first card is already part of the highest, then
                // then (card.getValue() > count) will never be true again and if there are three
                // or more players going to war, warLords.add(warChest.indexOf(sameCard) will
                // fire twice or more
                for(var sameCard : warChest ){
                    if (sameCard.getValue() == count) {warLords.add(warChest.indexOf(sameCard)); } // index count to send to toWar() to pull the right players for war
                }
            }
            // this gets the player not the card from warchest ->>System.out.println("the winner is: " + playerArrayList.get(cardTracker).); // check
        } // determining who wins round or if there will be war



        int pCount = 0;
        String playersToWar = "Players: ";

        for (Integer whichPlayer : warLords) { // gathering up the players to go to war
            pCount += 1;
            playersToWar += (whichPlayer + 1) + ", "; // adding 1 to print correct player, prints correct index regardless
            //playersToWar += (playerArrayList.get(whichPlayer).getPlayerNumber()) + ", "; // adding 1 to print correct player, prints correct index regardless
        }// gathering the players to go to war, if any
        if (pCount > 1){  // there >2 player in whichPlayer, going to war
            switch(warTime){
                case 0->{
                    warCount +=1;
                    System.out.println("Index's of players going to war: " +  warLords ); // correct player index is used <<--- correct index is used but it mismatches player number because we are using an index here, instead of a counter
                    playersToWar += "are going to war";
                    System.out.println(playersToWar);
                    updatedPlayerList = makeWar(playerArrayList, warLords, warChest, verbosity);
                    //makeWar(playerArrayList, warLords, warChest, verbosity);
                }
                case 1->{
                    doubleWarCount += 1;
                    System.out.println("Index's of players going to double war: " +  warLords ); // correct player index is used <<--- correct index is used but it mismatches player number because we are using an index here, instead of a counter
                    playersToWar += "are going to double war";
                    System.out.println(playersToWar);
                    updatedPlayerList = makeWar(playerArrayList, warLords, warChest, verbosity);
                }
            }

        }else { // one player won the round
            switch (warTime){
                case 0 -> {
                    int whichPlayer = cardTracker+1; // index starts counting @ 0
                    System.out.println("the winning card in war chest is card: " + warChest.get(cardTracker).toString()); // check the index of winning card in warchest
                    System.out.println("the winner is player list: " + playerArrayList.get(cardTracker).toString()); // check the index of winning card in player list
                    System.out.println("the winner is player: " + whichPlayer); // check the index of winning card in player list
                    System.out.println("testing: " + playerArrayList.get(whichPlayer).getPlayerNumber()); // check testing getplayernumber
                    updatedPlayerList = giveCards(playerArrayList, cardTracker, warChest); // call to give war chest to winner.
                }
                case 1 -> {
                    doubleWarCount += 1;
                    int whichPlayer = cardTracker+1; // index starts counting @ 0
                    System.out.println("the winning card in  double-war chest is card: " + warChest.get(cardTracker).toString()); // check the index of winning card in warchest
                    // System.out.println("the double-war winner is player list: " + playerArrayList.get(cardTracker).toString()); // will not use as player may have no cards after play
                    System.out.println("the double-war winner is player: " + whichPlayer); // check the index of winning card in player list
                    updatedPlayerList = giveCards(playerArrayList, cardTracker, warChest); // call to give war chest to winner.

                }
            }
        } // one player won the round and is getting war chest



        return updatedPlayerList; // erase
    }

    //********************************************* END **************************************************************





    //******************************************** START *************************************************************
    private ArrayList<Player> makeWarSwitchExample(ArrayList<Player> playerArrayList, ArrayList<Integer> warLords, int verbosity){
        /*
        method body
         */
        return
        switch (verbosity){
            case 0 -> playerArrayList;
            case 1 -> playerArrayList;
            default -> throw new IllegalStateException("Unexpected value: " + verbosity);
        };//return playerArrayList;
    }

    //********************************************* END **************************************************************
    //******************************************** START *************************************************************
    private ArrayList<Player> makeWar(ArrayList<Player> playerArrayList, ArrayList<Integer> warLords, ArrayList<Card> warChest, int verbosity){

        ArrayList<Player> warLordList = new ArrayList<>();
        for(var index: warLords){ // for every value in warLords
            warLordList.add(playerArrayList.get(index)); // and the player at index: index, to list
        }
        for(var lord : warLordList){
            System.out.println("makeWar() test: player " + (lord.getPlayerNumber() +1) + "\n");   // i dont know why i sometimes need the +1 and sometimes i dont
        }
        //System.out.println(warLordList); check that players are added
        //for(var warlord: warLordList){
        //    System.out.println(warlord.toString() + "\n"); // check to make sure correct players are added to list
        //}
        ArrayList<Player> afterMath = DrawCards(warLordList, verbosity, true);

        return playerArrayList; ///?
    }

    //********************************************* END **************************************************************



    //******************************************** START *************************************************************
    private ArrayList<Player> giveCards(ArrayList<Player> playerList, int winner, ArrayList<Card> warChest){
        // System.out.println("before adding:\n" + playerList); // check what lists look like before
        // int counter = 0;
        for(var card : warChest){   // for every card
            //playerList.get(winner).bottomAddCard(warChest.remove(counter)); // doesnt work for some reason
            playerList.get(winner).bottomAddCard(card);   // add that card to player list
            // counter += 1;
        }
        // System.out.println("after adding:\n" + playerList); // check what lists look like after
        return playerList;
    }

    //********************************************* END **************************************************************




    //******************************************** START *************************************************************
    // report()
    // summary
    // who won?
    // how many steps?
    // # of wars, double wars, etc
    private void report(ArrayList<Player> playerList , int verbiageSelect){
        String report; // = "Summary report:\n";
        switch(verbiageSelect){
            case 1 -> { // case 1 displays all verbiage
                String joker = "";
                if (jokerPick)  joker += "YES";
                else joker += "NO";
                verbType += "Print everything";
                report = String.format("""
                ************ Summary report ************
                    Verbiage     :  %s
                    Total players:  %d
                    Games played :  %d
                    Joker used   :  %s
                    %s
                ********** End Summary report **********""", verbType, playerNum, gamesNum, joker, startingDeck1);
                // full verbiage summary string

                System.out.println(report); // print full report string
                // System.out.println(startingDeck1); // prints what players started with
            }
            case 2 -> {
                firstDeckDeal(playerList, verbiageSelect);
                System.out.println(startingDeck2);
            }
        }
    }

    //********************************************* END **************************************************************



    //#################################################################################################################################################

    public static void main (String[] args){
        // check creation of deck for debugging
        //Deck newDeck = new Deck();
        //System.out.println(newDeck);

        War warGame = new War();
        // System.out.println("\n");
        // System.out.println("playerNum = " + warGame.playerNum);
        // System.out.println("verbosity = " + warGame.verbosity);
        // System.out.println("gamesNum  = " + warGame.gamesNum);
        // System.out.println("jokerPick = " + warGame.jokerPick);

        // warGame.report(warGame.verbosity); // check verbiage print
        // warGame.initialize();
        // System.out.println(warGame.listSize); <<-- dont do this, this just prints the variable itself instead of what it holds

    }
}