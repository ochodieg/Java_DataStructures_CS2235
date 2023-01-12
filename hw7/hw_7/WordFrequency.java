package hw_7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


public class WordFrequency {
    public static void main(String[] args) throws FileNotFoundException{


        //String directory = new File(".").getAbsolutePath();
        File toParse = new File("Malala2013.txt");
        HashMap<String, Integer> wordCounts = new HashMap<>();
//        Scanner scannerToParse = new Scanner(toParse);
//        while (scannerToParse.hasNextLine()){
//            System.out.println(scannerToParse.nextLine());
//        } // scanner check

        // set key and value
        // check if key is already there
        // if it is, get the value,
        // add 1
        // update the value with new value


        int count= 0; // check
        Scanner scannerToParse = new Scanner(toParse).useDelimiter("[^a-zA-Z0-9-']+");
        while (scannerToParse.hasNext()){
            String currentWord = scannerToParse.next().toLowerCase();
            count++; // check
            //System.out.println(scannerToParse.next().toLowerCase());
            if(!wordCounts.containsKey(currentWord)){
                wordCounts.put(currentWord, 1);
                //System.out.println(currentWord);
            }else{
                //System.out.println(currentWord + " check");
                int value = wordCounts.get(currentWord);
                //System.out.println("value is " + value);
                //int newValue = value+=1;
                wordCounts.replace(currentWord, ++value);
                //value ++;
            }
        } // scanner check
        // System.out.println(count); // count check
        //System.out.println(wordCounts.entrySet());
        //System.out.println(wordCounts.values());
        String toScreen ="";
        int secondCount = 0;
        for (var set : wordCounts.entrySet()){
            secondCount += set.getValue();
            toScreen += String.format("""
                    %s:%d
                    """,set.getKey(),set.getValue());
        }
        System.out.println(toScreen);
        if(count == secondCount) System.out.println(count + " words counted.");
        else System.out.println("!counting error!");

    }
}
