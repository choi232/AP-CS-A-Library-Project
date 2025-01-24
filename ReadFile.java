/*
Author: Mikael

Purpose: to build a board for battleship

2 boards letters down the side and numbers across the top (10x10 board)
5 different ship types
Carrier has 5 pegs
2 colored set of pets
*/
import java.util.Scanner;
public class Board {
	
	char[][] ships;
	char[][] guess;
	
	public Board(){
		ships = new char[10][10];
		guess = new char[10][10];
		setBoard();
	}
      
	public void setBoard(){
		for (int row = 0; row < 10; row++){
			for (int col = 0; col < 10; col++){
				ships[row][col] = '.';
				guess[row][col] = '.';
			}
		}
		
	}
	/*
	function checkShip takes two int parameters (row, col) as well as board object and returns true if there is a ship at that index and false if not
	 */
	public boolean checkShip(char[][] board, int row, int col){ //chose to use 'S' to signify a ship
		if(board[row][col] == 'S'){ //checks if board has a ship ('S') there it returns true
			return true;
		}
		return false; //else return false
	}
	
	
	/*
	function printBoard returns no value and takes no parameters but prints out the current state of the board
	*/
	public void printBoard(){ //if you name the method toString it will not allow you to run it since it would be overriding toString from String library so I called it printBoard
		int letter = 65;
		System.out.println("  0 1 2 3 4 5 6 7 8 9"); //header for columns also used 0-9 instead of 0-10 otherwise there will be an uneven spacing
		//iterate through 2d array and print
		for(int r = 0; r < 10; r++, letter++){ //increment both r and letter at same time in for loop
			System.out.printf("%c ", letter);//print out side header of letters
			for(int c = 0; c < 10; c++){
				System.out.print(guess[r][c] + " ");//access 2d array and print individual value
			}
			System.out.println(); //print \n to create line break between each row
		}
		letter = 65;
		System.out.println("  0 1 2 3 4 5 6 7 8 9"); //header for columns also used 0-9 instead of 0-10 otherwise there will be an uneven spacing
		//iterate through 2d array and print
		for(int r = 0; r < 10; r++, letter++){ //increment both r and letter at same time in for loop
			System.out.printf("%c ", letter);//print out side header of letters
			for(int c = 0; c < 10; c++){
				System.out.print(ships[r][c] + " ");//access 2d array and print individual value
			}
			System.out.println(); //print \n to create line break between each row
		}
	}


	/*
	method to place the ships from point (sRowStart, sColStart) to desired length
	placeShip requires sRowStart and sColStart to be a value from 0-9 as it
	considers the first row/col of the board at 0 not 1
	if vertical is true, it places ship vertically downwards from startpoint
	if vertical is false, it places ship horizontally to the right from the start
	point
	*/
	public boolean placeShip(int len, int sRowStart, int sColStart, boolean vertical){
	int sRowEnd, sColEnd;
		if(vertical){
			//calculate endpoint if ship is placed downwards
			sRowEnd = sRowStart + len;
			sColEnd = sColStart;
		}
		else{
			//calculate endpoint if ship is placed to the left
			sRowEnd = sRowStart;
			sColEnd = sColStart + len;
		}
		
		if(len > 5 || len < 2 || sRowEnd < 0 || sColEnd < 0 || sRowEnd > 9 ||sColEnd > 9){
			return false;
		}
		if(vertical){
			for(int row = sRowStart; row < (sRowStart + len); row++){
				if(ships[row][sColStart] == 's'){
					return false;
				}
			}
			//places ship from start point and downwards
			for(int row = sRowStart; row < (sRowStart + len); row++){
				ships[row][sColStart] = 's';
			}
			return true;
		}
		else {
			for(int col = sColStart; col < (sColStart + len); col++){
				if(ships[sRowStart][col] == 's'){
					return false;
				}
			}
			//places ship from start point and to the right
			for(int col = sColStart; col < (sColStart + len); col++){
				ships[sRowStart][col] = 's';
			}
			return true;
		}
	}
}

