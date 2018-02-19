package project2;

import java.util.*;

import project2.Color;
import project2.ColorList;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * This is the main class for testing the Color, ColorList, and OrderedLinkedList classes
 * and OrderedList interface created.  
 * 
 * @author Gayeon Park
 */
public class ColorConverter {

	public static void main(String[] args) throws FileNotFoundException {	
		//Checks if there is a file passed in as a command line
		if (args.length < 1){
			System.err.println("Usage Error: the program expects file name as an argument.");
			System.exit(0);
		}

		File cssColorList = new File(args[0]);
		//Check if file exists
		if (!cssColorList.exists()){
			System.err.printf("ERROR: the file %s. doesn't exist.\n", args[0]);
		}
		//Check if file can be read	
		if (!cssColorList.canRead()) {
			System.err.printf("ERROR: cannot read file %s.\n\n", args[0]);
			System.exit(1);
		}
		//open and read the data file using the Scanner object
		Scanner inColors = new Scanner(cssColorList);

		//create the OrderedLinkedList of Color objects
		//OrderedLinkedList<Color> listOfColors = new OrderedLinkedList<Color>();
		ColorList listOfColors = new ColorList();
		while ( inColors.hasNext() ) {
			//the input file contains the data one per line
			//and the name of the color and its hexvalue are separated by "," and
			//arbitrary number of white space characters 

			//read the next line from file
			String line = inColors.nextLine();
			//split the line at ","
			String [] names  = line.split(",");
			//trim the extra white space around the names of
			//the color name and its hexvalue
			names[0] = names[0].trim();
			names[1] = names[1].trim();
			
			//create a new Color class using the above two values 
			//and add it to ColorList listofColors
			listOfColors.add(new Color(names[1],names[0]));		
		}

		//close the input file
		inColors.close();

		//Ask the user to enter a hexadecimal color value.
		System.out.println("Enter the color in HEX format (#RRGGBB) or 'quit' to stop:");
		Scanner input = new Scanner(System.in);
		String userInput = input.next();
		
		//The program runs in a loop asking for user info,
		//printing out the corresponding RGB value and the color name (if such exists) 
		//until the user quits. The program catches any IllegalArgumentException from
		//invalid user input. 
		while (!userInput.equalsIgnoreCase("quit")){
			//Call getColorByHexValue function on the Colorlist of Color objects created above
			//and pass in the hexadecimal color value entered in by user as a parameter.
			//If null is returned, there's no Color object in that ColorList and a new Color'
			//object has to be created with userInput as parameter.
			//If the function returns a Color object in the ColorList with matching hexvalue 
			//as the user input, print the string returned by the toString method on that 
			//existing Color object in the ColorList.
			try{
				Color colorFound = listOfColors.getColorByHexValue(userInput);	
				if (listOfColors.getColorByHexValue(userInput) == null){
						Color colorCreated = new Color(userInput);
						System.out.println(colorCreated.toString());
				} else	System.out.println(listOfColors.getColorByHexValue(userInput).toString()); 
				} catch (IllegalArgumentException e){
					System.out.println(e.getMessage());
			}
			
			//Prompts the user for another input
			System.out.println("Enter the color in HEX format (#RRGGBB) or 'quit' to stop:");
			userInput = input.next();
		}
	}

}