package controller;

import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import model.Dictionary;
import model.HumanPlayer;
import model.BoggleBoard;
import model.BoggleGame;
import model.BogglePlayer;
import model.WordSet;
import utilities.BoggleConstants;
import utilities.EggTimer;
import utilities.Utilities;

import view.file.BoggleFileIO;
import view.text.BoggleTextIO;

/**
 * BoggleBoardControl - represents controls the action of the Boggle game.
 * 
 * Modifications: PA02 - Added displayScore(), getDifficultyLevel(), 
 *                       getPointsToWin(), getRejectWords(), playSingleRound(),
 *                       rejectWords() methods, writeFile()
 *                     - Modified run(), addWordsToPlayer(), beginGame(),
 *                       displayScore(), endGame(), endRound(), playRounds()
 *                       
 *                       
 *
 * @author Michael Norton
 * @version PA02 (13 October 2016), PA01 (23 September 2016)
 */
public class BoggleControl implements BoggleConstants
{
    // ----------------------------------------------------------------------
    // Declarations
    // ----------------------------------------------------------------------
    private EggTimer timer;
    private BoggleFileIO fileIO;    // added for PA2
    private BoggleGame game;
    private BoggleTextIO io;


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

    } // constructor

    /************************** Public methods ****************************/

    /**
     * run - Play a game of boggle
     *  
     * Modification: PA02 - modified for PA2 to test for success of 
     *                      dictionary open
     *               PA03 - modified to handle multiple games
     *
     * @throws IOException
     */
    public void run() throws IOException
    {
        if ( readFile() )
        {
            do 
            {
                beginGame();
                playRounds();
                endGame();
            }            
            while ( playAgain() );
            
            if ( !writeFile() )
            {
                io.display( "Unable to write new File\n" );
                io.display( "Original Dictionary preserved.\n" );

            } // end if

            

        }
        else
            io.display( "No Dictionary - Unable to Play!!!" + NL );

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
            BogglePlayer player = game.getPlayer( HUMAN );
            String[] wordArray = words.split( "\\s+" );

            for ( String word : wordArray )
                if ( Utilities.isValidWord( word ) )
                {
                    HumanPlayer human = (HumanPlayer)player;
                    human.add( word, game.getDifficultyLevel() );
                
                } // end if

        } // end if

    } // method addWordsToPlayer


    /**
     * beginGame
     *
     * handles beginning of game events
     * 
     * Modification: P0A2 - added call to readFile
     *                    - added code to get/set pointsToWin and
     *                      difficulty
     *               PA03 = removed call to readFile
     *
     * @return boolean
     * @throws IOException
     */
    private void beginGame() throws IOException
    {
        io.display( "Welcome to Boggle" + NL );
        io.display( NL );

        // added for PA2
        getPointsToWin();

        // added for PA2
        getDifficultyLevel();

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
     * Modification: PA02 - added for PA2
     *               PA03 - scores array for both players, show scores for
     *                      both players - move computeScore calls here 
     *                      (from endRound)     
     *                      
     * @param player (BogglePlayer)
     * @throws IOException
     */
    private void displayScore( int roundNumber, BogglePlayer[] players ) 
            throws IOException
    {
        if ( players != null && players.length == 2 )
        {
            for ( int i = 0; i < 2; i++ )
                players[ i ].computeScore();

            io.display( "Round " + roundNumber + NL );
            io.display( "Round Scores" + NL );
            io.display( "         You: " +
                players[ HUMAN ].getRoundScore() + NL );
            io.display( "    Computer: " +
                players[ COMPUTER ].getRoundScore() + NL );
            io.display( "Total Scores" + NL );
            io.display( "         You: " +
                players[ HUMAN ].getTotalScore() + NL );
            io.display( "    Computer: " +
                players[ COMPUTER ].getTotalScore() );

        } // end if

        else
                io.display( "Bad Player array, cannot print scores." );

        io.display( NL );

    } // method displayScore


    /**
     * displayWordList - displays words at the end of a round
     * 
     * Modification: **MLN PA02 - added comments to clarify what is going on
     */
    private void displayWordList( String prompt, WordSet listSet )
    {
        // declarations of variables required for the display
        boolean wordsLeftToPrint = true;
        int lineCounter = 0;    // use to determine which prompt to print
        int splitPosition = 0;  // where to split the line
        int wordsLength = 0;

        String intro = ""; // point either to prompt or to spaces
        String spaces = "                                  "; 
        String tempList = "";
        String toPrint = "";
        String wordList = "";

        // instantiations required for the display
        // player = game.getPlayer(); **MLN sent via parameter so not needed

        if ( listSet != null )
        wordList = listSet.toString();
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
     * displayWords - Displays words at the end of a round
     * 
     * ** added for PA2 (moved from endRound method)
     *
     * @param Player
     */
    private void displayWords( BogglePlayer[] players )
    {
        WordSet[] sets = generateSets( players );
        String[] prompts = generatePrompts();

        // display lists
        for ( int i = 0; i < sets.length; i++ )
            displayWordList( prompts[ i ], sets[ i ] );

        io.display( NL );

    } // method displayWords


    /**
     * endGame - Handles end of game events
     * 
     * ** modified for PA2 -- added call to writeFile
     * ** modified for PA3 -- moved call to writeFile to playGame
     * **                     Ending message code revamped to print 
     * **                     correct winning message **
     *
     * @throws IOException
     */
    private void endGame() throws IOException
    {
        BogglePlayer human = game.getPlayer( HUMAN );
        BogglePlayer computer = game.getPlayer( COMPUTER );
        String message = "";

        if (human.getTotalScore() > computer.getTotalScore() ) 
                message = "You Win!\n";
        else
            if (computer.getTotalScore() > human.getTotalScore() )
                    message = "The Computer Wins!\n";
            else
                    message = "It's a tie!\n";

        // reset for next game
        game.startNewGame();

        io.display( message );


    } // method endGame


    /**
     * endRound - Handles end of round events
     *
     * ** modified for PA2 - return type changed to boolean to indicate
     * **                    whether or not to continue - if game score >= 
     * **                    points needed, then carryOn = false;
     * ** modified for PA3 - added computer player, sent human & computer
     * **                    players to displayWords method
     *
     * @return boolean
     * @throws IOException
     */
    private boolean endRound() throws IOException
    {
        boolean carryOn = true; // added for PA2
        int maxScore = 0;

        BogglePlayer[] players = new BogglePlayer[2];
        Toolkit tk = Toolkit.getDefaultToolkit();

        players[ HUMAN ] = game.getPlayer( HUMAN );
        players[ COMPUTER ] = game.getPlayer( COMPUTER );

        tk.beep();
        io.display( NL );

        // following lines added for PA2
        displayWords( players ); // moved this procedure in PA2
        getRejectWords( players[ HUMAN ] );

        displayScore( game.getRoundNumber(), players );

        // reset scores and lists for both players
        for ( int i = 0; i < players.length; i++ )
            players[ i ].reset();

        maxScore = Math.max(
                players[ HUMAN ].getTotalScore(),
                players[ COMPUTER ].getTotalScore() );

        if ( maxScore >= game.getPointsToWin() )
            carryOn = false;

        return carryOn;

    } // method endRound


    /**
     * generatePrompts - Generate the prompts for the end of round listings
     * 
     * ** added for PA3 **
     *
     * @return an array of prompts
     */
    private String[] generatePrompts()
    {
        String[] prompts = new String[ 4 ];

        // declare & instantiate prompts
        prompts[ ALL_WORDS ] =          "Words found by both players:      ";
        prompts[ INVALID_WORDS ] =      "Words typed but not on the board: ";
        prompts[ HUMAN_UNIQUE ] =       "Words found only by you:          ";
        prompts[ COMPUTER_UNIQUE ] =    "Words found only by the computer: ";

        return prompts;

    } // method generatePrompts


    /**
     * generateSets
     *
     * generate the WordSets for a user
     * 
     * ** added for PA3 **
     *
     * @param a BogglePlayer array
     * @return an array of WordSets
     * @throws IOException
     */
    private WordSet[] generateSets( BogglePlayer[] players )
    {
        WordSet[] sets = new WordSet[ 4 ];

        // don't try this if the incoming array is not correct
        if ( players != null && players.length == 2 )
        {
            // generate the valid/invalid lists for each player
            for ( int i = 0; i < players.length; i++ )
                players[ i ].validate();

            // get the intersection of the two lists
            sets[ ALL_WORDS ] = players[ HUMAN ].getSet().
                intersection(players[ COMPUTER ].
                    getSet() );

            // human list - computer list
            sets[ HUMAN_UNIQUE ] = players[ HUMAN].getValidSet().
                difference(players[ COMPUTER ].getValidSet() );

            // computer list - human list
            sets[ COMPUTER_UNIQUE ] = players[ COMPUTER].getValidSet().
                difference( players[ HUMAN ].getValidSet() );

            // human invalid list
            sets[ INVALID_WORDS ] = players[ HUMAN ].getInvalidSet();

            // set the unique list for each player
            players[ HUMAN ].setUniqueSet( sets[ HUMAN_UNIQUE ] );
            players[ COMPUTER ].setUniqueSet( sets[ COMPUTER_UNIQUE ] );

        } // end if

        return sets;

    } // method generateSets


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
        if ( Utilities.isValidInt( entry, MIN_DIFFICULTY, 
            MAX_DIFFICULTY ) )
                game.setDifficulty( Integer.parseInt(entry) );
    
    } // method getDifficultyLevel()

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
        if ( Utilities.isValidInt(entry, MIN_POINTS_TO_WIN,
                MAX_POINTS_TO_WIN ) )
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
                io.display( "Type rejected words; type <Enter> on a blank " +
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
        
        io.display( NL );
        
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
      * playAgain - Prompt the user to play another game
      * 
      * ** added for PA3 **
      *
      * @return true if playing another game
      * @throws IOException
      */
     private boolean playAgain() throws IOException
     {
         boolean again = false;
         String answer = "";

         io.display( "Play another game (Y/N)? " );
         answer = io.get().toLowerCase();

         if ( answer.length() == 1 && answer.charAt(0) == 'y' )
             again = true;

         return again;

     } // method playAgain


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

        boolean canRead = fileIO.open( READER );

        if ( canRead )
        {
            String word = fileIO.readLine();
            while ( word != null )
            {
                 dict.add( word.toLowerCase() );
                 word = fileIO.readLine();

            } // end while

            fileIO.close( READER );

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
        BogglePlayer player = game.getPlayer( HUMAN );
              
        WordSet thisSet = player.getSet();
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
        Dictionary dict = Dictionary.getBoggleDictionary();
        
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
        boolean canWrite = fileIO.open( WRITER );
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


                while ( counter < MAX_WORDS )
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

            fileIO.close( WRITER );

        } // end if

        return canWrite;

    } // method writeFile()
        
} // class BoggleControl
