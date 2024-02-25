package Main_Program.myapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/** The Inventory Management Application class, which extends the Application class.*/
public class InventoryManagementApplication extends Application {

    /** The start method.
     * This method starts the application.
     * FUTURE ENHANCEMENT: A possible future enhancement that can brought to take this application to the next level is to save and exit the data in a database.
     * Currently, this application only stores new data temporarily and any pre-populated data is hard coded into the program.
     * Using a database, we can make this a full-fledged application.
     * @param stage Stage.*/
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryManagementApplication.class.getResource("/view/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 500);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /** The main method of the application.
     * This method launches the application.
     * @param args args*/
    public static void main(String[] args) {
        launch();
    }

    
}