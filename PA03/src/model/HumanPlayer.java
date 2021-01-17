package model;

import java.util.Iterator;
import java.util.Random;
/**
* ClassName – represents human player extending the player
*
* Acknowledgements: I acknowledge that I have neither given nor
* received assistance for this assignment except as
* noted below:
* 				TA Juliana 
* 				TA Christy
*
* Modifications: MLN 9/30/2011: Changed ArrayList to linked list
* MLN 10/2/2011: Added method to do something useful
*
* @author Alex Gellios
* @version PA3 (11/4/2016)
*/
public class HumanPlayer extends BogglePlayer{
		
	/**
	 * HumanPlayer - constructor giving it access to the parent classes
     * 
     * Modification: **MLN PA03 - added for PA3
	 */
	public HumanPlayer(){
		super();		
	}
	
	/**
     * Add a word to this player's word list
     * 
     * Modification: **MLN: PA3”added difficulty to parameter and used value to 
     *                      determine when to add to dictionary
     * 
     * @param word the word to add
     * @return true if the word was successfully added to this player's word 
     *         list
     */
    public boolean add( String word, int difficulty )
    {
        boolean success = false;
        int randomNumber = -1;
        Random random = new Random();
        
      
        randomNumber = random.nextInt( 10 ); // number between 1 & 10
        
        success = set.add( word.toLowerCase() );
        
        if ( success && randomNumber < difficulty )
            dictionary.add(  word.toLowerCase() );
        
        
        return success;

    } // method add( String, int )
	
    /**
     * validate - iterates through the set
     * 
     * added for PA3
     */
	public void validate(){
		validateWords(set.iterator());
	}
	
	/**
     * validateWords - iterates through the set
     * 
     * added for PA3
     */
	protected void validateWords(Iterator<String> list){
		super.validateWord(list);
		WordSet bad = getInvalidSet();
		
			for(int i = 0;i < bad.size();i++){
				dictionary.remove(bad.get(i));
			}
	}
}
