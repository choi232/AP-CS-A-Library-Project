/*
Author: Mikael Choi

Purpose: Implementation of Binary Search to 
look for the user inputted value within
the StudenBarcodeIDS.csv file
*/

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList; //Import ArrayList
import java.util.Collections; // Import the Scanner class to read text files
import java.util.Scanner; //Import Collections for sorting function

public class BinarySearch {
  public static void main(String[] args) {
    //ArrayList Creation
    ArrayList<Integer> listOne = new ArrayList<Integer>();
    ArrayList<ArrayList<String>> listTwo = new ArrayList<ArrayList<String>>();
    ArrayList<Integer> listLibrary = new ArrayList<Integer>();
    try {
      File fileOne = new File("StudentBarcodeIDS.csv");
      Scanner scanOne = new Scanner(fileOne);

      boolean isFirstLine = true;
      //Loop through all data in csv
      while (scanOne.hasNextLine()) {
        String dataOne = scanOne.nextLine();
        if(isFirstLine){
          dataOne = scanOne.nextLine();
          isFirstLine = false;
        }
        //Cast String to Integer Wrapper Class
        listOne.add(Integer.parseInt(dataOne));
      }
      scanOne.close();
    } 
    //If File is not found dont crash the program just throw error
    catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    //Prints out if parseInt comes across an invalid string for parsing
    catch(NumberFormatException e){
      System.out.println("Not a valid integer: " + e);
    }

    //Generate User Input for Search Value
    Scanner scan = new Scanner(System.in);
    System.out.print("Please enter a number to search for in the dataset: ");

    //Loop to ensure user enters a proper integer value for search value
    int search;
    //creates an infinite loop which only breaks when user-input is a valid number
    while(true){
      //while in loop use try and catch to find if 
      try {
        search = Integer.parseInt(scan.nextLine());
        break;
      } 
      //if Integer parseInt returns a number format exception error then prompt for a valid integer again
      catch (NumberFormatException e) {
        System.out.print("Enter a valid integer to search: ");
      }
    }
    
    System.out.println();
    scan.close();

    //need to imagine an imaginary array of possible values which shrinks as we guess values in binary search

    Collections.sort(listOne); //sort array
    int lowIndex = 0; //lower bound of possible values
    int highIndex = listOne.size()-1; //upper bound of possible values
    boolean isFound = false; //bool flag for if searching value is not in data set
    int middle, guess, counter = 0;
    while(highIndex - lowIndex + 1 > 0){ //size of array is greater than zero
      counter++;
      middle = lowIndex + (highIndex - lowIndex)/2; //set middle to half of possible array plus current lower bound
      guess = listOne.get(middle);

      //Verbose print commands to show processing
      System.out.println("Iteration #" + counter); //implementation of counter for search length
      System.out.print("Middle Index: " + middle);
      System.out.println("  Middle Value: " + guess);
      System.out.println("lowIndex & highIndex Range: (" + lowIndex + ", " + highIndex + ")\n");

      //check if guess is right
      if(guess == search){
        System.out.println("Found: " + guess);
        isFound = true;
        break;
      }

      //guess too high then adjust upper bound
      else if(guess > search){
        highIndex = middle - 1;
      }

      //guess too low then adjust lower bound
      else if(guess < search){
        lowIndex = middle + 1;
      }
    }

    //Handling if inputted number is not in csv
    if(!isFound) System.out.println("Not Found");

  }
}



