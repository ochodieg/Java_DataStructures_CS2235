package HW_2_3;
import java.util.Arrays;
import java.util.Random;    // import used for random numbers
import java.util.Scanner;   // import used for user input
// import javax.swing.Timer;
// import java.util.Timer;

class Bear extends Animal{} // bear object inherits from animal class
class Fish extends Animal{} // Fish object inherits from animal class

public class River {
    private static Scanner  riverSize = new Scanner(System.in);
                                                // scanner object reference variable for input
    private Animal[][]      animals;            // object array reference variable
    private Random          rand;               // random object reference variable
    private int             length;             // length of array. Kept as global for scanner to manipulate
    private int             height;             // height of array. Kept as global for scanner to manipulate
    private int             totalBears  =   0;  // keeps track of Bears in array ea. iteration
    private int             totalFish   =   0;  // keeps track of Fish in array ea. iteration
    private int             totalNulls  =   0;  // keeps track of nulls in array ea. iteration
                                                // last 3  are kept as global variables so that -
                                                // - multiple functions can modify
    // constructor
    /** Default Constructs new river instance.
     */
    public River() {
        int totalAnimals = this.getSize();          // variable invokes getSize() method to set size of
                                                    // animals array
        length = 20;                                // instance variable limits length of array
        height = 10;                                // instance variable limits lowest height to 10
                                                    // and is as divisor for larger amounts
        if (totalAnimals > 100) {                   // sets array size for larger arrays
            height  = totalAnimals / length;
            animals = new Animal[length][height];
        } else if ( totalAnimals == 100 ) {         // sets array size for arrays of 100
            length  = 10;
            animals = new Animal[length][height];
        }

        for     (int y = 0; y < height; y++) {      // nested for loop goes through each cell to randomly
            for (int x = 0; x < length; x++) {      // determine what cell will be filled with
                rand = new Random();                // new random object sets a random
                int chance =                        // number between 0 - 99, every iteration
                        rand.nextInt(100);

                if (chance <= 33) {                 // ~30% chance that current cell
                    animals[x][y] = new Bear();     // will be filled with new Bear object
                } else if (chance <= 66) {          // ~30% chance that current cell
                    animals[x][y] = new Fish();     // will be filled with new Fish object
                } else {                            // ~30% chance that current cell
                    animals[x][y] = null;           // will be null
                }
            }
        }
    }

    /**
     * Requests user input data to set array size
     * @return  arraySize = int value used to set array size
     */
    public int getSize() {
        String chosenSize = riverSize.next();   // reference variable instantiates[?] new scanner object
                                                // variable initialized[?] as String variable, setting the
                                                // data type the input will be. '.next()' invokes 'next()' method
                                                // returns "next" element
        int arraySize = 0;                      // initialized instance variable

        while ( !chosenSize.equals("big")   &&  // while loop requests correct input.
                !chosenSize.equals("small") &&  // conditions -> input must be either: "big", or "small",
                !chosenSize.equals("normal"))   // or "normal"
        {                                       // message will loop if condition not met
            System.out.println("please type a given size");
            chosenSize = riverSize.next();      // updates every loop
        }
        switch (chosenSize) {                   // switch case -> enhanced switch case. itemcode: chosenSize
            case "big" -> arraySize = 500;      // sets arraySize
            case "normal" -> arraySize = 300;
            case "small" -> arraySize = 100;
        }
        return arraySize;
    }

    /**
     *
     * @return Summary = a string to display array each iteration
     */
        public String riverSummary () {
            String summary      = "";
            int currentBears    = 0;
            int currentFish     = 0;
            int currentNulls    = 0;
            for (int y = 0; y < height; y++) {      // nested loop goes through every array cell
                for (int x = 0; x < length; x++) {
                    if (        animals[x][y]
                            instanceof Bear) {      // if cell contains Bear object,
                        summary += "-Bear-";        // "-Bear-" is concatenated to return string
                        currentBears++;
                        //totalBears++;
                    } else if ( animals[x][y]
                            instanceof Fish) {      // if cell contains Fish object,
                        summary += "-Fish-";        // "-Fish-" is concatenated to return string
                        currentFish++;
                        //totalFish++;
                    } else if ( animals[x][y]
                            == null) {              // if cell is null,
                        summary += "-****-";        // "-****-" is concatenated to return string
                        currentNulls++;
                        //totalNulls++;
                    }
                }
                summary += "\n";
            }
            // variables below are outside loop so that they update and the total objects and nulls counted
            // at the end of loop, everytime method is invoked. If done within loop with class members,
            // because they are not static[?] they will update every iteration without being reset and
            // sum of objects and nulls will include all objects and nulls to have ever been displayed

            totalBears  = currentBears;
            totalFish   = currentFish;
            totalNulls  = currentNulls;
            summary += "\nBears: " + totalBears + "\t\tFish left: " + totalFish + "\t\tNulls: " + totalNulls + "\n";
            return summary;
        }

