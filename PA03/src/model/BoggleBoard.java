package model;

import java.util.Random;

/**
 * BoggleBoard - this class manages the Boggle board
 * 
 * Acknowledgements: I acknowledge that I have neither given nor received
 * assistance for this assignment except as noted below: TA Juliana TA Christy
 * 
 * Modifications: PA02/03 - Converted to Singleton to make PA03 easier
 * 
 * @author Michael Norton
 * @author Alex Gellios
 * @version PA03 (11 November 2016)
 * 
 *          modified for PA3
 *          added the hasWord method which searches the board for a word on the board
 */

public class BoggleBoard {
	private char[][] board;

	public static final String LETTERS = "JKQYZ" + "BBCCFFGGMMPPVV" + "DDDUUUWWWXXX" + "HHHHHLLLLLRRRRR"
			+ "AAAAAAIIIIIINNNNNNSSSSSSOOOOOO" + "EEEEEEEEEETTTTTTTTTT";

	private static BoggleBoard brd;

	/**
	 * Default constructor
	 */
	private BoggleBoard() {
		board = new char[4][4];
		mix();

	} // default constructor

	/**
	 * Explicit value constructor (for testing only)
	 * 
	 * @param staticBoard
	 *            - 16 character String for the board
	 */
	private BoggleBoard(String staticBoard) {
		this(); // call default constructor--failsafe in case staticBoard is
				// too short

		if (staticBoard.length() >= 16) {
			int counter = 0;

			for (int row = 0; row < 4; row++)
				for (int col = 0; col < 4; col++) {
					board[row][col] = staticBoard.charAt(counter);
					counter++;

				} // end for

		} // end if

	} // explicit value constructor

	/**
	 * getCell - return the char at the specified call. Return (char)0 if
	 * invalid indexes
	 * 
	 * @param row
	 * @param col
	 * @return the char at the specified cell
	 */
	public char getCell(int row, int col) {
		char charToGet = (char) 0;

		if (row >= 0 && row < 4)
			if (col >= 0 && col < 4)
				charToGet = board[row][col];

		return charToGet;

	} // method getCell( int, int )

	/**
	 * serves as entry point to recursively search for parameter added for PA3
	 * 
	 */
	public boolean hasWord(String word) {
		boolean[][] shadow = new boolean[4][4];
		boolean retVal = false;
		word = word.toUpperCase();

		//fill the shadow board
		for (int i = 0; i < 4; i++) { 
			for (int j = 0; j < 4; j++) {
				shadow[i][j] = false;
			}
		}
		
		//go through the whole board while referring to the hasWord method
		for (int checkRow = 0; checkRow < 4 && !retVal; checkRow++) {
			for (int checkCol = 0; checkCol < 4 && !retVal; checkCol++) {
				retVal = hasWord(word, checkRow, checkCol, shadow);
			}
		}

		return retVal;
	}

	/**
	 * recursive method that searches for word added for PA3
	 * 
	 */
	private boolean hasWord(String word, int row, int col, boolean[][] shadow) {
		boolean retVal = false;

		if (word != null && word.length() > 0) {
			if (row < 0)
				row = 0;
			if (col < 0)
				col = 0;
			if (row > 3)
				row = 3;
			if (col > 3)
				col = 3;
			if (word.charAt(0) == getCell(row, col) && (shadow[row][col] == false)) {

				shadow[row][col] = true;
				if (word.length() == 1) {
					retVal = true;
				} else {
					word = word.substring(1);
					for (int checkRow = row - 1; checkRow <= row + 1 && !retVal; checkRow++) {
						for (int checkCol = col - 1; checkCol <= col + 1 && !retVal; checkCol++) {
							retVal = hasWord(word, checkRow, checkCol, shadow);
						}
					}
					shadow[row][col] = false;
				}
			}
		}

		return retVal;
	}

	/**
	 * Randomly assigns chars to the board based on the specified probabilities
	 */
	public void mix() {
		Random random = new Random();

		for (int row = 0; row < 4; row++)
			for (int col = 0; col < 4; col++)
				board[row][col] = LETTERS.charAt(random.nextInt(96));

	} // method mix()

	/**
	 * Return the Boggle board as a String
	 * 
	 * @return the board as a String
	 */
	public String toString() {
		String boardAsString = "";

		for (int row = 0; row < 4; row++)
			for (int col = 0; col < 4; col++) {
				boardAsString += board[row][col] + " ";
				if (col == 3)
					boardAsString = boardAsString.trim() + "\n";

			} // end for

		return boardAsString;

	} // method toString()

	/******************************* Static Methods ***********************/

	/**
	 * createBoard - Singleton method to ensure that there is never more than a
	 * single BoggleBoard object
	 * 
	 * Modification: **MLN PA02 - Added for PA2 (to make PA3 easier)
	 * 
	 * @return the one and only Dictionary
	 */
	public static BoggleBoard getBoggleBoard() {
		if (brd == null)
			brd = new BoggleBoard();

		return brd;

	} // static Singleton method

	/**
	 * createBoard - Singleton method to ensure that there is never more than a
	 * single BoggleBoard object
	 * 
	 * Modification: **MLN PA02 - Added for PA2 (to make PA3 easier)
	 * 
	 * @return the one and only Dictionary
	 */
	public static BoggleBoard getStaticBoggleBoard(String letters) {
		if (brd == null)
			brd = new BoggleBoard(letters);

		return brd;

	} // static Singleton method for overloaded constructor

} // class BoggleBoard