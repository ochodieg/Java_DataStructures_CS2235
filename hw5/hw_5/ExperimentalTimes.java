package hw_5;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;


public class ExperimentalTimes {
    public static void main(String[] args){
        int f = 800000;
        int b = 8000000;
        int m = 200000;


        ArrayDeque<Integer> dek     = new ArrayDeque<>();
        ArrayList<Integer> aList    = new ArrayList<>();
        LinkedList<Integer> lList  = new LinkedList<>();

        long dekFrontStart      = System.currentTimeMillis();
        for(int i = 0; i < f; i++) dek.addFirst(i);
        long dekFrontEnd        = System.currentTimeMillis();
        long dekFrontTime       = dekFrontEnd - dekFrontStart;

        long dekBackStart       = System.currentTimeMillis();
        for(int i = 0; i < b; i++) dek.addLast(i);
        long dekBackEnd         = System.currentTimeMillis();
        long dekBackTime        = dekBackEnd - dekBackStart;

        long dekRemoveFrontStart       = System.currentTimeMillis();
        for(int i = 0; i < f; i++) dek.removeFirst();
        long dekRemoveFrontEnd         = System.currentTimeMillis();
        long dekRemoveFrontTime        = dekRemoveFrontEnd - dekRemoveFrontStart;

        long dekRemoveBackStart       = System.currentTimeMillis();
        for(int i = 0; i < b; i++) dek.removeLast();
        long dekRemoveBackEnd         = System.currentTimeMillis();
        long dekRemoveBackTime        = dekRemoveBackEnd - dekRemoveBackStart;



        long aListFrontStart    = System.currentTimeMillis();;
        //for(int i = 0; i < f; i++) aList.add(0, i);
        long aListFrontEnd      = System.currentTimeMillis();
        long aListFrontTime     = aListFrontEnd - aListFrontStart;

        long aListBackStart     = System.currentTimeMillis();;
        //for(int i = 0; i < b; i++) aList.add(i);
        long aListBackEnd       = System.currentTimeMillis();
        long aListBackTime      = aListBackEnd - aListBackStart;

        long aListMidStart      = System.currentTimeMillis();;
        //for(int i = 0; i < m; i++) aList.add((aList.size()/2), i);
        long aListMidEnd        = System.currentTimeMillis();
        long aListMidTime       = aListMidEnd - aListMidStart;

        long aListRemoveStart      = System.currentTimeMillis();;
        //for(int i = f; i > 0; i--) aList.remove(0);
        //aList.subList(0, f ).clear();
        long aListRemoveEnd        = System.currentTimeMillis();
        long aListRemoveTime       = aListRemoveEnd - aListRemoveStart;



        long lListFrontStart    = System.currentTimeMillis();
        for ( int i = 0; i < f; i++) lList.addFirst(i);
        long lListFrontEnd      = System.currentTimeMillis();
        long lListFrontTime     = lListFrontEnd - lListFrontStart;

        long lListBackStart     = System.currentTimeMillis();
        for ( int i = 0; i < b; i++) lList.addLast(i);
        long lListBackEnd       = System.currentTimeMillis();
        long lListBackTime      = lListBackEnd - lListBackStart;

        long lListMidStart      = System.currentTimeMillis();
        //for ( int i = 0; i < m; i++) lList.add(lList.size()/2, i);
        long lListMidEnd        = System.currentTimeMillis();
        long lListMidTime       = lListMidEnd - lListMidStart;

        long lListRemoveStart      = System.currentTimeMillis();;
        for(int i = f; i >0; i--) lList.remove(0);
        //lList.subList(0, f ).clear();
        long lListRemoveEnd        = System.currentTimeMillis();
        long lListRemoveTime       = lListRemoveEnd - lListRemoveStart;



        int dekamount       = dek.size();
        //int dekamount2  = dek2.size();

        int aListAmount     = aList.size();
        //int aListb      = aListB.size();
        //int aListm      = aListM.size();

        int linkedAmount    = lList.size();


        System.out.println("it took " + dekFrontTime
                + "ms to add " + f + " elements to the front of deque");
        System.out.println("it took " + dekBackTime
                + "ms to add " + b + " elements to the back of deque");
        System.out.println("Total Deque amount = " + dekamount + "\n");


        System.out.println("it took " + aListFrontTime
                + "ms to add " + f + " elements to the front of an ArrayList");
        System.out.println("it took " + aListBackTime
                + "ms to add " + b + " elements to the back of an ArrayList");
        System.out.println("it took " + aListMidTime
                + "ms to add " + m + " elements to the middle of an ArrayList");
        System.out.println("Total Array List amount = " + aListAmount + "\n");


        System.out.println("it took " + lListFrontTime
                + "ms to add " + f + " elements to the front of a Linked List");
        System.out.println("it took " + lListBackTime
                + "ms to add " + b + " elements to the back of a Linked List");
        System.out.println("it took " + lListMidTime
                + "ms to add " + m + " elements to the middle of a Linked List");
        System.out.println("Total Linked List amount = " + linkedAmount + "\n");

        System.out.println("it took " + aListRemoveTime
                + "ms to remove all elements from Array List");
        System.out.println("it took " + lListRemoveTime
                + "ms to remove all elements from Linked List");
        System.out.println("it took " + dekRemoveFrontTime
                + "ms to remove Front elements from Deque");
        System.out.println("it took " + dekRemoveBackTime
                + "ms to remove Back elements from Deque");




        //ArrayList<Integer> aListB    = new ArrayList<>();
        //ArrayList<Integer> aListM    = new ArrayList<>();
        //for(int i = 0; i < m; i++) aListM.add(0, i);
        /* since:
        ArrayList<Integer> aListM    = new ArrayList<>(m);
        sets the capacity of the list (how many elements the list can
        potentially accommodate without reallocating its internal structures)
        and not the actual size (the # of elements in the list), the for-loop
        above creates a list we can add elements to, without an out-of-bounds
        exception error.
        However, if we simply add to the middle of a list, in a for loop, as we go:
        for(int i = 0; i < m; i++) aListM.add((aListM.size()/2), i);
        we can continuously add to the middle in one loop, without the error.
         */
        // System.out.println("check1 " + aListM.size());


        //System.out.println("check2 " + aListM.size());
        //System.out.println(aListM);
        //System.out.println(aListB);
        //System.out.println(aListb);
        //System.out.println(aListF);
        //System.out.println(aListf);
    }



}
