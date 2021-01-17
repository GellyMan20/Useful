package tests;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import model.BoggleBoard;
import model.BogglePlayer;
import model.ComputerPlayer;
import model.Dictionary;
import model.HumanPlayer;
import model.WordSet;

/**
 * BogglePlayerTest - this class tests the Boggle player
 * 
 * @author Michael Norton
 * @version PA01 (19 September 2016)
 */
public class BogglePlayerTest
{
    private BoggleBoard board;
    private HumanPlayer human;
    private Dictionary dictionary;
    
    public static final int NUMBER = 10;
    
    public BogglePlayerTest()
    {
        dictionary = Dictionary.getBoggleDictionary();
        board = BoggleBoard.getStaticBoggleBoard( "LTYBHGAOLOSTNATA" );
    }
    
    @Before
    public void setup()
    {
        dictionary.clear();
        
        dictionary.add( "Hello" );
        dictionary.add( "there" );

        human = new HumanPlayer();
        
        for ( int i = 0; i < NUMBER; i++ )
            human.add( Integer.toString( i ), 10 ); // add all words
        
    }

    @Test
    public void testAddNormalHighDifficulty()
    {
        boolean success = human.add( "Again", 10 );       
        assertTrue( "Player.add(): Word \"Again\" either not added or cannot be found using set.contains()", 
                human.getSet().contains( "Again" ) );
        assertTrue( "Word added but returns false", success );
        assertEquals( "Should be 11 words in word set", 11, human.getSet().size() );
        assertEquals( "Should be 13 words in dictonary with high difficulty", 13, dictionary.size() );
        
    }

    @Test
    public void testAddNormalLowDifficulty()
    {
        for ( int i = 0; i < 20; i++ )
            human.add( "" + i, 3 );
        assertEquals( 20, human.getSet().size() );
        assertTrue( "Low difficulty, word set size should be greater than dictionary size", human.getSet().size() > dictionary.size() );
        
    }

    @Test
    public void testGet()
    {
        human.reset();
        
        human.add( "Hello",  10 );
        human.add( "there",  10 );
        
        String[] words = new String[ human.getSet().size() ];
        
        for ( int i = 0; i < human.getSet().size(); i++ )
            words[ i ] = human.getWord( i );
            
        for ( int j = 0; j < words.length; j++ )
        {
            assertNotNull( "Word should not be null", human.getWord( j ) );
            assertTrue( "Correct word not found", human.getWord( j ).equalsIgnoreCase( 
                    "Hello") || human.getWord( j ).equalsIgnoreCase( "there") );
        }
        
    }


    @Test
    public void testGetTooHigh()
    {
        assertNull("getWord( list.size() ) should return null", human.getWord( 
                human.getSet().size() ) );
    }
    
    @Test
    public void testGetTooLow()
    {
        assertNull( "getWord( -1 ) should return null", human.getWord( -1 ) );
    }

    @Test
    public void testGetSet()
    {
        assertNotNull("WordSet object should not be null", human.getSet() );
    }
    
    @Test
    public void testComputeScoreNoWords()
    {
        human.reset();
        assertEquals( "No Words, round score should be 0", 0, human.getRoundScore() );
        assertEquals( "No Words, total score should be 0", 0, human.getTotalScore() );
    }
    
    @Test
    public void testComputeScoreWords1Round()
    {
        WordSet unique = human.getUniqueSet();

        human.reset();
        unique.add( "Hello" );
        unique.add( "In" );
        unique.add( "hungry" );
 
        assertEquals( "3 Words, round score should be 0", 0, human.getRoundScore() );
        assertEquals( "3 Words, total score should be 0", 0, human.getTotalScore() );
    }

    @Test
    public void testComputeScoreWords2Rounds()
    {
        WordSet unique = human.getUniqueSet();
        
        
        human.reset();
        unique.add( "Hello" );
        unique.add( "In" );
        unique.add( "hungery" );

        human.computeScore();
        
        assertEquals( "1st round, 3 Words, round score should be 7", 7, human.getRoundScore() );
        assertEquals( "1st round, 3 Words, total score should be 7", 7, human.getTotalScore() );

        human.reset();
        unique.add( "Jello" );
        unique.add( "On" );
        unique.add( "Germany" );

        human.computeScore();
        
        assertEquals( "2nd round, 3 Words, round score should be 7", 7, human.getRoundScore() );
        assertEquals( "2nd round, 3 Words, total score should be 14", 14, human.getTotalScore() );
        
    }
    
