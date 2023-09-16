/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

/**
 *
 * @author duyvu
 */
public abstract class Constants {

    // Date
    public static final String DATE_DELIMITER = "-";

    // Invalid Message
    public static final String INVALID_MSG(String subject) {
        return "Invalid " + subject + ". Please try again.";
    }
}
