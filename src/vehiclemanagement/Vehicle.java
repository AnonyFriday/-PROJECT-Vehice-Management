/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vehiclemanagement;

import java.util.Date;
import tools.Utilities;

/**
 *
 * @author duyvu
 */
public class Vehicle implements Comparable<Vehicle> {

    String id;
    String name;
    String color;
    double price;
    String brand;
    String type;
    Date productDate;

    public Vehicle(String id) {
        this.id = id;
    }

    public Vehicle(String id,
                   String name,
                   String color,
                   double price,
                   String brand,
                   String type,
                   Date productDate) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.brand = brand;
        this.type = type;
        this.productDate = productDate;
    }

    /** Comparing 2 Vehicles criteria applied on Sort algorithm
     *
     * @param o: a next vehicle to compare with the current vehicle
     * @return 1,-1,0 bases on the vehicle's id
     */
    @Override
    public int compareTo(Vehicle o) {
        return this.id.compareTo(o.id);
    }

    /** Checking if 2 Vehicles are equals or not
     *
     * @param obj: a next vehicle
     * @return true or false bases on the vehicle's id comparison
     */
    @Override
    public boolean equals(Object obj) {
        Vehicle v = ((Vehicle) obj);
        return this.id.equals(v.id);
    }

    /** Printing out the Object
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return id + "," + name + "," + color +
               "," + price + "," + brand + "," +
               type + "," + Utilities.parseStringFromDate(productDate, "dd-MM-yyyy");
    }

    // TODO: Read and Write from File
    // ======================================
    // = Getters & Setters
    // ======================================
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }
}
