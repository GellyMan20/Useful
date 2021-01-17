package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import model.BoggleBoard;
import model.BoggleGame;
import model.BogglePlayer;
import model.ComputerPlayer;
import model.Dictionary;
import model.HumanPlayer;
import model.WordSet;
import utilities.EggTimer;
import utilities.Utilities;
import view.file.BoggleFileIO;
import view.gui.BoggleWest;
import view.gui.BoggleWindow;
import view.text.BoggleTextIO;

/**
 * BoggleListener - handles events generated from BoggleWest
 * 
 * @author Michael Norotn
 * @version HW18 (16 Nov. 2016)
 *
 */
public class BoggleListener extends WindowAdapter implements ActionListener, TickListener {
	// from PA3
	private EggTimer timer;
	private BoggleFileIO fileIO;
	private BoggleGame game;
	private BoggleTextIO io;

	private BoggleWindow frame;
	private int spinValue;
	private String words;

	// add players
	private HumanPlayer human;
	private ComputerPlayer computer;

	/**
	 * Explicit value constructor
	 * 
	 * @param frame
	 *            - the BoggleWest frame
	 */
	public BoggleListener(BoggleWindow frame) {
		fileIO = new BoggleFileIO();
		game = new BoggleGame();
		io = new BoggleTextIO();

		this.frame = frame;

		// create players
		computer = (ComputerPlayer) game.getPlayer(0);
		human = (HumanPlayer) game.getPlayer(1);

	} // explicit value constructor

	/**
	 * Responds to button presses
	 * 
	 * @param e
	 *            - the ActionEvent object
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			boolean existsDictionary = readFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		makeGame();

		// round button
		if (e.getSource() == frame.getWest().getRoundButton()) {
			timerStuff();
			game.playRound();
			frame.getEast().getScorePanel().getBottomScore().getLabel().setText("Game Status: ongoing");
			frame.getEast().getScorePanel().getTopScorePanel().getRoundLabel().setText("   " + game.getRoundNumber());
			frame.getEast().getScorePanel().getBottomScore().getHumWords().clear();
			frame.getEast().getScorePanel().getBottomScore().getComWords().clear();
			frame.getEast().getScorePanel().getBottomScore().getBothWords().clear();
			frame.getEast().getScorePanel().getBottomScore().getInvalidWords().clear();
			human.getSet().clear();
			computer.getSet().clear();
		}
		// quit button
		if (e.getSource() == frame.getEast().getGamePanel().getQuitButton())
			System.exit(0);
		// do restart stuff
		if (e.getSource() == frame.getEast().getGamePanel().getRestartButton()) {
			makeGame();
			restart();
			frame.getWest().getRoundButton().setEnabled(true);
		// reject word button
		
			// do some stuff
		}

	} // method actionPerformed

	/**
	 * Responds to the timer, executes every second
	 * 
	 * @param timer
	 *            - the EggTimer generating the ticks
	 */
	@Override
	public void tick(EggTimer timer) {
		this.timer = timer;

		frame.getWest().getTimeLabel().setText(timer.getTimeLeft());

		if (timer.getSecondsLeft() == 0) {
			
			getDisplayStuff();
			
			human.computeScore();
			computer.computeScore();
			
			frame.getEast().getScorePanel().getTopScorePanel().getRoundLabel().setText("   " + game.getRoundNumber());
			frame.getEast().getScorePanel().getTopScorePanel().getHumRoundNumberLabel()
					.setText("   " + human.getRoundScore());
			frame.getEast().getScorePanel().getTopScorePanel().getcomRoundNumberLabel()
					.setText("   " + computer.getRoundScore());
			frame.getEast().getScorePanel().getTopScorePanel().gethumTotalScoreNumberLabel()
					.setText("   " + human.getTotalScore());
			frame.getEast().getScorePanel().getTopScorePanel().getcomTotalScoreNumberLabel()
					.setText("   " + computer.getTotalScore());
			frame.getWest().getRoundButton().setEnabled(true);
			frame.getWest().getTextArea().setEnabled(false);

			getDisplayStuff();
		} // end if

	} // method tick

