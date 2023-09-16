/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vehiclemanagement;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author duyvu
 */
public class VehicleList extends ArrayList<Vehicle> {

    Comparator<Vehicle> cNameDesc = new Comparator<Vehicle>() {
        @Override
        public int compare(Vehicle o1,
                           Vehicle o2) {
            return -o1.name.compareTo(o2.name);
        }
    };

    // Constructor
    public VehicleList() {

    }

    // TODO: addVehicle
    // TODO: search. Denote that do not precheck the String for searching
}
