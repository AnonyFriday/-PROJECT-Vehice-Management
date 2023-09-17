/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vehiclemanagement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

    // ==================================
    // == ADD GROUP 
    // ==================================
    /**
     * Add newly created Vehicle object to the list
     * <br>Ask user to continue
     */
    public void addVehicle() {
	String name, color, brand, type, id, priceStr;
	Date productDate;
	int foundVehicleIndex = -1;

	// Input id and precheck its existance or not
	// - Does not allow null value
	// - If user enter the id, then precheck it through the readString function for its existence
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
	    foundVehicleIndex = checkVehiclekExist(new Vehicle(id));

	    // Output to user as if id is duplicated
	    if (foundVehicleIndex != -1) {
		System.out.println(Constants.DUPLICATED_MSG("Vehicle's ID"));
	    }
	} while (foundVehicleIndex != -1);

	// Input Name
	// - Does not allow null value since making no sense if storing unknown vehicle
	// - If user enter the name, then precheck it through the readString function
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
	// - If user enter nothing, than assign NULL as the default character
	// - If user enter the type, then precheck it through the readString function
	type = Utilities.readString("Enter Vehicle's Type (Default is NULL)",
		new String[]{Constants.INVALID_MSG("Vehicle's Type"),
		    Constants.MUST_IN_CONDITIONS_MSG("Vehicle's Type",
			    "No special characters",
			    "Contains only alphabet characters and space"),
		    Constants.DEFAULT_VALUE_MSG("Vehicle's Type", "NULL")},
		"^[a-zA-Z ]+$",
		true);
	type = type.isEmpty() ? "NULL" : type;

	// Input Color
	// - If user enter nothing, than assign NULL as the default character
	// - If user enter the color, then precheck it through the readString function
	color = Utilities.readString("Enter Vehicle's Color (Default is NULL)",
		new String[]{Constants.INVALID_MSG("Vehicle's Color"),
		    Constants.MUST_IN_CONDITIONS_MSG("Vehicle's Color",
			    "No special characters",
			    "Contains only alphabet characters and space"),
		    Constants.DEFAULT_VALUE_MSG("Vehicle's Color", "NULL")},
		"^[a-zA-Z ]+$",
		true);
	color = color.isEmpty() ? "NULL" : color;

	// Input Brand
	// - If user enter nothing, than assign NULL as the default character
	// - If user enter the brand, then precheck it through the readString function
	brand = Utilities.readString("Enter Vehicle's Brand (Default is NULL)",
		new String[]{Constants.INVALID_MSG("Vehicle's Brand"),
		    Constants.MUST_IN_CONDITIONS_MSG("Vehicle's Brand",
			    "No special characters",
			    "Contains only alphabet characters and space"),
		    Constants.DEFAULT_VALUE_MSG("Vehicle's Brand", "NULL")},
		"^[a-zA-Z ]+$",
		true);
	brand = brand.isEmpty() ? "NULL" : brand;

	// Input price
	// - If user enter nothing, than assign 0 as the default character
	// - If user enter the price, then precheck it through the readString function
	priceStr = Utilities.readString("Enter Vehicle's Price (Default is 0)",
		new String[]{Constants.INVALID_MSG("Vehicle's Price"),
		    Constants.MUST_IN_CONDITIONS_MSG("Vehicle's Price",
			    "Decimal Seperator must be '.' (e.g. 123.123)",
			    "Price must be greater than or equal to 0",
			    "No special characters",
			    "Have to be numeric type"),
		    Constants.DEFAULT_VALUE_MSG("Vehicle's Price", "0.0")},
		"^\\d+\\.?\\d*$",
		true);
	priceStr = priceStr.isEmpty() ? "0.0" : priceStr;

	// Input date
	// - If user enter nothing, than assign 01-01-1970 as the default value
	// - If user enter the date, then precheck it through the readDate function
	productDate = Utilities.readDate("Enter Vehicle's Date (Default is 01-01-1970)",
		new String[]{Constants.INVALID_MSG("Vehicle's Price"),
		    Constants.MUST_IN_CONDITIONS_MSG("Vehicle's Date",
			    "Date must be in format dd-MM-yyyy",
			    "No special characters"),
		    Constants.DEFAULT_VALUE_MSG("Vehicle's Date", "01-01-1970")},
		"dd-MM-yyyy",
		true);

	if (productDate == null) {
	    // If user do not enter anything, then return the default date at 01-01-1970
	    productDate = Utilities.parseDateFromString("01-01-1970", Constants.DATE_FORMAT);
	}

	// Adding new vehicle to the list
	Vehicle newVehicle = new Vehicle(id, name, color, Double.parseDouble(priceStr), brand, type, productDate);

	// Print out message if add failed
	if (this.add(newVehicle)) {
	    System.out.println("Added successfully. New vehicle: " + newVehicle.toString());
	} else {
	    System.out.println("Added failed. Please try again.");
	}
    }

    // ==================================
    // == UPDATE GROUP 
    // ==================================
    public void updateVehicle() {
	// Find ID and extract the Vehicle on that ID
	int foundVehicleIndex = checkVehicleExistOnId();
	Vehicle vehicle = null;
	String name, color, brand, type, id, priceStr;
	Date productDate;

	// Get the founded Vehicle and update the information
	if (foundVehicleIndex != -1) {

	    vehicle = this.get(foundVehicleIndex);

	    // Modify Name
	    // - If user enter nothing, than preserve the old value
	    // - If user enter the name, then precheck it through the readString function
	    name = Utilities.readString("Update Vehicle's Name (Enter to unchange)",
		    new String[]{Constants.INVALID_MSG("Vehicle's Name"),
			Constants.MUST_IN_CONDITIONS_MSG("Vehicle's Name",
				"Name must be between 8 to 30 characters",
				"No special characters",
				"Cannot be null",
				"Contains only alphabets, numeric characters and space")},
		    "^[a-zA-Z0-9 ]{8,30}$",
		    true);
	    if (!name.isEmpty()) {
		vehicle.setName(name);
	    }

	    // Modify Type
	    // - If user enter nothing, than preserve the old value
	    // - If user enter the type, then precheck it through the readString function
	    type = Utilities.readString("Update Vehicle's Type (Enter to unchange)",
		    new String[]{Constants.INVALID_MSG("Vehicle's Type"),
			Constants.MUST_IN_CONDITIONS_MSG("Vehicle's Type",
				"No special characters",
				"Contains only alphabet characters and space"),
			Constants.DEFAULT_VALUE_MSG("Vehicle's Type", "null")},
		    "^[a-zA-Z ]+$",
		    true);
	    if (!type.isEmpty()) {
		vehicle.setType(type);
	    }

	    // Modify Color
	    // - If user enter nothing, than preserve the old value
	    // - If user enter the color, then precheck it through the readString function
	    color = Utilities.readString("Update Vehicle's Color (Enter to unchange)",
		    new String[]{Constants.INVALID_MSG("Vehicle's Color"),
			Constants.MUST_IN_CONDITIONS_MSG("Vehicle's Color",
				"No special characters",
				"Contains only alphabet characters and space"),
			Constants.DEFAULT_VALUE_MSG("Vehicle's Color", "null")},
		    "^[a-zA-Z ]+$",
		    true);
	    if (!color.isEmpty()) {
		vehicle.setColor(color);
	    }

	    // Modify Brand
	    // - If user enter nothing, than preserve the old value
	    // - If user enter the brand, then precheck it through the readString function
	    brand = Utilities.readString("Update Vehicle's Brand (Enter to unchange)",
		    new String[]{Constants.INVALID_MSG("Vehicle's Brand"),
			Constants.MUST_IN_CONDITIONS_MSG("Vehicle's Brand",
				"No special characters",
				"Contains only alphabet characters and space"),
			Constants.DEFAULT_VALUE_MSG("Vehicle's Brand", "null")},
		    "^[a-zA-Z ]+$",
		    true);
	    if (!brand.isEmpty()) {
		vehicle.setBrand(brand);
	    }

	    // Modify price
	    // - If user enter nothing, than preserve the old value
	    // - If user enter the price, then precheck it through the readString function
	    priceStr = Utilities.readString("Update Vehicle's Price (Enter to unchange)",
		    new String[]{Constants.INVALID_MSG("Vehicle's Price"),
			Constants.MUST_IN_CONDITIONS_MSG("Vehicle's Price",
				"Decimal Seperator must be '.' (e.g. 123.123)",
				"Price must be greater than or equal to 0",
				"No special characters",
				"Have to be numeric type")},
		    "^\\d+\\.?\\d*$",
		    true);
	    if (!priceStr.isEmpty()) {
		vehicle.setPrice(Double.parseDouble(priceStr));
	    }

	    // Modify year
	    // - If user enter nothing, than preserve the old value
	    // - If user enter the date, then precheck it through the readDate function
	    productDate = Utilities.readDate("Update Vehicle's Date (Enter to unchange)",
		    new String[]{Constants.INVALID_MSG("Vehicle's Price"),
			Constants.MUST_IN_CONDITIONS_MSG("Vehicle's Date",
				"Date must be in format dd-MM-yyyy",
				"No special characters"),
			Constants.DEFAULT_VALUE_MSG("Vehicle's Date", "01-01-1970")},
		    "dd-MM-yyyy",
		    true);

	    if (!(productDate == null)) {
		// If user do not enter anything, then return the default date at 01-01-1970
		vehicle.setProductDate(productDate);
	    }

	    // Printout the Vehicle after updating successfully
	    System.out.println("Updating Successfully. New updated value: " + vehicle.toString());
	}
    }

    // ==================================
    // == CHECK EXISTANCE GROUP 
    // ==================================
    /**
     * Check the existance of the Vehicle based on ID
     *
     * @param vehicle: a passed vehicle to the function
     * @return -1 or the index of the founded Vehicle
     */
    private int checkVehiclekExist(Vehicle vehicle) {
	return this.indexOf(vehicle);
    }

    /**
     * Check to see if the Vehicle'ID exist in the list or not
     *
     * @return -1 or the index of the founded Vehicle
     */
    public int checkVehicleExistOnId() {
	String id = null;

	// Input id and precheck its pattern
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
	} while (id.isEmpty());

	// Check if the passed id is on the list or not
	Vehicle vehicle = new Vehicle(id);
	int foundVehicleIndex = checkVehiclekExist(vehicle);
	if (foundVehicleIndex != -1) {
	    System.out.println("The vehicle " + id + " founded at index " + foundVehicleIndex);
	    System.out.println("Vehicle: " + this.get(foundVehicleIndex).toString());
	} else {
	    System.out.println("Not found " + id);
	}

	// Return Vehicle's ID if found
	return foundVehicleIndex;
    }

    // ==================================
    // == DISPLAY GROUP 
    // ==================================
    /**
     * Display Vehicles from the list by 2 options
     *
     * <br> - Display all vehicles general
     * <br> - Display all vehicles by year
     * <br> - Display all vehicles with min year
     * <br> - Display all vehicles with max year
     */
    public void displayVehicles() {

	// Get option of displaying vehicles
	byte option = (byte) Menu.getChoiceInt("Display all Vehicles", "Display all Vehicles on Year");

	switch (option) {

	    // Display Vehicles without condition
	    case 1: {
		displayVehiclesAll();
		break;
	    }

	    // Display Vehicles by year
	    case 2: {
		int year = Integer.parseInt(Utilities.readString("Enter the year",
			new String[]{"Year accepts only numeric value (e.g. 1999)"},
			"^\\d{4}$",
			false));

		displayVechiclesAll(year);
		break;
	    }
	    default: {
		break;
	    }
	}
    }

    /**
     * Display all Vehicles without any condition
     */
    private void displayVehiclesAll() {
	for (Vehicle vehicle : this) {
	    System.out.println(vehicle.toString());
	}
    }

    /**
     * Display all Vehicles by year
     *
     * @param year: matching year
     */
    private void displayVechiclesAll(int year) {
	boolean isFoundByYear = false;
	for (Vehicle vehicle : this) {

	    // Extract the year from Vehicle's product date
	    int productYear = Utilities.getDatePart(vehicle.getProductDate(), Calendar.YEAR);

	    // Print all matched vehicles
	    if (productYear == year) {
		isFoundByYear = true;
		System.out.println(vehicle.toString());
	    }
	}

	// If not found, output the message
	if (!isFoundByYear) {
	    System.out.println(Constants.EMPTY_VALUE_MSG);
	}
    }

    // ==================================
    // == REMOVE GROUP
    // ==================================
    public void removeVehicle() {
	// Find ID and extract the Vehicle on that ID
	int foundVehicleIndex = checkVehicleExistOnId();

	// If not found than return nothing
	if (foundVehicleIndex == -1) {
	    return;
	}

	// Preprompting againt for deletion
	String[] invalidBooleanMsg = new String[]{
	    Constants.MUST_IN_CONDITIONS_MSG("Continue",
	    "Only accept boolean value (1, 0, f, t, true, false)")};

	boolean isConfirmToDelete = Utilities.readBoolean("Are you sure to delete this vehicle? (Y/N)", invalidBooleanMsg);

	// Delete the vehicle
	if (foundVehicleIndex != -1 && isConfirmToDelete) {
	    this.remove(foundVehicleIndex);
	    System.out.println("Confirmed to delete Successfully.");
	} else {
	    System.out.println("Confirmed not to delete.");
	}
    }

    // TODO: search. Denote that do not precheck the String for searching
    // TODO: saves all vehicles to file
}
