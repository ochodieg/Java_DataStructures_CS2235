package hw_5;

import java.util.ArrayList;
import java.util.LinkedList;

public class test {
    public static void main(String[] args) {

        // create an empty array list with an initial capacity
        ArrayList<Integer> arrlist = new ArrayList<Integer>(5);
        ArrayList<Integer> arrlist2 = new ArrayList<Integer>();

        LinkedList<Integer> lList  = new LinkedList<>();
        LinkedList<Integer> lList2  = new LinkedList<>();

        // use add() method to add elements in the list
        System.out.println("########### adding to array1 #############");
        arrlist.add(15);
        System.out.println(arrlist);
        arrlist.add(22);
        System.out.println(arrlist);
        arrlist.add(30);
        System.out.println(arrlist);
        arrlist.add(40);
        System.out.println(arrlist);

        System.out.println("########### adding to middle of array1 #############");

        arrlist.add((arrlist.size()/2), 15);
        System.out.println(arrlist);
        arrlist.add((arrlist.size()/2), 22);
        System.out.println(arrlist);
        arrlist.add((arrlist.size()/2), 30);
        System.out.println(arrlist);
        arrlist.add((arrlist.size()/2), 40);
        System.out.println(arrlist);

        System.out.println("########### adding to front of array2 #############");

        arrlist2.add(0, 15);
        System.out.println(arrlist2);
        arrlist2.add(0, 22);
        System.out.println(arrlist2);
        arrlist2.add(0, 30);
        System.out.println(arrlist2);
        arrlist2.add(0, 40);
        System.out.println(arrlist2);

        System.out.println("########### adding to linked list1 #############");

        lList.add(15);
        System.out.println(lList);
        lList.add(22);
        System.out.println(lList);
        lList.add(30);
        System.out.println(lList);
        lList.add(40);
        System.out.println(lList);

        System.out.println("########### adding to first of linked list2 #############");

        lList2.addFirst(15);
        System.out.println(lList2);
        lList2.addFirst(22);
        System.out.println(lList2);
        lList2.addFirst(30);
        System.out.println(lList2);
        lList2.addFirst(40);
        System.out.println(lList2);


        System.out.println("########### adding to middle of linked list1 #############");

        System.out.println(lList);
        lList.add(lList.size()/2, 16);
        System.out.println(lList);
        lList.add(lList.size()/2,23);
        System.out.println(lList);
        lList.add(lList.size()/2,31);
        System.out.println(lList);
        lList.add(lList.size()/2,41);
        System.out.println(lList);

        System.out.println("########################");

        System.out.println(lList);



        // adding element 25 at third position
        //arrlist.add(2,25);

        // let us print all the elements available in list
        //for (Integer number : arrlist) {
        //    System.out.println("Number = " + number);
        //}
        System.out.println("########### arr list1 #############");
        System.out.println(arrlist);
        System.out.println("########### arr list2 #############");
        System.out.println(arrlist2);
        System.out.println("########### link list1 #############");
        System.out.println(lList);
        System.out.println("########### link list2 #############");
        System.out.println(lList2);
    }
}