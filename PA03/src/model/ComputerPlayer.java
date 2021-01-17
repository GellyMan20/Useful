package model;

import java.io.IOException;

public class ComputerPlayer extends BogglePlayer {
	
/**
* ComputerPlayer – represents the computer player extending the player
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
	/**
	 * ComputerPlayer - constructor giving it access to the parent classes
     * 
     * Modification: **MLN PA03 - added for PA3
	 */
	public ComputerPlayer() {
		super();		
	}
	
	/**
	 * validate - iterates through the dictionary
     * 
     * Modification: **MLN PA03 - added for PA3
	 */
	public void validate() 
	{
		validateWord(dictionary.iterator());
	}

}
