package view.text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BoggleTextIO - handles all interaction with the user.  All control is 
 * handled by the BoggleControl class
 *
 * @author Michael Norton
 * @version PA01 (20 September 2016)
 */
public class BoggleTextIO
{
    //----------------------------------------------------------------------
    // Declarations
    //----------------------------------------------------------------------
    private BufferedReader reader;

    public static final String NL = "\n";
    public static final String TAB = "\t";


    /**
     * Default Constructor
     */
    public BoggleTextIO()
    {
        reader = new BufferedReader(
            new InputStreamReader(
                System.in));

    } // constructor


    /**
     * display - Overloaded method - displays char to user
     *
     * @param the char to display
     */
    public void display( char c )
    {
        System.out.print( c );

    } // method display


    /**
     * display - Overloaded method - displays String to user
     *
     * @param the string to display
     */
    public void display( String s )
    {
        System.out.print( s );

    } // method display


    /**
     * get - Gets input from the user
     *
     * @return the string entered by the user
     */
    public String get() throws IOException
    {
        return reader.readLine();

    } // method get
    

    /**
     * pause - Pause the screen and ask the user to press ENTER to continue
     * @throws IOException 
     */
    public void pause() throws IOException
    {
        display( "Press <Enter> to begin the next round. " );
        get();

    }

} // class TextIO
