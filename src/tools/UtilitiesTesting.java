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

    public static void main(String[] args) {
        // Testing Parsing Boolean
        System.out.println("Resolve: " + Utilities.parseBoolean("1"));
        System.out.println("Resolve: " + Utilities.parseBoolean("1asd"));
        System.out.println("Resolve: " + Utilities.parseBoolean("t"));
        System.out.println("Resolve: " + Utilities.parseBoolean("T"));
        System.out.println("Resolve: " + Utilities.parseBoolean("True"));
        System.out.println("Resolve: " + Utilities.parseBoolean("tasdasd"));
        System.out.println("Resolve: " + Utilities.parseBoolean("0"));
        System.out.println("Resolve: " + Utilities.parseBoolean("asd0"));

        // Testing normalizing the date str
        System.out.println("Resolve: " + Utilities.normalizeDateStr("7    --- 2 ///  2023"));
        System.out.println("Resolve: " + Utilities.normalizeDateStr("7..........2 $#$%  2023"));
        System.out.println("Resolve: " + Utilities.normalizeDateStr("29    --- 2 !!!  2023"));

        // Testing Parsing Date based on the given format
        System.out.println("Resolve: " + Utilities.parsingDateFromString(Utilities.normalizeDateStr("12   -  22__ 2023"),
                                                                         "dd-MM-yyyy"));

        // Testing get the calendar part of the date
        // Testing Parsing String based on Date and the given format
        String[] formats = {"yyyy-MM-dd", "MM-dd-yyyy", "dd-MM-yyyy"};
        String[] dateString = {"2023-02-21", "03-12-2000", "12-11-1999"};
        Date date = null;

        System.out.println("");
        for (int i = 0; i < 3; i++) {
            System.out.println(dateString[i] + "(" + formats[i] + ")");
            date = Utilities.parsingDateFromString(dateString[i], formats[i]);
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
}
