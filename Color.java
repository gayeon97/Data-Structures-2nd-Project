package project2;

/**
 * This class stores information about a particular Color that can be given as:
 * a). hexadecimal, b). hexadecimal and color name corresponding to the hexadecimal
 * and c). RGB values. 
 * This class provides all the variables and methods of Color class.  
 * 
 * @author Gayeon Park
 */
public class Color implements Comparable<Color> {

	private String colorHexValue;
	private String colorName;

	/**
	 * This constructor creates a hexadecimal value for a CSS color
	 * using the String parameter passed in the format #XXXXXX in 
	 * which the X is replaced by a hexadecimal symbol
	 * (digits 0-9 and characters A-F in either upper or lower case).
	 * 
	 * @param colorHexValue a String representation of colorHexValue
	 * 
	 * @throws IllegalArgumentException if the length of 
	 * the parameter is not equal to 7. Also throws exception
	 * if the parameter does not start with "#". Even when the
	 * top two conditions are satisfied, if the parameter is formatted 
	 * differently or contains different characters, the parameter
	 * is considered invalid.
	 */
	public Color (String colorHexValue) throws IllegalArgumentException{
		//The string parameter has to be 7 in length or otherwise error
		if (colorHexValue.length() != 7){
			throw new IllegalArgumentException("Error: This is not a valid color specification.");
		}

		//1st index in the string parameter has to be # or otherwise error
		if (!colorHexValue.substring(0,1).equals("#")){
			throw new IllegalArgumentException("Error: This is not a valid color specification.");
		}	

		//Checks if every character in colorHexvalue parameter is 
		//contained in the string of all valid characters possible for colorHexValue
		String validChars = "ABCDEFabcdef0123456789";
		for (int i = 1; i < colorHexValue.length(); i ++){
			if (!validChars.contains(colorHexValue.substring(i,i+1))){
				throw new IllegalArgumentException("Error: This is not a valid color specification.");
			}
		}

		//Every character in parameter colorHexvalue is tested to be valid
		//and colorHexValue is set to the value of the instance variable this.colorHexValue
		this.colorHexValue = colorHexValue;
	}

	/**
	 * This constructor creates a hexadecimal value for a CSS color
	 * using the String parameter passed in the format #XXXXXX in 
	 * which the X is replaced by a hexadecimal symbol
	 * (digits 0-9 and characters A-F in either upper or lower case).
	 * The second string parameter representing colorName 
	 * can be anything from a null to an empty string.
	 * 
	 * @param colorHexValue a String representation of colorHexValue
	 * @param colorName the name of the colorHexValue 
	 * 
	 * @throws IllegalArgumentException if the length of 
	 * the first parameter is not equal to 7. Also throws exception
	 * if the first parameter does not start with "#". Even when the
	 * top two conditions are satisfied, if the first parameter is
	 * formatted differently or contains different characters, it
	 * is considered invalid.  
	 */
	public Color (String colorHexValue, String colorName) throws IllegalArgumentException{
		//The string parameter has to be 7 in length or otherwise error
		if (colorHexValue.length() != 7){
			throw new IllegalArgumentException("Error: This is not a valid color specification.");
		}

		//1st index in the string parameter has to be # or otherwise error
		if (!colorHexValue.substring(0,1).equals("#")){
			throw new IllegalArgumentException("Error: This is not a valid color specification.");
		}	

		//Checks if every character in colorHexvalue parameter is 
		//contained in the string of all valid characters possible for colorHexValue
		String validChars = "ABCDEFabcdef0123456789";
		for (int i = 1; i < colorHexValue.length(); i ++){
			if (!validChars.contains(colorHexValue.substring(i,i+1))){
				throw new IllegalArgumentException("Error: This is not a valid color specification.");
			}
		}

		//Every character in parameter colorHexvalue is tested to be valid
		//and colorHexValue is set to the value of the instance variable this.colorHexValue
		this.colorHexValue = colorHexValue;
		//Parameter colorHexvalue proved to be a valid hexavalue of a color 
		//so this.colorName can be set to the parameter colorName passed in
		this.colorName = colorName;
	}

	/**
	 * This constructor creates a hexadecimal value for a CSS color 
	 * using RGB values (RRGGBB). Integer values for red, green and 
	 * blue have to be in the range from 0 to 255 (inclusive).
	 * 
	 * @param red the int value of red component of the RGB value
	 * @param green the int value of green component of the RGB value
	 * @param blue the int value of blue component of the RGB value
	 * 
	 * @throws IllegalArgumentException if the three parameters are
	 * out of range or contains different characters.
	 */
	public Color (int red, int green, int blue) throws IllegalArgumentException{
		//if the int parameteres for red, green and blue values
		//are in the correct range from 0-255, 
		//take those parameters and compute the hexadecimal value
		//if any of the parameters are out of range or is formatted differently,
		//throw IllegalArgumentException
		if ( (0 <= red && red <= 255) && (0 <= green && green <= 255) && (0 <= blue && blue <= 255) ){
			this.colorHexValue = String.format("#%02x%02x%02x", red,green,blue);
		} else {
			throw new IllegalArgumentException("Error: This is not a valid color specification.");
		}
	}

