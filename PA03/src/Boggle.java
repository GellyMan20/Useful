
import java.io.*;

import controller.BoggleControl;

/**
 * Boggle - starts the Boggle game
 *
 * @author Michael Norton
 * @version PA01 (23 September 2016)
 */
public class Boggle
{
	/***********************************************************************
	 * main
	 **********************************************************************/
	public static void main(String[] args) throws IOException
	{
		BoggleControl controller = new BoggleControl();
		controller.run();
	}
}