package model;
import utilities.Utilities;

/**
 * BoggleGame - manages the game information
 * 
 * Acknowledgements: I acknowledge that I have neither given nor
 * received assistance for this assignment except as
 * noted below:
 * 				TA Juliana 
 * 				TA Christy
 * 
 * Modifications: PA02 - Added parameters dictionary, difficultyLevel 
 *                       pointsToWin
 *                     - Added methods: getDictionary(), getDifficultyLevel(),
 *                       getPointsToWin(), setDifficulty(), setPointsToWin()
 *                     - Refactored logic for "between" and put in static method
 *                       in Utilities class.
 *           PA03 	- Used Singleton versions of Dictionary and BoggleBoard
 *           		-added global variables
 *           		-altered for human and computer players
 *           		-added startNewGame method
 * 
 * @author Michael Norton
 * @author Alex Gellios
 * @version PA02 (8 October 2016), PA01 (9/20/2016)
 */
public class BoggleGame
{
    private int difficulty;
    private int pointsToWin;
    private int roundNumber;
    
    private BoggleBoard board; 
    private ComputerPlayer computer; //new for PA3
    private HumanPlayer human;      //new for PA3
    private Dictionary dictionary; 
    
    public static final int COMPUTER = 0;
    public static final int DEFAULT_DIFFICULTY = 5;
    public static final int DEFAULT_POINTS_TO_WIN = 100;
    public static final int HUMAN = 1;
    public static final int MAX_DIFFICULTY = 10;
    public static final int MAX_POINTS_TO_WIN = 10000;
    public static final int MIN_DIFFICULTY = 1;
    public static final int MIN_POINTS_TO_WIN = 1;
    
    /**
     * default constructor
     */
    public BoggleGame()
    {
        roundNumber = 0;
        difficulty = DEFAULT_DIFFICULTY;
        pointsToWin = DEFAULT_POINTS_TO_WIN;
        
        board = BoggleBoard.getBoggleBoard();
        dictionary = Dictionary.getBoggleDictionary();
        computer = new ComputerPlayer();
        human = new HumanPlayer();
        

    } // default constructor
    
    /**
     * Return the Boggle board
     * 
     * @return the BoggleBoard
     */
    public BoggleBoard getBoard()
    {
        return board;

    } // method getBoard()
    

    /**
     * Return the Dictionary
     * 
     * Modification: **MLN PA01 - New for PA2
     * 
     * @return the Dictionary
     */
    public Dictionary getDictionary()
    {
        return dictionary;

    } // method getDictionary()
    
    /**
     * Return the difficulty level
     * 
     * Modification: **MLN PA02 - New for PA2
     * 
     * @return the difficulty level
     */
    public int getDifficultyLevel()
    {
        return difficulty;
        
    } // method getDifficultyLevel()
    
    
    /**
     * Return the Boggle player
     * 
     * modified for PA3
     * determines which player to get(human or computer)
     * 
     * @return the BogglePlayer
     */
    public BogglePlayer getPlayer(int which)
    {   BogglePlayer retVal = null;
    
    	if(which == 0)
    		retVal = computer;
    	if(which == 1)
    		retVal = human;
    	
		return retVal;

    } // method getPlayer()
    
    
    /**
     * Return the points required to win the game
     * 
     * Modification: **MLN PA02 - New for PA2
     * 
     * @return the points to win
     */
    public int getPointsToWin()
    {
        return pointsToWin;

    } // method getPointsToWin()
    
    
    /**
     * Return the current round number
     * 
     * @return the current round number
     */
    public int getRoundNumber()
    {
        return roundNumber;
    
    } // method getRoundNumber()
    
    /**
     * Play a single round of Boggle
     */
    public void playRound()
    {
        roundNumber++;
        board.mix();
        
    } // method playRound()


    /**
     * Set the difficulty level
     * 
     * Modification: **MLN PA02 - New for PA2
     * 
     * @param difficulty
     */
    public void setDifficulty( int level )
    {
        difficulty = DEFAULT_DIFFICULTY;
        
        if ( Utilities.isBetween( level, MIN_DIFFICULTY, MAX_DIFFICULTY ) )
            difficulty = level;
        
    } // method setDifficulty( int )
    
    
    /**
     * Set the points required to win
     * 
     * Modification: **MLN PA02 - New for PA2
     * 
     * @param points
     */
    public void setPointsToWin( int points )
    {
        pointsToWin = DEFAULT_POINTS_TO_WIN;
        
        if ( Utilities.isBetween( points, MIN_POINTS_TO_WIN, 
                MAX_POINTS_TO_WIN ) )
            pointsToWin = points;
        
    } // method setPointsToWin( int )
    
    /**
     * Resets the game
     * 
     * Modification: **MLN PA03 - New for PA3
     * resets for player and computer
     * 
     * @param points
     */
    public void startNewGame(){ 
    	roundNumber = 0;
    	human.resetGame();
    	computer.resetGame();
    }
    
} // class BoggleGame