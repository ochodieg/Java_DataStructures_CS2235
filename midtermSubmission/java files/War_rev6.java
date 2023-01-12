package MidProject_rev6;

import java.util.*;


public class War_rev6 {
    // variables that determine conditions
    private final int       playerNum;
    private final int       gamesNum;
    private final boolean   jokerPick;
    public final int       verbosity; // selection 2, print every 50 rounds: if rounds%50 == 0

    public int rounds = 0; // keeps track of every round
    public int allWars = 0;
    public int warCount = 0; // keeps track of regular wars
    public int warsInRound = 0; // add to every time drawDeux is called. used to keep track of double/triple wars
    public int tripWar = 0;

    public int roundsCount = 0;
    public int allWarsCount = 0;
    public int warCountCount = 0;
    public int warsInRoundCount = 0;
    public int tripWarCount = 0;

    public int gameCount = 0;

    public String startingMetrics       =""; // this is only updated once, at start, to display the initial metrics (verbiage 1-3)

    public String drawPhase_Print       = ""; // clear and print every round
    public String drawPhase_String      = ""; // concat to and print at the end of game

    public String roundDrawWinner_Print = ""; // clear and print every round
    public String roundDrawWinner_String= ""; // concat to and print at the end of game

    public String warStartReport_Print  = ""; // print and clear every round
    public String warStartReport_String = ""; // concat to and print at the end of the game

    public String zeroCheckCull_Print   = ""; // print and clear every round
    public String zeroCheckCull_String  = ""; // concat to and print at the end of the game

    public String warDrawPhase_Print = ""; // print and clear every round
    public String warDrawPhase_String =""; // concat to and print at the end of the game

    public String zeroCheckWarCull_Print   = ""; // print and clear every round
    public String zeroCheckWarCull_String  = ""; // concat to and print at the end of the game

    public String warDrawWinner_Print = ""; // clear and print every round
    public String warDrawWinner_String= ""; // concat to and print at the end of game

    public String roundEnd_Print = "";  // print and clear every round
    public String roundEnd_String = ""; // concat to and print at the end of the game

    public String finalSummaryDisplay = ""; // all important metrics will be concat' for a summary finalReportString

    public War_rev6(){
        Scanner userInput = new Scanner(System.in);
        jokerPick = this.getJoker(userInput);
        playerNum = this.getPlayers(userInput,jokerPick);
        verbosity = this.getVerbosity(userInput);
        gamesNum = this.getGames(userInput);

        //ArrayList<Player> MasterPlayerArrayList = this.initialize(jokerPick, playerNum);
        // System.out.println( MasterPlayerArrayList);

        for(int i = 0 ; i<gamesNum ; i++){


            ArrayList<Player> MasterPlayerArrayList = this.initialize(jokerPick, playerNum);



            switch (verbosity){
                case 1,2,3->{
                    if (verbosity == 3) {
                        System.out.println( startingMetrics);
                    }

                    while(MasterPlayerArrayList.size()>1){
                        MasterPlayerArrayList = DrawCards(MasterPlayerArrayList, 0);
//                        for(var player: MasterPlayerArrayList){
//                            int amount = player.size();
//                            ArrayList<Card> toShuffle = new ArrayList<>();
//                            for(int f = 0; f<amount;f++){
//                                toShuffle.add(player.dealCard());
//                            }
//                            Collections.shuffle(toShuffle);
//                            for(int g = 0; g<amount;g++){
//                                player.bottomAddCard(toShuffle.remove(0));
//                            }
//
//                        }
                        switch (verbosity){
                            case 1,2->{
                                RoundSummary(MasterPlayerArrayList);
                            }
                        }
                    }

                    switch (verbosity){
                        case 1,2,3->{
                            printGameSummary();
                        }
                    }
                }
            }
            roundsCount += rounds;
            rounds = 0;
            //System.out.println(rounds);
            allWarsCount += allWars;
            allWars = 0;
            //System.out.println(allWars);
            warCountCount += warCount;
            warCount=0;
            //System.out.println(warCount);
            //System.out.println(warsInRoundCount);
            //System.out.println(tripWarCount);
    //        System.out.println(roundsCount);
    //        System.out.println(rounds);
    //        System.out.println(allWarsCount);
    //        System.out.println(allWars);
    //        System.out.println(warCountCount);
    //        System.out.println(warCount);
    //        System.out.println(warsInRoundCount);
    //        System.out.println(warsInRound);
    //        System.out.println(tripWarCount);
    //        System.out.println(tripWar);

                gameCount +=1;
            }
            int avgSteps = roundsCount/gamesNum;
            int avgWars = allWarsCount/gamesNum;
            int avgDubWars = warsInRoundCount/gamesNum;
            int avgTripWars = tripWarCount/gamesNum;
            System.out.println("After " + gameCount + " games with " + playerNum + " players:");
            System.out.println("Average number of rounds per game: " + avgSteps);
            System.out.println("Average number of wars per game: " + avgWars);
            System.out.println("Average number of double wars per game: " + avgDubWars);
            System.out.println("Average number of triple wars per game: " + avgTripWars);
    }

    // these determine the conditions
    //*************************************************************************************************************************************************************
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