	/**
	 * gets the BoggleGame
	 * 
	 * @return game
	 */
	public BoggleGame getGame() {
		return game;
	}

	/**
	 * Respond to window closing
	 * 
	 * @param e
	 *            the WindowEvent object
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);

	} // method windowClosing

	/**
	 * addWordsToPlayer
	 *
	 * add the list of words from the player to the Player's word list
	 * 
	 * Modification: **MLN PA02 - added difficulty level to param of player.add
	 * - refactor: add null and length tests
	 *
	 * @param words
	 *            (String)
	 */
	private void addWordsToPlayer(String words) {
		if (words != null) {

			String[] wordArray = words.split("\\s+");

			for (String word : wordArray)
				if (Utilities.isValidWord(word))
					human.add(word, game.getDifficultyLevel());

		} // end if

	} // method addWordsToPlayer

	/**
	 * endGame - handles end of game events
	 * 
	 * Modification: **MLN PA02 - added call to writeFile
	 *
	 * @throws IOException
	 */
	private void endGame() throws IOException {

		if (human.getTotalScore() >= game.getPointsToWin()) {
			System.out.println("You Win!");
		} else if (computer.getTotalScore() >= game.getPointsToWin()) {
			System.out.println("The computer wins!");
		} else if (human.getTotalScore() == computer.getTotalScore()) {
			System.out.println("It's a Tie!");
		}
		if (!writeFile()) // added for PA2
		{
			io.display("Unable to write new File" + "\n");
			io.display("Original Dictionary preserved." + "\n");
		}
	} // end if

	/**
	 * getWords - gets words from the text panel
	 */
	private String getWords() {
		words = frame.getWest().getTextArea().getText();
		return words;
	}

	/**
	 * getDisplayStuff - uses displayWordList to display everything
	 * 
	 * added for PA3
	 */
	private void getDisplayStuff() {
		
		addWordsToPlayer(getWords());
		
		computer.validate();  
    	WordSet CValidSet = computer.getValidSet();
    	
    	human.validate();
    	WordSet HValidSet = human.getValidSet();
    	
    	human.setUniqueSet(HValidSet.difference(computer.getValidSet()));
    	computer.setUniqueSet(CValidSet.difference(human.getValidSet()));
    	
    	WordSet bothPlayers = human.getValidSet().intersection(computer.getValidSet()); 
    	WordSet notOnBoard = human.getInvalidSet(); 
    	WordSet UserValidWords = human.getUniqueSet(); 
    	WordSet ComputerValidWords = computer.getUniqueSet(); 
		
		System.out.println(human.getUniqueSet());
		System.out.println(HValidSet);
		
		for (int i = 0; i < UserValidWords.size(); i++) {
			frame.getEast().getScorePanel().getBottomScore().getHumWords().insertElementAt(UserValidWords.get(i), i);
			//System.out.println(UserValidWords.get(i));
		}
		for (int i = 0; i < ComputerValidWords.size(); i++) {
			frame.getEast().getScorePanel().getBottomScore().getComWords().insertElementAt(ComputerValidWords.get(i),
					i);
		}
		for (int i = 0; i < bothPlayers.size(); i++) {
			frame.getEast().getScorePanel().getBottomScore().getBothWords().insertElementAt(bothPlayers.get(i), i);
		}	
		for (int i = 0; i < notOnBoard.size(); i++) {
			frame.getEast().getScorePanel().getBottomScore().getInvalidWords().insertElementAt(notOnBoard.get(i), i);
		}	


	}

	/**
	 * readFile - read file and populate Dictionary
	 *
	 * Modification: **MLN PA02 - added for PA2
	 *
	 * @return true if the file can be read
	 **/
	private boolean readFile() throws IOException {
		Dictionary dict = Dictionary.getBoggleDictionary();

		boolean canRead = fileIO.open(BoggleFileIO.READER);

		if (canRead) {
			String word = fileIO.readLine();
			while (word != null) {
				dict.add(word.toLowerCase());
				word = fileIO.readLine();

			} // end while

			fileIO.close(BoggleFileIO.READER);

		} // end if

		return canRead;

	} // method readFile

