package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
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

/** This is the Modify Part Controller class, which implements the Initialization class.*/
public class ModifyPartController implements Initializable {

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

    /** This method allows for the transfer of a Part object into the Modify Part scene.
     * This method takes the fields of the Parts object and pre-populates the text fields with the corresponding data. This method also saves the index of the Part.
     * @param part The Part to Modify.
     * @param index Index of the Part that is being modified.*/
    public void transferPart(Part part, int index) {
        modifyPartIDTxt.setText(String.valueOf(part.getId()));
        modifyPartNameTxt.setText(part.getName());
        modifyPartInvTxt.setText(String.valueOf(part.getStock()));
        modifyPartPriceTxt.setText(String.valueOf(part.getPrice()));
        modifyPartMinTxt.setText(String.valueOf(part.getMin()));
        modifyPartMaxTxt.setText(String.valueOf(part.getMax()));

        if (part instanceof InHouse) {
            modifyPartInHouseRB.setSelected(true);
            uniqueID.setText(String.valueOf(((InHouse) part).getMachineid()));
            inHouseOrOutSourced.setText("Machine ID");
        } else if (part instanceof Outsourced) {
            modifyPartOutsourcedRB.setSelected(true);
            uniqueID.setText(((Outsourced) part).getCompanyName());
            inHouseOrOutSourced.setText("Company Name");
        }

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
    private RadioButton modifyPartInHouseRB;

    @FXML
    private RadioButton modifyPartOutsourcedRB;

    @FXML
    private ToggleGroup modifyPartTG;


    /** This is the action taken when the "In-House" radio button is selected.
     * This method sets the unique subclass identifier label to "Machine ID" and configures our application to update the current object to an In-House Part object.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void inHouseSelected(ActionEvent event) {
        inHouseOrOutSourced.setText("Machine ID");
    }

    /** This is the action taken when the "Outsourced" radio button is selected.
     * This method sets the unique subclass identifier label to "Machine ID" and configures our application to update the current object to an Outsourced Part object.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void outsourcedSelected(ActionEvent event) {
        inHouseOrOutSourced.setText("Company Name");
    }



    @FXML
    private TextField modifyPartIDTxt;

    @FXML
    private TextField modifyPartNameTxt;

    @FXML
    private TextField modifyPartInvTxt;

    @FXML
    private TextField modifyPartPriceTxt;

    @FXML
    private TextField modifyPartMaxTxt;

    @FXML
    private TextField modifyPartMinTxt;

    @FXML
    private Label inHouseOrOutSourced;

    @FXML
    private TextField uniqueID;


    /** This is the action taken when the "Save" button is pressed.
     * This method gets the values entered into the text fields by the user to update the Parts object. This method then loads the Main Menu scene.
     * This method displays an Error Dialog Box if incorrect field values are entered.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void modifyPartSave(ActionEvent event) throws IOException {

        try  {

            int stock = Integer.parseInt(modifyPartInvTxt.getText());
            int min = Integer.parseInt(modifyPartMinTxt.getText());
            int max = Integer.parseInt(modifyPartMaxTxt.getText());

            if (stock < min || stock > max){

                errorBox("""
                        Max must be greater than Min.\s
                        Min must be less than Max.\s
                        Inv must be between Max and Min""");

            } else {

                int id = Integer.parseInt(modifyPartIDTxt.getText());
                String name = modifyPartNameTxt.getText();
                double price = Double.parseDouble(modifyPartPriceTxt.getText());

                if (modifyPartInHouseRB.isSelected()) {
                    int machineid = Integer.parseInt(uniqueID.getText());
                    Inventory.updatePart(transferredIndex, new InHouse(id, name, price, stock, min, max, machineid));
                } else {
                    String companyName = uniqueID.getText();
                    Inventory.updatePart(transferredIndex, new Outsourced(id, name, price, stock, min, max, companyName));
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
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void modifyPartCancel(ActionEvent event) throws IOException {
        sceneLoader(event, "/view/MainMenu.fxml");
    }


    /** This is an override Initialize method.
     * This method disables the Part ID text field.
     * @param url URL.
     * @param resourceBundle Resource Bundle.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modifyPartIDTxt.setDisable(true);
    }
}