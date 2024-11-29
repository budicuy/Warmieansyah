package myapp;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class FAbout extends Application {

    @FXML
    private Button btDash;

    @FXML
    private Button btMenu;

    @FXML
    private Button btKeluar;

    public static void main(String[] args) {
        launch(args);

    }
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FAbout.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("About");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



@FXML
private void Dash() {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("FAdmin.fxml"));
        Stage stage = (Stage) btDash.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Admin");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

@FXML
private void Keluar() {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("FLogin.fxml"));
        Stage stage = (Stage) btKeluar.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
@FXML
private void Menu() {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("FMakanan.fxml"));
        Stage stage = (Stage) btMenu.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Menu Makanan");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}