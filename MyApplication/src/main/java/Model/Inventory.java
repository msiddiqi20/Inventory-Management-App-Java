package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This is the Inventory class.*/
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /** This is the Inventory Part Adder.
     * This Adds a Part to the Inventory Parts list.
     * @param newPart This is the Part you would like to Add to the Inventory (Part).*/
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /** This is the Part Looker (using the Part ID).
     * This method uses the Part ID to look up the matching Part in the Inventory Parts list.
     * @param partID This the Part ID of the Part you would like to lookup (int).
     * @return Returns the Part with the corresponding Part ID (Part).*/
    public static Part lookupPart(int partID) {

        for (Part i : allParts) {
            int idCheck = i.getId();

            if (idCheck == partID){
                return i;
            }
        }

        return null;
    }

    /** This is the Part Looker (using the Part Name).
     * This method uses the Part Name to look up the matching Part in the Inventory Parts list.
     * @param partName This the Part Name of the Part you would like to lookup (String).
     * @return Returns the Part with the corresponding Part Name (Part).*/
    public static ObservableList<Part> lookupPart(String partName) {

        ObservableList<Part> containsName = null;

        for (Part i : allParts) {
            String nameCheck = i.getName();

            if (nameCheck.contains(partName)){
                containsName.add(i);
            }
        }

        return containsName;
    }

    /** This is the Inventory Part Updater.
     * This Updates a Part in the Inventory Parts list.
     * @param index This is the index of the Part you would like tp Update (int).
     * @param selectedPart This is the Part you would like to Update in the Inventory (Part).*/
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /** This is the Inventory Part Deleter.
     * This Deletes a Part in the Inventory Parts list.
     * @param selectedPart This is the Part you would like to Delete (Part).
     * @return Returns true if the Part was Deleted, or false if it was not (boolean).*/
    public static boolean deletePart(Part selectedPart) {
        boolean wasPartDeleted = allParts.remove(selectedPart);
        return wasPartDeleted;
    }

    /** This is the Inventory Parts list getter.
     * This returns the Inventory Parts list.
     * @return Returns the Inventory Parts list.*/
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }



    /** This is the Inventory Product Adder.
     * This Adds a Product to the Inventory Products list.
     * @param newProduct This is the Product you would like to Add to the Inventory (Product).*/
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /** This is the Product Looker (using the Product ID).
     * This method uses the Product ID to look up the matching Product in the Inventory Products list.
     * @param productID This the Product ID of the Product you would like to lookup (int).
     * @return Returns the Product with the corresponding Product ID (Product).*/
    public static Product lookupProduct(int productID) {

        for (Product i : allProducts) {
            int idCheck = i.getId();

            if (idCheck == productID){
                return i;
            }
        }

        return null;
    }

    /** This is the Product Looker (using the Product Name).
     * This method uses the Product Name to look up the matching Product in the Inventory Products list.
     * @param productName This the Product Name of the Product you would like to lookup (String).
     * @return Returns the Product with the corresponding Product Name (Product).*/
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> containsName = null;

        for (Product i : allProducts) {
            String nameCheck = i.getName();

            if (nameCheck.contains(productName)){
                containsName.add(i);
            }
        }

        return containsName;
    }

    /** This is the Inventory Product Updater.
     * This Updates a Product in the Inventory Products list.
     * @param index This is the index of the Product you would like tp Update (int).
     * @param newProduct This is the Product you would like to Update in the Inventory (Product).*/
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /** This is the Inventory Product Deleter.
     * This Deletes a Product in the Inventory Products list.
     * @param selectedProduct This is the Product you would like to Delete (Product).
     * @return Returns true if the Product was Deleted, or false if it was not (boolean).*/
    public static boolean deleteProduct(Product selectedProduct) {
        boolean wasProductDeleted = allParts.remove(selectedProduct);
        return wasProductDeleted;
    }

    /** This is the Inventory Products list getter.
     * This returns the Inventory Products list.
     * @return Returns the Inventory Products list.*/
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
