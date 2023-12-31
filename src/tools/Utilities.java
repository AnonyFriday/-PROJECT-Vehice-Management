/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.SpringLayout;

/**
 * Common tools in Application
 *
 * @author Vu Kim Duy
 */
public abstract class Utilities {

    // ======================================
    // = ATTRIBUTES
    // ======================================
    public static final Scanner sc = new Scanner(System.in);

    // ======================================
    // = BOOLEAN GROUPS
    // ======================================
    /**
     * Parsing the input string based on the boolean data
     *
     * @param input: input a string
     * @return true if the string is 't', 'T', 1, any case of 'true' and 'false'
     */
    public static Boolean parseBoolean(String input) {

	Boolean value;

	// Match only 1 character: t,1,f or true at the beginning of the string
	String regexTrueValue = "^([t1y]{1})|^(true)";
	String regexFalseValue = "^([f0n]{1})|^(false)";

	// Return true | false if match each group of the given pattern
	input = input.trim().toLowerCase();
	if (input.matches(regexTrueValue)) {
	    return true;
	} else if (input.matches(regexFalseValue)) {
	    return false;
	}

	// Return the null value if not matching with the boolean pattern
	return null;
    }

    /**
     * Reading the boolean pattern from the input string
     *
     * @param prompt: A prompt to input a string
     * @param invalidMsgs: invalid messages
     * @return true or false based on pattern configured on the for the input string
     */
    public static boolean readBoolean(String prompt,
	    String[] invalidMsgs) {

	Boolean result;
	do {
	    System.out.print("\n" + prompt + ": ");
	    result = parseBoolean(sc.nextLine());

	    // If the Boolean object is null, print out to the console and try again
	    if (result == null && invalidMsgs.length > 0) {
		for (String message : invalidMsgs) {
		    System.out.println(message);
		}
	    }

	} while (result == null);

	return result;
    }

    // ======================================
    // = DATE GROUPS
    // ======================================
    /**
     * Normalizing a data string to use only '-' as the seperator
     *
     * <br><br>For example with 7------2//////////2023 --> 7-2-2023
     *
     * @param dateStr: input date string
     * @param sep: the separator for the formated date
     * @return new string
     */
    public static String normalizeDateStr(String dateStr) {

	// Chaining the replaceAll to format the date string
	// ** Special character followed by .<character> => ./**
	// First regex: 7   --  2 /// 2033 => 7--2///2023 (replace all in-between spaces)
	// Second regex: 7--2///2033 => 7-2-2023 (replace all in-between special escape characters)
	String result = dateStr
		.replaceAll("\\s+", "")
		.replaceAll("[!@#$%^&*()_./-]+", Constants.DATE_DELIMITER);
	return result;
    }

    /**
     * Parse Date based on
     *
     * @param inputStr
     * @param dateFormat
     * @return
     */
    public static Date parseDateFromString(String inputStr,
	    String dateFormat) {

	// Format the input string to 2-3-2022
	inputStr = normalizeDateStr(inputStr);
	DateFormat formatter = new SimpleDateFormat(dateFormat);

	// Set lenient to force the strict rule apply on natural calendar 
	// e.g. 31/2 => error
	formatter.setLenient(false);

	// Formatting the given 2-3-2023 to the specific format date
	try {
	    return formatter.parse(inputStr);
	} catch (ParseException ex) {
	    System.err.println(ex);
	}
	return null; // Return error if parsing date unsuccessful
    }

    /**
     * Parsing a String format from the Date Object
     *
     * @param date
     * @param dateFormat
     * @return
     */
    public static String parseStringFromDate(Date date,
	    String dateFormat) {
	// If null than return empty string
	if (date == null) {
	    return "";
	}

	// Create the formatter with the given dateFormat
	DateFormat formatter = new SimpleDateFormat(dateFormat);

	// return the format of dateString based on the dateObject
	return formatter.format(date);
    }

    /**
     * Extract the part of date by using Calendar package
     *
     * @param date
     * @param calendarPart
     * @return
     */
    public static int getDatePart(Date date,
	    int calendarPart) {

	// Determine the calendar by creating the object GregorianCalendar
	Calendar calendar = new GregorianCalendar(Locale.ENGLISH);
	calendar.setTime(date);

	// Set Lenient to false to force the strict rule on natural calendar
	calendar.setLenient(false);

	// Handling the 0th-index of the first month if extracting month
	if (calendarPart == Calendar.MONTH) {
	    return calendar.get(calendarPart) + 1;
	}

	// Other Calendar parts
	return calendar.get(calendarPart);
    }

