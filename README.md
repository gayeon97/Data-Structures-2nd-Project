# Data-Structures-2nd-Project
2nd Java Project for Data Structures course

This project provides an alternative implementaiton of the program implemented for project 1. The major difference
in the program is the data structure used for the ColorList class. In this project, a sorted linked list is implemented and used instead of the ArrayList<E> class in the first project.

This project still works with an input file containing the listing of named CSS colors and their hexadecimal values. Each line in the input file will contain the name of the color, followed by a comma, followed by one or more spaces, followed by a hexadecimal color value.

When the user runs the program, he/she will provide the name of the input file containing the list of the named CSS colors as a command line argument. The user may start the program from the command line or run it within an IDE like Eclipse - from the point of view of your program this does not matter. If the name of the input file provided as a command line argument is incorrect or the file cannot be opened for any reason, the program should display an error message and terminate.

The program should allow the user to enter a hexadecimal color value. It should produce the corresponding RGB value and the color name (if such exists).

The program should run in a loop allowing the user to check as many hexadecimal values as they wish. On each iteration, the user should be prompted to enter either a name (for which the program computes the results) or the word ”quit” (any case of letters should work) to indicate the termination of the program.

If the user enters a value that is incorrect (i.e., not a valid hexadecimal value and not the ”quit” keyword), an error message should be printed (stating that the entered string is not a valid hexadecimal color value) and the program should allow the user to enter another value.

If the user enters a valid hexadecimal value, that value should be displayed together with the corresponding RGB values and the CSS name (if such exists).

If the user enters a single word ”quit” instead of the color value, the program should terminate.
