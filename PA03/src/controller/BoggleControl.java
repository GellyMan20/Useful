package controller;
import model.BoggleGame;
import model.BogglePlayer;
import model.ComputerPlayer;
import model.BoggleBoard;
import model.WordSet;
import utilities.EggTimer;
import utilities.Utilities;
import view.file.BoggleFileIO;
import view.text.BoggleTextIO;
import model.Dictionary;
import model.HumanPlayer;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * BoggleBoardControl - represents controls the action of the Boggle game.
 * 
 * Acknowledgements: I acknowledge that I have neither given nor
* received assistance for this assignment except as
* noted below:
* 				TA Juliana 
* 				TA Christy
 * 
 * Modifications: PA02 = Added displayScore(), getDifficultyLevel(), 
 *                       getPointsToWin(), getRejectWords(), playSingleRound(),
 *                       rejectWords() methods, writeFile()
 *                     - Modified run(), addWordsToPlayer(), beginGame(),
 *                       displayScore(), endGame(), endRound(), playRounds()
 *                PA03 = added getDisplayStuff(),
 *                		 added parameters to displayWordList for getDisplayStuff()   
 *                       
 *
 * @author Michael Norton
 * @version PA02 (13 October 2016), PA01 (23 September 2016)
 */
public class BoggleControl
{
    // ----------------------------------------------------------------------
    // Declarations
    // ----------------------------------------------------------------------
    private EggTimer timer;
    private BoggleFileIO fileIO;    // added for PA2
    private BoggleGame game;
    private BoggleTextIO io;
    private HumanPlayer human;
    private ComputerPlayer computer;

    public static int GAME_TIME = 3;
    public static String NL = "\n";
    public static String TAB = "\t";

    /**
     * default constructor
     * 
     * @throws IOException
     */
    public BoggleControl() throws IOException
    {
        fileIO = new BoggleFileIO();  // added for PA2
        game = new BoggleGame();
        io = new BoggleTextIO();
        computer = (ComputerPlayer)game.getPlayer(0);
        human = (HumanPlayer)game.getPlayer(1);
        

    } // constructor

    /************************** Public methods ****************************/

    /**
     * run - Play a game of boggle
     *  
     * Modification: **MLN - modified for PA2 to test for success of 
     *                       dictionary open
     *
     * @throws IOException
     */
    public void run() throws IOException
    {	
    	String again = "";
    	do{
        if ( beginGame() )
        {
            playRounds();
            endGame();
        }
        else
            io.display( "No Dictionary - Unable to Play!!!" + NL );
        	System.out.println("Play another game (y or n)>");
        	again = io.get();
        	game.startNewGame();
    	}while(again.equalsIgnoreCase("y"));
    } // method playGame


    /*************************** Private Methods **************************/

    /**
     * addWordsToPlayer
     *
     * add the list of words from the player to the Player's word list
     * 
     * Modification: **MLN PA02 - added difficulty level to param of player.add
     *                          - refactor: add null and length tests
     *
     * @param words (String)
     */
    private void addWordsToPlayer( String words )
    {
        if ( words != null )
        {
          
            String[] wordArray = words.split( "\\s+" );

            for ( String word : wordArray )
                if ( Utilities.isValidWord( word ) )
                    human.add( word, game.getDifficultyLevel() );

        } // end if

    } // method addWordsToPlayer


    /**
     * beginGame
     *
     * handles beginning of game events
     * 
     * ** modified for PA2 -- added call to readFile
     * ** modified for PA2 -- added code to get/set pointsToWin and
     * ** difficulty
     *
     * @return boolean
     * @throws IOException
     */
    private boolean beginGame() throws IOException
    {
        boolean existsDictionary = readFile();  // added for PA2

        if ( existsDictionary )
        {
            io.display( "Welcome to Boggle" + NL );
            io.display( NL );

            // added for PA2
            getPointsToWin();

            // added for PA2
            getDifficultyLevel();

        } // end if

        return existsDictionary;

    } // method beginGame

