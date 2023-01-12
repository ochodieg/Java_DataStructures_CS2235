package MidProjectTest;
// go through loops and finds places
// where you can use an iterator
//import jdk.swing.interop.SwingInterOpUtils;

import java.util.*;


public class War_rev2 {
    private final int       playerNum;
    private final int       verbosity;
    private final int       gamesNum;
    private final boolean   jokerPick;
    //private int playerCount;


    public String initialDeckStats = ""; //<-- these need to be initialized otherwise they print a null, before printing what they were assigned???
    // should never be accessed when verbiage = 4. Clear at end of print stage

    //public String startingDeck2 = "";
    //public String verbType = "";


    public int rounds = 1; // updates every round loop (not x2 war or x3 war...etc)
    public int allSteps = 0;
    public int warCount = 0; // counts regular wars
    public int higherWars = 0; // keeps track of all wars higher than trip. war.
    public int totalWars = 0;  // keeps track of ALL wars // will add all wars up in the end  // updated every war (regardless of X2 X3.. etc. maked comparsion like:
    // if(warCount%(doubleWar + tripleWar) != 1) { (warCount - doubleWar + tripleWar) wars were someKind? of war

    public int doubleWarCount = 0; // counts every double war
    public String tempDoubleWarReport=""; //updates (concat to but at the end, where everything prints, clear after printing
    public String DoubleWarReport = ""; // concats

    public int TripleWarCount =0;
    public String tempTripleWarReport = ""; // same as above
    public String TripleWarReport = ""; // same as above


    public String warReport =""; // concats to. every time a war starts, then when war ends (may need to clear every round
    public String winnersList =""; //concat to. every round // clear every round?
    public String gameWinner = ""; // concat update at the end of game loop
    public String playersRemoved = ""; // concat to, every time player removed

    public String endRoundPhase = ""; // updates every end-round phase. used to display "player removed" (if any) and round summary
    public String zeroCheckRemoval = ""; // updates in playerCheck if player was removed // may need to keep as it may be
    // used multiple times in 1 loop (doublewars...etc)
    public String roundChange =""; // updates every round that isnt a double, triple, etc, war
    // only update in WarChest_NoPriorWar()
    public String warChange = ""; // same as roundChange for WarChest_PriorWar()

    public String tempWarlordPrep = "";
    public String warLordPrep = "";






    public War_rev2() {
        Scanner userInput = new Scanner(System.in);
        jokerPick = this.getJoker(userInput);
        playerNum = this.getPlayers(userInput,jokerPick);
        verbosity = this.getVerbosity(userInput);
        gamesNum = this.getGames(userInput);
        ArrayList<Player> playerArrayList = this.initialize(jokerPick, playerNum, verbosity);
        //System.out.println("starting deck: \n" + this.initialDeckStats); // check starting stats
        //playerArrayList = this.DrawCards(playerArrayList, null, verbosity,false,rounds,0); // checking if drawcards
        //playerDeckState(playerArrayList,verbosity); // making sure drawcards gets correct stuff and deck state werks
        //System.out.println(rounds);

        while (playerArrayList.size()>0){
            playerArrayList = DrawCards(playerArrayList,null,verbosity,false, 0, 0);
        }



        System.out.println(playersRemoved);
        System.out.println(allSteps);
    }
    private int getPlayers(Scanner askAmount, boolean jokerPick){ // private - calling it from within class instance
        int limiter;
        String limit = "";
        if (jokerPick){
            limiter = 54;
            limit = "** Joker has been selected: Player limit is 54 **\n" +
                    "       (please enter at least 2 players)\n>> ";
        }
        else {
            limiter = 52;
            limit = "** Joker will not be used: Player limit is 52 **\n" +
                    "       (please enter at least 2 players)\n>> ";
        }
        System.out.println("    >>> How many players will there be? <<<");
        int players;
        do {
             System.out.print(limit);
            while (!askAmount.hasNextInt()) {
                String input = askAmount.next();
                System.out.printf("      *** \"%s\" is not a valid amount ***\n", input);
                System.out.print("     *** Please enter at least 2 players ***\n>> ");
            }
            players = askAmount.nextInt();
        } while (players < 2 || players > limiter);
        System.out.printf("        -- You have entered %d players --\n\n", players);
        //playerNum = players;
        //playerCount += players;
        //playerNum.close(); // dont close scanner until finally done
        return players;}// gets number of players from user
    private int getVerbosity(Scanner verbiage){
        // Scanner verbiage = new Scanner(System.in);
        String verbiageSelect = """
                    
                       >>> How verbose should this program be? <<<
                    1) Print everything.
                    2) Print important metrics.
                    3) Print summary only.""";
        System.out.println(verbiageSelect);

        int number;
        do {
            System.out.print(" (Please select a given option)\n>> ");
            while (!verbiage.hasNextInt()) {
                String input = verbiage.next();
                System.out.printf("*** \"%s\" is not a valid option ***\n>> ", input);
            }
            number = verbiage.nextInt();
        } while (number < 1 || number > 3);

        System.out.printf("            -- Verbosity select: %d --\n\n", number);
        // System.out.println("getVerbosity check " + number); // check
        return number;}
    private int getGames(Scanner gameAmount){
        //Scanner gameAmount = new Scanner(System.in);

        System.out.println("\n   >>> How many games will be simulated? <<<");
        int games;
        do {
            System.out.print("        (Please enter at least 1 game)\n>> ");
            while (!gameAmount.hasNextInt()) {
                String input = gameAmount.next();
                System.out.printf("*** \"%s\" is not a valid amount ***\n", input);
                System.out.print("*** Please enter at least 1 game ***\n>> ");
            }
            games = gameAmount.nextInt();
        } while (games < 1 );
        System.out.printf("-- You have entered %d games --\n\n", games);
        // System.out.println("getGames check " + games); // check
        //gameAmount.close(); // dont close scanner until finally done
        return games;}
    private boolean getJoker(Scanner askForJ){
        // Scanner userInput = new Scanner(System.in);
        // String askForJ = "";
        boolean joker;
        String jokerSelect = """
                
                      >>> Will this game use Jokers? <<<
                <y> yes
                <N> No
                >>  """;
        System.out.print(jokerSelect);
        String choice = askForJ.next().trim().toLowerCase();
        while ( !(choice.equals("yes") || (choice.equals("y"))) &&
                !(choice.equals("no") || (choice.equals("n")))
        ){
            System.out.println("*** please select a valid option ***\n>> ");
            choice = askForJ.next();
        }
        switch (choice){
            case "yes", "y" -> {    // switch case yes or y turns joker into true
                joker = true;
                System.out.println("    -- Jokers will be used (54 card deck) --\n\n");
            }
            case "no", "n"  -> {    // switch case no or n turns joker into true
                joker = false;
                System.out.println("   -- Jokers will not be used (52 card deck) --\n\n");
            }
            default -> throw new IllegalStateException("Unexpected value: " + choice);
            // added throw since i initiated joker up top but i didn't want it to give a false value for whatever
            // reason, I'd rather get an error msg to help troubleshoot if need be
        }
        return joker;
    }

