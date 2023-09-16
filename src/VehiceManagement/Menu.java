/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VehiceManagement;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author duyvu
 */
public class Menu {

    private final static Scanner sc = new Scanner(System.in);

    /**
     * 
     * 
     * ** All of the languages have the ability to let method take dynamic amount of arguments. It considers a 1D-array
     *
     * @param options
     * @return
     */
    public static int getChoiceInt(String... options) {
	int L = options.length;
	for (int i = 0; i < L; i++) {
	    System.out.println((i + 1) + '-' + options[i]);
	    System.out.println("Choose (1.." + L + "): ");
	}
	return Integer.parseInt(sc.nextLine());
    }

    /**
     * Get the option by Numeric type from the user
     * @param list
     * @return 
     */
    public static Object getChoiceObject(List list) {
	int choice = 0;
	int L = list.size();
	for (int i = 0; i < L; i++) {
	    System.out.println((i + 1) + '-' + list.get(i).toString());
	}
	return (choice > 0 && choice <= L) ? list.get(choice - 1) : null;
    }

    public static void main(String[] args) {
	
    }
}
