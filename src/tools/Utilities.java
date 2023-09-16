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

/** Common tools in Application
 *
 * @author Vu Kim Duy
 */
public abstract class Utilities {

    // ======================================
    // = ATTRIBUTES
    // ======================================
    private static final Scanner sc = new Scanner(System.in);

    // ======================================
    // = BOOLEAN GROUPS
    // ======================================
    /** Parsing the input string based on the boolean data
     *
     * @param input: input a string
     * @return true if the string is 't', 'T', 1, any case of 'true' and 'false'
     */
    public static Boolean parseBoolean(String input) {

        // Match only 1 character: t,1,f or true at the beginning of the string
        String regex = "^([t1f]{1}\\W*)|^(true)";

        // Return true if match each group of the given pattern
        return input.trim().toLowerCase().matches(regex);
    }

    // ======================================
    // = DATE GROUPS
    // ======================================
    /** Normalizing a data string to use only '-' as the seperator
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
                .replaceAll("[!@#$%^&*()_./-]+", "-");
        return result;
    }

    /** Parse Date based on
     *
     * @param inputStr
     * @param dateFormat
     * @return
     */
    public static Date parsingDateFromString(String inputStr,
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
            System.out.println(ex);
        }
        return null; // Return error if parsing date unsuccessful
    }

    /** Parsing a String format from the Date Object
     *
     * @param date
     * @param dateFormat
     * @return
     */
    public static String parsingStringFromDate(Date date,
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

    /** Extract the part of date by using Calendar package
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
}