    //******************************************** START *************************************************************
    private ArrayList<Player> initialize
    (boolean joker, int chosenPlayerAmount, int verbiageSelect){
        // create the deck and deal it to the players
        Deck newDeck = new Deck(joker); // deck is made
        newDeck.shuffle();  // deck is shuffled
        ArrayList<Player> playerList = new ArrayList<>(); // make list to add players to

        for (int i = 0; i < chosenPlayerAmount; i++) { // add all players to player list
            playerList.add(i, new Player(i+1));
        }
        /* for(var player : playerList){
            System.out.println("player " + player.getPlayerNumber() + "\n");
        } // check */
        int totalCards = newDeck.size();
        int totalPlayers = playerList.size();
        int modulus = totalCards % totalPlayers; // determines if deck will be split evenly among players
        if (modulus == 0){
            for (var player : playerList){
                for (int i = 0; i < (totalCards/totalPlayers); i++)
                    player.bottomAddCard(newDeck.dealCard());
            }
        }         // deals hand when cards are split evenly
        else if (modulus != 0){
            for (var player : playerList){
                for (int i = 0; i < (totalCards/totalPlayers); i++)
                    player.bottomAddCard(newDeck.dealCard());
            }
            for (int i = 0; i < modulus; i++) playerList.get(i).bottomAddCard(newDeck.dealCard());
        }   // deals hand when cards aren't split evenly

        switch (verbiageSelect){
            case 1 ->{
                System.out.println("============ Starting metrics: ============");
                firstDeckDeal(playerList, 1); // prints initial deck depending on verbiage select
                System.out.println("===========================================");
            } //  calls firstDeckDeal() to create full stats and initialStats instance variable
            case 2 ->{
                System.out.println("============ Starting metrics: ============");
                firstDeckDeal(playerList, 2); // prints initial deck depending on verbiage select
                System.out.println("===========================================");
            } // calls firstDeckDeal() to create partial stats and initialStats instance variable
            case 3 ->{
                firstDeckDeal(playerList, 3);
            } // calls firstDeckDeal() to  only create initialStats instance variable
        }
        return playerList;
    } // initializes decks and starting stats
    //********************************************* END **************************************************************

    //******************************************** START *************************************************************
    // composes string that lists the cards players were first dealt
    private void firstDeckDeal(ArrayList<Player> playerList, int verbiageSelect){
        String firstDeal = "";

        switch (verbiageSelect){
            case 1 -> {
                for (var player : playerList){
                    int cCount = player.size();
                    //firstDeal += "player " + player.getPlayerNumber() + " has " + cCount + " cards:\n" +  player + "\n\n"; // check
                    firstDeal += String.format("""
                            
                            player %d has %d cards:
                            (%s)
                            """,player.getPlayerNumber(),cCount,player);
                    // startingDeck1 += "player " + pCount + " started with " + cCount + " cards\n";
                    initialDeckStats += String.format("""
                            
                            player %d started with %d cards
                            """, player.getPlayerNumber(),cCount);
                }
                System.out.println(firstDeal);
            } // verbiage 1 prints full starting stats and creates an initialStates instance variable
            case 2 -> {
                for (var player : playerList){
                    int cCount = player.size();
                    firstDeal += String.format("""
                            
                            player %d has %d cards:
                            """,player.getPlayerNumber(),cCount);
                    initialDeckStats += String.format("""
                            
                            player %d started with %d cards
                            """, player.getPlayerNumber(),cCount);
                }
                System.out.println(firstDeal);
            } // verbiage 2 prints some starting stats and creates an initialStates instance variable
            case 3 -> {
                for (var player : playerList){
                    int cCount = player.size();
                    initialDeckStats += String.format("""
                            
                            player %d started with %d cards
                            """, player.getPlayerNumber(),cCount);
                }
            } // verbiage 3 only creates an initialStates instance variable
        }
    } // creates starting stats
    //********************************************* END **************************************************************

    //******************************************** START *************************************************************
    private String playerDeckState(ArrayList<Player> playerList, int verbiageSelect){
        //int pCount = 0;
        String firstDeal = "";
        switch (verbiageSelect){
            case 1 -> {
                for (var player : playerList){
                    int cCount = player.size();
                    int playerNum = player.getPlayerNumber();
                    firstDeal += String.format("""
                            
                            >> player %d has %d cards:
                            %s
                            """, playerNum, cCount, player);
                    //firstDeal += "player " + pCount + " has " + cCount + " cards: " + player + "\n"; // check
                    //startingDeck1 += "player " + pCount + " started with " + cCount + " cards\n";
                    //pCount += 1;
                }
                //System.out.println(firstDeal);
            }
            case 2 -> {
                for (var player : playerList){
                    int cCount = player.size();
                    int playerNum = player.getPlayerNumber();
                    firstDeal += String.format("""
                            
                            >> player %d has %d cards
                            """, playerNum, cCount);
                    // firstDeal += "player shit " + pCount + " has " + cCount + " cards\n"; // check
                    //startingDeck2 += "     player " + pCount + " started with " + cCount + " cards\n";
                    //pCount += 1;
                }
                //System.out.println(firstDeal);
            }
            //case 3 -> {
            //for (var player : playerList) {
            //int cCount = player.size();
            //}
            //}
        }
        return firstDeal;

    } // used every round to print
    //state of the decks
    // only called during verbosity 1-3
    //********************************************* END **************************************************************

    //******************************************** START *************************************************************
//    private String warReport(){
//
//    }
    //********************************************* END **************************************************************
    //******************************************** START *************************************************************
    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    // should only fire if verbosity 1-3
    // END WAR REPORT
    private String toWarReport(ArrayList<Integer> warLordsCount, int verbosity, Card fireStarter){
        // String warPlayerIndices = String.format("%s",warLordsCount); // used for debug
        // no longer works since using iterator. would only work if we -1 from every number
        String playersToWarReport ="";

        switch (verbosity){
            case 1,2 ->{
                int pCount = 0;
                String playersToWar = "Players: ";

                Iterator<Integer> iterator = warLordsCount.iterator();

                while (iterator.hasNext()){
                    int whichPlayer = iterator.next();
                    pCount += 1;
                    playersToWar += (whichPlayer); // adding 1 to print correct player, prints correct index regardless
                    if (iterator.hasNext()) playersToWar += ", ";
                }
//                for (Integer whichPlayer : warLordsCount) { // gathering up the players to go to war
//                    pCount += 1;
//                    playersToWar += (whichPlayer); // adding 1 to print correct player, prints correct index regardless
//                    if (iterator.hasNext()) playersToWar += ", ";
//                    //playersToWar += (playerArrayList.get(whichPlayer).getPlayerNumber()) + ", "; // adding 1 to print correct player, prints correct index regardless
//                }// gathering the players to go to war, if any
                String amount = String.format("%d players tied and are going to war", pCount);
                warReport += String.format("%s went to war in round %d\n", playersToWar, rounds);
                playersToWar += "\nhave the highest cards and are going to war";

                switch (verbosity){
                    case 1->{
                        playersToWarReport = String.format("""
                        *************** WAR START ****************
                        %s
                        Card: %s, Started the war
                        
                        %s
                        ******************************************
                        """,amount,fireStarter, playersToWar);
                    }
                    case 2->{

                        playersToWarReport = String.format("""
                        *************** WAR START ****************
                        %s
                        ******************************************
                        """,amount);
                    }
                }

            }
            case 3->{
                int pCount = 0;
                for (Integer whichPlayer : warLordsCount) { // gathering up the players to go to war
                    pCount += 1; }
                warReport += String.format("%s went to war in round %d\n", pCount, rounds);
            }
        }
        return playersToWarReport;
    } // used to print and add strings
    // of what happened in war
    //********************************************* END **************************************************************

