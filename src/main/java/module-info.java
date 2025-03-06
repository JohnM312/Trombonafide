module com.trombonafide {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires jfugue;
    requires com.fasterxml.jackson.databind;

    opens com.trombonafide to javafx.fxml;
    exports com.trombonafide;
    exports com.trombonafide.util;

    opens com.model to com.fasterxml.jackson.databind;
    exports com.model;
}
