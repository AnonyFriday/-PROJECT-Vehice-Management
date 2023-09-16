package vehiclemanagement;

import java.util.Scanner;
import tools.Constants;
import tools.Utilities;

/** A small program illustrates the vehicle management application
 *
 * @author duyvu
 */
public class VehicleManagement {

    public static void main(String[] args) {

        VehicleList list = new VehicleList();

        // A variable determince the user's choice and program's execution
        int userChoice;
        boolean isContinueProgram = false;

        // A switching structure program conducted by the user's option
        do {

            // Generate the Menu options
            userChoice = Menu.getChoiceInt("Add new Vehicle",
                                           "Check exists Vehicle",
                                           "Update Vehicle",
                                           "Delete Vehicle",
                                           "Search Vehicle",
                                           "Display all Vehicles",
                                           "Save all Vehicles to a file",
                                           "Load all Vehicles from a file");

            switch (userChoice) {
                case 1: {
                    // Execute the add vehicle
                    list.addVehicle();

                    // Ask user to continue adding to the list
                    Menu.continueOption(() -> list.addVehicle(),
                                        "Do you want to continue adding new vehicle (Y/N).");
                    break;
                }
                case 2: {
                    // Check if the id is on the list or not
                    list.checkExistOnId();
                    break;
                }
                case 3: {
                    break;
                }
                case 4: {
                    break;
                }
                case 5: {
                    break;
                }
                case 6: {
                    break;
                }
                case 7: {
                    break;
                }
                case 8: {
                    break;
                }
                default: {
                    break;
                }
            }
            isContinueProgram = Utilities.readBoolean("Do you want to continue the program (Y/N)?",
                                                      new String[]{Constants.INVALID_MSG("Continue program"),
                                                                   Constants.MUST_IN_CONDITIONS_MSG("Continue program",
                                                                                                    "Accept only (Y,1,true/N,0,false) value.")});
        } while (isContinueProgram);
    }
}
