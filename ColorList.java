package project2;

import java.util.ArrayList;

/**
 * This class represents an arraylist that stores all the Color objects 
 * whose hexadecimal value and name are provided in the input file.
 * This class inherits from OrderedLinkedList<Color> class 
 * and contain no data field with the type of OrderedLinkedList<Color>.
 * All Color objects are added or accessed through the inherited methods of OrderedLinkedList. 
 * 
 * @author Gayeon_Park
 *
 */
public class ColorList extends OrderedLinkedList<Color>{

	//Default Constructor
	public ColorList(){

	}

	/**
	 * Passes the name of a hexadecimal value into its parameter.
	 * The name of a hexadecimal value should be case insensitive. 
	 * 
	 * @param colorName name of a potential hexadecimal value to be checked  
	 * 
	 * @return the Color object in the list whose color name 
	 *  matches the colorName specified by the parameter. 
	 *  If the method is called with a non-existent color name, 
	 *  returns null.
	 */
	public Color getColorByName (String colorName){
		for (int i = 0; i < size(); i ++){
			Color retrievedColor = get(i);
			if (retrievedColor != null && retrievedColor.getName().equalsIgnoreCase(colorName)){
					return retrievedColor;
			}
		}
		return null;
	}

	/**
	 * Passes a hexadecimal value into its parameter.
	 * The name of a hexadecimal value should be case insensitive.
	 * 
	 * @param colorHexValue a potential hexadecimal value to be checked
	 * 
	 * @return the Color object in the list whose hexadecimal value 
	 * matches the colorHexValue specified by the parameter.  
	 * If the method is called with a non-existent 
	 * hexadeciamal value, returns null.
	 */
	public Color getColorByHexValue (String colorHexValue){
		for (int i = 0; i < size(); i ++){
			Color retrievedColor = get(i);
			if (retrievedColor != null && retrievedColor.getHexValue().equalsIgnoreCase(colorHexValue)){
					return retrievedColor;
			}
		}
		return null;
	}
}

