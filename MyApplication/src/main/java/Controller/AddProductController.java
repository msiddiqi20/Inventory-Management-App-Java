package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/** This is the Add Product Controller class, which implements the Initialization class.*/
public class AddProductController implements Initializable {

    private ObservableList<Part> tempAssociatedParts = FXCollections.observableArrayList();

    /**This the Scene Loader method.
     * This method enables the application to load and direct to the desired scene after an event occurs.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent).
     * @param location This is the location of the desired scene's path file (.fxml, passed as a String). */
    public void sceneLoader(ActionEvent event, String location) throws IOException {

        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource(location));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This is the nextID method.
     * This method calculates the next ID number to generate for the Product based on the saved data.
     * @return Returns the next ID number to generate.*/
    public int nextID() {

        ObservableList<Product> tempList = Inventory.getAllProducts();

        if (tempList.isEmpty()) {
            return 1;
        }

        int tempListSize = tempList.size();
        int lastID = tempList.get(tempListSize - 1).getId();
        return lastID + 1;
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
    private TextField addProductIDTxt;

    @FXML
    private TextField addProductNameTxt;

    @FXML
    private TextField addProductInvTxt;

    @FXML
    private TextField addProductPriceTxt;

    @FXML
    private TextField addProductMaxTxt;

    @FXML
    private TextField addProductMinTxt;



    @FXML
    private TextField addProductSearchTxt;

    @FXML
    private TableView<Part> productTopTableView;

    @FXML
    private TableColumn<Part, Integer> addProductTPartIDCol;

    @FXML
    private TableColumn<Part, String> addProductTPartNameCol;

    @FXML
    private TableColumn<Part, Integer> addProductTInventoryLevelCol;

    @FXML
    private TableColumn<Part, Double> addProductTppuCol;

    /** This is the action taken when the "Add" button is pressed.
     * This method adds the selected Parts to the Associated Parts list and to the Associated Parts Table.
     * This method also displays an Error Dialog Box if no item is selected to be added for Association.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void addProductTAdd(ActionEvent event) {

        if (productTopTableView.getSelectionModel().getSelectedItems().isEmpty()) {

            errorBox("No part was selected for association!" +
                    " \nPlease select a part to add to association.");

        } else {

            productBottomTableView.getItems().addAll(productTopTableView.getSelectionModel().getSelectedItems());

        }

    }



    @FXML
    private TableView<Part> productBottomTableView;

    @FXML
    private TableColumn<Part, Integer> addProductBPartIDCol;

    @FXML
    private TableColumn<Part, String> addProductBPartNameCol;

    @FXML
    private TableColumn<Part, Integer> addProductBInventoryLevelCol;

    @FXML
    private TableColumn<Part, Double> addProductBppuCol;

    /** This is the action taken when the "Remove Associated Part" button is pressed.
     * This method only Deletes the selected Associated Parts from the Associated Parts list and the Associated Parts Table.
     * A Confirmation Dialog Box is displayed to confirm deletion.
     * This method also displays an Error Dialog Box if no item is selected to be Deleted.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void addProductBRemoveAssociatedPart(ActionEvent event) {

        if (productBottomTableView.getSelectionModel().getSelectedItems().isEmpty()) {

            errorBox("No associated part was selected for removal!" +
                    " \nPlease select a part to remove association.");

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setContentText("Are you sure you want to delete this product?");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK){
                productBottomTableView.getItems().removeAll(productBottomTableView.getSelectionModel().getSelectedItems());
            }

        }

    }


    /** This is the action taken when the "Save" button is pressed.
     * This method gets the values entered into the text fields by the user to create a new Products object. This method then loads the Main Menu scene.
     * After creating the new Products object, the Associated Parts list is created.
     * This method displays an Error Dialog Box if incorrect field values are entered.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void addProductBSave(ActionEvent event) throws IOException {

        try {

            int stock = Integer.parseInt(addProductInvTxt.getText());
            int min = Integer.parseInt(addProductMinTxt.getText());
            int max = Integer.parseInt(addProductMaxTxt.getText());

            if (stock < min || stock > max){

                errorBox("""
                        Max must be greater than Min.\s
                        Min must be less than Max.\s
                        Inv must be between Max and Min""");

            } else {

                int id = Integer.parseInt(addProductIDTxt.getText());
                String name = addProductNameTxt.getText();
                double price = Double.parseDouble(addProductPriceTxt.getText());

                Product tempProduct = new Product(id, name, price, stock, min, max);

                for (Part i : tempAssociatedParts) {
                    tempProduct.addAssociatedPart(i);
                }

                Inventory.addProduct(tempProduct);

                sceneLoader(event, "/view/MainMenu.fxml");
            }

        } catch (NumberFormatException e) {

            errorBox("You entered invalid value types into the fields!" +
                    " \nPlease enter valid values into each field!");
        }


    }

    /** This is the action taken when the "Cancel" button is pressed.
     * This method calls the Scene Loader and loads the Main Menu Scene (passing the ActionEvent object and the file path of the Main Menu Scene).
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void addProductBCancel(ActionEvent event) throws IOException {
        sceneLoader(event, "/view/MainMenu.fxml");
    }


    /** This is an override Initialize method.
     * This method sets both the Parts and Associated Parts tables with the correct fields and values. This method also sets up the search function for Parts table.
     * @param url URL.
     * @param resourceBundle Resource Bundle.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addProductIDTxt.setDisable(true);
        addProductIDTxt.setText(String.valueOf(nextID()));

        productTopTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        productTopTableView.setItems(Inventory.getAllParts());
        addProductTPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductTPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductTInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductTppuCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productBottomTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        productBottomTableView.setItems(tempAssociatedParts);
        addProductBPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductBPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductBInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductBppuCol.setCellValueFactory(new PropertyValueFactory<>("price"));



        FilteredList<Part> partFilteredList = new FilteredList<>(Inventory.getAllParts(), s -> true);

        addProductSearchTxt.textProperty().addListener((observable, oldValue, newValue) -> {

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
        partSortedList.comparatorProperty().bind(productTopTableView.comparatorProperty());
        productTopTableView.setItems(partSortedList);

    }

}