package view.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import controller.BoggleListener;

/**
 * ScoreBottomPanel - Panel holding the JLists 
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

public class ScoreBottomPanel{

	//create Button
	private JButton rejectWordButton;
	
	//create Labels
	private JLabel youLabel;
	private JLabel computerLabel;
	private JLabel commonLabel;
	private JLabel invalidLabel;
	private JLabel gameStatus;
	
	//create Panels
	private JPanel everythingPanel;
	private JPanel gridStuff;
	
	//create Scroll Panes
	private JScrollPane humWordsScroll;
	private JScrollPane comWordsScroll;
	private JScrollPane bothWordsScroll;
	private JScrollPane invalidWordsScroll;
	
	//create Text Fields
	private JList<String> humWordsText;
	private JList<String> comWordsText;
	private JList<String> bothWordsText;
	private JList<String> invalidWordsText;	
	
	//create defaultListModels
	private DefaultListModel<String> humanWordsList;
	private DefaultListModel<String> computerWordsList;
	private DefaultListModel<String> commonWordsList;
	private DefaultListModel<String> invalidWordsList;
	
	//create listener to access other stuff
	private BoggleListener listener;
	
	/**
	 * Default Constructor
	 * 
	 * @param listen
	 */
	public ScoreBottomPanel(BoggleListener listen) {
		
		listener = listen;
		
		// put shit together
		createComponents();
		setPanels();
		addComponents();
		addListeners();
	}
	
	/**
	 * getBottomScorePanel - gets the bottom score panel
	 * 
	 * @return score panel
	 */
	public JPanel getBottomScorePanel(){
		return everythingPanel;
	}
	
	/**
	 * getButton - gets the reject word button
	 * 
	 * @return score panel
	 */
	public JButton getRejectButton(){
		return rejectWordButton;
	}
	
	/**
	 * getLabel - gets the game status
	 * 
	 * @return score panel
	 */
	public JLabel getLabel(){
		return gameStatus;
	}
	
	public DefaultListModel<String> getHumWords(){
		return humanWordsList;
	}
	
	public DefaultListModel<String> getComWords(){
		return computerWordsList;
	}
	
	public DefaultListModel<String> getBothWords(){
		return commonWordsList;
	}
	
	public DefaultListModel<String> getInvalidWords(){
		return invalidWordsList;
	}

	/**
	 * addComponents - add components to their preferred place
	 */
	private void addComponents(){
	
		gridStuff.add(youLabel);
		gridStuff.add(computerLabel);
		gridStuff.add(commonLabel);
		gridStuff.add(invalidLabel);
		gridStuff.add(humWordsScroll);
		gridStuff.add(comWordsScroll);
		gridStuff.add(bothWordsScroll);
		gridStuff.add(invalidWordsScroll);
		
		everythingPanel.add(gridStuff);
		everythingPanel.add(Box.createVerticalStrut(10));
		everythingPanel.add(rejectWordButton);
		everythingPanel.add(Box.createVerticalStrut(10));
		everythingPanel.add(gameStatus);
		}
	
	/**
	 * addLsteners - add listener to reject word button
	 */
    private void addListeners(){
    	rejectWordButton.addActionListener(listener);
    }
	
    /**
	 * createComponents - creates the components for the Panel
	 */
	private void createComponents(){	
		
		//create buttons
		rejectWordButton = new JButton("    Reject Words    ");
		
		//create Panels
		everythingPanel = new JPanel();
		gridStuff = new JPanel();
		
		//create Labels
		youLabel = new JLabel("You");
		computerLabel = new JLabel("Computer");
		commonLabel = new JLabel("Common");
		invalidLabel = new JLabel("Invalid");
		gameStatus = new JLabel("Game Status: Round Over");
		
		//create default list models
		humanWordsList = new DefaultListModel<String>();
		computerWordsList = new DefaultListModel<String>();
		commonWordsList = new DefaultListModel<String>();
		invalidWordsList = new DefaultListModel<String>();
		
		//create text Fields
		humWordsText = new JList<String>(humanWordsList);
		comWordsText = new JList<String>(computerWordsList);
		bothWordsText = new JList<String>(commonWordsList);
		invalidWordsText = new JList<String>(invalidWordsList);
		
		
		//add scroll panels with text panes inside
		humWordsScroll = new JScrollPane(humWordsText);
		comWordsScroll = new JScrollPane(comWordsText);
		bothWordsScroll = new JScrollPane(bothWordsText);
		invalidWordsScroll = new JScrollPane(invalidWordsText);
		
		
		//set sizes
		humWordsScroll.setMinimumSize(new Dimension(200,200));
		comWordsScroll.setMinimumSize(new Dimension(200,200));
		bothWordsScroll.setMinimumSize(new Dimension(200,200));
		invalidWordsScroll.setMinimumSize(new Dimension(200,200));
		
	}
	
	/**
	 * setPanels - sets the panels to their preferred layout
	 */
	private void setPanels(){
		//set panel layout
    	everythingPanel.setLayout(new BoxLayout(everythingPanel, BoxLayout.Y_AXIS)); //set layout     	
    	gridStuff.setLayout(new GridLayout(2,4));
    	  	
	}
	
}
