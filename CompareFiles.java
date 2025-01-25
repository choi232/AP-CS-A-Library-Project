/*
Author: Mikael Choi

Cross Comparison of StudentBarcode.csv & library.txt to find
same barcodes and return these barcodes as well as the corresponding
library book & the past lenders of such library book

**Must Run In VS-Code because Geany limits computing capabilities
*/

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Collections; //Import ArrayList
import java.util.HashSet; // Import the Scanner class to read text files
import java.util.Scanner; //Import Collections for sorting function


public class CompareFiles {
  public static void main(String[] args) {
    //Magic Numbers
    final int tabsTitle = 21;
    final int tabsCheckOut = 72;
	  //ArrayList Creation
    ArrayList<Integer> studentBarcodes = new ArrayList<Integer>();
    ArrayList<Integer> libraryStudentBarcodes = new ArrayList<Integer>();
    ArrayList<String> libraryBooks = new ArrayList<String>();
    ArrayList<String> libraryCheckOut = new ArrayList<String>();
    HashSet<Integer> libraryBarcodeSet = new HashSet<>();
    //try catch blocks
    try {

      //initialize File & Scanner Objects
      File fileOne = new File("StudentBarcodeIDS.csv");
      File fileTwo = new File("library.txt");
      Scanner scanOne = new Scanner(fileOne);
      Scanner scanTwo = new Scanner(fileTwo);
      boolean isFirstLine = true;

      //First Loop for studentBarcodes
      while (scanOne.hasNextLine()) {
        String dataOne = scanOne.nextLine();
        if(isFirstLine){
          dataOne = scanOne.nextLine();
          isFirstLine = false;
        }
        //Cast String to Integer Wrapper Class
        studentBarcodes.add(Integer.parseInt(dataOne));
      }
      scanOne.close();

      isFirstLine = true;
      boolean firstColumn = true;

      //Second Loop for libraryStudentBarcodes & libraryBooks
      while (scanTwo.hasNextLine()) {
        String dataTwo = scanTwo.nextLine();
        if(isFirstLine){
          dataTwo = scanTwo.nextLine();
          isFirstLine = false;
        }
        int tabCount = 0;
        int startIndex = -1;
        for(int i = 0, len = dataTwo.length(); i < len; i++){
          boolean onTab;
          if(dataTwo.charAt(i) == '\t') onTab = true;
          else onTab = false;

          if(onTab) tabCount++;
          if(tabCount == 1 && onTab){
            //Hash Table to achieve O(1) when comparing if the barcode is not used before compared to looping through entire array O(n)
            int barcode = Integer.parseInt(dataTwo.substring(0, i));
            if(!libraryBarcodeSet.contains(barcode))  libraryStudentBarcodes.add(barcode);
          }
      
          if(tabCount == tabsTitle && onTab) startIndex = i+1;
          if(tabCount == tabsTitle+2 && onTab) libraryBooks.add(dataTwo.substring(startIndex, i).replace('\t', ' '));


          if(tabCount == tabsCheckOut && onTab) startIndex = i+1;
          if(tabCount == tabsCheckOut+1 && onTab){
            String people = "";
            String[] strArray = dataTwo.substring(startIndex, i).split(";");
            for(int k = 0; k < strArray.length; k+=2){
              if(strArray[k].length() > 0 && k != ((strArray.length/2 - 1)*2) && Character.isAlphabetic(strArray[k].charAt(0))) people += strArray[k] + "; ";
              else if (strArray[k].length() > 0 && Character.isAlphabetic(strArray[k].charAt(0))) people += strArray[k];
            }
            libraryCheckOut.add(people);
          }
        }
      }
      scanTwo.close();
    } 
    catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    catch(NumberFormatException e){} //simply here to catch any non-integer strings from parseInt allows while loop to continue and ignore non-integer values in ArrayList

    Collections.sort(studentBarcodes); //sort array
    int matches = 0;    

    for(int iterator = 0; iterator < libraryStudentBarcodes.size(); iterator++){
      int search = libraryStudentBarcodes.get(iterator);
      int lowIndex = 0; //lower bound of possible values
      int highIndex = studentBarcodes.size()-1; //upper bound of possible values
      boolean isFound = false; //bool flag for if searching value is not in data set
      int middle, guess, counter = 0;
      
      while(highIndex - lowIndex + 1 > 0){ //size of array is greater than zero
        counter++;
        middle = lowIndex + (highIndex - lowIndex)/2; //set middle to half of possible array plus current lower bound
        guess = studentBarcodes.get(middle);

        //check if guess is right
        if(guess == search){
          System.out.println("Found: " + guess);
          System.out.println("Title: " + libraryBooks.get(iterator));
          if(libraryCheckOut.get(iterator).length() != 0) System.out.println("Past Lenders: " + libraryCheckOut.get(iterator) + "\n");
          else System.out.println();
          isFound = true;
          matches++;
          break;
        }

        //guess too high then adjust upper bound
        else if(guess > search) highIndex = middle - 1;

        //guess too low then adjust lower bound
        else if(guess < search) lowIndex = middle + 1;
        
      }
	  }

    System.out.println("Total Matches: " + matches);



  }
}
