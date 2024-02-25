package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This is the Product class.*/
public class Product {

    /** This is the Product class constructor.
     * The Product class constructor requires an ID, Name, Price, Stock, Minimum #, and Maximum #.
     * @param id This is the Product ID (int).
     * @param name This is the Product Name (String).
     * @param price This is the Product Price (double).
     * @param stock This is the Stock # of the Product (int).
     * @param min This is the Minimum Stock of this Product we can have (int).
     * @param max This is the Maximum Stock of this Product we can have (int). */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** This is the Associate Part Adder.
     * This adds a Part to the Associated Parts list for this Product.
     * @param part This is the Part you would like to Associate with this Product (Part).*/
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /** This is the Associate Part Deleter.
     * This deletes a Part in the Associated Parts list for this Product.
     * @param selectedAssociatedPart This is the Part you would like to Disassociate with this Product (Part).
     * @return Returns true if the Part was Disassociated, or false if it was not (boolean).*/
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        boolean wasRemoved = associatedParts.remove(selectedAssociatedPart);
        return wasRemoved;
    }

    /** This is the Product Associated Parts getter.
     * This returns the Associated Parts list for this Product.
     * @return Returns the Associated Parts list for this Product.*/
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    /** This is the Product ID getter.
     * This returns the ID of the Product.
     * @return Returns the Product ID (int).*/
    public int getId() {
        return id;
    }

    /** This is the Product ID setter.
     * This sets the ID for the Product.
     * @param id This is the desired Product ID (int).*/
    public void setId(int id) {
        this.id = id;
    }

    /** This is the Product Name getter.
     * This returns the Name of the Product.
     * @return Returns the Product Name (String).*/
    public String getName() {
        return name;
    }

    /** This is the Product Name setter.
     * This sets the Name for the Product.
     * @param name This is the desired Product Name (String).*/
    public void setName(String name) {
        this.name = name;
    }

    /** This is the Product Price getter.
     * This returns the Price of the Product.
     * @return Returns the Product Price (double).*/
    public double getPrice() {
        return price;
    }

    /** This is the Product Price setter.
     * This sets the ID for the Product.
     * @param price This is the desired Product Price (double).*/
    public void setPrice(double price) {
        this.price = price;
    }

    /** This is the Product Stock getter.
     * This returns the Stock of the Product.
     * @return Returns the Product Stock (int).*/
    public int getStock() {
        return stock;
    }

    /** This is the Product Stock setter.
     * This sets the Stock for the Product.
     * @param stock This is the desired Product Stock (int).*/
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** This is the Product Minimum Stock getter.
     * This returns the Minimum Stock of the Product.
     * @return Returns the Minimum Stock of the Product (int).*/
    public int getMin() {
        return min;
    }

    /** This is the Product Minimum Stock setter.
     * This sets the Minimum Stock for the Product.
     * @param min This is the desired Minimum Stock of the Product (int).*/
    public void setMin(int min) {
        this.min = min;
    }

    /** This is the Product Maximum Stock getter.
     * This returns the Maximum Stock of the Product.
     * @return Returns the Maximum Stock of the Product (int).*/
    public int getMax() {
        return max;
    }

    /** This is the Product Maximum Stock setter.
     * This sets the Maximum Stock for the Product.
     * @param max This is the desired Maximum Stock of the Product (int).*/
    public void setMax(int max) {
        this.max = max;
    }
}
