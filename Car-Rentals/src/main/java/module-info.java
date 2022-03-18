module com.example.lab6_homeassignmentlast {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.lab6_homeassignmentlast to javafx.fxml;
    exports com.example.lab6_homeassignmentlast;
}