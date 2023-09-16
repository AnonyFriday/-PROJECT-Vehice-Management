/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VehiceManagement;

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
    int productYear;

    public Vehicle(String id) {
	this.id = id;
    }

    public Vehicle(String id, String name, String color, double price, String brand, String type, int productYear) {
	this.id = id;
	this.name = name;
	this.color = color;
	this.price = price;
	this.brand = brand;
	this.type = type;
	this.productYear = productYear;
    }

    @Override
    public int compareTo(Vehicle o) {
	return this.id.compareTo(o.id);
    }

    @Override
    public boolean equals(Object obj) {
	Vehicle v = ((Vehicle) obj);
	return this.id.equals(v.id);
    }

    @Override
    public String toString() {
	return id + "," + name + "," + color
		+ "," + price + "," + brand + ","
		+ type + "," + productYear;
    }

    // TODO: Read and Write from File
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

    public int getProductYear() {
	return productYear;
    }

    public void setProductYear(int productYear) {
	this.productYear = productYear;
    }

}