        return players;}// gets number of players from user
    private int getVerbosity(Scanner verbiage){
        // Scanner verbiage = new Scanner(System.in);
        String verbiageSelect = """

                       >>> How verbose should this program be? <<<
                    1) Print everything.
                    2) Print important metrics.
                    3) Print summary only.
                    4) Run with no Strings.""";
        System.out.println(verbiageSelect);

        int number;
        do {
            System.out.print(" (Please select a given option)\n>> ");
            while (!verbiage.hasNextInt()) {
                String input = verbiage.next();
                System.out.printf("*** \"%s\" is not a valid option ***\n>> ", input);
            }
            number = verbiage.nextInt();
        } while (number < 1 || number > 4);

        System.out.printf("            -- Verbosity select: %d --\n\n", number);
        return number;}
    private int getGames(Scanner gameAmount){
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
        return games;}
    private boolean getJoker(Scanner askForJ){
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
        }
        return joker;
    }
    //*************************************************************************************************************************************************************
    // initializes the deck
    //******************************************** START *************************************************************
    private ArrayList<Player> initialize
    (boolean joker, int chosenPlayerAmount){

        // create the deck and deal it to the players
        Deck newDeck = new Deck(joker); // deck is made
        newDeck.shuffle();  // deck is shuffled
        ArrayList<Player> primordialPlayerList = new ArrayList<>(); // make list to add players to
        for (int i = 0; i < chosenPlayerAmount; i++) { // add all players to player list
            primordialPlayerList.add(i, new Player(i+1)); // i+1 so player number starts at 1
        } // add chosen players to list
        int totalCards = newDeck.size();
        int totalPlayers = primordialPlayerList.size();
        int modulus = totalCards % totalPlayers; // determines if deck will be split evenly among players
        if (modulus == 0){
            for (var player : primordialPlayerList){
                for (int i = 0; i < (totalCards/totalPlayers); i++)
                    player.bottomAddCard(newDeck.dealCard());
            }
        }         // deals hand when cards are split evenly
        else {
            for (var player : primordialPlayerList){
                for (int i = 0; i < (totalCards/totalPlayers); i++)
                    player.bottomAddCard(newDeck.dealCard());
            }
            for (int i = 0; i < modulus; i++) primordialPlayerList.get(i).bottomAddCard(newDeck.dealCard());
        }   // deals hand when cards aren't split evenly

        switch (verbosity){
            // cases one and two make string to print
            case 1,2 ->{

                String firstDeckDealString = firstDeckDeal(primordialPlayerList);
                String startingMetricsToString = String.format("""
                        ============ Starting metrics: ============

                        %s
                        ===========================================
                        """, firstDeckDealString);//%s return is formatted with whitespace on bottom
                // this is only updated once, at start
                System.out.println(startingMetricsToString);

            }
            // case 3 only calls firstDeckDeal() to create the starting metric string to print at summary
            case 3 ->{
                firstDeckDeal(primordialPlayerList);
            }
            // case 4 does nothing
            case 4 ->{

            }
        }
        return primordialPlayerList;
    } // initializes decks and starting stats
    //********************************************* END **************************************************************
    //******************************************** START *************************************************************
    // composes string that lists the cards players were first dealt
    private String firstDeckDeal(ArrayList<Player> primordialPlayerList){
        String firstDeal = "";
        startingMetrics += "STARTING METRICS:\n";

        switch (verbosity){
            // verbiage 1 makes string to return to initialize and concat to starting metrics
            case 1 -> {
                for (var player : primordialPlayerList){
                    int cCount = player.size();
                    firstDeal += String.format("""
                            >> player %d starts with %d card(s):
                            (%s)

                            """,player.getPlayerNumber(),cCount,player);

                    startingMetrics += String.format("""
                            >> PLAYER %d started with %d card(s)
                            (%s)
                            """, player.getPlayerNumber(),cCount,player);
                }
            }
            // verbiage 2 makes string without players deck to return to initialize and concat to starting metrics
            case 2 -> {
                for (var player : primordialPlayerList){
                    int cCount = player.size();
                    firstDeal += String.format("""
                            >> player %d has %d cards:

                            """,player.getPlayerNumber(),cCount);

                    startingMetrics += String.format("""
                            >> PLAYER %d started with %d cards
                            (%s)
                            """, player.getPlayerNumber(),cCount,player);
                }
            }
            // verbiage 3 only creates the starting metric string to print at summary
            case 3 -> {
                for (var player : primordialPlayerList){
                    int cCount = player.size();
                    startingMetrics += String.format("""
                            >> PLAYER %d started with %d cards
                            (%s)
                            """, player.getPlayerNumber(),cCount,player);
                }
            }
        }
        return firstDeal;
    } // creates starting stats
    //********************************************* END **************************************************************
    //******************************************** START *************************************************************
    private String playerDeckState(ArrayList<Player> playerList ){
        String roundlyDeckState = "";
        switch (verbosity){
            case 1 -> {
                for (var player : playerList){
                    int cCount = player.size();
                    int playerNum = player.getPlayerNumber();
                    roundlyDeckState += String.format("""
                            >> player %d has %d cards:
                            %s

                            """, playerNum, cCount, player);
                }
            }
            case 2 -> {
                for (var player : playerList){
                    int cCount = player.size();
                    int playerNum = player.getPlayerNumber();
                    roundlyDeckState += String.format("""
                            >> player %d has %d cards
                            %s
                            """, playerNum, cCount,player);
                }
            }
        }
        return roundlyDeckState;
    } // used every round to print state of the decks (verb 1-2)
    //********************************************* END **************************************************************
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //    private String roundlyPrint () <-- gathers all string info to print at end
    // makes string of initial card draw to print
    private void setDrawPhase_Strings (ArrayList< playerCardTuple<Card,Integer> > warChestDraw){
        String todrawPhase_Print = "";
        String warChest = "";
        //String todrawPhase_String = "";
        for(var tuple : warChestDraw){
            switch (verbosity){
                case 1,2,3->{
                    Card playersCard = tuple.getCard();
                    int playersNum = tuple.getPlayer();
                    drawPhase_String += String.format(">> player: %d pulled card %s in round %d\n", playersNum, playersCard,rounds);
                    switch (verbosity){
                        case 1,2->{
                            warChest += playersCard + "  ";
                            //Iterator<playerCardTuple<Card, Integer>> iterator = warChestDraw.iterator();
                            //if(iterator.hasNext()){ warChest += ", ";}
                            todrawPhase_Print+= String.format("***   player: %d pulled card: %s   ***\n\n", playersNum, playersCard);
                        }
                    }
                }
            }
        }
        switch(verbosity){
            case 1,2 ->{
                drawPhase_Print += String.format("""
                                    
                                    ====================== Round: %d ======================
                                    -------------------------------------------------------
                                          *** Players draw top cards to war-chest ***
                                    
                                    %s
                                    War-Chest:
                                    %s
                                    -------------------------------------------------------
                                    
                                    """, rounds, todrawPhase_Print,warChest);
            }
        }
    }
    // makes string of player winning draw (no war)
    private void setDrawWinStrings (int winner, Card winningCard, Player toWinningDeck){
        switch(verbosity){
            case 1,2 ->{
                roundDrawWinner_Print += String.format("""
                                ******************************************
                                The winner is player: %d
                                The winning card is: %s
                                The winning deck is:
                                %s
                                ******************************************
                                """, winner, winningCard,toWinningDeck);

                roundDrawWinner_String += String.format("""
                                ----------------------------
                                Player: %d won round %d
                                ----------------------------
                                """, winner, rounds);
            }
            case 3 ->{
                roundDrawWinner_String += String.format("""
                                ----------------------------
                                Player: %d won round %d
                                ----------------------------
                                """, winner, rounds);
            }
        }
    }
    // makes a String in the event of a war
    private void setWarStartReportStrings(ArrayList<playerCardTuple<Card,Integer>> warLords_warChestTupleList){
        switch (verbosity){
            case 1,2,3->{
                String players = "";
                for(var tuple : warLords_warChestTupleList){
                    players += tuple.getPlayer() + " - ";
                }
                switch (verbosity) {
                    case 1, 2 -> {
                        String fireStarter = warLords_warChestTupleList.get(1).getCard().toString();
                        // in this warChest tuple, the second card will be the one that starts the war, as its the first one that
                        // matches/challenges the highest card

                        warStartReport_Print += String.format("""
                        *************** WAR START ****************
                        %d players ( - %s),
                        tied and are going to war
                        
                        Card: %s Started the war
                        ******************************************
                        """, warLords_warChestTupleList.size(), players, fireStarter);
                        // warStartReport_String += String.format(">players: %s went to war in round %d\n", players, rounds);
                    }
                }
                warStartReport_String += String.format(">players: - %s are going to war in round %d\n", players, rounds);
            }
        }
    }
    // makes a String when players are Culled
    private void setZeroCheckCullStrings
    (playerCardTuple<ArrayList<playerCardTuple<Card,Integer>>,ArrayList<Player>> warLordsToCheck,
     ArrayList<Integer> losers, int winnerState){
        //zeroCheckCull_Print
        //zeroCheckCull_String
        String loserCount = "Player(s): -";
        if(losers != null){
            // make list of losers
            for(var loser : losers){
                loserCount += String.format(" %d -", loser);
            }
        } // if there were losers, make a string for them
        switch (winnerState){
            case 0,1 ->{
                // get # of player who won coin flip or default win
                int randomWinner = warLordsToCheck.getPlayerArrayList().get(0).getPlayerNumber();
                switch (winnerState){
                    case 0->{
                        switch (verbosity){
                            case 1,2,3->{
                                zeroCheckCull_String += String.format(">> Player: %s won coin flip in avant-match, round %d\n",randomWinner,rounds);
                                switch (verbosity){
                                    case 1,2->{
                                        zeroCheckCull_Print += String.format("""
                                ******************************************
                                   NO PLAYER HAD ENOUGH CARDS FOR WAR
                                ******************************************
                                  ** coin flip determining a winner...**
                                  
                                ** PLAYER %d HAS WON COIN FLIP AND WAR **
                                ******************************************
                                """, randomWinner);
                                    }
                                }
                            }
                        }
                    } // coin flip winner
                    case 1->{
                        switch (verbosity){
                            case 1,2,3->{
                                zeroCheckCull_String += String.format("""
                            >> %s were out of cards
                            >> Player: %s automatically won war %d in round: %d
                            """,loserCount, randomWinner,allWars,rounds);
                                switch (verbosity){
                                    case 1,2->{
                                        zeroCheckCull_Print += String.format("""
                                    ******************************************
                                      *** other players are out of cards ***
                                      !! %s were out of cards !!
                                      
                                      Player: %s automatically won the war!
                                    ******************************************
                                    """,loserCount, randomWinner);
                                    }

                                }
                            }
                        }

                    } // default winner
                }
            } // case 0: player wins a coin flip | case 1: player wins by default
            case 2->{
                // how many players going to war
                int playersLeft = warLordsToCheck.getPlayerArrayList().size();

                if(losers == null) { // if all warlords still have cards
                    switch (verbosity) {
                        case 1, 2, 3 -> {
                            zeroCheckCull_String += String.format("""
                                    >> All %s players survived zero-deck cull
                                       and are going to war %d in round %s
                                    \s""", playersLeft, allWars, rounds);
                            switch (verbosity) {
                                case 1,2 -> {
                                    zeroCheckCull_Print += String.format("""
                                            ** All %s players have enough cards to **
                                                        ** go to war **
                                            """, playersLeft);
                                }
                            }
                        }
                    }
                }      // all warlords are going to war
                else{
                    switch (verbosity) {
                        case 1, 2, 3 -> {
                            zeroCheckCull_String += String.format(">> %s removed in culling before war %d\n   in round: %d\n",loserCount, allWars, rounds);
                            switch (verbosity) {
                                case 1,2 -> {
                                    zeroCheckCull_Print = String.format("""
                                    ******************************************
                                    !! %s have no cards left for war !!
                                    !! and were removed before war in round %d !!
                                    
                                    %d players are still going to war
                                    ******************************************
                                    """,loserCount,rounds, playersLeft);
                                }
                            }

                        }
                    }
                } // some warlords are going to war
            } // players are going to war
        }
    }
    private void setDoubleWarDrawPhase (ArrayList< playerCardTuple<ArrayList<Card>, Player> > doubleWar_Chest){

        String todrawPhase_Print = "";
        String warChest = "";

        //String todrawPhase_String = "";
        for(var tuple : doubleWar_Chest){
            switch (verbosity){
                case 1,2,3->{
                    ArrayList<Card> cards = tuple.getCard();
                    String cardsPulled = "";

                    for (var card : cards){
                        cardsPulled += String.format("%s ", card.toString());
                    }

                    Player currentPlayer = tuple.getPlayer();
                    int playersNum = currentPlayer.getPlayerNumber();
                    warDrawPhase_String  += String.format(">> player: %d pulled card %s in WAR %d\n", playersNum, cardsPulled ,allWars);

                    switch (verbosity){
                        case 1,2->{
                            warChest += cardsPulled + "  ";
                            //Iterator<playerCardTuple<Card, Integer>> iterator = warChestDraw.iterator();
                            //if(iterator.hasNext()){ warChest += ", ";}
                            todrawPhase_Print+= String.format("***   player: %d pulled card: %s   ***\n\n", playersNum, cardsPulled);
                        }
                    }
                }
            }
        }
        switch(verbosity){
            case 1,2 ->{
                warDrawPhase_Print += String.format("""
                                    
                                    ********************** WAR %d **********************
                                    -------------------------------------------------------
                                          *** Players draw top cards to war-chest ***
                                    
                                    %s
                                    War-Chest:
                                    %s
                                    -------------------------------------------------------
                                    
                                    """, allWars, todrawPhase_Print,warChest);

            }
        }


    }
    private void setZeroCheckWarCullStrings
            (playerCardTuple<ArrayList<playerCardTuple<Card,Integer>>,ArrayList<Player>> warLordsToCheck,
             ArrayList<Integer> losers, int winnerState){
        //zeroCheckCull_Print
        //zeroCheckCull_String
        String loserCount = "Player(s): -";
        if(losers != null){
            // make list of losers
            for(var loser : losers){
                loserCount += String.format(" %d -", loser);
            }
        } // if there were losers, make a string for them
        switch (winnerState){
            case 0,1 ->{
                // get # of player who won coin flip or default win
                int randomWinner = warLordsToCheck.getPlayerArrayList().get(0).getPlayerNumber();
                switch (winnerState){
                    case 0->{
                        switch (verbosity){
                            case 1,2,3->{
                                zeroCheckWarCull_String += String.format(">> Player: %s won coin flip in war %d\n",randomWinner,allWars);
                                switch (verbosity){
                                    case 1,2->{
                                        zeroCheckWarCull_Print += String.format("""
                                ******************************************
                                   NO PLAYER HAD ENOUGH CARDS FOR WAR
                                ******************************************
                                  ** coin flip determining a winner...**
                                  
                                ** PLAYER %d HAS WON COIN FLIP AND WAR **
                                ******************************************
                                """, randomWinner);
                                    }
                                }
                            }
                        }
                    } // coin flip winner
                    case 1->{
                        switch (verbosity){
                            case 1,2,3->{
                                zeroCheckWarCull_String += String.format("""
                            >> %s were out of cards
                            >> Player: %s automatically won war %d in round: %d
                            """,loserCount, randomWinner,allWars,rounds);
                                switch (verbosity){
                                    case 1,2->{
                                        zeroCheckWarCull_Print += String.format("""
                                    ******************************************
                                      *** other players are out of cards ***
                                      !! %s were out of cards !!
                                      
                                      Player: %s automatically won the war!
                                    ******************************************
                                    """,loserCount, randomWinner);
                                    }

                                }
                            }
                        }

                    } // default winner
                }
            } // case 0: player wins a coin flip | case 1: player wins by default
            case 2->{
                // how many players going to war
                int playersLeft = warLordsToCheck.getPlayerArrayList().size();

                if(losers == null) { // if all warlords still have cards
                    switch (verbosity) {
                        case 1, 2, 3 -> {
                            zeroCheckWarCull_String += String.format("""
                                    >> All %s players survived zero-deck cull
                                       and are going to war %d in round %s
                                    \s""", playersLeft, allWars, rounds);
                            switch (verbosity) {
                                case 1,2 -> {
                                    zeroCheckWarCull_Print += String.format("""
                                            ** All %s players have enough cards to **
                                                        ** go to war **
                                            """, playersLeft);
                                }
                            }
                        }
                    }
                }      // all warlords are going to war
                else{
                    switch (verbosity) {
                        case 1, 2, 3 -> {
                            zeroCheckWarCull_String += String.format(">> %s removed in culling before war %d\n   in round: %d\n",loserCount, allWars, rounds);
                            switch (verbosity) {
                                case 1,2 -> {
                                    zeroCheckWarCull_Print = String.format("""
                                    ******************************************
                                    !! %s have no cards left for war !!
                                    !! and were removed before war in round %d !!
                                    
                                    %d players are still going to war
                                    ******************************************
                                    """,loserCount,rounds, playersLeft);
                                }
                            }

                        }
                    }
                } // some warlords are going to war
            } // players are going to war
        }
    }

    private void setWarWinStrings (int winner, Card winningCard){
        switch(verbosity){
            case 1,2 ->{
                warDrawWinner_Print += String.format("""
                                ******************************************
                                The winner of War %d is player: %d
                                The winning card is: %s
                                ******************************************
                                """, allWars, winner, winningCard);

                warDrawWinner_String += String.format("""
                                ----------------------------
                                Player: %d won War %d
                                ----------------------------
                                """, winner, allWars);
            }
            case 3 ->{
                warDrawWinner_String += String.format("""
                                ----------------------------
                                Player: %d won War %d
                                ----------------------------
                                """, winner, allWars);
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void setRoundEndStrings (ArrayList<Player> endingPlayerList){
        String toRoundEndString = "player(s): - ";
        String toRoundEndPrint = "PLAYER(s): - ";
        int notifier = 0;

        for ( var player : endingPlayerList){
            if (player.size() == 0){
                notifier += 1;
                int loserNumber = player.getPlayerNumber();
                switch(verbosity){
                    case 1,2,3 ->{
                        toRoundEndString += String.format("%d - ", loserNumber);

                        switch (verbosity){
                            case 1,2 ->{
                                toRoundEndPrint += String.format("%d - ", loserNumber);

                            }
                        }
                    }
                }
            }
        }

        if (notifier > 0) {
            switch (verbosity) {
                case 1, 2, 3 -> {
                    roundEnd_String += String.format("""
                            >> %d players had 0 cards at and of round %d
                            %s
                            were removed
                            """, notifier, rounds, toRoundEndString);
                    switch (verbosity) {
                        case 1, 2 -> {
                            roundEnd_Print += String.format("""
                                    ******************************************
                                                !!  %d PLAYERS  !!
                                              !! ARE OUT OF CARDS !!
                                            
                                    %S
                                    WERE REMOVED AT THE END OF ROUND %d
                                    ******************************************
                                    """, notifier,toRoundEndPrint, rounds);

                        }
                    }
                }
            }
        }

    }

    private void RoundSummary (ArrayList<Player> listSummary){


        switch (verbosity){
            case 1->{
                String displayString = "";
                String deckState = playerDeckState(listSummary);
                displayString += drawPhase_Print;
                displayString += roundDrawWinner_Print;
                displayString += warStartReport_Print;
                displayString += zeroCheckCull_Print;
                displayString += warDrawPhase_Print;
                displayString += zeroCheckWarCull_Print;
                displayString += warDrawWinner_Print;
                displayString +=  roundEnd_Print;
                displayString += "====================== End Round ======================\n";
                displayString += deckState;
                System.out.println(displayString);
            }
            case 2->{
                if(rounds%50 == 0){
                    String displayString = "";
                    String deckState = playerDeckState(listSummary);
                    displayString += drawPhase_Print;
                    displayString += roundDrawWinner_Print;
                    displayString += warStartReport_Print;
                    displayString += zeroCheckCull_Print;
                    displayString += warDrawPhase_Print;
                    displayString += zeroCheckWarCull_Print;
                    displayString += warDrawWinner_Print;
                    displayString += "====================== End Round ======================\n";
                    displayString += deckState;
                    System.out.println(displayString);
                }

            }
        }
        clear();
    }

    private void clear(){
        drawPhase_Print         = "";
        roundDrawWinner_Print   = "";
        warStartReport_Print   = "";
        zeroCheckCull_Print  = "";
        warDrawPhase_Print      = "";
        zeroCheckWarCull_Print  = "";
        warDrawWinner_Print      = "";
        roundEnd_Print = "";

    }
    private void printGameSummary (){
        String displayString = "";
        //String deckState = playerDeckState(listSummary);
        displayString +=        drawPhase_String;
        displayString +=  roundDrawWinner_String;
        displayString +=   warStartReport_String;
        displayString +=    zeroCheckCull_String;
        displayString +=     warDrawPhase_String;
        displayString += zeroCheckWarCull_String;
        displayString +=    warDrawWinner_String;
        displayString +=         roundEnd_String;
        //displayString += "====================== End Round ======================\n";
        //displayString += deckState;
        System.out.println(displayString);
    }
    //******************************************** START *************************************************************
    //********************************************* END **************************************************************
    // method takes a player number and list of player. Returns player with that number
    private Player getPlayer(ArrayList<Player> listToParse, int playersNumber){
        for(var player : listToParse){
            if (player.getPlayerNumber() == playersNumber){
                return player;
            }
        }
        return null;
    }
    // method takes a player object and list of players. Returns players index location
    private int getPlayerIndex(ArrayList<Player> listToParse, Player playerToFind){
        int playerIndex = -1; // -1 to denote error: couldnt find player
        for (var player : listToParse){
            if (player.getPlayerNumber() == playerToFind.getPlayerNumber()){
                playerIndex = listToParse.indexOf(player);
            }
        }
        return playerIndex;
    }
    // method takes a player object and player list. replaces original player with new player, of the same number
    private ArrayList<Player> replacePlayer(ArrayList<Player> listToParse, Player playerReplacement){
        for(var player : listToParse){
            if (player.getPlayerNumber() == playerReplacement.getPlayerNumber()){
                listToParse.set(listToParse.indexOf(player), playerReplacement);
                return listToParse;
            }
        }
        return null;
    }
    //******************************************** START *************************************************************
    // draws players cards
    private ArrayList<Player> DrawCards
    (ArrayList<Player> currentPlayerList, int warTracker ){
        // remove players who have no cards (by the time players are placed in here, !! they should have already been removed !!
        currentPlayerList.removeIf(player -> player.size() == 0); // if this doesn't work use below code

        int errorCheck1 = 0;
        int errorCheck2 = 0;

        // if after the players are removed, there are 1 or 0 players left, return the list
        if ( (currentPlayerList.size() == 0) || (currentPlayerList.size() == 1) ){
            System.out.println("!!! Error: Round tried to start with only one player !!!");
            errorCheck1 += 1; }
        // check to see if any player has zero cards in their deck, if they do, an error check will display
        // remove them and return list !!! this should not happen !!!
        for (var player : currentPlayerList) {
            if (player.size() == 0) {
                System.out.println("!!! Error: Round tried to start with a zero-cards-player !!!");
                errorCheck2 += 1;
            }
        }
        // return list if errors above
        if ( errorCheck1 != 0 || errorCheck2 != 0){
            return currentPlayerList;
        }


        // if there are more than 1 players left after culling
        else if (currentPlayerList.size() > 1){
            //if this is a normal round
            rounds += 1; // add a step


            // create warchest (arraylist of tuples containing the drawn card and the player # of the player that drew the card
            ArrayList<playerCardTuple<Card, Integer>> warChest = new ArrayList<>();
            for (var player : currentPlayerList){                       // for every player in list
                playerCardTuple<Card, Integer> Card_PlayerNum_Tuple =   // create a tuple of Card object and integer value
                        new playerCardTuple<>(player.dealCard(),player.getPlayerNumber()); // store drawn card and players #
                warChest.add(Card_PlayerNum_Tuple);                     // add that tuple pair to warChest
            }
            switch (verbosity){
                case 1,2,3->{
                    setDrawPhase_Strings(warChest);
                }
            }
            // currentPlayerList and war chest are sent to primordialWarChest where highest card(s)
            // are selected. If only one highest card, then warChest goes to winning player
            // if multiple cards are highest, then primordialWarChest sends players, and warChest to war
            currentPlayerList = primordialWarChest(currentPlayerList, warChest); //<- do this before handling printing


            //after updating currentPlayerList, remove any players that lost all their cards
            //make a sting about it
            switch (verbosity){
                case 1,2,3->{
                    setRoundEndStrings(currentPlayerList);
                }
            }


            currentPlayerList.removeIf(player -> player.size() == 0);
            // !!! make print select here to remove players that didnt have any cards after round !!!

            // switch case for print
        }
        if (warsInRound > 0){
            warsInRoundCount += warsInRound;
            warsInRound = 0;
            if (tripWar>0){
                tripWarCount += tripWar;
                tripWar = 0;
            }
        }



        return currentPlayerList;
    }
    //********************************************* END **************************************************************
    //******************************************** START *************************************************************
    //determines if there will be a war or if one player won
    private ArrayList<Player> primordialWarChest
    (ArrayList<Player> incomingPlayerArrayList, ArrayList<playerCardTuple<Card,Integer>> primordialWarChest){
        // contains cards drawn from DrawCards
        // this is where we will temporarily store cards not going to war
        List<Card> primordialChest =new ArrayList<>();
        // list containing tuples of players who are going to war
        ArrayList<playerCardTuple<Card,Integer>> avantChest = new ArrayList<>();

        Card CardTracker = new Card(0,"");  // initialize Card Object tracker
        int playerTracker       = 0;        // contains players # of player who had the highest card
        int highestCardValue    = 0;        // keeps track of the highest card value
        int pCount              = 0;        // keeps track of the number of players with the same card value

        for(var cardPlayerTuple : primordialWarChest){          // for every tuple
            Card playersCard = cardPlayerTuple.getCard();       // get players card
            int valueOfCard = playersCard.getValue();           // get value of card
            int playersNumber = cardPlayerTuple.getPlayer();    // get # of player who dealt card
            primordialChest.add(playersCard);                   // add copy of card into Deque


            if (valueOfCard > highestCardValue){    // if the value of card is higher than previous highest value
                pCount = 0;                         // pCount is reset
                highestCardValue = valueOfCard;     // highest value is set as the value of the card
                playerTracker = playersNumber;      // cardTracker is set to player # of whose card this belongs to
                CardTracker = playersCard;          // CardTracker object keeps track of the winning card
                avantChest.clear();                 // remove players who previously had the highest cards
            }
            else if ( valueOfCard == highestCardValue){ // if the value of the card is the same as the highest value
                CardTracker = playersCard;              // CardTracker Object keeps track of card that starts war
                if (avantChest.size() == 0 ){           // if this card and its dealer haven't already been indexed
                    for (var cardPlayerTuple2 : primordialWarChest){    // for every tuple pair in warChest
                        Card playersCard2 = cardPlayerTuple2.getCard(); // get the players card
                        int valueOfCard2 = playersCard2.getValue();     // get the cards value
                        int playersNumber2 = cardPlayerTuple2.getPlayer();
                        if ( valueOfCard2 == highestCardValue){    // if the cards value matched the highest value
                            avantChest.add(cardPlayerTuple2);     // add the tuple that contains the card into warLords list
                            pCount += 1;
                        }
                    }
                }
            }
        }
        ArrayDeque<Card> transientChest = new ArrayDeque<>();   // temporary chest stores copy of war cards going to war
        ArrayDeque<Card> primordialCards = new ArrayDeque<>(); // list of remaining cards, from draw, to give to winner

        for(var tuple: avantChest){     // store a copy of avantChest cards into transient chest
            Card card = tuple.getCard();
            transientChest.add(card);
        }
        for(var card : primordialChest){
            if(!transientChest.contains(card)){
                primordialCards.add(card);
            }
        }
        if (pCount > 1){ // if there are 2 or more players in warLordsPNum
            // make string letting user know
            switch(verbosity){
                case 1,2,3 ->{
                    setWarStartReportStrings(avantChest);
                }
            }
            //System.out.println("CHEk");
            // send warlords to pregame which sends them to war
            return avantMatch(incomingPlayerArrayList, avantChest, primordialCards, false);
        }
        else{ // if only one player won the draw
            // if one player won
            for(var cardPlayerTuple : primordialWarChest){ // for every tuple pair in WarChest
                Card cardToGive = cardPlayerTuple.getCard(); // get card
                //get player whose # matches playerTracker number, and add to the bottom of his deck
                Objects.requireNonNull(getPlayer(incomingPlayerArrayList, playerTracker)).bottomAddCard(cardToGive);
                // Objects.requireNonNull this makes the statement null safe as the getPlayer function can
                // possibly return a null if it does not find the player
            }
            // makes string summary of this step to print
            switch (verbosity){
                case 1,2,3->{
                    setDrawWinStrings(playerTracker,CardTracker,getPlayer(incomingPlayerArrayList,playerTracker));

                }
            }
            return incomingPlayerArrayList;
        }
    }
    //********************************************* END **************************************************************
    //******************************************** START *************************************************************
    private ArrayList<Player> avantMatch
            (ArrayList<Player> playerListToUpdate,
             ArrayList<
                     playerCardTuple<
                             Card,
                             Integer
                             >
                     > avantChest,
             ArrayDeque<Card> primordialCards,
             boolean warTime){

        // despite avantMatch only culling players who are unable to wage war, war counts will still be added
        // since avantMatch could lead to full war between players and successive wars and/or recursive wars
        // need to take this into account

        // forge a warChest with player object copies and their drawn cards
        // send forgedChest to check for warlords that are out of cards
        // if the warlords have zero cards left to pull, remove them
        // if only one warlord remains, make him the winner
        // if no warlord has any cards left to pull, randomly chose a winner
        // return the forgedChest (should never come back empty

        // check, here, if forgedChest has one or more player
        // if only one warLord left, replace player in list with his doppelganger, return changed chest (transient chest)
        // if multiple warlords remain, send to drawDeux to recursively draw cards?

        // make a player/card object chest from Card/Integer chest
        // make warChest from avantChest
        playerCardTuple<ArrayList<playerCardTuple<Card,Integer>>,ArrayList<Player>> warChest =
                forgeChest(avantChest,playerListToUpdate);

        // send warChest to check if any warlords have no cards left to draw
        if(!warTime){
            allWars += 1;
            warCount += 1;
            warChest = zeroDeckPlayerCull(warChest, warTime);
        }else {
            allWars += 1;

            warChest = zeroDeckPlayerCull(warChest, warTime);
        }
        //zeroDeckCull should always return a warChest with AT LEAST 1 warlord left
        if(warChest.getPlayerArrayList().size() ==1){   // if there is only one warLord after culling
            Player pyrrhicVictor = warChest.getPlayerArrayList().get(0);        // get warLord as pyrrhicVictor
            int spoilsOfWar = warChest.getArrayList_TupOf_CardAndPNum().size(); // get # of cards in warChest

            for(int i = spoilsOfWar -1; i >= 0 ; i--){
                Card toVictor = warChest.getArrayList_TupOf_CardAndPNum().get(i).getCard(); // get every card in warChest
                pyrrhicVictor.bottomAddCard(toVictor);                                      // give to pyrrhicVictor
            }// maybe set card to Null after returning it, in tuples getCard method? would this save on space?
            // pyrrhicVictor should now have all the cards from warChest in his deck

            int amountCount = primordialCards.size();

            for(int i = 0; i< amountCount; i++){
                pyrrhicVictor.bottomAddCard(primordialCards.remove()); // give the victor the remaining cards from initial draw
            }
            for(var player : playerListToUpdate){                                   // for every player in original list
                int playersLocation = getPlayerIndex(playerListToUpdate,player);    // get players index location
                if (player.getPlayerNumber() == pyrrhicVictor.getPlayerNumber()){   // if player # matches pyrrhicVictor's #
                    playerListToUpdate.set(playersLocation, pyrrhicVictor);         // replace player in list with pyrrhicVictor
                }
            }
        } else if(warChest.getPlayerArrayList().size() > 1){
            playerCardTuple<ArrayList<Player>,Player> playerListAndWinner = drawDeux(warChest);
            System.out.println("!!! T E S T I N G - 1 !!!");
            // winner of war becomes pyrrhic victor
            Player pyrrhicVictor = playerListAndWinner.getPlayer();
            int amountCount = primordialCards.size();
            for(int i =0; i< amountCount; i++){
                pyrrhicVictor.bottomAddCard(primordialCards.remove()); // for every card in primordial cards, give card to victor
            }
            // integrate winner
            for(var player : playerListToUpdate){                                   // for every player in original list
                int playersLocation = getPlayerIndex(playerListToUpdate,player);    // get players index location
                if (player.getPlayerNumber() == pyrrhicVictor.getPlayerNumber()){   // if player # matches pyrrhicVictor's #
                    playerListToUpdate.set(playersLocation, pyrrhicVictor);         // replace player in list with pyrrhicVictor
                }
            }
        }
        return playerListToUpdate;
    }
    //********************************************* END **************************************************************
    //******************************************** START *************************************************************
    // makes a tuple of player and cards for a complete comprehensive warChest
    // this should have been done to begin with but I would have to re-write a lot of code
    private playerCardTuple<ArrayList<playerCardTuple<Card, Integer>>,ArrayList<Player>>
    forgeChest(ArrayList<playerCardTuple<Card, Integer>> avantMatch, ArrayList<Player> allCurrentPlayersList){
        // takes list of player and a
        // tuple of < player numbers, drawn cards>
        // creates a tuple with a list of the players whose number matches the numbers in <Card,Int> tuple
        // and the <Card, Int> tuple in args

        // make lists to place all the warlords and their cards into
        // ArrayList<Card> theirCards = new ArrayList<>();
        ArrayList<Player> warLords = new ArrayList<>();

        // for every tuple in avantMatch, get the player number in tuple and use is to get the
        // player with the same number. Then add that player to warLords.
        for ( var tuple : avantMatch ){warLords.add(getPlayer(allCurrentPlayersList, tuple.getPlayer()));}
        return new playerCardTuple<>(avantMatch, warLords);
    }
    //********************************************* END **************************************************************
    //******************************************** START *************************************************************
    // looks for and removes warlords that are out of cards, before war, and removes them altogether
    private playerCardTuple<ArrayList<playerCardTuple<Card,Integer>>,ArrayList<Player>>
    zeroDeckPlayerCull( playerCardTuple<ArrayList<playerCardTuple<Card, Integer>>, ArrayList<Player>> warLordsToCheck, boolean warTime ){
        // collection.removeIf would be nice if i didn't have to do additional stuff in loop :<
        //warLordsToCheck.getPlayer().removeIf(warlord -> warlord.size() == 0);

        // keep track of all warlords
        int winnerCount = warLordsToCheck.getArrayList_TupOf_CardAndPNum().size();
        // keep track of warlords that were removed
        ArrayList<Integer> losers = new ArrayList<>();

        for(var warlord : warLordsToCheck.getPlayerArrayList()){ // for every warlord in warlord list
            if (warlord.size() == 0){                            // if he has no cards
                winnerCount -= 1;                                // subtract 1 from the count
                //warLordsToCheck.getPlayer().remove(warlord);
            }
        } // count players with no cards in deck


        if(winnerCount == 0){ // if winner count is 0 (all warLords have no cards)
            // shuffle warlord list
            // subtract all but one warlord (randomly pick winner)
            // return pair
            Collections.shuffle(warLordsToCheck.getPlayerArrayList()); // shuffle list
            // remove from the back, all but one player
            // this is to avoid any nullPointers
            for (int i = warLordsToCheck.getPlayerArrayList().size() - 2; i >= 0;  i--){
                losers.add(warLordsToCheck.getPlayerArrayList().get(i+1).getPlayerNumber()); // add random losers # to losers list
                warLordsToCheck.getPlayerArrayList().remove(i+1);   // remove loser
            } // remove all but 1 warlords
            // index loser #'s in losers list
            //System.out.println("!! RANDOMLY SELECTED WINNER !!");

            if(!warTime){
                switch (verbosity){
                    case 1,2,3->{setZeroCheckCullStrings(warLordsToCheck,losers,0);}
                }
            }else {
                // Print stuff for war Cull

                switch (verbosity) {
                    case 1, 2, 3 -> {
                        setZeroCheckWarCullStrings(warLordsToCheck, losers, 0);
                    }
                }
            }
        }else if (winnerCount == 1){ // if one warlord was left (warlord automatically wins)
            // collections removeIf, cull all players with no deck
            // give all cards in tuple to player
            // return pair
            // check in avantMatch if there is only one player left
            // and return list if so
            // make strings
            // warLordsToCheck.getPlayerArrayList().removeIf( warlord -> warlord.size() == 0 ); // remove all player with 0 decks
            for(var warlord : warLordsToCheck.getPlayerArrayList()){// for every warlord
                if (warlord.size() == 0){                           // if warlord has no cards left
                    losers.add(warlord.getPlayerNumber());          // add warlord to losers list
                    // warLordsToCheck.getPlayerArrayList().remove(warlord);
                }
            } // remove all warlords with 0 deck and index their #'s in losers list
            // System.out.println("!! ONLY ONE WARLORD SURVIVED THE CULLING !!");
            warLordsToCheck.getPlayerArrayList().removeIf(warlord -> warlord.size() == 0); // remove losing warlord

            if(!warTime){
                switch (verbosity){
                    case 1,2,3->{setZeroCheckCullStrings(warLordsToCheck,losers,1);}
                }
            }else {
                switch (verbosity) {
                    case 1, 2, 3 -> {
                        setZeroCheckWarCullStrings(warLordsToCheck, losers, 1);
                    }
                }
            }
        }else if (winnerCount > 1){ // if multiple players still remain (players are going to war phase)
            // collections removeIf, cull all players with no deck
            // return pair
            // check in avantMatch if there are multiple player left
            // if so, continue to pass into drawDeux() for war phase
            // System.out.println("!! BLITZ !!");
            // warLordsToCheck.getPlayerArrayList().removeIf( warlord -> warlord.size() == 0 ); // remove all player with 0 decks
            for(var warlord : warLordsToCheck.getPlayerArrayList()){// for every warlord
                if (warlord.size() == 0){                           // if warlord has no cards left
                    losers.add(warlord.getPlayerNumber());          // add warlord to losers list
                    //warLordsToCheck.getPlayerArrayList().remove(warlord); // remove warLord
                }
            } // remove all warlords with 0 deck and index their #'s in losers list
            warLordsToCheck.getPlayerArrayList().removeIf(warlord -> warlord.size() == 0); // remove warLord

            if(!warTime){
                switch (verbosity){
                    case 1,2,3->{
                        if (losers.size() == 0){setZeroCheckCullStrings(warLordsToCheck,null,2);} // if ALL warlords are warring
                        else {setZeroCheckCullStrings(warLordsToCheck,losers,2);}   // if there were some warlords with 0 cards left
                    }
                }
            }else{
                switch (verbosity){
                    case 1,2,3->{
                        if (losers.size() == 0){setZeroCheckWarCullStrings(warLordsToCheck,null,2);} // if ALL warlords are warring
                        else {setZeroCheckWarCullStrings(warLordsToCheck,losers,2);}   // if there were some warlords with 0 cards left
                    }
                }
            }
        }
        return warLordsToCheck;

    }
    //********************************************* END **************************************************************
    //******************************************** START *************************************************************
    private playerCardTuple<ArrayList<Player>,Player>
    drawDeux( playerCardTuple<  ArrayList<playerCardTuple<Card, Integer>>,   ArrayList<Player>>  transientWarChest) { // PULLED CARDS AND PLAYERS TO UPDATE, PRIMORDIAL WARCHEST STILL HOLDS THE PRIMORDIAL CARDS
        // this tuple contains all the objects needed for the war
        // extract the warlord players for double war from the transient war chest and place into their own list
        ArrayList<Player> inherited_WarLords = new ArrayList<>(transientWarChest.getPlayerArrayList());
        // extract the the original warchest from the transient war chest and place into its own list.
        // These cards will be given to the ultimate victor
        ArrayList<
                playerCardTuple<
                        Card,
                        Integer>
                > inherited_WarChest = new ArrayList<>(transientWarChest.getCard());
        // create new warChest, containing drawn cards and players that draw them
        ArrayList<
                playerCardTuple<
                        ArrayList<
                                Card
                                >,
                        Player>
                > doubleWar_Chest = new ArrayList<>();


        // repeat DrawCards with doubleWar compensation
        for (var warLord : inherited_WarLords) {             // for every warlord
            if (warLord.size() != 0){
                int iudex = warLord.size();
                ArrayList<Card> CardList = new ArrayList<>();   // make a list of cards
                if (iudex >= 4) {                                 // if that player has four or more cards
                    for (int i = 0; i < 4; i++) {
                        CardList.add(warLord.dealCard());       // warlord deals 4 cards to his card list
                    }
                } else {                                          // if warlord has less than 4 cards
                    int loopAmount = warLord.size();
                    for (int i = 0; i < loopAmount; i++) {
                        CardList.add(warLord.dealCard());       // warlord deals all their cards to their list
                    }
                }
                // once the cards have been drawn, add player and the list of his drawn cards in a card-list/Player Object
                // tuple: imperator_Armamentarium
                playerCardTuple<
                        ArrayList<Card>,
                        Player
                        > imperator_Armamentarium = new playerCardTuple<>(CardList, warLord);

                // place imperator_Armamentarium in chest for double war
                doubleWar_Chest.add(imperator_Armamentarium);   // send this to print, list of warlords and their pulled cards
            }
        }

        switch (verbosity){
            case 1, 2, 3->{
                setDoubleWarDrawPhase(doubleWar_Chest);
            }
        }
        return chestDeux(doubleWar_Chest, inherited_WarChest);
    }
    //********************************************* END **************************************************************

    private    playerCardTuple<ArrayList<Player>,Player>
    chestDeux(ArrayList<    playerCardTuple<ArrayList<Card>, Player>>  doubleWarChest,
              ArrayList<playerCardTuple<Card, Integer>> inherited_WarChest){
        // playerCardTuple<ArrayList<playerCardTuple<Card,Integer>>, ArrayList<Player>>


        ArrayList<playerCardTuple<Card,Integer>> primordialChestDeux = new ArrayList<>();

        List<Card> primordialChestTrois = new ArrayList<>(); // cards drawn in drawndeux //
        ArrayList<playerCardTuple<Card,Integer>> avantChestDeux = new ArrayList<>();


        Card iudexVenator = new Card(0,"X");                  // keeps track of winning card
        Player inimicus  =null;                    // keeps track of the winning player
        int quo = 0;                        // keeps track of the highest card value
        int pCount = 0;                     // keeps track of the number of players with the same card value

        for (var Armamentarium : doubleWarChest){                   // for every tuple in doubleWarChest array
            ArrayList<Card> cartaLiber = Armamentarium.getCard();   // get the players drawn cards
            Player adversarius = Armamentarium.getPlayer();         // get the player
            Card carta = cartaLiber.get(cartaLiber.size()-1);       // get the 4th card ( or last card, if >4 cards)
            int iudex = carta.getValue();                           // iudex takes the card value

            for(var card: cartaLiber){
                Card allCards = card; // this is redundant but also avoids changing the iteration, while iterating,
                // by updating the siz eof the collection
                playerCardTuple<Card, Integer> tup = new playerCardTuple<>(allCards, adversarius.getPlayerNumber());
                primordialChestDeux.add(tup);
                primordialChestTrois.add(allCards);                         // store copy of card in chest
            }


            if( iudex > quo){           // if the card number is higher than the previous highest value
                pCount = 0;             // pCount is reset
                quo = iudex;            // iudex decides the quo
                inimicus = adversarius; // inimicus becomes adversarius
                iudexVenator = carta;
                avantChestDeux.clear();
            }


            else if ( iudex == quo){    // if the value of the card is the same as the highest value
                iudexVenator = carta;
                inimicus = adversarius;
                pCount +=1;
                if(avantChestDeux.size() == 0){
                    for ( var Armamentarium2 : doubleWarChest ){
                        ArrayList<Card> cartaLiber2 = Armamentarium2.getCard();
                        Player adversarius2 = Armamentarium2.getPlayer();         // get the player
                        Card carta2 = cartaLiber2.get(cartaLiber.size()-1);
                        iudex = carta2.getValue();
                        if (iudex == quo){
                            playerCardTuple<Card,Integer> cardPlayerTup = new playerCardTuple<>(carta2,adversarius2.getPlayerNumber());
                            avantChestDeux.add(cardPlayerTup); // warlords that are going to war again and their cards
                            pCount +=1;

                        }
                    }
                }
            }
        }
        if(pCount > 1){ // multiples of the same card
            warsInRound += 1;
            allWars +=1;
            if (warsInRound > 2){
                tripWar +=1;
            }

            ArrayList<Player> playerListToSend = new ArrayList<>();
            for (var tuple : doubleWarChest){
                Player playerToAdd = tuple.getPlayer();
                playerListToSend.add(playerToAdd);
            }
            playerCardTuple<ArrayList<playerCardTuple<Card,Integer>>,ArrayList<Player>> transientSuccessor = new playerCardTuple<>(primordialChestDeux,playerListToSend);

            //ArrayList<Player> listToPack = avantMatch(playerListToSend, avantChestDeux,primordialCardsDeux,true);

            playerCardTuple<ArrayList<Player>,Player> listToAddInheritedWarChestTo = drawDeux(transientSuccessor);

            System.out.println("ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR");

            for (var tup : inherited_WarChest){
                Card cardToGive = tup.getCard();
                listToAddInheritedWarChestTo.getPlayer().bottomAddCard(cardToGive);
            }

            return listToAddInheritedWarChestTo; //NO

        }else{
            int giveAmount = primordialChestTrois.size();

            for (int i = 0; i<giveAmount; i++){
                inimicus.bottomAddCard(primordialChestTrois.remove(0)); // give winner all pulled cards
            }
            // give winner inherited cards
            for(var tup : inherited_WarChest){
                Card cardToGive = tup.getCard();
                inimicus.bottomAddCard(cardToGive); // give winner the previously tying cards
            }
            ArrayList<Player> updatedPlayerList = new ArrayList<>();
            // place players and winner in return list tuple
            for (var collection : doubleWarChest){
                Player updatedPlayer = collection.getPlayer();
                updatedPlayerList.add(updatedPlayer);
            }

            setWarWinStrings(inimicus.getPlayerNumber(),iudexVenator);


            ///////////////////// VERBOSITY HERE ///////////////////////

            return new playerCardTuple<>(updatedPlayerList,inimicus);
        }
    }
    public static void main (String[] args){

        War_rev6 warGame = new War_rev6();

    }

}