    /**
     * beginRound
     *
     * handles beginning of round events
     *
     * @throws IOException
     */
    private void beginRound() throws IOException
    {
        game.playRound(); // init game
        
        io.display( NL );
        io.pause();
        io.display( NL );
     }

    /**
     * displayBoard
     *
     * displays board to user
     *
     * @throws IOException
     */
    private void displayBoard() throws IOException
    {
        BoggleBoard board = BoggleBoard.getBoggleBoard(); // access current game board

        String[] boardArray = board.toString().split( "\n" );

        for ( String line : boardArray )
            io.display( TAB + line + NL );

    } // method displayBoard


    /**
     * displayScore - displays score to user
     * 
     * Modification: **MLN PA02 - added for PA2
     *
     * @param player (BogglePlayer)
     * @throws IOException
     */
    private void displayScore( int roundNumber ) 
      throws IOException
    {
        io.display( NL + "Round " + roundNumber + NL );
        io.display( "Round Scores " + NL);
        io.display( "         You: " + human.getRoundScore() + NL);
        io.display( "    Computer: " + computer.getRoundScore() + NL);
        io.display( "Game Scores " + NL);
        io.display( "         You: " + human.getTotalScore() + NL);
        io.display( "    Computer: " + computer.getTotalScore() + NL);
        io.display( NL );

    } // method displayScore


    /**
     * displayWords - displays words at the end of a round
     * 
     * Modification: **MLN PA02 - added comments to clarify what is going on
     * 				modified for PA3:
     * 								takes in parameters specific to each prompt 
     * 
     * @param prompt - prompt to display
     * @param in - set specific to each wordset to display
     */
    private void displayWordList(String prompt, WordSet in)
    {
        // declarations of variables required for the display
        boolean wordsLeftToPrint = true;
        int lineCounter = 0;    // use to determine which prompt to print
        int splitPosition = 0;  // where to split the line
        int wordsLength = 0;

        String intro = ""; // point either to prompt or to spaces
        String spaces = "                        ";

        String tempList = "";
        String toPrint = "";
        String wordList = "";

        // instantiations required for the display
        // player = game.getPlayer(); **MLN sent via parameter so not needed

        wordList = in.toString();
        wordsLength = 80 - prompt.length(); // amount of space left for words
                                            // after intro
        
        // print the list
        while ( wordsLeftToPrint )
        {
            wordsLeftToPrint = wordList.length() > 0;

            if ( wordsLeftToPrint )
            {
                // locate boundary for printing
                tempList = wordList.length() < wordsLength ? wordList
                        : wordList.substring( 0, wordsLength );
                splitPosition = tempList.lastIndexOf( ' ' );

                intro = lineCounter == 0 ? prompt : spaces; // what to print
                                                            // before the words

                toPrint = wordList.length() < wordsLength // the words to print
                        ? intro + tempList.trim()
                        : intro + tempList.substring( 0, splitPosition ).trim();
                io.display( toPrint + "\n" );

                // what is left over?
                if ( splitPosition > -1 && wordList.length() > wordsLength )
                    wordList = wordList.substring( splitPosition ).trim();
                else
                {
                    wordsLeftToPrint = false;
                    wordList = "";

                } // end else

                lineCounter++;
                
            } // end if
            
        } // end while

    } // method displayWords

    /**
     * endGame - handles end of game events
     * 
     * Modification: **MLN PA02 - added call to writeFile
     *
     * @throws IOException
     */
    private void endGame() throws IOException
    {

    	if( human.getTotalScore() >= game.getPointsToWin())
    	{
    		System.out.println("You Win!");
    	}
    	else if(computer.getTotalScore() >= game.getPointsToWin())
    	{
    		System.out.println("The computer wins!");
    	}
    	else if(human.getTotalScore() == computer.getTotalScore())
    	{
    		System.out.println("It's a Tie!");
    	}
        if ( !writeFile() )           // added for PA2
        {
            io.display("Unable to write new File" + NL);
            io.display("Original Dictionary preserved." + NL);

        } // end if

    } // method endGame


