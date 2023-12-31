/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vehiclemanagement;

import java.util.List;
import java.util.Scanner;
import tools.Constants;
import tools.Utilities;

/**
 *
 * @author duyvu
 */
public class Menu {

    /**
     * All of the languages have the ability to let method take dynamic amount of arguments. It considers a 1D-array
     *
     * @param options: supply multiple options for menu's options
     * @return a selection from the user
     */
    public static int getChoiceInt(String... options) {

	// Drawing console top edge
	System.out.println("");
	Constants.DRAWING_TABLE_EDGE_LINE();

	final int L = options.length;
	for (int i = 0; i < L; i++) {
	    System.out.println("\t\t| " + (i + 1) + "-" + options[i]);
	}

	// Drawing console bottom edge
	Constants.DRAWING_TABLE_EDGE_LINE();

	// Precheck the input option as the integer 
	return Integer.parseInt(Utilities.readString("Choose (1.." + L + ")(Other Numbers to exit program)",
		new String[]{Constants.INVALID_MSG("Option")},
		"^\\d+$",
		false));
    }

    /**
     * Get the option by Numeric type from the user
     *
     * @param list: a list of options
     * @return an object chosen by the user
     */
    public static Object getChoiceObject(List list) {
	int choice = 0;
	final int L = list.size();
	for (int i = 0; i < L; i++) {
	    System.out.println((i + 1) + '-' + list.get(i).toString());
	}
	return (choice > 0 && choice <= L) ? list.get(choice - 1) : null;
    }

    /**
     * Continue the method from User's actions
     *
     * @param continueFunction: a function to be executed next
     * @param prompt: a prompt to continue the function
     */
    public static void continueOption(IContinueOption continueFunction,
	    String prompt) {

	// Assign a list of valid error message if user input the wrong type of boolean
	String[] invalidBooleanMsg = new String[]{
	    Constants.MUST_IN_CONDITIONS_MSG("Continue",
	    "Only accept boolean value (1, 0, f, t, true, false)")};

	// If true, then continue the function by applying the Functional Interface
	boolean isContinued = Utilities.readBoolean(prompt, invalidBooleanMsg);
	while (isContinued) {
	    continueFunction.apply();
	    isContinued = Utilities.readBoolean(prompt, invalidBooleanMsg);
	}
    }
}
