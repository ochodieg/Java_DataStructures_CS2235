//package hw_3_copy_final;
//import java.util.Objects;
//import java.util.Random;
//import java.util.Scanner;
//
//class Bear extends Animal{}
//class Fish extends Animal{}
//class Null extends Animal{}
//// null object is used to represent null as
//// node method invocations produce nullPointExceptions
//// if node is actually null
//
//public class riverRedux <A> {
//
//
//    //private Object Bear;
//
//    //------nested animal node-------
//    private static class aniNode<A>{
//        // animal node instance variables
//        private A           animal;
//        private aniNode<A>  prev;
//        private aniNode<A>  next;
//
//        // constructor
//        public aniNode(A a, aniNode<A> prev, aniNode<A> next){
//            animal = a;
//            this.prev   = prev;
//            this.next   = next;
//        }
//        public A                    getAnimal() { return animal; }
//        public aniNode<A>           getPrev()   { return prev; }
//        public aniNode<A>           getNext()   { return next; }
//        public void setPrev(aniNode<A> prev)    { this.prev = prev; }
//        public void setNext(aniNode<A> next)    { this.next = next; }
//    }   //end of nested animal
//
//
//
//    // river instance variables
//    private static Scanner listSize = new Scanner(System.in);
//    private aniNode<A>  headerSentinel;
//    private aniNode<A>  trailerSentinel;
//    private int         riverSize = 0;   // number of elements
//    // public Random rand = new Random(); <-- if either of these are declared here, loop iterations
//    // public static Random rand;         <--with random numbers will all give the same number
//    public String log = "";
//
//
//    // river constructor
//    public riverRedux(){
//        headerSentinel = new aniNode<>(null, null, null); // creates header sentinel
//        trailerSentinel = new aniNode<>(null, headerSentinel, null); // creates trailer sentinel
//        // recall that trailerSentinel's predecessor is headerSentinel
//        headerSentinel.setNext(trailerSentinel); // sets the successor to trailer sentinel
//        // recall structure
//    }
//
//
//    // getter methods
//    public int      size()      { return riverSize; }       // returns number of animal elements in list
//    public boolean  isEmpty()   { return riverSize == 0; }  // bool check if list is empty (list size = 0)
//    public boolean isFull(int amount){
//        return riverSize == amount;
//    }
//
//    public A       firstAnimal(){
//        if (isEmpty()) return null;
//        return headerSentinel.getNext().getAnimal();
//        // returns a null if list is empty else getnext() is invoked
//        // onto the headerSentinel. Then getAnimal is invoked on the returned data
//        // notice that regardless of size being null, the sentinel is not included
//    }
//    public A             last(){
//        if (isEmpty()) return null;
//        return trailerSentinel.getPrev().getAnimal();
//    }
//
//
//    //update methods
//
//    /**
//     * this is the primary method called upon by other methods. because in a DLL
//     * every node added must have their predecessor and successor references modified,
//     * regardless of location - as all nodes between sentinels have these references -
//     * a method that adds nodes to the DLL requires those references modified and
//     * therefore calls upon this method
//     *
//     *
//     * @param anAnimal      element (or in this case animal) to be added
//     * @param predecessor   the argument that will be used in the node constructors parameter
//     *                      to set the prev variable, or nodes' predecessor. this will be a node
//     *                      of type A generic (or in this case, animal)
//     *
//     * @param successor     the argument that will be used in the node constructors parameter
//     *                      to set the next variable, or nodes' successor. this too will be a animal node
//     */
////nested methods?
//private void addBetween(A anAnimal, aniNode<A> predecessor, aniNode<A> successor){
//        aniNode<A> newest = new aniNode<>(anAnimal, predecessor, successor);
//        predecessor.setNext(newest);
//        successor.setPrev(newest);
//        riverSize++;
//        }
//
///**
// * takes an animal element then passes the argument into
// * the addBetween paramters along with the header sentinel
// * and its' successors reference. Therefore this method is
// * essentially just an override method to the addbetween method
// * as this simply invokes addbetween with parameters that add
// * element between header sentinel and whatever successor it points to
// * therefore, adds element anAnimal to the front of the list.
// *
// * @param anAnimal  takes animal element as argument
// */
//public void addFirst(A anAnimal){addBetween(anAnimal, headerSentinel, headerSentinel.getNext());
//        }
//
///**
// * adds element anAnimal to the end of the list in the same method that
// * the addFirst() method does.
// * @param anAnimal  takes animal element as argument
// */
//public void addLast(A anAnimal){addBetween(anAnimal, trailerSentinel.getPrev(), trailerSentinel);
//        }
//
///**
// * removes node given to parameter. Stores the nodes predecessor
// * and successor references in reference variables. Reference variables
// * are then used as the arguments in the parameters of their own setNext
// * and setPrev methods so that they point to each other, effectively, removing
// * the given node argument from any references from the list.
// * riverSize is then decreases by one.
// * @param nodeToRemove  the animal node to be removed
// * @return  nested node method invoked on the node
// *          argument given. This invocation returns the
// *          node's animal element (node method returns nodes element)
// */
//private A removeNode (aniNode<A> nodeToRemove){    // @org.jetbrains.annotations.NotNull
//        aniNode<A> predecessor = nodeToRemove.getPrev();
//        aniNode<A> successor   = nodeToRemove.getNext();
//        predecessor.setNext(successor);
//        successor.setPrev(predecessor);
//        riverSize--;
//        return nodeToRemove.getAnimal();
//        }
//
///** Removes and returns the first element of the list. */
//public A removeFirst( ) {
//        if (isEmpty( )) return null; // returns null if nothing to remove
//        return removeNode(headerSentinel.getNext( ));
//        // returns the first element (headers successor)
//        }
///** Removes and returns the last element of the list. */
//public A removeLast( ) {
//        if (isEmpty()) return null; // returns null if nothing to remove
//        return removeNode(trailerSentinel.getPrev());
//        // returns the last element (trailers predecessor)
//        }
//
//
//
///**
// * obtains user data to determine list size. Even though linked lists
// * don't require a set amount of nodes like arrays do with cells,
// * this value will be used to 'add' that many cells to the list
// * @return  returns a value used to set list size
// */
//public int getListSize() {
//        String chosenSize = listSize.next();   // dont forget ".next()" is required when working with scanner
//        // as it calls the scanner next method to obtain the 'next input'
//        int linkListSize = 0;                      // initialized instance variable
//
//        while ( !chosenSize.equals("big")   &&  // while loop requests correct input.
//        !chosenSize.equals("small") &&  // conditions -> input must be either: "big", or "small",
//        !chosenSize.equals("normal"))   // or "normal"
//        {                                       // message will loop if condition not met
//        System.out.println("please type a given size");
//        chosenSize = listSize.next();      // updates every loop
//        }
//        switch (chosenSize) {                   // switch case -> enhanced switch case. itemcode: chosenSize
//        case "big"    -> linkListSize = 500;      // sets arraySize
//        case "normal" -> linkListSize = 300;
//        case "small"  -> linkListSize = 100;
//        }
//        return linkListSize;
//        }
//
//
//public riverRedux<Animal> randomAnimal(riverRedux<Animal> animalNodes,int amount){
//        // rand.setSeed(System.currentTimeMillis());
//        // float chance = rand.nextFloat()*100;
//        // for some reason if I make chance a float
//        // random number will be the same everytime method is
//        // called ????
//        // riverRedux<Animal> animalNodes = new riverRedux<>();
//        // System.out.println(amount);
//        //for (int i = 1; i < amount; i++) {
//        while (!animalNodes.isFull(amount)){
//        Random rand = new Random(); // these need to be here so that each time method is called
//        int chance = rand.nextInt(100); // a NEW random # is given.
//        // Animal animal = new Animal(); // <-- oh boy, what a statement, huh?
//        // 0.33% chnace
//        // System.out.println(animalNodes.size()); // used to makes sure correct amount
//        // of nodes were added
//        //animalNodes.riverSize++;
//
//        if (chance < 33) {
//        Animal bearNode = new Bear();
//        // System.out.println(bearNode.toString());
//        // System.out.println(animalNodes.firstAnimal()); // used to check animal was added
//        animalNodes.addFirst(bearNode);
//        } else if (chance > 33 && chance < 66) {
//        Animal fishNode = new Fish();
//        //System.out.println(fishNode.toString());
//        // System.out.println(animalNodes.firstAnimal()); // used to check animal was added
//        animalNodes.addFirst(fishNode);
//        } else if (chance >= 66) {
//        //this Animal nullNode = new Null(); //<<-- used if using Null node instead of actual null
//        // System.out.println(animalNodes.firstAnimal()); // used to check null was added
//        //this animalNodes.addFirst(nullNode); //<<-- used if using Null node instead of actual null
//        //System.out.println(nullNode);
//        Animal nullnode = null;  //<<-- used if using actual null instead of Null object node
//        //System.out.println("null");
//        animalNodes.addFirst(nullnode);//<<-- used if using actual null instead of Null object node
//        // System.out.println(animalNodes.firstAnimal()); // used to check null was added
//        }
//        }
//        return animalNodes;
//        }
//
//    /*
//    public void createList(int nodeAmount, riverRedux<Animal> ecoList){
//        ecoList = new riverRedux<>();
//        for(int i = 0; i < nodeAmount; i++){
//            //Animal animalNode = ecoList.randomAnimal();
//            addFirst(ecoList.randomAnimal());
//
//        }
//    }
//
//     */
//
//
//public String listSummary(int listSize, riverRedux<Animal> animalNodes){
//        String display = "";
//        aniNode<Animal> nodeObject = animalNodes.headerSentinel; // gets node object[?] from list?
//        //nodeObject.getAnimal();
//        nodeObject = nodeObject.next;   // sets the node object to sentinels successor ie start of the list
//        //without progressing to sentinels successor, printing nodeObject.animal
//        // would yield a null (<<-- not a null object, just a null)
//        // System.out.println(nodeObject + " 0why???"); <<-- these dont print whats inside the node
//        // but rather a reference to it[????]
//
//
//
//        //System.out.println(nodeObject.animal + " 1why???");
//        //nodeObject = nodeObject.next;
//        //System.out.println(nodeObject.animal + " 2why???");
//        //System.out.println(whatever + " why???");
//
//        //Animal animInside = animalNodes.firstAnimal();
//        //animInside.getNext();<<<-- these methods will not work like this
//        // since you can not invoke them on Animal object itself, rather, Animal NODES[???]
//
//        //cant compare elements of node with the element istself as java
//        // cant know for sure that the elements are the same and will therefore
//        // assume it's not allowed. an expensive way to overcome this is by
//        // copying the list
//
//        /*
//        for(int i = 0; i < listSize; i++){
//            //aniNode<Animal> idk = nodeObject.getNext();
//            //aniNode<Bear> aear = new aniNode<>;
//            Animal whatever = nodeObject.animal;
//            if (whatever instanceof Bear){
//               display += "bear check";
//             }else if(animalNodes.firstAnimal() instanceof Fish){
//                display += "Fish check";
//             }else if ( Objects.equals(animalNodes.firstAnimal(), null)){
//                display += "null check";
//             }
//
//            nodeObject = nodeObject.next;
//            // String animal = animalNodes.getNext().getAnimal();
//
//        }
//
//         */
//        int i = 0;
//        int totBears = 0;
//        int totFish = 0;
//        int totNulls = 0;
//        while(nodeObject!= null){  // does this work because its checking if riverRedux node
//        // is equal null, instead of checking if the INSIDE the node is null???
//        //System.out.println("(" + nodeObject.getAnimal() + ")"); //<<-- used for checking nulls
//        //System.out.println("(" + nodeObject.toString() + ")") ;  //<<-- used for checking if memory usage
//        if (nodeObject.getAnimal() instanceof Bear){
//        //display += nodeObject.animal;
//        display += "-B-";
//        totBears++;
//        i++;
//        }else if (nodeObject.getAnimal() instanceof Fish){
//        display += "-F-";
//        totFish++;
//        i++;
//
//        }
//                /*
//                    }else if (nodeObject.animal instanceof Null){
//                    display += "---";
//                    i++;
//                } can be done this way if using a Null object that represents null instead of
//                actual null. This can be done and might have to be done, to check for certain issues
//                regarding nullPointerException issues, (cuz lawd knows there's not enough of those)
//                If done this way, i--; would also not be needed to give correct node amount
//                 */
//
//                /*
//                  else if (nodeObject.animal == null){
//                    display += "---";
//                    totNulls++;
//                    i++;
//                } was using actual nulls, however, this not only gives incorrect amount when
//                totBears, totFish, and totNulls are added (since it is including the trailer node as a null),
//                which really isnt a big deal as subtracting from will fix this,
//                it may also mess things up when finding and replacing nulls with bears or fish.
//                When parsing through list to find a random null to replace, it may find that the trailer
//                node meets this criteria, and will replace the trailer node with an object. obviously, I
//                don't want any sentinels being manipulated, therefore I will use a Null object as a representation
//                of a null. because ' System.out.println("(" + nodeObject.toString() + ")") ' prints memory locations
//                for all nodes (including sentinels) even though ' System.out.println("(" + nodeObject.getAnimal() + ")"); '
//                prints a memory location for all nodes that have an animal but nulls for sentinels
//                it leads me to think that nulls also use memory in the
//                same way an object node does, regardless. so if the outcome is the same, does it matter as long
//                as you don't explicitly need a node there??<<<<<<<<<<<<---------    *******ask*********
//
//                 */
//        else if (nodeObject.getAnimal() == null && nodeObject != animalNodes.trailerSentinel){
//        display += "-*-";
//        totNulls++;
//        i++;
//        }
//        nodeObject = nodeObject.next;
//
//        }
//        //direction(animalNodes); //<<--- testes random directions
//            /* no longer needed since using Null objects instead of actual nulls
//            therefore trailer node isnt included in count.
//
//
//            i--;// since the nodeObject variable is being compared to null,
//                // until nodeObject ITSELF is null, the loop will run 1 additional time
//                // accounting for the trailerSentinel. This will add 1 additional value to
//                // count iterator, therefore i--; adjusts this.
//        //totNulls--;
//             */
//
//
//        display += "\n there are " + i + " total nodes." +
//        "\n" + totBears + " Bears,\t" + totFish + " Fish,\t" + totNulls + " nulls.";
//
//        return display;
//        }
//
//public boolean bearCheck(int totalPossible, riverRedux<Animal> animalNodes){
//        boolean allBears = false;
//        //boolean iterator = true;
//        int iterator = 0;
//        int bearCount = 0;
//        aniNode<Animal> nodeObject = animalNodes.headerSentinel; // gets node object[?] from list?
//        //nodeObject.getAnimal();
//        //nodeObject = nodeObject.next;
//        while(!(iterator == totalPossible)){
//        iterator++;
//        nodeObject = nodeObject.next;
//        if (nodeObject.animal instanceof Bear){
//        bearCount ++;
//        }
//        }
//        return bearCount == iterator;
//        }
//
//
//
//
//
//
//
//public void direction(riverRedux<Animal> animalNodes){
//        //riverRedux<Animal> parse = animalNodes;
//        aniNode<Animal> nodeObject = animalNodes.headerSentinel;
//        nodeObject = nodeObject.next;
//        //riverRedux.aniNode<Animal> nodeObject2 = nodeObject.getNext();
//        // Animal nextanimal = nodeObject.next.animal;
//        int iteration = 1;
//        String toLog = "";
//        String direction = "Stay";
//
//        while(!(nodeObject == null)){
//        // toLog += "\nAnimal #" + iteration + " is a " + nodeObject.animal + "\n";
//        if ((nodeObject.getAnimal() instanceof Bear ||
//        nodeObject.getAnimal() instanceof Fish) &&
//        nodeObject.getAnimal() != null){
//        Random rand = new Random(); // these need to be here so that each time method is called
//        int chance = rand.nextInt(100);
//
//        if (chance < 33) {
//        direction = "L";
//        /*toLog +=*/ addOrRemove(nodeObject, direction, animalNodes);
//        // nodeObject.getAnimal() <- use for method
//        //Animal bearNode = new Bear();
//        //animalNodes.addFirst(bearNode);
//        } else if (chance > 33 && chance < 66) {
//        direction = "R";
//        /*toLog +=*/ addOrRemove(nodeObject, direction, animalNodes);
//        //Animal fishNode = new Fish();
//        //animalNodes.addFirst(fishNode);
//        } else if (chance >= 66) {
//        direction = "S";
//        /*toLog +=*/ addOrRemove(nodeObject, direction, animalNodes);
//        //Animal nullnode = null;
//        //animalNodes.addFirst(nullnode);
//        }
//        }
//        nodeObject = nodeObject.next;
//        }
//        //return toLog;
//        }
//
//
//
//
//
//
//
//
//
//
//
//public void addOrRemove(riverRedux.aniNode<Animal> nodeObject, String direction, riverRedux<Animal> animalNodes){
//        Animal anima = nodeObject.getAnimal();
//        /* System.out.println(anima + " direction -->" + direction);
//        use --> animalNodes.direction(animalNodes); in main to check random directions
//        works correctly
//         */
//        String result = "";
//
//        if (direction.equals("L")){
//        //#####################
//        if (nodeObject.getAnimal()                          instanceof Bear){//<<<
//
//        if (nodeObject.prev.getAnimal()         instanceof Bear){
//        /*result+=*/System.out.println("bears mate. bear added");
//        // randomly add bear
//        animalBorn(animalNodes, new Bear());
//        }else if (nodeObject.prev.getAnimal()   instanceof Fish){
//        nodeObject.prev.animal = nodeObject.animal;
//        nodeObject.animal = null;
//        /*result+=*/System.out.println("bear moved and fish eaten");
//        }
//        }else if (nodeObject.getAnimal()                    instanceof Fish){//<<<
//
//        if (nodeObject.prev.getAnimal()         instanceof Bear){
//        nodeObject.animal = null;
//        /*result+=*/System.out.println("fish tried to move but was eaten");
//        }else if (nodeObject.prev.getAnimal()   instanceof Fish){
//        /*result+=*/System.out.println("fish mate. fish added");
//        // randomly add fish
//        animalBorn(animalNodes, new Fish());
//        }
//        }
//
//        }else if (direction.equals("R")){
//        //###########################
//        if(nodeObject.getAnimal()                           instanceof Bear){//<<<
//
//        if (nodeObject.next.getAnimal()         instanceof Bear){
//        /*result+=*/System.out.println("bears mate. bear added");
//        // randomly add bear
//        animalBorn(animalNodes, new Bear());
//        }else if (nodeObject.next.getAnimal()   instanceof Fish){
//        nodeObject.next.animal = nodeObject.animal;
//        nodeObject.animal = null;
//        /*result+=*/System.out.println("bear moved and fish eaten");
//        }
//        }else if (nodeObject.getAnimal()                    instanceof Fish){//<<<
//
//        if (nodeObject.next.getAnimal()         instanceof Bear){
//        nodeObject.animal = null;
//        /*result+=*/System.out.println("fish tried to move but was eaten");
//        }else if (nodeObject.next.getAnimal()   instanceof Fish){
//        /*result+=*/System.out.println("fish mate. fish added");
//        // randomly add fish
//        animalBorn(animalNodes, new Fish());
//        }
//        }
//
//        }else if (direction.equals("S")){
//        //###########################
//        if(nodeObject.getAnimal() instanceof Bear){
//        /*result+=*/System.out.println("bear stayed");
//        }else if (nodeObject.getAnimal() instanceof Fish){
//        /*result+=*/System.out.println("fish stayed");
//        }
//        }
//        //return result;
//        }
//
//
//
//
//
//
//
//
//
//
//
//
///**
// * parses list. loop iterates nodes and stops once it finds a null
// * @param animalNodes
// * @param animalToAdd
// */
//public void animalBorn ( riverRedux<Animal> animalNodes, Animal animalToAdd ){
//        riverRedux.aniNode<Animal>  nodeObject = animalNodes.headerSentinel;
//        riverRedux.aniNode<Animal>  nodeObject2 = animalNodes.headerSentinel;
//        //aniNode<Animal> trailer = animalNodes.trailerSentinel;
//        nodeObject = nodeObject.next;
//        nodeObject2 = nodeObject2.next;
//
//        int counter = 1;
//        //riverRedux.aniNode<Animal> nodeObject
//
//        /**
//         if ((nodeObject != animalNodes.headerSentinel || nodeObject != animalNodes.trailerSentinel) &&
//         (nodeObject != null)){
//         while((nodeObject.animal != null) || nodeObject.next != animalNodes.trailerSentinel){ // && (nodeObject.getNext() != trailer)){
//         System.out.println("PASSED A " + nodeObject.animal); //<< used to test while loop
//         nodeObject = nodeObject.next;
//         }
//
//
//         System.out.println("stopped at " + nodeObject.animal); //<< used to test while loop
//         nodeObject.animal = animalToAdd;
//
//
//         }
//         */
//
//        while(nodeObject.next != animalNodes.trailerSentinel){
//        counter ++;
//        nodeObject = nodeObject.next;
//        }
//        for(int i = 0; i < counter; i++){
//        if(nodeObject2.getAnimal() == null){
//        nodeObject2.animal = animalToAdd;
//        i += 100;
//        }
//        nodeObject2 = nodeObject2.next;
//
//        }
//        }
//
//
//
//
//
//public String iterate(int nodeAmount, riverRedux<Animal> animalNodes){
//        String log = "";
//        String riverDisplay = "";
//        String displayToScreen;
//        int iterationNum = 0;
//        animalNodes.randomAnimal(animalNodes,nodeAmount);
//
//        while(!animalNodes.bearCheck(nodeAmount, animalNodes)){
//        riverDisplay += animalNodes.listSummary(nodeAmount,animalNodes);
//        riverDisplay += "\n";
//        //log +=
//        animalNodes.direction(animalNodes);
//        log += "\n";
//
//        }
//
//        //displayToScreen = log + "\n" + riverDisplay;
//        //return displayToScreen;
//        return riverDisplay;
//        }
//
//
//
//public static void main(String[] args){
//        String select = """
//                    how big is your river?
//                    1) small
//                    2) normal
//                    3) big""";
//        System.out.println(select);
//        // riverRedux<Animal> ecoList = new riverRedux<>();
//
//
//        //Animal animalz = new Animal();
//        //int testAmount = ecoList.getListSize();
//
//        //System.out.println("you chose " + testAmount);
//
//
//        riverRedux<Animal> animalNodes = new riverRedux<>();//<<<<------
//        int nodeAmount = animalNodes.getListSize();
//        //System.out.println(log);
//
//
//        String type = "empty";
//        //Animal testanimal;
//        //for(int i = 0; i<10; i++){
//        //Random rand = new Random();
//        // rand.setSeed(System.currentTimeMillis());
//        //int chance = rand.nextInt(100);   <<- this only works if no seed is set[?]
//        // otherwise, ea. loop gives the same random number
//        //System.out.println(chance);
//        //testanimal = ecoList.randomAnimal();
//
//        System.out.println(animalNodes);
//        animalNodes.randomAnimal(animalNodes,nodeAmount); //<<-- randomly fills list with objects
//        System.out.println(animalNodes.riverSize);
//
//        // why does instance of Bear work but
//        // if (Objects.equals(testanimal, new Bear())){
//        // does not? is it because you are comparing with
//        // a creation of a bear and not the bear object itself?
//        // if (animalNodes.firstAnimal() instanceof Bear){
//        //   type = "bear";
//        // }else if(animalNodes.firstAnimal() instanceof Fish){
//        //     type = "Fish";
//        // }else if ( Objects.equals(animalNodes.firstAnimal(), null)){
//        //   type = "null";
//        // }
//        // System.out.println(type);
//        // System.out.println(animalNodes.size()); <--- used to check correct node amount
//        // System.out.println(animalNodes.firstAnimal()); <--- used as redundancy check
//        //System.out.println(animalNodes.);
//        // System.out.println("********************");
//
//
//        /**
//         System.out.println(animalNodes);
//
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//         System.out.println(animalNodes.bearCheck(nodeAmount, animalNodes));
//         System.out.println("***********************************");
//         animalNodes.direction(animalNodes);
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//
//
//
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//         System.out.println(animalNodes.bearCheck(nodeAmount, animalNodes));
//         System.out.println("***********************************");
//         animalNodes.direction(animalNodes);
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//
//
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//         System.out.println(animalNodes.bearCheck(nodeAmount, animalNodes));
//         System.out.println("***********************************");
//         animalNodes.direction(animalNodes);
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//
//
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//         System.out.println(animalNodes.bearCheck(nodeAmount, animalNodes));
//         System.out.println("***********************************");
//         animalNodes.direction(animalNodes);
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//
//
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//         System.out.println(animalNodes.bearCheck(nodeAmount, animalNodes));
//         System.out.println("***********************************");
//         animalNodes.direction(animalNodes);
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//
//
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//         System.out.println(animalNodes.bearCheck(nodeAmount, animalNodes));
//         System.out.println("***********************************");
//         animalNodes.direction(animalNodes);
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//
//
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//         System.out.println(animalNodes.bearCheck(nodeAmount, animalNodes));
//         System.out.println("***********************************");
//         animalNodes.direction(animalNodes);
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//
//
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//         System.out.println(animalNodes.bearCheck(nodeAmount, animalNodes));
//         System.out.println("***********************************");
//         animalNodes.direction(animalNodes);
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//
//
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//         System.out.println(animalNodes.bearCheck(nodeAmount, animalNodes));
//         System.out.println("***********************************");
//         animalNodes.direction(animalNodes);
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//
//
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//         System.out.println(animalNodes.bearCheck(nodeAmount, animalNodes));
//         System.out.println("***********************************");
//         animalNodes.direction(animalNodes);
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//
//
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//         System.out.println(animalNodes.bearCheck(nodeAmount, animalNodes));
//         System.out.println("***********************************");
//         animalNodes.direction(animalNodes);
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//         System.out.println(animalNodes.bearCheck(nodeAmount, animalNodes));
//         System.out.println("***********************************");
//         animalNodes.direction(animalNodes);
//         System.out.println(animalNodes.listSummary(nodeAmount,animalNodes));
//         */
//
//
//
//        // while(!animalNodes.bearCheck(nodeAmount, an))
//
//
//        String fin = animalNodes.iterate(nodeAmount, animalNodes);
//        System.out.println(fin);
//
//        //while(!animalNodes.bearCheck(nodeAmount, animalNodes)){
//
//
//        //}
//        // System.out.println(("strings" instanceof  String)); <<-- used to check if something is string
//        //String test = "i";
//        //System.out.println((test.equals("i")));
//
//        //System.out.println(testanimal.toString());
//        //}
//
//    /*
//    *************** why cant i get this to change each iteration??? ************
//    String type = "ass";
//    Animal testanimal;
//    for(int i = 0; i<10; i++){
//        testanimal = ecoList.randomAnimal();
//                // why does instance of Bear work but
//        //if (Objects.equals(testanimal, new Bear())){
//        // does not? is it because you are comparing with
//        // a creation of a bear and not the bear object itself?
//        System.out.println(type);
//        if (testanimal instanceof Bear){
//            type = "bear";
//        }else if(testanimal instanceof Fish){
//            type = "Fish";
//        }else if ( Objects.equals(testanimal, null)){
//            type = "null";
//        }
//        System.out.println(testanimal);
//
//        //System.out.println(testanimal.toString());
//    }
//
//
//*/
//
//        }
//        }
//
//
//
//
//






