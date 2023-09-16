/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vehiclemanagement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import tools.Constants;
import tools.Utilities;

/**
 *
 * @author duyvu
 */
public class VehicleList extends ArrayList<Vehicle> {

    // Reading Scanner from Keyboard
    private static final Scanner sc;

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
        this.add(new Vehicle("VH100000", "VU KIM DUY", "red", 122.333, "TOKYO", "CAR", Utilities.parseDateFromString("12-02-2000", "dd-MM-yyyy")));
        this.add(new Vehicle("VH100100", "VU KIM DUY", "red", 122.333, "TOKYO", "CAR", Utilities.parseDateFromString("20-02-2001", "dd-MM-yyyy")));
        this.add(new Vehicle("VH100200", "VU KIM DUY", "red", 122.333, "TOKYO", "CAR", Utilities.parseDateFromString("13-02-2002", "dd-MM-yyyy")));
        this.add(new Vehicle("VH100300", "VU KIM DUY", "red", 122.333, "TOKYO", "CAR", Utilities.parseDateFromString("14-02-2010", "dd-MM-yyyy")));
        this.add(new Vehicle("VH100400", "VU KIM DUY", "red", 122.333, "TOKYO", "CAR", Utilities.parseDateFromString("15-02-1900", "dd-MM-yyyy")));
        this.add(new Vehicle("VH100500", "VU KIM DUY", "red", 122.333, "TOKYO", "CAR", Utilities.parseDateFromString("16-02-2100", "dd-MM-yyyy")));
        this.add(new Vehicle("VH100600", "VU KIM DUY", "red", 122.333, "TOKYO", "CAR", Utilities.parseDateFromString("17-02-2002", "dd-MM-yyyy")));
        this.add(new Vehicle("VH100700", "VU KIM DUY", "red", 122.333, "TOKYO", "CAR", Utilities.parseDateFromString("22-02-2009", "dd-MM-yyyy")));
    }

    /** Add newly created Vehicle object to the list
     * <br>Ask user to continue 
     */
    public void addVehicle() {
        String name, color, brand, type, id = null;
        double price;
        Date productDate;
        boolean isExisted = false;

        // Input id and precheck its existance or not
        do {
            id = Utilities.readString("Enter Vehicle's ID",
                                      new String[]{Constants.INVALID_MSG("Vehicle's Id"),
                                                   Constants.MUST_IN_CONDITIONS_MSG("Vehicle's Id",
                                                                                    "VH****** (* is from 0 - 9)",
                                                                                    "Cannot be null",
                                                                                    "No special characters")
                                      },
                                      "^VH\\d{6}$",
                                      false);

            // Checking existance of the Vehicle's ID
            isExisted = checkExist(new Vehicle(id));

            // Output to user as if id is duplicated
            if (isExisted) {
                System.out.println(Constants.DUPLICATED_MSG("Vehicle's ID"));
            }
        } while (isExisted);

        // Input Name
        name = Utilities.readString("Enter Vehicle's Name",
                                    new String[]{Constants.INVALID_MSG("Vehicle's Name"),
                                                 Constants.MUST_IN_CONDITIONS_MSG("Vehicle's Name",
                                                                                  "Name must be between 8 to 30 characters",
                                                                                  "No special characters",
                                                                                  "Cannot be null",
                                                                                  "Contains only alphabets, numeric characters and space")},
                                    "^[a-zA-Z0-9 ]{8,30}$",
                                    false);

        // Input Type
        type = Utilities.readString("Enter Vehicle's Type",
                                    new String[]{Constants.INVALID_MSG("Vehicle's Type"),
                                                 Constants.MUST_IN_CONDITIONS_MSG("Vehicle's Type",
                                                                                  "No special characters",
                                                                                  "Contains only alphabet characters and space"),
                                                 Constants.DEFAULT_VALUE_MSG("Vehicle's Type", "null")},
                                    "^[a-zA-Z ]+$",
                                    true);
        type = type.isEmpty() ? "NULL" : type;

        // Input Color
        color = Utilities.readString("Enter Vehicle's Color",
                                     new String[]{Constants.INVALID_MSG("Vehicle's Color"),
                                                  Constants.MUST_IN_CONDITIONS_MSG("Vehicle's Color",
                                                                                   "No special characters",
                                                                                   "Contains only alphabet characters and space"),
                                                  Constants.DEFAULT_VALUE_MSG("Vehicle's Color", "null")},
                                     "^[a-zA-Z ]+$",
                                     true);
        color = color.isEmpty() ? "NULL" : color;

        // Input Brand
        brand = Utilities.readString("Enter Vehicle's Brand",
                                     new String[]{Constants.INVALID_MSG("Vehicle's Brand"),
                                                  Constants.MUST_IN_CONDITIONS_MSG("Vehicle's Brand",
                                                                                   "No special characters",
                                                                                   "Contains only alphabet characters and space"),
                                                  Constants.DEFAULT_VALUE_MSG("Vehicle's Brand", "null")},
                                     "^[a-zA-Z ]+$",
                                     true);
        brand = brand.isEmpty() ? "NULL" : brand;

        // Input price
        price = Double.parseDouble(Utilities.readString("Enter Vehicle's Price",
                                                        new String[]{Constants.INVALID_MSG("Vehicle's Price"),
                                                                     Constants.MUST_IN_CONDITIONS_MSG("Vehicle's Price",
                                                                                                      "Price must be greater than or equal to 0",
                                                                                                      "No special characters",
                                                                                                      "Have to be numeric type")},
                                                        "^\\d+.?\\d*$",
                                                        false));

        // Input year
        productDate = Utilities.readDate("Enter Vehicle's Date",
                                         new String[]{Constants.INVALID_MSG("Vehicle's Price"),
                                                      Constants.MUST_IN_CONDITIONS_MSG("Vehicle's Date",
                                                                                       "Date must be in format dd-MM-yyyy",
                                                                                       "No special characters")},
                                         "dd-MM-yyyy");

        // Adding new vehicle to the list
        Vehicle newVehicle = new Vehicle(id, name, color, price, brand, type, productDate);
        this.add(newVehicle);

        // Ask user to continue adding to the list
    }

    /** Check the existance of the Vehicle based on Id
     *
     * @param vehicle: a passed vehicle to the function
     * @return true or false depends on the existance of the vehicle
     */
    public boolean checkExist(Vehicle vehicle) {
        return this.contains(vehicle);
    }

    /** Display Vehicles from the list
     *
     */
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
