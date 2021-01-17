package view.gui;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.BoggleListener;

/**
 * BoggleEast - Takes GamePanel and Score Panel and creates one panel for them
 * 
 * Acknowledgements: I acknowledge that I have neither given nor received
 * assistance for this assignment except as noted below: 
 * 						TA Juliana 
 * 						TA Christy
 * 						TA Sydney
 * 
 * @author Alex
 * @version 12/9/2016
 */

public class BoggleEast {

	private JPanel eastSide;
	private JPanel gamePart;
	private JPanel scorePart;

	private GamePanel game;
	private ScorePanel score;

	private BoggleListener listener;

	/**
	 * Default Constructor
	 * 
	 * @param listen
	 */
	public BoggleEast(BoggleListener listen) {

		listener = listen;

		// do everything
		createComponents();
		addComponents();
	}

	/**
	 * getEastSide - gets east side of main panel
	 * 
	 * @return eastSide
	 */
	public JPanel getEastSide() {
		return eastSide;
	}

	/**
	 * gamePanel - gets Game Panel
	 * 
	 * @return game
	 */
	public GamePanel getGamePanel() {
		return game;
	}

	/**
	 * getScorePanel - gets score panel
	 * 
	 * @return score
	 */
	public ScorePanel getScorePanel() {
		return score;
	}
	
	/**
	 * addComponents - add components to their preferred place
	 */
	private void addComponents(){
		// add components together
		eastSide.add(gamePart);
		eastSide.add(scorePart);
	}
	
	/**
	 * createComponents - creates the components for the Panel
	 */
	private void createComponents() {
		// create master panel
		eastSide = new JPanel();
		eastSide.setLayout(new BoxLayout(eastSide, BoxLayout.Y_AXIS)); // set
																		// layout

		// create components from other classes
		game = new GamePanel(listener);
		score = new ScorePanel(listener);

		// create panels
		eastSide = new JPanel();
		gamePart = game.getGamePanel();
		scorePart = score.getScoringPanel();
	}
}