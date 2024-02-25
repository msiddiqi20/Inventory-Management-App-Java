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

/** This is the Modify Product Controller class, which implements the Initialization class.*/
public class ModifyProductController implements Initializable {

    private ObservableList<Part> tempAssociatedParts = FXCollections.observableArrayList();
    private int transferredIndex;

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

    /** This method allows for the transfer of a Product object into the Modify Product scene.
     * This method takes the fields of the Product object and pre-populates the text fields with the corresponding data. This method also saves the index of the Product.
     * This method also sets up the Classicist Parts table that correlates with the selected Product.
     * @param product The Part to Modify.
     * @param index Index of the Part that is being modified.*/
    public void transferProduct(Product product, int index) {
        modifyProductIDTxt.setText(String.valueOf(product.getId()));
        modifyProductNameTxt.setText(product.getName());
        modifyProductInvTxt.setText(String.valueOf(product.getStock()));
        modifyProductPriceTxt.setText(String.valueOf(product.getPrice()));
        modifyProductMaxTxt.setText(String.valueOf(product.getMax()));
        modifyProductMinTxt.setText(String.valueOf(product.getMin()));

        tempAssociatedParts = product.getAllAssociatedParts();
        mproductBottomTableView.setItems(tempAssociatedParts);
        transferredIndex = index;
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
    private TextField modifyProductIDTxt;

    @FXML
    private TextField modifyProductNameTxt;

    @FXML
    private TextField modifyProductInvTxt;

    @FXML
    private TextField modifyProductPriceTxt;

    @FXML
    private TextField modifyProductMaxTxt;

    @FXML
    private TextField modifyProductMinTxt;



    @FXML
    private TextField modifyProductSearchTxt;

    @FXML
    private TableView<Part> mproductTopTableView;

    @FXML
    private TableColumn<Part, Integer> modifyProductTPartIDCol;

    @FXML
    private TableColumn<Part, String> modifyProductTPartNameCol;

    @FXML
    private TableColumn<Part, Integer> modifyProductTInventoryLevelCol;

    @FXML
    private TableColumn<Part, Double> modifyProductTppuCol;

    /** This is the action taken when the "Add" button is pressed.
     * This method adds the selected Parts to the Associated Parts list and to the Associated Parts Table.
     * This method also displays an Error Dialog Box if no item is selected to be added for Association.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void modifyProductTAdd(ActionEvent event) {

        if (mproductTopTableView.getSelectionModel().getSelectedItems().isEmpty()) {

            errorBox("No part was selected for association!" +
                    " \nPlease select a part to add to association.");

        } else {

            mproductBottomTableView.getItems().addAll(mproductTopTableView.getSelectionModel().getSelectedItems());
        }

    }



    @FXML
    private TableView<Part> mproductBottomTableView;

    @FXML
    private TableColumn<Part, Integer> modifyProductBPartIDCol;

    @FXML
    private TableColumn<Part, String> modifyProductBPartNameCol;

    @FXML
    private TableColumn<Part, Integer> modifyProductBInventoryLevelCol;

    @FXML
    private TableColumn<Part, Double> modifyProductBppuCol;

    /** This is the action taken when the "Remove Associated Part" button is pressed.
     * This method only Deletes the selected Associated Parts from the Associated Parts list and the Associated Parts Table.
     * A Confirmation Dialog Box is displayed to confirm deletion.
     * This method also displays an Error Dialog Box if no item is selected to be Deleted.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void modifyProductBRemoveAssociatedPart(ActionEvent event) {

        if (mproductBottomTableView.getSelectionModel().getSelectedItems().isEmpty()) {

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
                mproductBottomTableView.getItems().removeAll(mproductBottomTableView.getSelectionModel().getSelectedItems());
            }

        }

    }


    /** This is the action taken when the "Save" button is pressed.
     * This method gets the values entered into the text fields by the user to update the Products object. This method then loads the Main Menu scene.
     * After updating the Products object, the Associated Parts list is also updated.
     * This method displays an Error Dialog Box if incorrect field values are entered.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void modifyProductBSave(ActionEvent event) throws IOException {

        try {

            int stock = Integer.parseInt(modifyProductInvTxt.getText());
            int min = Integer.parseInt(modifyProductMinTxt.getText());
            int max = Integer.parseInt(modifyProductMaxTxt.getText());

            if (stock < min || stock > max){

                errorBox("""
                        Max must be greater than Min.\s
                        Min must be less than Max.\s
                        Inv must be between Max and Min""");

            } else {

                int id = Integer.parseInt(modifyProductIDTxt.getText());
                String name = modifyProductNameTxt.getText();
                double price = Double.parseDouble(modifyProductPriceTxt.getText());


                Product tempProduct = new Product(id, name, price, stock, min, max);

                for (Part i : tempAssociatedParts) {
                    tempProduct.addAssociatedPart(i);
                }

                Inventory.updateProduct(transferredIndex, tempProduct);

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
    void modifyProductBCancel(ActionEvent event) throws IOException {
        sceneLoader(event, "/view/MainMenu.fxml");
    }


    /** This is an override Initialize method.
     * This method sets both the Parts and Associated Parts tables with the correct fields and values. This method also sets up the search function for Parts table.
     * @param url URL.
     * @param resourceBundle Resource Bundle.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        modifyProductIDTxt.setDisable(true);

        mproductTopTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        mproductTopTableView.setItems(Inventory.getAllParts());
        modifyProductTPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductTPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductTInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductTppuCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        mproductBottomTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        mproductBottomTableView.setItems(tempAssociatedParts);
        modifyProductBPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductBPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductBInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductBppuCol.setCellValueFactory(new PropertyValueFactory<>("price"));



        FilteredList<Part> partFilteredList = new FilteredList<>(Inventory.getAllParts(), s -> true);

        modifyProductSearchTxt.textProperty().addListener((observable, oldValue, newValue) -> {

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
        partSortedList.comparatorProperty().bind(mproductTopTableView.comparatorProperty());
        mproductTopTableView.setItems(partSortedList);

    }
}