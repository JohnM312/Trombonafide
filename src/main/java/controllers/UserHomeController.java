/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import library.App;
import model.*;

/**
 *
 * @author portia
 */
public class UserHomeController implements Initializable {
    @FXML private Label lbl_title;
    @FXML private GridPane grid_books;
    private Library library;
    private User user;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        library = Library.getInstance();
        user = library.getCurrentUser();
        
        lbl_title.setText("Welcome " + user.getFirstName() + " " + user.getLastName());
        
        displayUserItems();
    }   
    
    private void displayUserItems(){
        ArrayList<Loan> loans = user.getLoans();
        for (int i=0; i < loans.size(); i++) {
            Loan loan = loans.get(i);
            Book book = loan.getBook();
            VBox vbox = new VBox();
            Label bookTitle = new Label(book.getTitle());
            bookTitle.setFont(new Font(14));
            vbox.getChildren().add(bookTitle);
            Image image = new Image(getClass().getResourceAsStream("/images/" + loan.getBook().getImageName()));
            ImageView image_book = new ImageView(image);
            image_book.setFitWidth(100);
            image_book.setPreserveRatio(true);
            vbox.getChildren().add(image_book);
            vbox.getStyleClass().add("book-grid-item");

            grid_books.add(vbox, i,0);

            image_book.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    /*Dialog<String> dialog = new Dialog<String>();
                    dialog.setTitle(book.getTitle());
                    dialog.setContentText("This is a sample dialog");
                    //Adding buttons to the dialog pane
                    ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().add(type);
                                
                    dialog.showAndWait();*/

                    Dialog<Loan> bookDialog = new BookItemDialog(loan);
                    bookDialog.showAndWait();
                }
                
            });
        }
    }
    
}