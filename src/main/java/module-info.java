module com.trombonafide {
    requires java.base;
    requires java.desktop;
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires jfugue;
    requires com.fasterxml.jackson.databind;

    requires junit; 
    opens com.trombonafide to javafx.fxml;
    exports com.trombonafide;
    exports com.trombonafide.util;

    opens com.model to com.fasterxml.jackson.databind;
    exports com.model;
}
