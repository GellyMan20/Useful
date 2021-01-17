package view.gui;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.BoggleListener;

/**
 * ScorePanel - Panel taking in the bottom score panel and top score panel to
 * create one panel
 * 
 * Acknowledgements: I acknowledge that I have neither given nor received
 * assistance for this assignment except as noted below:
 * 
 * TA Juliana TA Christy TA Sydney
 * 
 * @author Alex
 * @version 12/9/2016
 */

public class ScorePanel {

	// create panels
	JPanel score;
	JPanel topPanel;
	JPanel bottomPanel;

	// add other panels
	ScoreTopPanel topScore;
	ScoreBottomPanel bottomScore;

	//create listener to access other stuff
	private BoggleListener listener;

	/**
	 * Default Constructor
	 * 
	 * @param listen
	 */
	public ScorePanel(BoggleListener listen) {

		listener = listen;

		createComponents();
		setPanels();
		addBorders();
		addComponents();
	}
	
	/**
	 * getBottomScore - gets the bottom of the score Panel
	 * 
	 * @return score panel
	 */
	public ScoreBottomPanel getBottomScore() {
		return bottomScore;
	}
	
	/**
	 * getScoringPanel - gets the Score Panel
	 * 
	 * @return score 
	 */
	public JPanel getScoringPanel() {
		return score;
	}

	/**
	 * getTopScorePanel - gets the topPanel
	 * 
	 * @return topScore
	 */
	public ScoreTopPanel getTopScorePanel() {
		return topScore;
	}
	
	/**
	 * Add borders to the panels
	 */
	private void addBorders() {
		// create border, inset & title for the basepanel
		CompoundBorder externalBorder = new CompoundBorder(new EmptyBorder(10, 10, 10, 10),
				new TitledBorder(new EtchedBorder(), "Scoring"));

		score.setBorder(externalBorder);
	}
	
	/**
	 * addComponents - add components to their preferred place
	 */
	private void addComponents() {
		score.add(topPanel);
		score.add(bottomPanel);
	}
	
	/**
	 * createComponents - creates the components for the Panel
	 */
	private void createComponents() {
		score = new JPanel();

		// create components from other classes
		topScore = new ScoreTopPanel(listener);
		bottomScore = new ScoreBottomPanel(listener);

		// get panels
		topPanel = topScore.getTopScorePanel();
		bottomPanel = bottomScore.getBottomScorePanel();

	}

	/**
	 * setPanels - sets the panels to their preferred layout
	 */
	private void setPanels() {
		score.setLayout(new BoxLayout(score, BoxLayout.Y_AXIS));
	}

}
