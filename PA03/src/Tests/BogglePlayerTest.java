package Tests;
import model.HumanPlayer;
import model.BoggleBoard;
import model.Dictionary;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * BogglePlayerTest - this class tests the Boggle player
 * 
 * @author Michael Norton
 * @version PA01 (19 September 2016)
 */
public class BogglePlayerTest
{
	private BoggleBoard board;
    private HumanPlayer player;
    private Dictionary dictionary;
    
    public static final int NUMBER = 10;
    
    @Before
    public void setup()
    {
        dictionary = Dictionary.getBoggleDictionary();
        board = BoggleBoard.getStaticBoggleBoard( "LTYBHGVOLOSTNATA" );
        
        dictionary.clear();
        
        dictionary.add( "Hello" );
        dictionary.add( "there" );

        player = new HumanPlayer();
        
        for ( int i = 0; i < NUMBER; i++ )
            player.add( Integer.toString( i ), 10 ); // add all words
        
    }

    @Test
    public void testAddNormalHighDifficulty()
    {
        boolean success = player.add( "Again", 10 );       
        assertTrue( "add(): Word not added to the end", 
                player.getSet().contains( "Again" ) );
        assertTrue( "Word added but returns false", success );
        assertEquals( "Should be 11 words in word set", 11, player.getSet().size() );
        assertEquals( "Should be 13 words in dictonary with high difficulty", 13, dictionary.size() );
        
    }

    @Test
    public void testAddNormalLowDifficulty()
    {
        for ( int i = 0; i < 20; i++ )
            player.add( "" + i, 3 );
        assertEquals( 20, player.getSet().size() );
        assertTrue( "Low difficulty, word set size should be greater than dictionary size", player.getSet().size() > dictionary.size() );
        
    }

    @Test
    public void testGet()
    {
        player.reset();
        
        player.add( "Hello",  10 );
        player.add( "there",  10 );
        
        String[] words = new String[ player.getSet().size() ];
        
        for ( int i = 0; i < player.getSet().size(); i++ )
            words[ i ] = player.getWord( i );
            
        for ( int j = 0; j < words.length; j++ )
        {
            assertNotNull( "Word should not be null", player.getWord( j ) );
            assertTrue( "Correct word not found", player.getWord( j ).equalsIgnoreCase( 
                    "Hello") || player.getWord( j ).equalsIgnoreCase( "there") );
        }
        
    }


    @Test
    public void testGetTooHigh()
    {
        assertNull("getWord( list.size() ) should return null", player.getWord( 
                player.getSet().size() ) );
    }
    
    @Test
    public void testGetTooLow()
    {
        assertNull( "getWord( -1 ) should return null", player.getWord( -1 ) );
    }

    @Test
    public void testGetSet()
    {
        assertNotNull("WordSet object should not be null", player.getSet() );
    }
    
    @Test
    public void testComputeScoreNoWords()
    {
        player.reset();
        assertEquals( "No Words, round score should be 0", 0, player.getRoundScore() );
        assertEquals( "No Words, total score should be 0", 0, player.getTotalScore() );
    }
    
    @Test
    public void testComputeScoreWords1Round()
    {
        player.reset();
        player.add( "Hello", 5 );
        player.add( "In", 5 );
        player.add( "hungery", 5 );
        assertEquals( "3 Words, round score should be 0", 0, player.getRoundScore() );
        assertEquals( "3 Words, total score should be 0", 0, player.getTotalScore() );
    }

    @Test
    public void testComputeScoreWords2Rounds()
    {
        player.reset();
        player.add( "Hello", 10 );
        player.add( "In", 10 );
        player.add( "hungery", 10 );
        player.computeScore();
        
        assertEquals( "1st round, 3 Words, round score should be 7", 7, player.getRoundScore() );
        assertEquals( "1st round, 3 Words, total score should be 7", 7, player.getTotalScore() );

        player.reset();
        player.add( "Jello", 10 );
        player.add( "On", 10 );
        player.add( "Germany", 10 );
        player.computeScore();
        
        assertEquals( "2nd round, 3 Words, round score should be 7", 7, player.getRoundScore() );
        assertEquals( "2nd round, 3 Words, total score should be 14", 14, player.getTotalScore() );
        
    }
    
    public void testGetInvalidSet(){
    	player.add("toast",10);
    	player.validate();
    	equals(player.getValidSet().equals(player.getSet()));
    }
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void testRemoveValid()
    {
        int origSize = dictionary.size();
        
        player.remove( "" + ( NUMBER - 1 ) );
        
        assertEquals( "Remove word, size should be less 1", origSize - 1, dictionary.size() );
        assertFalse( dictionary.contains( "" + ( NUMBER - 1 ) ) );
        
    }
    
    @Test
    public void testRemoveInvalid()
    {
        int origSize = dictionary.size();
        
        player.remove( "Michael" );
        
        assertEquals( "Remove non-existant word, size should remain unchanged", origSize, dictionary.size() );
    }
    
    @Test
    public void testRemoveNull()
    {
        int origSize = dictionary.size();
        
        player.remove( null );
        
        assertEquals( "Remove null, size should remain unchanged", origSize, dictionary.size() );
        
    }
    
    @Test
    public void testRemoveEmptyString()
    {
        int origSize = dictionary.size();
        
        player.remove( "" );
        
        assertEquals( "Remove empty string, size should remain unchanged", origSize, dictionary.size() );

    }

}