import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList; // Import the Scanner class to read text files
import java.util.Scanner; //Import Collections for sorting function

public class ReadFile {
  public static void main(String[] args) {
	//ArrayList Creation
    ArrayList<Integer> studentBarcodes = new ArrayList<Integer>();
    ArrayList<ArrayList<String>> listTwo = new ArrayList<ArrayList<String>>();
    ArrayList<Integer> libraryStudentBarcodes = new ArrayList<Integer>();
    try {
      File fileOne = new File("StudentBarcodeIDS.csv");
      File fileTwo = new File("library.txt");
      Scanner scanOne = new Scanner(fileOne);
      Scanner scanTwo = new Scanner(fileTwo);
      boolean isFirstLine = true;
      //Loop through all data in csv
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
      //Loop through all data in csv
      while (scanTwo.hasNextLine()) {
        String dataTwo = scanTwo.nextLine();
        if(isFirstLine){
          dataTwo = scanTwo.nextLine();
          isFirstLine = false;
        }
        //Cast String to Integer Wrapper Class
        libraryStudentBarcodes.add(Integer.parseInt(dataTwo));
      }
      int row = 0;
      while (scanTwo.hasNextLine()) {
        listTwo.add(new ArrayList<String>());
        String dataTwo = scanTwo.nextLine();
        for(int i = 0, len = dataTwo.length(), lastIndex = 0; i < len; i++){
          if(dataTwo.charAt(i) == '\t'){
            listTwo.get(row).add(dataTwo.substring(lastIndex, i));
            lastIndex = i+1;
          }
          else if(i == len-1){
            listTwo.get(row).add(dataTwo.substring(lastIndex));
          }
        }
        row++;
      }
      scanTwo.close();
    } 
    catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    catch(NumberFormatException e){
      System.out.println("Not a valid integer: " + e);
    }
    
    for(int i = 0; i < libraryStudentBarcodes.size(); i++){
	  System.out.println(libraryStudentBarcodes.get(i));
    }

/*
    //Generate User Input for Search Value
    Scanner scan = new Scanner(System.in);
    System.out.print("Please enter a number to search for in the dataset: ");

    //Loop to ensure user enters a proper integer value for search value
    int search;
    while(true){
      try {
        search = Integer.parseInt(scan.nextLine());
        break;
      } 
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

*/

  }
}