    /**
     * Prompting user to input a date-like-string and convert to the Date object
     *
     * <br>Available Date format: dd-MM-yyyy, yyyy-MM-dd,
     *
     * @param prompt: will be printted before inputting
     * @param invalidMsgs: output messages to console incase of invalid input
     * @param dateFormat: input a date format
     * @param isSkippable: return null immediately if user donot enter any values
     * @return a date object based on the input string
     */
    public static Date readDate(String prompt,
	    String[] invalidMsgs,
	    String dateFormat,
	    boolean isSkippable) {

	Date date = null;
	String inputStr;
	do {
	    System.out.print("\n" + prompt + ": ");
	    inputStr = sc.nextLine().trim();

	    // Assign the date object created from the parsing function 
	    date = parseDateFromString(inputStr, dateFormat);

	    // If skippable is enabled and date == null, then break the loop immediately
	    if (date == null && isSkippable) {
		break;
	    }

	    // Output the msg if the date is valid
	    {
		if (date == null && invalidMsgs.length > 0) {
		    for (String message : invalidMsgs) {
			System.out.println(message);
		    }
		}
	    }
	} while (date == null);

	return date;
    }

    /**
     * Read Date Before the given date
     *
     *
     * @param prompt: prompting the date to return
     * @param invalidMsgs: output messages to console incase of invalid input
     * @param dateFormat: date format
     * @param markerDate: the given date
     * @return the date before the given date
     */
    public static Date readDateBefore(String prompt,
	    String[] invalidMsgs,
	    String dateFormat,
	    Date markerDate) {
	Date dateBefore = null;
	String inputStr = null;
	boolean isValidDateBefore = false;

	do {
	    // Input the date before the marker date
	    System.out.print("\n" + prompt + ": ");
	    inputStr = sc.nextLine().trim();
	    dateBefore = parseDateFromString(inputStr, dateFormat);
	    isValidDateBefore = (dateBefore != null) && dateBefore.before(markerDate);

	    // Output the msg if the date is valid
	    if (!isValidDateBefore && invalidMsgs.length > 0) {
		for (String message : invalidMsgs) {
		    System.out.println(message);
		}
	    }
	} while (!isValidDateBefore);

	return dateBefore;
    }

    /**
     * Read Date after the given date
     *
     * @param prompt: prompting the date to return
     * @param invalidMsgs: output messages to console incase of invalid input
     * @param dateFormat: date format
     * @param markerDate: the given date
     * @return the date after the given date
     */
    public static Date readDateAfter(String prompt,
	    String[] invalidMsgs,
	    String dateFormat,
	    Date markerDate) {
	Date dateAfter = null;
	String inputStr = null;
	boolean isValidDateAfter = false;

	do {
	    // Input the date after the marker date
	    System.out.print("\n" + prompt + ": ");
	    inputStr = sc.nextLine().trim();
	    dateAfter = parseDateFromString(inputStr, dateFormat);

	    // Print notice msg if not matching the pattern and having invalid messages   
	    isValidDateAfter = (dateAfter != null) && dateAfter.after(markerDate);
	    if (!isValidDateAfter && invalidMsgs.length > 0) {
		for (String message : invalidMsgs) {
		    System.out.println(message);
		}
	    }
	} while (!isValidDateAfter);

	return dateAfter;
    }

    // ======================================
    // = STRING GROUPS
    // ======================================
    /**
     * Read a String using the pre-define pattern
     *
     * @param prompt: prompting user msg
     * @param invalidMsgs:invalid messages for specific input type
     * @param strFormat: regular expression to match the string format
     * @param isSkippable: determine if the user skip the input and return null to set a default value
     * @return null value if skippable or a new value
     */
    public static String readString(String prompt,
	    String[] invalidMsgs,
	    String strFormat,
	    boolean isSkippable) {

	String inputStr = null;
	boolean isMatched = false;
	do {
	    System.out.print("\n" + prompt + ": ");
	    inputStr = sc.nextLine().trim();

	    // Break the loop immediately if the skippable is enable and user enter empty character
	    if (inputStr.isBlank() && isSkippable) {
		break;
	    }

	    // Comparing the input and the pattern
	    isMatched = inputStr.matches(strFormat);

	    // Print notice msg if not matching the pattern and having invalid messages         
	    if (!isMatched && invalidMsgs.length > 0) {
		for (String message : invalidMsgs) {
		    System.out.println(message);
		}
	    }
	} while (!isMatched);

	return inputStr;
    }
}
