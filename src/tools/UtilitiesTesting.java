/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author duyvu
 */
public class UtilitiesTesting {

    public static void testParsingBoolean() {
        // Testing Parsing Boolean
        System.out.println("Resolve: " + Utilities.parseBoolean("1"));
        System.out.println("Resolve: " + Utilities.parseBoolean("1asd"));
        System.out.println("Resolve: " + Utilities.parseBoolean("t"));
        System.out.println("Resolve: " + Utilities.parseBoolean("T"));
        System.out.println("Resolve: " + Utilities.parseBoolean("True"));
        System.out.println("Resolve: " + Utilities.parseBoolean("tasdasd"));
        System.out.println("Resolve: " + Utilities.parseBoolean("0"));
        System.out.println("Resolve: " + Utilities.parseBoolean("asd0"));
    }

    public static void testNormalizingTheDateString() {
        // Testing normalizing the date str
        System.out.println("Resolve: " + Utilities.normalizeDateStr("7    --- 2 ///  2023"));
        System.out.println("Resolve: " + Utilities.normalizeDateStr("7..........2 $#$%  2023"));
        System.out.println("Resolve: " + Utilities.normalizeDateStr("29    --- 2 !!!  2023"));

    }

    public static void testParsingDateFromString() {
        // Testing Parsing Date based on the given format
        System.out.println("Resolve: " + Utilities.parseDateFromString(Utilities.normalizeDateStr("12   -  22__ 2023"), "dd-MM-yyyy"));
    }

    public static void testDateGetPartAndParsingStringFromDate() {
        // Testing get the calendar part of the date
        // Testing Parsing String based on Date and the given format
        String[] formats = {"yyyy-MM-dd", "MM-dd-yyyy", "dd-MM-yyyy"};
        String[] dateString = {"2023-02-21", "03-12-2000", "12-11-1999"};
        Date date = null;

        System.out.println("");
        for (int i = 0; i < 3; i++) {
            System.out.println(dateString[i] + "(" + formats[i] + ")");
            date = Utilities.parseDateFromString(dateString[i], formats[i]);
            System.out.println("Date: " + date);

            if (date != null) {
                System.out.println("Days: " + Utilities.getDatePart(date, Calendar.DAY_OF_MONTH));
                System.out.println("Months:" + Utilities.getDatePart(date, Calendar.MONTH));
                System.out.println("Year: " + Utilities.getDatePart(date, Calendar.YEAR));
                System.out.println("");
            }
        }
        System.out.println("");
    }

    public static void testDateAndBeforeAndAfter() {
        // Testing reading Date
        Date currentDate = Utilities.readDate("Enter date with format dd-MM-yyyy", "dd-MM-yyyy");
        Date beforeDate = Utilities.readDateBefore("Enter the date before " + Utilities.parseStringFromDate(currentDate, "dd-MM-yyyy") + " with format dd-MM-yyyy",
                                                   "dd-MM-yyyy",
                                                   currentDate);
        Date afterDate = Utilities.readDateAfter("Enter the date after " + Utilities.parseStringFromDate(currentDate, "dd-MM-yyyy") + " with format dd-MM-yyyy",
                                                 "dd-MM-yyyy",
                                                 currentDate);

        System.out.println("Date: " + currentDate);
        System.out.println("Date Before: " + beforeDate);
        System.out.println("Date After: " + afterDate);

    }

    public static void testStringAndBooleanInput() {
        System.out.println("String: " + Utilities.readString("Enter the number", new String[]{"Invalid Message. Please try again."}, "^\\d{6,}$"));
        System.out.println("Boolean: " + Utilities.readBoolean("Enter the boolean", new String[]{"Invalid Boolean. Please try again."}));
    }

    // Entry point of the test program
    public static void main(String[] args) {
//        testDateAndBeforeAndAfter();
        testStringAndBooleanInput();
    }
}
