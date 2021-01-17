import java.util.Scanner;
import java.util.Random;

/**********************************************************************
 * Class purpose: Simulate an 8-ball
 * 
 * @author Alex Gellios
 * @version 10/19/2015 This version has a WebCAT reference test
 *********************************************************************/
public class EightBall {
	/******************************************************************
	 * Method purpose: simulate an 8-ball
	 *
	 * @param args
	 *  Command line arguments, ignored
	 *****************************************************************/
	public static void main(String[] args) {
		Scanner keyboard; // Input Stream

		String wantToAsk; // User input: want to ask a question
		String questionStr; // User input: the question

		int answerIndex; // The index value returned by random generator
		String answerStr; // The program's answer

		int seed; // User input: the random generator seed
		Random answerPicker; // The random generator object

		// 0. Use the command line arguments for the seed value
		if (args != null && args.length >= 1) {
			seed = Integer.parseInt(args[0]);
		} else {
			seed = 123456789;
		}

		// 1. Create a Scanner object (initialize keyboard)
		keyboard = new Scanner(System.in);

		// 2. Output the heading
		System.out.print("Magic 8 Ball\n\n");

		// *****************
		// 3. Get the inputs -- see Output section for prompts
		// *****************
		System.out.print("Do you want to ask a question(yes/no)? ");
		wantToAsk = keyboard.nextLine();

		// *****************
		// 4. Check if the user wants to enter a question or not.
		// *****************
		while (wantToAsk.equals("yes")) {
			
			System.out.print("What is your question?\n");
			questionStr = keyboard.nextLine();

			if (checkQuestion(questionStr) == true) {

				// *****************
				// 5. Input the user's questions, and check the length of the
				// question.
				// See checkQuestion method at bottom of lab.
				// *****************

				// ***************
				// 6. Instantiate the Random generator, using the seed value
				// from above.
				// Pick an answer -- See pickRandom method at bottom of lab.
				// ***************

				answerPicker = new Random();
				answerIndex = pickRandom(answerPicker);

				// ***************
				// 7. Output results
				// ***************
				switch (answerIndex) {
				
				case 1:
					answerStr = "Signs point to yes.";
					break;

				case 2:
					answerStr = "Yes.";
					break;

				case 3:
					answerStr = "Reply hazy, try again.";
					break;

				case 4:
					answerStr = "Without a doubt.";
					break;

				case 5:
					answerStr = "My sources say no.";
					break;
				case 6:
					answerStr = "As I see it, yes.";
					break;

				case 7:
					answerStr = "You may rely on it.";
					break;

				case 8:
					answerStr = "Concentrate and ask again.";
					break;

				case 9:
					answerStr = "Outlook not so good.";
					break;

				case 10:
					answerStr = "It is decidedly so.";
					break;

				case 11:
					answerStr = "Better not tell you now.";
					break;

				case 12:
					answerStr = "Very doubtful.";
					break;

				case 13:
					answerStr = "Yes - definitely.";
					break;

				case 14:
					answerStr = "It is certain.";
					break;

				case 15:
					answerStr = "Cannot predict now.";
					break;

				case 16:
					answerStr = "Most likely.";
					break;

				case 17:
					answerStr = "Ask again later.";
					break;

				case 18:
					answerStr = "My reply is no.";
					break;

				case 19:
					answerStr = "Outlook good.";
					break;

				case 20:
					answerStr = "Don't count on it.";
					break;
					
				default:
					answerStr = "error";

				}

				System.out.printf("Question: %s\n", questionStr);
				System.out.printf(" Answer: %s\n", answerStr);
				System.out.print("Do you want to ask a question(yes/no)? ");
				wantToAsk = keyboard.nextLine();
			}
			
		}
		System.out.print("Goodbye\n");
	}// end main

	/***********************
	 * method checkQuestion will check the question entered by the user and if
	 * it is too long, will display the error message and exit the program.
	 *
	 * @param question
	 *            The question that was asked
	 * @return True if the question is 60 or less, false otherwise
	 ***********************/
	public static boolean checkQuestion(String question) {
		if(question.endsWith("?")){
		
		if (question.length() > 60){
			System.out.print("Your question is too long. Be more concise.\n");
			return false;
			}
		
		if(question.length()< 3){
			System.out.print("Your question is too short. Please try again.\n");
			return false;
			}
		else
			return true;
		}
		System.out.print("You need to ask a question.\n");
		return false;
			} // end checkQuestion
	

	/***********************
	 * method pickRandom will pick a random number from 1 - 20 use the Random
	 * paramter to choose a random number between 0 and 1 then scale that number
	 * to 1 - 20.
	 *
	 * @param randi
	 *            The Random number generator
	 * @return the random number
	 ***********************/
	public static int pickRandom(Random randi) {
		int random;
		random = randi.nextInt(20) + 1;
		return random;

	} // end pickRandom
}
