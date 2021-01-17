package Tests;
import model.BoggleBoard;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * BoggleBoardTest - this class tests the Boggle board
 * 
 * @author Michael Norton
 * @version PA01 (19 September 2016)
 */
public class BoggleBoardTest
{
    private BoggleBoard board;
    
    @Before
    public void setup()
    {
        board = BoggleBoard.getStaticBoggleBoard( "LTYBHGVOLOSTNATA" );
    }

    @Test
    public void testGetCellInvalidTooLow()
    {
        assertEquals( "getCell(-1,-1) should return (char)0", 
                board.getCell( -1, -1 ), (char)0 );
    }

    @Test
    public void testGetCellInvalidTooHigh()
    {
        assertEquals( "getCell(4,4) should return (char)0", 
                board.getCell( 4, 4 ), (char)0 );
    }

    @Test
    public void testGetCellLowerLeftCorner()
    {
        assertEquals( "getCell(3, 0 ) should return \'N\'", 
                board.getCell( 3, 0 ), 'N' );
    }

    @Test
    public void testGetCellLowerRightCorner()
    {
        assertEquals( "getCell(3, 3 ) should return \'A\'", 
                board.getCell( 3, 3 ), 'A' );
    }

    @Test
    public void testGetCellMiddle()
    {
        assertEquals( "getCell( 1, 2 ) should return \'V\'", 
                board.getCell( 1, 2 ), 'V' );
    }

    @Test
    public void testGetCellUpperLeftCorner()
    {
        assertEquals( "getCell( 0, 0 ) should return \'L\'", 
                board.getCell( 0, 0 ), 'L' );
    }

    @Test
    public void testGetCellUpperRightCorner()
    {
        assertEquals( "getCell( 0, 3 ) should return \'B\'", 
                board.getCell( 0, 3 ), 'B' );
    }
    
    @Test
    public void testHasWord(){
    	assertTrue(board.hasWord("toast"));
    	assertTrue(board.hasWord("goal"));
    	assertFalse(board.hasWord("boby"));
    	assertFalse(board.hasWord("Door"));
    }
    
    @Test
    public void testToString()
    {
        String compBoard = "L T Y B\n" +
                           "H G V O\n" +
                           "L O S T\n" +
                           "N A T A";
        
        String actualBoard = board.toString();
        
        if ( actualBoard.indexOf('\t') > -1 )
            actualBoard = removeTabs( actualBoard );
        
        // adjust for final newline if present
        if ( actualBoard.length() == 32 )
            actualBoard = actualBoard.substring( 0, 31 );
        
        assertEquals( "BoggleBoard.toString() incorrect. Check for trailing space at end of lines", 
                compBoard, actualBoard );
               
    }
 
    private String removeTabs( String actualBoard )
    {
        String newBoard = "";
        
        for ( int i = 0; i < actualBoard.length(); i++ )
        {
            if ( actualBoard.charAt( i ) != '\t' )
                newBoard += actualBoard.charAt( i );
        }
        
        return newBoard;
    }

}