    //******************************************** START *************************************************************
    private void play(ArrayList<Player> playerList, int gamesNum, int verbosity, boolean jokerPick){
        int allCardAmount;
        if(jokerPick) allCardAmount = 54;
        else allCardAmount = 52;

        boolean playerHasNotWon = true;
        while (playerHasNotWon){

            if (playerList.size() == 1) playerHasNotWon = false;
        }
    } // $$$$$$$$$$$$$$ used to initiate and run game
    //********************************************* END **************************************************************

    //******************************************** START *************************************************************
    // drawcards
    private ArrayList<Player> DrawCards
    (ArrayList<Player> playerArrayList, Deck ifWarChest, int verbosity, boolean warTime, int round, int warTracker ){
        // need to know which players are going to war instead of making and passing in a new list that has player
        // that way i can just pull the correct players for war from 1 list

        // draws players first card and gives all to the highest player
        // contain all cards in deqeue?
        // is it better to do that?

        //IF THERE IS NO WAR, THEN NO INDEXES ARE PASSES


        // start of a round
        if(!warTime) { // round start
            allSteps += 1;
            //Deck poo = new Deck();
            // ArrayList<Card> warChest = new ArrayList<>();   // create war chest
            Deck warChest = new Deck();
            //ArrayList<Integer> pIndexesNoWar = new ArrayList<>(); // keeps track of player indexes
            //pIndexes = pIndexesNoWar;
            for (var player : playerArrayList) {    // for every player
                warChest.addCard(player.dealCard());    // take one card from deck and place it in war chest
                //pIndexesNoWar.add(player.getPlayerNumber()); // store playerArrayList index of current player
            }// every player adds a card to war chest and their index is stores in pIndexesNoWar
            //rounds += 1;
            //playerArrayList = WarChest_NoPriorWar(playerArrayList, warChest, verbosity);
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ print steps here $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
            switch (verbosity){ // $$$ change this, case 3 should still add to final display string, case 4 shouldnt do
                // anything with strings $$$$
                case 1,2,3 -> {
                    //System.out.println("check1 " + playerArrayList);
                    playerArrayList = WarChest_NoPriorWar(playerArrayList, warChest, verbosity);
                    //System.out.println("check2 " + playerArrayList);
                    switch(verbosity){
                        case 1 ->{
                            String summary = warChange += roundChange += tempWarlordPrep += zeroCheckRemoval; // &&&& place drawcards with war between zerocheck and roundchange
                            System.out.printf("""
                                    
                                    ====================== Round: %d ======================
                                    *** Players draw top cards to war-chest ***
                                    
                                    War-Chest:
                                    (%s)
                                    %s
                                    """, round, warChest,summary);
                            //System.out.println(roundChange); //&&&&&&
                            // was no war

                            //System.out.println(warChange); //$$$$$$$ might need to clear this after calling warchest and there<---war start
                            //System.out.println(zeroCheckRemoval); // only fires when a war starts
                            // System.out.println(playersRemoved);
                        }
                        case 2 ->{
                            // System.out.println("war chest:\n" + warChest); // check print war chest
                            System.out.printf("""
                                    
                                    ====================== Round: %d ======================
                                    """,round);
                            System.out.println(roundChange);
                            //System.out.println(zeroCheckRemoval);
                            warChange += zeroCheckRemoval;
                            System.out.println(warChange);
                            //ArrayList<Player> peaceUpdatedList; // create updated list
                            // WarChest() will give winning player cards
                        }
                    } // prints draw phase of round
                    // may need to make a check here if there is only one player
//                    for (var player : playerArrayList){
//                        if (player.size()<=0){
//                            int playerNum = player.getPlayerNumber();

                    allSteps += 1;
                    Iterator<Player> iterator = playerArrayList.iterator();
                    while (iterator.hasNext()){
                        Player playerToRemove = iterator.next();
                        if (playerToRemove.size() <=0){
                            int playerNum = playerToRemove.getPlayerNumber();

                            switch(verbosity){
                                case 1 ->{
                                    endRoundPhase += String.format("""
                                    ******************************************
                                                 PLAYER: %d REMOVED
                                    ******************************************
                                    """,playerNum);
                                    playersRemoved += String.format("Player %d Removed in Round %d\n",playerNum,round);
                                }
                                case 2 ->{
                                    endRoundPhase += String.format("""
                                    ******************************************
                                                  PLAYER REMOVED
                                    ******************************************
                                    """);
                                    playersRemoved += String.format("Player %d Removed in Round %d\n",playerNum,round);
                                }
                                case 3->{
                                    playersRemoved += String.format("Player %d Removed in Round %d\n",playerNum,round);
                                }
                            } // how do you want to display removal
                            iterator.remove();
                        }
                    }

                    endRoundPhase += String.format("""
                            ------------------------------------------
                            -------------- Player Decks --------------
                            %s
                            ------------------------------------------
                            ------------------------------------------""",playerDeckState(playerArrayList,verbosity));
                    switch (verbosity){
                        case 1,2->{
                            //System.out.println(playerDeckState(playerArrayList,verbosity)); // gonna have to change this when i make stuff to print war
                            System.out.println(endRoundPhase);
                            System.out.println("====================== End Round ======================\n");
                        }
                    }
//                            playerArrayList.remove(player.getPlayerNumber());
//                        }
//                    }

                    clear();
                }// if verbosity 1-3 is selected, print end round and player removed, and add to
                // summary string or just add to string
                case 4->{
                    playerArrayList = WarChest_NoPriorWar(playerArrayList, warChest, verbosity);
                    // have to use iterator when removing player, thus i can't use enhanced for loop :(
                    //playerArrayList.removeIf(player -> player.size() <= 0);

                    allSteps += 1;
                    Iterator<Player> iterator = playerArrayList.iterator();
                    while (iterator.hasNext()){
                        Player playerToRemove = iterator.next();
                        if (playerToRemove.size() <=0){
                            //int playerNum = playerToRemove.getPlayerNumber();
                            iterator.remove();
                        }
                    }
                } // remove player. no printing/ no strings
            } // remove player and make/print strings if selected
//            switch(verbosity){
//                case 1 ->{
//                    String cardDraw = "";
//
//                    for( var index : pIndexesNoWar){
//                        cardDraw += String.format("""
//                                """);
//
//                    }
//                    System.out.printf("""
//
//                            ====================== Round: %d ======================
//                            *** Players drew top cards to war-chest ***
//
//                            War-Chest:
//                            (%s)
//                            """, round, warChest);
//                }
//                case 2 ->{
//                    //System.out.println("war chest:\n" + warChest); // check print war chest
//                    System.out.printf("""
//
//                            ====================== Round: %d ======================
//                            """,round);
//                    //ArrayList<Player> peaceUpdatedList; // create updated list
//                    // WarChest() will give winning player cards
//                }
//            } // prints draw phase of round
//
//
//            playerArrayList = WarChest_NoPriorWar(playerArrayList, warChest, verbosity, 0, pIndexesNoWar);
//            // update playerList
//
//            playerArrayList.removeIf(player -> player.size() <= 0);
//            // parse list and remove player if they have no cards

            rounds += 1;
            return playerArrayList; } // place cards in war chest




        // start of a war
        else {
            // the incoming playerlist will only have warlords
            allSteps += 1;
            ArrayList<Integer> warLordNumber = new ArrayList<>(); // used to keep track of which cards belong to
            // who, in new warchest. should be the same size as warchest <- wut?
            Deck newWarChest = new Deck();

            switch (verbosity){
                case 1,2,3->{
                    String toWarlordPrep = "";
                    for (var player : playerArrayList){
                        int pNum = player.getPlayerNumber();
                        if(player.size() >= 4){
                            for (int i = 0; i < 4; i++){
                                newWarChest.addCard(player.dealCard());
                                warLordNumber.add(player.getPlayerNumber());
                            }
                            //toWarlordPrep += "player: " + pNum + " added 4 cards\n";
                            switch(verbosity){
                                case 1->{
                                    toWarlordPrep += String.format("-player: %d added 4 cards to new chest\n", pNum);
                                    warLordPrep += String.format("""
                                            - player: %d added 4 cards to
                                            new war chest in round %d
                                            """, pNum, rounds);
                                }
                                case 2->{
                                    toWarlordPrep += String.format("-player: %d added cards\n", pNum);
                                    warLordPrep += String.format("""
                                            - player: %d added 4 cards to
                                            new war chest in round %d
                                            """, pNum, rounds);
                                }
                                case 3->{
                                    warLordPrep += String.format("""
                                            - player: %d added 4 cards to
                                            new war chest in round %d
                                            """, pNum, rounds);
                                }
                            }

                        }
                        else if(player.size() < 4){
                            int cardAmount = player.size();
                            for (int i = 0; i< cardAmount; i++){
                                newWarChest.addCard(player.dealCard());
                                warLordNumber.add(player.getPlayerNumber());
                            }
                            //toWarlordPrep += "player: " + pNum + " added " + cardAmount + " cards\n";
                            switch(verbosity){
                                case 1->{
                                    toWarlordPrep += String.format("-player: %d added %d cards to new chest\n", pNum, cardAmount);
                                    warLordPrep += String.format("""
                                            - player: %d added %d cards to
                                            new war chest in round %d
                                            """, pNum,cardAmount, rounds);
                                }
                                case 2->{
                                    toWarlordPrep += String.format("-player: %d added cards\n", pNum);
                                    warLordPrep += String.format("""
                                            - player: %d added %d cards to
                                            new war chest in round %d
                                            """, pNum,cardAmount, rounds);
                                }
                                case 3->{
                                    warLordPrep += String.format("""
                                            - player: %d added %d cards to 
                                            new war chest in round %d
                                            """, pNum,cardAmount, rounds);
                                }
                            }

                        }
                    } // warLord places 4 or all of their cards into new warchest
                    // deals with strings
                    tempWarlordPrep = toWarlordPrep;
                }
                case 4 ->{
                    for (var player : playerArrayList){ // for every warlord
                        if(player.size() >= 4){ // if they have over 4 cards
                            for (int i = 0; i < 4; i++){ // place 4 cards into warchest
                                newWarChest.addCard(player.dealCard());
                                warLordNumber.add(player.getPlayerNumber()); // place warlords player # into list
                            }
                        } // if player has > 4 cards, place 4 cards into chest
                        else if(player.size() < 4){ // if player has less than four cards
                            int cardAmount = player.size(); // need to make this variable
                            // to use in for loop. if player.size is used or if iterator or for-each loop is used:
                            // the condition will change every loop and we wont get all the cards out
                            for (int i = 0; i< cardAmount; i++){ // player places all their cards into chest
                                newWarChest.addCard(player.dealCard());
                                warLordNumber.add(player.getPlayerNumber()); // place warlords player # into list
                            }
                        }
                    } // warLord places 4 or all of their cards into new warchest
                }
            } // draws 4 or all of warlords cards and places them in newWarChest
            // adds those warLords player-number to warLordNumber, to keep track of warLords' cards
            // prints and concats String depending on switch case

            // System.out.println("check " + newWarChest);
            // System.out.println("check " + warLordNumber);
            ArrayList<Deck> listOfChests = new ArrayList<>();
            listOfChests.add(ifWarChest);
            listOfChests.add(newWarChest);


            playerArrayList =
                    WarChestLoop
                            (playerArrayList, warLordNumber, newWarChest, listOfChests, verbosity, warTracker, rounds);

            // rounds += 1; // dont need to update round during doubleWar
            return  playerArrayList;
        }
    }