	/**
	 * Reject the words from the Player's WordSet and Dictionary
	 * 
	 * Modification: **MLN PA02 - added for PA2
	 * 
	 * @param rejectSet
	 */
	private void rejectWords(WordSet rejectSet) {
		BogglePlayer player = human;

		WordSet thisSet = player.getSet();
		WordSet wordsLeft;
		WordSet validRejects;

		wordsLeft = thisSet.difference(rejectSet);
		validRejects = thisSet.intersection(rejectSet);

		// remove words from player's word set
		thisSet = wordsLeft;

		// remove words from dictionary
		rejectWords((WordSet) validRejects.iterator());

	} // method rejectWords( WordSet )

	/**
	 * setDifficulty - sets the difficulty of the game
	 */
	private void setDifficulty() {
		frame.getEast().getGamePanel().getRadioButton5().doClick();
		game.setDifficulty(frame.getEast().getGamePanel().getDifficulty());
	}

	/**
	 * makeGame - makes the board
	 */
	private void makeGame() {

		BoggleBoard board = BoggleBoard.getBoggleBoard();
		JButton[][] gridButtons = frame.getWest().getGridButtons();

		board.mix();
		frame.getWest().getTimeLabel().setText("3:00");

		for (int row = 0; row < 4; row++)
			for (int col = 0; col < 4; col++)
				gridButtons[row][col].setText("" + board.getCell(row, col));

		frame.getWest().getRoundButton().setEnabled(false);
		frame.getWest().getTextArea().setEnabled(true);
		frame.getWest().getTextArea().setText("");
		frame.getWest().getTextArea().requestFocus();

		setDifficulty();
		setPointsToWin();
	}

	/**
	 * restart - restarts the timer
	 */
	private void restart() {
		this.timer.stop();
		frame.getWest().getTextArea().setEnabled(false);
		frame.getWest().getTimeLabel().setText("3:00");
		frame.getEast().getScorePanel().getBottomScore().getLabel().setText("Game Status: Waitng");
	}

	/**
	 * setPointsToWin - sets the point to win
	 */
	private void setPointsToWin() {
		frame.getEast().getGamePanel().getSpinner().setValue(100);
		spinValue = (Integer) frame.getEast().getGamePanel().getSpinner().getValue();
		game.setPointsToWin(spinValue);
	}

	/**
	 * timerStuff - starts timer
	 */
	private void timerStuff() {
		timer = new EggTimer(180);
		timer.addTickListener(this);
		frame.getWest().getTimeLabel().setText("3:00");
	}

	/**
	 * writeFile - write file from Dictionary - if over 300 words randomly
	 * select 300 without writing duplicates
	 *
	 * Modification: **MLN PA02 - added for PA2
	 *
	 * @return true if writing is possible
	 * @throws IOException
	 **/
	private boolean writeFile() throws IOException {
		boolean canWrite = fileIO.open(BoggleFileIO.WRITER);
		Dictionary dict = Dictionary.getBoggleDictionary();
		ArrayList<String> dictList = dict.getDictionary();

		if (canWrite) {

			// 300 or fewer, write everything
			if (dictList.size() <= 300)
				for (int i = 0; i < dictList.size(); i++)
					fileIO.write(dictList.get(i));

			// otherwise randomly write 300
			else {
				Random rand = new Random();
				int[] tracker = new int[dictList.size()]; // store slots used
				int counter = 0;
				int index = 0;

				while (counter < 300) {
					index = rand.nextInt(dictList.size());

					// write this if it hasn't already been written
					if (tracker[index] != 1) {
						fileIO.write(dictList.get(index));
						tracker[index] = 1;
						counter++;

					} // end if

				} // end while

			} // end else

			fileIO.close(BoggleFileIO.WRITER);

		} // end if

		return canWrite;

	} // method writeFile()

} // class BoggleListener