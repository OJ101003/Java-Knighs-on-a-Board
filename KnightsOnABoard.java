
/***************************************************************  
*  file: KnightsOnABoard.java  
*  author: Omar Jaber
*  class: CS 1400 
*  
*  assignment: program 5  
*  date last modified: 5/14/2022  
*  
*  purpose: Program checks if a knight on a chess board can take out another knight on a 8x8 chessboard
*  
****************************************************************/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KnightsOnABoard {

	// Main method, calls itself and gets a FileNotFoundException ready for the scanner
	public static void main(String[] args) throws FileNotFoundException {
		KnightsOnABoard board = new KnightsOnABoard();
		Scanner scnr = new Scanner(System.in); // Keyboard Scanner
		System.out.print("Please enter the name of a valid file: ");
		String input = scnr.nextLine(); // Gets file name
		File test = new File(input);
		test = board.validateFile(test); // Checks to make sure file exists
		board.validateData(test); // Makes sure the file data is correctly formatted
		int[][] boardArray = board.populateBoard(test); // Creates a 2D array for the board and fills up the array based off data from a text file.
		System.out.println("The board looks as follows:");
		board.printBoard(boardArray); // Prints the board out
		if (board.cannotCapture(boardArray)) { // This runs the cannotCapture method, which returns a boolean and makes sure that no knight can capture each other
			System.out.println("There is at least one knight which can capture another knight.");
		} else {
			System.out.println("No knights can capture any other knight.");
		}
	}
	// Makes sure the file the user inputted exists, if not then it loops until the input works.
	public File validateFile(File inputFile) { 
		Scanner valFileScanner = new Scanner(System.in);
		String fileName;
		while (!inputFile.exists()) {
			System.out.println("File does not exist!");
			System.out.println("Please enter the name of a valid file: ");
			fileName = valFileScanner.nextLine();
			inputFile = new File(fileName);
		}
		return inputFile;
	}
	
	// validateData makes sure the file read is a 8x8 grid. 
	public boolean validateData(File inputFile) throws FileNotFoundException { 
		Scanner scanner1 = new Scanner(inputFile);
		Scanner scanner2 = new Scanner(inputFile);
		int counterLines = 0;
		while (scanner1.hasNextLine()) {
			counterLines++;
			scanner1.nextLine();
		}
		if (counterLines != 8) { // Checks rows
			return false;
		}
		for (int i = 0; i < 8; i++) {
			String[] columnCount = (scanner2.nextLine()).split(" "); // Checks columns
			if (columnCount.length != 8) {
				return false;
			}
		}
		return true;
	}

	// Fills up a 2D array with the data on the user input file. If a number other than 1 or 0 is detected, it gives a error message and converts the numbers.
	public int[][] populateBoard(File inputFile) throws FileNotFoundException {
		int[][] boardArray = new int[8][8];
		Scanner scanner1 = new Scanner(inputFile);
		int temp;
		boolean error = false;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				temp = Integer.valueOf(scanner1.next());
				if (temp > 1) { // If number greater than 1, then set equal to 1
					temp = 1;
					error = true;
				} else if (temp < 0) { // If number less than 0, set equal to 0
					temp = 0;
					error = true;
				}
				boardArray[y][x] = temp;
			}

		}
		if (error)
			System.out.println("A number other than 1 or 0 was detected and converted");// Outputs error message

		return boardArray;
	}

	// Goes through the array with board data and checks to see if any knight can capture each other. If so then the program returns True, else false.
	public boolean cannotCapture(int[][] chessBoard) {
		boolean status = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int currentSpace = chessBoard[j][i];
				// chessBoard[x][y]. i should control the y axis, and j should control the x axis
				// i controls which row we're on, and j controls which column we're on
				if (currentSpace == 1) {
					if (j >= 1) {
						if (i >= 2) {
							if (chessBoard[j - 1][i - 2] == 1) {
								status = true;
							}
						}
						if (i <= 5) {
							if (chessBoard[j - 1][i + 2] == 1) {
								status = true;
							}
						}
					}
					if (j <= 6) {
						if (i >= 2) {
							if (chessBoard[j + 1][i - 2] == 1) {
								status = true;
							}
						}
						if (i <= 5) {
							if (chessBoard[j + 1][i + 2] == 1) {
								status = true;
							}
						}
					}
					if (i >= 1) {
						if (j >= 2) {
							if (chessBoard[j - 2][i - 1] == 1) {
								status = true;
							}
						}
						if (j <= 5) {
							if (chessBoard[j + 2][i - 1] == 1) {
								status = true;
							}
						}
					}
					if (i <= 6) {
						if (j >= 2) {
							if (chessBoard[j - 2][i + 1] == 1) {
								status = true;
							}
						}
						if (j <= 5) {
							if (chessBoard[j + 2][i + 1] == 1) {
								status = true;
							}
						}
					}
				}
			}

		}
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	// Simple method to print out the chess board
	public void printBoard(int[][] chessBoard) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(chessBoard[i][j] + " ");
			}
			System.out.println(" ");
		}
	}
}