    //********************************************* END **************************************************************



    /* come back to this
    //******************************************** START *************************************************************
    private ArrayList<Player> dequeWarChest(ArrayList<Player> playerArrayList, ArrayList<Card> warChest, int verbosity, int warTime, ArrayList<Integer> pIndexes){
        // conatins drawn cars
        ArrayList<Player> updatedPlayerList = new ArrayList<>();


        int cardTracker = 0;    // keeps track of winning card------------------------------------------------------------------------------------------not used
        int count = 0;          // keeps track of largest card
        ArrayList<Integer> warLordsCount = new ArrayList<>();  // indexes of players going to war

        // no war. determining if winner or war
        // Switch is useful and clear for enumerated values, not for comparisons
        // and switch does not support logic expressions so i will use if's
        for( var card : warChest){      // for every card in deck
            if (card.getValue() > count){ // if the value of the card is larger than the last
                count = card.getValue(); // count takes up the value of the card
                cardTracker = warChest.indexOf(card);   // card tracker value becomes the index of card that has highest and is used for winner in no-war

                warLordsCount.clear(); // prevents from accidentally adding wrong cards to war[?]
            } else if (card.getValue() == count){   // if the
                //warLordsCount.addFirst(playerTracker); // index count to send to toWar() to pull the right players for war
                warLordsCount.clear();   // need to clear first. without, if first card is already part of the highest, then
                // then (card.getValue() > count) will never be true again and if there are three
                // or more players going to war, warLordsCount.add(warChest.indexOf(sameCard) will
                // fire twice or more
                for(var sameCard : warChest ){
                    if (sameCard.getValue() == count) {warLordsCount.add(warChest.indexOf(sameCard)); } // index count to send to toWar() to pull the right players for war
                    // we got to pass that warchest along to add thos cards to winner deck, too
                }
            }
            // this gets the player not the card from warchest ->>System.out.println("the winner is: " + playerArrayList.get(cardTracker).); // check
        } // determining who wins round or if there will be war

        int pCount = 0;
        String playersToWar = "Players: ";

        for (Integer whichPlayer : warLordsCount) { // gathering up the players to go to war
            pCount += 1;
            playersToWar += (whichPlayer + 1) + ", "; // adding 1 to print correct player, prints correct index regardless
            //playersToWar += (playerArrayList.get(whichPlayer).getPlayerNumber()) + ", "; // adding 1 to print correct player, prints correct index regardless
        }// gathering the players to go to war, if any
        if (pCount > 1){  // there >2 player in whichPlayer, going to war
            switch(warTime){
                case 0->{
                    warCount +=1;
                    System.out.println("Index's of players going to war: " +  warLordsCount ); // correct player index is used <<--- correct index is used but it mismatches player number because we are using an index here, instead of a counter
                    playersToWar += "are going to war";
                    System.out.println(playersToWar);
                    updatedPlayerList = makeWar(playerArrayList, warLordsCount, warChest, verbosity);
                    //makeWar(playerArrayList, warLordsCount, warChest, verbosity);
                }
                case 1->{
                    doubleWarCount += 1;
                    System.out.println("Index's of players going to double war: " +  warLordsCount ); // correct player index is used <<--- correct index is used but it mismatches player number because we are using an index here, instead of a counter
                    playersToWar += "are going to double war";
                    System.out.println(playersToWar);
                    updatedPlayerList = makeWar(playerArrayList, warLordsCount, warChest, verbosity);
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

    //********************************************* END **************************************************************/
    //private ArrayList<Player> WarChest_NoPriorWar(ArrayList<Player> playerArrayList, Deck warChest, int verbosity, int poop){}
    //******************************************** START *************************************************************
    private ArrayList<Player> WarChest_NoPriorWar(ArrayList<Player> playerArrayList, Deck warChest, int verbosity){
        // conatins drawn cars
        //ArrayList<Player> updatedPlayerList = new ArrayList<>(); // might not need in this one
        ArrayList<Integer> warLordsCount = new ArrayList<>();  // indexes of players going to war
        allSteps += 1;

        int cardTracker = 0;    // keeps track of winning card value------------------------------------------------------------------------------------------not used
        Card CardTracker = new Card(0,"X");       // keeps track of actual card (experimental)
        int count = 0;          // keeps track of largest card
        int pCount = 0;

        // no war. determining if winner or war
        // Switch is useful and clear for enumerated values, not for comparisons
        // and switch does not support logic expressions so i will use if's
        for( var card : warChest.inDeck()){      // for every card in deck
            if (card.getValue() > count){ // if the value of the card is larger than the last
                pCount = 0;
                count = card.getValue(); // count takes up the value of the card
                //cardTracker = warChest.inDeck().indexOf(card);   // card tracker value becomes the index of card that has highest and is used for winner in no-war
                // only used if pCount is over 1 (ie going to war)
                cardTracker = warChest.getCardIndex(card); // experimental: if this doesnt work, use the one above ^^^
                // will print index error
                // this only works here because if it catches two cards of the same type,
                // it will continue to the else-if
                //CardTracker = warChest.getCard(card); // experimental
                CardTracker = card;

                warLordsCount.clear(); // prevents from accidentally adding wrong cards to war[?]
            }// if card is bigger than last card,
            else if (card.getValue() == count){   // if the
                //warLordsCount.addFirst(playerTracker); // index count to send to toWar() to pull the right players for war
                //warLordsCount.clear();   // need to clear first. without, if first card is already part of the highest, then
                // then (card.getValue() > count) will never be true again and if there are three
                // or more players going to war, warLordsCount.add(warChest.indexOf(sameCard) will
                // fire twice or more


//                for(var sameCard : warChest.inDeck() ){
//                    if (sameCard.getValue() == count) {warLordsCount.add(warChest.inDeck().indexOf(sameCard)); }
//                    // index count to send to toWar() to pull the right players for war (this war chest will have cards
//                    // from all players so indexes should match those of player list
//                    // we got to pass that warchest along to add thos cards to winner deck, too
//                }// for every card in warChest deck,
                // fire twice or more


//                if (!Objects.equals(card.getSuit(), "J")){
//                    for(var sameCard : warChest.inDeck() ){
//                        if (sameCard.getValue() == count) {warLordsCount.add(warChest.inDeck().indexOf(sameCard)); }
//                        // index count to send to toWar() to pull the right players for war (this war chest will have cards
//                        // from all players so indexes should match those of player list
//                        // we got to pass that warchest along to add thos cards to winner deck, too
//                    }
//                } else {
//                    for(var sameCard : warChest.inDeck() ){
//                        if (sameCard.getValue() == count) {
//                            warLordsCount.clear();
//                            int fIndex = warChest.inDeck().indexOf(sameCard);
//                            int lIndex = warChest.inDeck().lastIndexOf(sameCard);
//                            //if (lIndex == fIndex) {warLordsCount.add(warChest.inDeck().indexOf(sameCard));}
//                            warLordsCount.add(fIndex);
//                            warLordsCount.add(lIndex);
//                        }
//                        //int poop = sameCard.
//                        //warLordsCount.add(warChest.inDeck().indexOf(sameCard)); } // index count to send to toWar() to pull the right players for war
//                        // has issues ^^^ only works when card value and int are different (for example, a 14C and 14H
//                        // have the same value but are different cards
//                        // however, if the joker is the one we are looking for, then we are getting the first index
//                        // of 15J and because this is the only card that has the same number and suit
//                        // it will only use the first index
//                        // we got to pass that warchest along to add thos cards to winner deck, too
//                    }
//                }
                // if the value of the current card in war-chest is equal to count...
                // add the cards index number to warLordsCount

                CardTracker = card;
                if (warLordsCount.size() == 0) {
                    //int pCount = 0;


                    // $$$$$$$$$$$ this might be slower $$$$$$$$$$$


//                if (!Objects.equals(card.getSuit(), "J")){
//                    for(var sameCard : warChest.inDeck() ){
//                        if (sameCard.getValue() == count) {warLordsCount.add(warChest.inDeck().indexOf(sameCard)); }
//                        // index count to send to toWar() to pull the right players for war (this war chest will have cards
//                        // from all players so indexes should match those of player list
//                        // we got to pass that warchest along to add thos cards to winner deck, too
//
//                    }
//                } else {
//                    for(var sameCard : warChest.inDeck() ){
//                        if (sameCard.getValue() == count) {
//                            warLordsCount.clear();
//                            int fIndex = warChest.inDeck().indexOf(sameCard);
//                            int lIndex = warChest.inDeck().lastIndexOf(sameCard);
//                            //if (lIndex == fIndex) {warLordsCount.add(warChest.inDeck().indexOf(sameCard));}
//                            warLordsCount.add(fIndex);
//                            warLordsCount.add(lIndex);
//                        }
//                            //int poop = sameCard.
//                            //warLordsCount.add(warChest.inDeck().indexOf(sameCard)); } // index count to send to toWar() to pull the right players for war
//                            // has issues ^^^ only works when card value and int are different (for example, a 14C and 14H
//                            // have the same value but are different cards
//                            // however, if the joker is the one we are looking for, then we are getting the first index
//                            // of 15J and because this is the only card that has the same number and suit
//                            // it will only use the first index
//                        // we got to pass that warchest along to add thos cards to winner deck, too
//                    }
//                }

                    // $$$$$$$$$$$ this might be faster $$$$$$$$$$$
                    int i = 0;
                    //int pCount = 0;
                    for (final Iterator<Card> iterator = warChest.inDeck().iterator(); iterator.hasNext(); i++) {
                        int cardNumber = iterator.next().getValue();
                        if (cardNumber == count) {
                            int playerNumber = playerArrayList.get(i).getPlayerNumber();
                            warLordsCount.add(playerNumber);
                            pCount += 1;
                            //System.out.println(i+1);
                            // System.out.println("added player "+ playerNumber);
                        }
                    }
                }

//                Iterator<Card> iterator = warChest.inDeck().iterator();
//                //int cardNumber = 0;
//                //for(var sameCard : warChest.inDeck() ){
//                //if (!Objects.equals(card.getSuit(), "J")){
//                 while (iterator.hasNext()){
//                     int cardNumber = iterator.next().getValue();
//                     Card currentCard = iterator.next();
//                     if (cardNumber == count) warLordsCount.add(currentCard.);
//                 }
                    //}
                //}
            }
            // this gets the player not the card from warchest ->>System.out.println("the winner is: " + playerArrayList.get(cardTracker).); // check
        } // determining who wins round or if there will be war

//        int pCount = 0;
//        String playersToWar = "Players: ";
//        for (Integer whichPlayer : warLordsCount) { // gathering up the players to go to war
//            pCount += 1;
//            playersToWar += (whichPlayer + 1) + ", "; // adding 1 to print correct player, prints correct index regardless
//            playersToWar += (playerArrayList.get(whichPlayer).getPlayerNumber()) + ", "; // adding 1 to print correct player, prints correct index regardless
//        }// gathering the players to go to war, if any
//           one player won the round and is getting war chest




        // int warTime = 0;

//*********$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        if (pCount > 1){  // there >2 player in whichPlayer, going to war
            allSteps += 1;
            warCount +=1;
            totalWars += 1;
            switch (verbosity){
                case 1,2,3->{
                    warChange = toWarReport(warLordsCount, verbosity, CardTracker);
                }
            } // create a war report of cards going to war if verbosity 1-3
            //warChange += zeroCheckRemoval;
            //System.out.println("check3 " +playerArrayList);
            playerArrayList = toWar(playerArrayList, warLordsCount,warChest);
            //System.out.println("check4 " +playerArrayList);

            //System.out.println("Index's of players going to war: " +  warLordsCount ); // correct player index is used <<--- correct index is used but it mismatches player number because we are using an index here, instead of a counter
            //playersToWar += "are going to war";
            //System.out.println(playersToWar);
            //updatedPlayerList = makeWar(playerArrayList, warLordsCount, warChest, verbosity);
            //makeWar(playerArrayList, warLordsCount, warChest, verbosity);

//            doubleWarCount += 1;
//            System.out.println("Index's of players going to double war: " +  warLordsCount ); // correct player index is used <<--- correct index is used but it mismatches player number because we are using an index here, instead of a counter
//            //playersToWar += "are going to double war";
//            //System.out.println(playersToWar);
//            //warChange += toWarReport(warLordsCount, verbosity);
//            //updatedPlayerList = makeWar(playerArrayList, warLordsCount, warChest, verbosity);
//
        }
        else { // one player won the round

            //$$$$$$ maybe clear warReport here$$$$$$$
            for (var card : warChest.inDeck()){     // for every card in warchest deck
                playerArrayList.get(cardTracker).bottomAddCard(card); // get player that won and add that card to the bottom of deck
                // only works because there is only one of that card type in deck
                // otherwise getPlayerIndex would throw error
            } // add cards to winners deck
            // int whichPlayer = cardTracker+1; // index starts counting @ 0 (used for prints)

            switch (verbosity){
                case 1,2,3->{
                    allSteps += 1;
                    int winner = playerArrayList.get(cardTracker).getPlayerNumber();
                    String winnersDeck = playerArrayList.get(cardTracker).toString();
                    // updatedPlayerList = playerArrayList;
                    //String roundSummary = playerDeckState(playerArrayList,verbosity);
                    switch (verbosity){
                        case 1 ->{

                            roundChange = String.format("""
                                ******************************************
                                The winner is player: %d
                                The winning card is: %s
                                The winning deck is:
                                %s
                                ******************************************
                                """, winner, CardTracker,winnersDeck);

                            winnersList += String.format("""
                                ----------------------------
                                Player: %d won round %d
                                ----------------------------
                                """, winner, rounds);
                    } // print everything and add round summary to winnersList
                        case 2 ->{

                            roundChange = String.format("""
                                The winner is player: %d
                                """, winner);

                            winnersList += String.format("""
                                ============================
                                Player: %d won round %d
                                ============================
                                """, winner, rounds);

                    } // print winner and round, add summary to winnersList
                        case 3 ->{

                            winnersList += String.format("""
                                ============================
                                Player: %d won round %d
                                ============================
                                """, winner, rounds);
                    } // add round summary to winnersList

                    } // keeps track of middle of round metrics and stores them, if verbiage 1-3
    //            System.out.println("the winning card in war chest is card: " + CardTracker); // check the index of winning card in warchest
    //            System.out.println("the winner is player list: " + playerArrayList.get(cardTracker).toString()); // check the index of winning card in player list
    //            System.out.println("the winner is player: " + winner); // check the index of winning card in player list
    //            System.out.println("**testing** the winner is player: " + playerArrayList.get(cardTracker).getPlayerNumber()); // check testing getplayernumber
                }
                case 4->{
                    allSteps += 1;
                }
            }// cases 1-3 produce winner report (effects roundChange and winners list) and counts step
            // case 4 just counts step
        } // one player won the round and is getting war chest
        return playerArrayList; // erase
    }

    //********************************************* END **************************************************************/

    //******************************************** START *************************************************************
    private ArrayList<Player> WarChestLoop
    (ArrayList<Player> playerArrayList,ArrayList<Integer> warLordNumbers, Deck warChest, ArrayList<Deck> listOfChests,int verbosity, int warTracker, int rounds){
        allSteps += 1;
        switch (warTracker){
            case 1 ->{
                doubleWarCount +=1;
                totalWars += 1;
            } // count doubleWar
            case 2 ->{
                TripleWarCount +=1;
                totalWars +=1;
            } // count tripleWar
            default ->{
                higherWars +=1;
                totalWars +=1;
            } // count any war that's higher than x3
        } //count war types

        ArrayList<Integer> warLordsCount = new ArrayList<>();
        int cardTracker = 0;
        Card CardTracker = new Card(0,"X");
        int count = 0;
        int pCount = 0;

        int pNum = 0;

        //System.out.println("TEST0: " + playerArrayList);
        //System.out.println("TEST1: " + warLordNumbers);
        //System.out.println("TEST2: " + warChest);
        //System.out.println("TEST3: " + listOfChests);

        for( var card : warChest.inDeck()){      // for every card in deck
            if (card.getValue() > count){ // if the value of the card is larger than the last
                pNum = warLordNumbers.get(warChest.inDeck().indexOf(card)); // pNum becomes the number of player who gave that card

                pCount = 0;
                count = card.getValue(); // count takes up the value of the card
                //cardTracker = warChest.getCardIndex(card); // maybe no
                cardTracker = warChest.inDeck().indexOf(card);
                CardTracker = card;
                warLordsCount.clear();
            }// if card is bigger than last card,
            else if (card.getValue() == count){
                CardTracker = card;
                if (warLordsCount.size() == 0) {
                    int i = 0;
                    for (final Iterator<Card> iterator = warChest.inDeck().iterator(); iterator.hasNext(); i++) {
                        int cardNumber = iterator.next().getValue();
                        if (cardNumber == count) {
                            //int playerNumber = playerArrayList.get(i).getPlayerNumber();
                            int playerNumber = warLordNumbers.get(i);
                            warLordsCount.add(playerNumber);
                            pCount += 1;
                        }
                    }
                }
            }
            // this gets the player not the card from warchest ->>System.out.println("the winner is: " + playerArrayList.get(cardTracker).); // check
        }

        if (pCount > 1 ){
            System.out.println("FAIL");
            //int warTracker = 1;

            Iterator<Player> playerIterator = playerArrayList.iterator();
            ArrayList<Player> multiWarPlayers = new ArrayList<>(); // players going off to triple wars and such
            Deck multiWarChest = new Deck(); // place all cards in 1 deck
            for ( var deck : listOfChests){
                for (var card : deck.inDeck()){
                    multiWarChest.addCard(card);
                }
            }

            for (var newLord : warLordsCount){
                while(playerIterator.hasNext()){
                    Player playerToRemove = playerIterator.next();
                    if (playerToRemove.getPlayerNumber() == newLord){
                        multiWarPlayers.add(playerToRemove);
                        break;
                    }
                }
            }

            while (playerArrayList.size()>1){
            // wartracker plus 1 in param arg
                playerArrayList = DrawCards(multiWarPlayers,multiWarChest,verbosity,true, rounds, warTracker +1);
            }
        }
        else{
            //System.out.println("TEST4: " + pNum);
            //System.out.println("TEST5: " + count);
            //System.out.println("TEST6: " + cardTracker);
            //System.out.println("TEST7: " + CardTracker);
            //System.out.println("TEST8: " + pCount);

            int playerNum = warLordNumbers.get(cardTracker);

            //Player winner = new Player(pNum);
            int pLocation = 0;

            for(var player : playerArrayList){
                if ( player.getPlayerNumber() == playerNum){
                     pLocation = playerArrayList.indexOf(player);
                    break;
                }
            }

            //Player winner = playerArrayList.get(cardTracker);
            //int winnersNum = winner.getPlayerNumber();
            for (var deck : listOfChests ){ // for every deck in deck list
                for (var card : deck.inDeck()){ // for every card in that deck
                    playerArrayList.get(pLocation).bottomAddCard(card); // add that card to winners deck
                    //winner.bottomAddCard(card);
                }
            }
            //System.out.println("winner: " + winner);
            //System.out.println("winner number: " + winnersNum);


        }

         return playerArrayList; // DELETE
    }
    //********************************************* END **************************************************************/


    //******************************************** START *************************************************************
     private ArrayList<Player> toWar (ArrayList<Player> playerList, ArrayList<Integer> warLordNums, Deck warChest){ // counter override
        // copy players, that are in warChest, to new list
         // check that every player can draw at least one card
            // if a player does not have a card, he loses and is removed from the war
            // when the updated list is returned, the player with no card will not be in
            // list of players to be updated so after the update, player
            // will remain with 0 cards in deck and be removed when checked
         // pass list to draw cards
         // drawCards passes updated list to warChest_PriorWar() with 0 counter args
         // switch case 0 -> nothing?, 1-> x2warCounter +1, 2-> x3warCounter, default case (all other numbers)->totalWars +1
         // if warChest_PriorWar() produces another war, counter +1
         // passes -> toWar (with counter) -> drawCards -> warChest_PriorWar()
         // when done, copy players back into list


         // uses the player # of players going to war and copies the players from the player list,
         // into a warLords list
         // this could probably be done in the method that calls this, earlier on, but that would take a while
         // to recode %%%%%%%%%% fix maybe %%%%%%%%%%%%%%%
         //System.out.println(" list before copying players:\n" + playerList);
         ArrayList<Player> updatedPlayerList = new ArrayList<>();
         ArrayList<Player> warLords = new ArrayList<>();
         Iterator<Player> playerIterator = playerList.iterator();
         for (var playerNum : warLordNums){ // for every player number in warlordscount
             // System.out.println("player: "+ playerNum + "\n");
             while (playerIterator.hasNext()){ // while the iterator hasNext
                 // System.out.println("looking...\n");
                 Player playerToRemove = playerIterator.next(); // variable takes copy of current player
                 // System.out.println("Player: " + playerToRemove.getPlayerNumber() + "\n");
                 if (playerToRemove.getPlayerNumber() == playerNum){ // if that players number matches the warlords number
                     // System.out.println("player found\n");
                     warLords.add(playerToRemove); // add that player to warlords list
                     // System.out.println("player " + playerToRemove.getPlayerNumber() + " added");
                 break;
                 }
             }
         } // make list with copy of warlords
         // cull the warlords with no cards left before war starts
         // will make/print strings on culling depending on verbiage
         warLords = playerCheck(warLords, verbosity);
         //System.out.println("checking shit\n" + warLords);



//         if (warLords.size() == 0) {
//
//             return playerList;
//
//         }
         if (warLords.size() == 1){ // if one lord remains after the culling
             //System.out.println("beep");//zeroCheckRemoval += "\none player automatically one";
             Player winningWarLord = warLords.get(0);
             for (var card : warChest.inDeck()){winningWarLord.bottomAddCard(card);} // add every card in warChest
             // to bottom of winning warLords deck
             for (var player: playerList){ // for every player
                 if (player.getPlayerNumber() == winningWarLord.getPlayerNumber()){// if the players # matches the warlords #
                     playerList.set(playerList.indexOf(player), winningWarLord);
                     // set the player at index of player to the winningWarlord
                     break;
                 }// copy winning warlord into player masterlist
             } // if there is one warlord left, give him warchest and put him back into playerlist
             allSteps += 1;
             return playerList; // return list with winning warlord
         }// if there is one warlord left, give him warchest and put him back into playerlist and return list
         else {
             if (warLords.size() > 1) {
                 warLords = DrawCards(warLords, warChest, verbosity, true, rounds,1);// trying to figure out which warlords are gonna take it all baybee

                 for (var warLord: warLords){
                     for (var player : playerList){
                         if(player.getPlayerNumber() == warLord.getPlayerNumber()){
                             playerList.set(playerList.indexOf(player), warLord);   // go through and update players
                         }
                     }
                 }

                 // war lords draw cards (pass warlods and warchest) ->
                 // -> go to WarChest_PriorWAR(ArrayList<Player> playerArrayList, Deck warChest, warchest list?,
                 // int verbosity, int rounds?,
                 // int warTracker)

                 // switch case if warTracker = 1, then this is a doubleWaR (+=1), totalWars +=1
                 // if warTracker = 2, then this is a TripleWar (+=1), totalWars +=1
                 // default case (all other numbers > 0) higherWars +=1, totalWars +=1


                 // (doubleWar), determine which warlord has one or if there is another war (triple)

                 //System.out.println(" players definitely fighting and shiet");


                 // check method call

//         if (playerList.size() == 0 ){
//             return playerList;
//
//         }
                 // IF PLAYERLIST HAS ONE PLAYER, RETURN LIST
//%%%%%%%%%%%%%%%%%%%%
                 //System.out.println("list after copying players:\n" + playerList);
                 //System.out.println(warLords);

//         System.out.println("passed list: " + playerList);
//         System.out.println("war lords passed: " + warLords);
//         System.out.println("updated player list: " + updatedPlayerList);
//         System.out.println("# of players in list " + updatedPlayerList.size());
                 // player with no cards left will be printed as empty space (unless getPlayerNumber() used) but they will still be there
                 // ELSE vvv
//$$$$$
                 //updatedPlayerList = DrawCards(warLords, warChest,verbosity,true, rounds);
             }
             allSteps += 1;
             return playerList;

         }

        // return playerList; // DELETE
     }

    //********************************************* END **************************************************************/


    //******************************************** START *************************************************************
    // method removes players going to war that can't pull anymore cards
    private ArrayList<Player> playerCheck (ArrayList<Player> warLords, int verbosity) {
        ArrayList<Player> returnList = new ArrayList<>(); // list only used to return one player
        ArrayList<Player> losingWarLords = new ArrayList<>(); // list contains players
        // that can't compete in the war and automatically lose the war (0 cards in deck)


        Iterator<Player> warLordsIterator = warLords.iterator(); // make iterator
        int deckSize = 0;
        Player playerToRemove;
        int warLordReference = warLords.size();
        for (int i = 0; i < warLordReference; i++){ // while element has a next one
            playerToRemove = warLordsIterator.next(); // variable takes up element
            //deckSize = warLordsIterator.next().size(); // variable takes up elements deck size moves iterator an additional step each loop
            deckSize = playerToRemove.size();
            if(deckSize <= 0){ // if the deck size is 0
                losingWarLords.add(playerToRemove); // copy the player with no more cards into new warLords list
                warLordsIterator.remove();  // remove player with 0 cards from original warLords list
            }
        }

        if (warLordReference > warLords.size()) {
            switch (verbosity) {
                case 1, 2, 3 -> {
                    String loserReport = "";

                    Iterator<Player> iterator = losingWarLords.iterator();
                    while (iterator.hasNext()) {
                        Player whichPlayer = iterator.next();
                        loserReport += (whichPlayer.getPlayerNumber());
                        if (iterator.hasNext()) loserReport += ", ";
                    }
                    switch (verbosity) {
                        case 1 -> {
                            // zeroCheckRemoval will be used to print in DrawCards()
                            // playersRemoved will be used in final summary print
                            zeroCheckRemoval += String.format("""
                                    ******************************************
                                    Player: %s had no cards left for war
                                    and was removed before war in round %d
                                    ******************************************
                                    """, loserReport, rounds);
                            playersRemoved += String.format("Player: %s removed before war %d in round: %d\n", loserReport, totalWars, rounds);
                        }
                        case 2 -> {
                            zeroCheckRemoval += String.format("""
                                    ******************************************
                                    Players with no cards culled
                                    before war in round %d
                                    ******************************************
                                    """, rounds);
                            playersRemoved += String.format("Player: %s removed before war %d in round: %d\n", loserReport, totalWars, rounds);

                        }
                        case 3 -> {
                            playersRemoved += String.format("Player: %s removed before war %d in round: %d\n", loserReport, totalWars, rounds);
                        }
                    }
                }
            }
        }
        if (warLords.size() == 1){
            int winnerNum = warLords.get(0).getPlayerNumber();
            switch (verbosity) {
                case 1 -> {
                    // zeroCheckRemoval will be used to print in DrawCards()
                    // playersRemoved will be used in final summary print
                    zeroCheckRemoval += String.format("""
                                    ******************************************
                                      *** other players are out of cards ***
                                      
                                      Player: %s automatically won the war!
                                    ******************************************
                                    """, winnerNum);
                    playersRemoved += String.format("""
                            Players were out of cards
                            Player: %s automatically won war %d in round: %d
                            """, winnerNum, totalWars, rounds);
                }
                case 2 -> {
                    zeroCheckRemoval += String.format("""
                                    ******************************************
                                      Player: %s automatically won the war!
                                    ******************************************
                                    """, winnerNum);
                    playersRemoved += String.format("""
                            Players were out of cards
                            Player: %s automatically won war %d in round: %d
                            """, winnerNum, totalWars, rounds);

                }
                case 3 -> {
                    playersRemoved += String.format("""
                            Players were out of cards
                            Player: %s automatically won war %d in round: %d
                            """, winnerNum, totalWars, rounds);
                }
            }
        }

        if (warLords.isEmpty()){ // if all players had 0 cards left
            Collections.shuffle(losingWarLords); // shuffle the copied warlord list
            returnList.add(losingWarLords.get(0)); // pull the first warlord as winner
            switch (verbosity){

                case 1 ->{
                    zeroCheckRemoval += String.format("""
                                ******************************************
                                   NO PLAYER HAD ENOUGH CARDS FOR WAR
                                ******************************************
                                  ** coin flip determining a winner...**
                                  
                                ** PLAYER %d HAS WON COIN FLIP AND WAR **
                                ******************************************
                                """, losingWarLords.get(0).getPlayerNumber());
                    playersRemoved += String.format("Player: %s won coin flip in round %d\n",losingWarLords.get(0).getPlayerNumber(), rounds);
                }
                case 2->{
                    zeroCheckRemoval += String.format("""
                                ******************************************
                                   NO PLAYER HAD ENOUGH CARDS FOR WAR
                                ****************************************** 
                                ** PLAYER %d HAS WON COIN FLIP AND WAR **
                                ******************************************
                                """, losingWarLords.get(0).getPlayerNumber());
                    playersRemoved += String.format("Player: %s won coin flip in round %d\n",losingWarLords.get(0).getPlayerNumber(), rounds);

                }
                case 3->{
                    playersRemoved += String.format("Player: %s won coin flip in round %d\n",losingWarLords.get(0).getPlayerNumber(), rounds);
                }
            }
            return returnList;
        }else
            // System.out.println("all players have enough cards to make war"); // check


//        for (var playerNum : warLordNums){
//            // System.out.println("player: "+ playerNum + "\n");
//            while (playerIterator.hasNext()){
//                // System.out.println("looking...\n");
//                Player playerToRemove = playerIterator.next();
//                // System.out.println("Player: " + playerToRemove.getPlayerNumber() + "\n");
//                if (playerToRemove.getPlayerNumber() == playerNum){
//                    // System.out.println("player found\n");
//                    warLords.add(playerToRemove);
//                    // System.out.println("player " + playerToRemove.getPlayerNumber() + " added");
//                    break;
//                }
//            }
//        }
        return warLords;
    }

    //********************************************* END **************************************************************/



    public void clear(){
        tempDoubleWarReport="";
        //DoubleWarReport = "";
        tempTripleWarReport = ""; // same as above
        //TripleWarReport = "";
        //warReport ="";
        endRoundPhase = "";
        zeroCheckRemoval = "";
        roundChange ="";
        warChange = "";


    }




























    public static void main (String[] args){
        // check creation of deck for debugging
        //Deck newDeck = new Deck();
        //System.out.println(newDeck);

        War_rev2 warGame = new War_rev2();
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
