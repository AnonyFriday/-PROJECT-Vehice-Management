/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vehiclemanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tools.Constants;
import tools.Utilities;

/**
 *
 * @author duyvu
 */
public class VehicleList extends ArrayList<Vehicle> {

    // Default Constructor
    public VehicleList() {
//	this.add(new Vehicle("VH100400", "KIM ", "red", 122.333, "TOKYO", "CAR", Utilities.parseDateFromString("15-02-1900", "dd-MM-yyyy")));
//	this.add(new Vehicle("VH100000", "VU", "red", 122.333, "TOKYO", "CAR", Utilities.parseDateFromString("12-02-2000", "dd-MM-yyyy")));
//	this.add(new Vehicle("VH100100", "VU", "red", 122.333, "TOKYO", "CAR", Utilities.parseDateFromString("20-02-2001", "dd-MM-yyyy")));
//	this.add(new Vehicle("VH100500", "DUY", "red", 122.333, "TOKYO", "CAR", Utilities.parseDateFromString("16-02-2100", "dd-MM-yyyy")));
//	this.add(new Vehicle("VH100600", " DUY", "red", 122.333, "TOKYO", "CAR", Utilities.parseDateFromString("17-02-2002", "dd-MM-yyyy")));
//	this.add(new Vehicle("VH100700", "VU KIM DUY", "red", 122.333, "TOKYO", "CAR", Utilities.parseDateFromString("22-02-2009", "dd-MM-yyyy")));
//	this.add(new Vehicle("VH100200", "VU", "red", 122.333, "TOKYO", "CAR", Utilities.parseDateFromString("13-02-2002", "dd-MM-yyyy")));
//	this.add(new Vehicle("VH100300", "KIM ", "red", 122.333, "TOKYO", "CAR", Utilities.parseDateFromString("14-02-2010", "dd-MM-yyyy")));
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
	    foundVehicleIndex = checkVehiclekExistOnObject(new Vehicle(id));

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
		new String[]{Constants.INVALID_MSG("Vehicle's Date"),
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
	    Constants.DRAWING_LINE_ONE_MESSAGE("Added successfully. New vehicle: " + newVehicle.toString());
	} else {
	    Constants.DRAWING_LINE_ONE_MESSAGE("Added failed. Please try again.");
	}
    }

