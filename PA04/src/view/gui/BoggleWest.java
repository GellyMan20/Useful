package view.gui;

import controller.BoggleListener;
import model.BoggleGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * BoggleWest - the West side of the BoggleBoard game
 * 
 * @author Michael Norton
 * @author Alex Gellios
 * @version HW18 (16 Nov. 2016)
 *
 */
public class BoggleWest extends JFrame
{
	private BoggleGame game;
	
	private JButton roundButton;
	private JButton[][] gridButtons;
	private JLabel timeLabel;
	private JTextArea wordEntry;
	private JScrollPane textPane;

	private JPanel basePanel;
	private JPanel buttonPanel;
	private JPanel gridPanel;
	private JPanel midPanel;
	private JPanel textPanel;
	private JPanel timePanel;

    private BoggleListener listener;

    private static final long serialVersionUID = -8284582774189880953L;

    /**
     * Default constructor
     */
    public BoggleWest(BoggleListener listen)
	{
    	listener = listen;
    	
		createComponents();       
		
		game = new BoggleGame();
		
		setPanels();
        setButtonSize();
		addComponents();
		addBorders();
        addListeners();

        wordEntry.setEnabled( false );
        this.addWindowListener( listener );
        pack();
		setVisible(true);
	}
    
    /**
	 * getWestPanel - returns the main west panel
	 */	
    public JPanel getWestPanel(){
    	return basePanel;
    }

   /**
    * Return the round button
    * 
    * @return the round button
    */
    public JButton getRoundButton()
    {
        return roundButton;

    } // method getRoundButton
    
    /**
     * Get the array of grid buttons
     * 
     * @return the array of grid buttons
     */
    public JButton[][] getGridButtons()
    {
        return gridButtons;
        
    } // method getGridButtons

    
    /**
     * Get the text area
     * 
     * @return the text area
     */
    public JTextArea getTextArea()
    {
        return wordEntry;

    } // method getTextArea
    
    
    /**
     * Get the time lable
     * 
     * @return the Time label
     */
    public JLabel getTimeLabel()
    {
        return timeLabel;

    } // method getTimeLabel
    
    
    /**
     * Add borders to the panels
     */
    private void addBorders()
	{
		// create border, inset & title for the basepanel
		CompoundBorder externalBorder = new CompoundBorder (
			new EmptyBorder(10, 10, 10, 10), new TitledBorder (
				new EtchedBorder(), "Play") );

		// create border and inset for other panels
		CompoundBorder internalBorder = new CompoundBorder (
			new EmptyBorder(5, 5, 5, 5), new EtchedBorder() );

		basePanel.setBorder( externalBorder );
		gridPanel.setBorder( internalBorder );
		timePanel.setBorder( internalBorder );
		textPanel.setBorder( internalBorder );

	} // method addBorders


	/**
	 * Add components to the panels and the panels to the frame
	 */
	private void addComponents()
	{
		// add to buttonPanel
		buttonPanel.add(roundButton);

		// add to gridPanel
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				gridPanel.add(gridButtons[i][j]);

		// add to timePanel
		timePanel.add(timeLabel);

		// add to textPanel
		textPanel.add(textPane);

		// add to midPanel
		midPanel.add(buttonPanel, BorderLayout.NORTH);
		midPanel.add(gridPanel, BorderLayout.CENTER);
		midPanel.add(timePanel, BorderLayout.SOUTH);

		// add to basePanel
		basePanel.add(midPanel);
		basePanel.add(textPanel);

		setContentPane(basePanel);

	} // method addComponents


	/**
	 * Add listeners for the frame
	 */
	private void addListeners()
    {
        roundButton.addActionListener( listener );
        
    } // method addListeners

    /**
     * Create the components necessary for the frame
     */
    private void createComponents()
	{
		// create single components
		roundButton = new JButton("Start Round");
		gridButtons = new JButton[4][4];
		timeLabel = new JLabel("0:00", SwingConstants.CENTER);
		wordEntry = new JTextArea(16, 20);
		textPane = new JScrollPane(wordEntry);

		// create and set attributes for grid buttons
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
			{
				gridButtons[i][j] = new JButton(" ");
				gridButtons[i][j].setBackground(Color.white);

			} // end for


		// create panels
		basePanel = new JPanel();
		midPanel = new JPanel();
		buttonPanel = new JPanel();
		gridPanel = new JPanel();
		timePanel = new JPanel();
		textPanel = new JPanel();

	} // method createComponents

	/**
	 * Set the button size & make it square
	 * 
	 * @param btn - the JButton
	 */
	private void setButtonSize()
    {
        
        Dimension d = gridButtons[ 0 ][ 0 ].getPreferredSize();
        int size = (int)( d.getWidth() < d.getHeight() ? d.getHeight() : d.getWidth() );
        size = size * 2 / 3;

	    for ( int row = 0; row < gridButtons.length; row++ )
            for ( int col = 0; col < gridButtons[ 0 ].length; col++ )
            {
                JButton btn = gridButtons[ row ] [ col ];
                btn.setPreferredSize( new Dimension( size, size ) );
                
            } // end for
        
    } // method setButtonSize

	/**
	 * Set the panels
	 */
	private void setPanels()
	{
		basePanel.setLayout(new BoxLayout(basePanel, BoxLayout.Y_AXIS));
		midPanel.setLayout(new BorderLayout());
		gridPanel.setLayout(new GridLayout(4, 4));
		timePanel.setLayout(new BorderLayout());
		textPanel.setLayout(new BorderLayout());

	} // method setPanels

} // class BoggleWindow