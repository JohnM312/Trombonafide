/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Library;
import model.Email;
import library.App;

/**
 *
 * @author portia
 */
public class SignUpController implements Initializable {
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private TextField txt_first_name;
    @FXML
    private TextField txt_last_name;
    @FXML
    private TextField txt_age;
    @FXML
    private TextField txt_phone_number;
    @FXML
    private Label lbl_error;

    @FXML
    private void btnSignupClicked(MouseEvent event) throws IOException {
        String email = txt_email.getText();
        String password = txt_password.getText();
        String firstName = txt_first_name.getText();
        String lastName = txt_last_name.getText();

        // check for empty fields
        if (email.equals("") || password.equals("") || firstName.equals("") || lastName.equals("")) {
            lbl_error.setText("Sorry, You cannot leave blank fields");
            return;
        }

        Library library = Library.getInstance();

        if (!library.createAccount(email, firstName, lastName)) {
            lbl_error.setText("Sorry, this user couldn't be created.");
            return;
        }

        library.login(email);
        User user = library.getCurrentUser();
        App.setRoot("user_home");
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        App.setRoot("home");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}