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
    private Button BtDashboard;

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
private void handleDashboardButtonAction() {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("FAdmin.fxml"));
        Stage stage = (Stage) BtDashboard.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Admin Dashboard");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

@FXML
private void handleKeluarButtonAction() {
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
private void handleMenuButtonAction() {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("FMakanan.fxml"));
        Stage stage = (Stage) btMenu.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}