    // ==================================
    // == UPDATE GROUP 
    // ==================================
    /**
     * Update Vehicle Info based on Id
     */
    public void updateVehicleOnId() {
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
	    Constants.DRAWING_LINE_ONE_MESSAGE("Updating Successfully. New updated value: " + vehicle.toString());
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
    private int checkVehiclekExistOnObject(Vehicle vehicle) {
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
	int foundVehicleIndex = checkVehiclekExistOnObject(vehicle);
	if (foundVehicleIndex != -1) {
	    Constants.DRAWING_LINE_ONE_MESSAGE("The vehicle " + id + " founded at index " + foundVehicleIndex);
	    Constants.DRAWING_LINE_ONE_MESSAGE("Vehicle: " + this.get(foundVehicleIndex).toString());
	} else {
	    Constants.DRAWING_LINE_ONE_MESSAGE("Not found " + id);
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
	byte option = (byte) Menu.getChoiceInt("Display all Vehicles", "Display all Vehicles on Year", "Display all Vehicles on Price");

	switch (option) {

	    // Display Vehicles without condition
	    case 1: {
		displayVehiclesNoCondition();
		break;
	    }

	    // Display Vehicles by year
	    case 2: {
		displayVechiclesByYear();
		break;
	    }
	    default: {
		displayVehiclesByPrice();
		break;
	    }
	}
    }

    /**
     * Display all Vehicles without any condition
     */
    private void displayVehiclesNoCondition() {
	// If list is empty, then output a notification to console
	if (this.isEmpty()) {
	    Constants.DRAWING_LINE_ONE_MESSAGE(Constants.EMPTY_VALUE_MSG);
	}

	for (Vehicle vehicle : this) {
	    Constants.DRAWING_LINE_ONE_MESSAGE(vehicle.toString());
	}
    }

    /**
     * Display all Vehicles by year greater than inputted year
     *
     * @param year: matching year
     */
    private void displayVechiclesByYear() {
	// Enter the production year of the vehicle
	int year = Integer.parseInt(Utilities.readString("Enter the year",
		new String[]{"Year accepts only numeric value (e.g. 1999)"},
		"^\\d{4}$",
		false));

	// Iterating and find vehicles machtching names
	List<Vehicle> matches = new ArrayList<>();
	for (Vehicle vehicle : this) {

	    // Extract the year from Vehicle's product date
	    int productYear = Utilities.getDatePart(vehicle.getProductDate(), Calendar.YEAR);

	    // Add matched vehicles
	    if (productYear >= year) {
		matches.add(vehicle);
	    }
	}

	// Sorting matched vehicles field decensdingly by year
	Collections.sort(matches, (Vehicle o1, Vehicle o2) -> {
	    int o1_year = Utilities.getDatePart(o1.getProductDate(), Calendar.YEAR);
	    int o2_year = Utilities.getDatePart(o2.getProductDate(), Calendar.YEAR);
	    return o2_year - o1_year;
	});

	// Print out the matches
	for (Vehicle vehicle : matches) {
	    Constants.DRAWING_LINE_ONE_MESSAGE(vehicle.toString());
	}

	// If not found, output the message
	if (matches.isEmpty()) {
	    Constants.DRAWING_LINE_ONE_MESSAGE(Constants.EMPTY_VALUE_MSG);
	}
    }

    /**
     * Display all Vehicles by price less than inputted price
     *
     */
    private void displayVehiclesByPrice() {
	// Enter the production year of the vehicle
	double price = Double.parseDouble(Utilities.readString("Enter the price to search",
		new String[]{"Only accept numeric value"},
		"^\\d+\\.?\\d*$",
		false));

	// Iterating and find vehicles machtching names
	List<Vehicle> matches = new ArrayList<>();
	for (Vehicle vehicle : this) {

	    // Add matched vehicles
	    if (vehicle.getPrice() <= price) {
		matches.add(vehicle);
	    }
	}

	// Sorting matched vehicles field decensdingly by year
	Collections.sort(matches, (Vehicle o1, Vehicle o2) -> {
	    double o1_price = o1.getPrice();
	    double o2_price = o2.getPrice();
	    return Double.compare(o2_price, o1_price);
	});

	// Print out the matches
	for (Vehicle vehicle : matches) {
	    Constants.DRAWING_LINE_ONE_MESSAGE(vehicle.toString());
	}

	// If not found, output the message
	if (matches.isEmpty()) {
	    Constants.DRAWING_LINE_ONE_MESSAGE(Constants.EMPTY_VALUE_MSG);
	}
    }

    // ==================================
    // == REMOVE GROUP
    // ==================================
    /**
     * Remove Vehicle from the list based on ID
     */
    public void removeVehicleOnId() {
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
	    Constants.DRAWING_LINE_ONE_MESSAGE("Confirmed to delete Successfully.");
	} else {
	    Constants.DRAWING_LINE_ONE_MESSAGE("Cancel delete a vehicle.");
	}
    }

    // ==================================
    // == SEARCH GROUP
    // ==================================
    /**
     * Searching Vehicles By Multiple options
     */
    public void searchVehicles() {

	// Get option of searching vehicles
	byte option = (byte) Menu.getChoiceInt(
		"Search By Id",
		"Search By Name",
		"Search By In Range of Min and Max Date");

	switch (option) {

	    // Searching By Id
	    case 1: {
		searchVehicleById();
		break;
	    }

	    // Search By Name
	    case 2: {
		searchVehicleByName();
		break;
	    }

	    // Search in Range of Date
	    case 3: {
		searchVehicleByDateInRange();
		break;
	    }
	    default: {
		break;
	    }
	}
    }

    /**
     * Searching Vehicles By Id
     */
    public void searchVehicleById() {
	checkVehicleExistOnId();
    }

    /**
     * Searching Vehicles By Names
     *
     * @return a list of matched and sorted names
     */
    public void searchVehicleByName() {
	// Input Name
	// - Does not allow null value since making no sense if storing unknown vehicle
	// - If user enter the name, then precheck it through the readString function
	String name = Utilities.readString("Enter Name Keyword",
		new String[]{Constants.INVALID_MSG("Name Keyword"),
		    Constants.MUST_IN_CONDITIONS_MSG("Name Keyword",
			    "No special characters",
			    "Name contains only alphabets, numeric characters and space")},
		"^[a-zA-Z0-9 ]*$",
		false);

	// Iterating and find vehicles machtching names
	List<Vehicle> matches = new ArrayList<>();
	for (Vehicle vehicle : this) {
	    if (vehicle.getName().toLowerCase().contains(name.toLowerCase())) {
		matches.add(vehicle);
	    }
	}

	// Sorting Matched Names Descendingly
	Collections.sort(matches, (Vehicle o1, Vehicle o2) -> -o1.getName().compareToIgnoreCase(o2.getName()));

	// Printout the list of matching names
	for (Vehicle vehicle : matches) {
	    Constants.DRAWING_LINE_ONE_MESSAGE(vehicle.toString());
	}

	// If nothing found, print a message to console
	if (matches.isEmpty()) {
	    Constants.DRAWING_LINE_ONE_MESSAGE(Constants.EMPTY_VALUE_MSG);
	}
    }

    /**
     * Search Vehicles By Date in Range
     */
    public void searchVehicleByDateInRange() {

	boolean isFound = false;

	// Input min date
	// - If user enter the date, then precheck it through the readDate function
	Date minDate = Utilities.readDate("Enter Min Date",
		new String[]{Constants.INVALID_MSG("Min Date"),
		    Constants.MUST_IN_CONDITIONS_MSG("Min Date",
			    "Date must be in format dd-MM-yyyy",
			    "No special characters")},
		"dd-MM-yyyy",
		false);

	// Input max date based on min date
	Date maxDate = Utilities.readDateAfter("Enter Max Date",
		new String[]{Constants.INVALID_MSG("Max Date"),
		    Constants.MUST_IN_CONDITIONS_MSG("Max Date",
			    "Date must be in format dd-MM-yyyy",
			    "No special characters",
			    "Max date has to be after " + Utilities.parseStringFromDate(minDate, "dd-MM-yyyy"))},
		"dd-MM-yyyy", minDate);

	// Iterating and find vehicles machtching in range of min and max date
	// minDate <= target <= maxDate
	for (Vehicle vehicle : this) {
	    if ((vehicle.getProductDate().after(minDate) || vehicle.getProductDate().equals(minDate))
		    && (vehicle.getProductDate().before(maxDate) || vehicle.getProductDate().equals(maxDate))) {
		Constants.DRAWING_LINE_ONE_MESSAGE(vehicle.toString());
		isFound = true;
	    }
	}

	// If nothing found, print a message to console
	if (!isFound) {
	    Constants.DRAWING_LINE_ONE_MESSAGE(Constants.EMPTY_VALUE_MSG);
	}
    }

    // ==================================
    // == FILE GROUP
    // ==================================
    /**
     * Loading content from file to the list
     *
     * @param filename: a file contains vehicles
     */
    public void loadVehiclesFromFile(String filename) {

	// Precheck if the file has been loaded
	if (!this.isEmpty()) {
	    Constants.DRAWING_LINE_ONE_MESSAGE("File has been loaded. Cannot load more.");
	    return;
	}

	// Reading file stream
	FileReader fileReader = null;

	// Reading file by line
	BufferedReader bufferReader = null;

	try {
	    fileReader = new FileReader(new File(filename));
	    bufferReader = new BufferedReader(fileReader);
	    String line = bufferReader.readLine();

	    // Iterating each line of file to extract format
	    while (line != null) {
		// Line format: id,name,color,price,brand,type,productDate
		StringTokenizer str = new StringTokenizer(line, ",");

		// Adding those token into Vehicle's attributes
		String id = str.nextToken().trim();
		String name = str.nextToken().trim();
		String color = str.nextToken().trim();
		double price = Double.parseDouble(str.nextToken().trim());
		String brand = str.nextToken().trim();
		String type = str.nextToken().trim();
		Date productDate = Utilities.parseDateFromString(str.nextToken().trim(), Constants.DATE_FORMAT);

		// Adding to the list
		this.add(new Vehicle(id, name, color, price, brand, type, productDate));

		// Next line
		line = bufferReader.readLine();
	    }

	    // Print sucessful messages
	    Constants.DRAWING_LINE_ONE_MESSAGE("Loaded file successfully.");

	} catch (FileNotFoundException ex) {

	    // Exit and printout if file is not 
	    System.err.println(ex);
	    Constants.DRAWING_LINE_ONE_MESSAGE("File not found. Please try again.");

	} catch (IOException ex) {

	    // Logging if IO having problems
	    Logger.getLogger(VehicleList.class.getName()).log(Level.SEVERE, null, ex);

	} finally {

	    // Close all streams
	    try {
		fileReader.close();
		bufferReader.close();
	    } catch (IOException ex) {
		Logger.getLogger(VehicleList.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }

    /**
     * Save list of vehicles to the file
     *
     * <br>With the mode isOverride, the file will be save to the original file if turning
     * <br>on or save at new file, same location if turning off
     *
     * @param fileName: a file is saved from the program
     */
    public void saveVehiclesFromFile(String fileName) {

	// Reading file stream
	FileWriter fileWriter = null;

	// Reading file by line
	PrintWriter printWriter = null;

	// File to save with overriden mode
	// vehicle.dat -> vehicleModified.dat
	fileName = isSaveAtNewFile(fileName);

	try {
	    fileWriter = new FileWriter(fileName);
	    printWriter = new PrintWriter(fileWriter);

	    // Iterating the list and send the the writer stream
	    for (Vehicle vehicle : this) {
		printWriter.println(vehicle.toString());
	    }

	    // Print sucessful messages
	    Constants.DRAWING_LINE_ONE_MESSAGE("Save file successfully.");

	} catch (FileNotFoundException ex) {

	    // Exit and printout if file is not 
	    System.err.println(ex);
	    System.out.println("File not found. Please try again.");

	} catch (IOException ex) {

	    // Logging if IO having problems
	    Logger.getLogger(VehicleList.class.getName()).log(Level.SEVERE, null, ex);

	} finally {

	    // Close all streams
	    try {
		fileWriter.close();
		printWriter.close();
	    } catch (IOException ex) {
		Logger.getLogger(VehicleList.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }

    /**
     * Determine the file is being override or saved at new file
     *
     * @param fileName: original file
     * @return a file to be save
     */
    public String isSaveAtNewFile(String fileName) {
	boolean isSaveAtNewFile = Utilities.readBoolean("Do you want to save at new file (Y/N)",
		new String[]{"Only accept boolean value (Y/N)"});

	if (isSaveAtNewFile) {
	    // vehicle.dat -> vehicleCopied.dat
	    String regex = "(\\w+)(\\.(dat|txt))$";
	    String replacementFile = "$1Copied$2";

	    Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(fileName);

	    fileName = m.replaceAll(replacementFile);
	}
	return fileName;
    }
}
