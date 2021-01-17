package view.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import utilities.BoggleConstants;
import utilities.Utilities;
/**
 * Class to manage the File I/O operations
 * 
 * @author Michael Norton
 * @version PA02 (12 October 2016)
 */
public class BoggleFileIO implements BoggleConstants
{
    private BufferedReader reader;
    private PrintWriter writer;
    private File file;
        
   /**
     * Default constructor
     */
    public BoggleFileIO()
    {
        file = new File( "dictionary.txt" );
        
    } // default constructor
    
    
    /**
     * Explicit value constructor
     * 
     * @param fileName - the file to open
     */
    public BoggleFileIO( String fileName )
    {   
        this(); // call default just in case this fails
        
        if ( Utilities.isValidWord( fileName) )
            file = new File( fileName );
         
    } // explicit constructor
    
    
    /**
     * Close the selected file
     * 
     * @param which - which file to close (reader/writer)
     * @throws IOException
     */
    public void close( int which ) throws IOException
    {
        if ( which == READER )
        {
            if ( reader != null )
            {
                reader.close();
                reader = null;
                
            } // end if
            
        } // end if
        else
            if ( writer != null )
            {
                writer.close();        
                writer = null;
            
            } // end if
        
    } // method close( int )


    /**
     * Open a file for reading and writing
     * 
     * @param which: reading or writing
     * @return true if the open was successful
     * @throws IOException 
     */
    public boolean open( int which ) throws IOException
    {
        boolean canOpen = false;
        
        if ( which == READER )
        {
            if ( file.exists() )
            {
                canOpen = true;
                reader = new BufferedReader( new FileReader( file ) );
            
            } // end if
            
        } // end if
        else
        {
            writer = new PrintWriter( new FileWriter( file ) );
               
            if ( file.canWrite() )
                    canOpen = true;
                
        } // end else
        
        return canOpen;

    } // method open( int )
    
    
    /**
     * Read a line from the file
     * 
     * @return a line from the file
     * @throws IOException 
     */
    public String readLine() throws IOException
    {
        String line = null;
        
        if ( reader != null )
            line = reader.readLine();
        
        return line;

    } // method readLine()
 
    
    /**
     * Write a line to the file
     * 
     * @param line - the line to write
     */
    public void write( String line )
    {
        if ( Utilities.isValidWord( line ) )
            if ( writer != null )
                writer.println( line );
        
    } // method write( String )

} // class BoggleFileIO
