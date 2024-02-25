package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** This is the Main Menu Controller class, which implements the Initialization class.*/
public class MainMenuController implements Initializable {

    /**This the Scene Loader method.
     * This method enables the application to load and direct to the desired scene after an event occurs.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent).
     * @param location This is the location of the desired scene's path file (.fxml, passed as a String).*/
    public void sceneLoader(ActionEvent event, String location) throws IOException {

        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource(location));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This is the Error Box method.
     * This method creates an Error Dialog Box with the desired content.
     * @param errorContentText This is the desired content to be displayed in the Error Dialog Box (String).*/
    public void errorBox(String errorContentText) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(errorContentText);
        alert.showAndWait();
    }



    @FXML
    private TextField mainMenuPartsSearchTxt;

    @FXML
    private TableView<Part> partsTableView;

    @FXML
    private TableColumn<Part, Integer> mainMenuPartsPartIDCol;

    @FXML
    private TableColumn<Part, String> mainMenuPartsPartNameCol;

    @FXML
    private TableColumn<Part, Integer> mainMenuPartsInventoryLevelCol;

    @FXML
    private TableColumn<Part, Double> mainMenuPartsPPUCol;


    /** This is the action taken when the "Add" button is pressed at the Main Menu under the Parts Table.
     * This method calls the Scene Loader and loads the AddPart Scene (passing the ActionEvent object and the file path of the AddPart Scene).
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void mainMenuPartsAdd(ActionEvent event) throws IOException {
        sceneLoader(event, "/view/AddPart.fxml");
    }

    /** This is the action taken when the "Modify" button is pressed at the Main Menu under the Parts Table.
     * This method creates an instance of the Modify Part Controller and calls the method transferPart in order to pass the selected object to the Modify Part Scene.
     * This method also displays an Error Dialog Box if no item is selected to Modify.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void mainMenuPartsModify(ActionEvent event) throws IOException {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyPart.fxml"));
            loader.load();

            ModifyPartController MPartController = loader.getController();
            MPartController.transferPart(partsTableView.getSelectionModel().getSelectedItem(),
                    partsTableView.getSelectionModel().getSelectedIndex());

            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (NullPointerException e) {

            errorBox("No part was selected for modification!" +
                    " \nPlease select a part to modify.");
        }

    }

    /** This is the action taken when the "Delete" button is pressed at the Main Menu under the Parts Table.
     * This method Deletes all parts that were selected. A Confirmation Dialog Box is displayed to confirm deletion.
     * This method also displays an Error Dialog Box if no item is selected to be Deleted.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void mainMenuPartsDelete(ActionEvent event) {

        if (partsTableView.getSelectionModel().getSelectedItem() == null) {

            errorBox("No part was selected for deletion!" +
                    " \nPlease select a part to delete.");

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setContentText("Are you sure you want to delete this part?");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK){
                Inventory.getAllParts().remove(partsTableView.getSelectionModel().getSelectedItem());;
            }

        }

    }



    @FXML
    private TextField mainMenuProductsSearchTxt;

    @FXML
    private TableView<Product> productsTableView;

    @FXML
    private TableColumn<Product, Integer> mainMenuProductsPartIDCol;

    @FXML
    private TableColumn<Product, String> mainMenuProductsPartNameCol;

    @FXML
    private TableColumn<Product, Integer> mainMenuProductsInventoryLevelCol;

    @FXML
    private TableColumn<Product, Double> mainMenuProductsPPUCol;


    /** This is the action taken when the "Add" button is pressed at the Main Menu under the Products Table.
     * This method calls the Scene Loader and loads the AddProduct Scene (passing the ActionEvent object and the file path of the AddProduct Scene).
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void mainMenuProductsAdd(ActionEvent event) throws IOException {
        sceneLoader(event, "/view/AddProduct.fxml");
    }

    /** This is the action taken when the "Modify" button is pressed at the Main Menu under the Products Table.
     * This method creates an instance of the Modify Product Controller and calls the method transferProduct in order to pass the selected object to the Modify Product Scene.
     * This method also displays an Error Dialog Box if no item is selected to Modify.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void mainMenuProductsModify(ActionEvent event) throws IOException {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyProduct.fxml"));
            loader.load();

            ModifyProductController MProductController = loader.getController();
            MProductController.transferProduct(productsTableView.getSelectionModel().getSelectedItem(),
                    productsTableView.getSelectionModel().getSelectedIndex());

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (NullPointerException e) {

            errorBox("No product was selected for modification!" +
                    " \nPlease select a product to modify.");

        }

    }

    /** This is the action taken when the "Delete" button is pressed at the Main Menu under the Products Table.
     * This method only Deletes the selected Product if it has no Associated Parts. A Confirmation Dialog Box is displayed to confirm deletion.
     * This method also displays an Error Dialog Box if no item is selected to be Deleted or if the selected item has Associated Parts.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void mainMenuProductsDelete(ActionEvent event) {

        if (productsTableView.getSelectionModel().getSelectedItem() == null) {

            errorBox("No product was selected for deletion!" +
                    " \nPlease select a product to delete.");


        } else if (!productsTableView.getSelectionModel().getSelectedItem().getAllAssociatedParts().isEmpty()) {

            errorBox("The selected product cannot be deleted because it has parts associated with it.");

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setContentText("Are you sure you want to delete this product?");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK){
               Inventory.getAllProducts().remove(productsTableView.getSelectionModel().getSelectedItem());
            }

        }

    }


    /** This is the action taken when the "Exit" button is pressed at the Main Menu.
     * This method exits and closes the Application safely.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void mainMenuExit(ActionEvent event) {
        System.exit(0);
    }

    /** This is an override Initialize method.
     * This method sets both the Parts and Products tables with the correct fields and values. This method also sets up the search function for both tables.
     * @param url URL.
     * @param resourceBundle Resource Bundle.
     * RUNTIME ERROR: A runtime error that I came across was my search function not working.
     * I had set up my listener and predicate perfectly, but I didn't see any tableview changes.
     * To solve this issue, I debugged my code and went step by step to see my code execute.
     * Doing this made me realize that I didn't make the sorted list, bind it, and reset the table view.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partsTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        partsTableView.setItems(Inventory.getAllParts());
        mainMenuPartsPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainMenuPartsPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainMenuPartsInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainMenuPartsPPUCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        productsTableView.setItems(Inventory.getAllProducts());
        mainMenuProductsPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainMenuProductsPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainMenuProductsInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainMenuProductsPPUCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        FilteredList<Part> partFilteredList = new FilteredList<>(Inventory.getAllParts(), s -> true);

        mainMenuPartsSearchTxt.textProperty().addListener((observable, oldValue, newValue) -> {

            partFilteredList.setPredicate(Part -> {

               if (newValue.isEmpty() || newValue.isBlank()) {
                   return true;
               }

               String searchText = newValue.toLowerCase();

               if (Part.getName().toLowerCase().contains(searchText)){
                   return true;
               } else if (String.valueOf(Part.getId()).contains(searchText)) {
                   return true;
               } else {
                   return false;
               }

            });

        });

        SortedList<Part> partSortedList = new SortedList<>(partFilteredList);
        partSortedList.comparatorProperty().bind(partsTableView.comparatorProperty());
        partsTableView.setItems(partSortedList);



        FilteredList<Product> productFilteredList = new FilteredList<>(Inventory.getAllProducts(), s -> true);

        mainMenuProductsSearchTxt.textProperty().addListener((observable, oldValue, newValue) -> {

            productFilteredList.setPredicate(Product -> {

                if (newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }

                String searchText = newValue.toLowerCase();

                if (Product.getName().toLowerCase().contains(searchText)){
                    return true;
                } else if (String.valueOf(Product.getId()).contains(searchText)) {
                    return true;
                } else {
                    return false;
                }

            });

        });

        SortedList<Product> productSortedList = new SortedList<>(productFilteredList);
        productSortedList.comparatorProperty().bind(productsTableView.comparatorProperty());
        productsTableView.setItems(productSortedList);

    }

}