package Controller;

import Model.Inventory;
import Model.InHouse;
import Model.Outsourced;
import Model.Part;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This is the Add Part Controller class, which implements the Initialization class.*/
public class AddPartController implements Initializable {

    /**This the Scene Loader method.
     * This method enables the application to load and direct to the desired scene after an event occurs.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent).
     * @param location This is the location of the desired scene's path file (.fxml, passed as a String).  */
    public void sceneLoader(ActionEvent event, String location) throws IOException {

        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource(location));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This is the nextID method.
     * This method calculates the next ID number to generate for the Part based on the saved data.
     * @return Returns the next ID number to generate.*/
    public int nextID() {

        ObservableList<Part> tempList = Inventory.getAllParts();

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
    private RadioButton addPartInHouseRB;

    @FXML
    private RadioButton addPartOutsourcedRB;

    @FXML
    private ToggleGroup addPartTG;


    /** This is the action taken when the "In-House" radio button is selected.
     * This method sets the unique subclass identifier label to "Machine ID" and configures our application to create an In-House Part object.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void inHouseSelected(ActionEvent event) {
        inHouseOrOutSourced.setText("Machine ID");
    }

    /** This is the action taken when the "Outsourced" radio button is selected.
     * This method sets the unique subclass identifier label to "Machine ID" and configures our application to create an Outsourced Part object.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void outsourcedSelected(ActionEvent event) {
        inHouseOrOutSourced.setText("Company Name");
    }



    @FXML
    private TextField addPartIDTxt;

    @FXML
    private TextField addPartNameTxt;

    @FXML
    private TextField addPartInvTxt;

    @FXML
    private TextField addPartPriceTxt;

    @FXML
    private TextField addPartMaxTxt;

    @FXML
    private TextField addPartMinTxt;

    @FXML
    private Label inHouseOrOutSourced;

    @FXML
    private TextField uniqueIDTxt;


    /** This is the action taken when the "Save" button is pressed.
     * This method gets the values entered into the text fields by the user to create a new Parts object. This method then loads the Main Menu scene.
     * This method displays an Error Dialog Box if incorrect field values are entered.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void addPartSave(ActionEvent event) throws IOException {

        try {

            int stock = Integer.parseInt(addPartInvTxt.getText());
            int min = Integer.parseInt(addPartMinTxt.getText());
            int max = Integer.parseInt(addPartMaxTxt.getText());

            if (stock < min || stock > max){

                errorBox("""
                        Max must be greater than Min.\s
                        Min must be less than Max.\s
                        Inv must be between Max and Min""");

            } else {

                int id = Integer.parseInt(addPartIDTxt.getText());
                String name = addPartNameTxt.getText();
                double price = Double.parseDouble(addPartPriceTxt.getText());

                if (addPartInHouseRB.isSelected()) {
                    int machineid = Integer.parseInt(uniqueIDTxt.getText());
                    Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineid));
                } else {
                    String companyName = uniqueIDTxt.getText();
                    Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));
                }

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
    void addPartCancel(ActionEvent event) throws IOException {
        sceneLoader(event, "/view/MainMenu.fxml");
    }


    /** This is an override Initialize method.
     * This method disables the Part ID text field, calls the nextID method, and pre-selects the In-House radio button.
     * @param url URL.
     * @param resourceBundle Resource Bundle.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addPartInHouseRB.setSelected(true);
        addPartIDTxt.setDisable(true);
        addPartIDTxt.setText(String.valueOf(nextID()));
    }

}