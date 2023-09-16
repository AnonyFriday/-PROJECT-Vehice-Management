package vehiclemanagement;

import java.util.Scanner;

/** A small program illustrates the vehicle management application
 *
 * @author duyvu
 */
public class VehicleManagement {

    public static void main(String[] args) {

        // ======================================
        // = VARIABLES FOR MENU
        // ======================================
        Scanner sc = new Scanner(System.in);
        int userChoice;

        // Generate the Menu options
        userChoice = Menu.getChoiceInt("Add new Vehicle", "Check exists Vehicle");
        
//        switch (userChoice) {
//            case 1:
//                
//                break;
//            default:
//                throw new AssertionError();
//        }
    }
}
