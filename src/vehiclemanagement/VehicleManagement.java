package vehiclemanagement;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.Constants;
import tools.Utilities;

/**
 * A small program illustrates the vehicle management application
 *
 * @author duyvu
 */
public class VehicleManagement {

    public static void main(String[] args) {

	VehicleList list = new VehicleList();

	// A variable determince the user's choice and program's execution
	int userChoice;
	boolean isExitedProgram = false;

	// Load file at first for the application
	list.loadVehiclesFromFile(Constants.FILENAME);

	do {
	    // Generate the Menu options
	    userChoice = Menu.getChoiceInt(
		    "Add new Vehicle",
		    "Check Vehicle's existance",
		    "Update Vehicle",
		    "Delete Vehicle",
		    "Search Vehicle",
		    "Display all Vehicles",
		    "Save all Vehicles to a file");

	    switch (userChoice) {
		// Adding Vehicle
		case 1: {
		    list.addVehicle();

		    // Ask user to continue adding to the list
		    Menu.continueOption(() -> list.addVehicle(),
			    "Do you want to continue ADDING new vehicle (Y/N)");
		    break;
		}

		// Check Vehicle's existance on ID
		case 2: {
		    list.checkVehicleExistOnId();
		    break;
		}

		// Update Vehicle on ID
		case 3: {
		    list.updateVehicleOnId();
		    break;
		}

		// Remove Vehicle on ID
		case 4: {
		    list.removeVehicleOnId();
		    break;
		}
		// Search Vehicle on Id, Name, Min-Max Date
		case 5: {
		    list.searchVehicles();

		    // Ask user to continue searching to the list
		    Menu.continueOption(() -> list.searchVehicles(),
			    "Do you want to continue SEARCHING vehicles (Y/N)");
		    break;
		}
		// Display Vehicles
		case 6: {
		    list.displayVehicles();

		    // Ask user to continue displaying to the list
		    Menu.continueOption(() -> list.displayVehicles(),
			    "Do you want to continue DISPLAYING vehicles (Y/N)");
		    break;
		}
		case 7: {
		    list.saveVehiclesFromFile(Constants.FILENAME);
		    break;
		}
		default: {
		    // Asking the user to continue the program
		    isExitedProgram = Utilities.readBoolean("Are you sure to EXIT the program (Y/N)?",
			    new String[]{Constants.INVALID_MSG("Exit the program"),
				Constants.MUST_IN_CONDITIONS_MSG("Exit the program's value",
					"Accept only (Y,1,true/N,0,false) value")});
		    break;
		}
	    }
	} while (!isExitedProgram);
    }
}
