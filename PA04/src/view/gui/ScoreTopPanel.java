package view.gui;
import model.BogglePlayer;
import model.BoggleGame;

import model.HumanPlayer;
import model.ComputerPlayer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.BoggleListener;

/**
 * ScoreTopPanel - Panel consisting of radioButtons, JSpinner, restart, and quit button 
 * 
 * Acknowledgements: I acknowledge that I have neither given nor received
 * assistance for this assignment except as noted below: 
 * 					
 * 					TA Juliana 
 * 					TA Christy
 * 					TA Sydney
 * 
 * @author Alex Gellios
 * @version 12/9/2016
 */

public class ScoreTopPanel {
	
	// create panels
	private JPanel score;
	private JPanel roundPanel;
	private JPanel playerPanel;
	private JPanel roundScorePanel;
	private JPanel totalScorePanel;

	// create Labels
	private JLabel roundLabel;
	private JLabel roundScoreLabel;
	private JLabel totalScoreLabel;
	private JLabel humanLabel;
	private JLabel computerLabel;

	// create labels containing values
	private JLabel roundNumLabel;
	private JLabel humRoundNumLabel;
	private JLabel comRoundNumLabel;
	private JLabel humTotalScoreNumLabel;
	private JLabel comTotalScoreNumLabel;
	
	//create listener to access other things
	private BoggleListener listener;
	
	
	/**
	 * Default Constructor
	 * 
	 * @param listen
	 */
	public ScoreTopPanel(BoggleListener listen) {
			
		listener = listen;
		
		// create everything
		createComponents();
		addBorders();
		setPanels();
		addComponents();
	}
	
	
	/**
	 * getTopScorePanel - gets the whole panel created by this class
	 * 
	 * @return score panel
	 */
	public JPanel getTopScorePanel(){
		return score;
	}
	/**
	 * getroundLabel - gets roundLabel
	 * 
	 * @return roundNumLabel
	 */
	public JLabel getRoundLabel(){
		return roundNumLabel;
	}
	
	/**
	 * getHumRoundNumberLabel - gets roundLabel
	 * 
	 * @return humRoundNumLabel
	 */
	public JLabel getHumRoundNumberLabel(){
		return humRoundNumLabel;
	}
	
	/**
	 * getcomRoundNumberLabel - gets roundLabel
	 * 
	 * @return comRoundNumLabel
	 */
	public JLabel getcomRoundNumberLabel(){
		return comRoundNumLabel;
	}
	
	/**
	 * gethumTotalScoreNumberLabel - gets roundLabel
	 * 
	 * @return humTotalScoreNumLabel
	 */
	public JLabel gethumTotalScoreNumberLabel(){
		return humTotalScoreNumLabel;
	}
	
	/**
	 * getcomTotalScoreNumberLabel - gets roundLabel
	 * 
	 * @return comTotalScoreNumLabel
	 */
	public JLabel getcomTotalScoreNumberLabel(){
		return comTotalScoreNumLabel;
	}
	
	/**
	 * addBorders - add borders to the specified panels
	 */	
	private void addBorders() {
		// create border, insert & title for the game panel
		CompoundBorder externalBorder = new CompoundBorder(new EmptyBorder(10, 10, 10, 10),
				new TitledBorder(new EtchedBorder(), ""));

		// set borders
		score.setBorder(externalBorder);

	} // method addBorders

	
	/**
	 * addComponents - add components to their preferred place
	 */
	private void addComponents() {
		// add components to round Label
		roundPanel.add(roundLabel);
		roundPanel.add(roundNumLabel);

		// add components to player panel
		playerPanel.add(humanLabel);
		playerPanel.add(computerLabel);

		// add components to round score Panel
		roundScorePanel.add(roundScoreLabel);
		roundScorePanel.add(humRoundNumLabel);
		roundScorePanel.add(comRoundNumLabel);

		// add components to total score
		totalScorePanel.add(totalScoreLabel);
		totalScorePanel.add(humTotalScoreNumLabel);
		totalScorePanel.add(comTotalScoreNumLabel);

		// add components to score panel
		score.add(roundPanel);
		score.add(Box.createHorizontalStrut(100));
		score.add(playerPanel);
		score.add(Box.createHorizontalStrut(20));
		score.add(roundScorePanel);
		score.add(Box.createHorizontalStrut(10));
		score.add(totalScorePanel);
		score.add(Box.createHorizontalStrut(10));

	}

	/**
	 * createComponents - creates the components for the Panel
	 */
	private void createComponents() {
		
		// create panels
		score = new JPanel();
		roundPanel = new JPanel();
		playerPanel = new JPanel();
		roundScorePanel = new JPanel();
		totalScorePanel = new JPanel();

		// create Labels
		roundLabel = new JLabel("Round: ");
		roundScoreLabel = new JLabel("Round Score");
		totalScoreLabel = new JLabel("Total Score");
		humanLabel = new JLabel("            You:");
		computerLabel = new JLabel("Computer:");

		// Labels Containing Values
		roundNumLabel = new JLabel(Integer.toString(listener.getGame().getRoundNumber()));
		humRoundNumLabel = new JLabel("   " + listener.getGame().getPlayer(1).getRoundScore());
		comRoundNumLabel = new JLabel("   " + listener.getGame().getPlayer(0).getRoundScore());
		humTotalScoreNumLabel = new JLabel("   " + listener.getGame().getPlayer(1).getTotalScore());
		comTotalScoreNumLabel = new JLabel("   " + listener.getGame().getPlayer(0).getTotalScore());

	}
	
	/**
	 * setPanels - sets the panels to their preferred layout
	 */
	private void setPanels() {
		roundPanel.setLayout(new BoxLayout(roundPanel, BoxLayout.X_AXIS)); // set layout
		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS)); // set layout
		roundScorePanel.setLayout(new BoxLayout(roundScorePanel, BoxLayout.Y_AXIS)); // set layout
		totalScorePanel.setLayout(new BoxLayout(totalScorePanel, BoxLayout.Y_AXIS)); // set layout
		score.setLayout(new BoxLayout(score, BoxLayout.X_AXIS)); // set layout

	}

}
