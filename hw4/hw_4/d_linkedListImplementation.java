package hw_4;
import java.util.Random;


public class d_linkedListImplementation<A> {


    private static class aniNode<A> {    // CHANGE THIS TO PUBLIC SO WE CAN ACCESS THESE NODES FROM OUTSIDE RIVER CLASS
        // NOW WE DONT HAVE TO USE A WHOLE LIST EVERYTIME WE WANT TO ACCESS THE NODE
        // animal node instance variables
        private A animal;
        private aniNode<A> prev;
        private aniNode<A> next;

        // constructor
        public aniNode(A animal, aniNode<A> prev, aniNode<A> next) {
            this.animal = animal;
            this.prev = prev;
            this.next = next;
        }

        public A getAnimal() {return animal;}
        public aniNode<A> getPrev() {return prev;}
        public aniNode<A> getNext() {return next;}
        public void setPrev(aniNode<A> prev) {this.prev = prev;}
        public void setNext(aniNode<A> next) {this.next = next;}
    }   //end of nested animal
    private aniNode<A> headerSentinel;
    private aniNode<A> trailerSentinel;
    private int riverSize = 0;   // number of elements
    public int size() {return riverSize;}       // returns number of animal elements in list
    public boolean isEmpty() {return riverSize == 0;}  // bool check if list is empty (list size = 0)
    public boolean isFull(int amount) {return riverSize == amount;}
    private void addBetween(A anAnimal, aniNode<A> predecessor, aniNode<A> successor) {
        aniNode<A> newest = new aniNode<>(anAnimal, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        riverSize++;
    }
    public void addFirst(A anAnimal)
    {addBetween(anAnimal, headerSentinel, headerSentinel.getNext());}

    // constructor
    public d_linkedListImplementation(int amount) {

        headerSentinel = new aniNode<>(null, null, null); // creates header sentinel
        trailerSentinel = new aniNode<>(null, headerSentinel, null); // creates trailer sentinel
        headerSentinel.setNext(trailerSentinel); // sets the successor to trailer sentinel
        for(int i = 0; i < amount; i++){
            Random rand = new Random(); // these need to be here so that each time method is called
            int chance = rand.nextInt(100); // a NEW random # is given.
            if (chance < 33) {
                Animal bearNode = new Bear();
                //log+="random object added was a: " + animalNodes.firstAnimal() + "\n";
                this.addFirst((A) bearNode);
            } else if (chance > 33 && chance < 66) {
                Animal fishNode = new Fish();
                //log+= "random object added was a: " + animalNodes.firstAnimal() + "\n";
                this.addFirst((A) fishNode);
            } else if (chance >= 66) {
                Animal nullnode = null;
                this.addFirst((A) nullnode);
        }

    }}

    //////////////
    public d_linkedListImplementation<Animal> randomAnimal(d_linkedListImplementation<Animal> animalNodes, int amount ){
        while (!animalNodes.isFull(amount)){
            Random rand = new Random(); // these need to be here so that each time method is called
            int chance = rand.nextInt(100); // a NEW random # is given.
            if (chance < 33) {
                Animal bearNode = new Bear();
                //log+="random object added was a: " + animalNodes.firstAnimal() + "\n";
                animalNodes.addFirst(bearNode);
            } else if (chance > 33 && chance < 66) {
                Animal fishNode = new Fish();
                //log+= "random object added was a: " + animalNodes.firstAnimal() + "\n";
                animalNodes.addFirst(fishNode);
            } else if (chance >= 66) {
                Animal nullnode = null;
                animalNodes.addFirst(nullnode);
                //log+= "random object added was a: " + animalNodes.firstAnimal() + "\n";
            }
            //log+="****CHECK 1****\n";
            //log+=animalNodes.riverSize + " object Nodes added\n";
        }
        //log+= "****CHECK 1****\n";
        //log+= "Finished making list. Total node count: " + animalNodes.riverSize + "\n";
        //log+= "****BEFORE ITERATION****\n";
        return animalNodes;}
    ///////////////

    public String listSummary(d_linkedListImplementation<Animal> animalNodes){
        String display = "";
        aniNode<Animal> nodeObject = animalNodes.headerSentinel; // gets node object[?] from list?
        nodeObject = nodeObject.next;   // sets the node object to sentinels successor ie start of the list
        int i           = 0;
        int totBears    = 0;
        int totFish     = 0;
        int totNulls    = 0;
        while(nodeObject!= null){  // does this work because its checking if riverRedux node
            // is equal null, instead of checking if the INSIDE the node is null???
            if (nodeObject.getAnimal()      instanceof Bear){
                //display += nodeObject.animal;
                display += "-B-";
                totBears++;
                i++;
            }else if (nodeObject.getAnimal() instanceof Fish){
                display += "-F-";
                totFish++;
                i++;
            }
            else if (nodeObject.getAnimal() == null && nodeObject != animalNodes.trailerSentinel){
                display += "-*-";
                totNulls++;
                i++;
            }
            nodeObject = nodeObject.next;
        }
        display += "\n there are "      +
                i + " total nodes:"     +
                "\n"        + totBears  +
                " Bears,\t" + totFish   +
                " Fish,\t"  + totNulls  +
                " nulls.";

        return display;}



    //**********************************************************************
    public boolean bearCheck(int totalPossible, d_linkedListImplementation<Animal> animalNodes){
        int iterator    = 0;
        int bearCount   = 0;
        aniNode<Animal> nodeObject = animalNodes.headerSentinel; // gets node object[?] from list?
        while(!(iterator == totalPossible)){
            iterator++;
            nodeObject = nodeObject.next;
            if (nodeObject.animal instanceof Bear){
                bearCount ++;
                //log += "****CHECK 2****\n";
                //log += "there are " + bearCount + " bears, currently, in the list\n";
            }
            //log +="****CHECK 2: CHECKING IF ALL BEARS****\n";
            //log += (bearCount == iterator) + "\n";
        }
        return bearCount == iterator;
    }//***********************************************************************************************************
    public void direction(d_linkedListImplementation<Animal> animalNodes){
        aniNode<Animal> nodeObject = animalNodes.headerSentinel;
        nodeObject = nodeObject.next;
        String direction = "Stay";

        while(!(nodeObject == null)){
            if ((nodeObject.getAnimal() instanceof Bear     ||
                    nodeObject.getAnimal()  instanceof Fish)    &&
                    nodeObject.getAnimal() != null){
                Random rand = new Random(); // these need to be here so that each time method is called
                int chance = rand.nextInt(100);

                if          (chance < 33) {
                    direction = "L";
                    addOrRemove(nodeObject, direction, animalNodes);
                } else if   (chance > 33 && chance < 66) {
                    direction = "R";
                    addOrRemove(nodeObject, direction, animalNodes);
                } else if   (chance >= 66) {
                    direction = "S";
                    addOrRemove(nodeObject, direction, animalNodes);
                }
            }
            nodeObject = nodeObject.next;
        }
    }//***********************************************************************************************************
    public void addOrRemove(d_linkedListImplementation.aniNode<Animal> nodeObject, String direction, d_linkedListImplementation<Animal> animalNodes){
        Animal anima = nodeObject.getAnimal();
        //log+= "****CHECK 3****\n";
        //log+= anima + " randomly moved to random direction -->" + direction + "\n";
        if (direction.equals("L")){
            ///////////////////////////////////
            if (nodeObject.getAnimal()                          instanceof Bear){//<<<
                if (nodeObject.prev.getAnimal()         instanceof Bear){
                    //log+="Bear moved into node with bear in it. Bears mate and new bear added\n";
                    animalBorn(animalNodes, new Bear());
                }else if (nodeObject.prev.getAnimal()   instanceof Fish){
                    nodeObject.prev.animal  = nodeObject.animal;
                    nodeObject.animal       = null;
                    //log+="Bear moved into node with fish in it and fish was eaten\n";
                }
            }else if (nodeObject.getAnimal()                    instanceof Fish){//<<<
                if (nodeObject.prev.getAnimal()         instanceof Bear){
                    nodeObject.animal       = null;
                    //log+="Fish moved into node with Bear in it. Fish was eaten.\n";
                }else if (nodeObject.prev.getAnimal()   instanceof Fish){
                    //log+="Fish moved into node with fish in it and they mate. Fish added.\n";
                    animalBorn(animalNodes, new Fish());
                }
            }
        }else if (direction.equals("R")){
            ///////////////////////////////////
            if(nodeObject.getAnimal()                           instanceof Bear){//<<<
                if (nodeObject.next.getAnimal()         instanceof Bear){
                    //log+="Bear moved into node with bear in it. Bears mate and new bear added\n";
                    animalBorn(animalNodes, new Bear());
                }else if (nodeObject.next.getAnimal()   instanceof Fish){
                    nodeObject.next.animal  = nodeObject.animal;
                    nodeObject.animal       = null;
                    //log+="Bear moved into node with fish in it and fish was eaten\n";
                }
            }else if (nodeObject.getAnimal()                    instanceof Fish){//<<<
                if (nodeObject.next.getAnimal()         instanceof Bear){
                    nodeObject.animal       = null;
                    //log+="Fish moved into node with Bear in it. Fish was eaten.\n";
                }else if (nodeObject.next.getAnimal()   instanceof Fish){
                    //log+="Fish moved into node with fish in it and they mate. Fish added.\n";
                    animalBorn(animalNodes, new Fish());
                }
            }
        }else if (direction.equals("S")){
            ///////////////////////////////////
            if(nodeObject.getAnimal()                          instanceof Bear){
                //log+="Lazy bear stayed in node\n";
            }else if (nodeObject.getAnimal()                   instanceof Fish){
                //log+="Lazy fish stayed in node\n";
            }
        }
    }//***********************************************************************************************************
    /**
     * parses list. loop iterates nodes and stops once it finds a null
     * @param animalNodes
     * @param animalToAdd
     */
    public void animalBorn ( d_linkedListImplementation<Animal> animalNodes, Animal animalToAdd ){
        d_linkedListImplementation.aniNode<Animal>  nodeObject = animalNodes.headerSentinel;
        d_linkedListImplementation.aniNode<Animal>  nodeObject2 = animalNodes.headerSentinel;
        nodeObject = nodeObject.next;
        nodeObject2 = nodeObject2.next;
        int counter = 1;
        while(nodeObject.next != animalNodes.trailerSentinel){
            counter ++;
            nodeObject = nodeObject.next;
        }
        for(int i = 0; i < counter; i++){
            if(nodeObject2.getAnimal() == null){
                nodeObject2.animal = animalToAdd;
                i += 100;
            }
            nodeObject2 = nodeObject2.next;
        }
    }//***********************************************************************************************************
    public String iterate(int nodeAmount, d_linkedListImplementation<Animal> animalNodes){
        //String riverDisplay = "";
        String riverDisplay = "done";
        //String iterationCount;
        int iterationNum = 0;

        while(!animalNodes.bearCheck(nodeAmount, animalNodes)){
            iterationNum++;
            animalNodes.direction(animalNodes);
            //riverDisplay += "==== Iteration number: "           +
                    //iterationNum    + " ====\n"              +
                    //animalNodes.listSummary(animalNodes)    + "\n\n";
            //log += "\n";
        }
        //iterationCount = "---IT TOOK "  +
                //iterationNum    +
                //" ITERATIONS FOR BEARS TO FILL RIVER.---";
        //riverDisplay += iterationCount;
        return riverDisplay;}
    //**********************************************************************


    public static void main(String[] args){


        long d_listStartTime = System.currentTimeMillis();
        d_linkedListImplementation<Animal> animaNodes = new d_linkedListImplementation(160000);
        //animaNodes.randomAnimal(animaNodes,5000);
        long d_listEndTime = System.currentTimeMillis();
        long d_listTime = d_listEndTime - d_listStartTime;


        long iterateStartTime = System.currentTimeMillis();
        String fin = animaNodes.iterate(animaNodes.riverSize, animaNodes);
        long iterateEndTime = System.currentTimeMillis();
        long iterateTime = iterateEndTime - iterateStartTime;
        System.out.println(fin);




        //long printStartTime = System.currentTimeMillis();
        //System.out.println(animaNodes.listSummary(animaNodes));
        //System.out.println(animaNodes.listSummary(animaNodes));
        //long printEndTime = System.currentTimeMillis();
        //long printTime = printEndTime - printStartTime;

        System.out.println("initializing d_list Time: " + d_listTime + "ms");
        System.out.println("Iteration Time: " + iterateTime + "ms");
        //System.out.println("Print time: " + printTime + "ms");



    }



}


