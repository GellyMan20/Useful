package model;

import java.util.Iterator;
import java.util.Random;

import utilities.BoggleConstants;

/**
 * ComputerPlayer - child of BogglePlayer
 * 
 * Modifications: New for PA3
 * 
 * @author Michael Norton
 * @version PA03 (27 October 2016)
 */
public class HumanPlayer extends BogglePlayer implements BoggleConstants
{
    /**
     * Default constructor
     */
    public HumanPlayer()
    {
        super();
    
    } // default constructor

    
    /**
     * Add a word to this player's word list
     * 
     * Modification: PA02 - added difficulty to parameter and used value to 
     *                      determine when to add to dictionary
     *               PA03 - made abstract in BogglePlayer and defined here
     *                      
     * 
     * @param word the word to add
     * @return true if the word was successfully added to this player's word 
     *         list
     */
    public boolean add( String word, int difficulty )
    {
        boolean success = false;
        Dictionary dictionary = Dictionary.getBoggleDictionary();
        
        int randomNumber = -1;
        Random random = new Random();
        
        randomNumber = random.nextInt( 10 ); // number between 1 & 10
        
        success = getSet().add( word.toLowerCase() );
        
        if ( success && randomNumber < difficulty )
            dictionary.add(  word.toLowerCase() );
        
        return success;

    } // method add( String, int )
    

    /**
     * Build the appropriate Iterator and send it to the validateWords() method
     */
    @Override
    public void validate()
    {
        Iterator< String > list = getSet().iterator();
        
        validateWords( list );
        
    } // method validate
    
	
    /**
     * Run parent version of method to create valid/invalid sets. Then remove
     * words from the Dictionary
     * 
     * @param the Iterator for the human player
     */
    @Override
    protected void validateWords( Iterator< String > list )
    {
        super.validateWords( list );
        WordSet invalid = getInvalidSet();
        Dictionary dict = Dictionary.getBoggleDictionary();
        
        // remove invalid words from the dictionary
        for ( int i = 0; i < invalid.size(); i++ )
            dict.remove( invalid.get( i ) );
        
    }  // method validateWords

} // child class HumanPlayer
