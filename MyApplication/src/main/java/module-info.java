/** Module*/
module Main_Program.myapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens Main_Program.myapplication to javafx.fxml;
    exports Main_Program.myapplication;

    opens Controller to javafx.fxml;
    exports Controller;

    opens Model to javafx.fxml;
    exports Model;

}