	/** 
	 * Returns the value of the red component of the RGB value.
	 * 
	 * @return the int value of red component of the RGB value
	 */
	public int getRed(){
		return Integer.parseInt(this.colorHexValue.substring(1,3),16);
	}

	/**
	 * Returns the value of the green component of the RGB value.
	 * 
	 * @return the int value of green component of the RGB value
	 */
	public int getGreen(){
		return Integer.parseInt(this.colorHexValue.substring(3,5),16);
	}

	/**
	 * Returns the value of the blue component of the RGB value. 
	 * 
	 * @return the int value of blue component of the RGB value
	 */
	public int getBlue(){
		return Integer.parseInt(this.colorHexValue.substring(5,7),16);
	}

	/**
	 * Returns the name of the color of this Color object. 
	 * 
	 * If there is no name associated with this Color object, the method returns null.  
	 * @return the name of the color or null if there is no name 
	 * associated with this Color object
	 */
	public String getName(){
		return colorName;
	}

	/**
	 * Returns the hexadecimal representation of this Color object in the format #XXXXXX 
	 * in which the X is replaced by digits 0-9 and letters A-F in all upper case.
	 * 
	 * @return string of colorHexValue in the format #XXXXXX with all letters
	 * used in hexadecimal expression as upper case
	 */
	public String getHexValue(){			
		return colorHexValue.toUpperCase();
	}

	/**
	 * Overrides the equals method of the equals method of Object class.
	 * The method checks if two Color objects are equal by comparing
	 * the hexadecimal strings representing their values, ignoring the case
	 * of the letters in the strings. 
	 * The color name is not be considered in the comparison for equality.
	 * Checks for following cases and return appropriate boolean value:
	 * a). if this Color Object is checking with itself,
	 * b). if the object passed in as a parameter is null and
	 * c). if the object passed in as parameter is a Color object. 
	 * 
	 * @param Object obj
	 * 
	 * @return true if the hexadecimal strings of two Color objects
	 * are equal ignoring case
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Color))
			return false;
		Color other = (Color) obj;
		if (this.colorHexValue == null) {
			if (other.colorHexValue != null)
				return false;
			else return false;
		} 
		return this.getHexValue().equalsIgnoreCase(other.getHexValue());
	}

	/**
	 * Implements the compareTo method of Comparable<Color> interface.
	 * In this method, the comparison is done by the alphanumeric comparison of
	 * the hexadecimal value associated with the color, ignoring the 
	 * case of letteres used in hexedcimal expressions.
	 * (The digits are sorted before letters in alphanumeric ordering).
	 * 
	 * @param otherColorObject 
	 * 
	 * @return a negative integer, zero, or a positive integer 
	 * if this object is less than, equal to, or greater than 
	 * the specified object, respectively
	 */
	public int compareTo(Color otherColorObject) {
		return this.colorHexValue.compareToIgnoreCase(otherColorObject.colorHexValue);
	}

	/**
	 * Override the toString method of String class.
	 * The return string is formatted in three parts.
	 * For first part #XXXXXX, the uppercase letters 
	 * are placeholders for six hexadecimal symbols,
	 * and letters should be printed in uppercase.
	 * 
	 * For second part (RRR,GGG,BBB):
	 *		RRR are three digits representing the red component. 
	 * 		GGG are three digits representing the green component. 
	 * 		BBB three digits representing the blue component.
	 * If the number has fewer than three digits, 
	 * the missing leading digits should be replaced by spaces.
	 * 
	 * For third part, NAME is a (possibly empty) string 
	 * representing the color name.
	 * 
	 * @ return a string according to the specified format:
	 * #XXXXXX, (RRR, GGG, BBB), NAME or
	 * #XXXXXX, (RRR, GGG, BBB) if the Color object does not have a name.
	 */
	public String toString(){		
		String numComponent = String.format("(%1$3s,%2$3s,%3$3s)",getRed(),getGreen(),getBlue());

		if (this.colorName == null){
			return 	String.format("%1$s, %2$s,",getHexValue(), numComponent);
		}
		else
			return String.format("%1$1s, %2$1s, %3$s",getHexValue(), numComponent, getName());
	}

}

