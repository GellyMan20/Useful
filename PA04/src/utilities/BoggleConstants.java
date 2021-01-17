package utilities;

/**
 * Finals for general use in the Boggle game
 *
 * @author Michael Norton
 * @date 27 October 2016
 */
public interface BoggleConstants
{
    public static final int COMPUTER = 0;
    public static final int HUMAN = 1;
    
    public static final int LIST = 0;       // added for PA3
    public static final int VALID = 1;      // added for PA3
    public static final int INVALID = 2;    // added for PA3
    public static final int UNIQUE = 3;     // added for PA3

    public static final int DEFAULT_DIFFICULTY = 5;
    public static final int DEFAULT_POINTS_TO_WIN = 100;
    public static final int MAX_DIFFICULTY = 10;
    public static final int MAX_POINTS_TO_WIN = 10000;
    public static final int MIN_DIFFICULTY = 1;
    public static final int MIN_POINTS_TO_WIN = 10;
    
    public static final int MAX_WORDS = 300;
    
    public static final int READER = 0;
    public static final int WRITER = 1;
    
    public static String NL = "\n";
    public static String TAB = "\t";

    public static int GAME_TIME = 180;

    public static final int ALL_WORDS = 0;          // added for PA3
    public static final int INVALID_WORDS = 1;      // added for PA3
    public static final int HUMAN_UNIQUE = 2;       // added for PA3
    public static final int COMPUTER_UNIQUE = 3;    // added for PA3

} // interface BoggleConstants