    /**
     * endRound - handles end of round events
     *
     * Modification: **MLN PA02 - return type changed to boolean to indicate
     *                            whether or not to continue - if game score >= 
     *                            points needed, then carryOn = false;
     *
     * @return boolean
     * @throws IOException
     */
    private boolean endRound() throws IOException
    {
        boolean carryOn = true; // added for PA2

        //BogglePlayer player = game.getPlayer();
        Toolkit tk = Toolkit.getDefaultToolkit();

        tk.beep();
        io.display( NL );

        // following lines added for PA2
        // changed for PA3
        getDisplayStuff();
        
        getRejectWords( human );
        
        human.computeScore();
        computer.computeScore();

        displayScore( game.getRoundNumber() );
        human.reset(); // reset score & lists
        computer.reset(); // reset score & lists

        if ( human.getTotalScore() >= game.getPointsToWin()){
        	carryOn = false;
        }
        else if(computer.getTotalScore() >= game.getPointsToWin())
        {
        	carryOn = false;
        }

        return carryOn;

    } // method endRound

    
       
    /**
     * getDifficultyLevel - get the level of difficulty for the game
     * 
     * Modification: **MLN PA02 - added for PA2
     * 
     * @throws IOException
     */
    private void getDifficultyLevel() throws IOException
    {
        String entry;
        io.display( "Difficulty level (1-10; enter for 5)> " );
        entry = io.get();
        if ( Utilities.isValidInt( entry, BoggleGame.MIN_DIFFICULTY, 
            BoggleGame.MAX_DIFFICULTY ) )
                game.setDifficulty( Integer.parseInt(entry) );
    
    } // method getDifficultyLevel()
    
    
    /**
     * getDisplayStuff - uses displayWordList to display everything
     * 
     * added for PA3
     */
    private void getDisplayStuff(){
    	String prompt1 = "Words found by both players: ";
    	String prompt2 = "Words typed but not on the board: ";
    	String prompt3 = "Words found only by you: ";
    	String prompt4 = "Words found only by the computer: ";
    	
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
    	
    	displayWordList(prompt1, bothPlayers);
    	displayWordList(prompt2, notOnBoard);
    	displayWordList(prompt3, UserValidWords);
    	displayWordList(prompt4, ComputerValidWords);
    	
    }    
    
    /**
     * getPointsToWin - get the number of points required to win
     * 
     * Modification: **MLN PA02 - added for PA2
     * 
     * @throws IOException
     */
    private void getPointsToWin() throws IOException
    {
        String entry;
        io.display( "Points needed to win (1-10,000; enter for 100)> " );
        entry = io.get();
        if ( Utilities.isValidInt(entry, BoggleGame.MIN_POINTS_TO_WIN,
                BoggleGame.MAX_POINTS_TO_WIN ) )
            game.setPointsToWin( Integer.parseInt(entry) );
    
    } // method getPointsToWin()

    /***
     * rejectWords - get words from user to remove from the dictionary
     *
     * Modification: **MLN PA02 - added for PA2
     * 
     * @param player - the BogglePlayer
     * @throws IOException
     **/
    private void getRejectWords( BogglePlayer player ) throws IOException
    {
        int lineNumber = 1;
        
        String entry = "";
        String[] wordArray;
        
        WordSet rejectedWords = player.getRejectSet();
    
        do
        {
            if ( lineNumber == 1 )
                io.display( NL + "Type rejected words; type <Enter> on a blank " +
                  "line when done." + NL );
    
            io.display( "Rejected word> " );
            entry = io.get();
    
            if ( Utilities.isValidWord( entry ) ) // to catch user attempt to escape out
            {
                wordArray = entry.split( "\\s+" );
                for ( String word : wordArray )
                    if ( Utilities.isValidWord( word ) )
                        rejectedWords.add( word );
    
            } // end if
            
            lineNumber++;
    
        } while ( Utilities.isValidWord( entry ) );
        
        rejectWords( rejectedWords ); // use set methods to handle the word rejection
    
    } // method rejectWords( BogglePlayer )

