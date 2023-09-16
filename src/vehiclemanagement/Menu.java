/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vehiclemanagement;

import java.util.List;
import java.util.Scanner;
import tools.Utilities;

/**
 *
 * @author duyvu
 */
public class Menu {

    /** All of the languages have the ability to let method take dynamic amount of arguments. It considers a 1D-array
     *
     * @param options: supply multiple options for menu's options
     * @return a selection from the user
     */
    public static int getChoiceInt(String... options) {
        final int L = options.length;
        for (int i = 0; i < L; i++) {
            System.out.println((i + 1) + "-" + options[i]);
        }
        System.out.print("Choose (1.." + L + "): ");
        return Integer.parseInt(Utilities.sc.nextLine());
    }

    /** Get the option by Numeric type from the user
     *
     * @param list
     * @return
     */
    public static Object getChoiceObject(List list) {
        int choice = 0;
        final int L = list.size();
        for (int i = 0; i < L; i++) {
            System.out.println((i + 1) + '-' + list.get(i).toString());
        }
        return (choice > 0 && choice <= L) ? list.get(choice - 1) : null;
    }
}
