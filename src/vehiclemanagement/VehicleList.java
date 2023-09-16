/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vehiclemanagement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import tools.Constants;
import tools.Utilities;

/**
 *
 * @author duyvu
 */
public class VehicleList extends ArrayList<Vehicle> {

    // Reading Scanner from Keyboard
    private static Scanner sc;

    static {
        sc = new Scanner(System.in);
    }

//    Comparator<Vehicle> cNameDesc = new Comparator<Vehicle>() {
//        @Override
//        public int compare(Vehicle o1,
//                           Vehicle o2) {
//            return -o1.name.compareTo(o2.name);
//        }
//    };
    // Default Constructor
    public VehicleList() {
        this.add(new Vehicle("VH1000", "VU KIM DUY", "red", 122.333, "TOKYO", "CAR", 1997));
        this.add(new Vehicle("VH1001", "VU KIM DUY1", "red", 122.333, "TOKYO", "CAR", 1998));
        this.add(new Vehicle("VH1002", "VU KIM DUY2", "red", 122.333, "TOKYO", "CAR", 1999));
        this.add(new Vehicle("VH1003", "VU KIM DUY3", "red", 122.333, "TOKYO", "CAR", 1990));
        this.add(new Vehicle("VH1004", "VU KIM DUY4", "red", 122.333, "TOKYO", "CAR", 1991));
        this.add(new Vehicle("VH1005", "VU KIM DUY5", "red", 122.333, "TOKYO", "CAR", 1992));
        this.add(new Vehicle("VH1006", "VU KIM DUY6", "red", 122.333, "TOKYO", "CAR", 1993));
        this.add(new Vehicle("VH1007", "VU KIM DUY7", "red", 122.333, "TOKYO", "CAR", 1994));
    }

    // TODO: addVehicle
    public void addVehicle() {
        String name, color, brand, type, id;
        double price;
        int productYear;
        boolean isExisted = false;

        // Input id and precheck its existance or not
        do {
            id = Utilities.readString("Enter Vehicle's ID",
                                      new String[]{Constants.INVALID_MSG("Vehicle's Id"),
                                                   Constants.MUST_IN_CONDITIONS_MSG("Vehicle's Id",
                                                                                    "VH******, * is from 0 - 9.", "no special")
                                      },
                                      "^VH\\d{4}$");

            // Checking existance of the Vehicle's ID
            isExisted = checkExist(new Vehicle(id));

            // Output to user as if id is duplicated
            if (isExisted) {
                System.out.println(Constants.DUPLICATED_MSG("Vehicle's ID"));
            }
        } while (isExisted);

        // Input Name
        name = Utilities.readString("Enter Vehicle's Name",
                                    new String[]{"Invalid Vehicle's Name. Please Try Again.",
                                                 "The Vehicle's Name must be less than 30. No special character"}, "\\w{30}");
    }

    /** Check the existance of the Vehicle based on Id
     *
     * @param vehicle: a passed vehicle to the function
     * @return true or false depends on the existance of the vehicle
     */
    public boolean checkExist(Vehicle vehicle) {
        return this.contains(vehicle);
    }

    // TODO: display all vehicles
    public void displayVehicles() {
        for (Vehicle vehicle : this) {
            System.out.println(vehicle.toString());
        }
    }

    // TODO: Update
    // TODO: Remove
    // TODO: search. Denote that do not precheck the String for searching
    // TODO: saves all vehicles to file
    public static void main(String[] args) {
        VehicleList list = new VehicleList();
        list.displayVehicles();
        list.addVehicle();
    }
}