    /**
     * getWords - get words from the player
     *
     * @return the words entered by the user
     * @throws IOException
     */
     private String getWords() throws IOException
     {
        io.display( timer.getTimeLeft() + "> " );

        return io.get();

     } // method getWords


    /**
     * playRounds - plays multiple rounds of Boggle
     * 
     * **MLN - modified for PA2 to play multiple rounds
     *
     * @throws IOException
     */
    private void playRounds() throws IOException
    {
        do
        {
            beginRound();
            playSingleRound();

        } while ( endRound() );

    } // method playRounds()


    /**
     * playSingleRound - play a single round of Boggle
     * 
     * Modification: **MLN PA02 - Refactored for PA2
     * 
     * @throws IOException
     */
    private void playSingleRound() throws IOException
    {
        timer = new EggTimer( GAME_TIME );

        io.display( "Round " + game.getRoundNumber() + NL );
        displayBoard();

        io.display( NL + "You have three minutes to enter " +
                "words, one per line." + NL );

        while( timer.getSecondsLeft() > 0 )
            addWordsToPlayer( getWords() );
        
    } // method playSingleRound()
    
    
    /**
     * readFile - read file and populate Dictionary
     *
     * Modification: **MLN PA02 - added for PA2
     *
     * @return true if the file can be read
     **/
    private boolean readFile() throws IOException
    {
        Dictionary dict = Dictionary.getBoggleDictionary();

        boolean canRead = fileIO.open( BoggleFileIO.READER );

        if ( canRead )
        {
            String word = fileIO.readLine();
            while ( word != null )
            {
                 dict.add( word.toLowerCase() );
                 word = fileIO.readLine();

            } // end while

            fileIO.close( BoggleFileIO.READER );

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
    private void rejectWords( WordSet rejectSet )
    {
        //BogglePlayer player = game.getPlayer();
              
        WordSet thisSet = human.getSet();
        WordSet wordsLeft;
        WordSet validRejects;
        
        wordsLeft = thisSet.difference( rejectSet );
        validRejects = thisSet.intersection( rejectSet );
        
        // remove words from player's word set
        thisSet = wordsLeft; 
        
        // remove words from dictionary
        rejectWords( validRejects.iterator() );     
        
    } // method rejectWords( WordSet )
    
    
    /**
     * Reject the words from the Dictionary
     * 
     * Modification: **MLN PA02 - added for PA2
     * 
     * @param rejectSet - an Iterator from the rejectSet
     */
    private void rejectWords( Iterator< String > rejectSet )
    {
        Dictionary dict = game.getDictionary();
        
        while ( rejectSet.hasNext() )
            dict.remove( rejectSet.next() );
        
    } // method rejectWords( Iterator )
    
    
    /**
     * writeFile - write file from Dictionary - if over 300 words randomly select
     * 300 without writing duplicates
     *
     * Modification: **MLN PA02 - added for PA2
     *
     * @return true if writing is possible
     * @throws IOException
     **/
    private boolean writeFile() throws IOException
    {
        boolean canWrite = fileIO.open( BoggleFileIO.WRITER );
        Dictionary dict = Dictionary.getBoggleDictionary();
        ArrayList< String > dictList = dict.getDictionary();
        
        if ( canWrite )
        {
            
            // 300 or fewer, write everything
            if ( dictList.size() <= 300 )
                for ( int i = 0; i < dictList.size(); i++ )
                    fileIO.write( dictList.get(i) );

            // otherwise randomly write 300
            else
            {
                Random rand = new Random();
                int[] tracker = new int[ dictList.size() ]; // store slots used
                int counter = 0;
                int index = 0;


                while ( counter < 300 )
                {
                    index = rand.nextInt( dictList.size() );

                    // write this if it hasn't already been written
                    if ( tracker[ index ] != 1 )
                    {
                        fileIO.write( dictList.get( index ) );
                        tracker[ index ] = 1;
                        counter++;

                    } // end if

                } // end while

            } // end else

            fileIO.close( BoggleFileIO.WRITER );

        } // end if

        return canWrite;

    } // method writeFile()
    
} // class BoggleControl
