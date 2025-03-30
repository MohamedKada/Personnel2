module com.example.personnel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    opens com.example.personnel to javafx.fxml;
    opens com.example.personnel.controllers to javafx.fxml;
    opens com.example.personnel.models to javafx.base;
    
    exports com.example.personnel;
    exports com.example.personnel.controllers;
    exports com.example.personnel.models;
}