    /**
     * Unfruitful function loops through array cells and randomly
     * moves object to either left or right cell or keeps object in cell
     * - if bear object is moved into cell already occupied with bear object,
     * addAnimal method is invoked with Bear() param.
     * - if Fish object is moved into cell already occupied with Fish object,
     * addAnimal method is invoked with Fish() param.
     * - if Fish object is moved into cell already occupied with Bear object, or
     *   if Bear object is moved into cell already occupied with Fish object,
     * cell with instanceof Fish is set to null. (bear eats fish)
     */
    public void move(){
        for ( int y = 0; y < this.height; y++ ){            // nested loop iterates
            for (int x = 0; x < this.length; x++){          // through every array cell
                // currently, code randomly moves cell object in either: left, right, or no movement
                // a random int (range: 0 - 2) is subtracted by 1. The resultant int
                // stored in instance variable is either: -1, 0, or 1.
                // this value is then added to current loops X-coordinate in array
                // This either keeps coordinate or moves it by one in either direction
                // code can later be added here to accomplish the same, for the Y-coordinate.
                int direction = rand.nextInt(3) - 1;
                int newSpot = x + direction;
                if (newSpot < this.length && newSpot > 0){  // statement keeps newSpot variable within
                                                            // the bounds of the array length.
                    if (animals[newSpot][y] == null){
                        animals[newSpot][y] = animals[x][y];
                        animals[x][y]       = null;
                    }else if(animals[newSpot][y]    instanceof Bear
                            && animals[x][y]        instanceof Bear){
                        addAnimal(new Bear());              // bear mates with bear
                        // call add bear function
                        //System.out.println("new bear added");
                    }else if(animals[newSpot][y]    instanceof Fish
                            && animals[x][y]        instanceof Fish){
                        addAnimal(new Fish());              // fish mates with fish
                        // call add fish function
                        //System.out.println("new fishey");
                    }else if (animals[newSpot][y]   instanceof Bear
                            && animals[x][y]        instanceof Fish) {
                        animals[x][y]       = null;         // bear eats fish
                    }else if (animals[newSpot][y]   instanceof Fish
                            && animals[x][y]        instanceof Bear){
                        animals[newSpot][y] = null;         // bear eats fish
                    }
                }
            }
        }
    }

    /**
     * randomly selects location within bounds of array and places
     * new animal object there.
     * @param newAnimal animal object to be added
     */
    public void addAnimal(Animal newAnimal) {
        int allAnimals = this.totalBears + this.totalFish;
        int randomLength;
        int randomHeight;
        int i = 0;
        // currently, hw2 pdf states for new animal object to be randomly
        // placed in array but this code can be updated to place
        // animal object near the cells that invoke this method or
        // in specified place.
        if (allAnimals < (this.height * this.length)) {
            while (i < 1) {
                randomLength = rand.nextInt(this.length);
                randomHeight = rand.nextInt(this.height);
                if (animals[randomLength][randomHeight] == null) {
                    animals[randomLength][randomHeight] = newAnimal;
                }
                    i++;    // if this is inside if statement while will be infinite loop.
            }               // currently, loop runs once, lengthening the time it takes
        }                   // to fill river with bears. fix somehow!
    }

    /**
     * method iterates through all cells in array and returns
     * a boolean depending on whether or not all cells contain bears.
     * @return true if all array cells are bears
     */
        public boolean bearCheck() {
            boolean allBears = false;
            boolean iterator = true;
            int currentBears = 0;
            int totalPossible = this.length*this.height;
            while (iterator) {
                for (int y = 0; y < this.height; y++) {
                    for (int x = 0; x < this.length; x++) {
                        if (animals[x][y] instanceof Bear) {
                            currentBears++;
                        }
                    }
                }
                iterator = false;
                if(currentBears == totalPossible){
                    allBears = true;
                }
            }
            // this.totalBears =currentBears;
            // System.out.println(totalBears);
            return allBears;
        }

    public static void main(String[] args){
            String select = """
                    how big is your river?
                    1) small
                    2) normal
                    3) big""";
            System.out.println(select);
            River ecoSystem = new River();

            int loop = 1;
            int amountReq = 1;
            while(!ecoSystem.bearCheck()){              // loop iterates until all cells are filled w/ bears.
                System.out.println("loop iteration: "
                        + loop + "\n");
                System.out.println(ecoSystem.riverSummary());   //executes array summary every iteration
                ecoSystem.move();                               // executes move invocation, updating while condition
                amountReq++;
                loop++;
            }

        System.out.println("final iteration\n\n" + ecoSystem.riverSummary());
        System.out.println("it took " + amountReq + " loops for bears to fill the river");
        }
    }









            //****************************
            /*                           *** Test code. run in main to test stuff. ignore this***
            // System.out.println(Arrays.toString(pp.animals)); // prints every thing in array but because they all objects it no workie goodie
            for (int i = 0; i < ecoSystem.animals.length; i++) {
                System.out.println(i);
                System.out.println(Arrays.toString(ecoSystem.animals[i]));}
            for(int i = 0; i < 1000; i++) {
                System.out.println(ecoSystem.riverSummary());
                System.out.println(ecoSystem.bearCheck()); // in final code, bear check should come before summary, so that summary gets correct number of bear and fish
                ecoSystem.move();}
            */
/*
                // trying to set a timed display, just for fun
                //Timer t = new Timer (500, event -> System.out.println(ecoSystem.riverSummary()) );
                //t.start();
                // System.out.println(ecoSystem.bearCheck()); // in final code, bear check should come before summary, so that summary gets correct number of bear and fish
                //Timer timer = new Timer();
                //
                //timer.schedule( new TimerTask() {
                //    public void run() {
                //       // do your work
                //    }
                // }, 0, 60*1000);
                timer.cancel();
 */
            //*******************************