    @Test
    public void testRemoveValid()
    {
        int origSize = dictionary.size();
        
        human.remove( "" + ( NUMBER - 1 ) );
        
        assertEquals( "Remove word, size should be less 1", origSize - 1, dictionary.size() );
        assertFalse( dictionary.contains( "" + ( NUMBER - 1 ) ) );
        
    }
    
    @Test
    public void testRemoveInvalid()
    {
        int origSize = dictionary.size();
        
        human.remove( "Michael" );
        
        assertEquals( "Remove non-existant word, size should remain unchanged", origSize, dictionary.size() );
    }
    
    @Test
    public void testRemoveNull()
    {
        int origSize = dictionary.size();
        
        human.remove( null );
        
        assertEquals( "Remove null, size should remain unchanged", origSize, dictionary.size() );
        
    }
    
    @Test
    public void testRemoveEmptyString()
    {
        int origSize = dictionary.size();
        
        human.remove( "" );
        
        assertEquals( "Remove empty string, size should remain unchanged", origSize, dictionary.size() );

    }
    
    @Test
    public void testHumanValidate()
    {
        // get the necessary objects
        WordSet valid = human.getValidSet();
        
        // clear the sets and dictionary
        human.reset();
        dictionary.clear();
        
        // add words with high difficulty (all should end up in dictionary)
        human.add( "lost", 10 );
        human.add( "goal", 10 );
        human.add( "onion", 10 );
        
        human.validate();
        
        assertEquals( "Dictionary should have 2 items after computer player validation", 2, dictionary.size() );
        assertEquals( "Valid WordSet should have 2 items after computer player validation", 2, valid.size() );
               
    }

    @Test
    public void testComputerValidate()
    {
        BogglePlayer computer = new ComputerPlayer();      
        WordSet valid = computer.getValidSet();

        dictionary.clear();
        computer.reset();
        
        // add words to dictionary
        dictionary.add( "lost" );
        dictionary.add( "goal" );
        dictionary.add( "onion" );
        
        computer.validate();
        
        assertEquals( "Dictionary should have 3 items after computer player validation", 3, dictionary.size() );
        assertEquals( "Valid WordSet should have 2 items after computer player validation", 2, valid.size() );
               
    }
    
    @Test
    public void testReset()
    {
        WordSet invalid = human.getInvalidSet();
        WordSet reject = human.getRejectSet();
        WordSet set = human.getSet();
        WordSet unique = human.getUniqueSet();
        WordSet valid = human.getValidSet();
        
        // make sure sets are empty
        invalid.clear();
        reject.clear();
        set.clear();
        unique.clear();
        valid.clear();
        
        // add item to each set
        invalid.add( "item" );
        reject.add( "reject" );
        set.add( "set" );
        unique.add( "unique" );
        valid.add( "valid" );
        
        // assert size == 1
        assertEquals( "Invalid set should have 1 item before reset()", 1, invalid.size() );
        assertEquals( "Reject set should have 1 item before reset()", 1, reject.size() );
        assertEquals( "Set set should have 1 item before reset()", 1, set.size() );
        assertEquals( "Unique set should have 1 item before reset()", 1, unique.size() );
        assertEquals( "Valid set should have 1 item before reset()", 1, valid.size() );
        
        // call reset method
        human.reset();
        
        // all sizes should be 0
        assertEquals( "Invalid set should have 0 items after reset()", 0, invalid.size() );
        assertEquals( "Reject set should have 0 items after reset()", 0, reject.size() );
        assertEquals( "Set set should have 0 items after reset()", 0, set.size() );
        assertEquals( "Unique set should have 0 items after reset()", 0, unique.size() );
        assertEquals( "Valid set should have 0 items after reset()", 0, valid.size() );
        
        
    }
    
    private void generateUnique()
    {
        WordSet set = human.getSet();
        WordSet unique = human.getUniqueSet();
        
        if ( unique.size() == 0 )
        {
            if ( set.size() > 0 )
            {
                Iterator<String> iterator = set.iterator();
                
                while (iterator.hasNext() )
                    unique.add( iterator.next() );
            }

        }
        
    }
}
