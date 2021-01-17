package view.gui;

import utilities.BoggleConstants;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.BoggleListener;

/**
 * GamePanel - Panel holding the radioButtons, quit, and restart methods
 * 
 * Acknowledgements: I acknowledge that I have neither given nor received
 * assistance for this assignment except as noted below:
 * 
 * TA Juliana TA Christy TA Sydney
 * 
 * @author Alex
 * @version 12/9/2016
 */

public class GamePanel {

	private BoggleListener listener;
	private ButtonGroup group;

	// constants
	int defPoints = BoggleConstants.DEFAULT_POINTS_TO_WIN;
	int minPoints = BoggleConstants.MIN_POINTS_TO_WIN;
	int maxPoints = BoggleConstants.MAX_POINTS_TO_WIN;
	int minDif = BoggleConstants.MIN_DIFFICULTY;
	int maxDif = BoggleConstants.MAX_DIFFICULTY;

	// create panels
	private JPanel game;
	private JPanel difPanel;
	private JPanel pointPanel;

	// create label
	private JLabel difLabel;
	private JLabel pointLabel;

	// create buttons
	private JRadioButton[] difficulty;
	private JButton restart;
	private JButton quit;

	// create spinner
	private SpinnerNumberModel points;
	private JSpinner pointSelect;

	private int difficultyLevel;

	/**
	 * Default constructor
	 */
	public GamePanel(BoggleListener listen) {

		listener = listen;

		createComponents(); // create components
		setPanels(); // set panel layout
		addBorders(); // add borders
		addComponents();
		addListeners();
	}

	/**
	 * checks if button is selected
	 * 
	 * @return int value
	 */
	public int getDifficulty() {

		if (difficulty[0].isSelected())
			difficultyLevel = 1;
		if (difficulty[1].isSelected())
			difficultyLevel = 2;
		if (difficulty[2].isSelected())
			difficultyLevel = 3;
		if (difficulty[3].isSelected())
			difficultyLevel = 4;
		if (difficulty[4].isSelected())
			difficultyLevel = 5;
		if (difficulty[5].isSelected())
			difficultyLevel = 6;
		if (difficulty[6].isSelected())
			difficultyLevel = 7;
		if (difficulty[7].isSelected())
			difficultyLevel = 8;
		if (difficulty[8].isSelected())
			difficultyLevel = 9;
		if (difficulty[9].isSelected())
			difficultyLevel = 10;

		return difficultyLevel;
	}
	
	/**
	 * getGamePanel - gets the game Panel
	 * 
	 * @return game panel
	 */
	public JPanel getGamePanel() {
		return game;
	}

	/**
	 * getQuitButton - gets the quit button
	 * 
	 * @return quit button
	 */
	public JButton getQuitButton() {
		return quit;
	}

	/**
	 * getRadioButton5 - gets RadioButton5
	 * 
	 * @return radioButton
	 */
	public JRadioButton getRadioButton5() {
		return difficulty[4];
	}

	/**
	 * getRestartButton - gets the restart button
	 * 
	 * @return restart button
	 */
	public JButton getRestartButton() {
		return restart;
	}

	/**
	 * getSpinner - gets the spinner
	 * 
	 * @return spinner
	 */
	public JSpinner getSpinner() {
		return pointSelect;
	}

	/**
	 * getSpinnerNumberModel - gets the number model in the spinner
	 * 
	 * @return quit button
	 */
	public SpinnerModel getSpinnerModel() {
		return points;
	}

	/**
	 * Add borders to the panels
	 */
	private void addBorders() {
		// create border, insert & title for the game panel
		CompoundBorder externalBorder = new CompoundBorder(new EmptyBorder(10, 10, 10, 10),
				new TitledBorder(new EtchedBorder(), "Game"));

		// set borders
		game.setBorder(externalBorder);

	} // method addBorders

	/**
	 * addComponents - add components to their preferred place
	 */
	private void addComponents() {
		// add components to difficulty panel
		difPanel.add(difLabel);
		for (int i = 0; i < 10; i++) {
			difPanel.add(difficulty[i]);
		}

		// add components to point panel
		pointPanel.add(pointLabel);
		pointPanel.add(pointSelect);
		pointPanel.add(Box.createHorizontalStrut(60));
		pointPanel.add(restart);
		pointPanel.add(quit);

		// add components to game panel
		game.add(difPanel);
		game.add(pointPanel);
	}

	/**
	 * addListeners - add listeners to buttons
	 */
	private void addListeners() {
		restart.addActionListener(listener);
		quit.addActionListener(listener);
	}

	/**
	 * createComponents - creates the components for the Panel
	 */
	private void createComponents() {

		// create panels
		game = new JPanel();
		difPanel = new JPanel();
		pointPanel = new JPanel();

		// create Labels
		difLabel = new JLabel("Difficulty: ");
		pointLabel = new JLabel("Needed to Win: ");

		// create spinner
		points = new SpinnerNumberModel(defPoints, minPoints, maxPoints, 1);
		pointSelect = new JSpinner(points);

		// create buttons
		difficulty = new JRadioButton[10];
		for (int i = 0; i < 10; i++) {
			difficulty[i] = new JRadioButton(Integer.toString(i + 1));
		}

		group = new ButtonGroup();
		for (int i = 0; i < 10; i++) {
			group.add(difficulty[i]);
		}
		difficulty[4].setSelected(true);

		restart = new JButton("Restart");
		quit = new JButton("Quit");

	}

	/**
	 * setPanels - sets the panels to their preferred layout
	 */
	private void setPanels() {
		// set panel layout
		game.setLayout(new BoxLayout(game, BoxLayout.Y_AXIS)); // set layout
		difPanel.setLayout(new BoxLayout(difPanel, BoxLayout.X_AXIS)); // set
																		// layout
		pointLabel.setLayout(new BoxLayout(pointLabel, BoxLayout.X_AXIS)); // set
																			// layout
	}
}
