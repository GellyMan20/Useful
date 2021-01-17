package view.gui;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.BoggleListener;

/**
 * BoggleWindow - Frame holding all of the panels 
 * 
 * Acknowledgements: I acknowledge that I have neither given nor received
 * assistance for this assignment except as noted below: 
 * 					
 * 					TA Juliana 
 * 					TA Christy
 * 					TA Sydney
 * 
 * @author Alex
 * @version 12/9/2016
 */

public class BoggleWindow extends JFrame {

	private JPanel boggleWindow;
	private JPanel westSide;
	private JPanel eastSide;

	private BoggleEast east;
	private BoggleWest west;
	
	private BoggleListener listener;

	/**
	 * Default Constructor
	 * 
	 * @param listen
	 */
	public BoggleWindow() {
		
		addComponents();
		
		// JFrame stuff
		setVisible(true);
		setContentPane(boggleWindow);
		pack();
		
		this.addWindowListener( listener );
		
	}
	
	/**
	 * getEast - gets the east side
	 * 
	 * @return eastSide object
	 */
	public BoggleEast getEast(){
		return east;
	}
	
	/**
	 * getWest - gets the west side
	 * 
	 * @return westSide object
	 */
	public BoggleWest getWest(){
		return west;
	}
	
	/**
	 * addComponents - add components to their preferred place
	 */
	private void addComponents() {
		
		listener = new BoggleListener( this );
		
		// create Objects
		east = new BoggleEast(listener);
		west = new BoggleWest(listener);
		
		//create main Panel
		boggleWindow = new JPanel();
		boggleWindow.setLayout(new BoxLayout(boggleWindow, BoxLayout.X_AXIS));
		
		//create other panels
		eastSide = east.getEastSide();
		westSide = west.getWestPanel();
		
		//add panels to main panel
		boggleWindow.add(westSide);
		boggleWindow.add(eastSide);
	
	}
	
	/**
	 * main method
	 * 
	 * @param Args
	 */
	public static void main(String[] Args){
		new BoggleWindow();
	}
}
