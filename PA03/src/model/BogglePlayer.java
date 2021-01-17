package model;
import java.util.Iterator;
import java.util.Random;

import utilities.Utilities;

/**
 * BogglePlayer - manages the details for a single Boggle player
 * 
 * Acknowledgements: I acknowledge that I have neither given nor
 * received assistance for this assignment except as
 * noted below:
 * 				TA Juliana 
 * 				TA Christy
 * 
 * Modifications: PA02 - Added parameter dictionary to constructor 
 *                     - Added methods computeScore(), getRoundScore(), 
 *                       getTotalScore(), remove(), reset() 
 *                     - Added method getRejectSet(): not in SRS 
 *                     - Changed method getList() to getSet()
 *                     - Modified add() to add to dictionary according to  
 *                       supplied difficulty level
 *                PA3: Used Singleton version of Dictionary
 *                	   class is now abstract used by human and computer players
 *                			-added getInValidSet
 *                			-added getRejectSet
 *                			-added UniqueSet
 *                			-added ValidSet
 *                			-added resetGame
 *                			-added validate
 *                			-added abstract validateWord
 * 
 * @author Michael Norton
 * @author Alex Gellios
 * @version PA03 (3 November 2016), PA01 (20 September 2016)
 */
abstract public class BogglePlayer
{
    private int roundScore;  
    private int totalScore;
    
    protected BoggleBoard board;
    protected Dictionary dictionary;
    protected WordSet rejectSet;
    protected WordSet set;
    protected WordSet validSet;
    protected WordSet invalidSet;
    protected WordSet uniqueSet;
    
    
    
    /**
     * Explicit value constructor
     * 
     * Modification: **MLN PA2-Added the dictionary to the parameter            
     *                           
     * @param d - the dictionary object
     */
    public BogglePlayer()
    {
        roundScore = 0;
        totalScore = 0;
        
        board = BoggleBoard.getBoggleBoard();
        dictionary = Dictionary.getBoggleDictionary();
        board = BoggleBoard.getBoggleBoard();
        invalidSet = new WordSet();
        rejectSet = new WordSet();
        set = new WordSet();
        uniqueSet = new WordSet();
        validSet = new WordSet();

    } // explicit value constructor
    
    /**
     * Computes the round and total scores for the player
     * 
     * Modification: **MLN PA02 - new for PA2
     */
    public void computeScore()
    {
        int points = 0;
        
        for ( int i = 0; i < uniqueSet.size(); i++ )
        {
            switch ( uniqueSet.get( i ).length() )
            {
                case 0: case 1: case 2: points = 0; break;
                case 3: case 4: points = 1; break;
                case 5: points = 2; break;
                case 6: points = 3; break;
                case 7: points = 5; break;
                default: points = 11;
            }
            
            roundScore += points;
            totalScore += points;
        }
        
    }
    
    /**
     * Gets the invalid WordSet
     * 
     * Modification: **MLN PA03 - new for PA3
     */
    public WordSet getInvalidSet(){
    	WordSet retVal = null;
    	if(invalidSet != null)
    		retVal = invalidSet;
     	return retVal;
    	
    }
    
    /**
     * Return the player's set of rejected words
     * 
     * Modification: **MLN PA3 - added for PA3
     * 
     * @return a WordSet containing this player's rejected words
     */
    public WordSet getRejectSet()
    {	    	
        return rejectSet;
    }
    
    
    /**
     * Return this player's word list
     * 
     * Modification: **MLN: PA2 - changed WordList to WordSet and changed name
     *                      of method to getSet
     * 
     * @return the word list for this player
     */
    public WordSet getSet()
    {
        return set;

    } // method getList()
    
    /**
     * Return the round score
     * 
     * Modification: **MLN PA02 - New for PA2
     * 
     * @return the score for the current round
     */
    public int getRoundScore()
    {
        return roundScore;
    
    } // method getRoundScore()
    
    /**
     * Return the total score
     * 
     * Modification: **MLN PA02 - New for PA2
     * 
     * @return the total
     */
    public int getTotalScore()
    {
        return totalScore;
    
    } // method getTotalScore()
    
    /**
     * Returns wordSet object containing unique words
     * 
     * Modification: **MLN: PA3 - new for PA3 
     * 
     * @return the word list for this player
     */
    public WordSet getUniqueSet()
    {
    	
        return uniqueSet;

    } // method getList()
    
    /**
     * Returns wordSet object containing valid words
     * 
     * Modification: **MLN: PA3 - new for PA3 
     * 
     * @return the word list for this player
     */
    public WordSet getValidSet()
    {
        return validSet;

    } // method getList()
    
    
    
    /**
     * Return a word from this player's word list
     * 
     * @param position the position in the list for the word to retrieve
     * @return a word from the word list
     */
    public String getWord( int position )
    {
        return set.get( position );
 
    } // method getWord( int )
    
    
    /**
     * Remove a word from the dictionary if added during the current round
     * 
     * Modification: **MLN PA02 - New for PA2
     *               Note: logic for ensuring word in set is redundant - 
     *               this is handled by the set operations in the 
     *               controller (but better safe than sorry!!)
     * 
     * @param word - the word to remove from the dictionary
     */
    public void remove( String word )
    {   
        if ( Utilities.isValidWord( word ) )
            if ( set.contains( word.toLowerCase() ) )
                dictionary.remove( word.toLowerCase() );

    } // method remove( String )
    
    /**
     * Clear the player's word set and set the round score to 0
     * 
     * Modification: **MLN PA02 - Added for PA2
     */
    public void reset()
    {	
    	 roundScore = 0;
    	
    	invalidSet.clear();
    	uniqueSet.clear();
        rejectSet.clear();
        set.clear();
        validSet.clear();
        
        
    } // method reset()
    
    /**
     * reset round and set the score game to 0
     * 
     * Modification: **MLN PA03 - Added for PA3
     */
    public void resetGame(){
    	totalScore = 0;
    	reset();
    }
    
    
    /**
     * sets the uniqueSet to the incoming set
     * 
     * Modification: **MLN PA03 - Added for PA3
     */
    public void setUniqueSet(WordSet in){
    	uniqueSet = in;
    }
    
    /** 
     * checks to see if word is valid and adds it to validSe if it is and inValid set if it isn't
     * 
     * Modification: **MLN PA03 - Added for PA3
     */
    protected void validateWord(Iterator<String> list){
    	invalidSet.clear();
    	validSet.clear();
    	
    	while(list.hasNext()){
    		String value = list.next();
    		if(board.hasWord(value))validSet.add(value);
    		else invalidSet.add(value);
    	}    	
    }
    
    /** 
     * abstract class meant to be used for children
     * 
     * Modification: **MLN PA03 - Added for PA3
     */
    abstract public void validate();

}