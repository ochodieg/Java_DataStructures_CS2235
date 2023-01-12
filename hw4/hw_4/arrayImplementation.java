package hw_4;
import java.util.Random;


public class arrayImplementation {


    private Animal[][] animals;
    private Random rand;
    private int length;
    private int height;
    private int totalBears = 0;
    private int totalFish = 0;
    private int totalNulls = 0;

    public int size = 320000;

    /**
     * Default Constructs new river instance.
     */
    public arrayImplementation() {
        int totalAnimals = size;

        length = 20;
        height = 10;

        if (totalAnimals > 100) {
            height = totalAnimals / length;
            animals = new Animal[length][height];
        } else if (totalAnimals == 100) {
            length = 10;
            animals = new Animal[length][height];
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < length; x++) {
                rand = new Random();
                int chance =
                        rand.nextInt(100);

                if (chance <= 33) {
                    animals[x][y] = new Bear();
                } else if (chance <= 66) {
                    animals[x][y] = new Fish();
                } else {
                    animals[x][y] = null;
                }
            }
        }
    }


    public String riverSummary() {
        String summary = "";
        int currentBears = 0;
        int currentFish = 0;
        int currentNulls = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < length; x++) {
                if (animals[x][y]
                        instanceof Bear) {
                    summary += "-Bear-";
                    currentBears++;
                    //totalBears++;
                } else if (animals[x][y]
                        instanceof Fish) {
                    summary += "-Fish-";
                    currentFish++;
                    //totalFish++;
                } else if (animals[x][y]
                        == null) {
                    summary += "------";
                    currentNulls++;
                    //totalNulls++;
                }
            }
            summary += "\n";
        }


        totalBears = currentBears;
        totalFish = currentFish;
        totalNulls = currentNulls;
        summary += "\nBears: " + totalBears + "\t\tFish left: " + totalFish + "\t\tNulls: " + totalNulls + "\n";
        return summary;
    }

    public void move(){
        for ( int y = 0; y < this.height; y++ ){
            for (int x = 0; x < this.length; x++){

                int direction = rand.nextInt(3) - 1;
                int newSpot = x + direction;
                if (newSpot < this.length && newSpot > 0){

                    if (animals[newSpot][y] == null){
                        animals[newSpot][y] = animals[x][y];
                        animals[x][y]       = null;
                    }else if(animals[newSpot][y]    instanceof Bear
                            && animals[x][y]        instanceof Bear){
                        addAnimal(new Bear());

                    }else if(animals[newSpot][y]    instanceof Fish
                            && animals[x][y]        instanceof Fish){
                        addAnimal(new Fish());

                    }else if (animals[newSpot][y]   instanceof Bear
                            && animals[x][y]        instanceof Fish) {
                        animals[x][y]       = null;
                    }else if (animals[newSpot][y]   instanceof Fish
                            && animals[x][y]        instanceof Bear){
                        animals[newSpot][y] = null;
                    }
                }
            }
        }
    }

    public void addAnimal(Animal newAnimal) {
        int allAnimals = this.totalBears + this.totalFish;
        int randomLength;
        int randomHeight;
        int i = 0;

        if (allAnimals < (this.height * this.length)) {
            while (i < 1) {
                randomLength = rand.nextInt(this.length);
                randomHeight = rand.nextInt(this.height);
                if (animals[randomLength][randomHeight] == null) {
                    animals[randomLength][randomHeight] = newAnimal;
                }
                i++;
            }
        }
    }


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

        return allBears;
    }





    public static void main(String[] args){
        int loop = 1;
        int amountReq = 1;

        long arrayStartTime = System.currentTimeMillis();
        arrayImplementation test = new arrayImplementation();
        long arrayEndTime = System.currentTimeMillis();
        long arrayTime = arrayEndTime - arrayStartTime;


        long iterateStartTime = System.currentTimeMillis();
        while(!test.bearCheck()){
            test.move();
        }
        long iterateEndTime = System.currentTimeMillis();
        long iterateTime = iterateEndTime - iterateStartTime;

        //long printStartTime = System.currentTimeMillis();
        //System.out.println(test.riverSummary());
        //long printEndTime = System.currentTimeMillis();
        //long printTime = printEndTime - printStartTime;

        System.out.println("initializing array Time: " + arrayTime + "ms");
        //System.out.println("Print time: " + printTime + "ms");
        System.out.println("iterate Time: " + iterateTime + "ms");



    }
}



